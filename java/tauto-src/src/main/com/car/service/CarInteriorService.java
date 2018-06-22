package main.com.car.service;

import main.com.car.dao.po.CarInterior;
import main.com.car.dao.search.CarInteriorSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;

public interface CarInteriorService extends BaseService<CarInterior>{

	/**
	 * 根据品牌获取内饰
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carInteriorGetByBrand(CarInteriorSearch search, Result result)throws Exception;

	/**
	 * 编辑
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carInteriorEdit(CarInteriorSearch search, Result result)throws Exception;

	/**
	 * 删除
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result carInteriorDelete(CarInteriorSearch search, Result result)throws Exception;

}
