package main.com.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

    /**
     * 对给定的字符串进行base64解码操作
     */
    public static String decodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.decodeBase64(inputData.getBytes("utf-8")), "utf-8");
        } catch (UnsupportedEncodingException e) {
        }

        return null;
    }

    /**
     * 对给定的字符串进行base64加密操作
     */
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes("utf-8")), "utf-8");
        } catch (UnsupportedEncodingException e) {
        }

        return null;
    }
    
    /**
     * 对给定的字符串进行base64加密操作
     */
    public static String encodeData(byte[] bs) {
    	try {
    		if (null == bs || bs.length <= 0) {
    			return "";
    		}
//    		Base64Str = Convert.ToBase64String(bs);
//    		String str = new String(Base64.encode(bs.toByteArray())); 
//    		return new String(Base64.encodeBase64(bs.getBytes("utf-8")), "utf-8");
    		return BASE64Encoder.encode(bs);
    	} catch (Exception e) {
    	}
    	
    	return null;
    }

}
