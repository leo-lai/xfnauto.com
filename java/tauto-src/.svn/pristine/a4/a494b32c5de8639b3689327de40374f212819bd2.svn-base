package main.com.system.service;

import java.util.List;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.Menu;
import main.com.system.dao.search.MenuSearch;
import main.com.system.dao.vo.MenuVo;

public interface ManagerMenuService extends BaseService<Menu>{

	/**
	 * 根据父类获取菜单
	 * @param menuSearch
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result menuList(MenuSearch menuSearch,Result result)throws Exception;

	/**
	 * 获取全部菜单列表
	 * @param menuSearch
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result getAllMenu(MenuSearch menuSearch,Result result)throws Exception;

	/**
	 * 添加菜单
	 * @param menuSearch
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result addMenu(MenuSearch menuSearch, Result result)throws Exception;

	/**
	 * 修改菜单
	 * @param menuSearch
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result editMenu(MenuSearch menuSearch, Result result)throws Exception;
	/**
	 * 获取用户菜单
	 * @param menuSearch
	 * @param result
	 * @return
	 * @throws Exception
	 */
	List<MenuVo> getUserRoleMenu(Integer userId)throws Exception;

	/**
	 * 获取树状菜单列表
	 * @param menuSearch
	 * @param result
	 * @return
	 */
	Result menuListTree(MenuSearch menuSearch, Result result);

	/**
	 * 删除菜单
	 * @param menuSearch
	 * @param result
	 * @return
	 */
	Result deleteMenu(MenuSearch menuSearch, Result result);

}
