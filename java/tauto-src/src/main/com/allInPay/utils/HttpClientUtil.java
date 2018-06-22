package main.com.allInPay.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.ObjectUtils;

public class HttpClientUtil {

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
		HttpClient client = new HttpClient();
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