package main.com.weixinApp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.exception.BusinessException;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.po.ConsumerOrder;
import main.com.stock.dao.po.ConsumerOrderCar;
import main.com.stock.dao.po.ConsumerOrderInfo;
import main.com.stock.dao.po.ConsumerOrderUser;
import main.com.utils.ConvertUtil;
import main.com.weixinApp.dao.dao.ConsumerOrderCarDao;
import main.com.weixinApp.dao.dao.ConsumerOrderDao;
import main.com.weixinApp.dao.dao.ConsumerOrderInfoDao;
import main.com.weixinApp.dao.dao.ConsumerOrderUserDao;
import main.com.weixinApp.dao.search.ConsumerOrderUserCreateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderUserUpdateSearch;
import main.com.weixinApp.service.ConsumerOrderUserService;
@Service
public class ConsumerOrderUserImpl extends BaseServiceImpl<ConsumerOrderUser> implements ConsumerOrderUserService{

	@Autowired
	private ConsumerOrderUserDao consumerOrderUserDao;
	
	@Override
	protected BaseDao<ConsumerOrderUser> getBaseDao() {
		// TODO Auto-generated method stub
		return consumerOrderUserDao;
	}
	
	@Autowired
	private ConsumerOrderDao consumerOrderDao;
	
	@Autowired
	private ConsumerOrderInfoDao consumerOrderInfoDao;
	
	@Autowired
	private ConsumerOrderCarDao consumerOrderCarDao;
	
	@Override
	public Result create(ConsumerOrderUserCreateSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderUser user = new ConsumerOrderUser();
		ConvertUtil.objectToObject(search, user);
		
		ConsumerOrder order = consumerOrderDao.selectById(search.getOrderId());
		if(order == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		user.setCreateTime(new Date());	
		//校验
		Result validate = validate(user);
		if(!validate.isSuccess()) {
			return validate;
		}
		if(!consumerOrderUserDao.insert(user)) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "新增失败");
		}
		return new Result(user);
	}
	
	@Override
	public Result update(ConsumerOrderUserUpdateSearch search) {
		// TODO Auto-generated method stub
		
		ConsumerOrderUser user = new ConsumerOrderUser();
		ConvertUtil.objectToObject(search, user);
		//校验
		Result validate = validate(user);
		if(!validate.isSuccess()) {
			return validate;
		}
		if(!consumerOrderUserDao.updateById(user)) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
		}
		return new Result(ResultCode.CODE_STATE_200,true,"更新成功");
	}
	
	/**
	 * 校验唯一
	 * @param user
	 * @return
	 */
	public Result validate(ConsumerOrderUser user) {
		boolean flag = true;
		String errorMsg = null;
		
		//校验提车人/客户手机号唯一
		Map<String,Object> m = new HashMap<>();
		m.put("orderId", user.getOrderId());
		m.put("type", user.getType());
		m.put("isDel", 0);
		List<ConsumerOrderUser> users = consumerOrderUserDao.select(m);
		if(users != null && !users.isEmpty()) {
			for(ConsumerOrderUser u : users) {
				if(u.getUserPhone() == null || u.getUserPhone().isEmpty() || !Objects.equals(user.getUserPhone(), u.getUserPhone())) {
					continue;
				}
				if(user.getId() == null || (user.getId() != null && !Objects.equals(user.getId(), u.getId()))) {
					flag = false;
					errorMsg = "手机号不能重复";
				}
			}
		}
		return new Result(flag,(Object)errorMsg);
	}
	
	@Override
	public Result delete(Integer id) {
		// TODO Auto-generated method stub
		ConsumerOrderUser user = consumerOrderUserDao.selectById(id);
		if(user == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		//如果删除的是客户，则遍历客户下订单,删除
		if(Objects.equals(user.getType(), 1)) {
			Map<String,Object> m1 = new HashMap<>();
			m1.put("customerId", user.getId());
			m1.put("orderId", user.getOrderId());
			m1.put("isDel", 0);
			List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m1);
			for(ConsumerOrderInfo i : infos) {
				i.setIsDel(1);
				consumerOrderInfoDao.updateById(i);
				
				Map<String,Object> m2 = new HashMap<>();
				m2.put("infoId", i.getId());
				m2.put("isDel", 0);
				List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m2);
				for(ConsumerOrderCar c : cars) {
					c.setIsDel(1);
					consumerOrderCarDao.updateById(c);
				}
			}
		}
		
		user.setId(id);
		user.setIsDel(1);
		if(consumerOrderUserDao.updateById(user)) {
			return new Result(ResultCode.CODE_STATE_200,true,"删除成功");
		}
		return new Result(ResultCode.CODE_STATE_200,false,"删除失败");
	}

	@Override
	@Transactional
	public Result cancel(Integer id)throws Exception{
		Result result = new Result();
		ConsumerOrder order = consumerOrderDao.selectById(id);
		if(order == null) {
			result.setError("你选择的订购单不存在或者已删除");
			return result;
		}
		try {
			Map<String,Object> m1 = new HashMap<>();
			m1.put("orderId", order.getId());
			m1.put("isDel", 0);
			List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m1);
			for(ConsumerOrderInfo i : infos) {
				i.setIsDel(1);
				if(!consumerOrderInfoDao.updateById(i)) {
					throw new Exception("取消购车单出错，自动回滚");
				}	
				Map<String,Object> m2 = new HashMap<>();
				m2.put("infoId", i.getId());
				m2.put("isDel", 0);
				List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m2);
				for(ConsumerOrderCar c : cars) {
					c.setIsDel(1);
					if(!consumerOrderCarDao.updateById(c)) {
						throw new Exception("取消购车单出错，自动回滚");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("取消购车单出错，自动回滚");
		}
		
		return result;
	}
	
	

	
}
