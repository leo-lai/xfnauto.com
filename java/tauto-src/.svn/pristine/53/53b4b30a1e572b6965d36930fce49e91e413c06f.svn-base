package main.com.customer.service;

import java.util.Map;

import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;

public interface CustomerOrderService extends BaseService<CustomerOrder>{

	/**
	 * 创建新订单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result editCustomerOrder(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 支付
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result payInOrder(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 查看订单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrderInfo(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 银行审批通过
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result bankApprovalPass(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 交付车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result turnOverVehicle(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 把分期修改为全款
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result changeFullPayment(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 银行查看订单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result bankExamineOders(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 首页客户管理
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getMapCustomerOrder(SystemUsers users)throws Exception;

	/**
	 * 订单支付记录
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result orderPayList(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 订单各项费用
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result orderPriceList(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 财务确认订单完结
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result endOrder(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 打印接口
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrderPrint(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 取消订单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result cancelOrder(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 订单列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrderList(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;
}
