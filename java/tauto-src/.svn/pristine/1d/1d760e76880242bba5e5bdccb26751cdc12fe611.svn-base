package main.com.pay.allInPay;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * 各种固定值配置文件
 * @author Zwen
 *
 */
public class SystemConfig {


    private static Map<String, String> configMap = new HashMap<String, String>();

    static {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("sysconfig");
            Set<String> keySet = bundle.keySet();
            for (String key : keySet) {
                configMap.put(key, new String(bundle.getString(key).getBytes("ISO8859-1"),"utf-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否调试模式
     *
     * @return
     */
    public static boolean isDebugMode() {
        return "true".equals(configMap.get("isdebug"));
    }
    
    /**
     * 获取默认加价率
     * @return
     */
    public static Float getDefaultRate(){
    	String defaultRate =  configMap.get("defaultRate");
    	if(defaultRate == null || "".equals(defaultRate))return 1.0f;
    	return Float.parseFloat(defaultRate);
    }
    
    /**
	 * 获取通联默认信息
	 *
	 * @return
	 */
  //20160831修改为数据库
    public static String getMD5Key() {
		return configMap.get("MD5Key");
	}
    public static String getInputCharset() {
		return configMap.get("inputCharset");
	}
	public static String getPickupUrl() {//已作废
		return configMap.get("pickupUrl");
	}

	public static String getReceiveUrl() {//
		return configMap.get("receiveUrl");
	}
	
	public static String getVersion() {
		return configMap.get("version");
	}
	
	public static String getLanguage() {
		return configMap.get("language");
	}
	
	public static String getSignType() {
		return configMap.get("signType");
	}
	
		public static String getMerchantId() {
		return configMap.get("merchantId");
	}
	
	public static String getOrderCurrency() {
		return configMap.get("orderCurrency");
	}
	
	public static String getOrderExpireDatetime() {
		return configMap.get("orderExpireDatetime");
	}
	
	public static String getPayType() {
		return configMap.get("payType");
	}
	public static String getHomeQueryTest() {
		return configMap.get("homeQueryTest");
	}
	public static String getHomeQueryReal() {
		return configMap.get("homeQueryReal");
	}
	public static String getQueryVersion() {
		return configMap.get("queryVersion");
	}
	public static String getPayUrlTest() {
		return configMap.get("payUrlTest");
	}
	public static String getPayUrlreal() {
		return configMap.get("payUrlreal");
	}
	public static String getHomeQuery() {
		return configMap.get("homeQuery");
	}
	public static String getPayUrl() {
		return configMap.get("payUrl");
	}
	public static String getReturnPayVersion() {
		return configMap.get("returnPayVersion");
	}
	public static String getQueryreturnPayVersion() {
		return configMap.get("queryreturnPayVersion");
	}
	public static String getReturnPayQuery() {
		return configMap.get("returnPayQuery");
	}
	
	public static String get(String str) {
		return configMap.get(str);
	}
	
}
