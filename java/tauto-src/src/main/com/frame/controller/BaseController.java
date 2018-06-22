package main.com.frame.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.vo.LogisticsDriverVo;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.utils.StringUtil;
import main.com.weixinHtml.dao.po.ShopUsers;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import main.com.customer.dao.po.UsersTmpl;
import main.com.frame.domain.Result;
/**
 * 基础控制器，其他控制器需extends此控制器获得initBinder自动转换的功能
 * 
 * 
 */
@Controller
public class BaseController {

	protected static final Logger logger = Logger
			.getLogger(BaseController.class);

	protected HttpServletRequest request;

	protected ApplicationContext context;

	protected HttpServletResponse response ;
	
	/**
	 * 自动注入当前Request对象
	 * 
	 * @param request
	 */
	@ModelAttribute
	public void setReq(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * 自动注入当前Request对象
	 * 
	 * @param request
	 */
	@ModelAttribute
	public void setRes(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * 获得Spring容器对象
	 * 
	 * @return
	 */
	protected ApplicationContext getContext() {
		return context;
	}

	/**
	 * 获取HttpServletRequest对象
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * 获取HttpServletResponse对象
	 * 
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return response;
	}
	
	public ShopUsers supplementSearchShopUser(String sessionId){
		HttpSession session = request.getSession();
		if(StringUtil.isEmpty(sessionId)){
			return null;
		}
		ShopUsers shopUsers = (ShopUsers) session.getAttribute(sessionId);
		if(shopUsers == null){
			return null;
		}
		return shopUsers;
	}
	
	public Integer supplementSearchShop(String sessionId,Result result){
		HttpSession session = request.getSession();
		if(StringUtil.isEmpty(sessionId)){
			result.setError(ResultCode.CODE_STATE_4002, "登录信息错误");
			return null;
		}
		ShopUsers shopUsers = (ShopUsers) session.getAttribute(sessionId);
		if(shopUsers == null){
			result.setError(ResultCode.CODE_STATE_4002, "登录信息错误");
			return null;
		}
		return shopUsers.getShopUserId();
	}
	
	public Integer supplementSearch(String sessionId,Result result){
		HttpSession session = request.getSession();
		if(StringUtil.isEmpty(sessionId)){
			result.setError(ResultCode.CODE_STATE_4002, "登录信息错误");
			return null;
		}
		SystemUsers systemUsers = (SystemUsers) session.getAttribute(sessionId);
		if(systemUsers == null){
			result.setError(ResultCode.CODE_STATE_4002, "登录信息错误");
			return null;
		}
		return systemUsers.getUsersId();
	}
	
	public SystemUsersVo supplementSearchSystemUsers(String sessionId){
		HttpSession session = request.getSession();
		if(StringUtil.isEmpty(sessionId)){
			return null;
		}
		SystemUsersVo systemUsers = (SystemUsersVo) session.getAttribute(sessionId);
		if(systemUsers == null){
			return null;
		}
		return systemUsers;
	}
	
	public SystemUsers supplementSearchUser(String sessionId){
		HttpSession session = request.getSession();
		if(StringUtil.isEmpty(sessionId)){
			return null;
		}
		SystemUsers systemUsers = null;
		try {
			systemUsers = (SystemUsers) session.getAttribute(sessionId);
			return systemUsers;
		} catch (Exception e) {
			e.printStackTrace();
			if(systemUsers == null){
				return null;
			}else {
				return systemUsers;
			}
		}
	}
	
	public LogisticsDriverVo supplementSearchLogisticsDriver(String sessionId){
		HttpSession session = request.getSession();
		if(StringUtil.isEmpty(sessionId)){
			return null;
		}
		LogisticsDriverVo driverVo = (LogisticsDriverVo) session.getAttribute(sessionId);
		if(driverVo == null){
			return null;
		}
		return driverVo;
	}
	
	public UsersTmpl supplementSearchaAppUser(String sessionId){
		HttpSession session = request.getSession();
		if(StringUtil.isEmpty(sessionId)){
			return null;
		}
		UsersTmpl usersTmpl = (UsersTmpl) session.getAttribute(sessionId);
		if(usersTmpl == null){
			return null;
		}
		return usersTmpl;
	}
	
	public Boolean checkUpParameter(Object parameter,Result result,String desc){
		if(StringUtil.isEmpty(parameter+"")){
			result.setError(ResultCode.CODE_STATE_4005, desc+"不能为空");
			return true;
		}
		return false;
	}
	
	protected String getIpAddr() {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
