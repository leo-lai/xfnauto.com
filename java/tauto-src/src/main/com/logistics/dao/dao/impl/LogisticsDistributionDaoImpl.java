package main.com.logistics.dao.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.logistics.dao.dao.LogisticsDistributionDao;
import main.com.logistics.dao.po.LogisticsDistribution;
import main.com.logistics.dao.po.LogisticsGoodsCar;
import main.com.logistics.dao.vo.LogisticsDistributionVo;
import main.com.utils.StringCode;

@Repository
public class LogisticsDistributionDaoImpl extends BaseDaoImpl<LogisticsDistribution> implements LogisticsDistributionDao{

	@Override
	public String getCode() throws Exception {
		String distributionCode = StringCode.getOtherCodes(4, "PS");
		try {
			List<LogisticsDistribution> list = sqlSessionTemplate.selectList(getSqlName("selectByCode"),
					distributionCode);
			if(list != null && list.size() > 0) {
				return getCode(); 
			}else {
				return distributionCode;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectJoinAllList")), e);
		}
	}

	@Override
	public List<LogisticsDistributionVo> selectJoinGoods(Map<String, Object> params) throws Exception {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectJoinGoods"),
					params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectJoinGoods")), e);
		}
	}
	
	@Override
	public List<LogisticsGoodsCar> queryCarInDistribution(Map<String,Object> map){
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getSqlName("queryCarInDistribution"),map);
	}
	
	@Override
	public List<LogisticsDistribution> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getSqlName("list"),map);
	}

	@Override
	public LogisticsDistributionVo selectByIdJoinAll(Integer distributionId) throws Exception {
		try {
			if(distributionId == null || distributionId <= 0) {
				return null;
			}
			return sqlSessionTemplate.selectOne(
					getSqlName("selectByIdJoinAll"), distributionId);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s",
					getSqlName("selectByIdJoinAll")), e);
		}
	}
}
