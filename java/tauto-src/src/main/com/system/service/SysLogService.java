package main.com.system.service;

import main.com.system.dao.po.LogOperate;

/**
 * 日志服务的接口
 * 
 *
 */
public interface SysLogService {

	/**
	 * 添加操作日志
	 * 
	 * @param logSystem
	 * @throws Exception
	 */
	public void addOperateLog(LogOperate logOperate) throws Exception;


}
