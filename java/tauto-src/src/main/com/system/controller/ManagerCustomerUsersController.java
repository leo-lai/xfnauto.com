package main.com.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.car.dao.search.CarsSearch;
import main.com.customer.dao.search.CustomerUsersSearch;
import main.com.customer.service.CustomerUsersService;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemUsersSearch;
import main.com.utils.UUIDUtils;

/**
 * 客户管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerCustomerUsersController extends BaseController{

	public static Logger logger = Logger.getLogger(ManagerCustomerUsersController.class);
	
	@Autowired
	CustomerUsersService customerUsersService;
	
	/**
	 * 用户添加
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/addCustomerUsersr", method = RequestMethod.POST)
//	@LogPoint(logDes = "后台客户管理模块", operateModule = "添加新客户")
//	public Result addCustomerUsers_old(CustomerUsersSearch search) {
//		Result result = new Result();
//		try {
//			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
//			result = customerUsersService.addCustomerUsers(search,result,users);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("系统请求出错",e);
//			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
//		}
//		return result;
//	}
	/**
	 * 用户添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addCustomerUsersr", method = RequestMethod.POST)
	@LogPoint(logDes = "后台客户管理模块", operateModule = "添加新客户")
	public Result addCustomerUsers(CustomerUsersSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerUsersService.addCustomerUsers(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 获取客户详细信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerUsersrInfo", method = RequestMethod.POST)
	public Result customerUsersrInfo(CustomerUsersSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerUsersService.customerUsersrInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
}
