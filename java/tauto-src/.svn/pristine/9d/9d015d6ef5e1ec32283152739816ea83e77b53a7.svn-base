package main.com.car.dao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.com.car.dao.dao.CarBrowseRecordDao;
import main.com.car.dao.po.CarBrowseRecord;
import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;

@Repository
public class CarBrowseRecordDaoImpl extends BaseDaoImpl<CarBrowseRecord> implements CarBrowseRecordDao{

	@Override
	public List<CarBrowseRecord> findEnbinding(Integer usersTmplId) throws Exception {
		try {
			return sqlSessionTemplate.selectList(getSqlName("enbinding"),
					usersTmplId);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName(SqlId.SQL_SELECT)), e);
		}
	}

}
