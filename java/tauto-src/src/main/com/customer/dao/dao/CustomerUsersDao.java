package main.com.customer.dao.dao;

import main.com.customer.dao.po.CustomerUsers;
import main.com.frame.dao.BaseDao;

public interface CustomerUsersDao extends BaseDao<CustomerUsers>{

	/**
	 * 利用电话号码查询用户
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	CustomerUsers selectByPhone(String phoneNumber)throws Exception;

	String getCode()throws Exception;

}
