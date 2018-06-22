package main.com.customer.service;

import main.com.customer.dao.po.CustomerCustomerOrg;
import main.com.customer.dao.search.CustomerCarSearch;
import main.com.customer.dao.search.CustomerCustomerOrgSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;

public interface CustomerCustomerOrgService extends BaseService<CustomerCustomerOrg>{

	/**
	 * 客户管理列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrgList(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 标记为追踪客户
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result trackCustomerOrg(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 标记客户为不购车客户
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result notBuyCustomerOrg(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 修改客户预约信息
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result bespeakChangeCustomerOrg(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 更改销售顾问
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 */
	Result systenUserChangeCustomerOrg(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 添加备注
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result addcustomerRemarks(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 修改用户信息
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result changeUserInfo(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 修改用户个人信息
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result changeUserCarInfo(CustomerCarSearch search, Result result, SystemUsers users)throws Exception;

}
