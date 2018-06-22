package main.com.stock.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.iterators.ArrayListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarInteriorDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.dao.OrgCarsConfigureDao;
import main.com.car.dao.po.OrgCarsConfigure;
import main.com.car.dao.vo.CarColourVo;
import main.com.car.dao.vo.CarInteriorVo;
import main.com.car.dao.vo.CarsVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.dao.StockStorageDao;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.po.StockStorage;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.vo.StockCarVo;
import main.com.stock.dao.vo.StockStorageVo;
import main.com.stock.service.StockCarService;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.DateUtil;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class StockCarServiceImpl extends BaseServiceImpl<StockCar> implements StockCarService {

	@Autowired
	StockCarDao stockCarDao;
	
	@Autowired
	StockStorageDao stockStorageDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	OrgCarsConfigureDao orgCarsConfigureDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CarColourDao carColourDao;
	
	@Autowired
	CarInteriorDao carInteriorDao;
	
	@Override
	protected BaseDao<StockCar> getBaseDao() {
		return stockCarDao;
	}

	@Override
	public Result stockCarList(StockCarSearch search, Result result, SystemUsers users)throws Exception{
//		if(search.getStorageId() == null || search.getCarsId() == null || search.getInteriorId() == null || search.getColourId() == null) {
//			result.setError(ResultCode.CODE_STATE_4005, "参数不全，无法执行此操作");
//			return result;
//		}
		Map<String,Object> params = new HashMap<String, Object>();
//		params.put("orgId", search.getOrgId());
		params.put("carsInfo", search.getCarsName());
//		params.put("carsId", search.getCarsId());
//		params.put("interiorId", search.getInteriorId());
//		params.put("colourId", search.getColourId());
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
		if(set != null && set.size() > 0) {
			params.put("storageIds", set);
		}else {//没有入库单就没有库存
			allMap.put("page", search.getPage());
			allMap.put("total", 0);
			allMap.put("rows", 10);
			allMap.put("list", new ArrayList<Map<String,Object>>());
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		params.put("overSure", 1);
		List<StockCarVo> cars = stockCarDao.selectByOrg(params);
		//把数据库压力转嫁给程序
		for(StockCarVo stockCarVo : cars) {
			for(StockStorageVo storageVo : storages) {
				if(stockCarVo.getStorageId().equals(storageVo.getStorageId())) {
					stockCarVo.setStorageVo(storageVo);
				}
			}
		}
		Long total = stockCarDao.selectCountByOrg(params);
		if(total == null) {
			total = 0l;
		}
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(StockCarVo stockCarVo : cars) {
			Map<String, Object> map = new HashMap<String,Object>();
//			map.put("frameNumber", stockCarVo.getFrameNumber());
			map.put("carsId", stockCarVo.getCarsId());
//			map.put("engineNumber", stockCarVo.getEngineNumber());
//			map.put("certificateNumber", stockCarVo.getCertificateNumber());
			map.put("warehouseId", stockCarVo.getWarehouseId());
			map.put("warehouseName", stockCarVo.getWarehouseName());
//			map.put("stockCarImages", stockCarVo.getStockCarImages());
			map.put("invoicePrice", stockCarVo.getInvoicePrice());
			map.put("depositPrice", stockCarVo.getDepositPrice());
			map.put("discountPrice", stockCarVo.getDiscountPrice());
			map.put("isOnLine", stockCarVo.getIsOnLine());
//			map.put("stockCarId", stockCarVo.getStockCarId());
//			map.put("storageId", stockCarVo.getStorageId());
			map.put("number", stockCarVo.getNumber());
			map.put("isOnLine", stockCarVo.getIsOnLine());
			map.put("carsName", stockCarVo.getCarsInfo());
			map.put("colourName", stockCarVo.getColourName());
			map.put("colourId", stockCarVo.getColourId());
			map.put("interiorId", stockCarVo.getInteriorId());
			map.put("interiorName", stockCarVo.getInteriorName());
			map.put("guidingPrice", stockCarVo.getGuidingPrice());
			map.put("createDate", DateUtil.format(stockCarVo.getCreateDate()));
			if(stockCarVo.getStorageVo() != null) {
				map.put("orgId", stockCarVo.getStorageVo().getOrgId());
				map.put("orgName", stockCarVo.getStorageVo().getOrgName());
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
	public Result stockCarEdit(StockCarSearch search, Result result, SystemUsers users) throws Exception {
//		StockCarVo stockCarVo = null;
//		if(search.getStockCarId()== null) {
//			result.setError(ResultCode.CODE_STATE_4005, "请选择具体的库存车辆数据进行操作");
//			return result;
//		}else {
//			stockCarVo = stockCarDao.selectById(search.getStockCarId());
//			if(stockCarVo == null) {
//				result.setError(ResultCode.CODE_STATE_4005, "你所选择的数据已删除或不存在，请重新选择数据操作");
//				return result;
//			}
//		}
		if(search.getCarsId() == null || search.getInteriorId() == null || search.getColourId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "参数不全，无法执行此操作");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("carsId", search.getCarsId());
		params.put("interiorId", search.getInteriorId());
		params.put("colourId", search.getColourId());
//		params.put("storageId", search.getStorageId());
		//根据用户org查询查询出自身跟下级的库存单
		Map<String,Object> selectMap = new HashMap<String, Object>();
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请联系管理员");
			return result;
		}
		Organization organization = null;
		if(StringUtil.isNotEmpty(search.getOrgId())) {
			organization = organizationDao.selectById(search.getOrgId());
			if(organization == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的公司数据不存在，请重新选择");
				return result;
			}
			selectMap.put("orgId", organization.getOrgId());
		}else {
			selectMap.put("orgId", users.getOrgId());
			organization = organizationDao.selectById(users.getOrgId());
		}
		List<StockStorageVo> storages =  stockStorageDao.select(selectMap);
		Set<Integer> set = new HashSet<>();
		for(StockStorage storage : storages) {
			set.add(storage.getStorageId());
		}
		params.put("storageIds", set);
		List<StockCarVo> cars = stockCarDao.select(params);
		params.put("orgId", users.getOrgId());
		List<OrgCarsConfigure> orgCarsConfigures = orgCarsConfigureDao.select(params);
		if(orgCarsConfigures == null || orgCarsConfigures.size() <= 0) {
			OrgCarsConfigure carsConfigure = new OrgCarsConfigure();
			carsConfigure.setCarsId(search.getCarsId());
//			carsConfigure.setInteriorId(search.getInteriorId());
			carsConfigure.setColourId(search.getColourId());
			carsConfigure.setCreateDate(new Date());
			carsConfigure.setIsDelete(false);
			carsConfigure.setOrgId(users.getOrgId());
			if(organization != null) {
				carsConfigure.setOrgLevel(organization.getOrgLevel());
			}
			if(search.getCarsId() != null) {
				CarsVo carVo = carsDao.selectById(search.getCarsId());
				if(carVo == null) {
					result.setError(ResultCode.CODE_STATE_4005, "你所选择的车型不存在");
					return result;
				}
				carsConfigure.setCarsId(carVo.getCarId());
				StringBuffer buffer = new StringBuffer();
					//品牌(本田) + 车系（雅阁） + 年款（2016/2017） + 排量(2.0) + 变速箱类型（手动自动） + 款式（高中低配）
					buffer.setLength(0);
					buffer.append(carVo.getBrandName()).append(" ");
					buffer.append(carVo.getFamilyName()).append(" ");
					buffer.append(carVo.getYearPattern()).append("款").append(" ");
					buffer.append(carVo.getPl()).append(" ");
					buffer.append(carVo.getGearboxName()).append(carVo.getStyleName());
					carsConfigure.setCarsInfo(buffer.toString());
					carsConfigure.setGuidingPrice(new BigDecimal(carVo.getPrice()));
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
				carsConfigure.setColourId(colourVo.getCarColourId());
				carsConfigure.setColourName(colourVo.getCarColourName());
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
//				carsConfigure.setInteriorId(interiorVo.getInteriorId());
//				carsConfigure.setInteriorName(interiorVo.getInteriorName());
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "请选择的车辆内饰");
				return result;
			}
			//是否线上展示
			if(search.getIsOnLine() != null) {
				carsConfigure.setIsOnLine(search.getIsOnLine());
			}
			//定金
			if(search.getDepositPrice() != null && search.getDepositPrice().doubleValue() > 0) {
				carsConfigure.setDepositPrice(search.getDepositPrice());
			}
			//优惠
			if(search.getDiscountPrice() != null && search.getDiscountPrice().doubleValue() > 0) {
				carsConfigure.setDiscountPrice(search.getDiscountPrice());
			}
			//发票
			if(search.getInvoicePrice() != null && search.getInvoicePrice().doubleValue() > 0) {
				carsConfigure.setInvoicePrice(search.getInvoicePrice());
			}
			if(!orgCarsConfigureDao.insert(carsConfigure)) {
				result.setError(ResultCode.CODE_STATE_4005, "数据保存错误，请联系管理员");
				return result;
			}
		}else {
			for(OrgCarsConfigure carsConfigure : orgCarsConfigures) {
				//是否线上展示
				if(search.getIsOnLine() != null) {
					carsConfigure.setIsOnLine(search.getIsOnLine());
				}
				//定金
				if(search.getDepositPrice() != null && search.getDepositPrice().doubleValue() > 0) {
					carsConfigure.setDepositPrice(search.getDepositPrice());
				}
				//优惠
				if(search.getDiscountPrice() != null && search.getDiscountPrice().doubleValue() > 0) {
					carsConfigure.setDiscountPrice(search.getDiscountPrice());
				}
				//发票
				if(search.getInvoicePrice() != null && search.getInvoicePrice().doubleValue() > 0) {
					carsConfigure.setInvoicePrice(search.getInvoicePrice());
				}
				if(!orgCarsConfigureDao.updateById(carsConfigure)) {
					result.setError(ResultCode.CODE_STATE_4005, "数据保存错误，请联系管理员");
					return result;
				}
			}
		}
		for(StockCarVo stockCarVo : cars) {
			//是否线上展示
			if(search.getIsOnLine() != null) {
				stockCarVo.setIsOnLine(search.getIsOnLine());
			}
			//定金
			if(search.getDepositPrice() != null && search.getDepositPrice().doubleValue() > 0) {
				stockCarVo.setDepositPrice(search.getDepositPrice());
			}
			//优惠
			if(search.getDiscountPrice() != null && search.getDiscountPrice().doubleValue() > 0) {
				stockCarVo.setDiscountPrice(search.getDiscountPrice());
			}
			//发票
			if(search.getInvoicePrice() != null && search.getInvoicePrice().doubleValue() > 0) {
				stockCarVo.setInvoicePrice(search.getInvoicePrice());
			}
			if(!stockCarDao.updateById(stockCarVo)) {
				result.setError(ResultCode.CODE_STATE_4005, "数据保存错误，请联系管理员");
				return result;
			}
		}
		result.setOK(ResultCode.CODE_STATE_200, "保存成功");
		return result;
//		return stockCarDao.updateByIdAndResultIT(stockCarVo, result);
	}

	@Override
	public Result stockCarInfo(StockCarSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getCarsId() == null || search.getInteriorId() == null || search.getColourId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "参数不全，无法执行此操作");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("carsId", search.getCarsId());
		params.put("interiorId", search.getInteriorId());
		params.put("colourId", search.getColourId());
//		params.put("storageId", search.getStorageId());
		
		//根据用户org查询查询出自身跟下级的库存单
		Map<String,Object> selectMap = new HashMap<String, Object>();
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请联系管理员");
			return result;
		}
//		if(StringUtil.isNotEmpty(search.getOrgId())) {
//			Organization organization = organizationDao.selectById(search.getOrgId());
//			if(organization == null) {
//				result.setError(ResultCode.CODE_STATE_4005, "你所选择的公司数据不存在，请重新选择");
//				return result;
//			}
//			selectMap.put("orgCode", organization.getOrgCode());
//		}else {
//			selectMap.put("orgCode", users.getOrgCode());
//		}
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
		params.put("storageIds", set);
		List<StockCarVo> cars = stockCarDao.select(params);
		//把数据库压力转嫁给程序
		for(StockCarVo stockCarVo : cars) {
			for(StockStorageVo storageVo : storages) {
				if(stockCarVo.getStorageId().equals(storageVo.getStorageId())) {
					stockCarVo.setStorageVo(storageVo);
				}
			}
		}
//		Map<String,Object> allMap = new HashMap<String, Object>();
//		Long total = stockCarDao.getRowCount(params);
//		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(StockCarVo stockCarVo : cars) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("frameNumber", stockCarVo.getFrameNumber());
			map.put("carsId", stockCarVo.getCarsId());
			map.put("engineNumber", stockCarVo.getEngineNumber());
			map.put("certificateNumber", stockCarVo.getCertificateNumber());
			map.put("warehouseId", stockCarVo.getWarehouseId());
			map.put("warehouseName", stockCarVo.getWarehouseName());
			map.put("stockCarImages", stockCarVo.getStockCarImages());
			map.put("invoicePrice", stockCarVo.getInvoicePrice());
			map.put("depositPrice", stockCarVo.getDepositPrice());
			map.put("discountPrice", stockCarVo.getDiscountPrice());
			map.put("isOnLine", stockCarVo.getIsOnLine());
			map.put("stockCarId", stockCarVo.getStockCarId());
			map.put("storageId", stockCarVo.getStorageId());
			map.put("number", stockCarVo.getNumber());
			map.put("unitPrice", stockCarVo.getUnitPrice());
			map.put("createDate", DateUtil.format(stockCarVo.getCreateDate()));
			if(stockCarVo.getStorageVo() != null) {
				map.put("orgId", stockCarVo.getStorageVo().getOrgId());
				map.put("orgName", stockCarVo.getStorageVo().getOrgName());
			}
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
//		allMap.put("page", search.getPage());
//		allMap.put("total", total);
//		allMap.put("rows", rows);
//		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

}
