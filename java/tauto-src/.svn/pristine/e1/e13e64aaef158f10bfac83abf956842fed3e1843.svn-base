package main.com.logistics.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.logistics.dao.po.LogisticsDistribution;
import main.com.logistics.dao.search.CarInDistributionSearch;
import main.com.logistics.dao.search.DriverDistributionSearch;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.system.dao.po.SystemUsers;

public interface LogisticsDistributionService extends BaseService<LogisticsDistribution> {

	/**
	 * 配送单列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result distributionList(LogisticsDistributionSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 配送单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result distributionInfo(LogisticsDistributionSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 托运单编辑
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result distributionEdit(LogisticsDistributionSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 查看已分配的车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result distributionGoodsCarList(LogisticsDistributionSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 删除已分配的车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result distributionGoodsCarDelete(LogisticsDistributionSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 分配车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result distributionGoodsCarAdd(LogisticsDistributionSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 根据司机ID查询配送单
	 * @param search
	 * @return
	 */
	Result queryDriverDistributions(DriverDistributionSearch search) throws Exception;

	/**
	 * 根据配送单号查询车辆
	 * @param search
	 * @return
	 * @throws Exception
	 */
	Result queryCarInDistribution(CarInDistributionSearch search) throws Exception;
}
