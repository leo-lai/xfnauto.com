package main.com.weixinApp.service;

import main.com.car.dao.po.OrgCarsConfigure;
import main.com.car.dao.search.OrgCarsConfigureSearch;
import main.com.frame.domain.Result;
import main.com.frame.service.BaseService;
import main.com.system.dao.po.SystemUsers;

public interface EmployeeOrgCarsConfigureService extends BaseService<OrgCarsConfigure>{

	/**
	 * 编辑在售车辆
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result editOrgCarsConfigure(OrgCarsConfigureSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 在售车辆列表
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result orgCarsConfigureList(OrgCarsConfigureSearch search, Result result, SystemUsers users)throws Exception;

	/**
	 * 车型详情
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	Result orgCarsConfigureInfo(OrgCarsConfigureSearch search, Result result, SystemUsers users)throws Exception;

}
