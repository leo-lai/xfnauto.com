package main.com.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;


/**
 * 七牛云储存工具类
 * @author qbs
 *
 */
public class QiniuUtils {
	
	//七牛云账号的ak和sk，以及配置域名
//	private static final String  ak = "rRn0sfRtPfAwP2hE5M7yE9BBpj5AHqbnXBoW_zxN";
//	private static final String  sk = "RK02U_rN5pDhFGv2OUXbmvfBFiZgmHVTwpXv2Hki";
//	public static final String domain = "http://7xj223.com2.z0.glb.qiniucdn.com/";//域名
	
    public final static String ACCESS_KEY = "540c9PMHKRytUmSi0879CAVu17gaDSFdOtz29vWI";
    public final static String SECRET_KEY = "YYTu6sfB95H3EYnxIIuDsoxJcxRRCppkY-FqFSpD";
    public final static String WEB_PATH = "http://opii7iyzy.bkt.clouddn.com/";//"http://oobso2cnc.bkt.clouddn.com/";//"";
    
	
	/**
	 * 获取七牛云上传凭证
	 */
	public static String getUploadToken(String bucket){
		if(bucket == null || "".equals(bucket))bucket = "usee1";
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		String uploadToken = auth.uploadToken(bucket);
		return uploadToken;
	}
	
	/**
	 * 上传文件到七牛云
	 */
	public static int uploadFile(byte[] file, String fileName, String bucket){
		//构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone2());
		//...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		//...生成上传凭证，然后准备上传
//		UploadManager uploadManager = new UploadManager();//七牛上传管理器
		String uploadToken = getUploadToken(bucket);
		Response res = null;
		int statusCode = 0 ;
		try {
			res = uploadManager.put(file, fileName,uploadToken);//fileName是key,在7牛相当于文件名
			statusCode = res.statusCode;
		} catch (QiniuException e) {
			e.printStackTrace();
		}
		return statusCode;
	}
	
	/**
	 * 上传文件到七牛云
	 */
	public static String uploadFileBackPath(byte[] file, String fileName, String bucket){
		//构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone2());
		//...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		//...生成上传凭证，然后准备上传
//		UploadManager uploadManager = new UploadManager();//七牛上传管理器
		String uploadToken = getUploadToken(bucket);
		Response res = null;
		int statusCode = 0 ;
		try {
			res = uploadManager.put(file, fileName,uploadToken);//fileName是key,在7牛相当于文件名
			statusCode = res.statusCode;
//			System.out.println("七牛返回码："+statusCode);
		} catch (QiniuException e) {
			e.printStackTrace();
		}
		return WEB_PATH+fileName;
	}
	
	
}
