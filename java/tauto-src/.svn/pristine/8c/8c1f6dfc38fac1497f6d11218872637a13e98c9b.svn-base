package main.com.weixinHtml.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import main.com.frame.cache.CacheTimerHandler;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.RoleDao;
import main.com.system.dao.dao.SystemRegionDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.dao.SystemWarehouseDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.Role;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.po.SystemWarehouse;
import main.com.system.dao.search.OrganizationSearch;
import main.com.system.dao.search.SystemUsersSearch;
import main.com.system.dao.vo.OrganizationVo;
import main.com.system.dao.vo.SystemRegionVo;
import main.com.utils.Base64Util;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.HTTPRequest;
import main.com.utils.MD5Encoder;
import main.com.utils.StringUtil;
import main.com.utils.UUIDUtils;
import main.com.utils.Value;
import main.com.utils.rlycode.PhoneUtil;
import main.com.weixin.dao.po.WeixinAppToken;
import main.com.weixin.service.WeixinAppTokenService;
import main.com.weixinHtml.dao.dao.ShopUsersDao;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.search.ShopUserSearch;
import main.com.weixinHtml.dao.vo.ShopUsersVo;
import main.com.weixinHtml.service.ShopUserService;

@Service
public class ShopUserServiceImpl extends BaseServiceImpl<ShopUsers> implements ShopUserService {

	@Autowired
	ShopUsersDao shopUsersDao;

	@Autowired
	WeixinAppTokenService weixinAppTokenService;

	@Autowired
	OrganizationDao organizationDao;

	@Autowired
	SystemRegionDao systemRegionDao;

	@Autowired
	SystemWarehouseDao systemWarehouseDao;

	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Override
	protected BaseDao<ShopUsers> getBaseDao() {
		return shopUsersDao;
	}

	@Override
	public Result loginPassword(ShopUserSearch search, String id) throws Exception {
		Result result = new Result();
		if (StringUtil.isEmpty(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4005, "电话号码不能为空");
			return result;
		}
		if (StringUtil.isEmpty(search.getPassword())) {
			result.setError(ResultCode.CODE_STATE_4005, "密码不能为空");
			return result;
		}
		Map<String, Object> pramas = new HashMap<String, Object>();
		pramas.put("phoneNumber", search.getPhoneNumber());
		List<ShopUsersVo> usersVos = shopUsersDao.select(pramas);
		if (usersVos == null || usersVos.size() == 0) {
			result.setError(ResultCode.CODE_STATE_4005, "用户不存在，请先注册！");
			return result;
		} else if (usersVos != null && usersVos.size() > 1) {
			result.setError(ResultCode.CODE_STATE_4004, "用户名重复，登录出错，请联系客服");
			return result;
		}
		ShopUsersVo usersVo = usersVos.get(0);
		// if(StringUtil.isNotEmpty(search.getCode())){
		// WeixinAppToken appToken =
		// weixinAppTokenService.setAccessTokenWEB(search.getCode());
		// if(appToken == null){
		// result.setError(ResultCode.CODE_STATE_4005, "微信服务器无响应，登录失败");
		// return result;
		// }
		// if(StringUtil.isNotEmpty(appToken.getOpenid()) &&
		// StringUtil.isNotEmpty(usersVo.getOpenId()) &&
		// !usersVo.getOpenId().equals(appToken.getOpenid())){
		// result.setError(ResultCode.CODE_STATE_4005, "此电话号码已绑定其他微信号,请更换电话号或微信号登录");
		// return result;
		// }
		// }
		if (StringUtil.isEmpty(usersVo.getPassword())) {
			result.setError(ResultCode.CODE_STATE_4004, "密码错误");
			return result;
		}
//		if(StringUtil.isEmpty(usersVo.getOpenId())) {
//			usersVo = getShopUserWeixin(usersVo, search.getCode(),false);
//		}
		if (usersVo.getPassword().equals(MD5Encoder.encodeByMD5(search.getPassword()))) {
			usersVo.setSessionId(UUIDUtils.random());
			if (shopUsersDao.updateById(usersVo)) {
				result.setOK(ResultCode.CODE_STATE_200, "登陆成功", userMap(usersVo));
				return result;
			} else {
				result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
				return result;
			}
		} else {
			result.setError(ResultCode.CODE_STATE_4004, "密码错误");
			return result;
		}
	}

	public Map<String, Object> userMap(ShopUsers shopUsers) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openId", shopUsers.getOpenId());
		if (StringUtil.isNotEmpty(shopUsers.getNikeName())) {
			map.put("userName", Base64Util.decodeData(shopUsers.getNikeName()));
		}
		map.put("sessionId", shopUsers.getSessionId());
		map.put("headPortrait", shopUsers.getHeadPortrait());
		map.put("phoneNumber", shopUsers.getPhoneNumber());
		map.put("sex", shopUsers.getAgentGender());
		map.put("userCode", shopUsers.getUserCode());
		map.put("userType", shopUsers.getUserType());
		map.put("realName", shopUsers.getRealName());
		if (shopUsers.getOverFollow() != null && shopUsers.getOverFollow() == true) {
			map.put("isFollow", 1);
		} else {
			map.put("isFollow", 0);
		}
		if (shopUsers.getOverPush() != null && shopUsers.getOverPush()) {
			map.put("notify", 1);
		} else {
			map.put("notify", 0);
		}
		return map;
	}

	@Override
	public Result refreshCode(ShopUserSearch search, ShopUsers user) throws Exception {
		Result result = new Result();
		if (StringUtil.isEmpty(search.getCode())) {
			result.setError(ResultCode.CODE_STATE_4008, "授权信息错误");
			return result;
		}

		ShopUsers shopUsers = shopUsersDao.selectById(user.getShopUserId());
		if (shopUsers == null) {
			result.setError(ResultCode.CODE_STATE_4005, "用户信息错误");
			return result;
		}
		WeixinAppToken appToken = weixinAppTokenService.setAccessTokenWEB(search.getCode());
		if (appToken == null) {
			result.setError(ResultCode.CODE_STATE_4008, "微信服务器无响应，刷新失败");
			return result;
		}
		// 拉取用户信息
		// https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
		String requestUrl = Value.Weixin.getUserInfoWEB.replace("ACCESS_TOKEN", appToken.getAccessToken())
				.replace("OPENID", appToken.getOpenid());
		org.json.JSONObject jsonObject = HTTPRequest.sendTheGet(requestUrl, "GET");
		if (jsonObject.has("nickname")) {
			String userName = Base64Util.encodeData(jsonObject.get("nickname") + "");
			// userName = Base64Util.encodeData(userName);
			shopUsers.setNikeName(userName);
		}
		if (jsonObject.has("headimgurl")) {
			String image = jsonObject.get("headimgurl") + "";
			shopUsers.setHeadPortrait(image);

		}
		if (jsonObject.has("sex")) {
			String sex = jsonObject.get("sex") + "";
			shopUsers.setAgentGender(Integer.parseInt(sex));// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
		}
		if (jsonObject.has("unionid")) {
			String unionid = jsonObject.get("unionid") + "";
			shopUsers.setUnionId(unionid);// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
		}
		if (jsonObject.has("subscribe")) {
			String subscribe = jsonObject.get("subscribe") + "";
			if ("0".equals(subscribe)) {
				shopUsers.setOverFollow(false);// 是否已关注
			} else if ("1".equals(subscribe)) {
				shopUsers.setOverFollow(true);// 是否已关注
			}
		}
		shopUsers.setOpenId(appToken.getOpenid());
		// shopUsers.setIsPush(true);
		if (shopUsersDao.updateById(shopUsers)) {// (resultCode, message, data);
			result.setOK(ResultCode.CODE_STATE_200, "刷新信息成功", userMap(shopUsers));
			return result;
		} else {
			result.setError(ResultCode.CODE_STATE_4008, "微信服务器无响应，刷新失败");
			return result;
		}
	}

	@Override
	public Result loginCodeOfShop(ShopUserSearch search, String id) throws Exception {
		Result result = new Result();
		if (StringUtil.isEmpty(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4005, "电话号码不能为空");
			return result;
		}
		if (!search.getPhoneCode().equals(Value.check)) {
			if (CacheTimerHandler.getCache(search.getPhoneNumber()) == null) {
				result.setError(ResultCode.CODE_STATE_4005, "验证码错误");
				return result;
			}
			if (StringUtil.isEmpty(search.getPhoneCode()) || !CacheTimerHandler.getCache(search.getPhoneNumber())
					.getCacheContext().toString().equals(search.getPhoneCode())) {
				result.setError(ResultCode.CODE_STATE_4005, "验证码错误");
				return result;
			}
		}
		Map<String, Object> pramas = new HashMap<String, Object>();
		pramas.put("phoneNumber", search.getPhoneNumber());
		List<ShopUsersVo> usersVos = shopUsersDao.select(pramas);
		if (usersVos == null || usersVos.size() == 0) {
			result.setError(ResultCode.CODE_STATE_4005, "用户不存在，请先注册");
			return result;
		} else if (usersVos != null && usersVos.size() > 1) {
			result.setError(ResultCode.CODE_STATE_4004, "手机号码重复。请更换号码重试");
			return result;
		}
		ShopUsers usersVo = usersVos.get(0);
		if (StringUtil.isNotEmpty(search.getCode())) {// 手机号与微信OpenId一一对应
			WeixinAppToken appToken = weixinAppTokenService.setAccessTokenWEB(search.getCode());
			if (appToken == null) {
				result.setError(ResultCode.CODE_STATE_4008, "微信服务器无响应，登录失败");
				return result;
			}
			if (StringUtil.isNotEmpty(appToken.getOpenid()) && StringUtil.isNotEmpty(usersVo.getOpenId())
					&& !usersVo.getOpenId().equals(appToken.getOpenid())) {
				result.setError(ResultCode.CODE_STATE_4005, "此电话号码已绑定其他微信号,请更换电话号或微信号登录");
				return result;
			}
		}
		usersVo.setSessionId(UUIDUtils.random());
		if (shopUsersDao.updateById(usersVo)) {
			result.setOK(ResultCode.CODE_STATE_200, "登录成功", userMap(usersVo));
			return result;
		} else {
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
			return result;
		}
	}

	@Override
	public Result silenceLogin(ShopUserSearch search, String id) throws Exception {
		Result result = new Result();
		ShopUsers shopUsers = null;
		WeixinAppToken appToken = null;
		if (StringUtil.isNotEmpty(search.getOpenId())) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("openId", search.getOpenId());
			List<ShopUsersVo> userVos = shopUsersDao.select(params);
			if (userVos != null && userVos.size() > 0) {
				ShopUsers usersVo = userVos.get(0);
				shopUsers = usersVo;
				shopUsers.setSessionId(UUIDUtils.random());
				if (shopUsersDao.updateById(shopUsers)) {

				} else {
					result.setOK(ResultCode.CODE_STATE_4002, "用户更新登录失败", "");
					return result;
				}
			}
		} else if (StringUtil.isNotEmpty(search.getCode())) {
			appToken = weixinAppTokenService.setAccessTokenWEB(search.getCode());
			if (appToken != null) {
				ShopUsers usersVo = shopUsersDao.loginOfOpenId(appToken.getOpenid(), false);
				if (usersVo != null) {
					shopUsers = usersVo;
					shopUsers.setSessionId(UUIDUtils.random());
					if (shopUsersDao.updateById(shopUsers)) {

					} else {
						result.setOK(ResultCode.CODE_STATE_4002, "用户更新登录失败", "");
						return result;
					}
				} else {
					// 用户不存在，新建用户
					shopUsers = new ShopUsers();
					// 拉取用户信息
					// https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
					String requestUrl = Value.Weixin.getUserInfoWEB.replace("ACCESS_TOKEN", appToken.getAccessToken())
							.replace("OPENID", appToken.getOpenid());
					org.json.JSONObject jsonObject = HTTPRequest.sendTheGet(requestUrl, "GET");
					if (jsonObject.has("nickname")) {
						String userName = Base64Util.encodeData(jsonObject.get("nickname") + "");
						// userName = Base64Util.encodeData(userName);
						shopUsers.setNikeName(userName);
					}
					if (jsonObject.has("headimgurl")) {
						String image = jsonObject.get("headimgurl") + "";
						shopUsers.setHeadPortrait(image);

					}
					if (jsonObject.has("sex")) {
						String sex = jsonObject.get("sex") + "";
						shopUsers.setAgentGender(Integer.parseInt(sex));// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
					}
					if (jsonObject.has("unionid")) {
						String unionid = jsonObject.get("unionid") + "";
						shopUsers.setUnionId(unionid);// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
					}
					if (jsonObject.has("subscribe")) {
						String subscribe = jsonObject.get("subscribe") + "";
						if ("0".equals(subscribe)) {
							shopUsers.setOverFollow(false);// 是否已关注
						} else if ("1".equals(subscribe)) {
							shopUsers.setOverFollow(true);// 是否已关注
						}
					} else {
						shopUsers.setOverFollow(false);// 是否已关注
					}
					shopUsers.setOpenId(appToken.getOpenid());
					shopUsers.setSessionId(UUIDUtils.random());
					if (shopUsersDao.insert(shopUsers)) {
					} else {
						result.setOK(ResultCode.CODE_STATE_4002, "用户更新登录失败", "");
						return result;
					}
				}
			} else {
				result.setOK(ResultCode.CODE_STATE_4002, "微信服务器异常，请退出重新授权。", "");
				return result;
			}
		} else {
			result.setOK(ResultCode.CODE_STATE_4002, "微信服务器异常，请退出重新授权。", "");
			return result;
		}
		if (shopUsers == null) {
			result.setOK(ResultCode.CODE_STATE_4002, "微信服务器异常，请退出重新授权。", "");
			return result;
		} else {
			result.setOK(ResultCode.CODE_STATE_200, "", userMap(shopUsers));
			return result;
		}
	}

	@Override
	public Result notify(ShopUserSearch search, Result result) throws Exception {
		if (search.getNotify() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "参数错误");
			return result;
		}
		ShopUsers shopUsers = shopUsersDao.selectById(search.getShopUserId());
		if (shopUsers == null) {
			result.setError(ResultCode.CODE_STATE_4005, "用户信息错误");
			return result;
		}
		if (search.getNotify()) {// 如果开启
			if (StringUtil.isEmpty(shopUsers.getOpenId())) {
				result.setOK(ResultCode.CODE_STATE_200, "", false);
				return result;
			}
			shopUsers.setOverPush(true);
		} else {
			shopUsers.setOverPush(false);
		}
		if (shopUsersDao.updateById(shopUsers)) {
			result.setOK(ResultCode.CODE_STATE_200, "操作成功", true);
		} else {
			result.setError(ResultCode.CODE_STATE_4005, "网络异常，保存失败！");
		}
		return result;
	}

	@Override
	@Transactional
	public Result loginOut(ShopUserSearch search) throws Exception {
		Result result = new Result();
		if (StringUtil.isEmpty(search.getSessionId())) {
			result.setError(ResultCode.CODE_STATE_4005, "sessionId不能为空");
			return result;
		}
		Map<String, Object> pramas = new HashMap<String, Object>();
		pramas.put("sessionId", search.getSessionId());
		List<ShopUsersVo> usersVos = shopUsersDao.select(pramas);
		if (usersVos == null || usersVos.size() == 0) {
			result.setError(ResultCode.CODE_STATE_4003, "用户不存在");
			return result;
		} else if (usersVos != null && usersVos.size() > 1) {
			result.setError(ResultCode.CODE_STATE_4004, "用户名重复，退出登录出错，请联系客服");
			return result;
		}
		ShopUsersVo usersVo = usersVos.get(0);
		usersVo.setSessionId("");
		if (shopUsersDao.updateById(usersVo)) {
			result.setOK(ResultCode.CODE_STATE_200, "退出登录成功");
			return result;
		} else {
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
			return result;
		}
	}

	@Override
	public Result shopRegister(ShopUserSearch search, Result result) throws Exception {
		if (StringUtil.isEmpty(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4005, "电话号码不能为空");
			return result;
		} else if (!PhoneUtil.isPhone(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4005, "电话号码格式错误");
			return result;
		}
		if (StringUtil.isEmpty(search.getPassword())) {
			result.setError(ResultCode.CODE_STATE_4005, "密码不能为空");
			return result;
		}
		if (StringUtil.isEmpty(search.getUserType())) {
			result.setError(ResultCode.CODE_STATE_4005, "用户类型不能为空");
			return result;
		}
		if (StringUtil.isEmpty(search.getRealName()) && search.getUserType().equals(2)) {
			result.setError(ResultCode.CODE_STATE_4005, "请输入真实姓名");
			return result;
		}
		if (!search.getPhoneCode().equals(Value.check)) {
			if (CacheTimerHandler.getCache(search.getPhoneNumber()) == null) {
				result.setError(ResultCode.CODE_STATE_4005, "验证码错误");
				return result;
			}
			if (StringUtil.isEmpty(search.getPhoneCode()) || !CacheTimerHandler.getCache(search.getPhoneNumber())
					.getCacheContext().toString().equals(search.getPhoneCode())) {
				result.setError(ResultCode.CODE_STATE_4005, "验证码错误");
				return result;
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phoneNumber", search.getPhoneNumber());
		List<ShopUsersVo> usersVos = shopUsersDao.select(params);
		if (usersVos != null && usersVos.size() != 0) {
			result.setError(ResultCode.CODE_STATE_4003, "此号码用户已存在,请直接登录");
			return result;
		}
		if (StringUtil.isEmpty(search.getCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "请先授权");
			return result;
		}
		ShopUsers shopUser = getShopUserWeixin(search.getCode(), false);
		shopUser.setCreateTime(new Date());
		if (StringUtil.isNotEmpty(search.getPassword())) {
			shopUser.setPassword(MD5Encoder.encodeByMD5(search.getPassword()));
		}
		if (StringUtil.isNotEmpty(search.getPhoneNumber())) {
			shopUser.setPhoneNumber(search.getPhoneNumber());
		}
		if (StringUtil.isNotEmpty(search.getUserType())) {
			shopUser.setUserType(search.getUserType());
		}
		if (StringUtil.isNotEmpty(search.getRealName())) {
			shopUser.setRealName(search.getRealName());
		}
		shopUser.setUserCode(shopUsersDao.getUserCode());
		return save(shopUser, result);
	}

	public ShopUsers getShopUserWeixin(String code, Boolean overSave) throws Exception {
		WeixinAppToken appToken = weixinAppTokenService.setAccessTokenWEB(code);
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("openId", appToken.getOpenid()); // 使用OpenID查询
//
//		List<ShopUsersVo> usersVos = shopUsersDao.select(params);
//		ShopUsers shopUsers = null;
//		if (usersVos != null && usersVos.size() > 0) {
//			shopUsers = usersVos.get(0);
//		} else {
//			// 用户不存在，新建用户
//			shopUsers = new ShopUsers();
//		}
		ShopUsers shopUsers = new ShopUsers();
		
		// 拉取用户信息
		// https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
		String requestUrl = Value.Weixin.getUserInfoWEB.replace("ACCESS_TOKEN", appToken.getAccessToken())
				.replace("OPENID", appToken.getOpenid());
		org.json.JSONObject jsonObject = HTTPRequest.sendTheGet(requestUrl, "GET");
		if (jsonObject.has("nickname")) {
			System.out.println(jsonObject.get("nickname"));
			String userName = Base64Util.encodeData(jsonObject.get("nickname") + "");
			System.out.println("userName:"+userName);
			// userName = Base64Util.encodeData(userName);
			shopUsers.setNikeName(userName);
//			shopUsers.setRealName(userName);
		}
		if (jsonObject.has("headimgurl")) {
			String image = jsonObject.get("headimgurl") + "";
			shopUsers.setHeadPortrait(image);
		}
		if (jsonObject.has("sex")) {
			String sex = jsonObject.get("sex") + "";
			shopUsers.setAgentGender(Integer.parseInt(sex));// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
		}
		if (jsonObject.has("unionid")) {
			String unionid = jsonObject.get("unionid") + "";
			shopUsers.setUnionId(unionid);// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
		}
		if (jsonObject.has("subscribe")) {
			String subscribe = jsonObject.get("subscribe") + "";
			if ("0".equals(subscribe)) {
				shopUsers.setOverFollow(false);// 是否已关注
			} else if ("1".equals(subscribe)) {
				shopUsers.setOverFollow(true);// 是否已关注
			}
		} else {
			shopUsers.setOverFollow(false);// 是否已关注
		}
		shopUsers.setOpenId(appToken.getOpenid());
		shopUsers.setSessionId(UUIDUtils.random());
		if (overSave != null && overSave) {
			return save(shopUsers);
		} else {
			return shopUsers;
		}
	}
	
	public ShopUsers getShopUserWeixin(ShopUsers shopUsers,String code, Boolean overSave) throws Exception {
		if(StringUtil.isEmpty(code)) {
			return shopUsers;
		}
		WeixinAppToken appToken = weixinAppTokenService.setAccessTokenWEB(code);
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("openId", appToken.getOpenid()); // 使用OpenID查询
//
//		List<ShopUsersVo> usersVos = shopUsersDao.select(params);
//		ShopUsers shopUsers = null;
//		if (usersVos != null && usersVos.size() > 0) {
//			shopUsers = usersVos.get(0);
//		} else {
//			// 用户不存在，新建用户
//			shopUsers = new ShopUsers();
//		}
//		ShopUsers shopUsers = new ShopUsers();
		
		// 拉取用户信息
		// https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
		String requestUrl = Value.Weixin.getUserInfoWEB.replace("ACCESS_TOKEN", appToken.getAccessToken())
				.replace("OPENID", appToken.getOpenid());
		org.json.JSONObject jsonObject = HTTPRequest.sendTheGet(requestUrl, "GET");
		if (jsonObject.has("nickname")) {
			System.out.println(jsonObject.get("nickname"));
			String userName = Base64Util.encodeData(jsonObject.get("nickname") + "");
			System.out.println("userName:"+userName);
			// userName = Base64Util.encodeData(userName);
			shopUsers.setNikeName(userName);
//			shopUsers.setRealName(userName);
		}
		if (jsonObject.has("headimgurl")) {
			String image = jsonObject.get("headimgurl") + "";
			shopUsers.setHeadPortrait(image);
		}
		if (jsonObject.has("sex")) {
			String sex = jsonObject.get("sex") + "";
			shopUsers.setAgentGender(Integer.parseInt(sex));// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
		}
		if (jsonObject.has("unionid")) {
			String unionid = jsonObject.get("unionid") + "";
			shopUsers.setUnionId(unionid);// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
		}
		if (jsonObject.has("subscribe")) {
			String subscribe = jsonObject.get("subscribe") + "";
			if ("0".equals(subscribe)) {
				shopUsers.setOverFollow(false);// 是否已关注
			} else if ("1".equals(subscribe)) {
				shopUsers.setOverFollow(true);// 是否已关注
			}
		} else {
			shopUsers.setOverFollow(false);// 是否已关注
		}
		shopUsers.setOpenId(appToken.getOpenid());
		shopUsers.setSessionId(UUIDUtils.random());
		if (overSave != null && overSave) {
			return save(shopUsers);
		} else {
			return shopUsers;
		}
	}

	public ShopUsers save(ShopUsers shopUsers) {
		if (shopUsers == null) {
			return null;
		} else if (StringUtil.isEmpty(shopUsers.getShopUserId())) {
			if (shopUsersDao.insert(shopUsers)) {
				return shopUsers;
			} else {
				return null;
			}
		} else {
			if (shopUsersDao.updateById(shopUsers)) {
				return shopUsers;
			} else {
				return null;
			}
		}
	}

	public Result save(ShopUsers shopUsers, Result result) {
		if (shopUsers == null) {
			result.setError("保存数据错误");
			return result;
		} else if (StringUtil.isEmpty(shopUsers.getShopUserId())) {
			if (shopUsersDao.insert(shopUsers)) {
				result.setOK(ResultCode.CODE_STATE_200, "操作成功", userMap(shopUsers));
				return result;
			} else {
				result.setError(ResultCode.CODE_STATE_4008, "系统正在升级...");
				return result;
			}
		} else {
			if (shopUsersDao.updateById(shopUsers)) {
				result.setOK(ResultCode.CODE_STATE_200, "操作成功", userMap(shopUsers));
				return result;
			} else {
				result.setError(ResultCode.CODE_STATE_4008, "系统正在升级...");
				return result;
			}
		}
	}

	@Override
	public Result shopforgetPassWord(ShopUserSearch search, Result result) throws Exception {
		if (StringUtil.isEmpty(search.getPhoneNumber())) {
			result.setError(ResultCode.CODE_STATE_4005, "电话号码不能为空");
			return result;
		}
		if (!search.getPhoneCode().equals(Value.check)) {
			if (CacheTimerHandler.getCache(search.getPhoneNumber()) == null) {
				result.setError(ResultCode.CODE_STATE_4005, "验证码错误");
				return result;
			}
			if (StringUtil.isEmpty(search.getPhoneCode()) || !CacheTimerHandler.getCache(search.getPhoneNumber())
					.getCacheContext().toString().equals(search.getPhoneCode())) {
				result.setError(ResultCode.CODE_STATE_4005, "验证码错误");
				return result;
			}
		}
		if (StringUtil.isEmpty(search.getPassword())) {
			result.setError(ResultCode.CODE_STATE_4005, "新密码不能为空");
			return result;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phoneNumber", search.getPhoneNumber());
		List<ShopUsersVo> usersVos = shopUsersDao.select(params);
		if (usersVos == null || usersVos.size() == 0) {
			result.setError(ResultCode.CODE_STATE_4005, "用户不存在");
			return result;
		}

		ShopUsersVo usersVo = usersVos.get(0);
		usersVo.setPassword(MD5Encoder.encodeByMD5(search.getPassword()));
		return save(usersVo, result);
	}

	@Override
	@Transactional
	public Result supplementOrg(ShopUserSearch search, Result result, ShopUsers shopUser) throws Exception {
		if (StringUtil.isEmpty(search.getNatureType())) {
			result.setError("请选择商家类型");
			return result;
		}
		if (StringUtil.isEmpty(search.getShortName())) {
			result.setError("请店铺商家名称");
			return result;
		}

		Organization organization = new Organization();
		Map<String, Object> orgParams = new HashMap<>();
//		orgParams.put("telePhone", shopUser.getPhoneNumber());
//		orgParams.put("phone", shopUser.getPhoneNumber());
//		orgParams.put("allstatus", true);
//		orgParams.put("status", true);
//		List<Organization> organizations = organizationDao.select(orgParams);
		if(StringUtil.isNotEmpty(shopUser.getOrgId())) {
			result.setError("你的电话号已存在系统门店中或等待审核");
			return result;
		}
//		if (organizations != null && organizations.size() > 0) {
//		
//		}
		organization.setTelePhone(shopUser.getPhoneNumber());
//		organization.setLinkMan(shopUser.getRealName());
		
		// 新建的组织就默认给新建一个仓库
		SystemWarehouse systemWarehouse = new SystemWarehouse();
		systemWarehouse.setCreateDate(new Date());
		systemWarehouse.setIsDelete(false);
		systemWarehouse.setWarehouseName("整车库");
		systemWarehouse.setWarehouseType(GeneralConstant.SystemWarehouseType.vehicle);
		organization.setOrgCode(organizationDao.getorgCode());

		if (StringUtil.isNotEmpty(search.getLinkMan())) {
			organization.setLinkMan(search.getLinkMan());
		} else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入店长名称");
			return result;
		}
		if (StringUtil.isNotEmpty(search.getShortName())) {
			organization.setShortName(search.getShortName());
		} else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入组织名称");
			return result;
		}
		if (StringUtil.isNotEmpty(search.getServicePhone())) {
			organization.setServicePhone(search.getServicePhone());
		} else {
//			result.setError(ResultCode.CODE_STATE_4005, "请输入客服电话");
//			return result;
		}
		
		
		if (StringUtil.isNotEmpty(search.getServiceName())) {
			organization.setServiceName(search.getServiceName());
		} else {
//			result.setError(ResultCode.CODE_STATE_4005, "请输入客服名称");
//			return result;
		}

		organization.setOrgLevel(GeneralConstant.Org.Level_3);
		organization.setOrgType(GeneralConstant.Org.Type_2);
		organization.setTheSource(GeneralConstant.Org.SC);
		organization.setNatureType(search.getNatureType());

		search.setParentId(61);// 默认挂在淘车网下面
		if (search.getParentId() != null) {
			OrganizationVo parentOrganization = organizationDao.selectById(search.getParentId());
			if (parentOrganization == null) {
				result.setError(ResultCode.CODE_STATE_4005, "抱歉，你选择的上级并不存在于系统，请重新选择");
				return result;
			}
			organization.setParentId(search.getParentId());
			organization.setOrgCodeLevel(parentOrganization.getOrgCodeLevel() + GeneralConstant.SystemBoolean.SPLIT_
					+ organization.getOrgCode());
		} else {
			organization.setParentId(0);
			organization.setOrgCodeLevel(organization.getOrgCode());
		}
		if (StringUtil.isNotEmpty(search.getBusinessLicense())) {
			organization.setBusinessLicense(search.getBusinessLicense());
		} else {
			result.setError(ResultCode.CODE_STATE_4005, "请上传营业执照");
			return result;
		}
		if (StringUtil.isNotEmpty(search.getIdCardPicOn())) {
			organization.setIdCardPicOn(search.getIdCardPicOn());
		} else {
			result.setError(ResultCode.CODE_STATE_4005, "请上传身份证正面照");
			return result;
		}
		if (StringUtil.isNotEmpty(search.getIdCardPicOff())) {
			organization.setIdCardPicOff(search.getIdCardPicOff());
		} else {
			result.setError(ResultCode.CODE_STATE_4005, "请上传身份证反面照");
			return result;
		}
		if (StringUtil.isNotEmpty(search.getLatitude())) {
			organization.setLatitude(search.getLatitude());
		}
		// else {
		// result.setError(ResultCode.CODE_STATE_4005, "请填写经度");
		// return result;
		// }
		if (StringUtil.isNotEmpty(search.getLongitude())) {
			organization.setLongitude(search.getLongitude());
		}
		// else {
		// result.setError(ResultCode.CODE_STATE_4005, "请填写经度");
		// return result;
		// }

		// 查找地址
		if (StringUtil.isEmpty(search.getProvinceId()) || StringUtil.isEmpty(search.getCityId())
				|| StringUtil.isEmpty(search.getAreaId())) {
			result.setError(ResultCode.CODE_STATE_4005, "省市区请选择完整");
			return result;
		}
		List<String> regionIds = new ArrayList<String>();// sortField
		regionIds.add(search.getProvinceId());
		regionIds.add(search.getCityId());
		regionIds.add(search.getAreaId());
		Map<String, Object> params = new HashMap<>();
		params.clear();
		params.put("sortField", true);
		params.put("regionIds", regionIds);
		List<SystemRegionVo> regionVos = systemRegionDao.select(params);// 查出三个地区，按照省市区排列
		organization.setProvinceId(regionVos.get(0).getRegionId());
		organization.setProvinceName(regionVos.get(0).getRegionName());
		organization.setCityId(regionVos.get(1).getRegionId());
		organization.setCityName(regionVos.get(1).getRegionName());
		organization.setAreaId(regionVos.get(2).getRegionId());
		organization.setAreaName(regionVos.get(2).getRegionName());

		if (StringUtil.isNotEmpty(search.getAddress())) {
			organization.setAddress(search.getAddress());
		} else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写公司地址");
			return result;
		}

		if (StringUtil.isNotEmpty(search.getIntroduce())) {
			organization.setIntroduce(search.getIntroduce());
		} else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写公司简介");
			return result;
		}

		if (StringUtil.isNotEmpty(search.getImageUrl())) {
			organization.setImageUrl(search.getImageUrl());
		}
		try {
			if (organization.getOrgId() == null) {
				organization.setStatus(GeneralConstant.Org.audited);
				organization.setCreateDate(new Date());
				if (!organizationDao.insert(organization)) {
					result.setError(ResultCode.CODE_STATE_4005, "保存失败，请联系IT部");
					throw new Exception("B端完善商家资料失败，手动回滚");
				}
				systemWarehouse.setOrgId(organization.getOrgId());
				systemWarehouse.setOrgName(organization.getShortName());
				systemWarehouse.setOrgCode(organization.getOrgCode());
				if(!systemWarehouseDao.insert(systemWarehouse)) {
					result.setError(ResultCode.CODE_STATE_4005, "保存失败，请联系IT部");
					throw new Exception("B端完善商家资料添加默认仓库失败，手动回滚");
				}
				shopUser.setOrgId(organization.getOrgId());
				shopUser.setOrgName(organization.getShortName());
				if(!shopUsersDao.updateById(shopUser)) {
					result.setError(ResultCode.CODE_STATE_4005, "保存失败，请联系IT部");
					throw new Exception("B端完善商家资料，更新自身门店信息失败，手动回滚");
				}
				result.setOK(ResultCode.CODE_STATE_200, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Result shopOrgList(OrganizationSearch search, Result result, SystemUsers user) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(), search.getRows());
		Map<String, Object> params = new HashMap<>();
		params.put("keywords", search.getKeywords());
		if(search.getStatus() != null) {
			params.put("status", search.getStatus());
		}else {
			params.put("status", true);
			params.put("allstatus", true);
		}
		params.put("TheSource", GeneralConstant.Org.SC);
		params.put("sortField", "create_date");
		List<OrganizationVo> organizationVos = organizationDao.select(params);
		//为了不干扰以前的代码，只有在此处循环处理
//		for(OrganizationVo vo : organizationVos) {
//			vo.setCreateDate();
//		}
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", organizationVos);
		return new Result(returnMap);
	}

	/**
	 * 1启用 2禁用 3待审核
	 */
	@Override
	@Transactional
	public Result shopOrgAudit(ShopUserSearch search, Result result, SystemUsers user) throws Exception {
		if (StringUtil.isEmpty(search.getOrgId())) {
			result.setError("请先选择门店");
			return result;
		}
		OrganizationVo organizationVo = organizationDao.selectById(search.getOrgId());
		if (organizationVo == null) {
			result.setError("你选择的记录不存在或者已删除，请重新选择");
			return result;
		}
		if (!organizationVo.getStatus().equals(GeneralConstant.Org.audited)) {
			result.setError("该商户已被审核，请勿重复操作");
			return result;
		}
		SystemUsers users = null;
		if(StringUtil.isEmpty(search.getIsPass())) {
			result.setError("请选择通过或不通过");
			return result;
		}
		if (search.getIsPass()) {
			organizationVo.setStatus(GeneralConstant.Org.status_on);
			//添加用户账号
			result = addUser(organizationVo, result);
			if(!result.isSuccess()) {
				return result;
			}
			users = (SystemUsers)result.getData();
		} else {
			organizationVo.setStatus(GeneralConstant.Org.status_off);
		}
		try {
			if(users != null) {
				if(!systemUsersDao.insert(users)) {
					throw new Exception("审核B端用户，生成商务端用户失败，手动抛错回滚");
				}
			}
			if (organizationDao.updateById(organizationVo)) {
				result.setOK(ResultCode.CODE_STATE_200, "");
			} else {
				result.setError("数据操作出错，请联系管理员");
				throw new Exception("审核B端用户，生成商务端用户失败，手动抛错回滚");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return result;
	}
	
	public Result addUser(OrganizationVo organizationVo, Result result) throws Exception {
		SystemUsers systemUsers = null;
		if(StringUtil.isEmpty(organizationVo.getTelePhone())) {
			result.setError(ResultCode.CODE_STATE_4005, "手机号不能为空");
			return result;
		}else if(!StringUtil.isNumeric(organizationVo.getTelePhone()) || organizationVo.getTelePhone().length() > 11){
			result.setError(ResultCode.CODE_STATE_4005, "手机号格式错误，生成商务端账号失败");
			return result;
		}
		//查询当前系统此号码是否存在
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phoneNumber", organizationVo.getTelePhone());
		List<SystemUsers> users = this.systemUsersDao.select(params);
		if(users != null && users.size() > 0) {
			result.setError(ResultCode.CODE_STATE_4005, "此电话号已存在商务端，生成商务端账号失败");
			return result;
		}
		systemUsers = new SystemUsers();
		systemUsers.setIsEnable(true);
		systemUsers.setRealName(organizationVo.getLinkMan());
		systemUsers.setStatus(1);
		systemUsers.setPassword(MD5Encoder.encodeByMD5(organizationVo.getTelePhone()));//后续将会使用短信发送密码

		systemUsers.setOrgId(organizationVo.getOrgId());
		systemUsers.setOrgName(organizationVo.getShortName());
		systemUsers.setOrgCode(organizationVo.getOrgCode());
		//验证角色岗位
		Role role = roleDao.selectById(47);//默认店铺管理员
		if(role == null) {
			result.setError(ResultCode.CODE_STATE_4005, "选择角色岗位不存在，请重新选择");
			return result;
		}
		List<ShopUsersVo> usersVos = shopUsersDao.select(params);
		if (usersVos == null || usersVos.size() == 0) {
			result.setError(ResultCode.CODE_STATE_4005, "用户不存在");
			return result;
		}
		ShopUsersVo usersVo = usersVos.get(0);
//		if(StringUtil.isEmpty(search.getRealName())) {
//			result.setError(ResultCode.CODE_STATE_4005, "请输入真实姓名");
//			return result;
//		}
//		if(search.getBirthday() != null) {
//			systemUsers.setBirthday(DateUtil.format(search.getBirthday(),"yyyy-MM-dd"));
//		}
//		if(search.getCardNo() != null) {
//			systemUsers.setCardNo(search.getCardNo());
//		}
//		if(StringUtil.isNotEmpty(search.getEntryTime())) {
//			systemUsers.setEntryTime(DateUtil.format(search.getEntryTime(),"yyyy-MM-dd"));
//		} 
//		if(search.getBasePay() != null) {
//			systemUsers.setBasePay(search.getBasePay());
//		} 
		systemUsers.setAgentGender(usersVo.getAgentGender());
		systemUsers.setHeadPortrait(usersVo.getHeadPortrait());
//		systemUsers.setRealName(usersVo.getRealName());
		systemUsers.setPhoneNumber(usersVo.getPhoneNumber());
		systemUsers.setCreateTime(new Date());
		systemUsers.setBirthday(usersVo.getBirthday());
		result.setOK(ResultCode.CODE_STATE_200, "", systemUsers);
//		if(search.getUserId() == null) {
//			if(systemUsersDao.insert(systemUsers)) {
//				params.clear();
//				params.put("userId", systemUsers.getUsersId());
//				params.put("roleId", role.getRoleId());
//				systemUsersDao.insertRoleUser(params);
//				result.setOK(ResultCode.CODE_STATE_200, "保存成功");
//			}else {
//				result.setError(ResultCode.CODE_STATE_4005, "系统保存数据失败，请联系IT部");			
//			}
//		}else {
//			if(systemUsersDao.updateById(systemUsers)) {
//				params.clear();
//				params.put("userId", systemUsers.getUsersId());
//				params.put("roleId", role.getRoleId());
//				systemUsersDao.updateRoleUserById(params);
//				result.setOK(ResultCode.CODE_STATE_200, "保存成功");
//			}else {
//				result.setError(ResultCode.CODE_STATE_4005, "系统保存数据失败，请联系IT部");			
//			}
//		}
		return result;
	}

	@Override
	public Result allInPayConfigure(ShopUserSearch search, Result result, SystemUsers user) throws Exception {
		OrganizationVo organizationVo = organizationDao.selectById(search.getOrgId());
		if (organizationVo == null) {
			result.setError("你选择的记录不存在或者已删除，请重新选择");
			return result;
		}
		if (organizationVo.getStatus().equals(GeneralConstant.Org.audited)) {
			result.setError("该商户正在等待审核，暂不能进行配置");
			return result;
		}
		if (organizationVo.getStatus().equals(GeneralConstant.Org.status_off)) {
			result.setError("该商户已被禁用，无法进行配置");
			return result;
		}
		if (StringUtil.isNotEmpty(search.getAllInPayAppId())) {
			organizationVo.setAllInPayAppId(search.getAllInPayAppId());
		} else {
			result.setError("请输入通联分配的AppId");
			return result;
		}
		if (StringUtil.isNotEmpty(search.getAllInPaySecretKey())) {
			organizationVo.setAllInPaySecretKey(search.getAllInPaySecretKey());
		} else {
			result.setError("请输入通联分配的SecretKey");
			return result;
		}
		if (StringUtil.isNotEmpty(search.getAllInPayMerchant())) {
			organizationVo.setAllInPayMerchant(search.getAllInPayMerchant());
		} else {
			result.setError("请输入通联分配的商户号");
			return result;
		}
		organizationVo.setOverAllInPay(true);
		if (organizationDao.updateById(organizationVo)) {
			result.setOK(ResultCode.CODE_STATE_200, "");
		} else {
			result.setError("数据操作出错，请联系管理员");
		}
		return result;
	}

	@Override
	public Result myOrgInfo(ShopUserSearch search, Result result, ShopUsers shopUser) throws Exception {
		if(StringUtil.isEmpty(shopUser.getOrgId())) {
			result.setOK(ResultCode.CODE_STATE_200, "");
			return result;
		}
		Map<String, Object> orgParams = new HashMap<>();
		orgParams.put("orgId", shopUser.getOrgId());
		orgParams.put("allstatus", true);
		orgParams.put("status", true);
		List<OrganizationVo> organizations = organizationDao.select(orgParams);
		if(organizations.size() > 0) {
			result.setOK(ResultCode.CODE_STATE_200, "",organizations.get(0));
		}else {
			result.setOK(ResultCode.CODE_STATE_200, "");
		}
		return result;
	}
}
