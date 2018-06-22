package main.com.system.dao.dao.impl;

import java.util.List;
import java.util.Map;

import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.exception.DaoException;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemUsersSearch;
import main.com.system.dao.search.UserSearch;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.utils.GeneralConstant;
import main.com.utils.StringCode;

import org.springframework.stereotype.Repository;

@Repository
public class SystemUsersDaoImpl extends BaseDaoImpl<SystemUsers> implements SystemUsersDao {

	@Override
	public Long selectCountJoin(Map<String, Object> params) {
		try {
//			Map<String, Object> params = BeanUtils.toMap(search);
			return sqlSessionTemplate.selectOne(getSqlName("selectCountJoin"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(SqlId.SQL_SELECT_COUNT)), e);
		}
	}

	@Override
	public void insertRoleUser(Map<String, Object> params) throws Exception {
		try {
			int rows = sqlSessionTemplate.insert(getSqlName("insertRoleUser"),
					params);
		} catch (Exception e) {
			throw new DaoException(String.format("添加对象出错！语句：%s",
					getSqlName(SqlId.SQL_INSERT)), e);
		}
	}

	@Override
	public void updateRoleUserById(Map<String, Object> params) throws Exception {
		try {
			int rows = sqlSessionTemplate.update(getSqlName("updateRoleUserById"),
					params);
		} catch (Exception e) {
			throw new DaoException(String.format("添加对象出错！语句：%s",
					getSqlName(SqlId.SQL_INSERT)), e);
		}
	}

	@Override
	public SystemUsers selectByIdInUse(Integer userId) throws Exception {
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectByIdInUse"), userId);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName("selectByIdInUse")), e);
		}
	}

	@Override
	public String getUserCode() throws Exception {
		String userCode = StringCode.getUserCodes(GeneralConstant.userCodeLength);
		List<SystemUsersVo> systemUsersVos = sqlSessionTemplate.selectList(getSqlName("selectByUserCode"), userCode);
		if(systemUsersVos != null && systemUsersVos.size() > 0 ){
			return getUserCode();
		}else{
			return userCode;
		}
	}
}
