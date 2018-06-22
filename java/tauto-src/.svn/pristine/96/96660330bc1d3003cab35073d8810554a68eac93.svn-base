package main.com.logistics.dao.dao.impl;

import org.springframework.stereotype.Repository;

import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.logistics.dao.dao.LogisticsDynamicLineInfoDao;
import main.com.logistics.dao.po.LogisticsDynamicLineInfo;

@Repository
public class LogisticsDynamicLineInfoDaoImpl extends BaseDaoImpl<LogisticsDynamicLineInfo> implements LogisticsDynamicLineInfoDao{

	@Override
	public Boolean deleteByParentId(Integer dynamicLineId) throws Exception {
		try {
			int rows = sqlSessionTemplate.delete(
					getSqlName("deleteByParentId"), dynamicLineId);
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID删除对象出错！语句：%s",
					getSqlName("deleteByParentId")), e);
		}
	}

}
