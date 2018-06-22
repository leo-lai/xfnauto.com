package main.com.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.MenuDao;
import main.com.system.dao.po.Menu;
import main.com.system.dao.search.MenuSearch;
import main.com.system.dao.vo.MenuVo;
import main.com.system.service.ManagerMenuService;
import main.com.utils.StringUtil;

@Service
public class ManagerMenuServiceImpl extends BaseServiceImpl<Menu>implements ManagerMenuService{

	@Autowired
	MenuDao menuDao;
	
	@Override
	protected BaseDao<Menu> getBaseDao() {
		return menuDao;
	}

	@Override
	public Result menuList(MenuSearch menuSearch,Result result) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if(menuSearch.getParentId() == null){
			params.put("parentId", menuSearch.getParentId());
		}
		if(menuSearch.getRoleId() != null) {
			params.put("roleId", menuSearch.getRoleId());
		}
		List<MenuVo> menuVos = menuDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(MenuVo vo : menuVos){
			Map<String, Object> map = takeMenu(vo);
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}

	public Map<String, Object> takeMenu(MenuVo vo){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuId", vo.getMenuId());
		map.put("levelNum", vo.getLevelNum());
		map.put("MenuName", vo.getMenuName());
		map.put("parentId", vo.getParentId());
		map.put("src", vo.getSrc());
		return map;
	}

	@Override
	public Result getAllMenu(MenuSearch menuSearch,Result result) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if(menuSearch.getParentId() == null){
			params.put("parentId", 0);
		}else{
			params.put("parentId", menuSearch.getParentId());
		}
		params.put("isdelete", false);
		List<MenuVo> menus = menuDao.getUserRoleMenu(params);//
		result.setOK(ResultCode.CODE_STATE_200, "", getSyncGridTreeMap(menus, Integer.parseInt(params.get("parentId")+"")));
		return result;
	}
	
	public List<Map<String, Object>> getSyncGridTreeMap(List<MenuVo> list, Integer parentId) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for(MenuVo menuVo: list){
			int id = menuVo.getMenuId();
			int pid = menuVo.getParentId();
			if(pid == parentId){
				List<Map<String, Object>> children = getSyncGridTreeMap(list, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("menuId", menuVo.getMenuId());
				params.put("parentId", menuVo.getParentId());
				params.put("menuName", menuVo.getMenuName());
				params.put("src", menuVo.getSrc());
				params.put("children", children);
				maps.add(params);
			}
		}
		return maps;
	}

	@Override
	public Result addMenu(MenuSearch menuSearch, Result result) throws Exception {
		Menu menu = new Menu();
		if(StringUtil.isEmpty(menuSearch.getMenuName())) {
			result.setError(ResultCode.CODE_STATE_4005, "请填写菜单名称");
			return result;
		}
		if(StringUtil.isEmpty(menuSearch.getSrc())) {
			result.setError(ResultCode.CODE_STATE_4005, "请填写菜单地址");
			return result;
		}
		menu.setMenuName(menuSearch.getMenuName());
		menu.setSrc(menuSearch.getSrc());
		if(menuSearch.getParentId() == null || menuSearch.getParentId().equals(0)) {
			menu.setParentId(0);
		}else {
			Menu parent = menuDao.selectById(menuSearch.getParentId());
			if(parent == null) {
				result.setError(ResultCode.CODE_STATE_4005, "父类不存在，添加失败，请校验数据");
				return result;
			}
			menu.setParentId(menuSearch.getParentId());
		}
		
		return menuDao.insertAndResultIT(menu, result);
	}

	@Override
	public Result editMenu(MenuSearch menuSearch, Result result) throws Exception {
		Menu menu = null;
		if(menuSearch.getMenuId() == null) {
			if(StringUtil.isEmpty(menuSearch.getMenuName())) {
				result.setError(ResultCode.CODE_STATE_4005, "请填写菜单名称");
				return result;
			}
			if(StringUtil.isEmpty(menuSearch.getSrc())) {
				result.setError(ResultCode.CODE_STATE_4005, "请填写菜单地址");
				return result;
			}
			menu = new Menu();
			menu.setMenuName(menuSearch.getMenuName());
			menu.setSrc(menuSearch.getSrc());
			if(menuSearch.getParentId() == null || menuSearch.getParentId().equals(0)) {
				menu.setParentId(0);
			}else {
				Menu parent = menuDao.selectById(menuSearch.getParentId());
				if(parent == null) {
					result.setError(ResultCode.CODE_STATE_4005, "父类不存在，添加失败，请校验数据");
					return result;
				}
				menu.setParentId(menuSearch.getParentId());
			}
			if(!menuDao.insert(menu)) {
				result.setError(ResultCode.CODE_STATE_4005, "数据保存错误，请联系IT部");
				return result;
			}
		}else {
			menu = menuDao.selectById(menuSearch.getMenuId());
			if(menu == null) {
				result.setError(ResultCode.CODE_STATE_4005, "系统不存在此菜单，请重新选择");
				return result;
			}
			if(StringUtil.isNotEmpty(menuSearch.getMenuName())) {
				menu.setMenuName(menuSearch.getMenuName());
			}
			if(StringUtil.isNotEmpty(menuSearch.getSrc())) {
				menu.setSrc(menuSearch.getSrc());
			}
			if(!menuDao.updateById(menu)) {
				result.setError(ResultCode.CODE_STATE_4005, "数据更新错误，请联系IT部");
				return result;
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menuId", menu.getMenuId());
		params.put("parentId", menu.getParentId());
		params.put("menuName", menu.getMenuName());
		params.put("src", menu.getSrc());
		result.setOK(ResultCode.CODE_STATE_200, "", params);
		return result;
//		return menuDao.updateByIdAndResultIT(menu, result);
	}

	@Override
	public List<MenuVo> getUserRoleMenu(Integer userId) throws Exception {
		//查询用户菜单
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return menuDao.getUserRoleMenu(params);//查出当前角色的所有菜单
	}

	@Override
	public Result menuListTree(MenuSearch menuSearch, Result result) {
		Map<String, Object> params = new HashMap<String, Object>();
		//查出全部，不需要上级ID
//		if(menuSearch.getParentId() == null){
//			params.put("parentId", 0);
//		}else{
//			params.put("parentId", menuSearch.getParentId());
//		}
		if(menuSearch.getRoleId() != null) {
			params.put("roleId", menuSearch.getRoleId());
		}
		params.put("isdelete", false);
		List<MenuVo> menus = menuDao.select(params);//
		result.setOK(ResultCode.CODE_STATE_200, "", getSyncGridTreeMap(menus, params.get("parentId")==null?0:Integer.parseInt(params.get("parentId")+"")));
		return result;
	}

	@Override
	@Transactional
	public Result deleteMenu(MenuSearch menuSearch, Result result) {
		//根据菜单id删除关联的菜单角色关系
		if(menuSearch.getMenuId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "系统不存在此菜单，请重新选择");
			return result;
		}
		Menu menu = menuDao.selectById(menuSearch.getMenuId());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menuId", menu.getMenuId());
//		if(menuDao.deleteRoleMenu(params)) {
//			menu.setIsdelete(true);	
//		}else {
//			result.setError(ResultCode.CODE_STATE_4005, "系统删除数据出错，请联系IT部");
//			return result;
//		}
		menuDao.deleteRoleMenu(params);//有可能菜单尚未分配给角色，不需要返回真
		menu.setIsDelete(true);	
		return menuDao.updateByIdAndResultIT(menu, result);
	}
}
