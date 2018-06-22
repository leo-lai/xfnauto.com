package main.com.weixinApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.OrganizationSearch;
import main.com.system.dao.search.SystemWarehouseSearch;
import main.com.system.dao.vo.OrganizationVo;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.system.service.OrganizationService;
import main.com.utils.StringUtil;
import main.com.utils.Value;
import main.com.weixin.service.WeixinAppTokenService;
import main.com.weixinHtml.dao.dao.ShareMaterialInfoDao;
import main.com.weixinHtml.dao.po.ShareMaterial;
import main.com.weixinHtml.dao.search.ShareMaterialInfoSearch;
import main.com.weixinHtml.dao.search.ShareMaterialSearch;
import main.com.weixinHtml.dao.search.ShopAdvanceOrderSearch;
import main.com.weixinHtml.dao.search.ShopApplyLoanSearch;
import main.com.weixinHtml.dao.search.ShopFindCarOfferSearch;
import main.com.weixinHtml.dao.search.ShopFindCarSearch;
import main.com.weixinHtml.dao.search.ShopGoodsCarsActivitySearch;
import main.com.weixinHtml.dao.search.ShopGoodsCarsSearch;
import main.com.weixinHtml.dao.search.ShopUserSearch;
import main.com.weixinHtml.dao.vo.ShareMaterialInfoVo;
import main.com.weixinHtml.service.ShareMaterialInfoService;
import main.com.weixinHtml.service.ShareMaterialService;
import main.com.weixinHtml.service.ShopAdvanceOrderService;
import main.com.weixinHtml.service.ShopApplyLoanService;
import main.com.weixinHtml.service.ShopFindCarOfferService;
import main.com.weixinHtml.service.ShopFindCarService;
import main.com.weixinHtml.service.ShopGoodsCarsActivityService;
import main.com.weixinHtml.service.ShopGoodsCarsService;
import main.com.weixinHtml.service.ShopUserService;

/**
 * 冗余一部分商城的商务端管理接口
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/emInterface/employee/shop/")
public class ShopAllController extends BaseController{

	@Autowired
	ShopGoodsCarsService shopGoodsCarsService;
	
	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	ShopAdvanceOrderService advanceOrderService; 

	@Autowired
	ShopApplyLoanService applyLoanService;
	
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
			}else {
				result.setOK(ResultCode.CODE_STATE_4002, "登录信息失效");
				return result;
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
			SystemUsersVo users = this.supplementSearchSystemUsers(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}else {
				result.setOK(ResultCode.CODE_STATE_4002, "登录信息失效");
				return result;
			}
			result = shopGoodsCarsService.shopGoodsCarsList(search,result,users);
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
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "车商品上下架")
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
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "车商品删除")
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
	 * 车商品编辑
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopGoodsCarsEdit")
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "车商品编辑")
	public Result shopGoodsCarsEdit(ShopGoodsCarsSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchSystemUsers(search.getSessionId());
			if(users == null) {
//				System.out.println("sessionId："+search.getSessionId());
//				System.out.println("系统查找不到登录用户："+search.getSessionId());
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
	
	/**
	 * 预订单列表 商务端
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orgAdvanceOrderList")
	public Result orgAdvanceOrderList(ShopAdvanceOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsersVo users = supplementSearchSystemUsers(search.getSessionId());
			result = advanceOrderService.orgAdvanceOrderList(search,result,users,true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 预订详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orgAdvanceOrderInfo")
	public Result orgAdvanceOrderInfo(ShopAdvanceOrderSearch search) {
		Result result = new Result();
		try {
			result = advanceOrderService.orgAdvanceOrderInfo(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
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
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "二级素材编辑")
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
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "二级素材删除")
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
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "二级素材上下架")
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
	
	@Autowired
	ShareMaterialInfoService shareMaterialInfoService;

	
	@Autowired
	ShareMaterialInfoDao shareMaterialInfoDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	WeixinAppTokenService weixinAppTokenService;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	/**
	 * 三级素材列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myShareMaterialInfoList")
	public Result myShareMaterial(ShareMaterialInfoSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shareMaterialInfoService.myShareMaterialInfoList(search,result,users);
		} catch (Exception e) { 
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 三级素材编辑（创建分享）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shareMaterialInfoEdit")
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "三级素材编辑")
	public Result shareMaterialInfoEdit(ShareMaterialInfoSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shareMaterialInfoService.shareMaterialInfoEdit(search,result,users);
		} catch (Exception e) { 
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 三级素材删除
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shareMaterialInfoDel")
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "三级素材删除")
	public Result shareMaterialInfoDel(ShareMaterialInfoSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shareMaterialInfoService.shareMaterialInfoDel(search,result,users);
		} catch (Exception e) { 
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 三级素材详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shareMaterialInfoInfo")
	public Result shareMaterialInfoInfo(ShareMaterialInfoSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shareMaterialInfoService.shareMaterialInfoInfo(search,result,users);
		} catch (Exception e) { 
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 三级素材分享
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shareMaterialInfoShar")
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "三级素材分享")
	public Result shareMaterialInfoShar(ShareMaterialInfoSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = shareMaterialInfoService.shareMaterialInfoShar(search,result,users,request,response);			
		} catch (Exception e) { 
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 三级素材分享
	 * @param menuSearch
	 * @return
	 */
	@RequestMapping(value = "/shareMaterialInfoSharHtml")
	public String shareMaterialInfoSharHtml(ShareMaterialInfoSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = this.supplementSearchUser(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			ShareMaterialInfoVo materialInfoVo = shareMaterialInfoDao.selectByIdJoin(search.getMaterialInfoId());
			if(materialInfoVo == null) {
				System.out.println("你选择的素材不存在或者已删除");
				return "index";
			}
			Organization organization = organizationDao.selectById(users.getOrgId());
			if(organization == null) {
				System.out.println("你的权限不足");
				return "index";
			}
			ShareMaterial shareMaterial = materialInfoVo.getMaterialVo();
			if(shareMaterial == null) {
				System.out.println("该素材的上级素材不存在或者已删除，分享失败");
				return "index";
			}
//			String url = "http://localhost:8080/tauto/share.html?materialName="+shareMaterial.getMaterialName()+"&baseImage="+shareMaterial.getImage()+"&image="+
//					materialInfoVo.getImage()+"&remarks"+materialInfoVo.getRemarks()+"&userName="+users.getRealName()+"&phone="+users.getPhoneNumber()+"&orgName="+
//					organization.getShortName()+"&headPortrait"+users.getHeadPortrait()+"&ticketImage"+users.getTicketImage();
			request.setAttribute("materialName", shareMaterial.getMaterialName());
			request.setAttribute("baseImage", shareMaterial.getImage());
			request.setAttribute("image", materialInfoVo.getImage());
			request.setAttribute("remarks", materialInfoVo.getRemarks());
			request.setAttribute("userName", users.getRealName());
			request.setAttribute("phone", users.getPhoneNumber());
			request.setAttribute("orgName", organization.getShortName());
			request.setAttribute("headPortrait", users.getHeadPortrait());
			if(StringUtil.isEmpty(users.getTicketImage())) {
				if(StringUtil.isEmpty(users.getUserCode())) {
					users.setUserCode(systemUsersDao.getUserCode());
				}
				users.setTicket(weixinAppTokenService.getQRticket(users.getUserCode()));
				users.setTicketImage(Value.Weixin.ticket.replace("TICKET", users.getTicket()));
				systemUsersDao.updateById(users);
			}
			request.setAttribute("ticketImage", users.getTicketImage());
		} catch (Exception e) { 
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return "share";
	}
	
	/**
	 * 全部贷款列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applyLoanList")
	public Result applyLoanList(ShopApplyLoanSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = applyLoanService.applyLoanList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 贷款审核
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applyLoanAudit")
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "贷款审核")
	public Result applyLoanAudit(ShopApplyLoanSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = applyLoanService.applyLoanAudit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	@Autowired
	ShopFindCarService shopFindCarService;
	
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
	
	@Autowired
	ShopFindCarOfferService shopFindCarOfferService;
	
	/**
	 * 给寻车申请报价
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopFindCarOffer")
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "寻车报价")
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
	
	@Autowired
	ShopGoodsCarsActivityService activityService;
	
	/**
	 * 车活动列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orgActivityList")
	public Result orgActivityList(ShopGoodsCarsActivitySearch search) {
		Result result = new Result();
		try {
			SystemUsersVo users = this.supplementSearchSystemUsers(search.getSessionId());
			if(users != null) {
				search.setOrgId(users.getOrgId());
			}
			result = activityService.activityList(search,result,users);
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
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "车活动上下架")
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
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "车活动删除")
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
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "车活动编辑")
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
	
	@Autowired
	private ShopUserService shopUsersService;
	
	/**
	 * 商城B端用户列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopOrgList", method = RequestMethod.POST)
	public Result shopOrgList(OrganizationSearch search) {
		Result result = new Result();
		try {
			SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
			if(user == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息已失效重新登录重试！");
				return result;
			}
			result = shopUsersService.shopOrgList(search,result,user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 审核B端用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shopOrgAudit", method = RequestMethod.POST)
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "B端用户审核")
	public Result shopOrgAudit(ShopUserSearch search) {
		Result result = new Result();
		try {
			SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
			if(user == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息已失效重新登录重试！");
				return result;
			}
			result = shopUsersService.shopOrgAudit(search,result,user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
	
	/**
	 * 配置B端用户通联支付信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/allInPayConfigure", method = RequestMethod.POST)
	@LogPoint(logDes = "商务端淘车快管理模块", operateModule = "配置B端用户通联支付信息")
	public Result allInPayConfigure(ShopUserSearch search) {
		Result result = new Result();
		try {
			SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
			if(user == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息已失效重新登录重试！");
				return result;
			}
			result = shopUsersService.allInPayConfigure(search,result,user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;
	}
}
