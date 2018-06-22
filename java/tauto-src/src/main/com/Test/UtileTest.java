package main.com.Test;

import java.util.HashMap;
import java.util.Map;

public class UtileTest {
	private String WEBURL = "http://localhost:8080/useeproject";

	@org.junit.Test
	public void changeOrder() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();  
		createMap.put("superUserId", "120");
		String url =WEBURL + "/utils/contorller/changeOrder";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	
	@org.junit.Test
	public void updateUserInfo() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();  
//		createMap.put("superUserId", "120");
		String url =WEBURL + "/utils/contorller/updateUserInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	
	@org.junit.Test
	public void updateAgentInfo() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();  
//		createMap.put("superUserId", "120");
		String url =WEBURL + "/utils/contorller/updateAgentInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	
	@org.junit.Test
	public void createTickte() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();  
		String url =WEBURL + "/utils/contorller/createTickte";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	
	@org.junit.Test
	public void downloadWXQR() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>(); 
		createMap.put("url", "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQEB8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyc1luQkFScmFmWGwxMDAwMDAwN2cAAgRiND5ZAwQAAAAA");
		String url =WEBURL + "/interface/shop/downloadWXQR";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
}
