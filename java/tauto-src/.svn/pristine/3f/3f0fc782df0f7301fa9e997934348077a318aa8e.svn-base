package main.com.logistics.dao.dao;

import java.util.List;
import java.util.Map;

import main.com.frame.dao.BaseDao;
import main.com.logistics.dao.po.LogisticsConsignment;

public interface LogisticsConsignmentDao extends BaseDao<LogisticsConsignment>{

	/**
	 * 查询分页
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Long getCountJoin(Map<String, Object> params)throws Exception;

	/**
	 * 获取托运单单号
	 * @return
	 * @throws Exception
	 */
	public String getCode() throws Exception;
	
	/**
	 * 托运单列表
	 * @param map
	 * @return
	 */
	List<LogisticsConsignment> listLogisticsConsignment(Map<String,Object> map);
	
	
}
