package main.com.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.customer.dao.search.CustomerCustomerOrgSearch;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemGroupingSearch;
import main.com.system.dao.search.SystemUserGroupingSearch;
import main.com.system.service.SystemGroupingService;

/**
 * 用户分组 ------ 数据权限管理
 * 
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerSystemGroupingController extends BaseController{
	public static Logger logger = Logger.getLogger(ManagerSystemGroupingController.class);

	@Autowired
	SystemGroupingService systemGroupingService;

	/**
	 * 获取所有组别列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/systemGroupingList")
	public Result customerOrgList(SystemGroupingSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = systemGroupingService.systemGroupingList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 编辑分组
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/systemGroupingEdit")
	public Result systemGroupingEdit(SystemGroupingSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = systemGroupingService.systemGroupingEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 编辑组内成员
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/systemUserGroupingEdit")
	public Result systemUserGroupingEdit(SystemUserGroupingSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = systemGroupingService.systemUserGroupingEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 删除组内成员
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/systemUserGroupingDalete")
	public Result systemUserGroupingDalete(SystemUserGroupingSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = systemGroupingService.systemUserGroupingDalete(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 删除组
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/systemGroupingDalete")
	public Result systemGroupingDalete(SystemGroupingSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = systemGroupingService.systemGroupingDalete(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 查看组内成员
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/systemUserGroupingList")
	public Result systemUserGroupingList(SystemGroupingSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = systemGroupingService.systemUserGroupingList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
