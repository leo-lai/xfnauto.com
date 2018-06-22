package main.com.weixinHtml.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinHtml.dao.po.ShareMaterialInfo;
import main.com.weixinHtml.dao.search.ShareMaterialInfoSearch;

public interface ShareMaterialInfoService extends BaseService<ShareMaterialInfo>{

	/**
	 * 三级素材列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result myShareMaterialInfoList(ShareMaterialInfoSearch search, Result result,SystemUsers users)throws Exception;

	/**
	 * 三级编辑素材（创建分享）
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shareMaterialInfoEdit(ShareMaterialInfoSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 三级素材删除
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shareMaterialInfoDel(ShareMaterialInfoSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 分享
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shareMaterialInfoShar(ShareMaterialInfoSearch search, Result result, SystemUsers users,HttpServletRequest request,HttpServletResponse response)throws Exception;

	/**
	 * 分享详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shareMaterialInfoInfo(ShareMaterialInfoSearch search, Result result, SystemUsers users)throws Exception;

}
