package main.com.weixinApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.search.BaseSearch;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinApp.dao.search.ConsumerOrderSearch;
import main.com.weixinApp.dao.search.ConsumerOrderUpdateLogisticsInfoSearch;
import main.com.weixinApp.dao.search.CreateConsumerOrderSearch;
import main.com.weixinApp.dao.search.ListOrderSearch;
import main.com.weixinApp.dao.search.OrderUpdateSearch;
import main.com.weixinApp.dao.search.OrderUpdateStateSearch;
import main.com.weixinApp.service.ConsumerOrderService;
import main.com.weixinHtml.dao.search.ShareMaterialInfoSearch;
@Controller
@RequestMapping(value="/emInterface/employee/consumerOrder")
public class ConsumerOrderController extends BaseController{
	
	@Autowired
	private ConsumerOrderService consumerOrderService;
	
	/**
	 * 客户经理新建订购单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/createOrder")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "客户经理新建订购单")
	public Result createOrder(@RequestBody CreateConsumerOrderSearch search) {
		Result result = new Result();
		SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
		search.setCreatorId(user.getUsersId());
		search.setCreator(user.getRealName());
		search.setCreatorName(user.getUserName());
		try {
			return consumerOrderService.createOrderShop(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("系统正在升级...");
			return result; 
		}
		//consumerOrderService.createOrder(search);
	}
	
	/**
	 * 客户经理新建订购单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/createOrderShop")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "客户经理由商城预订单新建订购单")
	public Result createOrderShop(@RequestBody CreateConsumerOrderSearch search) {
		Result result = new Result();
		SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
		search.setCreatorId(user.getUsersId());
		search.setCreator(user.getRealName());
		try {
			return consumerOrderService.createOrderShop(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("系统正在升级...");
			return result; 
		}		
	}
	
	@ResponseBody
	@RequestMapping(value = "/listOrders")
	public Result listOrders(ListOrderSearch search) {
		SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
		search.setCurrentOrgId(user.getOrgId());
		return consumerOrderService.listOrders(search,user);
	}
	
	@ResponseBody
	@RequestMapping(value="/getOrderDetail")
	public Result getOrderDetail(Integer orderId) {
		return consumerOrderService.getOrderDetail(orderId);
	}
	
	@ResponseBody
	@RequestMapping(value="/getContractInfo")
	public Result getContractInfo(Integer orderId) {
//		System.out.println("访问了电子合同接口");
		return consumerOrderService.getOrderDetail(orderId);
	}
	
	/**
	 * 获取合同截图
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getContractInfoImage")
	public Result getContractInfoImage(Integer orderId) {
		Result result = new Result();
		try {
			result = consumerOrderService.getContractInfoImage(orderId,result,request,response);			
		} catch (Exception e) { 
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 三级素材分享
	 * @param menuSearch
	 * @return
	 */
	@RequestMapping(value = "/getContractInfoImageHtml")
	public String getContractInfoImageHtml(Integer orderId) {
		Result result = new Result();
		try {
			consumerOrderService.getContractInfoImageHtml(orderId,result,request,response);			
		} catch (Exception e) { 
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return "contractInfo";
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "更新订购单")
	public Result updateOrder(OrderUpdateSearch search) {
		return consumerOrderService.update(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "删除订购单")
	public Result delete(Integer id) {
		return consumerOrderService.delete(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateState")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "更新订购单状态")
	public Result updateState(OrderUpdateStateSearch search) throws Exception {
		SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
		search.setCurrentUser(user);
		return consumerOrderService.updateState(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/getPaymentInfo")
	public Result getPaymentInfo(Integer orderId) {
		return consumerOrderService.getPaymentInfo(orderId);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateLogisticsInfo")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "更新订购单子单")
	public Result updateLogisticsInfo(ConsumerOrderUpdateLogisticsInfoSearch search) {
		return consumerOrderService.updateLogisticsInfo(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/auditList")
	public Result auditList(BaseSearch search) {
		return consumerOrderService.auditList(search);
	}
	
	/**
	 * 取消订单
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/cancel")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "取消订购单")
	public Result cancel(Integer id) {
		Result result = new Result();
		try {
			return consumerOrderService.cancel(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("系统正在升级....");
			return result;
		}
	}
	
	/**
	 * 退款
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/countermand")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "订购单退款")
	public Result countermand(ConsumerOrderSearch search) {
		Result result = new Result();
		try {
			return consumerOrderService.countermand(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("系统正在升级....");
			return result;
		}
	}
	
	/**
	 * 退款审核
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/countermandExamine")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "订购单退款审核")
	public Result countermandExamine(ConsumerOrderSearch search) {
		Result result = new Result();
		try {
			return consumerOrderService.countermandExamine(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("系统正在升级....");
			return result;
		}
	}
}
