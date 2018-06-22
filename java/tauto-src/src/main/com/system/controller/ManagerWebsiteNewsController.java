package main.com.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.search.WebsiteNewsSearch;
import main.com.system.dao.vo.WebsiteNewsVo;
import main.com.system.service.WebsiteNewsService;
import main.com.utils.DateUtil;
/**
 * 官网新闻管理
 * @author User
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerWebsiteNewsController extends BaseController{
	public static Logger logger = Logger.getLogger(ManagerWebsiteNewsController.class);

	@Autowired
	WebsiteNewsService websiteNewsService;
	
	/**
	 * 新闻列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/websiteNewsList", method = RequestMethod.POST)
	public Result websiteNewsList(WebsiteNewsSearch search) {
		Result result = new Result();
		try {
//			if(checkUpParameter(search.getRebateRecordIds(), result, "流水唯一标识"))return result;
//			if(checkUpParameter(search.getIsPass(), result, "审核结果"))return result;
//			if(search.getIsPass() == false){
//				if(checkUpParameter(search.getRefuseRemark(), result, "拒绝理由不能为空"))return result;
//			}
			result = websiteNewsService.websiteNewsList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 新闻详情
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/websiteNewsInfo", method = RequestMethod.POST)
	public Result websiteNewsInfo(WebsiteNewsSearch search) {
		Result result = new Result();
		try {
			if(checkUpParameter(search.getNewsId(), result, "新闻ID"))return result;
			result = websiteNewsService.websiteNewsInfo(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 新闻详情
	 * @return
	 */
	@RequestMapping(value = "/websiteNewsInfoALT", method = {RequestMethod.POST,RequestMethod.GET})
	public String websiteNewsInfoALT(WebsiteNewsSearch search) {
		StringBuffer message = new StringBuffer("系统正在升级");
		try {
			if(search.getNewsId() == null || search.getNewsId() <= 0){
				message.setLength(0);           //设置StringBuffer变量的长度为0
				message = message.append("参数错误");
			}
			WebsiteNewsVo websiteNews = websiteNewsService.getById(search.getNewsId());
//			request.setAttribute("newsId", websiteNews.getNewsId());
			request.setAttribute("newsTitle", websiteNews.getNewsTitle());
//			request.setAttribute("newsDescribe", websiteNews.getNewsDescribe());
			request.setAttribute("newsContent", websiteNews.getNewsContent());
			request.setAttribute("newsMinImage", websiteNews.getNewsMinImage()==null?"":websiteNews.getNewsMinImage());//审核时间
			request.setAttribute("createDate", DateUtil.format(websiteNews.getCreateDate()));
//			request.setAttribute("isDelete", websiteNews.getIsDelete());
//			request.setAttribute("systemUsersId", websiteNews.getSystemUsersId());//审核人名称
//			request.setAttribute("systemUsersName", websiteNews.getSystemUsersName());
//			request.setAttribute("published", DateUtil.format(websiteNews.getPublished()));
//			request.setAttribute("updateDate", DateUtil.format(websiteNews.getUpdateDate()));
//			request.setAttribute("remarks", websiteNews.getRemarks()==null?"":websiteNews.getRemarks());
//			request.setAttribute("newsType", websiteNews.getNewsType());
			return "test/test";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			request.setAttribute("error", message);
//			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
			return "error/error";
		}
	}
	
	/**
	 * 新闻添加或修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/websiteNewsEdit", method = RequestMethod.POST)
	public Result websiteNewsEdit(WebsiteNewsSearch search) {
		Result result = new Result();
		try {
			if(checkUpParameter(search.getNewsContent(), result, "新闻内容"))return result;
			if(checkUpParameter(search.getPublished(), result, "发布时间"))return result;
			if(checkUpParameter(search.getNewsType(), result, "请选择类型"))return result;
			result = websiteNewsService.websiteNewsEdit(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
	
	/**
	 * 新闻添加或修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/websiteNewsDelete", method = RequestMethod.POST)
	public Result websiteNewsDelete(WebsiteNewsSearch search) {
		Result result = new Result();
		try {
			if(checkUpParameter(search.getNewsId(), result, "请选择"))return result;
			result = websiteNewsService.websiteNewsDelete(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
}
