package main.com.stock.dao.dao;

import java.util.List;
import java.util.Map;

import main.com.frame.dao.BaseDao;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.vo.StockCarVo;

public interface StockCarDao extends BaseDao<StockCar> {

	/**
	 * 根据门店查询库存分类
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<StockCarVo> selectByOrg(Map<String, Object> params)throws Exception;

	/**
	 * 根据门店查询
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Long selectCountByOrg(Map<String, Object> params)throws Exception;

	/**
	 * 根据ID查询，不论是否已出库
	 * @param stockCarId
	 * @return
	 * @throws Exception
	 */
	StockCarVo selectByIdAtAll(Integer stockCarId)throws Exception;
	
	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	StockCar getById(Integer id);

}
