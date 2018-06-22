package main.com.logistics.dao.dao;

import main.com.frame.dao.BaseDao;
import main.com.logistics.dao.po.LogisticsDynamicLineInfo;

public interface LogisticsDynamicLineInfoDao extends BaseDao<LogisticsDynamicLineInfo>{

	/**
	 * 根据父类ID删除
	 * @param dynamicLineId
	 * @throws Exception
	 */
	Boolean deleteByParentId(Integer dynamicLineId)throws Exception;

}
