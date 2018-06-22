package main.com.logistics.dao.dao.impl;

import org.springframework.stereotype.Repository;

import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.logistics.dao.dao.LogisticsCarDao;
import main.com.logistics.dao.po.LogisticsCar;
import main.com.logistics.dao.vo.LogisticsCarVo;

@Repository
public class LogisticsCarDaoImpl extends BaseDaoImpl<LogisticsCar> implements LogisticsCarDao{

	@Override
	public LogisticsCarVo selectByGpsPin(String gpsPin) throws Exception {
		try {
			return sqlSessionTemplate.selectOne(
					getSqlName("selectByGpsPin"), gpsPin);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s",
					getSqlName(SqlId.SQL_SELECT_BY_ID)), e);
		}
	}

}
