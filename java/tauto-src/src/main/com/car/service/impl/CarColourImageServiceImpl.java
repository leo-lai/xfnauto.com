package main.com.car.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarColourImageDao;
import main.com.car.dao.po.CarColourImage;
import main.com.car.dao.search.CarColourImageSearch;
import main.com.car.dao.vo.CarColourImageVo;
import main.com.car.service.CarColourImageService;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.utils.StringUtil;

@Service
public class CarColourImageServiceImpl extends BaseServiceImpl<CarColourImage>implements CarColourImageService{

	@Autowired
	CarColourImageDao carColourImageDao;
	
	@Autowired
	CarColourDao carColourDao;
	
	@Override
	protected BaseDao<CarColourImage> getBaseDao() {
		return carColourImageDao;
	}

	@Override
	public Result carColourImageGetByCarColour(CarColourImageSearch search, Result result) throws Exception {
		//校验颜色
		if(search.getCarColourId() == null) {
			
		}
		if(search.getCarsId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择车类型");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("carColourId", search.getCarColourId());
		params.put("carsId", search.getCarsId());
		List<CarColourImageVo> carColourImageVos = carColourImageDao.select(params);
		if(carColourImageVos == null || carColourImageVos.size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "请求成功");
			return result;
		}
		CarColourImageVo imageVo = carColourImageVos.get(0);
//		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
//		for(CarColourImageVo imageVo : carColourImageVos) {
			Map<String,Object> map = new HashMap<String, Object>();	
			map.put("carColourImageId", imageVo.getCarColourImageId());
			map.put("imagePath", imageVo.getImagePath());
			map.put("carColourId", imageVo.getCarColourId());
			map.put("carsId", imageVo.getCarsId());
//			maps.add(map);
//		}
//		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",map);
		return result;
	}

	@Override
	public Result carColourImageAdd(CarColourImageSearch search, Result result) throws Exception {
		CarColourImageVo colourImageVo = null;
		if(StringUtil.isEmpty(search.getCarColourImageId())) {
			colourImageVo = new CarColourImageVo();
			if(search.getCarColourId() == null) {
				result.setError(ResultCode.CODE_STATE_4005, "图片所属颜色不明确");
				return result;
			}
			if(search.getCarsId() == null) {
				result.setError(ResultCode.CODE_STATE_4005, "图片所属车型不明确");
				return result;
			}
			if(StringUtil.isEmpty(search.getImagePath())) {
				result.setError(ResultCode.CODE_STATE_4005, "请上传图片");
				return result;
			}
			colourImageVo.setCarColourId(search.getCarColourId());
			colourImageVo.setCarsId(search.getCarsId());
		}else {
			colourImageVo = carColourImageDao.selectById(search.getCarColourImageId());
			if(colourImageVo == null) {
				result.setError("你选择的图片相关数据不存在或者已删除，请重新选择");
				return result;
			}
		}
		if(StringUtil.isEmpty(search.getImagePath())) {
			result.setError(ResultCode.CODE_STATE_4005, "请上传至少一张图片");
			return result;
		}
		colourImageVo.setImagePath(search.getImagePath());
		if(StringUtil.isEmpty(search.getCarColourImageId())) {
			return carColourImageDao.insertAndResultIT(colourImageVo, result);
		}else {
			return carColourImageDao.updateByIdAndResultIT(colourImageVo, result);
		}
		
	}

	@Override
	public Result carColourImageDelete(CarColourImageSearch search, Result result) throws Exception {
		if(search.getCarColourImageId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体图片进行操作");
			return result;
		}
		CarColourImageVo colourImageVo = carColourImageDao.selectById(search.getCarColourImageId());
		if(colourImageVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "所选择的图片不存在，请重新选择");
			return result;
		}
		colourImageVo.setIsDelete(true);
		return carColourImageDao.updateByIdAndResultIT(colourImageVo, result);
	}

	@Override
	public Result carColourImageEdit(CarColourImageSearch search, Result result) throws Exception {
		if(search.getCarColourImageId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体图片进行操作");
			return result;
		}
		CarColourImageVo colourImageVo = carColourImageDao.selectById(search.getCarColourImageId());
		if(colourImageVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "所选择的图片不存在，请重新选择");
			return result;
		}
		if(StringUtil.isEmpty(search.getImagePath())) {
			result.setError(ResultCode.CODE_STATE_4005, "请上传至少一张图片");
			return result;
		}
		colourImageVo.setImagePath(search.getImagePath());
		return carColourImageDao.updateByIdAndResultIT(colourImageVo, result);
	}

}
