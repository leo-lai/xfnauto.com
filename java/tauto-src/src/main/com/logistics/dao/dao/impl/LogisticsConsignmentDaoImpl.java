package main.com.logistics.dao.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.logistics.dao.dao.LogisticsConsignmentDao;
import main.com.logistics.dao.po.LogisticsConsignment;
import main.com.logistics.dao.po.LogisticsDistribution;
import main.com.utils.StringCode;

@Repository
public class LogisticsConsignmentDaoImpl extends BaseDaoImpl<LogisticsConsignment> implements LogisticsConsignmentDao{

	@Override
	public Long getCountJoin(Map<String, Object> params) throws Exception {
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectCountJoin"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName("selectCountJoin")), e);
		}
	}

	@Override
	public String getCode() throws Exception {
		String consignmentCode = StringCode.getOtherCodes(4, "TY");
		try {
			List<LogisticsConsignment> list = sqlSessionTemplate.selectList(getSqlName("selectByCode"),
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
	
	@Override
	public List<LogisticsConsignment> listLogisticsConsignment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getSqlName("listLogisticsConsignment"),map);
	}
}
