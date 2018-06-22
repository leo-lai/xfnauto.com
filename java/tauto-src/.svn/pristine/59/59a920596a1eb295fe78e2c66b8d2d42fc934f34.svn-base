package main.com.customer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloopen.rest.sdk.utils.DateUtil;

import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.po.Cars;
import main.com.car.dao.vo.CarsVo;
import main.com.customer.dao.dao.CustomerCarDao;
import main.com.customer.dao.dao.CustomerCustomerOrgDao;
import main.com.customer.dao.dao.CustomerOrderDao;
import main.com.customer.dao.dao.CustomerRemarksDao;
import main.com.customer.dao.dao.CustomerUsersDao;
import main.com.customer.dao.po.CustomerCar;
import main.com.customer.dao.po.CustomerCustomerOrg;
import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.po.CustomerRemarks;
import main.com.customer.dao.po.CustomerUsers;
import main.com.customer.dao.search.CustomerCarSearch;
import main.com.customer.dao.search.CustomerCustomerOrgSearch;
import main.com.customer.dao.search.CustomerUsersSearch;
import main.com.customer.dao.vo.CustomerCustomerOrgVo;
import main.com.customer.service.CustomerCustomerOrgService;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;
import main.com.system.dao.vo.OrganizationVo;
import main.com.utils.GeneralConstant;
import main.com.utils.IdcardValidator;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class CustomerCustomerOrgServiceImpl extends BaseServiceImpl<CustomerCustomerOrg> implements CustomerCustomerOrgService{

	@Autowired
	CustomerCustomerOrgDao customerCustomerOrgDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CustomerRemarksDao customerRemarksDao;
	
	@Autowired
	CustomerUsersDao customerUsersDao;
	
	@Autowired
	CustomerCarDao customerCarDao;
	
	@Autowired
	CustomerOrderDao customerOrderDao;
	
	@Override
	protected BaseDao<CustomerCustomerOrg> getBaseDao() {
		return customerCustomerOrgDao;
	}

	@Override
	public Result customerOrgList(CustomerCustomerOrgSearch search, Result result,SystemUsers users) throws Exception {
		//关联查询总是报错，没辣么多时间在这里墨迹。有时间再回来优化 20171102
//		OrganizationVo organizationVo = organizationDao.selectById(users.getOrgId());

//		List<OrganizationVo> organizationVos = organizationDao.selectChildren(users.getOrgId());
//		for(OrganizationVo organizationVo2 : organizationVos) {
//			System.out.println(organizationVo2.getShortName());
//		}
		
		//收集当前用户组织下的第三级组织数据（旧方法，保留）
//		OrganizationVo organizationVo = organizationDao.selectById(users.getOrgId());
//		if(organizationVo == null) {
//			result.setError(ResultCode.CODE_STATE_4005, "你的组织身份不明确，无客户信息可以查看");
//			return result;
//		}
//		Set<Integer> ids = new HashSet<Integer>();
//		Set<Integer> containsIds = new HashSet<Integer>();
//		containsIds.add(organizationVo.getOrgId());
//		if(organizationVo.getOrgLevel().equals(GeneralConstant.Org.Level_1)) { //如果是第一级
//			//查询二级
//			List<OrganizationVo> vosSecond = organizationDao.getByParentId(organizationVo.getOrgId());
//			for(OrganizationVo organizationVo2 : vosSecond) {
//				//查询三级
//				List<OrganizationVo> regionsThree = organizationDao.getByParentId(organizationVo2.getOrgId());
//				containsIds.add(organizationVo2.getOrgId());
//				for(OrganizationVo organizationVo3 : regionsThree) {
//					ids.add(organizationVo3.getOrgId());
//					containsIds.add(organizationVo3.getOrgId());
//				}
//			}
//		}else if(organizationVo.getOrgLevel().equals(GeneralConstant.Org.Level_2)) {//如果是二级
//			//查询第三级
//			List<OrganizationVo> regionsThree = organizationDao.getByParentId(organizationVo.getOrgId());
//			for(OrganizationVo organizationVo3 : regionsThree) {
//				ids.add(organizationVo3.getOrgId());
//				containsIds.add(organizationVo3.getOrgId());
//			}
//		}else {
//			ids.add(organizationVo.getOrgId());
//		}
		//收集当前用户组织及其下级数据
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgCodeLevel", users.getOrgCode());
		List<OrganizationVo> organizationVos = organizationDao.select(params);
		if(organizationVos == null || organizationVos.size() <= 0) {
			result.setError(ResultCode.CODE_STATE_4005, "你的组织身份不明确，无客户信息可以查看");
			return result;
		}
		Set<Integer> containsIds = new HashSet<Integer>();
		for(OrganizationVo organizationVo : organizationVos) {
			containsIds.add(organizationVo.getOrgId());
		}
		if(search.getOrgId() != null && !containsIds.contains(search.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的门店不在你的权限范围之内，请重新选择");
			return result;
		}else if(search.getOrgId() != null && containsIds.contains(search.getOrgId())){
			containsIds.clear();
			containsIds.add(search.getOrgId());
		}
		//根据组织查询出所有的客户
		params.clear();
		params = getParams(search);//普通搜索条件
		params.put("orgIds", containsIds);//组织搜索
		List<CustomerCustomerOrgVo> customerOrgVos = customerCustomerOrgDao.select(params);
		Long total = customerCustomerOrgDao.getRowCount(params);
		int rows = search.getRows();
		Map<String,Object> allMap = new HashMap<String, Object>();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		//查询用户的订单
		Set<Integer> set = new HashSet<Integer>();
		if(customerOrgVos == null || customerOrgVos.size() <= 0) {
			allMap.put("page", search.getPage());
			allMap.put("total", total);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		params.clear();
		params.put("orgIds", containsIds);//组织搜索
		for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
			set.add(customerOrgVo.getCustomerUsersId());
		}
		params.put("customerUsersIds", set);//组织搜索
		List<CustomerOrder> customerOrders = customerOrderDao.select(params);		
		for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("phoneNumber", customerOrgVo.getPhoneNumber()); //电话
			map.put("customerUsersOrgId", customerOrgVo.getCustomerUsersOrgId()); //ID
			map.put("customerUsersName", customerOrgVo.getCustomerUsersName()); //客户姓名
			map.put("customerUsersId", customerOrgVo.getCustomerUsersId()); //客户ID
			map.put("intentionCarInfo", customerOrgVo.getIntentionCarInfo()!= null?customerOrgVo.getIntentionCarInfo():""); //意向车辆信息
			map.put("carsName", customerOrgVo.getIntentionCarInfo()!= null?customerOrgVo.getIntentionCarInfo():""); //意向车辆信息
			map.put("carsId", customerOrgVo.getIntentionCarId()); //意向车辆信息
			Cars cars = carsDao.selectById(customerOrgVo.getIntentionCarId());
			if(cars != null) {
				map.put("brandId", cars.getBrandId()); //意向车辆信息
				map.put("familyId", cars.getFamilyId()); //销售顾问
			}else {
				map.put("brandId", ""); //意向车辆信息
				map.put("familyId", ""); //销售顾问
			}
			map.put("intentionCarId", customerOrgVo.getIntentionCarId()); //意向车辆信息
//			map.put("systemUserName", customerOrgVo.getSystemUserName()); //销售顾问			
//			map.put("systemUserId", customerOrgVo.getSystemUserId()); //销售顾问		
			CustomerOrder customerOrder_1 = null;
			if(customerOrders != null && customerOrders.size() >= 0) {
				for(CustomerOrder customerOrder : customerOrders) {
					if(customerOrder.getCustomerId().equals(customerOrgVo.getCustomerUsersId())) {
						customerOrder_1 = customerOrder;
						switch(customerOrder.getCustomerOrderState().intValue()) {
						case 1:
							map.put("orderState", "待交定金");
							break;
						case 3:
							map.put("orderState", "待银行审核");
							break;
						case 4:
							map.put("orderState", "已被银行拒绝");
							break;
						case 5:
							map.put("orderState", "等待车辆出库");
							break;
						case 7:
							map.put("orderState", "等待加装精品");
							break;
						case 9:
							map.put("orderState", "等待上牌");
							break;
						case 11:
							map.put("orderState", "等待贴膜");
							break;
						case 13:
							map.put("orderState", "等待交车");
							break;
						case 15:
							map.put("orderState", "已提车");
							break;
						case 17:
							map.put("orderState", "已交车，订单已完结");
							break;
						default:
							map.put("orderState", "未知");
							break;
						}
						map.put("systemUserName", customerOrder.getSystemUserName()); //销售顾问			
						map.put("systemUserId", customerOrder.getSystemUserId()); //销售顾问		
						map.put("expectWayId", customerOrgVo.getExpectWayId()); //
						if(customerOrder.getPaymentWay().equals(GeneralConstant.CustomerOrderState.byStages)) {
							map.put("expectWayName", "贷款购车"); //
						}else {
							map.put("expectWayName", "全款购车"); //
						}
					}
				}
//				CustomerOrder customerOrder = customerOrders.get(0);
				if(customerOrder_1 == null) {
					map.put("orderState", "无购车订单"); //订单最新动态
					map.put("systemUserName", customerOrgVo.getSystemUserName()); //销售顾问			
					map.put("systemUserId", customerOrgVo.getSystemUserId()); //销售顾问		
					
					map.put("expectWayId", customerOrgVo.getExpectWayId()); //
					if(customerOrgVo.getExpectWayId() != null ) {
						if(customerOrgVo.getExpectWayId().equals(GeneralConstant.CustomerOrderState.byStages)) {
							map.put("expectWayName", "贷款购车"); //
						}else {
							map.put("expectWayName", "全款购车"); //
						}
					}else {
						map.put("expectWayName", "未知"); //
					}
					
				}
			}else {
				map.put("orderState", "无购车订单"); //订单最新动态
				map.put("systemUserName", customerOrgVo.getSystemUserName()); //销售顾问			
				map.put("systemUserId", customerOrgVo.getSystemUserId()); //销售顾问		
				
				map.put("expectWayId", customerOrgVo.getExpectWayId()); //
				if(customerOrgVo.getExpectWayId() != null) {
					if(customerOrgVo.getExpectWayId().equals(GeneralConstant.CustomerOrderState.byStages)) {
						map.put("expectWayName", "贷款购车"); //
					}else {
						map.put("expectWayName", "全款购车"); //
					}
				}else {
					map.put("expectWayName", "未知"); //
				}
			}
			map.put("remark", customerOrgVo.getRemarks()); //
			map.put("remarks", customerOrgVo.getRemarks()); //
			if(search.getIsBespeak() != null && search.getIsBespeak()) {//是否是预约
				map.put("orgShortName", customerOrgVo.getOrgShortName()); //预约的门店
				map.put("timeOfAppointment", main.com.utils.DateUtil.format(customerOrgVo.getAppointmentDate(),true)+" "+customerOrgVo.getTimeOfAppointment()); //预约时间段
				map.put("appointmentDate", main.com.utils.DateUtil.format(customerOrgVo.getAppointmentDate(),true)); //预约时间段
				map.put("timeOfAppointment", customerOrgVo.getTimeOfAppointment()); //预约时间段
				map.put("timeOfAppointmentDate", main.com.utils.DateUtil.format(customerOrgVo.getTimeOfAppointmentDate(),true)); //客户提交预约的时间
				map.put("carPurchaseIntention", customerOrgVo.getCarPurchaseIntention()!=null?Integer.parseInt(customerOrgVo.getCarPurchaseIntention()):3); //购车意向
			}
			if(search.getIsTrack() != null && search.getIsTrack()) {//是否是追踪列表
//				map.put("expectWayName", customerOrgVo.getExpectWayName()); //意向车型
			}
			maps.add(map);
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}

	public Map<String,Object> getParams(CustomerCustomerOrgSearch search){
		Map<String,Object> params = new HashMap<String, Object>();
		
		if(search.getIsTrack() != null && search.getIsTrack()) {
			params.put("isTrack", true);
		}
		if(search.getIsBespeak() != null && search.getIsBespeak()) {
			params.put("isBespeak", true);
		}
		if(StringUtil.isNotEmpty(search.getPhoneNumber())) {
			params.put("phoneNumber", search.getPhoneNumber());
		}
		if(StringUtil.isNotEmpty(search.getStartDate())) {
			params.put("startDate", search.getStartDate());
		}
		if(StringUtil.isNotEmpty(search.getEndDate())) {
			params.put("endDate", search.getEndDate());
		}
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	public Result trackCustomerOrg(CustomerCustomerOrgSearch search, Result result, SystemUsers users)
			throws Exception {
		if(search.getCustomerUsersOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择客户");
			return result;
		}
		CustomerCustomerOrgVo customerOrgVos = customerCustomerOrgDao.selectById(search.getCustomerUsersOrgId());
		if(customerOrgVos == null) {
			result.setError(ResultCode.CODE_STATE_4005, "所选客户不存在，请核对数据");
			return result;
		}
		if(!customerOrgVos.getIsEdit()) {
			result.setError(ResultCode.CODE_STATE_4005, "此用户信息已成为其他门店消费客户，已被锁定，你不能继续编辑客户信息");
			return result;
		}
		if(!customerOrgVos.getOrgId().equals(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "变更此项用户信息不在你的权限之内");
			return result;
		}
		customerOrgVos.setIsTrack(true);
		SystemUsers systemUsers = null;
		if(search.getSystemUserId()!= null && customerOrgVos.getSystemUserId() == null) {
			systemUsers = systemUsersDao.selectById(search.getSystemUserId());
			customerOrgVos.setSystemUserId(systemUsers.getUsersId());
			customerOrgVos.setSystemUserPhone(systemUsers.getPhoneNumber());
			customerOrgVos.setSystemUserName(systemUsers.getRealName());
		}else if(search.getSystemUserId()!= null && customerOrgVos.getSystemUserId() != null && !search.getSystemUserId().equals(customerOrgVos.getSystemUserId())) {
			systemUsers = systemUsersDao.selectById(search.getSystemUserId());
			customerOrgVos.setSystemUserId(systemUsers.getUsersId());
			customerOrgVos.setSystemUserPhone(systemUsers.getPhoneNumber());
			customerOrgVos.setSystemUserName(systemUsers.getRealName());
		}
		return customerCustomerOrgDao.updateByIdAndResultIT(customerOrgVos, result);
	}

	@Override
	public Result notBuyCustomerOrg(CustomerCustomerOrgSearch search, Result result, SystemUsers users)
			throws Exception {
		if(search.getCustomerUsersOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择客户");
			return result;
		}
		CustomerCustomerOrgVo customerOrgVos = customerCustomerOrgDao.selectById(search.getCustomerUsersOrgId());
		if(customerOrgVos == null) {
			result.setError(ResultCode.CODE_STATE_4005, "所选客户不存在，请核对数据");
			return result;
		}
		if(!customerOrgVos.getIsEdit()) {
			result.setError(ResultCode.CODE_STATE_4005, "此用户信息已成为其他门店消费客户，已被锁定，你不能继续编辑客户信息");
			return result;
		}
		if(!customerOrgVos.getOrgId().equals(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "变更此项用户信息不在你的权限之内");
			return result;
		}
		customerOrgVos.setIsNotBuy(true);
		customerOrgVos.setIsAppointment(false);
		return customerCustomerOrgDao.updateByIdAndResultIT(customerOrgVos, result);
	}

	@Override
	public Result bespeakChangeCustomerOrg(CustomerCustomerOrgSearch search, Result result, SystemUsers users)
			throws Exception {
		if(search.getCustomerUsersOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择客户");
			return result;
		}
		CustomerCustomerOrgVo customerOrgVos = customerCustomerOrgDao.selectById(search.getCustomerUsersOrgId());
		if(customerOrgVos == null) {
			result.setError(ResultCode.CODE_STATE_4005, "所选客户不存在，请核对数据");
			return result;
		}
		if(!customerOrgVos.getIsEdit()) {
			result.setError(ResultCode.CODE_STATE_4005, "此用户信息已成为其他门店消费客户，已被锁定，你不能继续编辑客户信息");
			return result;
		}
		if(!customerOrgVos.getOrgId().equals(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "变更此项用户信息不在你的权限之内");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getTimeOfAppointment())) {
			customerOrgVos.setAppointmentDate(search.getAppointmentDate());
			customerOrgVos.setTimeOfAppointment(search.getTimeOfAppointment());
			customerOrgVos.setTimeOfAppointmentDate(new Date());
		}
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			customerOrgVos.setRemarks(search.getRemarks());
		}
		customerOrgVos.setIsAppointment(true);
		if(search.getIntentionCarId() != null) {
			CarsVo cars = carsDao.selectById(search.getIntentionCarId());
			if(cars == null) {
				result.setError(ResultCode.CODE_STATE_4005, "所选车型不存在，请重新选择");
				return result;
			}
			customerOrgVos.setIntentionCarId(cars.getCarId());
			StringBuffer buffer = new StringBuffer();
			buffer.append(cars.getBrandName()).append(" ");
			buffer.append(cars.getFamilyName()).append(" ");
			buffer.append(cars.getYearPattern()).append("款").append(" ");
			buffer.append(cars.getPl()).append(" ");
			buffer.append(cars.getGearboxName()).append(cars.getStyleName());
			customerOrgVos.setIntentionCarInfo(buffer.toString());
		}
		return customerCustomerOrgDao.updateByIdAndResultIT(customerOrgVos, result);
	}

	@Override
	public Result systenUserChangeCustomerOrg(CustomerCustomerOrgSearch search, Result result, SystemUsers users)
			throws Exception {
		if(search.getCustomerUsersOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择客户");
			return result;
		}
		CustomerCustomerOrgVo customerOrgVos = customerCustomerOrgDao.selectById(search.getCustomerUsersOrgId());
		if(customerOrgVos == null) {
			result.setError(ResultCode.CODE_STATE_4005, "所选客户不存在，请核对数据");
			return result;
		}
		if(!customerOrgVos.getIsEdit()) {
			result.setError(ResultCode.CODE_STATE_4005, "此用户信息已成为其他门店消费客户，已被锁定，你不能继续编辑客户信息");
			return result;
		}
		if(!customerOrgVos.getOrgId().equals(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "变更此项用户信息不在你的权限之内");
			return result;
		}
		SystemUsers systemUsers = null;
		if(search.getSystemUserId()!= null && customerOrgVos.getSystemUserId() == null) {
			systemUsers = systemUsersDao.selectById(search.getSystemUserId());
			customerOrgVos.setSystemUserId(systemUsers.getUsersId());
			customerOrgVos.setSystemUserPhone(systemUsers.getPhoneNumber());
			customerOrgVos.setSystemUserName(systemUsers.getRealName());
		}else if(search.getSystemUserId()!= null && customerOrgVos.getSystemUserId() != null && !search.getSystemUserId().equals(customerOrgVos.getSystemUserId())) {
			systemUsers = systemUsersDao.selectById(search.getSystemUserId());
			customerOrgVos.setSystemUserId(systemUsers.getUsersId());
			customerOrgVos.setSystemUserPhone(systemUsers.getPhoneNumber());
			customerOrgVos.setSystemUserName(systemUsers.getRealName());
		}
		customerOrgVos.setIsTrack(true);
		customerOrgVos.setIsAppointment(false);
		return customerCustomerOrgDao.updateByIdAndResultIT(customerOrgVos, result);
	}

	@Override
	public Result addcustomerRemarks(CustomerCustomerOrgSearch search, Result result, SystemUsers users)
			throws Exception {
		CustomerRemarks customerRemarks = new CustomerRemarks();
		customerRemarks.setCreateDate(new Date());
		CustomerUsers customerUsers = customerUsersDao.selectById(search.getCustomerUsersId());
		if(customerUsers == null) {
			result.setError(ResultCode.CODE_STATE_4005, "未知备注用户");
			return result;
		}
		customerRemarks.setCustomerId(customerUsers.getCustomerUsersId());
		customerRemarks.setCustomerName(customerUsers.getCustomerUsersName());
		customerRemarks.setRemarksContent(search.getRemarksContent());
		customerRemarks.setSystemUserId(users.getUsersId());
		customerRemarks.setSystemUserName(users.getRealName());
		customerRemarks.setOrgId(users.getOrgId());
		customerRemarks.setOrgCode(users.getOrgCode());
		if(customerRemarksDao.insert(customerRemarks)) {
			Map<String,Object> remarksMap = new HashMap<String, Object>();
			remarksMap.put("createDate", main.com.utils.DateUtil.format(customerRemarks.getCreateDate()));
			remarksMap.put("remarksContent", customerRemarks.getRemarksContent());
			remarksMap.put("remarksId", customerRemarks.getRemarksId());
			remarksMap.put("systemUserId", customerRemarks.getSystemUserId());
			remarksMap.put("systemUserName", customerRemarks.getSystemUserName());
			remarksMap.put("customerUsersId", customerRemarks.getCustomerId());
			result.setOK(ResultCode.CODE_STATE_200, "", TakeCareMapDate.cutNullMap(remarksMap));
		}else {
			result.setError("保存数据失败，请联系管理员");
		}
		return result;//customerRemarksDao.insertAndResultIT(customerRemarks, result);
	}

	@Override
	public Result changeUserInfo(CustomerCustomerOrgSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getCustomerUsersId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请先确定用户");
			return result;
		}
		CustomerUsers customerUsers = customerUsersDao.selectById(search.getCustomerUsersId());
		if(customerUsers == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的用户不存在或者已删除");
			return result;
		}
		if(StringUtil.isEmpty(search.getCustomerUsersName())) {
			result.setError(ResultCode.CODE_STATE_4005, "请输入客户姓名");			
			return result;
		}
		customerUsers.setCustomerUsersName(search.getCustomerUsersName());
		if(StringUtil.isEmpty(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4005, "请输入客户手机号");			
			return result;
		}
		customerUsers.setPhoneNumber(search.getPhoneNumber());
		customerUsers.setAnnualIncome(search.getAnnualIncome());
		IdcardValidator validator = new IdcardValidator();
		if(search.getCardNo() != null && validator.isValidatedAllIdcard(search.getCardNo())) {
			customerUsers.setCardNo(search.getCardNo());
		}else if(search.getCardNo() != null) {
			result.setError(ResultCode.CODE_STATE_4005, "身份证号码不正确");			
			return result;
		}
		customerUsers.setAgentGender(search.getAgentGender());
		customerUsers.setIncomeSource(search.getIncomeSource());
		customerUsers.setMaritalStatus(search.getMaritalStatus());
		customerUsers.setHousingSource(search.getHousingSource());
		customerUsers.setIsHasDriversLicense(search.getIsHasDriversLicense());
		customerUsers.setEmail(search.getEmail());
		customerUsers.setEducation(search.getEducation());
		customerUsers.setAddress(search.getAddress());
		customerUsers.setEmergencyAContact(search.getEmergencyAContact());       //紧急联系人A
		customerUsers.setEmergencyBContact(search.getEmergencyBContact());          //紧急联系人B
		customerUsers.setEmergencyARelationship(search.getEmergencyARelationship());       //与用户关系A
		customerUsers.setEmergencyBRelationship(search.getEmergencyBRelationship());         //与用户关系B
		customerUsers.setEmergencyAPhone(search.getEmergencyAPhone());               //联系人A电话号
		customerUsers.setEmergencyBPhone(search.getEmergencyBPhone());               //联系人B电话号
		customerUsers.setWorkUnit(search.getWorkUnit());                    //工作单位
		customerUsers.setAnnualIncomeAfterTax(search.getAnnualIncomeAfterTax());         //税后年收入
		customerUsers.setWorkingPlace(search.getWorkingPlace());                //工作地点
		customerUsers.setEntryTime(search.getEntryTime());                 //工作入职时间
		customerUsers.setPosition(search.getPosition());                //职位
		customerUsers.setCompanyTelephone(search.getCompanyTelephone());        //公司电话
		return customerUsersDao.updateByIdAndResultIT(customerUsers, result);
	}

	@Override
	public Result changeUserCarInfo(CustomerCarSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(search.getCustomerCarId())) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的车辆不存在或者已删除");
			return result;
		}
		CustomerCar customerCar = customerCarDao.selectById(search.getCustomerCarId());
		if(customerCar == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的车辆不存在或者已删除");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getLicensePlateNumber())) {
			customerCar.setLicensePlateNumber(search.getLicensePlateNumber());
		}
		if(StringUtil.isNotEmpty(search.getAfterSaleSupport())) {
			customerCar.setAfterSaleSupport(search.getAfterSaleSupport());
		}
		if(StringUtil.isNotEmpty(search.getPurchaseTax())) {
			customerCar.setPurchaseTax(search.getPurchaseTax());
		}
		if(StringUtil.isNotEmpty(search.getExciseTax())) {
			customerCar.setExciseTax(search.getExciseTax());
		}
		if(StringUtil.isNotEmpty(search.getVehicleAndVesselTax())) {
			customerCar.setVehicleAndVesselTax(search.getVehicleAndVesselTax());
		}
		if(StringUtil.isNotEmpty(search.getPremium())) {
			customerCar.setPremium(search.getPremium());
		}
		if(StringUtil.isNotEmpty(search.getCompulsoryInsurance())) {
			customerCar.setCompulsoryInsurance(search.getCompulsoryInsurance());
		}
		if(StringUtil.isNotEmpty(search.getThirdPartyLiabilityInsurance())) {
			customerCar.setThirdPartyLiabilityInsurance(search.getThirdPartyLiabilityInsurance());
		}
		if(StringUtil.isNotEmpty(search.getVehicleLossInsurance())) {
			customerCar.setVehicleLossInsurance(search.getVehicleLossInsurance());
		}
		if(StringUtil.isNotEmpty(search.getRiskOfGlassBreakage())) {
			customerCar.setRiskOfGlassBreakage(search.getRiskOfGlassBreakage());
		}
		if(StringUtil.isNotEmpty(search.getSelfIgnitionLossInsurance())) {
			customerCar.setSelfIgnitionLossInsurance(search.getSelfIgnitionLossInsurance());
		}
		if(StringUtil.isNotEmpty(search.getExemptionFromSpecialContract())) {
			customerCar.setExemptionFromSpecialContract(search.getExemptionFromSpecialContract());
		}
		if(StringUtil.isNotEmpty(search.getNoLiabilityInsurance())) {
			customerCar.setNoLiabilityInsurance(search.getNoLiabilityInsurance());
		}
		if(StringUtil.isNotEmpty(search.getPersonnelLiabilityInsurance())) {
			customerCar.setPersonnelLiabilityInsurance(search.getPersonnelLiabilityInsurance());
		}
		if(StringUtil.isNotEmpty(search.getScratchRisk())) {
			customerCar.setScratchRisk(search.getScratchRisk());
		}
		return customerCarDao.updateByIdAndResultIT(customerCar, result);
	}
}
