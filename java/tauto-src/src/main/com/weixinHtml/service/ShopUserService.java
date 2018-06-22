package main.com.weixinHtml.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.search.ShopUserSearch;

public interface ShopUserService extends BaseService<ShopUsers>{

	/**
	 * 密码登录
	 * @param search
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Result loginPassword(ShopUserSearch search, String id)throws Exception;

	/**
	 * 刷新用户信息
	 * @param search
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Result refreshCode(ShopUserSearch search, ShopUsers user)throws Exception;

	/**
	 * 验证码登录
	 * @param search
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Result loginCodeOfShop(ShopUserSearch search, String id)throws Exception;

	/**
	 * 静默登录
	 * @param search
	 * @param id
	 * @return
	 */
	Result silenceLogin(ShopUserSearch search, String id)throws Exception;

	/**
	 * 开启或者关闭推送
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result notify(ShopUserSearch search, Result result)throws Exception;

	/**
	 * 登出
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public Result loginOut(ShopUserSearch search) throws Exception;

	/**
	 * 注册
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result shopRegister(ShopUserSearch search, Result result)throws Exception;

	/**
	 * 忘记密码
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result shopforgetPassWord(ShopUserSearch search, Result result)throws Exception;

	/**
	 * 补充商户用户的门店申请信息
	 * @param search
	 * @param result
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Result supplementOrg(ShopUserSearch search, Result result, ShopUsers user)throws Exception;

	/**
	 * 商城B端用户列表
	 * @param search
	 * @param result
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Result shopOrgList(OrganizationSearch search, Result result, SystemUsers user)throws Exception;

	/**
	 * 商城B端用户审核
	 * @param search
	 * @param result
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Result shopOrgAudit(ShopUserSearch search, Result result, SystemUsers user)throws Exception;

	/**
	 * 配置通联支付信息
	 * @param search
	 * @param result
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Result allInPayConfigure(ShopUserSearch search, Result result, SystemUsers user)throws Exception;

	/**
	 * B端用户门店信息
	 * @param search
	 * @param result
	 * @param user
	 * @return
	 * @throws Exception
	 */
	Result myOrgInfo(ShopUserSearch search, Result result, ShopUsers user)throws Exception;
}
