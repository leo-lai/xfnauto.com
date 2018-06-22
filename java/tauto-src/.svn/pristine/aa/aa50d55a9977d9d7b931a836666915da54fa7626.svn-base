package main.com.system.controller;

import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.search.StockOrderSearch;
import main.com.stock.service.StockOrderService;
import main.com.system.dao.po.SystemUsers;
import net.sf.json.JSONObject;

/**
 * 三级向二级订车
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerStockOrderController extends BaseController {

	public static Logger logger = Logger.getLogger(ManagerStockOrderController.class);

	@Autowired
	StockOrderService orderService;
	
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
			result = orderService.stockOrderList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *获取客户订车单列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerOrderList")
	public Result customerOrderList(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.customerOrderList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *出库客户订车单车辆
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerOrderStorageOut")
	public Result customerOrderStorageOut(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.customerOrderStorageOut(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *出库客户车辆前置
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/customerOrderStorageOutBefor")
	public Result customerOrderStorageOutBefor(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.customerOrderStorageOutBefor(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *查看订单详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderInfo")
	public Result stockOrderInfo(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.stockOrderInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 *获取车辆定金
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/carDepositPrice")
	public Result carDepositPrice(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.carDepositPrice(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 *下订单
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderCreate")
	public Result stockOrderCreate(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.stockOrderCreate(search,result,users);
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
	public Result stockOrderCancel(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.stockOrderCancel(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *修改订单尾款
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderchangebalancePrice")
	public Result stockOrderchangebalancePrice(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.stockOrderchangebalancePrice(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *通知有车 前置
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderNoticeBefor")
	public Result stockOrderNoticeBefor(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.stockOrderNoticeBefor(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 *通知有车
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderNotice")
	public Result stockOrderNotice(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.stockOrderNotice(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 出库车辆 前置
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderStorageOutBefor")
	public Result stockOrderStorageOutBefor(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.stockOrderStorageOutBefor(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 * 出库车辆
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderStorageOut")
	public Result stockOrderStorageOut(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.stockOrderStorageOut(search,result,users);
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
	public Result stockOrderSign(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.stockOrderSign(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	
	
	/**
	 *订单支付（定金和尾款）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stockOrderPay")
	public Result stockOrderPay(StockOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			result = orderService.stockOrderPay(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	/**
	 *订单支付（定金和尾款）
	 * @param menuSearch
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/allInPay/resultPay")
//	public String allInPayresultPay() {
//		PrintWriter out = null;
//		try{
//			System.out.println("************收到通联网关支付通知***************");
//			out =response.getWriter();//提取出来以后报错，所以每次使用都生成一个
//			JSONObject obj = new JSONObject();
//			obj.put("success", true);
//    		obj.put("resultCode", ResultCode.CODE_STATE_200);
//			obj.put("message", "成功");
//			out.println(obj);
//			out.flush();
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			out.close();
//		}
//		return "";
//	}
}
