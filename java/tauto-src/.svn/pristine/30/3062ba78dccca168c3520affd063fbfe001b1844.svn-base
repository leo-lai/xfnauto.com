package main.com.logistics.dao.dao;

import java.util.List;
import java.util.Map;

import main.com.frame.dao.BaseDao;
import main.com.logistics.dao.po.LogisticsConsignment;
import main.com.logistics.dao.po.LogisticsDistribution;
import main.com.logistics.dao.po.LogisticsGoodsCar;
import main.com.logistics.dao.vo.LogisticsDistributionVo;

public interface LogisticsDistributionDao extends BaseDao<LogisticsDistribution>{

	/**
	 * 获取CODE
	 */
	public String getCode()throws Exception;

	/**
	 * 查看已分配的车辆
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<LogisticsDistributionVo> selectJoinGoods(Map<String, Object> params)throws Exception;
	
	/**
	 * 根据单号查询车辆
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<LogisticsGoodsCar> queryCarInDistribution(Map<String,Object> map);
	
	/**
	 * 获取物流单列表
	 * @param map
	 * @return
	 */
	List<LogisticsDistribution> list(Map<String, Object> map);

	/**
	 * 配送单所有关联获取
	 * @param distributionId
	 * @return
	 * @throws Exception
	 */
	public LogisticsDistributionVo selectByIdJoinAll(Integer distributionId)throws Exception;

}
