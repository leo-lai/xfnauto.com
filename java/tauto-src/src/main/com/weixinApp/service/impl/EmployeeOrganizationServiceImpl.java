package main.com.weixinApp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import main.com.system.dao.vo.OrganizationVo;
import main.com.system.dao.vo.SystemRegionVo;
import main.com.utils.GeneralConstant;
import main.com.utils.MD5Encoder;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.weixinApp.service.EmployeeOrganizationService;

@Service
public class EmployeeOrganizationServiceImpl extends BaseServiceImpl<Organization> implements EmployeeOrganizationService{

	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	SystemWarehouseDao systemWarehouseDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	SystemRegionDao systemRegionDao;
	
	@Override
	protected BaseDao<Organization> getBaseDao() {
		return organizationDao;
	}

	@Override
	public Result organizationList(OrganizationSearch search, Result result, SystemUsers systemUsers) throws Exception {
		Map<String,Object> params = getParams(search,systemUsers);
		List<OrganizationVo> organizationVos = organizationDao.selectJoin(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = organizationDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(OrganizationVo organizationVo : organizationVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("orgId", organizationVo.getOrgId());
			//名称
			map.put("shortName", organizationVo.getShortName());			
			//地址
			map.put("address", organizationVo.getAddress());
			map.put("provinceId", organizationVo.getProvinceId());
			map.put("cityId", organizationVo.getCityId());
			map.put("areaId", organizationVo.getAreaId());
			map.put("provinceName", organizationVo.getProvinceName());
			map.put("cityName", organizationVo.getCityName());
			map.put("areaName", organizationVo.getAreaName());
			map.put("remarks", organizationVo.getIntroduce());
			//
			map.put("linkMan", organizationVo.getLinkMan());
			map.put("telePhone", organizationVo.getTelePhone());
			maps.add(TakeCareMapDate.cutNullMap(map));
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
		if(StringUtil.isNotEmpty(search.getTelePhone())){
			params.put("telePhone", search.getTelePhone());
		}
		
		if(StringUtil.isNotEmpty(search.getKeywords())){
			params.put("keywords", search.getKeywords());
		}
		
		params.put("orgTypeElse", GeneralConstant.Org.Type_4);//除了其他，全部列出来
		params.put("orgLevelIn", GeneralConstant.Org.Level_3);//只查询第三级
		//从第几条开始
		params.put("sortField", "orgId");
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows());
		return params;
	}

	@Override
	@Transactional
	public Result organizationEdit(OrganizationSearch search, Result result,SystemUsers user) throws Exception {
		Organization organization = null;
		SystemWarehouse systemWarehouse = null;
		List<SystemUsers> systemUsers = null;
		SystemUsers systemUser = null;
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
			//新建的组织默认把联系人添加成用户
			params.put("phoneNumber", search.getTelePhone());
			systemUsers = systemUsersDao.select(params);
			if(systemUsers != null && systemUsers.size() > 0) {
				result.setError("系统已存在联系电话为："+search.getTelePhone()+"（"+systemUsers.get(0).getOrgName()+"）的用户");
				return result;
			}
			systemUser = new SystemUsers();
			systemUser.setRealName(search.getLinkMan());
			systemUser.setPhoneNumber(search.getTelePhone());
			systemUser.setCreateTime(new Date());
			systemUser.setIsEnable(true);
			systemUser.setPassword(MD5Encoder.encodeByMD5(search.getTelePhone()));
			organization.setOrgCode(organizationDao.getorgCode());
			systemUser.setOrgCode(organization.getOrgCode());
			organization.setOrgLevel(GeneralConstant.Org.Level_3);
			organization.setOrgType(GeneralConstant.Org.Type_3);
		}
		if(StringUtil.isNotEmpty(search.getShortName())) {
			organization.setShortName(search.getShortName());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入组织名称");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getLinkMan())) {
			organization.setLinkMan(search.getLinkMan());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入联系人姓名");
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
			organization.setOrgLevel(GeneralConstant.Org.Level_3);
		}
		if(search.getOrgType() != null) {
			organization.setOrgType(search.getOrgType());
		}
		if(StringUtil.isEmpty(search.getParentId())) {//此处是二级添加三级门店，没有选择上级的话就直接把二级当做三级
			search.setParentId(user.getOrgId());
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
//			result.setError(ResultCode.CODE_STATE_4005, "请填写经度");
//			return result;
		}
		if(search.getLongitude() != null) {
			organization.setLongitude(search.getLongitude());
		}else {
//			result.setError(ResultCode.CODE_STATE_4005, "请填写经度");
//			return result;
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
//			result.setError(ResultCode.CODE_STATE_4005, "请填写联系电话");
//			return result;
		}
		if(StringUtil.isNotEmpty(search.getIntroduce())) {
			organization.setIntroduce(search.getIntroduce());
		}else if(StringUtil.isNotEmpty(search.getRemarks())){
			organization.setIntroduce(search.getRemarks());
//			result.setError(ResultCode.CODE_STATE_4005, "请填写公司简介");
//			return result;
		}
		if(StringUtil.isNotEmpty(search.getBankAccount())) {
			if(!StringUtil.isNumeric(search.getBankAccount())) {
				result.setError(ResultCode.CODE_STATE_4005, "银行账号格式错误");
				return result;
			}
			organization.setBankAccount(search.getBankAccount());
		}else {
//			result.setError(ResultCode.CODE_STATE_4005, "请填写银行账户");
//			return result;
		}
		if(StringUtil.isNotEmpty(search.getBankName())) {
			organization.setBankName(search.getBankName());
		}else {
//			result.setError(ResultCode.CODE_STATE_4005, "请填写银行名称");
//			return result;
		}
		if(StringUtil.isNotEmpty(search.getOpeningBranch())) {
			organization.setOpeningBranch(search.getOpeningBranch());
		}else {
//			result.setError(ResultCode.CODE_STATE_4005, "请填写开户银行支行");
//			return result;
		}
		if(StringUtil.isNotEmpty(search.getNameOfAccount())) {
			organization.setNameOfAccount(search.getNameOfAccount());
		}else {
//			result.setError(ResultCode.CODE_STATE_4005, "请填写银行帐户名");
//			return result;
		}

		if(StringUtil.isNotEmpty(search.getImageUrl())) {
			organization.setImageUrl(search.getImageUrl());
		}
		
		try {
			if(organization.getOrgId() == null) {
				organization.setStatus(GeneralConstant.Org.status_on);
				 if(organizationDao.insert(organization)) {
					 systemWarehouse.setOrgId(organization.getOrgId());
					 systemWarehouse.setOrgName(organization.getShortName());
					 systemWarehouse.setOrgCode(organization.getOrgCode());
					 systemWarehouseDao.insert(systemWarehouse);
					 //插入联系用户
					 if(systemUser != null) {
						 systemUser.setOrgId(organization.getOrgId());
						 systemUser.setOrgName(organization.getShortName());
						 systemUser.setStatus(1);
						 systemUsersDao.insert(systemUser);
					 }
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
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("商务端二级编辑三级保存数据库出错");
		}
		 return result;
	}

}
