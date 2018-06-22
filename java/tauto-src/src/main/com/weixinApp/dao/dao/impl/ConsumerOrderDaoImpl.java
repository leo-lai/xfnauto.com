package main.com.weixinApp.dao.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.stock.dao.po.ConsumerOrder;
import main.com.weixinApp.dao.dao.ConsumerOrderDao;
@Repository
public class ConsumerOrderDaoImpl extends BaseDaoImpl<ConsumerOrder> implements ConsumerOrderDao{

	@Override
	public List<ConsumerOrder> listOrders(Map<String,Object> map) {
		// TODO Auto-generated method stub
		try {
			return sqlSessionTemplate.selectList(getSqlName("listOrders"),
					map);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("listOrders")), e);
		}
	}
	
	@Override
	public List<ConsumerOrder> mylistOrders(Map<String,Object> map) {
		// TODO Auto-generated method stub
		try {
			return sqlSessionTemplate.selectList(getSqlName("mylistOrders"),
					map);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("listOrders")), e);
		}
	}
	
}
