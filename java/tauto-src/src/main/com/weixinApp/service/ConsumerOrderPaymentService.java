package main.com.weixinApp.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.stock.dao.po.ConsumerOrderPayment;
import main.com.weixinApp.dao.search.ConsumerOrderPaymentCreateSearch;

public interface ConsumerOrderPaymentService extends BaseService<ConsumerOrderPayment>{
	
	/**
	 * 新增
	 * @param search
	 * @return
	 */
	Result create(ConsumerOrderPaymentCreateSearch search);

}
