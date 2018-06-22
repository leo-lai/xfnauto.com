package main.com.logistics.dao.dao.impl;

import org.springframework.stereotype.Repository;

import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.logistics.dao.dao.LogisticsGoodsCarCostsDao;
import main.com.logistics.dao.po.LogisticsGoodsCarCosts;
import main.com.logistics.dao.vo.LogisticsGoodsCarCostsVo;

@Repository
public class LogisticsGoodsCarCostsDaoImpl extends BaseDaoImpl<LogisticsGoodsCarCosts> implements LogisticsGoodsCarCostsDao{

	@Override
	public LogisticsGoodsCarCostsVo selectByGoodsCarId(Integer goodsCarId) throws Exception {		
		try {
			if(goodsCarId == null || goodsCarId <= 0) {
				return null;
			}
			return sqlSessionTemplate.selectOne(
					getSqlName("selectByGoodsCarId"), goodsCarId);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s",
					getSqlName("selectByGoodsCarId")), e);
		}
	}

}
