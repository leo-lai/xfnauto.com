package main.com.allInPay.service;

import java.util.TreeMap;

import main.com.allInPay.dao.po.AllinpayWechatBill;
import main.com.frame.service.BaseService;

public interface AllInPayWeChatAppService extends BaseService<AllinpayWechatBill>{
	/**
	 * 把返回的Map转化为对象
	 * @param map
	 * @param allInPay
	 */
	public void setValue(TreeMap<String, String> map,AllinpayWechatBill allInPay);

	/**
	 * 支付回调
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String resultPay(TreeMap<String, String> params)throws Exception;
}
