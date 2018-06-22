package main.com.weixinApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.search.LogisticsCarSearch;
import main.com.logistics.service.LogisticsCarService;
import main.com.system.dao.po.SystemUsers;

/**
 * 物流板车管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/emInterface/employee")
public class EmployeeLogisticsCarController extends BaseController{


	@Autowired
	LogisticsCarService logisticsCarService; //直接使用其他service，物流变得太多了，暂时保留这样两边一起使用
	
	/**
	 * 板车列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logisticsCarList")
	public Result logisticsCarList(LogisticsCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = logisticsCarService.logisticsCarList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
//	/**
//	 * 板车列表（下拉）（已在物流单EmployeeDistributionController中存在）
//	 * @param menuSearch
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/logisticsCarListList")
//	public Result logisticsCarListList(LogisticsCarSearch search) {
//		Result result = new Result();
//		try {
//			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
//			result = logisticsCarService.logisticsCarListList(search,result,users);
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
//		}
//		return result;
//	}
	
	/**
	 * 板车编辑
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logisticsCarEdit")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "板车编辑")
	public Result logisticsCarEdit(LogisticsCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = logisticsCarService.logisticsCarEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 板车启用禁用
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logisticsCarIsEnable")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "板车启用禁用")
	public Result logisticsCarIsEnable(LogisticsCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = logisticsCarService.logisticsCarIsEnable(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
