package main.com.system.dao.dao;

import java.util.Map;

import main.com.frame.dao.BaseDao;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemUsersSearch;

/**
 * 系统用户操作类接口
 */
public interface SystemUsersDao extends BaseDao<SystemUsers> {

	/**
	 * 查询关联关系的页数总数
	 * @param params
	 * @return
	 */
	Long selectCountJoin(Map<String, Object> params)throws Exception;

	/**
	 * 写入用户角色中间表
	 * @param params
	 * @throws Exception
	 */
	void insertRoleUser(Map<String, Object> params)throws Exception;

	/**
	 * 更新中间表
	 * @param params
	 * @throws Exception
	 */
	void updateRoleUserById(Map<String, Object> params)throws Exception;
	
	/**
	 * 根据ID查询，避免了删除和禁用的用户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	SystemUsers selectByIdInUse(Integer userId)throws Exception;
	
	/**
	 * 生成新的UserCode
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public String getUserCode()throws Exception;
}
