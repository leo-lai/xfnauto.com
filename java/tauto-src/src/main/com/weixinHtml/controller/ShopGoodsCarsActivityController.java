package main.com.weixinHtml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinHtml.dao.search.ShopGoodsCarsActivitySearch;
import main.com.weixinHtml.service.ShopGoodsCarsActivityService;

/**
 * 商城商品活动
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interfaceShop/goodsCarsActivity")
public class ShopGoodsCarsActivityController extends BaseController{

	@Autowired
	ShopGoodsCarsActivityService activityService;
	
	/**
	 * 车商品列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/activityList")
	public Result activityList(ShopGoodsCarsActivitySearch search) {
		Result result = new Result();
		try {
//			SystemUsers users = this.supplementSearchUser(search.getSessionId());
//			if(users != null) {
//				search.setOrgId(users.getOrgId());
//			}
			result = activityService.activityList(search,result);
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
	@RequestMapping(value = "/orgActivityList")
	public Result orgActivityList(ShopGoodsCarsActivitySearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = activityService.activityList(search,result);
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
	@RequestMapping(value = "/activityShelves")
	public Result shopGoodsCarsShelves(ShopGoodsCarsActivitySearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}else {
				result.setOK(ResultCode.CODE_STATE_4002, "登录信息失效");
				return result;
			}
			result = activityService.activityShelves(search,result);
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
	@RequestMapping(value = "/activityInfo")
	public Result activityInfo(ShopGoodsCarsActivitySearch search) {
		Result result = new Result();
		try {
			result = activityService.activityInfo(search,result);
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
	@RequestMapping(value = "/activityDel")
	public Result activityDel(ShopGoodsCarsActivitySearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}else{
				result.setError(ResultCode.CODE_STATE_4002, "你的登录信息已失效，请重新登录");
				return result;
			}
			result = activityService.activityDel(search,result);
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
	@RequestMapping(value = "/activityEdit")
	public Result activityEdit(ShopGoodsCarsActivitySearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchSystemUsers(search.getSessionId());
			if(users == null) {
				result.setError(ResultCode.CODE_STATE_4002, "你的登录信息已失效，请重新登录");
				return result;
			}
			result = activityService.activityEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
