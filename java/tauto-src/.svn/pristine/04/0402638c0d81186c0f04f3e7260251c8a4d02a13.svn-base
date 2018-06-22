package main.com.weixinApp.service.impl;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.exception.BusinessException;
import main.com.frame.constants.ConsumerOrderState;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.po.ConsumerOrder;
import main.com.stock.dao.po.ConsumerOrderPayment;
import main.com.utils.ConvertUtil;
import main.com.weixinApp.dao.dao.ConsumerOrderDao;
import main.com.weixinApp.dao.dao.ConsumerOrderPaymentDao;
import main.com.weixinApp.dao.search.ConsumerOrderPaymentCreateSearch;
import main.com.weixinApp.dao.search.OrderUpdateStateSearch;
import main.com.weixinApp.service.ConsumerOrderPaymentService;
import main.com.weixinApp.service.ConsumerOrderService;
@Service
public class ConsumerOrderPaymentServiceImpl extends BaseServiceImpl<ConsumerOrderPayment> implements ConsumerOrderPaymentService{

	@Autowired
	private ConsumerOrderPaymentDao consumerOrderPaymentDao;
	
	@Autowired
	private ConsumerOrderService consumerOrderService;
	
	@Autowired
	private ConsumerOrderDao consumerOrderDao;
	
	@Override
	protected BaseDao<ConsumerOrderPayment> getBaseDao() {
		// TODO Auto-generated method stub
		return consumerOrderPaymentDao;
	}
	
	@Override
	@Transactional
	public Result create(ConsumerOrderPaymentCreateSearch search) {
		// TODO Auto-generated method stub
		
		ConsumerOrder order = consumerOrderDao.selectById(search.getOrderId());
		if(order == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		ConsumerOrderPayment payment = new ConsumerOrderPayment();
		ConvertUtil.objectToObject(search, payment);
		payment.setCreateTime(new Date());
		
		OrderUpdateStateSearch s = new OrderUpdateStateSearch();
		s.setOrderId(order.getId());
		
		if(Objects.equals(order.getState(), ConsumerOrderState.DepositPaying.getCode())) {
			if(Objects.equals(order.getOrderType(), ConsumerOrderState.Traffic.getCode())) {//如果是炒车单				
				s.setState(ConsumerOrderState.Doned.getCode());//炒车单直接到上传票证的状态
			}else {
				s.setState(ConsumerOrderState.CarDistributing.getCode());
			}
			payment.setType(1);//定金
		}
		if(Objects.equals(order.getState(), ConsumerOrderState.FinalPricePaying.getCode())) {
			s.setState(ConsumerOrderState.StockOuting.getCode());
			payment.setType(2);//尾款
		}
		
		if(consumerOrderPaymentDao.insert(payment)) {
			try {
				consumerOrderService.updateState(s);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new BusinessException(ResultCode.CODE_STATE_4005, "更新订单状态失败");
			}
			return new Result(ResultCode.CODE_STATE_200,true,"新增成功");
		}
		return new Result(ResultCode.CODE_STATE_200,false,"新增失败");
	}
	
}
