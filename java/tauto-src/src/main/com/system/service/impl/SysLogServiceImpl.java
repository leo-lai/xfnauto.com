package main.com.system.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.system.dao.dao.SysLogOperateDao;
import main.com.system.dao.po.LogOperate;
import main.com.system.service.SysLogService;

/**
 * 框架日之服务接口的实现类
 * 
 * 
 */
@Service
public class SysLogServiceImpl implements SysLogService {

	private static final Logger logger = Logger.getLogger(SysLogServiceImpl.class);

	@Autowired
	private SysLogOperateDao logOperateDao;

	@Override
	public void addOperateLog(LogOperate logOperate) throws Exception {
		this.logOperateDao.addOperateLog(logOperate);
	}


}
