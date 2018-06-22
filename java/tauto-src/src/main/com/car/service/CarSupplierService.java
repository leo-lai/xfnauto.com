package main.com.car.service;

import main.com.car.dao.po.CarSupplier;
import main.com.car.dao.search.CarColourImageSearch;
import main.com.car.dao.search.CarSupplierSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;

public interface CarSupplierService extends BaseService<CarSupplier>{

	/**
	 * 供应商管理列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result supplierList(CarSupplierSearch search, Result result,SystemUsers systemUsers)throws Exception;

	/**
	 * 供应商列表下拉
	 * @param search
	 * @param result
	 * @return
	 */
	Result supplierListList(CarSupplierSearch search, Result result,SystemUsers systemUsers)throws Exception;

	/**
	 * 供应商编辑
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result supplierEdit(CarSupplierSearch search, Result result,SystemUsers systemUsers)throws Exception;

	/**
	 * 供应商删除
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result supplierDelete(CarSupplierSearch search, Result result)throws Exception;

}
