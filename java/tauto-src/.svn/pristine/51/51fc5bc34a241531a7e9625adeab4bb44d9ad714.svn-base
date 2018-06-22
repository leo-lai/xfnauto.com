package main.com.weixinApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinApp.dao.search.ChangeCarApplySearch;
import main.com.weixinApp.dao.search.ChangeCarApproveSearch;
import main.com.weixinApp.dao.search.ChangeCarSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoChangePriceSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoUpdateStateSearch;
import main.com.weixinApp.dao.search.CreateConsumerOrderInfoSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoCreateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoUpdateSearch;
import main.com.weixinApp.dao.search.DistributeCarSearch;
import main.com.weixinApp.dao.search.QueryVinSearch;
import main.com.weixinApp.dao.search.RedistributeCarSearch;
import main.com.weixinApp.dao.search.RefuseCarChangeSearch;
import main.com.weixinApp.service.ConsumerOrderInfoService;

@Controller
@RequestMapping(value="/emInterface/employee/consumerOrderInfo")
public class ConsumerOrderInfoController extends BaseController{
	
	@Autowired
	private ConsumerOrderInfoService consumerOrderInfoService;
	
	/**
	 * 创建子单信息
	 * @param search
	 * @return
	 */
	@RequestMapping(value="/createOrderInfo")
	@ResponseBody
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "创建订购单子单")
	public Result createOrderInfo(@RequestBody CreateConsumerOrderInfoSearch search) {
		return consumerOrderInfoService.createOrderInfo(search);
	}
	
	/**
	 * 创建
	 * @param search
	 * @return
	 */
	@RequestMapping(value="/create")
	@ResponseBody
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "创建订购单子单")
	public Result create(ConsumerOrderInfoCreateSearch search) {
		return consumerOrderInfoService.create(search);
	}
	
	/**
	 * 更新
	 * @param search
	 * @return
	 */
	@RequestMapping(value="/update")
	@ResponseBody
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "更新订购单子单")
	public Result update(ConsumerOrderInfoUpdateSearch search) {
		return consumerOrderInfoService.update(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/queryVin")
	public Result queryVin(QueryVinSearch search) {
		SystemUsers user = supplementSearchSystemUsers(search.getSessionId());
		search.setCurrentUser(user);
		return consumerOrderInfoService.queryVin(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/distributeCar")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "分配订购单车辆")
	public Result distributeCar(DistributeCarSearch search) {
		return consumerOrderInfoService.distributeCar(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/redistributeCar")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "重新分配订购单车辆")
	public Result redistributeCar(RedistributeCarSearch search) {
		return consumerOrderInfoService.redistributeCar(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/changeCar")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "更改订购单车辆")
	public Result changeCar(ChangeCarSearch search) {
		return consumerOrderInfoService.changeCar(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/refuseChangeCar")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "拒绝更换订购单车辆")
	public Result refuseChangeCar(RefuseCarChangeSearch search) {
		return consumerOrderInfoService.refuseChangeCar(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/changeCarApply")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "提交订购单换车申请")
	public Result changeCarApply(ChangeCarApplySearch search) {
		return consumerOrderInfoService.changeCarApply(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/changeCarApprove")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "同意订购单换车申请")
	public Result changeCarApprove(ChangeCarApproveSearch search) {
		return consumerOrderInfoService.changeCarApprove(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateState")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "更新订购单子单状态")
	public Result updateState(ConsumerOrderInfoUpdateStateSearch search) {
		return consumerOrderInfoService.updateState(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "删除订购单子单")
	public Result delete(Integer id) {
		return consumerOrderInfoService.delete(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/changePrice")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "修改订购单车辆价格")
	public Result changePrice(ConsumerOrderInfoChangePriceSearch search) {
		return consumerOrderInfoService.changePrice(search);
	}

}
