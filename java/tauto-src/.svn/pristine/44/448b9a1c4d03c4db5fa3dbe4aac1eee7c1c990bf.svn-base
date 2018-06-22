package main.com.stock.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.search.StockCarSearch;
import main.com.system.dao.po.SystemUsers;

public interface StockCarService extends BaseService<StockCar> {

	/**
	 * 库存车辆列表，可查看自身跟下级
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 */
	Result stockCarList(StockCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 库存的定金/优惠/是否线上展示修改
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockCarEdit(StockCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 查看明细
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockCarInfo(StockCarSearch search, Result result, SystemUsers users)throws Exception;

}
