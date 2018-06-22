package main.com.weixinHtml.service.impl;

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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import main.com.car.dao.dao.CarBrandDao;
import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarInteriorDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.search.CarsSearch;
import main.com.car.dao.vo.CarBrandVo;
import main.com.car.dao.vo.CarColourVo;
import main.com.car.dao.vo.CarInteriorVo;
import main.com.car.dao.vo.CarsVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemRegionDao;
import main.com.system.dao.dao.SystemWarehouseDao;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.po.SystemWarehouse;
import main.com.system.dao.vo.OrganizationVo;
import main.com.system.dao.vo.SystemRegionVo;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.utils.DateUtil;
import main.com.utils.StringUtil;
import main.com.weixinHtml.dao.dao.ShopGoodsCarsDao;
import main.com.weixinHtml.dao.po.ShopGoodsCars;
import main.com.weixinHtml.dao.search.ShopGoodsCarsSearch;
import main.com.weixinHtml.dao.vo.ShopGoodsCarsVo;
import main.com.weixinHtml.service.ShopGoodsCarsService;

@Service
public class ShopGoodsCarsServiceImpl extends BaseServiceImpl<ShopGoodsCars> implements ShopGoodsCarsService{

	@Autowired
	ShopGoodsCarsDao shopGoodsCarsDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CarColourDao carColourDao;
	
	@Autowired
	CarInteriorDao carInteriorDao;
	
	@Autowired
	SystemWarehouseDao systemWarehouseDao;
	
	@Autowired
	CarBrandDao carBrandDao;
	
	@Autowired
	SystemRegionDao regionDao;
	
	@Autowired
	SystemRegionDao systemRegionDao;
	
	@Override
	protected BaseDao<ShopGoodsCars> getBaseDao() {
		return shopGoodsCarsDao;
	}

	@Override
	public Result shopGoodsCarsList(ShopGoodsCarsSearch search, Result result,SystemUsersVo users) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("keywords", search.getKeywords());
		params.put("provinceId", search.getProvinceId());
		params.put("cityId", search.getCityId());
		params.put("areaId", search.getAreaId());
		params.put("brandId", search.getBrandId());
		params.put("orgId", search.getOrgId());
		params.put("orgCode", search.getOrgCode());
		List<String> strings = new ArrayList<>();
		strings.add("总经理");
		strings.add("IT部");
		strings.add("仓管");
		strings.add("仓管主管");
		strings.add("B端客户总监");
		strings.add("销售经理");
		if(!strings.contains(users.getRoleName())) {
			params.put("systemUsersId", users.getUsersId());			
		}
		if(StringUtil.isNotEmpty(search.getOrgId())) {
			params.put("createOder", true);
			params.put("overOffShelf", search.getOverOffShelf());
		}else {
			params.put("shelfOder", true);
			params.put("overOffShelf", false);//只展示上架的车辆
		}
		if(search.getIsNew() != null && search.getIsNew()) {//是否是新品上架展示(七天内)
			params.put("overOffShelf", false);//只展示上架的车辆
			Date date = new Date();
			params.put("startDate", DateUtil.format(DateUtil.operDayDate(date, -7), true));
			params.put("endDate", DateUtil.format(date,true));//只展示上架的车辆
		}
		List<ShopGoodsCarsVo> carsVos = shopGoodsCarsDao.selectJoin(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", carsVos);
		return new Result(returnMap);		
	}
	
	@Override
	public Result shopGoodsCarsList(ShopGoodsCarsSearch search, Result result) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("keywords", search.getKeywords());
		params.put("provinceId", search.getProvinceId());
		params.put("cityId", search.getCityId());
		params.put("areaId", search.getAreaId());
		params.put("brandId", search.getBrandId());
		params.put("orgId", search.getOrgId());
		params.put("orgCode", search.getOrgCode());
		if(StringUtil.isNotEmpty(search.getOrgId())) {
			params.put("createOder", true);
			params.put("overOffShelf", search.getOverOffShelf());
		}else {
			params.put("shelfOder", true);
			params.put("overOffShelf", false);//只展示上架的车辆
		}
		if(search.getIsNew() != null && search.getIsNew()) {//是否是新品上架展示(七天内)
			params.put("overOffShelf", false);//只展示上架的车辆
			Date date = new Date();
			params.put("startDate", DateUtil.format(DateUtil.operDayDate(date, -7), true));
			params.put("endDate", DateUtil.format(date,true));//只展示上架的车辆
		}
		List<ShopGoodsCarsVo> carsVos = shopGoodsCarsDao.selectJoin(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", carsVos);
		return new Result(returnMap);		
	}

	@Override
	public Result shopGoodsCarsInfo(ShopGoodsCarsSearch search, Result result) throws Exception {
		ShopGoodsCarsVo carsVo = shopGoodsCarsDao.selectByIdJoin(search.getGoodsCarsId());
		if(carsVo == null) {
			result.setError("抱歉，你选择得车辆不存在或者已下架");
			return result;
		}
		result.setOK(ResultCode.CODE_STATE_200, "", carsVo);
		return result;
	}

	@Override
	public Result shopGoodsCarsShelves(ShopGoodsCarsSearch search, Result result) throws Exception {
		ShopGoodsCarsVo carsVo = shopGoodsCarsDao.selectById(search.getGoodsCarsId());
		if(carsVo == null) {
			result.setError("抱歉，你选择得车辆不存在或者已删除");
			return result;
		}
		if(!carsVo.getOrgId().equals(search.getOrgId())) {
			result.setError("抱歉，你没有此权限");
			return result;
		}
		if(search.getOverOffShelf() != null && search.getOverOffShelf()) {
			carsVo.setOverOffShelf(true);
		}else if(search.getOverOffShelf() != null && !search.getOverOffShelf()){
			carsVo.setOverOffShelf(false);
			carsVo.setOnShelfDate(new Date());//更新上架时间
		}else {
			result.setError("抱歉，参数错误");
			return result;
		}
		return save(carsVo, result, carsVo.getGoodsCarsId());
	}

	@Override
	public Result shopGoodsCarsDel(ShopGoodsCarsSearch search, Result result) throws Exception {
		ShopGoodsCarsVo carsVo = shopGoodsCarsDao.selectById(search.getGoodsCarsId());
		if(carsVo == null) {
			result.setError("抱歉，你选择得车辆不存在或者已删除");
			return result;
		}
		if(!carsVo.getOrgId().equals(search.getOrgId())) {
			result.setError("抱歉，你没有此权限");
			return result;
		}
		carsVo.setOverDelete(true);
		return save(carsVo, result, carsVo.getGoodsCarsId());
	}

	@Override
	public Result shopGoodsCarsEdit(ShopGoodsCarsSearch search, Result result, SystemUsers users) throws Exception {
		ShopGoodsCars goodsCars = null;
		if(StringUtil.isNotEmptyMoreThenZero(search.getGoodsCarsId())) {
			goodsCars = shopGoodsCarsDao.selectById(search.getGoodsCarsId());
			if(goodsCars == null) {
				result.setError("抱歉，你选择得车辆不存在或者已删除");
				return result;
			}
			if(!goodsCars.getOrgId().equals(users.getOrgId())) {
				result.setError("抱歉，你没有此权限");
				return result;
			}
		}else {
			goodsCars = new ShopGoodsCars();
			goodsCars.setOverDelete(false);
			goodsCars.setCreateDate(new Date());
			goodsCars.setOverOffShelf(true);
			OrganizationVo organizationVo = organizationDao.selectById(users.getOrgId());
			if(organizationVo == null) {
				result.setError("抱歉，你的门店数据不存在，你没有此权限");
				return result;
			}
			goodsCars.setOrgId(organizationVo.getOrgId());
			goodsCars.setOrgName(organizationVo.getShortName());
			goodsCars.setOrgCode(organizationVo.getOrgCode());
			goodsCars.setSystemUsersId(users.getUsersId());
		}
		
//		goodsCars.setAreaId(organizationVo.getAreaId());
//		goodsCars.setCityId(organizationVo.getCityId());
//		goodsCars.setProvinceId(organizationVo.getProvinceId());
		
		if(search.getCarsId() != null) {
			CarsVo carVo = carsDao.selectById(search.getCarsId());
			if(carVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车型不存在");
				return result;
			}
			goodsCars.setCarsId(carVo.getCarId());
			goodsCars.setCarsName(carVo.getCarName());
			goodsCars.setGuidingPrice(new BigDecimal(carVo.getPrice()));
			goodsCars.setImage(carVo.getIndexImage());
			goodsCars.setFamilyId(carVo.getFamilyId());
			goodsCars.setBrandId(carVo.getBrandId());
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
			goodsCars.setColourId(colourVo.getCarColourId());
			goodsCars.setColourName(colourVo.getCarColourName());
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
			goodsCars.setInteriorId(interiorVo.getInteriorId());
			goodsCars.setInteriorName(interiorVo.getInteriorName());
		}
		if(StringUtil.isNotEmptyMoreThenZero(search.getSaleingNumber())) {
			goodsCars.setSaleingNumber(search.getSaleingNumber());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入可售数量");
			return result;
		}
		if(StringUtil.isNotEmptyAll(search.getDiscountPriceOnLine())) {
			goodsCars.setDiscountPriceOnLine(search.getDiscountPriceOnLine());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入线上优惠");
			return result;
		}
		if(StringUtil.isNotEmptyAll(search.getDiscountPriceUnderLine())) {
			goodsCars.setDiscountPriceUnderLine(search.getDiscountPriceUnderLine());
		}else {
//			result.setError(ResultCode.CODE_STATE_4005, "请输入线下优惠");
//			return result;
		}
		if(search.getOverInsurance() != null) {
			goodsCars.setOverInsurance(search.getOverInsurance());
		}
		if(StringUtil.isNotEmpty(search.getBareCarPriceOnLine())) {
			goodsCars.setBareCarPriceOnLine(search.getBareCarPriceOnLine());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入线上裸车价");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getDepositPrice())) {
			goodsCars.setDepositPrice(search.getDepositPrice());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输定金金额");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getBareCarPriceUnderLine())) {
			goodsCars.setBareCarPriceUnderLine(search.getBareCarPriceUnderLine());
		}else {
//			result.setError(ResultCode.CODE_STATE_4005, "请输入线下裸车价");
//			return result;
		}
		if(StringUtil.isNotEmpty(search.getInvoicePrice())) {
			goodsCars.setInvoicePrice(search.getInvoicePrice());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入开票价");
			return result;
		}
		
		//查找地址
		if(StringUtil.isEmpty(search.getProvinceId()) || StringUtil.isEmpty(search.getCityId()) || StringUtil.isEmpty(search.getAreaId())) {
			result.setError(ResultCode.CODE_STATE_4005, "省市区请选择完整");
			return result;
		}
		List<String> regionIds = new ArrayList<String>();//sortField
		regionIds.add(search.getProvinceId());
		regionIds.add(search.getCityId());
		regionIds.add(search.getAreaId());
		Map<String, Object> params = new HashMap<>();
		params.put("sortField", true);
		params.put("regionIds", regionIds);
		List<SystemRegionVo> regionVos = systemRegionDao.select(params);//查出三个地区，按照省市区排列
		goodsCars.setProvinceId(regionVos.get(0).getRegionId());
		goodsCars.setProvinceName(regionVos.get(0).getRegionName());
		goodsCars.setCityId(regionVos.get(1).getRegionId());
		goodsCars.setCityName(regionVos.get(1).getRegionName());
		goodsCars.setAreaId(regionVos.get(2).getRegionId());
		goodsCars.setAreaName(regionVos.get(2).getRegionName());
		
		if(StringUtil.isNotEmpty(search.getWarehouseId())) {
			SystemWarehouse warehouse = systemWarehouseDao.selectById(search.getWarehouseId());
			if(warehouse == null || !warehouse.getOrgId().equals(users.getOrgId()) || (warehouse.getIsDelete()!=null && warehouse.getIsDelete())) {
				result.setError(ResultCode.CODE_STATE_4005, "你的门店不存在此仓库，选择错误");
				return result;
			}
			goodsCars.setWarehouseId(warehouse.getWarehouseId());
			goodsCars.setWarehouseName(warehouse.getWarehouseName());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择仓库");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getLogisticsCycle())) {
			goodsCars.setLogisticsCycle(search.getLogisticsCycle());
		}
		if(StringUtil.isNotEmpty(search.getLogisticsPrice())) {
			goodsCars.setLogisticsPrice(search.getLogisticsPrice());
		}
		if(StringUtil.isNotEmpty(search.getInvoiceCycle())) {
			goodsCars.setInvoiceCycle(search.getInvoiceCycle());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入开票周期");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getDateOfManufacture())) {
			System.out.println("生产日期:"+search.getDateOfManufacture());
			goodsCars.setDateOfManufacture(DateUtil.format(search.getDateOfManufacture(),true));
		}
//		if(StringUtil.isNotEmpty(search.getDateOfManufacture())) {
//			goodsCars.setDateOfManufacture(DateUtil.format(search.getDateOfManufacture()));
//		}
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			goodsCars.setRemarks(search.getRemarks());
		}
		if(StringUtil.isNotEmpty(search.getCarsImage())) {
			goodsCars.setCarsImage(search.getCarsImage());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请上传车辆图片");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getCarsImages())) {
			goodsCars.setCarsImages(search.getCarsImages());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请上传车辆图片");
			return result;
		}
		
		goodsCars.setLongitude(search.getLongitude());
		goodsCars.setLatitude(search.getLatitude());
		return save(goodsCars, result, goodsCars.getGoodsCarsId());
	}

	@Override
	public Result brandListList(CarsSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<>();
		params.put("overOffShelf", false);//只展示上架的车辆
		params.put("brandGrop", true);//只展示上架的车辆
		List<ShopGoodsCarsVo> carsVos = shopGoodsCarsDao.select(params);
		Set<Integer> set = new HashSet<>();
		for(ShopGoodsCarsVo goodsCarsVo : carsVos) {
			set.add(goodsCarsVo.getBrandId());
		}
		List<Map<String,Object>> maps = new ArrayList<>();
		if(set.size() <= 0) {
			return new Result(maps);
		}
		params.clear();
		params.put("ids", set);
		List<CarBrandVo> carBrandVos = carBrandDao.select(params);
		for(CarBrandVo brandVo : carBrandVos) {
			Map<String,Object> map = new HashMap<>();
			map.put("brandId", brandVo.getBrandId());
			map.put("brandName", brandVo.getBrandName());
			map.put("brandImage", brandVo.getImgUrl());
			maps.add(map);
		}
		return new Result(maps);		
	}

	@Override
	public Result cityListList(CarsSearch search, Result result) throws Exception {
		Map<String,Object> params = new HashMap<>();
		params.put("overOffShelf", false);//只展示上架的车辆
		params.put("cityGrop", true);//根据地区分组
		List<ShopGoodsCarsVo> carsVos = shopGoodsCarsDao.select(params);
		Set<String> set = new HashSet<>();
		for(ShopGoodsCarsVo goodsCarsVo : carsVos) {
			set.add(goodsCarsVo.getCityId());
		}
		List<Map<String,Object>> maps = new ArrayList<>();
		if(set.size() <= 0) {
			return new Result(maps);
		}
		params.clear();
		params.put("regionIds", set);
		List<SystemRegionVo> regionVos = regionDao.select(params);
		for(SystemRegionVo regionVo : regionVos) {
			Map<String,Object> map = new HashMap<>();
			map.put("regionId", regionVo.getRegionId());
			map.put("regionName", regionVo.getRegionName());
			maps.add(map);
		}
		return new Result(maps);
	}

}
