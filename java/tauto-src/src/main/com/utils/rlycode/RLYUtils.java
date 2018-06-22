package main.com.utils.rlycode;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Set;

import main.com.utils.StringUtil;

public class RLYUtils {
	
	public static SimpleDateFormat formatToSystem = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static SimpleDateFormat formatFromRLY = new SimpleDateFormat("yyyyMMddhhmmss");

	/**
	 * 正常挂机
	 */

	public static Integer CUT_1 = 1;// 通话中取消回拨、直拨和外呼的正常结束通话

	public static Integer CUT_2 = 2;// 账户欠费或者设置的通话时间到

	public static Integer CUT_3 = 3;//回拨通话中主叫挂断，正常结束通话

	public static Integer CUT_4 = 4;//回拨通话中被叫挂断，正常结束通话

	/**
	 * 通用类型
	 */

	public static Integer CUT_M_1 = -1;// 被叫没有振铃就收到了挂断消息

	public static Integer CUT_M_2 = -2;// 呼叫超时没有接通被挂断

	public static Integer CUT_M_5 = -5;// 被叫通道建立了被挂断

	public static Integer CUT_M_6 = -6;// 系统鉴权失败

	public static Integer CUT_M_7 = -7;// 第三方鉴权失败

	public static Integer CUT_M_11 = -11;// 账户余额不足

	/**
	 * 直拨类型
	 */

	public static Integer CUT_M_8 = -8;// 直拨被叫振铃了挂断

	/**
	 * 回拨类型
	 */

	public static Integer CUT_M_3 = -3;// 回拨主叫接通了主叫挂断

	public static Integer CUT_M_4 = -4;// 回拨主叫通道创建了被挂断

	public static Integer CUT_M_9 = -9;// 回拨被叫振铃了挂断

	public static Integer CUT_M_10 = -10;// 回拨主叫振铃了挂断

	public static Integer CUT_M_14 = -14;//回拨取消呼叫(通过取消回拨接口)
	
	/**
	 * 容联云相关连接信息
	 */
//	private static String YLYHTTP = "sandboxapp.cloopen.com";
	
	private static String YLYHTTP = "app.cloopen.com";
	
	private static String YLYPORT = "8883";
	
	private static String ACCOUNT = "8a48b5514a27bb6c014a31f10cee0629";
	
	private static String TOKEN = "a2e882de6cf044a3ae3e83d5aac3ccc6";
	
	//子账号
	private static String CHILDACCOUNT = "d8f39a64142d11e79eaa6c92bf2c165d";
	//子账号密码
	private static String CHILDTOKEN = "e2c25ded64c44eabf9f4c91762fc9411";
	
	//应用ID
	private static String APPID = "8a48b5514ee36774014ef7cee46618a7";
	
	//短信模板
	private static String MODEID = "222768";
	
	//短信模板
	private static String MODEID_INPAY = "168686";
	
	//短信模板
	private static String MODEID_FILM = "194512";
	
	//短信模板(派单时通知司机)
	private static String MODEID_DRIVER = "239007";
	
	//短信模板(车辆到达，通知接车人)
//	private static String MODEID_FILM = "194512";
	
	//回调地址
	private static String BACKHTTP_REAL = "";
	
	private static String BACKHTTP_TEST = "";
	
	public static CCPRestSDK getCCPRestSDK(){
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(YLYHTTP, YLYPORT);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setSubAccount(CHILDACCOUNT, CHILDTOKEN);// 初始化子帐号和子帐号TOKEN
		restAPI.setAppId(APPID);// 初始化应用ID
		return restAPI;
	}
	
	//双向回拨
	public static HashMap<String,Object> callBackBoth(String from,String to){
		System.out.println("双向回拨开始.......");
//		if (!PhoneUtil.isMobile(from)) {
//			if (!PhoneUtil.isPhone(from)) {
//				System.out.println("退出2");
//		           return null;
//		    }
//	    }
//		if (!PhoneUtil.isMobile(to)) {
//			if (!PhoneUtil.isPhone(to)) {
//				System.out.println("退出2");
//		           return null;
//		    }
//	    }
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = getCCPRestSDK();
		System.out.println("通话");
		result = restAPI.callback(from, to, "02038603199", "02038603199", null, null, null, null, null,
				BACKHTTP_REAL, "1", "1", null, null);
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
			HashMap<String,Object> callBack = (HashMap<String, Object>) data.get("CallBack");
			return callBack;
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			return null;
		}
	}
	
	//取消回拨
	public static Boolean callCancel(String callSid){
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = getCCPRestSDK();
		result = restAPI.CallCancel(callSid, "0");
		if("000000".equals(result.get("statusCode"))){
			return true;
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			return false;
		}
	}
	
	
	//外呼通知
	public static Boolean landingCall(String phone,String wavName){
		if(StringUtil.isEmpty(phone)){
			return null;
		}
		HashMap<String, Object> result = null;

		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(YLYHTTP, YLYPORT);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(RLYUtils.ACCOUNT, RLYUtils.TOKEN);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(APPID);// 初始化应用ID
		//type=1，则播放默认语音文件,0是自定义语音文件 
		//result = restAPI.landingCall("号码1", "语音文件名", "语音文本", "显示的主叫号码", "循环播放次数",1,"回调地址", "用户私有数据", "最大通话时长", "发音速度", "音量", "音调", "背景音编号");

		result = restAPI.landingCall(phone, wavName, "", "02038603199", "3",0,
				"","", "", "", "", "", "");
//		System.out.println("SDKTestLandingCall result=" + result);
		
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
			return true;
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			return false;
		}
	}
	
	/**
	 * 发送短信模板请求
	 * 
	 * @param to
	 *            必选参数 短信接收端手机号码集合，用英文逗号分开，每批发送的手机号数量不得超过100个
	 * @param templateId
	 *            必选参数 模板Id
	 * @param datas
	 *            可选参数 内容数据，用于替换模板中{序号}
	 * @return
	 */
	public static Boolean sendTemplateSMS(String phone,String code){
		if(StringUtil.isEmpty(phone)){
			return null;
		}
		if(!PhoneUtil.isPhone(phone)){
			return null;
		}
		HashMap<String, Object> result = null;
		
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(YLYHTTP, YLYPORT);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(RLYUtils.ACCOUNT, RLYUtils.TOKEN);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(APPID);// 初始化应用ID
		//type=1，则播放默认语音文件,0是自定义语音文件 
		//result = restAPI.landingCall("号码1", "语音文件名", "语音文本", "显示的主叫号码", "循环播放次数",1,"回调地址", "用户私有数据", "最大通话时长", "发音速度", "音量", "音调", "背景音编号");
		
		result = restAPI.sendTemplateSMS(phone, MODEID,new String[]{code,"120"});
//		System.out.println("SDKTestLandingCall result=" + result);
		
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
			return true;
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			return false;
		}
	}
	
	/**
	 * 发送短信模板请求
	 * 
	 * @param to
	 *            必选参数 短信接收端手机号码集合，用英文逗号分开，每批发送的手机号数量不得超过100个
	 * @param templateId
	 *            必选参数 模板Id
	 * @param datas
	 *            可选参数 内容数据，用于替换模板中{序号}
	 * @return
	 */
	public static Boolean sendTemplateInPay(String phone){
		if(StringUtil.isEmpty(phone)){
			return null;
		}
		if(!PhoneUtil.isPhone(phone)){
			return null;
		}
		HashMap<String, Object> result = null;
		
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(YLYHTTP, YLYPORT);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(RLYUtils.ACCOUNT, RLYUtils.TOKEN);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(APPID);// 初始化应用ID
		//type=1，则播放默认语音文件,0是自定义语音文件 
		//result = restAPI.landingCall("号码1", "语音文件名", "语音文本", "显示的主叫号码", "循环播放次数",1,"回调地址", "用户私有数据", "最大通话时长", "发音速度", "音量", "音调", "背景音编号");
		
		result = restAPI.sendTemplateSMS(phone, MODEID_INPAY,new String[]{});
//		System.out.println("SDKTestLandingCall result=" + result);
		
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
			return true;
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			return false;
		}
	}
	/**
	 * 发送短信模板请求
	 * 
	 * @param to
	 *            必选参数 短信接收端手机号码集合，用英文逗号分开，每批发送的手机号数量不得超过100个
	 * @param templateId
	 *            必选参数 模板Id
	 * @param datas
	 *            可选参数 内容数据，用于替换模板中{序号}
	 * @return
	 */
	public static Boolean sendTemplateNotice(String phone,String modeid,String[] parameter){
		if(StringUtil.isEmpty(phone)){
			return null;
		}
		if(!PhoneUtil.isPhone(phone)){
			return null;
		}
		HashMap<String, Object> result = null;
		
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(YLYHTTP, YLYPORT);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(RLYUtils.ACCOUNT, RLYUtils.TOKEN);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(APPID);// 初始化应用ID
		//type=1，则播放默认语音文件,0是自定义语音文件 
		//result = restAPI.landingCall("号码1", "语音文件名", "语音文本", "显示的主叫号码", "循环播放次数",1,"回调地址", "用户私有数据", "最大通话时长", "发音速度", "音量", "音调", "背景音编号");
		if(parameter == null) {
			parameter = new String[]{};
		}
		result = restAPI.sendTemplateSMS(phone, modeid,parameter);
		System.out.println("SDKTestLandingCall result=" + result);
		
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
//			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//			Set<String> keySet = data.keySet();
//			for(String key:keySet){
//				Object object = data.get(key);
//				System.out.println(key +" = "+object);
//			}
			return true;
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			return false;
		}
	}
	
	/**
	 * 发送短信模板请求
	 * 
	 * @param to
	 *            必选参数 短信接收端手机号码集合，用英文逗号分开，每批发送的手机号数量不得超过100个
	 * @param templateId
	 *            必选参数 模板Id
	 * @param datas
	 *            可选参数 内容数据，用于替换模板中{序号}
	 * @return
	 */
	public static Boolean sendTemplateInFilm(String phone,String projectSubject,String number,String addressAndTime,String kefu){
		if(StringUtil.isEmpty(phone)){
			return null;
		}
		if(!PhoneUtil.isPhone(phone)){
			return null;
		}
		HashMap<String, Object> result = null;
		
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(YLYHTTP, YLYPORT);// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(RLYUtils.ACCOUNT, RLYUtils.TOKEN);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(APPID);// 初始化应用ID
		//type=1，则播放默认语音文件,0是自定义语音文件 
		//result = restAPI.landingCall("号码1", "语音文件名", "语音文本", "显示的主叫号码", "循环播放次数",1,"回调地址", "用户私有数据", "最大通话时长", "发音速度", "音量", "音调", "背景音编号");
		
		result = restAPI.sendTemplateSMS(phone,MODEID_FILM,new String[]{projectSubject,number,addressAndTime,kefu});
//		System.out.println("SDKTestLandingCall result=" + result);
		
		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				System.out.println(key +" = "+object);
			}
			return true;
		}else{
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
			return false;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		callBackBoth("02038603199","18607180015");
//		callCancel("1606141425053096000600150005edad");
//		landingCall("18607180015","newOrder.wav");
		sendTemplateSMS("15875319507","4409");
	}
	
}
