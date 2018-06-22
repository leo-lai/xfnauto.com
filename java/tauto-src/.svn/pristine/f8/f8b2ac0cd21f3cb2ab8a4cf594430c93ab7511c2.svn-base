package main.com.weixinApp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.dao.search.UsersTmplSearch;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;
import main.com.weixinApp.service.AppGeneralizeService;

/**
 * 推广预约
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interface/generalize")
public class AppGeneralizeController extends BaseController{

	public static Logger logger = Logger.getLogger(AppGeneralizeController.class);
	
	@Autowired
	AppGeneralizeService appGeneralizeService;
	
	
	/**
	 * 门店列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationList", method = RequestMethod.POST)
	public Result organizationList(OrganizationSearch search) {
		Result result = new Result();
		try {
			UsersTmpl users = supplementSearchaAppUser(search.getSessionId());
			if(users == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息失效，请刷新重试");
				return result;
			}
			result = appGeneralizeService.organizationList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 *	门店预约
	 * @param search
	 * @return
	 */
	@RequestMapping("/bespeak")
	@ResponseBody
	public Result bespeak(UsersTmplSearch search){
		Result result = new Result();
		try {
			UsersTmpl users = supplementSearchaAppUser(search.getSessionId());
			if(users == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息失效，请刷新重试");
				return result;
			}else{
				search.setUsersTmpl(users);
			}
			result = appGeneralizeService.bespeak(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 *	推广信息
	 * @param search
	 * @return
	 */
	@RequestMapping("/generalizeInfo")
	@ResponseBody
	public Result generalizeInfo(UsersTmplSearch search){
		Result result = new Result();
		try {
			UsersTmpl users = supplementSearchaAppUser(search.getSessionId());
			if(users == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息失效，请刷新重试");
				return result;
			}else{
				search.setUsersTmpl(users);
			}
			result = appGeneralizeService.generalizeInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
}
