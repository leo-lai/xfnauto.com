package main.com.customer.dao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.com.customer.dao.dao.CustomerOrderDao;
import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.vo.CustomerOrderVo;
import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.system.dao.po.Organization;
import main.com.utils.GeneralConstant;
import main.com.utils.StringCode;

@Repository
public class CustomerOrderDaoImpl extends BaseDaoImpl<CustomerOrder> implements CustomerOrderDao{

	@Override
	public String getCode(String phone) throws Exception {
		String customerOrderCode = StringCode.getOrderCode(phone);
		try {
			List<CustomerOrder> list = sqlSessionTemplate.selectList(getSqlName("selectByCode"),
					customerOrderCode);
			if(list != null && list.size() > 0) {
				return getCode(phone); 
			}else {
				return customerOrderCode;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectJoinAllList")), e);
		}
	}

	@Override
	public CustomerOrder selectByUsersId(Integer customerUsersId) throws Exception {
		try {
			List<CustomerOrder> list = sqlSessionTemplate.selectList(getSqlName("selectByUsersId"),
					customerUsersId);
			if(list != null && list.size() > 0) {
				return list.get(0);
			}else {
				return null;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName(SqlId.SQL_SELECT_JOIN)), e);
		}
	}

	@Override
	public CustomerOrderVo selectByIdJoinPayment(Integer customerOrderId) throws Exception {
		try {
			if(customerOrderId == null || customerOrderId <= 0) {
				return null;
			}
			return sqlSessionTemplate.selectOne(
					getSqlName("selectByIdJoinPayment"), customerOrderId);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s",
					getSqlName("selectByIdJoinPayment")), e);
		}
	}

}
