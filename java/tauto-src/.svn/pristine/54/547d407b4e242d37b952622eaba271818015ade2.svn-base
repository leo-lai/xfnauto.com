package main.com.system.controller;

import javax.servlet.http.HttpSession;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemUsersSearch;
import main.com.system.service.SystemUsersService;
import main.com.utils.StringUtil;
import main.com.utils.UUIDUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/management/admin")
public class ManagerUserController extends BaseController{

	public static Logger logger = Logger.getLogger(ManagerUserController.class);
	
	@Autowired
	SystemUsersService systemUsersService;
	
	/**
	 * 用户登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@LogPoint(logDes = "后台用户模块", operateModule = "用户登录")
	public Result login(SystemUsersSearch search) {
		Result result = new Result();
		try {
			if(StringUtil.isEmpty(search.getPhoneNumber()) && StringUtil.isEmpty(search.getUserName())) {
				result.setError(ResultCode.CODE_STATE_4005, "请输入电话号码");
				return result;
			}
			if(checkUpParameter(search.getPassword(), result, "登录密码"))return result;
			HttpSession session = request.getSession();
			systemUsersService.login(search,result,session);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 用户登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loginOut", method = RequestMethod.POST)
	@LogPoint(logDes = "后台用户模块", operateModule = "用户注销登陆")
	public Result loginOut(SystemUsersSearch search) {
		Result result = new Result();
		try {
			if(checkUpParameter(search.getSessionId(), result, "登录凭据不能为空"))return result;
			if(systemUsersService.loginOut(search,result)){
			}
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
	@LogPoint(logDes = "后台用户模块", operateModule = "用户修改密码")
	public Result changePassword(SystemUsersSearch search) {
		Result result = new Result();
		try {
			if(checkUpParameter(search.getPasswordOld(), result, "旧密码不能为空"))return result;
			if(checkUpParameter(search.getPassword(), result, "新密码"))return result;
			HttpSession session = request.getSession();
			systemUsersService.changePassword(search,result,session);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 管理员重置密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/changeOtherPassword", method = RequestMethod.POST)
	@LogPoint(logDes = "后台用户模块", operateModule = "重置用户密码")
	public Result changeOtherPassword(SystemUsersSearch search) {
		Result result = new Result();
		try {
//			if(checkUpParameter(search.getPasswordOld(), result, "旧密码不能为空"))return result;
//			if(checkUpParameter(search.getPassword(), result, "新密码"))return result;
			HttpSession session = request.getSession();
			systemUsersService.changeOtherPassword(search,result,session);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 用户添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	@LogPoint(logDes = "后台用户模块", operateModule = "添加新用户")
	public Result addUser(SystemUsersSearch search) {
		Result result = new Result();
		try {
			systemUsersService.addUser(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(UUIDUtils.create());
	}
	
	/**
	 * 系统用户列表 
	 */
	@ResponseBody
	@RequestMapping(value = "/userList", method = RequestMethod.POST)
	public Result userList(SystemUsersSearch search) {
		Result result = new Result();
		try {
//			result = systemUsersService.userList(search,result);//同级获取
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			search.setOrgCode(systemUsers.getOrgCode());
			result = systemUsersService.userList(search,result);//连带下级获取
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 * 启用禁用用户
	 *优化方案：深度强化权限管理，不只是菜单，还要检查是否是该用户下的可操作数据，暂没有深化权限控制
	 *现在是所有拥有此菜单权限的人都能修改该数据
	 */
	@ResponseBody
	@RequestMapping(value = "/userIsEnable", method = RequestMethod.POST)
	@LogPoint(logDes = "后台用户模块", operateModule = "开启或禁用用户")
	public Result userIsEnable(SystemUsersSearch search) {
		Result result = new Result();
		try {
			Integer userId = this.supplementSearch(search.getSessionId(), result);
			if(userId == null) {
				result.setError(ResultCode.CODE_STATE_4002, "登录信息失效");
				return result;
			}
			result = systemUsersService.userIsEnable(search,result,userId);
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
			result = systemUsersService.salesList(search,result);//连带下级获取
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
			result = systemUsersService.orgOneSelfList(search,result,systemUsers);//连带下级获取
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
}
