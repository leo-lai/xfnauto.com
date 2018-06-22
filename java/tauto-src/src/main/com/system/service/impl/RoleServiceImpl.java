package main.com.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.MenuDao;
import main.com.system.dao.dao.RoleDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.Role;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.RoleSearch;
import main.com.system.dao.search.SystemUsersSearch;
import main.com.system.dao.vo.RoleVo;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.system.service.RoleService;
import main.com.utils.StringUtil;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private SystemUsersDao systemUsersDao;
	
	@Autowired
	private MenuDao menuDao;
	
	@Override
	protected BaseDao<Role> getBaseDao() {
		return this.roleDao;
	}

	@Override
	public RoleVo getRoleById(Integer id) {
		return this.roleDao.selectById(id);
	}

//	@Override
//	public Boolean Merge(Role role) {
//		return this.roleDao.saveOrUpdate(role);
//	}

	@Override
	public Boolean deleteById(Integer id) {
		return this.roleDao.deleteById(id);
	}

	@Override
	public Result roleList(RoleSearch search, Result result) throws Exception {
		Map<String,Object> params = getParams(search);
		List<RoleVo> roleVos = roleDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = roleDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(RoleVo roleVo : roleVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("roleName", roleVo.getRoleName());
			map.put("roleId", roleVo.getRoleId());
			map.put("remark", roleVo.getRemark());			
			maps.add(map);
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}
	
	public Map<String,Object> getParams(RoleSearch search){
		Map<String,Object> params = new HashMap<String, Object>();
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	public Result roleEdit(RoleSearch search, Result result) throws Exception {
		Role role = null;
		if(StringUtil.isEmpty(search.getRoleName())) {
			result.setError(ResultCode.CODE_STATE_4005, "请填写角色/岗位名称");
			return result;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleName", search.getRoleName());
		List<RoleVo> roleVos = roleDao.select(params);
		if(search.getRoleId() == null) {
			role = new Role();
			if(roleVos != null && roleVos.size() > 0) {
				result.setError(ResultCode.CODE_STATE_4005, "此名称角色/岗位已存在，请重新输入");
				return result;
			}
		}else {
			role = roleDao.selectById(search.getRoleId());
			if(role == null) {
				result.setError(ResultCode.CODE_STATE_4005, "参数错误，系统不存在此记录");
				return result;
			}
			if(roleVos != null && roleVos.size() > 0 && !role.getRoleId().equals(roleVos.get(0).getRoleId())) {
				result.setError(ResultCode.CODE_STATE_4005, "此名称角色/岗位已存在，请重新输入");
				return result;
			}
		}
		role.setRoleName(search.getRoleName());
		role.setRemark(search.getRemark());
		if(search.getRoleId() == null) {
			return roleDao.insertAndResultIT(role, result);
		}else {
			return roleDao.updateByIdAndResultIT(role, result);
		}
	}

	@Override
	@Transactional
	public Result roleDelete(RoleSearch search, Result result) throws Exception {
		Role role = roleDao.selectById(search.getRoleId());
		if(role == null) {
			result.setError(ResultCode.CODE_STATE_4005, "参数错误，系统不存在此记录");
			return result;
		}
		if(role.getIsDelete()) {
			result.setError(ResultCode.CODE_STATE_200, "操作完成");
			return result;
		}
		//如果角色依然有人使用就不能删除
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("roleId", role.getRoleId());
		params.put("isEnable", true);
		List<SystemUsers> systemUsersList = systemUsersDao.selectJoin(params);
		if(systemUsersList != null) {
			result.setError(ResultCode.CODE_STATE_200, "此角色尚有系统用户在使用，不能进行删除");
			return result;
		}
		role.setIsDelete(true);
		if(roleDao.updateById(role)) {
			//删除掉角色菜单关系
			menuDao.deleteRoleMenuByRoleId(role.getRoleId());
			result.setOK(ResultCode.CODE_STATE_200, "操作已完成");
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "操作失败， ，请联系IT部");
		}
		return result;
	}

	@Override
	@Transactional
	public Result setRoleMenu(RoleSearch search, Result result) throws Exception {
		Boolean flag = false;
		if(search.getRoleId() == null || StringUtil.isEmpty(search.getMenuIds())) {
			result.setError(ResultCode.CODE_STATE_4005, "参数错误");
			return result;
		}
		//先删除之前的角色菜单对应关系
		this.menuDao.deleteRoleMenuByRoleId(search.getRoleId());
		//添加新的对应关系
		String[] menuIds = search.getMenuIds().split(",");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(search.getMenuIds() != null && menuIds.length > 0){
			for(int i=0; i<menuIds.length; i++){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("roleId", search.getRoleId());
				params.put("menuId", menuIds[i]);
				list.add(params);
			}
		}
		if(list.size() > 0) {
			if(menuDao.batchInsertRoleMenu(list)) {
				result.setOK(ResultCode.CODE_STATE_200, "保存成功");
			} else {
				result.setError(ResultCode.CODE_STATE_4005, "系统保存数据错误，请联系IT部");
			}
		}else {
			throw new Exception("给角色分配菜单，菜单参数错误");//抛错
//			result.setError(ResultCode.CODE_STATE_4005, "菜单参数错误，保存失败");
//			return result;
		}
		return result;
	}

	@Override
	public Result roleListList(RoleSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		List<RoleVo> roleVos = roleDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(RoleVo roleVo : roleVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("roleName", roleVo.getRoleName());
			map.put("roleId", roleVo.getRoleId());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}
}
