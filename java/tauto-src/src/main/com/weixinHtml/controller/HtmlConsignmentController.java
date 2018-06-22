package main.com.weixinHtml.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.dao.search.LogisticsDedicatedLineSearch;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.service.LogisticsDedicatedLineService;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinApp.service.EmployeeConsignmentService;

/**
 * 商城物流
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interfaceShop/consignment")
public class HtmlConsignmentController extends BaseController{

	public static Logger logger = Logger.getLogger(HtmlConsignmentController.class);

	@Autowired
	EmployeeConsignmentService employeeService;
	
	@Autowired
	LogisticsDedicatedLineService dedicatedLineService;

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
			result = employeeService.expensesCount(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}

	/**
	 * 专线资费设置列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logisticsLineList")
	public Result logisticsLineList(LogisticsDedicatedLineSearch search) {
		Result result = new Result();
		try {
			result = dedicatedLineService.LogisticsLineList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
