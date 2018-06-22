package main.com.weixinApp.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;

public interface EmployeeOrganizationService extends BaseService<Organization>{

	/**
	 * 三级门店列表管理
	 * @param search
	 * @param result
	 * @param systemUsers
	 * @return
	 * @throws Exception
	 */
	Result organizationList(OrganizationSearch search, Result result, SystemUsers systemUsers)throws Exception;

	/**
	 * 组织架构编辑
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result organizationEdit(OrganizationSearch search, Result result,SystemUsers systemUsers)throws Exception;

}
