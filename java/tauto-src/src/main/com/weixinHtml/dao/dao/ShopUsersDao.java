package main.com.weixinHtml.dao.dao;

import main.com.frame.dao.BaseDao;
import main.com.weixinHtml.dao.po.ShopUsers;

public interface ShopUsersDao extends BaseDao<ShopUsers>{

	/**
	 * 静默登录
	 * @param openId
	 * @param b
	 * @return
	 * @throws Exception
	 */
	ShopUsers loginOfOpenId(String openId, boolean b)throws Exception;

	/**
	 * 生成新的UserCode
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public String getUserCode()throws Exception;
}
