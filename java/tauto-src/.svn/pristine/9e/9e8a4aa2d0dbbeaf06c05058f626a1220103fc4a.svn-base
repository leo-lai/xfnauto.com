package main.com.weixinApp.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.logistics.dao.po.LogisticsDistribution;
import main.com.logistics.dao.search.CarInDistributionSearch;
import main.com.logistics.dao.search.DriverDistributionSearch;
import main.com.logistics.dao.search.LogisticsCarSearch;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.dao.vo.LogisticsDriverVo;
import main.com.system.dao.po.SystemUsers;

public interface EmployeeDistributionService extends BaseService<LogisticsDistribution> {

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
	 * 查看配送单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result distributionGoodsCarList(LogisticsDistributionSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 上传装车卸车图片
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result distributionGoodsCarImage(LogisticsDistributionSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 由托运单发起支付
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result logisticsInPayPOS(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;
	
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
//	Result distributionGoodsCarList(LogisticsDistributionSearch search, Result result, SystemUsers users)throws Exception;

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
	 * 配送单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result distributionInfo(LogisticsDistributionSearch search, Result result, SystemUsers users)throws Exception;
	/**
	 * 配送单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result distributionInfo(LogisticsDistributionSearch search, Result result)throws Exception;

	/**
	 * 派单（确认配送单，不能再进行更改）
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result distributionDelivery(LogisticsDistributionSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 接单（司机端）
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result distributionOrderTaking(LogisticsDistributionSearch search, Result result, LogisticsDriverVo users)throws Exception;

}
