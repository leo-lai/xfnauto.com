package main.com.Test;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class EmployeeTest {
	
	private String WEBURL = "http://127.0.0.1:8080/tauto";	
	/**
	 * 小程序登录
	 * @throws Exception
	 */
	@org.junit.Test
	public void login() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("nikeName", "admin");//昵称
		createMap.put("phoneNumber", "13800000009");//昵称
		createMap.put("password", "1");//密码
		createMap.put("headPortrait", "https://wx.qlogo.cn/mmopen/vi_32/MJdzHRsaDY4rQVefVpUohjAquwEFzzQtIVCic9X1FC2laFPHuUxOLqDKsLdbzlibmtaDvUeNfLtYkKCQepqdlMcQ/0");//昵称
		createMap.put("code", "");
		createMap.put("encryptedData", "");//包括敏感数据在内的完整用户信息的加密数据，详细见加密数据解密算法
		createMap.put("iv", "");//加密算法的初始向量，详细见加密数据解密算法
		String url =WEBURL + "/emInterface/employee/login";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);			
		System.out.println(msgString);
	}
	
	@org.junit.Test
	public void loginOut() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "0DB85CD5FEC1108251FAD4DC9D7CFC25");//用户名
		String url =WEBURL + "/emInterface/employee/loginOut";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	@org.junit.Test
	public void changePassword() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "2bf1134a4589e643856bfdcd911da2bc");//用户名
		createMap.put("password", "Password");//验证码暂时不考虑
		createMap.put("passwordOld", "1");//验证码暂时不考虑
		String url =WEBURL + "/emInterface/employee/changePassword";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 车辆库存车辆列表（车辆库存）
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockCarList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "0daa0dc54b1b88dfbaa0fe59b5aabcb6");
//		createMap.put("brandId", "43");//品牌ID
//		createMap.put("carsInfo", "宝马");//文字搜索
		String url =WEBURL + "/emInterface/employee/stockCarList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 获取车辆品牌列表（车辆库存）
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsBrandList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "7F5FB737F6734EB27B012764AEC85BF1");
		String url =WEBURL + "/emInterface/employee/carsBrandList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 查看车辆类型具体库存（车辆库存）
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockCarInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");
		createMap.put("carsId", "205");//车大类ID
		createMap.put("colourId", "66");//颜色ID
		createMap.put("interiorId", "52");//内饰ID
		String url =WEBURL + "/emInterface/employee/stockCarInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 *  获取自身或者下级架构列表（下拉列表）
	 * @throws Exception
	 */
	@org.junit.Test
	public void organizationLevelList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e933fa5dc8dd414508a9f3b364742b2a");
		String url =WEBURL + "/emInterface/employee/organizationLevelList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 *  输入电话号码搜索电话号码列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerPhoneSearchList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "864ef366d810c83df15e253048b26d6b");
		createMap.put("phoneNumber", "18");
		String url =WEBURL + "/emInterface/employee/customerPhoneSearchList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 *  预约客户列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void bespeakCustomerOrgList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e933fa5dc8dd414508a9f3b364742b2a");
		String url =WEBURL + "/emInterface/employee/bespeakCustomerOrgList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 *  落定客户/贷款通过客户/待完款客户/待加装上牌客户/待提车客户列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void orderStateCustomerList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "576e10ceaf332f970dfff474ea14432a");
		createMap.put("customerType", "2");//1.落定客户/2.贷款通过客户/3.待完款客户/4.待加装上牌客户/5.待提车客户列表
		String url =WEBURL + "/emInterface/employee/orderStateCustomerList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 添加新用户
	 * @throws Exception
	 */
	@org.junit.Test
	public void addCustomerUsersr_() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "27709d8942afdb19e8bc11236af41851");
		createMap.put("customerUsersName", "莉莉李");//客户姓名CustomerUsersName
		createMap.put("phoneNumber", "15873926150");//客户电话
		createMap.put("carPurchaseIntention", "1");//购车意向
		createMap.put("intentionCarId", "71");//意向车型
		createMap.put("expectWayId", "1");//购车方式
		createMap.put("expectWayId", "1");//购车方式
		createMap.put("remarks", "备注测试");         //备注
		createMap.put("appointmentDate", "2017/11/9");//预约到店日期
		createMap.put("timeOfAppointment", "10:50-20:09");//预约时间
		String url =WEBURL + "/emInterface/employee/addCustomerUsersr";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 客户详细信息
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerUsersrInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "78bac0f189c8ff9aac3a50f10a5b01b9");
		createMap.put("customerUsersId", "1");//客户ID
		String url =WEBURL + "/emInterface/employee/customerUsersrInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 添加备注
	 * @throws Exception
	 */
	@org.junit.Test
	public void addCustomerRemarks() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "24834225738d1934501da32f7d921aa0");
		createMap.put("customerUsersId", "45");//需要备注的用户ID
		createMap.put("remarksContent", "备注备注4");//备注
		String url =WEBURL + "/emInterface/employee/addCustomerRemarks";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}	
	/**
	 * 上传文件
	 * @throws Exception
	 */
	@org.junit.Test
	public void uploadFile() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		//文件名称 img_file
		String url =WEBURL + "/emInterface/employee/uploadFile";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}	
	/**
	 * 添加银行审核资料
	 * @throws Exception
	 */
	@org.junit.Test
	public void addBankAudits() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("customerUsersId", "1");//客户ID		
		createMap.put("bankAuditsImage", "");//多张图片使用英文逗号隔开
		createMap.put("bankAuditsvideo", "");//视频地址		
		String url =WEBURL + "/emInterface/employee/addBankAudits";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}	
	/**
	 * 获取车辆车系列表(下拉)
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsFamilyList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("brandId", "2");
		String url =WEBURL + "/emInterface/employee/carsFamilyList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取车大类列表（下拉）
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsListList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("familyId", "187");
		String url =WEBURL + "/emInterface/employee/carsListList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取待出库订单列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerOrderList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("carsSearch", "DD122334");//搜索参数
		String url =WEBURL + "/emInterface/employee/customerOrderList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取待出库订单详情
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerOrderInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("customerOrderId", "1");
		String url =WEBURL + "/emInterface/employee/customerOrderInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 根据订单查看库存车辆
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerOrderStockCar() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("customerOrderId", "1");
		String url =WEBURL + "/emInterface/employee/customerOrderStockCar";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 出库
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerOrderStockCarPutout() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("customerOrderId", "1");//订单ID
		createMap.put("stockCarId", "1"); //库存车辆ID
		String url =WEBURL + "/emInterface/employee/customerOrderStockCarPutout";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 待上牌列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void orderLicensePlateList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("startDate", "");//开始时间
		createMap.put("endDate", "");//结束时间
		String url =WEBURL + "/emInterface/employee/orderLicensePlateList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 上牌完成
	 * @throws Exception
	 */
	@org.junit.Test
	public void licensePlateDone() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("customerOrderId", "1");
		String url =WEBURL + "/emInterface/employee/licensePlateDone";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 待加装精品列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsProductsList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e55988f3d1c6ef1a15a76362a62e6f62");
//		createMap.put("customerOrderId", "39");
//		createMap.put("startDate", "");//开始时间
//		createMap.put("endDate", "");//结束时间
		String url =WEBURL + "/emInterface/employee/carsProductsList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 添加预计加装精品时间
	 * @throws Exception
	 */
	@org.junit.Test
	public void addCarsProductsEstimateDate() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "66ada874119e9ae20e25058a3c65113e");
		createMap.put("customerOrderId", "67");
		createMap.put("estimateDate", "2017-10-10 10:10:10");
		String url =WEBURL + "/emInterface/employee/addCarsProductsEstimateDate";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 精品详情
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsProductsInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "66ada874119e9ae20e25058a3c65113e");
		createMap.put("customerOrderId", "1");
		String url =WEBURL + "/emInterface/employee/carsProductsInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 加装精品完成
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsProductsDone() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("customerOrderId", "1");
		String url =WEBURL + "/emInterface/employee/carsProductsDone";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取销售顾问列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void salesList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		String url =WEBURL + "/emInterface/employee/salesList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 分配销售顾问
	 * @throws Exception
	 */
	@org.junit.Test
	public void systenUserChangeCustomerOrg() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("customerUsersOrgId", "1");
		createMap.put("systemUserId", "1");
		String url =WEBURL + "/emInterface/employee/systenUserChangeCustomerOrg";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取全部客户列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerUserList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "78009280e9ec6b5423d07ff8acc33434");
//		createMap.put("customerUsersSearch", "名称电话搜索");
//		createMap.put("buyCarAlready", "1");//1已下单用户 0未下单用户
//		createMap.put("paymentWay", "2");//购车方式 1全款 2贷款
//		createMap.put("orderStates", "1,3");//多个用逗号隔开 订单状态分别有 1 3 5 7 9 11 13
		String url =WEBURL + "/emInterface/employee/customerUserList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 添加新用户(添加时间预约字段)
	 * @throws Exception
	 */
	@org.junit.Test
	public void addCustomerUsersr() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "24834225738d1934501da32f7d921aa0");
		createMap.put("customerUsersName", "列表测试");//客户姓名
		createMap.put("phoneNumber", "18312414459");//客户电话
		createMap.put("carPurchaseIntention", "1");//购车意向 1三天内 2一周内
		createMap.put("intentionCarId", "71");//意向车型
		createMap.put("expectWayId", "1");//购车方式 1全款 2贷款
		createMap.put("remarks", "备注测试");         //备注
		createMap.put("appointmentDate", "2017/11/9");//预约到店日期
		createMap.put("timeOfAppointment", "10:50-20:09");//预约时间
		createMap.put("systemUserId", "1");//分配的销售ID
		String url =WEBURL + "/emInterface/employee/addCustomerUsersr";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 开单前置
	 * @throws Exception
	 */
	@org.junit.Test
	public void createOrderBefor() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("customerUsersId", "1");//客户姓名
		String url =WEBURL + "/emInterface/employee/createOrderBefor";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 开单
	 * @throws Exception
	 */
	@org.junit.Test
	public void editCustomerOrder() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "50732b773b07b68f19dc530b7863452e");
//		createMap.put("customerUsersId", "68");//客户姓名
//		createMap.put("customerOrderId", "1");//订单ID，新增不需要编辑需要
		createMap.put("customerUserCard", "440982199010076890");//客户身份证号		
		createMap.put("brandId", "2");//品牌ID
		createMap.put("familyId", "47");//车系
		createMap.put("carsId", "861");//车型
		createMap.put("colourId", "113");//车身颜色
		createMap.put("interiorId", "38");//内饰
		createMap.put("isMortgage", "1");//是否抵押车辆，0不抵押 1抵押
		createMap.put("carUnitPrice", "12");//最终成交价
		createMap.put("paymentWay", "2");//购车方案 1全款 2分期
		createMap.put("downPayments", "100.0");//首付金额
		createMap.put("loan", "20.0");//贷款金额
		createMap.put("loanPayBackStages", "12");//还款期数
		createMap.put("licensePlatePriace", "0");//上牌费用
		createMap.put("vehicleAndVesselTax", "10");//车船税
		createMap.put("insurancePriace", "0");//商业保险费用
		createMap.put("followInformation", "车载香水,脚垫");//赠送精品，多个使用逗号隔开
		createMap.put("remark", "备注");//备注
		createMap.put("purchaseTaxPriace", "0");//购置税
		createMap.put("boutiquePriace", "0");//精品加装费
		createMap.put("mortgagePriace", "0");//按揭费
		createMap.put("loanBank", "1");//贷款银行
		createMap.put("systemUserId", "234");//销售人员
		createMap.put("depositPrice", "145.00");//定金
		createMap.put("advanceOrderId", "84");//定金
		String url =WEBURL + "/emInterface/employee/editCustomerOrder";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 待贴膜列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsPadPastingList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "66ada874119e9ae20e25058a3c65113e");
		String url =WEBURL + "/emInterface/employee/carsPadPastingList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 贴膜完成
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsPadPastingDone() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("customerOrderId", "1");
		String url =WEBURL + "/emInterface/employee/carsPadPastingDone";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 交车
	 * @throws Exception
	 */
	@org.junit.Test
	public void turnOverVehicle() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("customerOrderId", "1");
		createMap.put("extractCarImage", "");//人车合照
		String url =WEBURL + "/emInterface/employee/turnOverVehicle";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 订单详细信息
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerOrderAllInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "50732b773b07b68f19dc530b7863452e");
		createMap.put("customerOrderId", "58");
		String url =WEBURL + "/emInterface/employee/customerOrderAllInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/************************************************************* 2018 / 01 /05 ****************************************************************************/
	/**
	 * 创建订单
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderCreate() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("carsId", "10");//车大类ID
		createMap.put("colourId", "66");//颜色ID
		createMap.put("interiorId", "17");//内饰ID
		createMap.put("stockOrderNumber", "1");//采购数量
		createMap.put("stockOrderRemarks", "1");//备注
		createMap.put("certificateDate", "1");//合格证资料到达时间   1：随车， 2:3个工作日内，3：七个工作日内，4:10个工作日内，5:15个工作日内
		createMap.put("templateImage", "1");//其他附件资料图片，多个使用英文逗号隔开
		createMap.put("mileage", "120");//公里数
		createMap.put("overStrongInsurance", "1");//是否附带交强险 0否 1是
		createMap.put("followInformation", "点烟器,合同");//随车资料，使用英文逗号隔开
		
		String url =WEBURL + "/emInterface/employee/stockOrderCreate";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取向上级订车单详情
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e26016e76c2c89dd332766da8962179f");
		createMap.put("stockOrderId", "36");//订单ID
		String url =WEBURL + "/emInterface/employee/stockOrderInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 取消订车单
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderCancel() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("stockOrderId", "1");//订单ID
		String url =WEBURL + "/emInterface/employee/stockOrderCancel";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 内饰下拉列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carInteriorList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("familyId", "1");//订单ID
		String url =WEBURL + "/emInterface/employee/carInteriorList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 颜色下拉列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carColourList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("familyId", "1");//订单ID
		String url =WEBURL + "/emInterface/employee/carColourList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 银行审核通过
	 * @throws Exception
	 */
	@org.junit.Test
	public void bankApprovalPass() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("customerOrderId", "1");
		String url =WEBURL + "/emInterface/employee/bankApprovalPass";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/*************************************************************  2018 01 10   ************************************************************************/
	/**
	 * 新增在售车型
	 * @throws Exception
	 */
	@org.junit.Test
	public void editOrgCarsConfigure() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e26016e76c2c89dd332766da8962179f");
		createMap.put("orgCarsConfigureId", "16383");//新增不需要
		createMap.put("carsId", "4124");//车大类ID
		createMap.put("colourId", "79");//车身颜色ID
		createMap.put("discountPrice", "10");//优惠金额
		createMap.put("depositPrice", "10");//默认定金
		createMap.put("isOnLine", "0");//默认定金
		String url =WEBURL + "/emInterface/employee/editOrgCarsConfigure";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 在售车型列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void orgCarsConfigureList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e26016e76c2c89dd332766da8962179f");
		createMap.put("isOnLine", "");//是否在售 是1 否0（搜索条件）
		createMap.put("carsName", "");//车辆名称搜索
		String url =WEBURL + "/emInterface/employee/orgCarsConfigureList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 在售车型详情
	 * @throws Exception
	 */
	@org.junit.Test
	public void orgCarsConfigureInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "3ba92402cb1800ee026c729bd4cafcb8");
		createMap.put("orgCarsConfigureId", "256");//ID
		String url =WEBURL + "/emInterface/employee/orgCarsConfigureInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/************************************************************ 2018 01 11  **************************************************************************/
	/**
	 * 标记为已过线检查
	 * @throws Exception
	 */
	@org.junit.Test
	public void overTheLine() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "3ba92402cb1800ee026c729bd4cafcb8");
		createMap.put("customerOrderId", "1");//ID
		String url =WEBURL + "/emInterface/employee/overTheLine";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 订车单列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "3ba92402cb1800ee026c729bd4cafcb8");
		createMap.put("stockOrderState", "");//状态搜索
		createMap.put("orderSearch", "手自一体四驱至尊版");//ID
		String url =WEBURL + "/emInterface/employee/stockOrderList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 订车单列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderSign() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "3ba92402cb1800ee026c729bd4cafcb8");
		createMap.put("stockOrderId", "1");//状态搜索
		String url =WEBURL + "/emInterface/employee/stockOrderSign";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/*******************************************2018 01 12****************************************************************/
	/**
	 * 二级入库单列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void storageList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6de3db55b567064b942674e5ea6fd7ec");
//		createMap.put("storageCode", "R");
		String url =WEBURL + "/emInterface/employee/storageList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 组织仓库列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void organizationWarehouseList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "c7d9d39fa3a516180a1f0934b30cb637");
		createMap.put("orgId", "");
		String url =WEBURL + "/emInterface/employee/organizationWarehouseList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 二级组织入库单编辑
	 * @throws Exception
	 */
	@org.junit.Test
	public void storageEdit() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "c7d9d39fa3a516180a1f0934b30cb637");
		createMap.put("storageId", "");//入库单ID
		createMap.put("storageSource", "1");// 1:资源采购 2:4S点出货
		createMap.put("systemUsersId", "2");//采购员ID
		createMap.put("supplierId", "1");//供应商ID
		createMap.put("contractNumber", "1asjdhfkjasdf");//合同编号
		createMap.put("contractImage", "");//合同扫描件
		createMap.put("certificateDate", "1");//合格证到手时间 1：随车 2：3-7天 3：3-7天
		createMap.put("logisticsCost", "1001.01");//运费
		String url =WEBURL + "/emInterface/employee/storageEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 二级组织入库单编辑
	 * @throws Exception
	 */
	@org.junit.Test
	public void storageInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "50732b773b07b68f19dc530b7863452e");
		createMap.put("storageId", "68");//入库单ID
		String url =WEBURL + "/emInterface/employee/storageInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 根据二级组织入库单获取车辆列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void storageCarList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("storageId", "2");//入库单ID
//		createMap.put("carsId", "2");//车类型ID
//		createMap.put("interiorId", "2");//内饰ID
//		createMap.put("colourId", "2");//颜色ID
		String url =WEBURL + "/emInterface/employee/storageCarList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 根据二级组织入库单获取车辆列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void storageCarDelete() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("stockCarId", "2");//入库单ID
		String url =WEBURL + "/emInterface/employee/storageCarDelete";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 编辑二级入库的具体车辆
	 * @throws Exception
	 */
	@org.junit.Test
	public void storageCarEdit() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("stockCarId", "2");//车辆ID
		createMap.put("storageId", "2");//入库单ID
		createMap.put("carsId", "2");//车型ID
		createMap.put("interiorId", "2");//内饰ID
		createMap.put("colourId", "2");//颜色ID
		createMap.put("unitPrice", "2");//车辆单价
		createMap.put("frameNumber", "2");//车架号
		createMap.put("engineNumber", "2");//发动机号
		createMap.put("certificateNumber", "2");//合格证号
		createMap.put("certificateDate", "1");//票据到达时间
		createMap.put("warehouseId", "2");//仓库ID
		createMap.put("stockCarImages", "2");//验车照片，多个以英文逗号隔开
		String url =WEBURL + "/emInterface/employee/storageCarEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 供应商列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void supplierListList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e779896050c4d78dac5a479147bbdc47");
		String url =WEBURL + "/emInterface/employee/supplierListList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	
	/************************************************** 2018 01 16  **********************************************************/
	/**
	 * 获取用户本身组织的所有人员
	 * @throws Exception
	 */
	@org.junit.Test
	public void orgOneSelfList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e779896050c4d78dac5a479147bbdc47");
		String url =WEBURL + "/emInterface/employee/orgOneSelfList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/******************************************************* 2018 01 18*******************************************************************/
	/**
	 * 获取打印信息（PC）
	 * @throws Exception
	 */ 
	@org.junit.Test
	public void customerOrderPrint() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "c7d9d39fa3a516180a1f0934b30cb637");
		createMap.put("customerOrderId", "25");
		String url =WEBURL + "/emInterface/employee/customerOrderPrint";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 获取托运单列表
	 * @throws Exception
	 */ 
//	@org.junit.Test
//	public void consignmentList() throws Exception {
//		Map<String,String> createMap = new HashMap<String,String>();
//		createMap.put("sessionId", "8bb07be8a13552ad5a0c9be7b1475fcb");
//		createMap.put("consignmentState", "1");
//		String url =WEBURL + "/emInterface/employee/consignmentList";
//		System.out.println("请求的地址：" + url);
//		System.out.println("请求的参数：" + createMap.toString());
//		String msgString = HttpClientUtil.doPost(url, createMap);
//		System.out.println(msgString);
//	}
	/**
	 * 获取托运单详情
	 * @throws Exception
	 */ 
//	@org.junit.Test
//	public void consignmentInfo() throws Exception {
//		Map<String,String> createMap = new HashMap<String,String>();
//		createMap.put("sessionId", "8bb07be8a13552ad5a0c9be7b1475fcb");
//		createMap.put("consignmentId", "2");
//		String url =WEBURL + "/emInterface/employee/consignmentInfo";
//		System.out.println("请求的地址：" + url);
//		System.out.println("请求的参数：" + createMap.toString());
//		String msgString = HttpClientUtil.doPost(url, createMap);
//		System.out.println(msgString);
//	}
	/**
	 * 托运单编辑
	 * @throws Exception
	 */
//	@org.junit.Test
//	public void consignmentEdit() throws Exception {//
//		Map<String,String> createMap = new HashMap<String,String>();
//		createMap.put("sessionId", "8bb07be8a13552ad5a0c9be7b1475fcb");//用户名
//		createMap.put("consignmentId", "2");
//		createMap.put("startingPointAddress", "起点地址");//起点地址
//		createMap.put("startingPointLatitude", "13.000673");//起点纬度
//		createMap.put("startingPointLongitude", "573.1575245");//起点经度
//		createMap.put("destinationAddress", "终点地址");//终点地址
//		createMap.put("destinationLatitude", "58.167522");//终点纬度
//		createMap.put("destinationLongitude", "17.056545610");//终点经度
//		createMap.put("leaveTheCarPersonName", "阳绿");//交车人姓名
//		createMap.put("leaveTheCarPersonPhone", "1111111111");//交车人电话
//		createMap.put("consignmentType", "1");//配送方式  1配送上门 2专线配送
//		createMap.put("appointmentTime", "2017-12-28 12:12:12");//预约时间
//		createMap.put("extractTheCarPersonName", "老绿");//提车人姓名
//		createMap.put("extractTheCarPersonPhone", "1111111111");//提车人电话
//		createMap.put("extractTheCarPersonIdcard", "440982199010076890");//提车人身份证号
//		createMap.put("remarks", "托运单");//备注
//		createMap.put("goodsCarInfo", "1,2,11112_1,3,11111");//托运车辆(品牌,车系,车架号_品牌,车系,车架号_品牌,车系,车架号)
//		String url =WEBURL + "/emInterface/employee/consignmentEdit";
//		System.out.println("请求的地址：" + url);
//		System.out.println("请求的参数：" + createMap.toString());
//		String msgString = HttpClientUtil.doPost(url, createMap);
//		System.out.println(msgString);
//	}
	
	/************************************************************* 2018 01 22 ***************************************************************************/
	/**
	 * 托运单支付请求
	 * @throws Exception
	 */ 
	@org.junit.Test
	public void consignmentInPay() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "74353ccd9ee7144139a9bdc510527ea3");
		createMap.put("consignmentId", "2");
		String url =WEBURL + "/emInterface/employee/consignmentInPay";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/***************************************2018 01 24 *********************************************************/
	
	/**
	 * 员工端查询个人业绩
	 * @throws Exception
	 */
	@org.junit.Test
	public void querySalesPerformance() throws Exception{
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "4f5205508ffc060c258094e204cdbd3b");
		createMap.put("queryDate", "2018-05");
		String url =WEBURL + "/emInterface/employee/querySalesPerformance";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/********************************************************** 2018 02 05******************************************************************/
	/**
	 * 取消订单
	 * @throws Exception
	 */ 
	@org.junit.Test
	public void cancelOrder() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "ef0fbfb18b452a0f56029e9bf300bd2f");
		createMap.put("customerOrderId", "72");
		String url =WEBURL + "/emInterface/employee/cancelOrder";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
//		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	
	
	
	
	
	/****************************************************************物流****************************************************************************/
	/***********************************************2018 01 30****************************************************************/
	/**
	 * 托运单运费计算
	 * @throws Exception
	 */
	@org.junit.Test
	public void expensesCount() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "9ce547739b19b5ea1f9f0d20e16eab1f");//用户名
		createMap.put("consignmentType", "2");//配送方式 1点对点（非专线） 2专线
		createMap.put("number", "2");//托运车辆总数
		createMap.put("consignmentTypeLineId", "1");//专线ID（如果选择是专线配送）
		createMap.put("appointmentTime", "2018-01-30");//预约配送时间
		createMap.put("startingPointLongitude", "116.481028");//起点经度（如果选择的是点对点配送）
		createMap.put("startingPointLatitude", "39.989643");//起点维度（如果选择的是点对点配送）
		createMap.put("destinationLongitude", "116.465302");//终点经度（如果选择的是点对点配送）
		createMap.put("destinationLatitude", "40.004717");//终点维度（如果选择的是点对点配送）
		createMap.put("carsIds", "68,71");//车辆车大类ID（如果选择的是点对点配送）
		String url =WEBURL + "/emInterface/employee/expensesCount";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 获取托运单列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void consignmentList() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		createMap.put("consignmentCode", "1");//订单号搜索
		createMap.put("consignmentState", "1");//订单状态
		createMap.put("payMethod", "1");//支付方法 1微信支付 2现金支付
		createMap.put("consignmentInPayState", "1");//支付状态 0初始/未支付 1已支付
		String url =WEBURL + "/emInterface/employee/consignmentList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 查看托运单详情
	 * @throws Exception
	 */
	@org.junit.Test
	public void consignmentInfo() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f65a5cf96beed8ed5a368c87cc35ba86");//用户名
		createMap.put("consignmentId", "13");//
		String url =WEBURL + "/emInterface/employee/consignmentInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 修改托运单
	 * @throws Exception
	 */
	@org.junit.Test
	public void consignmentEdit() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		createMap.put("consignmentId", "1");//新增不需要此参数
		createMap.put("appointmentTime", "2017-12-28 12:12:12");//预约时间
		createMap.put("consignmentType", "1");//配送方式  1配送上门 2专线配送
		createMap.put("leaveTheCarPersonName", "阳绿");//交车人姓名
		createMap.put("leaveTheCarPersonPhone", "1111111111");//交车人电话
		createMap.put("extractTheCarPersonName", "老绿");//提车人姓名
		createMap.put("extractTheCarPersonPhone", "1111111111");//提车人电话
		createMap.put("extractTheCarPersonIdcard", "440982188754986134");//提车人身份证号
		createMap.put("goodsCarInfo", "1,6798r7e89345_2,79837497459");//托运车辆(ID与车架号用英文逗号隔开，车辆之间用下划线隔开)
		String url =WEBURL + "/emInterface/employee/consignmentEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 获取配送单列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void distributionList() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		createMap.put("distributionCode", "");//订单号搜索
		createMap.put("distributionState", "");//订单状态
		String url =WEBURL + "/emInterface/employee/distributionList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 获取板车列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void logisticsCarList() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		String url =WEBURL + "/emInterface/employee/logisticsCarList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 获取板车列表（下拉）
	 * @throws Exception
	 */
	@org.junit.Test
	public void logisticsCarListList() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		String url =WEBURL + "/emInterface/employee/logisticsCarListList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 板车编辑
	 * @throws Exception
	 */
	@org.junit.Test
	public void logisticsCarEdit() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		createMap.put("logisticsCarId", "1");//新增不需要此参数，编辑必须
		createMap.put("licensePlateNumber", "粤AYT890");//车牌
		createMap.put("orgId", "64");//服务组织ID
		createMap.put("logisticsCarType", "2");//板车类型 1小 2中 3大
		createMap.put("logisticsCarAddress", "广州");//停放地址
		createMap.put("logisticsCarNature", "1");//板车性质  1自有2加盟
		createMap.put("logisticsCarVacancy", "30");//最大装载量
		createMap.put("remarks", "备注");//备注
		String url =WEBURL + "/emInterface/employee/logisticsCarEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 板车启用禁用
	 * @throws Exception
	 */
	@org.junit.Test
	public void logisticsCarIsEnable() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		createMap.put("logisticsCarId", "1");//新增不需要此参数，编辑必须
		createMap.put("isEnable", "0");//1启用 0禁用
		String url =WEBURL + "/emInterface/employee/logisticsCarIsEnable";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取司机下拉列表（需要提前配置司机组）
	 * @throws Exception
	 */
	@org.junit.Test
	public void orgOneSelfDriverList() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		String url =WEBURL + "/emInterface/employee/orgOneSelfDriverList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 配送单编辑
	 * @throws Exception
	 */
	@org.junit.Test
	public void distributionEdit() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
//		createMap.put("distributionId", "1");//新增不需要此参数，编辑必须
//		createMap.put("createDate", "2017-12-30 12:12:12");//配送时间
		createMap.put("destinationType", "1");//1点对点 2专线
		createMap.put("logisticsCarId", "2");//板车ID
		createMap.put("driverId", "1");//司机ID（系统用户ID）
		createMap.put("idCardPicOn", "");//身份证正面
		createMap.put("idCardPicOff", "");//身份证反面
		createMap.put("remarks", "备注备注");//备注
		String url =WEBURL + "/emInterface/employee/distributionEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 查看待配送车辆
	 * @throws Exception
	 */
	@org.junit.Test
	public void consignmentGoodsCarList() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		String url =WEBURL + "/emInterface/employee/consignmentGoodsCarList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 查看已分配的车辆
	 * @throws Exception
	 */
	@org.junit.Test
	public void distributionGoodsCarList() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "c7d9d39fa3a516180a1f0934b30cb638");//用户名
		createMap.put("distributionId", "1");//配送单ID
		String url =WEBURL + "/emInterface/employee/distributionGoodsCarList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 删除已分配的车辆
	 * @throws Exception
	 */
	@org.junit.Test
	public void distributionGoodsCarDelete() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		createMap.put("distributionId", "1");//配送单ID
		createMap.put("goodsCarId", "1");//车辆ID
		String url =WEBURL + "/emInterface/employee/distributionGoodsCarDelete";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 添加待分配的车辆
	 * @throws Exception
	 */
	@org.junit.Test
	public void distributionGoodsCarAdd() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		createMap.put("distributionId", "1");//配送单ID
		createMap.put("goodsCarIds", "1,2");//车辆ID
		String url =WEBURL + "/emInterface/employee/distributionGoodsCarAdd";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
}
