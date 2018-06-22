package main.com.allInPay.utils;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONObject;

public class AllInPayUtil {
	public static <T> T json2Obj(String jsonstr,Class<T> cls){
    	JSONObject jo =JSONObject.fromObject(jsonstr);
		T obj = (T)JSONObject.toBean(jo, cls);
		return obj;
    }
	
	public static String dateToString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		if(date == null){
			date = new Date();
		}
		return format.format(date);
	}
	/**
	 * MD5
	 * @param data
	 * @return
	 */
	 public static String md5(String data) {
        try {
        	MessageDigest md = MessageDigest.getInstance("MD5");
        	 md.reset();
             md.update(data.getBytes("utf-8"));
             byte[] hash = md.digest();
             StringBuffer outStrBuf = new StringBuffer(32);
             for (int i = 0; i < hash.length; i++) {
                 int v = hash[i] & 0xFF;
                 if (v < 16) {
                 	outStrBuf.append('0');
                 }
                 outStrBuf.append(Integer.toString(v, 16).toLowerCase());
             }
             return outStrBuf.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
	       
	}
	
	/**
	 * MD5
	 * @param params
	 * @param key
	 * @return
	 */
	public static String sign(Map<String,String> params,String key){
		if(params.containsKey("sign")){
			params.remove("sign");
		}
//		params.put("key", key);
		Map<String,String> keyMap = new HashMap<String, String>();
		keyMap.put("key", key);
		StringBuilder sb = new StringBuilder();
		sb.append("&");
		for(Map.Entry<String, String> entry:params.entrySet()){
			if(entry.getValue()!=null && !entry.getValue().toString().equals("") && entry.getValue().length()>0){
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		for(Map.Entry<String, String> entry:keyMap.entrySet()){
			if(entry.getValue()!=null && !entry.getValue().toString().equals("") && entry.getValue().length()>0){
				sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		
//		if(sb.length()>0){
//			sb.deleteCharAt(sb.length()-1);
//		}
		String sign = md5(sb.toString()).toUpperCase();
//		params.remove("key");
		return sign;
	}
	
	/**
	 * @param params
	 * @param key
	 * @return
	 */
	public static String sign(StringBuffer stringBuffer,String key){
		stringBuffer.append("key").append("=").append(key);
		
		String sign = md5(stringBuffer.toString()).toUpperCase();
//		params.remove("key");
		return sign;
	}
	
	/**
	 * @param param
	 * @param key
	 * @return
	 * @throws Exception
	 */
	 public static boolean validSign(TreeMap<String,String> param,String key) throws Exception{
	    	if(param!=null&&!param.isEmpty()){
	    		if(!param.containsKey("sign")){
	    			return false;
	    		}
	    		String sign = param.get("sign").toString();
	    		System.out.println("================sign:"+sign+"==============");
	    		param.remove("sign");
	    		String systemSign = sign(param,key);
	    		return sign.toLowerCase().equals(systemSign);
	    	}
	    	return false;
	    }
	 
		/**
		 * @param param
		 * @param key
		 * @return
		 * @throws Exception
		 */
		 public static boolean validSign(StringBuffer buffer,String key,String sign) throws Exception{
		    	if(buffer == null || "".equals(buffer.toString()) || key==null || "".equals(key)){
		    		return false;
		    	}
	    		System.out.println("================sign:"+sign+"==============");
	    		String systemSign = sign(buffer,key);
	    		return sign.equals(systemSign);
		    }
	 
	 
	 /**
		 * @param param
		 * @param key
		 * @return
		 * @throws Exception
		 */
//		 public static boolean validSign(Map<String,String> param,String key) throws Exception{
//		    	if(param!=null&&!param.isEmpty()){
//		    		if(!param.containsKey("sign")){
//		    			return false;
//		    		}
//		    		String sign = param.get("sign").toString();
//		    		System.out.println("================sign:"+sign+"==============");
//		    		param.remove("sign");
//		    		String systemSign = sign(param,key);
//		    		return sign.toLowerCase().equals(systemSign);
//		    	}
//		    	return false;
//		    }
		 
		 /**
		  * @param params
		  * @param key
		  * @return
		  */
//			public static String sign(Map<String,String> params,String key){
//				if(params.containsKey("sign")){
//					params.remove("sign");
//				}
//				params.put("key", key);
//				StringBuilder sb = new StringBuilder();
//				for(Map.Entry<String, String> entry:params.entrySet()){
//					if(entry.getValue()!=null && !entry.getValue().toString().equals("") && entry.getValue().length()>0){
//						sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//					}
//				}
//				if(sb.length()>0){
//					sb.deleteCharAt(sb.length()-1);
//				}
//				String sign = md5(sb.toString()).toUpperCase();
//				params.remove("key");
//				return sign;
//			}
	 /**
	  * @param orderid
	  * @param trxid
	  * @throws Exception
	  */
	 public static Map<String,String> query(String url,Map<String,String> params,String key,StringBuffer stringBuffer) throws Exception{
			HttpConnectionUtil http = new HttpConnectionUtil(url);
			http.init();
			if(!params.containsKey("signMsg") || null == params.get("signMsg") || params.get("signMsg").equals("")){
				params.put("signMsg", AllInPayUtil.sign(stringBuffer,key));
    		}
			byte[] bys = http.postParams(params, true);
			String result = new String(bys,"UTF-8");
			System.out.println("ret:"+result);
//			Map<String,String> map = handleResult(result,key);
			Map<String,String> map = handleResult(result);
			for(Map.Entry<String, String> entry:map.entrySet()){
				System.out.println(entry.getKey()+":"+entry.getValue());
			}
			return map;
		}
		
	 /**
	  * @param orderid
	  * @param trxid
	  * @throws Exception
	  */
	 public static Map<String,String> returnQuery(String url,Map<String,String> params) throws Exception{
			HttpConnectionUtil http = new HttpConnectionUtil(url);
			http.init();
			byte[] bys = http.postParams(params, true);
			String result = new String(bys,"UTF-8");
			System.out.println("ret:"+result);
//			Map<String,String> map = handleResult(result,key);
			Map<String,String> map = handleResult(result);
			for(Map.Entry<String, String> entry:map.entrySet()){
				System.out.println(entry.getKey()+":"+entry.getValue());
			}
			return map;
		}
	 /**
	  * @param result
	  * @return
	  * @throws Exception
	  */
		private static Map<String,String> handleResult(String result,String key) throws Exception{
			Map map = AllInPayUtil.json2Obj(result, Map.class);
			if(map == null){
				throw new Exception("错误");
			}
			if("SUCCESS".equals(map.get("retcode"))){
				TreeMap tmap = new TreeMap();
				tmap.putAll(map);
				String sign = tmap.remove("sign").toString();
				String sign1 = AllInPayUtil.sign(tmap,key);
				if(sign1.equals(sign)){
					return map;
				}else{
					throw new Exception("错误");
				}
			}else{
				throw new Exception(map.get("retmsg").toString());
			}
		}
		
		 /**
		  * @param result
		  * @return
		  * @throws Exception
		  */
			private static Map<String,String> handleResult(String result) throws Exception{
				result = result.replaceAll("=", "\":\"");
				result = result.replaceAll("&", "\",\"");
				if(result.indexOf("{")<=0 || result.indexOf("}")<=0){
					result = "{\""+result+"\"}";
				}
				Map map = AllInPayUtil.json2Obj(result, Map.class);
				if(map == null){
					throw new Exception("拼接通联网关支付出错");
				}
				return map;
//				if("SUCCESS".equals(map.get("retcode"))){
//					TreeMap tmap = new TreeMap();
//					tmap.putAll(map);
//					String sign = tmap.remove("sign").toString();
//					String sign1 = AllInPayUtil.sign(tmap,key);
//					if(sign1.equals(sign)){
//						return map;
//					}else{
//						throw new Exception("拼接通联网关支付出错");
//					}
//				}else{
//					throw new Exception(map.get("retmsg").toString());
//				}
			}
}
