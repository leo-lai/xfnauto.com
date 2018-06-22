package main.com.weixin.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.utils.Base64Util;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.HTTPRequest;
import main.com.utils.MD5Encoder;
import main.com.utils.StringUtil;
import main.com.utils.Value;
import main.com.utils.weixin.PayUtils;
import main.com.weixin.dao.dao.WeixinAppTokenDao;
import main.com.weixin.dao.po.WeixinAppToken;
import main.com.weixin.dao.search.WeixinAppTokenSearch;
import main.com.weixin.dao.vo.WeixinAppTokenVo;
import main.com.weixin.service.WeixinAppTokenService;

@Service
public class WeixinAppTokenServiceImpl extends
		BaseServiceImpl<WeixinAppToken> implements
		WeixinAppTokenService {

	public static Logger logger = Logger
			.getLogger(WeixinAppTokenServiceImpl.class);

	@Autowired
	private WeixinAppTokenDao apptokenDao;
	
	@Override
	protected BaseDao<WeixinAppToken> getBaseDao() {
		return apptokenDao;
	}


	@Override
	@Transactional
	public WeixinAppToken getAccessTokenJAVA() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tokenFrom", Value.Weixin.tokenFromJave);
			List<WeixinAppToken> apptokens = this.apptokenDao
					.select(map);
			if (apptokens == null || apptokens.size() == 0) {
				return null;
			}
			WeixinAppToken apptoken = apptokens.get(0);
			if (StringUtil.isNotEmpty(apptoken.getAccessToken())
					&& apptoken.getTime() != null) {
				String time = apptoken.getTime().getTime() + "";
				Date data = new Date();
				if ((data.getTime() - Long.parseLong(time)) < 7000 * 1000L) {
					return apptoken;
				}
			}

			String requestUrl = Value.Weixin.accessTokenUrl.replace(
					"APPID", apptoken.getAppId()).replace("APPSECRET",
					apptoken.getAppSecret());
			JSONObject jsonObject = HTTPRequest.httpsRequest(requestUrl, "GET",
					null);
			// 如果请求成功
			try {
				if (null != jsonObject) {
					 System.out.println(jsonObject);
					apptoken.setAccessToken(jsonObject
							.getString("access_token"));
					apptoken.setTime(new Date());
					this.apptokenDao.updateById(apptoken);
					return apptoken;
				}
			} catch (Exception e) {
				logger.error(
						"获取accesstoken失败 errcode:{"
								+ jsonObject.getString("errcode")
								+ "} errmsg:{" + jsonObject.getString("errmsg")
								+ "}", e);
			}
		} catch (Exception e) {
			logger.error("getAccessTokenJAVA获取 accessToken 失败", e);
		}
		return null;

	}
	
	@Override
	@Transactional
	public WeixinAppToken getAccessTokenApp() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tokenFrom", Value.Weixin.tokenFromApp);
			List<WeixinAppToken> apptokens = this.apptokenDao
					.select(map);
			if (apptokens == null || apptokens.size() == 0) {
				return null;
			}
			WeixinAppToken apptoken = apptokens.get(0);
			if (StringUtil.isNotEmpty(apptoken.getAccessToken())
					&& apptoken.getTime() != null) {
				String time = apptoken.getTime().getTime() + "";
				Date data = new Date();
				if ((data.getTime() - Long.parseLong(time)) < 7000 * 1000L) {
					return apptoken;
				}
			}
			
			String requestUrl = Value.Weixin.accessTokenUrl.replace(
					"APPID", apptoken.getAppId()).replace("APPSECRET",
							apptoken.getAppSecret());
			JSONObject jsonObject = HTTPRequest.httpsRequest(requestUrl, "GET",
					null);
			// 如果请求成功
			try {
				if (null != jsonObject) {
					// System.out.println(jsonObject);
					apptoken.setAccessToken(jsonObject
							.getString("access_token"));
					apptoken.setTime(new Date());
					this.apptokenDao.updateById(apptoken);
					return apptoken;
				}
			} catch (Exception e) {
				logger.error(
						"获取accesstoken失败 errcode:{"
								+ jsonObject.getString("errcode")
								+ "} errmsg:{" + jsonObject.getString("errmsg")
								+ "}", e);
			}
		} catch (Exception e) {
			logger.error("getAccessTokenApp获取 accessToken 失败", e);
		}
		return null;
		
	}
	
	@Override
	public WeixinAppTokenVo getAccessTokenAllInPay() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tokenFrom", Value.Weixin.tokenFromAllInPay);
		List<WeixinAppTokenVo> apptokens = this.apptokenDao
				.select(map);
		if (apptokens == null || apptokens.size() == 0) {
			return null;
		}
		return apptokens.get(0);
	}
	
	// public WeixinAppToken ()throws Exception{
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("tokenFrom", GeneralConstant.Weixin.tokenFromJave);
	// List<WeixinAppToken> apptokens = this.apptokenDao
	// .select(map);
	// if (apptokens == null || apptokens.size() == 0) {
	// return null;
	// }
	// WeixinAppToken apptoken = apptokens.get(0);
	// }

	@Transactional
	@Override
	public Result getJsapiTicket(String url)throws Exception{
		Result result = new Result();
		WeixinAppToken apptoken = getAccessTokenJAVA();
		String nonce_str = PayUtils.createNoncestr();// 随机字符串
		Date nowdate = new Date();
		long timestamp = nowdate.getTime();
		String ticket = null;
		try {
			if (StringUtil.isEmpty(apptoken.getJsapiTicket())
					|| apptoken.getJsapiTime() == null
					|| (timestamp - apptoken.getJsapiTime().getTime()) > 7100 * 1000L) {
				// System.out.println("*******************");
				String requestUrl = Value.Weixin.getTicket.replace("ACCESS_TOKEN",
						apptoken.getAccessToken());
				JSONObject jsonObject = HTTPRequest.httpsRequest(requestUrl,
						"GET", null);
				// 如果请求成功
				try {
					if (jsonObject.has("ticket")) {
						// System.out.println(jsonObject);
						ticket = jsonObject.getString("ticket");
					}
				} catch (Exception e) {
					logger.error(
							"获取jsapiTicket失败 errcode:{"
									+ jsonObject.get("errcode") + "} errmsg:{"
									+ jsonObject.getString("errmsg") + "}", e);
				}

				if (ticket != null) {
					apptoken.setJsapiTicket(ticket);
					apptoken.setJsapiTime(nowdate);
					this.apptokenDao.updateById(apptoken);
				}
			}
			String signature = this.sign(apptoken.getJsapiTicket(), url,
					nonce_str, timestamp + "");
			Map<String, String> resultmap = new HashMap<String, String>();
			resultmap.put("signature", signature);
			resultmap.put("appId", apptoken.getAppId());
			resultmap.put("nonceStr", nonce_str);
			resultmap.put("timestamp", timestamp + "");
			result.setOK(ResultCode.CODE_STATE_200, "", resultmap);
			return result;
		} catch (Exception e) {
			logger.error("获取 jsapiTicket 失败");
			result.setOK(ResultCode.CODE_STATE_4005, "微信授权失败");
		}
		return result;
	}
	@Transactional
	@Override
	public Result getJsapiTicketApp(String url)throws Exception{
		Result result = new Result();
		WeixinAppToken apptoken = getAccessTokenApp();
		String nonce_str = PayUtils.createNoncestr();// 随机字符串
		Date nowdate = new Date();
		long timestamp = nowdate.getTime();
		String ticket = null;
		try {
			if (StringUtil.isEmpty(apptoken.getJsapiTicket())
					|| apptoken.getJsapiTime() == null
					|| (timestamp - apptoken.getJsapiTime().getTime()) > 7100 * 1000L) {
				// System.out.println("*******************");
				String requestUrl = Value.Weixin.getTicket.replace("ACCESS_TOKEN",
						apptoken.getAccessToken());
				JSONObject jsonObject = HTTPRequest.httpsRequest(requestUrl,
						"GET", null);
				// 如果请求成功
				try {
					if (jsonObject.has("ticket")) {
						// System.out.println(jsonObject);
						ticket = jsonObject.getString("ticket");
					}
				} catch (Exception e) {
					logger.error(
							"获取jsapiTicket失败 errcode:{"
									+ jsonObject.get("errcode") + "} errmsg:{"
									+ jsonObject.getString("errmsg") + "}", e);
				}
				
				if (ticket != null) {
					apptoken.setJsapiTicket(ticket);
					apptoken.setJsapiTime(nowdate);
					this.apptokenDao.updateById(apptoken);
				}
			}
			String signature = this.sign(apptoken.getJsapiTicket(), url,
					nonce_str, timestamp + "");
			Map<String, String> resultmap = new HashMap<String, String>();
			resultmap.put("signature", signature);
			resultmap.put("appId", apptoken.getAppId());
			resultmap.put("nonceStr", nonce_str);
			resultmap.put("timestamp", timestamp + "");
			result.setOK(ResultCode.CODE_STATE_200, "", resultmap);
			return result;
		} catch (Exception e) {
			logger.error("获取 jsapiTicket 失败");
			result.setOK(ResultCode.CODE_STATE_4005, "微信授权失败");
		}
		return result;
	}
	
	
	@Override
	public WeixinAppToken setAccessTokenWEB(String code) throws Exception {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("tokenFrom", GeneralConstant.Weixin.tokenFromWeb);
//		List<WeixinAppToken> apptokens = this.apptokenDao
//				.select(map);
//		if (apptokens == null) {
//			return null;
//		}
//		WeixinAppToken apptoken = apptokens.get(0);
		
//		if (StringUtil.isNotEmpty(apptoken.getAccessToken())//如果已经存在就验证是否有效，有效就返回
//				&& apptoken.getTime() != null) { //如果授权有效就返回
//			String time = apptoken.getTime().getTime() + "";
//			Date data = new Date();
//			if ((data.getTime() - Long.parseLong(time)) < 7200 * 1000L) {
//				return apptoken;
//			}else{
//				String requestUrl = GeneralConstant.Weixin.getAccTokenWebByRefresh.replace(
//						"APPID", apptoken.getAppId()).replace("REFRESH_TOKEN",
//								apptoken.getRefreshToken());
//				JSONObject jsonObject = HTTPRequest.httpsRequest(requestUrl, "GET",
//						null);
				// 如果请求成功
//				try {
//					if (null != jsonObject) {
//						apptoken.setAccessToken(jsonObject.getString("access_token"));
//						apptoken.setRefreshToken(jsonObject.getString("refresh_token"));
//						apptoken.setTime(new Date());
//						apptoken.setOpenid(jsonObject.getString("openid"));
//						this.apptokenDao.updateById(apptoken);
//						return apptoken;
//					}
//				} catch (Exception e) {
//					logger.error(
//							"获取accesstoken失败 errcode:{"
//									+ jsonObject.getString("errcode")
//									+ "} errmsg:{" + jsonObject.getString("errmsg")
//									+ "}", e);
//				}
//			}
//		}
//		Map<String, Object> map = new HashMap<String, Object>();//基于页面授权的apptoken会快速失效
//		map.put("tokenFrom", Value.Weixin.tokenFromWeb);
//		List<WeixinAppToken> apptokens = this.apptokenDao
//				.select(map);
		WeixinAppToken apptoken = null;
//		if (apptokens == null || apptokens.size() == 0) {
			apptoken = new WeixinAppToken();
			apptoken.setAppId(Value.Weixin.APPID);
			apptoken.setAppSecret(Value.Weixin.SECRET);
//		}else{
//			apptoken = apptokens.get(0);
//		}
		
		String requestUrl = Value.Weixin.getAccTokenWeb.replace(
				"APPID", apptoken.getAppId()).replace("SECRET",
						apptoken.getAppSecret()).replace("CODE", code);
		JSONObject jsonObject = HTTPRequest.httpsRequest(requestUrl, "GET",
				null);
		try {
			if (null != jsonObject) {
				if(!jsonObject.has("access_token")){
					return null;
				}
				apptoken.setAccessToken(jsonObject.getString("access_token"));
				apptoken.setRefreshToken(jsonObject.getString("refresh_token"));
				apptoken.setTime(new Date());
				apptoken.setOpenid(jsonObject.getString("openid"));
//				this.apptokenDao.updateById(apptoken);
				return apptoken;
			}
		} catch (Exception e) {
			System.out.println("微信网页授权错误返回："+jsonObject);
			if("40163".equals(jsonObject.get("errcode").toString())){
				return null;
			}
			logger.error(
					"setAccessTokenWEB获取accesstoken失败 errcode:{"
							+ jsonObject.get("errcode")
							+ "} errmsg:{" + jsonObject.getString("errmsg")
							+ "}", e);
			
		}
		return null;
	}
	
	/**
	 * 购车状态变更通知（小程序，通知两端）
	 * 
	 * {
  "touser": "OPENID",  
  "template_id": "TEMPLATE_ID", 
  "page": "index",          
  "form_id": "FORMID",         
  "data": {
      "keyword1": {
          "value": "339208499", 
          "color": "#173177"
      }, 
      "keyword2": {
          "value": "2015年01月05日 12:30", 
          "color": "#173177"
      }, 
      "keyword3": {
          "value": "粤海喜来登酒店", 
          "color": "#173177"
      } , 
      "keyword4": {
          "value": "广州市天河区天河路208号", 
          "color": "#173177"
      } 
  },
  "emphasis_keyword": "keyword1.DATA" 
}
	 * @param openId
	 * @param agentType
	 * @param name
	 * @param phone
	 * @return
	 */
	public String pushContentOfBinding(String openId,int agentType,String name,String phone,String fristRemark,Boolean isSuccess){
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		jsonObject.put("touser", openId);
		jsonObject.put("template_id", Value.Weixin.template_idBindingS);
		jsonObject.put("page", Value.Weixin.template_idBindingS);
		if(isSuccess){
			
		}else{
			jsonObject.put("template_id", Value.Weixin.template_idBindingF);
		}
		if(agentType == 1){
			jsonObject.put("url",Value.Weixin.pushBindingU);
		}else{
			jsonObject.put("url",Value.Weixin.pushBindingH);
		}
		net.sf.json.JSONObject jsonObDate = new net.sf.json.JSONObject();
		net.sf.json.JSONObject frist = new net.sf.json.JSONObject();
		frist.put("value", fristRemark);//
		frist.put("color", "#173177");
		jsonObDate.put("first", frist);
		net.sf.json.JSONObject keyword1 = new net.sf.json.JSONObject();
		if(isSuccess){
			if(StringUtil.isEmpty(name)){
				name = "扫码用户";
			}else{
				name = "扫码用户("+Base64Util.decodeData(name)+")";
			}
		}else{
			name = "失败";
		}
		keyword1.put("value", name);
		keyword1.put("color", "#173177");
		jsonObDate.put("keyword1", keyword1);
		net.sf.json.JSONObject keyword2 = new net.sf.json.JSONObject();
//		if(isSuccess){
//			phone = StringUtil.shieldPhone(phone);
//		}else{
//			if(StringUtil.isEmpty(name)){
//				phone = StringUtil.shieldPhone(phone);
//			}else{
//				phone = "扫码用户("+Base64Util.decodeData(name)+")";
//			}
//		}
		if(StringUtil.isNotEmpty(phone)){
			phone = StringUtil.shieldPhone(phone);
		}else{
			phone = "***********";
		}
		keyword2.put("value", phone);
		keyword2.put("color", "#173177");
		jsonObDate.put("keyword2", keyword2);
		net.sf.json.JSONObject keyword3 = new net.sf.json.JSONObject();
		keyword3.put("value", DateUtil.getWeixinDate());
		keyword3.put("color", "#173177");
		jsonObDate.put("keyword3", keyword3);
		net.sf.json.JSONObject remark = new net.sf.json.JSONObject();
		remark.put("value", "点击查看详情！");
		remark.put("color", "#173177");
		jsonObDate.put("remark", remark);
		jsonObject.put("data", jsonObDate);
		return jsonObject.toString();
	}
	
	/**
	 * 小U发货推送拼装
	 * @param openId
	 * @param agentType
	 * @param name
	 * @param phone
	 * @return
	 */
	public String pushContentOfDeliveryGoods(String openId,String fristRemark,String word1,String remarkWord,String deliveryCode){
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		jsonObject.put("touser", openId);
		jsonObject.put("template_id", Value.Weixin.template_deliverygoods);
		jsonObject.put("url",Value.Weixin.pushDeliverygoods+deliveryCode);
		net.sf.json.JSONObject jsonObDate = new net.sf.json.JSONObject();
		net.sf.json.JSONObject frist = new net.sf.json.JSONObject();
		frist.put("value", fristRemark);//
		frist.put("color", "#173177");
		jsonObDate.put("first", frist);
		net.sf.json.JSONObject keyword1 = new net.sf.json.JSONObject();
		keyword1.put("value", word1);
		keyword1.put("color", "#173177");
		jsonObDate.put("keyword1", keyword1);
		net.sf.json.JSONObject keyword2 = new net.sf.json.JSONObject();
		keyword2.put("value", DateUtil.getWeixinDate());
		keyword2.put("color", "#173177");
		jsonObDate.put("keyword2", keyword2);
//		net.sf.json.JSONObject keyword3 = new net.sf.json.JSONObject();
//		keyword3.put("value", DateUtil.getWeixinDate());
//		keyword3.put("color", "#173177");
//		jsonObDate.put("keyword3", keyword3);
		net.sf.json.JSONObject remark = new net.sf.json.JSONObject();
		remark.put("value", remarkWord);
		remark.put("color", "#173177");
		jsonObDate.put("remark", remark);
		jsonObject.put("data", jsonObDate);
		return jsonObject.toString();
	}
	
	/**
	 * 代理商申请审核通知拼装
	 * @param openId
	 * @param agentType
	 * @param name
	 * @param phone
	 * @return
	 */
	public String pushContentOfApply(String openId,int agentType,String name,String phone,String fristRemark,Boolean isSuccess){
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		jsonObject.put("touser", openId);
		jsonObject.put("template_id", Value.Weixin.template_idBindingF);//不需要多个模板
		jsonObject.put("url","");//不需要链接点击
		if(true){
//			jsonObject.put("url",Value.Weixin.pushBindingU);
		}else if(true){
//			jsonObject.put("url",Value.Weixin.pushBindingH);
		}else{
		}
		net.sf.json.JSONObject jsonObDate = new net.sf.json.JSONObject();
		net.sf.json.JSONObject frist = new net.sf.json.JSONObject();
		frist.put("value", fristRemark);//
		frist.put("color", "#173177");
		jsonObDate.put("first", frist);
		net.sf.json.JSONObject keyword1 = new net.sf.json.JSONObject();

		if(isSuccess){
			keyword1.put("value", "通过");
		}else{
			keyword1.put("value", "拒绝");
		}
		keyword1.put("color", "#173177");
		jsonObDate.put("keyword1", keyword1);
		net.sf.json.JSONObject keyword2 = new net.sf.json.JSONObject();
		keyword2.put("value", name);
		keyword2.put("color", "#173177");
		jsonObDate.put("keyword2", keyword2);
		net.sf.json.JSONObject keyword3 = new net.sf.json.JSONObject();
		keyword3.put("value", DateUtil.getWeixinDate());
		keyword3.put("color", "#173177");
		jsonObDate.put("keyword3", keyword3);
		net.sf.json.JSONObject remark = new net.sf.json.JSONObject();
		if(isSuccess){
			remark.put("value", "欢迎加入U视一号大家庭");
		}else{
			remark.put("value", "客服服务："+GeneralConstant.KEFU);
		}
		remark.put("color", "#173177");
		jsonObDate.put("remark", remark);
		jsonObject.put("data", jsonObDate);
		return jsonObject.toString();
	}
	
	/**
	 * 代理商的推荐者申请成为代理商的模板通知
	 * @param openId
	 * @param agentType
	 * @param name
	 * @param phone
	 * @return
	 */
	public String pushContentOfApply(String openId,String name,String fristRemark,Integer lowerAgentInfoId){
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		jsonObject.put("touser", openId);
		jsonObject.put("template_id", Value.Weixin.template_idBindingF);//不需要多个模板
		jsonObject.put("url",Value.Weixin.toExamineU.replaceAll("2131", lowerAgentInfoId+""));//点击链接
		net.sf.json.JSONObject jsonObDate = new net.sf.json.JSONObject();
		net.sf.json.JSONObject frist = new net.sf.json.JSONObject();
		frist.put("value", fristRemark);//
		frist.put("color", "#173177");
		jsonObDate.put("first", frist);
		net.sf.json.JSONObject keyword1 = new net.sf.json.JSONObject();
		keyword1.put("value", "申请中");
		keyword1.put("color", "#173177");
		jsonObDate.put("keyword1", keyword1);
		net.sf.json.JSONObject keyword2 = new net.sf.json.JSONObject();
		keyword2.put("value", name);
		keyword2.put("color", "#173177");
		jsonObDate.put("keyword2", keyword2);
		net.sf.json.JSONObject keyword3 = new net.sf.json.JSONObject();
		keyword3.put("value", DateUtil.getWeixinDate());
		keyword3.put("color", "#173177");
		jsonObDate.put("keyword3", keyword3);
		net.sf.json.JSONObject remark = new net.sf.json.JSONObject();
		remark.put("value", "点击进入审核页面");
		remark.put("color", "#173177");
		jsonObDate.put("remark", remark);
		jsonObject.put("data", jsonObDate);
		return jsonObject.toString();
	}
	
	/**
	 * 电影节活动通知模板
	 * @param openId
	 * @param agentType
	 * @param name
	 * @param phone
	 * @return
	 */
	public String pushContentOfFilmFestival(String openId,String name,String fristRemark,String state,Boolean isClick,Integer id,Date createDate){
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		jsonObject.put("touser", openId);
		jsonObject.put("template_id", Value.Weixin.template_filmFestival);//不需要多个模板
		if(isClick){
			jsonObject.put("url",Value.Weixin.filmFestivalU+id);//点击链接
		}else{
			jsonObject.put("url","");//点击链接
		}
		net.sf.json.JSONObject jsonObDate = new net.sf.json.JSONObject();
		net.sf.json.JSONObject frist = new net.sf.json.JSONObject();
		frist.put("value", fristRemark);//
		frist.put("color", "#173177");
		jsonObDate.put("first", frist);
		net.sf.json.JSONObject keyword1 = new net.sf.json.JSONObject();
		keyword1.put("value", state);
		keyword1.put("color", "#173177");
		jsonObDate.put("keyword1", keyword1);
//		net.sf.json.JSONObject keyword2 = new net.sf.json.JSONObject();
//		keyword2.put("value", name);
//		keyword2.put("color", "#173177");
//		jsonObDate.put("keyword2", keyword2);
		net.sf.json.JSONObject keyword2 = new net.sf.json.JSONObject();
		keyword2.put("value", DateUtil.getWeixinDate(createDate));
		keyword2.put("color", "#173177");
		jsonObDate.put("keyword2", keyword2);
		net.sf.json.JSONObject remark = new net.sf.json.JSONObject();
		if(isClick){
			remark.put("value", "点击查看");
		}
		remark.put("color", "#173177");
		jsonObDate.put("remark", remark);
		jsonObject.put("data", jsonObDate);
		return jsonObject.toString();
	}
	
	/**
	 * 提现审核通过后通知模板
	 * @param openId
	 * @param agentType
	 * @param name
	 * @param phone
	 * @return
	 */
	public String pushTemplateWithdrawals(String openId,String name,String fristRemark,String amount){
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		jsonObject.put("touser", openId);
		jsonObject.put("template_id", Value.Weixin.template_withdrawals);
		jsonObject.put("url","");//点击链接
		net.sf.json.JSONObject jsonObDate = new net.sf.json.JSONObject();
		net.sf.json.JSONObject frist = new net.sf.json.JSONObject();
		frist.put("value", fristRemark);//
		frist.put("color", "#173177");
		jsonObDate.put("first", frist);
		net.sf.json.JSONObject keyword1 = new net.sf.json.JSONObject();
		keyword1.put("value", name);
		keyword1.put("color", "#173177");
		jsonObDate.put("keyword1", keyword1);
		net.sf.json.JSONObject keyword2 = new net.sf.json.JSONObject();
		keyword2.put("value", amount);
		keyword2.put("color", "#173177");
		jsonObDate.put("keyword2", keyword2);
		net.sf.json.JSONObject remark = new net.sf.json.JSONObject();
		remark.put("color", "#173177");
		remark.put("value", "请注意查收");
		jsonObDate.put("remark", remark);
		jsonObject.put("data", jsonObDate);
		return jsonObject.toString();
	}
	
	/**
	 * 返利推送
	 * @param openId
	 * @param agentType
	 * @param name
	 * @param phone
	 * @return
	 */
	public String pushContentOfRebate(String openId,int agentType,String goodsName,String amount,String rebateType,String backAmount){
		net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
		jsonObject.put("touser", openId);
		jsonObject.put("template_id", Value.Weixin.template_id_Rebate);
		if(true){
			jsonObject.put("url",Value.Weixin.pushRebateU);
		}else if(true){
			jsonObject.put("url",Value.Weixin.pushRebateH);
		}else{
//			jsonObject.put("url","");
		}
		net.sf.json.JSONObject jsonObDate = new net.sf.json.JSONObject();
		net.sf.json.JSONObject frist = new net.sf.json.JSONObject();
		frist.put("value", rebateType);//返利类型
		frist.put("color", "#173177");
		jsonObDate.put("first", frist);
		net.sf.json.JSONObject keyword1 = new net.sf.json.JSONObject();
//		if(StringUtil.isEmpty(goodsName)){
//			goodsName = "商品";
//		}else{
//			goodsName = "商品("+goodsName+")";
//		}
		keyword1.put("value", goodsName);
		keyword1.put("color", "#173177");
		jsonObDate.put("keyword1", keyword1);
		net.sf.json.JSONObject keyword2 = new net.sf.json.JSONObject();
		keyword2.put("value", amount+"元");
		keyword2.put("color", "#173177");
		jsonObDate.put("keyword2", keyword2);
		net.sf.json.JSONObject keyword3 = new net.sf.json.JSONObject();
		keyword3.put("value", DateUtil.getWeixinDate());
		keyword3.put("color", "#173177");
		jsonObDate.put("keyword3", keyword3);
		net.sf.json.JSONObject remark = new net.sf.json.JSONObject();
		if(StringUtil.isNotEmpty(backAmount)){
			remark.put("value", "返利金额："+backAmount+"元");
		}
		remark.put("color", "#173177");
		jsonObDate.put("remark", remark);
		jsonObject.put("data", jsonObDate);
		return jsonObject.toString();
	}
	
	/**
	 * 微信消息模板消息推送
	 * BfjY4Zwh8W-vvC2dYdiHGsMlZRYA4h83yf-EGBnEfOU 返利模板
	 * yvv0UTaecC6trcEDtR8MZtzg228dW9UhexnVHXRW21c 绑定模板
	 */
	@Override
	public Boolean pushTemplate(String openId,int agentType,Boolean isBinding,String name,String phoneNumberOrAmount,String rebateType,String backAmountOrFristRemark
			,Boolean isSuccess){
		WeixinAppToken appToken = this.getAccessTokenApp();
		try {
			String requestUrl = Value.Weixin.pushTemplateUrl.replace("ACCESS_TOKEN",
					appToken.getAccessToken());
			if(isBinding){
				HTTPRequest.sendPostRequest(requestUrl,pushContentOfBinding(openId, agentType, name, phoneNumberOrAmount,backAmountOrFristRemark,isSuccess));
			}else{
				if(isSuccess){
					HTTPRequest.sendPostRequest(requestUrl,
							pushContentOfRebate(openId, agentType, name, phoneNumberOrAmount, rebateType, backAmountOrFristRemark));
				}else{
					HTTPRequest.sendPostRequest(requestUrl,pushContentOfBinding(openId, agentType, name, phoneNumberOrAmount,backAmountOrFristRemark,isSuccess));
//					HTTPRequest.sendPostRequest(requestUrl,
//							pushContentOfRebate(openId, agentType, name, phoneNumberOrAmount, rebateType, backAmountOrFristRemark));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	@Transactional
	public WeixinAppToken getAccessTokenWEBByRefresh() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tokenFrom", Value.Weixin.tokenFromWeb);
			List<WeixinAppToken> apptokens = this.apptokenDao
					.select(map);
			if (apptokens == null) {
				return null;
			}
			WeixinAppToken apptoken = apptokens.get(0);
			if (StringUtil.isNotEmpty(apptoken.getAccessToken())
					&& apptoken.getTime() != null) { //如果授权有效就返回
				String time = apptoken.getTime().getTime() + "";
				Date data = new Date();
				if ((data.getTime() - Long.parseLong(time)) < 7200 * 1000L) {
					return apptoken;
				}
			}
			//刷新网页授权
			//https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
			String requestUrl = Value.Weixin.getAccTokenWebByRefresh.replace(
					"APPID", apptoken.getAppId()).replace("REFRESH_TOKEN",
							apptoken.getRefreshToken());
			JSONObject jsonObject = HTTPRequest.httpsRequest(requestUrl, "GET",
					null);
			// 如果请求成功
			try {
				if (null != jsonObject) {
					/**
					 * {
						   "access_token":"ACCESS_TOKEN",
						   "expires_in":7200,
						   "refresh_token":"REFRESH_TOKEN",
						   "openid":"OPENID",
						   "scope":"SCOPE"
						}
					 */
					apptoken.setAccessToken(jsonObject.getString("access_token"));
					apptoken.setRefreshToken(jsonObject.getString("refresh_token"));
					apptoken.setTime(new Date());
					apptoken.setOpenid(jsonObject.getString("openid"));
					this.apptokenDao.updateById(apptoken);
					return apptoken;
				}
			} catch (Exception e) {
				logger.error(
						"获取accesstoken失败 errcode:{"
								+ jsonObject.getString("errcode")
								+ "} errmsg:{" + jsonObject.getString("errmsg")
								+ "}", e);
			}
		} catch (Exception e) {
			logger.error("getAccessTokenWEBByRefresh获取 accessToken 失败", e);
		}
		return null;
		
	}

	/**
	 * @Transactional public void getSignature(HttpServletRequest request, long
	 *                accountid) { WeixinAppToken apptoken =
	 *                this.getAccessToken(accountid); //
	 *                System.out.println("=============" + apptoken); String
	 *                nonce_str = "9hKgyCLgGZOgQmEI"; Date nowdate = new Date();
	 *                long timestamp = nowdate.getTime(); String ticket = null;
	 *                try { if (apptoken != null) { if
	 *                (StringUtil.isEmpty(apptoken.getJsapiTicket()) ||
	 *                apptoken.getJsapiTime() == null || (timestamp -
	 *                apptoken.getJsapiTime().getTime()) > 7200 * 1000L) { //
	 *                System.out.println("*******************"); String
	 *                requestUrl = Const.jsapi_ticket_url.replace(
	 *                "ACCESS_TOKEN", apptoken.getAccessToken()); JSONObject
	 *                jsonObject = HTTPRequest.httpsRequest( requestUrl, "GET",
	 *                null); // 如果请求成功
	 * 
	 *                try { if (null != jsonObject) { //
	 *                System.out.println(jsonObject); ticket =
	 *                jsonObject.getString("ticket"); } } catch (Exception e) {
	 *                logger.error( "获取jsapiTicket失败 errcode:{" +
	 *                jsonObject.getString("errcode") + "} errmsg:{" +
	 *                jsonObject.getString("errmsg") + "}", e); }
	 * 
	 *                if (ticket != null) { apptoken.setJsapiTicket(ticket);
	 *                apptoken.setJsapiTime(nowdate);
	 *                this.apptokenDao.updateById(apptoken); } else { return; }
	 * 
	 *                }
	 * 
	 *                String url = this.getUrl(request); String signature =
	 *                this.sign(apptoken.getJsapiTicket(), url, nonce_str,
	 *                timestamp + "");
	 *                request.getSession().setAttribute("signature", signature);
	 *                request.getSession().setAttribute("appId",
	 *                apptoken.getAppId());
	 *                request.getSession().setAttribute("nonceStr", nonce_str);
	 *                request.getSession().setAttribute("timestamp", timestamp);
	 *                } } catch (Exception e) {
	 *                logger.error("获取 jsapiTicket 失败"); } }
	 */
	public String sign(String jsapi_ticket, String url, String nonce_str,
			String timestamp) {

		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
				+ "&timestamp=" + timestamp + "&url=" + url;
		// System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = this.byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// ret.put("url", url);
		// ret.put("jsapi_ticket", jsapi_ticket);
		// ret.put("nonceStr", nonce_str);
		// ret.put("timestamp", timestamp);
		// ret.put("signature", signature);

		return signature;
	}

	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private String getUrl(HttpServletRequest request) {
		String url = request.getScheme() + "://"; // 请求协议 http 或
		// https
		url += request.getHeader("host"); // 请求服务器
		url += request.getRequestURI(); // 工程名
		if (request.getQueryString() != null) {// 判断请求参数是否为空
			url += "?" + request.getQueryString(); // 参数

		}
		return url;

	}
//	@Override
//	public String getQRticket(String userCode) throws Exception {
//		WeixinAppToken appToken = this.getAccessTokenJAVA();
//		String requestUrl = Value.Weixin.getQrImage.replace("TOKENPOST", appToken.getAccessToken());
//		//获取微信二维码
//		//https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKENPOST
//		String code = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {"
//				+ "\"scene\": {\"scene_str\": \"123\"}}}".replace("123", userCode);
//		String buffer = HTTPRequest.sendPostRequest(requestUrl,code);
//		JSONObject json = new JSONObject(buffer);
//		
//		//微信返回
////		{"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm
////			3sUw==","expire_seconds":60,"url":"http:\/\/weixin.qq.com\/q\/kZgfwMTm72WWPkovabbI"}
//		if(!json.has("ticket")){
//			return null;
//		}else{
//			return json.getString("ticket");
//		}
//	}


	@Override
	public WeixinAppToken getGPSApp() throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tokenFrom", Value.Weixin.GPSFrom);
			List<WeixinAppToken> apptokens = this.apptokenDao
					.select(map);
			if (apptokens == null || apptokens.size() == 0) {
				return null;
			}
			WeixinAppToken apptoken = apptokens.get(0);
			if (StringUtil.isNotEmpty(apptoken.getAccessToken())
					&& apptoken.getTime() != null) {
				String time = apptoken.getTime().getTime() + "";
				Date data = new Date();
				if ((data.getTime() - Long.parseLong(time)) < 7000 * 1000L) {
					return apptoken;
				}
			}
			String time = System.currentTimeMillis()+"";
			if(time.length() > 10) {
				time = time.substring(0, 10);
			}
			String requestUrl = Value.Weixin.accessGPSUrl.replace("ACCOUNT", apptoken.getAppId()).replace("TIME",
							time).replace("SINGNATURE", MD5Encoder.encodeByMD5LowerCase(MD5Encoder.encodeByMD5LowerCase(apptoken.getAppSecret())+time));
			JSONObject jsonObject = HTTPRequest.sendTheGet(requestUrl, "GET");
			// 如果请求成功
			try {
				if (null != jsonObject) {
					// System.out.println(jsonObject);
					apptoken.setAccessToken(jsonObject
							.getString("access_token"));
					apptoken.setTime(new Date());
					this.apptokenDao.updateById(apptoken);
					return apptoken;
				}
			} catch (Exception e) {
				System.out.println("请求定位设备授权失败"+jsonObject);
			}
		} catch (Exception e) {
			logger.error("getAccessTokenApp获取 accessToken 失败", e);
		}
		return null;
	}


	@Override
	public WeixinAppToken getBaiduApp() throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tokenFrom", Value.Weixin.baiduFrom);
			List<WeixinAppToken> apptokens = this.apptokenDao
					.select(map);
			if (apptokens == null || apptokens.size() == 0) {
				return null;
			}
			WeixinAppToken apptoken = apptokens.get(0);
			if (StringUtil.isNotEmpty(apptoken.getAccessToken())
					&& apptoken.getTime() != null) {
				String time = apptoken.getTime().getTime() + "";
				Date data = new Date();
				if ((data.getTime() - Long.parseLong(time)) < 2592000 * 1000L) {
					return apptoken;
				}
			}
			
			JSONObject jsonObject = getAuth(apptoken.getAppId(), apptoken.getAppSecret());
			// 如果请求成功
			try {
				if (null != jsonObject) {
					// System.out.println(jsonObject);
					apptoken.setAccessToken(jsonObject.getString("access_token"));
					apptoken.setTime(new Date());
					this.apptokenDao.updateById(apptoken);
					return apptoken;
				}
			} catch (Exception e) {
//				System.out.println("请求定位设备授权失败"+jsonObject);
			}
		} catch (Exception e) {
			logger.error("getAccessTokenApp获取 accessToken 失败", e);
		}
		return null;
	}
	
	 /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static JSONObject getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            return jsonObject;
//            String access_token = jsonObject.getString("access_token");
//            return access_token;
        } catch (Exception e) {
            System.err.printf("获取百度图片识别授权token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    
	@Override
	public String getQRticket(String userCode) throws Exception {
		WeixinAppToken appToken = this.getAccessTokenJAVA();
		String requestUrl = Value.Weixin.getQrImage.replace("TOKENPOST", appToken.getAccessToken());
		//获取微信二维码
		//https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKENPOST
		String code = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {"
				+ "\"scene\": {\"scene_str\": \"123\"}}}".replace("123", userCode);
		String buffer = HTTPRequest.sendPostRequest(requestUrl,code);
		JSONObject json = new JSONObject(buffer);
		System.out.println("请求生成二维码数据返回："+json);
		//微信返回
//		{"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm
//			3sUw==","expire_seconds":60,"url":"http:\/\/weixin.qq.com\/q\/kZgfwMTm72WWPkovabbI"}
		if(!json.has("ticket")){
			return null;
		}else{
			return json.getString("ticket");
		}
	}
}
