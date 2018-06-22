package main.com.weixinApp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.car.dao.search.CarBrandSearch;
import main.com.car.dao.search.CarColourSearch;
import main.com.car.dao.search.CarFamilySearch;
import main.com.car.dao.search.CarInteriorSearch;
import main.com.car.dao.search.CarsSearch;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.search.StockOrderSearch;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinApp.service.EmployeeCarService;

/**
 * 员工端 -- 车
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/emInterface/employee")
public class EmployeeCarController extends BaseController{
	public static Logger logger = Logger.getLogger(EmployeeCarController.class);

	@Autowired
	EmployeeCarService employeeService;
	
	/**
	 *车辆库存车辆列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockCarList")
	public Result storageCarList(StockCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.stockCarList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 获取车辆品牌列表(下拉)
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carsBrandList")
	@ResponseBody
	public Result carsBrandList(CarBrandSearch search) {
		Result result = new Result();
		try{
			result = employeeService.carsBrandList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 根据品牌获取车系列表(下拉)
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carsFamilyList")
	@ResponseBody
	public Result carsFamilyList(CarFamilySearch search) {
		Result result = new Result();
		try{
			result = employeeService.carsFamilyList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取车辆类型列表（下拉无分页列表）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/carsListList")
	public Result carsListList(CarsSearch search) {
		Result result = new Result();
		try {
			result = employeeService.carsListList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 内饰下拉
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carInteriorList")
	@ResponseBody
	public Result carInteriorGetByBrand(CarInteriorSearch search) {
		Result result = new Result();
		try{
			result = employeeService.carInteriorList(search,result);
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
	@RequestMapping(value = "/carColourList")
	@ResponseBody
	public Result carColourList(CarColourSearch search) {
		Result result = new Result();
		try{
			result = employeeService.carColourList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取具体库存车辆
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockCarInfo")
	public Result stockCarInfo(StockCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.stockCarInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *获取库存订单列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderList")
	public Result stockOrderList(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.stockOrderList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 三级向二级订车
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderCreate")
	@LogPoint(logDes = "员工端库存管理模块", operateModule = "编辑订车单")
	public Result stockOrderCreate(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.stockOrderCreate(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *查看向上级订车详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderInfo")
	public Result stockOrderInfo(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.stockOrderInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *取消订单
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderCancel")
	@LogPoint(logDes = "员工端库存管理模块", operateModule = "取消订单")
	public Result stockOrderCancel(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.stockOrderCancel(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *签收并自动入库
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderSign")
	@LogPoint(logDes = "员工端库存管理模块", operateModule = "签收订车单并自动入库")
	public Result stockOrderSign(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.stockOrderSign(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
