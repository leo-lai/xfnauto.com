package main.com.stock.dao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.stock.dao.dao.StockStorageDao;
import main.com.stock.dao.po.StockStorage;
import main.com.stock.dao.vo.StockStorageVo;
import main.com.utils.GeneralConstant;
import main.com.utils.StringCode;

@Repository
public class StockStorageDaoImpl extends BaseDaoImpl<StockStorage> implements StockStorageDao {

	@Override
	public String getStorageCode() throws Exception {
		String storageCode = StringCode.getOtherCodes(GeneralConstant.storageCodeLength,"RK");
		List<StockStorageVo> storageVos = sqlSessionTemplate.selectList(getSqlName("selectByCode"), storageCode);
		if(storageVos != null && storageVos.size() > 0 ){
			return getStorageCode();
		}else{
			return storageCode;
		}
	}

}
