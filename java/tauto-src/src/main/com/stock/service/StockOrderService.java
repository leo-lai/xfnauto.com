package main.com.stock.service;

import java.util.Map;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.stock.dao.po.StockOrder;
import main.com.stock.dao.search.StockOrderSearch;
import main.com.system.dao.po.SystemUsers;

public interface StockOrderService extends BaseService<StockOrder> {

	/**
	 * 获取库存订单列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockOrderList(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 查看订单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 */
	Result stockOrderInfo(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 创建新订单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockOrderCreate(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 订单支付
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockOrderPay(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 订单取消
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockOrderCancel(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 添加并自动入库
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockOrderSign(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 修改订单尾款
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 */
	Result stockOrderchangebalancePrice(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 通知有车
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 */
	Result stockOrderNotice(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 出库车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockOrderStorageOut(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 获取首页订车数据
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getMapNumber(SystemUsers users)throws Exception;

	/**
	 * 通知有车前置
	 * @param search
	 * @param result
	 * @return
	 */
	Result stockOrderNoticeBefor(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 出库车辆前置
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockOrderStorageOutBefor(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 获取车辆定金
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result carDepositPrice(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 获取客户订车列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 */
	Result customerOrderList(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 出库客户订车单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrderStorageOut(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 出库客户车辆前置
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrderStorageOutBefor(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

}
