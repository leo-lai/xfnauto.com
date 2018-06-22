/**
 * $Id: PayUtils.java Nov 20, 2014 2:25:53 PM hdp
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package main.com.utils.weixin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import main.com.utils.StringUtil;
import main.com.utils.Value;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 微信支付工具
 * <p>创建时间: Nov 20, 2014 2:25:53 PM</p>
 * @author <a href="mailto:hongdanping@163.com">hdp</a>
 * @version V1.0
 * @since V1.0
 */
public final class PayUtils {
	/**
	 * 日志
	 */
	private static Logger LOG = LoggerFactory.getLogger(PayUtils.class);
	
	public static final String JSAPI = "JSAPI";
	
	private PayUtils() {
		throw new RuntimeException("can't init");
	}
	
	/**
	 * 	作用：产生随机字符串，不长于32位
	 */
	public static String createNoncestr() {
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}
	
	/**
	 * 	作用：格式化参数，签名过程需要使用
	 */
	private static String formatBizQueryParaMap(Map<String, String> paraMap, boolean urlencode) {
		StringBuilder sb = new StringBuilder();
		TreeMap<String, String> sortMap = new TreeMap<String, String>(paraMap);
		if (urlencode) {
			try {
				for (String key : sortMap.keySet()) {
					sb.append(key).append("=").append(URLEncoder.encode(sortMap.get(key), "UTF-8")).append("&");
				}
			} catch (UnsupportedEncodingException e) {
				LOG.error(e.getMessage(), e);
			}
		} else {
			for (String key : sortMap.keySet()) {
				sb.append(key).append("=").append(sortMap.get(key)).append("&");
			}
		}
		
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	
	/**
	 * 	作用：生成签名
	 */
	public static String getSign(Map<String, String> params) {
		StringBuilder sb = new StringBuilder();
		sb.append(formatBizQueryParaMap(params, false));
		//签名步骤二：在string后加入KEY
		sb.append("&key=").append(Value.Weixin.KEY);
		//签名步骤三：MD5加密
		String result = null;
		try {
			result = MD5.getMD5(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e.getMessage(), e);
			return result;
		}
		//签名步骤四：所有字符转为大写
		return result.toUpperCase();
	}
	
	public static String getSign(String nonceStr, String outTradeNo, String body, String totalFee, String notifyUrl, String tradeType, String openid, String ip,
			String appId,String mchId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("out_trade_no", outTradeNo);
		params.put("body", body);
		params.put("total_fee", totalFee);
		params.put("notify_url", notifyUrl);
		params.put("trade_type", tradeType);
		params.put("openid", openid);
		params.put("appid", appId);//公众账号ID,Value.Weixin.APPID
		params.put("mch_id", mchId);//商户号 Value.Weixin.MCHID
		params.put("nonce_str", nonceStr);//随机字符串
		return getSign(params);
	}
	
//	public static String getSignAppPay(String nonceStr, String outTradeNo, String body, String totalFee, String notifyUrl, String tradeType, String openid, String ip) {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("out_trade_no", outTradeNo);
//		params.put("body", body);
//		params.put("total_fee", totalFee);
//		params.put("notify_url", notifyUrl);
//		params.put("trade_type", tradeType);
//		params.put("openid", openid);
//		params.put("appid", Value.Weixin.APPID_APP);//公众账号ID
//		params.put("mch_id", Value.Weixin.MCHID_APP);//商户号
//		params.put("nonce_str", nonceStr);//随机字符串
//		return getSign(params);
//	}
	
	/**
	 * 作用：以post方式提交xml到对应的接口url
	 * @param xml
	 * @param url
	 * @return
	 */
	public static String postXmlCurl(String xml, String url) {
		HttpClient client = HttpClients.createSystem();
		HttpPost post = new HttpPost(url);
		String result = null;
		try {
			post.setEntity(new StringEntity(xml));
			HttpResponse httpResponse = client.execute(post);
			if(httpResponse.getStatusLine().getStatusCode() == 200) {  
				HttpEntity httpEntity = httpResponse.getEntity();  
				result = EntityUtils.toString(httpEntity);//取出应答字符串  
			} 
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage(), e);
		} catch (ClientProtocolException e) {
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return result;  
	}


	/**
	 * 作用：使用证书，以post方式提交xml到对应的接口url
	 * @param xml
	 * @param url
	 * @return
	 */
	private static String postXmlSSLCurl(String xml, String url){
		try {
			return HTTPSRequest.httpsRequestSec(url, "POST", xml);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}
	/**
	 * 创建xml
	 * @param outTradeNo
	 * @param body
	 * @param totalFee
	 * @param notifyUrl
	 * @param tradeType
	 * @param openid
	 * @param ip
	 * @return
	 */
	private static String createXml(String nonceStr, String outTradeNo, String body, String totalFee, String notifyUrl, String tradeType, String openid, String ip, String sign,
			String appId,String mchId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("out_trade_no", outTradeNo);
		params.put("body", body);
		params.put("total_fee", totalFee);
		params.put("notify_url", notifyUrl);
		params.put("trade_type", tradeType);
		params.put("openid", openid);
		params.put("appid", appId);//公众账号ID,Value.Weixin.APPID
		params.put("mch_id", mchId);//商户号,Value.Weixin.MCHID
		params.put("nonce_str", nonceStr);//随机字符串
		params.put("sign", sign);//签名
	    return  XmlUtils.mapToXml(params);
	}
	
	
	/**
	 * 	作用：使用证书post请求xml
	 */
	private static String postXmlSSL(String nonceStr, String url, String outTradeNo, String body, String totalFee, String notifyUrl, String tradeType, String openid, String ip, String sign,
			String appId,String mchId) {	
		 String xml = createXml(nonceStr, outTradeNo, body, totalFee, notifyUrl, tradeType, openid, ip, sign,appId,mchId);
		return postXmlSSLCurl(xml,url);
	}

	/**
	 * 获取请求id
	 * @param outTradeNo 订单号
	 * @param body 订单详情
	 * @param totalFee 总金额
	 * @param tradeType JSAPI
	 * @param openid 用户openid
	 * @param ip 客户端ip
	 * @return
	 */
	public static String getPrepayId(String nonceStr, String outTradeNo, String body, String totalFee, String tradeType, String openid, 
			String ip, String sign,String notify,String appId,String mchId) {
		if (StringUtil.isEmpty(tradeType)) tradeType = JSAPI;
		String xml = postXmlSSL(nonceStr, Value.Weixin.CURL_URL, outTradeNo, body, totalFee, notify, tradeType, openid, ip, sign,appId,mchId);
		if (null != xml) {
			Map<String, String> resultMap =XmlUtils.xmlToMap(xml);
			System.out.println("**********weixinBack********:"+resultMap);
			if (null != resultMap) return resultMap.get("prepay_id");
		}
		return null;
	}
	/**
	 * 退款接口
	 * <p>退款有一定延时，用零钱支付的退款,20 分钟内到账，银行卡支付的退款 3 个工作日后重新查询退款状态。</p>
	 * @param outTradeNo 商户订单号
	 * @param outRefundNo 商户退款单号  商户系统内部的退款单号，商户系统内部唯一
	 * @param totalFee 总金额  （分）
	 * @param refundFee 退款金额（分）
	 * @return 退款申请提交成功返回true，反之返回false
	 */
	public static boolean refund(String outTradeNo, String outRefundNo, int totalFee, int refundFee,String appId,String mchId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("out_trade_no", outTradeNo);
		params.put("out_refund_no", outRefundNo);
		params.put("total_fee", String.valueOf(totalFee));
		params.put("refund_fee", String.valueOf(refundFee));
		params.put("appid", appId);//Value.Weixin.APPID
		params.put("mch_id", mchId);//Value.Weixin.MCHID
		params.put("op_user_id", mchId);//Value.Weixin.MCHID
		params.put("nonce_str", PayUtils.createNoncestr());
		params.put("sign", PayUtils.getSign(params));
		
		String xml = XmlUtils.mapToXml(params);
		String result = PayUtils.postXmlSSLCurl(xml, Value.Weixin.REFUND_URL);
		Map<String, String> resultMap = XmlUtils.xmlToMap(result);
		return "SUCCESS".equals(resultMap.get("return_code"));
	}
	/**
	 * 退款接口
	 * <p>退款有一定延时，用零钱支付的退款,20 分钟内到账，银行卡支付的退款 3 个工作日后重新查询退款状态。</p>
	 * @param outTradeNo 商户订单号
	 * @param outRefundNo 商户退款单号  商户系统内部的退款单号，商户系统内部唯一
	 * @param totalFee 总金额  （分）
	 * @param refundFee 退款金额（分）
	 * @return 退款申请提交成功返回true，反之返回false
	 */
	public static boolean refundAPPPay(String outTradeNo, String outRefundNo, int totalFee, int refundFee,String appId,String mchId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("out_trade_no", outTradeNo);
		params.put("out_refund_no", outRefundNo);
		params.put("total_fee", String.valueOf(totalFee));
		params.put("refund_fee", String.valueOf(refundFee));
		params.put("appid", appId);
		params.put("mch_id", mchId);
		params.put("op_user_id", mchId);
		params.put("nonce_str", PayUtils.createNoncestr());
		params.put("sign", PayUtils.getSign(params));
		
		String xml = XmlUtils.mapToXml(params);
		String result = PayUtils.postXmlSSLCurl(xml, Value.Weixin.REFUND_URL);
		Map<String, String> resultMap = XmlUtils.xmlToMap(result);
		return "SUCCESS".equals(resultMap.get("return_code"));
	}
	/**
	 * 退款状态查询
	 * @param outTradeNo 订单号
	 * @return
	 */
	public static RefundStatus refundQuery(String outTradeNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("out_trade_no", outTradeNo);
		params.put("appid", Value.Weixin.APPID);
		params.put("mch_id", Value.Weixin.MCHID);
		params.put("nonce_str", PayUtils.createNoncestr());
		params.put("sign", PayUtils.getSign(params));
		
		String xml = XmlUtils.mapToXml(params);
		String result = PayUtils.postXmlSSLCurl(xml, Value.Weixin.REFUND_QUERY_URL);
		Map<String, String> resultMap = XmlUtils.xmlToMap(result);
		if (null == resultMap.get("return_code")) return RefundStatus.ERROR;
		if (!"SUCCESS".equals(resultMap.get("return_code")))  return RefundStatus.ERROR;
		String status = resultMap.get("refund_status_"+0);
		return RefundStatus.valueOf(status);
	}
	
	/**
	 * 校验请求
	 * @param xml
	 * @return
	 */
	public static boolean checkSign(Map<String, String> params) {
		Map<String, String> map = new HashMap<String, String>(params);
		String key = "sign";
		String sign = map.get(key);
		if (StringUtil.isEmpty(sign)) return false;
		map.remove(key);
		return sign.equals(getSign(map));
	}
	/**
	 * 接受到回调后的返回
	 * @param success
	 * @param msg
	 * @return
	 */
	public static String returnXml(boolean success, String msg) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("return_code", success ? "SUCCESS" : "FAIL");
		if (StringUtil.isEmpty(msg)) map.put("return_msg", msg);
		return XmlUtils.mapToXml(map);
	}
	public static void main(String[] args) {
		String openid = "op7X8tz1Ug0J2khfm8jP-UiDKacw";
		BigDecimal totalFee = new BigDecimal(0.01);
		String body = "测试，共享";
//		Orders weixinOrder = new Orders();
		String notify = "";//回调地址 
		
//		weixinOrder.setIp("116.21.163.170");
//		weixinOrder.setCreateTime(new Date());
//		weixinOrder.setOpenid(openid);
//		weixinOrder.setOutTradeNo(UUID.randomUUID().toString().replaceAll("\\-", ""));
//		weixinOrder.setTotalFee(totalFee);
//		weixinOrder.setTradeStatus(TradeStatus.PRAPARE);
//		weixinOrder.setTradeType(PayUtils.JSAPI);
//		weixinOrder.setBody(body);
		
//		String nonceStr = PayUtils.createNoncestr();
//		if (LOG.isInfoEnabled()) LOG.info("nonceStr:"+nonceStr);
//		if (LOG.isInfoEnabled()) LOG.info("OutTradeNo:"+weixinOrder.getOutTradeNo()+",body:"+weixinOrder.getBody()+",totalFee"+weixinOrder.getTotalFee().floatValue()+", PayConfig.NOTIFY_URL:"+notify+",tradeType:"+weixinOrder.getTradeType()+", openid:"+openid+", Ip:"+weixinOrder.getIp());
//		String sign = PayUtils.getSign(nonceStr, weixinOrder.getOutTradeNo(), weixinOrder.getBody(), String.valueOf(weixinOrder.getTotalFee().multiply(new BigDecimal(100)).intValue()), notify, weixinOrder.getTradeType(), openid, weixinOrder.getIp());
//		if (LOG.isInfoEnabled()) LOG.info("sign:"+sign);
//		String prepayId = PayUtils.getPrepayId(nonceStr, weixinOrder.getOutTradeNo(), weixinOrder.getBody(), String.valueOf(weixinOrder.getTotalFee().multiply(new BigDecimal(100)).intValue()), weixinOrder.getTradeType(), weixinOrder.getOpenid(), weixinOrder.getIp(), sign,notify);
//		if (LOG.isInfoEnabled()) LOG.info("prepayId:"+prepayId);
//		weixinOrder.setPrepayId(prepayId);
	}
}
