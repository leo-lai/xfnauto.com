package main.com.weixinApp.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.stock.dao.po.ConsumerOrderUser;
import main.com.weixinApp.dao.search.ConsumerOrderUserCreateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderUserUpdateSearch;

public interface ConsumerOrderUserService extends BaseService<ConsumerOrderUser>{
	
	/**
	 * 新增
	 * @param search
	 * @return
	 */
	Result create(ConsumerOrderUserCreateSearch search);
	
	/**
	 * 更新
	 * @param search
	 * @return
	 */
	Result update(ConsumerOrderUserUpdateSearch search);
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Result delete(Integer id);
	
	/**
	 * 校验唯一
	 * @param user
	 * @return
	 */
	Result validate(ConsumerOrderUser user);

	Result cancel(Integer id)throws Exception;
}
