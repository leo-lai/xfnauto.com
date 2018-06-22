package main.com.weixin.dao.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.com.customer.dao.po.CustomerOrder;
import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.system.dao.po.SystemUsers;
import main.com.weixin.dao.dao.EmployeeUserDao;
@Repository
public class EmployeeUserDaoImpl extends BaseDaoImpl<SystemUsers> implements EmployeeUserDao{
	
	@Override
	public List<CustomerOrder> queryFinishedOrders(Map map) {
		// TODO Auto-generated method stub
		try {
			return sqlSessionTemplate.selectList(getSqlName("queryFinishedOrders"), map);
		}catch(Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName("queryFinishedOrders")), e);
		}
		
	}
	
	@Override
	public BigDecimal getTotalGrossProfit(Map map) {
		// TODO Auto-generated method stub
		try {
			return sqlSessionTemplate.selectOne(getSqlName("getTotalGrossProfit"), map);
		}catch(Exception e) {
			throw new DaoException(String.format("sql语句出错！语句：%s", getSqlName(getSqlName("getTotalGrossProfit"))), e);
		}
	}
	
	
	

}
