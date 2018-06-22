package main.com.weixinApp.dao.dao;

import main.com.frame.dao.BaseDao;
import main.com.stock.dao.po.ConsumerOrderCar;
import main.com.weixinApp.service.ConsumerOrderCarService;

public interface ConsumerOrderCarDao extends BaseDao<ConsumerOrderCar>{
	
	 Boolean update(ConsumerOrderCar entity);
}
