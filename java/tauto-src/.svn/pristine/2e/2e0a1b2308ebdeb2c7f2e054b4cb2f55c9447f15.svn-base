package main.com.car.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.car.dao.dao.CarBrandDao;
import main.com.car.dao.dao.CarExpectWayDao;
import main.com.car.dao.dao.CarFamilyDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.dao.CarsStyleDao;
import main.com.car.dao.po.CarBrand;
import main.com.car.dao.po.CarFamily;
import main.com.car.dao.po.Cars;
import main.com.car.dao.po.CarsStyle;
import main.com.car.dao.search.CarBrandSearch;
import main.com.car.dao.search.CarExpectWaySearch;
import main.com.car.dao.search.CarFamilySearch;
import main.com.car.dao.search.CarsSearch;
import main.com.car.dao.search.CarsStyleSearch;
import main.com.car.dao.vo.CarBrandVo;
import main.com.car.dao.vo.CarExpectWayVo;
import main.com.car.dao.vo.CarFamilyVo;
import main.com.car.dao.vo.CarsStyleVo;
import main.com.car.service.CarsService;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class CarsServiceImpl extends BaseServiceImpl<Cars>implements CarsService{

	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CarBrandDao carBrandDao;
	
	@Autowired
	CarFamilyDao carFamilyDao;
	
	@Autowired
	CarsStyleDao carStyleDao;
	
	@Autowired
	CarExpectWayDao carExpectWayDao;
	
	@Override
	protected BaseDao<Cars> getBaseDao() {
		return carsDao;
	}

	@Override
	public Result carsList(CarsSearch search, Result result) throws Exception {
		Map<String,Object> params = getParams(search);
		List<Cars> carsList = carsDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = carsDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(Cars cars : carsList) {
			Map<String,Object> map = new HashMap<String, Object>();
			//品牌(本田) + 车系（雅阁） + 年款（2016/2017） + 排量(2.0) + 变速箱类型（手动自动） + 款式（高中低配）
			StringBuffer buffer = new StringBuffer();
			buffer.append(cars.getBrandName()).append(" ");
			buffer.append(cars.getFamilyName()).append(" ");
			buffer.append(cars.getYearPattern()+"款").append(" ");
			buffer.append(cars.getPl()).append(" ");
			buffer.append(cars.getGearboxName()).append(cars.getStyleName());
			map.put("carsName",buffer);
			map.put("bareCarPrice", cars.getBareCarPrice());//裸车价
			map.put("price", cars.getPrice());//裸车价
			map.put("carsId", cars.getCarId());
			map.put("bareCarPrice", cars.getBareCarPrice());
			map.put("brandCode", cars.getBrandCode());
			map.put("brandId", cars.getBrandId());
			map.put("carId", cars.getCarId());
			map.put("comprehensivePrice", cars.getComprehensivePrice());
			map.put("emissionStandard", cars.getEmissionStandard());
			map.put("engineDesc", cars.getEngineDesc());
			map.put("engineModel", cars.getEngineModel());
			map.put("familyId", cars.getFamilyId());
			map.put("familyName", cars.getFamilyName());
			map.put("gearboxName", cars.getGearboxName());
			map.put("gearNum", cars.getGearNum());
			map.put("inairform", cars.getInairform());
			map.put("introduce", cars.getIntroduce());
			map.put("marketDate", cars.getMarketDate());
			map.put("oilConsumption", cars.getOilConsumption()!= null?Float.parseFloat(cars.getOilConsumption()):cars.getOilConsumption());
			map.put("pl", cars.getPl());
			map.put("powerType", cars.getPowerType());
			map.put("price", cars.getPrice());
			map.put("seat", cars.getSeat());
			map.put("styleId", cars.getStyleId());
			map.put("styleName", cars.getStyleName());
			map.put("supplyOil", cars.getSupplyOil());
			map.put("vehicleName", cars.getVehicleName());
			map.put("wheelbase", cars.getWheelbase());
			map.put("yearPattern", cars.getYearPattern()!=null? Integer.parseInt(cars.getYearPattern()):cars.getYearPattern());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}
	
	public Map<String,Object> getParams(CarsSearch search){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getCarsName())) {
			params.put("carsName", search.getCarsName());
		}
		if(StringUtil.isNotEmpty(search.getSearchText())) {
			params.put("searchText", search.getSearchText());
		}
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	@Transactional
	public Result carsEdit(CarsSearch search, Result result) throws Exception {
		Cars cars = null;
		if(search.getCarId() == null) {
			cars = new Cars();
		}else {
			cars = carsDao.selectById(search.getCarId());
			if(cars == null) {
				result.setError(ResultCode.CODE_STATE_4005, "主键为："+search.getCarId()+"的车辆类型数据不存在，请核对数据");
				return result;
			}
		}
		if(search.getBrandId() != null) {
			CarBrandVo carBrand = carBrandDao.selectById(search.getBrandId());
			if(carBrand == null) {
				result.setError(ResultCode.CODE_STATE_4005, "主键为："+search.getBrandId()+"的品牌数据不存在，请核对数据");
				return result;
			}
			cars.setBrandId(carBrand.getBrandId());
			cars.setBrandName(carBrand.getBrandName());
		}
		if(search.getFamilyId() != null) {
			CarFamilyVo carFamily = carFamilyDao.selectById(search.getFamilyId());
			if(carFamily == null) {
				result.setError(ResultCode.CODE_STATE_4005, "主键为："+search.getFamilyId()+"的车系数据不存在，请核对数据");
				return result;
			}
			cars.setFamilyId(carFamily.getCarFamilyId());
			cars.setFamilyName(carFamily.getCarFamilyName());
		}
		if(StringUtil.isNotEmpty(search.getYearPattern())) {
			if(!StringUtil.isNumeric(search.getYearPattern())) {
				result.setError(ResultCode.CODE_STATE_4005, "年款输入格式错误，请输入年份，如：2017");
				return result;
			}
			cars.setYearPattern(search.getYearPattern());
		}
		if(StringUtil.isNotEmpty(search.getPl())) {
			cars.setPl(search.getPl().toUpperCase());
		}
		if(StringUtil.isNotEmpty(search.getGearboxName())) {
			cars.setGearboxName(search.getGearboxName());
		}
		if(search.getStyleId() != null) {
			CarsStyleVo carsStyle = carStyleDao.selectById(search.getStyleId());
			if(carsStyle == null) {
				result.setError(ResultCode.CODE_STATE_4005, "主键为："+search.getStyleId()+"的车系数据不存在，请核对数据");
				return result;
			}
			cars.setStyleId(carsStyle.getCarStyleId());
			cars.setStyleName(carsStyle.getCarStyleName());
		}
		
		if(search.getBareCarPrice() != null) {
			cars.setBareCarPrice(search.getBareCarPrice());
		}
		if(search.getPrice() != null) {
			cars.setPrice(search.getPrice());
		}
		
		if(search.getSeat() != null) {
			cars.setSeat(search.getSeat());
		}
		if(StringUtil.isNotEmpty(search.getIntroduce())) {
			cars.setIntroduce(search.getIntroduce());
		}
		if(StringUtil.isNotEmpty(search.getMarketDate())) {
			cars.setMarketDate(search.getMarketDate());
		}
		if(StringUtil.isNotEmpty(search.getVehicleName())) {
			cars.setVehicleName(search.getVehicleName());
		}
		if(StringUtil.isNotEmpty(search.getOilConsumption())) {
//			if(search.getOilConsumption().indexOf("L/100KM") <= -1 || search.getOilConsumption().indexOf("l/100km") <= -1) {
//				
//			}
			cars.setOilConsumption(search.getOilConsumption());// + "L/100KM");
//			cars.setOilConsumption(search.getOilConsumption().toUpperCase());
		}
		//品牌(本田) + 车系（雅阁） + 年款（2016/2017） + 排量(2.0) + 变速箱类型（手动自动） + 款式（高中低配）
		StringBuffer buffer = new StringBuffer();
		buffer.append(cars.getBrandName());
		buffer.append(cars.getFamilyName());
		buffer.append(cars.getYearPattern()+"款");
		buffer.append(cars.getPl());
		buffer.append(cars.getGearboxName()).append(cars.getStyleName());
		cars.setCarName(buffer.toString());
		if(search.getCarId() == null) {
			return carsDao.insertAndResultIT(cars, result);
		}else {
			return carsDao.updateByIdAndResultIT(cars, result);

		}
	}

	@Override
	public Result carsBrandEdit(CarBrandSearch search, Result result) throws Exception {
		CarBrand carBrand = null;
		if(search.getBrandId() == null) {
			carBrand = new CarBrand();
		}else {
			carBrand = carBrandDao.selectById(search.getBrandId());
			if(carBrand == null) {
				result.setError(ResultCode.CODE_STATE_4005, "数据错误");
				return result;
			}
		}
		if(StringUtil.isEmpty(search.getBrandName())) {
			result.setError(ResultCode.CODE_STATE_4005, "请输入品牌名称");
			return result;
		}
		carBrand.setBrandName(search.getBrandName());
		if(search.getBrandId() == null) {
			return carBrandDao.insertAndResultIT(carBrand, result);
		}else {
			return carBrandDao.updateByIdAndResultIT(carBrand, result);

		}
	}

	@Override
	public Result carsFamilyEdit(CarFamilySearch search, Result result) throws Exception {
		CarFamily carFamily = null;
		if(search.getCarFamilyId() == null) {
			carFamily = new CarFamily();
		}else {
			carFamily = carFamilyDao.selectById(search.getCarFamilyId());
			if(carFamily == null) {
				result.setError(ResultCode.CODE_STATE_4005, "数据错误");
				return result;
			}
			if(search.getBrandId() == null) {
				search.setBrandId(carFamily.getBrandId());
			}
		}
		if(search.getBrandId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "所属品牌信息不明确，操作失败");
			return result;
		}
		carFamily.setBrandId(search.getBrandId());
		carFamily.setCarFamilyName(search.getCarFamilyName());
		if(search.getCarFamilyId() == null) {
			return carFamilyDao.insertAndResultIT(carFamily, result);
		}else {
			return carFamilyDao.updateByIdAndResultIT(carFamily, result);

		}
	}

	@Override
	public Result carsStyleEdit(CarsStyleSearch search, Result result) throws Exception {
		CarsStyle carsStyle = null;
		if(search.getCarStyleId() == null) {
			carsStyle = new CarsStyle();
		}else {
			carsStyle = carStyleDao.selectById(search.getCarStyleId());
			if(carsStyle == null) {
				result.setError(ResultCode.CODE_STATE_4005, "数据错误");
				return result;
			}
			if(search.getFamilyId() == null) {
				search.setFamilyId(carsStyle.getFamilyId());
			}
		}
		if(search.getFamilyId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "所属车系信息不明确，操作失败");
			return result;
		}
		carsStyle.setFamilyId(search.getFamilyId());
		carsStyle.setCarStyleName(search.getCarStyleName());
		if(search.getCarStyleId() == null) {
			return carStyleDao.insertAndResultIT(carsStyle, result);
		}else {
			return carStyleDao.updateByIdAndResultIT(carsStyle, result);

		}
	}

	@Override
	public Result carsDelete(CarsSearch search, Result result) throws Exception {
		Cars cars = carsDao.selectById(search.getCarId());
		if(cars == null) {
			result.setError(ResultCode.CODE_STATE_4005, "主键为："+search.getCarId()+"的车辆类型数据不存在，请核对数据");
			return result;
		}
		cars.setIsDelete(true);
		return carsDao.updateByIdAndResultIT(cars, result);
	}

	@Override
	public Result carsBrandList(CarBrandSearch search, Result result) {
		List<CarBrandVo> carBrands = carBrandDao.select(new HashMap<String,Object>());
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarBrandVo brandVo : carBrands) {
			Map<String,Object> map = new HashMap<String, Object>();		
			map.put("id", brandVo.getBrandId());
			map.put("name", brandVo.getBrandName());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result carsFamilyList(CarFamilySearch search, Result result) throws Exception {
		Map<String, Object> params =  new HashMap<String,Object>();
		if(search.getBrandId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请先选择品牌");
			return result;
		}
		params.put("brandId", search.getBrandId());
		List<CarFamilyVo> carFamilyVos = carFamilyDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarFamilyVo carFamilyVo : carFamilyVos) {
			Map<String,Object> map = new HashMap<String, Object>();		
			map.put("pId", carFamilyVo.getBrandId());
			map.put("id", carFamilyVo.getCarFamilyId());
			map.put("name", carFamilyVo.getCarFamilyName());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result carsListList(CarsSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		if(search.getFamilyId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请先选择车系");
			return result;
		}
		params.put("familyId", search.getFamilyId());
		List<Cars> carsList = carsDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		
		for(Cars cars : carsList) {
			Map<String,Object> map = new HashMap<String, Object>();
			//品牌(本田) + 车系（雅阁） + 年款（2016/2017） + 排量(2.0) + 变速箱类型（手动自动） + 款式（高中低配）
			StringBuffer buffer = new StringBuffer();
			buffer.append(cars.getBrandName()).append(" ");
			buffer.append(cars.getFamilyName()).append(" ");
			buffer.append(cars.getYearPattern()+"款").append(" ");
			buffer.append(cars.getPl()).append(" ");
			buffer.append(cars.getGearboxName()).append(cars.getStyleName());
			map.put("name",buffer);
			map.put("id", cars.getCarId());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result carExpectWayList(CarExpectWaySearch search, Result result) throws Exception {
		Map<String, Object> params =  new HashMap<String,Object>();
		if(search.getBrandId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请先选择品牌");
			return result;
		}
		if(search.getBrandId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请先选择汽车品牌");
			return result;
		}
		if(StringUtil.isEmpty(search.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的组织身份不明确，无法进行此操作");
			return result;
		}
		params.put("brandId", search.getBrandId());
		params.put("orgCode", search.getOrgCode());
		List<CarExpectWayVo> carExpectWayVos = carExpectWayDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarExpectWayVo carExpectWayVo : carExpectWayVos) {
			Map<String,Object> map = new HashMap<String, Object>();		
			map.put("pId", carExpectWayVo.getBrandId());
			map.put("id", carExpectWayVo.getExpectWayId());
			map.put("name", carExpectWayVo.getExpectWayName());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result carsBrandPageList(CarBrandSearch search, Result result) throws Exception {
		Map<String,Object> params = getBrandPageParams(search);
		List<CarBrandVo> carBrands = carBrandDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();		
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = carBrandDao.getRowCount(params);
		int rows = search.getRows();
		for(CarBrandVo brandVo : carBrands) {
			Map<String,Object> map = new HashMap<String, Object>();		
			map.put("brandId", brandVo.getBrandId());
			map.put("brandName", brandVo.getBrandName());
			maps.add(map);
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}
	
	public Map<String,Object> getBrandPageParams(CarBrandSearch search){
		Map<String,Object> params = new HashMap<String, Object>();
//		if(StringUtil.isNotEmpty(search.getSearchText())) {
//			params.put("searchText", search.getSearchText());
//		}
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	public Result carsStyleList(CarsStyleSearch search, Result result) throws Exception {
		if(StringUtil.isEmpty(search.getFamilyId())) {
			result.setError(ResultCode.CODE_STATE_4005, "请先选择汽车车系");
			return result;
		}
		Map<String,Object> params = new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(search.getFamilyId())) {
			params.put("familyId", search.getFamilyId());
		}
		if(StringUtil.isNotEmpty(search.getCarFamilyId())) {
			params.put("carFamilyId", search.getCarFamilyId());
		}
		List<CarsStyleVo> styleVos = carStyleDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarsStyleVo styleVo : styleVos) {
			Map<String,Object> map = new HashMap<String, Object>();		
			map.put("pId", styleVo.getFamilyId());
			map.put("id", styleVo.getCarStyleId());
			map.put("name", styleVo.getCarStyleName());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result carsFamilyListPage(CarFamilySearch search, Result result) throws Exception {
		Map<String,Object> params = getFamilyListParams(search);
		List<CarFamilyVo> carFamilyVos = carFamilyDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();		
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = carFamilyDao.getRowCount(params);
		int rows = search.getRows();
		for(CarFamilyVo familyVo : carFamilyVos) {
			Map<String,Object> map = new HashMap<String, Object>();		
			map.put("brandId", familyVo.getBrandId());
			map.put("carFamilyId", familyVo.getCarFamilyId());
			map.put("carFamilyName", familyVo.getCarFamilyName());
			maps.add(map);
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}
	
	public Map<String,Object> getFamilyListParams(CarFamilySearch search){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getBrandId())) {
			params.put("brandId", search.getBrandId());
		}
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	public Result carsInfo(CarsSearch search, Result result) throws Exception {
		Cars cars = carsDao.selectById(search.getCarId());
		if(cars == null) {
			result.setError(ResultCode.CODE_STATE_4005, "主键为："+search.getCarId()+"的车辆类型数据不存在，请核对数据");
			return result;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		//品牌(本田) + 车系（雅阁） + 年款（2016/2017） + 排量(2.0) + 变速箱类型（手动自动） + 款式（高中低配）
		StringBuffer buffer = new StringBuffer();
		buffer.append(cars.getBrandName()).append(" ");
		buffer.append(cars.getFamilyName()).append(" ");
		buffer.append(cars.getYearPattern()+"款").append(" ");
		buffer.append(cars.getPl()).append(" ");
		buffer.append(cars.getGearboxName()).append(cars.getStyleName());
		map.put("carsName",buffer);
		map.put("bareCarPrice", cars.getBareCarPrice());//裸车价
		map.put("price", cars.getPrice());//裸车价
		map.put("carsId", cars.getCarId());
		map.put("bareCarPrice", cars.getBareCarPrice());
		map.put("brandCode", cars.getBrandCode());
		map.put("brandId", cars.getBrandId());
		map.put("carId", cars.getCarId());
		map.put("comprehensivePrice", cars.getComprehensivePrice());
		map.put("emissionStandard", cars.getEmissionStandard());
		map.put("engineDesc", cars.getEngineDesc());
		map.put("engineModel", cars.getEngineModel());
		map.put("familyId", cars.getFamilyId());
		map.put("familyName", cars.getFamilyName());
		map.put("gearboxName", cars.getGearboxName());
		map.put("gearNum", cars.getGearNum());
		map.put("inairform", cars.getInairform());
		map.put("introduce", cars.getIntroduce());
		map.put("marketDate", cars.getMarketDate());
		map.put("oilConsumption", cars.getOilConsumption()!= null?Float.parseFloat(cars.getOilConsumption()):cars.getOilConsumption());
		map.put("pl", cars.getPl());
		map.put("powerType", cars.getPowerType());
		map.put("price", cars.getPrice());
		map.put("seat", cars.getSeat());
		map.put("styleId", cars.getStyleId());
		map.put("styleName", cars.getStyleName());
		map.put("supplyOil", cars.getSupplyOil());
		map.put("vehicleName", cars.getVehicleName());
		map.put("wheelbase", cars.getWheelbase());
		map.put("yearPattern", cars.getYearPattern()!=null? Float.parseFloat(cars.getYearPattern()):cars.getYearPattern());
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",TakeCareMapDate.cutNullMap(map));
		return result;
	}
}
