package main.com.weixinApp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.dao.search.UsersTmplSearch;
import main.com.frame.cache.CacheEntity;
import main.com.frame.cache.CacheTimerHandler;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.utils.GeneralConstant.SystemBoolean;
import main.com.utils.rlycode.RLYUtils;
import main.com.weixinApp.service.AppUsersService;
/**
 * 小程序用户管理
 * @author User
 *
 */
@Controller
@RequestMapping("/interface/weixinapp")
public class AppUsersController extends BaseController{

	public static Logger logger = Logger.getLogger(AppUsersController.class);

	@Autowired
	AppUsersService appUsersService;
	
	/**
	 * 用户小程序登录
	 * @param search
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Result decodeUserInfo(UsersTmplSearch search){
		Result result = new Result();
		try {
			result = appUsersService.decodeUserInfo(search,result,request.getSession().getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级");
		}
		return result;
	}
	
	/**
	 * 刷新我的个人信息
	 * @param search
	 * @return
	 */
	@RequestMapping("/refreshMyInfo")
	@ResponseBody
	public Result refreshMyInfo(UsersTmplSearch search){
		Result result = new Result();
		try {
			UsersTmpl users = supplementSearchaAppUser(search.getSessionId());
			if(users == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息失效，请刷新重试");
				return result;
			}else{
				search.setUsersTmpl(users);
			}
			result = appUsersService.refreshMyInfo(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 *	门店预约前置
	 * @param search
	 * @return
	 */
	@RequestMapping("/bespeakBefor")
	@ResponseBody
	public Result bespeakBefor(UsersTmplSearch search){
		Result result = new Result();
		try {
			UsersTmpl users = supplementSearchaAppUser(search.getSessionId());
			if(users == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息失效，请刷新重试");
				return result;
			}else{
				search.setUsersTmpl(users);
			}
			result = appUsersService.bespeakBefor(search,result);
		} catch (Exception e) {
			e.printStackTrace();
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
			result = appUsersService.bespeak(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	/**
	 *	电话号绑定
	 * @param search
	 * @return
	 */
	@RequestMapping("/phonebinding")
	@ResponseBody
	public Result phonebinding(UsersTmplSearch search){
		Result result = new Result();
		try {
			UsersTmpl users = supplementSearchaAppUser(search.getSessionId());
			if(users == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息失效，请刷新重试");
				return result;
			}else{
				search.setUsersTmpl(users);
			}
			result = appUsersService.phonebinding(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 手机验证码
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/phoneVerifyCode", method = RequestMethod.POST)
	public synchronized Result getPhoneVerifyCode(String phoneNumber) throws Exception {
        //Integer type = 0;
        Result result = new Result();
        int sendQuantity = 0;
        // 生成验证码
        int validCode = (int) ((Math.random() * 9 + 1) * 1000);
        // 判断缓存中的验证码是否已经存在和过期
        CacheEntity cacheEntity = CacheTimerHandler.getCache(phoneNumber);
        if (cacheEntity != null) {
            validCode = (int) cacheEntity.getCacheContext();
            long interval = cacheEntity.getTimeoutStamp()
                    - System.currentTimeMillis();
            // 一分钟内不会重新发送验证码，超过一分钟才生成新的验证码，也不会重复发送验证码
            if (interval >= (cacheEntity.getValidityTime() - 60) * 1000) {
                result.setError(ResultCode.CODE_STATE_4006, "你的验证码仍在一分钟有效期内。");
                return result;
            }
        }

        // 校验已发送条数
        CacheEntity cEntity = CacheTimerHandler.getCache("sendQuantity_" + phoneNumber);
        if(cEntity != null){
        	sendQuantity = (int) cEntity.getCacheContext();
        	if(sendQuantity>10){
        		result.setError(ResultCode.CODE_STATE_4006, "您的手机短信验证码发送条数已超过限制条数， 同一个手机号码24小时之内只能发送10条验证码");
                return result;
        	}
        }
        sendQuantity = sendQuantity + 1;
        if(SystemBoolean.FALSE){//不向手机发送
            cacheEntity = new CacheEntity(phoneNumber, validCode);
            CacheTimerHandler.addCache(phoneNumber, cacheEntity);
            
            cEntity = new CacheEntity("sendQuantity_" + phoneNumber, sendQuantity, 86400);  //一天24小时等于86400秒
            CacheTimerHandler.addCache("sendQuantity_" + phoneNumber, cEntity, 86400);
            
            result.setOK(ResultCode.CODE_STATE_200, "验证码已成功发送");
        }else{//正式发送
        	Boolean send = RLYUtils.sendTemplateSMS(phoneNumber, String.valueOf(validCode));
        	if(send){
//        		System.out.println("=====================>>验证码已成功发生：" + validCode);
                cacheEntity = new CacheEntity(phoneNumber, validCode);
                CacheTimerHandler.addCache(phoneNumber, cacheEntity);
                
                cEntity = new CacheEntity("sendQuantity_" + phoneNumber, sendQuantity, 86400);  //一天24小时等于86400秒
                CacheTimerHandler.addCache("sendQuantity_" + phoneNumber, cEntity, 86400);            
                result.setOK(ResultCode.CODE_STATE_200, "验证码已成功发送");
        	}else if(send == null){
        		 result.setError(ResultCode.CODE_STATE_4006, "手机号码格式错误");
        	}else{
        		 result.setError(ResultCode.CODE_STATE_4006, "手机号码格式错误或该号码验证码发送已达上限，如继续请更换手机号");
        	}
        	 
        }
        return result;
    }
}
