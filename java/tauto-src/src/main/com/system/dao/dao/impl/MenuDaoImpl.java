package main.com.system.dao.dao.impl;

import java.util.List;
import java.util.Map;

import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.system.dao.dao.MenuDao;
import main.com.system.dao.po.Menu;
import main.com.system.dao.search.MenuSearch;
import main.com.system.dao.vo.MenuVo;

import org.springframework.stereotype.Repository;


@Repository
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {

	@Override
	public Boolean batchInsertRoleMenu(List<Map<String, Object>> roleMenuList) {
		int rows = this.sqlSessionTemplate.insert(this.getSqlName("batchInsertRoleMenu"), roleMenuList);
		if(rows > 0) return true;
		return false;
	}
	
	@Override
	public Boolean deleteRoleMenuByRoleId(Integer roleId){
		int row = this.sqlSessionTemplate.delete(this.getSqlName("deleteRoleMenuByRoleId"), roleId);
		if(row>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Boolean deleteRoleMenu(Map<String, Object> params) {
		int row = this.sqlSessionTemplate.delete(this.getSqlName("deleteRoleMenu"), params);
		if(row>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<MenuVo> getUserRoleMenu(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList(this.getSqlName("getUserRoleMenu"), params);
	}
	
	@Override
	public List<MenuVo> selectMax(Map<String, Object> params) {
		return this.sqlSessionTemplate.selectList(this.getSqlName("selectMax"), params);
	}
}
