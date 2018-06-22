package main.com.logistics.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.logistics.dao.po.LogisticsCar;
import main.com.logistics.dao.search.LogisticsCarSearch;
import main.com.system.dao.po.SystemUsers;

public interface LogisticsCarService extends BaseService<LogisticsCar> {

	/**
	 * 板车管理列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result logisticsCarList(LogisticsCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 板车列表下拉
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result logisticsCarListList(LogisticsCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 板车编辑
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result logisticsCarEdit(LogisticsCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 板车启用禁用
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result logisticsCarIsEnable(LogisticsCarSearch search, Result result, SystemUsers users)throws Exception;

}
