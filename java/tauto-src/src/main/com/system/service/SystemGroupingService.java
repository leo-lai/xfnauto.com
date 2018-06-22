package main.com.system.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemGrouping;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemGroupingSearch;
import main.com.system.dao.search.SystemUserGroupingSearch;

public interface SystemGroupingService extends BaseService<SystemGrouping> {

	/**
	 * 获取所有分组的列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result systemGroupingList(SystemGroupingSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 编辑
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result systemGroupingEdit(SystemGroupingSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 编辑组内成员
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result systemUserGroupingEdit(SystemUserGroupingSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 删除
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result systemUserGroupingDalete(SystemUserGroupingSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 删除组
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result systemGroupingDalete(SystemGroupingSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 查看组内成员
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result systemUserGroupingList(SystemGroupingSearch search, Result result, SystemUsers users)throws Exception;

}
