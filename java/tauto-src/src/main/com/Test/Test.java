package main.com.Test;

import java.util.HashMap;
import java.util.Map;

import main.com.utils.HTTPRequest;
import main.com.utils.HttpPostTest;

public class Test { 
	private String WEBURL = "http://111.230.170.36:8080/tauto";
//	private String WEBURL = "http://127.0.0.1:8080/tauto";

	@org.junit.Test
	public void addUser() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "57cdaf8a1c9ff938e08b98e8cd123e63");
		createMap.put("phoneNumber", "14789652354");
		createMap.put("userId", "");
		createMap.put("orgId", "1");
		createMap.put("roleId", "13");
		createMap.put("realName", "菲菲");
		createMap.put("birthday", "1990-11-11");
		createMap.put("cardNo", "440982199579821587");
		createMap.put("entryTime", "2017-10-11");
		createMap.put("basePay", "10000");
		createMap.put("agentGender", "2");
		String url =WEBURL + "/management/admin/addUser";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);		
		System.out.println(msgString);
	}
//	
//	public static void main(String[] args) {
//		System.out.println(System.currentTimeMillis());
//	}
	
	@org.junit.Test
	public void login() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
//		createMap.put("phoneNumber", "15875319473");//用户名
		createMap.put("userName", "15875319473");//用户名
		createMap.put("password", "123");//密码
//		createMap.put("code", "3316");//验证码暂时不考虑
		String url =WEBURL + "/management/admin/login";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void loginOut() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "zwen");//用户名
//		createMap.put("code", "3316");//验证码暂时不考虑
		String url =WEBURL + "/management/admin/loginOut";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
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
	@org.junit.Test
	public void changeOtherPassword() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "0DB85CD5FEC1108251FAD4DC9D7CFC25");//用户名
		createMap.put("userId", "12");//验证码暂时不考虑
		String url =WEBURL + "/management/admin/changeOtherPassword";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void organizationList() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "57cdaf8a1c9ff938e08b98e8cd123e63");
		createMap.put("orgName", "");//用户名
		String url =WEBURL + "/management/admin/organizationList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void organizationEdit() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "35623da7fcea9cda4bec7cc43a477899");
		createMap.put("orgId", "");//主键
		createMap.put("shortName", "门店添加");//用户名
		createMap.put("address", "地址");//用户名
		createMap.put("telePhone", "联系电话");//用户名
		createMap.put("orgType", "1");//组织类型 1直营2加盟3其他
		createMap.put("orgLevel", "3");//等级 1公司2分公司3门店
		createMap.put("longitude", "13.0");//经度
		createMap.put("latitude", "13.0");//维度
		createMap.put("imageUrl", "图片");//维度
		createMap.put("parentId", "2");//上级ID，如无上级为空
		createMap.put("bankAccount", "134565132");//银行账号
		createMap.put("bankName", "银行名称");//银行名称
		createMap.put("openingBranch", "开户支行");//开户支行
		createMap.put("nameOfAccount", "银行账号名称");//银行账号名称
		createMap.put("provinceId", "100000");//银行账号名称
		createMap.put("cityId", "110100");//银行账号名称
		createMap.put("areaId", "110101");//银行账号名称
		createMap.put("introduce", "简介");//银行账号名称
		String url =WEBURL + "/management/admin/organizationEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void organizationInfo() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("orgId", "1");//主键
		String url =WEBURL + "/management/admin/organizationInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void organizationOnOff() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "57cdaf8a1c9ff938e08b98e8cd123e63");
		createMap.put("orgId", "4");//主键
		createMap.put("isOn", "0");//用户名 0禁用 1开启
		String url =WEBURL + "/management/admin/organizationOnOff";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void userList() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "7f5684a67755f32462d24da184a7f795");
		createMap.put("realName", "");//主键
		createMap.put("orgName", "");//用户名 0禁用 1开启
		String url =WEBURL + "/management/admin/userList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void userIsEnable() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "7f5684a67755f32462d24da184a7f795");
		createMap.put("isEnable", "1");//主键
		createMap.put("userId", "2");//主键
		String url =WEBURL + "/management/admin/userIsEnable";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void roleList() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "34205165C578E4857652264DD32FA1D8");
		String url =WEBURL + "/management/admin/roleList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void roleEdit() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "34205165C578E4857652264DD32FA1D8");
		createMap.put("roleId", "14");
		createMap.put("roleName", "传创金融科技");
		createMap.put("remark", "测试");
		String url =WEBURL + "/management/admin/roleEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void roleDelete() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "34205165C578E4857652264DD32FA1D8");
		createMap.put("roleId", "14");
		String url =WEBURL + "/management/admin/roleDelete";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void setRoleMenu() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "34205165C578E4857652264DD32FA1D8");
		createMap.put("roleId", "14");
		createMap.put("menuIds", "1,2,3,4,5,6,7,8,9");
		String url =WEBURL + "/management/admin/setRoleMenu";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void menuListTree() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "B87CAA8751597B95B5DB3E33A2833817");
		createMap.put("roleId", "14");
		String url =WEBURL + "/management/admin/menuListTree";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void addMenu() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "34205165C578E4857652264DD32FA1D8");
		createMap.put("parentId", "0");
		createMap.put("menuName", "添加菜单");
		createMap.put("src", "/management/admin/editMenu");
		String url =WEBURL + "/management/admin/addMenu";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void editMenu() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "053c15c385b6703be649b466708bfc5d");
//		createMap.put("parentId", "0");
		createMap.put("menuId", "18");
		createMap.put("menuName", "修改菜单");
		createMap.put("src", "/management/admin/editMenu");
		String url =WEBURL + "/management/admin/editMenu";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void deleteMenu() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "34205165C578E4857652264DD32FA1D8");
		createMap.put("menuId", "17");
		String url =WEBURL + "/management/admin/deleteMenu";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void carsList() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "edb1eee084b5bf1f9f00a0a65befe2dc");
		createMap.put("carsName", "");//文字搜索
		String url =WEBURL + "/management/admin/carsList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void carsEdit() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "053c15c385b6703be649b466708bfc5d");
		createMap.put("carId", "");//车类型ID，编辑有ID，新增无ID
		createMap.put("brandId", "44");//品牌ID
		createMap.put("familyId", "272");//车系ID
		createMap.put("styleId", "5");//配置级别
		createMap.put("marketDate", "2017/10");//上市年月
		createMap.put("pl", "3.0T");//排量
		createMap.put("gearboxName", "自动");//变速箱类型
		createMap.put("bareCarPrice", "2000000");//裸车价
		createMap.put("price", "210000");//官方指导价
		createMap.put("seat", "5");//座位数
		createMap.put("introduce", "测试数据介绍");//图文介绍
		createMap.put("yearPattern", "2017款");//年款
		createMap.put("vehicleName", "轿车");//车型
		createMap.put("oilConsumption", "5.0");//油耗（后台自动添加单位 L/100KM）
		
		String url =WEBURL + "/management/admin/carsEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void carsBrandEdit() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "57cdaf8a1c9ff938e08b98e8cd123e63");
		createMap.put("brandName", "奔驰");//车类型ID，编辑有ID，新增无ID
		createMap.put("brandId", "");//品牌ID
		String url =WEBURL + "/management/admin/carsBrandEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void carsFamilyEdit() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "34205165C578E4857652264DD32FA1D8");
		createMap.put("carFamilyName", "卡罗拉");//车类型ID，编辑有ID，新增无ID
		createMap.put("brandId", "2");//品牌ID
		String url =WEBURL + "/management/admin/carsFamilyEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void carsStyleEdit() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "34205165C578E4857652264DD32FA1D8");
		createMap.put("carStyleId", "3");//车类型ID，编辑有ID，新增无ID
		createMap.put("carStyleName", "中配");//车类型ID，编辑有ID，新增无ID
		createMap.put("brandId", "");//品牌ID
		String url =WEBURL + "/management/admin/carsStyleEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}

	/**
	 * 获取客户列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerOrgList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "cbcab6159e72f8c4ddfb5564971efeeb");
		createMap.put("orgId", "");//为上级组织查看下级所有门店准备
		String url =WEBURL + "/management/admin/customerOrgList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取追踪客户列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void trackCustomerOrgList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "864ef366d810c83df15e253048b26d6b");
		createMap.put("orgId", "");//为上级组织查看下级所有门店准备
		String url =WEBURL + "/management/admin/trackCustomerOrgList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取预约客户列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void bespeakCustomerOrgList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "7F5FB737F6734EB27B012764AEC85BF1");
		createMap.put("orgId", "");//为上级组织查看下级所有门店准备
		String url =WEBURL + "/management/admin/bespeakCustomerOrgList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	@org.junit.Test
	public void addCustomerUsersr() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("customerUsersName", "莉莉李");//客户姓名CustomerUsersName
		createMap.put("phoneNumber", "18312414347");//客户电话
		createMap.put("carPurchaseIntention", "一周之内");//购车意向
		createMap.put("intentionCarId", "1");//意向车型
		createMap.put("expectWayId", "1");//购车方式
		createMap.put("remarks", "备注测试");         //备注
		createMap.put("systemUserId", "127");         //销售顾问
		String url =WEBURL + "/management/admin/addCustomerUsersr";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	@org.junit.Test
	public void addCustomerUsersr_old() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "7F5FB737F6734EB27B012764AEC85BF1");
		createMap.put("customerUsersName", "莉莉李");//客户姓名
		createMap.put("phoneNumber", "15875319407");//客户电话
		createMap.put("annualIncome", "1000000亿");//客户年收入
		createMap.put("cardNo", "440982199010074328");//客户身份证号
		createMap.put("agentGender", "2");//性别 1男 2女
		createMap.put("incomeSource", "上市老总");//收入来源
		createMap.put("maritalStatus", "1");//婚姻状况 1未婚 2已婚
		createMap.put("housingSource", "全额自费");//住房来源
		createMap.put("isHasDriversLicense", "1");//是否有驾驶证 0没有 1有
		createMap.put("email", "872630176@qq.com");//邮箱
		createMap.put("education", "博士");//教育
		createMap.put("address", "广州天河天河城");//住址
		createMap.put("emergencyAContact", "哈哈李");       //紧急联系人A
		createMap.put("emergencyBContact", "静静李");         //紧急联系人B
		createMap.put("emergencyARelationship", "父女");        //与用户关系A
		createMap.put("emergencyBRelationship", "姊妹");         //与用户关系B
		createMap.put("emergencyAPhone", "15789431682");               //联系人A电话号
		createMap.put("emergencyBPhone", "17649831579");               //联系人B电话号
		createMap.put("workUnit", "广州天高软件科技有限公司");                //工作单位
		createMap.put("annualIncomeAfterTax", "1000000亿");         //税后年收入
		createMap.put("workingPlace", "全世界");               //工作地点
		createMap.put("entryTime", "1995/02");                 //工作入职时间
		createMap.put("position", "总裁");                 //职位
		createMap.put("companyTelephone", "020-123456");         //公司电话
		createMap.put("remarks", "备注测试");         //备注
		String url =WEBURL + "/management/admin/addCustomerUsersr";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 标记为跟踪客户
	 * @throws Exception
	 */
	@org.junit.Test
	public void trackCustomerOrg() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "7F5FB737F6734EB27B012764AEC85BF1");
		createMap.put("customerUsersOrgId", "1");//客户组织中间表ID
		createMap.put("systemUserId", "3");//销售顾问
		String url =WEBURL + "/management/admin/trackCustomerOrg";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 标记为不买车客户
	 * @throws Exception
	 */
	@org.junit.Test
	public void notBuyCustomerOrg() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "7F5FB737F6734EB27B012764AEC85BF1");
		createMap.put("customerUsersOrgId", "1");//客户组织中间表ID
		String url =WEBURL + "/management/admin/notBuyCustomerOrg";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 客户预约信息登记
	 * @throws Exception
	 */
	@org.junit.Test
	public void bespeakChangeCustomerOrg() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "7F5FB737F6734EB27B012764AEC85BF1");
		createMap.put("customerUsersOrgId", "1");//客户组织中间表ID
		createMap.put("appointmentDate", "2017/11/9 ");//客户组织中间表ID
		createMap.put("timeOfAppointment", "10:50-20:09");//日期时间段叠加，预约时间
		createMap.put("remarks", "备注备注");//备注
		createMap.put("intentionCarId", "2");//意向车类型ID
		String url =WEBURL + "/management/admin/bespeakChangeCustomerOrg";
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
		String url =WEBURL + "/management/admin/carsBrandList";
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
	public void carsBrandPageList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "A52F4C73AD89575D47D3C8C047759CC4");
		
		String url =WEBURL + "/management/admin/carsBrandPageList";
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
	public void carsFamilyList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("brandId", "2");
		String url =WEBURL + "/management/admin/carsFamilyList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取车系列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsListList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("familyId", "187");
		String url =WEBURL + "/management/admin/carsListList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 获取客户信息
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerUsersrInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "def0e67b9cc105a34aa783ce20a5306e");
		createMap.put("customerUsersId", "86");
		createMap.put("customerUsersOrgId", "118");//客户组织中间表ID
		String url =WEBURL + "/management/admin/customerUsersrInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取客户信息
	 * @throws Exception
	 */
	@org.junit.Test
	public void carExpectWayList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "7F5FB737F6734EB27B012764AEC85BF1");
		createMap.put("brandId", "1");
		String url =WEBURL + "/management/admin/carExpectWayList";
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
		createMap.put("sessionId", "7F5FB737F6734EB27B012764AEC85BF1");
		String url =WEBURL + "/management/admin/organizationLevelList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 根据等级获取上级
	 * @throws Exception
	 */
	@org.junit.Test
	public void organizationLevelListByLevel() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		createMap.put("orgLevel", "3");
		String url =WEBURL + "/management/admin/organizationLevelListByLevel";
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
		createMap.put("sessionId", "7F5FB737F6734EB27B012764AEC85BF1");
		createMap.put("systemUserId", "3");
		createMap.put("customerUsersOrgId", "1");
		String url =WEBURL + "/management/admin/systenUserChangeCustomerOrg";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 销售顾问列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void salesList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "3e115be604dd23e797212e0d098bcb92");
		createMap.put("realName", "");
		String url =WEBURL + "/management/admin/salesList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 品牌颜色列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carColourGetByBrand() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "23c1fee015bb2d4a795b91eea3503d47");
		createMap.put("familyId", "2");//品牌ID
		String url =WEBURL + "/management/admin/carColourGetByBrand";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 品牌颜色编辑
	 * @throws Exception
	 */
	@org.junit.Test
	public void carColourEdit() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "23c1fee015bb2d4a795b91eea3503d47");
		createMap.put("familyId", "5");//品牌ID
		createMap.put("carColourId", "");//颜色ID
		createMap.put("CarColourName", "红黑色");//颜色名称
		String url =WEBURL + "/management/admin/carColourEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 品牌颜色删除
	 * @throws Exception
	 */
	@org.junit.Test
	public void carColourDelete() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "fa91f19efbdfe69d26bbe956529e5573");
		createMap.put("carColourId", "1");//颜色ID
		String url =WEBURL + "/management/admin/carColourDelete";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 品牌内饰列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carInteriorGetByBrand() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "A52F4C73AD89575D47D3C8C047759CC4");
		createMap.put("carFamilyId", "2");//品牌ID
		String url =WEBURL + "/management/admin/carInteriorGetByBrand";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 品牌内饰编辑
	 * @throws Exception
	 */
	@org.junit.Test
	public void carInteriorEdit() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "A52F4C73AD89575D47D3C8C047759CC4");
		createMap.put("carFamilyId", "2");//品牌ID
		createMap.put("interiorId", "");//颜色ID
		createMap.put("interiorName", "红黑色");//颜色名称
		String url =WEBURL + "/management/admin/carInteriorEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 品牌内饰删除
	 * @throws Exception
	 */
	@org.junit.Test
	public void carInteriorDelete() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "A52F4C73AD89575D47D3C8C047759CC4");
		createMap.put("interiorId", "1");//颜色ID
		String url =WEBURL + "/management/admin/carInteriorDelete";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取颜色的图片
	 * @throws Exception
	 */
	@org.junit.Test
	public void carColourImageGetByCarColour() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "A52F4C73AD89575D47D3C8C047759CC4");
		createMap.put("carColourId", "2");
		String url =WEBURL + "/management/admin/carColourImageGetByCarColour";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 添加车身颜色对应图片
	 * @throws Exception
	 */
	@org.junit.Test
	public void carColourImageAdd() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "A52F4C73AD89575D47D3C8C047759CC4");
		createMap.put("carColourId", "2");
		createMap.put("carsId", "1");
		createMap.put("imagePath", "http://img3.redocn.com/tupian/20150425/suyahuawenbiankuangkapianbeijingsucai_3937892.jpg");
		String url =WEBURL + "/management/admin/carColourImageAdd";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 添加车身颜色对应图片
	 * @throws Exception
	 */
	@org.junit.Test
	public void carColourImageDelete() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "A52F4C73AD89575D47D3C8C047759CC4");
		createMap.put("carColourImageId", "1");
		String url =WEBURL + "/management/admin/carColourImageDelete";
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
	public void supplierList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "B87CAA8751597B95B5DB3E33A2833817");
		createMap.put("orgName", "");
		String url =WEBURL + "/management/admin/supplierList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 供应商列表(下拉)
	 * @throws Exception
	 */
	@org.junit.Test
	public void supplierListList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "B87CAA8751597B95B5DB3E33A2833817");
		String url =WEBURL + "/management/admin/supplierListList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 供应商编辑
	 * @throws Exception
	 */
	@org.junit.Test
	public void supplierEdit() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "57cdaf8a1c9ff938e08b98e8cd123e63");
		createMap.put("supplierId", "");//如果是编辑就一定需要ID，新增则不需要
		createMap.put("supplierName", "供应商测试");
		createMap.put("phoneNumber", "020-123456789");//联系方式
		createMap.put("remarks", "供应商备注");//供应商备注
		String url =WEBURL + "/management/admin/supplierEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 供应商删除
	 * @throws Exception
	 */
	@org.junit.Test
	public void supplierDelete() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "B87CAA8751597B95B5DB3E33A2833817");
		createMap.put("supplierId", "2");//删除
		String url =WEBURL + "/management/admin/supplierDelete";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 二级入库单列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void storageList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "e5f1cb6a30ed271e802359a8d1350515");
		String url =WEBURL + "/management/admin/storageList";
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
		createMap.put("sessionId", "4b32097d508bf3c477d5b733cff0038c");
		createMap.put("orgId", "");
		String url =WEBURL + "/management/admin/organizationWarehouseList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 仓库编辑
	 * @throws Exception
	 */
	@org.junit.Test
	public void organizationWarehouseEdit() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "57cdaf8a1c9ff938e08b98e8cd123e63");
		createMap.put("orgId", "1");
		createMap.put("warehouseId", "");
		createMap.put("warehouseName", "西北仓");
		createMap.put("warehouseType", "1");
		String url =WEBURL + "/management/admin/organizationWarehouseEdit"; 
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 组织仓库删除
	 * @throws Exception
	 */
	@org.junit.Test
	public void organizationWarehouseDalete() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("warehouseId", "7");
		String url =WEBURL + "/management/admin/organizationWarehouseDalete";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 二级组织入库单新建
	 * @throws Exception
	 */
	@org.junit.Test
	public void storageInsert() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		String url =WEBURL + "/management/admin/storageInsert";
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
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("storageId", "2");//入库单ID
		createMap.put("storageSource", "1");// 1:资源采购 2:4S点出货
		createMap.put("systemUsersId", "2");//采购员ID
		createMap.put("supplierId", "1");//供应商ID
		createMap.put("contractNumber", "1asjdhfkjasdf");//合同编号
		createMap.put("contractImage", "");//合同扫描件
		createMap.put("certificateDate", "1");//合格证到手时间 1：随车 2：3-7天 3：3-7天
		createMap.put("logisticsCost", "1001.01");//运费
		String url =WEBURL + "/management/admin/storageEdit";
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
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("storageId", "2");//入库单ID
		String url =WEBURL + "/management/admin/storageInfo";
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
		String url =WEBURL + "/management/admin/storageCarList";
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
		String url =WEBURL + "/management/admin/storageCarDelete";
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
		createMap.put("warehouseId", "2");//仓库ID
		createMap.put("stockCarImages", "2");//验车照片，多个以英文逗号隔开
		String url =WEBURL + "/management/admin/storageCarEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 获取车系分页列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsFamilyListPage() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "57cdaf8a1c9ff938e08b98e8cd123e63");
		createMap.put("brandId", "");//筛选条件
		String url =WEBURL + "/management/admin/carsFamilyListPage";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取车等级的下拉（高中低配）
	 * @throws Exception
	 */
	@org.junit.Test
	public void carsStyleList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "053c15c385b6703be649b466708bfc5d");
		createMap.put("familyId", "2");//筛选条件
		String url =WEBURL + "/management/admin/carsStyleList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 车辆库存车辆列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockCarList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "75d1671ff2ec15d881af785311c04cbc");
		createMap.put("frameNumber", "");//车架号 筛选条件43
		String url =WEBURL + "/management/admin/stockCarList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 修改库存车辆信息（修改库存车辆的定金/优惠/是否线上展示等）
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockCarEdit() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "aa6bc10d484c255feea90ce1e3e3fcaf");
		createMap.put("carsId", "205");//
		createMap.put("colourId", "66");//
		createMap.put("interiorId", "52");//
		createMap.put("isOnLine", "1");//是否在线
		createMap.put("depositPrice", "1.1");//定金
		createMap.put("discountPrice", "1.1");//优惠
		createMap.put("invoicePrice", "1.1");//发票金额
		String url =WEBURL + "/management/admin/stockCarEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 车辆库存车辆查看明细
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockCarInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("carsId", "");//车大类
		createMap.put("interiorId", "1");//内饰ID
		createMap.put("colourId", "1.1");//颜色ID
		String url =WEBURL + "/management/admin/stockCarInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 获取库存订单列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "3e115be604dd23e797212e0d098bcb92");
		String url =WEBURL + "/management/admin/stockOrderList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取库存订单列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("stockOrderId", "1");//订单ID
		createMap.put("isSellList", "1");//是否是卖家列表 0表示买家 1表示买家
		String url =WEBURL + "/management/admin/stockOrderInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
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
		createMap.put("certificateDate", "1");//合格证资料到达时间 1：随车 2:1-3 3:3-7 
		String url =WEBURL + "/management/admin/stockOrderCreate";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 取消订单
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderCancel() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("stockOrderId", "1");//订单ID
		String url =WEBURL + "/management/admin/stockOrderCancel";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 签收并自动入库
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderSign() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "75d1671ff2ec15d881af785311c04cbc");
		createMap.put("stockOrderId", "31");//订单ID
		String url =WEBURL + "/management/admin/stockOrderSign";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 二级组织更改尾款
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderchangebalancePrice() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("stockOrderId", "1");//订单ID
		createMap.put("balancePrice", "1.1");//尾款金额
		String url =WEBURL + "/management/admin/stockOrderchangebalancePrice";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 车辆出库
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderStorageOut() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("stockOrderId", "1");//订单ID
		createMap.put("stockCarIds", "1,2");//多个使用英文逗号隔开
		createMap.put("logisticsMode", "1");//1随车 2物流
		createMap.put("followInformation", "灭火器,备胎");//多个用逗号隔开
		String url =WEBURL + "/management/admin/stockOrderStorageOut";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 通知有车
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderNotice() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("stockOrderId", "1");//订单ID
		createMap.put("balancePrice", "120000");//尾款
		String url =WEBURL + "/management/admin/stockOrderNotice";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 通知有车前置
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderNoticeBefor() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("stockOrderId", "1");//订单ID
		String url =WEBURL + "/management/admin/stockOrderNoticeBefor";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 车辆出库前置
	 * @throws Exception
	 */
	@org.junit.Test
	public void stockOrderStorageOutBefor() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f27641100e90c86452ef573f6a37fb13");
		createMap.put("stockOrderId", "1");//订单ID
		String url =WEBURL + "/management/admin/stockOrderStorageOutBefor";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 车辆出库前置
	 * @throws Exception
	 */
	@org.junit.Test
	public void index() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "576e10ceaf332f970dfff474ea14432a");
		String url =WEBURL + "/management/admin/index";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取车辆定金
	 * @throws Exception
	 */
	@org.junit.Test
	public void carDepositPrice() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "87a1647f0252a8d7b9ba6044c8498df5");
		createMap.put("carsId", "68");//车大类ID
		createMap.put("colourId", "146");//颜色ID
		createMap.put("interiorId", "49");//内饰ID
		String url =WEBURL + "/management/admin/carDepositPrice";
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
		createMap.put("sessionId", "d43c939d5e6351551843c321411c0beb");
		createMap.put("customerUsersId", "10");//需要备注的用户ID
		createMap.put("remarksContent", "备注备注");//备注
		String url =WEBURL + "/management/admin/addCustomerRemarks";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}	
	
	/**
	 * 修改用户个人信息
	 * @throws Exception
	 */
	@org.junit.Test
	public void changeUserInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "7F5FB737F6734EB27B012764AEC85BF1");
		createMap.put("customerUsersId", "1");//客户姓名
		createMap.put("customerUsersName", "莉莉李");//客户姓名
		createMap.put("phoneNumber", "15875319407");//客户电话
		createMap.put("annualIncome", "1000000亿");//客户年收入
		createMap.put("cardNo", "440982199010074328");//客户身份证号
		createMap.put("agentGender", "2");//性别 1男 2女
		createMap.put("incomeSource", "上市老总");//收入来源
		createMap.put("maritalStatus", "1");//婚姻状况 1未婚 2已婚
		createMap.put("housingSource", "全额自费");//住房来源
		createMap.put("isHasDriversLicense", "1");//是否有驾驶证 0没有 1有
		createMap.put("email", "872630176@qq.com");//邮箱
		createMap.put("education", "博士");//教育
		createMap.put("address", "广州天河天河城");//住址
		createMap.put("emergencyAContact", "哈哈李");       //紧急联系人A
		createMap.put("emergencyBContact", "静静李");         //紧急联系人B
		createMap.put("emergencyARelationship", "父女");        //与用户关系A
		createMap.put("emergencyBRelationship", "姊妹");         //与用户关系B
		createMap.put("emergencyAPhone", "15789431682");               //联系人A电话号
		createMap.put("emergencyBPhone", "17649831579");               //联系人B电话号
		createMap.put("workUnit", "广州天高软件科技有限公司");                //工作单位
		createMap.put("annualIncomeAfterTax", "1000000亿");         //税后年收入
		createMap.put("workingPlace", "全世界");               //工作地点
		createMap.put("entryTime", "1995/02");                 //工作入职时间
		createMap.put("position", "总裁");                 //职位
		createMap.put("companyTelephone", "020-123456");         //公司电话
		String url =WEBURL + "/management/admin/changeUserInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 修改用户车辆信息
	 * @throws Exception
	 */
	@org.junit.Test
	public void changeUserCarInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "7F5FB737F6734EB27B012764AEC85BF1");
		createMap.put("customerCarId", "1");//用户个人车辆主键
		createMap.put("licensePlateNumber", "粤H88888");//用户个人车辆主键
		createMap.put("afterSaleSupport", "免费保养一次");//用户个人车辆主键
		createMap.put("purchaseTax","0.01"); //购置税
		createMap.put("exciseTax","0.01"); //消费税
		createMap.put("vehicleAndVesselTax","0.01"); //车船税
		createMap.put("premium","0.01"); //上牌费用
		createMap.put("compulsoryInsurance","0.01"); //强制保险
		createMap.put("thirdPartyLiabilityInsurance","0.01"); //第三者责任险
		createMap.put("vehicleLossInsurance","0.01"); //车辆损失险
		createMap.put("riskOfGlassBreakage","0.01"); //玻璃单独破碎险
		createMap.put("selfIgnitionLossInsurance","0.01"); //自燃损失险
		createMap.put("exemptionFromSpecialContract","0.01"); //不计免赔特约险
		createMap.put("noLiabilityInsurance","0.01"); //无过责任险
		createMap.put("personnelLiabilityInsurance","0.01"); //车上人员责任险
		createMap.put("scratchRisk","0.01"); //车上划痕险		
		String url =WEBURL + "/management/admin/changeUserCarInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/******************************************** 2017/12/08 *************************************************************/
	/**
	 * 编辑订单 
	 * @throws Exception
	 */
	@org.junit.Test
	public void editCustomerOrder() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("customerOrderId", "2");//新开单不需要，编辑订单就需要
		createMap.put("customerUserId", "3");//用户个人车辆主键
		createMap.put("brandId", "2");//品牌ID
		createMap.put("familyId", "47");//车系
		createMap.put("carsId", "861");//车型
		createMap.put("colourId", "113");//车身颜色
		createMap.put("interiorId", "38");//内饰
		createMap.put("isMortgage", "38");//是否抵押车辆，0不抵押 1抵押
		createMap.put("amount", "100048.16");//最终成交价
		createMap.put("paymentWay", "2");//购车方案 1全款 2分期
		createMap.put("downPayments", "1.0");//首付金额
		createMap.put("loan", "1.0");//贷款金额
		createMap.put("loanPayBackStages", "12");//还款期数
		createMap.put("isPurchaseTax", "1");//是否附带购置税 1是 0否
		createMap.put("isTakeLicensePlate", "1");//是否附带上牌费 1是 0否
		createMap.put("licensePlatePriace", "1.0");//上牌费用
		createMap.put("isInsurance", "1");//是否商业保险 1是 0否
		createMap.put("insurancePriace", "1");//商业保险费用
		createMap.put("followInformation", "车载香水,脚垫");//赠送精品，多个使用逗号隔开
		createMap.put("remark", "备注");//备注
		String url =WEBURL + "/management/admin/editCustomerOrder";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 订单支付
	 * @throws Exception
	 */
	@org.junit.Test
	public void payInOrder() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "87a1647f0252a8d7b9ba6044c8498df5");
		createMap.put("customerOrderId", "15");//新开单不需要，编辑订单就需要
		createMap.put("amount", "90000");//支付金额 98000
		createMap.put("isCash", "1");//是否是现金 1是 0否
		String url =WEBURL + "/management/admin/payInOrder";
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
	public void customerOrderInfo() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("customerOrderId", "2");//订单ID
		String url =WEBURL + "/management/admin/customerOrderInfo";
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
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("customerOrderId", "2");//订单ID
		String url =WEBURL + "/management/admin/bankApprovalPass";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 修改成全款
	 * @throws Exception
	 */
	@org.junit.Test
	public void changeFullPayment() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("customerOrderId", "2");//订单ID
		String url =WEBURL + "/management/admin/changeFullPayment";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 交付车辆
	 * @throws Exception
	 */
	@org.junit.Test
	public void turnOverVehicle() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("customerOrderId", "2");//订单ID
		createMap.put("extractCarImage", "");//人车合照
		String url =WEBURL + "/management/admin/turnOverVehicle";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取客户订车单列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerOrderList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("customerOrderState", "");//订单状态搜索 1新创建订单 3等待银行审核 4银行拒绝 5待出库......
		String url =WEBURL + "/management/admin/customerOrderList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 门店出库车辆
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerOrderStorageOut() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("customerOrderId", "2");
		createMap.put("stockCarId", "124");
		String url =WEBURL + "/management/admin/customerOrderStorageOut";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 门店出库车辆前置
	 * @throws Exception
	 */
	@org.junit.Test
	public void customerOrderStorageOutBefor() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "6715615a743e8153472d33d32656cf55");
		createMap.put("customerOrderId", "2");
		String url =WEBURL + "/management/admin/customerOrderStorageOutBefor";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 银行审核列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void bankToExamineOrderList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "4d8f9318f64f8514b0aa725add440332");
		createMap.put("customerPhoneNumber", "");//客户电话
		createMap.put("isPassThrough", "");//是否审核通过筛选 0不通过 1通过
		String url =WEBURL + "/management/admin/bankToExamineOrderList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 银行审核结果
	 * @throws Exception
	 */
	@org.junit.Test
	public void bankToExamineOrder() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "3c56b627d07e830ce4b75f202122b24e");
		createMap.put("refusalReason", "");//拒绝理由
		createMap.put("isPassThrough", "");//是否审核通过筛选 0不通过 1通过
		createMap.put("customerOrderId", "");//订单ID
		String url =WEBURL + "/management/admin/bankToExamineOrder";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/***************************************************** 2018 / 01 / 04 ***************************************************************************/	
	/**
	 * 订单支付记录
	 * @throws Exception
	 */
	@org.junit.Test
	public void orderPayList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "3c56b627d07e830ce4b75f202122b24e");
		createMap.put("customerOrderId", "1");//订单ＩＤ
		String url =WEBURL + "/management/admin/orderPayList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 订单各项费用
	 * @throws Exception
	 */
	@org.junit.Test
	public void orderPriceList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "3c56b627d07e830ce4b75f202122b24e");
		createMap.put("customerOrderId", "1");//订单ＩＤ
		String url =WEBURL + "/management/admin/orderPriceList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/***************************************************** 2018 / 01 / 15 ***************************************************************************/
	/**
	 * 获取组别列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void systemGroupingList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "0b73534084d7b1bd8f814087c3acece7");
		String url =WEBURL + "/management/admin/systemGroupingList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取组别成员列表
	 * @throws Exception
	 */ 
	@org.junit.Test
	public void systemUserGroupingList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "0b73534084d7b1bd8f814087c3acece7");
		createMap.put("groupingId", "13");
		String url =WEBURL + "/management/admin/systemUserGroupingList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 编辑分组自身信息
	 * @throws Exception
	 */ 
	@org.junit.Test
	public void systemGroupingEdit() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "0b73534084d7b1bd8f814087c3acece7");
		createMap.put("groupingId", "");//新增不需要
		createMap.put("groupingName", "分组测试测试");//新增不需要
		createMap.put("remarks", "分组测试测试");//新增不需要
		String url =WEBURL + "/management/admin/systemGroupingEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 编辑组内成员
	 * @throws Exception
	 */ 
	@org.junit.Test
	public void systemUserGroupingEdit() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "0b73534084d7b1bd8f814087c3acece7");
		createMap.put("userGroupingId", "144");//新增不需要
		createMap.put("userId", "176");
		createMap.put("groupingId", "13");
		createMap.put("overManage", "1"); //是否是管理员 1是 0否
		String url =WEBURL + "/management/admin/systemUserGroupingEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 删除组内成员
	 * @throws Exception
	 */ 
	@org.junit.Test
	public void systemUserGroupingDalete() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "0b73534084d7b1bd8f814087c3acece7");
		createMap.put("userGroupingId", "144");//
		String url =WEBURL + "/management/admin/systemUserGroupingDalete";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 删除组内成员
	 * @throws Exception
	 */ 
	@org.junit.Test
	public void systemGroupingDalete() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "0b73534084d7b1bd8f814087c3acece7");
		createMap.put("groupingId", "44");//
		String url =WEBURL + "/management/admin/systemGroupingDalete";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 获取自身组织的成员
	 * @throws Exception
	 */ 
	@org.junit.Test
	public void orgOneSelfList() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "0b73534084d7b1bd8f814087c3acece7");
		String url =WEBURL + "/management/admin/orgOneSelfList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/******************************************************* 2018 01 17*******************************************************************/
	/**
	 * 获取打印信息（PC）
	 * @throws Exception
	 */ 
	@org.junit.Test
	public void customerOrderPrint() throws Exception {
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "c7d9d39fa3a516180a1f0934b30cb637");
		createMap.put("customerOrderId", "25");
		String url =WEBURL + "/management/admin/customerOrderPrint";
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
		createMap.put("sessionId", "c7d9d39fa3a516180a1f0934b30cb637");
		createMap.put("customerOrderId", "25");
		String url =WEBURL + "/management/admin/cancelOrder";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
}
