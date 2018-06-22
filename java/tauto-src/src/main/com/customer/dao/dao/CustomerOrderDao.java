package main.com.customer.dao.dao;

import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.vo.CustomerOrderVo;
import main.com.frame.dao.BaseDao;

public interface CustomerOrderDao extends BaseDao<CustomerOrder>{

	/**
	 * 获取CODE
	 * @return
	 * @throws Exception
	 */
	public String getCode(String phone)throws Exception;

	/**
	 * 获取
	 * @param customerUsersId
	 * @return
	 * @throws Exception
	 */
	public CustomerOrder selectByUsersId(Integer customerUsersId)throws Exception;

	/**
	 * 查询订单的所有支付记录
	 * @param customerOrderId
	 * @return
	 * @throws Exception
	 */
	public CustomerOrderVo selectByIdJoinPayment(Integer customerOrderId)throws Exception;

}
