package main.com.allInPay.service;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import main.com.allInPay.dao.po.AllInPayWeb;
import main.com.frame.service.BaseService;

public interface AllInPayWebService extends BaseService<AllInPayWeb>{

	/*
	 * 回调
	 */
	String resultPay(TreeMap<String, String> params)throws Exception;
	
	
	String resultPay(AllInPayWeb allInPay)throws Exception;

	/**
	 * 根据回调或者查询返回写入支付信息
	 * @param issuccess
	 * @param allInPay
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public String checkPay(Boolean issuccess,AllInPayWeb allInPay,String result)throws Exception;
	
	/**
	 * 把返回的Map转化为对象
	 * @param map
	 * @param allInPay
	 */
	public void setValue(TreeMap<String, String> map,AllInPayWeb allInPay);
	
	/**
	 * 把返回的Map转化为对象
	 * @param map
	 * @param allInPay
	 */
	public void setValue(Map<String, String> map,AllInPayWeb allInPay);
	
	/**
	 * 网关支付参数拼装
	 * @param orderCode
	 * @param pickupUrl
	 * @param payUser
	 * @param productName
	 * @param quantity
	 * @param amount
	 * @param unitAmount
	 * @param payDate
	 * @return
	 * @throws Exception
	 */
//	public Result setPayInfo(String orderCode,String pickupUrl,UserVo payUser,String productName,String quantity
//			,Float amount,Float unitAmount,Date payDate) throws Exception;

	/**
	 * 根据支付信息获取MD5
	 * @param allInPay
	 * @return
	 * @throws Exception
	 */
	public String selectMD5Key(AllInPayWeb allInPay)throws Exception;
}
