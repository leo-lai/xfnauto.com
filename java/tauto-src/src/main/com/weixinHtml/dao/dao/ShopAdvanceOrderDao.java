package main.com.weixinHtml.dao.dao;

import main.com.frame.dao.BaseDao;
import main.com.weixinHtml.dao.po.ShopAdvanceOrder;

public interface ShopAdvanceOrderDao extends BaseDao<ShopAdvanceOrder>{

	/**
	 * 获取订单号
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	String getCode(String phoneNumber)throws Exception;

}
