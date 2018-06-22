package main.com.weixinApp.dao.dao.impl;

import main.com.stock.dao.po.ConsumerOrderCar;
import main.com.weixinApp.dao.dao.ConsumerOrderCarDao;

import org.springframework.stereotype.Repository;

import lombok.Data;
import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
@Repository
public class ConsumerOrderCarDaoImpl extends BaseDaoImpl<ConsumerOrderCar> implements ConsumerOrderCarDao{
	
	@Override
	public Boolean update(ConsumerOrderCar entity) {
		// TODO Auto-generated method stub
		try {
			int rows = sqlSessionTemplate.update(
					getSqlName("update"), entity);
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID更新对象出错！语句：%s",
					getSqlName("update")), e);
		}
	}
	
	
}
