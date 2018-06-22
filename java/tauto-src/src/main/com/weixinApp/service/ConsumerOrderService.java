package main.com.weixinApp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.frame.domain.Result;
import main.com.frame.search.BaseSearch;
import main.com.frame.service.BaseService;
import main.com.stock.dao.po.ConsumerOrder;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinApp.dao.search.ConsumerOrderSearch;
import main.com.weixinApp.dao.search.ConsumerOrderUpdateLogisticsInfoSearch;
import main.com.weixinApp.dao.search.CreateConsumerOrderSearch;
import main.com.weixinApp.dao.search.ListOrderSearch;
import main.com.weixinApp.dao.search.OrderUpdateSearch;
import main.com.weixinApp.dao.search.OrderUpdateStateSearch;

public interface ConsumerOrderService extends BaseService<ConsumerOrder>{

	/**
	 * 创建订购单
	 * @param search
	 * @return
	 */
	Result createOrder(CreateConsumerOrderSearch search);
	
	/**
	 * 查询订单列表
	 * @param search
	 * @return
	 */
	Result listOrders(ListOrderSearch search,SystemUsers user);
	
	public Result listOrders(ListOrderSearch search,SystemUsers user,String isPC);
	
	/**
	 * 审核列表
	 * @return
	 */
	Result auditList(BaseSearch search);
	
	/**
	 * 根据ID获取订单详情
	 * @param id
	 * @return
	 */
	Result getOrderDetail(Integer id);
	
	/**
	 * 获取合同信息
	 * @param id
	 * @return
	 */
	Result getContractInfo(Integer id);
	
	/**
	 * 更新订单
	 * @param search
	 * @return
	 */
	Result update(OrderUpdateSearch search);
	
	/**
	 * 删除
	 * @return
	 */
	Result delete(Integer id);
	
	/**
	 * 更新订单状态
	 * @param search
	 * @return
	 */
	Result updateState(OrderUpdateStateSearch search) throws Exception;
	
	/**
	 * 生成订单编码
	 * @return
	 */
	String createOrderCode(String phone);
	
	/**
	 * 获取支付信息
	 * @param orderId
	 * @return
	 */
	Result getPaymentInfo(Integer orderId);
	
	/**
	 * 更新物流信息
	 * @param search
	 * @return
	 */
	Result updateLogisticsInfo(ConsumerOrderUpdateLogisticsInfoSearch search);

	Result cancel(Integer id)throws Exception;

	/**
	 * 退款
	 * @param search
	 * @return
	 * @throws Exception
	 */
	Result countermand(ConsumerOrderSearch search,Result result)throws Exception;
	
	/**
	 * 退款审核
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public Result countermandExamine(ConsumerOrderSearch search,Result result) throws Exception;

	/**
	 * 根据商城的预订单生成订购单
	 * @param search
	 * @return
	 * @throws Exception
	 */
	Result createOrderShop(CreateConsumerOrderSearch search,Result result)throws Exception;

	Result getContractInfoImage(Integer orderId, Result result, HttpServletRequest request,
			HttpServletResponse response)throws Exception;

	Result getContractInfoImageHtml(Integer orderId, Result result, HttpServletRequest request,
			HttpServletResponse response)throws Exception;
}
