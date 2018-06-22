package main.com.system.dao.dao.impl;

import java.util.List;

import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.system.dao.dao.RoleDao;
import main.com.system.dao.po.Role;
import main.com.system.dao.search.RoleSearch;
import main.com.system.dao.vo.RoleVo;

import org.springframework.stereotype.Repository;


@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

//	@Override
//	public List<RoleVo> selectByUserId(Integer userId) {
//		return this.sqlSessionTemplate.selectList(this.getSqlName("selectByUserId"), userId);
//	}

}
