package main.com.car.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.car.dao.dao.CarSupplierDao;
import main.com.car.dao.po.CarBrand;
import main.com.car.dao.po.CarColour;
import main.com.car.dao.po.CarSupplier;
import main.com.car.dao.search.CarColourImageSearch;
import main.com.car.dao.search.CarSupplierSearch;
import main.com.car.dao.vo.CarColourImageVo;
import main.com.car.dao.vo.CarSupplierVo;
import main.com.car.service.CarSupplierService;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.search.RoleSearch;
import main.com.system.dao.vo.RoleVo;
import main.com.utils.StringUtil;

@Service
public class CarSupplierServiceImpl extends BaseServiceImpl<CarSupplier>implements CarSupplierService{

	@Autowired
	CarSupplierDao carSupplierDao;
	
	@Override
	protected BaseDao<CarSupplier> getBaseDao() {
		return carSupplierDao;
	}

	@Override
	public Result supplierList(CarSupplierSearch search, Result result,SystemUsers systemUsers) throws Exception {
		Map<String,Object> params = getParams(search,systemUsers);
		List<CarSupplierVo> carSupplierVos = carSupplierDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = carSupplierDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarSupplierVo carSupplierVo : carSupplierVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("supplierId", carSupplierVo.getSupplierId());
			map.put("supplierName", carSupplierVo.getSupplierName());
			map.put("phoneNumber", carSupplierVo.getPhoneNumber());
			map.put("remark", carSupplierVo.getRemarks());			
			map.put("orgId", carSupplierVo.getOrgId());			
			map.put("orgName", carSupplierVo.getOrgName());			
			maps.add(map);
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}
	
	public Map<String,Object> getParams(CarSupplierSearch search,SystemUsers systemUsers){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getOrgName())) {
			params.put("orgName", search.getOrgName());
		}
		if(StringUtil.isNotEmpty(search.getSupplierName())) {
			params.put("supplierName", search.getSupplierName());
		}
		params.put("orgCode", systemUsers.getOrgCode());
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	public Result supplierListList(CarSupplierSearch search, Result result,SystemUsers systemUsers) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgCode", systemUsers.getOrgCode());
		List<CarSupplierVo> carSupplierVos = carSupplierDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CarSupplierVo carSupplierVo : carSupplierVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", carSupplierVo.getSupplierId());
			map.put("name", carSupplierVo.getSupplierName());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result supplierEdit(CarSupplierSearch search, Result result,SystemUsers systemUsers) throws Exception {
		CarSupplier carSupplier = null;
		if(search.getSupplierId() == null) {
			carSupplier = new CarSupplier();	
			carSupplier.setOrgId(systemUsers.getOrgId());
			carSupplier.setOrgName(systemUsers.getOrgName());
			carSupplier.setIsDelete(false);
			carSupplier.setCreaterDate(new Date());
		}else {
			carSupplier = carSupplierDao.selectById(search.getSupplierId());	
			if(carSupplier == null) {
				result.setError(ResultCode.CODE_STATE_4005, "所选择的供应商不存在，请重新选择");
				return result;
			}
		}
		if(StringUtil.isNotEmpty(search.getSupplierName())) {
			carSupplier.setSupplierName(search.getSupplierName());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写供应商名称");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getPhoneNumber())) {
			carSupplier.setPhoneNumber(search.getPhoneNumber());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写供应商联系方式");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getRemark())) {
			carSupplier.setRemarks(search.getRemark());
		}
		if(search.getSupplierId() == null) {
			return carSupplierDao.insertAndResultIT(carSupplier, result);
		}else {
			return carSupplierDao.updateByIdAndResultIT(carSupplier, result);
		}
	}

	@Override
	public Result supplierDelete(CarSupplierSearch search, Result result) throws Exception {
		if(search.getSupplierId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体供应商进行操作");
			return result;
		}
		CarSupplierVo carSupplierVo = carSupplierDao.selectById(search.getSupplierId());
		if(carSupplierVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "所选择的供应商不存在，请重新选择");
			return result;
		}
		carSupplierVo.setIsDelete(true);
		return carSupplierDao.updateByIdAndResultIT(carSupplierVo, result);
	}

}
