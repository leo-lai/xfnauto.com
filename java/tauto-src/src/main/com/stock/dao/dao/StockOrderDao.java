package main.com.stock.dao.dao;

import main.com.frame.dao.BaseDao;
import main.com.stock.dao.po.StockOrder;

public interface StockOrderDao extends BaseDao<StockOrder>{

	public String getOrderCode() throws Exception;
}
