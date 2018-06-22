package main.com.system.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemUsersSearch;

public interface SystemUsersService extends BaseService<SystemUsers> {

	/**
	 * 登录
	 * @param search
	 * @param id
	 * @param result 
	 * @param request 
	 * @return
	 */
	Result login(SystemUsersSearch search,Result result,HttpSession session)throws Exception;

	/**
	 * 用户退出
	 * @param search 
	 * @param search
	 * @param id
	 * @param result
	 */
	Boolean loginOut(SystemUsersSearch search, Result result)throws Exception;

	/**
	 * 修改密码
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result changePassword(SystemUsersSearch search, Result result,HttpSession session)throws Exception;

	/**
	 * 用户添加
	 * @param search
	 * @param result
	 */
	Result addUser(SystemUsersSearch search, Result result)throws Exception;
	/**
	 * 获取用户角色和菜单
	 * @param search
	 * @param result
	 */
	Map<String, Object> getUserRoleAndMune(SystemUsersSearch search)throws Exception;

	/**
	 * 系统用户列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result userList(SystemUsersSearch search, Result result)throws Exception;

	/**
	 * 启用或禁用用户
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result userIsEnable(SystemUsersSearch search, Result result,Integer userId)throws Exception;

	/**
	 * 销售顾问下拉列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result salesList(SystemUsersSearch search, Result result)throws Exception;

	/**
	 * 重置其他用户密码
	 * @param search
	 * @param result
	 * @param session
	 * @return
	 * @throws Exception
	 */
	Result changeOtherPassword(SystemUsersSearch search, Result result, HttpSession session)throws Exception;

	/**
	 * 获取自身组织的所有成员
	 * @param search
	 * @param result
	 * @param systemUsers
	 * @return
	 * @throws Exception
	 */
	Result orgOneSelfList(SystemUsersSearch search, Result result, SystemUsers systemUsers)throws Exception;

}
