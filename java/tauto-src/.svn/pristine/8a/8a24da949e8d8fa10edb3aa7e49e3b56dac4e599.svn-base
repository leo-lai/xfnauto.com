package main.com.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.car.dao.search.CarBrandSearch;
import main.com.car.dao.search.CarColourSearch;
import main.com.car.service.CarColourService;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;

/**
 * 车辆车身颜色和内饰颜色管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerCarColourController extends BaseController{

	public static Logger logger = Logger.getLogger(ManagerCarColourController.class);
	
	@Autowired
	CarColourService carColourService;
	
	/**
	 * 获取车辆品牌颜色列表(下拉)
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carColourGetByBrand")
	@ResponseBody
	public Result carColourGetByBrand(CarColourSearch search) {
		Result result = new Result();
		try{
			result = carColourService.carColourGetBrand(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取车辆品牌颜色列表(下拉)
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carColourGetByCars")
	@ResponseBody
	public Result carColourGetByCars(CarColourSearch search) {
		Result result = new Result();
		try{
			result = carColourService.carColourGetByCars(search,result);
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
	@RequestMapping(value = "/carColourEdit")
	@ResponseBody
	@LogPoint(logDes = "车辆车身颜色管理模块", operateModule = "修改车身颜色配置")
	public Result carColourEdit(CarColourSearch search) {
		Result result = new Result();
		try{
			result = carColourService.carColourEdit(search,result);
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
	@RequestMapping(value = "/carColourDelete")
	@ResponseBody
	@LogPoint(logDes = "车辆车身颜色管理模块", operateModule = "删除车身颜色配置")
	public Result carColourDelete(CarColourSearch search) {
		Result result = new Result();
		try{
			result = carColourService.carColourDelete(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
}
