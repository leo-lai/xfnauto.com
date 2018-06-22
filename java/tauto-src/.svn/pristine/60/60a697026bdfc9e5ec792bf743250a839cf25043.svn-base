package main.com.Test;

import java.util.HashMap;
import java.util.Map;

public class GeneralizeTest {
	private String WEBURL = "http://127.0.0.1:8080/tauto";

	/**
	 * 获取门店列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void organizationList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "055daea8366d5cb2547fb20b0a77f4cd");
		String url =WEBURL + "/interface/generalize/organizationList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 预约
	 * @throws Exception
	 */
	@org.junit.Test
	public void bespeak() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6924498317b217162e93d113c5a77ef4");//登录标识
		createMap.put("orgId", "1");//门店ID
		createMap.put("carId", "205");//车大类
		createMap.put("customerUsersName", "名称");//姓名
		createMap.put("phoneNumber", "19876498399");//电话
		createMap.put("orgId", "58");//选择的门店ID
		createMap.put("carPurchaseIntention", "1");//1一天 2三天 3一周
		String url =WEBURL + "/interface/generalize/bespeak";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	
	/**
	 * 获取推广预约信息
	 * @throws Exception
	 */
	@org.junit.Test
	public void generalizeInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6924498317b217162e93d113c5a77ef4");//登录标识
		String url =WEBURL + "/interface/generalize/generalizeInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
}
