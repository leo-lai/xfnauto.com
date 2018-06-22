package main.com.weixin.dao.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import main.com.customer.dao.po.CustomerOrder;
import main.com.frame.dao.BaseDao;
import main.com.system.dao.po.SystemUsers;

public interface EmployeeUserDao extends BaseDao<SystemUsers>{
	
	/**
	 * 获取当月售车数量
	 * @param map
	 * @return
	 */
	List<CustomerOrder> queryFinishedOrders(Map map);
	
	/**
	 * 获取总毛利
	 * @param map
	 * @return
	 */
	BigDecimal getTotalGrossProfit(Map map);
}
