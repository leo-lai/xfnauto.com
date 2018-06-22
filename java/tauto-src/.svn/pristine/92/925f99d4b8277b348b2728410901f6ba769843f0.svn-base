/**   
 * @Title: SysLogOperateDaoImpl.java 
 * @Description: 操作日志
 * @date 2014年11月17日 下午4:31:09 
 * @version V1.0   
 */
package main.com.system.dao.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.com.system.dao.dao.SysLogOperateDao;
import main.com.system.dao.po.LogOperate;


/**
 * 
 *
 */
@Repository
public class SysLogOperateDaoImpl implements SysLogOperateDao {
	@Autowired(required = true)
	protected SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void addOperateLog(LogOperate logOperate) throws Exception {
		sqlSessionTemplate.insert("main.com.system.dao.po.LogOperate.addOperateLog", logOperate);
//		main.com.system.dao.po.SystemUsers.selectByCode
//		main.com.system.dao.po.SystemUsers.select
//		main.com.system.dao.po.SystemUsers.insert
	}

}
