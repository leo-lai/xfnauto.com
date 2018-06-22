package main.com.weixinApp.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;

import main.com.exception.BusinessException;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemUsersSearch;
import main.com.utils.StringUtil;
import main.com.weixinApp.dao.search.SalesPerformanceSearch;
import main.com.weixinApp.service.EmployeeUserService;

/**
 * 员工端 -- 个人中心
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/emInterface/employee")
public class EmployeeUserController extends BaseController{
public static Logger logger = Logger.getLogger(EmployeeUserController.class);
	
	@Autowired
	EmployeeUserService employeeService;
	
	/**
	 * 用户登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@LogPoint(logDes = "员工端模块", operateModule = "用户登录")
	public Result login(SystemUsersSearch search) {
		Result result = new Result();
		try {
			if(StringUtil.isEmpty(search.getPhoneNumber())) {
				result.setError(ResultCode.CODE_STATE_4005, "请输入电话号码");
				return result;
			}
			if(checkUpParameter(search.getPassword(), result, "登录密码"))return result;
			HttpSession session = request.getSession();
			employeeService.login(search,result,session);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 用户登录登出
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loginOut", method = RequestMethod.POST)
	@LogPoint(logDes = "员工端模块", operateModule = "用户注销登陆")
	public Result loginOut(SystemUsersSearch search) {
		Result result = new Result();
		try {
			if(checkUpParameter(search.getSessionId(), result, "登录凭据不能为空"))return result;
			result = employeeService.loginOut(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 用户修改密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@LogPoint(logDes = "员工端模块", operateModule = "用户修改密码")
	public Result changePassword(SystemUsersSearch search) {
		Result result = new Result();
		try {
			if(checkUpParameter(search.getPasswordOld(), result, "旧密码不能为空"))return result;
			if(checkUpParameter(search.getPassword(), result, "新密码"))return result;
			HttpSession session = request.getSession();
			employeeService.changePassword(search,result,session);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 获取销售顾问下拉数据列表
	 */
	@ResponseBody
	@RequestMapping(value = "/salesList", method = RequestMethod.POST)
	public Result salesList(SystemUsersSearch search) {
		Result result = new Result();
		try {
//			result = systemUsersService.userList(search,result);//同级获取
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			search.setOrgCode(systemUsers.getOrgCode());
			result = employeeService.salesList(search,result,systemUsers);//连带下级获取
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 获取销售顾问下拉数据列表
	 */
	@ResponseBody
	@RequestMapping(value = "/orgOneSelfList", method = RequestMethod.POST)
	public Result orgOneSelfList(SystemUsersSearch search) {
		Result result = new Result();
		try {
//			result = systemUsersService.userList(search,result);//同级获取
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
//			search.setOrgCode(systemUsers.getOrgCode());
			result = employeeService.orgOneSelfList(search,result,systemUsers);//连带下级获取
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 上传我的微信二维码
	 */
	@ResponseBody
	@RequestMapping(value = "/weixinQrImage", method = RequestMethod.POST)
	public Result weixinQrImage(SystemUsersSearch search) {
		Result result = new Result();
		try {
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.weixinQrImage(search,result,systemUsers);//连带下级获取
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 获取司机下拉列表
	 */
	@ResponseBody
	@RequestMapping(value = "/orgOneSelfDriverList", method = RequestMethod.POST)
	public Result orgOneSelfDriverList(SystemUsersSearch search) {
		Result result = new Result();
		try {
//			result = systemUsersService.userList(search,result);//同级获取
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
//			search.setOrgCode(systemUsers.getOrgCode());
			result = employeeService.orgOneSelfDriverList(search,result,systemUsers);//连带下级获取
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 查询销售业绩
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/querySalesPerformance",method = RequestMethod.POST)
	public Result querySalesPerformance(SalesPerformanceSearch search) throws Exception{
		SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
		search.setSystemUserId(systemUsers.getUsersId());
		if(null == search.getQueryDate()) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "查询年月不能为空");
		}
		return employeeService.querySalesPerformance(search);
	}
}
