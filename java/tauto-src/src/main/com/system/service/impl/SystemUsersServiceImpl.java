package main.com.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import main.com.frame.cache.CacheTimerHandler;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.logistics.dao.dao.LogisticsDriverDao;
import main.com.system.dao.dao.MenuDao;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.RoleDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.Role;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemUsersSearch;
import main.com.system.dao.vo.MenuVo;
import main.com.system.dao.vo.OrganizationVo;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.system.service.SystemUsersService;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.MD5Encoder;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.utils.UUIDUtils;
import main.com.utils.Value;
import main.com.utils.rlycode.PhoneUtil;
import main.com.weixinHtml.dao.dao.ShopUsersDao;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.vo.ShopUsersVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemUsersServiceImpl extends BaseServiceImpl
<SystemUsers>implements SystemUsersService{
	
	@Autowired
	private SystemUsersDao systemUsersDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private OrganizationDao organizationDao;
	
	@Autowired
	private ShopUsersDao shopUsersDao;

	@Override
	protected BaseDao<SystemUsers> getBaseDao() {
		return systemUsersDao;
	}

	@Override
	public Result login(SystemUsersSearch search,Result result,HttpSession session) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getUserName())) {
			params.put("phoneNumber", search.getUserName());
		}
		if(StringUtil.isNotEmpty(search.getPhoneNumber())) {
			params.put("phoneNumber", search.getPhoneNumber());
		}
		params.put("isStatus", true);
		params.put("isEnable", true);
		List<SystemUsers> systemUsersList = systemUsersDao.select(params);
		if(systemUsersList == null || systemUsersList.size() == 0){
			result.setError(ResultCode.CODE_STATE_4003, "用户不存在");
			return result;
		}
		SystemUsers systemUsers = systemUsersList.get(0);
//		System.out.println(MD5Encoder.encodeByMD5(search.getPassword()));
		if(!systemUsers.getPassword().equals(MD5Encoder.encodeByMD5(search.getPassword()))){
			result.setError(ResultCode.CODE_STATE_4003, "密码错误");
			return result;
		}
		//检查所属组织是否被禁用
		if(systemUsers.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4003, "所属组织不明确，系统拒绝登陆，请联系管理员把你的数据补充完整");
			return result;
		}
		OrganizationVo organizationVo = organizationDao.selectById(systemUsers.getOrgId());
		if(organizationVo == null) {
			result.setError(ResultCode.CODE_STATE_4003, "所属组织不明确，系统拒绝登陆，请联系管理员把你的数据补充完整");
			return result;
		}
		if(GeneralConstant.Org.status_off.equals(organizationVo.getStatus())) {
			result.setError(ResultCode.CODE_STATE_4003, "你所属的组织"+organizationVo.getShortName()+" 已被禁用");
			return result;
		}
		if(GeneralConstant.Org.audited.equals(organizationVo.getStatus())) {
			result.setError(ResultCode.CODE_STATE_4003, "你的商务账户申请仍在审核中，请耐心等待");
			return result;
		}
		systemUsers.setSessionId(UUIDUtils.random());
		systemUsers.setLoginTime(new Date());
//		session.setAttribute(systemUsers.getSessionId(), systemUsers);
		//查询用户角色给出用户的权限和菜单（三级菜单，一级 二级 以及三级按钮控制）
		search.setUserId(systemUsers.getUsersId());
		Map<String,Object> map = getUserRoleAndMune(search);
		if(systemUsersDao.updateById(systemUsers)){
//			params.clear();
			map.put("orgLevel", organizationVo.getOrgLevel());
			map.put("orgType", organizationVo.getOrgType());
			map.put("userName", systemUsers.getUserName());
			map.put("realName", systemUsers.getRealName());
			map.put("sessionId", systemUsers.getSessionId());
			result.setOK(ResultCode.CODE_STATE_200, "登录成功", TakeCareMapDate.cutNullMap(map));
			return result;
		}else{
			result.setError(ResultCode.CODE_STATE_4008, "系统错误，请联系IT部门。");
			return result;
		}
	}

	@Override
	public Boolean loginOut(SystemUsersSearch search,Result result)
			throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
//		params.put("sessionId", search.getUserName());
//		List<SystemUsers> systemUsersList = systemUsersDao.select(params);
//		if(systemUsersList == null || systemUsersList.size() == 0){
//			result.setError(ResultCode.CODE_STATE_4003, "用户信息错误");
//			return false;
//		}
		SystemUsers systemUsers = systemUsersDao.selectByCode(search.getSessionId());
		if(systemUsers == null){
			result.setError(ResultCode.CODE_STATE_4003, "用户信息错误");
			return false;
		}
		systemUsers.setSessionId("");
//		systemUsers.setLoginTime("");
		if(systemUsersDao.updateById(systemUsers)){
			params.clear();
			result.setOK(ResultCode.CODE_STATE_200, "登出成功", params);
			return true;
		}else{
			result.setError(ResultCode.CODE_STATE_4008, "系统错误，请联系IT部门。");
			return false;
		}
	}

	@Override
	public Result changePassword(SystemUsersSearch search, Result result,HttpSession session)
			throws Exception {
		SystemUsers systemUsers = systemUsersDao.selectByCode(search.getSessionId());
		if(systemUsers == null){
			result.setError(ResultCode.CODE_STATE_4003, "用户信息错误");
			return result;
		}
		if(!systemUsers.getPassword().equals(MD5Encoder.encodeByMD5(search.getPasswordOld()))){
			result.setError(ResultCode.CODE_STATE_4003, "用户旧密码错误");
			return result;
		}
		systemUsers.setPassword(MD5Encoder.encodeByMD5(search.getPassword()));
		systemUsers.setSessionId(UUIDUtils.random());
		systemUsers.setLoginTime(new Date());
		if(systemUsersDao.updateById(systemUsers)){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", systemUsers.getUserName());
			params.put("realName", systemUsers.getRealName());
			params.put("sessionId", systemUsers.getSessionId());
			result.setOK(ResultCode.CODE_STATE_200, "密码修改成功", params);
			return result;
		}else{
			result.setError(ResultCode.CODE_STATE_4008, "系统错误，请联系IT部门。");
			return result;
		}
	}

	@Override
	@Transactional
	public Result addUser(SystemUsersSearch search, Result result) throws Exception {
		SystemUsers systemUsers = null;
		if(StringUtil.isEmpty(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4005, "手机号不能为空");
			return result;
		}else if(!StringUtil.isNumeric(search.getPhoneNumber()) || search.getPhoneNumber().length() > 11){
			result.setError(ResultCode.CODE_STATE_4005, "手机号格式错误，请输入正确号码");
			return result;
		}
		//查询当前系统此号码是否存在
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phoneNumber", search.getPhoneNumber());
		List<SystemUsers> users = this.systemUsersDao.select(params);
		if(search.getUserId() == null) {
			systemUsers = new SystemUsers();
			if(users != null && users.size() > 0) {
				result.setError(ResultCode.CODE_STATE_4005, "此电话号已存在，请输入新号码");
				return result;
			}
			systemUsers.setIsEnable(true);
			systemUsers.setStatus(1);
			systemUsers.setPassword(MD5Encoder.encodeByMD5(search.getPhoneNumber()));//后续将会使用短信发送密码
		}else {
			systemUsers = systemUsersDao.selectById(search.getUserId());			
			if(users !=null && users.size() >0 && !systemUsers.getUsersId().equals(users.get(0).getUsersId())) {
				result.setError(ResultCode.CODE_STATE_4005, "此电话号已存在，请输入新号码");
				return result;
			}
		}
		if(search.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择所属组织");
			return result;
		}
		//验证组织
		Organization organization = organizationDao.selectById(search.getOrgId());
		if(organization == null) {
			result.setError(ResultCode.CODE_STATE_4005, "选择所属组织不存在，请重新选择");
			return result;
		}
		systemUsers.setOrgId(organization.getOrgId());
		systemUsers.setOrgName(organization.getShortName());
		systemUsers.setOrgCode(organization.getOrgCode());
		if(search.getRoleId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择角色岗位");
			return result;
		}
		//验证角色岗位
		Role role = roleDao.selectById(search.getRoleId());
		if(role == null) {
			result.setError(ResultCode.CODE_STATE_4005, "选择角色岗位不存在，请重新选择");
			return result;
		}
		if(StringUtil.isEmpty(search.getRealName())) {
			result.setError(ResultCode.CODE_STATE_4005, "请输入真实姓名");
			return result;
		}
		if(search.getBirthday() != null) {
			systemUsers.setBirthday(DateUtil.format(search.getBirthday(),"yyyy-MM-dd"));
		}
		if(search.getCardNo() != null) {
			systemUsers.setCardNo(search.getCardNo());
		}
		if(StringUtil.isNotEmpty(search.getEntryTime())) {
			systemUsers.setEntryTime(DateUtil.format(search.getEntryTime(),"yyyy-MM-dd"));
		} 
		if(search.getBasePay() != null) {
			systemUsers.setBasePay(search.getBasePay());
		} 
		if(search.getAgentGender() != null) {
			systemUsers.setAgentGender(search.getAgentGender());
		} 
		if(StringUtil.isEmpty(search.getHeadPortrait())) {
			systemUsers.setHeadPortrait(search.getHeadPortrait());
		} 
		systemUsers.setRealName(search.getRealName());
		systemUsers.setPhoneNumber(search.getPhoneNumber());
		systemUsers.setWeixinQrImage(search.getWeixinQrImage());
		systemUsers.setCreateTime(new Date());
		
		//添加一个商城的用户，使得添加一个系统，两边都可以用
		ShopUsers shopUser = null;
		params.put("phoneNumber", search.getPhoneNumber());
		List<ShopUsersVo> usersVos = shopUsersDao.select(params);
		if (usersVos == null || usersVos.size() <= 0) {
			shopUser = new ShopUsers();
			shopUser.setCreateTime(new Date());
			shopUser.setPhoneNumber(search.getPhoneNumber());
			shopUser.setPassword(MD5Encoder.encodeByMD5(search.getPhoneNumber()));
			shopUser.setUserType(2);//设置为B端用户
			shopUser.setRealName(search.getRealName());
			shopUser.setUserCode(shopUsersDao.getUserCode());
			shopUser.setOrgId(organization.getOrgId());
			shopUser.setOrgName(organization.getShortName());
			shopUser.setHeadPortrait(search.getHeadPortrait());
			shopUser.setAgentGender(search.getAgentGender());
			shopUser.setCardNo(search.getCardNo());
			shopUser.setStatus(1);
			shopUser.setOverPush(true);
			shopUser.setOverEnable(true);
		}
		
		try {
			if(search.getUserId() == null) {
				if(systemUsersDao.insert(systemUsers)) {
					params.clear();
					params.put("userId", systemUsers.getUsersId());
					params.put("roleId", role.getRoleId());
					systemUsersDao.insertRoleUser(params);
					result.setOK(ResultCode.CODE_STATE_200, "保存成功");
				}else {
					result.setError(ResultCode.CODE_STATE_4005, "系统保存数据失败，请联系IT部");
					throw new Exception("系统用户添加出错，手动回滚");
				}
			}else {
				if(systemUsersDao.updateById(systemUsers)) {
					params.clear();
					params.put("userId", systemUsers.getUsersId());
					params.put("roleId", role.getRoleId());
					systemUsersDao.updateRoleUserById(params);
					result.setOK(ResultCode.CODE_STATE_200, "保存成功");
				}else {
					result.setError(ResultCode.CODE_STATE_4005, "系统保存数据失败，请联系IT部");
					throw new Exception("系统用户编辑出错，手动回滚");
				}
			}
			if(shopUser != null) {
				if(!shopUsersDao.insert(shopUser)) {
					result.setError(ResultCode.CODE_STATE_4005, "系统保存数据失败，请联系IT部");
					throw new Exception("系统用户编辑，添加商城B端用户时出错，手动回滚");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("用户编辑出错，手动回滚");
		}
		return result;
	}
	
	@Override
	public Map<String, Object> getUserRoleAndMune(SystemUsersSearch search) throws Exception {
		//查询用户角色
		if(search.getUserId() == null) {
			return new HashMap<String,Object>();
		}
		Role role = roleDao.selectByUserId(search.getUserId());
		if(role == null) {
			return new HashMap<String,Object>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", role.getRoleName());
		map.put("roleId", role.getRoleId());
		//查询用户菜单
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", search.getUserId());
//		params.put("levelNum", 0);
		List<MenuVo> menus = menuDao.getUserRoleMenu(params);//查出当前角色的所有菜单
		if(menus == null || menus.size()<=0) {
			map.put("menus", new ArrayList<MenuVo>());
		}
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		maps = getSyncGridTreeMap(menus, 0);
		map.put("menus", maps);
		return map;
	}
	
	/**
	 * 返回类树形结构
	 * @param list
	 * @param parentId
	 * @return
	 */
	public List<MenuVo> getSyncGridTree(List<MenuVo> list, Integer parentId) {
		List<MenuVo> menuTreeGrid = new ArrayList<MenuVo>();//返回的树形结构的菜单列表
		for(MenuVo menuVo: list){
			int id = menuVo.getMenuId();
			int pid = menuVo.getParentId();
			if(pid == parentId){
				List<MenuVo> children = getSyncGridTree(list, id);
				menuVo.setChildren(children);
				menuTreeGrid.add(menuVo);
			}
		}
		return menuTreeGrid;
	}
	
	/**
	 * 返回Map树形结构
	 * @param list
	 * @param parentId
	 * @return
	 */
	public List<Map<String, Object>> getSyncGridTreeMap(List<MenuVo> list, Integer parentId) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for(MenuVo menuVo: list){
			int id = menuVo.getMenuId();
			int pid = menuVo.getParentId();
			if(pid == parentId){
				List<Map<String, Object>> children = getSyncGridTreeMap(list, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("menuId", menuVo.getMenuId());
				params.put("menuName", menuVo.getMenuName());
				params.put("children", children);
				maps.add(params);
			}
		}
		return maps;
	}

	@Override
	public Result userList(SystemUsersSearch search, Result result) throws Exception {
		Map<String,Object> params = getParams(search);
		List<SystemUsersVo> systemUsersVos = systemUsersDao.selectJoin(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = systemUsersDao.selectCountJoin(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(SystemUsersVo usersVo : systemUsersVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("phoneNumber", usersVo.getPhoneNumber());
			map.put("userId", usersVo.getUsersId());
			map.put("roleId", usersVo.getRoleId());
			map.put("realName", usersVo.getRealName());
			map.put("orgName", usersVo.getOrgName());
			map.put("orgId", usersVo.getOrgId());
			map.put("roleName", usersVo.getRoleName());
			map.put("agentGender", usersVo.getAgentGender());
			map.put("basePay", usersVo.getBasePay());
			map.put("birthday", usersVo.getBirthday()!=null?main.com.utils.DateUtil.format(usersVo.getBirthday(),true):"");
			map.put("cardNo", usersVo.getCardNo());
			map.put("entryTime", usersVo.getEntryTime()!=null?main.com.utils.DateUtil.format(usersVo.getEntryTime(),true):"");
			if(usersVo.getIsEnable()) {
				map.put("isEnable", 1);//启用
			}else {
				map.put("isEnable", 0);//禁用
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
	
	public Map<String,Object> getParams(SystemUsersSearch search){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getRealName())){
			params.put("realName", search.getRealName());
		}
		if(StringUtil.isNotEmpty(search.getOrgName())){
			params.put("orgName", search.getOrgName());
		}
		params.put("orgCodeLevel", search.getOrgCode());
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	public Result userIsEnable(SystemUsersSearch search, Result result,Integer userId) throws Exception {
		if(search.getUserId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择有效数据进行操作");
			return result;
		}
		if(userId.equals(search.getUserId())) {
			result.setError(ResultCode.CODE_STATE_4005, "抱歉，不能对自身进行此操作");
			return result;
		}
		SystemUsers users = systemUsersDao.selectById(search.getUserId());
		if(users == null) {
			result.setError(ResultCode.CODE_STATE_4005, "系统不存在用户ID为"+search.getUserId()+"的数据，请核对数据后重新操作");
			return result;
		}
		if(search.getIsEnable() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "参数错误，此操作参数不能为空");
			return result;
		}
		if(search.getIsEnable()) {//启用
			if(users.getIsEnable()) {
				result.setError(ResultCode.CODE_STATE_4005, users.getRealName()+"已是启用状态，不需要进行启用操作");
				return result;
			}
			users.setIsEnable(search.getIsEnable());
		}else {
			if(!users.getIsEnable()) {
				result.setError(ResultCode.CODE_STATE_4005, users.getRealName()+"已是禁用状态，不需要进行启用操作");
				return result;
			}
			users.setIsEnable(search.getIsEnable());
		}
		return systemUsersDao.updateByIdAndResultIT(users, result);
	}

	@Override
	public Result salesList(SystemUsersSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgCodeLevel", search.getOrgCode());
		params.put("isEnable", true);
		if(StringUtil.isNotEmpty(search.getRealName())) {
			params.put("realName", search.getRealName());			
		}
		List<SystemUsersVo> systemUsersVos = systemUsersDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(SystemUsersVo usersVo : systemUsersVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("systemUserName", usersVo.getRealName());
			map.put("systemUserId", usersVo.getUsersId());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}

	@Override
	public Result changeOtherPassword(SystemUsersSearch search, Result result, HttpSession session) throws Exception {
		SystemUsers users = systemUsersDao.selectById(search.getUserId());
		if(users == null) {
			result.setError(ResultCode.CODE_STATE_4005, "系统不存在用户ID为"+search.getUserId()+"的数据，请核对数据后重新操作");
			return result;
		}
		if(StringUtil.isEmpty(users.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4005, "用户"+users.getRealName()+"的电话号不存在，请完善数据后重新操作");
			return result;
		}
		users.setPassword(MD5Encoder.encodeByMD5(users.getPhoneNumber()));
		return systemUsersDao.updateByIdAndResultIT(users, result);
	}

	@Override
	public Result orgOneSelfList(SystemUsersSearch search, Result result, SystemUsers systemUsers) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgId", systemUsers.getOrgId());
		params.put("isEnable", true);
		if(StringUtil.isNotEmpty(search.getRealName())) {
			params.put("realName", search.getRealName());			
		}
		List<SystemUsersVo> systemUsersVos = systemUsersDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(SystemUsersVo usersVo : systemUsersVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("systemUserName", usersVo.getRealName());
			map.put("systemUserId", usersVo.getUsersId());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}
}
