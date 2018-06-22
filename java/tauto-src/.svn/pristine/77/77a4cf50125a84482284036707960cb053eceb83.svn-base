package main.com.weixinHtml.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.vo.OrganizationVo;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.utils.StringUtil;
import main.com.weixinHtml.dao.dao.ShopGoodsCarsActivityDao;
import main.com.weixinHtml.dao.dao.ShopGoodsCarsDao;
import main.com.weixinHtml.dao.po.ShopGoodsCars;
import main.com.weixinHtml.dao.po.ShopGoodsCarsActivity;
import main.com.weixinHtml.dao.search.ShopGoodsCarsActivitySearch;
import main.com.weixinHtml.dao.vo.ShopGoodsCarsActivityVo;
import main.com.weixinHtml.service.ShopGoodsCarsActivityService;

@Service
public class ShopGoodsCarsActivityServiceImpl extends BaseServiceImpl<ShopGoodsCarsActivity> implements ShopGoodsCarsActivityService{

	@Autowired
	ShopGoodsCarsActivityDao activityDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	ShopGoodsCarsDao shopGoodsCarsDao;
	
	@Override
	protected BaseDao<ShopGoodsCarsActivity> getBaseDao() {
		return activityDao;
	}

	
	@Override
	public Result activityList(ShopGoodsCarsActivitySearch search, Result result,SystemUsersVo users) {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("keywords", search.getKeywords());
		params.put("brandId", search.getBrandId());
		params.put("orgId", search.getOrgId());
		params.put("orgCode", search.getOrgCode());
		if(StringUtil.isNotEmpty(search.getOrgId())) {
			params.put("overOffShelf", search.getOverOffShelf());
		}else {
			params.put("overOffShelf", false);//只展示上架的车辆
			params.put("state", 0);//只展示未过期的活动
		}
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
		List<ShopGoodsCarsActivity> goodsCarsActivities = activityDao.selectJoin(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", goodsCarsActivities);
		return new Result(returnMap);		
	}
	
	@Override
	public Result activityList(ShopGoodsCarsActivitySearch search, Result result) {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("keywords", search.getKeywords());
		params.put("brandId", search.getBrandId());
		params.put("orgId", search.getOrgId());
		params.put("orgCode", search.getOrgCode());
		if(StringUtil.isNotEmpty(search.getOrgId())) {
			params.put("overOffShelf", search.getOverOffShelf());
		}else {
			params.put("overOffShelf", false);//只展示上架的车辆
			params.put("state", 0);//只展示未过期的活动
		}
		List<ShopGoodsCarsActivity> goodsCarsActivities = activityDao.selectJoin(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", goodsCarsActivities);
		return new Result(returnMap);		
	}

	@Override
	public Result activityInfo(ShopGoodsCarsActivitySearch search, Result result) throws Exception {
		ShopGoodsCarsActivity activity = activityDao.selectByIdJoin(search.getGoodsCarsActivityId());
		if(activity == null) {
			result.setError("抱歉，你选择得活动车辆不存在或者已下架");
			return result;
		}
		result.setOK(ResultCode.CODE_STATE_200, "", activity);
		return result;
	}

	@Override
	public Result activityDel(ShopGoodsCarsActivitySearch search, Result result) throws Exception {
		ShopGoodsCarsActivityVo activityVo = activityDao.selectById(search.getGoodsCarsActivityId());
		if(activityVo == null) {
			result.setError("抱歉，你选择得车辆不存在或者已删除");
			return result;
		}
		if(!activityVo.getOrgId().equals(search.getOrgId())) {
			result.setError("抱歉，你没有此权限");
			return result;
		}
		activityVo.setOverDelete(true);
		return save(activityVo, result, activityVo.getGoodsCarsActivityId());
	}

	@Override
	public Result activityEdit(ShopGoodsCarsActivitySearch search, Result result, SystemUsers users) throws Exception {
		ShopGoodsCarsActivityVo activityVo = null;
		ShopGoodsCars goodsCars = null;
		if(StringUtil.isNotEmptyMoreThenZero(search.getGoodsCarsActivityId())) {
			activityVo = activityDao.selectById(search.getGoodsCarsActivityId());
			if(activityVo == null) {
				result.setError("抱歉，你选择得车辆不存在或者已删除");
				return result;
			}
			if(!activityVo.getOrgId().equals(users.getOrgId())) {
				result.setError("抱歉，你没有此权限");
				return result;
			}
		}else {
			activityVo = new ShopGoodsCarsActivityVo();
			activityVo.setOverDelete(false);
			activityVo.setCreateDate(new Date());
			activityVo.setOverOffShelf(true);
			OrganizationVo organizationVo = organizationDao.selectById(users.getOrgId());
			if(organizationVo == null) {
				result.setError("抱歉，你的门店数据不存在，你没有此权限");
				return result;
			}
			activityVo.setOrgId(organizationVo.getOrgId());
			activityVo.setOrgName(organizationVo.getShortName());
			activityVo.setState(0);
			
			if(StringUtil.isEmpty(search.getGoodsCarsId())){
				result.setError("请先选择车类型");
				return result;
			}
			goodsCars = shopGoodsCarsDao.selectById(search.getGoodsCarsId());
			if(goodsCars == null) {
				result.setError("你选择车类型不存在");
				return result;
			}
			activityVo.setCarsId(goodsCars.getCarsId());
			activityVo.setCarsName(goodsCars.getCarsName());
			activityVo.setGuidingPrice(goodsCars.getGuidingPrice());
			activityVo.setColourId(goodsCars.getColourId());
			activityVo.setColourName(goodsCars.getColourName());
			activityVo.setInteriorId(goodsCars.getInteriorId());
			activityVo.setInteriorName(goodsCars.getInteriorName());
			activityVo.setBareCarPriceUnderLine(goodsCars.getBareCarPriceUnderLine());
			activityVo.setOverInsurance(goodsCars.getOverInsurance());
			activityVo.setBareCarPriceOnLine(goodsCars.getBareCarPriceOnLine());
			activityVo.setBareCarPriceUnderLine(goodsCars.getBareCarPriceUnderLine());
			activityVo.setInvoicePrice(goodsCars.getInvoicePrice());
			activityVo.setWarehouseId(goodsCars.getWarehouseId());
			activityVo.setWarehouseName(goodsCars.getWarehouseName());
			activityVo.setLogisticsCycle(goodsCars.getLogisticsCycle());
			activityVo.setLogisticsPrice(goodsCars.getLogisticsPrice());
			activityVo.setInvoiceCycle(goodsCars.getInvoiceCycle());
			activityVo.setDateOfManufacture(goodsCars.getDateOfManufacture());
			activityVo.setCarsImages(goodsCars.getCarsImages());
			activityVo.setImage(goodsCars.getImage());
			activityVo.setAreaId(goodsCars.getAreaId());
			activityVo.setCityId(goodsCars.getCityId());
			activityVo.setProvinceId(goodsCars.getProvinceId());
			activityVo.setAreaName(goodsCars.getAreaName());
			activityVo.setCityName(goodsCars.getCityName());
			activityVo.setProvinceName(goodsCars.getProvinceName());
			activityVo.setBrandId(goodsCars.getBrandId());
			activityVo.setFamilyId(goodsCars.getFamilyId());
			activityVo.setOrgCode(goodsCars.getOrgCode());
			activityVo.setSystemUsersId(goodsCars.getSystemUsersId());
			activityVo.setLongitude(goodsCars.getLongitude());
			activityVo.setLatitude(goodsCars.getLatitude());
		}
		if(StringUtil.isNotEmptyMoreThenZero(search.getSaleingNumber())) {
			activityVo.setSaleingNumber(search.getSaleingNumber());
		}else {
			result.setError("请输入可售数量");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getDepositPrice())) {
			activityVo.setDepositPrice(search.getDepositPrice());
		}else {
			result.setError("请输入定金金额");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getActivityPrice())) {
			activityVo.setActivityPrice(search.getActivityPrice());
			activityVo.setDiscountPriceOnLine(search.getActivityPrice().subtract(activityVo.getGuidingPrice()));
		}else {
			result.setError("请输入活动售价");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			activityVo.setRemarks(search.getRemarks());
		}else {
			if(goodsCars != null) {
				activityVo.setRemarks(goodsCars.getRemarks());
			}else {
				result.setError("请填写活动描述");
				return result;
			}
		}
		if(StringUtil.isNotEmpty(search.getCarsImage())) {
			activityVo.setCarsImage(search.getCarsImage());
		}else {
			if(goodsCars != null) {
				activityVo.setCarsImage(goodsCars.getCarsImage());
			}else {
				result.setError("请上传活动图片");
				return result;
			}
		}
		return save(activityVo, result, activityVo.getGoodsCarsActivityId());
	}

	@Override
	public Result activityShelves(ShopGoodsCarsActivitySearch search, Result result) throws Exception {
		ShopGoodsCarsActivityVo carsVo = activityDao.selectById(search.getGoodsCarsActivityId());
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
		return save(carsVo, result, carsVo.getGoodsCarsActivityId());
	}
}
