package main.com.customer.service;

import main.com.customer.dao.po.UsersTmpl;
import main.com.frame.service.BaseService;

public interface UsersTmplService extends BaseService<UsersTmpl> {

	/**
	 * 根据sessionId查询用户
	 * @param sessionId
	 * @return
	 */
	UsersTmpl getUserBySessionId(String sessionId)throws Exception;

}
