package main.com.system.dao.dao;

import java.util.Map;

import main.com.frame.dao.BaseDao;
import main.com.system.dao.po.SystemUserGrouping;
import main.com.system.dao.vo.SystemUserGroupingVo;

public interface SystemUserGroupingDao extends BaseDao<SystemUserGrouping>{

	SystemUserGroupingVo selectByBothId(Map<String, Object> params)throws Exception;

}
