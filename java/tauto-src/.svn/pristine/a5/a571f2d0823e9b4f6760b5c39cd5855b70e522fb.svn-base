package main.com.system.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;
import main.com.system.dao.search.SystemWarehouseSearch;
import main.com.system.service.OrganizationService;

@Controller
@RequestMapping("/management/admin")
public class ManagerOrganizationController extends BaseController{
	
	public static Logger logger = Logger.getLogger(ManagerOrganizationController.class);
	
	@Autowired
	OrganizationService organizationService;
	
	/**
	 * 组织架构列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationList", method = RequestMethod.POST)
	public Result organizationList(OrganizationSearch search) {
		Result result = new Result();
		try {
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			result = organizationService.organizationList(search,result,systemUsers);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 组织架构详细信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationInfo", method = RequestMethod.POST)
	public Result organizationInfo(OrganizationSearch search) {
		Result result = new Result();
		try {
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			result = organizationService.organizationInfo(search,result,systemUsers);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 组织架构添加或修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationEdit", method = RequestMethod.POST)
	@LogPoint(logDes = "组织架构模块", operateModule = "用户修改组织")
	public Result organizationEdit(OrganizationSearch search) {
		Result result = new Result();
		try {
			result = organizationService.organizationEdit(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 组织架构禁用启用
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationOnOff", method = RequestMethod.POST)
	@LogPoint(logDes = "组织架构模块", operateModule = "用户启用或禁用组织")
	public Result organizationOnOff(OrganizationSearch search) {
		Result result = new Result();
		try {
			result = organizationService.organizationOnOff(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 获取自身或者下级架构列表（下拉列表）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationLevelList", method = RequestMethod.POST)
	public Result organizationLevelList(OrganizationSearch search) {
		Result result = new Result();
		try {
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			result = organizationService.organizationLevelList(search,result,systemUsers);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 获取自身或者下级架构列表（下拉列表）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationLevelListByLevel", method = RequestMethod.POST)
	public Result organizationLevelListByLevel(OrganizationSearch search) {
		Result result = new Result();
		try {
			result = organizationService.organizationLevelListByLevel(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 组织仓库列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationWarehouseList", method = RequestMethod.POST)
	public Result organizationWarehouseList(SystemWarehouseSearch search) {
		Result result = new Result();
		try {
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			result = organizationService.organizationWarehouseList(search,result,systemUsers);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 组织仓库添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationWarehouseEdit", method = RequestMethod.POST)
	@LogPoint(logDes = "组织架构模块", operateModule = "添加或修改组织仓库")
	public Result organizationWarehouseEdit(SystemWarehouseSearch search) {
		Result result = new Result();
		try {
			result = organizationService.organizationWarehouseEdit(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 组织仓库删除
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationWarehouseDalete", method = RequestMethod.POST)
	@LogPoint(logDes = "组织架构模块", operateModule = "删除组织仓库")
	public Result organizationWarehouseDalete(SystemWarehouseSearch search) {
		Result result = new Result();
		try {
			result = organizationService.organizationWarehouseDalete(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
}
