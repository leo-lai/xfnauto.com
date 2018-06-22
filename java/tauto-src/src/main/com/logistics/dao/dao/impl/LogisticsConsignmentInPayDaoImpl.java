package main.com.logistics.dao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.logistics.dao.dao.LogisticsConsignmentInPayDao;
import main.com.logistics.dao.po.LogisticsConsignment;
import main.com.logistics.dao.po.LogisticsConsignmentInPay;
import main.com.utils.GeneralConstant;
import main.com.utils.StringCode;

@Repository
public class LogisticsConsignmentInPayDaoImpl extends BaseDaoImpl<LogisticsConsignmentInPay> implements LogisticsConsignmentInPayDao{

	@Override
	public String getCode() throws Exception {
		String consignmentCode = StringCode.getOtherCodes(4, GeneralConstant.OrderCodePRE.ORDER_PT);
		try {
			List<LogisticsConsignmentInPay> list = sqlSessionTemplate.selectList(getSqlName("selectByCode"),
					consignmentCode);
			if(list != null && list.size() > 0) {
				return getCode(); 
			}else {
				return consignmentCode;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectByCode")), e);
		}
	}

}
