package main.com.logistics.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.search.LogisticsDistributionSearch;
import main.com.logistics.dao.search.LogisticsDynamicLineSearch;
import main.com.logistics.dao.vo.LogisticsDynamicLineInfoVo;
import main.com.logistics.service.LogisticsDynamicLineService;
import main.com.system.dao.po.SystemUsers;

/**
 * 物流 ----- 非专线配置
 * @author Zwen
 *
 */
@Controller
//@RequestMapping("/management/admin")
@RequestMapping("/emInterface/employee")//由于物流原型和设计多次更改，直接修改前缀，懒得挣扎
public class LogisticsDynamicLineController extends BaseController{
	public static Logger logger = Logger.getLogger(LogisticsDynamicLineController.class);
	
	@Autowired
	LogisticsDynamicLineService dynamicLineService;

	/**
	 * 非专线配置详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dynamicLineInfo")
	public Result dynamicLineInfo(LogisticsDynamicLineSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = dynamicLineService.dynamicLineInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 非专线配置编辑
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dynamicLineEdit")
	@LogPoint(logDes = "商务端物流管理模块", operateModule = "非专线配置编辑")
	public Result dynamicLineEdit(@RequestBody LogisticsDynamicLineSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = dynamicLineService.dynamicLineEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
