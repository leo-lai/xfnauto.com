package main.com.weixinApp.service;

import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;

public interface AppOderService  extends BaseService<CustomerOrder>{

	/**
	 * 订单详情
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result orderInfo(CustomerOrderSearch search, Result result)throws Exception;

	/**
	 * 订单跟踪
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result orderTrack(CustomerOrderSearch search, Result result)throws Exception;

	/**
	 * 电子合同
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 */
	Result customerOrderPrint(CustomerOrderSearch search, Result result, UsersTmpl users);

}
