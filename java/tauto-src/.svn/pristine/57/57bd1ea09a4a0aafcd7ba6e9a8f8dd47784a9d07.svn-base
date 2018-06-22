package main.com.weixinApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.domain.Result;
import main.com.weixinApp.dao.search.ConsumerOrderPaymentCreateSearch;
import main.com.weixinApp.service.ConsumerOrderPaymentService;

@Controller
@RequestMapping(value="/emInterface/employee/consumerOrderPayment")
public class ConsumerOrderPaymentController {
	
	private ConsumerOrderPaymentService consumerOrderPaymentService;
	
	@ResponseBody
	@RequestMapping(value="/create")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "订购单支付")
	public Result create(ConsumerOrderPaymentCreateSearch search) {
		return consumerOrderPaymentService.create(search);
	}

}
