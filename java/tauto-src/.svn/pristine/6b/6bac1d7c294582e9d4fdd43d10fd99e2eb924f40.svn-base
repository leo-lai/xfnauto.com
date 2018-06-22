package main.com.weixinApp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.po.Cars;
import main.com.car.dao.vo.CarsVo;
import main.com.customer.dao.dao.CustomerCustomerOrgDao;
import main.com.customer.dao.dao.CustomerOrderDao;
import main.com.customer.dao.dao.CustomerOrderTrackDao;
import main.com.customer.dao.dao.CustomerRemarksDao;
import main.com.customer.dao.dao.CustomerUsersDao;
import main.com.customer.dao.dao.UsersTmplDao;
import main.com.customer.dao.po.CustomerCustomerOrg;
import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.po.CustomerRemarks;
import main.com.customer.dao.po.CustomerUsers;
import main.com.customer.dao.po.CustomerUsersDTO;
import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.dao.search.CustomerCustomerOrgSearch;
import main.com.customer.dao.search.CustomerUsersSearch;
import main.com.customer.dao.vo.CustomerCustomerOrgVo;
import main.com.customer.dao.vo.CustomerOrderTrackVo;
import main.com.customer.dao.vo.CustomerOrderVo;
import main.com.customer.dao.vo.CustomerRemarksVo;
import main.com.customer.dao.vo.CustomerUsersVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemGroupingDao;
import main.com.system.dao.dao.SystemUserGroupingDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.SystemGrouping;
import main.com.system.dao.po.SystemUserGrouping;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;
import main.com.system.dao.vo.OrganizationVo;
import main.com.system.dao.vo.SystemGroupingVo;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.IdcardValidator;
import main.com.utils.Number;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.weixinApp.service.EmployeeCustomerService;
import main.com.weixinHtml.dao.dao.ShopAdvanceOrderDao;
import main.com.weixinHtml.dao.vo.ShopAdvanceOrderVo;

@Service
public class EmployeeCustomerServiceImpl extends BaseServiceImpl<CustomerUsers>implements EmployeeCustomerService{

	@Autowired
	CustomerUsersDao customerUsersDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CustomerCustomerOrgDao customerCustomerOrgDao;
	
	@Autowired
	CustomerOrderDao customerOrderDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	UsersTmplDao usersTmplDao;
	
	@Autowired
	CustomerRemarksDao customerRemarksDao;
	
	@Autowired
	CustomerOrderTrackDao customerOrderTrackDao;
	
	@Autowired
	SystemUserGroupingDao systemUserGroupingDao;
	
	@Autowired
	SystemGroupingDao systemGroupingDao;
	
	@Autowired
	ShopAdvanceOrderDao shopAdvanceOrderDao;
	
	@Override
	protected BaseDao<CustomerUsers> getBaseDao() {
		return customerUsersDao;
	}

	@Override
	public Result organizationLevelList(OrganizationSearch search, Result result, SystemUsers systemUsers) {
		if(StringUtil.isEmpty(systemUsers.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的组织身份不明确，不能进行此操作");
			return result;
		}
		Map<String, Object> params =  new HashMap<String,Object>();
		params.put("orgCodeLevel", systemUsers.getOrgCode());
		List<OrganizationVo> list = organizationDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(OrganizationVo organization : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("shortName", organization.getShortName());
			map.put("orgId", organization.getOrgId());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}
	
	@Override
	public Result customerOrgList(CustomerCustomerOrgSearch search, Result result,SystemUsers users) throws Exception {
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
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = customerCustomerOrgDao.getRowCount(params);
		int rows = search.getRows();
		if(customerOrgVos == null  || customerOrgVos.size() <= 0) {
			allMap.put("page", search.getPage());
			allMap.put("total", 0);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		Set<Integer> set = new HashSet<Integer>();
		for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
			set.add(customerOrgVo.getCustomerUsersId());
		}
		params.put("ids", set);
		List<CustomerUsersVo> customerUsersVos = customerUsersDao.select(params);		
		if(customerUsersVos == null || customerUsersVos.size() <= 0) {
			allMap.put("page", search.getPage());
			allMap.put("total", 0);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		for(CustomerUsersVo customerUsersVo : customerUsersVos) {
			for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
				if(customerUsersVo.getCustomerUsersId().equals(customerOrgVo.getCustomerUsersId())) {
					customerOrgVo.setCustomerUsersVo(customerUsersVo);
				}
			}
		}
		for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("phoneNumber", customerOrgVo.getPhoneNumber()); //电话
			map.put("customerUsersOrgId", customerOrgVo.getCustomerUsersOrgId()); //ID
			map.put("customerUsersName", customerOrgVo.getCustomerUsersName()); //客户姓名
			map.put("customerUsersId", customerOrgVo.getCustomerUsersId()); //客户ID
			map.put("carsName", customerOrgVo.getIntentionCarInfo()!= null?customerOrgVo.getIntentionCarInfo():""); //意向车辆信息
			map.put("carsId", customerOrgVo.getIntentionCarId()); //意向车辆信息			
			map.put("orderState", ""); //订单最新动态
			if(customerOrgVo.getExpectWayId() != null) {
				if(customerOrgVo.getExpectWayId().equals(GeneralConstant.CustomerOrderState.fullPayment)) {
					map.put("expectWayId", customerOrgVo.getExpectWayId()); //
					map.put("expectWayName", "全款购车"); //
				}else {
					map.put("expectWayId", customerOrgVo.getExpectWayId()); //
					map.put("expectWayName", "贷款购车"); //
				}
			}else {
				map.put("expectWayId", ""); //
				map.put("expectWayName", ""); //
			}
			map.put("remark", customerOrgVo.getRemarks()); //
			map.put("remarks", customerOrgVo.getRemarks()); //
			map.put("carPurchaseIntention", customerOrgVo.getCarPurchaseIntention()!=null?Integer.parseInt(customerOrgVo.getCarPurchaseIntention()):2); //购车意向
			if(customerOrgVo.getCustomerUsersVo() != null) {
				map.put("headPortrait", customerOrgVo.getCustomerUsersVo().getHeadPortrait());
			}else {
				map.put("headPortrait", "");
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
	
	
	
	public Result customerUserList_作废保留(CustomerCustomerOrgSearch search, Result result,SystemUsers users) throws Exception {
		//收集当前用户组织及其下级数据
		Map<String,Object> params = new HashMap<String,Object>();
		
		//数据权限控制，因为数据权限只有一处，为了节省开发时间，直接写到此处，后续需要写到缓存里
		//提取出来销售组
		Map<String,Object> groupingMap = new HashMap<String,Object>();
		groupingMap.put("orgId", users.getOrgId());
		groupingMap.put("groupingName", "销售组");
		List<SystemGrouping> systemGroupings = systemGroupingDao.select(groupingMap);
		if(systemGroupings == null || systemGroupings.size() <= 0) {
			result.setError("贵司尚未进行 销售组 权限配置，请先配置数据权限");
			return result;
		}
		SystemGrouping grouping = systemGroupings.get(0);
		groupingMap.put("userId", users.getUsersId());
		groupingMap.put("groupingId", grouping.getGroupingId());
		List<SystemUserGrouping> groupings = systemUserGroupingDao.select(groupingMap);
		if(groupings == null || groupings.size() <= 0) {
			result.setError("你的权限分组信息未知，暂不能查看用户，请先让管理员配置你的权限");
			return result;
		}else {
			SystemUserGrouping userGrouping = groupings.get(0);
			if(!userGrouping.getOverManage()) {				
				params.put("systemUserId", users.getUsersId());
			}else {
				SystemGroupingVo systemGrouping = systemGroupingDao.selectByIdJoin(grouping.getGroupingId());
				Set<Integer> set = new HashSet<Integer>();
				for(SystemUserGrouping systemUserGrouping : systemGrouping.getSystemUserGroupingVos()) {
					set.add(systemUserGrouping.getUserId());
				}
				params.put("systemUserIds", set);
			}
		}
		
		params.put("orgCode", users.getOrgCode());
		if(StringUtil.isNotEmpty(search.getCustomerUsersSearch())) {
			params.put("customerUsersSearch", search.getCustomerUsersSearch());
		}
//		if(StringUtil.isNotEmpty(search.getPhoneNumber())) {
//			params.put("phoneSearch", search.getPhoneNumber());
//		}
		if(StringUtil.isNotEmpty(search.getPaymentWay())) {
			params.put("paymentWay", search.getPaymentWay());
		}
		//1.查询出来该门店的所有用户
		//从第几条开始
		params.put("sortField", true);
//		params.put("isPage", true);
//		params.put("offset", (search.getPage()-1)*search.getRows());
//		//返回几条
//		params.put("limit", search.getRows());
		List<CustomerCustomerOrgVo> customerOrgVos = customerCustomerOrgDao.select(params);
//		Map<String,Object> allMap = new HashMap<String, Object>();
//		Long total = customerCustomerOrgDao.getRowCount(params);
//		int rows = search.getRows();
		
		/**
		 * 去掉因为预约和实际下单不一致的期待购车方式，兼容预约和全部客户的期待购车方式，使两者可以不一致决解bug：
		 * 新增客户时选择分期，然后开单时选择全款，客户列表中按全款查找，找不到该单
		 */
		Set<Integer> customerUsersIds = new HashSet<Integer>();
		List<CustomerCustomerOrgVo> customerOrgVos2 = new ArrayList<CustomerCustomerOrgVo>();
		if(StringUtil.isNotEmpty(search.getPaymentWay())) {
			params.remove("paymentWay");
			customerOrgVos2 = customerCustomerOrgDao.select(params);
			for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos2) {
				customerUsersIds.add(customerOrgVo.getCustomerUsersId());
			}
		}else {
			for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
				customerUsersIds.add(customerOrgVo.getCustomerUsersId());
			}
		}
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		if(customerUsersIds.size() <= 0) {
//			allMap.put("page", search.getPage());
//			allMap.put("total", total);
//			allMap.put("rows", rows);
//			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
			return result;
		}
		//2.查询客户
		params.put("customerUsersIds", customerUsersIds);
		List<CustomerUsersVo> customerUsersVos = customerUsersDao.select(params);		
		//3.整理预约信息到客户列表
		
		/**
		 * 去掉因为预约和实际下单不一致的期待购车方式，兼容预约和全部客户的期待购车方式，使两者可以不一致决解bug：
		 * 新增客户时选择分期，然后开单时选择全款，客户列表中按全款查找，找不到该单
		 */
		if(StringUtil.isNotEmpty(search.getPaymentWay())) {
			for(CustomerUsersVo customerUsersVo : customerUsersVos) {
				for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos2) {
					if(customerUsersVo.getCustomerUsersId().equals(customerOrgVo.getCustomerUsersId())) {
						customerUsersVo.setCustomerOrgVo(customerOrgVo);
					}
				}
			}
			params.put("paymentWay", search.getPaymentWay());
		}else {
			for(CustomerUsersVo customerUsersVo : customerUsersVos) {
				for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
					if(customerUsersVo.getCustomerUsersId().equals(customerOrgVo.getCustomerUsersId())) {
						customerUsersVo.setCustomerOrgVo(customerOrgVo);
					}
				}
			}
		}
//		if(search.getBuyCarAlready()) {
////			params.put("buyCarAlready", search.getBuyCarAlready());
//			
//		}		
		
		if(StringUtil.isNotEmpty(search.getOrderStates())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orderStates = search.getOrderStates().split(GeneralConstant.SystemBoolean.SPLIT);
			for(String state : orderStates) {
				set.add(Integer.parseInt(state));
			}
			params.put("orderStates", set);
			search.setBuyCarAlready(true);
		}
		List<CustomerOrderVo> customerOrderVos = customerOrderDao.select(params);	
		//5.整合订单
		for(CustomerUsersVo customerUsersVo : customerUsersVos) {
			for(CustomerOrderVo customerOrderVo : customerOrderVos) {
				if(customerUsersVo.getCustomerUsersId().equals(customerOrderVo.getCustomerId())) {
					if(customerUsersVo.getCustomerOrderVos() == null) {
						customerUsersVo.setCustomerOrderVos(new ArrayList<>());
					}
					customerUsersVo.getCustomerOrderVos().add(customerOrderVo);
//					customerUsersVo.setCustomerOrderVo(customerOrderVo);
				}
			}
		}
		
//		Map<String,Object> allMap = new HashMap<String, Object>();
//		Long total = customerCustomerOrgDao.getRowCount(params);
//		int rows = search.getRows();
		for(CustomerUsersVo customerUsersVo : customerUsersVos) {
			if(customerUsersVo.getCustomerOrgVo() == null) {
				continue;
			}
			if(search.getBuyCarAlready() != null && search.getBuyCarAlready()) {
				if(customerUsersVo.getCustomerOrderVos() == null || customerUsersVo.getCustomerOrderVos().size() <= 0) {
					continue;
				}
			}
//			if(search.getBuyCarAlready() != null && search.getBuyCarAlready() == false) {
//				if(customerUsersVo.getCustomerOrderVo() != null) {
//					continue;
//				}
//			}
			Map<String,Object> mapOrder = new HashMap<>();

			mapOrder.put("customerUsersName", customerUsersVo.getCustomerUsersName());
			mapOrder.put("customerUsersId", customerUsersVo.getCustomerUsersId());
			mapOrder.put("phoneNumber", customerUsersVo.getPhoneNumber());
			mapOrder.put("headPortrait", customerUsersVo.getHeadPortrait());
			
			List<Map<String,Object>> orderList = new ArrayList<>();
			Map<String, Object> appointmentMap = new HashMap<String,Object>();

			if(customerUsersVo.getCustomerOrderVos() != null && customerUsersVo.getCustomerOrderVos().size() > 0) {
				
			for(CustomerOrderVo customerOrder : customerUsersVo.getCustomerOrderVos()) {
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("customerOrderId", customerOrder.getCustomerOrderId());				
				switch (customerOrder.getCustomerOrderState()) {
				case 1:
					map.put("orderStateName", "待交定金");
					map.put("orderState", 1);
					break;
				case 3:
					map.put("orderStateName", "待银行审核");
					map.put("orderState", 3);
					break;
				case 4:
					map.put("orderStateName", "已被银行拒绝");
					map.put("orderState", 4);
					break;
				case 5:
					map.put("orderStateName", "等待车辆出库");
					map.put("orderState", 5);
					break;
				case 7:
					map.put("orderStateName", "等待加装精品");
					map.put("orderState", 7);
					break;
				case 9:
					map.put("orderStateName", "等待上牌");
					map.put("orderState", 9);
					break;
				case 11:
					map.put("orderStateName", "等待贴膜");
					map.put("orderState", 11);
					break;
				case 13:
					map.put("orderStateName", "等待交车");
					map.put("orderState", 13);
					break;
				case 15:
					map.put("orderStateName", "已人车合照");
					map.put("orderState", 15);
					break;
				case 17:
					map.put("orderStateName", "已完款，交车放行");
					map.put("orderState", 17);
					break;
				case 19:
					 //新增需求，添加回访内容返回
				    Map<String,Object> paramsTrack = new HashMap<String, Object>();
					paramsTrack.put("customerOrderId", customerOrder.getCustomerOrderId());
				    List<CustomerOrderTrackVo> customerOrderTrackVos = customerOrderTrackDao.select(paramsTrack);
					for(CustomerOrderTrackVo trackVo : customerOrderTrackVos) {
						if(trackVo.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.orderVisit)) {
							map.put("visitContent", trackVo.getTrackContent());
							break;
						}
					}
					map.put("orderStateName", "已回访");
					map.put("orderState", 19);
					break;
				default:
					map.put("orderStateName", "未知");
					map.put("orderState", 0);
					break;
				}
				map.put("carsName", customerOrder.getCarsName());
				map.put("paymentWay", customerOrder.getPaymentWay());
				map.put("systemUserName", customerOrder.getSystemUserName());				
				orderList.add(map);
			}
			}else {
				Map<String, Object> mapOrg = new HashMap<String,Object>();
				CustomerCustomerOrgVo customerOrgVo = customerUsersVo.getCustomerOrgVo();
				if(StringUtil.isNotEmpty(search.getPaymentWay())){
					if(StringUtil.isEmpty(customerOrgVo.getExpectWayId())) {
						continue;
					}
					if(!customerOrgVo.getExpectWayId().equals(search.getPaymentWay())) {
						continue;
					}
				}
				mapOrg.put("customerUsersOrgId", customerOrgVo.getCustomerUsersOrgId());
				mapOrg.put("orderState", "无购车订单");
				mapOrg.put("carsName", customerOrgVo.getIntentionCarInfo());
				mapOrg.put("paymentWay", customerOrgVo.getExpectWayId());
				mapOrg.put("systemUserName", customerOrgVo.getSystemUserName());
				mapOrg.put("timeOfAppointmentDate", DateUtil.format(customerOrgVo.getTimeOfAppointmentDate()));
				appointmentMap.put("mapOrg", mapOrg);
//				maps.add(TakeCareMapDate.cutNullMap(map));
			}
			mapOrder.put("appointment", appointmentMap);
			mapOrder.put("orderList", orderList);
			maps.add(mapOrder);
		}
//		allMap.put("page", search.getPage());
//		allMap.put("total", total);
//		allMap.put("rows", rows);
//		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}
	
	@Override
	public Result customerUserList(CustomerCustomerOrgSearch search, Result result,SystemUsers users) throws Exception {
		//收集当前用户组织及其下级数据
		Map<String,Object> params = new HashMap<String,Object>();
		
		//数据权限控制，因为数据权限只有一处，为了节省开发时间，直接写到此处，后续需要写到缓存里
		//提取出来销售组
		Map<String,Object> groupingMap = new HashMap<String,Object>();
		groupingMap.put("orgId", users.getOrgId());
		groupingMap.put("groupingName", "销售组");
		List<SystemGrouping> systemGroupings = systemGroupingDao.select(groupingMap);
		if(systemGroupings == null || systemGroupings.size() <= 0) {
			result.setError("贵司尚未进行 销售组 权限配置，请先配置数据权限");
			return result;
		}
		SystemGrouping grouping = systemGroupings.get(0);
		groupingMap.put("userId", users.getUsersId());
		groupingMap.put("groupingId", grouping.getGroupingId());
		List<SystemUserGrouping> groupings = systemUserGroupingDao.select(groupingMap);
		if(groupings == null || groupings.size() <= 0) {
			result.setError("你的权限分组信息未知，暂不能查看用户，请先让管理员配置你的权限");
			return result;
		}else {
			SystemUserGrouping userGrouping = groupings.get(0);
			if(!userGrouping.getOverManage()) {				
				params.put("systemUserId", users.getUsersId());
			}else {
				SystemGroupingVo systemGrouping = systemGroupingDao.selectByIdJoin(grouping.getGroupingId());
				Set<Integer> set = new HashSet<Integer>();
				for(SystemUserGrouping systemUserGrouping : systemGrouping.getSystemUserGroupingVos()) {
					set.add(systemUserGrouping.getUserId());
				}
				params.put("systemUserIds", set);
			}
		}
		
		params.put("orgCode", users.getOrgCode());
		if(StringUtil.isNotEmpty(search.getCustomerUsersSearch())) {
			params.put("customerUsersSearch", search.getCustomerUsersSearch());
		}
//		if(StringUtil.isNotEmpty(search.getPhoneNumber())) {
//			params.put("phoneSearch", search.getPhoneNumber());
//		}
		if(StringUtil.isNotEmpty(search.getPaymentWay())) {
			params.put("paymentWay", search.getPaymentWay());
		}
		//1.查询出来该门店的所有用户
		//从第几条开始
		params.put("sortField", true);
//		params.put("isPage", true);
//		params.put("offset", (search.getPage()-1)*search.getRows());
//		//返回几条
//		params.put("limit", search.getRows());
		List<CustomerCustomerOrgVo> customerOrgVos = customerCustomerOrgDao.select(params);
//		Map<String,Object> allMap = new HashMap<String, Object>();
//		Long total = customerCustomerOrgDao.getRowCount(params);
//		int rows = search.getRows();
		
		/**
		 * 去掉因为预约和实际下单不一致的期待购车方式，兼容预约和全部客户的期待购车方式，使两者可以不一致决解bug：
		 * 新增客户时选择分期，然后开单时选择全款，客户列表中按全款查找，找不到该单
		 */
		Set<Integer> customerUsersIds = new HashSet<Integer>();
		List<CustomerCustomerOrgVo> customerOrgVos2 = new ArrayList<CustomerCustomerOrgVo>();
		if(StringUtil.isNotEmpty(search.getPaymentWay())) {
			params.remove("paymentWay");
			customerOrgVos2 = customerCustomerOrgDao.select(params);
			for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos2) {
				customerUsersIds.add(customerOrgVo.getCustomerUsersId());
			}
		}else {
			for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
				customerUsersIds.add(customerOrgVo.getCustomerUsersId());
			}
		}
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		if(customerUsersIds.size() <= 0) {
//			allMap.put("page", search.getPage());
//			allMap.put("total", total);
//			allMap.put("rows", rows);
//			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
			return result;
		}
		//2.查询客户
		params.put("customerUsersIds", customerUsersIds);
		List<CustomerUsersVo> customerUsersVos = customerUsersDao.select(params);		
		//3.整理预约信息到客户列表
		
		/**
		 * 去掉因为预约和实际下单不一致的期待购车方式，兼容预约和全部客户的期待购车方式，使两者可以不一致决解bug：
		 * 新增客户时选择分期，然后开单时选择全款，客户列表中按全款查找，找不到该单
		 */
		if(StringUtil.isNotEmpty(search.getPaymentWay())) {
			for(CustomerUsersVo customerUsersVo : customerUsersVos) {
				for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos2) {
					if(customerUsersVo.getCustomerUsersId().equals(customerOrgVo.getCustomerUsersId())) {
						customerUsersVo.setCustomerOrgVo(customerOrgVo);
					}
				}
			}
			params.put("paymentWay", search.getPaymentWay());
		}else {
			for(CustomerUsersVo customerUsersVo : customerUsersVos) {
				for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
					if(customerUsersVo.getCustomerUsersId().equals(customerOrgVo.getCustomerUsersId())) {
						customerUsersVo.setCustomerOrgVo(customerOrgVo);
					}
				}
			}
		}
//		if(search.getBuyCarAlready()) {
////			params.put("buyCarAlready", search.getBuyCarAlready());
//			
//		}		
		
		if(StringUtil.isNotEmpty(search.getOrderStates())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orderStates = search.getOrderStates().split(GeneralConstant.SystemBoolean.SPLIT);
			for(String state : orderStates) {
				set.add(Integer.parseInt(state));
			}
			params.put("orderStates", set);
			search.setBuyCarAlready(true);
		}
		List<CustomerOrderVo> customerOrderVos = customerOrderDao.select(params);	
		//5.整合订单
		for(CustomerUsersVo customerUsersVo : customerUsersVos) {
			for(CustomerOrderVo customerOrderVo : customerOrderVos) {
				if(customerUsersVo.getCustomerUsersId().equals(customerOrderVo.getCustomerId())) {
					customerUsersVo.setCustomerOrderVo(customerOrderVo);
				}
			}
		}
		
//		Map<String,Object> allMap = new HashMap<String, Object>();
//		Long total = customerCustomerOrgDao.getRowCount(params);
//		int rows = search.getRows();
		for(CustomerUsersVo customerUsersVo : customerUsersVos) {
			if(customerUsersVo.getCustomerOrgVo() == null) {
				continue;
			}
			if(search.getBuyCarAlready() != null && search.getBuyCarAlready()) {
				if(customerUsersVo.getCustomerOrderVo() == null) {
					continue;
				}
			}
//			if(search.getBuyCarAlready() != null && search.getBuyCarAlready() == false) {
//				if(customerUsersVo.getCustomerOrderVo() != null) {
//					continue;
//				}
//			}
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("customerUsersName", customerUsersVo.getCustomerUsersName());
			map.put("customerUsersId", customerUsersVo.getCustomerUsersId());
			map.put("phoneNumber", customerUsersVo.getPhoneNumber());
			map.put("headPortrait", customerUsersVo.getHeadPortrait());			
			if(customerUsersVo.getCustomerOrderVo() != null) {
				CustomerOrder customerOrder = customerUsersVo.getCustomerOrderVo();
				map.put("customerOrderId", customerOrder.getCustomerOrderId());				
				switch (customerOrder.getCustomerOrderState()) {
				case 1:
					map.put("orderStateName", "待交定金");
					map.put("orderState", 1);
					break;
				case 3:
					map.put("orderStateName", "待银行审核");
					map.put("orderState", 3);
					break;
				case 4:
					map.put("orderStateName", "已被银行拒绝");
					map.put("orderState", 4);
					break;
				case 5:
					map.put("orderStateName", "等待车辆出库");
					map.put("orderState", 5);
					break;
				case 7:
					map.put("orderStateName", "等待加装精品");
					map.put("orderState", 7);
					break;
				case 9:
					map.put("orderStateName", "等待上牌");
					map.put("orderState", 9);
					break;
				case 11:
					map.put("orderStateName", "等待贴膜");
					map.put("orderState", 11);
					break;
				case 13:
					map.put("orderStateName", "等待交车");
					map.put("orderState", 13);
					break;
				case 15:
					map.put("orderStateName", "已人车合照");
					map.put("orderState", 15);
					break;
				case 17:
					map.put("orderStateName", "已完款，交车放行");
					map.put("orderState", 17);
					break;
				case 19:
					//新增需求，添加回访内容返回
					Map<String,Object> paramsTrack = new HashMap<String, Object>();
					paramsTrack.put("customerOrderId", customerOrder.getCustomerOrderId());
					List<CustomerOrderTrackVo> customerOrderTrackVos = customerOrderTrackDao.select(paramsTrack);
					for(CustomerOrderTrackVo trackVo : customerOrderTrackVos) {
						if(trackVo.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.orderVisit)) {
							map.put("visitContent", trackVo.getTrackContent());
							break;
						}
					}
					map.put("orderStateName", "已回访");
					map.put("orderState", 19);
					break;
				default:
					map.put("orderStateName", "未知");
					map.put("orderState", 0);
					break;
				}
				map.put("carsName", customerOrder.getCarsName());
				map.put("paymentWay", customerOrder.getPaymentWay());
				map.put("systemUserName", customerOrder.getSystemUserName());
			}else {
				CustomerCustomerOrgVo customerOrgVo = customerUsersVo.getCustomerOrgVo();
				if(StringUtil.isNotEmpty(search.getPaymentWay())){
					if(StringUtil.isEmpty(customerOrgVo.getExpectWayId())) {
						continue;
					}
					if(!customerOrgVo.getExpectWayId().equals(search.getPaymentWay())) {
						continue;
					}
				}
				map.put("customerUsersOrgId", customerOrgVo.getCustomerUsersOrgId());
				map.put("orderState", "无购车订单");
				map.put("carsName", customerOrgVo.getIntentionCarInfo());
				map.put("paymentWay", customerOrgVo.getExpectWayId());
				map.put("systemUserName", customerOrgVo.getSystemUserName());
				map.put("timeOfAppointmentDate", DateUtil.format(customerOrgVo.getTimeOfAppointmentDate()));
			}
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
//		allMap.put("page", search.getPage());
//		allMap.put("total", total);
//		allMap.put("rows", rows);
//		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}
	
	/**
	 * 要查询所有购车与非购车用户，连表查询会使数据缺失，故而全部分离查询再整合
	 */
	public Result customerUserList_old(CustomerCustomerOrgSearch search, Result result,SystemUsers users) throws Exception {
		//收集当前用户组织及其下级数据
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgCode", users.getOrgCode());
		if(StringUtil.isNotEmpty(search.getCustomerUsersSearch())) {
			params.put("customerUsersSearch", search.getCustomerUsersSearch());
		}
//		if(StringUtil.isNotEmpty(search.getPhoneNumber())) {
//			params.put("phoneSearch", search.getPhoneNumber());
//		}
		if(search.getBuyCarAlready() != null && search.getBuyCarAlready()) {
			params.put("buyCarAlready", search.getBuyCarAlready());
		}
		if(StringUtil.isNotEmpty(search.getPaymentWay())) {
			params.put("paymentWay", search.getPaymentWay());
		}
		if(StringUtil.isNotEmpty(search.getOrderStates())) {
			Set<Integer> set = new HashSet<Integer>();
			String[] orderStates = search.getOrderStates().split(GeneralConstant.SystemBoolean.SPLIT);
			for(String state : orderStates) {
				set.add(Integer.parseInt(state));
			}
			params.put("orderStates", set);
		}
		List<CustomerUsersVo> customerUsersVos = customerUsersDao.selectJoin(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
//		Map<String,Object> allMap = new HashMap<String, Object>();
//		Long total = customerCustomerOrgDao.getRowCount(params);
//		int rows = search.getRows();
		for(CustomerUsersVo customerUsersVo : customerUsersVos) {
			if(customerUsersVo.getCustomerOrgVos() == null || customerUsersVo.getCustomerOrgVos().size() <= 0) {
				continue;
			}
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("customerUsersName", customerUsersVo.getCustomerUsersName());
			map.put("customerUsersId", customerUsersVo.getCustomerUsersId());
			map.put("phoneNumber", customerUsersVo.getPhoneNumber());
			map.put("headPortrait", customerUsersVo.getHeadPortrait());
			if(customerUsersVo.getCustomerOrderVos() != null && customerUsersVo.getCustomerOrderVos().size() > 0) {
				CustomerOrder customerOrder = customerUsersVo.getCustomerOrderVos().get(0);
				switch (customerOrder.getCustomerOrderState()) {
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
				case 12:
					map.put("orderState", "等待交车");
					break;
				case 13:
					map.put("orderState", "已交车");
					break;
				case 15:
					map.put("orderState", "尾款已结算");
					break;
				case 17:
					map.put("orderState", "订单已完结");
					break;
				default:
					map.put("orderState", "未知");
					break;
				}
				map.put("carsName", customerOrder.getCarsName());
				map.put("paymentWay", customerOrder.getPaymentWay());
				map.put("systemUserName", customerOrder.getSystemUserName());
			}else {
				CustomerCustomerOrgVo customerOrgVo = customerUsersVo.getCustomerOrgVos().get(0);
				map.put("orderState", "无购车订单");
				map.put("carsName", customerOrgVo.getIntentionCarInfo());
				map.put("paymentWay", customerOrgVo.getExpectWayId());
				map.put("systemUserName", customerOrgVo.getSystemUserName());
			}
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
//		allMap.put("page", search.getPage());
//		allMap.put("total", total);
//		allMap.put("rows", rows);
//		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
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
	public Result orderStateCustomerList(CustomerCustomerOrgSearch search, Result result, SystemUsers users)
			throws Exception {
		//利用状态查询订单，根据订单筛选客户
		String state = "";
		//1.落定客户/2.贷款通过客户/3.待完款客户/4.待加装上牌客户/5.待提车客户列表
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isEmpty(search.getCustomerType())) {
			result.setError("请选择要查看的用户类型");
			return result;
		}
		switch(search.getCustomerType()) {
			case 1: state = "deposit";break;//GeneralConstant.CustomerOrderState.paymentOfADeposit;//落定用户，包括3和5
			case 2: state = "loanAudit";break;//GeneralConstant.CustomerOrderState.loanAudit;//落定用户，包括3和5
			case 3: state = "deliveryOfTheTail";break;//GeneralConstant.CustomerOrderState.loanAudit;//车辆已出库等待交付尾款
			case 4: state = "retrofitting";break;//GeneralConstant.CustomerOrderState.loanAudit;//等待加装上牌
			case 5: state = "deliver";break;//GeneralConstant.CustomerOrderState.loanAudit;//等待交车	
			default:
				state = "deposit";break;
		}
		params.put("state", state);
		if(StringUtil.isNotEmpty(search.getOrgId())) {
			params.put("orgId", search.getOrgId());
		}else {
			params.put("orgId", users.getOrgId());
			params.put("systemUserId", users.getUsersId());
		}
		params.put("orgCode", users.getOrgCode());
		params.put("isGroupBy", true);
		
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		List<CustomerOrderVo> customerOrders = customerOrderDao.select(params);
		if(customerOrders == null  || customerOrders.size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
			return result;
		}
		List<Integer> set = new ArrayList<Integer>();
		for(CustomerOrder customerOrder : customerOrders) {
			set.add(customerOrder.getCustomerId());
		}
		params.put("ids", set);
		List<CustomerUsersVo> customerUsersVos = customerUsersDao.select(params);		
		if(customerUsersVos == null || customerUsersVos.size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
			return result;
		}
		for(CustomerUsersVo customerUsersVo : customerUsersVos) {
			for(CustomerOrderVo customerOrderVo : customerOrders) {
				if(customerUsersVo.getCustomerUsersId().equals(customerOrderVo.getCustomerId())) {
					customerOrderVo.setCustomerUsersVo(customerUsersVo);
				}
			}
		}
		
		for(CustomerOrderVo customerOrder : customerOrders) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("customerUsersId", customerOrder.getCustomerId());
			map.put("customerUsersName", customerOrder.getCustomerName());
			map.put("phoneNumber", customerOrder.getCustomerPhoneNumber());
			map.put("carsName", customerOrder.getCarsName());
			map.put("paymentWay", customerOrder.getPaymentWay());
			map.put("isMortgage", customerOrder.getIsMortgage());
			if(customerOrder.getCustomerUsersVo() != null) {
				map.put("headPortrait", customerOrder.getCustomerUsersVo().getHeadPortrait());
			}
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}

	@Override
	public Result phoneCustomerSearchList(CustomerCustomerOrgSearch search, Result result, SystemUsers users)
			throws Exception {
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		if(StringUtil.isEmpty(search.getPhoneNumber())) {
			result.setOK(ResultCode.CODE_STATE_200, "", maps);
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		//从第几条开始
//		params.put("sortField", true);
//		params.put("isPage", true);
//		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
//		params.put("limit", search.getRows()); 
		params.put("phoneSearch", search.getPhoneNumber());
		List<CustomerUsers> customerUsers = customerUsersDao.select(params);//new ArrayList<CustomerUsers>();
		//根据门店筛选用户
		Set<Integer> set = new HashSet<Integer>();
		for(CustomerUsers customerUsers2 : customerUsers) {
			set.add(customerUsers2.getCustomerUsersId());
		}
		Map<String,Object> orgParams = new HashMap<String, Object>();
		if(set == null || set.size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "", maps);
			return result;
		}
		orgParams.put("customerUsersIds", set);
		orgParams.put("orgCode", users.getOrgCode());//兼容2级查看
		List<CustomerCustomerOrgVo> customerOrgVos = customerCustomerOrgDao.select(orgParams);
		if(customerOrgVos == null || customerOrgVos.size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "", maps);
			return result;
		}
		set.clear();
		if(!set.isEmpty()) {//清空set
			set = new HashSet<Integer>();
		}
		for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
			set.add(customerOrgVo.getCustomerUsersId());
		}
		for(int i=0;i<customerUsers.size();i++) {
			CustomerUsers customerUsers2 = customerUsers.get(i);
			if(set.add(customerUsers2.getCustomerUsersId())) {
				continue;
			}
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("customerUsersId", customerUsers2.getCustomerUsersId());
			map.put("customerUsersName", customerUsers2.getCustomerUsersName());
			map.put("phoneNumber", customerUsers2.getPhoneNumber());
			maps.add(map);
			if(i == 9) {//最多返回十个
				break;
			}
		}
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}

	@Override
	public Result addCustomerUsersr(CustomerUsersSearch search, Result result, SystemUsers users)
			throws Exception {
		CustomerUsersDTO customerUsersDTO = setThisCustomerUsers(search,result);//新建预约用户
		//收集当前用户组织下的第三级组织数据
		OrganizationVo organizationVo = organizationDao.selectById(users.getOrgId());
		if(organizationVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的组织身份不明确，无客户信息可以查看");
			return result;
		}
		if(!organizationVo.getOrgLevel().equals(GeneralConstant.Org.Level_3)) {
			result.setError(ResultCode.CODE_STATE_4005, "你不属于门店级别操作员，无权限进行此操作");
			return result;
		}
		if(!customerUsersDTO.getResult().isSuccess()) {
			return customerUsersDTO.getResult();
		}
		//对比销售顾问数据
//		if(search.getSystemUserId() == null) {
//			result.setError(ResultCode.CODE_STATE_4005, "请选择销售顾问");			
//			return result;
//		}
//		SystemUsers systemUsers = systemUsersDao.selectById(search.getSystemUserId());
//		if(systemUsers == null) {
//			result.setError(ResultCode.CODE_STATE_4005, "销售顾问信息错误");			
//			return result;
//		}
//		if(!users.getOrgId().equals(systemUsers.getOrgId())) {
//			result.setError(ResultCode.CODE_STATE_4005, "销售顾问："+systemUsers.getRealName()+"的信息访问不在你的权限范围内，请重新选择");			
//			return result;
//		}	
		
		//利用电话号查询用户唯一性，如果前后重复是同一个门店，辣么就拒绝录入，如果门店不同的话就新建---用户门店 这部分中间数据
		CustomerUsers customerUsers = customerUsersDao.selectByPhone(search.getPhoneNumber());
		//不管哪个门店，如果录入的手机号码和临时用户表的电话号匹配，就更新临时表进行绑定
		UsersTmpl usersTmpl = usersTmplDao.selectByPhone(search.getPhoneNumber());
		if(customerUsers != null) {//用户已存在，比较数据
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("customerUsersId", customerUsers.getCustomerUsersId());
			params.put("orgId", users.getOrgId());
			List<CustomerCustomerOrg> CustomerCustomerOrgVo = customerCustomerOrgDao.select(params);
			if(CustomerCustomerOrgVo == null || CustomerCustomerOrgVo.size() <= 0) {//门店不同
				CustomerCustomerOrg customerCustomerOrg = new CustomerCustomerOrg();				
				customerCustomerOrg = getCustomerCustomerOrg(customerCustomerOrg, search, customerUsers, organizationVo, users);
				customerCustomerOrg.setCustomerUsersId(customerUsersDTO.getCustomerUsers().getCustomerUsersId());
				if(customerCustomerOrgDao.insert(customerCustomerOrg)) {
					result.setOK(ResultCode.CODE_STATE_200, "操作成功",customerUsers.getCustomerUsersId());
				}else {
					result.setError(ResultCode.CODE_STATE_4005, "系统保存数据错误，请联系IT部");
				}
				if(usersTmpl!=null) {//顺手检测更新一下
					usersTmpl.setCustomerUsersId(customerUsers.getCustomerUsersId());
					usersTmpl.setPhoneNumber(customerUsers.getPhoneNumber());
					usersTmpl.setCustomerUsersName(search.getCustomerUsersName());
					usersTmplDao.updateById(usersTmpl);//绑定用户微信
				}	
			}else {//如果门店相同也让其修改 （旧需求）
				if(search.getAdvanceOrderId() == null) {
					CustomerCustomerOrg customerCustomerOrg = CustomerCustomerOrgVo.get(0);
					result.setError(ResultCode.CODE_STATE_4005, "电话号为："+search.getPhoneNumber()+"的用户已预约了店内销售:"+customerCustomerOrg.getSystemUserName()+"，请勿重复添加");
					return result;
				}else {
					result.setOK(ResultCode.CODE_STATE_200, "操作成功",customerUsers.getCustomerUsersId());
				}
				//新需求（2018 03 12）门店相同不再让修改，直接提示预约经理
//				CustomerCustomerOrg customerCustomerOrg = CustomerCustomerOrgVo.get(0);
//				customerCustomerOrg = getCustomerCustomerOrg(customerCustomerOrg, search, customerUsers, organizationVo, users);
//				if(customerCustomerOrgDao.updateById(customerCustomerOrg)) {
//					customerUsersDao.updateById(customerUsersDTO.getCustomerUsers());
//					result.setOK(ResultCode.CODE_STATE_200, "操作成功");
//				}else {
//					result.setError(ResultCode.CODE_STATE_4005, "系统保存数据错误，请联系IT部");
//				}
			}
		}else {//用户尚未存在就新建
			customerUsers = customerUsersDTO.getCustomerUsers();
			CustomerCustomerOrg customerCustomerOrg = new CustomerCustomerOrg();
			customerCustomerOrg = getCustomerCustomerOrg(customerCustomerOrg, search, customerUsers, organizationVo, users);			
			if(customerUsersDao.insert(customerUsersDTO.getCustomerUsers())) {				
				if(usersTmpl!=null) {
					usersTmpl.setCustomerUsersId(customerUsers.getCustomerUsersId());
					usersTmpl.setPhoneNumber(customerUsers.getPhoneNumber());
					usersTmpl.setCustomerUsersName(search.getCustomerUsersName());
					usersTmplDao.updateById(usersTmpl);//绑定用户微信
				}	
				customerCustomerOrg.setCustomerUsersId(customerUsersDTO.getCustomerUsers().getCustomerUsersId());
				customerCustomerOrgDao.insert(customerCustomerOrg);
				result.setOK(ResultCode.CODE_STATE_200, "操作成功",customerCustomerOrg.getCustomerUsersId());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "系统保存数据错误，请联系IT部");
			}
		}
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			CustomerRemarks customerRemarks = new CustomerRemarks();
			customerRemarks.setCreateDate(new Date());
			if(customerUsers == null) {
				return result;
			}
			customerRemarks.setCustomerId(customerUsers.getCustomerUsersId());
			customerRemarks.setCustomerName(customerUsers.getCustomerUsersName());
//			customerRemarks.setRemarksContent(search.getRemark());
			customerRemarks.setRemarksContent(search.getRemarks());
			customerRemarks.setSystemUserId(users.getUsersId());
			customerRemarks.setSystemUserName(users.getRealName());
			customerRemarks.setOrgId(users.getOrgId());
			customerRemarksDao.insertAndResultIT(customerRemarks, result);
		}
		return result;
	}
	
	public CustomerCustomerOrg getCustomerCustomerOrg(CustomerCustomerOrg customerCustomerOrg,CustomerUsersSearch search,CustomerUsers customerUsers,
			OrganizationVo organizationVo,SystemUsers users)throws Exception{
		customerCustomerOrg.setCreateDate(new Date());
		customerCustomerOrg.setCustomerUsersName(customerUsers.getCustomerUsersName());
		customerCustomerOrg.setPhoneNumber(customerUsers.getPhoneNumber());
		customerCustomerOrg.setOrgId(organizationVo.getOrgId());
		customerCustomerOrg.setOrgShortName(organizationVo.getShortName());
		customerCustomerOrg.setIsEdit(true);
		customerCustomerOrg.setIsNotBuy(false);
		customerCustomerOrg.setIsTrack(false);
		customerCustomerOrg.setRemarks(search.getRemark());
		if(StringUtil.isNotEmpty(search.getSystemUserId())) {
			SystemUsers systemUsers = systemUsersDao.selectById(search.getSystemUserId());
			customerCustomerOrg.setSystemUserId(systemUsers.getUsersId());
			customerCustomerOrg.setSystemUserName(systemUsers.getRealName());
			customerCustomerOrg.setSystemUserPhone(systemUsers.getPhoneNumber());
		}else {
			customerCustomerOrg.setSystemUserId(users.getUsersId());
			customerCustomerOrg.setSystemUserName(users.getRealName());
			customerCustomerOrg.setSystemUserPhone(users.getPhoneNumber());
		}
//		customerCustomerOrg.setIntentionCarInfo(search.getIntentionCarInfo());
		customerCustomerOrg.setCarPurchaseIntention(search.getCarPurchaseIntention());
		if(search.getExpectWayId() != null) {//购车方式已改，1全款 2分期
			customerCustomerOrg.setExpectWayId(search.getExpectWayId());
		}
//		if(search.getExpectWayId() != null) {
//			CarExpectWay carExpectWay = carExpectWayDao.selectById(search.getExpectWayId());
//			if(carExpectWay == null) {
//				throw new Exception("购车方式数据为空");
//			}
//			customerCustomerOrg.setExpectWayId(carExpectWay.getExpectWayId());
//			customerCustomerOrg.setExpectWayName(carExpectWay.getExpectWayName());
//		}
		if(StringUtil.isNotEmpty(search.getTimeOfAppointment())) {
			customerCustomerOrg.setAppointmentDate(DateUtil.format(search.getAppointmentDate(),"yyyy-MM-dd"));
			customerCustomerOrg.setTimeOfAppointment(search.getTimeOfAppointment());
			customerCustomerOrg.setTimeOfAppointmentDate(new Date());
		}
		customerCustomerOrg.setIsAppointment(true);//新建为预约
		customerCustomerOrg.setIntensity(search.getIntensity());
		customerCustomerOrg.setMakeSource(search.getMakeSource());
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			customerCustomerOrg.setRemarks(search.getRemarks());
		}
		
//		//把商城预订单的车辆塞进去当做用户的意向车辆
//		if (StringUtil.isNotEmpty(search.getAdvanceOrderId())) {
//			ShopAdvanceOrderVo advanceOrder = shopAdvanceOrderDao.selectByIdJoin(search.getAdvanceOrderId());
//			
//		}
		
		if (search.getIntentionCarId() != null && search.getCarsId() == null) {
			search.setCarsId(search.getIntentionCarId());
		}
		if(search.getCarsId() != null) {
			CarsVo cars = carsDao.selectById(search.getCarsId());
			customerCustomerOrg.setIntentionCarId(cars.getCarId());
			StringBuffer buffer = new StringBuffer();
			buffer.append(cars.getBrandName()).append(" ");
			buffer.append(cars.getFamilyName()).append(" ");
			buffer.append(cars.getYearPattern()).append("款").append(" ");
			buffer.append(cars.getPl()).append(" ");
			buffer.append(cars.getGearboxName()).append(cars.getStyleName());
			customerCustomerOrg.setIntentionCarInfo(buffer.toString());
		}
		return customerCustomerOrg;
	}
	
	public CustomerUsersDTO setThisCustomerUsers(CustomerUsersSearch search,Result result) {
		CustomerUsersDTO dto = new CustomerUsersDTO();
		dto.setAdvanceOrderId(search.getAdvanceOrderId());
		CustomerUsers customerUsers = null;
		if(StringUtil.isEmpty(search.getCustomerUsersId())) {
			customerUsers = new CustomerUsers();
			dto.setCustomerUsers(customerUsers);
		}else {
//			customerUsers = customerUsersDao.selectById(search.getCustomerUsersId());
//			if(customerUsers == null) {
//				result.setError(ResultCode.CODE_STATE_4005, "你选择的客户不存在或者已删除");			
//				return dto;
//			}
//			dto.setCustomerUsers(customerUsers);
			customerUsers = dto.getCustomerUsers();
		}
		dto.setResult(result);
		if(StringUtil.isEmpty(search.getCustomerUsersName())) {
			result.setError(ResultCode.CODE_STATE_4005, "请输入客户姓名");			
			return dto;
		}
		customerUsers.setCustomerUsersName(search.getCustomerUsersName());
		if(StringUtil.isEmpty(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4005, "请输入客户手机号");			
			return dto;
		}
		customerUsers.setPhoneNumber(search.getPhoneNumber());
		customerUsers.setAnnualIncome(search.getAnnualIncome());
		IdcardValidator validator = new IdcardValidator();
		if(search.getCardNo() != null && validator.isValidatedAllIdcard(search.getCardNo())) {
			customerUsers.setCardNo(search.getCardNo());
		}else if(search.getCardNo() != null) {
			result.setError(ResultCode.CODE_STATE_4005, "身份证号码不正确");			
			return dto;
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
		customerUsers.setCreateDate(new Date());
		result.setSuccess(true);
		return dto;
	}
	
	@Override
	public Result customerUsersrInfo(CustomerUsersSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getCustomerUsersId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体用户进行操作");			
			return result;
		}
		CustomerUsers customerUsers = customerUsersDao.selectById(search.getCustomerUsersId());
		if(customerUsers == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的用户不存在，请重新选择数据操作");			
			return result;
		}
		CustomerCustomerOrgVo customerOrgVo = null;
		
//		if(search.getCustomerUsersOrgId() != null) {
//			customerOrgVo = customerCustomerOrgDao.selectById(search.getCustomerUsersOrgId());
//		}
//		if(customerOrgVo == null) {
//			result.setError(ResultCode.CODE_STATE_4005, "用户-门店关系错误");			
//			return result;
//		}
		//查询客户购车
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgCode", users.getOrgCode());
		params.put("orgId", users.getOrgId());
		params.put("customerUsersId", customerUsers.getCustomerUsersId());
		List<CustomerCustomerOrgVo> customerOrgVos = customerCustomerOrgDao.select(params);
		if(customerOrgVos == null || customerOrgVos.size() <= 0) {
			result.setError(ResultCode.CODE_STATE_4005, "抱歉，你的门店里，查询不到此用户");			
			return result;
		}
		customerOrgVo = customerOrgVos.get(0);
		List<CustomerOrderVo> customerOrders = customerOrderDao.select(params);
		CustomerOrderVo order = null;
		if(customerOrders != null && customerOrders.size() > 0) {
			order = getTheOrder(customerOrders);
		}
		Map<String,Object> maps = new HashMap<String,Object>();		
		Map<String,Object> customerMap = new HashMap<String,Object>();
		Map<String,Object> appointmentMap = new HashMap<String,Object>();
		Map<String,Object> orderMap = new HashMap<String,Object>();
		maps.put("customerUsersName", customerUsers.getCustomerUsersName());
		maps.put("phoneNumber", customerUsers.getPhoneNumber());
		maps.put("headPortrait", customerUsers.getHeadPortrait());
		customerMap = getCustomerUsersrMap(customerUsers, customerOrgVo);
		
//		if(order == null) {//查询门店-客户基本资料
		appointmentMap.put("customerUsersOrgId", customerOrgVo.getCustomerUsersOrgId()); //ID
		appointmentMap.put("intentionCarInfo", customerOrgVo.getIntentionCarInfo()!= null?customerOrgVo.getIntentionCarInfo():""); //意向车辆信息
		appointmentMap.put("systemUserName", customerOrgVo.getSystemUserName()); //销售顾问			
		appointmentMap.put("systemUserId", customerOrgVo.getSystemUserId()); //销售顾问ID			
		appointmentMap.put("expectWayName", customerOrgVo.getExpectWayName()); //期待购车方式
		appointmentMap.put("expectWayId", customerOrgVo.getExpectWayId()); //期待购车方式
		appointmentMap.put("carPurchaseIntention", customerOrgVo.getCarPurchaseIntention() != null ? Integer.parseInt(customerOrgVo.getCarPurchaseIntention()):2);//购车时间
		appointmentMap.put("timeOfAppointment", customerOrgVo.getTimeOfAppointment());//预约时间
		appointmentMap.put("timeOfAppointmentDate", DateUtil.format(customerOrgVo.getTimeOfAppointmentDate()));//预约日期
		appointmentMap.put("appointmentDate", DateUtil.format(customerOrgVo.getAppointmentDate(),"yyyy-MM-dd"));//预约日期
//			maps.put("isOrder", 0);
//		}else {
		
		//20180309新增需求：返回银行拒绝贷款的备注(拒绝理由在订单追踪里)
		CustomerOrderTrackVo customerOrderTrackVo = null;
		
		if(order != null) {
			orderMap.put("carsName", order.getCarsName());//车
			orderMap.put("carsId", order.getCarsId());//车大类ID
			orderMap.put("systemUserName", order.getSystemUserName());//销售
			orderMap.put("systemUserId", order.getSystemUserId());//销售
			orderMap.put("paymentWay", order.getPaymentWay());//购车方式
			orderMap.put("customerOrderCode", order.getCustomerOrderCode());//购车方式
			orderMap.put("customerOrderId", order.getCustomerOrderId());//购车方式
			orderMap.put("customerOrderState", order.getCustomerOrderState());//购车方式
			if(GeneralConstant.CustomerOrderState.fullPayment.equals(order.getPaymentWay())) {
				orderMap.put("paymentWayInfo", "全款");//购车时间
			}else if(GeneralConstant.CustomerOrderState.byStages.equals(order.getPaymentWay())) {				
				orderMap.put("paymentWayInfo", "首付："+(Number.getCeil((order.getDownPayments()!= null ?order.getDownPayments().doubleValue():0.0d) / 10000d, 2)+"万")
						+"，贷款"+(Number.getCeil((order.getLoan()!= null ?order.getLoan().doubleValue():0.0d) / 10000d, 2)+"万")+",分期数"+order.getLoanPayBackStages());//购车时间
			}else {
				orderMap.put("paymentWayInfo", "未知");//购车方案
			}
			orderMap.put("bankAuditsImage", customerUsers.getBankAuditsImage());
			orderMap.put("bankAuditsvideo", customerUsers.getBankAuditsvideo());
			orderMap.put("loanBank", order.getLoanBank());
			
			//查询订单
			Map<String,Object> paramsTrack = new HashMap<String, Object>();
			paramsTrack.put("customerOrderId", order.getCustomerOrderId());
//			paramsTrack.put("customerOrderId", order.getCustomerOrderId());
			List<CustomerOrderTrackVo> customerOrderTrackVos = customerOrderTrackDao.select(paramsTrack);
			CustomerOrderTrackVo orderTrackVo = customerOrderTrackVos.get(0);
			for(CustomerOrderTrackVo trackVo : customerOrderTrackVos) {
				if(trackVo.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.notPassThrough)) {
					customerOrderTrackVo = trackVo;//提取记录银行拒绝的订单跟踪记录
				}
			}
			orderMap.put("trackContent", orderTrackVo.getTrackContent());
			orderMap.put("createDate", DateUtil.format(orderTrackVo.getCreateDate()));
			maps.put("isOrder", 1);
		}else {
			maps.put("isOrder", 0);
		}	
		//查询客户备注
		Map<String,Object> remarksMap = getCustomerRemarks(customerUsers,users);
		maps.put("appointmentMap", TakeCareMapDate.cutNullMap(appointmentMap));
		maps.put("orderMap", TakeCareMapDate.cutNullMap(orderMap));
		maps.put("customerMap", TakeCareMapDate.cutNullMap(customerMap));
		maps.put("remarksMap", TakeCareMapDate.cutNullMap(remarksMap));	
		
		//银行拒绝备注
		if(customerOrderTrackVo != null) {
			maps.put("overLoanRefuse", 1);
			maps.put("loanRefuseRemarks", customerOrderTrackVo.getTrackContent());
		}else {
			maps.put("overLoanRefuse", 0);
			maps.put("loanRefuseRemarks", "");
		}
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}
	
	private Map<String, Object> getCustomerRemarks(CustomerUsers customerUsers, SystemUsers users) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sortField", true);
		params.put("customerId", customerUsers.getCustomerUsersId());
		params.put("orgId", users.getOrgId());
		List<CustomerRemarksVo> remarksVos = customerRemarksDao.select(params);
		Map<String,Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CustomerRemarksVo remarksVo : remarksVos) {
			Map<String,Object> remarksMap = new HashMap<String, Object>();
			remarksMap.put("createDate", DateUtil.format(remarksVo.getCreateDate()));
			remarksMap.put("remarksContent", remarksVo.getRemarksContent());
			remarksMap.put("remarksId", remarksVo.getRemarksId());
			remarksMap.put("systemUserId", remarksVo.getSystemUserId());
			remarksMap.put("systemUserName", remarksVo.getSystemUserName());
			remarksMap.put("customerUsersId", remarksVo.getCustomerId());
			maps.add(TakeCareMapDate.cutNullMap(remarksMap));
		}
		map.put("list", maps);
		return map;
	}
	
	public CustomerOrderVo getTheOrder(List<CustomerOrderVo> orderVos) {
		/**
		 * 客户预约成功调整预约成功页面
		客户落定成功后进入小程序显示订单跟踪页面
			- 订单状态
				开单
				落定
				银行贷款审批(全款跳过)
				车辆出库
				完款
				加装／上牌
			- 显示时间最后的购车订单对应状态
			- 同时在不同地方买车的土豪不考虑
			- 订单跟踪状态下方显示该订单对应销售人员
		客户交付车辆成功后进入小程序显示订单完成界面
		 */
		if(orderVos.size() == 1) {
			return orderVos.get(0);
		}
		for(CustomerOrderVo orderVo : orderVos) {
			if(orderVo.getCustomerOrderState() < GeneralConstant.CustomerOrderState.deliver) {
				return orderVo;
			}
		}
		//如果木有正在进行的订单，则返回最后一个订单
		return orderVos.get(0);
	}

	@Override
	public Result addCustomerRemarks(CustomerCustomerOrgSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(search.getRemarksContent())) {
			result.setError(ResultCode.CODE_STATE_4005, "请输入备注内容");
			return result;
		}
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
		return result;
//		return customerRemarksDao.insertAndResultIT(customerRemarks, result);
	}

	@Override
	public Result addBankAudits(CustomerUsersSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getCustomerUsersId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体用户进行操作");			
			return result;
		}
		CustomerUsers customerUsers = customerUsersDao.selectById(search.getCustomerUsersId());
		if(customerUsers == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的用户不存在，请重新选择数据操作");			
			return result;
		}
		//收集当前用户组织下的第三级组织数据
		OrganizationVo organizationVo = organizationDao.selectById(users.getOrgId());
		if(organizationVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的组织身份不明确，无客户信息可以查看");
			return result;
		}
		if(!organizationVo.getOrgLevel().equals(GeneralConstant.Org.Level_3)) {
			result.setError(ResultCode.CODE_STATE_4005, "你不属于门店级别操作员，无权限进行此操作");
			return result;
		}
		if(StringUtil.isEmpty(search.getBankAuditsImage())) {
			result.setError(ResultCode.CODE_STATE_4005, "请上传资料图片");			
			return result;
		}
		if(StringUtil.isEmpty(search.getBankAuditsvideo())) {
			result.setError(ResultCode.CODE_STATE_4005, "请上传签名视频");			
			return result;
		}
		customerUsers.setBankAuditsImage(search.getBankAuditsImage());
		customerUsers.setBankAuditsvideo(search.getBankAuditsvideo());
		return customerUsersDao.updateByIdAndResult(customerUsers, result);
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
		customerOrgVos.setIsAppointment(false);
		return customerCustomerOrgDao.updateByIdAndResult(customerOrgVos, result);
	}

	@Override
	public Result createOrderBefor(CustomerCustomerOrgSearch search, Result result, SystemUsers users)
			throws Exception {
		if(search.getCustomerUsersId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体用户进行操作");			
			return result;
		}
		Map<String, Object> params =  new HashMap<String,Object>();
		params.put("customerUsersId", search.getCustomerUsersId());
		params.put("orgId", users.getOrgId());
		List<CustomerCustomerOrg> customerCustomerOrgs = customerCustomerOrgDao.select(params);
		Map<String, Object> map =  new HashMap<String,Object>();
		if(customerCustomerOrgs == null || customerCustomerOrgs.size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "", map);
			return result;
		}
		CustomerUsers customerUsers = customerUsersDao.selectById(search.getCustomerUsersId());
		if(customerUsers == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的用户不存在或者已被删除，请选择有效用户进行操作");			
			return result;
		}
		CustomerCustomerOrg customerCustomerOrg = customerCustomerOrgs.get(0);
		map.put("customerUsersName", customerCustomerOrg.getCustomerUsersName());
		map.put("customerUsersId", customerCustomerOrg.getCustomerUsersId());
		map.put("customerUserCard", customerUsers.getCardNo());
		map.put("carsId", customerCustomerOrg.getIntentionCarId());
		Cars cars = carsDao.selectById(customerCustomerOrg.getIntentionCarId());
		map.put("carsName", customerCustomerOrg.getIntentionCarInfo());
		map.put("guidingPrice", cars.getPrice());
		map.put("brandId", cars.getBrandId());
		map.put("familyId", cars.getFamilyId());
		map.put("systemUserId", customerCustomerOrg.getSystemUserId());
		map.put("systemUserName", customerCustomerOrg.getSystemUserName());
		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}
	
	//客户资料
	public Map<String, Object> getCustomerUsersrMap(CustomerUsers customerUsers,CustomerCustomerOrgVo customerOrgVo){
		Map<String,Object> map = new HashMap<String,Object>();
//		Map<String,Object> maps = new HashMap<String,Object>();
		map.put("customerUsersName", customerUsers.getCustomerUsersName());
		map.put("customerUsersId", customerUsers.getCustomerUsersId());
		map.put("phoneNumber", customerUsers.getPhoneNumber());
		map.put("agentGender", customerUsers.getAgentGender());//1女 2男
		map.put("annualIncome", customerUsers.getAnnualIncome());
		map.put("cardNo", customerUsers.getCardNo());//身份证号
		map.put("incomeSource", customerUsers.getIncomeSource());
		map.put("maritalStatus", customerUsers.getMaritalStatus());//1 已婚 2未婚
		map.put("housingSource", customerUsers.getHousingSource());//住房来源
		if(customerUsers.getIsHasDriversLicense() != null && customerUsers.getIsHasDriversLicense()) {
			map.put("isHasDriversLicense", 1);//驾驶证
		}else {
			map.put("isHasDriversLicense", 0);//驾驶证
		}
		map.put("email", customerUsers.getEmail());
		map.put("education", customerUsers.getEducation());//学历
		map.put("address", customerUsers.getAddress());//地址
		map.put("emergencyAContact", customerUsers.getEmergencyAContact());//紧急联系人A
		map.put("emergencyBContact", customerUsers.getEmergencyBContact());//紧急联系人B
		map.put("emergencyARelationship", customerUsers.getEmergencyARelationship());//紧急联系人A关系
		map.put("emergencyBRelationship", customerUsers.getEmergencyBRelationship());//紧急联系人B关系
		map.put("emergencyAPhone", customerUsers.getEmergencyAPhone());//紧急联系人A电话
		map.put("emergencyBPhone", customerUsers.getEmergencyBPhone());//紧急联系人B电话
		map.put("workUnit", customerUsers.getWorkUnit());//工作单位
		map.put("annualIncomeAfterTax", customerUsers.getAnnualIncomeAfterTax());//税后年收入
		map.put("workingPlace", customerUsers.getWorkingPlace());//工作地点
		map.put("entryTime", customerUsers.getEntryTime());//入职时间
		map.put("position", customerUsers.getPosition());//职位
		map.put("companyTelephone", customerUsers.getCompanyTelephone());//公司联系电话
		map.put("headPortrait", customerUsers.getHeadPortrait());//公司联系电话
//		maps.put("customerUsersrInfo", TakeCareMapDate.cutNullMap(map));
		return TakeCareMapDate.cutNullMap(map);
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
	public Result myCustomerOrderList(CustomerCustomerOrgSearch search, Result result, SystemUsers users)
			throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("customerUsersId", search.getCustomerUsersId());
		List<CustomerOrderVo> customerOrderVos = customerOrderDao.select(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", customerOrderVos);
		return new Result(returnMap);
	}
}
