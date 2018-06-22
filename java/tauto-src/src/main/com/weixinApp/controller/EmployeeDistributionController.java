package main.com.weixinApp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.search.LogisticsCarSearch;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.dao.search.UpdateDistributionStateSearch;
import main.com.logistics.service.LogisticsDriverService;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinApp.service.EmployeeDistributionService;

/**
 * 员工端 --物流
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/emInterface/employee")
public class EmployeeDistributionController extends BaseController{

	public static Logger logger = Logger.getLogger(EmployeeDistributionController.class);

	@Autowired
	EmployeeDistributionService employeeService;
	
	@Autowired
	private LogisticsDriverService logisticsDriverService;
	
	/**
	 * 配送单列表
	 * @param menuSearch
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/distributionList")
	public Result distributionList(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.distributionList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 配送单详情
	 * @param menuSearch
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/distributionInfo")
	public Result distributionInfo(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.distributionInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 查看已分配的车辆(已作废)
	 * @param menuSearch
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/distributionGoodsCarList")
//	public Result distributionGoodsCarList(LogisticsDistributionSearch search) {
//		Result result = new Result();
//		try {
//			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
//			result = employeeService.distributionGoodsCarList(search,result,users);
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
//		}
//		return result;
//	}
	
	/**
	 * 上传装车卸车图片（已单独放到司机端）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/distributionGoodsCarImage")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "上传装车卸车图片")
	public Result distributionGoodsCarImage(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.distributionGoodsCarImage(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 托运单发起支付(POS)
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logisticsInPayPOS")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "托运单发起支付")
	public Result consignmentInPayPOS(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.logisticsInPayPOS(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	

	/**
	 * 配送单编辑
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/distributionEdit")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "配送单编辑")
	public Result distributionEdit(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.distributionEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 查看已分配的车辆
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/distributionGoodsCarList")
	public Result distributionGoodsCarList(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.distributionGoodsCarList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 删除已分配的车辆
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/distributionGoodsCarDelete")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "删除已分配的车辆")
	public Result distributionGoodsCarDelete(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.distributionGoodsCarDelete(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 添加待分配的车辆（分配车辆）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/distributionGoodsCarAdd")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "添加待分配的车辆")
	public Result distributionGoodsCarAdd(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.distributionGoodsCarAdd(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			if(result.getMessage() == null || "".equals(result.getMessage()+"")) {
				result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
			}
		}
		return result;
	}
	
	/**
	 * 板车列表（下拉）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logisticsCarListList")
	public Result logisticsCarListList(LogisticsCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.logisticsCarListList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 派单
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/distributionDelivery")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "派单")
	public Result distributionDelivery(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.distributionDelivery(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 更新物流单状态
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/updateDistributionState")
	@ResponseBody
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "更新物流单状态")
	public Result updateDistributionState(UpdateDistributionStateSearch search) {
		return logisticsDriverService.updateDistributionState(search);
	}
}
