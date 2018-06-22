package main.com.system.dao.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.system.dao.dao.SystemUserGroupingDao;
import main.com.system.dao.po.SystemUserGrouping;
import main.com.system.dao.vo.SystemUserGroupingVo;

@Repository
public class SystemUserGroupingDaoImpl extends BaseDaoImpl<SystemUserGrouping> implements SystemUserGroupingDao{

	@Override
	public SystemUserGroupingVo selectByBothId(Map<String, Object> params) throws Exception {
		try {
			return sqlSessionTemplate.selectOne(
					getSqlName("selectByBothId"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s",
					getSqlName("selectByBothId")), e);
		}
	}

}
