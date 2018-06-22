package main.com.weixinHtml.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinHtml.dao.search.ShareMaterialSearch;
import main.com.weixinHtml.dao.search.ShopGoodsCarsSearch;
import main.com.weixinHtml.service.ShareMaterialService;

/**
 * 二级素材管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interfaceShop/shareMaterial")
public class ShareMaterialController extends BaseController{

	public static Logger logger = Logger.getLogger(HtmlConsignmentController.class);
	
	@Autowired
	ShareMaterialService shareMaterialService;
	
	/**
	 * 二级管理素材列表（全部素材列表）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myShareMaterialList")
	public Result myShareMaterial(ShareMaterialSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shareMaterialService.myShareMaterialList(search,result);
		} catch (Exception e) { 
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 二级已上架素材列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shareMaterialList")
	public Result shareMaterialList(ShareMaterialSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shareMaterialService.shareMaterialList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 二级素材下拉列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shareMaterialListList")
	public Result shareMaterialListList(ShareMaterialSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shareMaterialService.shareMaterialListList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 二级编辑素材
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shareMaterialEdit")
	public Result shareMaterialEdit(ShareMaterialSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shareMaterialService.shareMaterialEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 二级删除素材
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shareMaterialDel")
	public Result shareMaterialDel(ShareMaterialSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shareMaterialService.shareMaterialDel(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 二级素材上下架
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shareMaterialOverShelf")
	public Result shareMaterialOverShelf(ShareMaterialSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shareMaterialService.shareMaterialOverShelf(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 二级素材详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shareMaterialInfo")
	public Result shareMaterialInfo(ShareMaterialSearch search) {
		Result result = new Result();
		try {
			result = shareMaterialService.shareMaterialInfo(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
