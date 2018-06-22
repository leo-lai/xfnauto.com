package main.com.logistics.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.service.LogisticsConsignmentService;
import main.com.system.dao.po.SystemUsers;

/**
 * 托运单后台管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class LogisticsConsignmentController extends BaseController{
public static Logger logger = Logger.getLogger(LogisticsConsignmentController.class);
	
	@Autowired
	LogisticsConsignmentService logisticsConsignmentService;

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
			result = logisticsConsignmentService.consignmentList(search,result,users);
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
			result = logisticsConsignmentService.consignmentInfo(search,result,users);
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
	public Result consignmentEdit(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = logisticsConsignmentService.consignmentEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 托运单列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/consignmentGoodsCarList")
	public Result consignmentGoodsCarList(LogisticsConsignmentSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = logisticsConsignmentService.consignmentCarList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
