package main.com.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.car.dao.search.CarBrandSearch;
import main.com.car.dao.search.CarExpectWaySearch;
import main.com.car.dao.search.CarFamilySearch;
import main.com.car.dao.search.CarsSearch;
import main.com.car.dao.search.CarsStyleSearch;
import main.com.car.service.CarsService;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.MenuSearch;

/**
 * 车大类
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerCarsController extends BaseController{
	
	public static Logger logger = Logger.getLogger(ManagerCarsController.class);

	@Autowired
	CarsService carsService;
	
	/**
	 * 获取车辆类型列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/carsList")
	public Result carsList(CarsSearch search) {
		Result result = new Result();
		try {
			result = carsService.carsList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}

	@RequestMapping(value = "/carsEdit")
	@ResponseBody
	@LogPoint(logDes = "后台车型管理模块", operateModule = "添加或修改车大类")
	public Result carsEdit(CarsSearch search) {
		Result result = new Result();
		try{
			result = carsService.carsEdit(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/carsDelete")
	@ResponseBody
	@LogPoint(logDes = "后台车型管理模块", operateModule = "删除车大类")
	public Result carsDelete(CarsSearch search) {
		Result result = new Result();
		try{
			result = carsService.carsDelete(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/carsInfo")
	@ResponseBody
	@LogPoint(logDes = "后台车型管理模块", operateModule = "删除车大类")
	public Result carsInfo(CarsSearch search) {
		Result result = new Result();
		try{
			result = carsService.carsInfo(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 添加品牌
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carsBrandEdit")
	@ResponseBody
	@LogPoint(logDes = "后台车型管理模块", operateModule = "添加或修改车辆品牌")
	public Result carsBrandEdit(CarBrandSearch search) {
		Result result = new Result();
		try{
			result = carsService.carsBrandEdit(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 添加车系
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carsFamilyEdit")
	@ResponseBody
	@LogPoint(logDes = "后台车型管理模块", operateModule = "添加或修改车辆车系")
	public Result carsFamilyEdit(CarFamilySearch search) {
		Result result = new Result();
		try{
			result = carsService.carsFamilyEdit(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 添加级别
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carsStyleEdit")
	@ResponseBody
	@LogPoint(logDes = "后台车型管理模块", operateModule = "添加或修改车型")
	public Result carsStyleEdit(CarsStyleSearch search) {
		Result result = new Result();
		try{
			result = carsService.carsStyleEdit(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
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
			result = carsService.carsBrandList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取车辆品牌列表（分页）
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carsBrandPageList")
	@ResponseBody
	public Result carsBrandPageList(CarBrandSearch search) {
		Result result = new Result();
		try{
			result = carsService.carsBrandPageList(search,result);
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
			result = carsService.carsFamilyList(search,result);
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
			result = carsService.carsListList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	
	/**
	 * 获取车等级（高中低配列表）（下拉）
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carsStyleList")
	@ResponseBody
	@LogPoint(logDes = "后台车型管理模块", operateModule = "添加或修改车型")
	public Result carsStyleList(CarsStyleSearch search) {
		Result result = new Result();
		try{
			result = carsService.carsStyleList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 根据品牌获取车系列表（带分页）
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carsFamilyListPage")
	@ResponseBody
	public Result carsFamilyListPage(CarFamilySearch search) {
		Result result = new Result();
		try{
			result = carsService.carsFamilyListPage(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 获取购车方式（下拉无分页列表）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/carExpectWayList")
	public Result carExpectWayList(CarExpectWaySearch search) {
		Result result = new Result();
		try {
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			search.setOrgCode(systemUsers.getOrgCode());
			result = carsService.carExpectWayList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
