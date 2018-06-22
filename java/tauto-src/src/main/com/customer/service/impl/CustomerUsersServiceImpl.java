package main.com.customer.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.car.dao.dao.CarExpectWayDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.po.CarExpectWay;
import main.com.car.dao.vo.CarsVo;
import main.com.customer.dao.dao.CustomerCarDao;
import main.com.customer.dao.dao.CustomerCustomerOrgDao;
import main.com.customer.dao.dao.CustomerOrderDao;
import main.com.customer.dao.dao.CustomerOrderInPayDao;
import main.com.customer.dao.dao.CustomerOrderTrackDao;
import main.com.customer.dao.dao.CustomerRemarksDao;
import main.com.customer.dao.dao.CustomerUsersDao;
import main.com.customer.dao.dao.UsersTmplDao;
import main.com.customer.dao.po.CustomerCar;
import main.com.customer.dao.po.CustomerCustomerOrg;
import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.po.CustomerRemarks;
import main.com.customer.dao.po.CustomerUsers;
import main.com.customer.dao.po.CustomerUsersDTO;
import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.customer.dao.search.CustomerUsersSearch;
import main.com.customer.dao.vo.CustomerCustomerOrgVo;
import main.com.customer.dao.vo.CustomerOrderInPayVo;
import main.com.customer.dao.vo.CustomerOrderTrackVo;
import main.com.customer.dao.vo.CustomerOrderVo;
import main.com.customer.dao.vo.CustomerRemarksVo;
import main.com.customer.service.CustomerUsersService;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.vo.StockCarVo;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.vo.OrganizationVo;
import main.com.system.service.OrganizationService;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.IdcardValidator;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class CustomerUsersServiceImpl extends BaseServiceImpl<CustomerUsers> implements CustomerUsersService{

	@Autowired
	CustomerUsersDao customerUsersDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	CustomerCustomerOrgDao customerCustomerOrgDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	CarExpectWayDao carExpectWayDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CustomerOrderDao customerOrderDao;
	
	@Autowired
	CustomerOrderTrackDao customerOrderTrackDao;
	
	@Autowired
	StockCarDao stockCarDao;
	
	@Autowired
	CustomerRemarksDao customerRemarksDao;
	
	@Autowired
	CustomerCarDao customerCarDao;
	
	@Autowired
	UsersTmplDao usersTmplDao;
	
	@Autowired
	CustomerOrderInPayDao customerOrderInPayDao;
	
	@Override
	protected BaseDao<CustomerUsers> getBaseDao() {
		return customerUsersDao;
	}

	public Result addCustomerUsers_old(CustomerUsersSearch search, Result result,SystemUsers users) throws Exception {
		CustomerUsersDTO customerUsersDTO = setThisCustomerUsers(search,result);//紧新建不预约
		if(!customerUsersDTO.getResult().isSuccess()) {
			return customerUsersDTO.getResult();
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
		//利用电话号查询用户唯一性，如果前后重复是同一个门店，辣么就拒绝录入，如果门店不同的话就新建---用户门店 这部分中间数据
		CustomerUsers customerUsers = customerUsersDao.selectByPhone(search.getPhoneNumber());
		if(customerUsers != null) {
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("customerUsersId", customerUsers.getCustomerUsersId());
			params.put("orgId", users.getOrgId());
			List<CustomerCustomerOrg> CustomerCustomerOrgVo = customerCustomerOrgDao.select(params);
			if(CustomerCustomerOrgVo == null || CustomerCustomerOrgVo.size() <= 0) {//门店不同
				CustomerCustomerOrg customerCustomerOrg = new CustomerCustomerOrg();
				customerCustomerOrg.setCreateDate(new Date());
				customerCustomerOrg.setCustomerUsersName(customerUsersDTO.getCustomerUsers().getCustomerUsersName());
				customerCustomerOrg.setPhoneNumber(customerUsersDTO.getCustomerUsers().getPhoneNumber());
				customerCustomerOrg.setOrgId(organizationVo.getOrgId());
				customerCustomerOrg.setOrgShortName(organizationVo.getShortName());
				customerCustomerOrg.setIsEdit(true);
				customerCustomerOrg.setRemarks(search.getRemarks());
				customerCustomerOrg.setSystemUserId(users.getUsersId());
				customerCustomerOrg.setSystemUserName(users.getRealName());
				customerCustomerOrg.setSystemUserPhone(users.getPhoneNumber());
				customerCustomerOrg.setCustomerUsersId(customerUsers.getCustomerUsersId());
				if(customerCustomerOrgDao.insert(customerCustomerOrg)) {
					result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				}else {
					result.setError(ResultCode.CODE_STATE_4005, "系统保存数据错误，请联系IT部");
				}
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "电话号为："+search.getPhoneNumber()+"的用户已存在，请勿重复添加");
			}
		}else {
			CustomerCustomerOrg customerCustomerOrg = new CustomerCustomerOrg();
			customerCustomerOrg.setCreateDate(new Date());
			customerCustomerOrg.setCustomerUsersName(customerUsersDTO.getCustomerUsers().getCustomerUsersName());
			customerCustomerOrg.setPhoneNumber(customerUsersDTO.getCustomerUsers().getPhoneNumber());
			customerCustomerOrg.setOrgId(organizationVo.getOrgId());
			customerCustomerOrg.setOrgShortName(organizationVo.getShortName());
			customerCustomerOrg.setIsEdit(true);
			customerCustomerOrg.setRemarks(search.getRemarks());
			customerCustomerOrg.setSystemUserId(users.getUsersId());
			customerCustomerOrg.setSystemUserName(users.getRealName());
			customerCustomerOrg.setSystemUserPhone(users.getPhoneNumber());
			if(customerUsersDao.insert(customerUsersDTO.getCustomerUsers())) {
				customerCustomerOrg.setCustomerUsersId(customerUsersDTO.getCustomerUsers().getCustomerUsersId());
				customerCustomerOrgDao.insert(customerCustomerOrg);
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "系统保存数据错误，请联系IT部");
			}
		}
		return result;
	}
	
	@Override
	public Result addCustomerUsers(CustomerUsersSearch search, Result result,SystemUsers users) throws Exception {
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
		if(search.getSystemUserId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择销售顾问");			
			return result;
		}
		SystemUsers systemUsers = systemUsersDao.selectById(search.getSystemUserId());
		if(systemUsers == null) {
			result.setError(ResultCode.CODE_STATE_4005, "销售顾问信息错误");			
			return result;
		}
		if(!users.getOrgId().equals(systemUsers.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "销售顾问："+systemUsers.getRealName()+"的信息访问不在你的权限范围内，请重新选择");			
			return result;
		}	
		
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
				customerCustomerOrg = getCustomerCustomerOrg(customerCustomerOrg, search, customerUsers, organizationVo, systemUsers);
				customerCustomerOrg.setCustomerUsersId(customerUsersDTO.getCustomerUsers().getCustomerUsersId());
				if(customerCustomerOrgDao.insert(customerCustomerOrg)) {
					result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				}else {
					result.setError(ResultCode.CODE_STATE_4005, "系统保存数据错误，请联系IT部");
				}
				if(usersTmpl!=null) {//顺手检测更新一下
					usersTmpl.setCustomerUsersId(customerUsers.getCustomerUsersId());
					usersTmpl.setPhoneNumber(customerUsers.getPhoneNumber());
					usersTmpl.setCustomerUsersName(search.getCustomerUsersName());
					usersTmplDao.updateById(usersTmpl);//绑定用户微信
				}	
			}else {//如果门店相同也让其修改
//				result.setError(ResultCode.CODE_STATE_4005, "电话号为："+search.getPhoneNumber()+"的用户已存在，请勿重复添加");
				CustomerCustomerOrg customerCustomerOrg = CustomerCustomerOrgVo.get(0);
				customerCustomerOrg = getCustomerCustomerOrg(customerCustomerOrg, search, customerUsers, organizationVo, systemUsers);
				if(customerCustomerOrgDao.updateById(customerCustomerOrg)) {
					customerUsersDao.updateById(customerUsersDTO.getCustomerUsers());
					result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				}else {
					result.setError(ResultCode.CODE_STATE_4005, "系统保存数据错误，请联系IT部");
				}
			}
		}else {//用户尚未存在就新建
			customerUsers = customerUsersDTO.getCustomerUsers();
			CustomerCustomerOrg customerCustomerOrg = new CustomerCustomerOrg();
			customerCustomerOrg = getCustomerCustomerOrg(customerCustomerOrg, search, customerUsers, organizationVo, systemUsers);			
			if(customerUsersDao.insert(customerUsersDTO.getCustomerUsers())) {				
				if(usersTmpl!=null) {
					usersTmpl.setCustomerUsersId(customerUsers.getCustomerUsersId());
					usersTmpl.setPhoneNumber(customerUsers.getPhoneNumber());
					usersTmpl.setCustomerUsersName(search.getCustomerUsersName());
					usersTmplDao.updateById(usersTmpl);//绑定用户微信
				}	
				customerCustomerOrg.setCustomerUsersId(customerUsers.getCustomerUsersId());
				customerCustomerOrgDao.insert(customerCustomerOrg);
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "系统保存数据错误，请联系IT部");
			}
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
		customerCustomerOrg.setSystemUserId(users.getUsersId());
		customerCustomerOrg.setSystemUserName(users.getRealName());
		customerCustomerOrg.setSystemUserPhone(users.getPhoneNumber());
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
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			customerCustomerOrg.setRemarks(search.getRemarks());
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
		CustomerUsers customerUsers = null;
		if(dto.getCustomerUsers() == null) {
			customerUsers = new CustomerUsers();
			dto.setCustomerUsers(customerUsers);
		}else {
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
		if(search.getCustomerUsersOrgId() != null) {
			customerOrgVo = customerCustomerOrgDao.selectById(search.getCustomerUsersOrgId());
		}
		if(customerOrgVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "用户-门店关系错误");			
			return result;
		}
		Map<String,Object> maps = new HashMap<String,Object>();
		//查询门店-客户基本资料
		Map<String,Object> userMap = getCustomerUsersrMap(customerUsers,customerOrgVo);
		maps.put("userMap", userMap);
		Map<String,Object> customerOrgMap = new HashMap<String,Object>();
		if(customerOrgVo != null) {
			customerOrgMap.put("customerUsersOrgId", customerOrgVo.getCustomerUsersOrgId()); //ID
			customerOrgMap.put("intentionCarInfo", customerOrgVo.getIntentionCarInfo()!= null?customerOrgVo.getIntentionCarInfo():""); //意向车辆信息
			customerOrgMap.put("systemUserName", customerOrgVo.getSystemUserName()); //销售顾问			
			customerOrgMap.put("systemUserId", customerOrgVo.getSystemUserId()); //销售顾问
			if(customerOrgVo.getExpectWayId() != null) {
				if(customerOrgVo.getExpectWayId().equals(GeneralConstant.CustomerOrderState.byStages)) {
					customerOrgMap.put("expectWayName", "贷款购车"); //期待购车方式	
				}else {
					customerOrgMap.put("expectWayName", "全款购车"); //期待购车方式	
				}	
			}else {
				customerOrgMap.put("expectWayName", "未知"); //期待购车方式	
			}
		}
		maps.put("customerOrgMap", TakeCareMapDate.cutNullMap(customerOrgMap));
		//查询客户购车
		Map<String,Object> orderMap = getCustomerOderMap(customerUsers,users,search);
		maps.put("orderMap", TakeCareMapDate.cutNullMap(orderMap));
		//查询客户备注
		Map<String,Object> remarksMap = getCustomerRemarks(customerUsers,users);
		maps.put("remarksMap", TakeCareMapDate.cutNullMap(remarksMap));
		//车辆资料
		Map<String,Object> customerCarMap = getCustomerCar(customerUsers,search.getOrderId());
		if(customerCarMap != null) {
			if(orderMap != null && orderMap.containsKey("extractCarImage")) {
				customerCarMap.put("extractCarImage", orderMap.get("extractCarImage"));
			}else {
				customerCarMap.put("extractCarImage", "");
			}
		}
		maps.put("customerCarMap", TakeCareMapDate.cutNullMap(customerCarMap));
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}
	
	private Map<String, Object> getCustomerCar(CustomerUsers customerUsers,Integer customerOrderId) {
		if(StringUtil.isEmpty(customerOrderId)) {
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sortField", true);
		params.put("customerUserId", customerUsers.getCustomerUsersId());
		params.put("customerOrderId", customerOrderId);
		List<CustomerCar> customerCars = customerCarDao.select(params);
		if(customerCars == null || customerCars.size() <= 0) {
			return null;
		}
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CustomerCar customerCar : customerCars) {
			Map<String,Object> customerCarMap = new HashMap<String, Object>();
			customerCarMap.put("colourId", customerCar.getColourId());
			customerCarMap.put("customerCarId", customerCar.getCustomerCarId());
			customerCarMap.put("colourName", customerCar.getColourName());
			customerCarMap.put("carsId", customerCar.getCarsId());
			customerCarMap.put("stockCarName", customerCar.getStockCarName());
			customerCarMap.put("engineNumber", customerCar.getEngineNumber());
			customerCarMap.put("give", customerCar.getGive());
			customerCarMap.put("frameNumber", customerCar.getFrameNumber());
			customerCarMap.put("engineNumber", customerCar.getEngineNumber());
			customerCarMap.put("licensePlateNumber", customerCar.getLicensePlateNumber());
			customerCarMap.put("transactionPrice", customerCar.getTransactionPrice());
			customerCarMap.put("carPurchasePlan", customerCar.getCarPurchasePlan());
			customerCarMap.put("loanScheme", customerCar.getLoanScheme());
			customerCarMap.put("afterSaleSupport", customerCar.getAfterSaleSupport());
			customerCarMap.put("purchaseTax", customerCar.getPurchaseTax());
			customerCarMap.put("exciseTax", customerCar.getExciseTax());
			customerCarMap.put("vehicleAndVesselTax", customerCar.getVehicleAndVesselTax());
			customerCarMap.put("premium", customerCar.getPremium());
			customerCarMap.put("compulsoryInsurance", customerCar.getCompulsoryInsurance());
			customerCarMap.put("thirdPartyLiabilityInsurance", customerCar.getThirdPartyLiabilityInsurance());
			customerCarMap.put("vehicleLossInsurance", customerCar.getVehicleLossInsurance());
			customerCarMap.put("riskOfGlassBreakage", customerCar.getRiskOfGlassBreakage());
			customerCarMap.put("selfIgnitionLossInsurance", customerCar.getSelfIgnitionLossInsurance());
			customerCarMap.put("exemptionFromSpecialContract", customerCar.getExemptionFromSpecialContract());
			customerCarMap.put("noLiabilityInsurance", customerCar.getNoLiabilityInsurance());
			customerCarMap.put("personnelLiabilityInsurance", customerCar.getPersonnelLiabilityInsurance());
			customerCarMap.put("scratchRisk", customerCar.getScratchRisk());
//			customerCarMap.put("images", customerCar.get);
			maps.add(TakeCareMapDate.cutNullMap(customerCarMap));
		}
		map.put("list", maps);
		return map;
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

	private Map<String, Object> getCustomerOderMap(CustomerUsers customerUsers, SystemUsers users,CustomerUsersSearch search)throws Exception{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgCode", users.getOrgCode());
		params.put("orgId", users.getOrgId());
		params.put("customerUsersId", customerUsers.getCustomerUsersId());
		params.put("sortField", true);
		List<CustomerOrderVo> customerOrders = customerOrderDao.select(params);
		Map<String,Object> map = new HashMap<String,Object>();
		if(customerOrders == null || customerOrders.size() <= 0) {
			return null;
		}
		CustomerOrderVo order = getTheOrder(customerOrders);
		
		Double amount = 0.0d;
		Map<String,Object> paramsInpay = new HashMap<String,Object>();
		paramsInpay.put("customerOrderId", order.getCustomerOrderId());
		List<CustomerOrderInPayVo> orderInPayVos = customerOrderInPayDao.select(paramsInpay);
		if(orderInPayVos != null && orderInPayVos.size() > 0) {
			for(CustomerOrderInPayVo orderInPayVo : orderInPayVos) {
				if(orderInPayVo.getAmount() == null) {
					continue;
				}
				amount += orderInPayVo.getAmount().doubleValue();
			}
		}
		if(GeneralConstant.CustomerOrderState.byStages.equals(order.getPaymentWay())) {
			map.put("amount", order.getDownPayments().doubleValue() - amount);
			map.put("totalAmount", order.getDownPayments().doubleValue());
		}else {
			map.put("amount", order.getAmount().doubleValue() - amount);
			map.put("totalAmount", order.getAmount().doubleValue());
		}
		map.put("orderState", order.getCustomerOrderState());
		map.put("loanBank", order.getLoanBank());
		map.put("paymentWay", order.getPaymentWay());
		map.put("depositPrice", order.getDepositPrice());
		map.put("customerOrderId", order.getCustomerOrderId());
		map.put("orgName", order.getOrgName());
		map.put("extractCarImage", order.getExtractCarImage());
		List<Map<String, Object>> mapTrack = orderTrack(order);
		search.setOrderId(order.getCustomerOrderId());
		map.put("list", mapTrack);
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
	
	
	public List<Map<String, Object>> orderTrack(CustomerOrderVo orderVo) throws Exception {
		//查询订单
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("customerOrderId", orderVo.getCustomerOrderId());
		params.put("sortField", true);
		List<CustomerOrderTrackVo> customerOrderTrackVos = customerOrderTrackDao.select(params);
		String image = "";
		if(StringUtil.isNotEmpty(orderVo.getStockCarId())) {
			StockCarVo stockCar = stockCarDao.selectByIdAtAll(orderVo.getStockCarId());
			image = stockCar.getStockCarImages();
		}
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CustomerOrderTrackVo orderTrackVo : customerOrderTrackVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("createDate", main.com.utils.DateUtil.format(orderTrackVo.getCreateDate()));
			map.put("trackContent", orderTrackVo.getTrackContent());
			if(GeneralConstant.CustomerOrderState.deliveryOfTheTail.equals(orderTrackVo.getCustomerOrderState())) {
				map.put("image", image);
			}else {
				map.put("image", "");
			}
			maps.add(map);
		}
		return maps;
	}

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
}
