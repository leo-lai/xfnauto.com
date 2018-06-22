package main.com.Interceptor;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import main.com.utils.GeneralConstant;
import main.com.utils.SystemPath;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SysListener extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(SysListener.class);

    /**
     * Constructor of the object.
     */
    public SysListener() {
	super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    @Override
    public void destroy() {
	super.destroy(); // Just puts "destroy" string in log
	// Put your code here
    }

    @Override
    public void init() throws ServletException {
	log.info("开始配置系统参数...");
	ServletConfig servletConfig = this.getServletConfig();
	log.info("读取系统路径");
	// Const.ROOT_PATH =
	// servletConfig.getServletContext().getRealPath("/")+"/xml/";
	SystemPath.ROOT_PATH = servletConfig.getServletContext().getRealPath("/")
		.toString();
	System.out.println(SystemPath.ROOT_PATH);
	System.out.println(SystemPath.ROOT_PATH+"res"+File.separator+"image"+1+".png");
	// Const.REQUEST_PATH = "http://127.0.0.1:8080/requestInfo_2/";

    }

}
