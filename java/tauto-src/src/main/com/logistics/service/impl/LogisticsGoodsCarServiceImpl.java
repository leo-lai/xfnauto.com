package main.com.logistics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.frame.dao.BaseDao;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.logistics.dao.dao.LogisticsGoodsCarDao;
import main.com.logistics.dao.po.LogisticsGoodsCar;
import main.com.logistics.service.LogisticsGoodsCarService;

/** 
* @author liaozijie 
* @version 创建时间：2018年1月16日 下午5:24:02 
* 类描述： 
*/
@Service
public class LogisticsGoodsCarServiceImpl extends BaseServiceImpl<LogisticsGoodsCar> implements LogisticsGoodsCarService{

	@Autowired
	private LogisticsGoodsCarDao logisticsGoodsCarDao;
	
	@Override
	protected BaseDao<LogisticsGoodsCar> getBaseDao() {
		// TODO Auto-generated method stub
		return logisticsGoodsCarDao;
	}
	
}
