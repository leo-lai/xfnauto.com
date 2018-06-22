package main.com.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.mock.web.DelegatingServletInputStream;

public class RequestWrapper extends HttpServletRequestWrapper{

	private Map<String, String[]> parameterMap; // 所有参数的Map集合

	private final String body; //JSON数据链

//	public RequestWrapper(HttpServletRequest request) {
//		super(request);
//		
//		StringBuilder sb = new StringBuilder();
//		try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()))){
//			char[] charBuffer = new char[1024];
//			int b;
//			while((b = br.read(charBuffer)) > 0) {
//				sb.append(charBuffer,0,b);
//			}
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		body = sb.toString();
//	}
	public RequestWrapper(HttpServletRequest request) {
		super(request);
		 parameterMap = request.getParameterMap();
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()))){
			char[] charBuffer = new char[1024];
			int b;
			while((b = br.read(charBuffer)) > 0) {
				sb.append(charBuffer,0,b);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		body = sb.toString();
	}
//	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes());
		return new ServletInputStream() {
			
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return bais.read();
			}

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		};
	}
    // 重写几个HttpServletRequestWrapper中的方法
    /**
     * 获取所有参数名
     *
     * @return 返回所有参数名
     */
//    @Override
//    public Enumeration<String> getParameterNames() {
//        Vector<String> vector = new Vector<String>(parameterMap.keySet());
//        return vector.elements();
//    }

    /**
     * 获取指定参数名的值，如果有重复的参数名，则返回第一个的值 接收一般变量 ，如text类型
     *
     * @param name
     *            指定参数名
     * @return 指定参数名的值
     */
    @Override
    public String getParameter(String name) {
        String[] results = parameterMap.get(name);
        if (results == null || results.length <= 0)
            return null;
        else {
            return results[0];
        }
    }

}
