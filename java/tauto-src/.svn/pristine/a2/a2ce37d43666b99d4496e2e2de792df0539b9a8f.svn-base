package main.com.weixinApp.service;

import javax.servlet.http.HttpSession;

import main.com.car.dao.search.CarBrandSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.stock.dao.search.StockCarSearch;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.SystemUsersSearch;
import main.com.weixinApp.dao.search.SalesPerformanceSearch;

public interface EmployeeUserService extends BaseService<SystemUsers>{

	/**
	 * 员工端登录
	 * @param search
	 * @param result
	 * @param session
	 * @throws Exception
	 */
	Result login(SystemUsersSearch search, Result result, HttpSession session)throws Exception;

	/**
	 * 登出
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result loginOut(SystemUsersSearch search, Result result)throws Exception;

	/**
	 * 修改密码
	 * @param search
	 * @param result
	 * @param session
	 * @throws Exception
	 */
	Result changePassword(SystemUsersSearch search, Result result, HttpSession session)throws Exception;

	/**
	 * 获取销售顾问列表
	 * @param search
	 * @param result
	 * @return
	 * @throws Exception
	 */
	Result salesList(SystemUsersSearch search, Result result,SystemUsers systemUsers)throws Exception;

	/**
	 * 只获取自身组织的人员
	 * @param search
	 * @param result
	 * @param systemUsers
	 * @return
	 */
	Result orgOneSelfList(SystemUsersSearch search, Result result, SystemUsers systemUsers)throws Exception;
	
	/**
	 * 查询销售业绩
	 * @param search
	 * @return
	 * @throws Exception
	 */
	Result querySalesPerformance(SalesPerformanceSearch search) throws Exception;

	/**
	 * 司机列表
	 * @param search
	 * @param result
	 * @param systemUsers
	 * @return
	 */
	Result orgOneSelfDriverList(SystemUsersSearch search, Result result, SystemUsers systemUsers);

	/**
	 * 我的微信名片
	 * @param search
	 * @param result
	 * @param systemUsers
	 * @return
	 * @throws Exception
	 */
	Result weixinQrImage(SystemUsersSearch search, Result result, SystemUsers systemUsers)throws Exception;
}
