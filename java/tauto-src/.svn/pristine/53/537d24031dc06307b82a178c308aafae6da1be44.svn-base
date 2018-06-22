package main.com.Test;

import java.util.HashMap;
import java.util.Map;

public class AppTest {
//	private String WEBURL = "http://127.0.0.1:8080/tauto";
	private String WEBURL = "http://admin.mifengqiche.com/tauto/";

	/**
	 * 小程序登录
	 * @throws Exception
	 */
	@org.junit.Test
	public void login() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("code", "013OqqRN0wz4d525iuSN0jVIRN0OqqRa");//小程序返回的code
		createMap.put("rawData", "{\"nickName\":\"LAI\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Guangzhou\",\"province\":\"Guangdong\",\"country\":\"China\",\"avatarUrl\":\"https://wx.qlogo.cn/mmopen/vi_32/MJdzHRsaDY4rQVefVpUohjAquwEFzzQtIVCic9X1FC2laFPHuUxOLqDKsLdbzlibmtaDvUeNfLtYkKCQepqdlMcQ/0\"}");//小程序返回的rawData
		createMap.put("signature", "c4c19940bff6871efb3da19ce4b84860919c87cc");//小程序返回的signature
		createMap.put("encryptedData", "gKuvDU69VhmdSMdsX4kuwBwACTSOy5BOlKN1SXpvP/wrO8YxYTrD/A0aoW0mnqFgR+4oEG9nhnvl8D9e7utqOtmUETtv8rlQt/1n7qN8ZhUgsiCaD8Re3fJCCTe4j9/42C01BPJMC7SbY+LHQvnosgVmtetCum1m8qgph2y+bVwN9IJQnjAVZd+yIPQPPnP6KVAUA84FNcGPkwTbH/xADT2Zb9PM1NlMKqtsaz/7oyu6txNf9k7IcjaXlNIv6+/Juu40xipUC1+MMIj4dxj6PlY+RzCGB7Ribup1Er4boSTXIc5HnrezVX4AsLf/7YWC19pQVlYneS06h2xTuMADJla3Oe4uQKE0HA43NxXRErO0xKUOybwrY/axYDihGoOopGElxWkOapH00DohsM+1BEyD1DUYn3fMiS1sRzuzNGfGlAWkMI3Z2eeq9ZhJYu6uGvOYp20vJ0lsUVuspoZaxh6aExbKiO403oJvF3gKIBk=\r\n" + 
				"iv:\r\n" + 
				"dEAQ4+1FHJn3ZwA6S+8DNg==");//小程序返回的encryptedData
		createMap.put("iv", "");//小程序返回的iv
		String url =WEBURL + "/interface/weixinapp/login";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 小程序登录
	 * @throws Exception
	 */
	@org.junit.Test
	public void refreshMyInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "");//登录标识
		String url =WEBURL + "/interface/weixinapp/refreshMyInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 获取车辆品牌列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsBrandList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "7F5FB737F6734EB27B012764AEC85BF1");
		String url =WEBURL + "/interface/cars/carsBrandList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取车大类列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "0517bb549ec5f3096f3c39b9f8a23418");//登录标识
//		createMap.put("minPrice", "50000");//低价
//		createMap.put("maxPrice", "100000");//高价
		createMap.put("vehicleName", "SUV");//车型
//		createMap.put("brandId", "1");//登录标识
//		createMap.put("carName", "本田雅阁");//登录标识
//		createMap.put("page", "0");//登录标识
//		createMap.put("rows", "10");//登录标识
		String url =WEBURL + "/interface/cars/carsList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 获取车辆详情
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "055daea8366d5cb2547fb20b0a77f4cd");//登录标识
		createMap.put("carId", "1016");
		String url =WEBURL + "/interface/cars/carsInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 获取车辆介绍
	 * @throws Exception
	 */
	@org.junit.Test
	public void carIntroduce() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "2bf1134a4589e643856bfdcd911da2bc");//登录标识
		createMap.put("carId", "4124");
		String url =WEBURL + "/interface/cars/carIntroduce";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 获取年款列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsYearPatternList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "2bf1134a4589e643856bfdcd911da2bc");//登录标识
		createMap.put("brandId", "1");
		createMap.put("familyId", "22");
		String url =WEBURL + "/interface/cars/carsYearPatternList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 根据车系获取车大类列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsFamilyList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "055daea8366d5cb2547fb20b0a77f4cd");//登录标识
		createMap.put("familyId", "72");
//		createMap.put("yearPattern", "2017");
		String url =WEBURL + "/interface/cars/carsFamilyList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 问题列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carProblemList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "2bf1134a4589e643856bfdcd911da2bc");//登录标识
		String url =WEBURL + "/interface/cars/carProblemList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 预约前置
	 * @throws Exception
	 */
	@org.junit.Test
	public void bespeakBefor() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "c0bc3fbeb966d22c3bad473ddb6375a4");//登录标识
		createMap.put("carId", "68");//车大类ID
//		createMap.put("interiorId", "2");//内饰
		createMap.put("colourId", "71");//颜色ID
		createMap.put("latitude", "113.280734");//维度
		createMap.put("longitude", "23.083247");//经度
		createMap.put("isDistance", "0");//1 按照距离排序 0按照优惠排序 默认按照
		String url =WEBURL + "/interface/weixinapp/bespeakBefor";
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
		String url =WEBURL + "/interface/weixinapp/bespeak";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 获取车辆参数配置
	 * @throws Exception
	 */
	@org.junit.Test
	public void carParameter() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "2bf1134a4589e643856bfdcd911da2bc");//登录标识
		createMap.put("carId", "4130");//车大类
		String url =WEBURL + "/interface/cars/carParameter";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 购车计算器全款
	 * @throws Exception
	 */
	@org.junit.Test
	public void fullPayment() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "3126fed5b27403eef4696a46b8025c82");//登录标识
		createMap.put("carId", "4146");//车大类
		String url =WEBURL + "/interface/cars/fullPayment";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 购车计算器贷款
	 * @throws Exception
	 */
	@org.junit.Test
	public void loanPayment() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "3126fed5b27403eef4696a46b8025c82");//登录标识
		createMap.put("carId", "4146");//车大类
		createMap.put("paymentRatio", "0.3");//首付比例
		createMap.put("timeOfPayment", "1");//还款年限
		String url =WEBURL + "/interface/cars/loanPayment";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 获取订单详情
	 * @throws Exception
	 */
	@org.junit.Test
	public void orderInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "2bf1134a4589e643856bfdcd911da2bc");//登录标识
		String url =WEBURL + "/interface/order/orderInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 订单跟踪列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void orderTrack() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "26f532d5353e06b7651dc39664dd2b23");//登录标识
		createMap.put("customerOrderId", "35");//登录标识
		String url =WEBURL + "/interface/order/orderTrack";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 手机号码绑定接口
	 * @throws Exception
	 */
	@org.junit.Test
	public void phonebinding() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "2bf1134a4589e643856bfdcd911da2bc");//登录标识
		createMap.put("phoneCode", "7894");//验证码
		createMap.put("phoneNumber", "198765498317");//登录标识
		String url =WEBURL + "/interface/weixinapp/phonebinding";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	/**
	 * 手机号码绑定接口
	 * @throws Exception
	 */
	@org.junit.Test
	public void phoneVerifyCode() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "2bf1134a4589e643856bfdcd911da2bc");//登录标识
		createMap.put("phoneNumber", "198765498317");//登录标识
		String url =WEBURL + "/interface/weixinapp/phoneVerifyCode";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
}
