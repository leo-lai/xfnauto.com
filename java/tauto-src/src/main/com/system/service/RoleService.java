package main.com.system.service;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.Role;
import main.com.system.dao.search.RoleSearch;
import main.com.system.dao.vo.RoleVo;

public interface RoleService extends BaseService<Role> {
//	public List<EasyuiTreeNode> tree(RoleVo roleVo);
//	
//	public List<EasyuiTreeNode> tree(RoleVo roleVo,String userRoleIds);
	
//	public List<RoleVo> getRoleTreeGrid(Integer id);
	
	public RoleVo getRoleById(Integer id);
	
//	public Boolean Merge(Role role);
	
	public Boolean deleteById(Integer id);

	/**
	 * 获取角色列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public Result roleList(RoleSearch search, Result result)throws Exception;

	/**
	 * 角色添加
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public Result roleEdit(RoleSearch search, Result result)throws Exception;

	/**
	 * 角色删除
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public Result roleDelete(RoleSearch search, Result result)throws Exception;

	/**
	 * 给角色分配菜单
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public Result setRoleMenu(RoleSearch search, Result result)throws Exception;

	/**
	 * 角色列表下拉
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public Result roleListList(RoleSearch search, Result result)throws Exception;
}
