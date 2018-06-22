package main.com.system.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.WebsiteNews;
import main.com.system.dao.search.WebsiteNewsSearch;

public interface WebsiteNewsService extends BaseService<WebsiteNews>{

	/**
	 * 新闻列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result websiteNewsList(WebsiteNewsSearch search, Result result)throws Exception;

	/**
	 * 编辑新闻
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result websiteNewsEdit(WebsiteNewsSearch search, Result result)throws Exception;

	/**
	 * 下线新闻
	 * @param search
	 * @param result
	 * @return
	 */
	Result websiteNewsDelete(WebsiteNewsSearch search, Result result)throws Exception;

	/**
	 * 新闻详情
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result websiteNewsInfo(WebsiteNewsSearch search, Result result)throws Exception;

}
