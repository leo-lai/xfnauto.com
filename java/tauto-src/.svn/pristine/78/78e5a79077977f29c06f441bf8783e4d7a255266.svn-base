package main.com.weixinApp.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.stock.dao.po.ConsumerOrderCar;
import main.com.weixinApp.dao.search.ConsumerOrderCarCreateOrUpdateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarListSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUpdateStateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadCertificationPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadCiPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadExpressPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadOtherPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadTciPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderCarUploadTicketPicSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoCreateSearch;
import main.com.weixinApp.dao.search.UploadCheckCarPicSearch;

public interface ConsumerOrderCarService extends BaseService<ConsumerOrderCar> {
	
	/**
	 * 上传验车照片
	 * @param search
	 * @return
	 */
	Result uploadCheckCarPic(UploadCheckCarPicSearch search);
	
	/**
	 * 上传票据
	 * @param search
	 * @return
	 */
	Result uploadTicketPic(ConsumerOrderCarUploadTicketPicSearch search);
	
	/**
	 * 上传合格证
	 * @param search
	 * @return
	 */
	Result uploadCertificatonPic(ConsumerOrderCarUploadCertificationPicSearch search);
	
	/**
	 * 上传交强险照片
	 * @param seasrch
	 * @return
	 */
	Result uploadTciPic(ConsumerOrderCarUploadTciPicSearch seasrch);
	
	/**
	 * 上传商业险照片
	 * @param search
	 * @return
	 */
	Result uploadCiPic(ConsumerOrderCarUploadCiPicSearch search);
	
	/**
	 * 上传快递单
	 * @param search
	 * @return
	 */
	Result uploadExpressPic(ConsumerOrderCarUploadExpressPicSearch search);
	
	/**
	 * 上传其他图片
	 * @param search
	 * @return
	 */
	Result uploadOtherPic(ConsumerOrderCarUploadOtherPicSearch search);
	/**
	 * 创建or更新
	 * @param search
	 * @return
	 */
	Result createOrUpdate(ConsumerOrderCarCreateOrUpdateSearch search);
	
	/**
	 * 列表
	 * @param search
	 * @return
	 */
	Result list(ConsumerOrderCarListSearch search);
	
	/**
	 * 更新状态
	 * @param search
	 * @return
	 */
	Result updateState(ConsumerOrderCarUpdateStateSearch search);
	
}
