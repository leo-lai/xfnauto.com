package main.com.customer.dao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.com.customer.dao.dao.UsersTmplDao;
import main.com.customer.dao.po.UsersTmpl;
import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;

@Repository
public class UsersTmplDaoImpl extends BaseDaoImpl<UsersTmpl> implements UsersTmplDao{

	@Override
	public UsersTmpl selectBySessionId(String sessionId) throws Exception {
		try {
			return sqlSessionTemplate.selectOne(
					getSqlName("selectBySessionId"), sessionId);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s",
					getSqlName("selectBySessionId")), e);
		}
	}

	@Override
	public UsersTmpl selectByPhone(String phoneNumber) throws Exception {
		try {
			List<UsersTmpl> usersTmpls = sqlSessionTemplate.selectList(
					getSqlName("selectByPhone"), phoneNumber);
			if(usersTmpls != null && usersTmpls.size() > 0) {
				return usersTmpls.get(0);
			}else {
				return null;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s",
					getSqlName("selectBySessionId")), e);
		}
	}

}
