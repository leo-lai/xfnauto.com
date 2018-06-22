package main.com.utils.PingXX;

import java.io.File;

import main.com.utils.SystemPath;
import main.com.utils.Value;

import com.pingplusplus.Pingpp;

public class PingPlusPlus {
	   
    /** 
     * Pingpp 管理平台对应的 API Key 
     */  
    private final static String apiKey = Value.Charge.apiKey;  
  
  
    /** 
     * Pingpp 管理平台对应的应用 ID 
     */  
    private final static String appId = Value.Charge.appId;  
    /** 
     * 你生成的私钥路径 
     */  
    private final static String privateKeyFilePath = SystemPath.ROOT_PATH+File.separator+"res"+ File.separator+"rsa_private_key.pem";  
  
    public static String charge(String orderNo,int amount,String subject,String body,String channel,String clientIP)throws Exception{  
  
        // 设置 API Key  
        Pingpp.apiKey = apiKey;  
  
        // 设置私钥路径，用于请求签名  
        Pingpp.privateKeyPath = privateKeyFilePath;
//        Pingpp.
        PingPlusCharge charge=new PingPlusCharge(appId);  
        String chargeString=charge.createCharge(orderNo,amount,subject,body,channel,clientIP);  
        return chargeString;  
    }  
}
