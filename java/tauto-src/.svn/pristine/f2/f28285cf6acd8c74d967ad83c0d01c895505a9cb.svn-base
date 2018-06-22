package main.com.system.dao.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import main.com.frame.constants.SqlId;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.system.dao.dao.SystemRegionDao;
import main.com.system.dao.po.SystemRegion;
import main.com.system.dao.search.SystemRegionSearch;
import main.com.system.dao.vo.SystemRegionVo;
import main.com.utils.BeanUtils;

@Repository
public class SystemRegionDaoImpl extends BaseDaoImpl<SystemRegion> implements
		SystemRegionDao {

	@Override
	public SystemRegionVo getRegion(SystemRegionSearch regionSearch)
			throws Exception {
//		Map<String, Object> params = BeanUtils.toMap(regionSearch);
		Map<String, String> params = new HashMap<String, String>();
		params.put("regionName", regionSearch.getRegionName());
		List<SystemRegionVo> regionVos = sqlSessionTemplate.selectList(getSqlName("select"),
				params);
		if(regionVos!=null && regionVos.size() > 0){
			return regionVos.get(0);
		}
		 return null;
	}

	@Override
	public SystemRegion selectById(String areaCode) throws Exception {
		try {
			SystemRegion regionVo = sqlSessionTemplate.selectOne(getSqlName(SqlId.SQL_SELECT_BY_ID),
					areaCode);
			return regionVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SystemRegion> getByParentId(String parentId) throws Exception {
		List<SystemRegion> regionVos = sqlSessionTemplate.selectList(getSqlName("selectByParentId"),
				parentId);
		return regionVos;
	}

}
