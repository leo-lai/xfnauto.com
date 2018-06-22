package main.com.system.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.car.dao.po.CarColourImage;
import main.com.car.dao.search.CarColourImageSearch;
import main.com.car.dao.search.CarColourSearch;
import main.com.car.service.CarColourImageService;
import main.com.frame.annotation.LogPoint;
import main.com.frame.controller.BaseController;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;

/**
 * 车身的颜色图片配置
 * @author Zwen
 *
 */
@Controller
@RequestMapping("/management/admin")
public class ManagerCarColourImageController extends BaseController{

	public static Logger logger = Logger.getLogger(ManagerCarColourImageController.class);

	@Autowired
	CarColourImageService carColourImageService;
	
	/**
	 * 获取车辆品牌颜色的图片列表(下拉)
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carColourImageGetByCarColour")
	@ResponseBody
	public Result carColourImageGetByCarColour(CarColourImageSearch search) {
		Result result = new Result();
		try{
			result = carColourImageService.carColourImageGetByCarColour(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	/**
	 * 添加颜色的图片
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carColourImageAdd")
	@ResponseBody
	@LogPoint(logDes = "车身的颜色图片配置模块", operateModule = "给车身颜色添加图片")
	public Result carColourImageAdd(CarColourImageSearch search) {
		Result result = new Result();
		try{
			result = carColourImageService.carColourImageAdd(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
	
	/**
	 * 添加颜色的图片
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/carColourImageEdit")
	@ResponseBody
	@LogPoint(logDes = "车身的颜色图片配置模块", operateModule = "编辑车身颜色图片")
	public Result carColourImageEdit(CarColourImageSearch search) {
		Result result = new Result();
		try{
			result = carColourImageService.carColourImageEdit(search,result);
		}catch(Exception e){
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_500, e.getMessage());
		}
		return result;
	}
}
