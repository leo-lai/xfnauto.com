package main.com.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.search.StockStorageSearch;
import main.com.stock.service.StockStorageService;
import main.com.system.dao.po.SystemUsers;

/**
 * 二级组织入库管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerStockStorageController extends BaseController {

public static Logger logger = Logger.getLogger(ManagerStockStorageController.class);
	
	@Autowired
	StockStorageService storageService;
	
	/**
	 * 获取入库单记录列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storageList")
	public Result storageList(StockStorageSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = storageService.storageList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *二级入库编辑
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storageEdit")
	@LogPoint(logDes = "二级入库模块", operateModule = "编辑入库单")
	public Result storageEdit(StockStorageSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = storageService.storageEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 *二级入库新建
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storageInsert")
	@LogPoint(logDes = "二级入库模块", operateModule = "新建入库单")
	public Result storageInsert(StockStorageSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = storageService.storageInsert(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 *获取二级库存单详细信息
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storageInfo")
	public Result storageInfo(StockStorageSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = storageService.storageInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 *根据二级库存单获取具体车辆列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storageCarList_old")
	public Result storageCarList_old(StockCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = storageService.storageCarList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 *根据二级库存单获取具体车辆列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storageCarList")
	public Result storageCarList(StockCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = storageService.storageCarList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 *删除二级库存单的具体车辆
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storageCarDelete")
	@LogPoint(logDes = "二级入库模块", operateModule = "删除入库单里具体车辆")
	public Result storageCarDelete(StockCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = storageService.storageCarDelete(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *编辑二级库存单的具体车辆（添加或修改）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storageCarEdit")
	@LogPoint(logDes = "二级入库模块", operateModule = "添加或修改入库单里具体车辆")
	public Result storageCarEdit(StockCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = storageService.storageCarEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
}
