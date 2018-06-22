package main.com.weixinHtml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.car.dao.search.CarsSearch;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemWarehouseSearch;
import main.com.system.service.OrganizationService;
import main.com.weixinApp.service.AppCarsService;
import main.com.weixinHtml.dao.search.ShopFindCarOfferSearch;
import main.com.weixinHtml.dao.search.ShopGoodsCarsSearch;
import main.com.weixinHtml.service.ShopGoodsCarsService;

/**
 * 商城商品
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interfaceShop/shopGoodsCars")
public class ShopGoodsCarsController extends BaseController{

	@Autowired
	ShopGoodsCarsService shopGoodsCarsService;
	
	@Autowired
	AppCarsService appCarsService;
	
	@Autowired
	OrganizationService organizationService;
	
	/**
	 * 车商品列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopGoodsCarsList")
	public Result shopGoodsCarsList(ShopGoodsCarsSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shopGoodsCarsService.shopGoodsCarsList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 车商品列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orgShopGoodsCarsList")
	public Result orgShopGoodsCarsList(ShopGoodsCarsSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shopGoodsCarsService.shopGoodsCarsList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 车商品详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopGoodsCarsInfo")
	public Result shopGoodsCarsInfo(ShopGoodsCarsSearch search) {
		Result result = new Result();
		try {
			result = shopGoodsCarsService.shopGoodsCarsInfo(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 上架下架
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopGoodsCarsShelves")
	public Result shopGoodsCarsShelves(ShopGoodsCarsSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}else {
				result.setOK(ResultCode.CODE_STATE_4002, "登录信息失效");
				return result;
			}
			result = shopGoodsCarsService.shopGoodsCarsShelves(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 删除
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopGoodsCarsDel")
	public Result shopGoodsCarsDel(ShopGoodsCarsSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}else{
				result.setError(ResultCode.CODE_STATE_4002, "你的登录信息已失效，请重新登录");
				return result;
			}
			result = shopGoodsCarsService.shopGoodsCarsDel(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 编辑
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopGoodsCarsEdit")
	public Result shopGoodsCarsEdit(ShopGoodsCarsSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchSystemUsers(search.getSessionId());
			if(users == null) {
				result.setError(ResultCode.CODE_STATE_4002, "你的登录信息已失效，请重新登录");
				return result;
			}
			result = shopGoodsCarsService.shopGoodsCarsEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 购车计算器全款
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/fullPayment")
	@ResponseBody
	public Result fullPayment(CarsSearch search) {
		Result result = new Result();
		try{
			result = appCarsService.fullPayment(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 购车计算器贷款
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/loanPayment")
	@ResponseBody
	public Result loanPayment(CarsSearch search) {
		Result result = new Result();
		try{
			result = appCarsService.loanPayment(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 品牌下拉
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/brandListList")
	@ResponseBody
	public Result brandListList(CarsSearch search) {
		Result result = new Result();
		try{
			result = shopGoodsCarsService.brandListList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 区域下拉
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/cityListList")
	@ResponseBody
	public Result cityListList(CarsSearch search) {
		Result result = new Result();
		try{
			result = shopGoodsCarsService.cityListList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 组织仓库列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationWarehouseList", method = RequestMethod.POST)
	public Result organizationWarehouseList(SystemWarehouseSearch search) {
		Result result = new Result();
		try {
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			result = organizationService.organizationWarehouseList(search,result,systemUsers);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
}
