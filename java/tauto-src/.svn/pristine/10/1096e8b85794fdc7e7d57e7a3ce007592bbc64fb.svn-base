package main.com.weixinApp.service;

import main.com.car.dao.search.CarSupplierSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.stock.dao.po.StockStorage;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.search.StockStorageSearch;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemWarehouseSearch;

public interface EmployeeStockStorageService extends BaseService<StockStorage>{

	/**
	 * 获取入库单列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageList(StockStorageSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 二级编辑入库单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageEdit(StockStorageSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 二级入库单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageInfo(StockStorageSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 获取入库单里的车辆列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageCarList(StockCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 删除库存单里的车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageCarDelete(StockCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 编辑入库单里的车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageCarEdit(StockCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 组织仓库列表
	 * @param search
	 * @param result
	 * @param systemUsers
	 * @return
	 * @throws Exception
	 */
	Result organizationWarehouseList(SystemWarehouseSearch search, Result result, SystemUsers systemUsers)throws Exception;

	/**
	 * 供应商列表
	 * @param search
	 * @param result
	 * @param systemUsers
	 * @return
	 * @throws Exception
	 */
	Result supplierListList(CarSupplierSearch search, Result result, SystemUsers systemUsers)throws Exception;

	/**
	 * 删除入库单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageDelete(StockCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 确认已全部入库
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageOverSure(StockCarSearch search, Result result, SystemUsers users)throws Exception;
}
