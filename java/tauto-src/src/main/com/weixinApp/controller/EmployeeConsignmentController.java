package main.com.weixinApp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinApp.service.EmployeeConsignmentService;

/**
 * 员工端 --托运单
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/emInterface/employee")
public class EmployeeConsignmentController extends BaseController{

	public static Logger logger = Logger.getLogger(EmployeeConsignmentController.class);

	@Autowired
	EmployeeConsignmentService employeeService;

	/**
	 * 托运单列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/consignmentList")
	public Result consignmentList(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.consignmentList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 托运单详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/consignmentInfo")
	public Result consignmentInfo(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.consignmentInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 编辑接车人
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/extractTheCarPersons")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "编辑接车人")
	public Result extractTheCarPersons(@RequestBody LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.extractTheCarPersons(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 编辑送车人
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/leaveTheCarPersons")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "编辑送车人")
	public Result leaveTheCarPersons(@RequestBody LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.leaveTheCarPersons(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 托运单编辑
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/consignmentEdit")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "托运单编辑")
	public Result consignmentEdit(@RequestBody LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.consignmentEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 托运单发起支付(小程序支付)
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/consignmentInPay")
	public Result consignmentInPay(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.consignmentInPay(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 计算托运单资费
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/expensesCount")
	public Result expensesCount(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.expensesCount(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 根据配送单ID查看物流实时位置
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logisticsDistributionGPS")
	public Result logisticsDistributionGPS(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.logisticsDistributionGPS(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 根据物流单ID查看物流实时位置
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logisticsConsignmentGPS")
	public Result logisticsConsignmentGPS(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.logisticsConsignmentGPS(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 获取两点距离（公里数）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMileage")
	public Result getMileage(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.getMileage(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 查看运费详情 LogisticsGoodsCarCostsDao
	 */
	@ResponseBody
	@RequestMapping(value = "/getExpensesInfo")
	public Result getExpensesInfo(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.getExpensesInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 配送单列表
	 * @param menuSearch
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/distributionList")
//	public Result distributionList(LogisticsDistributionSearch search) {
//		Result result = new Result();
//		try {
//			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
//			result = employeeService.distributionList(search,result,users);
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
//		}
//		return result;
//	}

	/**
	 * 配送单详情
	 * @param menuSearch
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/distributionInfo")
//	public Result distributionInfo(LogisticsDistributionSearch search) {
//		Result result = new Result();
//		try {
//			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
//			result = employeeService.distributionInfo(search,result,users);
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
//		}
//		return result;
//	}
	
	/**
	 * 托运单电子合同
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/consignmentContract")
	public Result consignmentContract(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			result = employeeService.consignmentContract(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
