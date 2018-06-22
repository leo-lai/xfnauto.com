package main.com.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import main.com.logistics.dao.po.LogisticsDynamicLine;
import main.com.logistics.dao.vo.LogisticsDynamicLineInfoVo;
import main.com.logistics.dao.vo.LogisticsDynamicLineVo;

public class LogisticsTest {
	private String WEBURL = "http://127.0.0.1:8080/tauto";
//	private String WEBURL = "http://111.230.170.36:8080/tauto";
	
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
		String url =WEBURL + "/management/admin/consignmentList";
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
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		createMap.put("consignmentId", "1");//
		String url =WEBURL + "/management/admin/consignmentInfo";
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
		String url =WEBURL + "/management/admin/consignmentEdit";
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
		createMap.put("sessionId", "f65a5cf96beed8ed5a368c87cc35ba86");//用户名
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
		String url =WEBURL + "/management/admin/logisticsCarList";
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
		String url =WEBURL + "/management/admin/logisticsCarListList";
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
		String url =WEBURL + "/management/admin/logisticsCarEdit";
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
		String url =WEBURL + "/management/admin/logisticsCarIsEnable";
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
		String url =WEBURL + "/management/admin/orgOneSelfDriverList";
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
		String url =WEBURL + "/management/admin/distributionEdit";
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
		String url =WEBURL + "/management/admin/consignmentGoodsCarList";
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
		String url =WEBURL + "/management/admin/distributionGoodsCarList";
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
		String url =WEBURL + "/management/admin/distributionGoodsCarDelete";
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
		createMap.put("sessionId", "f65a5cf96beed8ed5a368c87cc35ba86");//用户名
		createMap.put("distributionId", "20");//配送单ID
		createMap.put("goodsCarIds", "53,57,56,58");//车辆ID
		String url =WEBURL + "/management/admin/distributionGoodsCarAdd";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/************************************************** 2018 01 25 **********************************************************/
	/**
	 * 专线资费设置列表
	 * @throws Exception
	 */
	@org.junit.Test
	public void dedicatedLineList() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		String url =WEBURL + "/management/admin/dedicatedLineList";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 专线资费设置编辑
	 * @throws Exception
	 */
	@org.junit.Test
	public void dedicatedLineEdit() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		createMap.put("dedicatedLineId", "");//主键，新增时不需要
		createMap.put("dedicatedLineName", "广州-江门");//专线名称
		createMap.put("startingPointAddress", "广州东晓南548号");//起始地址
		createMap.put("destinationAddress", "江门鹤山解放路123号");//到达地址
		createMap.put("departureTime", "09:10");//正常出发时间
		createMap.put("amount", "300.00");//运费
		createMap.put("remarks", "我的备注");//备注
		String url =WEBURL + "/management/admin/dedicatedLineEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 专线资费设置删除
	 * @throws Exception
	 */
	@org.junit.Test
	public void dedicatedLineDelete() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		createMap.put("dedicatedLineId", "1");//主键
		String url =WEBURL + "/management/admin/dedicatedLineDelete";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 非专线资费详情
	 * @throws Exception
	 */
	@org.junit.Test
	public void dynamicLineInfo() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "b1d0535947173e8d8e3ad69b12a9af22");//用户名
		String url =WEBURL + "/management/admin/dynamicLineInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 非专线资费编辑
	 * @throws Exception
	 */
	@org.junit.Test
	public void dynamicLineEdit_() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "9ce547739b19b5ea1f9f0d20e16eab1f");//用户名
//		createMap.put("gradeFirst", "10.00");//车价附加费0-10
//		createMap.put("gradeSecond", "20.00");//车价附加费10-20
//		createMap.put("gradeThird", "30.00");//车价附加费20-30
//		createMap.put("gradeFour", "30.00");//车价附加费30-40
//		createMap.put("gradeFive", "40.00");//车价附加费40-60
//		createMap.put("gradeSix", "50.00");//车价附加费>60
//		createMap.put("startPrice", "60.00");//起步价
//		createMap.put("startingMileage", "10.00");//起步里程
		LogisticsDynamicLineVo dynamicLine = new LogisticsDynamicLineVo();
		dynamicLine.setGradeFirst(new BigDecimal(10.0));
		dynamicLine.setGradeSecond(new BigDecimal(10.0));
		dynamicLine.setGradeThird(new BigDecimal(10.0));
		dynamicLine.setGradeFour(new BigDecimal(10.0));
		dynamicLine.setGradeFive(new BigDecimal(10.0));
		dynamicLine.setGradeSix(new BigDecimal(10.0));
		dynamicLine.setStartPrice(new BigDecimal(10.0));
		dynamicLine.setStartingMileage(1d);
		List<LogisticsDynamicLineInfoVo> infoVos = new ArrayList<>();
		for(int i=0;i<3;i++) {
			LogisticsDynamicLineInfoVo dynamicLineInfoVo = new LogisticsDynamicLineInfoVo();
			dynamicLineInfoVo.setAmount(new BigDecimal(3));
			dynamicLineInfoVo.setDynamicLineId(1);
			dynamicLineInfoVo.setMaxMileage(10d+4d+i);
			dynamicLineInfoVo.setMinMileage(9d+3d+i);
			infoVos.add(dynamicLineInfoVo);
		}
		dynamicLine.setLineInfoVos(infoVos);
//		JSONObject jObject = new JSONObject();
//		JSONObject object = new JSONObject();
//		object.put("dynamicLineInfoId", 1);
//		object.put("dynamicLineId", 1);
//		object.put("amount", 1);
//		object.put("minMileage", 1);
//		object.put("maxMileage", 1);
//		jObject.put("lineInfoVos", object);
//		createMap.put("lineInfoVos", infoVos.toString());//溢出价格和里程 每个之间使用英文逗号隔开，每组之间使用下划线隔开(价格,溢出里程,溢出里程_价格,溢出里程,溢出里程)
//		createMap.put("lineInfoVoes", "[{'dynamicLineInfoId':1,'dynamicLineId':1,'amount':2.0d,'minMileage':10,'maxMileage':20}]");//溢出价格和里程 每个之间使用英文逗号隔开，每组之间使用下划线隔开(价格,溢出里程,溢出里程_价格,溢出里程,溢出里程)
		String url =WEBURL + "/management/admin/dynamicLineEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 非专线资费编辑
	 * @throws Exception
	 */
	@org.junit.Test
	public void dynamicLineEdit() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "9ce547739b19b5ea1f9f0d20e16eab1f");//用户名
		createMap.put("gradeFirst", "10.00");//车价附加费0-10
		createMap.put("gradeSecond", "20.00");//车价附加费10-20
		createMap.put("gradeThird", "30.00");//车价附加费20-30
		createMap.put("gradeFour", "30.00");//车价附加费30-40
		createMap.put("gradeFive", "40.00");//车价附加费40-60
		createMap.put("gradeSix", "50.00");//车价附加费>60
		createMap.put("startPrice", "60.00");//起步价
		createMap.put("startingMileage", "10.00");//起步里程
//		createMap.put("lineInfoVos", infoVos.toString());//溢出价格和里程 每个之间使用英文逗号隔开，每组之间使用下划线隔开(价格,溢出里程,溢出里程_价格,溢出里程,溢出里程)
		createMap.put("lineInfoVoes", "[{'dynamicLineInfoId':1,'dynamicLineId':1,'amount':2.0d,'minMileage':10,'maxMileage':20}]");//溢出价格和里程 每个之间使用英文逗号隔开，每组之间使用下划线隔开(价格,溢出里程,溢出里程_价格,溢出里程,溢出里程)
		String url =WEBURL + "/management/admin/dynamicLineEdit";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * 非专线资费详情
	 * @throws Exception
	 */
	@org.junit.Test
	public void recordGpsPos() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "9ce547739b19b5ea1f9f0d20e16eab1f");//用户名
		String url =WEBURL + "/management/admin/recordGpsPos";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * GPS
	 * @throws Exception
	 */
	@org.junit.Test
	public void logisticsDistributionGPS() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f65a5cf96beed8ed5a368c87cc35ba86");//用户名
		createMap.put("distributionId", "1");//用户名
		String url =WEBURL + "/emInterface/employee/logisticsDistributionGPS";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	/**
	 * GPS
	 * @throws Exception
	 */
	@org.junit.Test
	public void logisticsConsignmentGPS() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "c9bf477e663cbbc165f1808727b162a9");//用户名
		createMap.put("consignmentId", "1");//用户名
		String url =WEBURL + "/emInterface/employee/logisticsConsignmentGPS";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
	/**
	 * 配送单详情
	 * @throws Exception
	 */
	@org.junit.Test
	public void distributionInfo() throws Exception {//
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("sessionId", "f65a5cf96beed8ed5a368c87cc35ba86");//用户名
		createMap.put("distributionId", "2");//用户名
		String url =WEBURL + "/emInterface/employee/distributionInfo";
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + createMap.toString());
		String msgString = HttpClientUtil.doPost(url, createMap);
		System.out.println(msgString);
	}
	
}
