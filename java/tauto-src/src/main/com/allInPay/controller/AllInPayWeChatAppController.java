package main.com.allInPay.controller;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.allInPay.dao.po.AllInPayWeb;
import main.com.allInPay.service.AllInPayWeChatAppService;
import main.com.allInPay.service.AllInPayWebService;
import main.com.allInPay.utils.AllInPayUtil;
import main.com.allInPay.utils.SybUtil;
import main.com.frame.controller.BaseController;
import main.com.pay.allInPay.SystemConfig;
import main.com.weixin.dao.vo.WeixinAppTokenVo;
import main.com.weixin.service.WeixinAppTokenService;

/**
 * 通联支付(微信小程序)
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/allInPayWeChatApp")
public class AllInPayWeChatAppController extends BaseController{
	
	@Autowired
	private AllInPayWeChatAppService payWeChatAppService;
	
	@Autowired
	WeixinAppTokenService weixinAppTokenService;

	@RequestMapping("/resultPay")
	protected void resultPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("接收到通知");
		request.setCharacterEncoding("gbk");//通知传输的编码为GBK
		response.setCharacterEncoding("gbk");
		TreeMap<String,String> params = getParams(request);//动态遍历获取所有收到的参数,此步非常关键,因为收银宝以后可能会加字段,动态获取可以兼容
		try {
			WeixinAppTokenVo appTokenVo = weixinAppTokenService.getAccessTokenAllInPay();
			boolean isSign = SybUtil.validSign(params, appTokenVo.getAllInPayKey());// 接受到推送通知,首先验签
			System.out.println("验签结果:"+isSign);
			//验签完毕进行业务处理
			if("SUCCESS".equals(params.get("retcode"))){//支付接收成功
				payWeChatAppService.resultPay(params);
			}
		} catch (Exception e) {//处理异常
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{//收到通知,返回success
			response.getOutputStream().write("success".getBytes());
			response.flushBuffer();
		}
	}
	
	/**
	 * 动态遍历获取所有收到的参数,此步非常关键,因为收银宝以后可能会加字段,动态获取可以兼容由于收银宝加字段而引起的签名异常
	 * @param request
	 * @return
	 */
	private TreeMap<String, String> getParams(HttpServletRequest request){
		TreeMap<String, String> map = new TreeMap<String, String>();
		Map reqMap = request.getParameterMap();
		for(Object key:reqMap.keySet()){
			String value = ((String[])reqMap.get(key))[0];
			System.out.println(key+";"+value);
			map.put(key.toString(),value);
		}
		return map;
	}
}
