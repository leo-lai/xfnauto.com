package main.com.logistics.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.search.LogisticsDedicatedLineSearch;
import main.com.logistics.service.LogisticsDedicatedLineService;
import main.com.system.dao.po.SystemUsers;

/**
 * 物流托运 ----- 专线资费设置
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/emInterface/employee")
public class LogisticsDedicatedLineController extends BaseController{
	public static Logger logger = Logger.getLogger(LogisticsDedicatedLineController.class);
	
	@Autowired
	LogisticsDedicatedLineService dedicatedLineService;
	
	/**
	 * 专线资费设置列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dedicatedLineList")
	public Result dedicatedLineList(LogisticsDedicatedLineSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = dedicatedLineService.dedicatedLineList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
//	/**
//	 * 专线和非专线列表（列表）
//	 * @param menuSearch
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/dedicatedLineListList")
//	public Result dedicatedLineListList(LogisticsDedicatedLineSearch search) {
//		Result result = new Result();
//		try {
//			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
//			result = dedicatedLineService.dedicatedLineListList(search,result,users);
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
//		}
//		return result;
//	}
	
	/**
	 * 专线资费设置编辑
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dedicatedLineEdit")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "专线资费设置编辑")
	public Result dedicatedLineEdit(LogisticsDedicatedLineSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = dedicatedLineService.dedicatedLineEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 专线资费设置删除
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dedicatedLineDelete")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "专线资费设置删除")
	public Result dedicatedLineDelete(LogisticsDedicatedLineSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = dedicatedLineService.dedicatedLineDelete(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
