package main.com.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import main.com.utils.express.ExpressConfig;

import org.apache.el.stream.Stream;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HTTPRequest {


    public static String http(String httpUrl) throws Exception {

	URL url = new URL(httpUrl);
	InputStream is = null;
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
	conn.setDoInput(true);
	conn.connect();
	is = conn.getInputStream(); // 得到网络返回的输入流
	/*
	 * BufferedReader br = new BufferedReader(new InputStreamReader
	 * (is,"UTF-8")); String line = ""; String responseBody = null ; while
	 * ((line = br.readLine ()) != null){ responseBody = line; }
	 */
	byte[] b = new byte[1024];
	StringBuffer s = new StringBuffer();
	for (int i = 0; is.read(b) != -1; i++) {
	    s.append(new String(b));
	}

	return s.toString();

    }

    public static String http2(String httpUrl) throws Exception {
	URL url = new URL(httpUrl);
	InputStream is = null;
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
	conn.setDoInput(true);
	conn.connect();
	is = conn.getInputStream(); // 得到网络返回的输入流
	BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
	String line = "";
	String responseBody = null;
	while ((line = br.readLine()) != null) {
	    responseBody = line;
	}
	// System.out.println(responseBody);

	return responseBody;

    }

    /**
     * http://api.kuaidi100.com/eorderapi.do?method=getElecOrder&param=%7B"partnerId":"K61991227",
     * "partnerKey":"M5A779k6","kuaidicom":"yuantong","orderId":"DD2017042495071054","recMan":
     * %7B"mobile":"15875319507","name":"黎志文","printAddr":"广东省广州市海珠区U视一号"%7D,"sendMan":
     * %7B"name":"黎志文","mobile":"15875319507","printAddr":"广州天平架陶庄路5号空间F0039",
     * "company":"贵州U视一号生物科技有限公司"%7D,"cargo":"化妆品","count":"1","weight":"0.25",
     * "payType":"SHIPPER","expType":"圆通快递","needTemplate":"1"%7D
     * &sign=5ACA527CDF551D766DE842AC7EE2A261&t=1493176421678&key=eQvyfQlW628

     * @param httpUrl
     * @param keyword
     * @param itemName
     * @param name
     * @param requestCode
     * @param from
     * @param to
     * @param pageSize
     * @param page
     * @param hospitalId
     * @return
     * @throws Exception
     */
    public static String httpToPost(String httpUrl, String method,String param,String sign,String t,String key) throws Exception {
	String result = "";
	HttpPost httppost = new HttpPost(httpUrl);
	// 建立HttpPost对象
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	// 建立一个NameValuePair数组，用于存储欲传送的参数
	if (method != null && !method.equals("")) {
	    params.add(new BasicNameValuePair("method", method));
	}
	if (param != null && !param.equals("")) {
	    params.add(new BasicNameValuePair("param", param));
	}
	if (sign != null && !sign.equals("")) {
	    params.add(new BasicNameValuePair("sign", sign));
	}
	if (t != null && !t.equals("")) {
	    params.add(new BasicNameValuePair("t", t));
	}
	if (key != null && !key.equals("")) {
	    params.add(new BasicNameValuePair("key", key));
	}
	// 设置编码
	HttpResponse response;
	JSONObject object = null;
	httppost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
//	System.out.println("请求的参数：");
//	System.out.println(params);
	response = new DefaultHttpClient().execute(httppost);
//	System.out.println(response.getStatusLine().getStatusCode());
	if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
	    result = EntityUtils.toString(response.getEntity());
//	    net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject(result);
	    object = new JSONObject(result);
//	    System.out.println(object.get("message").toString().getBytes("GBK"));
//	    System.out.println(object.get("message").toString().getBytes("UTF-8"));
//	    System.out.println(new String(object.get("message").toString().getBytes("ISO-8859-1"), "UTF-8"));
	    if(StringUtil.isNotEmpty(object.getString("message"))){
	    	object.put("message", new String(object.getString("message").getBytes("ISO-8859-1"), "UTF-8"));
	    }
	    return object.toString();
	}else{
		System.out.println("快递100报错");
		return null;
	}
    }
    
    public static String httpToPostPush(String httpUrl,String access_token,String paramsPush) throws Exception {//ACCESS_TOKEN
    	String result = "";
    	HttpPost httppost = new HttpPost(httpUrl);
    	// 建立HttpPost对象
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	// 建立一个NameValuePair数组，用于存储欲传送的参数
    	if (access_token != null && !access_token.equals("")) {
    	    params.add(new BasicNameValuePair("access_token", access_token));
    	}
    	if (params != null && !params.equals("")) {
    	    params.add(new BasicNameValuePair("param", paramsPush));
    	}

    	// 设置编码
    	HttpResponse response;
    	JSONObject object = null;
    	httppost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
    	System.out.println("请求的参数：");
    	System.out.println(params);
    	response = new DefaultHttpClient().execute(httppost);
    	System.out.println(response.getStatusLine().getStatusCode());
    	if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
    	    result = EntityUtils.toString(response.getEntity());
//    	    System.out.println("推送结果：  "+ result);
//    	    object = new JSONObject(result);
//    	    if(StringUtil.isNotEmpty(object.getString("message"))){
//    	    	object.put("message", new String(object.getString("message").getBytes("ISO-8859-1"), "UTF-8"));
//    	    }
//    	    return object.toString();
    	}
//    	else{
    		System.out.println("推送链接微信服务器失败");
//    		return null;
//    	}
			return result;
       }
    
    
    //ExpressConfig.ElectronicsOrderUrl, ExpressConfig.schema, subscribe.toString()
    public static String subscribePost(String httpUrl, String schema,String param) throws Exception {
    	String result = "";
    	HttpPost httppost = new HttpPost(httpUrl);
    	// 建立HttpPost对象
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	// 建立一个NameValuePair数组，用于存储欲传送的参数
    	if (schema != null && !schema.equals("")) {
    		params.add(new BasicNameValuePair("schema", schema));
    	}
    	if (param != null && !param.equals("")) {
    		params.add(new BasicNameValuePair("param", param));
    	}
    	// 设置编码
    	HttpResponse response;
    	JSONObject object = null;
    	httppost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
//    	System.out.println("请求的参数：");
    	System.out.println(params);
    	response = new DefaultHttpClient().execute(httppost);
    	System.out.println(response.getStatusLine().getStatusCode());
    	if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
    		result = EntityUtils.toString(response.getEntity());
//	    net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject(result);
    		object = new JSONObject(result);
//	    System.out.println(object.get("message").toString().getBytes("GBK"));
//	    System.out.println(object.get("message").toString().getBytes("UTF-8"));
//	    System.out.println(new String(object.get("message").toString().getBytes("ISO-8859-1"), "UTF-8"));
    		
    		if(StringUtil.isNotEmpty(result)){
//    			object.put("message", );
    		}
//    		if(StringUtil.isNotEmpty(object.getString("message"))){
//    			object.put("message", new String(object.getString("message").getBytes("ISO-8859-1"), "UTF-8"));
//    		}
    	}
    	
//	System.out.println(result.getBytes("GBK"));
    	return new String(result.getBytes("ISO-8859-1"), "UTF-8");
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
    public static JSONObject httpsRequest(String requestUrl,
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
     * 发送post请求到IM服务器
     * 
     * @param url
     * @param json
     * @return 请求返回值，如果返回null表示请求超时
     * @throws Exception
     */
    public static String sendPostRequest(String url, String json)
	    throws Exception {
//	System.out.println("请求app后台json-" + json);
	HttpPost httppost = new HttpPost(url);
	String result = null;
	HttpResponse response;
	try {
	    httppost.setEntity(new StringEntity(json, "utf-8"));
	    httppost.setHeader("Content-Type",
	    // "application/x-www-form-urlencoded;" +
		    " charset=UTF-8");
	    response = new DefaultHttpClient().execute(httppost);
	    if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
		result = EntityUtils.toString(response.getEntity());
	    } else {
		System.out.println("请求接口 返回异常");
		return null;
	    }
	} catch (Exception e) {
	    throw e;
	}
//	System.out.println("推送返回结果："+result);
	return result;
    }

    // 发起url请求
    public static JSONObject sendTheGet(String requestUrl, String type)
	    throws Exception {
	URL realUrl = new URL(requestUrl);
	StringBuffer bufferRes = new StringBuffer();
	try {
	    HttpURLConnection conn = (HttpURLConnection) realUrl
		    .openConnection();
	    // 连接超时
	    conn.setConnectTimeout(25000);
	    // 读取超时 --服务器响应比较慢,增大时间
	    conn.setReadTimeout(25000);
	    HttpURLConnection.setFollowRedirects(true);
	    // 请求方式
	    conn.setRequestMethod(type);
	    conn.setDoOutput(true);
	    conn.setDoInput(true);// 设置http头连接等
	    conn.setRequestProperty("User-Agent",
		    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
	    conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
	    conn.connect();
	    // 获取URLConnection对象对应的输出流
	    OutputStreamWriter out = new OutputStreamWriter(
		    conn.getOutputStream());
	    // 发送请求参数
	    // out.write(URLEncoder.encode(params,"UTF-8"));
	    // 写入菜单
	    // out.write(WeixinConfig.getValue("smune")!=null?WeixinConfig.getValue("smune"):smune);
	    out.flush();
	    out.close();
	    InputStream in = conn.getInputStream();
	    BufferedReader read = new BufferedReader(new InputStreamReader(in,
		    "UTF-8"));
	    String valueString = null;
	    while ((valueString = read.readLine()) != null) {
		bufferRes.append(valueString);
	    }
	    in.close();
	    if (conn != null) {
		// 关闭连接
		conn.disconnect();
	    }
	    JSONObject jsonObject = new JSONObject(bufferRes.toString());
	    return jsonObject;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    // 发起url请求
    public static JSONObject sendTheGetGPS(String requestUrl, String type)
    		throws Exception {
    	URL realUrl = new URL(requestUrl);
    	StringBuffer bufferRes = new StringBuffer();
    	try {
    		HttpURLConnection conn = (HttpURLConnection) realUrl
    				.openConnection();
    		// 连接超时
    		conn.setConnectTimeout(25000);
    		// 读取超时 --服务器响应比较慢,增大时间
    		conn.setReadTimeout(25000);
    		HttpURLConnection.setFollowRedirects(true);
    		// 请求方式
    		conn.setRequestMethod(type);
    		conn.setDoOutput(true);
    		conn.setDoInput(true);// 设置http头连接等
    		conn.setRequestProperty("User-Agent",
    				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
//	    conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
    		conn.connect();
    		// 获取URLConnection对象对应的输出流
    		OutputStreamWriter out = new OutputStreamWriter(
    				conn.getOutputStream());
    		// 发送请求参数
    		// out.write(URLEncoder.encode(params,"UTF-8"));
    		// 写入菜单
    		// out.write(WeixinConfig.getValue("smune")!=null?WeixinConfig.getValue("smune"):smune);
    		out.flush();
    		out.close();
    		InputStream in = conn.getInputStream();
    		BufferedReader read = new BufferedReader(new InputStreamReader(in,
    				"UTF-8"));
    		String valueString = null;
    		while ((valueString = read.readLine()) != null) {
    			bufferRes.append(valueString);
    		}
    		in.close();
    		if (conn != null) {
    			// 关闭连接
    			conn.disconnect();
    		}
    		JSONObject jsonObject = new JSONObject(bufferRes.toString());
    		return jsonObject;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    public static JSONObject sendTheMedia(String requestUrl,File file,String type)
    	    throws Exception {
    	JSONObject result =null;
    	URL realUrl = new URL(requestUrl);
    	 HttpURLConnection con = (HttpURLConnection) realUrl
    			    .openConnection();
    	con.setDoInput(true);     	  
    	con.setDoOutput(true);      	  
    	con.setUseCaches(false); // post方式不能使用缓存
    	// 设置请求头信息      	  
    	con.setRequestProperty("Connection", "Keep-Alive");     	  
    	con.setRequestProperty("Charset", "UTF-8");  
    	// 设置边界      	  
    	String BOUNDARY = "----------" + System.currentTimeMillis();      	  
    	con.setRequestProperty("Content-Type",  
    	        "multipart/form-data; boundary="
    	        + BOUNDARY);
    	// 请求正文信息     	  
    	// 第一部分：     	  
    	StringBuilder sb = new StringBuilder();     	  
    	sb.append("--"); // 必须多两道线     	  
    	sb.append(BOUNDARY);     	  
    	sb.append("\r\n");      	  
    	sb.append("Content-Disposition: form-data;name=\"media\";filelength=\""+file.length()+"\";filename=\""
    	        + file.getName() + "\"\r\n");  
    	sb.append("Content-Type:application/octet-stream\r\n\r\n");  
    	byte[] head = sb.toString().getBytes("utf-8");  
    	// 获得输出流  
    	OutputStream out = new DataOutputStream(con.getOutputStream());  
    	// 输出表头  
    	out.write(head);
    	// 文件正文部分  
    	// 把文件已流文件的方式 推入到url中    
    	DataInputStream in = new DataInputStream(new FileInputStream(file));     	  
    	int bytes = 0;  
    	byte[] bufferOut = new byte[1024];  
    	while ((bytes = in.read(bufferOut)) != -1) {  
    	    out.write(bufferOut, 0, bytes);  
    	}  
    	in.close(); 
    	// 结尾部分  
    	byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
    	out.write(foot);
    	out.flush();
    	out.close();
    	StringBuffer buffer = new StringBuffer();  
    	BufferedReader reader = null;   
    	try {      	  
    	    // 定义BufferedReader输入流来读取URL的响应     	  
    	    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));      	  
    	    String line = null;  
    	    while ((line = reader.readLine()) != null) {  
    	        buffer.append(line);  
    	    }  
    	    if (result == null) {  
    	        result = new JSONObject(buffer.toString());  
    	    }  
    	} catch (IOException e) {  
    	    System.out.println("发送POST请求出现异常！" + e);  
    	    e.printStackTrace();  
    	    throw new IOException("数据读取异常");  
    	} finally {  
    	  
    	    if (reader != null) {  
    	        reader.close();  
    	    }  
    	}  
    	return result;  
    }
}
