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
import main.com.stock.service.StockCarService;
import main.com.stock.service.StockStorageService;
import main.com.system.dao.po.SystemUsers;

/**
 * 库存车辆管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerStockCarController extends BaseController {

	public static Logger logger = Logger.getLogger(ManagerStockCarController.class);

	@Autowired
	StockCarService stockCarService;
	
	/**
	 *根据车辆库存车辆列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockCarList")
	public Result storageCarList(StockCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = stockCarService.stockCarList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *根据车辆库存车辆（添加或修改）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockCarEdit")
	@LogPoint(logDes = "库存管理模块", operateModule = "修改库存车辆的定金/优惠/是否线上展示")
	public Result storageCarEdit(StockCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = stockCarService.stockCarEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 *车辆库存车辆查看明细
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockCarInfo")
	public Result storageCarInfo(StockCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = stockCarService.stockCarInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
