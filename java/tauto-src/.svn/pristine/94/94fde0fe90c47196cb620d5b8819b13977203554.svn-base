package main.com.car.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.car.dao.dao.CarBrandDao;
import main.com.car.dao.dao.CarFamilyDao;
import main.com.car.dao.dao.CarInteriorDao;
import main.com.car.dao.po.CarBrand;
import main.com.car.dao.po.CarFamily;
import main.com.car.dao.po.CarInterior;
import main.com.car.dao.search.CarInteriorSearch;
import main.com.car.dao.vo.CarInteriorVo;
import main.com.car.service.CarInteriorService;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.utils.StringUtil;

@Service
public class CarInteriorServiceImpl extends BaseServiceImpl<CarInterior>implements CarInteriorService{

	@Autowired
	CarInteriorDao carInteriorDao;
	
	@Autowired
	CarBrandDao carBrandDao;
	
	@Autowired
	CarFamilyDao carFamilyDao;
	
	@Override
	protected BaseDao<CarInterior> getBaseDao() {
		return carInteriorDao;
	}

	@Override
	public Result carInteriorGetByBrand(CarInteriorSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		if(search.getFamilyId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择车系");
			return result;
		}
		params.put("familyId", search.getFamilyId());
		List<CarInteriorVo> interiorVos = carInteriorDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarInteriorVo interiorVo : interiorVos) {
			Map<String,Object> map = new HashMap<String, Object>();		
			map.put("familyId", interiorVo.getFamilyId());
			map.put("interiorId", interiorVo.getInteriorId());
			map.put("interiorName", interiorVo.getInteriorName());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result carInteriorEdit(CarInteriorSearch search, Result result) throws Exception {
		CarInteriorVo carInteriorVo = null;
		if(search.getInteriorId() == null) {
			carInteriorVo = new CarInteriorVo();
			if(search.getFamilyId() == null) {
				result.setError(ResultCode.CODE_STATE_4005, "请先选择车系");
				return result;
			}
			CarFamily family = carFamilyDao.selectById(search.getFamilyId());
			if(family == null) {
				result.setError(ResultCode.CODE_STATE_4005, "车系数据不存在，请重新选择");
				return result;
			}
			carInteriorVo.setFamilyId(family.getCarFamilyId());
		}else {
			carInteriorVo = carInteriorDao.selectById(search.getInteriorId());			
		}
		if(StringUtil.isNotEmpty(search.getInteriorName())) {
			carInteriorVo.setInteriorName(search.getInteriorName());
		}
		if(search.getInteriorId() == null) {
			return carInteriorDao.insertAndResultIT(carInteriorVo, result);
		}else {
			return carInteriorDao.updateByIdAndResultIT(carInteriorVo, result);
		}
	}

	@Override
	public Result carInteriorDelete(CarInteriorSearch search, Result result) throws Exception {
		if(search.getInteriorId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请先选择具体颜色数据进行操作");
			return result;
		}
		CarInteriorVo carInteriorVo = carInteriorDao.selectById(search.getInteriorId());
		carInteriorVo.setIsDelete(true);
		return carInteriorDao.updateByIdAndResultIT(carInteriorVo, result);
	}

}
