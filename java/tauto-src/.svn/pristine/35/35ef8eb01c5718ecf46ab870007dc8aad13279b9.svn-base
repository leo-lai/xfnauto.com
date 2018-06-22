package main.com.system.dao.dao;

import java.util.List;

import main.com.frame.dao.BaseDao;
import main.com.system.dao.po.SystemRegion;
import main.com.system.dao.search.SystemRegionSearch;
import main.com.system.dao.vo.SystemRegionVo;

public interface SystemRegionDao extends BaseDao<SystemRegion> {

	/**
	 * 获取行政地区
	 * @param regionSearch
	 * @return
	 * @throws Exception
	 */
	SystemRegionVo getRegion(SystemRegionSearch regionSearch)throws Exception;

	SystemRegion selectById(String areaCode) throws Exception;

	public List<SystemRegion> getByParentId(String parentId)throws Exception ;

}
