package main.com.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.IDN;
import java.net.URL;
import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.ObjectUtils;
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import main.com.utils.MyX509TrustManager;  
/* 
 * 利用HttpClient进行post请求的工具类 
 */  
public class HttpClientUtil {  
	private static String charset = "utf-8";
    public static String doPost(String url,Map<String,String> map){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url);  
            //设置参数  
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    } 
    
    @SuppressWarnings("resource")
	public static String doPost(String url){  
    	HttpClient httpClient = null;  
    	HttpPost httpPost = null;  
    	String result = null;  
    	try{  
    		httpClient = new SSLClient();  
//    		httpClient = HttpClients.createDefault();
    		httpPost = new HttpPost(url);  
    		httpPost = null;//new HttpPost(url);  
//    		String regEx = "[\\01111011-\\01111101]";
//    		Pattern p=Pattern.compile(regEx);
//    		Matcher m=p.matcher(url);
//    		if(m.find()){
//    			httpPost = new HttpPost(IDN.toASCII(url));
//    			}else {
//    				httpPost = new HttpPost(url);
//    			}
//    		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//    		httpPost.setConfig(requestConfig);
    		HttpResponse response = httpClient.execute(httpPost);  
    		if(response != null){  
    			HttpEntity resEntity = response.getEntity();  
    			if(resEntity != null){  
    				result = EntityUtils.toString(resEntity,charset);  
    			}  
    		}  
    	}catch(Exception ex){  
    		ex.printStackTrace();  
    	}  
    	return result; 
    	
    	/**
    	 * 
    	 * httpClient = HttpClients.createDefault();
String regEx = "[\\u4e00-\\u9fa5]";
Pattern p=Pattern.compile(regEx);
Matcher m=p.matcher(url);
HttpGet httpGet = null;
if(m.find()){
httpGet = new HttpGet(IDN.toASCII(url));
}else {
httpGet = new HttpGet(url);
}
httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
httpGet.setConfig(requestConfig);
try {
httpReponse = httpClient.execute(httpGet);
} catch (ClientProtocolException e) {
flag++;
e.printStackTrace();
} catch (IOException e) {
flag++;
e.printStackTrace();
}
    	 */
    	
    	
    	
    	
    	
    	
    	
    	
    }

    /**
     * 发起https请求并获取结果
     * 
     * @param requestUrl
     *            请求地址
     * @param requestMethod
     *            请求方式（GET、POST）
     * @param outputStr
     *            提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject transboundaryRequest(String requestUrl,
	    String requestMethod, String outputStr) throws Exception {
	try {
	    JSONObject jsonObject = null;
	    StringBuffer buffer = new StringBuffer();
	    // 创建SSLContext对象，并使用我们指定的信任管理器初始化
	    TrustManager[] tm = { new MyX509TrustManager() };
	    SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	    sslContext.init(null, tm, new java.security.SecureRandom());
	    // 从上述SSLContext对象中得到SSLSocketFactory对象
	    SSLSocketFactory ssf = sslContext.getSocketFactory();

	    URL url = new URL(requestUrl);
	    HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
		    .openConnection();
	    httpUrlConn.setSSLSocketFactory(ssf);

	    httpUrlConn.setDoOutput(true);
	    httpUrlConn.setDoInput(true);
	    httpUrlConn.setUseCaches(false);
	    // 设置请求方式（GET/POST）
	    httpUrlConn.setRequestMethod(requestMethod);

	    if ("GET".equalsIgnoreCase(requestMethod)) {
		httpUrlConn.connect();
		Thread.sleep(3000);
	    }

	    // 当有数据需要提交时
	    if (null != outputStr) {
		OutputStream outputStream = httpUrlConn.getOutputStream();
		// 注意编码格式，防止中文乱码
		outputStream.write(outputStr.getBytes("UTF-8"));
		outputStream.close();
	    }
	    // 将返回的输入流转换成字符串
	    InputStream inputStream = httpUrlConn.getInputStream();
	    InputStreamReader inputStreamReader = new InputStreamReader(
		    inputStream, "utf-8");
	    BufferedReader bufferedReader = new BufferedReader(
		    inputStreamReader);

	    String str = null;
	    while ((str = bufferedReader.readLine()) != null) {
		buffer.append(str);
	    }
	    bufferedReader.close();
	    inputStreamReader.close();
	    // 释放资源
	    inputStream.close();
	    inputStream = null;
	    httpUrlConn.disconnect();
	    jsonObject = new JSONObject(buffer.toString());

	    return jsonObject;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    
    /**
	 * 跨域请求并接收返回的数据 参数说明: url:跨域请求的地址 params:需传递的参数,格式为: 参数1=AAA&参数B=BBB&参数C=CCC
	 * 
	 * @return String
	 * @throws IOException
	 * @throws HttpException
	 */
	public static String transboundaryRequest(String url, String params)
			throws HttpException, IOException {
		// 请求方法
		String response = new String();
		org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
		// 解决乱码问题
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				"UTF-8");
		PostMethod method = new PostMethod(ObjectUtils.toString(url));
		String[] paramList = params.split("&");
		for (int i = 0; i < paramList.length; i++) {
			String[] param = paramList[i].split("=");
			if (param[0] != "") {
				if (param.length > 1) {
					method.addParameter(param[0],
							ObjectUtils.toString(param[1]));
				} else {
					method.addParameter(param[0], "");
				}
			}
		}
		client.executeMethod(method);
		response = method.getResponseBodyAsString();
		return response;
	}
}  