package main.com.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.customer.dao.search.CustomerUsersSearch;
import main.com.customer.service.CustomerOrderService;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;

/**
 * 客户订单管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerCustomerOrderController extends BaseController{

	public static Logger logger = Logger.getLogger(ManagerCustomerOrderController.class);
	
	@Autowired
	CustomerOrderService customerOrderService;
	
	/**
	 * 订单列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerOrderList", method = RequestMethod.POST)
	public Result customerOrderList(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.customerOrderList(search,result,users);
		} catch (Exception e) {
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 创建买车单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editCustomerOrder", method = RequestMethod.POST)
	@LogPoint(logDes = "后台客户管理模块", operateModule = "编辑买车单")
	public Result editCustomerOrder(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.editCustomerOrder(search,result,users);
		} catch (Exception e) {
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 订单进行支付
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/payInOrder", method = RequestMethod.POST)
	@LogPoint(logDes = "后台客户管理模块", operateModule = "创建支付定金")
	public Result payInOrder(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.payInOrder(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 查看订单详情
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerOrderInfo", method = RequestMethod.POST)
	public Result customerOrderInfo(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.customerOrderInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 *取消订单
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelOrder")
	@LogPoint(logDes = "PC端订单管理模块", operateModule = "取消订单")
	public Result cancelOrder(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.cancelOrder(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 银行审核通过
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bankApprovalPass", method = RequestMethod.POST)
	@LogPoint(logDes = "后台客户管理模块", operateModule = "更改为银行通过审核状态")
	public Result bankApprovalPass(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.bankApprovalPass(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 把分期改成全款
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeFullPayment", method = RequestMethod.POST)
	@LogPoint(logDes = "后台客户管理模块", operateModule = "更改为银行通过审核状态")
	public Result changeFullPayment(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.changeFullPayment(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 客户提车辆
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/turnOverVehicle", method = RequestMethod.POST)
	@LogPoint(logDes = "后台客户管理模块", operateModule = "交付车辆")
	public Result turnOverVehicle(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.turnOverVehicle(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 财务确认交付车辆
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/endOrder", method = RequestMethod.POST)
	@LogPoint(logDes = "后台客户管理模块", operateModule = "交付车辆")
	public Result endOrder(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.endOrder(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 银行查看贷款单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bankExamineOders", method = RequestMethod.POST)
	public Result bankExamineOders(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.bankExamineOders(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}	
	/**
	 * 查看订单支付记录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderPayList", method = RequestMethod.POST)
	public Result orderPayList(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.orderPayList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 查看订单各项费用
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderPriceList", method = RequestMethod.POST)
	public Result orderPriceList(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.orderPriceList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
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
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = customerOrderService.customerOrderPrint(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
}
