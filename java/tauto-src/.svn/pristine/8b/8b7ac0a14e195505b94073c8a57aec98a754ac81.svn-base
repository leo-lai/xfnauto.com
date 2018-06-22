package main.com.weixinApp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.car.dao.search.OrgCarsConfigureSearch;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.stock.dao.search.StockCarSearch;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinApp.service.EmployeeOrderService;
import main.com.weixinApp.service.EmployeeOrgCarsConfigureService;

/**
 * 员工端 ------- 商品配置（在售车型管理）
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/emInterface/employee")
public class EmployeeOrgCarsConfigureController extends BaseController{
	public static Logger logger = Logger.getLogger(EmployeeOrgCarsConfigureController.class);

	@Autowired
	EmployeeOrgCarsConfigureService employeeService;

	/**
	 * 编辑在售车型
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editOrgCarsConfigure")
	@LogPoint(logDes = "员工端商品配置模块", operateModule = "编辑在售车型")
	public Result editOrgCarsConfigure(OrgCarsConfigureSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.editOrgCarsConfigure(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *在售车型列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orgCarsConfigureList")
	public Result storageCarList(OrgCarsConfigureSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.orgCarsConfigureList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *在售车型详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orgCarsConfigureInfo")
	public Result orgCarsConfigureInfo(OrgCarsConfigureSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.orgCarsConfigureInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
