package main.com.weixinApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.annotation.LogPoint;
import main.com.frame.domain.Result;
import main.com.weixinApp.dao.search.ConsumerOrderCarCreateOrUpdateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarListSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUpdateStateSearch;
import main.com.weixinApp.dao.search.UploadCheckCarPicSearch;
import main.com.weixinApp.service.ConsumerOrderCarService;

@Controller
@RequestMapping(value="/emInterface/employee/consumerOrderCar")
public class ConsumerOrderCarController {

	@Autowired
	private ConsumerOrderCarService consumerOrderCarService;
	
	@ResponseBody
	@RequestMapping(value="/uploadCheckCarPic")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "上传验车图片")
	public Result uploadCheckCarPic(UploadCheckCarPicSearch search) {
		return consumerOrderCarService.uploadCheckCarPic(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/list")
	public Result list(ConsumerOrderCarListSearch search) {
		return consumerOrderCarService.list(search);
	}
	
	/**
	 * 创建/更新
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/createOrUpdate")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "新增或更新车辆")
	public Result createOrUpdate(ConsumerOrderCarCreateOrUpdateSearch search) {
		return consumerOrderCarService.createOrUpdate(search);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateState")
	@LogPoint(logDes = "商务端代购管理模块", operateModule = "更新车辆状态")
	public Result updateState(ConsumerOrderCarUpdateStateSearch search) {
		return consumerOrderCarService.updateState(search);
	}
}
