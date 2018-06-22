package main.com.car.dao.dao.impl;

import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.po.Cars;
import main.com.frame.constants.SqlId;
import main.com.frame.dao.BaseDao;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.system.dao.dao.DictionaryDao;
import main.com.system.dao.po.Dictionary;

/**
 * 车型大类信息表
 * @author Zwen
 *
 */
@Repository
public class CarsDaoImpl extends BaseDaoImpl<Cars> implements CarsDao{

	@Override
	public Long selectCountIndex(Map<String, Object> params) throws Exception {
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectCountIndex"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(SqlId.SQL_SELECT_COUNT)), e);
		}
	}
	
}
