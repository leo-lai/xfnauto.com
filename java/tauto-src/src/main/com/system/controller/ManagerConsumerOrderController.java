package main.com.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import main.com.frame.constants.ConsumerOrderState;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinApp.dao.search.ConsumerOrderSearch;
import main.com.weixinApp.dao.search.ListOrderSearch;
import main.com.weixinApp.dao.search.OrderUpdateStateSearch;
import main.com.weixinApp.service.ConsumerOrderService;
@Controller
@RequestMapping(value="/management/admin/ConsumerOrder")
public class ManagerConsumerOrderController extends BaseController{
	
	@Autowired
	private ConsumerOrderService consumerOrderService;
	
	@ResponseBody
	@RequestMapping(value = "/listOrders")
	public Result listOrders(ListOrderSearch search) {
		SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
		search.setOrgCode(user.getOrgCode());
		return consumerOrderService.listOrders(search,user,null);
	}
	
	@ResponseBody
	@RequestMapping(value="/getOrderDetail")
	public Result getOrderDetail(Integer orderId) {
		return consumerOrderService.getOrderDetail(orderId);
	}
	
	@ResponseBody
	@RequestMapping(value="/getPaymentInfo")
	public Result getPaymentInfo(Integer orderId) {
		return consumerOrderService.getPaymentInfo(orderId);
	}
	
	/**
	 * 购车单电子合同PC
	 * @param orderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getContractInfo")
	public Result getContractInfo(Integer orderId) {
//		System.out.println("访问了电子合同接口");
		return consumerOrderService.getOrderDetail(orderId);
	}
	
	/**
	 * 完成订单
	 * @param orderId
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/finishOrder")
	public Result finishOrder(Integer orderId) throws Exception {
		OrderUpdateStateSearch search = new OrderUpdateStateSearch();
		search.setOrderId(orderId);
		search.setState(ConsumerOrderState.Doned.getCode());
		return consumerOrderService.updateState(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/cancel")
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
