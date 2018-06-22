package main.com.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.car.dao.dao.OrgCarsConfigureDao;
import main.com.car.dao.vo.OrgCarsConfigureVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemRegionDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.dao.SystemWarehouseDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.po.SystemWarehouse;
import main.com.system.dao.search.OrganizationSearch;
import main.com.system.dao.search.SystemWarehouseSearch;
import main.com.system.dao.vo.MenuVo;
import main.com.system.dao.vo.OrganizationVo;
import main.com.system.dao.vo.SystemRegionVo;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.system.dao.vo.SystemWarehouseVo;
import main.com.system.service.OrganizationService;
import main.com.utils.GeneralConstant;
import main.com.utils.StringCode;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class OrganizationServiceImpl extends BaseServiceImpl<Organization> implements OrganizationService{

	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	SystemRegionDao systemRegionDao;
	
	@Autowired
	SystemWarehouseDao systemWarehouseDao;
	
	@Autowired
	OrgCarsConfigureDao orgCarsConfigureDao;
	
	@Override
	protected BaseDao<Organization> getBaseDao() {
		return organizationDao;
	}

	@Override
	public Result organizationList(OrganizationSearch search, Result result,SystemUsers systemUsers) throws Exception {
		Map<String,Object> params = getParams(search,systemUsers);
		List<OrganizationVo> organizationVos = organizationDao.selectJoin(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = organizationDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(OrganizationVo organizationVo : organizationVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("orgId", organizationVo.getOrgId());
			map.put("linkMan", organizationVo.getLinkMan());
			//名称
			map.put("shortName", organizationVo.getShortName());
			//上级
			if(organizationVo.getOrgParent() != null) {
				map.put("parentName", organizationVo.getOrgParent().getShortName());
				map.put("parentId", organizationVo.getOrgParent().getOrgId());
			}else {
				map.put("parentName", "");
				map.put("parentId", "");
			}
			//标签（等级）
			map.put("orgLevel", organizationVo.getOrgLevel());
			//地址
			map.put("address", organizationVo.getAddress());
			//状态
			map.put("status", organizationVo.getStatus());			
			maps.add(map);
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}

	public Map<String,Object> getParams(OrganizationSearch search,SystemUsers systemUsers){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getOrgName())){
			params.put("orgName", search.getOrgName());
		}
		params.put("orgCodeLevel", systemUsers.getOrgCode());
		//从第几条开始
		params.put("sortField", "orgId");
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	public Result organizationEdit(OrganizationSearch search, Result result) throws Exception {
		Organization organization = null;
		SystemWarehouse systemWarehouse = null;
		List<SystemUsers> systemUsers = null;
		Map<String,Object> params = new HashMap<String,Object>();
		if(search.getOrgId() != null) {
			params.put("orgId", search.getOrgId());
			List<OrganizationVo> organizationVos = organizationDao.selectJoin(params);
			if(organizationVos == null) {
				result.setError(ResultCode.CODE_STATE_4005, "系统不存在orgId为"+search.getOrgId()+"的数据，请核对数据后重新操作");
				return result;
			}
			organization = organizationVos.get(0);
			//修改用户的对应机构名称
			systemUsers = systemUsersDao.select(params);
			if(StringUtil.isNotEmpty(search.getShortName()) && !organization.getShortName().equals(search.getShortName())) {
				for(SystemUsers users : systemUsers) {
					users.setOrgName(search.getShortName());
				}
			}
		}else {
			organization = new Organization();
			//新建的组织就默认给新建一个仓库
			systemWarehouse = new SystemWarehouse();
			systemWarehouse.setCreateDate(new Date());
			systemWarehouse.setIsDelete(false);
			systemWarehouse.setWarehouseName("整车库");
			systemWarehouse.setWarehouseType(GeneralConstant.SystemWarehouseType.vehicle);
			organization.setOrgCode(organizationDao.getorgCode());
			organization.setCreateDate(new Date());
			organization.setStatus(GeneralConstant.Org.status_on);
		}
		if(StringUtil.isNotEmpty(search.getShortName())) {
			organization.setShortName(search.getShortName());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入组织名称");
			return result;
		}
		if(search.getOrgLevel() != null) {
			if(search.getOrgLevel().equals(GeneralConstant.Org.Level_1) && search.getParentId() != null) {
				result.setError(ResultCode.CODE_STATE_4005, "当前："+search.getShortName()+"级别为公司，不应该存在上级");
				return result;
			}else if(search.getOrgLevel()>GeneralConstant.Org.Level_1 && search.getParentId() == null) {
				result.setError(ResultCode.CODE_STATE_4005, "当前："+search.getShortName()+"级别为非公司级别，请选择上级");
				return result;
			}
			organization.setOrgLevel(search.getOrgLevel());
		}else {
			organization.setOrgLevel(1);
		}
		if(search.getOrgType() != null) {
			organization.setOrgType(search.getOrgType());
		}
		if(search.getParentId() != null) {
			OrganizationVo parentOrganization = organizationDao.selectById(search.getParentId());
			if(parentOrganization == null) {
				result.setError(ResultCode.CODE_STATE_4005, "抱歉，你选择的上级并不存在于系统，请重新选择");
				return result;
			}
			organization.setParentId(search.getParentId());
			organization.setOrgCodeLevel(parentOrganization.getOrgCodeLevel() + GeneralConstant.SystemBoolean.SPLIT_ + organization.getOrgCode());
		}else {
			organization.setParentId(0);
			organization.setOrgCodeLevel(organization.getOrgCode());
		}
		if(search.getLatitude() != null) {
			organization.setLatitude(search.getLatitude());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写经度");
			return result;
		}
		if(search.getLongitude() != null) {
			organization.setLongitude(search.getLongitude());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写经度");
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
		params.clear();
		params.put("sortField", true);
		params.put("regionIds", regionIds);
		List<SystemRegionVo> regionVos = systemRegionDao.select(params);//查出三个地区，按照省市区排列
		organization.setProvinceId(regionVos.get(0).getRegionId());
		organization.setProvinceName(regionVos.get(0).getRegionName());
		organization.setCityId(regionVos.get(1).getRegionId());
		organization.setCityName(regionVos.get(1).getRegionName());
		organization.setAreaId(regionVos.get(2).getRegionId());
		organization.setAreaName(regionVos.get(2).getRegionName());
		
		if(StringUtil.isNotEmpty(search.getAddress())) {
			organization.setAddress(search.getAddress());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写公司地址");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getTelePhone())) {
			organization.setTelePhone(search.getTelePhone());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写联系电话");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getIntroduce())) {
			organization.setIntroduce(search.getIntroduce());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写公司简介");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getBankAccount())) {
			if(!StringUtil.isNumeric(search.getBankAccount())) {
				result.setError(ResultCode.CODE_STATE_4005, "银行账号格式错误");
				return result;
			}
			organization.setBankAccount(search.getBankAccount());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写银行账户");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getBankName())) {
			organization.setBankName(search.getBankName());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写银行名称");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getOpeningBranch())) {
			organization.setOpeningBranch(search.getOpeningBranch());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写开户银行支行");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getNameOfAccount())) {
			organization.setNameOfAccount(search.getNameOfAccount());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请填写银行帐户名");
			return result;
		}

		if(StringUtil.isNotEmpty(search.getImageUrl())) {
			organization.setImageUrl(search.getImageUrl());
		}
		if(organization.getOrgId() == null) {
			organization.setStatus(GeneralConstant.Org.status_on);
			 if(organizationDao.insert(organization)) {
				 systemWarehouse.setOrgId(organization.getOrgId());
				 systemWarehouse.setOrgName(organization.getShortName());
				 systemWarehouse.setOrgCode(organization.getOrgCode());
				 systemWarehouseDao.insert(systemWarehouse);
				 result.setOK(ResultCode.CODE_STATE_200, "");
			 }else {
				 result.setError(ResultCode.CODE_STATE_4005, "保存失败，请联系IT部");
			 }
		}else {
			if(organizationDao.updateById(organization)) {
				if(systemUsers != null) {
					for(SystemUsers users : systemUsers) {
						systemUsersDao.updateById(users);
					}
				}
				 result.setOK(ResultCode.CODE_STATE_200, "");
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "保存失败，请联系IT部");
			}
		}
		 return result;
	}

	@Override
	@Transactional
	public Result organizationOnOff(OrganizationSearch search, Result result) throws Exception {
		if(search.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择有效数据进行操作");
			return result;
		}
		Organization organization = organizationDao.selectById(search.getOrgId());
		if(organization == null) {
			result.setError(ResultCode.CODE_STATE_4005, "系统不存在orgId为"+search.getOrgId()+"的数据，请核对数据后重新操作");
			return result;
		}
		if(search.getIsOn() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "参数错误，此操作参数不能为空");
			return result;
		}
		List<SystemUsersVo> systemUsersVos = new ArrayList<SystemUsersVo>();
		List<OrganizationVo> organizationVos = new ArrayList<OrganizationVo>();
		List<OrgCarsConfigureVo> carsConfigures = new ArrayList<OrgCarsConfigureVo>();
		
		if(search.getIsOn()) {//启用
			if(GeneralConstant.Org.status_on.equals(organization.getStatus())) {
				result.setError(ResultCode.CODE_STATE_4005, organization.getShortName()+"已是启用状态，不需要进行启用操作");
				return result;
			}
			organization.setStatus(GeneralConstant.Org.status_on);
			//查询出来所有被禁用的
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("orgId", organization.getOrgId());
			carsConfigures = orgCarsConfigureDao.select(params);
			for(OrgCarsConfigureVo configureVo : carsConfigures) {
				configureVo.setIsDelete(false);
			}
		}else {//如果是禁用
			if(GeneralConstant.Org.status_off.equals(organization.getStatus())) {
				result.setError(ResultCode.CODE_STATE_4005, organization.getShortName()+"已是禁用状态，不需要进行禁用操作");
				return result;
			}
			if(organization.getOrgLevel().equals(GeneralConstant.Org.Level_1)) {
				result.setError(ResultCode.CODE_STATE_4005, organization.getShortName()+"组织为一级组织，不能进行禁用操作");
				return result;
			}
			organization.setStatus(GeneralConstant.Org.status_off);
			//查询其下面所有的组织
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("orgCodeLevel", organization.getOrgCode());
			organizationVos = organizationDao.select(params);
			//查询该组织下面的所有用户
			params.put("isEnable", true);
			systemUsersVos = systemUsersDao.select(params);
			//查询出来正在使用的
			params.put("orgCodeLevel", organization.getOrgCode());
			carsConfigures = orgCarsConfigureDao.select(params);
			for(OrgCarsConfigureVo configureVo : carsConfigures) {
				configureVo.setIsDelete(true);
			}
		}
		if(organizationDao.updateById(organization)) {
			//把组织下面的所有用户踢下线
			if(GeneralConstant.Org.status_off.equals(organization.getStatus())) {
				for(OrganizationVo organizationVo : organizationVos) {//禁用所有下级
					organizationVo.setStatus(GeneralConstant.Org.status_off);
					organizationDao.updateById(organizationVo);
				}
				for(SystemUsersVo systemUsersVo : systemUsersVos) {//禁用自身和下级的所有用户
					systemUsersVo.setSessionId("");
					systemUsersDao.updateById(systemUsersVo);
				}
				for(OrgCarsConfigureVo configureVo : carsConfigures) {
					orgCarsConfigureDao.updateById(configureVo);
				}
			}
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "系统错误，请联系IT部");
		}
		return result;
	}
	
	/**
	 * 返回组织的树状结构(保留)
	 * @param list
	 * @param parentId
	 * @return
	 */
	public List<OrganizationVo> getSyncGridTreeList(List<OrganizationVo> list, Integer parentId) {
		for(OrganizationVo organizationVo: list){
			int id = organizationVo.getOrgId();
			int pid = organizationVo.getParentId();
			if(pid == parentId){
				List<OrganizationVo> children = getSyncGridTreeList(list, id);
				organizationVo.setChildrens(children);
			}
		}
		return list;
	}

	@Override
	public Result organizationLevelList(OrganizationSearch search, Result result, SystemUsers systemUsers) {
		if(StringUtil.isEmpty(systemUsers.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的组织身份不明确，不能进行此操作");
			return result;
		}
		Map<String, Object> params =  new HashMap<String,Object>();
		params.put("orgCodeLevel", systemUsers.getOrgCode());
		List<OrganizationVo> list = organizationDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(OrganizationVo organization : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("shortName", organization.getShortName());
			map.put("orgId", organization.getOrgId());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Result organizationWarehouseList(SystemWarehouseSearch search, Result result, SystemUsers systemUsers) throws Exception {
		if(StringUtil.isEmpty(systemUsers.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司身份不明确，请联系管理员补充完整");
			return result;
		}
		Map<String, Object> params =  new HashMap<String,Object>();				
		if(StringUtil.isNotEmpty(search.getOrgId())) {
			Organization organization = organizationDao.selectById(search.getOrgId());
			if(organization == null) {
				result.setError(ResultCode.CODE_STATE_4005, "系统不存在orgId为"+search.getOrgId()+"的数据，请核对数据后重新操作");
				return result;
			}
			params.put("orgId", search.getOrgId());
		}else {
			params.put("orgId", systemUsers.getOrgId());
		}
		List<SystemWarehouseVo> warehouseVos = systemWarehouseDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(SystemWarehouseVo vo : warehouseVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("orgId", vo.getOrgId());
			map.put("warehouseId", vo.getWarehouseId());
			map.put("warehouseName", vo.getWarehouseName());
			map.put("warehouseType", vo.getWarehouseType());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}

	@Override
	public Result organizationWarehouseEdit(SystemWarehouseSearch search, Result result) throws Exception {
		SystemWarehouse warehouse = null;
		if(search.getWarehouseId() == null) {
			warehouse = new SystemWarehouse();
			if(search.getOrgId() == null) {
				result.setError(ResultCode.CODE_STATE_4005, "请选择有效数据进行操作");
				return result;
			}
			Organization organization = organizationDao.selectById(search.getOrgId());
			if(organization == null) {
				result.setError(ResultCode.CODE_STATE_4005, "系统不存在orgId为"+search.getOrgId()+"的数据，请核对数据后重新操作");
				return result;
			}
			warehouse.setOrgId(organization.getOrgId());
			warehouse.setOrgName(organization.getShortName());
			warehouse.setOrgCode(organization.getOrgCode());
			warehouse.setCreateDate(new Date());
		}else {
			warehouse = systemWarehouseDao.selectById(search.getWarehouseId());
			if(warehouse == null) {
				result.setError(ResultCode.CODE_STATE_4005, "请选择有效数据进行操作");
				return result;
			}
		}
		if(StringUtil.isNotEmpty(search.getWarehouseName())) {
			warehouse.setWarehouseName(search.getWarehouseName());
		}
		if(search.getWarehouseType() != null && (search.getWarehouseType().equals(GeneralConstant.SystemWarehouseType.parts) || search.getWarehouseType().equals(GeneralConstant.SystemWarehouseType.vehicle))) {			
			warehouse.setWarehouseType(search.getWarehouseType());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "仓库类型选择错误");
			return result;
		}
		if(search.getWarehouseId() == null) {
			return systemWarehouseDao.insertAndResultIT(warehouse, result);
		}else {
			return systemWarehouseDao.updateByIdAndResultIT(warehouse, result);
		}
	}

	@Override
	public Result organizationWarehouseDalete(SystemWarehouseSearch search, Result result) throws Exception {
		SystemWarehouse warehouse = systemWarehouseDao.selectById(search.getWarehouseId());
		if(warehouse == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择有效数据进行操作");
			return result;
		}
		warehouse.setIsDelete(true);
		return systemWarehouseDao.updateByIdAndResultIT(warehouse, result);
	}

	@Override
	public Result organizationInfo(OrganizationSearch search, Result result, SystemUsers systemUsers) throws Exception {
		if(search.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择有效数据进行操作");
			return result;
		}
		OrganizationVo organizationVo = organizationDao.selectById(search.getOrgId());
		if(organizationVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的组织不存在或者已删除，请核对数据后重新操作");
			return result;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("orgId", organizationVo.getOrgId());
		//名称
		map.put("shortName", organizationVo.getShortName());
		//上级
		map.put("parentId", (organizationVo.getParentId() == null || organizationVo.getParentId().equals(0)) ?"":organizationVo.getParentId());
		//标签（等级）
		map.put("orgLevel", organizationVo.getOrgLevel());
		//地址
		map.put("address", organizationVo.getAddress());
		//状态
		map.put("status", organizationVo.getStatus());
		map.put("areaId", organizationVo.getAreaId());
		map.put("provinceId", organizationVo.getProvinceId());
		map.put("cityId", organizationVo.getCityId());
		map.put("area", organizationVo.getAreaName());
		map.put("province", organizationVo.getProvinceName());
		map.put("city", organizationVo.getCityName());
		map.put("bankAccount", organizationVo.getBankAccount());
		map.put("bankName", organizationVo.getBankName());
		map.put("imageUrl", organizationVo.getImageUrl());
		map.put("imgIntroduce", organizationVo.getImgIntroduce());
		map.put("introduce", organizationVo.getIntroduce());
		map.put("latitude", organizationVo.getLatitude());
		map.put("linkMan", organizationVo.getLinkMan());
		map.put("longitude", organizationVo.getLongitude());
		map.put("nameOfAccount", organizationVo.getNameOfAccount());
		map.put("openingBranch", organizationVo.getOpeningBranch());
		map.put("orgLevel", organizationVo.getOrgLevel());
		map.put("orgType", organizationVo.getOrgType());
		map.put("remark", organizationVo.getRemark());
		map.put("telePhone", organizationVo.getTelePhone());
		map.put("zipCode", organizationVo.getZipCode());
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",TakeCareMapDate.cutNullMap(map));
		return result;
	}
	
	@Override
	public Result organizationInfo(String orgCode, Result result) throws Exception {
		if(StringUtil.isEmpty(orgCode)) {
			result.setOK(ResultCode.CODE_STATE_200, "", "");
			return result;
		}
		OrganizationVo organizationVo = organizationDao.selectByCode(orgCode);
		result.setOK(ResultCode.CODE_STATE_200, "", organizationVo);
		return result;
	}

	@Override
	public Result organizationLevelListByLevel(OrganizationSearch search, Result result) throws Exception {
		Map<String, Object> params =  new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(search.getOrgLevel())) {
			params.put("orgLevel", search.getOrgLevel() - 1);
		}
		List<OrganizationVo> list = organizationDao.select(params);
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(OrganizationVo organization : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("shortName", organization.getShortName());
			map.put("orgId", organization.getOrgId());
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",maps);
		return result;
	}

	@Override
	public Organization getOrganization(SystemUsers users) throws Exception {
		if(users.getOrgId() == null) {
			return null;
		}
		OrganizationVo organizationVo = organizationDao.selectById(users.getOrgId());
		if(organizationVo == null) {
				return null;
		}
		else {
			return organizationVo;
		}
	}
	
	
}
