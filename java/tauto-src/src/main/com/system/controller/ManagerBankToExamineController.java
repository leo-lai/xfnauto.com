package main.com.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.stock.dao.search.StockOrderSearch;
import main.com.system.dao.po.SystemUsers;
import main.com.system.service.BankToExamineService;

@Controller
@RequestMapping("/management/admin")
public class ManagerBankToExamineController extends BaseController{

	public static Logger logger = Logger.getLogger(ManagerBankToExamineController.class);
	
	@Autowired
	BankToExamineService bankToExamineService;

	/**
	 *获取客户订车单列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bankToExamineOrderList")
	public Result bankToExamineOrderList(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = bankToExamineService.customerOrderList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 审核贷款
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bankToExamineOrder")
	@LogPoint(logDes = "银行审核贷款模块", operateModule = "提交审核结果")
	public Result bankToExamineOrder(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = bankToExamineService.bankToExamineOrder(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
