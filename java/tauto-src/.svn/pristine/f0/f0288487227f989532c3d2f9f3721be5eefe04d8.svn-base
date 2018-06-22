package main.com.logistics.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.logistics.dao.dao.LogisticsConsignmentDao;
import main.com.logistics.dao.dao.LogisticsGoodsCarDao;
import main.com.logistics.dao.po.LogisticsConsignment;
import main.com.logistics.dao.po.LogisticsConsignmentInPay;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.dao.vo.LogisticsConsignmentVo;
import main.com.logistics.dao.vo.LogisticsGoodsCarVo;
import main.com.logistics.service.LogisticsConsignmentService;
import main.com.stock.dao.po.ConsumerOrderUser;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;
import main.com.system.dao.vo.OrganizationVo;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.IdcardValidator;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.weixinApp.dao.dao.ConsumerOrderUserDao;
import main.com.weixinApp.dao.vo.ConsumerOrderUserVO;

@Service
public class LogisticsConsignmentServiceImpl extends BaseServiceImpl<LogisticsConsignment> implements LogisticsConsignmentService{

	@Autowired
	LogisticsConsignmentDao logisticsConsignmentDao;
	
	@Autowired
	LogisticsGoodsCarDao logisticsGoodsCarDao;
	
	@Autowired
	ConsumerOrderUserDao consumerOrderUserDao;
	
	@Override
	protected BaseDao<LogisticsConsignment> getBaseDao() {
		return logisticsConsignmentDao;
	}
	
	@Override
	public Result consignmentList(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		Map<String,Object> params =  getParams(search,users);
		List<LogisticsConsignmentVo> consignmentVos = logisticsConsignmentDao.selectJoin(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = logisticsConsignmentDao.getCountJoin(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(LogisticsConsignmentVo consignmentVo : consignmentVos) {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("consignmentId", consignmentVo.getConsignmentId());
			map.put("consignmentCode", consignmentVo.getConsignmentCode());
			map.put("consignmentInPayState", consignmentVo.getConsignmentInPayState());
			map.put("consignmentType", consignmentVo.getConsignmentType());
			map.put("createDate", consignmentVo.getCreateDate()!=null?DateUtil.format(consignmentVo.getCreateDate()):"");
			map.put("startingPointAddress", consignmentVo.getStartingPointAddress());
			map.put("destinationAddress", consignmentVo.getDestinationAddress());
//			map.put("leaveTheCarPersonPhone", consignmentVo.getLeaveTheCarPersonPhone());
			map.put("appointmentTime", consignmentVo.getAppointmentTime()!= null?DateUtil.format(consignmentVo.getAppointmentTime()):"");
			map.put("remarks", consignmentVo.getRemarks());
			map.put("consignmentState", consignmentVo.getConsignmentState());
			map.put("amount", consignmentVo.getAmount()!= null?consignmentVo.getAmount().doubleValue():0.0d);
			map.put("consignmentInPayState", consignmentVo.getConsignmentInPayState());
			if(consignmentVo.getConsignmentInPayVos() != null && consignmentVo.getConsignmentInPayVos().size() > 0) {
				for(LogisticsConsignmentInPay consignmentInPay : consignmentVo.getConsignmentInPayVos()) {
					map.put("consignmentInPayState", consignmentInPay.getPayMethod());
				}
			}else {
				map.put("consignmentInPayState", "");
			}
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}
	
	public Map<String,Object> getParams(LogisticsConsignmentSearch search,SystemUsers systemUsers){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getConsignmentCode())) {
			params.put("consignmentCode", search.getConsignmentCode());
		}
		if(StringUtil.isNotEmpty(search.getConsignmentState())) {
			params.put("onConsignmentState", true);
		}
		if(StringUtil.isNotEmpty(search.getConsignmentState())) {
			params.put("payMethod", search.getPayMethod());
		}
		if(StringUtil.isNotEmpty(search.getConsignmentInPayState())) {
			params.put("consignmentInPayState", search.getConsignmentInPayState());
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

	@Override
	@Transactional
	public Result consignmentEdit(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(search.getConsignmentId())) {
			result.setError("请先选择托运单");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("consignmentId", search.getConsignmentId());
		List<LogisticsConsignmentVo> consignmentVos = logisticsConsignmentDao.select(params);
		if(consignmentVos == null || consignmentVos.size() <= 0) {
			result.setError("你选择的托运单不存在或者已删除");
			return result;
		}
		LogisticsConsignmentVo consignmentVo = consignmentVos.get(0);
		if(consignmentVo.getGoodsCarVos() == null || consignmentVo.getGoodsCarVos().size() <= 0) {
			result.setError("你选择的托运单下不存在任何托运车辆，废托运单，不能进行编辑，请直接删除");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getAppointmentTime())) {
			consignmentVo.setAppointmentTime(DateUtil.format(search.getAppointmentTime()));
		}else {
			result.setError("请选择预约时间");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getConsignmentType())) {
			consignmentVo.setConsignmentType(search.getConsignmentType());
		}else {
			result.setError("请选择配送方式");
			return result;
		}
//		if(StringUtil.isNotEmpty(search.getLeaveTheCarPersonName())) {
//			consignmentVo.setLeaveTheCarPersonName(search.getLeaveTheCarPersonName());
//		}else {
//			result.setError("请输入交车人名称");
//			return result;
//		}
//		if(StringUtil.isNotEmpty(search.getLeaveTheCarPersonPhone())) {
//			consignmentVo.setLeaveTheCarPersonPhone(search.getLeaveTheCarPersonPhone());
//		}else {
//			result.setError("请输入提车人联系方式");
//			return result;
//		}
//		if(StringUtil.isNotEmpty(search.getExtractTheCarPersonName())) {
//			consignmentVo.setExtractTheCarPersonName(search.getExtractTheCarPersonName());
//		}else {
//			result.setError("请输入提车人名称");
//			return result;
//		}
//		if(StringUtil.isNotEmpty(search.getExtractTheCarPersonPhone())) {
//			consignmentVo.setExtractTheCarPersonPhone(search.getExtractTheCarPersonPhone());
//		}else {
//			result.setError("请输入提车人联系方式");
//			return result;
//		}
//		if(StringUtil.isNotEmpty(search.getExtractTheCarPersonIdcard())) {
//			IdcardValidator validator = new IdcardValidator();
//			if(!validator.isIdcard(search.getExtractTheCarPersonIdcard())) {
//				result.setError("提车人身份证格式错误，请核对数据");
//				return result;
//			}
//			consignmentVo.setExtractTheCarPersonIdcard(search.getExtractTheCarPersonIdcard());
//		}else {
//			result.setError("请输入提车人身份证号");
//			return result;
//		}
		if(StringUtil.isNotEmpty(search.getGoodsCarInfo())) {
			result.setError("托运车辆错误");
			return result;
		}
		//剪切
		List<Map<String,String>> maps = cutOffGoodsCar(search.getGoodsCarInfo());
		if(maps.size() <= 0) {
			result.setError("托运车辆错误");
			return result;
		}
		for(LogisticsGoodsCarVo goodsCarVo : consignmentVo.getGoodsCarVos()) {
			for(Map<String,String> map : maps) {
				if(goodsCarVo.getGoodsCarId().equals(Integer.parseInt(map.get("key")+""))) {
					goodsCarVo.setFrameNumber(map.get("value"));
				}
			}
		}
		if(logisticsConsignmentDao.updateById(consignmentVo)) {
			for(LogisticsGoodsCarVo goodsCarVo : consignmentVo.getGoodsCarVos()) {
				logisticsGoodsCarDao.updateById(goodsCarVo);
			}
			result.setOK(ResultCode.CODE_STATE_200, "更新成功");
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "数据更新失败，请联系管理员");
		}
		return result;
	}
	
	public List<Map<String,String>> cutOffGoodsCar(String goodsCarInfo){
		List<Map<String,String>> maps = new ArrayList<Map<String,String>>();
		//先把单个剪辑下来
		String[] goodsCarInfoList = goodsCarInfo.split(GeneralConstant.SystemBoolean.SPLIT_);
		for(String string : goodsCarInfoList) {
			Map<String,String> map = new HashMap<String,String>();
			String[] stringList = string.split(GeneralConstant.SystemBoolean.SPLIT);
			map.put("key", stringList[0]);
			map.put("value", stringList[1]);
			maps.add(map);
		}
		return maps;
	}

	@Override
	public Result consignmentInfo(LogisticsConsignmentSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(search.getConsignmentId())) {
			result.setError("请先选择托运单");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("consignmentId", search.getConsignmentId());
		List<LogisticsConsignmentVo> consignmentVos = logisticsConsignmentDao.selectJoin(params);
		if(consignmentVos == null || consignmentVos.size() <= 0) {
			result.setError("你选择的托运单不存在或者已删除");
			return result;
		}
		LogisticsConsignmentVo consignmentVo = consignmentVos.get(0);
		Map<String,Object> map = new HashMap<String, Object>();	
		map.put("consignmentId", consignmentVo.getConsignmentId());
		map.put("appointmentTime", consignmentVo.getAppointmentTime()!=null?DateUtil.format(consignmentVo.getAppointmentTime()):"");
		map.put("consignmentType", consignmentVo.getConsignmentType());
		//获取交车人和接车人
		params.put("ids", consignmentVo.getThePersionId(consignmentVo.getLeaveTheCarPersonIds() + "," + consignmentVo.getExtractTheCarPersonIds()));
		List<ConsumerOrderUser> consumerOrderUsers =  consumerOrderUserDao.select(params);
		consignmentVo.getThePerson(consumerOrderUsers, consignmentVo, true);
//		map.put("leaveTheCarPersonPhone", consignmentVo.getLeaveTheCarPersonPhone());
//		map.put("leaveTheCarPersonName", consignmentVo.getLeaveTheCarPersonName());
//		map.put("extractTheCarPersonPhone", consignmentVo.getExtractTheCarPersonPhone());
//		map.put("extractTheCarPersonName", consignmentVo.getExtractTheCarPersonName());
//		map.put("extractTheCarPersonIdcard", consignmentVo.getExtractTheCarPersonIdcard());
		
		map.put("leaveTheCarPerson", consignmentVo.getLeaveTheCarPerson());
		map.put("extractTheCarPerson", consignmentVo.getExtractTheCarPerson());
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		for(LogisticsGoodsCarVo goodsCarVo : consignmentVo.getGoodsCarVos()) {
			Map<String,Object> goodsMap = new HashMap<String, Object>();	
			goodsMap.put("carsName", goodsCarVo.getBrandName() + goodsCarVo.getFamilyName());
			goodsMap.put("brandId", goodsCarVo.getBrandId());
			goodsMap.put("familyId", goodsCarVo.getFamilyId());
			goodsMap.put("frameNumber", goodsCarVo.getFrameNumber());
			goodsMap.put("goodsCarId", goodsCarVo.getGoodsCarId());
			goodsMap.put("distributionId", goodsCarVo.getDistributionId());
			goodsMap.put("distributionCode", goodsCarVo.getDistributionCode());
			goodsMap.put("acceptImage", goodsCarVo.getAcceptImage());
			goodsMap.put("colourName", goodsCarVo.getColourName());
			goodsMap.put("interiorName", goodsCarVo.getInteriorName());
			goodsMap.put("acceptImage", goodsCarVo.getAcceptImage());
			goodsMap.put("deliverToImage", goodsCarVo.getDeliverToImage());
			goodsMap.put("followInformation", goodsCarVo.getFollowInformation());
			maps.add(TakeCareMapDate.cutNullMap(goodsMap));
		}
		map.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "",map);
		return result;
	}
	
	@Override
	public Result consignmentCarList(LogisticsConsignmentSearch search, Result result, SystemUsers systemUsers) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("onConsignmentState", true);
		params.put("onGoodsCarState", true);
		params.put("orgCodeLevel", systemUsers.getOrgCode());
		params.put("carNotDelete", true);
		List<LogisticsConsignmentVo> consignmentVos = logisticsConsignmentDao.selectJoin(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(LogisticsConsignmentVo consignmentVo : consignmentVos) {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("consignmentId", consignmentVo.getConsignmentId());
			map.put("consignmentCode", consignmentVo.getConsignmentCode());			
			map.put("consignmentType", consignmentVo.getConsignmentType());
			map.put("createDate", consignmentVo.getCreateDate()!=null?DateUtil.format(consignmentVo.getCreateDate()):"");
			map.put("startingPointAddress", consignmentVo.getStartingPointAddress());
			map.put("destinationAddress", consignmentVo.getDestinationAddress());
//			map.put("leaveTheCarPersonPhone", consignmentVo.getLeaveTheCarPersonPhone());
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
				minMaps.add(TakeCareMapDate.cutNullMap(minMap));
			}
			map.put("minMaps", minMaps);
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}
}
