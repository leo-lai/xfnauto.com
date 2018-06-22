package main.com.weixinApp.service;

import main.com.car.dao.po.Cars;
import main.com.car.dao.search.CarBrandSearch;
import main.com.car.dao.search.CarExpectWaySearch;
import main.com.car.dao.search.CarFamilySearch;
import main.com.car.dao.search.CarsSearch;
import main.com.car.dao.search.CarsStyleSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;

public interface AppCarsService extends BaseService<Cars>{

	/**
	 * 车辆类型列表
	 * @param menuSearch
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsList(CarsSearch menuSearch, Result result)throws Exception;
	
	/**
	 * 车大类INFO
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsInfo(CarsSearch search, Result result)throws Exception;

	/**
	 * 品牌列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsBrandList(CarBrandSearch search, Result result)throws Exception;

	/**
	 * 现有年款列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsYearPatternList(CarsSearch search, Result result)throws Exception;

	/**
	 * 根据车系获取车大类列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carsFamilyList(CarsSearch search, Result result)throws Exception;

	/**
	 * 获取问题列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carProblemList(CarsSearch search, Result result)throws Exception;

	/**
	 * 获取车辆富文本介绍
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carIntroduce(CarsSearch search, Result result)throws Exception;

	/**
	 * 获取参数配置
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carParameter(CarsSearch search, Result result)throws Exception;

	/**
	 * 获取购车全款
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result fullPayment(CarsSearch search, Result result)throws Exception;

	/**
	 * 获取购车贷款
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result loanPayment(CarsSearch search, Result result)throws Exception;

}
