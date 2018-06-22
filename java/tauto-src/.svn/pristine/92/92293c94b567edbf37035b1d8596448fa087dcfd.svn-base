package main.com.weixinHtml.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;
import main.com.weixinHtml.dao.po.ShopFindCar;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.search.ShopApplyLoanSearch;
import main.com.weixinHtml.dao.search.ShopFindCarSearch;

public interface ShopFindCarService extends BaseService<ShopFindCar>{

	/**
	 * 寻车编辑
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shopFindCarEdit(ShopFindCarSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 我的寻车列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shopFindCarList(ShopFindCarSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 寻车列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shopFindCarList(ShopFindCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 取消我的寻车申请
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shopFindCarDel(ShopFindCarSearch search, Result result, ShopUsers users)throws Exception;

	/**
	 * 商务版 处理寻车
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shopFindCarUpdateState(ShopFindCarSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 寻车单详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result shopFindCarInfo(ShopFindCarSearch search, Result result, ShopUsers users)throws Exception;

}
