package main.com.weixinHtml.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.weixinHtml.dao.dao.ShareMaterialDao;
import main.com.weixinHtml.dao.po.ShareMaterial;
import main.com.weixinHtml.dao.search.ShareMaterialSearch;
import main.com.weixinHtml.dao.vo.ShareMaterialVo;
import main.com.weixinHtml.service.ShareMaterialService;

@Service
public class ShareMaterialServiceImpl extends BaseServiceImpl<ShareMaterial> implements ShareMaterialService{

	@Autowired
	ShareMaterialDao shareMaterialDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Override
	protected BaseDao<ShareMaterial> getBaseDao() {
		return shareMaterialDao;
	}

	@Override
	public Result shareMaterialList(ShareMaterialSearch search, Result result) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("keywords", search.getKeywords());
		params.put("overShelf", true);
		List<ShareMaterialVo> materialVos = shareMaterialDao.selectJoin(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", materialVos);
		return new Result(returnMap);		
	}

	@Override
	public Result myShareMaterialList(ShareMaterialSearch search, Result result) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("keywords", search.getKeywords());		
//		params.put("orgId", search.getOrgId());
		if(search.getOverShelf()!=null) {
			params.put("overShelf", search.getOverShelf());
		}
		List<ShareMaterialVo> materialVos = shareMaterialDao.selectJoin(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", materialVos);
		return new Result(returnMap);
	}

	@Override
	public Result shareMaterialEdit(ShareMaterialSearch search, Result result,SystemUsers users) throws Exception {
		Organization organization = organizationDao.selectById(users.getOrgId());
		if(organization == null) {
			result.setError("你的权限不足");
			return result;
		}
		if(organization.getOrgLevel() < GeneralConstant.Org.Level_2) {
			result.setError("你的权限不足");
			return result;
		}
		ShareMaterialVo materialVo = null;
		if(StringUtil.isEmpty(search.getMaterialId())) {
			materialVo = new ShareMaterialVo();
			materialVo.setCreateDate(new Date());
			materialVo.setSystemUserId(users.getUsersId());
			materialVo.setOverDelete(false);
			materialVo.setOverShare(false);
			materialVo.setOverShelf(false);
		}else {
			materialVo = shareMaterialDao.selectById(search.getMaterialId());
			if(materialVo == null) {
				result.setError("你选择的素材不存在或者已删除");
				return result;
			}
		}
		if(StringUtil.isEmpty(search.getMaterialName())) {
			result.setError("请填写素材名称");
			return result;
		}
		materialVo.setMaterialName(search.getMaterialName());
		if(StringUtil.isEmpty(search.getImage())) {
			result.setError("请上传素材图片");
			return result;
		}
		materialVo.setImage(search.getImage());
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			materialVo.setRemarks(search.getRemarks());
		}
		return save(materialVo, result, materialVo.getMaterialId());
	}

	@Override
	public Result shareMaterialDel(ShareMaterialSearch search, Result result, SystemUsers users) throws Exception {
		ShareMaterialVo materialVo = shareMaterialDao.selectById(search.getMaterialId());
		if(materialVo == null) {
			result.setError("你选择的素材不存在或者已删除");
			return result;
		}
		Organization organization = organizationDao.selectById(users.getOrgId());
		if(organization == null) {
			result.setError("你的权限不足");
			return result;
		}
		if(organization.getOrgLevel() < GeneralConstant.Org.Level_2) {
			result.setError("你的权限不足");
			return result;
		}
		if(materialVo.getOverShelf()) {
			result.setError("素材"+materialVo.getMaterialName()+"已上架商城，请先下架");
			return result;
		}
		if(materialVo.getOverDelete()) {
			result.setError("素材"+materialVo.getMaterialName()+"已删除，无需重复操作");
			return result;
		}
		materialVo.setOverDelete(true);
		return save(materialVo, result, materialVo.getMaterialId());
	}

	@Override
	public Result shareMaterialOverShelf(ShareMaterialSearch search, Result result, SystemUsers users)
			throws Exception {
		ShareMaterialVo materialVo = shareMaterialDao.selectById(search.getMaterialId());
		if(materialVo == null || materialVo.getOverDelete()) {
			result.setError("你选择的素材不存在或者已删除");
			return result;
		}
		Organization organization = organizationDao.selectById(users.getOrgId());
		if(organization == null) {
			result.setError("你的权限不足");
			return result;
		}
		if(organization.getOrgLevel() < GeneralConstant.Org.Level_2) {
			result.setError("你的权限不足");
			return result;
		}
		if(StringUtil.isEmpty(search.getOverShelf())) {
			result.setError("请选择上架或下架操作");
			return result;
		}
		if(search.getOverShelf()) {
			if(materialVo.getOverShelf()) {
				result.setError("素材"+materialVo.getMaterialName()+"已上架商城，无需重复操作");
				return result;
			}
			materialVo.setOverShelf(true);
			materialVo.setOnShelfDate(new Date());
		}else {
			if(!materialVo.getOverShelf()) {
				result.setError("素材"+materialVo.getMaterialName()+"已从商城下架，无需重复操作");
				return result;
			}
			materialVo.setOverShelf(false);
		}
		return save(materialVo, result, materialVo.getMaterialId());
	}

	@Override
	public Result shareMaterialListList(ShareMaterialSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<>();
		params.put("overShelf", true);
		List<ShareMaterialVo> materialVos = shareMaterialDao.select(params);
		List<Map<String,Object>> maps = new ArrayList<>();
		for(ShareMaterialVo materialVo : materialVos) {
			Map<String,Object> map = new HashMap<>();
			map.put("id", materialVo.getMaterialId());
			map.put("name", materialVo.getMaterialName());
			maps.add(map);
		}
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("list", maps);
		return new Result(returnMap);
	}

	@Override
	public Result shareMaterialInfo(ShareMaterialSearch search, Result result) throws Exception {
		ShareMaterialVo materialVo = shareMaterialDao.selectByIdJoin(search.getMaterialId());
		if(materialVo == null || materialVo.getOverDelete()) {
			result.setError("你选择的素材不存在或者已删除");
			return result;
		}
		result.setOK(ResultCode.CODE_STATE_200, "", materialVo);
		return result;
	}

}
