package main.com.system.dao.dao;

import java.util.List;
import java.util.Map;

import main.com.frame.dao.BaseDao;
import main.com.system.dao.po.Menu;
import main.com.system.dao.search.MenuSearch;
import main.com.system.dao.vo.MenuVo;


public interface MenuDao extends BaseDao<Menu> {
	/**
	 * 批量插入角色菜单
	 * @param roleMenuList
	 * @return
	 */
	public Boolean batchInsertRoleMenu(List<Map<String, Object>> roleMenuList);
	
	/**
	 * 通过角色id删除角色菜单
	 * @param roleId
	 * @return
	 */
	public Boolean deleteRoleMenuByRoleId(Integer roleId);
	
	/**
	 * 组合条件删除角色菜单
	 * @param params
	 * @return
	 */
	public Boolean deleteRoleMenu(Map<String, Object> params);
	
	/**
	 * 获取用户角色菜单
	 * @param params
	 * @return
	 */
	public List<MenuVo> getUserRoleMenu(Map<String, Object> params);
	/**
	 * 获取多层菜单
	 * @param params
	 * @return
	 */
	public List<MenuVo> selectMax(Map<String, Object> params);
	
}
