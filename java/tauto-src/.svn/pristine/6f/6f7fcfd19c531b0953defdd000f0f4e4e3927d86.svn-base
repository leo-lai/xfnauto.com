package main.com.allInPay.controller;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.allInPay.dao.po.AllInPayWeb;
import main.com.allInPay.service.AllInPayWebService;
import main.com.allInPay.utils.AllInPayUtil;
import main.com.frame.controller.BaseController;
import main.com.pay.allInPay.SystemConfig;

/**
 * 通联支付(网关)
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/allInPayWeb")
public class AllInPayWebController extends BaseController{
	
	@Autowired
	private AllInPayWebService allInPayService;
	
	/**
	 * 查询订单
	 * @param response
	 * @param request
	 * @throws Exception
	 */
//	@RequestMapping(value = "/queryPay", method = RequestMethod.POST)
//	@ResponseBody
//	public void queryOrder(HttpServletResponse response,HttpServletRequest request) throws Exception {
//		TreeMap<String,String> params = getParams(request);
//		String rspStr = "";
//		try{
//			if(FuncUtil.validSign(params, AppConstants.APPKEY)){//验签成功,进行业务处理
//				QueryRsp rsp = this.allInPayService.queryOrder(params);
//				rspStr = FuncUtil.toJsonResult(rsp);
//			}else{//验签失败
//				rspStr = getDefaultMsg("验签失败");
//			}
//		}catch (Exception e) {//处理异常
//			e.printStackTrace();
//			rspStr = getDefaultMsg("处理异常");
//		}finally{
//			System.out.println("返回数据:"+rspStr);
//			if(!FuncUtil.isEmpty(rspStr)){
//				response.getOutputStream().write(rspStr.getBytes("utf-8"));
//			}
//			response.flushBuffer();
//		}
//	}
	
	/**
	 * 结果通知
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/resultPayWeb", method = RequestMethod.POST)
	@ResponseBody
	public void resultPayWeb(AllInPayWeb allInPay) throws Exception{
		System.out.println("接收到通联网关回调通知");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		StringBuffer buffer =  getbuffer(request);
//		TreeMap<String,String> params = getParams(request);
//		TreeMap<String,String> map = params;//备份
		String result = "error";
		String sign = request.getParameter("signMsg");//	签名字符串	1024	不可空	以上所有非空参数按上述顺序与密钥组合，经加密后生成该值。
		try{
			//获取秘钥
			String md5Key =  SystemConfig.getMD5Key();
			boolean isSign = AllInPayUtil.validSign(buffer,md5Key,sign);// 接受到推送通知,首先验签
//			System.out.println("验签结果:"+isSign);
			//验签完毕进行业务处理
			if(isSign){
				result = this.allInPayService.resultPay(allInPay);
			}
		}catch (Exception e) {//处理异常
			e.printStackTrace();
		}finally{//收到通知,返回success
			response.getOutputStream().write(result.getBytes());
			response.flushBuffer();
		}
	}
	
//	private String getDefaultMsg(String errmsg){
//		BaseRsp rsp = BaseRsp.getFaildResult(errmsg);
//		return FuncUtil.toJsonResult(rsp);
//	}
	
	private TreeMap<String, String> getParams(HttpServletRequest request){
		TreeMap<String, String> map = new TreeMap<String, String>();
		Map reqMap = request.getParameterMap();
		for(Object key:reqMap.keySet()){
			map.put(key.toString(), ((String[])reqMap.get(key))[0]);
		}
		return map;
	}
	
	private StringBuffer getbuffer(HttpServletRequest request){
		StringBuffer buffer = new StringBuffer();
		forsign(buffer,"merchantId",request.getParameter("merchantId"));//商户号	30	不可空	数字串，与提交订单时的商户号保持一致
		forsign(buffer,"version",request.getParameter("version"));//	网关返回支付结果接口版本	10	不可空	固定选择值：v1.0；注意为小写字母
		forsign(buffer,"language",request.getParameter("language"));//	网页显示语言种类	2	可空	固定值：1；1代表中文显示
		forsign(buffer,"signType",request.getParameter("signType"));//	签名类型	2	不可空	固定选择值：0、1，与提交订单时的签名类型保持一致
		forsign(buffer,"payType",request.getParameter("payType"));//	支付方式	2	可空	字符串，与提交订单时的支付方式一致
		forsign(buffer,"issuerId",request.getParameter("issuerId"));//	发卡方机构代码	8	可空	固定为空
		forsign(buffer,"paymentOrderId",request.getParameter("paymentOrderId"));//	通联订单号	50	不可空	字符串，通联订单号
		forsign(buffer,"orderNo",request.getParameter("orderNo"));//	商户订单号	50	不可空	字符串，与提交订单时的商户订单号保持一致
		forsign(buffer,"orderDatetime",request.getParameter("orderDatetime"));//	商户订单提交时间	14	不可空	数字串，与提交订单时的商户订单提交时间保持一致
		forsign(buffer,"orderAmount",request.getParameter("orderAmount"));//	商户订单金额	10	不可空	整型数字，金额与币种有关如果是人民币，则单位是分，即10元提交时金额应为1000如果是美元，单位是美分，即10美元提交时金额为1000
		forsign(buffer,"payDatetime",request.getParameter("payDatetime"));//	支付完成时间	14	不可空	日期格式：yyyyMMDDhhmmss，例如：20121116020101
		forsign(buffer,"payAmount",request.getParameter("payAmount"));//	订单实际支付金额	10	不可空	整型数字，实际支付金额，用户实际支付币种为人民币；以分为单位，例如10元返回时应为1000分
		forsign(buffer,"ext1",request.getParameter("ext1"));//	扩展字段1	128	可空	字符串，与提交订单时的扩展字段1保持一致
		forsign(buffer,"ext2",request.getParameter("ext2"));//	扩展字段2	128	可空	字符串，与提交订单时的扩展字段2保持一致
		forsign(buffer,"payResult",request.getParameter("payResult"));//	处理结果	2	不可空	1：支付成功仅在支付成功时通知商户。商户可以通过查询接口查询订单状态。
		forsign(buffer,"errorCode",request.getParameter("errorCode"));//	错误代码	10	可空	固定为空
		forsign(buffer,"returnDatetime",request.getParameter("returnDatetime"));//	结果返回时间	14	不可空	系统返回支付结果的时间，日期格式：yyyyMMDDhhmmss
		return buffer;
	}
	
	void forsign(StringBuffer buffer,String name,String value){
		if(value == null || "".equals(value)){
			return;
		}
		buffer.append(name).append("=").append(value).append("&");
	} 
}
