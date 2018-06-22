package main.com.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

/**
 * 小程序的获取用户信息的公共类
 * @author User
 *
 */
public class UserInfoUtil {
	
	//员工端小程序ID
	private static final String EMP_APPID = "wx8925785b341446d8";
	//员工端小程序秘钥
	private static final String EMP_APPSERECT = "faae07ae361212c1130420fba37ff387";
	//司机端端小程序ID
	private static final String EMP_APPID_DRIVER = "wxdabe61514b9aead4";
	//司机端小程序秘钥
	private static final String EMP_APPSERECT_DRIVER = "dcd38a580d0b3cb0ffc7cbcf65d59947";

	//获取code的请求地址
	public static String Get_Code ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STAT#wechat_redirect";
	//替换字符串
	public static String getCode(String APPID,String REDIRECT_URI,String SCOPE) {
	return String.format(Get_Code,APPID,REDIRECT_URI,SCOPE);
	}
	//获取Web_access_tokenhttps的请求地址
	public static String Web_access_tokenhttps ="https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
	//替换字符串
	public static String getWebAccess(String CODE) {  
	return String.format(Web_access_tokenhttps,"wx6bd022401f09940b",/*这里填小程序的appid*/"7608768e7e5c2cae67e175953178b626",/*这里填该小程序的SECRET*/CODE);
	}
	
	
	//有人很无聊：wx032cb58d1eb2fe3e   c8ddbe8e2aba9bab47a9ecec761fad0b
	//淘宝用车快
	/*
	 * AppID(小程序ID)  wx6bd022401f09940b 
AppSecret(小程序密钥) 7608768e7e5c2cae67e175953178b626
	 */
	//拉取用户信息的请求地址
	public static String User_Message ="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
	//替换字符串
	public static String getUserMessage(String access_token, String openid) {
	return String.format(User_Message,access_token,openid);
	}
	
	
	/**
	 * 
	 * date: 2018年1月19日 上午11:54:01
	 * @param code
	 * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据，详细见加密数据解密算法
	 * @param iv 加密算法的初始向量，详细见加密数据解密算法
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getUserInfo(String code,String encryptedData,String iv) throws Exception {
		String token = String.format(Web_access_tokenhttps,EMP_APPID,EMP_APPSERECT,code);// 通过自定义工具类组合出小程序需要的登录凭证
		JSONObject jsonObject = HTTPRequest.sendTheGet(token, "GET");
		System.out.println("jsonObject:"+jsonObject);
		if(jsonObject.has("session_key")) {
			String sessionKey = jsonObject.getString("session_key");
		    AES aes = new AES();
	        byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
	        if(null != resultByte && resultByte.length > 0) {
	        	String userInfo = new String(resultByte, "UTF-8");
	        	return new JSONObject(userInfo);
	        }
		}
        return null;
	}
	/**
	 * 
	 * date: 2018年1月19日 上午11:54:01
	 * @param code
	 * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据，详细见加密数据解密算法
	 * @param iv 加密算法的初始向量，详细见加密数据解密算法
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getDriverInfo(String code,String encryptedData,String iv) throws Exception {
		String token = String.format(Web_access_tokenhttps,EMP_APPID_DRIVER,EMP_APPSERECT_DRIVER,code);// 通过自定义工具类组合出小程序需要的登录凭证
		JSONObject jsonObject = HTTPRequest.sendTheGet(token, "GET");
		System.out.println("jsonObject:"+jsonObject);
		if(jsonObject.has("session_key")) {
			String sessionKey = jsonObject.getString("session_key");
			AES aes = new AES();
			byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
			if(null != resultByte && resultByte.length > 0) {
				String userInfo = new String(resultByte, "UTF-8");
				return new JSONObject(userInfo);
			}
		}
		return null;
	}
}
