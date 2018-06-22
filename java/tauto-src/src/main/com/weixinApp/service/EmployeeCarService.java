package main.com.weixinApp.service;

import main.com.car.dao.search.CarBrandSearch;
import main.com.car.dao.search.CarColourSearch;
import main.com.car.dao.search.CarFamilySearch;
import main.com.car.dao.search.CarInteriorSearch;
import main.com.car.dao.search.CarsSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.search.StockOrderSearch;
import main.com.system.dao.po.SystemUsers;

public interface EmployeeCarService extends BaseService<StockCar>{

	/**
	 * 员工端查看车辆库存
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockCarList(StockCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 获取品牌
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsBrandList(CarBrandSearch search, Result result)throws Exception;

	/**
	 * 获取具体车辆详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 */
	Result stockCarInfo(StockCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 获取车系下拉列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsFamilyList(CarFamilySearch search, Result result)throws Exception;

	/**
	 * 获取车大类下拉列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsListList(CarsSearch search, Result result)throws Exception;

	/**
	 * 三级向二级订车
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockOrderCreate(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 三级向二级订车单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockOrderInfo(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 三级取消订车单
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 */
	Result stockOrderCancel(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 签收并自动入库
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockOrderSign(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 内饰下拉
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carInteriorList(CarInteriorSearch search, Result result)throws Exception;

	/**
	 * 颜色下拉
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carColourList(CarColourSearch search, Result result)throws Exception;

	/**
	 * 获取订车单列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result stockOrderList(StockOrderSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 百度图片识别对接
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result imageRecognition(String url, Result result)throws Exception;
}
