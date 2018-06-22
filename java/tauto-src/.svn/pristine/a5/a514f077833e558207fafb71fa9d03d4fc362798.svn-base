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

import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarInteriorDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.dao.OrgCarsConfigureDao;
import main.com.car.dao.po.OrgCarsConfigure;
import main.com.car.dao.search.OrgCarsConfigureSearch;
import main.com.car.dao.vo.CarColourVo;
import main.com.car.dao.vo.CarsVo;
import main.com.car.dao.vo.OrgCarsConfigureVo;
import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.customer.dao.vo.CustomerOrderVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.dao.StockStorageDao;
import main.com.stock.dao.po.StockStorage;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.vo.StockCarVo;
import main.com.stock.dao.vo.StockStorageVo;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.weixinApp.service.EmployeeOrgCarsConfigureService;

@Service
public class EmployeeOrgCarsConfigureServiceImpl extends BaseServiceImpl<OrgCarsConfigure>
		implements EmployeeOrgCarsConfigureService {

	@Autowired
	OrgCarsConfigureDao orgCarsConfigureDao;

	@Autowired
	OrganizationDao organizationDao;

	@Autowired
	CarsDao carsDao;

	@Autowired
	CarColourDao carColourDao;

	@Autowired
	CarInteriorDao carInteriorDao;
	
	@Autowired
	StockStorageDao stockStorageDao;

	@Autowired
	StockCarDao stockCarDao;
	
	@Override
	protected BaseDao<OrgCarsConfigure> getBaseDao() {
		return orgCarsConfigureDao;
	}

	@Override
	public Result editOrgCarsConfigure(OrgCarsConfigureSearch search, Result result, SystemUsers users)
			throws Exception {
		Organization organization = organizationDao.selectById(users.getOrgId());
		if (organization == null) {
			result.setError("你的公司信息错误，不能进行此操作，请与管理员联系");
			return result;
		}
		OrgCarsConfigure carsConfigure = null;
		if (StringUtil.isEmpty(search.getOrgCarsConfigureId())) {
			carsConfigure = new OrgCarsConfigure();
			carsConfigure.setCreateDate(new Date());
			carsConfigure.setIsDelete(false);
			carsConfigure.setIsOnLine(true);
			carsConfigure.setOrgId(organization.getOrgId());
			carsConfigure.setOrgLevel(organization.getOrgLevel());
		} else {
			carsConfigure = orgCarsConfigureDao.selectById(search.getOrgCarsConfigureId());
		}
		if (search.getCarsId() != null) {
			CarsVo carVo = carsDao.selectById(search.getCarsId());
			if (carVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车型不存在");
				return result;
			}
			carsConfigure.setCarsId(carVo.getCarId());
			StringBuffer buffer = new StringBuffer();
			// 品牌(本田) + 车系（雅阁） + 年款（2016/2017） + 排量(2.0) + 变速箱类型（手动自动） + 款式（高中低配）
			buffer.setLength(0);
			buffer.append(carVo.getBrandName()).append(" ");
			buffer.append(carVo.getFamilyName()).append(" ");
			buffer.append(carVo.getYearPattern()).append("款").append(" ");
			buffer.append(carVo.getPl()).append(" ");
			buffer.append(carVo.getGearboxName()).append(carVo.getStyleName());
			carsConfigure.setCarsInfo(buffer.toString());
			carsConfigure.setGuidingPrice(new BigDecimal(carVo.getPrice()));
			carsConfigure.setFamilyId(carVo.getFamilyId());
			carsConfigure.setBrandId(carVo.getBrandId());
			carsConfigure.setVehicleName(carVo.getVehicleName());
		} else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择的车型");
			return result;
		}
		if (search.getColourId() != null) {
			CarColourVo colourVo = carColourDao.selectById(search.getColourId());
			if (colourVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车身颜色不存在");
				return result;
			}
			carsConfigure.setColourId(colourVo.getCarColourId());
			carsConfigure.setColourName(colourVo.getCarColourName());
		} else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择的车身颜色");
			return result;
		}
		//检查数据是否重复
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("carsId", search.getCarsId());
		params.put("colourId", search.getColourId());
		params.put("orgId", users.getOrgId());
		List<OrgCarsConfigure> orgCarsConfigures = orgCarsConfigureDao.select(params);
		if(orgCarsConfigures != null && orgCarsConfigures.size() > 0) {
			if(StringUtil.isEmpty(search.getOrgCarsConfigureId())) {//新增			
				result.setError(ResultCode.CODE_STATE_4005, "此在售车型已存在，请重新选择");
				return result;			
			}else {
				for(OrgCarsConfigure configure : orgCarsConfigures) {
					if(!configure.getOrgCarsConfigureId().equals(carsConfigure.getOrgCarsConfigureId())) {
						result.setError(ResultCode.CODE_STATE_4005, "此在售车型已存在，请重新选择");
						return result;
					}
				}
			}
		}
//		if (search.getInteriorId() != null) {
//			CarInteriorVo interiorVo = carInteriorDao.selectById(search.getInteriorId());
//			if (interiorVo == null) {
//				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车辆内饰不存在");
//				return result;
//			}
//			carsConfigure.setInteriorId(interiorVo.getInteriorId());
//			carsConfigure.setInteriorName(interiorVo.getInteriorName());
//		} else {
//			result.setError(ResultCode.CODE_STATE_4005, "请选择的车辆内饰");
//			return result;
//		}
		// 是否线上展示
		if (search.getIsOnLine() != null) {
			carsConfigure.setIsOnLine(search.getIsOnLine());
		}
		// 优惠
		if (search.getDiscountPrice() != null && search.getDiscountPrice().doubleValue() >= 0d) {
			carsConfigure.setDiscountPrice(search.getDiscountPrice());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写优惠金额");
			return result;
		}
		// 定金
		if (search.getDepositPrice() != null && search.getDepositPrice().doubleValue() >= 0d) {
			carsConfigure.setDepositPrice(search.getDepositPrice());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写定金金额");
			return result;
		}
		// 发票
		if (search.getInvoicePrice() != null && search.getInvoicePrice().doubleValue() >= 0d) {
			carsConfigure.setInvoicePrice(search.getInvoicePrice());
		}
		if(StringUtil.isEmpty(search.getOrgCarsConfigureId())) {
			return orgCarsConfigureDao.insertAndResultIT(carsConfigure, result);
		}else {
			return orgCarsConfigureDao.updateByIdAndResultIT(carsConfigure, result);
		}
	}

	@Override
	public Result orgCarsConfigureList(OrgCarsConfigureSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请先联系管理员");
			return result;
		}
		Map<String,Object> params = getParams(search,users);
		List<OrgCarsConfigureVo> carsConfigureVos = orgCarsConfigureDao.selectJoin(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = orgCarsConfigureDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		
		Set<Integer> carsIds = new HashSet<Integer>();
		Set<Integer> colourIds = new HashSet<Integer>();
		for(OrgCarsConfigureVo carsConfigureVo : carsConfigureVos) {
			carsIds.add(carsConfigureVo.getCarsId());
			colourIds.add(carsConfigureVo.getColourId());
		}
		StockCarSearch carSearch = new StockCarSearch();
		carSearch.setCarsIds(carsIds);
		carSearch.setColourIds(colourIds);
		List<StockCarVo> cars = stockCarList(carSearch, result, users);
		for(OrgCarsConfigureVo carsConfigureVo : carsConfigureVos) {
			List<StockCarVo> stockCars = new ArrayList<StockCarVo>();
			for(StockCarVo carVo : cars) {
				if(carVo.getCarsId().equals(carsConfigureVo.getCarsId()) && carVo.getColourId().equals(carsConfigureVo.getColourId())) {
					stockCars.add(carVo);
				}
			}
			carsConfigureVo.setStockCars(stockCars);
		}
		for(OrgCarsConfigureVo carsConfigureVo : carsConfigureVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			if(carsConfigureVo.getCarsVo() != null) {
				map.put("indexImage", carsConfigureVo.getCarsVo().getIndexImage());	
			}else {
				map.put("indexImage", "");	
			}
			map.put("carsId", carsConfigureVo.getCarsId());
			map.put("orgCarsConfigureId", carsConfigureVo.getOrgCarsConfigureId());
			if(carsConfigureVo.getIsOnLine() != null && carsConfigureVo.getIsOnLine()) {
				map.put("isOnLine", 1);
			}else {
				map.put("isOnLine", 0);
			}
			map.put("colourName", carsConfigureVo.getColourName());
			map.put("colourId", carsConfigureVo.getColourId());
			map.put("number", carsConfigureVo.getStockCars().size());//
			map.put("carsNameBefor", takeCareCarsName(carsConfigureVo.getCarsInfo(), true));
			map.put("carsNameAfter", takeCareCarsName(carsConfigureVo.getCarsInfo(), false));
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
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

	public Map<String,Object> getParams(OrgCarsConfigureSearch search, SystemUsers users){
		Map<String,Object> params = new HashMap<String, Object>();
		if(search.getIsOnLine() != null) {
			params.put("isOnLine", search.getIsOnLine());
		}
		params.put("isOnLineNull", false);
		if(StringUtil.isNotEmpty(search.getCarsName())) {
			params.put("carsName", search.getCarsName());
		}
		params.put("orgCode", users.getOrgCode());
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows()); 
		return params;
	}
	
	/**
	 * 查询库存
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public List<StockCarVo> stockCarList(StockCarSearch search, Result result, SystemUsers users) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		if(search.getCarsIds() != null && search.getCarsIds().size() > 0) {
			params.put("carsIds", search.getCarsIds());
		}
		if(search.getColourIds() != null && search.getColourIds().size() > 0) {
			params.put("colourIds", search.getColourIds());
		}
		if(search.getCarsId() != null) {
			params.put("carsId", search.getCarsId());
		}
		if(search.getColourId() != null) {
			params.put("colourId", search.getColourId());
		}
		params.put("lessGroupBy", true);
		List<StockCarVo> list = new ArrayList<StockCarVo>();
		//根据用户org查询查询出自身跟下级的库存单
		Map<String,Object> selectMap = new HashMap<String, Object>();
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请联系管理员");
			return list;
		}
		if(StringUtil.isNotEmpty(search.getOrgId())) {
			Organization organization = organizationDao.selectById(search.getOrgId());
			if(organization == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的公司数据不存在，请重新选择");
				return list;
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
		if(set.size() <= 0) {
			return list;
		}
		params.put("storageIds", set);
//		list = stockCarDao.selectByOrg(params);
		list = stockCarDao.select(params);
		return list;
	}

	@Override
	public Result orgCarsConfigureInfo(OrgCarsConfigureSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(search.getOrgCarsConfigureId())) {
			result.setError("请选择具体车辆查看");
			return result;
		}
		OrgCarsConfigureVo carsConfigureVo = orgCarsConfigureDao.selectById(search.getOrgCarsConfigureId());
		if(carsConfigureVo == null) {
			result.setError("你选择的车型不存在或已删除");
			return result;
		}
		StockCarSearch carSearch = new StockCarSearch();
		carSearch.setCarsId(carsConfigureVo.getCarsId());
		carSearch.setColourId(carsConfigureVo.getColourId());
		List<StockCarVo> stockCarVos = stockCarList(carSearch, result, users);
		Map<String, Object> map = new HashMap<String, Object>();
		if(carsConfigureVo.getCarsVo() != null) {
			map.put("indexImage", carsConfigureVo.getCarsVo().getIndexImage());	
		}else {
			map.put("indexImage", "");	
		}
		map.put("carsId", carsConfigureVo.getCarsId());
		map.put("orgCarsConfigureId", carsConfigureVo.getOrgCarsConfigureId());
		if(carsConfigureVo.getIsOnLine() != null && carsConfigureVo.getIsOnLine()) {
			map.put("isOnLine", 1);
		}else {
			map.put("isOnLine", 0);
		}
		map.put("colourName", carsConfigureVo.getColourName());
		map.put("colourId", carsConfigureVo.getColourId());
		map.put("number", stockCarVos!= null?stockCarVos.size():0);//库存要补
		map.put("carsName", carsConfigureVo.getCarsInfo());
		map.put("guidingPrice", carsConfigureVo.getGuidingPrice() != null?carsConfigureVo.getGuidingPrice().doubleValue():"");
		map.put("depositPrice", carsConfigureVo.getDepositPrice() != null?carsConfigureVo.getDepositPrice().doubleValue():"");
		map.put("discountPrice", carsConfigureVo.getDiscountPrice() != null?carsConfigureVo.getDiscountPrice().doubleValue():"");
		List<Map<String, Object>> stockCarMaps = new ArrayList<Map<String, Object>>();
		for(StockCarVo stockCarVo : stockCarVos) {
			Map<String, Object> carMap = new HashMap<String, Object>();
			carMap.put("frameNumber", stockCarVo.getFrameNumber());
			carMap.put("interiorName", stockCarVo.getInteriorName());
			carMap.put("stockCarImages", stockCarVo.getStockCarImages());
			stockCarMaps.add(carMap);
		}
		map.put("list", stockCarMaps);
		result.setOK(ResultCode.CODE_STATE_200, "", map);
		return result;
	}
}
