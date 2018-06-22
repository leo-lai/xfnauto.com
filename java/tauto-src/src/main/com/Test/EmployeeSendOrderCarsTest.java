package main.com.Test;

import java.util.HashMap;
import java.util.Map;

import main.com.utils.StringUtil;

/**
 * 二级主动给三级下单测试类
 * @author Zwen
 *
 */
public class EmployeeSendOrderCarsTest {

	private String WEBURL = "http://127.0.0.1:8080/tauto";
//	private String WEBURL = "http://admin.mifengqiche.com/tauto/";

	/**
	 * 获取门店列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void organizationList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "9ce547739b19b5ea1f9f0d20e16eab1f");//登录标识
		String url =WEBURL + "/emInterface/employee/organizationList";
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
	public void organizationEdit() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "9ce547739b19b5ea1f9f0d20e16eab1f");//登录标识
		createMap.put("orgId", "");//组织主键，新增不需要
		createMap.put("telePhone", "15798431689");//联系人电话
		createMap.put("linkMan", "linkMan");//联系人姓名
		createMap.put("shortName", "我要新建组织");//组织名称
		createMap.put("provinceId", "100000");//省
		createMap.put("cityId", "110100");//市
		createMap.put("areaId", "110101");//区
		createMap.put("address", "1");//街道路牌门号地址
		createMap.put("latitude", "2548.021584");//经度
		createMap.put("longitude", "28.021584");//纬度
		String url =WEBURL + "/emInterface/employee/organizationEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
}
