package main.com.logistics.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.search.LogisticsCarSearch;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.service.LogisticsCarService;
import main.com.system.dao.po.SystemUsers;

/**
 * 板车管理 
 * @author Zwen
 *
 * 2018-02-27 板车管理PC端暂时丢弃
 */
@Controller
@RequestMapping("/management/admin")
public class LogisticsCarController extends BaseController{
public static Logger logger = Logger.getLogger(LogisticsCarController.class);
	
	@Autowired
	LogisticsCarService logisticsCarService;
	
	/**
	 * 板车列表
	 * @param menuSearch
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/logisticsCarList")
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
	
	/**
	 * 板车列表（下拉）
	 * @param menuSearch
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/logisticsCarListList")
	public Result logisticsCarListList(LogisticsCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = logisticsCarService.logisticsCarListList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 板车编辑
	 * @param menuSearch
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/logisticsCarEdit")
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
//	@ResponseBody
//	@RequestMapping(value = "/logisticsCarIsEnable")
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
