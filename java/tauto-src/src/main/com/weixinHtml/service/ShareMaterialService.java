package main.com.weixinHtml.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinHtml.dao.po.ShareMaterial;
import main.com.weixinHtml.dao.search.ShareMaterialSearch;

public interface ShareMaterialService extends BaseService<ShareMaterial>{

	/**
	 * 素材列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result shareMaterialList(ShareMaterialSearch search, Result result)throws Exception;

	/**
	 * 我的素材列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result myShareMaterialList(ShareMaterialSearch search, Result result)throws Exception;

	/**
	 * 二级编辑素材
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
//	Result shareMaterialEdit(ShareMaterialSearch search, Result result)throws Exception;
	Result shareMaterialEdit(ShareMaterialSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 二级删除素材
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shareMaterialDel(ShareMaterialSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 二级素材上下架
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shareMaterialOverShelf(ShareMaterialSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 耳机素材下拉列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result shareMaterialListList(ShareMaterialSearch search, Result result)throws Exception;

	/**
	 * 二级素材详情
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result shareMaterialInfo(ShareMaterialSearch search, Result result)throws Exception;

}
