package main.com.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import main.com.frame.domain.Result;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadCertificationPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadCiPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadExpressPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadOtherPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadTciPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadTicketPicSearch;
import main.com.weixinApp.service.ConsumerOrderCarService;

@Controller
@RequestMapping(value="/management/admin/ConsumerOrderCar")
public class ManagerConsumerOrderCarController {

	@Autowired
	private ConsumerOrderCarService consumerOrderCarService;
	
	/**
	 * 上传所有票证
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadTickPic")
	Result uploadTickPic(ConsumerOrderCarUploadTicketPicSearch search) {
		return consumerOrderCarService.uploadTicketPic(search);
	}
	
	/**
	 * 上传合格证
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadCertificationPic")
	Result uploadCertificationPic(ConsumerOrderCarUploadCertificationPicSearch search) {
		return consumerOrderCarService.uploadCertificatonPic(search);
	}
	
	/**
	 * 上传交强险图片
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadTciPic")
	Result uploadTciPic(ConsumerOrderCarUploadTciPicSearch search) {
		return consumerOrderCarService.uploadTciPic(search);
	}
	
	/**
	 * 上传商业险图片
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadCiPic")
	Result uploadCiPic(ConsumerOrderCarUploadCiPicSearch search) {
		return consumerOrderCarService.uploadCiPic(search);
	}
	
	/**
	 * 上传快递单
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadExpressPic")
	Result uploadExpressPic(ConsumerOrderCarUploadExpressPicSearch search) {
		return consumerOrderCarService.uploadExpressPic(search);
	}
	
	/**
	 * 上传其他图片
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadOtherPic")
	Result uploadOtherPic(ConsumerOrderCarUploadOtherPicSearch search) {
		return consumerOrderCarService.uploadOtherPic(search);
	}
}
