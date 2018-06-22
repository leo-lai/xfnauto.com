package main.com.system.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.customer.dao.search.CustomerUsersSearch;
import main.com.customer.service.CustomerOrderService;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.stock.service.StockOrderService;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.service.OrganizationService;

/**
 * 首页统计
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerIndexController extends BaseController{

	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	StockOrderService orderService;
	
	@Autowired
	CustomerOrderService customerOrderService;
	
	/**
	 * 首页
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public Result addCustomerUsers(CustomerUsersSearch search) {
		Result result = new Result();
		try {
			SystemUsers users = supplementSearchSystemUsers(search.getSessionId());
			Organization organization = organizationService.getOrganization(users);
			Map<String,Object> params = new HashMap<String, Object>();
			Map<String, Object> organizationMap = new HashMap<String,Object>();
			if(organization == null) {
				organizationMap.put("address", "");
				organizationMap.put("shortName", "");
				organizationMap.put("imageUrl", "");
				organizationMap.put("orgLevel", "");
			}else {
				organizationMap.put("address", organization.getProvinceName() + organization.getCityName() + organization.getAreaName()+organization.getAddress());
				organizationMap.put("shortName", organization.getShortName());
				organizationMap.put("imageUrl", organization.getImageUrl());
				organizationMap.put("orgLevel", organization.getOrgLevel());
			}
			params.put("organization", organizationMap);//门店
			Map<String, Object> customerOrderMap = customerOrderService.getMapCustomerOrder(users);
			params.put("customerOrderMap", customerOrderMap);//客户管理
			Map<String, Object> orderMap = orderService.getMapNumber(users);
			params.put("stockOrder", orderMap);//订购车辆
			result.setOK(ResultCode.CODE_STATE_200, "",params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统请求出错",e);
			result.setError(ResultCode.CODE_STATE_4004, "系统繁忙，请稍后再试！");
		}
		return result;
	}
}
