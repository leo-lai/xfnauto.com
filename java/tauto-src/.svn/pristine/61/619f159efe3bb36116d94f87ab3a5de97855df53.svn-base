package main.com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;

/**
 * 微信二维码图片下载
 * @author User
 *
 */
public class WeiXinQRDownLoad {

//	@RequestMapping("/img")
//    @ResponseBody
    public static byte[] WeiXinQRDownLoad_QR(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!StringUtil.isEmpty(url)) {
            URL getUrl = new URL(url);
            URLConnection connection = getUrl.openConnection();
            connection.setRequestProperty("Referer", "");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
            connection.setDoOutput(true);
            InputStream fis = connection.getInputStream();
            response.setContentType("image/*");
//            OutputStream os = response.getOutputStream();
//            int count = 0;
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
//            while ((count = fis.read(buffer)) != -1) {
//                os.write(buffer, 0, count);
//            }
//            os.flush();
            fis.close();
            return buffer;
        }
		return null;
    }
    
    public static byte[]  getInputStream(String url)throws Exception{
        InputStream is = null;
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet
                    .openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            // 获取文件转化为byte流
            is = http.getInputStream();
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            return buffer;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	if(is!=null){
        		is.close();
        	}
        }
        return null;
    }
    
    /**
     * 下载微信二维码图片，合成并上传到七牛 无聊
     * @param request
     * @param url
     * @param response
     * @return
     * @throws Exception
     */
    public static String downloadWXQRuploadQiNiu(HttpServletRequest request,String url,HttpServletResponse response) throws Exception {
		try {
			String code = StringCode.getProblemCode();
			String ctxpath = request.getSession().getServletContext().getRealPath("/")+"static/weixinQR/"+code+".png";
			String firstPath = request.getSession().getServletContext().getRealPath("/")+"static/";
			//(WeiXinQRDownLoad.getInputStream(url), "mytest", null);//默认未images空间
			WeiXinResult weiXinResult = ImageDownLoad.downMeaterMetod(ImageDownLoad.GET_METHOD, url, ctxpath);
			System.out.println("weiXinResult:"+weiXinResult);
			//合成图片
			String maxPath = request.getSession().getServletContext().getRealPath("/")+"static/image/max.png";
			String finalPath = ImgSyntheticWord.syntheticImgAndImg(firstPath, weiXinResult.getPath(), maxPath);
//			System.out.println("合成图片路径："+finalPath);
			//图片上传七牛
			String qnPath = QiniuUtils.uploadFileBackPath(ImageUtil.FileTobyte(finalPath), code, null);//默认未images空间 +".jpg";
//			System.out.println("qnPath:"+qnPath);		
			//删除
			ImageUtil.deleteFile(weiXinResult.getPath());
			ImageUtil.deleteFile(finalPath);
			
//			System.out.println("path:"+path);
        	return qnPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
	return "";
    }
    
    /**
     * 下载微信二维码图片，合成并保存到本地
     * @param request
     * @param url
     * @param response
     * @return
     * @throws Exception
     */
    public static String downloadWXQR(HttpServletRequest request,String url,HttpServletResponse response) throws Exception {
    	try {
    		String code = StringCode.getProblemCode();
    		String ctxpath = request.getSession().getServletContext().getRealPath("/")+"static/weixinQR/"+code+".png";
    		String firstPath = request.getSession().getServletContext().getRealPath("/")+"static/";
    		//(WeiXinQRDownLoad.getInputStream(url), "mytest", null);//默认未images空间
    		WeiXinResult weiXinResult = ImageDownLoad.downMeaterMetod(ImageDownLoad.GET_METHOD, url, ctxpath);
//    		System.out.println("weiXinResult:"+weiXinResult);
    		//合成图片
    		String maxPath = request.getSession().getServletContext().getRealPath("/")+"static/image/max.png";
    		String finalPath = ImgSyntheticWord.syntheticImgAndImg(firstPath, weiXinResult.getPath(), maxPath);
//    		System.out.println("合成图片路径："+finalPath);
    		//图片上传七牛
//    		String qnPath = QiniuUtils.uploadFileBackPath(ImageUtil.FileTobyte(finalPath), code, null);//默认未images空间 +".jpg";
//			System.out.println("qnPath:"+qnPath);		
    		//删除
    		ImageUtil.deleteFile(weiXinResult.getPath());
//    		ImageUtil.deleteFile(finalPath);
    		
//			System.out.println("path:"+path);
    		return finalPath;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "";
    }
    
    /**
     * 下载微信二维码图片，合成并保存到本地
     * @param request
     * @param url
     * @param response
     * @return
     * @throws Exception
     */
    public static String downloadWXQR(HttpServletRequest request,String url,HttpServletResponse response,String userName) throws Exception {
    	try {
    		String code = StringCode.getProblemCode();
    		String ctxpath = request.getSession().getServletContext().getRealPath("/")+"static/weixinQR/"+code+".png";
    		String firstPath = request.getSession().getServletContext().getRealPath("/")+"static/";
    		//(WeiXinQRDownLoad.getInputStream(url), "mytest", null);//默认未images空间
    		WeiXinResult weiXinResult = ImageDownLoad.downMeaterMetod(ImageDownLoad.GET_METHOD, url, ctxpath);
//    		System.out.println("weiXinResult:"+weiXinResult);
    		//合成图片
    		String maxPath = request.getSession().getServletContext().getRealPath("/")+"static/image/max.png";
    		if(StringUtil.isNotEmpty(userName)){
    			userName = "代理商："+userName;
    		}
    		String finalPath = ImgSyntheticWord.syntheticImgAndImgAndFont(firstPath, weiXinResult.getPath(), maxPath,userName);
//    		System.out.println("合成图片路径："+finalPath);
    		//图片上传七牛
//    		String qnPath = QiniuUtils.uploadFileBackPath(ImageUtil.FileTobyte(finalPath), code, null);//默认未images空间 +".jpg";
//			System.out.println("qnPath:"+qnPath);		
    		//删除
    		ImageUtil.deleteFile(weiXinResult.getPath());
//    		ImageUtil.deleteFile(finalPath);
    		
//			System.out.println("path:"+path);
    		return finalPath;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "";
    }
}
