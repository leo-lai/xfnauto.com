package main.com.logistics.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.logistics.dao.po.LogisticsConsignment;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.system.dao.po.SystemUsers;

public interface LogisticsConsignmentService extends BaseService<LogisticsConsignment>{

	/**
	 * 托运单列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result consignmentList(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 托运单编辑
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result consignmentEdit(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 托运单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result consignmentInfo(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 查看待配送车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result consignmentCarList(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

}
