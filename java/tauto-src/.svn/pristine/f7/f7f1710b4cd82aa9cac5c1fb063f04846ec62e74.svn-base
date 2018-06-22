package main.com.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.domain.Result;
import main.com.weixinApp.dao.search.ConsumerOrderPaymentCreateSearch;
import main.com.weixinApp.service.ConsumerOrderPaymentService;

@Controller
@RequestMapping(value="/management/admin/ConsumerOrderPayment")
public class ManagerConsumerOrderPaymentController {

	@Autowired
	private ConsumerOrderPaymentService consumerOrderPaymentService;
	
	@ResponseBody
	@RequestMapping(value="/create")
	public Result create(ConsumerOrderPaymentCreateSearch search) {
		return consumerOrderPaymentService.create(search);
	}
}
