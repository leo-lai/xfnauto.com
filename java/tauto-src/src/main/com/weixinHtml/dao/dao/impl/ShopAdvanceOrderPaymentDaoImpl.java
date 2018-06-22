package main.com.weixinHtml.dao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.utils.GeneralConstant;
import main.com.utils.StringCode;
import main.com.weixinHtml.dao.dao.ShopAdvanceOrderPaymentDao;
import main.com.weixinHtml.dao.po.ShopAdvanceOrder;
import main.com.weixinHtml.dao.po.ShopAdvanceOrderPayment;

@Repository
public class ShopAdvanceOrderPaymentDaoImpl extends BaseDaoImpl<ShopAdvanceOrderPayment> implements ShopAdvanceOrderPaymentDao{

	@Override
	public String getCode() throws Exception {
		String orderCode = StringCode.getOtherCodes(4,GeneralConstant.OrderCodePRE.ORDER_AY);
		try {
			List<ShopAdvanceOrderPayment> list = sqlSessionTemplate.selectList(getSqlName("selectByCode"),
					orderCode);
			if(list != null && list.size() > 0) {
				return getCode(); 
			}else {
				return orderCode;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectJoinAllList")), e);
		}
	}

}
