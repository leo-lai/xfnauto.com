package main.com.weixinHtml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinHtml.dao.search.ShopFindCarOfferSearch;
import main.com.weixinHtml.service.ShopFindCarOfferService;

/**
 * 商城寻车报价
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interfaceShop/shopFindCarOffer")
public class ShopFindCarOfferController extends BaseController{

	@Autowired
	ShopFindCarOfferService shopFindCarOfferService;
	
	/**
	 * 给寻车申请报价
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopFindCarOffer")
	public Result shopFindCarOffer(ShopFindCarOfferSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = shopFindCarOfferService.shopFindCarOffer(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 给寻车申请报价详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopFindCarOfferInfo")
	public Result shopFindCarOfferInfo(ShopFindCarOfferSearch search) {
		Result result = new Result();
		try {
			result = shopFindCarOfferService.shopFindCarOfferInfo(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 商户版，我的报价
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myShopFindCarOfferList")
	public Result myShopFindCarOffer(ShopFindCarOfferSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = shopFindCarOfferService.myShopFindCarOffer(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
