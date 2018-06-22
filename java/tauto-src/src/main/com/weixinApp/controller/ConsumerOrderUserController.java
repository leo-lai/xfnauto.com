package main.com.weixinApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.domain.Result;
import main.com.weixinApp.dao.search.ConsumerOrderUserCreateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderUserUpdateSearch;
import main.com.weixinApp.service.ConsumerOrderUserService;

@Controller
@RequestMapping(value="/emInterface/employee/consumerOrderUser")
public class ConsumerOrderUserController {
	
	@Autowired
	private ConsumerOrderUserService consumerOrderUserService;
	
	
	@ResponseBody
	@RequestMapping(value="/update")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "修改订购单第三方客户信息")
	public Result update(ConsumerOrderUserUpdateSearch search) {
		return consumerOrderUserService.update(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/create")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "创建订购单第三方客户信息")
	public Result create(ConsumerOrderUserCreateSearch search) {
		return consumerOrderUserService.create(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "删除订购单第三方客户信息")
	public Result delete(Integer id) {
		return consumerOrderUserService.delete(id);
	}
	
}
