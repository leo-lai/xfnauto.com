package main.com.utils.express;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import main.com.utils.MD5Encoder;
import main.com.utils.Value;
import main.com.utils.weixin.MD5;
import main.com.utils.weixin.PayConfig;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExpressUtile {

	private static Logger LOG = LoggerFactory.getLogger(ExpressUtile.class);
	
	private ExpressUtile() {
		throw new RuntimeException("can't init");
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
//					if(key.equals("recMan") || key.equals("sendMan")){
//						sb.append(key).append("=").append(formatBizQueryParaMap_(JSONObject.fromObject(sortMap.get(key)),true)).append("&");
//					}
					System.out.println(key);
					sb.append(key).append("=").append(URLEncoder.encode(sortMap.get(key).toString(), "UTF-8")).append("&");
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
	 * 	作用：格式化参数，签名过程需要使用
	 */
	private static String formatBizQueryParaMap_(Map<String, String> paraMap, boolean urlencode) {
		StringBuilder sb = new StringBuilder();
		TreeMap<String, String> sortMap = new TreeMap<String, String>(paraMap);
		if (urlencode) {
			try {
				for (String key : sortMap.keySet()) {
					if(key.equals("recMan") || key.equals("sendMan")){
//						sb.append(key).append("=").append(formatBizQueryParaMap(JSONObject.fromObject(sortMap.get(key)),true)).append("&");
					}
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
	 * @param t 
	 * @throws Exception 
	 */
	public static String getSign(Map<String, String> params, String t) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(params.toString());//formatBizQueryParaMap(params, true)
		//签名步骤二：在string后加入KEY sign = MD5 (param+t+key+secret) 
		sb.append(t);
		sb.append(Value.KUAIDI.KUAIDIKEY);
		sb.append(Value.KUAIDI.KUAIDISECRET);
		//签名步骤三：MD5加密
		System.out.println("要加密的串：");
		System.out.println(sb.toString());
		String result = null;
		//			result = MD5.getMD5(sb.toString());
		result = MD5(sb.toString());
		//签名步骤四：所有字符转为大写
		return result.toUpperCase();
	}
	
	public static String MD5(String str)throws Exception{
		MessageDigest md5 = MessageDigest.getInstance("MD5");  
		md5.update((str).getBytes("UTF-8"));  
		byte b[] = md5.digest();  
		  
		int i;  
		StringBuffer buf = new StringBuffer("");  
		  
		for(int offset=0; offset<b.length; offset++){  
		    i = b[offset];  
		    if(i<0){  
		        i+=256;  
		    }  
		    if(i<16){  
		        buf.append("0");  
		    }  
		    buf.append(Integer.toHexString(i));  
		}  
		  
		return buf.toString();
	}
	
//	public static String getSign() {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("out_trade_no", outTradeNo);
//		params.put("body", body);
//		params.put("total_fee", totalFee);
//		params.put("notify_url", notifyUrl);
//		params.put("trade_type", tradeType);
//		params.put("openid", openid);
//		params.put("appid", PayConfig.APPID);//公众账号ID
//		params.put("mch_id", PayConfig.MCHID);//商户号
//		params.put("nonce_str", nonceStr);//随机字符串
//		return getSign(params);
//	}
}
