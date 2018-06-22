package main.com.weixinApp.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.logistics.dao.po.LogisticsConsignment;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.system.dao.po.SystemUsers;

public interface EmployeeConsignmentService extends BaseService<LogisticsConsignment>{

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
	 * 托运单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result consignmentInfo(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

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
	 * 托运单发起支付
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result consignmentInPay(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 资费计算
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result expensesCount(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 配送单实时物流位置查看
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result logisticsDistributionGPS(LogisticsDistributionSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 托运单发起支付（POS）
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result consignmentInPayPOS(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

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
	 * 根据两点经纬度获取两点距离
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result getMileage(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 查看运费详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result getExpensesInfo(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 编辑接车人
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result extractTheCarPersons(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 编辑交车人
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result leaveTheCarPersons(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 根据托运单查看板车的实时位置
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 */
	Result logisticsConsignmentGPS(LogisticsConsignmentSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 电子合同
	 * @param search
	 * @param result
	 * @return
	 */
	Result consignmentContract(LogisticsConsignmentSearch search, Result result)throws Exception;

	/**
	 * 计算资费，商城接口重载
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public Result expensesCount(LogisticsConsignmentSearch search, Result result) throws Exception;
}
