package main.com.logistics.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.logistics.dao.dao.LogisticsCarDao;
import main.com.logistics.dao.po.LogisticsCar;
import main.com.logistics.dao.search.LogisticsCarSearch;
import main.com.logistics.dao.vo.LogisticsCarVo;
import main.com.logistics.service.LogisticsCarService;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class LogisticsCarServiceImpl extends BaseServiceImpl<LogisticsCar> implements LogisticsCarService {

	@Resource
	LogisticsCarDao logisticsCarDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Override
	protected BaseDao<LogisticsCar> getBaseDao() {
		return logisticsCarDao;
	}

	@Override
	public Result logisticsCarList(LogisticsCarSearch search, Result result, SystemUsers users) {
		Map<String, Object> params = getParams(search, users);
		List<LogisticsCarVo> logisticsCarVos = logisticsCarDao.select(params);
		Map<String, Object> allMap = new HashMap<String, Object>();
		Long total = logisticsCarDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for (LogisticsCarVo logisticsCarVo : logisticsCarVos) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("logisticsCarId", logisticsCarVo.getLogisticsCarId());
			map.put("licensePlateNumber", logisticsCarVo.getLicensePlateNumber());
			map.put("logisticsCarAddress", logisticsCarVo.getLogisticsCarAddress());
			map.put("orgName", logisticsCarVo.getOrgName());
			map.put("logisticsCarType", logisticsCarVo.getLogisticsCarType());
			map.put("logisticsCarVacancy", logisticsCarVo.getLogisticsCarVacancy());
			map.put("logisticsCarNature", logisticsCarVo.getLogisticsCarNature());
			map.put("remarks", logisticsCarVo.getRemarks());
			map.put("isEnable", logisticsCarVo.getIsEnable());
			map.put("gpsName", logisticsCarVo.getGpsName());
			map.put("gpsPIN", logisticsCarVo.getGpsPIN());
			map.put("status", logisticsCarVo.getLogisticsCarState());
			map.put("consignmentType", logisticsCarVo.getConsignmentType());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功", allMap);
		return result;
	}

	public Map<String,Object> getParams(LogisticsCarSearch search,SystemUsers systemUsers){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgCodeLevel", systemUsers.getOrgCode());
		params.put("keywords", search.getKeywords());
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
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
	public Result logisticsCarEdit(LogisticsCarSearch search, Result result, SystemUsers users) throws Exception {
		LogisticsCarVo logisticsCarVo = null;
		if(StringUtil.isNotEmpty(search.getLogisticsCarId())) {
			logisticsCarVo = logisticsCarDao.selectById(search.getLogisticsCarId());
			if(logisticsCarVo == null) {
				result.setError("你选择的板车不存在或者已删除，请重新选择");
				return result;
			}			
		}else {
			logisticsCarVo = new LogisticsCarVo();
			logisticsCarVo.setCreateDate(new Date());
			logisticsCarVo.setIsDelete(false);
			logisticsCarVo.setIsEnable(true);
			logisticsCarVo.setLogisticsCarState(0);//默认闲置 0闲置 1运输
		}
		if(StringUtil.isNotEmpty(search.getLicensePlateNumber())) {
			Map<String, Object> map = new HashMap<>();
			map.put("licensePlateNumber", search.getLicensePlateNumber());
			List<LogisticsCarVo> carVos = logisticsCarDao.select(map);
			if(carVos != null && carVos.size() > 0) {
				LogisticsCarVo carVo = carVos.get(0);
				if(StringUtil.isNotEmpty(search.getLogisticsCarId()) && !search.getLogisticsCarId().equals(carVo.getLogisticsCarId())) {
					result.setError("车牌号重复，该车牌在系统已存在，请核对数据");
					return result;
				}
			}
			logisticsCarVo.setLicensePlateNumber(search.getLicensePlateNumber());
		}else {
			result.setError("请输入车牌号码");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getOrgId())) {
			Organization organization = organizationDao.selectById(search.getOrgId());
			if(organization == null) {
				result.setError("你选择的组织不存在或者已禁用，请重新选择");
				return result;
			}
			logisticsCarVo.setOrgId(search.getOrgId());
			logisticsCarVo.setOrgName(organization.getShortName());
		}else {
			logisticsCarVo.setOrgId(users.getOrgId());
			logisticsCarVo.setOrgName(users.getOrgName());
//			result.setError("请选择服务组织");
//			return result;
		}
		if(StringUtil.isNotEmpty(search.getLogisticsCarType())) {
			logisticsCarVo.setLogisticsCarType(search.getLogisticsCarType());
		}else {
			logisticsCarVo.setLogisticsCarType(2);//默认是中型板车
//			result.setError("请选择板车类型");
//			return result;
		}
		if(StringUtil.isNotEmpty(search.getConsignmentType())) {
			logisticsCarVo.setConsignmentType(search.getConsignmentType());
		}else {
			result.setError("请选择板车的运输类型");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getLogisticsCarAddress())) {
			logisticsCarVo.setLogisticsCarAddress(search.getLogisticsCarAddress());
		}
		if(StringUtil.isNotEmpty(search.getLogisticsCarNature())) {
			logisticsCarVo.setLogisticsCarNature(search.getLogisticsCarNature());
		}else {
			logisticsCarVo.setLogisticsCarNature(1);//默认为自有
//			result.setError("请选择板车性质");
//			return result;
		}
		if(StringUtil.isNotEmpty(search.getLogisticsCarVacancy())) {
			logisticsCarVo.setLogisticsCarVacancy(search.getLogisticsCarVacancy());
		}else {
			result.setError("请输入运载数量");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			logisticsCarVo.setRemarks(search.getRemarks());
		}
//		if(StringUtil.isNotEmpty(search.getGpsName())) {
//			logisticsCarVo.setGpsName(search.getGpsName());
//		}
		if(StringUtil.isNotEmpty(search.getGpsPIN())) {
			if(search.getGpsPIN().length() > 25) {
				result.setError("设备号码过长，请保持在25位之内，如有疑问，请联系管理员");
				return result;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("gpsPin", search.getGpsPIN());
			List<LogisticsCarVo> carVos = logisticsCarDao.select(map);
			if(carVos != null && carVos.size() > 0) {
				LogisticsCarVo carVo = carVos.get(0);
				if(StringUtil.isNotEmpty(search.getLogisticsCarId()) && !search.getLogisticsCarId().equals(carVo.getLogisticsCarId())) {
					result.setError("GPS设备唯一编码重复，编码："+search.getGpsPIN()+"设备已安装在板车："+carVo.getLicensePlateNumber()+"上");
					return result;
				}
			}
			logisticsCarVo.setGpsPIN(search.getGpsPIN());
		}
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			logisticsCarVo.setRemarks(search.getRemarks());
		}
		if(StringUtil.isEmpty(search.getLogisticsCarId())) {
			return logisticsCarDao.insertAndResultIT(logisticsCarVo, result);
		}else {
			return logisticsCarDao.updateByIdAndResultIT(logisticsCarVo, result);
		}
	}

	@Override
	public Result logisticsCarIsEnable(LogisticsCarSearch search, Result result, SystemUsers users) throws Exception {
		LogisticsCarVo logisticsCarVo = logisticsCarDao.selectById(search.getLogisticsCarId());
		if(logisticsCarVo == null) {
			result.setError("你选择的板车不存在或者已删除，请重新选择");
			return result;
		}
		if(search.getIsEnable() == null) {
			result.setError("参数错误");
			return result;
		}
		if(search.getIsEnable()) {
			if(logisticsCarVo.getIsEnable()) {
				result.setError("板车本已是启用状态，不需要进行此操作");
				return result;
			}
			logisticsCarVo.setIsEnable(true);
		}else {
			if(!logisticsCarVo.getIsEnable()) {
				result.setError("板车本已是禁用状态，不需要进行此操作");
				return result;
			}
			logisticsCarVo.setIsEnable(false);
		}
		return logisticsCarDao.updateByIdAndResultIT(logisticsCarVo, result);
	}
}
