package main.com.customer.service;

import main.com.customer.dao.po.CustomerUsers;
import main.com.customer.dao.search.CustomerUsersSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemUsersSearch;

public interface CustomerUsersService extends BaseService<CustomerUsers>{

	/**
	 * 添加新客户
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result addCustomerUsers(CustomerUsersSearch search, Result result,SystemUsers users)throws Exception;

	/**
	 * 添加新客户信息
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerUsersrInfo(CustomerUsersSearch search, Result result, SystemUsers users)throws Exception;
}
