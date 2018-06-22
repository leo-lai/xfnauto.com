package main.com.weixinApp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.constant.LogisticsDistributionState;
import main.com.constant.LogisticsGoodsCarState;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.logistics.constant.DeliveryState;
import main.com.logistics.constant.DrivateState;
import main.com.logistics.constant.LogisticsCarState;
import main.com.logistics.dao.dao.LogisticsCarDao;
import main.com.logistics.dao.dao.LogisticsConsignmentInPayDao;
import main.com.logistics.dao.dao.LogisticsDistributionDao;
import main.com.logistics.dao.dao.LogisticsDriverDao;
import main.com.logistics.dao.dao.LogisticsDynamicLineDao;
import main.com.logistics.dao.dao.LogisticsGoodsCarCostsDao;
import main.com.logistics.dao.dao.LogisticsGoodsCarDao;
import main.com.logistics.dao.po.LogisticsConsignmentInPay;
import main.com.logistics.dao.po.LogisticsDistribution;
import main.com.logistics.dao.po.LogisticsGoodsCar;
import main.com.logistics.dao.search.CarInDistributionSearch;
import main.com.logistics.dao.search.DriverDistributionSearch;
import main.com.logistics.dao.search.LogisticsCarSearch;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.dao.vo.LogisticsCarVo;
import main.com.logistics.dao.vo.LogisticsConsignmentVo;
import main.com.logistics.dao.vo.LogisticsDistributionVo;
import main.com.logistics.dao.vo.LogisticsDriverVo;
import main.com.logistics.dao.vo.LogisticsDynamicLineVo;
import main.com.logistics.dao.vo.LogisticsGoodsCarCostsVo;
import main.com.logistics.dao.vo.LogisticsGoodsCarVo;
import main.com.stock.dao.po.ConsumerOrderUser;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.BeanUtils;
import main.com.utils.ConvertUtil;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.utils.rlycode.RLYUtils;
import main.com.weixinApp.dao.dao.ConsumerOrderUserDao;
import main.com.weixinApp.service.EmployeeDistributionService;

@Service
public class EmployeeDistributionServiceImpl extends BaseServiceImpl<LogisticsDistribution> implements EmployeeDistributionService{

	@Autowired
	LogisticsDistributionDao logisticsDistributionDao;
	
	@Autowired
	LogisticsGoodsCarDao logisticsGoodsCarDao;
	
	@Autowired
	ConsumerOrderUserDao consumerOrderUserDao;
	
	@Autowired
	LogisticsConsignmentInPayDao inPayDao;
	
	@Autowired
	LogisticsGoodsCarCostsDao goodsCarCostsDao;
	
	@Autowired
	LogisticsDynamicLineDao logisticsDynamicLineDao;
	
	@Autowired
	LogisticsCarDao logisticsCarDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	LogisticsDriverDao logisticsDriverDao;
	
	@Override
	protected BaseDao<LogisticsDistribution> getBaseDao() {
		return logisticsDistributionDao;
	}

	@Override
	public Result distributionList(LogisticsDistributionSearch search, Result result, SystemUsers systemUsers)
			throws Exception {
		Map<String,Object> params = getParams(search,systemUsers);
		List<LogisticsDistributionVo> distributionVos = logisticsDistributionDao.selectJoinGoods(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = logisticsDistributionDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(LogisticsDistributionVo distributionVo : distributionVos) {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("distributionId", distributionVo.getDistributionId());
			map.put("distributionCode", distributionVo.getDistributionCode());
			map.put("destinationType", distributionVo.getDestinationType());
			map.put("consignmentType", distributionVo.getDestinationType());
			map.put("distributionState", distributionVo.getDistributionState());
			map.put("distributionStateName",LogisticsDistributionState.getMsgByCode(distributionVo.getDistributionState()));
			map.put("driverName", distributionVo.getDriverName());
			map.put("driverId", distributionVo.getDriverId());
			map.put("driverPhone", distributionVo.getDriverPhone());
			map.put("logisticsCar", distributionVo.getLogisticsCarVo());
			List<Map<String,Object>> list = new ArrayList<>();
			for(LogisticsGoodsCarVo goodsCarVo : distributionVo.getGoodsCarVos()) {
				Map<String,Object> goodsMap = new HashMap<>();
//				ConvertUtil.objectToMap(goodsCarVo, map2);
//				Map<String,Object> map2 = BeanUtils.toMap(goodsCarVo);
				goodsMap.put("brandId", goodsCarVo.getBrandId());
				goodsMap.put("familyId", goodsCarVo.getFamilyId());
				goodsMap.put("frameNumber", goodsCarVo.getFrameNumber());
				goodsMap.put("goodsCarId", goodsCarVo.getGoodsCarId());
				goodsMap.put("distributionId", goodsCarVo.getDistributionId());
				goodsMap.put("distributionCode", goodsCarVo.getDistributionCode());
				goodsMap.put("acceptImage", goodsCarVo.getAcceptImage());
				goodsMap.put("deliverToImage", goodsCarVo.getDeliverToImage());
				goodsMap.put("goodsCarStateName", goodsCarVo.getGoodsCarStateName());
				goodsMap.put("goodsCarState", goodsCarVo.getGoodsCarState());
				goodsMap.put("carsName", goodsCarVo.getCarsName());
				goodsMap.put("carsId", goodsCarVo.getCarsId());
				goodsMap.put("colourName", goodsCarVo.getColourName());
				goodsMap.put("colourId", goodsCarVo.getColourId());
				goodsMap.put("interiorId", goodsCarVo.getInteriorId());
				goodsMap.put("interiorName", goodsCarVo.getInteriorName());
				goodsMap.put("signPic", goodsCarVo.getSignPic());
				goodsMap.put("followInformation", goodsCarVo.getFollowInformation());
				goodsMap.put("consignmentVo", goodsCarVo.getConsignmentVo());
				goodsMap.put("carCostsVo", goodsCarVo.getCarCostsVo());
				list.add(goodsMap);
			}
//			map.put("goodsCars", distributionVo.getGoodsCarVos());//直接封装报错
			map.put("goodsCars", list);
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
		if(StringUtil.isNotEmpty(search.getKeywords())) {
			params.put("keywords", search.getKeywords());
		}
		if(StringUtil.isNotEmpty(search.getDistributionState())) {
			params.put("distributionState", search.getDistributionState());
		}
		if(StringUtil.isNotEmpty(search.getStartDate())) {
			params.put("startDate", search.getStartDate());
		}
		if(StringUtil.isNotEmpty(search.getEndDate())) {
			params.put("endDate", search.getEndDate());
		}
		if(StringUtil.isNotEmpty(search.getDriverId())) {
			params.put("driverId", search.getDriverId());
		}
		if(StringUtil.isNotEmpty(search.getDistributionStateMAX())) {
			params.put("distributionStateMAX", search.getDistributionStateMAX());			
		}
//		params.put("driverId", systemUsers.getUsersId());
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

//	@Override
	public Result distributionGoodsCarList_(LogisticsDistributionSearch search, Result result, SystemUsers users)
			throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("driverId", users.getUsersId());
		if(StringUtil.isEmpty(search.getDistributionId())) {
			result.setError("请选择具体的配送单查看");
			return result;
		}
		params.put("distributionId", search.getDistributionId());
		List<LogisticsDistributionVo> distributionVos = logisticsDistributionDao.selectJoinGoods(params);
		if(distributionVos == null || distributionVos.size() <= 0) {
			result.setError("你选择的配送单不存在或者已删除，请选择其他配送单查看");
			return result;
		}
		LogisticsDistributionVo distributionVo = distributionVos.get(0);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		if(distributionVo == null || distributionVo.getGoodsCarVos().size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		}
		Set<Integer> set = new HashSet<Integer>();
		List<LogisticsConsignmentVo> consignmentVos = new ArrayList<LogisticsConsignmentVo>();
		for(LogisticsGoodsCarVo goodsCarVo : distributionVo.getGoodsCarVos()) {
			if(goodsCarVo.getConsignmentVo() == null) {
				continue;
			}
			if(!set.add(goodsCarVo.getConsignmentVo().getConsignmentId())) {
				continue;
			}
			consignmentVos.add(goodsCarVo.getConsignmentVo());
		}
		for(LogisticsConsignmentVo consignmentVo : consignmentVos) {
			List<LogisticsGoodsCarVo> goodsCarVos = new ArrayList<LogisticsGoodsCarVo>();
			for(LogisticsGoodsCarVo goodsCarVo : distributionVo.getGoodsCarVos()) {
				if(consignmentVo.getConsignmentId().equals(goodsCarVo.getConsignmentId())) {
					goodsCarVos.add(goodsCarVo);
				}
			}
			consignmentVo.setGoodsCarVos(goodsCarVos);
		}
		Map<String,Object> allMap = new HashMap<String, Object>();	
		allMap.put("", distributionVo);
		allMap.put("distributionId", distributionVo.getDistributionId());
		allMap.put("distributionCode", distributionVo.getDistributionCode());
		allMap.put("licensePlateNumber", distributionVo.getLogisticsCarVo().getLicensePlateNumber());
		allMap.put("startDate", distributionVo.getStartDate()!=null?DateUtil.format(distributionVo.getStartDate()):"");
		allMap.put("endDate", distributionVo.getEndDate()!=null?DateUtil.format(distributionVo.getEndDate()):"");
		
		for(LogisticsConsignmentVo consignmentVo : consignmentVos) {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("consignmentId", consignmentVo.getConsignmentId());
			map.put("consignmentCode", consignmentVo.getConsignmentCode());			
			map.put("consignmentType", consignmentVo.getConsignmentType());
			map.put("createDate", consignmentVo.getCreateDate()!=null?DateUtil.format(consignmentVo.getCreateDate()):"");
			map.put("startingPointAddress", consignmentVo.getStartingPointAddress());
			map.put("destinationAddress", consignmentVo.getDestinationAddress());
//			map.put("leaveTheCarPersonPhone", consignmentVo.getLeaveTheCarPersonPhone());
//			map.put("leaveTheCarPersonName", consignmentVo.getLeaveTheCarPersonName());
//			map.put("extractTheCarPersonIdcard", consignmentVo.getExtractTheCarPersonIdcard());
//			map.put("extractTheCarPersonName", consignmentVo.getExtractTheCarPersonName());
//			map.put("extractTheCarPersonPhone", consignmentVo.getExtractTheCarPersonPhone());
			//获取交车人和接车人
			params.put("ids", consignmentVo.getThePersionId(consignmentVo.getLeaveTheCarPersonIds() + "," + consignmentVo.getExtractTheCarPersonIds()));
			List<ConsumerOrderUser> consumerOrderUsers =  consumerOrderUserDao.select(params);
			consignmentVo.getThePerson(consumerOrderUsers, consignmentVo, true);
			map.put("leaveTheCarPerson", consignmentVo.getLeaveTheCarPerson());
			map.put("extractTheCarPerson", consignmentVo.getExtractTheCarPerson());
			map.put("appointmentTime", consignmentVo.getAppointmentTime()!= null?DateUtil.format(consignmentVo.getAppointmentTime()):"");
			map.put("remarks", consignmentVo.getRemarks());
			map.put("amount", consignmentVo.getAmount()!= null?consignmentVo.getAmount().doubleValue():0.0d);
			if(consignmentVo.getGoodsCarVos() == null || consignmentVo.getGoodsCarVos().size() <= 0) {
				continue;
			}
			List<Map<String, Object>> minMaps = new ArrayList<Map<String,Object>>();
			for(LogisticsGoodsCarVo goodsCarVo : consignmentVo.getGoodsCarVos()) {
				Map<String,Object> minMap = new HashMap<String, Object>();	
				minMap.put("brandId", goodsCarVo.getBrandId());
				minMap.put("brandName", goodsCarVo.getBrandName());
				minMap.put("familyId", goodsCarVo.getFamilyId());
				minMap.put("familyName", goodsCarVo.getFamilyName());
				minMap.put("frameNumber", goodsCarVo.getFrameNumber());
				minMap.put("goodsCarState", goodsCarVo.getGoodsCarState());
				minMap.put("goodsCarId", goodsCarVo.getGoodsCarId());
				minMap.put("deliverToImage", goodsCarVo.getDeliverToImage());
				minMap.put("acceptImage", goodsCarVo.getAcceptImage());
				minMap.put("followInformation", goodsCarVo.getFollowInformation());
				minMaps.add(TakeCareMapDate.cutNullMap(minMap));
			}
			map.put("minMaps", minMaps);
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}

	@Override
	@Transactional
	public Result distributionGoodsCarImage(LogisticsDistributionSearch search, Result result, SystemUsers users)
			throws Exception {
		LogisticsGoodsCarVo goodsCarVo = logisticsGoodsCarDao.selectById(search.getGoodsCarId());
		if(goodsCarVo == null) {
			result.setError("你选择的托运车辆不存在或者已删除，请选择其它车辆进行操作");
			return result;
		}
		if(StringUtil.isEmpty(search.getAcceptImage()) && StringUtil.isEmpty(search.getDeliverToImage())) {
			result.setError("请上传图片");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getAcceptImage())) {
			goodsCarVo.setGoodsCarState(GeneralConstant.GoodsCarsState.ING);
			goodsCarVo.setAcceptImage(search.getAcceptImage());
		}
		if(StringUtil.isNotEmpty(search.getDeliverToImage())) {
			goodsCarVo.setGoodsCarState(GeneralConstant.GoodsCarsState.DOME);
			goodsCarVo.setDeliverToImage(search.getDeliverToImage());
		}
		//待更改其他两个单的状态
		if(logisticsGoodsCarDao.updateById(goodsCarVo)) {
			
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("更新数据错误，请联系管理员");
		}
		return result;
	}

	@Override
	public Result logisticsInPayPOS(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
//		if(StringUtil.isNotEmpty(search.getDistributionCode())) {
//			params.put("distributionCode", search.getDistributionCode());
//		}else {
//			result.setError("物流单选择错误");
//			return result;
//		}
		Set<String> set = new HashSet<>(Arrays.asList((search.getGoodsCarIds().split(GeneralConstant.SystemBoolean.SPLIT))));
		if(StringUtil.isNotEmpty(search.getGoodsCarIds())) {
			params.put("goodsCarIds", set);
		}else {
			result.setError("托运车辆选择错误");
			return result;
		}

//		LogisticsDistributionVo distributionVo = distributionVos.get(0);
//		List<LogisticsGoodsCarVo> goodsCarVos = distributionVo.getGoodsCarVos();
//		if(goodsCarVos == null || goodsCarVos.size() <= 0) {
//			result.setError("托运车辆获取错误");
//			return result;
//		}

//		if(!distributionVo.getDistributionState().equals(LogisticsDistributionState.Signed.getCode())) {
//			result.setError("物流单未签收完毕，暂未能开始支付");
//			return result;
//		}
		//查询每一车的总运费
		List<LogisticsGoodsCarVo> goodsCarVos2 = logisticsGoodsCarDao.selectJoin(params);
		if(goodsCarVos2 == null || goodsCarVos2.size() <= 0) {
			result.setError("获取托运车辆获取错误");
			return result;
		}
		LogisticsDistributionVo distributionVo = goodsCarVos2.get(0).getDistributionVo();
		if(distributionVo == null) {
			result.setError("你选择的物流单不存在或者已删除");
			return result;
		}
		LogisticsConsignmentVo consignmentVo = goodsCarVos2.get(0).getConsignmentVo();
		if(consignmentVo == null) {
			result.setError("你选择的托运单不存在或者已删除");
			return result;
		}
		Set<Integer> integers = new HashSet<>();
		integers.add(consignmentVo.getConsignmentId());
		for(LogisticsGoodsCarVo goodsCarVo : goodsCarVos2) {
			if(goodsCarVo.getGoodsCarState() > LogisticsGoodsCarState.Signed.getCode()) {
				result.setError("车辆："+goodsCarVo.getCarsName() + goodsCarVo.getFrameNumber() +"已支付，请勿重复支付，请重新选择");
				return result;
			}else if(goodsCarVo.getGoodsCarState() < LogisticsGoodsCarState.Signed.getCode()){
				result.setError("车辆："+goodsCarVo.getCarsName() + goodsCarVo.getFrameNumber() +"尚未签收，不能进行支付，请重新选择");
				return result;
			}
			if(integers.add(goodsCarVo.getConsignmentId())) {
				result.setError("车辆："+goodsCarVo.getCarsName() + goodsCarVo.getFrameNumber() +"托运单信息冲突，不能同时支付两张托运单里的车两运费，请重新选择");
				return result;
			}
		}
		List<LogisticsGoodsCarCostsVo> carCostsVos = goodsCarCostsDao.select(params);
		if(carCostsVos == null || carCostsVos.size() <= 0 || carCostsVos.size() != set.size()) {
			result.setError("获取车辆运费错误");
			return result;
		}
//		params.put("ids", consignmentVo.getThePersionId(consignmentVo.getExtractTheCarPersonIds()));
//		List<ConsumerOrderUser> consumerOrderUsers =  consumerOrderUserDao.select(params);		
		LogisticsConsignmentInPay inPay = new LogisticsConsignmentInPay();
		inPay.setCreateDate(new Date());
		inPay.setDistributionCode(distributionVo.getDistributionCode());
		inPay.setDistributionId(distributionVo.getDistributionId());
		inPay.setGoodsCarIds(search.getGoodsCarIds());
		inPay.setConsignmentInPayState(GeneralConstant.OrderInPayState.un_paid);
		inPay.setConsignmentCode(consignmentVo.getConsignmentCode());
		inPay.setConsignmentId(consignmentVo.getConsignmentId());
		inPay.setConsignmentInPayCode(inPayDao.getCode());
		inPay.setPayName(consignmentVo.getPurchasersName());
//		if(consumerOrderUsers != null) {
//			ConsumerOrderUser orderUser = consumerOrderUsers.get(0);
//			inPay.setConsumerOrderUserId(orderUser.getId());
//			inPay.setConsumerOrderUserName(orderUser.getUserName());
//			inPay.setPhoneNumber(orderUser.getUserPhone());
//		}
		Double amount = 0.0d;
		Double unitPrice = 0.0d;
		for(LogisticsGoodsCarCostsVo costsVo : carCostsVos) {
			amount += costsVo.getCostsAmount().doubleValue();
			unitPrice = costsVo.getCostsAmount().doubleValue();
		}
		inPay.setAmount(new BigDecimal(amount));
		//获取非专线的运费配置
		LogisticsDynamicLineVo dynamicLineVo = null;
		if(consignmentVo.getConsignmentType().equals(GeneralConstant.ConsignmentState.TYPEPOINT)) {
			List<LogisticsDynamicLineVo> dynamicLineVos = logisticsDynamicLineDao.selectJoin(new HashMap<String,Object>());
			if(dynamicLineVos == null || dynamicLineVos.size() <= 0) {
				dynamicLineVos = logisticsDynamicLineDao.select(new HashMap<String,Object>());
			}
			if(dynamicLineVos != null && dynamicLineVos.size() > 0) {
				dynamicLineVo = dynamicLineVos.get(0);
			}
//			inPay.setPayName("点对点配送物流");
		}else {
//			inPay.setPayName("专线物流");
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("destinationAddress", distributionVo.getDestinationAddress());
		map.put("startingPointAddress", distributionVo.getStartingPointAddress());
		map.put("consignmentType", consignmentVo.getConsignmentType());
		map.put("mileage", consignmentVo.getMileage());
		map.put("dynamicLine", dynamicLineVo);
		map.put("number", set.size());
		map.put("amount", amount);
		map.put("unitPrice", unitPrice);
		map.put("payCode", inPay.getConsignmentInPayCode());
		if(inPayDao.insert(inPay)) {
			result.setOK(ResultCode.CODE_STATE_200, "", map);
		}else {
			result.setError("生成支付单出错，请联系管理员");
		}
		return result;
	}
	
	
	@Override
	public Result distributionEdit(LogisticsDistributionSearch search, Result result, SystemUsers users)
			throws Exception {
		LogisticsDistribution distribution = null;
		if(StringUtil.isNotEmpty(search.getDistributionId())) {
			distribution = logisticsDistributionDao.selectById(search.getDistributionId());
			if(distribution == null) {
				result.setError("你选择的配送单不存在，请重新选择");
				return result;
			}
			//如果原来存在板车
			if(distribution.getDistributionState() > LogisticsDistributionState.Init.getCode()) {
				result.setError("物流单正在持续，已不能更改基本信息");
				return result;
			}
			
		}else {
			distribution = new LogisticsDistribution();
			distribution.setDistributionState(GeneralConstant.DistributionState.START);
			distribution.setOrgId(users.getOrgId());
			distribution.setOrgName(users.getOrgName());
			distribution.setDistributionCode(logisticsDistributionDao.getCode());
			distribution.setCreateDate(new Date());
			distribution.setSystemUserId(users.getUsersId());
			distribution.setSystemUserName(users.getRealName());
		}
		if(StringUtil.isNotEmpty(search.getConsignmentType())) {
			distribution.setDestinationType(search.getConsignmentType());
		}else {
			result.setError("请选择物流方式");
			return result;
		}
//		if(StringUtil.isNotEmpty(search.getCreateDate())) {
//			distribution.setStartDate(DateUtil.format(search.getCreateDate()));
//		}else {
//			result.setError("请选择配送时间");
//			return result;
//		}
//		if(StringUtil.isNotEmpty(search.getStartingPointAddress())) {
//			distribution.setStartingPointAddress(search.getStartingPointAddress());
//			distribution.setStartingPointLatitude(search.getStartingPointLatitude());
//			distribution.setStartingPointLongitude(search.getStartingPointLongitude());
//		}else {
//			result.setError(ResultCode.CODE_STATE_4005, "请输入起点位置");
//			return result;
//		}
//		if(StringUtil.isNotEmpty(search.getDestinationAddress())) {
//			distribution.setDestinationAddress(search.getDestinationAddress());
//			distribution.setDestinationLatitude(search.getDestinationLatitude());
//			distribution.setDestinationLongitude(search.getDestinationLongitude());
//		}else {
//			result.setError(ResultCode.CODE_STATE_4005, "请输入终点位置");
//			return result;
//		}
		if(StringUtil.isNotEmpty(search.getLogisticsCarId())) {
			LogisticsCarVo logisticsCarVo = logisticsCarDao.selectById(search.getLogisticsCarId());
			if(logisticsCarVo == null) {
				result.setError("你选择的配送车辆不存在或已被禁用，请重新选择");
				return result;
			}
			if(StringUtil.isNotEmpty(distribution.getVacancy()) && StringUtil.isNotEmpty(distribution.getLeaveVacancy())) {//更改板车一定要大于/等于板车上已分配的车				
				if(logisticsCarVo.getLogisticsCarVacancy() < (distribution.getVacancy() - distribution.getLeaveVacancy())) {
					result.setError("该配送单已分配有"+(distribution.getVacancy() - distribution.getLeaveVacancy())+"辆托运车辆，你选择的板车运输位不足");
					return result;
				}
			}
			distribution.setLogisticsCarId(logisticsCarVo.getLogisticsCarId());
			distribution.setVacancy(logisticsCarVo.getLogisticsCarVacancy());
			distribution.setDistributionCarType(logisticsCarVo.getLogisticsCarType());
			distribution.setDistributionLicensePlate(logisticsCarVo.getLicensePlateNumber());
			distribution.setLeaveVacancy(logisticsCarVo.getLogisticsCarVacancy());
			distribution.setDestinationType(logisticsCarVo.getConsignmentType());//配合班车的运输类型
		}else {
			result.setError("请选择配送车辆");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getDriverId())) {
			LogisticsDriverVo logisticsDriverVo = logisticsDriverDao.selectById(search.getDriverId());
//			SystemUsers systemUsers = systemUsersDao.selectById(search.getDriverId());
			if(logisticsDriverVo == null) {
				result.setError("你选择的配送司机不存在或者已删除，请重新选择");
				return result;
			}
			distribution.setDriverId(search.getDriverId());
			distribution.setDriverName(logisticsDriverVo.getRealName());
			distribution.setDriverPhone(logisticsDriverVo.getPhoneNumber());
		}else {
			result.setError("请选择配送司机");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			distribution.setRemarks(search.getRemarks());
		}
		if(StringUtil.isEmpty(search.getDistributionId())) {
			return logisticsDistributionDao.insertAndResultIT(distribution, result);
		}else {
			return logisticsDistributionDao.updateByIdAndResultIT(distribution, result);
		}
	}

	@Override
	public Result distributionGoodsCarList(LogisticsDistributionSearch search, Result result, SystemUsers users)
			throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgCodeLevel", users.getOrgCode());
		if(StringUtil.isEmpty(search.getDistributionId())) {
			result.setError("请选择具体的配送单查看");
			return result;
		}
		params.put("distributionId", search.getDistributionId());
		List<LogisticsDistributionVo> distributionVos = logisticsDistributionDao.selectJoinGoods(params);
		if(distributionVos == null || distributionVos.size() <= 0) {
			result.setError("你选择的配送单不存在或者已删除，请选择其他配送单查看");
			return result;
		}
		LogisticsDistributionVo distributionVo = distributionVos.get(0);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		if(distributionVo == null || distributionVo.getGoodsCarVos().size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		}
		Set<Integer> set = new HashSet<Integer>();
		List<LogisticsConsignmentVo> consignmentVos = new ArrayList<LogisticsConsignmentVo>();
		for(LogisticsGoodsCarVo goodsCarVo : distributionVo.getGoodsCarVos()) {
			if(goodsCarVo.getConsignmentVo() == null) {
				continue;
			}
			if(!set.add(goodsCarVo.getConsignmentVo().getConsignmentId())) {
				continue;
			}
			consignmentVos.add(goodsCarVo.getConsignmentVo());
		}
		for(LogisticsConsignmentVo consignmentVo : consignmentVos) {
			List<LogisticsGoodsCarVo> goodsCarVos = new ArrayList<LogisticsGoodsCarVo>();
			for(LogisticsGoodsCarVo goodsCarVo : distributionVo.getGoodsCarVos()) {
				if(consignmentVo.getConsignmentId().equals(goodsCarVo.getConsignmentId())) {
					goodsCarVos.add(goodsCarVo);
				}
			}
			consignmentVo.setGoodsCarVos(goodsCarVos);
		}
		for(LogisticsConsignmentVo consignmentVo : consignmentVos) {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("consignmentId", consignmentVo.getConsignmentId());
			map.put("consignmentCode", consignmentVo.getConsignmentCode());			
			map.put("consignmentType", consignmentVo.getConsignmentType());
			map.put("createDate", consignmentVo.getCreateDate()!=null?DateUtil.format(consignmentVo.getCreateDate()):"");
			map.put("startingPointAddress", consignmentVo.getStartingPointAddress());
			map.put("destinationAddress", consignmentVo.getDestinationAddress());
			//获取交车人和接车人
			params.put("ids", consignmentVo.getThePersionId(consignmentVo.getLeaveTheCarPersonIds() + "," + consignmentVo.getExtractTheCarPersonIds()));
			List<ConsumerOrderUser> consumerOrderUsers =  consumerOrderUserDao.select(params);
			consignmentVo.getThePerson(consumerOrderUsers, consignmentVo, true);
			map.put("leaveTheCarPerson", consignmentVo.getLeaveTheCarPerson());
			map.put("extractTheCarPerson", consignmentVo.getExtractTheCarPerson());
			map.put("appointmentTime", consignmentVo.getAppointmentTime()!= null?DateUtil.format(consignmentVo.getAppointmentTime()):"");
			map.put("remarks", consignmentVo.getRemarks());
			map.put("amount", consignmentVo.getAmount()!= null?consignmentVo.getAmount().doubleValue():0.0d);
			if(consignmentVo.getGoodsCarVos() == null || consignmentVo.getGoodsCarVos().size() <= 0) {
				continue;
			}
			List<Map<String, Object>> minMaps = new ArrayList<Map<String,Object>>();
			for(LogisticsGoodsCarVo goodsCarVo : consignmentVo.getGoodsCarVos()) {
				Map<String,Object> minMap = new HashMap<String, Object>();	
				minMap.put("brandId", goodsCarVo.getBrandId());
				minMap.put("brandName", goodsCarVo.getBrandName());
				minMap.put("familyId", goodsCarVo.getFamilyId());
				minMap.put("familyName", goodsCarVo.getFamilyName());
				minMap.put("frameNumber", goodsCarVo.getFrameNumber());
				minMap.put("goodsCarState", goodsCarVo.getGoodsCarState());
				minMap.put("goodsCarId", goodsCarVo.getGoodsCarId());
				minMaps.add(TakeCareMapDate.cutNullMap(minMap));
			}
			map.put("minMaps", minMaps);
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	@Transactional
	public Result distributionGoodsCarDelete(LogisticsDistributionSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(search.getDistributionId())) {
			result.setError("请选择具体的配送单查看");
			return result;
		}
		if(StringUtil.isEmpty(search.getGoodsCarId())) {
			result.setError("请选择具体的托运车辆进行操作");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgCodeLevel", users.getOrgCode());
		if(StringUtil.isEmpty(search.getDistributionId())) {
			result.setError("请选择具体的配送单查看");
			return result;
		}
		params.put("distributionId", search.getDistributionId());
		List<LogisticsDistributionVo> distributionVos = logisticsDistributionDao.selectJoinGoods(params);
		if(distributionVos == null || distributionVos.size() <= 0) {
			result.setError("你选择的配送单不存在或者已删除，请选择其他配送单查看");
			return result;
		}
		LogisticsDistributionVo distributionVo = distributionVos.get(0);
		if(distributionVo.getDistributionState().equals(GeneralConstant.DistributionState.DOME)) {
			result.setError("配送单已完成，已不能删减车辆");
			return result;
		}
		if(distributionVo.getDistributionState().equals(GeneralConstant.DistributionState.CANCEL)) {
			result.setError("配送单已取消，已无法删减车辆");
			return result;
		}
		if(distributionVo == null || distributionVo.getGoodsCarVos().size() <= 0) {
			result.setError("你选择的配送单不存在任何已分配的车辆，暂不能进行此操作");
			return result;
		}
		Set<Integer> set = new HashSet<Integer>();
		LogisticsGoodsCarVo logisticsGoodsCarVo = null;
		for(LogisticsGoodsCarVo goodsCarVo : distributionVo.getGoodsCarVos()) {
			set.add(goodsCarVo.getGoodsCarId());
			if(search.getGoodsCarId().equals(goodsCarVo.getGoodsCarId())) {
				logisticsGoodsCarVo = goodsCarVo;
			}
		}
		if(set.add(search.getGoodsCarId()) || logisticsGoodsCarVo == null) {
			result.setError("你选择的托运车辆不存在于你选择的配送单内，请核对数据。操作失败");
			return result;
		}
		if(!logisticsGoodsCarVo.getGoodsCarState().equals(GeneralConstant.GoodsCarsState.TAKE)) {
			result.setError("该车辆并非等待装车，已不能进行此操作");
			return result;
		}
		logisticsGoodsCarVo.setDistributionCode(0+"");
		logisticsGoodsCarVo.setDistributionId(0);
		logisticsGoodsCarVo.setGoodsCarState(GeneralConstant.GoodsCarsState.START);
		if(logisticsGoodsCarDao.updateById(logisticsGoodsCarVo)) {
			distributionVo.setLeaveVacancy(distributionVo.getLeaveVacancy() + 1);//添加空位
			logisticsDistributionDao.updateById(distributionVo);
		}else {
			result.setError("数据更新失败，请联系管理员");
		}
		return result;
	}

	@Override
	@Transactional
	public Result distributionGoodsCarAdd(LogisticsDistributionSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(search.getDistributionId())) {
			result.setError("请选择具体的配送单查看");
			return result;
		}
		if(StringUtil.isEmpty(search.getGoodsCarIds())) {
			result.setError("请选择选择需要添加的车辆");
			return result;
		}
//		LogisticsDistributionVo distributionVo = logisticsDistributionDao.selectById(search.getDistributionId());
		try {
			LogisticsDistributionVo distributionVo = logisticsDistributionDao.selectByIdJoinAll(search.getDistributionId());
			if(distributionVo == null) {
				result.setError("你选择的配送单不存在或者已删除，请选择其他配送单查看");
				return result;
			}
			//剪切出来所有选择的车辆
			if(distributionVo.getDistributionState().equals(GeneralConstant.DistributionState.DOME)) {
				result.setError("配送单已完成，已不能额外添加车辆");
				return result;
			}
			if(distributionVo.getDistributionState().equals(GeneralConstant.DistributionState.CANCEL)) {
				result.setError("配送单已取消，已不能添加车辆");
				return result;
			}
			String[] goodsCarIds = search.getGoodsCarIds().split(GeneralConstant.SystemBoolean.SPLIT);
			if(goodsCarIds.length <= 0) {
				result.setError("请选择选择需要添加的车辆");
				return result;
			}		
			/**
			 * 再更新(删除旧)
			 */
			if(distributionVo.getGoodsCarVos() != null && distributionVo.getGoodsCarVos().size() > 0) {//旧分配车辆（设计是把添加和删除结合一起，此处每一次添加全部清除旧数据）
				for(LogisticsGoodsCarVo goodsCarVo : distributionVo.getGoodsCarVos()) {
					if(!LogisticsGoodsCarState.Distributed.getCode().equals(goodsCarVo.getGoodsCarState())) {
						if(StringUtil.isNotEmpty(goodsCarVo.getFrameNumber())) {
							result.setError("已分配的车辆："+goodsCarVo.getCarsName() +":"+ goodsCarVo.getFrameNumber()+"已历过已分配状态，不能进行删除分配");
						}else {
							result.setError("已分配的车辆："+goodsCarVo.getCarsName()+"已历过已分配状态，不能进行删除分配");
						}
						return result;
					}
				}
				/**
				 * 分开两步，避免事件失效
				 */
				for(LogisticsGoodsCarVo goodsCarVo : distributionVo.getGoodsCarVos()) {
					goodsCarVo.setDistributionCode(0+"");
					goodsCarVo.setDistributionId(0);
					goodsCarVo.setGoodsCarState(LogisticsGoodsCarState.Init.getCode());
					distributionVo.setLeaveVacancy(distributionVo.getLeaveVacancy() + 1);//添加空位				
					logisticsGoodsCarDao.updateById(goodsCarVo);
				}
			}
			
			/**
			 * 查询
			 */
			Set<Integer> set = new HashSet<Integer>();
			for(String goodsCarId : goodsCarIds) {
				set.add(Integer.parseInt(goodsCarId));
			}
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("ids", set);
			List<LogisticsGoodsCarVo> goodsCarVos = logisticsGoodsCarDao.select(params);
			if(goodsCarVos == null || goodsCarVos.size() <= 0) {
				result.setError("你选择的车辆不存在或者已删除，请重新选择");
				return result;
			}

			/**
			 * 添加新
			 */
			if(distributionVo.getLeaveVacancy() < goodsCarVos.size()) {
//				result.setError("当前配送单剩余空位："+distributionVo.getLeaveVacancy()+"，你的选择超额："+(goodsCarVos.size() - distributionVo.getLeaveVacancy())+"辆");
				result.setError("当前板车运输能力为："+distributionVo.getVacancy()+"辆，您的选择已超额");
				throw new Exception("手动抛错，主动让事件回滚");
//				return result;
			}
			for(LogisticsGoodsCarVo goodsCarVo : goodsCarVos) {//分两步，同上
				if(!LogisticsGoodsCarState.Init.getCode().equals(goodsCarVo.getGoodsCarState())) {
					result.setError("车辆："+goodsCarVo.getCarsName() +":"+ goodsCarVo.getFrameNumber()+"不是等待分配状态，不能进行分配添加");
					throw new Exception("手动抛错，主动让事件回滚");
				}
			}
			for(LogisticsGoodsCarVo goodsCarVo : goodsCarVos) {
				goodsCarVo.setDistributionId(distributionVo.getDistributionId());
				goodsCarVo.setDistributionCode(distributionVo.getDistributionCode());
				goodsCarVo.setGoodsCarState(LogisticsGoodsCarState.Distributed.getCode());
				logisticsGoodsCarDao.updateById(goodsCarVo);
			}
			distributionVo.setLeaveVacancy(distributionVo.getLeaveVacancy() - goodsCarVos.size());
			return logisticsDistributionDao.updateByIdAndResultIT(distributionVo, result);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	@Override
	public Result queryDriverDistributions(DriverDistributionSearch search) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = ConvertUtil.objectToMap(search);
		List<LogisticsDistribution> list = logisticsDistributionDao.select(map);
		if(list != null && !list.isEmpty()){
			List<LogisticsDistributionVo> vos = new ArrayList<>(list.size());
			ConvertUtil.listObjectToListObject(list,vos,LogisticsDistributionVo.class);
			return new Result(vos);
		}
		return new Result(false,"无相应数据");
	}

	
	@Override
	public Result queryCarInDistribution(CarInDistributionSearch search) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map = ConvertUtil.objectToMap(search);
		List<LogisticsGoodsCar> cars = logisticsDistributionDao.queryCarInDistribution(map);
		if(cars != null & !cars.isEmpty()){
			List<LogisticsGoodsCarVo> vos = new ArrayList<>(cars.size());
			ConvertUtil.listObjectToListObject(cars,vos,LogisticsGoodsCarVo.class);
			return new Result(vos);
		}
		return new Result(false,"无相应数据");
	}

	@Override
	public Result logisticsCarListList(LogisticsCarSearch search, Result result, SystemUsers users) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgCodeLevel", users.getOrgCode());
		params.put("isEnable", true);
		List<LogisticsCarVo> logisticsCarVos = logisticsCarDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for (LogisticsCarVo logisticsCarVo : logisticsCarVos) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", logisticsCarVo.getLogisticsCarId());
			map.put("name", logisticsCarVo.getLicensePlateNumber());
			map.put("number", logisticsCarVo.getLogisticsCarVacancy());
			map.put("state", logisticsCarVo.getLogisticsCarState());
			map.put("consignmentType", logisticsCarVo.getConsignmentType());
			map.put("stateName", LogisticsCarState.getMsgByCode(logisticsCarVo.getLogisticsCarState()));
			if(GeneralConstant.LogisticsCarType.MIN.equals(logisticsCarVo.getLogisticsCarType())) {
				map.put("type", "小型车");
			}else if(GeneralConstant.LogisticsCarType.MID.equals(logisticsCarVo.getLogisticsCarType())) {
				map.put("type", "中型车");
			}else if(GeneralConstant.LogisticsCarType.MAX.equals(logisticsCarVo.getLogisticsCarType())) {
				map.put("type", "大型车");
			}else {
				map.put("type", "未知");
			}
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功", maps);
		return result;
	}

	@Override
	public Result distributionInfo(LogisticsDistributionSearch search, Result result, SystemUsers users)
			throws Exception {
		LogisticsDistributionVo distributionVo = logisticsDistributionDao.selectByIdJoinAll(search.getDistributionId());
		if(distributionVo == null) {
			result.setError("你选择的配送单不存在或者已删除");
			return result;
		}
		Map<String,Object> map = new HashMap<String, Object>();	
//		map.put("distributionId", distributionVo.getDistributionId());
//		map.put("distributionCode", distributionVo.getDistributionCode());
//		map.put("destinationType", distributionVo.getDestinationType());
//		map.put("consignmentType", distributionVo.getDestinationType());
//		map.put("distributionState", distributionVo.getDistributionState());
//		map.put("distributionStateName",LogisticsDistributionState.getMsgByCode(distributionVo.getDistributionState()));
//		map.put("driverName", distributionVo.getDriverName());
//		map.put("driverId", distributionVo.getDriverId());
//		map.put("driverPhone", distributionVo.getDriverPhone());
//		map.put("destinationAddress", distributionVo.getDestinationAddress());
//		map.put("startingPointAddress", distributionVo.getStartingPointAddress());
//		map.put("destinationLatitude", distributionVo.getDestinationLatitude());
//		map.put("destinationLongitude", distributionVo.getDestinationLongitude());
//		map.put("startingPointLatitude", distributionVo.getStartingPointLatitude());
//		map.put("startingPointLongitude", distributionVo.getStartingPointLongitude());
//		map.put("destinationLongitude", distributionVo.getDestinationLongitude());
//		map.put("systemUserId", distributionVo.getSystemUserId());
//		map.put("systemUserName", distributionVo.getSystemUserName());
//		map.put("createDate", DateUtil.format(distributionVo.getCreateDate()));
//		map.put("logisticsCar", distributionVo.getLogisticsCarVo());
//		map.put("goodsCars", distributionVo.getGoodsCarVos());
		map.put("distributionId", distributionVo.getDistributionId());
		map.put("distributionCode", distributionVo.getDistributionCode());
		map.put("destinationType", distributionVo.getDestinationType());
		map.put("destinationAddress", distributionVo.getDestinationAddress());
		map.put("destinationLatitude", distributionVo.getDestinationLatitude());
		map.put("destinationLongitude", distributionVo.getDestinationLongitude());
		map.put("startingPointAddress", distributionVo.getStartingPointAddress());
		map.put("startingPointLatitude", distributionVo.getStartingPointLatitude());
		map.put("startingPointLongitude", distributionVo.getStartingPointLongitude());
		map.put("remarks", distributionVo.getRemarks());
		map.put("systemUserId", distributionVo.getSystemUserId());
		map.put("systemUserName", distributionVo.getSystemUserName());
		map.put("createDate", DateUtil.format(distributionVo.getCreateDate()));
		map.put("destinationType", distributionVo.getDestinationType());
		map.put("consignmentType", distributionVo.getDestinationType());
		map.put("distributionState", distributionVo.getDistributionState());
		map.put("distributionStateName",LogisticsDistributionState.getMsgByCode(distributionVo.getDistributionState()));
		map.put("driverName", distributionVo.getDriverName());
		map.put("driverId", distributionVo.getDriverId());
		map.put("driverPhone", distributionVo.getDriverPhone());
		map.put("logisticsCar", distributionVo.getLogisticsCarVo());
		List<Map<String,Object>> list = new ArrayList<>();
		for(LogisticsGoodsCarVo goodsCarVo : distributionVo.getGoodsCarVos()) {
			Map<String,Object> goodsMap = new HashMap<>();
//			ConvertUtil.objectToMap(goodsCarVo, map2);
//			Map<String,Object> map2 = BeanUtils.toMap(goodsCarVo);
			goodsMap.put("brandId", goodsCarVo.getBrandId());
			goodsMap.put("familyId", goodsCarVo.getFamilyId());
			goodsMap.put("frameNumber", goodsCarVo.getFrameNumber());
			goodsMap.put("goodsCarId", goodsCarVo.getGoodsCarId());
			goodsMap.put("distributionId", goodsCarVo.getDistributionId());
			goodsMap.put("distributionCode", goodsCarVo.getDistributionCode());
			goodsMap.put("acceptImage", goodsCarVo.getAcceptImage());
			goodsMap.put("deliverToImage", goodsCarVo.getDeliverToImage());
			goodsMap.put("goodsCarStateName", goodsCarVo.getGoodsCarStateName());
			goodsMap.put("goodsCarState", goodsCarVo.getGoodsCarState());
			goodsMap.put("carsName", goodsCarVo.getCarsName());
			goodsMap.put("carsId", goodsCarVo.getCarsId());
			goodsMap.put("colourName", goodsCarVo.getColourName());
			goodsMap.put("colourId", goodsCarVo.getColourId());
			goodsMap.put("signPic", goodsCarVo.getSignPic());
			goodsMap.put("consignmentVo", goodsCarVo.getConsignmentVo());
			goodsMap.put("carCostsVo", goodsCarVo.getCarCostsVo());
			goodsMap.put("interiorId", goodsCarVo.getInteriorId());
			goodsMap.put("interiorName", goodsCarVo.getInteriorName());
			list.add(goodsMap);
		}
//		map.put("goodsCars", distributionVo.getGoodsCarVos());//直接封装报错
		map.put("goodsCars", list);
		if(distributionVo.getLogisticsDriverVo() != null) {
			distributionVo.getLogisticsDriverVo().setPassword("");
			distributionVo.getLogisticsDriverVo().setSessionId("");
			distributionVo.getLogisticsDriverVo().setBasePay(0.0d);
			map.put("logisticsDriver", distributionVo.getLogisticsDriverVo());
		}else {
			map.put("logisticsDriver", new HashMap<>());
		}
//		maps.add(TakeCareMapDate.cutNullMap(map));
		
		result.setOK(ResultCode.CODE_STATE_200, "", map);
//		result.setOK(ResultCode.CODE_STATE_200, "", ConvertUtil.objectToMap(distribution));
		return result;
	}
	
	@Override
	public Result distributionInfo(LogisticsDistributionSearch search, Result result)
			throws Exception {
		LogisticsDistributionVo distributionVo = logisticsDistributionDao.selectByIdJoinAll(search.getDistributionId());
		if(distributionVo == null) {
			result.setError("你选择的配送单不存在或者已删除");
			return result;
		}
		Map<String,Object> map = new HashMap<String, Object>();	
		map.put("distributionId", distributionVo.getDistributionId());
		map.put("distributionCode", distributionVo.getDistributionCode());
		map.put("destinationType", distributionVo.getDestinationType());
		map.put("destinationAddress", distributionVo.getDestinationAddress());
		map.put("destinationLatitude", distributionVo.getDestinationLatitude());
		map.put("destinationLongitude", distributionVo.getDestinationLongitude());
		map.put("startingPointAddress", distributionVo.getStartingPointAddress());
		map.put("startingPointLatitude", distributionVo.getStartingPointLatitude());
		map.put("startingPointLongitude", distributionVo.getStartingPointLongitude());
		map.put("remarks", distributionVo.getRemarks());
		map.put("systemUserId", distributionVo.getSystemUserId());
		map.put("systemUserName", distributionVo.getSystemUserName());
		map.put("createDate", DateUtil.format(distributionVo.getCreateDate()));
		map.put("destinationType", distributionVo.getDestinationType());
		map.put("consignmentType", distributionVo.getDestinationType());
		map.put("distributionState", distributionVo.getDistributionState());
		map.put("distributionStateName",LogisticsDistributionState.getMsgByCode(distributionVo.getDistributionState()));
		map.put("driverName", distributionVo.getDriverName());
		map.put("driverId", distributionVo.getDriverId());
		map.put("driverPhone", distributionVo.getDriverPhone());
		map.put("logisticsCar", distributionVo.getLogisticsCarVo());
		List<Map<String,Object>> list = new ArrayList<>();
		for(LogisticsGoodsCarVo goodsCarVo : distributionVo.getGoodsCarVos()) {
			Map<String,Object> goodsMap = new HashMap<>();
			goodsMap.put("brandId", goodsCarVo.getBrandId());
			goodsMap.put("familyId", goodsCarVo.getFamilyId());
			goodsMap.put("frameNumber", goodsCarVo.getFrameNumber());
			goodsMap.put("goodsCarId", goodsCarVo.getGoodsCarId());
			goodsMap.put("distributionId", goodsCarVo.getDistributionId());
			goodsMap.put("distributionCode", goodsCarVo.getDistributionCode());
			goodsMap.put("acceptImage", goodsCarVo.getAcceptImage());
			goodsMap.put("deliverToImage", goodsCarVo.getDeliverToImage());
			goodsMap.put("goodsCarStateName", goodsCarVo.getGoodsCarStateName());
			goodsMap.put("goodsCarState", goodsCarVo.getGoodsCarState());
			goodsMap.put("carsName", goodsCarVo.getCarsName());
			goodsMap.put("carsId", goodsCarVo.getCarsId());
			goodsMap.put("colourName", goodsCarVo.getColourName());
			goodsMap.put("colourId", goodsCarVo.getColourId());
			goodsMap.put("signPic", goodsCarVo.getSignPic());
			goodsMap.put("followInformation", goodsCarVo.getFollowInformation());
			goodsMap.put("consignmentVo", goodsCarVo.getConsignmentVo());
			goodsMap.put("carCostsVo", goodsCarVo.getCarCostsVo());
			list.add(goodsMap);
		}
		map.put("goodsCars", list);
		if(distributionVo.getLogisticsDriverVo() != null) {
			distributionVo.getLogisticsDriverVo().setPassword("");
			distributionVo.getLogisticsDriverVo().setSessionId("");
			distributionVo.getLogisticsDriverVo().setBasePay(0.0d);
			map.put("logisticsDriver", distributionVo.getLogisticsDriverVo());
		}else {
			map.put("logisticsDriver", new HashMap<>());
		}
		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}

	@Override
	@Transactional
	public Result distributionDelivery(LogisticsDistributionSearch search, Result result, SystemUsers users)
			throws Exception {
		LogisticsDistributionVo distributionVo = logisticsDistributionDao.selectByIdJoinAll(search.getDistributionId());
		if(distributionVo == null) {
			result.setError("你选择的配送单不存在或者已删除");
			return result;
		}
		if(GeneralConstant.DistributionState.START > distributionVo.getDistributionState()) {
			result.setError("该物流单已取消，无法进行派单");
			return result;
		}else if(GeneralConstant.DistributionState.START < distributionVo.getDistributionState()) {
			result.setError("该物流单已历过派单流程，无需重新派单");
			return result;
		}
		//各种检测
//		if(StringUtil.isEmpty(distributionVo.getDriverId())) {
//			result.setError("物流单缺少板车司机，派单失败");
//			return result;
//		}
		if(distributionVo.getLogisticsDriverVo() == null) {
			result.setError("物流单缺少板车司机，派单失败");
			return result;
		}else {
			if(distributionVo.getLogisticsDriverVo().getStatus() > DrivateState.FREE.getCode()) {
				result.setError("司机："+distributionVo.getLogisticsDriverVo().getRealName()+"正处于运输状态，暂不能向其派单");
				return result;
			}else if(distributionVo.getLogisticsDriverVo().getStatus() < DrivateState.FREE.getCode()) {
				result.setError("司机："+distributionVo.getLogisticsDriverVo().getRealName()+"已离职，不能向其派单");
				return result;
			}
		}
		if(distributionVo.getLogisticsCarVo() == null) {
			result.setError("物流单缺少板车，派单失败");
			return result;
		}else {
			if(distributionVo.getLogisticsCarVo().getLogisticsCarState().equals(LogisticsCarState.BUSY.getCode())) {
				result.setError("板车："+distributionVo.getLogisticsCarVo().getLicensePlateNumber()+"正在运输中，不能向其派单");
				return result;
			}
		}
		if(distributionVo.getGoodsCarVos() == null || distributionVo.getGoodsCarVos().size() <= 0) {
			result.setError("物流单尚未分配派送车辆，派单失败");
			return result;
		}
//		if(StringUtil.isEmpty(distributionVo.getStartingPointAddress())) {
//			result.setError("物流单的起点位置未知，派单失败");
//			return result;
//		}
//		if(StringUtil.isEmpty(distributionVo.getStartingPointAddress())) {
//			result.setError("物流单的终点位置未知，派单失败");
//			return result;
//		}
		try {
			for(LogisticsGoodsCarVo carVO : distributionVo.getGoodsCarVos()) {
				LogisticsGoodsCar car = new LogisticsGoodsCar();
				car.setGoodsCarId(carVO.getGoodsCarId());
				car.setGoodsCarState(LogisticsGoodsCarState.Dispatched.getCode());
				logisticsGoodsCarDao.updateById(car);
			}
			distributionVo.setDistributionState(LogisticsDistributionState.Distributed.getCode());		
			if(logisticsDistributionDao.updateById(distributionVo)) {
				result.setOK(ResultCode.CODE_STATE_200, "派单成功");
			}else {
				result.setError("更新物流单数据失败，请联系管理员");
				throw new Exception();
			}
			distributionVo.getLogisticsDriverVo().setStatus(DrivateState.BUSY.getCode());
			distributionVo.getLogisticsCarVo().setLogisticsCarState(LogisticsCarState.BUSY.getCode());
			if(logisticsCarDao.updateById(distributionVo.getLogisticsCarVo())) {				
			}else {
				result.setError("更新板车数据失败，请联系管理员");
				throw new Exception();
			}
			if(logisticsDriverDao.updateById(distributionVo.getLogisticsDriverVo())) {
				//发送短信通知
				RLYUtils.sendTemplateNotice(distributionVo.getLogisticsDriverVo().getPhoneNumber(), "239007", new String[] {});
			}else {
				result.setError("更新司机数据失败，请联系管理员");
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}

	@Override
	@Transactional
	public Result distributionOrderTaking(LogisticsDistributionSearch search, Result result, LogisticsDriverVo users)
			throws Exception {
		LogisticsDistributionVo distributionVo = logisticsDistributionDao.selectByIdJoinAll(search.getDistributionId());
		if(distributionVo == null) {
			result.setError("你选择的配送单不存在或者已删除");
			return result;
		}
		if(GeneralConstant.DistributionState.ING > distributionVo.getDistributionState()) {
			result.setError("该物流单尚未派单，尚不能接单");
			return result;
		}else if(GeneralConstant.DistributionState.DOME <= distributionVo.getDistributionState()) {
			result.setError("该物流单已接单，无需重新接单");
			return result;
		}
		try {
			for(LogisticsGoodsCarVo carVO : distributionVo.getGoodsCarVos()) {
				LogisticsGoodsCar car = new LogisticsGoodsCar();
				car.setGoodsCarId(carVO.getGoodsCarId());
				car.setGoodsCarState(LogisticsGoodsCarState.OrderReceived.getCode());
				logisticsGoodsCarDao.updateById(carVO);
			}
			distributionVo.setDistributionState(LogisticsDistributionState.OrderReceived.getCode());
			if(logisticsDistributionDao.updateById(distributionVo)) {
				result.setOK(ResultCode.CODE_STATE_200, "接单成功");
			}else {
				result.setError("更新数据失败，请联系管理员");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}
}
