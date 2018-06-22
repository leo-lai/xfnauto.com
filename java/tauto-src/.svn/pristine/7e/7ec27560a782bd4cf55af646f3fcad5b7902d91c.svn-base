package main.com.car.service;

import main.com.car.dao.po.Cars;
import main.com.car.dao.search.CarBrandSearch;
import main.com.car.dao.search.CarExpectWaySearch;
import main.com.car.dao.search.CarFamilySearch;
import main.com.car.dao.search.CarsSearch;
import main.com.car.dao.search.CarsStyleSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;

public interface CarsService extends BaseService<Cars>{

	/**
	 * 车辆类型列表
	 * @param menuSearch
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsList(CarsSearch menuSearch, Result result)throws Exception;

	/**
	 * 车辆类型添加
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsEdit(CarsSearch search, Result result)throws Exception;

	/**
	 * 添加品牌
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsBrandEdit(CarBrandSearch search, Result result)throws Exception;

	/**
	 * 添加车系
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsFamilyEdit(CarFamilySearch search, Result result)throws Exception;

	/**
	 * 添加车辆级别
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsStyleEdit(CarsStyleSearch search, Result result)throws Exception;

	Result carsDelete(CarsSearch search, Result result)throws Exception;

	/**
	 * 品牌列表
	 * @param search
	 * @param result
	 * @return
	 */
	Result carsBrandList(CarBrandSearch search, Result result)throws Exception;

	/**
	 * 车系列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsFamilyList(CarFamilySearch search, Result result)throws Exception;

	/**
	 * 车大类下来列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsListList(CarsSearch search, Result result)throws Exception;

	/**
	 * 购车方式下拉列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carExpectWayList(CarExpectWaySearch search, Result result)throws Exception;

	/**
	 * 品牌分页列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsBrandPageList(CarBrandSearch search, Result result)throws Exception;

	/**
	 * 获取车型（高中低配下拉列表）
	 * @param search
	 * @param result
	 * @return
	 */
	Result carsStyleList(CarsStyleSearch search, Result result)throws Exception;

	/**
	 * 获取车系列表，带分页
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsFamilyListPage(CarFamilySearch search, Result result)throws Exception;

	/**
	 * 车大类INFO
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsInfo(CarsSearch search, Result result)throws Exception;

}
