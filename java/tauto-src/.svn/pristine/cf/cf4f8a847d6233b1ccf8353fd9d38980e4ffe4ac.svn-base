package main.com.weixinHtml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.service.OrganizationService;
import main.com.weixinHtml.dao.search.ShopGoodsCarsSearch;

/**
 * 商城商品
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interfaceShop/shopIndex")
public class ShopIndexController {

	@Autowired
	OrganizationService organizationService;
	
	/**
	 * 根据orgCode获取门店信息
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orgInfo")
	public Result shopGoodsCarsList(ShopGoodsCarsSearch search) {
		Result result = new Result();
		try {
			result = organizationService.organizationInfo(search.getOrgCode(), result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
