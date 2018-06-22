package main.com.weixinApp.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.car.dao.dao.CarBrowseRecordDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.dao.OrgCarsConfigureDao;
import main.com.car.dao.po.CarBrowseRecord;
import main.com.car.dao.po.Cars;
import main.com.car.dao.po.OrgCarsConfigure;
import main.com.customer.dao.dao.CustomerCustomerOrgDao;
import main.com.customer.dao.dao.CustomerUsersDao;
import main.com.customer.dao.dao.UsersTmplDao;
import main.com.customer.dao.po.CustomerCustomerOrg;
import main.com.customer.dao.po.CustomerUsers;
import main.com.customer.dao.po.CustomerUsersDTO;
import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.dao.search.UsersTmplSearch;
import main.com.frame.cache.CacheTimerHandler;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.vo.OrganizationVo;
import main.com.utils.AES;
import main.com.utils.Base64Util;
import main.com.utils.GeneralConstant;
import main.com.utils.HTTPRequest;
import main.com.utils.LocationUtils;
import main.com.utils.Number;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.utils.UUIDUtils;
import main.com.utils.UserInfoUtil;
import main.com.utils.Value;
import main.com.weixinApp.service.AppUsersService;

@Service
public class AppUsersServiceImpl extends BaseServiceImpl<UsersTmpl> implements AppUsersService{

	@Autowired
	UsersTmplDao usersTmplDao;
	
	@Autowired
	OrgCarsConfigureDao orgCarsConfigureDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	CustomerUsersDao customerUsersDao;
	
	@Autowired
	CustomerCustomerOrgDao customerCustomerOrgDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CarBrowseRecordDao carBrowseRecordDao;
	
	@Override
	protected BaseDao<UsersTmpl> getBaseDao() {
		return usersTmplDao;
	}

	@Override
	public Result decodeUserInfo(UsersTmplSearch search, Result result, String sessionId) throws Exception {
		// 替换字符串，获得请求URL
		String token = UserInfoUtil.getWebAccess(search.getCode());// 通过自定义工具类组合出小程序需要的登录凭证															// code
//		System.out.println("》》》组合token为：" + token);
		// 通过https方式请求获得web_access_token并获得小程序的返回
//		JSONObject jsonObject = HTTPRequest.httpsRequest(token, "GET",
//				null);
		JSONObject jsonObject = HTTPRequest.sendTheGet(token, "GET");
//		System.out.println("小程序获取用户："+jsonObject);
    String sessionKey = jsonObject.getString("session_key");
    Map<String, Object> map = new HashMap<String, Object>();
    try {
        AES aes = new AES();
        byte[] resultByte = aes.decrypt(Base64.decodeBase64(search.getEncryptedData()), Base64.decodeBase64(sessionKey), Base64.decodeBase64(search.getIv()));
        if(null != resultByte && resultByte.length > 0){
            String userInfo = new String(resultByte, "UTF-8");
            JSONObject userInfoJosn = new JSONObject(userInfo);
//            System.out.println("userInfoJosn解密信息："+userInfoJosn);
            String openId = userInfoJosn.getString("openId"); //
            UsersTmpl usersTmpl = usersTmplDao.selectByCode(openId);
            if(usersTmpl == null){
            	usersTmpl = new UsersTmpl();
            	usersTmpl.setAppProgramOpenId(openId);
            	usersTmpl.setAgentGender(Integer.valueOf(userInfoJosn.get("gender")+"")); //": GENDER,
//	            userInfoJosn.getString("city"); //": "CITY",
//	            userInfoJosn.getString("province"); //": "PROVINCE",
//	            userInfoJosn.getString("country"); //": "COUNTRY",
	            if(userInfoJosn.has("unionId")){
	            	usersTmpl.setUnionId(userInfoJosn.getString("unionId")); //": "UNIONID",
	            }
	            usersTmpl.setNikeName(Base64Util.encodeData(userInfoJosn.getString("nickName"))); //": "NICKNAME",	
	            usersTmpl.setHeadPortrait(userInfoJosn.getString("avatarUrl")); //": "AVATARURL",
	            usersTmpl.setSessionId(UUIDUtils.random());
            }
            map.put("nikeName", Base64Util.decodeData(usersTmpl.getNikeName()));
            map.put("avatarUrl", usersTmpl.getHeadPortrait());
            map.put("sessionId", usersTmpl.getSessionId());
            map.put("agentGender", usersTmpl.getAgentGender());
            map.put("customerUsersName", usersTmpl.getCustomerUsersName());
            map.put("phoneNumber", usersTmpl.getPhoneNumber());
            map.put("customerUsersId", usersTmpl.getCustomerUsersId());
            if(StringUtil.isEmpty(usersTmpl.getUsersTempId())){
            	if(usersTmplDao.insert(usersTmpl)){
            		result.setOK(ResultCode.CODE_STATE_200, "", TakeCareMapDate.cutNullMap(map));
            	}else{
            		 result.setError(ResultCode.CODE_STATE_4005, "系统正在升级2");
            	}
            }else{
            	if(usersTmplDao.updateById(usersTmpl)){
            		result.setOK(ResultCode.CODE_STATE_200, "", TakeCareMapDate.cutNullMap(map));
            	}else{
            		 result.setError(ResultCode.CODE_STATE_4005, "系统正在升级3");
            	}
            }
        }
        return result;
    } catch (InvalidAlgorithmParameterException e) {
        e.printStackTrace();
        result.setError(ResultCode.CODE_STATE_4005, "系统正在升级4");
        return result;
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        result.setError(ResultCode.CODE_STATE_4005, "系统正在升级5");
        return result;
    }
	}

	@Override
	public Result refreshMyInfo(UsersTmplSearch search, Result result)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		UsersTmpl tmpl = usersTmplDao.selectById(search.getUsersTmpl().getUsersTempId());
		map.put("nikeName", Base64Util.decodeData(tmpl.getNikeName()));
        map.put("headPortrait", tmpl.getHeadPortrait());
        map.put("usersTempId", tmpl.getUsersTempId());
        map.put("sessionId", tmpl.getSessionId());		
        map.put("agentGender", tmpl.getAgentGender());
        map.put("customerUsersName", tmpl.getCustomerUsersName());
        map.put("phoneNumber", tmpl.getPhoneNumber());
        map.put("customerUsersId", tmpl.getCustomerUsersId());
    	result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}

	@Override
	public Result bespeakBefor(UsersTmplSearch search, Result result) throws Exception {
		//根据距离查找门店
//		if(StringUtil.isEmpty(search.getLatitude()) || StringUtil.isEmpty(search.getLongitude())) {
//			result.setError(ResultCode.CODE_STATE_4016, "请先授权获取位置");
//			return result;
//		}
		//查询出所有门店商品配置
		Map<String, Object> params = new HashMap<String, Object>();
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		params.put("carsId", search.getCarId());
//		params.put("interiorId", search.getInteriorId());
		params.put("colourId", search.getColourId());
		List<OrgCarsConfigure> carsConfigures = orgCarsConfigureDao.select(params);
		Long total = orgCarsConfigureDao.getRowCount(params);
		int rows = search.getRows();
		Set<Integer> orgIds = new HashSet<Integer>();
		for(OrgCarsConfigure configure : carsConfigures) {
			orgIds.add(configure.getOrgId());
		}
		if(orgIds.size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "请求成功","");
			return result;
		}
		//根据配置查询门店
		params.put("ids", orgIds);
		params.put("isPage", false);
		List<OrganizationVo> organizations = organizationDao.select(params);
		for(OrganizationVo organization : organizations) {//计算门店距离，查询出最优门店
			//第二步，配置优惠
			for(OrgCarsConfigure carsConfigure : carsConfigures) {
				if(carsConfigure.getOrgId().equals(organization.getOrgId())) {
					organization.setDiscount(carsConfigure.getDiscountPrice() == null ?0d:carsConfigure.getDiscountPrice().doubleValue());
					organization.setPrice(carsConfigure.getGuidingPrice().doubleValue());
				}
			}
			//第一步，配置距离
			if(StringUtil.isEmpty(organization.getLatitude()) || StringUtil.isEmpty(organization.getLongitude())) {
				organization.setDistance(0d);
				continue;
			}
			Double distance = LocationUtils.getDistance(organization.getLatitude(), organization.getLongitude(), search.getLatitude(), search.getLongitude());
			organization.setDistance(distance);
			
		}
		if(search.getIsDistance() == null) {
			search.setIsDistance(false);
		}
		if(search.getIsDistance()) {//根据距离排序
			 Collections.sort(organizations,new Comparator<OrganizationVo>(){
		            public int compare(OrganizationVo arg0, OrganizationVo arg1) {
		                return arg0.getDistance().compareTo(arg1.getDistance());
		            }
		        });
		}else { //根据优惠价格排序
			 Collections.sort(organizations,new Comparator<OrganizationVo>(){
		            public int compare(OrganizationVo arg0, OrganizationVo arg1) {
		                return arg1.getDiscount().compareTo(arg0.getDiscount());
		            }
		        });
		}
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(int i=0;i<organizations.size();i++) {
			OrganizationVo organization = organizations.get(i);
			Map<String,Object> map = new HashMap<String, Object>();
			if(i == 0) {
				map.put("isChoice", 1);
			}else {
				map.put("isChoice", 0);
			}
			map.put("orgName", organization.getShortName());
			map.put("address", organization.getProvinceName()+organization.getCityName()+organization.getAreaName()+organization.getAddress());
			map.put("distance", organization.getDistance());
			map.put("discount", organization.getDiscount());
			map.put("latitude", organization.getLatitude());
			map.put("longitude", organization.getLongitude());
			map.put("telPhone", organization.getTelePhone());
			map.put("orgId", organization.getOrgId());
			map.put("image", organization.getImageUrl());
			map.put("price", Number.getCeil((organization.getPrice()-organization.getDiscount()) / 10000d, 2)+"万");
//			map.put("price", organization.getPrice()-organization.getDiscount());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		Map<String,Object> allMap = new HashMap<String, Object>();
		allMap.put("phoneNumber", search.getUsersTmpl().getPhoneNumber());
		allMap.put("customerUsersName", search.getUsersTmpl().getCustomerUsersName());
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",TakeCareMapDate.cutNullMap(allMap));
		return result;
	}

	@Override
	@Transactional
	public Result bespeak(UsersTmplSearch search, Result result,UsersTmpl users) throws Exception {
		//输入电话号码进行绑定
		if(StringUtil.isEmpty(search.getCustomerUsersName())) {
			result.setError(ResultCode.CODE_STATE_4016, "请输入姓名");
			return result;
		}
		if(StringUtil.isEmpty(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4016, "请输入电话号");
			return result;
		}else if(!StringUtil.isNumeric(search.getPhoneNumber())){
			result.setError(ResultCode.CODE_STATE_4016, "电话号码格式错误");
			return result;
		}
		if(StringUtil.isEmpty(search.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4016, "请选择门店");
			return result;
		}
		Cars cars = carsDao.selectById(search.getCarId());
		if(cars == null) {
			result.setError(ResultCode.CODE_STATE_4005, "您选择的车辆不存在或已下架，请选择别的车辆查看");
			return result;
		}
		//品牌(本田) + 车系（雅阁） + 年款（2016/2017） + 排量(2.0) + 变速箱类型（手动自动） + 款式（高中低配）
		StringBuffer buffer = new StringBuffer();
		buffer.append(cars.getBrandName()).append(" ");
		buffer.append(cars.getFamilyName()).append(" ");
		buffer.append(cars.getYearPattern()+"款").append(" ");
		buffer.append(cars.getPl()).append(" ");
		buffer.append(cars.getGearboxName()).append(cars.getStyleName());
		Organization organization = organizationDao.selectById(search.getOrgId());
		if(organization == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的门店已退出线上预约或者不存在");
			return result;
		}
		//查询该用户是否已绑定
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("phoneNumber", search.getPhoneNumber());		
		List<CustomerUsers> customerUseres = customerUsersDao.select(params);
		CustomerUsers customerUsers = null;
		if(customerUseres == null || customerUseres.size() <= 0) {
			customerUsers = new CustomerUsers();
			customerUsers.setPhoneNumber(search.getPhoneNumber());
			customerUsers.setCustomerUsersName(search.getCustomerUsersName());
			customerUsers.setCreateDate(new Date());
			customerUsers.setAgentGender(users.getAgentGender());
			customerUsers.setCustomerUserCode(customerUsersDao.getCode());
			customerUsers.setHeadPortrait(users.getHeadPortrait());
			customerUsers.setUnionId(users.getUnionId());
			customerUsers.setAppProgramOpenId(users.getAppProgramOpenId());
			customerUsersDao.insert(customerUsers);//创建新用户
			users.setCustomerUsersId(customerUsers.getCustomerUsersId());
			users.setPhoneNumber(customerUsers.getPhoneNumber());
			users.setCustomerUsersName(search.getCustomerUsersName());
			usersTmplDao.updateById(users);//绑定用户微信
			carBrowseRecordUpdate(users.getUsersTempId(), customerUsers.getCustomerUsersId());//更新人车浏览记录
			CustomerCustomerOrg customerCustomerOrg = new CustomerCustomerOrg();//创建门店关联
			customerCustomerOrg.setCreateDate(new Date());
			customerCustomerOrg.setCarPurchaseIntention(search.getCarPurchaseIntention());
			customerCustomerOrg.setIsEdit(true);
			customerCustomerOrg.setIsShareEdit(true);
			customerCustomerOrg.setIntentionCarId(search.getCarId());
//			buffer.append(cars.getGearboxName()).append(cars.getStyleName());
			customerCustomerOrg.setIntentionCarInfo(buffer.toString());
			customerCustomerOrg.setOrgId(search.getOrgId());
			customerCustomerOrg.setOrgShortName(organization.getShortName());
			customerCustomerOrg.setIsAppointment(true);
			customerCustomerOrg.setCustomerUsersId(customerUsers.getCustomerUsersId());
			customerCustomerOrg.setCustomerUsersName(customerUsers.getCustomerUsersName());
			customerCustomerOrg.setPhoneNumber(customerUsers.getPhoneNumber());
			return customerCustomerOrgDao.insertAndResult(customerCustomerOrg, result);						
		}else {
			customerUsers = customerUseres.get(0);
			//进行预约
			params.put("customerUsersId", customerUsers.getCustomerUsersId());
			params.put("orgId", search.getOrgId());
			List<CustomerCustomerOrg> customerOrgs = customerCustomerOrgDao.select(params);
			if(customerOrgs == null || customerOrgs.size() <= 0) {
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
				return customerCustomerOrgDao.insertAndResult(customerCustomerOrg, result);
			}else {
				CustomerCustomerOrg customerCustomerOrg = customerOrgs.get(0);
				customerCustomerOrg.setCarPurchaseIntention(search.getCarPurchaseIntention());
				customerCustomerOrg.setIntentionCarId(search.getCarId());
				customerCustomerOrg.setIntentionCarInfo(buffer.toString());
				customerCustomerOrg.setIsAppointment(true);
				return customerCustomerOrgDao.updateByIdAndResult(customerCustomerOrg, result);
			}
		}
	}

	
	public void carBrowseRecordUpdate(Integer usersTmplId,Integer customerUsersId)throws Exception{
		List<CarBrowseRecord> browseRecords = carBrowseRecordDao.findEnbinding(usersTmplId);
		for(CarBrowseRecord browseRecord : browseRecords) {
			browseRecord.setCustomerId(customerUsersId);
			carBrowseRecordDao.updateById(browseRecord);
		}
	}

	@Override
	@Transactional
	public Result phonebinding(UsersTmplSearch search, Result result, UsersTmpl users) throws Exception {
		if(StringUtil.isEmpty(search.getPhoneNumber())){
			result.setError(ResultCode.CODE_STATE_4005, "电话号码不能为空");
			return result;
		}
		if(!search.getPhoneCode().equals(Value.check)){
			if(CacheTimerHandler.getCache(search.getPhoneNumber()) == null){
				result.setError(ResultCode.CODE_STATE_4005, "验证码错误");
				return result;
			}
			if(StringUtil.isEmpty(search.getPhoneCode()) || !CacheTimerHandler.getCache(search.getPhoneNumber()).getCacheContext().toString().equals(search.getPhoneCode())){
				result.setError(ResultCode.CODE_STATE_4005, "验证码错误");
				return result;
			}
		}
		Map<String,Object> params = new HashMap<String, Object>();
		Map<String,Object> map = new HashMap<String, Object>();
		params.put("phoneNumber", search.getPhoneNumber());		
		List<CustomerUsers> customerUseres = customerUsersDao.select(params);
		CustomerUsers customerUsers = null;
		if(customerUseres != null && customerUseres.size() > 0) {
			customerUsers = customerUseres.get(0);
			customerUsers.setAgentGender(users.getAgentGender());
			customerUsers.setHeadPortrait(users.getHeadPortrait());
			customerUsers.setUnionId(users.getUnionId());
			customerUsers.setAppProgramOpenId(users.getAppProgramOpenId());
			customerUsersDao.updateById(customerUsers);
			users.setCustomerUsersId(customerUsers.getCustomerUsersId());
			users.setPhoneNumber(customerUsers.getPhoneNumber());
			users.setCustomerUsersName(search.getCustomerUsersName());
			usersTmplDao.updateById(users);//绑定用户微信
            map.put("nikeName", Base64Util.decodeData(users.getNikeName()));
            map.put("avatarUrl", users.getHeadPortrait());
            map.put("sessionId", users.getSessionId());
            map.put("agentGender", users.getAgentGender());
            map.put("customerUsersName", users.getCustomerUsersName());
            map.put("phoneNumber", users.getPhoneNumber());
            map.put("customerUsersId", users.getCustomerUsersId());
            carBrowseRecordUpdate(users.getUsersTempId(), customerUsers.getCustomerUsersId());//更新人车浏览记录
			result.setOK(ResultCode.CODE_STATE_200, "",TakeCareMapDate.cutNullMap(map));
			return result;
		}else {
			users.setPhoneNumber(search.getPhoneNumber());
			usersTmplDao.updateById(users);//绑定用户微信
			map.put("nikeName", Base64Util.decodeData(users.getNikeName()));
            map.put("avatarUrl", users.getHeadPortrait());
            map.put("sessionId", users.getSessionId());
            map.put("agentGender", users.getAgentGender());
            map.put("customerUsersName", users.getCustomerUsersName());
            map.put("phoneNumber", users.getPhoneNumber());
            map.put("customerUsersId", users.getCustomerUsersId());
			result.setOK(ResultCode.CODE_STATE_200, "",TakeCareMapDate.cutNullMap(map));			
			return result;
		}
	}
}
