package main.com.weixinApp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.car.dao.dao.CarBrandDao;
import main.com.car.dao.dao.CarBrowseRecordDao;
import main.com.car.dao.dao.CarCarparameterDao;
import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarColourImageDao;
import main.com.car.dao.dao.CarExpectWayDao;
import main.com.car.dao.dao.CarFamilyDao;
import main.com.car.dao.dao.CarParameterDao;
import main.com.car.dao.dao.CarProblemDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.dao.CarsStyleDao;
import main.com.car.dao.dao.OrgCarsConfigureDao;
import main.com.car.dao.po.CarBrand;
import main.com.car.dao.po.CarBrowseRecord;
import main.com.car.dao.po.CarCarparameter;
import main.com.car.dao.po.CarColour;
import main.com.car.dao.po.CarColourImage;
import main.com.car.dao.po.CarFamily;
import main.com.car.dao.po.Cars;
import main.com.car.dao.po.CarsStyle;
import main.com.car.dao.po.OrgCarsConfigure;
import main.com.car.dao.search.CarBrandSearch;
import main.com.car.dao.search.CarExpectWaySearch;
import main.com.car.dao.search.CarFamilySearch;
import main.com.car.dao.search.CarsSearch;
import main.com.car.dao.search.CarsStyleSearch;
import main.com.car.dao.vo.CarBrandVo;
import main.com.car.dao.vo.CarCarparameterVo;
import main.com.car.dao.vo.CarExpectWayVo;
import main.com.car.dao.vo.CarFamilyVo;
import main.com.car.dao.vo.CarParameterVo;
import main.com.car.dao.vo.CarProblemVo;
import main.com.car.dao.vo.CarsStyleVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.utils.Number;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.weixinApp.service.AppCarsService;

@Service
public class AppCarsServiceImpl extends BaseServiceImpl<Cars>implements AppCarsService{

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
	
	@Autowired
	OrgCarsConfigureDao orgCarsConfigureDao;
	
	@Autowired
	CarColourImageDao carColourImageDao;
	
	@Autowired
	CarColourDao carColourDao;
	
	@Autowired
	CarProblemDao carProblemDao;
	
	@Autowired
	CarParameterDao carParameterDao;
	
	@Autowired
	CarCarparameterDao carCarparameterDao;
	
	@Autowired
	CarBrowseRecordDao carBrowseRecordDao;
	
	@Override
	protected BaseDao<Cars> getBaseDao() {
		return carsDao;
	}
	
	public Result carsList_backup(CarsSearch search, Result result) throws Exception {
		Map<String,Object> params = getParams(search);
		List<Cars> carsList = carsDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = carsDao.selectCountIndex(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		if(carsList == null || carsList.size() <= 0) {
			allMap.put("page", search.getPage());
			allMap.put("total", total);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		//查找最高优惠，把数据库压力转移至程序
		//查询出来所有的门店商品配置
		//由于车大类经过品牌筛选，所以与商品配置对不上，所以要查询出来所有的车大类
		
		
		Set<Integer> integers = new HashSet<Integer>();
		for(Cars cars : carsList) {
			integers.add(cars.getCarId());
		}
		params.put("carsIds", integers);
		params.put("orgLevel", 3);
		List<OrgCarsConfigure> carsConfigures = orgCarsConfigureDao.selectMax(params);		
		params.put("isPage", false);
//		List<CarColourImage> carColourImages = carColourImageDao.select(params);
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
//			map.put("bareCarPrice", cars.getBareCarPrice());//裸车价
			map.put("price", cars.getPrice());
			map.put("image", cars.getIndexImage());
//			if(carColourImages == null || carColourImages.size() <= 0) {
//				map.put("images", new ArrayList<Map<String,Object>>());//裸车价
//			}else {
//				List<Map<String,Object>> image = new ArrayList<Map<String,Object>>();
//				for(CarColourImage colourImage : carColourImages) {					
//					if(colourImage.getCarsId().equals(cars.getCarId())) {
//						Map<String,Object> mapImage = new HashMap<String, Object>();
//						mapImage.put("imagePath", colourImage.getImagePath());
//						image.add(mapImage);
//					}
//				}
//				map.put("images", image);//裸车价
//			}
			map.put("carId", cars.getCarId());
				for(OrgCarsConfigure configure : carsConfigures) {
					if(cars.getCarId().equals(configure.getCarsId())) {
						map.put("discountPrice", configure.getDiscountPrice());
					}
				}
			if(!map.containsKey("discountPrice")) {
				map.put("discountPrice", 0.0);
			}
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}
	
	@Override
	public Result carsList(CarsSearch search, Result result) throws Exception {
		Map<String,Object> params = getParams(search);
		params.put("orgLevel", 3);
		List<OrgCarsConfigure> carsConfigures = orgCarsConfigureDao.select(params);	
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = orgCarsConfigureDao.selectCountIndex(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		if(carsConfigures == null || carsConfigures.size() <= 0) {
			allMap.put("page", search.getPage());
			allMap.put("total", total);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		Map<String,Object> carMap = new HashMap<String, Object>();
		Set<Integer> integers = new HashSet<Integer>();
//		System.out.println("size："+carsConfigures.size());
		for(OrgCarsConfigure carsConfigure : carsConfigures) {
			if(carsConfigure == null) {
				continue;
			}
			integers.add(carsConfigure.getCarsId());
		}
		if(integers.size() <= 0) {
			allMap.put("page", search.getPage());
			allMap.put("total", total);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		carMap.put("ids", integers);
		List<Cars> carsList = carsDao.select(carMap);		
		for(OrgCarsConfigure carsConfigure : carsConfigures) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("carsName",carsConfigure.getCarsInfo());
			map.put("image", "");
			map.put("price", carsConfigure.getGuidingPrice());
			for(Cars cars : carsList) {
				if(cars.getCarId().equals(carsConfigure.getCarsId())) {
					map.put("image", cars.getIndexImage());
				}
			}
			map.put("carId", carsConfigure.getCarsId());
			OrgCarsConfigure carsConfigure2 = orgCarsConfigureDao.selectTopDiscount(carsConfigure.getFamilyId());		
			if(carsConfigure2 == null || carsConfigure2.getMaxDiscount() == null) {
				map.put("discountPrice", 0.0);
			}else {
				map.put("discountPrice", carsConfigure2.getMaxDiscount().doubleValue());
			}
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
		if(StringUtil.isNotEmpty(search.getVehicleName())) {
			params.put("vehicleName", search.getVehicleName());
		}
		if(StringUtil.isNotEmpty(search.getMinPrice())) {
			params.put("minPrice", search.getMinPrice());
		}
		if(StringUtil.isNotEmpty(search.getMaxPrice())) {
			params.put("maxPrice", search.getMaxPrice());
		}
		if(StringUtil.isNotEmpty(search.getBrandId())) {
			params.put("brandId", search.getBrandId());
		}
		if(StringUtil.isNotEmpty(search.getCarsName())) {
			params.put("carsName", search.getCarsName());
		}
		params.put("isfamilyIdgroup", true);
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
			result.setError(ResultCode.CODE_STATE_4005, "您选择的车辆不存在或已下架，请选择别的车辆查看");
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
		map.put("price", cars.getPrice());//裸车价
		map.put("carId", cars.getCarId());//裸车价
		map.put("carsId", cars.getCarId());//裸车价
		map.put("familyId", cars.getFamilyId());//
		map.put("brandId", cars.getBrandId());//
		map.put("indexImage", cars.getIndexImage());//
		Map<String,Object> params = new HashMap<String, Object>();
		//查询颜色列表
		params.put("familyId", cars.getFamilyId());
		List<CarColour> carColour = carColourDao.select(params);
		params.put("carsId", cars.getCarId());
		//查询颜色图片
		List<CarColourImage> carColourImages = carColourImageDao.select(params);
		List<OrgCarsConfigure> carsConfigures = orgCarsConfigureDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarColour colour : carColour) {
			Map<String, Object> mapColour = new HashMap<String, Object>();
			mapColour.put("carColourName", colour.getCarColourName());
			mapColour.put("carColourId", colour.getCarColourId());
			mapColour.put("colourNumber", colour.getColourNumber());
			String imagePath = "";
//			List<Map<String, Object>> mapsImage = new ArrayList<Map<String,Object>>();
			for(CarColourImage image : carColourImages) {
				if(image.getCarColourId().equals(colour.getCarColourId())) {
//					Map<String, Object> mapImage = new HashMap<String, Object>();
//					mapImage.put("carColourId", image.getCarColourId());
//					mapImage.put("carColourImageId", image.getCarColourImageId());
//					mapImage.put("carsId", image.getCarsId());
//					mapImage.put("carId", image.getCarsId());
//					mapImage.put("imagePath", image.getImagePath());
					imagePath += image.getImagePath() + ",";
//					mapsImage.add(mapImage);
				}				
			}
			for(OrgCarsConfigure carsConfigure : carsConfigures) {
				if(carsConfigure.getColourId().equals(colour.getCarColourId())) {
					mapColour.put("minPrice", carsConfigure.getGuidingPrice().doubleValue() - carsConfigure.getDiscountPrice().doubleValue());
				}
			}
			if(!mapColour.containsKey("minPrice")) {
				mapColour.put("minPrice", cars.getPrice());
			}
			mapColour.put("imagePath", imagePath);
//			mapColour.put("imageList", mapsImage);
			maps.add(mapColour);
		}
		//查询优惠价
//		Set<Integer> integers = new HashSet<Integer>();
//		for(Cars cars : carsList) {
//			integers.add(cars.getCarId());
//		}
		
//		Set<Integer> set = new HashSet<Integer>();
//		set.add(cars.getCarId());
//		params.put("carsIds", set);
		
//		if(carsConfigures == null || carsConfigures.size() <= 0) {
//			map.put("minPrice", cars.getPrice());
//		}else {
//			for(OrgCarsConfigure configure : carsConfigures) {
//				if(cars.getCarId().equals(configure.getCarsId())) {
//					map.put("minPrice", cars.getPrice() - configure.getDiscountPrice().floatValue());
//				}
//			}
//		}

//		for(OrgCarsConfigure configure : carsConfigures) {
//			if(cars.getCarId().equals(configure.getCarsId())) {
//				map.put("minPrice", cars.getPrice() - configure.getDiscountPrice().floatValue());
//			}
//		}
//		if(!map.containsKey("minPrice")) {
//			map.put("minPrice", cars.getPrice());
//		}
		map.put("list", maps);
		
		//记录车辆被浏览记录
		CarBrowseRecord carBrowseRecord = new CarBrowseRecord();
		carBrowseRecord.setCarsId(search.getCarId());
		carBrowseRecord.setCarsName(buffer.toString());
		carBrowseRecord.setCreateDate(new Date());
		carBrowseRecord.setCustomerId(search.getUsersTmpl().getCustomerUsersId());
		carBrowseRecord.setUserTempId(search.getUsersTmpl().getUsersTempId());
		carBrowseRecordDao.insert(carBrowseRecord);
		//记录车辆被浏览记录
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",TakeCareMapDate.cutNullMap(map));
		return result;
	}

	@Override
	public Result carsBrandList(CarBrandSearch search, Result result) throws Exception {
		List<CarBrandVo> carBrands = carBrandDao.select(new HashMap<String,Object>());
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarBrandVo brandVo : carBrands) {
			Map<String,Object> map = new HashMap<String, Object>();		
			map.put("id", brandVo.getBrandId());
			map.put("name", brandVo.getBrandName());
			map.put("image", brandVo.getImgUrl());
			map.put("logo", brandVo.getImgUrl());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result carsYearPatternList(CarsSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("brandId", search.getBrandId());
		params.put("familyId", search.getFamilyId());
		List<Cars> carsList = carsDao.select(params);
		Set<String> set = new HashSet<String>();
		for(Cars cars : carsList) {
			set.add(cars.getYearPattern());
		}
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(String yearPattern : set) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("yearPattern", yearPattern);
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result carsFamilyList(CarsSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("familyId", search.getFamilyId());
		params.put("yearPattern", search.getYearPattern());
		params.put("isCarsgroup", true);
		List<OrgCarsConfigure> carsConfigures = orgCarsConfigureDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		if(carsConfigures == null || carsConfigures.size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
			return result;
		}
		Set<Integer> integers = new HashSet<Integer>();
		for(OrgCarsConfigure carsConfigure : carsConfigures) {
			integers.add(carsConfigure.getCarsId());
		}
		if(integers.size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
			return result;
		}
		Map<String,Object> carMap = new HashMap<String, Object>();
		carMap.put("ids", integers);
		List<Cars> carsList = carsDao.select(carMap);
		
		for(OrgCarsConfigure carsConfigure : carsConfigures) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("carsName",carsConfigure.getCarsInfo());
			map.put("carId", carsConfigure.getCarsId());
			map.put("carsId", carsConfigure.getCarsId());
			map.put("price", carsConfigure.getGuidingPrice());//裸车价
			map.put("yearPattern", "");
			for(Cars cars : carsList) {
				if(cars.getCarId().equals(carsConfigure.getCarsId())) {
					map.put("yearPattern", cars.getYearPattern());
				}
			}
			if(carsConfigure.getGuidingPrice() != null && carsConfigure.getDiscountPrice()!= null && carsConfigure.getGuidingPrice().doubleValue() >= carsConfigure.getDiscountPrice().doubleValue()) {
				map.put("minPrice", carsConfigure.getGuidingPrice().doubleValue() - carsConfigure.getDiscountPrice().doubleValue());
			}else {
				map.put("minPrice", carsConfigure.getGuidingPrice());
			}
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result carProblemList(CarsSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		//从第几条开始
		params.put("sortField", true);
//		params.put("isPage", true);
//		params.put("offset", (search.getPage()-1)*search.getRows());
//		//返回几条
//		params.put("limit", search.getRows());
		List<CarProblemVo> carsList = carProblemDao.select(params);
//		Map<String,Object> allMap = new HashMap<String, Object>();
//		Long total = carsDao.getRowCount(params);
//		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarProblemVo problemVo : carsList) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("problemId", problemVo.getProblemId());
			map.put("problemProblem", problemVo.getProblemProblem());
			map.put("problemAnswer", problemVo.getProblemAnswer());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
//		allMap.put("page", search.getPage());
//		allMap.put("total", total);
//		allMap.put("rows", rows);
//		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result carIntroduce(CarsSearch search, Result result) throws Exception {
		Cars cars = carsDao.selectById(search.getCarId());
		if(cars == null) {
			result.setError(ResultCode.CODE_STATE_4005, "您选择的车辆不存在或已下架，请选择别的车辆查看");
			return result;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("carId", cars.getCarId());
		map.put("introduce", cars.getIntroduce());
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",map);
		return result;
	}

	@Override
	public Result carParameter(CarsSearch search, Result result) throws Exception {
		Cars cars = carsDao.selectById(search.getCarId());
		if(cars == null) {
			result.setError(ResultCode.CODE_STATE_4005, "您选择的车辆不存在或已下架，请选择别的车辆查看");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("carId", cars.getCarId());
		List<CarCarparameterVo> carparameters = carCarparameterDao.select(params);
		Set<Integer> set = new HashSet<Integer>();
		for(CarCarparameter carparameter : carparameters) {
			set.add(carparameter.getParameterId());
		}
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		if(set == null || set.size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
			return result;
		}
		params.put("parameterIds", set);
		List<CarParameterVo> carParameterVos = carParameterDao.select(params);
		for(CarParameterVo parameterVo : carParameterVos) {
			for(CarCarparameterVo carparameterVo : carparameters) {
				if(carparameterVo.getParameterId().equals(parameterVo.getParameterId())) {
					carparameterVo.setParameterName(parameterVo.getParameterName());
					carparameterVo.setTypeName(parameterVo.getTypeName());
					carparameterVo.setTypeCode(parameterVo.getTypeCode());
				}
			}
		}
		for(CarCarparameterVo carparameterVo : carparameters) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("carId", carparameterVo.getCarId());
			map.put("parameterId", carparameterVo.getParameterId());
			map.put("parameterName", carparameterVo.getParameterName());
			map.put("parameterValue", carparameterVo.getParameterValue());
			map.put("typeCode", carparameterVo.getTypeCode());
			map.put("typeName", carparameterVo.getTypeName());
			map.put("id", carparameterVo.getId());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result fullPayment(CarsSearch search, Result result) throws Exception {
		/**
		 * 全款预计落地价=裸车价+购置税+上牌费用+车船税+交强险+商业险

			裸车价 = 官方指导价
			
			购置税 ＝ 裸车价/(1+17%)×购置税率(10%)
			
			上牌费用 = 500
			
			车船税 = 当前车辆排量对应数值
			0-1.0	180
			1.0-1.6	360
			1.6-2.0	420
			2.0-2.5	720
			2.5-3.0	1800
			3.0-4.0	3000
			>4.0	4500
			
			交强险 = 座位数对应数值
			
			商业保险 = 各项保险类型对应数值的总和
		 */
		// TODO Auto-generated method stub
		Cars cars = carsDao.selectById(search.getCarsId());
		if(cars == null) {
			result.setError(ResultCode.CODE_STATE_4005, "您选择的车辆不存在或已下架，请选择别的车辆查看");
			return result;
		}
		
		Double guidancePrice = new BigDecimal(cars.getPrice()).doubleValue(); //指导价
		Double purchaseTax = guidancePrice/(1+0.17)*0.10; //购置税
		Double premium = 500d; //上牌费用
		Double vehicleAndAesselTax = 0d; //车船税
		if(StringUtil.isEmpty(cars.getPl())) {
			cars.setPl("0");
		}
		Double pl = Double.parseDouble(cars.getPl()!= null&&!cars.getPl().equals("")?cars.getPl().replaceAll("L", "").replaceAll("l", "").replaceAll("T", "").replaceAll("t", ""):"0");
		if(0d < pl && pl <= 1.0d) {
			vehicleAndAesselTax = 180d;
		}else
		if(1d < pl && pl <= 1.6d) {
			vehicleAndAesselTax = 360d;
		}else
		if(1.6d < pl && pl <= 2.0d) {
			vehicleAndAesselTax = 420d;
		}else
		if(2.0d < pl && pl <= 2.5d) {
			vehicleAndAesselTax = 720d;
		}else
		if(2.5d < pl && pl <= 3.0d) {
			vehicleAndAesselTax = 1800d;
		}else
		if(3.0d < pl && pl <= 4.0) {
			vehicleAndAesselTax = 3000d;
		}else
		if(4.0d < pl) {
			vehicleAndAesselTax = 4500d;
		}else {
			vehicleAndAesselTax = 180d;
		}
		Double strongInsurance = 0d; //交强险
		Integer set = cars.getSeat() != null?cars.getSeat():0;
		if(set <= 6) {
			strongInsurance = 950d;
		}else if(set > 6){
			strongInsurance = 1100d;
		}else {
			strongInsurance = 950d;
		}
		
		if(StringUtil.isEmpty(search.getThirdPartyLiabilityInsuranceTopBack())) {
			search.setThirdPartyLiabilityInsuranceTopBack(new BigDecimal(200000));//设置赔额为5万
		}
		Double thirdPartyLiabilityInsuranceTopBack = search.getThirdPartyLiabilityInsuranceTopBack().doubleValue();
		Double thirdPartyLiabilityInsurance = 516d; //第三方责任险(默认)
		if(thirdPartyLiabilityInsuranceTopBack.equals(50000d)) {
			thirdPartyLiabilityInsurance = 516d;
		}else if(thirdPartyLiabilityInsuranceTopBack.equals(100000d)) {
			thirdPartyLiabilityInsurance = 746d;
		}else if(thirdPartyLiabilityInsuranceTopBack.equals(200000d)) {
			thirdPartyLiabilityInsurance = 924d;
		}else if(thirdPartyLiabilityInsuranceTopBack.equals(300000d)) {
			thirdPartyLiabilityInsurance = 1000d;
		}else if(thirdPartyLiabilityInsuranceTopBack.equals(500000d)) {
			thirdPartyLiabilityInsurance = 1252d;
		}else if(thirdPartyLiabilityInsuranceTopBack.equals(1000000d)) {
			thirdPartyLiabilityInsurance = 1630d;
		}
		
		Double vehicleLossInsurance = Number.getCeil(458.76d + guidancePrice * (1.088 / 100), 2); //车辆损失险
		Double robberyAndTheftInsurance = Number.getCeil(101.88d + guidancePrice * 0.0045, 2); //全车盗抢险
		Double riskOfGlassBreakage = (0.25 / 100) * guidancePrice; //玻璃单独破碎险
		Double selfIgnitionLossInsurance = (0.15 / 100) * guidancePrice;//自燃损失险
		Double exemptionFromSpecialContract = 0.20d * (thirdPartyLiabilityInsurance + vehicleLossInsurance);//自燃损失险
		Double noLiabilityInsurance = 0.20d * (thirdPartyLiabilityInsurance);//无过责任险
		Double personnelLiabilityInsurance = set * 50d; //座位数 × 50 车上人员责任险
		Double bodyScratchRisk = 400d; //座位数 × 50 车上人员责任险
		Double commercialInsurance = thirdPartyLiabilityInsurance + vehicleLossInsurance + robberyAndTheftInsurance + riskOfGlassBreakage + selfIgnitionLossInsurance + 
				exemptionFromSpecialContract + noLiabilityInsurance + personnelLiabilityInsurance + bodyScratchRisk;
		//全款预计落地价=裸车价+购置税+上牌费用+车船税+交强险+商业险
		Double fullPrice = guidancePrice + purchaseTax + premium + vehicleAndAesselTax + strongInsurance + commercialInsurance;		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("fullPrice", Number.getCeil(fullPrice / 10000d, 2)+"万");
		map.put("guidancePrice", Number.getCeil(guidancePrice,2));
		map.put("purchaseTax", Number.getCeil(purchaseTax,2));
		map.put("premium", Number.getCeil(premium,2));
		map.put("vehicleAndAesselTax", Number.getCeil(vehicleAndAesselTax,2));
		map.put("strongInsurance", Number.getCeil(strongInsurance,2));
		map.put("commercialInsurance", Number.getCeil(commercialInsurance,2));
		
		map.put("thirdPartyLiabilityInsurance", Number.getCeil(thirdPartyLiabilityInsurance,2));
		map.put("vehicleLossInsurance", Number.getCeil(vehicleLossInsurance,2));
		map.put("robberyAndTheftInsurance", Number.getCeil(robberyAndTheftInsurance,2));
		map.put("riskOfGlassBreakage", Number.getCeil(riskOfGlassBreakage,2));
		map.put("selfIgnitionLossInsurance", Number.getCeil(selfIgnitionLossInsurance,2));
		map.put("exemptionFromSpecialContract", Number.getCeil(exemptionFromSpecialContract,2));
		map.put("personnelLiabilityInsurance", Number.getCeil(personnelLiabilityInsurance,2));
		map.put("noLiabilityInsurance", Number.getCeil(noLiabilityInsurance,2));
		map.put("bodyScratchRisk", Number.getCeil(bodyScratchRisk,2));
		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}
	
	public Double fullPaymentCount(Cars cars) {
		Double guidancePrice = new BigDecimal(cars.getPrice()).doubleValue(); //指导价
		Double purchaseTax = guidancePrice/(1+0.17)*0.10; //购置税
		Double premium = 500d; //上牌费用
		Double vehicleAndAesselTax = 0d; //车船税
		if(StringUtil.isEmpty(cars.getPl())) {
			cars.setPl("0");
		}
		Double pl = Double.parseDouble(cars.getPl());
		if(0d < pl && pl <= 1.0d) {
			vehicleAndAesselTax = 180d;
		}else
		if(1d < pl && pl <= 1.6d) {
			vehicleAndAesselTax = 360d;
		}else
		if(1.6d < pl && pl <= 2.0d) {
			vehicleAndAesselTax = 420d;
		}else
		if(2.0d < pl && pl <= 2.5d) {
			vehicleAndAesselTax = 720d;
		}else
		if(2.5d < pl && pl <= 3.0d) {
			vehicleAndAesselTax = 1800d;
		}else
		if(3.0d < pl && pl <= 4.0) {
			vehicleAndAesselTax = 3000d;
		}else
		if(4.0d < pl) {
			vehicleAndAesselTax = 4500d;
		}else {
			vehicleAndAesselTax = 180d;
		}
		Double strongInsurance = 0d; //交强险
		Integer set = cars.getSeat() != null?cars.getSeat():0;
		if(set <= 6) {
			strongInsurance = 950d;
		}else if(set > 6){
			strongInsurance = 1100d;
		}else {
			strongInsurance = 950d;
		}
		Double thirdPartyLiabilityInsurance = 516d; //第三方责任险
		Double vehicleLossInsurance = Number.getCeil(458.76d + guidancePrice * (1.088 / 100), 2); //车辆损失险
		Double robberyAndTheftInsurance = Number.getCeil(101.88d + guidancePrice * 0.0045, 2); //全车盗抢险
		Double riskOfGlassBreakage = (0.25 / 100) * guidancePrice; //玻璃单独破碎险
		Double selfIgnitionLossInsurance = (0.15 / 100) * guidancePrice;//自燃损失险
		Double exemptionFromSpecialContract = 0.2d * (thirdPartyLiabilityInsurance + vehicleLossInsurance);//自燃损失险
		Double noLiabilityInsurance = 0.2d * (thirdPartyLiabilityInsurance);//无过责任险
		Double personnelLiabilityInsurance = set * 50d; //座位数 × 50 车上人员责任险
		Double bodyScratchRisk = 400d; //座位数 × 50 车上人员责任险
		Double commercialInsurance = thirdPartyLiabilityInsurance + vehicleLossInsurance + robberyAndTheftInsurance + riskOfGlassBreakage + selfIgnitionLossInsurance + 
				exemptionFromSpecialContract + noLiabilityInsurance + personnelLiabilityInsurance + bodyScratchRisk;
		//全款预计落地价=裸车价+购置税+上牌费用+车船税+交强险+商业险
		Double fullPiace = guidancePrice + purchaseTax + premium + vehicleAndAesselTax + strongInsurance + commercialInsurance;		
		return fullPiace;
	}

	@Override
	public Result loanPayment(CarsSearch search, Result result) throws Exception {
		if(StringUtil.isEmpty(search.getPaymentRatio())) {
			search.setPaymentRatio(0.30d);
		}
		if(StringUtil.isEmpty(search.getTimeOfPayment())) {
			search.setTimeOfPayment(2);
		}
		Cars cars = carsDao.selectById(search.getCarsId());
		if(cars == null) {
			result.setError(ResultCode.CODE_STATE_4005, "您选择的车辆不存在或已下架，请选择别的车辆查看");
			return result;
		}
		Double guidancePrice = new BigDecimal(cars.getPrice()).doubleValue(); //指导价
		Double purchaseTax = guidancePrice/(1+0.17)*0.10; //购置税
		Double premium = 500d; //上牌费用
		Double vehicleAndAesselTax = 0d; //车船税
		if(StringUtil.isEmpty(cars.getPl())) {
			cars.setPl("0");
		}
//		Double pl = Double.parseDouble(cars.getPl());
		Double pl = Double.parseDouble(cars.getPl()!= null&&!cars.getPl().equals("")?cars.getPl().replaceAll("L", "").replaceAll("l", "").replaceAll("T", "").replaceAll("t", ""):"0");
		if(0d < pl && pl <= 1.0d) {
			vehicleAndAesselTax = 180d;
		}else
		if(1d < pl && pl <= 1.6d) {
			vehicleAndAesselTax = 360d;
		}else
		if(1.6d < pl && pl <= 2.0d) {
			vehicleAndAesselTax = 420d;
		}else
		if(2.0d < pl && pl <= 2.5d) {
			vehicleAndAesselTax = 720d;
		}else
		if(2.5d < pl && pl <= 3.0d) {
			vehicleAndAesselTax = 1800d;
		}else
		if(3.0d < pl && pl <= 4.0) {
			vehicleAndAesselTax = 3000d;
		}else
		if(4.0d < pl) {
			vehicleAndAesselTax = 4500d;
		}else {
			vehicleAndAesselTax = 180d;
		}
		Double strongInsurance = 0d; //交强险
		Integer set = cars.getSeat() != null?cars.getSeat():0;
		if(set <= 6) {
			strongInsurance = 950d;
		}else if(set > 6){
			strongInsurance = 1100d;
		}else {
			strongInsurance = 950d;
		}
		if(StringUtil.isEmpty(search.getThirdPartyLiabilityInsuranceTopBack())) {
			search.setThirdPartyLiabilityInsuranceTopBack(new BigDecimal(200000));//设置赔额为5万
		}
		Double thirdPartyLiabilityInsuranceTopBack = search.getThirdPartyLiabilityInsuranceTopBack().doubleValue();
		Double thirdPartyLiabilityInsurance = 516d; //第三方责任险(默认)
		if(thirdPartyLiabilityInsuranceTopBack.equals(50000d)) {
			thirdPartyLiabilityInsurance = 516d;
		}else if(thirdPartyLiabilityInsuranceTopBack.equals(100000d)) {
			thirdPartyLiabilityInsurance = 746d;
		}else if(thirdPartyLiabilityInsuranceTopBack.equals(200000d)) {
			thirdPartyLiabilityInsurance = 924d;
		}else if(thirdPartyLiabilityInsuranceTopBack.equals(300000d)) {
			thirdPartyLiabilityInsurance = 1000d;
		}else if(thirdPartyLiabilityInsuranceTopBack.equals(500000d)) {
			thirdPartyLiabilityInsurance = 1252d;
		}else if(thirdPartyLiabilityInsuranceTopBack.equals(1000000d)) {
			thirdPartyLiabilityInsurance = 1630d;
		}
		Double vehicleLossInsurance = Number.getCeil(458.76d + guidancePrice * (1.088 / 100), 2); //车辆损失险
		Double robberyAndTheftInsurance = Number.getCeil(101.88d + guidancePrice * 0.0045, 2); //全车盗抢险
		Double riskOfGlassBreakage = (0.25 / 100) * guidancePrice; //玻璃单独破碎险
		Double selfIgnitionLossInsurance = (0.15 / 100) * guidancePrice;//自燃损失险
		Double exemptionFromSpecialContract = 0.20d * (thirdPartyLiabilityInsurance + vehicleLossInsurance);//自燃损失险
		Double noLiabilityInsurance = 0.20d * (thirdPartyLiabilityInsurance);//无过责任险
		Double personnelLiabilityInsurance = set * 50d; //座位数 × 50 车上人员责任险
		Double bodyScratchRisk = 400d; //车身划痕险
		Double commercialInsurance = thirdPartyLiabilityInsurance + vehicleLossInsurance + robberyAndTheftInsurance + riskOfGlassBreakage + selfIgnitionLossInsurance + 
				exemptionFromSpecialContract + noLiabilityInsurance + personnelLiabilityInsurance + bodyScratchRisk;
		//全款预计落地价=裸车价+购置税+上牌费用+车船税+交强险+商业险
		Double fullPrice = guidancePrice + purchaseTax + premium + vehicleAndAesselTax + strongInsurance + commercialInsurance;
		Double interestRate = 0d; //利率
		switch(search.getTimeOfPayment()) {
			case 1: interestRate = 4.85 / 100;break;
			case 2: interestRate = 5.25 / 100;break;
			case 3: interestRate = 5.25 / 100;break;
			case 5: interestRate = 5.25 / 100;break;
			default:interestRate = 5.25 / 100;break;
		}
		//利息 = (落车价-首付金额) × 还款期数对应利率数值
		Double interest = (guidancePrice - search.getPaymentRatio() * guidancePrice) * interestRate;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("fullPrice", Number.getCeil((fullPrice + interest) / 10000d, 2)+"万");//落地价
		map.put("guidancePrice", Number.getCeil(guidancePrice, 2)); //指导价
		map.put("purchaseTax", Number.getCeil(purchaseTax, 2));//购置税
		map.put("premium", Number.getCeil(premium, 2));//上牌费用
		map.put("vehicleAndAesselTax", Number.getCeil(vehicleAndAesselTax, 2));//车船税
		map.put("strongInsurance", Number.getCeil(strongInsurance, 2));//交强险
		map.put("commercialInsurance", Number.getCeil(commercialInsurance, 2));//商业险	
		map.put("interest", Number.getCeil(interest, 2));//利息
		map.put("downPayments", Number.getCeil(search.getPaymentRatio() * guidancePrice, 2));//首付
		map.put("forTheMonth", Number.getCeil((guidancePrice - search.getPaymentRatio() * guidancePrice)/(search.getTimeOfPayment()*12), 2));//月供	
		
		map.put("thirdPartyLiabilityInsurance", Number.getCeil(thirdPartyLiabilityInsurance,2));//第三者
		map.put("vehicleLossInsurance", Number.getCeil(vehicleLossInsurance,2));
		map.put("robberyAndTheftInsurance", Number.getCeil(robberyAndTheftInsurance,2));
		map.put("riskOfGlassBreakage", Number.getCeil(riskOfGlassBreakage,2));
		map.put("selfIgnitionLossInsurance", Number.getCeil(selfIgnitionLossInsurance,2));
		map.put("exemptionFromSpecialContract", Number.getCeil(exemptionFromSpecialContract,2));
		map.put("personnelLiabilityInsurance", Number.getCeil(personnelLiabilityInsurance,2));
		map.put("noLiabilityInsurance", Number.getCeil(noLiabilityInsurance,2));
		map.put("bodyScratchRisk", Number.getCeil(bodyScratchRisk,2));
		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}
}
