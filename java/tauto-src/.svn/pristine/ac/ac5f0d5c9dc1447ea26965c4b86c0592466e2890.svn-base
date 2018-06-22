package main.com.weixinHtml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.weixinApp.service.EmployeeOrderService;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.search.ShopAdvanceOrderSearch;
import main.com.weixinHtml.dao.search.ShopApplyLoanSearch;
import main.com.weixinHtml.dao.search.ShopGoodsCarsActivitySearch;
import main.com.weixinHtml.service.ShopAdvanceOrderService;

/**
 * 商城预定下单
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/interfaceShop/advanceOrder")
public class ShopAdvanceOrderController extends BaseController{

	@Autowired
	ShopAdvanceOrderService advanceOrderService; 
	
	@Autowired
	EmployeeOrderService orderService;
	
	/**
	 * 预订单编辑
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/advanceOrderEdit")
	public Result advanceOrderEdit(ShopAdvanceOrderSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = advanceOrderService.advanceOrderEdit(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 预订单列表 商务端
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orgAdvanceOrderList")
	public Result orgAdvanceOrderList(ShopAdvanceOrderSearch search) {
		Result result = new Result();
		try {
			SystemUsersVo users = supplementSearchSystemUsers(search.getSessionId());
			result = advanceOrderService.orgAdvanceOrderList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 我的预订单列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myOrgAdvanceOrderList")
	public Result myOrgAdvanceOrderList(ShopAdvanceOrderSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = advanceOrderService.myOrgAdvanceOrderList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 我的订单或订车单列表
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myOrderList")
	public Result myOrderList(ShopAdvanceOrderSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = advanceOrderService.myOrderList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 我的订单的支付记录
	 */
	@ResponseBody
	@RequestMapping(value = "/myOrderPaymentList")
	public Result myOrderList(CustomerOrderSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = advanceOrderService.myOrderPaymentList(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 预订单取消删除
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orgAdvanceOrderDel")
	public Result orgAdvanceOrderDel(ShopAdvanceOrderSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = advanceOrderService.orgAdvanceOrderDel(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 预订详情
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orgAdvanceOrderInfo")
	public Result orgAdvanceOrderInfo(ShopAdvanceOrderSearch search) {
		Result result = new Result();
		try {
			result = advanceOrderService.orgAdvanceOrderInfo(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 预订单支付定金
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/orgAdvanceOrderInpay")
	public Result orgAdvanceOrderInpay(ShopAdvanceOrderSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = advanceOrderService.orgAdvanceOrderInpay(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 用户实际订单详情
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/myOrderInfo", method = RequestMethod.POST)
	public Result customerOrderInfo(ShopAdvanceOrderSearch search) {
		Result result = new Result();
		try {
			ShopUsers users = supplementSearchShopUser(search.getSessionId());
			result = advanceOrderService.myOrderInfo(search,result,users);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}

}
