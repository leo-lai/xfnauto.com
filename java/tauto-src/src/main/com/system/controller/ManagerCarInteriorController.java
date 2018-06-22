package main.com.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.car.dao.search.CarInteriorSearch;
import main.com.car.service.CarInteriorService;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;

/**
 * 品牌的内饰管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerCarInteriorController extends BaseController{

	public static Logger logger = Logger.getLogger(ManagerCarInteriorController.class);
	
	@Autowired
	CarInteriorService carInteriorService;
	
	/**
	 * 获取车辆品牌列表(下拉)
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carInteriorGetByBrand")
	@ResponseBody
	public Result carInteriorGetByBrand(CarInteriorSearch search) {
		Result result = new Result();
		try{
			result = carInteriorService.carInteriorGetByBrand(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 编辑
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carInteriorEdit")
	@ResponseBody
	@LogPoint(logDes = "品牌的内饰管理模块", operateModule = "编辑内饰数据")
	public Result carInteriorEdit(CarInteriorSearch search) {
		Result result = new Result();
		try{
			result = carInteriorService.carInteriorEdit(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 删除
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carInteriorDelete")
	@ResponseBody
	@LogPoint(logDes = "品牌的内饰管理模块", operateModule = "删除内饰数据")
	public Result carInteriorDelete(CarInteriorSearch search) {
		Result result = new Result();
		try{
			result = carInteriorService.carInteriorDelete(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
}
