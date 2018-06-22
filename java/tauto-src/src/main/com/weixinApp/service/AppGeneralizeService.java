package main.com.weixinApp.service;

import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.dao.search.UsersTmplSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;

public interface AppGeneralizeService extends BaseService<Organization>{

	/**
	 * 门店列表
	 * @param search
	 * @param result
	 * @param systemUsers
	 * @return
	 * @throws Exception
	 */
	Result organizationList(OrganizationSearch search, Result result)throws Exception;

	/**
	 * 预约
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result bespeak(UsersTmplSearch search, Result result, UsersTmpl users)throws Exception;

	/**
	 * 推广信息
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result generalizeInfo(UsersTmplSearch search, Result result, UsersTmpl users)throws Exception;

}
