package main.com.weixinHtml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.search.ShopApplyLoanSearch;
import main.com.weixinHtml.dao.search.ShopFindCarSearch;
import main.com.weixinHtml.service.ShopFindCarService;

/**
 * 商城寻车
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interfaceShop/shopFindCar")
public class ShopFindCarController extends BaseController{

	@Autowired
	ShopFindCarService shopFindCarService;
	
	/**
	 * 寻车申请
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopFindCarEdit")
	public Result shopFindCarEdit(ShopFindCarSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = shopFindCarService.shopFindCarEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 我的寻车申请列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myShopFindCarList")
	public Result myShopFindCarList(ShopFindCarSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = shopFindCarService.shopFindCarList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 寻车申请详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopFindCarInfo")
	public Result shopFindCarInfo(ShopFindCarSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = shopFindCarService.shopFindCarInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 全部寻车列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopFindCarList")
	public Result shopFindCarList(ShopFindCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = shopFindCarService.shopFindCarList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 我的寻车申请取消
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopFindCarDel")
	public Result shopFindCarDel(ShopFindCarSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = shopFindCarService.shopFindCarDel(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 我的寻车申请处理标识
	 * @param menuSearch
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/shopFindCarUpdateState")
	public Result shopFindCarUpdateState(ShopFindCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = shopFindCarService.shopFindCarUpdateState(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
