package main.com.Test;

import java.util.HashMap;
import java.util.Map;

public class ImageTest {

	private String WEBURL = "http://localhost:8080/tauto";
	
	/**
	 * 代理商列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void uploadImage() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();  
		createMap.put("sessionId", "E05A6FA4253BDB2EADBDE83157BFBD0D");
		createMap.put("img_file", "");//图片上传名称
		String url =WEBURL + "/management/admin/uploadImage";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	
	/**
	 * 代理商列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void imageRecognition() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();  
		createMap.put("url", "http://opii7iyzy.bkt.clouddn.com/20180307170925.jpg");
		String url =WEBURL + "/common/imageRecognition";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
}
