package main.com.system.service.impl;

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
import main.com.system.dao.dao.SystemGroupingDao;
import main.com.system.dao.dao.SystemUserGroupingDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.SystemGrouping;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemGroupingSearch;
import main.com.system.dao.search.SystemUserGroupingSearch;
import main.com.system.dao.vo.SystemGroupingVo;
import main.com.system.dao.vo.SystemUserGroupingVo;
import main.com.system.service.SystemGroupingService;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class SystemGroupingServiceImpl extends BaseServiceImpl<SystemGrouping> implements SystemGroupingService{

	@Autowired
	SystemGroupingDao systemGroupingDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	SystemUserGroupingDao systemUserGroupingDao;
	
	@Override
	protected BaseDao<SystemGrouping> getBaseDao() {
		return systemGroupingDao;
	}

	@Override
	public Result systemGroupingList(SystemGroupingSearch search, Result result, SystemUsers users)
			throws Exception {
		Map<String,Object> params = getParams(search,users);
		List<SystemGroupingVo> systemGroupingVos = systemGroupingDao.select(params);
		Long total = systemGroupingDao.getRowCount(params);
		int rows = search.getRows();
		Map<String,Object> allMap = new HashMap<String, Object>();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(SystemGroupingVo systemGroupingVo : systemGroupingVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("groupingId", systemGroupingVo.getGroupingId());
			map.put("groupingName", systemGroupingVo.getGroupingName());
			map.put("remarks", systemGroupingVo.getRemarks());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}

	public Map<String,Object> getParams(SystemGroupingSearch search,SystemUsers users){
		Map<String,Object> params = new HashMap<String, Object>();		
		if(StringUtil.isNotEmpty(search.getGroupingName())) {
			params.put("groupingName", search.getGroupingName());
		}
		params.put("orgCode", users.getOrgCode());
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	public Result systemGroupingEdit(SystemGroupingSearch search, Result result, SystemUsers users) throws Exception {
		SystemGrouping systemGrouping = null;
		if(StringUtil.isNotEmpty(search.getGroupingId())) {
			systemGrouping = systemGroupingDao.selectById(search.getGroupingId());
			if(systemGrouping == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你选择的分组不存在或者已被删除，请重新选择");
				return result;
			}
		}else {
			systemGrouping = new SystemGrouping();
			systemGrouping.setOverDelete(false);
		}
		if(StringUtil.isNotEmpty(search.getGroupingName())) {
			systemGrouping.setGroupingName(search.getGroupingName());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入组别名称");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			systemGrouping.setRemarks(search.getRemarks());
		}
		systemGrouping.setOrgId(users.getOrgId());
		if(StringUtil.isNotEmpty(search.getGroupingId())) {
			return systemGroupingDao.updateByIdAndResultIT(systemGrouping, result);
		}else {
			return systemGroupingDao.insertAndResultIT(systemGrouping, result);
		}
	}

	@Override
	public Result systemUserGroupingEdit(SystemUserGroupingSearch search, Result result, SystemUsers users)
			throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		SystemGrouping systemGrouping = null;
		SystemUserGroupingVo systemUserGroupingVo = null;
		if(StringUtil.isNotEmpty(search.getUserGroupingId())) {
			systemUserGroupingVo = systemUserGroupingDao.selectById(search.getUserGroupingId());
			if(systemUserGroupingVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你选择的组别-成员数据不存在或已被删除，请重新选择");
				return result;
			}
			params.put("userId", search.getUserId());
			params.put("groupingId", search.getGroupingId());	
			SystemUserGroupingVo systemUserGrouping = systemUserGroupingDao.selectByBothId(params);
			if(systemUserGrouping != null && !systemUserGrouping.getUserGroupingId().equals(search.getUserGroupingId())) {
				result.setError(ResultCode.CODE_STATE_4005, "组内已存在此人员，无需重复分配");
				return result;
			}
		}else {
			systemUserGroupingVo = new SystemUserGroupingVo();
			params.put("userId", search.getUserId());
			params.put("groupingId", search.getGroupingId());	
			SystemUserGroupingVo systemUserGrouping = systemUserGroupingDao.selectByBothId(params);
			if(systemUserGrouping != null) {
				result.setError(ResultCode.CODE_STATE_4005, "组内已存在此人员，无需重复分配");
				return result;
			}
			if(StringUtil.isNotEmpty(search.getGroupingId())) {
				systemGrouping = systemGroupingDao.selectById(search.getGroupingId());
				if(systemGrouping == null) {
					result.setError(ResultCode.CODE_STATE_4005, "你选择的分组不存在或者已被删除，请重新选择");
					return result;
				}
				systemUserGroupingVo.setGroupingId(systemGrouping.getGroupingId());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "组别选择错误，请重新选择");
				return result;
			}
			SystemUsers systemUsers = null;
			if(StringUtil.isNotEmpty(search.getUserId())) {
				systemUsers = systemUsersDao.selectById(search.getUserId());
				if(systemUsers == null) {
					result.setError(ResultCode.CODE_STATE_4005, "你选择的人员不存在或者已被删除，请重新选择");
					return result;
				}
				systemUserGroupingVo.setUserId(systemUsers.getUsersId());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "人员选择错误，请重新选择");
				return result;
			}
		}
		if(search.getOverManage() == null) {
			search.setOverManage(false);
		}
		systemUserGroupingVo.setOverManage(search.getOverManage());
		if(StringUtil.isNotEmpty(search.getUserGroupingId())) {
			return systemUserGroupingDao.updateByIdAndResultIT(systemUserGroupingVo, result);
		}else {
			return systemUserGroupingDao.insertAndResultIT(systemUserGroupingVo, result);
		}
	}

	@Override
	public Result systemUserGroupingDalete(SystemUserGroupingSearch search, Result result, SystemUsers users)
			throws Exception {
//		SystemUserGroupingVo systemUserGroupingVo = null;
//		if(StringUtil.isNotEmpty(search.getUserGroupingId())) {
//			systemUserGroupingVo = systemUserGroupingDao.selectById(search.getUserGroupingId());
//			if(systemUserGroupingVo == null) {
//				result.setError(ResultCode.CODE_STATE_4005, "你选择的组别-成员数据不存在或已被删除，请重新选择");
//				return result;
//			}
//		}
		if(systemUserGroupingDao.deleteById(search.getUserGroupingId())) {
			result.setOK(ResultCode.CODE_STATE_200, "删除成功");
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "删除失败");
		}
		return result; 
	}

	@Override
	public Result systemGroupingDalete(SystemGroupingSearch search, Result result, SystemUsers users) throws Exception {
		SystemGrouping systemGrouping = null;
		if(StringUtil.isNotEmpty(search.getGroupingId())) {
			systemGrouping = systemGroupingDao.selectById(search.getGroupingId());
			if(systemGrouping == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你选择的分组不存在或者已被删除，请重新选择");
				return result;
			}
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择组别");
			return result;
		}
		systemGrouping.setOverDelete(true);
		return systemGroupingDao.updateByIdAndResultIT(systemGrouping, result);
	}

	@Override
	public Result systemUserGroupingList(SystemGroupingSearch search, Result result, SystemUsers users)
			throws Exception {
		SystemGroupingVo systemGrouping = null;
		if(StringUtil.isNotEmpty(search.getGroupingId())) {
			systemGrouping = systemGroupingDao.selectByIdJoin(search.getGroupingId());
			if(systemGrouping == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你选择的分组不存在或者已被删除，请重新选择");
				return result;
			}
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择组别");
			return result;
		}
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		if(systemGrouping.getSystemUserGroupingVos() == null) {
			result.setOK(ResultCode.CODE_STATE_200, "", maps);
			return result;
		}
		for(SystemUserGroupingVo userGroupingVo : systemGrouping.getSystemUserGroupingVos()) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("groupingId", userGroupingVo.getGroupingId());
			map.put("userGroupingId", userGroupingVo.getUserGroupingId());
			map.put("userId", userGroupingVo.getUserId());
			map.put("userName", userGroupingVo.getUsersVo().getRealName());
			if(userGroupingVo.getOverManage()) {
				map.put("overManage", 1);
			}else {
				map.put("overManage", 0);
			}
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}
}
