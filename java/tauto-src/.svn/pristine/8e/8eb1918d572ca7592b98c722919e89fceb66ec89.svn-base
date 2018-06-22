package main.com.weixinApp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.logistics.constant.DrivateState;
import main.com.logistics.dao.dao.LogisticsDriverDao;
import main.com.logistics.dao.po.LogisticsDriver;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.dao.search.LogisticsDriverSearch;
import main.com.logistics.dao.vo.LogisticsConsignmentVo;
import main.com.logistics.dao.vo.LogisticsDriverVo;
import main.com.logistics.dao.vo.LogisticsGoodsCarVo;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.IdcardValidator;
import main.com.utils.MD5Encoder;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.weixinApp.service.EmployeeDriverService;

@Service
public class EmployeeDriverServiceImpl extends BaseServiceImpl<LogisticsDriver> implements EmployeeDriverService{

	@Autowired
	LogisticsDriverDao logisticsDriverDao;
	
	@Override
	protected BaseDao<LogisticsDriver> getBaseDao() {
		return logisticsDriverDao;
	}

	@Override
	public Result driverList(LogisticsDriverSearch search, Result result, SystemUsers users) throws Exception {
		Map<String,Object> params = getParams(search,users);
		List<LogisticsDriverVo> driverVos = logisticsDriverDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = logisticsDriverDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(LogisticsDriverVo driverVo : driverVos) {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("cardNo", driverVo.getCardNo());
			map.put("driverId", driverVo.getDriverId());
			map.put("realName", driverVo.getRealName());
			map.put("phoneNumber", driverVo.getPhoneNumber());
			map.put("status", driverVo.getStatus());
			map.put("idcardPicOn", driverVo.getIdcardPicOn());
			map.put("idcardPicOff", driverVo.getIdcardPicOff());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}

	public Map<String,Object> getParams(LogisticsDriverSearch search,SystemUsers systemUsers){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getRealName())) {
			params.put("realName", search.getRealName());
		}
		if(StringUtil.isNotEmpty(search.getPhoneNumber())) {
			params.put("phoneNumber", search.getPhoneNumber());
		}
		if(StringUtil.isNotEmpty(search.getDriverSearch())) {
			params.put("driverSearch", search.getDriverSearch());
		}
		if(StringUtil.isNotEmpty(search.getKeywords())) {
			params.put("driverSearch", search.getKeywords());
		}

//		if(StringUtil.isNotEmpty(search.getStartDate())) {
//			params.put("startDate", search.getStartDate());
//		}
//		if(StringUtil.isNotEmpty(search.getEndDate())) {
//			params.put("endDate", search.getEndDate());
//		}
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
	public Result driverEdit(LogisticsDriverSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4005, "手机号不能为空");
			return result;
		}else if(!StringUtil.isNumeric(search.getPhoneNumber()) || search.getPhoneNumber().length() > 11){
			result.setError(ResultCode.CODE_STATE_4005, "手机号格式错误，请输入正确号码");
			return result;
		}
		LogisticsDriverVo logisticsDriverVo = null;
		if(StringUtil.isEmpty(search.getDriverId())) {
			logisticsDriverVo = new LogisticsDriverVo();
			logisticsDriverVo = new LogisticsDriverVo();
			logisticsDriverVo.setOverEnable(GeneralConstant.SystemBoolean.TRUE);
			logisticsDriverVo.setOrgId(users.getOrgId());
			logisticsDriverVo.setOrgCode(users.getOrgCode());
			logisticsDriverVo.setOrgName(users.getOrgName());
			logisticsDriverVo.setPassword(MD5Encoder.encodeByMD5(search.getPhoneNumber()));
		}else {
			logisticsDriverVo = logisticsDriverDao.selectById(search.getDriverId());
		}
		//查询当前系统此号码是否存在
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phoneNumber", search.getPhoneNumber());
		List<LogisticsDriverVo> logisticsDriverVos = this.logisticsDriverDao.select(params);
		if(logisticsDriverVos !=null && logisticsDriverVos.size() >0 && (logisticsDriverVo.getDriverId() == null || !logisticsDriverVo.getDriverId().equals(logisticsDriverVos.get(0).getDriverId()))) {
			result.setError(ResultCode.CODE_STATE_4005, "此电话号已存在，请输入新号码");
			return result;
		}
		logisticsDriverVo.setPhoneNumber(search.getPhoneNumber());
		if(StringUtil.isEmpty(search.getRealName())) {
			result.setError(ResultCode.CODE_STATE_4005, "姓名不能为空");
			return result;
		}
		logisticsDriverVo.setRealName(search.getRealName());
		if(StringUtil.isEmpty(search.getCardNo())) {
			result.setError(ResultCode.CODE_STATE_4005, "身份证号不能为空");
			return result;
		}else {
			IdcardValidator iv = new IdcardValidator();  
	       if(!iv.isValidatedAllIdcard(search.getCardNo())) {
	    	   result.setError(ResultCode.CODE_STATE_4005, "身份证号格式错误");
				return result;
	       }
		}
		logisticsDriverVo.setCardNo(search.getCardNo());
		if(StringUtil.isEmpty(search.getIdcardPicOn())) {
			result.setError(ResultCode.CODE_STATE_4005, "请上传身份证正面");
			return result;
		}
		logisticsDriverVo.setIdcardPicOn(search.getIdcardPicOn());
		if(StringUtil.isEmpty(search.getIdcardPicOff())) {
			result.setError(ResultCode.CODE_STATE_4005, "请上传身份证反面");
			return result;
		}
		logisticsDriverVo.setIdcardPicOff(search.getIdcardPicOff());
		if(StringUtil.isEmpty(logisticsDriverVo.getDriverId())) {
			if(logisticsDriverDao.insert(logisticsDriverVo)) {
				result.setOK(ResultCode.CODE_STATE_200, "保存成功", logisticsDriverVo);
			}else {
				result.setError("更新数据错误，请与管理员联系");
			}
		}else {
			if(logisticsDriverDao.updateById(logisticsDriverVo)) {
				result.setOK(ResultCode.CODE_STATE_200, "保存成功", logisticsDriverVo);
			}else {
				result.setError("更新数据错误，请与管理员联系");
			}
		}
		return result;
	}

	@Override
	public Result driverInfo(LogisticsDriverSearch search, Result result, SystemUsers users) throws Exception {
		LogisticsDriverVo driverVo = logisticsDriverDao.selectById(search.getDriverId());
		if(driverVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的司机不存在或者已删除");
			return result;
		}
		Map<String,Object> map = new HashMap<String, Object>();	
		map.put("cardNo", driverVo.getCardNo());
		map.put("driverId", driverVo.getDriverId());
		map.put("realName", driverVo.getRealName());
		map.put("phoneNumber", driverVo.getPhoneNumber());
		map.put("status", driverVo.getStatus());
		map.put("idcardPicOn", driverVo.getIdcardPicOn());
		map.put("idcardPicOff", driverVo.getIdcardPicOff());
		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}

	@Override
	public Result driverListList(LogisticsDriverSearch search, Result result, SystemUsers users) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getRealName())) {
			params.put("driverSearch", search.getDriverSearch());
		}
		List<LogisticsDriverVo> driverVos = logisticsDriverDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(LogisticsDriverVo driverVo : driverVos) {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("cardNo", driverVo.getCardNo());
			map.put("driverId", driverVo.getDriverId());
			map.put("realName", driverVo.getRealName());
			map.put("phoneNumber", driverVo.getPhoneNumber());
			map.put("status", driverVo.getStatus());
			map.put("statusName", DrivateState.getMsgByCode(driverVo.getStatus()));
			map.put("idcardPicOn", driverVo.getIdcardPicOn());
			map.put("idcardPicOff", driverVo.getIdcardPicOff());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result driverDisable(LogisticsDriverSearch search, Result result, SystemUsers users) throws Exception {
		LogisticsDriverVo driverVo = logisticsDriverDao.selectById(search.getDriverId());
		if(driverVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的司机不存在或者已删除");
			return result;
		}
		if(!driverVo.getOverEnable()) {
			result.setError(ResultCode.CODE_STATE_4005, "该司机已被禁用，无需重复操作");
			return result;
		}
		driverVo.setOverEnable(false);
		if(logisticsDriverDao.updateById(driverVo)) {
			result.setOK(ResultCode.CODE_STATE_200, "禁用成功");
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "禁用失败，请联系管理员");
		}
		return result;
	}
}
