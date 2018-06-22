package main.com.weixinApp.service;

import main.com.customer.dao.po.CustomerUsers;
import main.com.customer.dao.search.CustomerCustomerOrgSearch;
import main.com.customer.dao.search.CustomerUsersSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;

public interface EmployeeCustomerService extends BaseService<CustomerUsers>{

	/**
	 * 获取自身或者下级门店列表
	 * @param search
	 * @param result
	 * @param systemUsers
	 * @return
	 * @throws Exception
	 */
	Result organizationLevelList(OrganizationSearch search, Result result, SystemUsers systemUsers)throws Exception;

	/**
	 * 
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrgList(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;
	
	/**
	 * 全部客户
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerUserList(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 落定客户/贷款通过客户/待完款客户/待加装上牌客户/待提车客户
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 */
	Result orderStateCustomerList(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 利用电话号码搜索用户电话列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result phoneCustomerSearchList(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 添加新客户
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result addCustomerUsersr(CustomerUsersSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 获取客户详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerUsersrInfo(CustomerUsersSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 添加备注
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result addCustomerRemarks(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 添加银行审核资料
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result addBankAudits(CustomerUsersSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 分配销售顾问
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result systenUserChangeCustomerOrg(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 开单前置
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result createOrderBefor(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 修改用户个人信息
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result changeUserInfo(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 我的订单列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result myCustomerOrderList(CustomerCustomerOrgSearch search, Result result, SystemUsers users)throws Exception;

}
