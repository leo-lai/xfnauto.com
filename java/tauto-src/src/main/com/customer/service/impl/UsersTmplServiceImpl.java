package main.com.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.customer.dao.dao.UsersTmplDao;
import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.service.UsersTmplService;
import main.com.frame.constants.SqlId;
import main.com.frame.dao.BaseDao;
import main.com.frame.exception.DaoException;
import main.com.frame.service.impl.BaseServiceImpl;

@Service
public class UsersTmplServiceImpl extends BaseServiceImpl<UsersTmpl> implements UsersTmplService{

	@Autowired
	UsersTmplDao usersTmplDao;
	
	@Override
	protected BaseDao<UsersTmpl> getBaseDao() {
		return usersTmplDao;
	}

	@Override
	public UsersTmpl getUserBySessionId(String sessionId) throws Exception {		
		return usersTmplDao.selectBySessionId(sessionId);		
	}

}
