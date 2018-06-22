package main.com.utils.weixin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import main.com.utils.MyX509TrustManager;
import main.com.utils.Value;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppHTTPSRequest {
	private static final Logger LOG  = LoggerFactory.getLogger(AppHTTPSRequest.class);
	private static SSLConnectionSocketFactory sslsf;
	static {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			InputStream instream = AppHTTPSRequest.class.getClassLoader().getResourceAsStream("apiclient_cert_APP.p12");
			try {
				keyStore.load(instream, Value.Weixin.MCHID.toCharArray());
			} finally {
				instream.close();
			}
			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, Value.Weixin.MCHID.toCharArray()).build();
			// Allow TLSv1 protocol only
			sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new ExceptionInInitializerError(e);
		}
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
	 * @return
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) throws Exception {
		StringBuilder builder = new StringBuilder();

		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// 从上述SSLContext对象中得到SSLSocketFactory对象
		SSLSocketFactory ssf = sslContext.getSocketFactory();

		URL url = new URL(requestUrl);
		HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
		httpUrlConn.setSSLSocketFactory(ssf);

		httpUrlConn.setDoOutput(true);
		httpUrlConn.setDoInput(true);
		httpUrlConn.setUseCaches(false);
		// 设置请求方式（GET/POST）
		httpUrlConn.setRequestMethod(requestMethod);

		if ("GET".equalsIgnoreCase(requestMethod))
			httpUrlConn.connect();

		// 当有数据需要提交时
		if (null != outputStr) {
			OutputStream outputStream = httpUrlConn.getOutputStream();
			// 注意编码格式，防止中文乱码
			outputStream.write(outputStr.getBytes("UTF-8"));
			outputStream.close();
		}
		// 将返回的输入流转换成字符串
		InputStream inputStream = httpUrlConn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			builder.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		// 释放资源
		inputStream.close();
		inputStream = null;
		httpUrlConn.disconnect();

		return builder.toString();
	}
	/**
	 * 带证书的ssl
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 * @throws Exception
	 */
	public static String httpsRequestSec(String requestUrl, String requestMethod, String outputStr) throws Exception {
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		if ("GET".equalsIgnoreCase(requestMethod)) {
			try {
				HttpGet httpget = new HttpGet(requestUrl);
				CloseableHttpResponse response = httpclient.execute(httpget);
				try {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						return EntityUtils.toString(entity, "UTF-8");
					}
					EntityUtils.consume(entity);
				} finally {
					response.close();
				}
			} finally {
				httpclient.close();
			}
		} else if ("POST".equalsIgnoreCase(requestMethod)) {
			try {

				HttpPost httppost = new HttpPost(requestUrl);
				HttpEntity body = new StringEntity(outputStr, "UTF-8");
				httppost.setEntity(body);
				CloseableHttpResponse response = httpclient.execute(httppost);
				try {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						return EntityUtils.toString(entity, "UTF-8");
					}
				} finally {
					response.close();
				}
			} finally {
				httpclient.close();
			}
		}

		return null;
	}
}
