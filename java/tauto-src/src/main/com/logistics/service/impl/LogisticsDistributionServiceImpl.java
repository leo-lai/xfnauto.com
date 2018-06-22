package main.com.logistics.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.logistics.dao.dao.LogisticsCarDao;
import main.com.logistics.dao.dao.LogisticsDistributionDao;
import main.com.logistics.dao.dao.LogisticsGoodsCarDao;
import main.com.logistics.dao.po.LogisticsDistribution;
import main.com.logistics.dao.po.LogisticsGoodsCar;
import main.com.logistics.dao.search.CarInDistributionSearch;
import main.com.logistics.dao.search.DriverDistributionSearch;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.dao.vo.LogisticsCarVo;
import main.com.logistics.dao.vo.LogisticsConsignmentVo;
import main.com.logistics.dao.vo.LogisticsDistributionVo;
import main.com.logistics.dao.vo.LogisticsGoodsCarVo;
import main.com.logistics.service.LogisticsDistributionService;
import main.com.stock.dao.po.ConsumerOrderUser;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.ConvertUtil;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.weixinApp.dao.dao.ConsumerOrderUserDao;

@Service
public class LogisticsDistributionServiceImpl extends BaseServiceImpl<LogisticsDistribution>
		implements LogisticsDistributionService {

	@Autowired
	LogisticsDistributionDao logisticsDistributionDao;
	
	@Autowired
	LogisticsCarDao logisticsCarDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	LogisticsGoodsCarDao logisticsGoodsCarDao;
	
	@Autowired
	ConsumerOrderUserDao consumerOrderUserDao;
	
	@Override
	protected BaseDao<LogisticsDistribution> getBaseDao() {
		return logisticsDistributionDao;
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
	@Override
	public Result distributionInfo(LogisticsDistributionSearch search, Result result, SystemUsers users)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
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
		}else {
			distribution = new LogisticsDistribution();
			distribution.setDistributionState(GeneralConstant.DistributionState.START);
			distribution.setOrgId(users.getOrgId());
			distribution.setOrgName(users.getOrgName());
			distribution.setDistributionCode(logisticsDistributionDao.getCode());
			distribution.setCreateDate(new Date());
		}
		if(StringUtil.isNotEmpty(search.getCreateDate())) {
			distribution.setStartDate(DateUtil.format(search.getCreateDate()));
		}else {
			result.setError("请选择配送时间");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getLogisticsCarId())) {
			LogisticsCarVo logisticsCarVo = logisticsCarDao.selectById(search.getLogisticsCarId());
			if(logisticsCarVo == null) {
				result.setError("你选择的配送车辆不存在或已被禁用，请重新选择");
				return result;
			}
			distribution.setLogisticsCarId(logisticsCarVo.getLogisticsCarId());
			distribution.setVacancy(logisticsCarVo.getLogisticsCarVacancy());
			distribution.setDistributionCarType(logisticsCarVo.getLogisticsCarType());
			distribution.setDistributionLicensePlate(logisticsCarVo.getLicensePlateNumber());
			distribution.setLeaveVacancy(logisticsCarVo.getLogisticsCarVacancy());
		}else {
			result.setError("请选择配送车辆");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getDriverId())) {
			SystemUsers systemUsers = systemUsersDao.selectById(search.getDriverId());
			if(systemUsers == null) {
				result.setError("你选择的配送司机不存在或者已删除，请重新选择");
				return result;
			}
			distribution.setDriverId(search.getDriverId());
			distribution.setDriverName(systemUsers.getRealName());
			distribution.setDriverPhone(systemUsers.getPhoneNumber());
			if(StringUtil.isEmpty(systemUsers.getIdCardPicOn()) && StringUtil.isEmpty(systemUsers.getIdCardPicOn())) {
				result.setError("请上传身份证正面");
				return result;
			}else {
				systemUsers.setIdCardPicOn(systemUsers.getIdCardPicOn());
			}
			if(StringUtil.isEmpty(systemUsers.getIdCardPicOff()) && StringUtil.isEmpty(systemUsers.getIdCardPicOff())) {
				result.setError("请上传身份证反面");
				return result;
			}else {
				systemUsers.setIdCardPicOff(systemUsers.getIdCardPicOff());
			}
			if(StringUtil.isEmpty(systemUsers.getIdCardPicOff()) || StringUtil.isEmpty(systemUsers.getIdCardPicOn())) {
				systemUsersDao.updateById(systemUsers);
			}
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
		LogisticsDistributionVo distributionVo = logisticsDistributionDao.selectById(search.getDistributionId());
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
		if(distributionVo.getLeaveVacancy() < goodsCarVos.size()) {
			result.setError("当前配送单剩余空位："+distributionVo.getLeaveVacancy()+"，你的选择超额："+(goodsCarVos.size() - distributionVo.getLeaveVacancy())+"辆");
			return result;
		}
		for(LogisticsGoodsCarVo goodsCarVo : goodsCarVos) {
			if(!GeneralConstant.GoodsCarsState.START.equals(goodsCarVo.getGoodsCarState())) {
				result.setError("车辆："+goodsCarVo.getFrameNumber()+"不是等待分配状态，不能进行分配添加");
				return result;
			}
		}
		for(LogisticsGoodsCarVo goodsCarVo : goodsCarVos) {
			goodsCarVo.setDistributionId(distributionVo.getDistributionId());
			goodsCarVo.setDistributionCode(distributionVo.getDistributionCode());
			goodsCarVo.setGoodsCarState(GeneralConstant.GoodsCarsState.TAKE);
			logisticsGoodsCarDao.updateById(goodsCarVo);
		}
		distributionVo.setLeaveVacancy(distributionVo.getLeaveVacancy() - goodsCarVos.size());
		return logisticsDistributionDao.updateByIdAndResultIT(distributionVo, result);
	}
	
	@Override
	public Result queryDriverDistributions(DriverDistributionSearch search) throws Exception {
		// TODO Auto-generated method stub
		Map map = ConvertUtil.objectToMap(search);
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
		Map map = ConvertUtil.objectToMap(search);
		List<LogisticsGoodsCar> cars = logisticsDistributionDao.queryCarInDistribution(map);
		if(cars != null & !cars.isEmpty()){
			List<LogisticsGoodsCarVo> vos = new ArrayList<>(cars.size());
			ConvertUtil.listObjectToListObject(cars,vos,LogisticsGoodsCarVo.class);
			return new Result(vos);
		}
		return new Result(false,"无相应数据");
	}
	
	
}
