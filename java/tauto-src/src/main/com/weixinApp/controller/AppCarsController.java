package main.com.weixinApp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.car.dao.search.CarBrandSearch;
import main.com.car.dao.search.CarsSearch;
import main.com.customer.dao.po.UsersTmpl;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.weixinApp.service.AppCarsService;

/**
 * 小程序车大类展示
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interface/cars")
public class AppCarsController extends BaseController{
	
	public static Logger logger = Logger.getLogger(AppCarsController.class);

	@Autowired
	AppCarsService appCarsService;
	
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
			result = appCarsService.carsList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	@RequestMapping(value = "/carsInfo")
	@ResponseBody
	public Result carsInfo(CarsSearch search) {
		Result result = new Result();
		try{
			UsersTmpl users = supplementSearchaAppUser(search.getSessionId());
			if(users == null){
				result.setError(ResultCode.CODE_STATE_4002, "登录信息失效，请刷新重试");
				return result;
			}else{
				search.setUsersTmpl(users);
			}
			result = appCarsService.carsInfo(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
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
			result = appCarsService.carsBrandList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	/**
	 * 年款列表
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carsYearPatternList")
	@ResponseBody
	public Result carsYearPatternList(CarsSearch search) {
		Result result = new Result();
		try{
			result = appCarsService.carsYearPatternList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	/**
	 * 根据车系获取车大类列表
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carsFamilyList")
	@ResponseBody
	public Result carsFamilyList(CarsSearch search) {
		Result result = new Result();
		try{
			result = appCarsService.carsFamilyList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	/**
	 * 获取问题列表
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carProblemList")
	@ResponseBody
	public Result carProblemList(CarsSearch search) {
		Result result = new Result();
		try{
			result = appCarsService.carProblemList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	/**
	 * 获取基本信息
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carIntroduce")
	@ResponseBody
	public Result carIntroduce(CarsSearch search) {
		Result result = new Result();
		try{
			result = appCarsService.carIntroduce(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	/**
	 * 获取参数配置
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carParameter")
	@ResponseBody
	public Result carParameter(CarsSearch search) {
		Result result = new Result();
		try{
			result = appCarsService.carParameter(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 购车计算器全款
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/fullPayment")
	@ResponseBody
	public Result fullPayment(CarsSearch search) {
		Result result = new Result();
		try{
			result = appCarsService.fullPayment(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 购车计算器贷款
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/loanPayment")
	@ResponseBody
	public Result loanPayment(CarsSearch search) {
		Result result = new Result();
		try{
			result = appCarsService.loanPayment(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统正在升级，请稍后再试");
		}
		return result;
	}
}
