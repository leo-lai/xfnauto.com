package main.com.weixinApp.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.car.dao.search.CarSupplierSearch;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.search.StockStorageSearch;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemWarehouseSearch;
import main.com.weixinApp.service.EmployeeStockStorageService;

/**
 * 员工端 ------- 二级入库管理
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/emInterface/employee")
public class EmployeeStockStorageController extends BaseController{
	public static Logger logger = Logger.getLogger(EmployeeStockStorageController.class);

	@Autowired
	EmployeeStockStorageService employeeService;
	
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
			result = employeeService.storageList(search,result,users);
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
	@LogPoint(logDes = "商务端入库模块", operateModule = "编辑入库单")
	public Result storageEdit(StockStorageSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.storageEdit(search,result,users);
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
			result = employeeService.storageInfo(search,result,users);
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
			result = employeeService.storageCarList(search,result,users);
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
			result = employeeService.storageCarDelete(search,result,users);
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
			result = employeeService.storageCarEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 组织仓库列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationWarehouseList", method = RequestMethod.POST)
	public Result organizationWarehouseList(SystemWarehouseSearch search) {
		Result result = new Result();
		try {
			SystemUsers systemUsers = this.supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.organizationWarehouseList(search,result,systemUsers);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
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
			result = employeeService.supplierListList(search,result,systemUsers);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 *删除二级库存单
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storageDelete")
	@LogPoint(logDes = "二级入库模块", operateModule = "删除入库单")
	public Result storageDelete(StockCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.storageDelete(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *全部确认入库
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/storageOverSure")
	@LogPoint(logDes = "二级入库模块", operateModule = "确认已全部入库")
	public Result storageOverSure(StockCarSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = employeeService.storageOverSure(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
}
