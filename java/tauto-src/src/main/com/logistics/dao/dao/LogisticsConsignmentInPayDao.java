package main.com.logistics.dao.dao;

import main.com.frame.dao.BaseDao;
import main.com.logistics.dao.po.LogisticsConsignmentInPay;

public interface LogisticsConsignmentInPayDao extends BaseDao<LogisticsConsignmentInPay>{

	/**
	 * 获取code
	 * @return
	 * @throws Exception
	 */
	String getCode()throws Exception;

}
