package main.com.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.dao.WebsiteNewsDao;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.po.WebsiteNews;
import main.com.system.dao.search.WebsiteNewsSearch;
import main.com.system.dao.vo.WebsiteNewsVo;
import main.com.system.service.WebsiteNewsService;
import main.com.utils.DateUtil;
import main.com.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteNewsServiceImpl extends BaseServiceImpl
<WebsiteNews>implements WebsiteNewsService{

	@Autowired
	WebsiteNewsDao websiteNewsDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Override
	protected BaseDao<WebsiteNews> getBaseDao() {
		return websiteNewsDao;
	}

	@Override
	public Result websiteNewsList(WebsiteNewsSearch search, Result result)
			throws Exception {
		Map<String,Object> params = getParams(search);
		List<WebsiteNewsVo> websiteNewsList = websiteNewsDao.select(params);
		Long total = websiteNewsDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		
		for(WebsiteNewsVo newsVo : websiteNewsList){
			Map<String,Object> orderMap = mapWebsiteNews(newsVo, false);
			maps.add(orderMap);
		}
		Map<String,Object> allMap = new HashMap<String, Object>();
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("page", search.getPage());
		allMap.put("websiteNews", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}

	public Map<String,Object> getParams(WebsiteNewsSearch search){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getNewsContent())){
			params.put("newsContent", search.getNewsContent());
		}
		if(StringUtil.isNotEmpty(search.getNewsDescribe())){
			params.put("newsDescribe", search.getNewsDescribe());
		}
		if(search.getNewsId() != null){
			params.put("newsId", search.getNewsId());
		}
		if(StringUtil.isNotEmpty(search.getStartDate())){
			params.put("startDate", search.getStartDate());
		}
		if(StringUtil.isNotEmpty(search.getFinishDate())){
			params.put("finishDate", search.getFinishDate());
		}
		if(StringUtil.isNotEmpty(search.getStartDate_push())){
			params.put("startDate_push", search.getStartDate_push());
		}
		if(StringUtil.isNotEmpty(search.getFinishDate_push())){
			params.put("finishDate_push", search.getFinishDate_push());
		}
		if(StringUtil.isNotEmpty(search.getNewsTitle())){
			params.put("newsTitle", search.getNewsTitle());
		}
		if(search.getNewsType() != null){
			params.put("newsType", search.getNewsType());
		}
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}
	
	public Map<String, Object> mapWebsiteNews(WebsiteNewsVo newsVo,Boolean hasContent){
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("newsId", newsVo.getNewsId());
		orderMap.put("newsTitle", newsVo.getNewsTitle());
		orderMap.put("newsDescribe", newsVo.getNewsDescribe());
		if(hasContent){
			orderMap.put("newsContent", newsVo.getNewsContent());
		}
		orderMap.put("newsMinImage", newsVo.getNewsMinImage()==null?"":newsVo.getNewsMinImage());//审核时间
		orderMap.put("createDate", DateUtil.format(newsVo.getCreateDate()));
		orderMap.put("isDelete", newsVo.getIsDelete());
		orderMap.put("systemUsersId", newsVo.getSystemUsersId());//审核人名称
		orderMap.put("systemUsersName", newsVo.getSystemUsersName());
		orderMap.put("published", DateUtil.format(newsVo.getPublished()));
		orderMap.put("updateDate", DateUtil.format(newsVo.getUpdateDate()));
		orderMap.put("remarks", newsVo.getRemarks()==null?"":newsVo.getRemarks());
		orderMap.put("newsType", newsVo.getNewsType());
		if(newsVo.getIsShare()!= null && newsVo.getIsShare()){
			orderMap.put("isShare", 1);
		}else{
			orderMap.put("isShare", 0);
		}
		orderMap.put("isShare", newsVo.getIsShare());
		return orderMap;
	}

	@Override
	public Result websiteNewsEdit(WebsiteNewsSearch search, Result result)
			throws Exception {
		SystemUsers systemUsers = systemUsersDao.selectByCode(search.getSessionId());
		if(systemUsers == null){
			result.setError(ResultCode.CODE_STATE_4002, "登录信息失效");
			return result;
		}
		WebsiteNews websiteNews = null;
		if(search.getNewsId() == null){
			websiteNews = new WebsiteNews();
			websiteNews.setCreateDate(new Date());
		}else{
			websiteNews = websiteNewsDao.selectById(search.getNewsId());
			if(websiteNews == null){
				result.setError(ResultCode.CODE_STATE_4005, "此新闻内容不存在，请重新选择");
				return result;
			}
		}
		websiteNews.setUpdateDate(new Date());
		websiteNews.setSystemUsersId(systemUsers.getUsersId());
		websiteNews.setSystemUsersName(systemUsers.getUserName());
		
		if(StringUtil.isNotEmpty(search.getNewsTitle())){
			websiteNews.setNewsTitle(search.getNewsTitle());
		}
		if(search.getNewsType() != null){
			websiteNews.setNewsType(search.getNewsType());
		}
		if(search.getIsShare() != null && search.getIsShare()){
			websiteNews.setIsShare(true);
		}else{
			websiteNews.setIsShare(false);
		}
		if(StringUtil.isNotEmpty(search.getNewsContent())){
			websiteNews.setNewsContent(search.getNewsContent());
		}
		if(StringUtil.isNotEmpty(search.getNewsDescribe())){
			websiteNews.setNewsDescribe(search.getNewsDescribe());
		}
		if(StringUtil.isNotEmpty(search.getRemarks())){
			websiteNews.setRemarks(search.getRemarks());
		}
		if(StringUtil.isNotEmpty(search.getNewsMinImage())){
			websiteNews.setNewsMinImage(search.getNewsMinImage());
		}
		if(search.getPublished() != null){
			if(DateUtil.format(search.getPublished()).getTime() - new Date().getTime() > 0){
				result.setError(ResultCode.CODE_STATE_4005, "发布时间错误");
				return result;
			}
			websiteNews.setPublished(DateUtil.format(search.getPublished()));
		}

		if(search.getNewsId() == null){
			if(websiteNewsDao.insert(websiteNews)){
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				return result;
			}else{
				result.setError(ResultCode.CODE_STATE_4005, "操作失败");
				return result;
			}
		}else{
			if(websiteNewsDao.updateById(websiteNews)){
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
				return result;
			}else{
				result.setError(ResultCode.CODE_STATE_4005, "操作失败");
				return result;
			}
		}
	}

	@Override
	public Result websiteNewsDelete(WebsiteNewsSearch search, Result result)
			throws Exception {
		WebsiteNews websiteNews = websiteNewsDao.selectById(search.getNewsId());
		if(websiteNews == null){
			result.setError(ResultCode.CODE_STATE_4005, "请选择新闻纪录进行操作");
			return result;
		}
		if(search.getIsDelete() == true){
			websiteNews.setIsDelete(true);
		}else if(search.getIsDelete() == false){
			websiteNews.setIsDelete(false);
		}else{
			result.setError(ResultCode.CODE_STATE_4005, "参数错误");
			return result;
		}
		if(websiteNewsDao.updateById(websiteNews)){
			result.setError(ResultCode.CODE_STATE_200, "操作成功");
			return result;
		}else{
			result.setError(ResultCode.CODE_STATE_4005, "操作失败");
			return result;
		}
	}

	@Override
	public Result websiteNewsInfo(WebsiteNewsSearch search, Result result)
			throws Exception {
		WebsiteNewsVo websiteNews = websiteNewsDao.selectById(search.getNewsId());
		if(websiteNews == null){
			result.setError(ResultCode.CODE_STATE_4005, "请选择新闻纪录进行操作");
			return result;
		}
		result.setOK(ResultCode.CODE_STATE_200, "", mapWebsiteNews(websiteNews, true));
		return result;
	}
}
