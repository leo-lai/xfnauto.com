package main.com.logistics.dao.dao.impl;

import java.io.Serializable;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.domain.Result;
import main.com.logistics.dao.dao.LogisticsDriverDao;
import main.com.logistics.dao.po.LogisticsDriver;
import main.com.logistics.service.LogisticsDriverService;

/** 
* @author liaozijie 
* @version 创建时间：2018年1月15日 下午5:15:52 
* 类描述： 
*/
@Repository
public class LogisticsDriverDaoImpl extends BaseDaoImpl<LogisticsDriver> implements LogisticsDriverDao {

	
	@Override
	public int uploadPic(Map map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update(getSqlName("uploadPic"),map);
	}
}
