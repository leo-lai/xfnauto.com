package main.com.weixinApp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiniu.util.StringUtils;

import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.po.Cars;
import main.com.customer.dao.dao.CustomerCustomerOrgDao;
import main.com.customer.dao.dao.CustomerUsersDao;
import main.com.customer.dao.dao.UsersTmplDao;
import main.com.customer.dao.po.CustomerCustomerOrg;
import main.com.customer.dao.po.CustomerUsers;
import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.dao.search.UsersTmplSearch;
import main.com.customer.dao.vo.CustomerCustomerOrgVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.search.OrganizationSearch;
import main.com.system.dao.vo.OrganizationVo;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.weixinApp.service.AppGeneralizeService;

@Service
public class AppGeneralizeServiceImpl extends BaseServiceImpl<Organization>implements AppGeneralizeService{

	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CustomerUsersDao customerUsersDao;
	
	@Autowired
	UsersTmplDao usersTmplDao;
	
	@Autowired
	CustomerCustomerOrgDao customerCustomerOrgDao;
	
	@Override
	protected BaseDao<Organization> getBaseDao() {
		return organizationDao;
	}

	@Override
	public Result organizationList(OrganizationSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgLevelIn", GeneralConstant.Org.Level_3);
		params.put("orgTypeElse", GeneralConstant.Org.Type_4);
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		List<OrganizationVo> organizations = organizationDao.select(params);
		for(OrganizationVo organizationVo : organizations) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("imageUrl", organizationVo.getImageUrl());
			map.put("shortName", organizationVo.getShortName());
			map.put("address", organizationVo.getProvinceName() + organizationVo.getCityName() + organizationVo.getAreaName() + organizationVo.getAddress());
			map.put("orgId", organizationVo.getOrgId());
			map.put("longitude", organizationVo.getLongitude());
			map.put("latitude", organizationVo.getLatitude());
			map.put("telPhone", organizationVo.getTelePhone());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}

	@Override
	public Result bespeak(UsersTmplSearch search, Result result, UsersTmpl users) throws Exception {
		// 输入电话号码进行绑定
		if (StringUtil.isEmpty(search.getCustomerUsersName())) {
			result.setError(ResultCode.CODE_STATE_4016, "请输入姓名");
			return result;
		}
		if (StringUtil.isEmpty(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4016, "请输入电话号");
			return result;
		} else if (!StringUtil.isNumeric(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4016, "电话号码格式错误");
			return result;
		}
		if (StringUtil.isEmpty(search.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4016, "请选择门店");
			return result;
		}
		Cars cars = carsDao.selectById(search.getCarId());
		if (cars == null) {
			result.setError(ResultCode.CODE_STATE_4005, "您选择的车辆不存在或已下架，请选择别的车辆查看");
			return result;
		}
		// 品牌(本田) + 车系（雅阁） + 年款（2016/2017） + 排量(2.0) + 变速箱类型（手动自动） + 款式（高中低配）
		StringBuffer buffer = new StringBuffer();
		buffer.append(cars.getBrandName()).append(" ");
		buffer.append(cars.getFamilyName()).append(" ");
		buffer.append(cars.getYearPattern() + "款").append(" ");
		buffer.append(cars.getPl()).append(" ");
		buffer.append(cars.getGearboxName()).append(cars.getStyleName());
		Organization organization = organizationDao.selectById(search.getOrgId());
		if (organization == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的门店已退出线上预约或者不存在");
			return result;
		}
		// 查询该用户是否已绑定
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phoneNumber", search.getPhoneNumber());
		List<CustomerUsers> customerUseres = customerUsersDao.select(params);
		CustomerUsers customerUsers = null;
		if (customerUseres == null || customerUseres.size() <= 0) {
			customerUsers = new CustomerUsers();
			customerUsers.setPhoneNumber(search.getPhoneNumber());
			customerUsers.setCustomerUsersName(search.getCustomerUsersName());
			customerUsers.setCreateDate(new Date());
			customerUsers.setAgentGender(users.getAgentGender());
			customerUsers.setCustomerUserCode(customerUsersDao.getCode());
			customerUsers.setHeadPortrait(users.getHeadPortrait());
			customerUsers.setUnionId(users.getUnionId());
			customerUsers.setAppProgramOpenId(users.getAppProgramOpenId());
			customerUsersDao.insert(customerUsers);// 创建新用户
			users.setCustomerUsersId(customerUsers.getCustomerUsersId());
			users.setPhoneNumber(customerUsers.getPhoneNumber());
			users.setCustomerUsersName(search.getCustomerUsersName());
			usersTmplDao.updateById(users);// 绑定用户微信
			// carBrowseRecordUpdate(users.getUsersTempId(),
			// customerUsers.getCustomerUsersId());//更新人车浏览记录
			CustomerCustomerOrg customerCustomerOrg = new CustomerCustomerOrg();// 创建门店关联
			customerCustomerOrg.setCreateDate(new Date());
			customerCustomerOrg.setCarPurchaseIntention(search.getCarPurchaseIntention());
			customerCustomerOrg.setIsEdit(true);
			customerCustomerOrg.setIsShareEdit(true);
			customerCustomerOrg.setIntentionCarId(search.getCarId());
			// buffer.append(cars.getGearboxName()).append(cars.getStyleName());
			customerCustomerOrg.setIntentionCarInfo(buffer.toString());
			customerCustomerOrg.setOrgId(search.getOrgId());
			customerCustomerOrg.setOrgShortName(organization.getShortName());
			customerCustomerOrg.setIsAppointment(true);
			customerCustomerOrg.setCustomerUsersId(customerUsers.getCustomerUsersId());
			customerCustomerOrg.setCustomerUsersName(customerUsers.getCustomerUsersName());
			customerCustomerOrg.setPhoneNumber(customerUsers.getPhoneNumber());
			customerCustomerOrg.setTheSource(GeneralConstant.Org.TG);
			customerCustomerOrg.setTimeOfAppointmentDate(new Date());
			return customerCustomerOrgDao.insertAndResult(customerCustomerOrg, result);
		} else {
			customerUsers = customerUseres.get(0);
			// 进行预约
			params.put("customerUsersId", customerUsers.getCustomerUsersId());
			params.put("orgId", search.getOrgId());
			List<CustomerCustomerOrg> customerOrgs = customerCustomerOrgDao.select(params);
			if (customerOrgs == null || customerOrgs.size() <= 0) {
				CustomerCustomerOrg customerCustomerOrg = new CustomerCustomerOrg();
				customerCustomerOrg.setCreateDate(new Date());
				customerCustomerOrg.setCarPurchaseIntention(search.getCarPurchaseIntention());
				customerCustomerOrg.setIsEdit(true);
				customerCustomerOrg.setIsShareEdit(true);
				customerCustomerOrg.setIntentionCarId(search.getCarId());
				customerCustomerOrg.setIntentionCarInfo(buffer.toString());
				customerCustomerOrg.setOrgId(search.getOrgId());
				customerCustomerOrg.setOrgShortName(organization.getShortName());
				customerCustomerOrg.setIsAppointment(true);
				customerCustomerOrg.setCustomerUsersId(customerUsers.getCustomerUsersId());
				customerCustomerOrg.setCustomerUsersName(customerUsers.getCustomerUsersName());
				customerCustomerOrg.setPhoneNumber(customerUsers.getPhoneNumber());
				customerCustomerOrg.setTheSource(GeneralConstant.Org.TG);
				customerCustomerOrg.setTimeOfAppointmentDate(new Date());
				return customerCustomerOrgDao.insertAndResult(customerCustomerOrg, result);
			} else {
				CustomerCustomerOrg customerCustomerOrg = customerOrgs.get(0);
				customerCustomerOrg.setCarPurchaseIntention(search.getCarPurchaseIntention());
				customerCustomerOrg.setIntentionCarId(search.getCarId());
				customerCustomerOrg.setIntentionCarInfo(buffer.toString());
				customerCustomerOrg.setIsAppointment(true);
				customerCustomerOrg.setTimeOfAppointmentDate(new Date());
				return customerCustomerOrgDao.updateByIdAndResult(customerCustomerOrg, result);
			}
		}
	}

	@Override
	public Result generalizeInfo(UsersTmplSearch search, Result result, UsersTmpl users) throws Exception {
		//1.查询所有预约的用户
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("theSource", GeneralConstant.Org.TG);
		List<CustomerCustomerOrgVo> customerOrgVos = customerCustomerOrgDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerUsersName", StringUtil.shieldName(customerOrgVo.getCustomerUsersName()));
			map.put("phoneNumber", StringUtil.shieldPhone(customerOrgVo.getPhoneNumber()));
			map.put("timeOfAppointmentDate", DateUtil.format(customerOrgVo.getTimeOfAppointmentDate(),"yyyy-MM-dd hh:mm"));
			maps.add(map);
		}
		Map<String, Object> allMap = new HashMap<String, Object>();
		allMap.put("list", maps);
		allMap.put("number", customerOrgVos.size());
		allMap.put("endDate", "2018-01-20 11:00:00");
		result.setOK(ResultCode.CODE_STATE_200, "", allMap);
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.format("2018-1-20","yyyy-MM-dd"));
	}
}
