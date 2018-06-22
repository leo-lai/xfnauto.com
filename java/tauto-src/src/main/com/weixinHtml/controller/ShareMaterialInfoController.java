package main.com.weixinHtml.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.StringUtil;
import main.com.utils.Value;
import main.com.weixin.service.WeixinAppTokenService;
import main.com.weixinHtml.dao.dao.ShareMaterialInfoDao;
import main.com.weixinHtml.dao.po.ShareMaterial;
import main.com.weixinHtml.dao.search.ShareMaterialInfoSearch;
import main.com.weixinHtml.dao.vo.ShareMaterialInfoVo;
import main.com.weixinHtml.service.ShareMaterialInfoService;

/**
 * 三级素材管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interfaceShop/shareMaterialInfo")
public class ShareMaterialInfoController extends BaseController{

	public static Logger logger = Logger.getLogger(ShareMaterialInfoController.class);
	
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
				System.out.println("UserCode:"+users.getUserCode());
				users.setTicket(weixinAppTokenService.getQRticket(users.getUserCode()));
				System.out.println("获取Ticket成功");
				users.setTicketImage(Value.Weixin.ticket.replace("TICKET", users.getTicket()));
				System.out.println("换掉连接成功");
				if(systemUsersDao.updateById(users)) {
					System.out.println("更新用户资料成功");
				}else {
					System.out.println("更新用户资料失败");
				}
				System.out.println("返回页面");
			}
			request.setAttribute("ticketImage", users.getTicketImage());
		} catch (Exception e) { 
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return "share";
	}
}
