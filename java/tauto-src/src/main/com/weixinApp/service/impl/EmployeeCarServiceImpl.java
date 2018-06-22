package main.com.weixinApp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

import main.com.Test.HttpClientUtil;
import main.com.car.dao.dao.CarBrandDao;
import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarFamilyDao;
import main.com.car.dao.dao.CarInteriorDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.dao.OrgCarsConfigureDao;
import main.com.car.dao.po.Cars;
import main.com.car.dao.po.OrgCarsConfigure;
import main.com.car.dao.search.CarBrandSearch;
import main.com.car.dao.search.CarColourSearch;
import main.com.car.dao.search.CarFamilySearch;
import main.com.car.dao.search.CarInteriorSearch;
import main.com.car.dao.search.CarsSearch;
import main.com.car.dao.vo.CarBrandVo;
import main.com.car.dao.vo.CarColourVo;
import main.com.car.dao.vo.CarFamilyVo;
import main.com.car.dao.vo.CarInteriorVo;
import main.com.car.dao.vo.CarsVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.dao.StockOrderDao;
import main.com.stock.dao.dao.StockStorageDao;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.po.StockOrder;
import main.com.stock.dao.po.StockStorage;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.search.StockOrderSearch;
import main.com.stock.dao.vo.StockCarVo;
import main.com.stock.dao.vo.StockOrderVo;
import main.com.stock.dao.vo.StockStorageVo;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.dao.SystemWarehouseDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.po.SystemWarehouse;
import main.com.system.dao.vo.SystemWarehouseVo;
import main.com.utils.CheckVIN;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.HTTPRequest;
import main.com.utils.HttpUtil;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.weixin.dao.po.WeixinAppToken;
import main.com.weixin.service.WeixinAppTokenService;
import main.com.weixinApp.service.EmployeeCarService;

@Service
public class EmployeeCarServiceImpl extends BaseServiceImpl<StockCar>implements EmployeeCarService{

	@Autowired
	StockCarDao stockCarDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	StockStorageDao stockStorageDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CarBrandDao carBrandDao;
	
	@Autowired
	CarFamilyDao carFamilyDao;
	
	@Autowired
	CarColourDao carColourDao;
	
	@Autowired
	CarInteriorDao carInteriorDao;
	
	@Autowired
	OrgCarsConfigureDao orgCarsConfigureDao;
	
	@Autowired
	StockOrderDao stockOrderDao;
	
	@Autowired
	SystemWarehouseDao systemWarehouseDao;
	
	@Autowired
	WeixinAppTokenService weixinAppTokenService;

	@Override
	protected BaseDao<StockCar> getBaseDao() {
		return stockCarDao;
	}

	@Override
	public Result stockCarList(StockCarSearch search, Result result, SystemUsers users)throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();		
//		params.put("carsId", search.getCarsId());
//		if(search.gets) {
//			params.put("carsInfo", search.getCarsName());
//		}
		params.put("storageCodeSearch", search.getInteriorId());		
		//从第几条开始
		params.put("sortField", true);
		params.put("groupBy", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		
		//根据用户org查询查询出自身跟下级的库存单
		Map<String,Object> selectMap = new HashMap<String, Object>();
		Map<String,Object> allMap = new HashMap<String, Object>();
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请联系管理员");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getCarsId())) {
			params.put("carsId", search.getCarsId());
		}
		if(StringUtil.isNotEmpty(search.getOrgId())) {
			Organization organization = organizationDao.selectById(search.getOrgId());
			if(organization == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的公司数据不存在，请重新选择");
				return result;
			}
			selectMap.put("orgId", organization.getOrgId());
		}else {
			selectMap.put("orgId", users.getOrgId());
		}
		List<StockStorageVo> storages =  stockStorageDao.select(selectMap);
		Set<Integer> set = new HashSet<>();
		for(StockStorage storage : storages) {
			set.add(storage.getStorageId());
		}
		Map<String,Object> carParams = new HashMap<String, Object>();
		if(set != null && set.size() > 0) {
			carParams.put("storageIds", set);
		}else {//没有入库单就没有库存
			allMap.put("page", search.getPage());
			allMap.put("total", 0);
			allMap.put("rows", 10);
			allMap.put("list", new ArrayList<Map<String,Object>>());
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		carParams.put("groupBy", true);
		if(StringUtil.isNotEmpty(search.getBrandId())) {
			carParams.put("brandId", search.getBrandId());
		}
		if(StringUtil.isNotEmpty(search.getCarsInfo())) {
			carParams.put("carsInfo", search.getCarsInfo());
		}
		carParams.put("overSure", 1);
		List<StockCarVo> cars = stockCarDao.selectByOrg(carParams);		
		//把数据库压力转嫁给程序
		Set<Integer> carSet = new HashSet<>();
		for(StockCarVo stockCarVo : cars) {
			carSet.add(stockCarVo.getCarsId());
		}
		
		if(carSet != null && carSet.size() > 0) {
			params.put("ids", carSet);
		}else {//没有入库单就没有库存
			allMap.put("page", search.getPage());
			allMap.put("total", 0);
			allMap.put("rows", 10);
			allMap.put("list", new ArrayList<Map<String,Object>>());
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		Long total = stockCarDao.selectCountByOrg(carParams);
//		Long total = stockCarDao.selectCountByOrg(params);
		if(total == null) {
			total = 0l;
		}
		List<CarsVo> carsVos = carsDao.select(params);		
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(StockCarVo stockCarVo : cars) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("carsId", stockCarVo.getCarsId());
			map.put("number", stockCarVo.getNumber());
			map.put("carsName", stockCarVo.getCarsInfo());
			map.put("colourName", stockCarVo.getColourName());
			map.put("colourId", stockCarVo.getColourId());
			map.put("interiorId", stockCarVo.getInteriorId());
			map.put("interiorName", stockCarVo.getInteriorName());
			for(CarsVo carsVo : carsVos) {
				if(carsVo.getCarId().equals(stockCarVo.getCarsId())) {
					map.put("brandFamilyName", carsVo.getBrandName()+carsVo.getFamilyName());
					map.put("carsInfo",carsVo.getYearPattern()+"款"+" "+carsVo.getPl()+" "+carsVo.getGearboxName()+carsVo.getStyleName());
					map.put("indexImage", carsVo.getIndexImage());
				}
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
	public Result carsBrandList(CarBrandSearch search, Result result) throws Exception {
		List<CarBrandVo> carBrands = carBrandDao.select(new HashMap<String,Object>());
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarBrandVo brandVo : carBrands) {
			Map<String,Object> map = new HashMap<String, Object>();		
			map.put("id", brandVo.getBrandId());
			map.put("name", brandVo.getBrandName());
			map.put("logo", brandVo.getImgUrl());			
			map.put("image", brandVo.getImgUrl());			
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result stockCarInfo(StockCarSearch search, Result result, SystemUsers users) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getCarsId())) {
			params.put("carsId", search.getCarsId());
		}
		if(StringUtil.isNotEmpty(search.getColourId())) {
			params.put("colourId", search.getColourId());
		}
		if(StringUtil.isNotEmpty(search.getInteriorId())) {
			params.put("interiorId", search.getInteriorId());
		}
		
		if(StringUtil.isNotEmpty(search.getOrgId())) {
			Organization organization = organizationDao.selectById(search.getOrgId());
			if(organization == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的公司数据不存在，请重新选择");
				return result;
			}
			params.put("orgId", organization.getOrgId());
		}else {
			params.put("orgId", users.getOrgId());
		}
		
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		
		Map<String,Object> selectMap = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getOrgId())) {
			Organization organization = organizationDao.selectById(search.getOrgId());
			if(organization == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的公司数据不存在，请重新选择");
				return result;
			}
			selectMap.put("orgId", organization.getOrgId());
		}else {
			selectMap.put("orgId", users.getOrgId());
		}
		List<StockStorageVo> storages =  stockStorageDao.select(selectMap);
		Set<Integer> set = new HashSet<>();
		for(StockStorage storage : storages) {
			set.add(storage.getStorageId());
		}
		Map<String,Object> allMap = new HashMap<String, Object>();
		if(set != null && set.size() > 0) {
			params.put("storageIds", set);
		}else {//没有入库单就没有库存
			allMap.put("number", 0);
			allMap.put("page", search.getPage());
			allMap.put("total", 0);
			allMap.put("rows", 10);
			allMap.put("list", new ArrayList<Map<String,Object>>());
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		
		List<StockCarVo> cars = stockCarDao.select(params);
		Long total = stockCarDao.getRowCount(params);
		int rows = search.getRows();
		
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		if(cars == null || cars.size() <= 0) {
			allMap.put("number", cars!=null?cars.size():0);
			allMap.put("page", search.getPage());
			allMap.put("total", total);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		}
		Map<String,Object> carparams = new HashMap<String, Object>();
		set.clear();
		for(StockCarVo stockCarVo : cars) {
			if(StringUtil.isEmpty(stockCarVo.getCarsId())) {
				continue;
			}
			set.add(stockCarVo.getCarsId());
		}
		List<CarsVo> carsVos = new ArrayList<CarsVo>();
		if(set != null && set.size() >0) {
			carparams.put("ids", set);
			//获取车大类
			carsVos = carsDao.select(carparams);
		}
		if(carsVos == null || carsVos.size() <= 0) {
			allMap.put("number", cars!=null?cars.size():0);
			allMap.put("page", search.getPage());
			allMap.put("total", total);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		}
		for(CarsVo carsVo : carsVos) {
			for(StockCarVo stockCarVo : cars) {
				if(stockCarVo.getCarsId().equals(carsVo.getCarId())) {
					stockCarVo.setCarsVo(carsVo);
				}
			}
		}
		for(StockCarVo stockCarVo : cars) {
			if(StringUtil.isEmpty(stockCarVo.getStockCarId())) {
				continue;
			}
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("frameNumber", stockCarVo.getFrameNumber());
			map.put("carsId", stockCarVo.getCarsId());
			map.put("stockCarImages", stockCarVo.getStockCarImages());
			map.put("stockCarId", stockCarVo.getStockCarId());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		if(cars!=null && cars.size()>0) {
			StockCarVo stockCarVo = cars.get(0);
			allMap.put("carsName", stockCarVo.getCarsInfo());
			allMap.put("carsId", stockCarVo.getCarsId());
			allMap.put("interiorId", stockCarVo.getInteriorId());
			allMap.put("interiorName", stockCarVo.getInteriorName());
			allMap.put("colourId", stockCarVo.getColourId());
			allMap.put("colourName", stockCarVo.getColourName());
			allMap.put("depositPrice", stockCarVo.getDepositPrice());
			allMap.put("discountPrice", stockCarVo.getDiscountPrice());
			allMap.put("guidingPrice", stockCarVo.getGuidingPrice());
			if(stockCarVo.getCarsVo() != null) {
				allMap.put("indexImage", stockCarVo.getCarsVo().getIndexImage());
			}else {
				allMap.put("indexImage", "");
			}
		}else {
			allMap.put("carsName", "");
			allMap.put("carsId", "");
			allMap.put("interiorId", "");
			allMap.put("interiorName", "");
			allMap.put("colourId", "");
			allMap.put("colourName", "");
			allMap.put("depositPrice", "");
			allMap.put("discountPrice", "");
			allMap.put("guidingPrice", "");
			allMap.put("indexImage", "");
		}
		allMap.put("number", cars!=null?cars.size():0);
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
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
			map.put("price", cars.getPrice());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result stockOrderCreate(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(users.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不完整，请联系管理员补充完整");
			return result;
		}
		StockOrder order = new StockOrder();
		Organization organization = organizationDao.selectById(users.getOrgId());
		if(!GeneralConstant.Org.Level_3.equals(organization.getOrgLevel())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司不是门店级别，不能进行此操作");
			return result;
		}
		order.setStockOrderBuyOrgId(organization.getOrgId());
		order.setStockOrderBuyOrgName(organization.getShortName());
		order.setStockOrderBuyOrgAddress(organization.getProvinceName() + organization.getCityName() + organization.getAreaName() + organization.getAddress());		
		Organization organizationParent = organizationDao.selectById(organization.getParentId());
		if(organizationParent == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的上级公司不存在或者已删除，无法发起此项操作");
			return result;
		}
		order.setStockOrderSellOrgId(organizationParent.getOrgId());
		order.setStockOrderSellOrgName(organizationParent.getShortName());
		if(search.getCarsId() != null) {
			CarsVo carVo = carsDao.selectById(search.getCarsId());
			if(carVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车型不存在");
				return result;
			}
			order.setCarsId(carVo.getCarId());
			StringBuffer buffer = new StringBuffer();
				//品牌(本田) + 车系（雅阁） + 年款（2016/2017） + 排量(2.0) + 变速箱类型（手动自动） + 款式（高中低配）
				buffer.setLength(0);
				buffer.append(carVo.getBrandName()).append(" ");
				buffer.append(carVo.getFamilyName()).append(" ");
				buffer.append(carVo.getYearPattern()).append("款").append(" ");
				buffer.append(carVo.getPl()).append(" ");
				buffer.append(carVo.getGearboxName()).append(carVo.getStyleName());
				order.setCarsInfo(buffer.toString());
				order.setGuidingPrice(new BigDecimal(carVo.getPrice()));
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择的车型");
			return result;
		}
		if(search.getColourId() != null) {
			CarColourVo colourVo = carColourDao.selectById(search.getColourId());
			if(colourVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车身颜色不存在");
				return result;
			}
			order.setColourId(colourVo.getCarColourId());
			order.setColourName(colourVo.getCarColourName());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择的车身颜色");
			return result;
		}
		if(search.getInteriorId() != null) {
			CarInteriorVo interiorVo = carInteriorDao.selectById(search.getInteriorId());
			if(interiorVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车辆内饰不存在");
				return result;
			}
			order.setInteriorId(interiorVo.getInteriorId());
			order.setInteriorName(interiorVo.getInteriorName());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择的车辆内饰");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getStockOrderNumber())) {
			order.setStockOrderNumber(search.getStockOrderNumber());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入采购数量");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getStockOrderRemarks())) {
			order.setStockOrderRemarks(search.getStockOrderRemarks());
		}
		if(StringUtil.isNotEmpty(search.getTemplateImage())) {
			order.setTemplateImage(search.getTemplateImage());
		}
		if(search.getCertificateDate() != null) {
			switch (search.getCertificateDate()) {
			case GeneralConstant.StockStorageAbstract.CertificateDate_one:
				order.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_one);
				break;
			case GeneralConstant.StockStorageAbstract.CertificateDate_two:
				order.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_two);
				break;
			case GeneralConstant.StockStorageAbstract.CertificateDate_three:
				order.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_three);
				break;
			case GeneralConstant.StockStorageAbstract.CertificateDate_four:
				order.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_four);
				break;
			case GeneralConstant.StockStorageAbstract.CertificateDate_five:
				order.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_five);
				break;
			default:
				result.setError("合格证时间选择错误，请重新选择");
				return result;
			}			
		}
//		order
		//查询定金和优惠
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("carsId", search.getCarsId());
		params.put("interiorId", search.getInteriorId());
		params.put("colourId", search.getColourId());
		params.put("orgId", organizationParent.getOrgId());
		params.put("orgLevel", organizationParent.getOrgLevel());
		List<OrgCarsConfigure> orgCarsConfigures = orgCarsConfigureDao.select(params);
		if(orgCarsConfigures == null || orgCarsConfigures.size() <= 0) {
			order.setDepositPrice(new BigDecimal(GeneralConstant.StockOrdersState.depositPrice * order.getStockOrderNumber()));//默认定金
			if(order.getDiscountPrice() == null) {
				order.setDiscountPrice(new BigDecimal(0));
			}
			if(order.getGuidingPrice().doubleValue() - order.getDiscountPrice().doubleValue() - GeneralConstant.StockOrdersState.depositPrice <= 0) {
				result.setError(ResultCode.CODE_STATE_4005, "价格错误，指导价减去优惠减去定金以后，尾款数额过低，不合理");
				return result;
			}
			order.setBalancePrice(new BigDecimal((order.getGuidingPrice().doubleValue() - order.getDiscountPrice().doubleValue() - GeneralConstant.StockOrdersState.depositPrice) * order.getStockOrderNumber()));//默认定金
		}else {
			OrgCarsConfigure carsConfigure = orgCarsConfigures.get(0);
			order.setDepositPrice(new BigDecimal(carsConfigure.getDepositPrice().doubleValue() * order.getStockOrderNumber()));//默认定金
			if(carsConfigure.getDepositPrice() == null) {
				carsConfigure.setDepositPrice(new BigDecimal(0));
			}
			if(carsConfigure.getGuidingPrice().doubleValue() - carsConfigure.getDepositPrice().doubleValue() - carsConfigure.getDepositPrice().doubleValue() <= 0) {
				result.setError(ResultCode.CODE_STATE_4005, "价格错误，指导价减去优惠减去定金以后，尾款数额过低，不合理");
				return result;
			}
			order.setBalancePrice(new BigDecimal((carsConfigure.getGuidingPrice().doubleValue() - carsConfigure.getDiscountPrice().doubleValue() - carsConfigure.getDepositPrice().doubleValue()) * order.getStockOrderNumber()));//默认定金
		}
		order.setStockOrderState(GeneralConstant.StockOrdersState.STATE_STATER);
		order.setCreateDate(new Date());
		order.setIsDelete(false);
		order.setStockOrderCode(stockOrderDao.getOrderCode());
		order.setStatementCodeAfter(order.getStockOrderCode()+"_2");
		order.setStatementCodeBefor(order.getStockOrderCode()+"_1");		
		return stockOrderDao.insertAndResultIT(order, result);
	}

	@Override
	public Result stockOrderInfo(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(search.getStockOrderId())) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体的订单记录查看");
			return result;
		}
		StockOrderVo orderVo = stockOrderDao.selectById(search.getStockOrderId());
		if(orderVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的记录不存在或者已删除");
			return result;
		}		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("carsName",orderVo.getCarsInfo());
		map.put("carsId", orderVo.getCarsId());//
		map.put("colourName", orderVo.getColourName());//
		map.put("colourId", orderVo.getColourId());//
		map.put("interiorId", orderVo.getInteriorId());//
		map.put("interiorName", orderVo.getInteriorName());
		map.put("stockOrderState", orderVo.getStockOrderState());
		map.put("stockOrderId", orderVo.getStockOrderId());
		map.put("stockOrderCode", orderVo.getStockOrderCode());			
		map.put("stockOrderNumber", orderVo.getStockOrderNumber());			
		map.put("guidingPrice", orderVo.getGuidingPrice());//指导价
		map.put("remark", orderVo.getStockOrderRemarks());//备注
		map.put("logisticsOddNumber", orderVo.getLogisticsOddNumber());//物流单号	
		map.put("depositPrice", orderVo.getDepositPrice());//定金
		map.put("balancePrice", orderVo.getBalancePrice());//尾款
		map.put("address", orderVo.getStockOrderBuyOrgAddress());
		map.put("logisticsMode", orderVo.getLogisticsMode());
		map.put("followInformation", orderVo.getFollowInformation());
		map.put("templateImage", orderVo.getTemplateImage());
		map.put("certificateDate", orderVo.getCertificateDate());
		map.put("remarks", orderVo.getStockOrderRemarks());//备注
		List<Map<String,Object>> carMapList = new ArrayList<Map<String,Object>>();
		if(StringUtil.isNotEmpty(orderVo.getStockCarIds())) {//卖方已经出库具体车辆
			String[] carIds = orderVo.getStockCarIds().split(GeneralConstant.SystemBoolean.SPLIT);
			Set<Integer> set = new HashSet<Integer>();
			for(String carId : carIds) {
				set.add(Integer.parseInt(carId));
			}
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("ids", set);
			params.put("isPutOut", true);
			List<StockCarVo> carVos = stockCarDao.select(params);//new ArrayList<StockCarVo>();
			for(StockCarVo stockCarVo : carVos) {
				Map<String,Object> carmap = new HashMap<String, Object>();
				carmap.put("frameNumber", stockCarVo.getFrameNumber());
				carmap.put("carsId", stockCarVo.getCarsId());
				carmap.put("engineNumber", stockCarVo.getEngineNumber());
				carmap.put("certificateNumber", stockCarVo.getCertificateNumber());
				carmap.put("warehouseId", stockCarVo.getWarehouseId());
				carmap.put("warehouseName", stockCarVo.getWarehouseName());
				carmap.put("stockCarImages", stockCarVo.getStockCarImages());
				carmap.put("invoicePrice", stockCarVo.getInvoicePrice());
				carmap.put("depositPrice", stockCarVo.getDepositPrice());
				carmap.put("discountPrice", stockCarVo.getDiscountPrice());
				carmap.put("isOnLine", stockCarVo.getIsOnLine());
				carmap.put("stockCarId", stockCarVo.getStockCarId());
				carmap.put("storageId", stockCarVo.getStorageId());
				carmap.put("number", stockCarVo.getNumber());
				carmap.put("createDate", DateUtil.format(stockCarVo.getCreateDate()));
				carMapList.add(TakeCareMapDate.cutNullMap(carmap));
			}
			map.put("list", carMapList);
		}else {
			map.put("list", new ArrayList<Map<String,Object>>());
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",TakeCareMapDate.cutNullMap(map));
		return result;
	}

	@Override
	public Result stockOrderCancel(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getStockOrderId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体订单进行操作");
			return result;
		}
		StockOrder order = stockOrderDao.selectById(search.getStockOrderId());
		if(order == null){
			result.setError(ResultCode.CODE_STATE_4006, "订单信息错误，你选择的订单已删除或不存在。请刷新重试或者重新选择");
			return result;
		}
		if(users.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不完整，请联系管理员补充完整");
			return result;
		}
		if(!order.getStockOrderBuyOrgId().equals(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单的买方与你公司信息不匹配，你不能进行修改");
			return result;
		}
		if(!GeneralConstant.StockOrdersState.STATE_STATER.equals(order.getStockOrderState())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单正在持续，已不能进行取消");
			return result;
		}
		order.setStockOrderState(GeneralConstant.StockOrdersState.STATE_CANCEL);
		order.setIsCancel(true);
		return stockOrderDao.updateByIdAndResultIT(order, result);
	}

	@Override
	public Result stockOrderSign(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getStockOrderId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体订单进行操作");
			return result;
		}
		StockOrder order = stockOrderDao.selectById(search.getStockOrderId());
		if(order == null){
			result.setError(ResultCode.CODE_STATE_4006, "订单信息错误，你选择的订单已删除或不存在。请刷新重试或者重新选择");
			return result;
		}
		if(users.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不完整，请联系管理员补充完整");
			return result;
		}
		if(!order.getStockOrderBuyOrgId().equals(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单的买方与你公司信息不匹配，你不能进行修改");
			return result;
		}
		if(!GeneralConstant.StockOrdersState.STATE_LEAVEON.equals(order.getStockOrderState())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单商品尚未出库，还不能进行签收入库");
			return result;
		}
		if(order.getPayDateBefor() == null || order.getPayMethodBefor() == null || order.getPayDateAfter() == null || order.getPayMethodAfter() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "订单尚未支付全款（包括定金和尾款），还不能进行签收入库");
			return result;
		}
		order.setStockOrderState(GeneralConstant.StockOrdersState.STATE_STORAGEPUTIN);//更改订单状态
		//入库
		if(StringUtil.isEmpty(order.getStockCarIds())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单车辆信息缺失，不能进行此操作，请联系管理员");
			return result;
		}
		//卖方已经出库具体车辆
		String[] carIds = order.getStockCarIds().split(GeneralConstant.SystemBoolean.SPLIT);
		Set<Integer> set = new HashSet<Integer>();
		for(String carId : carIds) {
			set.add(Integer.parseInt(carId));
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ids", set);
		params.put("isPutOut", true);
		List<StockCarVo> carVos = stockCarDao.select(params);//new ArrayList<StockCarVo>();
		List<StockCar> caStockCars = new ArrayList<StockCar>();
		
		//查询仓库
		Map<String, Object> wparams =  new HashMap<String,Object>();
		wparams.put("OrgCode", users.getOrgCode());
		wparams.put("isDelete", false);
		wparams.put("warehouseType", GeneralConstant.SystemWarehouseType.vehicle);
		List<SystemWarehouseVo> warehouseVos = systemWarehouseDao.select(wparams);
		if(warehouseVos == null || warehouseVos.size() <= 0) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司缺少整车仓，自动入库失败，请先补充仓库信息");
			return result;
		}
		SystemWarehouse systemWarehouse = warehouseVos.get(0);		
		for(StockCarVo stockCarVo : carVos) {
			StockCar car = new StockCar();
			car.setFrameNumber(stockCarVo.getFrameNumber());
			car.setCarsId(stockCarVo.getCarsId());
			car.setEngineNumber(stockCarVo.getEngineNumber());
			car.setCertificateNumber(stockCarVo.getCertificateNumber());
			car.setStockCarImages(stockCarVo.getStockCarImages());
			car.setInvoicePrice(stockCarVo.getInvoicePrice());
			car.setDepositPrice(stockCarVo.getDepositPrice());
			car.setDiscountPrice(stockCarVo.getDiscountPrice());
			car.setIsOnLine(true);
			car.setOrderId(order.getStockOrderId());
			car.setUnitPrice(stockCarVo.getUnitPrice());
			car.setCarsInfo(stockCarVo.getCarsInfo());
			car.setGuidingPrice(stockCarVo.getGuidingPrice());
			car.setColourId(stockCarVo.getColourId());
			car.setColourName(stockCarVo.getColourName());
			car.setInteriorId(stockCarVo.getInteriorId());
			car.setInteriorName(stockCarVo.getInteriorName());
			car.setCreateDate(new Date());
			car.setIsDelete(false);
			car.setWarehouseId(systemWarehouse.getWarehouseId());
			car.setWarehouseName(systemWarehouse.getWarehouseName());
			car.setOrgId(users.getOrgId());
			car.setIsPutOut(false);
			caStockCars.add(car);
		}
		//新建入库单
		StockStorage stockStorage = new StockStorage();
		stockStorage.setStorageCode(stockStorageDao.getStorageCode());
		stockStorage.setOrgId(users.getOrgId());
		stockStorage.setOrgName(users.getOrgName());
		stockStorage.setIsDelete(false);
		stockStorage.setCreateDate(new Date());
		stockStorage.setStorageSource(3);
		stockStorage.setTotalPurchase(order.getStockOrderNumber());
		stockStorage.setTotalPurchasePrice(order.getBalancePrice().doubleValue() + order.getDepositPrice().doubleValue());
		stockStorage.setSource(1);//设置为自动入库
		if(stockOrderDao.updateById(order)) {//更新订单状态
			//保存入库单
			stockStorageDao.insert(stockStorage);
			//入库
			for(StockCar car : caStockCars) {
				car.setStorageId(stockStorage.getStorageId());
				stockCarDao.insert(car);
			}
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "数据保存失败，请刷新重试或联系管理员");			
		}
		return result;
	}

	@Override
	public Result carInteriorList(CarInteriorSearch search, Result result) throws Exception {
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
	public Result carColourList(CarColourSearch search, Result result) throws Exception {
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
	public Result stockOrderList(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请先联系管理员");
			return result;
		}
		Map<String,Object> params = getParams(search,users);
		List<StockOrderVo> stockOrderVos = stockOrderDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = stockOrderDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(StockOrderVo orderVo : stockOrderVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("carsName",orderVo.getCarsInfo());
			map.put("carsNameBefor", takeCareCarsName(orderVo.getCarsInfo(), true));
			map.put("carsNameAfter", takeCareCarsName(orderVo.getCarsInfo(), false));
			map.put("createDate",orderVo.getCreateDate() != null ? DateUtil.format(orderVo.getCreateDate()):"");
			map.put("buyOrgName",orderVo.getStockOrderBuyOrgName());
			map.put("carsId", orderVo.getCarsId());//
			map.put("colourName", orderVo.getColourName());//
			map.put("colourId", orderVo.getColourId());//
			map.put("interiorId", orderVo.getInteriorId());//
			map.put("interiorName", orderVo.getInteriorName());
			map.put("stockOrderState", orderVo.getStockOrderState());
			map.put("stockOrderId", orderVo.getStockOrderId());
			map.put("stockOrderCode", orderVo.getStockOrderCode());			
			map.put("stockOrderNumber", orderVo.getStockOrderNumber());			
			map.put("guidingPrice", orderVo.getGuidingPrice()!=null?orderVo.getGuidingPrice().doubleValue():0);//指导价			
			map.put("balancePrice", orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0);//尾款			
			map.put("depositPrice", orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0);//定金
			if(orderVo.getPayDateBefor()!=null && orderVo.getPayMethodBefor() != null && orderVo.getPayDateAfter() == null && orderVo.getPayMethodAfter() == null) {				
				map.put("payBrief", "已支付定金："+(orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0)+"。共支付总额："+(orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0));//定金				
			}else
			if(orderVo.getPayDateBefor()!=null && orderVo.getPayMethodBefor() != null && orderVo.getPayDateAfter() != null && orderVo.getPayMethodAfter() != null) {
				map.put("payBrief", "已支付定金："+ (orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0) + "。已支付尾款："+
			(orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0)+"。共支付总额："+
						((orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0)+(orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0)));//
			}else if(orderVo.getPayDateBefor()==null && orderVo.getPayMethodBefor() == null && orderVo.getPayDateAfter() != null && orderVo.getPayMethodAfter() != null) {
				map.put("payBrief", "定金未支付。已支付尾款："+
						(orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0)+"。共支付总额："+
									((orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0)));//
			}else {
				map.put("payBrief", "未支付");
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
	
	public Map<String,Object> getParams(StockOrderSearch search, SystemUsers users){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getStockOrderState())) {
			params.put("stockOrderState", search.getStockOrderState());
		}
		if(StringUtil.isNotEmpty(search.getOrderSearch())) {
			params.put("orderSearch", search.getOrderSearch());
		}		
		if(search.getIsSellList() != null && search.getIsSellList()) {
			params.put("orgCodeSell", users.getOrgCode());
		}else {
			params.put("orgCodeBuy", users.getOrgCode());
		}
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows()); 
		return params;
	}
	
	public String takeCareCarsName(String carsName,Boolean isBefor) {
		if(StringUtil.isEmpty(carsName)) {
			return "";
		}
		String[] names = carsName.split(" ");
		if(names == null || names.length <= 0) {
			return "";
		}
		if(isBefor == null) {
			return carsName;
		}
		if(isBefor) {
			return names[0]+names[1];
		}else {
			String returnName = "";
			for(int i=2;i<names.length;i++) {
				returnName += names[i]+" ";
			}
			return returnName;
		}
	}

	@Override
	public Result imageRecognition(String url, Result result) throws Exception {
		// 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/webimage";
        try {
        	if(StringUtil.isEmpty(url)) {
        		result.setError("图片路径不能为空");
        		return result;
        	}
    		WeixinAppToken appToken = weixinAppTokenService.getBaiduApp();
    		if(appToken == null) {
    			result.setError("该功能暂未开放");
        		return result;
    		}
//        	Map<String,String> createMap = new HashMap<String,String>();
//    		createMap.put("access_token", appToken.getAccessToken());
//    		createMap.put("url", url);
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
//        	String params = URLEncoder.encode("url", "UTF-8") + "=" + URLEncoder.encode(url, "UTF-8");
//            JSONObject back = HTTPRequest.httpsRequest(otherHost, "POST", url);
//    		JSONObject back = new JSONObject(HttpClientUtil.doPost(url, createMap));
//    		String back = HttpClientUtil.doPost(url, createMap);
    		 String params = URLEncoder.encode("url", "UTF-8") + "=" + URLEncoder.encode(url, "UTF-8");
             /**
              * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
              */
             String accessToken = appToken.getAccessToken();
             String back = HttpUtil.post(otherHost, accessToken, params);
             if(StringUtil.isEmpty(back)) {
            	 result.setError("无识别内容");
         		return result;
             }
//             System.out.println("识别内容--------->"+back);
             JSONObject jsonObject = new JSONObject(back);
             JSONArray array = jsonObject.getJSONArray("words_result");
             String reg = "[\u4e00-\u9fa5]";
         	Pattern pat = Pattern.compile(reg); 
         	Boolean returnBack = false;
            for(int i=0;i<array.length();i++) {
            	String words =  array.getJSONObject(i).getString("words");
            	if(words.indexOf("车辆识别代号") > -1) {
            		returnBack = true;
            	}
            	//去掉所有中文和符号和空格
            	words = words.replaceAll(" ", "");
            	Matcher mat=pat.matcher(words); 
            	String repickStr = mat.replaceAll("");
            	repickStr = repickStr.replaceAll("[^a-z^A-Z^0-9]", ""); 
            	if(StringUtil.isEmpty(repickStr)) {
            		continue;
            	}
            	if(returnBack) {
            		result.setOK(ResultCode.CODE_STATE_200, "", repickStr);
            		return result;
            	}
//            	if(CheckVIN.checkVIN(repickStr)) {
//            		result.setOK(ResultCode.CODE_STATE_200, "", repickStr);
//            		return result;
//            	}
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setError("无识别内容");
 		return result;
	}
	
	public static void main(String[] args) {

    	// TODO Auto-generated method stub

    	String str = "123abc你好efc";

    	String reg = "[\u4e00-\u9fa5]";

    	Pattern pat = Pattern.compile(reg);  

    	Matcher mat=pat.matcher(str); 

    	String repickStr = mat.replaceAll("");

    	System.out.println("去中文后:"+repickStr);

}
}
