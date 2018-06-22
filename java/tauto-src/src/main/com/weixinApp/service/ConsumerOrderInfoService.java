package main.com.weixinApp.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.stock.dao.po.ConsumerOrderInfo;
import main.com.weixinApp.dao.search.ChangeCarApplySearch;
import main.com.weixinApp.dao.search.ChangeCarApproveSearch;
import main.com.weixinApp.dao.search.ChangeCarSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoChangePriceSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoCreateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoUpdateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoUpdateStateSearch;
import main.com.weixinApp.dao.search.CreateConsumerOrderInfoSearch;
import main.com.weixinApp.dao.search.DistributeCarSearch;
import main.com.weixinApp.dao.search.QueryVinSearch;
import main.com.weixinApp.dao.search.RedistributeCarSearch;
import main.com.weixinApp.dao.search.RefuseCarChangeSearch;

public interface ConsumerOrderInfoService extends BaseService<ConsumerOrderInfo>{
	
	/**
	 * 新增订购车辆
	 * @param search
	 * @return
	 */
	Result createOrderInfo(CreateConsumerOrderInfoSearch search);
	
	/**
	 * 新增
	 * @param search
	 * @return
	 */
	Result create(ConsumerOrderInfoCreateSearch search);
	
	/**
	 * 更新
	 * @param search
	 * @return
	 */
	Result update(ConsumerOrderInfoUpdateSearch search);
	
	/**
	 * 查询车架号
	 * @param search
	 * @return
	 */
	Result queryVin(QueryVinSearch search);
	
	/**
	 * 配车
	 * @param search
	 * @return
	 */
	Result distributeCar(DistributeCarSearch search);
	
	/**
	 * 重新配车
	 * @param search
	 * @return
	 */
	Result redistributeCar(RedistributeCarSearch search);
	
	/**
	 * 同意换车
	 * @param search
	 * @return
	 */
	Result changeCar(ChangeCarSearch search);
	
	/**
	 * 换车申请
	 * @param search
	 * @return
	 */
	Result changeCarApply(ChangeCarApplySearch search);
	
	/**
	 * 同意换车
	 * @param search
	 * @return
	 */
	Result changeCarApprove(ChangeCarApproveSearch search);
	
	
	/**
	 * 换车拒绝
	 * @param search
	 * @return
	 */
	Result refuseChangeCar(RefuseCarChangeSearch search);
	
	/**
	 * 更新状态
	 * @param search
	 * @return
	 */
	Result updateState(ConsumerOrderInfoUpdateStateSearch search);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Result delete(Integer id);
	
	/**
	 * 修改价格
	 * @param search
	 * @return
	 */
	Result changePrice(ConsumerOrderInfoChangePriceSearch search);
}
