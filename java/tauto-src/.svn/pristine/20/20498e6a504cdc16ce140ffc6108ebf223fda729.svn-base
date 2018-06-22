package main.com.system.dao.dao;

import java.util.List;

import main.com.frame.dao.BaseDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.vo.OrganizationVo;

public interface OrganizationDao extends BaseDao<Organization>{

	/**
	 * 获取所有子类
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<OrganizationVo> selectChildren(Integer orgId)throws Exception;

	/**
	 * 根据父类查询子类
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	List<OrganizationVo> getByParentId(Integer orgId)throws Exception;

	/**
	 * 根据获取Code
	 * @param randomCodes
	 * @return
	 * @throws Exception
	 */
	String getorgCode()throws Exception;

}
