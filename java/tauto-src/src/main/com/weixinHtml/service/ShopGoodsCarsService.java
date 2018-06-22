package main.com.weixinHtml.service;

import main.com.car.dao.search.CarsSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.weixinHtml.dao.po.ShopGoodsCars;
import main.com.weixinHtml.dao.search.ShopGoodsCarsSearch;

public interface ShopGoodsCarsService extends BaseService<ShopGoodsCars>{

	/**
	 * 商品列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shopGoodsCarsList(ShopGoodsCarsSearch search, Result result)throws Exception;
	
	/**
	 * 商务单查看商品列表
	 * @param search
	 * @param result
	 * @param isOrg
	 * @return
	 * @throws Exception
	 */
	Result shopGoodsCarsList(ShopGoodsCarsSearch search, Result result,SystemUsersVo users) throws Exception;

	/**
	 * 车商品详情
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result shopGoodsCarsInfo(ShopGoodsCarsSearch search, Result result)throws Exception;

	/**
	 * 上下架
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result shopGoodsCarsShelves(ShopGoodsCarsSearch search, Result result)throws Exception;

	/**
	 * 删除
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result shopGoodsCarsDel(ShopGoodsCarsSearch search, Result result)throws Exception;

	/**
	 * 编辑商品车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shopGoodsCarsEdit(ShopGoodsCarsSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 现有品牌下拉
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result brandListList(CarsSearch search, Result result)throws Exception;

	/**
	 * 现有城市下拉
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result cityListList(CarsSearch search, Result result)throws Exception;

}
