package main.com.weixinApp.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.logistics.dao.po.LogisticsDriver;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.dao.search.LogisticsDriverSearch;
import main.com.system.dao.po.SystemUsers;

public interface EmployeeDriverService extends BaseService<LogisticsDriver> {

	/**
	 * 司机列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result driverList(LogisticsDriverSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 司机资料编辑
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result driverEdit(LogisticsDriverSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 司机的详细信息
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result driverInfo(LogisticsDriverSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 获取司机下拉列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result driverListList(LogisticsDriverSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 禁用司机
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result driverDisable(LogisticsDriverSearch search, Result result, SystemUsers users)throws Exception;

}
