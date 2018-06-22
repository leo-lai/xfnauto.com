package main.com.stock.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.stock.dao.po.StockStorage;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.search.StockStorageSearch;
import main.com.system.dao.po.SystemUsers;

public interface StockStorageService extends BaseService<StockStorage>{

	/**
	 * 获取入库记录列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 */
	Result storageList(StockStorageSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 编辑
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageEdit(StockStorageSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 查看详细信息
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageInfo(StockStorageSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 新建
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageInsert(StockStorageSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 根据入库单等获取库存车辆列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageCarList(StockCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 删除二级库存的车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageCarDelete(StockCarSearch search, Result result, SystemUsers users)throws Exception;
	
	/**
	 * 编辑二级库存的车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result storageCarEdit(StockCarSearch search, Result result, SystemUsers users)throws Exception;

}
