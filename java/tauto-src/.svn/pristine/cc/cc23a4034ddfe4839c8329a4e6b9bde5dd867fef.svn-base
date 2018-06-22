package main.com.weixinHtml.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinHtml.dao.po.ShopFindCarOffer;
import main.com.weixinHtml.dao.search.ShopFindCarOfferSearch;

public interface ShopFindCarOfferService extends BaseService<ShopFindCarOffer>{

	/**
	 * 给寻车报价
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shopFindCarOffer(ShopFindCarOfferSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 给寻车报价详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shopFindCarOfferInfo(ShopFindCarOfferSearch search, Result result)throws Exception;

	/**
	 * 我的报价 商务版
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result myShopFindCarOffer(ShopFindCarOfferSearch search, Result result, SystemUsers users)throws Exception;

}
