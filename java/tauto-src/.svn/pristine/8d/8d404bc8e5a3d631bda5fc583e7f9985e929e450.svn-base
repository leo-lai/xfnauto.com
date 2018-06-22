package main.com.system.service;

import java.util.List;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemRegion;
import main.com.system.dao.search.SystemRegionSearch;
import main.com.system.dao.vo.SystemRegionVo;

public interface SystemRegionService extends BaseService<SystemRegion> {

	/**
	 * 获取一个行政地区
	 * @param regionSearch
	 * @return
	 */
	SystemRegionVo getRegion(SystemRegionSearch regionSearch)throws Exception;

	Result getRegionOfParentId(SystemRegionSearch search)throws Exception;

	List<SystemRegion> getAll()throws Exception;

}
