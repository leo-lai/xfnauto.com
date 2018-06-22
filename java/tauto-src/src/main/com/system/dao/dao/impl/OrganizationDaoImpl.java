package main.com.system.dao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.vo.OrganizationVo;
import main.com.utils.GeneralConstant;
import main.com.utils.StringCode;

@Repository
public class OrganizationDaoImpl extends BaseDaoImpl<Organization> implements OrganizationDao {

	@Override
	public List<OrganizationVo> selectChildren(Integer orgId) throws Exception {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectJoinAllList"),
					orgId);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectJoinAllList")), e);
		}
	}

	@Override
	public List<OrganizationVo> getByParentId(Integer orgId) throws Exception {
		try {
			return sqlSessionTemplate.selectList(getSqlName("getByParentId"),
					orgId);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectJoinAllList")), e);
		}
	}

	@Override
	public String getorgCode() throws Exception {
		String orgCode = StringCode.getRandomCodes(GeneralConstant.orgCodeLength);
		try {
			List<Organization> list = sqlSessionTemplate.selectList(getSqlName("selectByCode"),
					orgCode);
			if(list != null && list.size() > 0) {
				return getorgCode();
			}else {
				return orgCode;
			}
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s",
					getSqlName("selectJoinAllList")), e);
		}
	}
}
