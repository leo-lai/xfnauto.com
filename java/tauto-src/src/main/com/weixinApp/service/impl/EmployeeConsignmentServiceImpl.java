package main.com.weixinApp.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.Test.HttpClientUtil;
import main.com.allInPay.utils.AllInPayUtil;
import main.com.car.dao.dao.CarBrandDao;
import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarFamilyDao;
import main.com.car.dao.dao.CarInteriorDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.po.CarBrand;
import main.com.car.dao.po.CarColour;
import main.com.car.dao.po.CarFamily;
import main.com.car.dao.po.CarInterior;
import main.com.car.dao.vo.CarsVo;
import main.com.constant.LogisticsGoodsCarState;
import main.com.constant.LogisticsconsignmentState;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.logistics.dao.dao.LogisticsConsignmentDao;
import main.com.logistics.dao.dao.LogisticsDedicatedLineDao;
import main.com.logistics.dao.dao.LogisticsDistributionDao;
import main.com.logistics.dao.dao.LogisticsDriverDao;
import main.com.logistics.dao.dao.LogisticsDynamicLineDao;
import main.com.logistics.dao.dao.LogisticsGoodsCarCostsDao;
import main.com.logistics.dao.dao.LogisticsGoodsCarDao;
import main.com.logistics.dao.po.LogisticsConsignment;
import main.com.logistics.dao.po.LogisticsGoodsCar;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.dao.vo.LogisticsCarVo;
import main.com.logistics.dao.vo.LogisticsConsignmentVo;
import main.com.logistics.dao.vo.LogisticsDedicatedLineVo;
import main.com.logistics.dao.vo.LogisticsDistributionVo;
import main.com.logistics.dao.vo.LogisticsDriverVo;
import main.com.logistics.dao.vo.LogisticsDynamicLineInfoVo;
import main.com.logistics.dao.vo.LogisticsDynamicLineVo;
import main.com.logistics.dao.vo.LogisticsGoodsCarCostsVo;
import main.com.logistics.dao.vo.LogisticsGoodsCarVo;
import main.com.stock.dao.po.ConsumerOrderUser;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.vo.OrganizationVo;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.HTTPRequest;
import main.com.utils.IdcardValidator;
import main.com.utils.MD5Encoder;
import main.com.utils.Number;
import main.com.utils.weixin.PayUtils;
import main.com.weixin.dao.po.WeixinAppToken;
import main.com.weixin.dao.vo.WeixinAppTokenVo;
import main.com.weixin.service.WeixinAppTokenService;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.utils.Value;
import main.com.utils.redis.RedisClient;
import main.com.weixinApp.dao.dao.ConsumerOrderUserDao;
import main.com.weixinApp.service.EmployeeConsignmentService;

@Service
public class EmployeeConsignmentServiceImpl extends BaseServiceImpl<LogisticsConsignment> implements EmployeeConsignmentService {

	@Autowired
	LogisticsConsignmentDao consignmentDao;
	
	@Autowired
	LogisticsGoodsCarDao logisticsGoodsCarDao;
	
	@Autowired
	CarBrandDao carBrandDao;
	
	@Autowired
	CarFamilyDao carFamilyDao;
	
	@Autowired
	WeixinAppTokenService weixinAppTokenService;
	
	@Autowired
	LogisticsDedicatedLineDao logisticsDedicatedLineDao;
	
	@Autowired
	LogisticsDynamicLineDao logisticsDynamicLineDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	LogisticsDistributionDao logisticsDistributionDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	LogisticsGoodsCarCostsDao logisticsGoodsCarCostsDao;
	
	@Autowired
	ConsumerOrderUserDao consumerOrderUserDao;
	
	@Autowired
	CarColourDao carColourDao;
	
	@Autowired
	CarInteriorDao carInteriorDao;
	
	@Autowired
	LogisticsDriverDao logisticsDriverDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Override
	protected BaseDao<LogisticsConsignment> getBaseDao() {
		return consignmentDao;
	}

	@Override
	public Result consignmentList(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		Map<String,Object> params = getParams(search,users);
		List<LogisticsConsignmentVo> consignmentVos = consignmentDao.selectJoin(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = consignmentDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(LogisticsConsignmentVo consignmentVo : consignmentVos) {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("consignmentId", consignmentVo.getConsignmentId());
			map.put("consignmentCode", consignmentVo.getConsignmentCode());
			map.put("createDate", DateUtil.format(consignmentVo.getCreateDate()));
			map.put("appointmentTime", DateUtil.format(consignmentVo.getAppointmentTime()));
			map.put("appointmentTimeDate", consignmentVo.getAppointmentTimeDate());
			map.put("amount", consignmentVo.getAmount() != null?consignmentVo.getAmount().doubleValue():0);
			map.put("startingPointAddress", consignmentVo.getStartingPointAddress());
			map.put("destinationAddress", consignmentVo.getDestinationAddress());
			map.put("consignmentType", consignmentVo.getConsignmentType());
			map.put("consignmentState", consignmentVo.getConsignmentState());
			map.put("consignmentStateName", LogisticsconsignmentState.getMsgByCode(consignmentVo.getConsignmentState()));
//			map.put("consignmentInPayState", consignmentVo.getConsignmentInPayState());
//			if(consignmentVo.getGoodsCarVos() == null) {
//				map.put("goodsCarNumber", 0);
//			}else {
//				map.put("goodsCarNumber", consignmentVo.getGoodsCarVos().size());
//			}
//			if(consignmentVo.getGoodsCarVos() == null) {
//				map.put("goodsCarNumber", 0);
//			}else {
//				map.put("goodsCarNumber", consignmentVo.getGoodsCarVos().size());
//				map.put("list", getGoodsList(consignmentVo.getGoodsCarVos()));//暂时屏蔽车列表分组
//			}
			map.put("list", consignmentVo.getGoodsCarVos());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}
	
	/**
	 * LIST分组
	 * @param goodsCarVos
	 * @return
	 */
	public List<Map<String,Object>> getGoodsList(List<LogisticsGoodsCarVo> goodsCarVos){		
		List<Map<String, Object>> goodsMaps = new ArrayList<Map<String,Object>>();
		if(goodsCarVos == null || goodsCarVos.size() <= 0) {
			return goodsMaps;
		}
		 /*2、分组算法**/
	    Map<String, List<LogisticsGoodsCarVo>> goodsMapAfter = new HashMap<String, List<LogisticsGoodsCarVo>>();
	    for (LogisticsGoodsCarVo goodsCarVo : goodsCarVos) {
	      List<LogisticsGoodsCarVo> tempList = goodsMapAfter.get(goodsCarVo.getCarsId()+"");//goodsCarVo.getBrandId().toString()+goodsCarVo.getFamilyId().toString()
	      /*如果取不到数据,那么直接new一个空的ArrayList**/
	      if (tempList == null) {
	        tempList = new ArrayList<>();
	        tempList.add(goodsCarVo);
	        goodsMapAfter.put(goodsCarVo.getCarsId()+"", tempList);//(goodsCarVo.getBrandId().toString()+goodsCarVo.getFamilyId().toString())
	      }
	      else {
	        /*某个goodsCarVo之前已经存放过了,则直接追加数据到原来的List里**/
	        tempList.add(goodsCarVo);
	      }
	    }
	    
	    /**
	     * 根据前端显示剖析
	     */
	    for(String goodsId : goodsMapAfter.keySet()) {
	    	Map<String,Object> goodsMap = new HashMap<String, Object>();
	    	goodsMap.put("carsName", goodsMapAfter.get(goodsId).get(0).getBrandName() + goodsMapAfter.get(goodsId).get(0).getFamilyId());
	    	goodsMap.put("number", goodsMapAfter.get(goodsId).size());
	    	goodsMaps.add(goodsMap);
	    }
	    return goodsMaps;
	}

	public Map<String,Object> getParams(LogisticsConsignmentSearch search,SystemUsers systemUsers){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getKeywords())) {
			params.put("keywords", search.getKeywords());
		}
		if(StringUtil.isNotEmpty(search.getConsignmentState())) {
			params.put("consignmentState", search.getConsignmentState());
		}
		if(StringUtil.isNotEmpty(search.getConsignmentType())) {
			params.put("consignmentType", search.getConsignmentType());
		}
		if(StringUtil.isNotEmpty(search.getConsignmentInPayState())) {
			params.put("consignmentInPayState", search.getConsignmentInPayState());
		}
		if(StringUtil.isNotEmpty(search.getStartDate())) {
			params.put("startDate", search.getStartDate());
		}
		if(StringUtil.isNotEmpty(search.getEndDate())) {
			params.put("endDate", search.getEndDate());
		}
		if(StringUtil.isNotEmpty(search.getEndDate())) {
			params.put("endDate", search.getEndDate());
		}
//		params.put("orgCodeLevel", systemUsers.getOrgCode()); 
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	public Result consignmentInfo(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(search.getConsignmentId())) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体的托运单查看");
			return result;
		}
		LogisticsConsignmentVo logisticsConsignmentVo = consignmentDao.selectByIdJoin(search.getConsignmentId());
		if(logisticsConsignmentVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的托运单不存在或者已取消，请重新选择");
			return result;
		}
		//查询接车人
		Map<String, Object> params = new HashMap<>();
		if(StringUtil.isNotEmpty(logisticsConsignmentVo.getExtractTheCarPersonIds())) {
			params.put("ids", new HashSet<>(Arrays.asList((logisticsConsignmentVo.getExtractTheCarPersonIds().split(GeneralConstant.SystemBoolean.SPLIT)))));
			logisticsConsignmentVo.setExtractTheCarPerson(consumerOrderUserDao.select(params));
		}
		//查询交车人
		if(StringUtil.isNotEmpty(logisticsConsignmentVo.getLeaveTheCarPersonIds())) {
			params.put("ids", new HashSet<>(Arrays.asList((logisticsConsignmentVo.getLeaveTheCarPersonIds().split(GeneralConstant.SystemBoolean.SPLIT)))));
			logisticsConsignmentVo.setLeaveTheCarPerson(consumerOrderUserDao.select(params));
		}
		Map<String, Object> map = new HashMap<>();
		map.put("logisticsConsignment", logisticsConsignmentVo);
		result.setOK(ResultCode.CODE_STATE_200, "", logisticsConsignmentVo);
//		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}

	@Override
	@Transactional
	public Result consignmentEdit(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		LogisticsConsignmentVo logisticsConsignmentVo = null;
		List<LogisticsGoodsCarVo> goodsCarVos = new ArrayList<>();
		try {
			if(StringUtil.isEmpty(search.getConsignmentId())) {
				logisticsConsignmentVo = new LogisticsConsignmentVo();
				logisticsConsignmentVo.setCreateDate(new Date());
				logisticsConsignmentVo.setIsDelete(false);
				logisticsConsignmentVo.setIsCancel(false);
				if(StringUtil.isNotEmpty(search.getOrgId())) {
					logisticsConsignmentVo.setOrgId(search.getOrgId());
					OrganizationVo organization = organizationDao.selectById(search.getOrgId());
					if(organization == null) {
						result.setError(ResultCode.CODE_STATE_4005, "你选择的门店不存在或者已被禁用，请重新选择");
						return result;
					}
					logisticsConsignmentVo.setOrgName(organization.getShortName());
				}
				logisticsConsignmentVo.setConsignmentState(GeneralConstant.ConsignmentState.START);
				logisticsConsignmentVo.setConsignmentCode(consignmentDao.getCode());
				logisticsConsignmentVo.setConsignmentInPayState(GeneralConstant.OrderInPayState.un_paid);
				logisticsConsignmentVo.setSystemUserId(users.getUsersId());
				logisticsConsignmentVo.setSystemUserName(users.getRealName());
				logisticsConsignmentVo.setSystemUserPhone(users.getPhoneNumber());
				if(StringUtil.isNotEmpty(search.getAppointmentTime())) {
					System.out.println("写入时间");
					logisticsConsignmentVo.setAppointmentTime(DateUtil.format(search.getAppointmentTime(),"yyyy-MM-dd hh:mm"));
					System.out.println("接收的时间："+search.getAppointmentTime());
					System.out.println("时间格式："+DateUtil.format(search.getAppointmentTime(),"yyyy-MM-dd HH:mm"));
				}else {
					result.setError(ResultCode.CODE_STATE_4005, "请输入预约送时间");
					return result;
				}
//				logisticsConsignmentVo.setPurchasersId(search.getUsersId());
			}else {
				 logisticsConsignmentVo = consignmentDao.selectByIdJoin(search.getConsignmentId());
				if(!logisticsConsignmentVo.getOrgId().equals(users.getOrgId())) {
					result.setError(ResultCode.CODE_STATE_4005, "你选择的数据错误，你没有编辑该数据的权限");
					return result;
				}
			}
			if(StringUtil.isNotEmpty(search.getPurchasersName())) {
				logisticsConsignmentVo.setPurchasersName(search.getPurchasersName());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "请输入联系人姓名");
				return result;
			}
			if(StringUtil.isNotEmpty(search.getPurchasersPhone())) {
				if(!Number.isGoodNumber(search.getPurchasersPhone()) && search.getPurchasersPhone().length() != 11) {
					result.setError(ResultCode.CODE_STATE_4005, "手机号码格式错误");
					return result;
				}
				logisticsConsignmentVo.setPurchasersPhone(search.getPurchasersPhone());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "请输入联系电话");
				return result;
			}
			
			if(StringUtil.isNotEmpty(search.getStartingPointAddress())) {
				logisticsConsignmentVo.setStartingPointAddress(search.getStartingPointAddress());
				logisticsConsignmentVo.setStartingPointLatitude(search.getStartingPointLatitude());
				logisticsConsignmentVo.setStartingPointLongitude(search.getStartingPointLongitude());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "请输入起点位置");
				return result;
			}
			if(StringUtil.isNotEmpty(search.getDestinationAddress())) {
				logisticsConsignmentVo.setDestinationAddress(search.getDestinationAddress());
				logisticsConsignmentVo.setDestinationLatitude(search.getDestinationLatitude());
				logisticsConsignmentVo.setDestinationLongitude(search.getDestinationLongitude());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "请输入终点位置");
				return result;
			}
			if(StringUtil.isNotEmptyMoreThenZero(search.getAmount())) {
				logisticsConsignmentVo.setAmount(new BigDecimal(search.getAmount()).setScale(2, BigDecimal.ROUND_DOWN));
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "运输费用错误");
				return result;
			}
			if(StringUtil.isNotEmpty(search.getConsignmentType())) {//
				logisticsConsignmentVo.setConsignmentType(search.getConsignmentType());
				logisticsConsignmentVo.setConsignmentTypeLineId(search.getConsignmentTypeLineId());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "请选择配送方式");
				return result;
			}
			goodsCarVos = search.getGoodsCarVos();
			if(goodsCarVos == null || goodsCarVos.size() <= 0) {
				result.setError(ResultCode.CODE_STATE_4005, "请添加托运车辆");
				return result;
			}
//			Set<String> set = new HashSet<>();//新建单不会有车架号
			for(LogisticsGoodsCarVo goodsCar : goodsCarVos) {
				//考虑车辆的运费
				LogisticsDynamicLineVo dynamicLineVo = null;
				if(search.getConsignmentType().equals(GeneralConstant.ConsignmentState.TYPEPOINT)) {
					List<LogisticsDynamicLineVo> dynamicLineVos = logisticsDynamicLineDao.selectJoin(new HashMap<String,Object>());
					if(dynamicLineVos == null || dynamicLineVos.size() <= 0) {
						dynamicLineVos = logisticsDynamicLineDao.select(new HashMap<String,Object>());
						if(dynamicLineVos == null || dynamicLineVos.size() <= 0) {
							result.setError("非常抱歉，系统暂不支持点对点配送");
							return result;
						}
					}
					dynamicLineVo = dynamicLineVos.get(0);
				}
				LogisticsGoodsCarCostsVo carCostsVo = null;
				if(goodsCar.getGoodsCarId() == null) {
					carCostsVo = new LogisticsGoodsCarCostsVo();
				}else{
					carCostsVo = logisticsGoodsCarCostsDao.selectByGoodsCarId(goodsCar.getGoodsCarId());
					if(carCostsVo == null) {
						carCostsVo = new LogisticsGoodsCarCostsVo();
					}
				}
				CarsVo carsVo = carsDao.selectById(goodsCar.getCarsId());
				if(carsVo == null) {
					result.setError("你选择的车类型数据有误，请重新选择");
					return result;
				}
				goodsCar.setBrandId(carsVo.getBrandId());
				goodsCar.setBrandName(carsVo.getBrandName());
				goodsCar.setFamilyId(carsVo.getFamilyId());
				goodsCar.setFamilyName(carsVo.getFamilyName());
				if(StringUtil.isNotEmptyMoreThenZero(goodsCar.getColourId())) {
					CarColour colour = carColourDao.selectById(goodsCar.getColourId());
					if(colour != null) {
						goodsCar.setColourId(colour.getCarColourId());
						goodsCar.setColourName(colour.getCarColourName());
//						result.setError("你选择的车身颜色数据有误，请重新选择");
//						return result;
					}
				}
				if(StringUtil.isNotEmptyMoreThenZero(goodsCar.getInteriorId())) {
					CarInterior interior = carInteriorDao.selectById(goodsCar.getInteriorId());
					if(interior != null) {
						goodsCar.setInteriorId(interior.getInteriorId());
						goodsCar.setInteriorName(interior.getInteriorName());
//						result.setError("你选择的内饰颜色数据有误，请重新选择");
//						return result;
					}
					
				}
				
				if(carCostsVo.getLogisticsGoodsCarCostsId() == null) {
					//实际价格 - 预测价格 = 总加价/总优惠
					if(search.getAmount().compareTo(search.getEstimateAmount()) >= 0) {
						carCostsVo.setPriceMarkup(new BigDecimal((search.getAmount() - search.getEstimateAmount())/goodsCarVos.size()).setScale(2, BigDecimal.ROUND_DOWN));
					}else {
						carCostsVo.setDiscount(new BigDecimal((search.getEstimateAmount() - search.getAmount())/goodsCarVos.size()).setScale(2, BigDecimal.ROUND_DOWN));
					}
					carCostsVo.setCostsAmount(new BigDecimal(search.getAmount()/goodsCarVos.size()).setScale(2, BigDecimal.ROUND_DOWN));
					if(search.getConsignmentType().equals(GeneralConstant.ConsignmentState.TYPEPOINT)) {//点对点
						carCostsVo.setLineType(GeneralConstant.ConsignmentState.TYPEPOINT);
						carCostsVo.setInitiateRate(new BigDecimal(search.getInitiateRate()/goodsCarVos.size()).setScale(2, BigDecimal.ROUND_DOWN));//起步价
						carCostsVo.setOverflow(new BigDecimal(search.getOverflow()/goodsCarVos.size()).setScale(2, BigDecimal.ROUND_DOWN));//溢出价
//						 if(carsVo == null) {
//							 carCostsVo.setThePriceAdditional(new BigDecimal(0));
//						 }else 
						 if(carsVo.getPrice() >0 && carsVo.getPrice() <= 100000f) {
							 carCostsVo.setThePriceAdditional(dynamicLineVo.getGradeFirst());
						 }else if(carsVo.getPrice() >100000f && carsVo.getPrice() <= 200000f) {
							 carCostsVo.setThePriceAdditional(dynamicLineVo.getGradeSecond());
						 }else if(carsVo.getPrice() >200000f && carsVo.getPrice() <= 300000f) {
							 carCostsVo.setThePriceAdditional(dynamicLineVo.getGradeThird());
						 }else if(carsVo.getPrice() >300000f && carsVo.getPrice() <= 400000f) {
							 carCostsVo.setThePriceAdditional(dynamicLineVo.getGradeFour());
						 }else if(carsVo.getPrice() >400000f && carsVo.getPrice() <= 600000f) {
							 carCostsVo.setThePriceAdditional(dynamicLineVo.getGradeFive());
						 }else if(carsVo.getPrice() >=600000f) {
							 carCostsVo.setThePriceAdditional(dynamicLineVo.getGradeSix());
						 }
					}else {//专线
						carCostsVo.setLineType(GeneralConstant.ConsignmentState.TYPELINE);
						carCostsVo.setLineAmount(new BigDecimal(search.getAmount()/goodsCarVos.size()).setScale(2, BigDecimal.ROUND_DOWN));
					}
				}
				goodsCar.setCarCostsVo(carCostsVo);
//				CarBrand brand = carBrandDao.selectById(goodsCar.getBrandId());
//				if(brand == null) {
//					result.setError("品牌选择错误");
//					return result;
//				}
//				CarFamily carFamily = carFamilyDao.selectById(goodsCar.getFamilyId());
//				if(carFamily == null) {
//					result.setError("车系选择错误");
//					return result;
//				}
//				if(!set.add(goodsCar.getFrameNumber())) {//撤销车架号的检测 2018 03 01
//					result.setError("你的车架号存在重复，请核对数据");
//					return result;
//				}
			}
			
			if(search.getLeaveTheCarPerson() != null && search.getLeaveTheCarPerson().size() > 0) {
				checkPersion(search.getLeaveTheCarPerson(), "交车人", result);
				if(!result.isSuccess()) {
					return result;
				}
				logisticsConsignmentVo.setLeaveTheCarPersonIds(result.getData().toString());
			}
			if(search.getExtractTheCarPerson() != null && search.getExtractTheCarPerson().size() > 0) {
				checkPersion(search.getExtractTheCarPerson(), "提车人", result);
				if(!result.isSuccess()) {
					return result;
				}
				logisticsConsignmentVo.setExtractTheCarPersonIds(result.getData().toString());
			}
//			if(StringUtil.isNotEmpty(search.getGoodsCarInfo())) {
//				String[] goodsCarsStr = search.getGoodsCarInfo().split(GeneralConstant.SystemBoolean.SPLIT_);//剪下第一层
//				Set<String> set = new HashSet<>();
//				if(logisticsConsignmentVo.getGoodsCarVos() != null && logisticsConsignmentVo.getGoodsCarVos().size() > 0) {
//					for(LogisticsGoodsCar goodsCarsOld : logisticsConsignmentVo.getGoodsCarVos()) {
//						goodsCars.add(goodsCarsOld);
//					}
//				}
//				for(String goods : goodsCarsStr) {
//					LogisticsGoodsCar goodsCar = new LogisticsGoodsCar();
//					String[] str = goods.split(GeneralConstant.SystemBoolean.SPLIT);
//					CarBrand brand = carBrandDao.selectById(Integer.parseInt(str[0]));
//					if(brand == null) {
//						result.setError("品牌选择错误");
//						return result;
//					}
//					CarFamily carFamily = carFamilyDao.selectById(Integer.parseInt(str[1]));
//					if(carFamily == null) {
//						result.setError("车系选择错误");
//						return result;
//					}
//					goodsCar.setBrandId(brand.getBrandId());
//					goodsCar.setBrandName(brand.getBrandName());
//					goodsCar.setFamilyId(carFamily.getCarFamilyId());
//					goodsCar.setFamilyName(carFamily.getCarFamilyName());
//					if(set.add(str[2])) {
//						goodsCar.setFrameNumber(str[2]);
//					}else {
//						result.setError("你的车架号存在重复，请核对数据");
//						return result;
//					}
//					goodsCars.add(goodsCar);
//				}
//			}else {
//				result.setError(ResultCode.CODE_STATE_4005, "请添加托运车辆");
//				return result;
//			}
//			if(StringUtil.isNotEmpty(search.getLeaveTheCarPersonName())) {
//				logisticsConsignmentVo.setLeaveTheCarPersonName(search.getLeaveTheCarPersonName());
//			}else {
//				result.setError("请输入交车人名称");
//				return result;
//			}
//			if(StringUtil.isNotEmpty(search.getLeaveTheCarPersonPhone())) {
//				logisticsConsignmentVo.setLeaveTheCarPersonPhone(search.getLeaveTheCarPersonPhone());
//			}else {
//				result.setError("请输入提车人联系方式");
//				return result;
//			}
//			if(StringUtil.isNotEmpty(search.getExtractTheCarPersonName())) {
//				logisticsConsignmentVo.setExtractTheCarPersonName(search.getExtractTheCarPersonName());
//			}else {
//				result.setError("请输入提车人名称");
//				return result;
//			}
//			if(StringUtil.isNotEmpty(search.getExtractTheCarPersonPhone())) {
//				logisticsConsignmentVo.setExtractTheCarPersonPhone(search.getExtractTheCarPersonPhone());
//			}else {
//				result.setError("请输入提车人联系方式");
//				return result;
//			}
//			if(StringUtil.isNotEmpty(search.getExtractTheCarPersonIdcard())) {
//				IdcardValidator validator = new IdcardValidator();
//				if(!validator.isIdcard(search.getExtractTheCarPersonIdcard())) {
//					result.setError("提车人身份证格式错误，请核对数据");
//					return result;
//				}
//				logisticsConsignmentVo.setExtractTheCarPersonIdcard(search.getExtractTheCarPersonIdcard());
//			}else {
//				result.setError("请输入提车人身份证号");
//				return result;
//			}
			if(StringUtil.isNotEmpty(search.getMileage())) {
				logisticsConsignmentVo.setMileage(search.getMileage());
			}
			if(StringUtil.isNotEmpty(search.getRemarks())) {
				logisticsConsignmentVo.setRemarks(search.getRemarks());
			}else {
//				result.setError("请输入备注");
//				return result;
			}
			if(StringUtil.isEmpty(search.getConsignmentId())) {
				if(consignmentDao.insert(logisticsConsignmentVo)) {
					for(LogisticsGoodsCarVo goodsCar : goodsCarVos) {
						goodsCar.setConsignmentId(logisticsConsignmentVo.getConsignmentId());
						goodsCar.setConsignmentCode(logisticsConsignmentVo.getConsignmentCode());
						goodsCar.setCreateDate(new Date());
						goodsCar.setIsDelete(false);
						logisticsGoodsCarDao.insert(goodsCar);
						LogisticsGoodsCarCostsVo costsVo = goodsCar.getCarCostsVo();
						costsVo.setGoodsCarId(goodsCar.getGoodsCarId());
						if(StringUtil.isNotEmpty(costsVo.getLogisticsGoodsCarCostsId())) {
							logisticsGoodsCarCostsDao.updateById(costsVo);
						}else {
							logisticsGoodsCarCostsDao.insert(costsVo);
						}
					}
				}
			}else {
				if(consignmentDao.updateById(logisticsConsignmentVo)) {					
					for(LogisticsGoodsCarVo goodsCar : goodsCarVos) {
						LogisticsGoodsCarCostsVo costsVo = goodsCar.getCarCostsVo();
						if(StringUtil.isNotEmpty(goodsCar.getGoodsCarId())) {
							goodsCar.setIsDelete(true);
							logisticsGoodsCarDao.updateById(goodsCar);//删除所有旧车，以新的为准
						}else {
							goodsCar.setConsignmentId(logisticsConsignmentVo.getConsignmentId());
							goodsCar.setConsignmentCode(logisticsConsignmentVo.getConsignmentCode());
							goodsCar.setCreateDate(new Date());
							goodsCar.setIsDelete(false);
							logisticsGoodsCarDao.insert(goodsCar);
							costsVo.setGoodsCarId(goodsCar.getGoodsCarId());
						}
						if(StringUtil.isNotEmpty(costsVo.getLogisticsGoodsCarCostsId())) {
							logisticsGoodsCarCostsDao.updateById(costsVo);
						}else {
							logisticsGoodsCarCostsDao.insert(costsVo);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();//事件回滚
		}
		result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		return result;
	}
	
	/**
	 * 留档备份
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Result consignmentEdit_(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		LogisticsConsignmentVo logisticsConsignmentVo = null;
		List<LogisticsGoodsCar> goodsCars = new ArrayList<>();
		try {
			if(StringUtil.isEmpty(search.getConsignmentId())) {
				logisticsConsignmentVo = new LogisticsConsignmentVo();
				logisticsConsignmentVo.setCreateDate(new Date());
				logisticsConsignmentVo.setIsDelete(false);
				logisticsConsignmentVo.setIsCancel(false);
				logisticsConsignmentVo.setOrgId(users.getOrgId());
				logisticsConsignmentVo.setOrgName(users.getOrgName());
				logisticsConsignmentVo.setConsignmentState(GeneralConstant.ConsignmentState.START);
				logisticsConsignmentVo.setConsignmentCode(consignmentDao.getCode());
				logisticsConsignmentVo.setConsignmentInPayState(GeneralConstant.OrderInPayState.un_paid);
				logisticsConsignmentVo.setPurchasersId(users.getUsersId());
				logisticsConsignmentVo.setPurchasersName(users.getRealName());
				logisticsConsignmentVo.setPurchasersPhone(users.getPhoneNumber());
			}else {
				logisticsConsignmentVo = consignmentDao.selectByIdJoin(search.getConsignmentId());
				if(!logisticsConsignmentVo.getOrgId().equals(users.getOrgId())) {
					result.setError(ResultCode.CODE_STATE_4005, "你选择的数据错误，你没有编辑该数据的权限");
					return result;
				}
			}
			if(StringUtil.isNotEmpty(search.getStartingPointAddress())) {
				logisticsConsignmentVo.setStartingPointAddress(search.getStartingPointAddress());
				logisticsConsignmentVo.setStartingPointLatitude(search.getStartingPointLatitude());
				logisticsConsignmentVo.setStartingPointLongitude(search.getStartingPointLongitude());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "请输入起点位置");
				return result;
			}
			if(StringUtil.isNotEmpty(search.getDestinationAddress())) {
				logisticsConsignmentVo.setDestinationAddress(search.getDestinationAddress());
				logisticsConsignmentVo.setDestinationLatitude(search.getDestinationLatitude());
				logisticsConsignmentVo.setDestinationLongitude(search.getDestinationLongitude());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "请输入终点位置");
				return result;
			}
			if(StringUtil.isNotEmpty(search.getAppointmentTime())) {
				logisticsConsignmentVo.setAppointmentTime(DateUtil.format(search.getAppointmentTime()));
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "请输入配送时间");
				return result;
			}
			if(StringUtil.isNotEmpty(search.getConsignmentType())) {
				logisticsConsignmentVo.setConsignmentType(search.getConsignmentType());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "请选择配送方式");
				return result;
			}
			if(StringUtil.isNotEmpty(search.getGoodsCarInfo())) {
				String[] goodsCarsStr = search.getGoodsCarInfo().split(GeneralConstant.SystemBoolean.SPLIT_);//剪下第一层
				Set<String> set = new HashSet<>();
				if(logisticsConsignmentVo.getGoodsCarVos() != null && logisticsConsignmentVo.getGoodsCarVos().size() > 0) {
					for(LogisticsGoodsCar goodsCarsOld : logisticsConsignmentVo.getGoodsCarVos()) {
						goodsCars.add(goodsCarsOld);
					}
				}
				for(String goods : goodsCarsStr) {
					LogisticsGoodsCar goodsCar = new LogisticsGoodsCar();
					String[] str = goods.split(GeneralConstant.SystemBoolean.SPLIT);
					CarBrand brand = carBrandDao.selectById(Integer.parseInt(str[0]));
					if(brand == null) {
						result.setError("品牌选择错误");
						return result;
					}
					CarFamily carFamily = carFamilyDao.selectById(Integer.parseInt(str[1]));
					if(carFamily == null) {
						result.setError("车系选择错误");
						return result;
					}
					goodsCar.setBrandId(brand.getBrandId());
					goodsCar.setBrandName(brand.getBrandName());
					goodsCar.setFamilyId(carFamily.getCarFamilyId());
					goodsCar.setFamilyName(carFamily.getCarFamilyName());
					if(set.add(str[2])) {
						goodsCar.setFrameNumber(str[2]);
					}else {
						result.setError("你的车架号存在重复，请核对数据");
						return result;
					}
					goodsCars.add(goodsCar);
				}
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "请添加托运车辆");
				return result;
			}
//			if(StringUtil.isNotEmpty(search.getLeaveTheCarPersonName())) {
//				logisticsConsignmentVo.setLeaveTheCarPersonName(search.getLeaveTheCarPersonName());
//			}else {
//				result.setError("请输入交车人名称");
//				return result;
//			}
//			if(StringUtil.isNotEmpty(search.getLeaveTheCarPersonPhone())) {
//				logisticsConsignmentVo.setLeaveTheCarPersonPhone(search.getLeaveTheCarPersonPhone());
//			}else {
//				result.setError("请输入提车人联系方式");
//				return result;
//			}
//			if(StringUtil.isNotEmpty(search.getExtractTheCarPersonName())) {
//				logisticsConsignmentVo.setExtractTheCarPersonName(search.getExtractTheCarPersonName());
//			}else {
//				result.setError("请输入提车人名称");
//				return result;
//			}
//			if(StringUtil.isNotEmpty(search.getExtractTheCarPersonPhone())) {
//				logisticsConsignmentVo.setExtractTheCarPersonPhone(search.getExtractTheCarPersonPhone());
//			}else {
//				result.setError("请输入提车人联系方式");
//				return result;
//			}
//			if(StringUtil.isNotEmpty(search.getExtractTheCarPersonIdcard())) {
//				IdcardValidator validator = new IdcardValidator();
//				if(!validator.isIdcard(search.getExtractTheCarPersonIdcard())) {
//					result.setError("提车人身份证格式错误，请核对数据");
//					return result;
//				}
//				logisticsConsignmentVo.setExtractTheCarPersonIdcard(search.getExtractTheCarPersonIdcard());
//			}else {
//				result.setError("请输入提车人身份证号");
//				return result;
//			}
			if(StringUtil.isNotEmpty(search.getRemarks())) {
				logisticsConsignmentVo.setRemarks(search.getRemarks());
			}else {
//				result.setError("请输入备注");
//				return result;
			}
			if(StringUtil.isEmpty(search.getConsignmentId())) {
				if(consignmentDao.insert(logisticsConsignmentVo)) {
					for(LogisticsGoodsCar goodsCar : goodsCars) {
						goodsCar.setConsignmentId(logisticsConsignmentVo.getConsignmentId());
						goodsCar.setConsignmentCode(logisticsConsignmentVo.getConsignmentCode());
						goodsCar.setCreateDate(new Date());
						goodsCar.setIsDelete(false);
						logisticsGoodsCarDao.insert(goodsCar);
					}
				}
			}else {
				if(consignmentDao.updateById(logisticsConsignmentVo)) {
					for(LogisticsGoodsCar goodsCar : goodsCars) {
						if(StringUtil.isNotEmpty(goodsCar.getGoodsCarId())) {
							goodsCar.setIsDelete(true);
							logisticsGoodsCarDao.updateById(goodsCar);//删除所有旧车，以新的为准
						}else {
							goodsCar.setConsignmentId(logisticsConsignmentVo.getConsignmentId());
							goodsCar.setConsignmentCode(logisticsConsignmentVo.getConsignmentCode());
							goodsCar.setCreateDate(new Date());
							goodsCar.setIsDelete(false);
							logisticsGoodsCarDao.insert(goodsCar);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();//事件回滚
		}
		result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		return result;
	}

	@Override
	public Result consignmentInPay(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		if(search.getConsignmentId() == null){
			result.setError(ResultCode.CODE_STATE_4005, "订单选择错误");
			return result;
		}
		LogisticsConsignmentVo consignmentVo = consignmentDao.selectById(search.getConsignmentId());
		if(consignmentVo == null){
			result.setError(ResultCode.CODE_STATE_4005, "你选择的订单不存在或者已取消，请核对");
			return result;
		}
		if(consignmentVo.getConsignmentInPayState() != null && GeneralConstant.OrderInPayState.in_pay.equals(consignmentVo.getConsignmentInPayState())){
			result.setError(ResultCode.CODE_STATE_4005, "该订单已支付");
			return result;
		}
		if(consignmentVo.getConsignmentInPayState() != null && GeneralConstant.OrderInPayState.refund.equals(consignmentVo.getConsignmentInPayState())){
			result.setError(ResultCode.CODE_STATE_4005, "该订单已退款，不接受重新支付");
			return result;
		}
		/**
		 * 获取通联分配的APPID等信息
		 */
		WeixinAppTokenVo appToken = null;
		if(StringUtil.isNotEmpty(users.getOpenId())){//如果OpenId不为空
			appToken = weixinAppTokenService.getAccessTokenAllInPay();
			if(appToken == null){
				result.setError(ResultCode.CODE_STATE_4008, "微信支付异常，请尝试重新发起支付");
				return result;
			}
		}
		if(appToken == null) {
			result.setError(ResultCode.CODE_STATE_4008, "获取用户授权信息失败，请退出重新登陆后再次发起支付");
			return result;
		}
		Map<String,String> createMap = new HashMap<>();
		if(StringUtil.isEmpty(appToken.getAllInPayCusId()) || StringUtil.isEmpty(appToken.getAllInPayAppId()) || StringUtil.isEmpty(appToken.getAllInPayKey())) {
			result.setError(ResultCode.CODE_STATE_4008, "系统支付配置错误，请联系管理员");
			return result;
		}
		if(consignmentVo.getAmount() == null || consignmentVo.getAmount().doubleValue() <= 0) {
			result.setError(ResultCode.CODE_STATE_4008, "订单金额错误，请核对订单");
			return result;
		}
		
//		StringBuffer buffer = new StringBuffer();
//		createMap.put("acct", users.getOpenId());//	支付平台用户标识	小程序支付时使用 微信支付-C端用户的微信小程序openid 是	32	通过小程序的参数获取到微信openid，接口
//		forsign(buffer,"acct",users.getOpenId());
//
//		createMap.put("appid", appToken.getAllInPayAppId());//	应用ID	平台分配的APPID	否	8	
//		forsign(buffer,"appid",appToken.getAllInPayAppId());
//
//		createMap.put("body", "整车托运费");//	订单标题	订单商品名称，为空则以商户名作为商品名称	是	100	最大100个字节(50个中文字符)
//		forsign(buffer,"body","整车托运费");
//
//		createMap.put("cusid", appToken.getAllInPayCusId());//	商户号	平台分配的商户号	否	15	
//		forsign(buffer,"cusid",appToken.getAllInPayCusId());
//		
////		createMap.put("limit_pay", "");//	支付限制	no_credit--指定不能使用信用卡支付	是	32	
////		forsign(buffer,"limit_pay","");
//		
//		createMap.put("notify_url", appToken.getNotifyUrl());//	交易结果通知地址	接收异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。	是	256	因为刷卡支付交易结果实时返回,因此对于刷卡支付，该字段无效
//		forsign(buffer,"notify_url",appToken.getNotifyUrl());
//		
//		createMap.put("paytype", "W06");//	交易方式	详见附录3.3 交易方式	否	3	
//		forsign(buffer,"paytype","W06");
//		
//		createMap.put("randomstr", PayUtils.createNoncestr());//	随机字符串	商户自行生成的随机字符串	否	32	
//		forsign(buffer,"randomstr",PayUtils.createNoncestr());
//		
//		createMap.put("remark", "整车托运费");//	备注	备注信息	是	50	最大50个字节(25个中文字符)
//		forsign(buffer,"remark","整车托运费");
//		
//		createMap.put("reqsn", consignmentVo.getConsignmentCode());//	商户交易单号	商户的交易订单号	否	32	保证商户平台唯一
//		forsign(buffer,"reqsn",consignmentVo.getConsignmentCode());
//		
//		createMap.put("trxamt", consignmentVo.getAmount().multiply(new BigDecimal(100)).intValue()+"");//	交易金额	单位为分	否	15	
//		forsign(buffer,"trxamt",consignmentVo.getAmount().multiply(new BigDecimal(100)).intValue()+"");
//		
//		createMap.put("validtime", "5");//	有效时间	订单有效时间，以分为单位，不填默认为5分钟	是	2	最大60分钟
//		forsign(buffer,"validtime","5");
//
//		createMap.put("version", "11");//	版本号	接口版本号	可	2	默认填11
//		forsign(buffer,"version","11");
//		
//		
//		createMap.put("sign", AllInPayUtil.sign(buffer, appToken.getAllInPayKey()));//	签名	详见1.5	否	32	
		
		String url = "https://vsp.allinpay.com/apiweb/unitorder/pay";
		main.com.allInPay.utils.HttpConnectionUtil http = new main.com.allInPay.utils.HttpConnectionUtil(url);
		http.init();
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("cusid", appToken.getAllInPayCusId());
		params.put("appid", appToken.getAllInPayAppId());
		params.put("version", "11");
		params.put("trxamt", consignmentVo.getAmount().multiply(new BigDecimal(100)).intValue()+"");
		params.put("reqsn", consignmentVo.getConsignmentCode());
		params.put("paytype", "W06");
		params.put("randomstr", PayUtils.createNoncestr());
		params.put("body", "整车托运费");
		params.put("remark", "整车托运费");
		params.put("acct", users.getOpenId());
		params.put("authcode", "authcode");
		params.put("notify_url", appToken.getNotifyUrl());
		params.put("limit_pay", "");
		params.put("sign", main.com.allInPay.utils.SybUtil.sign(params,appToken.getAllInPayKey()));
		byte[] bys = http.postParams(params, true);
		String result_it = new String(bys,"UTF-8");
		Map<String,String> map = handleResult(result_it,appToken.getAllInPayKey());
		System.out.println("发起通联小程序支付");
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
//		String msgString = HttpClientUtil.doPost(url, createMap);
//		System.out.println(msgString);
		System.out.println("map:"+map);	
//		JSONObject json = new JSONObject(msgString);
		if(map.containsKey("retcode") && "FAIL".equals(map.get("retcode"))) {
			result.setError(map.get("retmsg"));
			return result;
		}else {
			result.setOK(ResultCode.CODE_STATE_200, map);
		}
		return result;
	}
	
	public static Map<String,String> handleResult(String result,String key) throws Exception{
		Map map = main.com.allInPay.utils.SybUtil.json2Obj(result, Map.class);
		if(map == null){
			throw new Exception("返回数据错误");
		}
		if("SUCCESS".equals(map.get("retcode"))){			
			TreeMap tmap = new TreeMap();
			tmap.putAll(map);
			String sign = tmap.remove("sign").toString();
			String sign1 = main.com.allInPay.utils.SybUtil.sign(tmap,key);
			if(sign1.toLowerCase().equals(sign.toLowerCase())){
				return map;
			}else{
				throw new Exception("验证签名失败");
			}
		}else{
			throw new Exception(map.get("retmsg").toString());
		}
	}
	
//	public Map<String,String> pay(long trxamt,String reqsn,String paytype,String body,String remark,String acct,String authcode,String notify_url,String limit_pay) throws Exception{
//		main.com.allInPay.utils.HttpConnectionUtil http = new main.com.allInPay.utils.HttpConnectionUtil(SybConstants.SYB_APIURL+"/pay");
//		http.init();
//		TreeMap<String,String> params = new TreeMap<String,String>();
//		params.put("cusid", SybConstants.SYB_CUSID);
//		params.put("appid", SybConstants.SYB_APPID);
//		params.put("version", "11");
//		params.put("trxamt", String.valueOf(trxamt));
//		params.put("reqsn", reqsn);
//		params.put("paytype", paytype);
//		params.put("randomstr", SybUtil.getValidatecode(8));
//		params.put("body", body);
//		params.put("remark", remark);
//		params.put("acct", acct);
//		params.put("authcode", authcode);
//		params.put("notify_url", notify_url);
//		params.put("limit_pay", limit_pay);
//		params.put("sign", main.com.allInPay.utils.SybUtil.sign(params,SybConstants.SYB_APPKEY));
//		byte[] bys = http.postParams(params, true);
//		String result = new String(bys,"UTF-8");
//		Map<String,String> map = handleResult(result);
//		return map;
//		
//	}
//	
	public Result consignmentInPay_(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		if(search.getConsignmentId() == null){
			result.setError(ResultCode.CODE_STATE_4005, "订单选择错误");
			return result;
		}
		LogisticsConsignmentVo consignmentVo = consignmentDao.selectById(search.getConsignmentId());
		if(consignmentVo == null){
			result.setError(ResultCode.CODE_STATE_4005, "你选择的订单不存在或者已取消，请核对");
			return result;
		}
		if(consignmentVo.getConsignmentInPayState() != null && GeneralConstant.OrderInPayState.in_pay.equals(consignmentVo.getConsignmentInPayState())){
			result.setError(ResultCode.CODE_STATE_4005, "该订单已支付");
			return result;
		}
		if(consignmentVo.getConsignmentInPayState() != null && GeneralConstant.OrderInPayState.refund.equals(consignmentVo.getConsignmentInPayState())){
			result.setError(ResultCode.CODE_STATE_4005, "该订单已退款，不接受重新支付");
			return result;
		}
		/**
		 * 获取通联分配的APPID等信息
		 */
		WeixinAppTokenVo appToken = null;
		if(StringUtil.isNotEmpty(users.getOpenId())){//如果OpenId不为空
			appToken = weixinAppTokenService.getAccessTokenAllInPay();
			if(appToken == null){
				result.setError(ResultCode.CODE_STATE_4008, "微信支付异常，请尝试重新发起支付");
				return result;
			}
		}
		if(appToken == null) {
			result.setError(ResultCode.CODE_STATE_4008, "获取用户授权信息失败，请退出重新登陆后再次发起支付");
			return result;
		}
		Map<String,String> createMap = new HashMap<>();
		if(StringUtil.isEmpty(appToken.getAllInPayCusId()) || StringUtil.isEmpty(appToken.getAllInPayAppId()) || StringUtil.isEmpty(appToken.getAllInPayKey())) {
			result.setError(ResultCode.CODE_STATE_4008, "系统支付配置错误，请联系管理员");
			return result;
		}
		if(consignmentVo.getAmount() == null || consignmentVo.getAmount().doubleValue() <= 0) {
			result.setError(ResultCode.CODE_STATE_4008, "订单金额错误，请核对订单");
			return result;
		}
		
		StringBuffer buffer = new StringBuffer();
		createMap.put("acct", users.getOpenId());//	支付平台用户标识	小程序支付时使用 微信支付-C端用户的微信小程序openid 是	32	通过小程序的参数获取到微信openid，接口
		forsign(buffer,"acct",users.getOpenId());
		
		createMap.put("appid", appToken.getAllInPayAppId());//	应用ID	平台分配的APPID	否	8	
		forsign(buffer,"appid",appToken.getAllInPayAppId());
		
		createMap.put("body", "整车托运费");//	订单标题	订单商品名称，为空则以商户名作为商品名称	是	100	最大100个字节(50个中文字符)
		forsign(buffer,"body","整车托运费");
		
		createMap.put("cusid", appToken.getAllInPayCusId());//	商户号	平台分配的商户号	否	15	
		forsign(buffer,"cusid",appToken.getAllInPayCusId());
		
//		createMap.put("limit_pay", "");//	支付限制	no_credit--指定不能使用信用卡支付	是	32	
//		forsign(buffer,"limit_pay","");
		
		createMap.put("notify_url", appToken.getNotifyUrl());//	交易结果通知地址	接收异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。	是	256	因为刷卡支付交易结果实时返回,因此对于刷卡支付，该字段无效
		forsign(buffer,"notify_url",appToken.getNotifyUrl());
		
		createMap.put("paytype", "W06");//	交易方式	详见附录3.3 交易方式	否	3	
		forsign(buffer,"paytype","W06");
		
		createMap.put("randomstr", PayUtils.createNoncestr());//	随机字符串	商户自行生成的随机字符串	否	32	
		forsign(buffer,"randomstr",PayUtils.createNoncestr());
		
		createMap.put("remark", "整车托运费");//	备注	备注信息	是	50	最大50个字节(25个中文字符)
		forsign(buffer,"remark","整车托运费");
		
		createMap.put("reqsn", consignmentVo.getConsignmentCode());//	商户交易单号	商户的交易订单号	否	32	保证商户平台唯一
		forsign(buffer,"reqsn",consignmentVo.getConsignmentCode());
		
		createMap.put("trxamt", consignmentVo.getAmount().multiply(new BigDecimal(100)).intValue()+"");//	交易金额	单位为分	否	15	
		forsign(buffer,"trxamt",consignmentVo.getAmount().multiply(new BigDecimal(100)).intValue()+"");
		
		createMap.put("validtime", "5");//	有效时间	订单有效时间，以分为单位，不填默认为5分钟	是	2	最大60分钟
		forsign(buffer,"validtime","5");
		
		createMap.put("version", "11");//	版本号	接口版本号	可	2	默认填11
		forsign(buffer,"version","11");
		
		
		createMap.put("sign", AllInPayUtil.sign(buffer, appToken.getAllInPayKey()));//	签名	详见1.5	否	32	
		
		String url = "https://vsp.allinpay.com/apiweb/unitorder/pay";
		System.out.println("发起通联小程序支付");
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
		JSONObject json = new JSONObject(msgString);
		if(json.has("retcode") && "FAIL".equals(json.get("retcode"))) {
			result.setError(json.get("retmsg"));
			return result;
		}
		result.setOK(ResultCode.CODE_STATE_200, "");
		return result;
	}
	
	void forsign(StringBuffer buffer,String name,String value){
		if(value == null || "".equals(value)){
			return;
		}
		buffer.append(name).append("=").append(value).append("&");
	}

	@Override
	public Result expensesCount(LogisticsConsignmentSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(search.getConsignmentType())) {
			result.setError("请先选择配送方式");
			return result;
		}
		if(StringUtil.isEmpty(search.getNumber())) {
			result.setError("请输入托运车辆数量");
			return result;
		}
		if(StringUtil.isEmpty(search.getAppointmentTime())) {
			result.setError("请先选择配送时间");
			return result;
		}
		if(StringUtil.isEmpty(search.getConsignmentTypeLineId()) && GeneralConstant.ConsignmentState.TYPELINE.equals(search.getConsignmentType())) {
			result.setError("请选择具体专线");
			return result;
		}
		Boolean isTody = false;
		try {
			isTody = DateUtil.isToday(search.getAppointmentTime(),result);
		} catch (Exception e) {
			return result;
		}
		Map<String, Object> map = new HashMap<>();
		Double amount = 0d;
		
		/**
		 * 高德地图路线规划接口请求
		 */
		String url = "http://restapi.amap.com/v3/direction/driving";
		String params = "origin="+search.getStartingPointLongitude()
				+","+search.getStartingPointLatitude()+"&destination="+search.getDestinationLongitude()
				+","+search.getDestinationLatitude()+"&extensions=all&output=JSON&key=f740edebff83983909d7971422cee5e8"
				+"&strategy=20";
		
//		System.out.println("URL:"+url+"?"+params);
		
		 JSONObject jsonObject = new JSONObject(HttpClientUtil.transboundaryRequest(url,params));
//		 System.out.println("高德导航返回："+jsonObject);
		 if(jsonObject == null || !jsonObject.has("status") || "0".equals(jsonObject.get("status"))) {
			 result.setError("抱歉，路线规划失败，请重新选择位置");
				return result;
		 }
		 JSONObject route = jsonObject.getJSONObject("route");
		 JSONArray paths = route.getJSONArray("paths");
		 JSONObject object = paths.getJSONObject(0);
		 Double distance = Double.valueOf(object.get("distance").toString());
		 distance = new BigDecimal(distance/1000).setScale(0, BigDecimal.ROUND_UP).doubleValue();
//		 System.out.println("distance:"+distance);
		 map.put("mileage", distance);
		
		if(GeneralConstant.ConsignmentState.TYPELINE.equals(search.getConsignmentType())) {//如果是专线配送
			LogisticsDedicatedLineVo logisticsDedicatedLine = logisticsDedicatedLineDao.selectById(search.getConsignmentTypeLineId());
			if(logisticsDedicatedLine == null) {
				result.setError("抱歉，你选择得专线不存在或已删除");
				return result;
			}
			if(isTody) {
				amount = ((logisticsDedicatedLine.getAdditionalAmount()!=null?logisticsDedicatedLine.getAdditionalAmount().doubleValue():0d)+(
						logisticsDedicatedLine.getAmount()!=null?logisticsDedicatedLine.getAmount().doubleValue():0d));
				map.put("additionalAmount", (logisticsDedicatedLine.getAdditionalAmount()!=null?logisticsDedicatedLine.getAdditionalAmount().doubleValue():0d) * search.getNumber());
			}else {
				amount = (logisticsDedicatedLine.getAdditionalAmount()!=null?logisticsDedicatedLine.getAdditionalAmount().doubleValue():0d)+(
						logisticsDedicatedLine.getAmount()!=null?logisticsDedicatedLine.getAmount().doubleValue():0d);
				map.put("additionalAmount", 0d);//今日预约附加费
			}
			map.put("amount", amount * search.getNumber());//总配送费
			map.put("amountLine", (logisticsDedicatedLine.getAmount()!=null?logisticsDedicatedLine.getAmount().doubleValue():0d) * search.getNumber());//专线配送费
			result.setOK(ResultCode.CODE_STATE_200, "", TakeCareMapDate.cutNullMap(map));
			return result;
		}else if(GeneralConstant.ConsignmentState.TYPEPOINT.equals(search.getConsignmentType())) {
			List<LogisticsDynamicLineVo> dynamicLineVos = logisticsDynamicLineDao.selectJoin(new HashMap<String,Object>());
			if(dynamicLineVos == null || dynamicLineVos.size() <= 0) {
				dynamicLineVos = logisticsDynamicLineDao.select(new HashMap<String,Object>());
				if(dynamicLineVos == null || dynamicLineVos.size() <= 0) {
					result.setError("非常抱歉，系统暂不支持点对点配送");
					return result;
				}
			}
			LogisticsDynamicLineVo dynamicLineVo = dynamicLineVos.get(0);
			//两地相距距离和路线
			if(StringUtil.isEmpty(search.getStartingPointLongitude()) || StringUtil.isEmpty(search.getStartingPointLatitude())) {
				result.setError("起点位置获取错误，请重新选择起点");
				return result;
			}
			if(StringUtil.isEmpty(search.getDestinationLongitude()) || StringUtil.isEmpty(search.getDestinationLatitude())) {
				result.setError("终点位置获取错误，请重新选择终点");
				return result;
			}
			
			 if(dynamicLineVo.getLineInfoVos() == null || dynamicLineVo.getLineInfoVos().size() <= 0) {//没有配置溢出，当作不溢出处理
				amount = 0d;
				if(distance <= dynamicLineVo.getStartingMileage()) { //起步价内
					amount = (dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0d) * search.getNumber();
				}else {
					amount = (dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0d) * search.getNumber();//没有配置溢出，当作不溢出处理
				}
			 }else {
				 if(distance <= dynamicLineVo.getStartingMileage()) { //起步价内
						amount = (dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0d) * search.getNumber();
				 }else {
						amount = (dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0d) * search.getNumber();//超出起步价
//						distance = distance - dynamicLineVo.getStartingMileage();//减去起步里程(分段收费不减里程)
						//LIST排序
						if(dynamicLineVo.getLineInfoVos().size() > 1) {
							Collections.sort(dynamicLineVo.getLineInfoVos(),new Comparator<LogisticsDynamicLineInfoVo>(){//由小到大
					            public int compare(LogisticsDynamicLineInfoVo arg0, LogisticsDynamicLineInfoVo arg1) {
					                return arg0.getMinMileage().compareTo(arg1.getMinMileage());//由小到大
					            }
					        });		
						}	
//						Double maxMileage = 0.0d;
						 Double lineAmount = 0.0d;
//						 for(LogisticsDynamicLineInfoVo lineInfoVo : dynamicLineVo.getLineInfoVos()) {//（查找出来溢出的最大里程
//							 if(distance > lineInfoVo.getMinMileage()) {//在此溢出历程内(分段收费)
//								 Double distance_for = 0.0d;
//								 if(lineInfoVo.getMaxMileage() != null && !lineInfoVo.getMaxMileage().equals(0d)) {
//									 distance_for = lineInfoVo.getMaxMileage() - lineInfoVo.getMinMileage();
//								 }else {
//									 distance_for = distance - lineInfoVo.getMinMileage();
//								 }
//								 amount += lineInfoVo.getAmount().doubleValue() * distance_for * search.getNumber();//每KM溢出价格*公里数*车辆数量
//								 lineAmount += lineInfoVo.getAmount().doubleValue() * distance_for * search.getNumber();
////								 break;//只匹配一个溢出
//							 }
//					 	}
						 Integer y = 0;
						 for(int i=0;i<dynamicLineVo.getLineInfoVos().size();i++) {
							 LogisticsDynamicLineInfoVo lineInfoVo = dynamicLineVo.getLineInfoVos().get(i);
							 if(distance <= lineInfoVo.getMaxMileage()) {
								y = i; //查询到溢出区间
								break; //获得所在区间后退出
							 }
						 }
						 if(y.equals(0)) {//表示溢出在无限区间，所有配置区间都要计算
							 y = dynamicLineVo.getLineInfoVos().size();
							 y -= 1;//把Y转成数组的下表，下表重0开始，所以去掉一位
						 }
						 for(int x=0;x<=y;x++) {
							 LogisticsDynamicLineInfoVo lineInfoVo = dynamicLineVo.getLineInfoVos().get(x);
							 if(StringUtil.isEmpty(lineInfoVo.getMaxMileage())) {
								 lineInfoVo.setMaxMileage(distance);
							 }
							 if(distance >= lineInfoVo.getMaxMileage()) {//表示完全溢出该区间
								 amount += lineInfoVo.getAmount().doubleValue() * (lineInfoVo.getMaxMileage() - lineInfoVo.getMinMileage()) * search.getNumber();//每KM溢出价格*公里数*车辆数量
								 lineAmount += lineInfoVo.getAmount().doubleValue() * (lineInfoVo.getMaxMileage() - lineInfoVo.getMinMileage()) * search.getNumber();
							 }else if(distance > lineInfoVo.getMinMileage()) {//表示在此区间内
								 amount += lineInfoVo.getAmount().doubleValue() * (distance - lineInfoVo.getMinMileage()) * search.getNumber();//每KM溢出价格*公里数*车辆数量
								 lineAmount += lineInfoVo.getAmount().doubleValue() * (distance - lineInfoVo.getMinMileage()) * search.getNumber();
							 }
						 }
						 
						 map.put("overflow", lineAmount);//溢出里程总价
//						 for(LogisticsDynamicLineInfoVo lineInfoVo : dynamicLineVo.getLineInfoVos()) {//（查找出来溢出的最大里程
//							 if(distance > lineInfoVo.getMinMileage()) {//在此溢出历程内(分段收费)
//								 amount += lineInfoVo.getAmount().doubleValue() * distance * search.getNumber();//每KM溢出价格*公里数*车辆数量
//								 map.put("line", lineInfoVo.getAmount().doubleValue() * search.getNumber() * distance);//溢出里程
//								 break;//只匹配一个溢出
//							 }
//						 }
						 
					}
			 }
			 map.put("initiateRate", (dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0d) * search.getNumber());//起步价
//			 amount = amount * search.getNumber();
			//查询车价
			 Double grade = 0d;
			if((dynamicLineVo.getGradeFirst()!= null && dynamicLineVo.getGradeFirst().doubleValue() > 0)
					|| (dynamicLineVo.getGradeSecond()!= null && dynamicLineVo.getGradeSecond().doubleValue() > 0)
					|| (dynamicLineVo.getGradeThird()!= null && dynamicLineVo.getGradeThird().doubleValue() > 0)
					|| (dynamicLineVo.getGradeFour()!= null && dynamicLineVo.getGradeFour().doubleValue() > 0)
					|| (dynamicLineVo.getGradeFive()!= null && dynamicLineVo.getGradeFive().doubleValue() > 0)
					|| (dynamicLineVo.getGradeSix()!= null && dynamicLineVo.getGradeSix().doubleValue() > 0)
					) {
				 if(StringUtil.isEmpty(search.getCarsIds())) {
					 result.setError("请先选择具体车类型");
					 return result;
				 }
				 String[] strings = search.getCarsIds().split(GeneralConstant.SystemBoolean.SPLIT);
				 Set<Integer> set = new HashSet<>();
				 Map<Integer,Integer> map2 = new HashMap<>();
				 for(String string : strings) {
					 if(set.add(Integer.parseInt(string))) {
						 if(!map2.containsKey(Integer.parseInt(string))) {
							 map2.put(Integer.parseInt(string), 1);
						 }else {
							 map2.put(Integer.parseInt(string), map2.get(Integer.parseInt(string))+1);
						 }
					 }else {
						 map2.put(Integer.parseInt(string), map2.get(Integer.parseInt(string))+1);
					 }
				 }
				 Map<String,Object> carsParams = new HashMap<>();
				 carsParams.put("ids", set);
				 List<CarsVo> carsVos = carsDao.select(carsParams);
//				 CarsVo carsVo =  carsDao.selectById(search.getCarsId());
				List<Map<String, Object>> maps = new ArrayList<>();
				 for(CarsVo carsVo : carsVos) {
					 if(carsVo.getPrice() >0 && carsVo.getPrice() <= 100000f) {
						 amount += dynamicLineVo.getGradeFirst().doubleValue() * map2.get(carsVo.getCarId());
						 grade += dynamicLineVo.getGradeFirst().doubleValue() * map2.get(carsVo.getCarId());
					 }else if(carsVo.getPrice() >100000f && carsVo.getPrice() <= 200000f) {
						 amount += dynamicLineVo.getGradeSecond().doubleValue() * map2.get(carsVo.getCarId());
						 grade += dynamicLineVo.getGradeSecond().doubleValue() * map2.get(carsVo.getCarId());
					 }else if(carsVo.getPrice() >200000f && carsVo.getPrice() <= 300000f) {
						 amount += dynamicLineVo.getGradeThird().doubleValue() * map2.get(carsVo.getCarId());
						 grade += dynamicLineVo.getGradeThird().doubleValue() * map2.get(carsVo.getCarId());
					 }else if(carsVo.getPrice() >300000f && carsVo.getPrice() <= 400000f) {
						 amount += dynamicLineVo.getGradeFour().doubleValue() * map2.get(carsVo.getCarId());
						 grade += dynamicLineVo.getGradeFour().doubleValue() * map2.get(carsVo.getCarId());
					 }else if(carsVo.getPrice() >400000f && carsVo.getPrice() <= 600000f) {
						 amount += dynamicLineVo.getGradeFive().doubleValue() * map2.get(carsVo.getCarId());
						 grade += dynamicLineVo.getGradeFive().doubleValue() * map2.get(carsVo.getCarId());
					 }else if(carsVo.getPrice() >=600000f) {
						 amount += dynamicLineVo.getGradeSix().doubleValue() * map2.get(carsVo.getCarId());
						 grade += dynamicLineVo.getGradeSix().doubleValue() * map2.get(carsVo.getCarId());
					 }
					 Map<String, Object> mapCar = new HashMap<>();
					 mapCar.put("carsName", carsVo.getCarName());
					 mapCar.put("carsId", carsVo.getCarId());
					 mapCar.put("guidePrice", carsVo.getPrice());
					 maps.add(mapCar);
				 }
				 map.put("list", maps);//车辆等级附加费
			}
			map.put("grade", grade);//车辆等级附加费
			map.put("amount", amount);//总配送费
			result.setOK(ResultCode.CODE_STATE_200, "", map);
			return result;
		}else {
			result.setError("你选择得配送方式错误，请重新选择");
			return result;
		}
	}
	
	@Override
	public Result expensesCount(LogisticsConsignmentSearch search, Result result) throws Exception {
		if(StringUtil.isEmpty(search.getConsignmentType())) {
			result.setError("请先选择配送方式");
			return result;
		}
		if(StringUtil.isEmpty(search.getNumber())) {
			result.setError("请输入托运车辆数量");
			return result;
		}
//		if(StringUtil.isEmpty(search.getAppointmentTime())) {
//			result.setError("请先选择配送时间");
//			return result;
//		}
		if(StringUtil.isEmpty(search.getConsignmentTypeLineId()) && GeneralConstant.ConsignmentState.TYPELINE.equals(search.getConsignmentType())) {
			result.setError("请选择具体专线");
			return result;
		}
		Boolean isTody = false;
		try {
			isTody = DateUtil.isToday(search.getAppointmentTime(),result);
		} catch (Exception e) {
			return result;
		}
		Map<String, Object> map = new HashMap<>();
		Double amount = 0d;
		
		/**
		 * 高德地图路线规划接口请求
		 */
		String url = "http://restapi.amap.com/v3/direction/driving";
		String params = "origin="+search.getStartingPointLongitude()
		+","+search.getStartingPointLatitude()+"&destination="+search.getDestinationLongitude()
		+","+search.getDestinationLatitude()+"&extensions=all&output=JSON&key=f740edebff83983909d7971422cee5e8"
		+"&strategy=20";
		
//		System.out.println("URL:"+url+"?"+params);
		
		JSONObject jsonObject = new JSONObject(HttpClientUtil.transboundaryRequest(url,params));
//		 System.out.println("高德导航返回："+jsonObject);
		if(jsonObject == null || !jsonObject.has("status") || "0".equals(jsonObject.get("status"))) {
			result.setError("抱歉，路线规划失败，请重新选择位置");
			return result;
		}
		JSONObject route = jsonObject.getJSONObject("route");
		JSONArray paths = route.getJSONArray("paths");
		JSONObject object = paths.getJSONObject(0);
		Double distance = Double.valueOf(object.get("distance").toString());
		distance = new BigDecimal(distance/1000).setScale(0, BigDecimal.ROUND_UP).doubleValue();
//		 System.out.println("distance:"+distance);
		map.put("mileage", distance);
		
		if(GeneralConstant.ConsignmentState.TYPELINE.equals(search.getConsignmentType())) {//如果是专线配送
			LogisticsDedicatedLineVo logisticsDedicatedLine = logisticsDedicatedLineDao.selectById(search.getConsignmentTypeLineId());
			if(logisticsDedicatedLine == null) {
				result.setError("抱歉，你选择得专线不存在或已删除");
				return result;
			}
			if(isTody) {
				amount = ((logisticsDedicatedLine.getAdditionalAmount()!=null?logisticsDedicatedLine.getAdditionalAmount().doubleValue():0d)+(
						logisticsDedicatedLine.getAmount()!=null?logisticsDedicatedLine.getAmount().doubleValue():0d));
				map.put("additionalAmount", (logisticsDedicatedLine.getAdditionalAmount()!=null?logisticsDedicatedLine.getAdditionalAmount().doubleValue():0d) * search.getNumber());
			}else {
				amount = (logisticsDedicatedLine.getAdditionalAmount()!=null?logisticsDedicatedLine.getAdditionalAmount().doubleValue():0d)+(
						logisticsDedicatedLine.getAmount()!=null?logisticsDedicatedLine.getAmount().doubleValue():0d);
				map.put("additionalAmount", 0d);//今日预约附加费
			}
			map.put("amount", amount * search.getNumber());//总配送费
			map.put("amountLine", (logisticsDedicatedLine.getAmount()!=null?logisticsDedicatedLine.getAmount().doubleValue():0d) * search.getNumber());//专线配送费
			result.setOK(ResultCode.CODE_STATE_200, "", TakeCareMapDate.cutNullMap(map));
			return result;
		}else if(GeneralConstant.ConsignmentState.TYPEPOINT.equals(search.getConsignmentType())) {
			List<LogisticsDynamicLineVo> dynamicLineVos = logisticsDynamicLineDao.selectJoin(new HashMap<String,Object>());
			if(dynamicLineVos == null || dynamicLineVos.size() <= 0) {
				dynamicLineVos = logisticsDynamicLineDao.select(new HashMap<String,Object>());
				if(dynamicLineVos == null || dynamicLineVos.size() <= 0) {
					result.setError("非常抱歉，系统暂不支持点对点配送");
					return result;
				}
			}
			LogisticsDynamicLineVo dynamicLineVo = dynamicLineVos.get(0);
			//两地相距距离和路线
			if(StringUtil.isEmpty(search.getStartingPointLongitude()) || StringUtil.isEmpty(search.getStartingPointLatitude())) {
				result.setError("起点位置获取错误，请重新选择起点");
				return result;
			}
			if(StringUtil.isEmpty(search.getDestinationLongitude()) || StringUtil.isEmpty(search.getDestinationLatitude())) {
				result.setError("终点位置获取错误，请重新选择终点");
				return result;
			}
			
			if(dynamicLineVo.getLineInfoVos() == null || dynamicLineVo.getLineInfoVos().size() <= 0) {//没有配置溢出，当作不溢出处理
				amount = 0d;
				if(distance <= dynamicLineVo.getStartingMileage()) { //起步价内
					amount = (dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0d) * search.getNumber();
				}else {
					amount = (dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0d) * search.getNumber();//没有配置溢出，当作不溢出处理
				}
			}else {
				if(distance <= dynamicLineVo.getStartingMileage()) { //起步价内
					amount = (dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0d) * search.getNumber();
				}else {
					amount = (dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0d) * search.getNumber();//超出起步价
//						distance = distance - dynamicLineVo.getStartingMileage();//减去起步里程(分段收费不减里程)
					//LIST排序
					if(dynamicLineVo.getLineInfoVos().size() > 1) {
						Collections.sort(dynamicLineVo.getLineInfoVos(),new Comparator<LogisticsDynamicLineInfoVo>(){//由小到大
							public int compare(LogisticsDynamicLineInfoVo arg0, LogisticsDynamicLineInfoVo arg1) {
								return arg0.getMinMileage().compareTo(arg1.getMinMileage());//由小到大
							}
						});		
					}	
//						Double maxMileage = 0.0d;
					Double lineAmount = 0.0d;
//						 for(LogisticsDynamicLineInfoVo lineInfoVo : dynamicLineVo.getLineInfoVos()) {//（查找出来溢出的最大里程
//							 if(distance > lineInfoVo.getMinMileage()) {//在此溢出历程内(分段收费)
//								 Double distance_for = 0.0d;
//								 if(lineInfoVo.getMaxMileage() != null && !lineInfoVo.getMaxMileage().equals(0d)) {
//									 distance_for = lineInfoVo.getMaxMileage() - lineInfoVo.getMinMileage();
//								 }else {
//									 distance_for = distance - lineInfoVo.getMinMileage();
//								 }
//								 amount += lineInfoVo.getAmount().doubleValue() * distance_for * search.getNumber();//每KM溢出价格*公里数*车辆数量
//								 lineAmount += lineInfoVo.getAmount().doubleValue() * distance_for * search.getNumber();
////								 break;//只匹配一个溢出
//							 }
//					 	}
					Integer y = 0;
					for(int i=0;i<dynamicLineVo.getLineInfoVos().size();i++) {
						LogisticsDynamicLineInfoVo lineInfoVo = dynamicLineVo.getLineInfoVos().get(i);
						if(distance <= lineInfoVo.getMaxMileage()) {
							y = i; //查询到溢出区间
							break; //获得所在区间后退出
						}
					}
					if(y.equals(0)) {//表示溢出在无限区间，所有配置区间都要计算
						y = dynamicLineVo.getLineInfoVos().size();
						y -= 1;//把Y转成数组的下表，下表重0开始，所以去掉一位
					}
					for(int x=0;x<=y;x++) {
						LogisticsDynamicLineInfoVo lineInfoVo = dynamicLineVo.getLineInfoVos().get(x);
						if(StringUtil.isEmpty(lineInfoVo.getMaxMileage())) {
							lineInfoVo.setMaxMileage(distance);
						}
						if(distance >= lineInfoVo.getMaxMileage()) {//表示完全溢出该区间
							amount += lineInfoVo.getAmount().doubleValue() * (lineInfoVo.getMaxMileage() - lineInfoVo.getMinMileage()) * search.getNumber();//每KM溢出价格*公里数*车辆数量
							lineAmount += lineInfoVo.getAmount().doubleValue() * (lineInfoVo.getMaxMileage() - lineInfoVo.getMinMileage()) * search.getNumber();
						}else if(distance > lineInfoVo.getMinMileage()) {//表示在此区间内
							amount += lineInfoVo.getAmount().doubleValue() * (distance - lineInfoVo.getMinMileage()) * search.getNumber();//每KM溢出价格*公里数*车辆数量
							lineAmount += lineInfoVo.getAmount().doubleValue() * (distance - lineInfoVo.getMinMileage()) * search.getNumber();
						}
					}
					
					map.put("overflow", lineAmount);//溢出里程总价
//						 for(LogisticsDynamicLineInfoVo lineInfoVo : dynamicLineVo.getLineInfoVos()) {//（查找出来溢出的最大里程
//							 if(distance > lineInfoVo.getMinMileage()) {//在此溢出历程内(分段收费)
//								 amount += lineInfoVo.getAmount().doubleValue() * distance * search.getNumber();//每KM溢出价格*公里数*车辆数量
//								 map.put("line", lineInfoVo.getAmount().doubleValue() * search.getNumber() * distance);//溢出里程
//								 break;//只匹配一个溢出
//							 }
//						 }
					
				}
			}
			map.put("initiateRate", (dynamicLineVo.getStartPrice()!=null?dynamicLineVo.getStartPrice().doubleValue():0d) * search.getNumber());//起步价
//			 amount = amount * search.getNumber();
			//查询车价
			Double grade = 0d;
			if((dynamicLineVo.getGradeFirst()!= null && dynamicLineVo.getGradeFirst().doubleValue() > 0)
					|| (dynamicLineVo.getGradeSecond()!= null && dynamicLineVo.getGradeSecond().doubleValue() > 0)
					|| (dynamicLineVo.getGradeThird()!= null && dynamicLineVo.getGradeThird().doubleValue() > 0)
					|| (dynamicLineVo.getGradeFour()!= null && dynamicLineVo.getGradeFour().doubleValue() > 0)
					|| (dynamicLineVo.getGradeFive()!= null && dynamicLineVo.getGradeFive().doubleValue() > 0)
					|| (dynamicLineVo.getGradeSix()!= null && dynamicLineVo.getGradeSix().doubleValue() > 0)
					) {
				if(StringUtil.isEmpty(search.getCarsIds())) {
					result.setError("请先选择具体车类型");
					return result;
				}
				String[] strings = search.getCarsIds().split(GeneralConstant.SystemBoolean.SPLIT);
				Set<Integer> set = new HashSet<>();
				Map<Integer,Integer> map2 = new HashMap<>();
				for(String string : strings) {
					if(set.add(Integer.parseInt(string))) {
						if(!map2.containsKey(Integer.parseInt(string))) {
							map2.put(Integer.parseInt(string), 1);
						}else {
							map2.put(Integer.parseInt(string), map2.get(Integer.parseInt(string))+1);
						}
					}else {
						map2.put(Integer.parseInt(string), map2.get(Integer.parseInt(string))+1);
					}
				}
				Map<String,Object> carsParams = new HashMap<>();
				carsParams.put("ids", set);
				List<CarsVo> carsVos = carsDao.select(carsParams);
//				 CarsVo carsVo =  carsDao.selectById(search.getCarsId());
				Double amountLine = amount.doubleValue();
				map.put("amountLine", amountLine);
				List<Map<String, Object>> maps = new ArrayList<>();
				for(CarsVo carsVo : carsVos) {
					if(carsVo.getPrice() >0 && carsVo.getPrice() <= 100000f) {
						amount += dynamicLineVo.getGradeFirst().doubleValue() * map2.get(carsVo.getCarId());
						grade += dynamicLineVo.getGradeFirst().doubleValue() * map2.get(carsVo.getCarId());
					}else if(carsVo.getPrice() >100000f && carsVo.getPrice() <= 200000f) {
						amount += dynamicLineVo.getGradeSecond().doubleValue() * map2.get(carsVo.getCarId());
						grade += dynamicLineVo.getGradeSecond().doubleValue() * map2.get(carsVo.getCarId());
					}else if(carsVo.getPrice() >200000f && carsVo.getPrice() <= 300000f) {
						amount += dynamicLineVo.getGradeThird().doubleValue() * map2.get(carsVo.getCarId());
						grade += dynamicLineVo.getGradeThird().doubleValue() * map2.get(carsVo.getCarId());
					}else if(carsVo.getPrice() >300000f && carsVo.getPrice() <= 400000f) {
						amount += dynamicLineVo.getGradeFour().doubleValue() * map2.get(carsVo.getCarId());
						grade += dynamicLineVo.getGradeFour().doubleValue() * map2.get(carsVo.getCarId());
					}else if(carsVo.getPrice() >400000f && carsVo.getPrice() <= 600000f) {
						amount += dynamicLineVo.getGradeFive().doubleValue() * map2.get(carsVo.getCarId());
						grade += dynamicLineVo.getGradeFive().doubleValue() * map2.get(carsVo.getCarId());
					}else if(carsVo.getPrice() >=600000f) {
						amount += dynamicLineVo.getGradeSix().doubleValue() * map2.get(carsVo.getCarId());
						grade += dynamicLineVo.getGradeSix().doubleValue() * map2.get(carsVo.getCarId());
					}
					Map<String, Object> mapCar = new HashMap<>();
					mapCar.put("carsName", carsVo.getCarName());
					mapCar.put("carsId", carsVo.getCarId());
					mapCar.put("guidePrice", carsVo.getPrice());
					maps.add(mapCar);
				}
				map.put("list", maps);//车辆等级附加费
			}
			map.put("grade", grade);//车辆等级附加费
			map.put("amount", amount);//总配送费
			result.setOK(ResultCode.CODE_STATE_200, "", map);
			return result;
		}else {
			result.setError("你选择得配送方式错误，请重新选择");
			return result;
		}
	}

	/**
	 * POS机支付
	 */
	@Override
	public Result consignmentInPayPOS(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		//查询出需要支付的车辆
		//生产支付单
		//改变托运单的支付状态 0初始 1已支付部分 2完全支付 3退款（暂不做考虑）
		return null;
	}
	
	
	@Override
	public Result distributionList(LogisticsDistributionSearch search, Result result, SystemUsers systemUsers)
			throws Exception {
		Map<String,Object> params = getParams(search,systemUsers);
		List<LogisticsDistributionVo> distributionVos = logisticsDistributionDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = logisticsDistributionDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(LogisticsDistributionVo distributionVo : distributionVos) {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("distributionId", distributionVo.getDistributionId());
			map.put("distributionCode", distributionVo.getDistributionCode());
			map.put("driverId", distributionVo.getDriverId());
			map.put("driverName", distributionVo.getDriverName());
			map.put("driverPhone", distributionVo.getDriverPhone());
			map.put("startDate", distributionVo.getStartDate()!=null?DateUtil.format(distributionVo.getStartDate()):"");
			if(distributionVo.getLogisticsCarVo() != null) {
				map.put("logisticsCarId", distributionVo.getLogisticsCarVo().getLogisticsCarId());
				map.put("logisticsCarType", distributionVo.getLogisticsCarVo().getLogisticsCarType());
				map.put("licensePlateNumber", distributionVo.getLogisticsCarVo().getLicensePlateNumber());
			}else {
				map.put("logisticsCarId", "");
				map.put("logisticsCarType", "");
				map.put("licensePlateNumber", "");
			}
			
			map.put("distributionState", distributionVo.getDistributionState());
			map.put("vacancy", distributionVo.getVacancy());
			map.put("leaveVacancy", distributionVo.getLeaveVacancy());
			map.put("remarks", distributionVo.getRemarks());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}

	public Map<String,Object> getParams(LogisticsDistributionSearch search,SystemUsers systemUsers){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getDistributionCode())) {
			params.put("distributionCode", search.getDistributionCode());
		}
		if(StringUtil.isNotEmpty(search.getDistributionState())) {
			params.put("distributionState", search.getDistributionState());
		}
		params.put("orgCodeLevel", systemUsers.getOrgCode());
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}
	
	public Result checkPersion(List<ConsumerOrderUser> leaveTheCarPerson,String type,Result result) {
		//保存提车人和交车人
		if(leaveTheCarPerson == null || leaveTheCarPerson.size() <= 0) {
			result.setError("请输入交车人信息");
			return result;
		}
		String persionId = "";
		for(ConsumerOrderUser orderUser : leaveTheCarPerson) {
			if(StringUtil.isEmpty(orderUser.getUserPhone())) {
				result.setError(type + "电话不能为空");
				return result;
			}
			//撤销想通电话号码的校验
//			ConsumerOrderUser consumerOrderUser = consumerOrderUserDao.selectByCode(orderUser.getUserPhone());
//			if(consumerOrderUser != null) {
//				if(StringUtil.isNotEmpty(orderUser.getId()) && !orderUser.getId().equals(consumerOrderUser.getId())) {
//					result.setError(type + "电话号："+orderUser.getUserPhone()+"已经存在");
//					return result;
//				}else if(StringUtil.isEmpty(orderUser.getId())) {
//					result.setError(type + "电话号："+orderUser.getUserPhone()+"已经存在");
//					return result;
//				}
//			}
			if(StringUtil.isNotEmpty(orderUser.getId())) {//更新提车交车人信息
				consumerOrderUserDao.updateById(orderUser);
			}else {
				consumerOrderUserDao.insert(orderUser);
			}
			persionId += orderUser.getId() + GeneralConstant.SystemBoolean.SPLIT;
		}
		result.setOK(ResultCode.CODE_STATE_200, "",persionId.substring(0, persionId.length() - 1));
		return result;
	}

	@Override
	public Result distributionInfo(LogisticsDistributionSearch search, Result result, SystemUsers users)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getMileage(LogisticsConsignmentSearch search, Result result, SystemUsers users) throws Exception {
		//两地相距距离和路线
		if(StringUtil.isEmpty(search.getStartingPointLongitude()) || StringUtil.isEmpty(search.getStartingPointLatitude())) {
			result.setError("起点位置获取错误，请重新选择起点");
			return result;
		}
		if(StringUtil.isEmpty(search.getDestinationLongitude()) || StringUtil.isEmpty(search.getDestinationLatitude())) {
			result.setError("终点位置获取错误，请重新选择终点");
			return result;
		}
		
		/**
		 * 高德地图路线规划接口请求
		 */
		String url = "http://restapi.amap.com/v3/direction/driving";
		String params = "origin="+search.getStartingPointLongitude()
				+","+search.getStartingPointLatitude()+"&destination="+search.getDestinationLongitude()
				+","+search.getDestinationLatitude()+"&extensions=all&output=JSON&key=f740edebff83983909d7971422cee5e8"
				+"&strategy=20";
		
//		System.out.println("URL:"+url+"?"+params);
		
		 JSONObject jsonObject = new JSONObject(HttpClientUtil.transboundaryRequest(url,params));
//		 System.out.println("高德导航返回："+jsonObject);
		 if(jsonObject == null || !jsonObject.has("status") || "0".equals(jsonObject.get("status"))) {
			 result.setError("抱歉，路线规划失败，请重新选择位置");
				return result;
		 }
		 JSONObject route = jsonObject.getJSONObject("route");
		 JSONArray paths = route.getJSONArray("paths");
		 JSONObject object = paths.getJSONObject(0);
		 Double distance = Double.valueOf(object.get("distance").toString());
		 result.setOK(ResultCode.CODE_STATE_200, "", distance);
		 return result;
	}

	@Override
	public Result getExpensesInfo(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		LogisticsConsignmentVo logisticsConsignmentVo = consignmentDao.selectByIdJoin(search.getConsignmentId());
		if(logisticsConsignmentVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的托运单不存在或者已取消，请重新选择");
			return result;
		}
		if(logisticsConsignmentVo.getGoodsCarVos() == null || logisticsConsignmentVo.getGoodsCarVos().size() <= 0) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的托运单暂没有产生运费");
			return result;
		}
		Set<Integer> set = new HashSet<>();
		for(LogisticsGoodsCarVo goodsCarVo : logisticsConsignmentVo.getGoodsCarVos()) {
			set.add(goodsCarVo.getGoodsCarId());
		}
		if(logisticsConsignmentVo.getAmount() == null) {
			logisticsConsignmentVo.setAmount(new BigDecimal(0));
		}
		Map<String, Object> params = new HashMap<>();
		params.put("goodsCarIds", set);
		List<LogisticsGoodsCarCostsVo> costsVos = logisticsGoodsCarCostsDao.select(params);
		Map<String, Object> map = new HashMap<>();
		map.put("mileage", logisticsConsignmentVo.getMileage());
		map.put("amount", logisticsConsignmentVo.getAmount()!=null?logisticsConsignmentVo.getAmount().doubleValue():0);
		map.put("appointmentTime", logisticsConsignmentVo.getAppointmentTime()!=null?DateUtil.format(logisticsConsignmentVo.getAppointmentTime()):"");
		map.put("consignmentType", logisticsConsignmentVo.getConsignmentType());
		map.put("startingPointAddress", logisticsConsignmentVo.getStartingPointAddress());
		map.put("destinationAddress", logisticsConsignmentVo.getDestinationAddress());
		map.put("goodsCarAmount", logisticsConsignmentVo.getAmount().doubleValue() / logisticsConsignmentVo.getGoodsCarVos().size());
		map.put("number", logisticsConsignmentVo.getGoodsCarVos().size());
		List<Map<String, Object>> maps = new ArrayList<>();
		for(LogisticsGoodsCarCostsVo costsVo : costsVos) {
			Map<String, Object> costMap = new HashMap<>();
			for(LogisticsGoodsCarVo goodsCarVo : logisticsConsignmentVo.getGoodsCarVos()) {
				if(goodsCarVo.getGoodsCarId().equals(costsVo.getGoodsCarId())) {
					costMap.put("carsName", goodsCarVo.getCarsName());
					costMap.put("thePriceAdditional", costsVo.getThePriceAdditional());
					maps.add(costMap);
				}
			}
		}
		map.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}

	@Override
	public Result extractTheCarPersons(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		LogisticsConsignmentVo logisticsConsignmentVo = consignmentDao.selectById(search.getConsignmentId());
		if(logisticsConsignmentVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的托运单不存在或者已取消，请重新选择");
			return result;
		}
		if(search.getExtractTheCarPerson() != null && search.getExtractTheCarPerson().size() > 0) {
			checkPersion(search.getExtractTheCarPerson(), "提车人", result);
			if(!result.isSuccess()) {
				return result;
			}
			if(StringUtil.isEmpty(logisticsConsignmentVo.getExtractTheCarPersonIds())) {
				logisticsConsignmentVo.setExtractTheCarPersonIds(result.getData().toString());
			}else {
				logisticsConsignmentVo.setExtractTheCarPersonIds(logisticsConsignmentVo.getExtractTheCarPersonIds() + GeneralConstant.SystemBoolean.SPLIT + result.getData().toString());
			}
		}
		if(consignmentDao.updateById(logisticsConsignmentVo)) {
			result.setOK(ResultCode.CODE_STATE_200, "添加成功");
		}else {
			result.setOK(ResultCode.CODE_STATE_200, "数据添加失败，请联系管理员");
		}
		return result;
	}

	@Override
	public Result leaveTheCarPersons(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		LogisticsConsignmentVo logisticsConsignmentVo = consignmentDao.selectById(search.getConsignmentId());
		if(logisticsConsignmentVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的托运单不存在或者已取消，请重新选择");
			return result;
		}
		if(search.getLeaveTheCarPerson() != null && search.getLeaveTheCarPerson().size() > 0) {
			checkPersion(search.getLeaveTheCarPerson(), "交车人", result);
			if(!result.isSuccess()) {
				return result;
			}
			if(StringUtil.isEmpty(logisticsConsignmentVo.getLeaveTheCarPersonIds())) {
				logisticsConsignmentVo.setLeaveTheCarPersonIds(result.getData().toString());
			}else {
				logisticsConsignmentVo.setLeaveTheCarPersonIds(logisticsConsignmentVo.getLeaveTheCarPersonIds() + GeneralConstant.SystemBoolean.SPLIT + result.getData().toString());
			}
		}
		if(consignmentDao.updateById(logisticsConsignmentVo)) {
			result.setOK(ResultCode.CODE_STATE_200, "添加成功");
		}else {
			result.setOK(ResultCode.CODE_STATE_200, "数据添加失败，请联系管理员");
		}
		return result;
	}

	@Override
	public Result logisticsDistributionGPS(LogisticsDistributionSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(search.getDistributionId())) {
			result.setError("配送单不明确");
			return result;
		}
		LogisticsDistributionVo distributionVo = logisticsDistributionDao.selectByIdJoin(search.getDistributionId());
		if(distributionVo == null) {
			result.setError("你选择的配送单不存在或已删除");
			return result;
		}
		Map<String, Object> map = new HashMap<>();
		if(distributionVo.getLogisticsCarVo() == null) {
			result.setError("抱歉，暂无法定位板车");
			return result;
		}
		LogisticsCarVo logisticsCarVo = distributionVo.getLogisticsCarVo();
		map.put("driverName", logisticsCarVo.getLicensePlateNumber());
		map.put("driverName", distributionVo.getDriverName());
		map.put("phoneNumber", distributionVo.getDriverPhone());
		map.put("distributionState", distributionVo.getDistributionState());
		LogisticsDriverVo systemUsers = logisticsDriverDao.selectById(distributionVo.getDriverId());
		map.put("headPortrait", systemUsers.getHeadPortrait()!= null?systemUsers.getHeadPortrait():"");
//		RedisClient redisClient = new RedisClient();
		//车辆GPS
		/**
		 * http://api.gpsoo.net/1/devices/tracking?
		 * access_token=0011045701369822736adb020814946df1ded1c8681d026d5c5
		 * &map_type=BAIDU &account=testacc&imeis=353419031939627,353419032982170&time=1366786321
		 */
		WeixinAppToken appToken = weixinAppTokenService.getGPSApp();
		if(appToken != null) {
			String url = "http://api.gpsoo.net/1/devices/tracking?access_token=ACCESSTOKEN"
					+ "&map_type=GOOGLE&account=ACCOUNT&imeis=IMEIS&time=TIME";
			String time = System.currentTimeMillis()+"";
			if(time.length() > 10) {
				time = time.substring(0, 10);
			}
			String requestUrl = url.replace(
					"ACCESSTOKEN", appToken.getAccessToken()).replace("TIME",
							time).replace("ACCOUNT", appToken.getAppId()).replace("IMEIS", logisticsCarVo.getGpsPIN());
			JSONObject jsonObject = HTTPRequest.sendTheGetGPS(requestUrl, "GET");
			// 如果请求成功
			try {
				RedisClient redisClient = new RedisClient();
				if (null != jsonObject) {
					redisClient.getJedis().set(logisticsCarVo.getGpsPIN(), jsonObject.toString());
				}else {
					//车辆GPS
					if(redisClient.getJedis().exists(logisticsCarVo.getGpsPIN())) {
						jsonObject = new JSONObject(redisClient.getJedis().get(logisticsCarVo.getGpsPIN()));
					}
				}
				// System.out.println(jsonObject);
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				JSONObject jsonDate = jsonArray.getJSONObject(0);
				//0:正常数据 1:设备未上线 2:设备已过期 3:设备离线
				if((jsonDate.get("device_info")+"").equals("1")) {
					System.out.println(logisticsCarVo.getGpsPIN()+"设备未上线");
				}
				if((jsonDate.get("device_info")+"").equals("2")) {
					System.out.println(logisticsCarVo.getGpsPIN()+"设备已过期");
				}
				if((jsonDate.get("device_info")+"").equals("3")) {
					System.out.println(logisticsCarVo.getGpsPIN()+"设备离线");
				}
				map.put("longitude", jsonDate.get("lng"));
				map.put("latitude", jsonDate.get("lat"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			result.setError("抱歉，暂无法定位板车");
			return result;
		}
		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}

	@Override
	public Result logisticsConsignmentGPS(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception{
		LogisticsConsignmentVo logisticsConsignmentVo = consignmentDao.selectByIdJoin(search.getConsignmentId());
		if(logisticsConsignmentVo == null) {
			result.setError("你选择的托运单不存在或暂未开始配送");
			return result;
		}
		if(logisticsConsignmentVo.getGoodsCarVos() == null || logisticsConsignmentVo.getGoodsCarVos().size() <= 0) {
			result.setError("你选择的托运单尚未添加托托运车辆");
			return result;
		}
		if(StringUtil.isEmpty(logisticsConsignmentVo.getGoodsCarVos().get(0).getDistributionId())) {
			result.setError("你选择的托运单尚未开始配送");
			return result;
		}
		LogisticsDistributionVo distributionVo = logisticsDistributionDao.selectByIdJoin(logisticsConsignmentVo.getGoodsCarVos().get(0).getDistributionId());
		if(distributionVo == null) {
			result.setError("你选择的配送单不存在或已删除");
			return result;
		}
		Map<String, Object> map = new HashMap<>();
		if(distributionVo.getLogisticsCarVo() == null) {
			result.setError("抱歉，暂无法定位板车");
			return result;
		}
		LogisticsCarVo logisticsCarVo = distributionVo.getLogisticsCarVo();
		map.put("driverName", logisticsCarVo.getLicensePlateNumber());
		map.put("driverName", distributionVo.getDriverName());
		map.put("phoneNumber", distributionVo.getDriverPhone());
		map.put("distributionState", distributionVo.getDistributionState());
		LogisticsDriverVo systemUsers = logisticsDriverDao.selectById(distributionVo.getDriverId());
		map.put("headPortrait", systemUsers.getHeadPortrait()!= null?systemUsers.getHeadPortrait():"");
//		RedisClient redisClient = new RedisClient();
		//车辆GPS
		/**
		 * http://api.gpsoo.net/1/devices/tracking?
		 * access_token=0011045701369822736adb020814946df1ded1c8681d026d5c5
		 * &map_type=BAIDU &account=testacc&imeis=353419031939627,353419032982170&time=1366786321
		 */
		WeixinAppToken appToken = weixinAppTokenService.getGPSApp();
		if(appToken != null) {
			String url = "http://api.gpsoo.net/1/devices/tracking?access_token=ACCESSTOKEN"
					+ "&map_type=GOOGLE&account=ACCOUNT&imeis=IMEIS&time=TIME";
			String time = System.currentTimeMillis()+"";
			if(time.length() > 10) {
				time = time.substring(0, 10);
			}
			String requestUrl = url.replace(
					"ACCESSTOKEN", appToken.getAccessToken()).replace("TIME",
							time).replace("ACCOUNT", appToken.getAppId()).replace("IMEIS", logisticsCarVo.getGpsPIN());
			JSONObject jsonObject = HTTPRequest.sendTheGetGPS(requestUrl, "GET");
			// 如果请求成功
			try {
				RedisClient redisClient = new RedisClient();
				if (null != jsonObject) {
					redisClient.getJedis().set(logisticsCarVo.getGpsPIN(), jsonObject.toString());
				}else {
					//车辆GPS
					if(redisClient.getJedis().exists(logisticsCarVo.getGpsPIN())) {
						jsonObject = new JSONObject(redisClient.getJedis().get(logisticsCarVo.getGpsPIN()));
					}
				}
				// System.out.println(jsonObject);
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				JSONObject jsonDate = jsonArray.getJSONObject(0);
				//0:正常数据 1:设备未上线 2:设备已过期 3:设备离线
				if((jsonDate.get("device_info")+"").equals("1")) {
					System.out.println(logisticsCarVo.getGpsPIN()+"设备未上线");
				}
				if((jsonDate.get("device_info")+"").equals("2")) {
					System.out.println(logisticsCarVo.getGpsPIN()+"设备已过期");
				}
				if((jsonDate.get("device_info")+"").equals("3")) {
					System.out.println(logisticsCarVo.getGpsPIN()+"设备离线");
				}
				map.put("longitude", jsonDate.get("lng"));
				map.put("latitude", jsonDate.get("lat"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			result.setError("抱歉，暂无法定位板车");
			return result;
		}
		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}
	
	/**
	 * 
	 * 旧定位方式，由第三方主动发送到我方服务器，我方使用内存数据库保存（备份）
	 @Override
	public Result logisticsDistributionGPS(LogisticsDistributionSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(search.getDistributionId())) {
			result.setError("配送单不明确");
			return result;
		}
		LogisticsDistributionVo distributionVo = logisticsDistributionDao.selectByIdJoin(search.getDistributionId());
		if(distributionVo == null) {
			result.setError("你选择的配送单不存在或已删除");
			return result;
		}
		Map<String, Object> map = new HashMap<>();
		if(distributionVo.getLogisticsCarVo() == null) {
			result.setError("抱歉，暂无法定位板车");
			return result;
		}
		LogisticsCarVo logisticsCarVo = distributionVo.getLogisticsCarVo();
		map.put("driverName", logisticsCarVo.getLicensePlateNumber());
		map.put("driverName", distributionVo.getDriverName());
		map.put("phoneNumber", distributionVo.getDriverPhone());
		map.put("distributionState", distributionVo.getDistributionState());
		SystemUsers systemUsers = systemUsersDao.selectById(distributionVo.getDriverId());
		map.put("headPortrait", systemUsers.getHeadPortrait());
		RedisClient redisClient = new RedisClient();
		//车辆GPS
		if(redisClient.getJedis().exists(logisticsCarVo.getGpsName())) {
			map.put("gps", redisClient.getJedis().get(logisticsCarVo.getGpsName()));
		}else {
			result.setError("抱歉，暂无法定位板车");
			return result;
		}
		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}

	 * 
	 */
	public static void main(String[] args) {
//		Calendar cld = Calendar.getInstance();
//		cld.set(Calendar.YEAR, 2018);
//		cld.set(Calendar.MONDAY,2);
//		cld.set(Calendar.DATE,15);
//		//调用Calendar类中的add()，增加时间量
//		cld.add(Calendar.DATE, 50);		       
//		System.out.println("增加50天的日期为："+cld.get(Calendar.YEAR)+"年"+cld.get(Calendar.MONTH)+"月"+cld.get(Calendar.DATE)+"日");
		
//		Double number = 1000d;
//		System.out.println(number/3);
		String time = "2018-03-16 12:12";
		System.out.println("时间格式："+DateUtil.format(time));
		String pattern = "yyyy-MM-dd hh:mm";	
		try {
			Date d = new java.text.SimpleDateFormat(pattern).parse(time);
			System.out.println(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Result consignmentContract(LogisticsConsignmentSearch search, Result result) throws Exception {
		if(StringUtil.isEmpty(search.getConsignmentId())) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体的托运单查看");
			return result;
		}
		LogisticsConsignmentVo logisticsConsignmentVo = consignmentDao.selectByIdJoin(search.getConsignmentId());
		if(logisticsConsignmentVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的托运单不存在或者已取消，请重新选择");
			return result;
		}
		//查询接车人
		Map<String, Object> params = new HashMap<>();
		if(StringUtil.isNotEmpty(logisticsConsignmentVo.getExtractTheCarPersonIds())) {
			params.put("ids", new HashSet<>(Arrays.asList((logisticsConsignmentVo.getExtractTheCarPersonIds().split(GeneralConstant.SystemBoolean.SPLIT)))));
			logisticsConsignmentVo.setExtractTheCarPerson(consumerOrderUserDao.select(params));
		}
		//查询交车人
		if(StringUtil.isNotEmpty(logisticsConsignmentVo.getLeaveTheCarPersonIds())) {
			params.put("ids", new HashSet<>(Arrays.asList((logisticsConsignmentVo.getLeaveTheCarPersonIds().split(GeneralConstant.SystemBoolean.SPLIT)))));
			logisticsConsignmentVo.setLeaveTheCarPerson(consumerOrderUserDao.select(params));
		}
		logisticsConsignmentVo.setPartyA("广州喜蜂鸟网络科技服务有限公司");
		logisticsConsignmentVo.setAppointmentTimeDate(logisticsConsignmentVo.getAppointmentTime()!=null?DateUtil.format(logisticsConsignmentVo.getAppointmentTime(),"yyyy年MM月dd日 HH时mm分"):"");
		logisticsConsignmentVo.setCreateTimeStr(logisticsConsignmentVo.getCreateDate()!=null?DateUtil.format(logisticsConsignmentVo.getCreateDate(),"yyyy年MM月dd日"):"");
//		Map<String, Object> map = new HashMap<>();
//		map.put("logisticsConsignment", logisticsConsignmentVo);
		result.setOK(ResultCode.CODE_STATE_200, "", logisticsConsignmentVo);//多层式封装，已不能直接返回
//		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}
}
