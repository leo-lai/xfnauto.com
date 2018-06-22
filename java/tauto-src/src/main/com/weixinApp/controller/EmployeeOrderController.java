package main.com.weixinApp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinApp.service.EmployeeOrderService;

/**
 * 员工端 ------- 订单模块
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/emInterface/employee")
public class EmployeeOrderController extends BaseController{
	public static Logger logger = Logger.getLogger(EmployeeOrderController.class);

	@Autowired
	EmployeeOrderService employeeService;
	
	/**
	 * 获取订单列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerOrderList")
	public Result customerOrderList(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.customerOrderList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
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
			result = employeeService.customerOrderInfo(search,result,users);
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
	@RequestMapping(value = "/customerOrderAllInfo", method = RequestMethod.POST)
	public Result customerOrderAllInfo(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.customerOrderAllInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 根据订单查看库存车辆
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerOrderStockCar", method = RequestMethod.POST)
	public Result customerOrderStockCar(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.customerOrderStockCar(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 出库订单的车辆
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerOrderStockCarPutout", method = RequestMethod.POST)
	@LogPoint(logDes = "员工端订单管理模块", operateModule = "出库订单的车辆")
	public Result customerOrderStockCarPutout(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.customerOrderStockCarPutout(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 *待上牌列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderLicensePlateList")
	public Result orderLicensePlateList(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.orderLicensePlateList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *上牌完成
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/licensePlateDone")
	@LogPoint(logDes = "员工端订单管理模块", operateModule = "上牌完成")
	public Result licensePlateDone(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.licensePlateDone(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *客户回访
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orderVisit")
	@LogPoint(logDes = "员工端订单管理模块", operateModule = "客户回访")
	public Result orderVisit(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.orderVisit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
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
	@LogPoint(logDes = "员工端订单管理模块", operateModule = "取消订单")
	public Result cancelOrder(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.cancelOrder(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *标记已过线检查
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/overTheLine")
	@LogPoint(logDes = "员工端订单管理模块", operateModule = "上牌完成")
	public Result overTheLine(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.overTheLine(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 待加装精品列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/carsProductsList")
	public Result carsProductsList(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.carsProductsList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 添加预计加装精品时间
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addCarsProductsEstimateDate")
	@LogPoint(logDes = "员工端订单管理模块", operateModule = "添加预计加装精品完成时间")
	public Result addCarsProductsEstimateDate(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.addCarsProductsEstimateDate(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 精品详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/carsProductsInfo")
	public Result carsProductsInfo(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.carsProductsInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 加装精品完成
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/carsProductsDone")
	@LogPoint(logDes = "员工端订单管理模块", operateModule = "加装精品完成")
	public Result carsProductsDone(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.carsProductsDone(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
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
			result = employeeService.editCustomerOrderShop(search,result,users);//employeeService.editCustomerOrder(search,result,users);
		} catch (Exception e) {
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 根据商城预订单创建买车单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createCustomerOrderShop", method = RequestMethod.POST)
	@LogPoint(logDes = "后台客户管理模块", operateModule = "编辑买车单")
	public Result editCustomerOrderShop(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.editCustomerOrderShop(search,result,users);
		} catch (Exception e) {
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 待贴膜列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/carsPadPastingList")
	public Result carsPadPastingList(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.carsPadPastingList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 *贴膜完成
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/carsPadPastingDone")
	@LogPoint(logDes = "员工端订单管理模块", operateModule = "贴膜完成")
	public Result carsPadPastingDone(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.carsPadPastingDone(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 交付车辆
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/turnOverVehicle", method = RequestMethod.POST)
	@LogPoint(logDes = "后台客户管理模块", operateModule = "交付车辆")
	public Result turnOverVehicle(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.turnOverVehicle(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 银行审核通过
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bankApprovalPass", method = RequestMethod.POST)
	@LogPoint(logDes = "员工端订单管理模块", operateModule = "更改为银行通过审核状态")
	public Result bankApprovalPass(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.bankApprovalPass(search,result,users);
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
	@LogPoint(logDes = "员工端订单管理模块", operateModule = "把分期改成全款")
	public Result changeFullPayment(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.changeFullPayment(search,result,users);
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
			result = employeeService.customerOrderPrint(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
}
