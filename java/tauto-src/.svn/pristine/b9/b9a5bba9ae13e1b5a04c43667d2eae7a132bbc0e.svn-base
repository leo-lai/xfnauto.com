package main.com.car.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.car.dao.dao.CarBrandDao;
import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarFamilyDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.po.CarBrand;
import main.com.car.dao.po.CarColour;
import main.com.car.dao.po.CarFamily;
import main.com.car.dao.search.CarColourSearch;
import main.com.car.dao.vo.CarColourVo;
import main.com.car.dao.vo.CarFamilyVo;
import main.com.car.dao.vo.CarsVo;
import main.com.car.service.CarColourService;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.utils.StringUtil;

@Service
public class CarColourServiceImpl extends BaseServiceImpl<CarColour>implements CarColourService{

	@Autowired
	CarColourDao carColourDao;
	
	@Autowired
	CarBrandDao carBrandDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CarFamilyDao carFamilyDao;
	
	@Override
	protected BaseDao<CarColour> getBaseDao() {	
		return carColourDao;
	}

	@Override
	public Result carColourGetBrand(CarColourSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		if(search.getFamilyId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择车系");
			return result;
		}
		params.put("familyId", search.getFamilyId());
		List<CarColourVo> colourVos = carColourDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarColourVo colourVo : colourVos) {
			Map<String,Object> map = new HashMap<String, Object>();		
			map.put("familyId", colourVo.getFamilyId());
			map.put("carColourId", colourVo.getCarColourId());
			map.put("carColourName", colourVo.getCarColourName());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result carColourEdit(CarColourSearch search, Result result) throws Exception {
		CarColour carColour = null;
		if(search.getCarColourId() == null) {
			carColour = new CarColour();
			if(search.getFamilyId() == null) {
				result.setError(ResultCode.CODE_STATE_4005, "请先选择车系");
				return result;
			}
			CarFamilyVo familyVo = carFamilyDao.selectById(search.getFamilyId());
			if(familyVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你选择的车系数据不存在，请重新选择");
				return result;
			}
			carColour.setFamilyId(familyVo.getCarFamilyId());
		}else {
			carColour = carColourDao.selectById(search.getCarColourId());			
		}
		if(StringUtil.isNotEmpty(search.getCarColourName())) {
			carColour.setCarColourName(search.getCarColourName());
		}
		Map<String,Object> map = new HashMap<String, Object>();		
		if(search.getCarColourId() == null) {
			if(carColourDao.insert(carColour)) {
				map.put("familyId", carColour.getFamilyId());
				map.put("carColourId", carColour.getCarColourId());
				map.put("carColourName", carColour.getCarColourName());
				result.setOK(ResultCode.CODE_STATE_200, "操作成功", map);			
			}else {
				result.setError("保存数据失败，请联系IT部");		
			}
		}else {
			if(carColourDao.updateById(carColour)) {
				map.put("familyId", carColour.getFamilyId());
				map.put("carColourId", carColour.getCarColourId());
				map.put("carColourName", carColour.getCarColourName());
				result.setOK(ResultCode.CODE_STATE_200, "操作成功", map);
			}else {
				result.setError("保存数据失败，请联系IT部");	
			}
		}
		return result;
	}

	@Override
	public Result carColourDelete(CarColourSearch search, Result result) throws Exception {
		if(search.getCarColourId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请先选择具体颜色数据进行操作");
			return result;
		}
		CarColour carColour = carColourDao.selectById(search.getCarColourId());
		carColour.setIsDelete(true);
		return carColourDao.updateByIdAndResultIT(carColour, result);
	}

	@Override
	public Result carColourGetByCars(CarColourSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		if(search.getCarsId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请先选择具体车型");
			return result;
		}
		CarsVo carsVo = carsDao.selectById(search.getCarsId());
		if(carsVo.getFamilyId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "此车型没有对应的车系，数据不完整，请先完善数据");
			return result;
		}
		params.put("familyId", search.getFamilyId());
		List<CarColourVo> colourVos = carColourDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarColourVo colourVo : colourVos) {
			Map<String,Object> map = new HashMap<String, Object>();		
			map.put("carFamilyId", colourVo.getFamilyId());
			map.put("carColourId", colourVo.getCarColourId());
			map.put("carColourName", colourVo.getCarColourName());
			map.put("carsId", carsVo.getCarId());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

}
