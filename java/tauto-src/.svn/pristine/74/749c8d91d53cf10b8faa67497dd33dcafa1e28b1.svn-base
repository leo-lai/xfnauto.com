package main.com.car.dao.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.com.car.dao.dao.OrgCarsConfigureDao;
import main.com.car.dao.po.OrgCarsConfigure;
import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;

@Repository
public class OrgCarsConfigureDaoImpl extends BaseDaoImpl<OrgCarsConfigure> implements OrgCarsConfigureDao{

	@Override
	public List<OrgCarsConfigure> selectMax(Map<String, Object> params) throws Exception {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectMax"),
					params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectMax")), e);
		}
	}

	@Override
	public Long selectCountIndex(Map<String, Object> params) throws Exception {
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectCountIndex"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(SqlId.SQL_SELECT_COUNT)), e);
		}
	}
	
	@Override
	public OrgCarsConfigure selectTopDiscount(Integer familyId) throws Exception {
		if(familyId == null || familyId <= 0) {
			return null;
		}
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectTopDiscount"), familyId);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName("selectTopDiscount")), e);
		}
	}

	@Override
	public List<OrgCarsConfigure> selectMaxList(Map<String, Object> params) throws Exception {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectMaxList"),
					params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectMax")), e);
		}
	}

}
