package main.com.stock.service.impl;

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

import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarInteriorDao;
import main.com.car.dao.dao.CarSupplierDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.po.CarColour;
import main.com.car.dao.po.Cars;
import main.com.car.dao.search.CarSupplierSearch;
import main.com.car.dao.vo.CarColourVo;
import main.com.car.dao.vo.CarInteriorVo;
import main.com.car.dao.vo.CarSupplierVo;
import main.com.car.dao.vo.CarsVo;
import main.com.customer.dao.search.CustomerCustomerOrgSearch;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.dao.StockStorageDao;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.po.StockStorage;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.search.StockStorageSearch;
import main.com.stock.dao.vo.StockCarVo;
import main.com.stock.dao.vo.StockStorageVo;
import main.com.stock.service.StockStorageService;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.dao.SystemWarehouseDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.po.SystemWarehouse;
import main.com.system.dao.vo.OrganizationVo;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class StockStorageServiceImpl extends BaseServiceImpl<StockStorage> implements StockStorageService{

	@Autowired
	StockStorageDao stockStorageDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	CarSupplierDao carSupplierDao;
	
	@Autowired
	StockCarDao stockCarDao;
	
	@Autowired
	CarColourDao carColourDao;
	
	@Autowired
	CarInteriorDao carInteriorDao;
	
	@Autowired
	SystemWarehouseDao systemWarehouseDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Override
	protected BaseDao<StockStorage> getBaseDao() {
		return stockStorageDao;
	}

	@Override
	public Result storageList(StockStorageSearch search, Result result, SystemUsers systemUsers) throws Exception {
		Map<String,Object> params = getParams(search,systemUsers);
		List<StockStorageVo> carSupplierVos = stockStorageDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = stockStorageDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(StockStorageVo stockStorage : carSupplierVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("supplierId", stockStorage.getSupplierId());
			map.put("supplierName", stockStorage.getSupplierName());
			map.put("remark", stockStorage.getRemarks());			
			map.put("orgId", stockStorage.getOrgId());			
			map.put("orgName", stockStorage.getOrgName());			
			map.put("storageId", stockStorage.getStorageId());			
			map.put("storageCode", stockStorage.getStorageCode());			
			map.put("storageSource", stockStorage.getStorageSource());			
			map.put("systemUsersId", stockStorage.getSystemUsersId());			
			map.put("systemUserName", stockStorage.getSystemUserName());			
			map.put("totalPurchase", stockStorage.getTotalPurchase());			
			map.put("totalPurchasePrice", stockStorage.getTotalPurchasePrice());
			map.put("logisticsCost", stockStorage.getLogisticsCost());
			map.put("createDate", DateUtil.format(stockStorage.getCreateDate()));
			map.put("contractImage", stockStorage.getContractImage());
			map.put("contractNumber", stockStorage.getContractNumber());
			map.put("certificateDate", stockStorage.getCertificateDate());
//			if(stockStorage.getStockCars() != null && stockStorage.getStockCars().size() > 0) {
//				map.put("carsNumber", stockStorage.getStockCars().size());
//				Double carUnitPrice = 0d;
//				for(StockCar car : stockStorage.getStockCars()) {
//					carUnitPrice += car.getUnitPrice();
//				}
//				map.put("carsPiace", carUnitPrice);
//			}else {
//				map.put("carsNumber", 0);
//				map.put("carsPiace", 0);
//			}
			map = TakeCareMapDate.cutNullMap(map);
			maps.add(map);
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}
	
	public Map<String,Object> getParams(StockStorageSearch search,SystemUsers systemUsers){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getStartDate())) {
			params.put("startDate", search.getStartDate());
		}
		if(StringUtil.isNotEmpty(search.getEndDate())) {
			params.put("endDate", search.getEndDate());
		}
		if(search.getSupplierId() != null) {
			params.put("supplierId", search.getSupplierId());
		}
		if(search.getStorageSource() != null) {
			params.put("storageSource", search.getStorageSource());
		}
		if(StringUtil.isNotEmpty(search.getStorageCode())) {
			params.put("storageCode", search.getStorageCode());
		}
//		params.put("orgCode", systemUsers.getOrgCode());//只能看自己
		params.put("orgId", systemUsers.getOrgId());
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	public Result storageEdit(StockStorageSearch search, Result result, SystemUsers systemUsers) throws Exception {
		StockStorage stockStorage = null;
		if(search.getStorageId() == null) {
			stockStorage = new StockStorage();
			stockStorage.setStorageCode(stockStorageDao.getStorageCode());
			stockStorage.setOrgId(systemUsers.getOrgId());
			stockStorage.setOrgName(systemUsers.getOrgName());
			stockStorage.setIsDelete(false);
			stockStorage.setCreateDate(new Date());
			stockStorage.setSystemUserName(systemUsers.getRealName());
			stockStorage.setSystemUsersId(systemUsers.getUsersId());
		}else {
			stockStorage = stockStorageDao.selectById(search.getStorageId());
			if(stockStorage == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的数据已删除或不存在，请重新选择数据操作");
				return result;
			}
		}
		if(search.getStorageSource() != null) {
			stockStorage.setStorageSource(search.getStorageSource());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择车辆来源");
			return result;
		}
		if(search.getSystemUsersId() != null) {
			SystemUsers users = systemUsersDao.selectByIdInUse(search.getSystemUsersId());
			if(systemUsers == null) {
				result.setError(ResultCode.CODE_STATE_4005, "所选择的采购员数据不存在或已禁用，请重新选择");
				return result;
			}
			stockStorage.setSystemUsersId(users.getUsersId());
			stockStorage.setSystemUserName(users.getRealName());
		}
		if(search.getSupplierId() != null) {
			CarSupplierVo carSupplier = carSupplierDao.selectById(search.getSupplierId());
			if(carSupplier == null) {
				result.setError(ResultCode.CODE_STATE_4005, "所选择的供应商数据不存在或已删除，请重新选择");
				return result;
			}
			stockStorage.setSupplierId(carSupplier.getSupplierId());
			stockStorage.setSupplierName(carSupplier.getSupplierName());
		}
		if(StringUtil.isNotEmpty(search.getContractNumber())) {
			stockStorage.setContractNumber(search.getContractNumber());
		}
		if(StringUtil.isNotEmpty(search.getContractImage())) {
			stockStorage.setContractImage(search.getContractImage());
		}
		if(StringUtil.isNotEmpty(search.getRemark())) {
			stockStorage.setRemarks(search.getRemark());
		}
		if(search.getCertificateDate() != null) {
			switch (search.getCertificateDate()) {
			case GeneralConstant.StockStorageAbstract.CertificateDate_one:
				stockStorage.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_one);
				break;
			case GeneralConstant.StockStorageAbstract.CertificateDate_two:
				stockStorage.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_two);
				break;
			case GeneralConstant.StockStorageAbstract.CertificateDate_three:
				stockStorage.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_three);
				break;
			case GeneralConstant.StockStorageAbstract.CertificateDate_four:
				stockStorage.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_four);
				break;
			case GeneralConstant.StockStorageAbstract.CertificateDate_five:
				stockStorage.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_five);
				break;
			default:
				result.setError("合格证时间选择错误，请重新选择");
				return result;
			}			
		}
//		if(search.getCertificateDate() != null &&(
//				GeneralConstant.StockStorageAbstract.CertificateDate_one.equals(search.getCertificateDate()) || 
//				GeneralConstant.StockStorageAbstract.CertificateDate_two.equals(search.getCertificateDate()) ||
//				GeneralConstant.StockStorageAbstract.CertificateDate_three.equals(search.getCertificateDate())
//			)) {
//			stockStorage.setCertificateDate(search.getCertificateDate());
//		}
		if(search.getLogisticsCost() != null && search.getLogisticsCost() > 0) {
			stockStorage.setLogisticsCost(search.getLogisticsCost());
		}
		if(search.getStorageId() == null) {
			return stockStorageDao.insertAndResultIT(stockStorage, result);
		}else {
			return stockStorageDao.updateByIdAndResultIT(stockStorage, result);
		}
	}

	@Override
	public Result storageInfo(StockStorageSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getStorageId() == null) {
			result.setOK(ResultCode.CODE_STATE_4005, "请选择具体库存单数据查看");
			return result;
		}
		StockStorageVo stockStorage = stockStorageDao.selectById(search.getStorageId());
		if(stockStorage == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你所选择的数据已删除或不存在，请重新选择数据操作");
			return result;
		}
		Map<String,Object> storageMap = new HashMap<String, Object>();
//		storageMap.put("supplierId", stockStorage.getSupplierId());
//		storageMap.put("supplierName", stockStorage.getSupplierName());
//		storageMap.put("remark", stockStorage.getRemarks());			
//		storageMap.put("orgId", stockStorage.getOrgId());			
//		storageMap.put("orgName", stockStorage.getOrgName());			
//		storageMap.put("storageId", stockStorage.getStorageId());			
//		storageMap.put("storageCode", stockStorage.getStorageCode());			
//		storageMap.put("storageSource", stockStorage.getStorageSource());			
//		storageMap.put("systemUserName", stockStorage.getSystemUserName());			
//		storageMap.put("totalPurchase", stockStorage.getTotalPurchase());			
//		storageMap.put("totalPurchasePrice", stockStorage.getTotalPurchasePrice());
//		storageMap.put("logisticsCost", stockStorage.getLogisticsCost());
//		storageMap.put("createDate", DateUtil.format(stockStorage.getCreateDate()));
//		storageMap.put("contractImage", stockStorage.getContractImage());
		
		storageMap.put("supplierId", stockStorage.getSupplierId());
		storageMap.put("supplierName", stockStorage.getSupplierName());
		storageMap.put("remark", stockStorage.getRemarks());			
		storageMap.put("orgId", stockStorage.getOrgId());			
		storageMap.put("orgName", stockStorage.getOrgName());			
		storageMap.put("storageId", stockStorage.getStorageId());			
		storageMap.put("storageCode", stockStorage.getStorageCode());			
		storageMap.put("storageSource", stockStorage.getStorageSource());			
		storageMap.put("systemUsersId", stockStorage.getSystemUsersId());			
		storageMap.put("systemUserName", stockStorage.getSystemUserName());			
		storageMap.put("totalPurchase", stockStorage.getTotalPurchase());			
		storageMap.put("totalPurchasePrice", stockStorage.getTotalPurchasePrice());
		storageMap.put("logisticsCost", stockStorage.getLogisticsCost());
		storageMap.put("createDate", DateUtil.format(stockStorage.getCreateDate()));
		storageMap.put("contractImage", stockStorage.getContractImage());
		storageMap.put("contractNumber", stockStorage.getContractNumber());
		storageMap.put("certificateDate", stockStorage.getCertificateDate());
		
		//查询车辆 //根据车型，颜色，内饰分类
//		Map<String,Object> params = new HashMap<String, Object>();
//		params.put("storageId", stockStorage.getStorageId());
//		params.put("groupBy", true);
//		List<StockCarVo> cars = stockCarDao.select(params);
//		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
//		for(StockCarVo stockCarVo : cars) {
//			Map<String, Object> map = new HashMap<String,Object>();
//			map.put("carsInfo", stockCarVo.getCarsInfo());
//			map.put("carsId", stockCarVo.getCarsId());
//			map.put("colourId", stockCarVo.getColourId());
//			map.put("colourName", stockCarVo.getColourName());
//			map.put("interiorId", stockCarVo.getInteriorId());
//			map.put("interiorName", stockCarVo.getInteriorName());
//			map.put("number", stockCarVo.getNumber());
//			map.put("unitPrice", stockCarVo.getUnitPrice());
//			maps.add(TakeCareMapDate.cutNullMap(map));
//		}
//		Map<String,Object> allMap = new HashMap<String, Object>();
//		allMap.put("stockStorage", TakeCareMapDate.cutNullMap(storageMap));
//		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "", storageMap);
		return result;
	}

	@Override
	public Result storageInsert(StockStorageSearch search, Result result, SystemUsers systemUsers) throws Exception {
		StockStorage stockStorage = new StockStorage();
		stockStorage.setStorageCode(stockStorageDao.getStorageCode());
		stockStorage.setOrgId(systemUsers.getOrgId());
		stockStorage.setOrgName(systemUsers.getOrgName());
		stockStorage.setIsDelete(false);
		if(stockStorageDao.insert(stockStorage)) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("storageCode", stockStorage.getStorageCode());
			map.put("storageId", stockStorage.getStorageId());
			result.setOK(ResultCode.CODE_STATE_200, "", map);
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "入库单生成错误，请联系IT部");
		}
		return result;
	}

	@Override
	public Result storageCarList(StockCarSearch search, Result result, SystemUsers users) throws Exception {
//		if(search.getStorageId() == null || search.getCarsId() == null || search.getInteriorId() == null || search.getColourId() == null) {
//			result.setError(ResultCode.CODE_STATE_4005, "参数不全，无法执行此操作");
//			return result;
//		}
		if(search.getStorageId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体的入库单查看");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("storageId", search.getStorageId());
		if(StringUtil.isNotEmpty(search.getCarsId())) {
			params.put("carsId", search.getCarsId());
		}
		if(StringUtil.isNotEmpty(search.getColourId())) {
			params.put("colourId", search.getColourId());
		}
		if(StringUtil.isNotEmpty(search.getInteriorId())) {
			params.put("interiorId", search.getInteriorId());
		}
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		List<StockCarVo> cars = stockCarDao.select(params);
		Map<String,Object> carparams = new HashMap<String, Object>();
		Set<Integer> set = new HashSet<Integer>();
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
		for(CarsVo carsVo : carsVos) {
			for(StockCarVo stockCarVo : cars) {
				if(stockCarVo.getCarsId().equals(carsVo.getCarId())) {
					stockCarVo.setCarsVo(carsVo);
				}
			}
		}
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = stockCarDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(StockCarVo stockCarVo : cars) {
			if(StringUtil.isEmpty(stockCarVo.getStockCarId())) {
				continue;
			}
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("carsName", stockCarVo.getCarsInfo());
			map.put("frameNumber", stockCarVo.getFrameNumber());
			map.put("carsId", stockCarVo.getCarsId());
			map.put("colourId", stockCarVo.getColourId());
			map.put("colourName", stockCarVo.getColourName());
			map.put("interiorId", stockCarVo.getInteriorId());
			map.put("interiorName", stockCarVo.getInteriorName());
			map.put("number", stockCarVo.getNumber());
			map.put("unitPrice", stockCarVo.getUnitPrice());
			map.put("certificateDate", stockCarVo.getCertificateDate());
			map.put("certificateNumber", stockCarVo.getCertificateNumber());
			map.put("warehouseId", stockCarVo.getWarehouseId());
			map.put("warehouseName", stockCarVo.getWarehouseName());
			map.put("stockCarImages", stockCarVo.getStockCarImages());
			map.put("engineNumber", stockCarVo.getEngineNumber());
			map.put("guidingPrice", stockCarVo.getGuidingPrice());
			map.put("stockCarId", stockCarVo.getStockCarId());
			map.put("storageId", stockCarVo.getStorageId());
			map.put("mileage", stockCarVo.getMileage());
			if(stockCarVo.getOverStrongInsurance() != null && stockCarVo.getOverStrongInsurance()) {
				map.put("overStrongInsurance", 1);
			}else {
				map.put("overStrongInsurance", 0);
			}
			map.put("followInformation", stockCarVo.getFollowInformation());
			map.put("warehouseId", stockCarVo.getWarehouseId());
			map.put("warehouseName", stockCarVo.getWarehouseName());
			if(stockCarVo.getCarsVo() != null) {
				map.put("brandId", stockCarVo.getCarsVo().getBrandId());
				map.put("familyId", stockCarVo.getCarsVo().getFamilyId());
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
	@Transactional
	public Result storageCarDelete(StockCarSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getStockCarId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体数据进行操作");
			return result;
		}
		StockCarVo stockCarVo = stockCarDao.selectById(search.getStockCarId());
		if(stockCarVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的数据不存在或者已删除，请重新选择");
			return result;
		}
		StockStorage stockStorage = stockStorageDao.selectById(stockCarVo.getStorageId());
		stockStorage.setTotalPurchase(stockStorage.getTotalPurchase() - 1);
		stockStorage.setTotalPurchasePrice(stockStorage.getTotalPurchasePrice() - stockCarVo.getUnitPrice());
		stockCarVo.setIsDelete(true);
		if(stockCarDao.updateById(stockCarVo)) {
			if(stockStorageDao.updateById(stockStorage)) {
				result.setOK(ResultCode.CODE_STATE_200, "删除成功");
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "数据保存失败，请联系管理员");
			}
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "数据保存失败，请联系管理员");
		}
		return result;
	}

	@Override
	public Result storageCarEdit(StockCarSearch search, Result result, SystemUsers users) throws Exception {
		StockCarVo stockCarVo = null;
		StockStorage stockStorage = null;
		if(StringUtil.isNotEmpty(users.getOrgId())) {
			Organization organization = organizationDao.selectById(users.getOrgId());
			if(organization.getOrgLevel().equals(GeneralConstant.Org.Level_3)) {//门店不能手动添加库存
				result.setError(ResultCode.CODE_STATE_4005, "你的入库单是系统自动生成，不需要手动添加车辆");
				return result;
			}
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请联系管理员");
			return result;
		}
		if(search.getStockCarId()== null) {
			stockCarVo = new StockCarVo();
			stockCarVo.setIsDelete(false);
			stockCarVo.setCreateDate(new Date());
			if(search.getStorageId() != null) {
				stockStorage = stockStorageDao.selectById(search.getStorageId());
				if(stockStorage == null) {
					result.setError(ResultCode.CODE_STATE_4005, "你所选择的入库单不存在");
					return result;
				}
				stockCarVo.setStorageId(stockStorage.getStorageId());
//				if(search.getUnitPrice() == null || search.getUnitPrice() <= 0) {
//					result.setError(ResultCode.CODE_STATE_4005, "请输入单价");
//					return result;
//				}else {
//					stockCarVo.setUnitPrice(search.getUnitPrice());
//				}
				if(stockStorage.getTotalPurchase() == null || stockStorage.getTotalPurchase() <= 0) {
					stockStorage.setTotalPurchase(1);
				}else {
					stockStorage.setTotalPurchase(stockStorage.getTotalPurchase() + 1);
				}
				stockCarVo.setOrgId(stockStorage.getOrgId());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "请选择相应的入库单");
				return result;
			}
		}else {
			stockCarVo = stockCarDao.selectById(search.getStockCarId());
			if(stockCarVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的数据已删除或不存在，请重新选择数据操作");
				return result;
			}
			stockStorage = stockStorageDao.selectById(stockCarVo.getStorageId());
		}
		if(search.getCarsId() != null) {
			CarsVo carVo = carsDao.selectById(search.getCarsId());
			if(carVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车型不存在");
				return result;
			}
			stockCarVo.setCarsId(carVo.getCarId());
			StringBuffer buffer = new StringBuffer();
				//品牌(本田) + 车系（雅阁） + 年款（2016/2017） + 排量(2.0) + 变速箱类型（手动自动） + 款式（高中低配）
				buffer.setLength(0);
				buffer.append(carVo.getBrandName()).append(" ");
				buffer.append(carVo.getFamilyName()).append(" ");
				buffer.append(carVo.getYearPattern()).append("款").append(" ");
				buffer.append(carVo.getPl()).append(" ");
				buffer.append(carVo.getGearboxName()).append(carVo.getStyleName());
			stockCarVo.setCarsInfo(buffer.toString());
			stockCarVo.setGuidingPrice(new BigDecimal(carVo.getPrice()));
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
			stockCarVo.setColourId(colourVo.getCarColourId());
			stockCarVo.setColourName(colourVo.getCarColourName());
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
			stockCarVo.setInteriorId(interiorVo.getInteriorId());
			stockCarVo.setInteriorName(interiorVo.getInteriorName());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择的车辆内饰");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getFrameNumber())) {
			//检查唯一性
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("frameNumber", search.getFrameNumber());
			if(StringUtil.isNotEmpty(search.getStockCarId())) {
				params.put("isnotCarId", search.getStockCarId());
			}
			List<StockCarVo> stockCarF = stockCarDao.select(params);
			if(stockCarF != null && stockCarF.size() > 0) {
				result.setError(ResultCode.CODE_STATE_4005, "此车架号已存在，车架号必须唯一，请重新核对后输入");
				return result;
			}
			stockCarVo.setFrameNumber(search.getFrameNumber());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入车架号");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getEngineNumber())) {
			//检查唯一性
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("engineNumber", search.getEngineNumber());
			if(StringUtil.isNotEmpty(search.getStockCarId())) {
				params.put("isnotCarId", search.getStockCarId());
			}
			List<StockCarVo> stockCarF = stockCarDao.select(params);
			if(stockCarF != null && stockCarF.size() > 0) {
				result.setError(ResultCode.CODE_STATE_4005, "此发动机号已存在，发动机号必须唯一，请重新核对后输入");
				return result;
			}
			stockCarVo.setEngineNumber(search.getEngineNumber());
		}else {
//			result.setError(ResultCode.CODE_STATE_4005, "请输入发动机编号");
//			return result;
		}
		if(search.getCertificateNumber() != null) {
			stockCarVo.setCertificateNumber(search.getCertificateNumber());
		}				
		if(search.getWarehouseId() != null) {
			SystemWarehouse systemWarehouse = systemWarehouseDao.selectById(search.getWarehouseId());
			if(systemWarehouse == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你选择的仓库已删除或不存在，请重新选择");
				return result;
			}
			stockCarVo.setWarehouseId(search.getWarehouseId());
			stockCarVo.setWarehouseName(systemWarehouse.getWarehouseName());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择仓库");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getStockCarImages())) {
			stockCarVo.setStockCarImages(search.getStockCarImages());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请上传验车照片");
			return result;
		}
		if(search.getUnitPrice() != null && search.getUnitPrice() > 0) {//除了新增，不能再修改单价
			stockCarVo.setUnitPrice(search.getUnitPrice());
			if(stockStorage.getTotalPurchasePrice() == null || stockStorage.getTotalPurchasePrice() <= 0) {
				stockStorage.setTotalPurchasePrice(search.getUnitPrice());
			}else {
				stockStorage.setTotalPurchasePrice(stockStorage.getTotalPurchasePrice() + search.getUnitPrice());
			}
		}
		if(search.getStockCarId()== null) {
//			return stockCarDao.insertAndResultIT(stockCarVo, result);
			if(stockCarDao.insert(stockCarVo)) {
				//修改入库单的采购总量等
				if(stockStorageDao.updateById(stockStorage)) {
					result.setOK(ResultCode.CODE_STATE_200, "保存成功");
				}else {
					result.setError(ResultCode.CODE_STATE_4005, "更新库存单失败，插入数据失败");
					throw new Exception("更新库存单失败，插入数据失败");
				}
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "数据保存失败，请联系管理员");
			}
			return result;
		}else {
			if(stockCarDao.updateById(stockCarVo)) {
				//修改入库单的采购总量等
				if(stockStorageDao.updateById(stockStorage)) {
					result.setOK(ResultCode.CODE_STATE_200, "保存成功");
				}else {
					result.setError(ResultCode.CODE_STATE_4005, "更新库存单失败，插入数据失败");
					throw new Exception("更新库存单失败，插入数据失败");
				}
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "数据保存失败，请联系管理员");
			}
			return result;
//			return stockCarDao.updateByIdAndResultIT(stockCarVo, result);
		}
	}

}
