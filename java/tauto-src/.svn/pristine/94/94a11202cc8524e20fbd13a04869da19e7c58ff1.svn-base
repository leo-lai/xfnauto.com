package main.com.logistics.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.service.LogisticsDistributionService;
import main.com.system.dao.po.SystemUsers;

/**
 * 配送单管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class LogisticsDistributionController extends BaseController{
public static Logger logger = Logger.getLogger(LogisticsDistributionController.class);
	
	@Autowired
	LogisticsDistributionService logisticsDistributionService;

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
			result = logisticsDistributionService.distributionList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}

	/**
	 * 配送单详情(后台管理暂不需要详情)
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/distributionInfo")
	public Result distributionInfo(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = logisticsDistributionService.distributionInfo(search,result,users);
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
	public Result distributionEdit(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = logisticsDistributionService.distributionEdit(search,result,users);
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
			result = logisticsDistributionService.distributionGoodsCarList(search,result,users);
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
	public Result distributionGoodsCarDelete(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = logisticsDistributionService.distributionGoodsCarDelete(search,result,users);
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
	public Result distributionGoodsCarAdd(LogisticsDistributionSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = logisticsDistributionService.distributionGoodsCarAdd(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			if(result.getMessage() == null || "".equals(result.getMessage()+"")) {
				result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
			}
		}
		return result;
	}
}
