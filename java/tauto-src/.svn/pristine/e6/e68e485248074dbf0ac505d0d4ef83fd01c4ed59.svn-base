package main.com.stock.dao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.stock.dao.dao.StockOrderDao;
import main.com.stock.dao.po.StockOrder;
import main.com.stock.dao.vo.StockStorageVo;
import main.com.utils.GeneralConstant;
import main.com.utils.StringCode;

@Repository
public class StockOrderDaoImpl extends BaseDaoImpl<StockOrder> implements StockOrderDao {

	@Override
	public String getOrderCode() throws Exception {
		String orderCode = StringCode.getOtherCodes(GeneralConstant.storageCodeLength,"DC");
		List<StockStorageVo> storageVos = sqlSessionTemplate.selectList(getSqlName("selectByCode"), orderCode);
		if(storageVos != null && storageVos.size() > 0 ){
			return getOrderCode();
		}else{
			return orderCode;
		}
	}
}
