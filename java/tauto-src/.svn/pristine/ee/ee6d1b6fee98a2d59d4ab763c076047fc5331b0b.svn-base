package main.com.stock.dao.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.vo.StockCarVo;

@Repository
public class StockCarDaoImpl extends BaseDaoImpl<StockCar> implements StockCarDao {

	@Override
	public List<StockCarVo> selectByOrg(Map<String, Object> params) throws Exception {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectByOrg"),
					params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectByOrg")), e);
		}
	}

	@Override
	public Long selectCountByOrg(Map<String, Object> params) throws Exception {
		try {
		return sqlSessionTemplate.selectOne(getSqlName("selectCountByOrg"), params);
	} catch (Exception e) {
		throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName("selectCountByOrg")), e);}	
	}

	@Override
	public StockCarVo selectByIdAtAll(Integer stockCarId) throws Exception {
		try {
		return sqlSessionTemplate.selectOne(getSqlName("selectByIdAtAll"), stockCarId);
	} catch (Exception e) {
		throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName("selectByIdAtAll")), e);}	
	}
	
	@Override
	public StockCar getById(Integer id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(getSqlName("getById"), id);
	}
}
