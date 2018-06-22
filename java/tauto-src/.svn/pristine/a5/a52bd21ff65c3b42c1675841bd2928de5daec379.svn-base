package main.com.car.service;

import main.com.car.dao.po.CarColour;
import main.com.car.dao.search.CarColourSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;

public interface CarColourService extends BaseService<CarColour>{

	/**
	 * 根据品牌获取颜色
	 * @param search
	 * @param result
	 * @return
	 */
	Result carColourGetBrand(CarColourSearch search, Result result)throws Exception;

	/**
	 * 车身颜色添加
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carColourEdit(CarColourSearch search, Result result)throws Exception;

	/**
	 * 车身颜色删除
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carColourDelete(CarColourSearch search, Result result)throws Exception;

	/**
	 * 根据车型获取颜色
	 * @param search
	 * @param result
	 * @return
	 */
	Result carColourGetByCars(CarColourSearch search, Result result)throws Exception;

}
