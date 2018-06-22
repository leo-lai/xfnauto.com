package main.com.Test;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/** 
* @author liaozijie 
* @version 创建时间：2018年1月16日 下午4:11:02 
* 类描述： 司机端测试用例
*/
public class DriverTest {
	
	private String WEBURL = "http://127.0.0.1:8080/tauto";
	

	/**
	 * 登录
	 * date: 2018年1月17日 下午5:05:32
	 * @throws Exception
	 */
	@org.junit.Test
	public void login(){
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("userName", "15600000000");//用户名
		createMap.put("password", "15600000000");//密码
		String url =WEBURL + "/driver/login";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 登出
	 * date: 2018年1月17日 下午5:05:38
	 * @throws Exception
	 */
	@org.junit.Test
	public void loginOut() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "zwen");//用户名
		String url =WEBURL + "/driver/loginOut";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 更改密码
	 * date: 2018年1月17日 下午5:05:54
	 * @throws Exception
	 */
	@org.junit.Test
	public void changePassword() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "zwen");//用户名
		createMap.put("password", "Password");//验证码暂时不考虑
		createMap.put("passwordOld", "Password");//验证码暂时不考虑
		String url =WEBURL + "/management/admin/changePassword";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 司机端查询配送单
	 * date: 2018年1月16日 下午4:10:38
	 */
	@org.junit.Test
	public void queryDriverDistributions() {
		Map<String,String> createMap = new HashMap();
		createMap.put("sessionId", "793adf643db8cf54ceb07fdd25f8f10b");
		createMap.put("distributionState", "3");//状态 -1取消 1等待配送 2配送中 3完成
		String url = WEBURL + "/driver/queryDriverDistributions";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
		
	}
	
	/**
	 * 开始配送
	 * date: 2018年1月17日 下午12:03:25
	 */
	@org.junit.Test
	public void startDelivery() {
		Map<String,String> createMap = new HashMap();
		createMap.put("sessionId", "9b9775ead560346a71bcc7a1b7354bde");
		createMap.put("distributionId", "1");//配送单ID
		String url = WEBURL + "/driver/startDelivery";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);	
	}
	
	/**
	 * 完成配送
	 * date: 2018年1月17日 下午12:03:40
	 */
	@org.junit.Test
	public void endDelivery() {
		Map<String,String> createMap = new HashMap();
		createMap.put("sessionId", "9b9775ead560346a71bcc7a1b7354bde");
		createMap.put("distributionId", "1");//配送单ID
		String url = WEBURL + "/driver/endDelivery";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
		
	}
	
	/**
	 * 货物车图片上传
	 * date: 2018年1月17日 上午10:12:51
	 */
	@org.junit.Test
	public void uploadPic() {
		Map<String,String> createMap = new HashMap();
		createMap.put("sessionId", "9b9775ead560346a71bcc7a1b7354bde");
		createMap.put("goodsCarId", "6");//货物车ID
		createMap.put("type", "2");//上传图片类型 1：装车图片 2：卸车图片
		createMap.put("picUrl", "endLoadCarPicUrl");//上传图片地址
		String url = WEBURL + "/driver/uploadPic";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}

}
