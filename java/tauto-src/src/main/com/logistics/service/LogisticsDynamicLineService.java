package main.com.logistics.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.logistics.dao.po.LogisticsDynamicLine;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.dao.search.LogisticsDynamicLineSearch;
import main.com.system.dao.po.SystemUsers;

public interface LogisticsDynamicLineService extends BaseService<LogisticsDynamicLine>{

	/**
	 * 获取专线配置资费详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result dynamicLineInfo(LogisticsDynamicLineSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 非专线配置编辑
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result dynamicLineEdit(LogisticsDynamicLineSearch search, Result result, SystemUsers users)throws Exception;

}
