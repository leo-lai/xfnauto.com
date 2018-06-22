package main.com.system.service;

import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;

public interface BankToExamineService extends BaseService<CustomerOrder>{

	/**
	 * 银行审核列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrderList(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 银行审核
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result bankToExamineOrder(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

}
