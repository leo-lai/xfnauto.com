package main.com.weixinApp.service;

import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;

public interface EmployeeOrderService extends BaseService<CustomerOrder>{

	/**
	 * 待出库车辆列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrderList(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 查看订单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrderInfo(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 根据订单查看库存
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrderStockCar(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 出库订单的车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrderStockCarPutout(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 待上牌列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result orderLicensePlateList(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 完成上牌
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result licensePlateDone(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 加装精品列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result carsProductsList(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 添加预计加装精品时间
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result addCarsProductsEstimateDate(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 精品列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result carsProductsInfo(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 加装精品完成
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result carsProductsDone(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 创建买车单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result editCustomerOrder(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 待贴膜车辆列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result carsPadPastingList(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 贴膜完成
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result carsPadPastingDone(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 交互车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result turnOverVehicle(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 全部订单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrderAllInfo(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 银行审核通过
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result bankApprovalPass(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 把分期改成全款
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result changeFullPayment(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 过线检查
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result overTheLine(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 订单详情（电子订单）
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result customerOrderPrint(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 取消订单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result cancelOrder(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 客户回访
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result orderVisit(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 根据商城预订购单开单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result editCustomerOrderShop(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception;
}
