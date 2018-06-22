package main.com.customer.dao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.com.customer.dao.dao.CustomerOrderInPayDao;
import main.com.customer.dao.po.CustomerOrderInPay;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.utils.GeneralConstant;
import main.com.utils.StringCode;

@Repository
public class CustomerOrderInPayDaoImpl extends BaseDaoImpl<CustomerOrderInPay> implements CustomerOrderInPayDao{

	@Override
	public String getPayCode() throws Exception {
		String customerOrderCode = StringCode.getOtherCodes(4, GeneralConstant.OrderCodePRE.ORDER_PDD);
		try {
			List<CustomerOrderInPay> list = sqlSessionTemplate.selectList(getSqlName("selectByCode"),
					customerOrderCode);
			if(list != null && list.size() > 0) {
				return getPayCode();
			}else {
				return customerOrderCode;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectByCode")), e);
		}
	}
}
