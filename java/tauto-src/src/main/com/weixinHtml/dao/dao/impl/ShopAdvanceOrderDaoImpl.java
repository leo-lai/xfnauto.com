package main.com.weixinHtml.dao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.utils.StringCode;
import main.com.weixinHtml.dao.dao.ShopAdvanceOrderDao;
import main.com.weixinHtml.dao.po.ShopAdvanceOrder;

@Repository
public class ShopAdvanceOrderDaoImpl extends BaseDaoImpl<ShopAdvanceOrder> implements ShopAdvanceOrderDao{

	@Override
	public String getCode(String phoneNumber) throws Exception {
		String orderCode = StringCode.getAdvanceOrderCode(phoneNumber);
		try {
			List<ShopAdvanceOrder> list = sqlSessionTemplate.selectList(getSqlName("selectByCode"),
					orderCode);
			if(list != null && list.size() > 0) {
				return getCode(phoneNumber); 
			}else {
				return orderCode;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectJoinAllList")), e);
		}
	}

}
