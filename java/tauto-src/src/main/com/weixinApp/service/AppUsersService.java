package main.com.weixinApp.service;

import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.dao.search.UsersTmplSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;

public interface AppUsersService extends BaseService<UsersTmpl> {

	/**
	 * 登录
	 * @param search
	 * @param result
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Result decodeUserInfo(UsersTmplSearch search, Result result, String id)throws Exception;

	/**
	 * 刷新我的个人信息
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result refreshMyInfo(UsersTmplSearch search, Result result)throws Exception;

	/**
	 * 预约前置
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result bespeakBefor(UsersTmplSearch search, Result result)throws Exception;

	/**
	 * 预约
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result bespeak(UsersTmplSearch search, Result result,UsersTmpl users)throws Exception;

	/**
	 * 输入电话号绑定客户
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result phonebinding(UsersTmplSearch search, Result result, UsersTmpl users)throws Exception;

}
