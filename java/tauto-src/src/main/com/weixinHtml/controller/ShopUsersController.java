package main.com.weixinHtml.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.search.ShopUserSearch;
import main.com.weixinHtml.service.ShopUserService;

/**
 * 商城用户
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interfaceShop/shopUsers")
public class ShopUsersController extends BaseController{

	@Autowired
	private ShopUserService shopUsersService;
	
	/**
	 * 用户登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loginPassword", method = RequestMethod.POST)
	public Result shopLoginPassWork(ShopUserSearch search) {
		Result result = new Result();
		try {
			HttpSession session = request.getSession();
			result = shopUsersService.loginPassword(search,session.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 用户信息刷新
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/refreshCode", method = RequestMethod.POST)
	public Result refreshCode(ShopUserSearch search) {
		Result result = new Result();
		try {
			ShopUsers user = supplementSearchShopUser(search.getSessionId());
			if(user == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息已失效重新登录重试！");
				return result;
			}
			result = shopUsersService.refreshCode(search,user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 验证码登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loginCode", method = RequestMethod.POST)
	public Result shopLoginCode(ShopUserSearch search) {
		Result result = new Result();
		try {
			HttpSession session = request.getSession();
			result = shopUsersService.loginCodeOfShop(search,session.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 2017/06/01取消直接登录，改用静默登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/silenceLogin", method = RequestMethod.POST)
	public Result silenceLogin(ShopUserSearch search) {
		Result result = new Result();
		try {
			HttpSession session = request.getSession();
			result = shopUsersService.silenceLogin(search,session.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 用户开启或关闭推送
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/notify", method = RequestMethod.POST)
	public Result notify(ShopUserSearch search) {
		Result result = new Result();
		try {
			Integer userId = supplementSearchShop(search.getSessionId(),result);
			if(userId == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息已失效重新登录重试！");
				return result;
			}else{
				search.setShopUserId(userId);
			}
			result = shopUsersService.notify(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 用户登出
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loginOut", method = RequestMethod.POST)
	public Result shopLoginOut(ShopUserSearch search) {
		Result result = new Result();
		try {
			result = shopUsersService.loginOut(search);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 用户注册
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Result shopRegister(ShopUserSearch search) {
		Result result = new Result();
		try {
			result = shopUsersService.shopRegister(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 用户修改密码和忘记密码 修改密码需要多一个参数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public Result shopforgetPassWord(ShopUserSearch search) {
		Result result = new Result();
		try {
			result = shopUsersService.shopforgetPassWord(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 补充商户用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/supplementOrg", method = RequestMethod.POST)
	public Result supplementOrg(ShopUserSearch search) {
		Result result = new Result();
		try {
			ShopUsers user = supplementSearchShopUser(search.getSessionId());
			if(user == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息已失效重新登录重试！");
				return result;
			}
			result = shopUsersService.supplementOrg(search,result,user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 商城B端用户列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopOrgList", method = RequestMethod.POST)
	public Result shopOrgList(OrganizationSearch search) {
		Result result = new Result();
		try {
			SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
			if(user == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息已失效重新登录重试！");
				return result;
			}
			result = shopUsersService.shopOrgList(search,result,user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 审核B端用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopOrgAudit", method = RequestMethod.POST)
	public Result shopOrgAudit(ShopUserSearch search) {
		Result result = new Result();
		try {
			SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
			if(user == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息已失效重新登录重试！");
				return result;
			}
			result = shopUsersService.shopOrgAudit(search,result,user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 配置B端用户通联支付信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/allInPayConfigure", method = RequestMethod.POST)
	public Result allInPayConfigure(ShopUserSearch search) {
		Result result = new Result();
		try {
			SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
			if(user == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息已失效重新登录重试！");
				return result;
			}
			result = shopUsersService.allInPayConfigure(search,result,user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * B端门店信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myOrgInfo", method = RequestMethod.POST)
	public Result myOrgInfo(ShopUserSearch search) {
		Result result = new Result();
		try {
			ShopUsers user = supplementSearchShopUser(search.getSessionId());
			if(user == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息已失效重新登录重试！");
				return result;
			}
			result = shopUsersService.myOrgInfo(search,result,user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
}
