package main.com.weixinApp.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import main.com.car.dao.search.CarBrandSearch;
import main.com.car.dao.search.CarColourSearch;
import main.com.car.dao.search.CarFamilySearch;
import main.com.car.dao.search.CarInteriorSearch;
import main.com.car.dao.search.CarsSearch;
import main.com.frame.cache.CacheEntity;
import main.com.frame.cache.CacheTimerHandler;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.utils.DownloadMediaResponse;
import main.com.utils.GeneralConstant;
import main.com.utils.NetWorkCenter;
import main.com.utils.QiniuUtils;
import main.com.utils.StreamUtil;
import main.com.utils.StringUtil;
import main.com.utils.GeneralConstant.SystemBoolean;
import main.com.utils.rlycode.RLYUtils;
import main.com.weixin.JSONUtil;
import main.com.weixin.dao.po.WeixinAppToken;
import main.com.weixin.service.WeixinAppTokenService;
import main.com.weixinApp.service.EmployeeCarService;

/**
 * 车大类三个下拉公用接口
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/common")
public class UtilCommonController {

	private static final Log logger = LogFactory.getLog(UtilCommonController.class);
	
	@Autowired
	EmployeeCarService employeeService;
	
	@Autowired
	WeixinAppTokenService weixinAppTokenService;
	
	/**
	 * 获取车辆品牌列表(下拉)
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carsBrandList")
	@ResponseBody
	public Result carsBrandList(CarBrandSearch search) {
		Result result = new Result();
		try{
			result = employeeService.carsBrandList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 根据品牌获取车系列表(下拉)
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carsFamilyList")
	@ResponseBody
	public Result carsFamilyList(CarFamilySearch search) {
		Result result = new Result();
		try{
			result = employeeService.carsFamilyList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取车辆类型列表（下拉无分页列表）
	 * @param menuSearch
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/carsListList")
	public Result carsListList(CarsSearch search) {
		Result result = new Result();
		try {
			result = employeeService.carsListList(search,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
	/**
	 * 内饰下拉
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carInteriorList")
	@ResponseBody
	public Result carInteriorGetByBrand(CarInteriorSearch search) {
		Result result = new Result();
		try{
			result = employeeService.carInteriorList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 获取车辆品牌颜色列表(下拉)
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carColourList")
	@ResponseBody
	public Result carColourList(CarColourSearch search) {
		Result result = new Result();
		try{
			result = employeeService.carColourList(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 图片识别
	 * @param menuSearch
	 * @return
	 * Image recognition
	 */
	@ResponseBody
	@RequestMapping(value = "/imageRecognition")
	public Result imageRecognition(String url) {
		Result result = new Result();
		try {
//			url = "http://opii7iyzy.bkt.clouddn.com/1521105892060";
			result = employeeService.imageRecognition(url,result);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "系统繁忙，请稍后再试");
		}
		return result;
	}
	
    /**
     * 文件上传（图片和视频）
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadFile")
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
//	    	QiuNiu qn = new QiuNiu();			
//		 String path = qn.uploadFileByte(buffer,System.currentTimeMillis()+suffix);
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
    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public Result uploadQiNiu(HttpServletRequest request,String media_ids) throws Exception {
    	Result result = new Result();
    	InputStream is = null;
    	 int len = 0;
    	FileOutputStream fileOutputStream = null;
		try {
			//接收参数-微信素材ID
			if(StringUtil.isEmpty(media_ids)){
				 result.setError(ResultCode.CODE_STATE_4009, "微信素材传递为空，请刷新重试");
				 return result;
			}
			WeixinAppToken apptoken = weixinAppTokenService.getAccessTokenJAVA();
			if(apptoken == null){
				result.setError(ResultCode.CODE_STATE_4009, "微信服务器连接失败，请稍后刷新重试");
			 return result;
			}
			//返回
			List<String> paths = new ArrayList<String>();
			List<byte[]> datas = new ArrayList<byte[]>();
			//剪切出来所有的素材ID
			String suffix = ".jpeg";//fileName.substring(dot).toLowerCase();// 后缀名
			String[] media_id_s = media_ids.split(GeneralConstant.SystemBoolean.SPLIT);
			for(String mediaId : media_id_s){
//		        MediaAPI mediaAPI = new MediaAPI(this.wxConfig);
		        DownloadMediaResponse dmr = downloadMedia(mediaId,apptoken);
		        if(dmr!=null & dmr.getContent()!=null){
		        	String fileName = mediaId;//UUIDUtils.create(); //产生一个新的名称
		        	int statusCode = QiniuUtils.uploadFile(dmr.getContent(), fileName, null);//默认未images空间
					if(statusCode==200){
//						System.out.println(QiniuUtils.WEB_PATH + fileName);
						paths.add(QiniuUtils.WEB_PATH + fileName);
//						if (allViewImageUrls.equals("")){
//							allViewImageUrls = QiniuUtils.WEB_PATH + fileName;
//						}else{
//							allViewImageUrls = allViewImageUrls + "," + QiniuUtils.domain + fileName;
//						}
					}
		        }
	        }
			result.setOK(ResultCode.CODE_STATE_200,"",paths);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4005, "上传文件出错");
		}finally{
			if(is != null){
				is.close();
			}
			if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}
	return result;
    }
    
    /**
     * 下载资源，实现的很不好，反正能用了。搞的晕了，之后会优化
     *
     * @param mediaId 微信提供的资源唯一标识
     * @return 响应对象
     */
    public DownloadMediaResponse downloadMedia(String mediaId,WeixinAppToken apptoken) {
        DownloadMediaResponse response = new DownloadMediaResponse();
        String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + apptoken.getAccessToken() + "&media_id=" + mediaId;
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(NetWorkCenter.CONNECT_TIMEOUT).setConnectTimeout(NetWorkCenter.CONNECT_TIMEOUT).setSocketTimeout(NetWorkCenter.CONNECT_TIMEOUT).build();
        CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        HttpGet get = new HttpGet(url);
        try {
            CloseableHttpResponse r = client.execute(get);
            if (HttpStatus.SC_OK == r.getStatusLine().getStatusCode()) {
                InputStream inputStream = r.getEntity().getContent();
                Header[] headers = r.getHeaders("Content-disposition");
                if (null != headers && 0 != headers.length) {
                    Header length = r.getHeaders("Content-Length")[0];
                    response.setContent(inputStream, Integer.valueOf(length.getValue()));
                    response.setFileName(headers[0].getElements()[0].getParameterByName("filename").getValue());
                } else {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    StreamUtil.copy(inputStream, out);
                    String json = out.toString();
                    System.out.println("微信H5上传文件微信返回："+json);
                    response = JSONUtil.toBean(json, DownloadMediaResponse.class);
                }
            }
        } catch (IOException e) {
        	logger.error("IO处理异常", e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
            	logger.error("异常", e);
            }
        }
        return response;
    }
    
	/**
	 * 手机验证码
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/phoneVerifyCode", method = RequestMethod.POST)
	public synchronized Result getPhoneVerifyCode(String phoneNumber) throws Exception {
        //Integer type = 0;
        Result result = new Result();
        int sendQuantity = 0;
        // 生成验证码
        int validCode = (int) ((Math.random() * 9 + 1) * 1000);
        // 判断缓存中的验证码是否已经存在和过期
        CacheEntity cacheEntity = CacheTimerHandler.getCache(phoneNumber);
        if (cacheEntity != null) {
            validCode = (int) cacheEntity.getCacheContext();
            long interval = cacheEntity.getTimeoutStamp()
                    - System.currentTimeMillis();
            // 一分钟内不会重新发送验证码，超过一分钟才生成新的验证码，也不会重复发送验证码
            if (interval >= (cacheEntity.getValidityTime() - 60) * 1000) {
                result.setError(ResultCode.CODE_STATE_4006, "你的验证码仍在一分钟有效期内。");
                return result;
            }
        }

        // 校验已发送条数
        CacheEntity cEntity = CacheTimerHandler.getCache("sendQuantity_" + phoneNumber);
        if(cEntity != null){
        	sendQuantity = (int) cEntity.getCacheContext();
        	if(sendQuantity>10){
        		result.setError(ResultCode.CODE_STATE_4006, "您的手机短信验证码发送条数已超过限制条数， 同一个手机号码24小时之内只能发送10条验证码");
                return result;
        	}
        }
        sendQuantity = sendQuantity + 1;
        if(SystemBoolean.FALSE){//不向手机发送
            cacheEntity = new CacheEntity(phoneNumber, validCode);
            CacheTimerHandler.addCache(phoneNumber, cacheEntity);
            
            cEntity = new CacheEntity("sendQuantity_" + phoneNumber, sendQuantity, 86400);  //一天24小时等于86400秒
            CacheTimerHandler.addCache("sendQuantity_" + phoneNumber, cEntity, 86400);
            
            result.setOK(ResultCode.CODE_STATE_200, "验证码已成功发送");
        }else{//正式发送
        	Boolean send = RLYUtils.sendTemplateSMS(phoneNumber, String.valueOf(validCode));
        	if(send){
//        		System.out.println("=====================>>验证码已成功发生：" + validCode);
                cacheEntity = new CacheEntity(phoneNumber, validCode);
                CacheTimerHandler.addCache(phoneNumber, cacheEntity);
                
                cEntity = new CacheEntity("sendQuantity_" + phoneNumber, sendQuantity, 86400);  //一天24小时等于86400秒
                CacheTimerHandler.addCache("sendQuantity_" + phoneNumber, cEntity, 86400);            
                result.setOK(ResultCode.CODE_STATE_200, "验证码已成功发送");
        	}else if(send == null){
        		 result.setError(ResultCode.CODE_STATE_4006, "手机号码格式错误");
        	}else{
        		 result.setError(ResultCode.CODE_STATE_4006, "手机号码格式错误或该号码验证码发送已达上限，如继续请更换手机号");
        	}       	 
        }
        return result;
    }
	
	//前端权限接口
	@RequestMapping(value = "/getConfig")
	@Transactional
	 @ResponseBody
	public Result getConfig(String url) throws Exception {
		Result result = new Result();
		try {
			if(StringUtil.isEmpty(url)){
				result.setError(ResultCode.CODE_STATE_4004, "URL错误");
				return result;
			}
			result = weixinAppTokenService.getJsapiTicket(url);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4004, "系统正在升级...");
		}
		return result;//返回信息
	}
}
