package main.com.system.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;
import main.com.system.dao.search.SystemWarehouseSearch;

public interface OrganizationService extends BaseService<Organization> {

	/**
	 * 查看组织架构列表
	 * @param search
	 * @param result
	 * @throws Exception
	 */
	Result organizationList(OrganizationSearch search, Result result,SystemUsers systemUsers)throws Exception;

	/**
	 * 添加或修改
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result organizationEdit(OrganizationSearch search, Result result)throws Exception;

	/**
	 * 开启关闭
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result organizationOnOff(OrganizationSearch search, Result result)throws Exception;

	/**
	 * 自身和下级的下拉列表
	 * @param search
	 * @param result
	 * @param systemUsers
	 * @return
	 */
	Result organizationLevelList(OrganizationSearch search, Result result, SystemUsers systemUsers)throws Exception;

	/**
	 * 组织仓库列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result organizationWarehouseList(SystemWarehouseSearch search, Result result, SystemUsers systemUsers)throws Exception;

	/**
	 * 组织仓库修改
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result organizationWarehouseEdit(SystemWarehouseSearch search, Result result)throws Exception;

	/**
	 * 组织仓库删除
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result organizationWarehouseDalete(SystemWarehouseSearch search, Result result)throws Exception;

	/**
	 * 组织详情
	 * @param search
	 * @param result
	 * @param systemUsers
	 * @return
	 * @throws Exception
	 */
	Result organizationInfo(OrganizationSearch search, Result result, SystemUsers systemUsers)throws Exception;
	
	public Result organizationInfo(String orgCode, Result result) throws Exception;

	/**
	 * 根据组织级别获取相应的上级
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result organizationLevelListByLevel(OrganizationSearch search, Result result)throws Exception;
	
	/**
	 * 获取组织
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Organization getOrganization(SystemUsers users)throws Exception;
}
