package main.com.weixinApp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.car.dao.search.CarsSearch;
import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinApp.service.AppCarsService;
import main.com.weixinApp.service.AppOderService;

/**
 * 用户订单跟踪
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interface/order")
public class AppOderController extends BaseController{

	public static Logger logger = Logger.getLogger(AppOderController.class);
	
	@Autowired
	AppOderService appOderService;
	
	@RequestMapping(value = "/orderInfo")
	@ResponseBody
	public Result orderInfo(CustomerOrderSearch search) {
		Result result = new Result();
		try{
			UsersTmpl users = supplementSearchaAppUser(search.getSessionId());
			if(users == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息失效，请刷新重试");
				return result;
			}else{
				search.setUsersTmpl(users);
			}
			result = appOderService.orderInfo(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	
	@RequestMapping(value = "/orderTrack")
	@ResponseBody
	public Result orderTrack(CustomerOrderSearch search) {
		Result result = new Result();
		try{
			UsersTmpl users = supplementSearchaAppUser(search.getSessionId());
			if(users == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息失效，请刷新重试");
				return result;
			}else{
				search.setUsersTmpl(users);
			}
			result = appOderService.orderTrack(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 订单打印，订单打印独立，兼容后期各种定制修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerOrderPrint", method = RequestMethod.POST)
	public Result customerOrderPrint(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			UsersTmpl users = supplementSearchaAppUser(search.getSessionId());
			result = appOderService.customerOrderPrint(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
}
