package main.com.weixinApp.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.car.dao.dao.CarBrandDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.po.Cars;
import main.com.car.dao.search.CarBrandSearch;
import main.com.car.dao.vo.CarBrandVo;
import main.com.car.dao.vo.CarsVo;
import main.com.customer.dao.dao.CustomerCustomerOrgDao;
import main.com.customer.dao.po.CustomerCustomerOrg;
import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.service.CustomerCustomerOrgService;
import main.com.exception.BusinessException;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.dao.StockStorageDao;
import main.com.stock.dao.po.StockStorage;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.vo.StockCarVo;
import main.com.stock.dao.vo.StockStorageVo;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemGroupingDao;
import main.com.system.dao.dao.SystemUserGroupingDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemGrouping;
import main.com.system.dao.po.SystemUserGrouping;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemUsersSearch;
import main.com.system.dao.vo.OrganizationVo;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.system.service.SystemUsersService;
import main.com.utils.Base64Util;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.GeneralConstant.CustomerOrderState;
import main.com.utils.MD5Encoder;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.utils.UUIDUtils;
import main.com.utils.UserInfoUtil;
import main.com.weixin.dao.dao.EmployeeUserDao;
import main.com.weixinApp.dao.search.SalesPerformanceSearch;
import main.com.weixinApp.dao.vo.SalesPerformanceVO;
import main.com.weixinApp.service.AppCarsService;
import main.com.weixinApp.service.EmployeeUserService;

@Service
public class EmployeeUserServiceImpl extends BaseServiceImpl<SystemUsers>implements EmployeeUserService{

	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	StockStorageDao stockStorageDao;
	
	@Autowired
	StockCarDao stockCarDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CarBrandDao carBrandDao;
	
	@Autowired
	SystemUserGroupingDao systemUserGroupingDao;
	
	@Autowired
	private CustomerCustomerOrgDao customerCustomerOrgDao;

	
	@Autowired
	private EmployeeUserDao employeeUserDao;
	
	@Autowired
	SystemGroupingDao systemGroupingDao;
	
	@Override
	protected BaseDao<SystemUsers> getBaseDao() {
		return systemUsersDao;
	}

	@Override
	public Result login(SystemUsersSearch search, Result result, HttpSession session) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getPhoneNumber())) {
			params.put("phoneNumber", search.getPhoneNumber());
		}else {
			result.setError(ResultCode.CODE_STATE_4003, "请输入电话号码");
			return result;
		}
		params.put("isStatus", true);
		params.put("isEnable", true);
		List<SystemUsersVo> systemUsersList = systemUsersDao.selectJoin(params);
		if(systemUsersList == null || systemUsersList.size() == 0){
			result.setError(ResultCode.CODE_STATE_4003, "用户不存在");
			return result;
		}
		SystemUsersVo systemUsers = systemUsersList.get(0);
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
		systemUsers.setNikeName(Base64Util.encodeData(search.getNikeName()));
		systemUsers.setHeadPortrait(search.getHeadPortrait());
		systemUsers.setLoginTime(new Date());
		
		JSONObject jsonObject = UserInfoUtil.getUserInfo(search.getCode(), search.getEncryptedData(), search.getIv());
		if(jsonObject != null) {
			if(jsonObject.has("openId")){
				systemUsers.setOpenId(String.valueOf(jsonObject.get("openId")));
			}
			if(jsonObject.has("gender")) {
				systemUsers.setAgentGender(Integer.valueOf(String.valueOf(jsonObject.get("gender"))));
			}
		}
//		session.setAttribute(systemUsers.getSessionId(), systemUsers);
		//查询用户角色给出用户的权限和菜单（三级菜单，一级 二级 以及三级按钮控制）
		search.setUserId(systemUsers.getUsersId());
//		Map<String,Object> map = getUserRoleAndMune(search);
//		//查询用户的数据权限
//		Map<String,Object> groupingMap = new HashMap<String, Object>();
//		groupingMap.put("userId", systemUsers.getUsersId());
//		List<SystemUserGrouping> groupings = systemUserGroupingDao.select(groupingMap);
		Map<String,Object> map = new HashMap<String, Object>();
		if(systemUsersDao.updateById(systemUsers)){
			map.put("orgLevel", organizationVo.getOrgLevel());
			map.put("userName", systemUsers.getUserName());
			map.put("phoneNumber", systemUsers.getPhoneNumber());
			map.put("realName", systemUsers.getRealName());
			map.put("sessionId", systemUsers.getSessionId());
			map.put("orgName", systemUsers.getOrgName());
			map.put("roleName", systemUsers.getRoleName());
			map.put("orgCode", organizationVo.getOrgCode());			
			map.put("headPortrait", systemUsers.getHeadPortrait());			
			map.put("nikeName", Base64Util.encodeData(systemUsers.getNikeName()));			
			map.put("weixinQrImage", systemUsers.getWeixinQrImage());			
			result.setOK(ResultCode.CODE_STATE_200, "登录成功", TakeCareMapDate.cutNullMap(map));
			return result;
		}else{
			result.setError(ResultCode.CODE_STATE_4008, "系统错误，请联系IT部门。");
			return result;
		}
	}

	@Override
	public Result loginOut(SystemUsersSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		SystemUsers systemUsers = systemUsersDao.selectByCode(search.getSessionId());
		if(systemUsers == null){
			result.setError(ResultCode.CODE_STATE_4003, "用户信息错误");
			return result;
		}
		systemUsers.setSessionId("");
//		systemUsers.setLoginTime("");
		if(systemUsersDao.updateById(systemUsers)){
			params.clear();
			result.setOK(ResultCode.CODE_STATE_200, "登出成功", params);
			return result;
		}else{
			result.setError(ResultCode.CODE_STATE_4008, "系统错误，请联系IT部门。");
			return result;
		}
	}

	@Override
	public Result changePassword(SystemUsersSearch search, Result result, HttpSession session) throws Exception {
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
	public Result salesList(SystemUsersSearch search, Result result,SystemUsers systemUsers) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgCodeLevel", systemUsers.getOrgCode());
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
	public Result orgOneSelfList(SystemUsersSearch search, Result result, SystemUsers systemUsers) throws Exception {
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String,Object> params = new HashMap<String,Object>();
		Map<String,Object> groupingMap = new HashMap<String,Object>();
		groupingMap.put("orgId", systemUsers.getOrgId());
		groupingMap.put("groupingName", "销售组");
		List<SystemGrouping> systemGroupings = systemGroupingDao.select(groupingMap);//为了兼容销售组与其他
		if(systemGroupings != null && systemGroupings.size() > 0) {
			SystemGrouping grouping = systemGroupings.get(0);
			groupingMap.put("groupingId", grouping.getGroupingId());
			List<SystemUserGrouping> groupings = systemUserGroupingDao.select(groupingMap);
			if(groupings != null && groupings.size() > 0) {
				Set<Integer> set = new HashSet<>();
				for(SystemUserGrouping userGrouping : groupings) {
					set.add(userGrouping.getUserId());
				}
				if(set.size() > 0) {
					params.put("ids", set);
				}
			}
		}
		params.put("orgId", systemUsers.getOrgId());
		params.put("isEnable", true);
		if(StringUtil.isNotEmpty(search.getRealName())) {
			params.put("realName", search.getRealName());			
		}
		List<SystemUsersVo> systemUsersVos = systemUsersDao.select(params);
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
	public Result querySalesPerformance(SalesPerformanceSearch search) throws Exception {
		// TODO Auto-generated method stub
		//获取传入日期
		Calendar start = Calendar.getInstance();
		start.setTime(search.getQueryDate());
		start.set(Calendar.DAY_OF_MONTH, 1);
		Calendar end = Calendar.getInstance();
		end.setTime(search.getQueryDate());
		end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(start.getTime());
		String endDate = sdf.format(end.getTime());
		
		Map<String,Object> m1 = new HashMap<>();
		m1.put("usersId", search.getSystemUserId());
		m1.put("usersName", search.getSystemUserName());
		List<SystemUsers> users = systemUsersDao.select(m1);
		if(users == null || users.isEmpty()) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "不存在此员工");
		}
		SystemUsers user = users.get(0);
		
		SalesPerformanceVO vo = new SalesPerformanceVO();
		
		vo.setSystemUserId(user.getUsersId());
		vo.setSystemUserName(user.getRealName());
		
		//获取分配客户数量
		Map<String,Object> m2 = new HashMap<>();
		m2.put("systemUserId", user.getUsersId());
		m2.put("startCreateDate", startDate);
		m2.put("endCreateDate", endDate);
		
		List<CustomerCustomerOrg> customerCustomerOrgs = customerCustomerOrgDao.select(m2);
		if(null != customerCustomerOrgs) {
			vo.setCustomerNum(customerCustomerOrgs.size());
		}
		
		//获取售车数量
		Map<String,Object> m3 = new HashMap<>();
		m3.put("systemUserId", search.getSystemUserId());
		m3.put("systemUserName", search.getSystemUserName());
		m3.put("customerOrderState", CustomerOrderState.orderBeenFinish);
		m3.put("startDate", startDate);
		m3.put("endDate", endDate);
		List<CustomerOrder> orders = employeeUserDao.queryFinishedOrders(m3);//订单集合
		vo.setSaledCarNum(orders.size());
		
		//成交率
		if(!Objects.equals(vo.getCustomerNum(), 0)) {
			BigDecimal turnoverRate = new BigDecimal(vo.getSaledCarNum())
					
					.divide(new BigDecimal(vo.getCustomerNum()),2);
//			Double turnoverRate = new BigDecimal(vo.getSaledCarNum())
//					.divide(new BigDecimal(vo.getCustomerNum()),2).doubleValue();
			vo.setTurnoverRate(turnoverRate);
		}
		
		if(vo.getSaledCarNum() > 0) {
			//首先获取总毛利
//			Map<String,Object> m4 = new HashMap<>();
//			List<Integer> orderIds = orders.stream().map(order -> order.getCustomerOrderId()).collect(Collectors.toList());
//			m4.put("list", orderIds);
//			BigDecimal totalGrossProfit = employeeUserDao.getTotalGrossProfit(m4);
			//计算平均毛利
//			BigDecimal averageGrossProfit = totalGrossProfit.divide(new BigDecimal(vo.getSaledCarNum()),2);
//			vo.setAverageGrossProfit(averageGrossProfit);
			
			//按揭台次
			Long mortgageCarNum = orders.stream().map((order) -> order.getPaymentWay())
					.filter((n) -> Objects.equals(n, new Integer(2))).count();
			vo.setMortgageCarNum(mortgageCarNum.intValue());
			
			//按揭比例
			BigDecimal mortgageRate = new BigDecimal(mortgageCarNum).divide(new BigDecimal(orders.size()),2);
			vo.setMortgageRate(mortgageRate);
			
			//精品加装产值
			BigDecimal totalBoutiquePrice = orders.stream().map((order) -> order.getBoutiquePriace()).reduce(BigDecimal.ZERO, BigDecimal::add);
			vo.setTotalBoutiquePrice(totalBoutiquePrice);
			
			//单车精品加装产值
			BigDecimal perBoutiquePrice = totalBoutiquePrice.divide(new BigDecimal(orders.size()),2);
			vo.setPerBoutiquePrice(perBoutiquePrice);
			
			//保险产值
			BigDecimal totalInsurancePrice = orders.stream().map((order) -> order.getInsurancePriace()).reduce(BigDecimal.ZERO, BigDecimal::add);
			vo.setTotalInsurancePrice(totalInsurancePrice);
			
			//单车保险产值
			BigDecimal perInsurancePrice = totalInsurancePrice.divide(new BigDecimal(orders.size()),2);
			vo.setPerInsurancePrice(perInsurancePrice);
		}
		
		return new Result(vo);
		
	}

	@Override
	public Result orgOneSelfDriverList(SystemUsersSearch search, Result result, SystemUsers systemUsers) {
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String,Object> params = new HashMap<String,Object>();
		Map<String,Object> groupingMap = new HashMap<String,Object>();
		groupingMap.put("orgId", systemUsers.getOrgId());
		groupingMap.put("groupingName", "司机组");
		List<SystemGrouping> systemGroupings = systemGroupingDao.select(groupingMap);
		if(systemGroupings != null && systemGroupings.size() > 0) {
			SystemGrouping grouping = systemGroupings.get(0);
			groupingMap.put("groupingId", grouping.getGroupingId());
			List<SystemUserGrouping> groupings = systemUserGroupingDao.select(groupingMap);
			if(groupings != null && groupings.size() > 0) {
				Set<Integer> set = new HashSet<>();
				for(SystemUserGrouping userGrouping : groupings) {
					set.add(userGrouping.getUserId());
				}
				if(set.size() > 0) {
					params.put("ids", set);
				}
			}
		}
		params.put("orgId", systemUsers.getOrgId());
		params.put("isEnable", true);
		if(StringUtil.isNotEmpty(search.getRealName())) {
			params.put("realName", search.getRealName());			
		}
		List<SystemUsersVo> systemUsersVos = systemUsersDao.select(params);
		for(SystemUsersVo usersVo : systemUsersVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("systemUserName", usersVo.getRealName());
			map.put("systemUserId", usersVo.getUsersId());
			map.put("phoneNumber", usersVo.getPhoneNumber());
			map.put("idCardPicOn", usersVo.getIdCardPicOn());
			map.put("idCardPicOff", usersVo.getIdCardPicOff());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}

	@Override
	public Result weixinQrImage(SystemUsersSearch search, Result result, SystemUsers systemUsers) throws Exception {
		if(StringUtil.isNotEmpty(search.getWeixinQrImage())) {
			systemUsers.setWeixinQrImage(search.getWeixinQrImage());
			if(systemUsersDao.updateById(systemUsers)) {
				result.setOK(ResultCode.CODE_STATE_200, "上传成功");
			}else {
				result.setError("保存数据出错，请联系管理员");
			}
		}else {
			result.setError("请上传微信二维码名片");
		}			
		return result;
	}

}
