package main.com.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.utils.BASE64Encoder;
import main.com.utils.QiniuUtils;
import main.com.utils.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
//import com.cloopen.rest.sdk.utils.encoder.BASE64Decoder;

/**
 * 各种图片上传
 * @author 
 *
 */
@Controller
@RequestMapping(value = "/management/admin")
public class ImageContorller {

    private static final Log logger = LogFactory.getLog(ImageContorller.class);

    /**
     * 
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public Result uploadQiNiu(MultipartHttpServletRequest request,
	    HttpSession session) throws Exception {
    	Result result = new Result();
    		CommonsMultipartFile myfile = (CommonsMultipartFile) request
    				.getFile("img_file");
    		 byte[] buffer = myfile.getBytes();
    		 String fileName = myfile.getOriginalFilename();
    			int dot = fileName.lastIndexOf('.');
    			String suffix = fileName.substring(dot).toLowerCase();// 后缀名
    			List fileTypes = new ArrayList();
    			fileTypes.add(".jpg");
    			fileTypes.add(".jpeg");
    			fileTypes.add(".bmp");
    			fileTypes.add(".gif");
    			fileTypes.add(".png");
    			if (!fileTypes.contains(suffix)) {
    				result.setError(ResultCode.CODE_STATE_4005, "图片类型错误，只能传.jpg/.jpeg/.bmp/.gif/.png");
    			    return result;
    			}
//    	    	QiuNiu qn = new QiuNiu();			
//    		 String path = qn.uploadFileByte(buffer,System.currentTimeMillis()+suffix);
    	    	String path = QiniuUtils.uploadFileBackPath(buffer, System.currentTimeMillis()+"", null);//默认未images空间

    		 if(StringUtil.isEmpty(path)){
    			 result.setError(ResultCode.CODE_STATE_4009, "上传文件出错");
    		 }else{
    			 result.setOK(ResultCode.CODE_STATE_200,"",path);
    		 }
	return result;
    }
    
    /**
     * 
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadImageBase64")
    @ResponseBody
    public Result uploadQiNiu(HttpServletRequest request) throws Exception {
    	Result result = new Result();
    		String imageFile = request.getParameter("img_file");
    		// 通过base64来转化图片
    		if(StringUtil.isEmpty(imageFile)) {
    			result.setError(ResultCode.CODE_STATE_4005, "请选择图片");
    			return result;
    		}
    		imageFile = imageFile.replaceAll("data:image/jpeg;base64,", "");      
    		// Base64解码  
    		try {
    			byte[] imageByte = BASE64Encoder.decode(imageFile);      
    			for (int i = 0; i < imageByte.length; ++i) {      
    				if (imageByte[i] < 0) {// 调整异常数据      
    					imageByte[i] += 256;      
    				}
    			}
    			String path = QiniuUtils.uploadFileBackPath(imageByte, System.currentTimeMillis()+"", null);//默认未images空间
    			if(StringUtil.isEmpty(path)){
    				result.setError(ResultCode.CODE_STATE_4009, "上传文件出错");
    			}else{
    				result.setOK(ResultCode.CODE_STATE_200,"",path);
    			}
    		} catch (Exception e) {
    			e.printStackTrace(); 
    		}
    	
    	return result;
    }
    
//    @RequestMapping(value = "editorUploadImg", produces = "text/plain;charset=UTF-8", method = { RequestMethod.POST })
//    @ResponseBody
//    public String uploadImg0(HttpSession session,
//	    MultipartHttpServletRequest request) throws Exception {
//	Company company = (Company) session.getAttribute(Const.SESSION_COMPANY);
//	String json = UploadUtil.uploadImg(request,
//		"upload/images/" + company.getCompanyRef() + "/");
//	JSONObject resjson = new JSONObject(json);
//	String res = resjson.getString("res");
//	JSONObject obj = new JSONObject();
//	if (res.equals("err")) {
//	    obj.put("error", 1);
//	    obj.put("message", resjson.getString("desc"));
//	} else {
//	    obj.put("error", 0);
//	    obj.put("url", resjson.getString("desc"));
//	    obj.put("width", "100%");
//	}
//	return obj.toString();
//    }
    
    @RequestMapping(value = "/editorUploadImg", produces = "text/plain;charset=UTF-8", method = { RequestMethod.POST })
    @ResponseBody
    public String uploadImg0_1(HttpSession session,
	    MultipartHttpServletRequest request) throws Exception {
//	String json = UploadUtil.uploadImg(request,
//		"upload/images/" + company.getCompanyRef() + "/");
	CommonsMultipartFile myfile = (CommonsMultipartFile) request
			.getFile("img_file");
	 byte[] buffer = myfile.getBytes();
//	 String fileName = myfile.getOriginalFilename();
//		int dot = fileName.lastIndexOf('.');
//		String suffix = fileName.substring(dot).toLowerCase();// 后缀名
//		QiuNiu qn = new QiuNiu();
		
    	String path = QiniuUtils.uploadFileBackPath(buffer, System.currentTimeMillis()+"", null);//默认未images空间
//	String json = qn.uploadFileByte(buffer,System.currentTimeMillis()+suffix);
//	JSONObject resjson = new JSONObject(path);
//	String res = resjson.getString("res");
	JSONObject obj = new JSONObject();
    obj.put("error", 0);
    obj.put("url", path);
    obj.put("width", "100%");
//	if (res.equals("err")) {
//	    obj.put("error", 1);
//	    obj.put("message", resjson.getString("desc"));
//	    obj.put("message", resjson.getString("desc"));
//	} else {
//	    obj.put("error", 0);
//	    obj.put("url", path);
//	    obj.put("width", "100%");
//	}
	return obj.toString();
    }
    
//    @RequestMapping(value = "editorUploadvideo", produces = "text/plain;charset=UTF-8", method = { RequestMethod.POST })
//    @ResponseBody
//    public String editorUploadvideo(HttpSession session,
//	    MultipartHttpServletRequest request) throws Exception {
//	Company company = (Company) session.getAttribute(Const.SESSION_COMPANY);
//	String json = UploadUtil.uploadvideo(request,
//		"upload/images/" + company.getCompanyRef() + "/");
//	JSONObject resjson = new JSONObject(json);
//	String res = resjson.getString("res");
//	JSONObject obj = new JSONObject();
//	if (res.equals("err")) {
//	    obj.put("error", 1);
//	    obj.put("message", resjson.getString("desc"));
//	} else {
//	    obj.put("error", 0);
//	    obj.put("url", resjson.getString("desc"));
//	    obj.put("width", "100%");
//	}
//	return obj.toString();
//    }

    @RequestMapping(value = "deleteImg", produces = "text/plain;charset=UTF-8", method = {
	    RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String deleteImg(Exception ex, HttpServletResponse response,
	    HttpServletRequest request) throws Exception {
	PrintWriter out = null;
	try {
	    out = response.getWriter();
	    out.write("ok");
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (out != null) {
		out.close();
	    }
	}
	return null;
    }
}
