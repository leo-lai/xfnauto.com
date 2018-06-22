package main.com.system.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.dao.ESCOrderInfoDao;
import main.com.system.dao.po.SystemRegion;
import main.com.system.service.SystemRegionService;
import main.com.utils.IOcontroller;
import main.com.weixin.service.WeixinAppTokenService;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 各种辅助功能总集
 * @author User
 *
 */
@Controller
@RequestMapping("/utils/contorller")
public class UtilsContorller {

	public static Logger logger = Logger.getLogger(UtilsContorller.class);
	
	@Autowired
	SystemRegionService systemRegionService;
	
	@Autowired
	ESCOrderInfoDao ESCOrderInfoDao;
	
	@Autowired
	WeixinAppTokenService weixinAppTokenService;
	
	/**
	 * JSON JS(地区JS静态代码)
	 * 
	 * @throws JSONException
	 */
	@RequestMapping(value = "/getConfig")
	@Transactional
	@ResponseBody
	public Result reedroginToTxt() throws Exception{
		Result result = new Result();
		 try {
			 List<SystemRegion> regions = systemRegionService.getAll();
			 JSONArray array = new JSONArray();
			 for(SystemRegion region : regions){
				 JSONObject objectFrist = new JSONObject();
				 objectFrist.put("value", region.getRegionId());
				 objectFrist.put("text", region.getRegionName());
				 List<JSONObject> objectsFrist = new ArrayList<JSONObject>();
				 for(SystemRegion regionsSecond : region.getChildrenRegion()){
					 JSONObject objectSecond = new JSONObject();
					 objectSecond.put("value", regionsSecond.getRegionId());
					 objectSecond.put("text", regionsSecond.getRegionName());
					 List<JSONObject> objectsSecond = new ArrayList<JSONObject>();
					 for(SystemRegion regionsThree : regionsSecond.getChildrenRegion()){
						 JSONObject objectThree = new JSONObject();
						 objectThree.put("value", regionsThree.getRegionId());
						 objectThree.put("text", regionsThree.getRegionName());
						 objectsSecond.add(objectThree);
					 }
					 objectSecond.put("children", objectsSecond);
					 objectsFrist.add(objectSecond);
				 }
				 objectFrist.put("children", objectsFrist);
				 array.put(objectFrist);
			 }
		   String path = "C:/Users/User/Desktop/";
		   String imgPath = "area6.txt";
		   	
		   File dir = new File(path);
		   if (!dir.exists()) {
		   		 dir.mkdirs();
		   }
		   File dirpathFile = new File(dir,imgPath);
		   IOcontroller.write(dirpathFile,array.toString());
		   result.setOK(ResultCode.CODE_STATE_200, "操作已完成");
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, "发生错误");
		}
		return result;
	}
	
	public static void main(String[] args) {
		BigDecimal b = new BigDecimal(199/199+"");
		System.out.println(b.setScale(2, BigDecimal.ROUND_HALF_UP).intValue());
	}
}
