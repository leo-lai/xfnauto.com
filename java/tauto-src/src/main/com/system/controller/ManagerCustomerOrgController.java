package main.com.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.customer.dao.po.CustomerCar;
import main.com.customer.dao.search.CustomerCarSearch;
import main.com.customer.dao.search.CustomerCustomerOrgSearch;
import main.com.customer.dao.search.CustomerUsersSearch;
import main.com.customer.service.CustomerCustomerOrgService;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;

/**
 * 客户门店中间表（客户管理）
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerCustomerOrgController extends BaseController{

	public static Logger logger = Logger.getLogger(ManagerCustomerOrgController.class);
	
	@Autowired
	CustomerCustomerOrgService customerOrgService;

	/**
	 * 获取客户列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerOrgList")
	public Result customerOrgList(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrgService.customerOrgList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 获取追踪客户列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/trackCustomerOrgList")
	public Result trackCustomerOrgList(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			search.setIsTrack(true);
			result = customerOrgService.customerOrgList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 预约客户列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bespeakCustomerOrgList")
	public Result bespeakCustomerOrgList(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			search.setIsBespeak(true);
			result = customerOrgService.customerOrgList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 标记为追踪客户
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/trackCustomerOrg")
	@LogPoint(logDes = "后台客户管理模块", operateModule = "标记客户为追踪客户")
	public Result trackCustomerOrg(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrgService.trackCustomerOrg(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 标记为不购车客户
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/notBuyCustomerOrg")
	@LogPoint(logDes = "后台客户管理模块", operateModule = "标记客户为不购车客户")
	public Result notBuyCustomerOrg(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrgService.notBuyCustomerOrg(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 登记客户预约信息
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bespeakChangeCustomerOrg")
	@LogPoint(logDes = "后台客户管理模块", operateModule = "登记客户预约信息")
	public Result bespeakChangeCustomerOrg(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrgService.bespeakChangeCustomerOrg(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 更改销售顾问
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/systenUserChangeCustomerOrg")
	@LogPoint(logDes = "后台客户管理模块", operateModule = "更改销售顾问")
	public Result systenUserChangeCustomerOrg(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrgService.systenUserChangeCustomerOrg(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 添加备注
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addCustomerRemarks")
	@LogPoint(logDes = "后台客户管理模块", operateModule = "添加备注")
	public Result addCustomerRemarks(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrgService.addcustomerRemarks(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 修改个人信息
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeUserInfo")
	@LogPoint(logDes = "后台客户管理模块", operateModule = "修改用户个人信息")
	public Result changeUserInfo(CustomerCustomerOrgSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrgService.changeUserInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 添加用户车辆信息
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeUserCarInfo")
	@LogPoint(logDes = "后台客户管理模块", operateModule = "添加用户车辆信息")
	public Result changeUserCarInfo(CustomerCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrgService.changeUserCarInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
