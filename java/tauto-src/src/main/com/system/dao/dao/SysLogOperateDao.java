package main.com.system.dao.dao;

import main.com.system.dao.po.LogOperate;

/**
 * 操作类日志的DAO接口
 * 
 * 
 */
public interface SysLogOperateDao {

	/**
	 * 插入操作日志
	 * 
	 * @param logOperate
	 * @throws Exception
	 */
	public void addOperateLog(LogOperate logOperate) throws Exception;

	
}
