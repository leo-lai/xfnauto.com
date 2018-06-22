package main.com.allInPay.controller;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.allInPay.dao.po.BaseRsp;
import main.com.allInPay.dao.po.QueryRsp;
import main.com.allInPay.service.AllInPayPostService;
import main.com.allInPay.utils.AppConstants;
import main.com.allInPay.utils.FuncUtil;

/**
 * 支付
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/allInPayPos")
public class AllInPayPostController {
	
	@Autowired
	private AllInPayPostService allInPayService;
	
	/**
	 * 查询订单
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOrder")
	@ResponseBody
	public void queryOrder(HttpServletResponse response,HttpServletRequest request) throws Exception {
		TreeMap<String,String> params = getParams(request);
//		System.out.println("接收到参数："+params);
		String rspStr = "";
		try{
			if(FuncUtil.validSign(params, AppConstants.APPKEY)){//验签成功,进行业务处理
				QueryRsp rsp = this.allInPayService.queryOrder(params);
				rspStr = FuncUtil.toJsonResult(rsp);
			}else{//验签失败
				rspStr = getDefaultMsg("验签失败");
			}
		}catch (Exception e) {//处理异常
			e.printStackTrace();
			rspStr = getDefaultMsg("处理异常");
		}finally{
//			System.out.println("返回数据:"+rspStr);
			if(!FuncUtil.isEmpty(rspStr)){
				response.getOutputStream().write(rspStr.getBytes("utf-8"));
			}
			response.flushBuffer();
		}
	}
	
	/**
	 * 结果通知
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/resultPay")
	@ResponseBody
	public void resultPay(HttpServletResponse response,HttpServletRequest request) throws Exception{
		System.out.println("接收到通知");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		TreeMap<String,String> params = getParams(request);
		String result = "error";
		try{
			boolean isSign = FuncUtil.validSign(params, AppConstants.APPKEY);// 接受到推送通知,首先验签
			System.out.println("验签结果:"+isSign);
			//验签完毕进行业务处理
			if(isSign){
				result = this.allInPayService.resultPay(params);
			}
		}catch (Exception e) {//处理异常
			e.printStackTrace();
		}finally{//收到通知,返回success
			response.getOutputStream().write(result.getBytes());
			response.flushBuffer();
		}
	}
	
	/**
	 * 结果通知
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/resultPay_noSing")
	@ResponseBody
	public void resultPay_noSing(HttpServletResponse response,HttpServletRequest request) throws Exception{
		System.out.println("接收到通知");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		TreeMap<String,String> params = getParams(request);
		System.out.println("接收到的参数："+params);
		String result = "error";
		try{
//			boolean isSign = FuncUtil.validSign(params, AppConstants.APPKEY);// 接受到推送通知,首先验签
//			System.out.println("验签结果:"+isSign);
			//验签完毕进行业务处理
//			if(isSign){
				result = this.allInPayService.resultPay_noSing(params);
//			}
		}catch (Exception e) {//处理异常
			e.printStackTrace();
		}finally{//收到通知,返回success
			response.getOutputStream().write(result.getBytes());
			response.flushBuffer();
		}
	}
	
	private String getDefaultMsg(String errmsg){
		BaseRsp rsp = BaseRsp.getFaildResult(errmsg);
		return FuncUtil.toJsonResult(rsp);
	}
	
	private TreeMap<String, String> getParams(HttpServletRequest request){
		TreeMap<String, String> map = new TreeMap<String, String>();
		Map reqMap = request.getParameterMap();
		for(Object key:reqMap.keySet()){
			map.put(key.toString(), ((String[])reqMap.get(key))[0]);
		}
		return map;
	}
	

}
