package main.com.weixinHtml.service;

import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.weixinHtml.dao.po.ShopAdvanceOrder;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.search.ShopAdvanceOrderSearch;
import main.com.weixinHtml.dao.search.ShopGoodsCarsActivitySearch;

public interface ShopAdvanceOrderService extends BaseService<ShopAdvanceOrder>{

	/**
	 * 预订单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result advanceOrderEdit(ShopAdvanceOrderSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 商务端 预约列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result orgAdvanceOrderList(ShopAdvanceOrderSearch search, Result result, SystemUsers users)throws Exception;
	/**
	 * 商务端 预约列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result orgAdvanceOrderList(ShopAdvanceOrderSearch search, Result result, SystemUsersVo users,Boolean isbreak)throws Exception;

	/**
	 * 删除 取消
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result orgAdvanceOrderDel(ShopAdvanceOrderSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 详情
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result orgAdvanceOrderInfo(ShopAdvanceOrderSearch search, Result result)throws Exception;

	/**
	 * 支付定金
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result orgAdvanceOrderInpay(ShopAdvanceOrderSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 我的预购单列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result myOrgAdvanceOrderList(ShopAdvanceOrderSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 我的订单列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result myOrderList(ShopAdvanceOrderSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 实际订单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result myOrderInfo(ShopAdvanceOrderSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 我的订单支付纪录
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result myOrderPaymentList(CustomerOrderSearch search, Result result, ShopUsers users)throws Exception;

}
