package main.com.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.car.dao.search.CarColourImageSearch;
import main.com.car.dao.search.CarSupplierSearch;
import main.com.car.service.CarSupplierService;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.RoleSearch;

/**
 * 供应商管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerCarSupplierController extends BaseController{

	public static Logger logger = Logger.getLogger(ManagerCarSupplierController.class);

	@Autowired
	CarSupplierService supplierService;
	
	/**
	 * 供应商列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/supplierList", method = RequestMethod.POST)
	@ResponseBody
	public Result supplierList(CarSupplierSearch search) {
		Result result = new Result();
		try{
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			result = supplierService.supplierList(search,result,systemUsers);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	/**
	 * 供应商列表（下拉）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/supplierListList", method = RequestMethod.POST)
	@ResponseBody
	public Result supplierListList(CarSupplierSearch search) {
		Result result = new Result();
		try{
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			result = supplierService.supplierListList(search,result,systemUsers);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 添加供应商
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/supplierEdit")
	@ResponseBody
	@LogPoint(logDes = "供应商管理模块", operateModule = "添加或修改供应商")
	public Result supplierEdit(CarSupplierSearch search) {
		Result result = new Result();
		try{
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			result = supplierService.supplierEdit(search,result,systemUsers);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 删除供应商
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/supplierDelete")
	@ResponseBody
	@LogPoint(logDes = "供应商管理模块", operateModule = "删除供应商")
	public Result supplierDelete(CarSupplierSearch search) {
		Result result = new Result();
		try{
			result = supplierService.supplierDelete(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
}
