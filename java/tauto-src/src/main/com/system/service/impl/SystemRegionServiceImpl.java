package main.com.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.frame.base.Page;
import main.com.frame.dao.BaseDao;
import main.com.frame.dao.impl.BaseDaoImpl;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.SystemRegionDao;
import main.com.system.dao.po.SystemRegion;
import main.com.system.dao.search.SystemRegionSearch;
import main.com.system.dao.vo.SystemRegionVo;
import main.com.system.service.SystemRegionService;
import main.com.utils.StringUtil;

@Service
public class SystemRegionServiceImpl
		extends BaseServiceImpl<SystemRegion>implements SystemRegionService {

	@Autowired
	SystemRegionDao systemRegionDao;
	
	@Override
	protected BaseDao<SystemRegion> getBaseDao() {
		return systemRegionDao;
	}

	@Override
	public SystemRegionVo getRegion(SystemRegionSearch regionSearch)
			throws Exception {
		return systemRegionDao.getRegion(regionSearch);
	}

	@Override
	public Result getRegionOfParentId(SystemRegionSearch search)
			throws Exception {
		Result result = new Result();
		if(StringUtil.isEmpty(search.getParentId()+"")){
			result.setError(ResultCode.CODE_STATE_4005, "参数错误");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		if(search.getParentId() == null){
			search.setParentId(0);//默认获取省一级
			search.setLevel(1);//默认获取省一级
			params.put("level", search.getLevel());
		}
		params.put("parentId", search.getParentId());
		List<SystemRegionVo> regionVos = systemRegionDao.select(params);
		if(regionVos == null){ //直辖市没有二级
			search.setLevel(1);
			search.setRegionId(String.valueOf(search.getParentId()));
			search.setParentId(null);//默认获取省一级
			params.put("level", search.getLevel());
			params.put("parentId", 0);
			regionVos = systemRegionDao.select(params);
		}
		JSONObject json = new JSONObject();
		json.put("Date", regionVos);
		result.setOK(ResultCode.CODE_STATE_200, "", json);
		return result;
	}

	/**
	 * 只执行一次，无需优化
	 */
	@Override
	public List<SystemRegion> getAll() throws Exception {
//		List<SystemRegion> all = new ArrayList<SystemRegion>();
		//查询父类
		List<SystemRegion> regionsFrist = systemRegionDao.getByParentId(0+"");
		//查询二级
		for(SystemRegion systemRegion : regionsFrist){
			List<SystemRegion> regionsSecond = systemRegionDao.getByParentId(systemRegion.getRegionId());
			for(SystemRegion systemRegionSecond : regionsSecond){
				//查询三级
				List<SystemRegion> regionsThree = systemRegionDao.getByParentId(systemRegionSecond.getRegionId());
				systemRegionSecond.setChildrenRegion(regionsThree);
			}
			systemRegion.setChildrenRegion(regionsSecond);
		}
		
		return regionsFrist;
	}

}
