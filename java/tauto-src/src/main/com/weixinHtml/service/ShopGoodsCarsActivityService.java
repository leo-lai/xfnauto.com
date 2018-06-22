package main.com.weixinHtml.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.weixinHtml.dao.po.ShopGoodsCarsActivity;
import main.com.weixinHtml.dao.search.ShopGoodsCarsActivitySearch;

public interface ShopGoodsCarsActivityService extends BaseService<ShopGoodsCarsActivity>{

	/**
	 * 活动列表
	 * @param search
	 * @param result
	 * @return
	 */
	Result activityList(ShopGoodsCarsActivitySearch search, Result result);
	
	/**
	 * 活动列表
	 * @param search
	 * @param result
	 * @return
	 */
	Result activityList(ShopGoodsCarsActivitySearch search, Result result,SystemUsersVo users);

	/**
	 * 活动列表详情
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result activityInfo(ShopGoodsCarsActivitySearch search, Result result)throws Exception;

	/**
	 * 活动删除
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result activityDel(ShopGoodsCarsActivitySearch search, Result result)throws Exception;

	/**
	 * 活动编辑
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result activityEdit(ShopGoodsCarsActivitySearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 上下架活动
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result activityShelves(ShopGoodsCarsActivitySearch search, Result result)throws Exception;
}
