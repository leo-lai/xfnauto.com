package main.com.logistics.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.logistics.dao.po.LogisticsDedicatedLine;
import main.com.logistics.dao.search.LogisticsDedicatedLineSearch;
import main.com.system.dao.po.SystemUsers;

public interface LogisticsDedicatedLineService extends BaseService<LogisticsDedicatedLine>{

	/**
	 * 物流专线资费配置列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result dedicatedLineList(LogisticsDedicatedLineSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result dedicatedLineEdit(LogisticsDedicatedLineSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 删除
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result dedicatedLineDelete(LogisticsDedicatedLineSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 资费列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result LogisticsLineList(LogisticsDedicatedLineSearch search, Result result)throws Exception;

//	/**
//	 * 专线非专线下拉列表
//	 * @param search
//	 * @param result
//	 * @param users
//	 * @return
//	 * @throws Exception
//	 */
//	Result dedicatedLineListList(LogisticsDedicatedLineSearch search, Result result, SystemUsers users)throws Exception;

}
