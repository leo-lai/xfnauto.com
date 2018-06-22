package main.com.customer.dao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.com.customer.dao.dao.CustomerUsersDao;
import main.com.customer.dao.po.CustomerUsers;
import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.system.dao.po.Organization;
import main.com.utils.GeneralConstant;
import main.com.utils.StringCode;

@Repository
public class CustomerUsersDaoImpl extends BaseDaoImpl<CustomerUsers> implements CustomerUsersDao{

	@Override
	public CustomerUsers selectByPhone(String phoneNumber) throws Exception {
		try {
			return sqlSessionTemplate.selectOne(
					getSqlName("selectByPhone"), phoneNumber);
		} catch (Exception e) {
			throw new DaoException(String.format("根据phoneNumber查询对象出错！语句：%s",
					getSqlName("selectByPhone")), e);
		}
	}

	@Override
	public String getCode() throws Exception {
		String customerUserCode = StringCode.getRandomCodes(GeneralConstant.orgCodeLength);
		try {
			List<Organization> list = sqlSessionTemplate.selectList(getSqlName("selectByCode"),
					customerUserCode);
			if(list != null && list.size() > 0) {
				return getCode();
			}else {
				return customerUserCode;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectJoinAllList")), e);
		}
	}

}
