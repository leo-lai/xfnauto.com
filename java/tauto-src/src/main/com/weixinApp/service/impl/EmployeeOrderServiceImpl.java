package main.com.weixinApp.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
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
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.vo.CarColourVo;
import main.com.car.dao.vo.CarInteriorVo;
import main.com.car.dao.vo.CarsVo;
import main.com.customer.dao.dao.CustomerCarDao;
import main.com.customer.dao.dao.CustomerCustomerOrgDao;
import main.com.customer.dao.dao.CustomerOrderDao;
import main.com.customer.dao.dao.CustomerOrderInPayDao;
import main.com.customer.dao.dao.CustomerOrderTrackDao;
import main.com.customer.dao.dao.CustomerUsersDao;
import main.com.customer.dao.po.CustomerCustomerOrg;
import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.po.CustomerOrderInPay;
import main.com.customer.dao.po.CustomerOrderTrack;
import main.com.customer.dao.po.CustomerUsers;
import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.customer.dao.search.CustomerUsersSearch;
import main.com.customer.dao.vo.CustomerCarVo;
import main.com.customer.dao.vo.CustomerOrderTrackVo;
import main.com.customer.dao.vo.CustomerOrderVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.dao.StockStorageDao;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.po.StockStorage;
import main.com.stock.dao.search.StockCarSearch;
import main.com.stock.dao.search.StockOrderSearch;
import main.com.stock.dao.vo.StockCarVo;
import main.com.stock.dao.vo.StockStorageVo;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.vo.OrganizationVo;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.GeneralConstant.CustomerOrderState;
import main.com.utils.IdcardValidator;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.weixinApp.service.EmployeeCustomerService;
import main.com.weixinApp.service.EmployeeOrderService;
import main.com.weixinHtml.dao.dao.ShopAdvanceOrderDao;
import main.com.weixinHtml.dao.dao.ShopUsersDao;
import main.com.weixinHtml.dao.po.ShopAdvanceOrderPayment;
import main.com.weixinHtml.dao.vo.ShopAdvanceOrderVo;
import main.com.weixinHtml.dao.vo.ShopUsersVo;

@Service
public class EmployeeOrderServiceImpl extends BaseServiceImpl<CustomerOrder> implements EmployeeOrderService{

	@Autowired
	CustomerOrderDao customerOrderDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	StockStorageDao stockStorageDao;
	
	@Autowired
	StockCarDao stockCarDao;
	
	@Autowired
	CustomerOrderTrackDao customerOrderTrackDao;
	
	@Autowired
	CustomerCarDao customerCarDao;
	
	@Autowired
	CustomerOrderInPayDao customerOrderInPayDao;
	
	@Autowired
	CustomerCustomerOrgDao customerCustomerOrgDao;
	
	@Autowired
	CarColourDao carColourDao;
	
	@Autowired
	CustomerUsersDao customerUsersDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CarInteriorDao carInteriorDao;
	
	@Autowired 
	ShopAdvanceOrderDao shopAdvanceOrderDao;
	
	@Autowired
	EmployeeCustomerService employeeCustomerService;
	
	@Autowired
	ShopUsersDao shopUserDao;
	
	@Override
	protected BaseDao<CustomerOrder> getBaseDao() {
		return customerOrderDao;
	}

	@Override
	public Result customerOrderList(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请先联系管理员");
			return result;
		}
		Map<String,Object> params = getCustomerParams(search,users);
		List<CustomerOrderVo> customerOrderVos = customerOrderDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = customerOrderDao.getRowCount(params);
		int rows = search.getRows();
		
		//2018 03 12 新增需求----显示库存
		/**
		 * 由于设计原因，查询库存之前必须查询入库单（有入库单删除而库存没有删除的情况，操蛋）
		 */
		//根据用户org查询查询出自身库存单
		Map<String,Object> selectMap = new HashMap<String, Object>();
		selectMap.put("orgId", users.getOrgId());
		List<StockStorageVo> storages =  stockStorageDao.select(selectMap);
		if(storages != null && storages.size() >0 ) {
			Set<Integer> set = new HashSet<>();
			for(StockStorage storage : storages) {
				set.add(storage.getStorageId());
			}
			
			Map<String,Object> carParams = new HashMap<String, Object>();
			if(set != null && set.size() > 0) {
				carParams.put("storageIds", set);
			}
			carParams.put("groupBy", true);
			
			List<StockCarVo> cars = stockCarDao.selectByOrg(carParams);
			for(CustomerOrderVo orderVo : customerOrderVos) {
				for(StockCarVo carVo : cars) {
					if(orderVo.getCarsId().equals(carVo.getCarsId()) && orderVo.getColourId().equals(carVo.getColourId()) && orderVo.getInteriorId().equals(carVo.getInteriorId())) {
						orderVo.setStockCarNumber(orderVo.getStockCarNumber() + carVo.getNumber());
					}
				}
			}
		}else {
//			for(CustomerOrderVo orderVo : customerOrderVos) {
//				
//			}
		}
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CustomerOrderVo orderVo : customerOrderVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("customerOrderCode",orderVo.getCustomerOrderCode());
			map.put("customerName", orderVo.getCustomerName());//
			map.put("createDate", DateUtil.format(orderVo.getCreateDate()));//
			map.put("customerOrderId", orderVo.getCustomerOrderId());//
			map.put("interiorId", orderVo.getInteriorId());//
			map.put("interiorName", orderVo.getInteriorName());//
			map.put("carsName", orderVo.getCarsName());
			map.put("colourId", orderVo.getColourId());
			map.put("colourName", orderVo.getColourName());
			map.put("number", 1);
			map.put("stockCarNumber", orderVo.getStockCarNumber());
			map.put("carsNameBefor", takeCareCarsName(orderVo.getCarsName(), true));
			map.put("carsNameAfter", takeCareCarsName(orderVo.getCarsName(), false));
			map.put("carsId", orderVo.getCarsId());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	}
	
	/**
	 * 获取车辆库存，参考
	 * @param search
	 * @param result
	 * @param users
	 * @return
	 * @throws Exception
	 */
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

	public Map<String,Object> getCustomerParams(CustomerOrderSearch search, SystemUsers users){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getCustomerOrderCode())) {
			params.put("customerOrderCode", search.getCustomerOrderCode());
		}
		if(StringUtil.isNotEmpty(search.getCarsName())) {
			params.put("carsName", search.getCarsName());
		}
		if(StringUtil.isNotEmpty(search.getCarsSearch())) {
			params.put("carsSearch", search.getCarsSearch());
		}
		if(StringUtil.isNotEmpty(search.getCustomerOrderState())) {
			params.put("customerOrderState", search.getCustomerOrderState());
		}else {
			params.put("customerOrderState", GeneralConstant.CustomerOrderState.loanAudit);
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
	
	@Override
	public Result customerOrderInfo(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrderVo customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("customerOrderCode",customerOrder.getCustomerOrderCode());
		map.put("createDate", DateUtil.format(customerOrder.getCreateDate(),"yyyy-MM-dd"));//
		map.put("customerOrderId", customerOrder.getCustomerOrderId());//
		map.put("interiorId", customerOrder.getInteriorId());//
		map.put("interiorName", customerOrder.getInteriorName());//
		map.put("carsName", customerOrder.getCarsName());
		map.put("carsId", customerOrder.getCarsId());
		map.put("colourId", customerOrder.getColourId());
		map.put("colourName", customerOrder.getColourName());
		map.put("remark", customerOrder.getRemarks());
		map.put("customerUserCard", customerOrder.getCustomerUserCard());//购买者身份证号
		map.put("number", 1);
		map.put("customerOrderType", 1);
		if(customerOrder.getCarsVo() != null) {
			map.put("familyId", customerOrder.getCarsVo().getFamilyId());
			map.put("guidingPrice", customerOrder.getCarsVo().getPrice());
		}else {
			map.put("familyId", "");
			map.put("guidingPrice", "");
		}
		Organization organization = organizationDao.selectById(customerOrder.getOrgId());
		if(organization != null) {
			map.put("crgId", customerOrder.getOrgId());
			map.put("orgId", customerOrder.getOrgId());
			map.put("orgName", customerOrder.getOrgName());
			map.put("address", organization.getProvinceName() + organization.getCityName() + organization.getAreaName() + organization.getAddress());
		}else {
			map.put("crgId", "");
			map.put("orgId", "");
			map.put("orgName", "");
			map.put("address", "");
		}
		if(StringUtil.isNotEmptyMoreThenZero(customerOrder.getStockCarId())) {
			StockCar stockCarVo = stockCarDao.getById(customerOrder.getStockCarId());
			map.put("frameNumber", stockCarVo.getFrameNumber());
		}else {
			map.put("frameNumber", "");
		}
		result.setOK(ResultCode.CODE_STATE_200, "", TakeCareMapDate.cutNullMap(map));
		return result;
	}

	private Map<String, Object> takeMapOfOrder(CustomerOrder customerOrder) {
		Map<String,Object> map = new HashMap<String,Object>();
	    map.put("customerOrderId", customerOrder.getCustomerOrderId()); //主键ID
	    map.put("customerOrderCode", customerOrder.getCustomerOrderCode()); //订单编号
	    map.put("customerOrderState", customerOrder.getCustomerOrderState()); //订单状态
	    map.put("createDate", customerOrder.getCreateDate()); //创建日期
	    map.put("customerUserId", customerOrder.getCustomerId()); //购买用户ID
	    map.put("customerName", customerOrder.getCustomerName()); //购买用户ID
	    map.put("customerUserCard", customerOrder.getCustomerUserCard()); //购买用户ID
	    map.put("customerPhoneNumber", customerOrder.getCustomerPhoneNumber()); //购买用户ID
	    map.put("carsId", customerOrder.getCarsId()); //车大类名称
	    map.put("carsName", customerOrder.getCarsName()); //车大类名称
	    map.put("carsIndexImage", customerOrder.getCarsIndexImage());//车辆展示图
	    map.put("brandId", customerOrder.getBrandId());//品牌ID
	    map.put("familyId", customerOrder.getFamilyId());//车系ID
	    map.put("depositPrice", customerOrder.getDepositPrice() != null ?customerOrder.getDepositPrice().doubleValue():0.0d); //定金
	    map.put("discountPrice", customerOrder.getDiscountPrice()!= null ?customerOrder.getDiscountPrice().doubleValue():0.0d);//优惠
	    map.put("interiorId", customerOrder.getInteriorId());//内饰ID
	    map.put("interiorName", customerOrder.getInteriorName());//内饰ID
	    map.put("colourId", customerOrder.getColourId());//颜色ID
	    map.put("colourName", customerOrder.getColourName());//颜色ID
	    map.put("followInformation", customerOrder.getFollowInformation());//随车资料，多个用逗号隔开
	    map.put("boutiqueInformation", customerOrder.getBoutiqueInformation());
	    map.put("balancePrice", customerOrder.getBalancePrice() != null ?customerOrder.getBalancePrice().doubleValue():0.0d);
	    map.put("orgId", customerOrder.getOrgId());
	    map.put("orgName", customerOrder.getOrgName());
	    map.put("stockCarId", customerOrder.getStockCarId());//具体库存车辆
	    map.put("remark", customerOrder.getRemarks());//备注
	    map.put("paymentWay", customerOrder.getPaymentWay());//付款方式 1.全款 2分期
	    map.put("loanBank", customerOrder.getLoanBank());//付款方式 1.全款 2分期
	    map.put("downPayments", customerOrder.getDownPayments()!= null ?customerOrder.getDownPayments().doubleValue():0.0d);//首付
	    map.put("loan", customerOrder.getLoan()!= null ?customerOrder.getLoan().doubleValue():0.0d);//贷款
	    map.put("loanPayBackStages", customerOrder.getLoanPayBackStages());//贷款分期数
	    map.put("amount", customerOrder.getAmount()!= null ?customerOrder.getAmount().doubleValue():0.0d);//最终成交价
	    map.put("customerUserCard", customerOrder.getCustomerUserCard());//购买者身份证号
	    if(customerOrder.getIsMortgage() != null && customerOrder.getIsMortgage()) {
		    map.put("isMortgage", 1);//是否抵押
	    }else {
		    map.put("isMortgage", 0);//是否抵押
	    }
	    if(customerOrder.getOverTheLine() != null && customerOrder.getOverTheLine()) {
	    	map.put("overTheLine", 1);//是否已过线检查
	    }else {
	    	map.put("overTheLine", 0);//是否已过线检查
	    }
	    if(customerOrder.getIsPurchaseTax() != null && customerOrder.getIsPurchaseTax()) {
	    	 map.put("isPurchaseTax", 1);//是否附带购置税
	    }else {
	    	 map.put("isPurchaseTax", 0);//是否附带购置税
	    }
	    if(customerOrder.getIsTakeLicensePlate() != null && customerOrder.getIsTakeLicensePlate()) {
	    	map.put("isTakeLicensePlate",  1);//是否附带上牌
	    }else {
	    	map.put("isTakeLicensePlate",  0);//是否附带上牌
	    }
	    if(customerOrder.getIsInsurance() != null && customerOrder.getIsInsurance()) {
	    	map.put("isInsurance", 1);//是否附加商业险
	    }else {
	    	map.put("isInsurance", 0);//是否附加商业险
	    }
	    map.put("licensePlatePriace", customerOrder.getLicensePlatePriace()!= null ?customerOrder.getLicensePlatePriace().doubleValue():0.0d);//上牌费用
	    map.put("insurancePriace", customerOrder.getInsurancePriace()!= null ?customerOrder.getInsurancePriace().doubleValue():0.0d);//保险金额
	    map.put("purchaseTaxPriace", customerOrder.getPurchaseTaxPriace()!= null ?customerOrder.getPurchaseTaxPriace().doubleValue():0.0d);//购置
	    map.put("vehicleAndVesselTax", customerOrder.getVehicleAndVesselTax()!= null ?customerOrder.getVehicleAndVesselTax().doubleValue():0.0d);//车船税
	    map.put("boutiquePriace", customerOrder.getBoutiquePriace()!= null ?customerOrder.getBoutiquePriace().doubleValue():0.0d);//精品加装费
	    map.put("mortgagePriace", customerOrder.getMortgagePriace()!= null ?customerOrder.getMortgagePriace().doubleValue():0.0d);//按揭
	    map.put("carUnitPrice", customerOrder.getCarUnitPrice()!= null ?customerOrder.getCarUnitPrice().doubleValue():0.0d);//按揭
	    
	    
	    map.put("systemUserId", customerOrder.getSystemUserId());//销售顾问
	    map.put("systemUserName", customerOrder.getSystemUserName());//销售顾问
	    map.put("systemUserPhone", customerOrder.getSystemUserPhone());	//销售电话
	    map.put("extractCarImage", customerOrder.getExtractCarImage());	//提车图片
	    CustomerUsers customerUsers = customerUsersDao.selectById(customerOrder.getCustomerId());
	    map.put("bankAuditsImage", customerUsers.getBankAuditsImage());	//提车图片
	    map.put("bankAuditsvideo", customerUsers.getBankAuditsvideo());	//提车图片
	    
	    //新增需求，只能随便写进来(返回银行审核的拒绝备注)
	    Map<String,Object> paramsTrack = new HashMap<String, Object>();
		paramsTrack.put("customerOrderId", customerOrder.getCustomerOrderId());
	    List<CustomerOrderTrackVo> customerOrderTrackVos = customerOrderTrackDao.select(paramsTrack);
		for(CustomerOrderTrackVo trackVo : customerOrderTrackVos) {
			if(trackVo.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.notPassThrough)) {
				map.put("overLoanRefuse", 1);
				map.put("loanRefuseRemarks", trackVo.getTrackContent());
				break;
			}else {
				map.put("overLoanRefuse", 0);
				map.put("loanRefuseRemarks", "");
			}
		}
		if(StringUtil.isNotEmptyMoreThenZero(customerOrder.getStockCarId())) {
//			System.out.println("库存ID："+customerOrder.getStockCarId());
			StockCar stockCarVo = stockCarDao.getById(customerOrder.getStockCarId());
			if(stockCarVo != null) {
				map.put("frameNumber", stockCarVo.getFrameNumber());
			}else {
				map.put("frameNumber", "");
			}
		}else {
			map.put("frameNumber", "");
		}
		return TakeCareMapDate.cutNullMap(map);
	}
	
	//根据订单查看库存车辆保留参考代码
	public Result customerOrderStorageOutBefor(CustomerOrderSearch search, Result result, SystemUsers users)
			throws Exception {	
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("carsId", customerOrder.getCarsId());
		params.put("interiorId", customerOrder.getInteriorId());
		params.put("colourId", customerOrder.getColourId());
		//从第几条开始
		params.put("sortField", true);
//		params.put("groupBy", true);
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
			map.put("frameNumber", stockCarVo.getFrameNumber());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("carsName", customerOrder.getCarsName());
		allMap.put("carsId", customerOrder.getCarsId());
		allMap.put("customerOrderId", customerOrder.getCustomerOrderId());
		allMap.put("number", 1);
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;	
	}
	
	@Override
	public Result customerOrderStockCar(CustomerOrderSearch search, Result result, SystemUsers users)throws Exception{
		if(StringUtil.isEmpty(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，无法执行此操作，请联系管理员");
			return result;
		}
		if(StringUtil.isEmpty(search.getCustomerOrderId())) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体的订单记录查看");
			return result;
		}
		CustomerOrderVo orderVo = customerOrderDao.selectById(search.getCustomerOrderId());
		if(orderVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的订单记录不存在或者已删除");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(orderVo.getCarsId())) {
			params.put("carsId", orderVo.getCarsId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+orderVo.getCustomerOrderCode()+"的车型数据错误，无法进行此操作");
			return result;
		}
		if(StringUtil.isNotEmpty(orderVo.getColourId())) {
			params.put("colourId", orderVo.getColourId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+orderVo.getCustomerOrderCode()+"的车身颜色数据错误，无法进行此操作");
			return result;
		}
		if(StringUtil.isNotEmpty(orderVo.getInteriorId())) {
			params.put("interiorId", orderVo.getInteriorId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+orderVo.getCustomerOrderCode()+"的车辆内饰数据错误，无法进行此操作");
			return result;
		}
		params.put("stockOrgId", users.getOrgId());
		List<StockCarVo> cars = stockCarDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		allMap.put("carsId", orderVo.getCarsId());
		allMap.put("carsName", orderVo.getCarsName());
		allMap.put("interiorName", orderVo.getInteriorName());
		allMap.put("interiorId", orderVo.getInteriorId());
		allMap.put("colourName", orderVo.getColourName());
		allMap.put("colourId", orderVo.getColourId());
		allMap.put("customerOrderId", orderVo.getCustomerOrderId());
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(StockCarVo stockCarVo : cars) {
			if(StringUtil.isEmpty(stockCarVo.getStockCarId())) {
				continue;
			}
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("frameNumber", stockCarVo.getFrameNumber());
			map.put("factoryOut", stockCarVo.getFactoryOut());
			map.put("stockCarId", stockCarVo.getStockCarId());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("list", maps);
		allMap.put("number", 1);
		result.setOK(ResultCode.CODE_STATE_200, "", allMap);
		return result;
	}

	@Override
	@Transactional
	public Result customerOrderStockCarPutout(CustomerOrderSearch search, Result result, SystemUsers users)
			throws Exception {
		//查询车辆，出库
		if(search.getCustomerOrderId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体订单进行操作");
			return result;
		}
		CustomerOrderVo customerOrderVo = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrderVo == null){
			result.setError(ResultCode.CODE_STATE_4006, "订单信息错误，你选择的订单已删除或不存在。请刷新重试或者重新选择");
			return result;
		}
		if(users.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不完整，请联系管理员补充完整");
			return result;
		}
		if(!customerOrderVo.getOrgId().equals(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单的卖方与你公司信息不匹配，你不能进行修改");
			return result;
		}
		if(!GeneralConstant.CustomerOrderState.loanAudit.equals(customerOrderVo.getCustomerOrderState())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单尚未收取全款的定金或银行尚未通过贷款审核，尚未能出库");
			return result;
		}
		Set<Integer> set = new HashSet<Integer>();
		if(StringUtil.isEmpty(search.getStockCarId())) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择出库车辆");
			return result;
		}
		set.add(search.getStockCarId());
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(customerOrderVo.getCarsId())) {
			params.put("carsId", customerOrderVo.getCarsId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+customerOrderVo.getCustomerOrderCode()+"的车型数据错误，无法进行此操作");
			return result;
		}
		if(StringUtil.isNotEmpty(customerOrderVo.getColourId())) {
			params.put("colourId", customerOrderVo.getColourId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+customerOrderVo.getCustomerOrderCode()+"的车身颜色数据错误，无法进行此操作");
			return result;
		}
		if(StringUtil.isNotEmpty(customerOrderVo.getInteriorId())) {
			params.put("interiorId", customerOrderVo.getInteriorId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+customerOrderVo.getCustomerOrderCode()+"的车辆内饰数据错误，无法进行此操作");
			return result;
		}
		params.put("stockOrgId", users.getOrgId());
		List<StockCarVo> cars = stockCarDao.select(params);		//查询出当前的可以该订单可以出库的所有车辆
		if(set.size() <= 0) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择出库车辆");
			return result;
		}
		if(cars == null || cars.size() <= 0) {
			result.setError(ResultCode.CODE_STATE_4005, "库存车辆不足");
			return result;
		}else if(cars.size() < set.size()) {
			result.setError(ResultCode.CODE_STATE_4005, "库存车辆不足");
			return result;
		}
		Set<Integer> bigSet = new HashSet<Integer>();
		for(StockCarVo carVo : cars) {
			bigSet.add(carVo.getStockCarId());
		}
		for(Integer min : set) {
			if(bigSet.add(min)) {//如果选择的车辆有不在查询出来的库存里面的，怎不允许通过
				result.setError(ResultCode.CODE_STATE_4005, "你选择的车辆不存在于库存，数据错误，请核对数据");
				return result;
			}
		}
		List<StockCarVo> changeCar = new ArrayList<StockCarVo>();
		for(StockCarVo carVo : cars) {
			if(set.contains(carVo.getStockCarId())) {
				carVo.setIsPutOut(true);
				carVo.setOrderId(customerOrderVo.getCustomerOrderId());
				changeCar.add(carVo);
			}
		}
		//修改订单状态
		customerOrderVo.setCustomerOrderState(GeneralConstant.CustomerOrderState.deliveryOfTheTail);
		StockCarVo carVo = changeCar.get(0);
		customerOrderVo.setStockCarId(carVo.getStockCarId());
		if(customerOrderDao.updateById(customerOrderVo)) {
			for(StockCarVo stockCarVo : changeCar) {//出库三级库存车辆
				stockCarDao.updateById(stockCarVo);
			}
			//新增订单跟踪
			//记录订单跟踪
			CustomerOrderTrack orderTrack = new CustomerOrderTrack();
			orderTrack.setCreateDate(new Date());
			/**
			 * 1:已提交定车单
				3:已支付定金,待银行审批贷款
				5:待车辆到店
				7:车辆到店，可到店交付尾款
				9:已交付尾款，待车辆加装上牌
				11:加装／上牌完成，可到店提车
			 */
			orderTrack.setCustomerOrderId(customerOrderVo.getCustomerOrderId());
			orderTrack.setCustomerOrderCode(customerOrderVo.getCustomerOrderCode());
			orderTrack.setCustomerOrderState(customerOrderVo.getCustomerOrderState());
			orderTrack.setTrackContent("车辆已出库");
			customerOrderTrackDao.insert(orderTrack);
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("数据保存失败，请联系管理员");
			return result;
		}
		//入库到人车表
		CustomerCarVo customerCarVo = new CustomerCarVo();
		customerCarVo.setCarsId(customerOrderVo.getCarsId());
		customerCarVo.setColourId(customerOrderVo.getColourId());
		customerCarVo.setInteriorId(customerOrderVo.getInteriorId());
		customerCarVo.setColourName(customerOrderVo.getColourName());
		customerCarVo.setInteriorName(customerOrderVo.getInteriorName());		
		customerCarVo.setStockCarName(carVo.getCarsInfo());
		customerCarVo.setStockCarId(carVo.getStockCarId());
		customerCarVo.setCreateDate(new Date());
		customerCarVo.setCertificateNumber(carVo.getCertificateNumber());
		customerCarVo.setEngineNumber(carVo.getEngineNumber());
		customerCarVo.setFrameNumber(carVo.getFrameNumber());
		customerCarVo.setTransactionPrice(customerOrderVo.getAmount());
		customerCarVo.setIsDelete(true);
		customerCarVo.setCustomerUserId(customerOrderVo.getCustomerId());
		customerCarVo.setCustomerOrderId(customerOrderVo.getCustomerOrderId());
		if(GeneralConstant.CustomerOrderState.fullPayment.equals(customerOrderVo.getPaymentWay())) {
			customerCarVo.setCarPurchasePlan("全款购车");
		}else if(GeneralConstant.CustomerOrderState.byStages.equals(customerOrderVo.getPaymentWay())){
			customerCarVo.setCarPurchasePlan("贷款分期");
			customerCarVo.setLoanScheme("贷款购车（首付："+(customerOrderVo.getDownPayments()!=null?customerOrderVo.getDownPayments().doubleValue():"未知")+"，贷款金额："+(customerOrderVo.getLoan()!=null?customerOrderVo.getLoan().doubleValue():"未知")
					+ "还款期数："+(customerOrderVo.getLoanPayBackStages()!=null?customerOrderVo.getLoanPayBackStages():"未知")+"）");
		}else {
			customerCarVo.setCarPurchasePlan("未知");
		}
		customerCarVo.setImages(customerOrderVo.getCarsIndexImage());
		customerCarDao.insert(customerCarVo);
		return result;
	}

	//等待上牌列表
	@Override
	public Result orderLicensePlateList(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请先联系管理员");
			return result;
		}
		search.setCustomerOrderState(CustomerOrderState.retrofitting);//设置查询上牌中的订单
//		Set<Integer> orderSet = new HashSet<Integer>();
//		if(search.getStartDate() != null || search.getEndDate() != null) {
//			Map<String,Object> payParams = new HashMap<String,Object>();
//			payParams.put("startDate", search.getStartDate());
//			payParams.put("endDate", search.getEndDate());
//			payParams.put("customerOrderState", CustomerOrderState.deliveryOfTheTail);
//			List<CustomerOrderInPay> inPays = customerOrderInPayDao.select(payParams);
//			for(CustomerOrderInPay inPay : inPays) {
//				orderSet.add(inPay.getCustomerOrderId());
//			}
//		}
		Map<String,Object> params = getCustomerParams(search,users);
//		if(orderSet != null && orderSet.size() >0) {
//			params.put("ids", orderSet);
//		}
		List<CustomerOrderVo> customerOrderVos = customerOrderDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = customerOrderDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();		
		//查询车辆
		Set<Integer> set = new HashSet<Integer>();
		for(CustomerOrderVo orderVo : customerOrderVos) {
			set.add(orderVo.getStockCarId());
			//查询订单支付尾款的时间（时间紧迫，懒得捣鼓连表查询，唉....）
			Map<String,Object> payParams = new HashMap<String, Object>();
			payParams.put("customerOrderId", orderVo.getCustomerOrderId());
			payParams.put("customerOrderState", GeneralConstant.CustomerOrderState.deliveryOfTheTail);
			List<CustomerOrderInPay> inPays = customerOrderInPayDao.select(payParams);
			if(inPays != null && inPays.size() > 0) {
				orderVo.setInPay(inPays.get(0));
			}
		}
		if(set.size() <= 0) {
			allMap.put("page", search.getPage());
			allMap.put("total", total);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		//查询车辆
		Map<String,Object> carParams = new HashMap<String, Object>();
		carParams.put("isPutOut", true);
		carParams.put("ids", set);
		List<StockCarVo> stockCarVos = stockCarDao.select(carParams);
		if(stockCarVos == null) {
			allMap.put("page", search.getPage());
			allMap.put("total", total);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		for(CustomerOrderVo orderVo : customerOrderVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("customerOrderCode",orderVo.getCustomerOrderCode());
			map.put("customerName", orderVo.getCustomerName());//
			map.put("createDate", orderVo.getCreateDate());//
			map.put("customerOrderId", orderVo.getCustomerOrderId());//
			map.put("interiorId", orderVo.getInteriorId());//
			map.put("interiorName", orderVo.getInteriorName());//
			map.put("carsName", orderVo.getCarsName());
			map.put("colourId", orderVo.getColourId());
			map.put("colourName", orderVo.getColourName());
			map.put("carsNameBefor", takeCareCarsName(orderVo.getCarsName(), true));
			map.put("carsNameAfter", takeCareCarsName(orderVo.getCarsName(), false));
			map.put("carsId", orderVo.getCarsId());
			map.put("isMortgage", orderVo.getIsMortgage());
			map.put("systemUserId", orderVo.getSystemUserId());
			map.put("systemUserName", orderVo.getSystemUserName());
			map.put("systemUserPhone", orderVo.getSystemUserPhone());
			for(StockCarVo carVo : stockCarVos) {
				if(carVo.getStockCarId().equals(orderVo.getStockCarId())) {
					map.put("frameNumber", carVo.getFrameNumber());
				}
			}
			if(orderVo.getInPay() != null) {
				map.put("payDate", DateUtil.format(orderVo.getInPay().getPayDate(),"yyyy-MM-dd"));
			}else {
				map.put("payDate", "");
			}
			if(orderVo.getOverTheLine() != null && orderVo.getOverTheLine()) {
				map.put("overTheLine", 1);
			}else {
				map.put("overTheLine", 0);
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
	public Result licensePlateDone(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(!GeneralConstant.CustomerOrderState.retrofitting.equals(customerOrder.getCustomerOrderState())) {
			if(customerOrder.getCustomerOrderState() > GeneralConstant.CustomerOrderState.retrofitting) {
				result.setError("订单已历过上牌完成的历程，已不需要进行此操作");
				return result;
			}else {
				result.setError("订单尚未至上牌历程，请耐心等待");
				return result;
			}
		}
		customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.padPasting);//上牌完成
		if(customerOrderDao.updateById(customerOrder)) {
			//记录订单跟踪
			CustomerOrderTrack orderTrack = new CustomerOrderTrack();
			orderTrack.setCreateDate(new Date());
			/**
			 * 1:已提交定车单
				3:已支付定金,待银行审批贷款
				5:待车辆到店
				7:车辆到店，可到店交付尾款
				9:已交付尾款，待车辆加装上牌
				11:加装／上牌完成，可到店提车
			 */
			orderTrack.setTrackContent("上牌完成，等待贴膜");
			orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
			orderTrack.setCustomerOrderState(customerOrder.getCustomerOrderState());
			orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
			customerOrderTrackDao.insert(orderTrack);
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("保存数据失败");
		}
		return result;
	}

	@Override
	public Result carsProductsList(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请先联系管理员");
			return result;
		}
		search.setCustomerOrderState(CustomerOrderState.deliveryOfTheTail);//设置查询上牌中的订单
		Set<Integer> orderSet = new HashSet<Integer>();
//		if(search.getStartDate() != null || search.getEndDate() != null) {
//			Map<String,Object> payParams = new HashMap<String,Object>();
//			payParams.put("startDate", search.getStartDate());
//			payParams.put("endDate", search.getEndDate());
//			payParams.put("customerOrderState", CustomerOrderState.deliveryOfTheTail);
//			List<CustomerOrderInPay> inPays = customerOrderInPayDao.select(payParams);
//			for(CustomerOrderInPay inPay : inPays) {
//				orderSet.add(inPay.getCustomerOrderId());
//			}
//		}
		Map<String,Object> params = getCustomerParams(search,users);
//		if(orderSet != null && orderSet.size() >0) {
//			params.put("ids", orderSet);
//		}
		List<CustomerOrderVo> customerOrderVos = customerOrderDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = customerOrderDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();		
		//查询车辆
		Set<Integer> set = new HashSet<Integer>();
		for(CustomerOrderVo orderVo : customerOrderVos) {
			set.add(orderVo.getStockCarId());
			//查询订单支付尾款的时间（时间紧迫，懒得捣鼓连表查询，唉....）
			Map<String,Object> payParams = new HashMap<String, Object>();
			payParams.put("customerOrderId", orderVo.getCustomerOrderId());
			payParams.put("customerOrderState", GeneralConstant.CustomerOrderState.deliveryOfTheTail);
			List<CustomerOrderInPay> inPays = customerOrderInPayDao.select(payParams);
			if(inPays != null && inPays.size() > 0) {
				orderVo.setInPay(inPays.get(0));
			}
		}
		if(set.size() <= 0) {
			allMap.put("page", search.getPage());
			allMap.put("total", total);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		//查询车辆
		Map<String,Object> carParams = new HashMap<String, Object>();
		carParams.put("isPutOut", true);
		carParams.put("ids", set);
		List<StockCarVo> stockCarVos = stockCarDao.select(carParams);
		if(stockCarVos == null) {
			allMap.put("page", search.getPage());
			allMap.put("total", total);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		for(CustomerOrderVo orderVo : customerOrderVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("customerOrderCode",orderVo.getCustomerOrderCode());
			map.put("customerName", orderVo.getCustomerName());//
//			map.put("createDate", DateUtil.format(orderVo.getCreateDate()));//
			map.put("customerOrderId", orderVo.getCustomerOrderId());//
			map.put("interiorId", orderVo.getInteriorId());//
			map.put("interiorName", orderVo.getInteriorName());//
			map.put("carsName", orderVo.getCarsName());
			map.put("colourId", orderVo.getColourId());
			map.put("colourName", orderVo.getColourName());
			map.put("carsNameBefor", takeCareCarsName(orderVo.getCarsName(), true));
			map.put("carsNameAfter", takeCareCarsName(orderVo.getCarsName(), false));
			map.put("carsId", orderVo.getCarsId());
			map.put("isMortgage", orderVo.getIsMortgage());
			map.put("systemUserId", orderVo.getSystemUserId());
			map.put("systemUserName", orderVo.getSystemUserName());
			map.put("systemUserPhone", orderVo.getSystemUserPhone());
			map.put("estimateDate", orderVo.getEstimateDate() != null ? DateUtil.format(orderVo.getEstimateDate(),"yyyy-MM-dd"):"");
			for(StockCarVo carVo : stockCarVos) {
				if(carVo.getStockCarId().equals(orderVo.getStockCarId())) {
					map.put("frameNumber", carVo.getFrameNumber());
				}
			}
			if(orderVo.getInPay() != null) {
				map.put("payDate", DateUtil.format(orderVo.getInPay().getPayDate(),"yyyy-MM-dd"));
			}else {
				map.put("payDate", "");
			}
			if(orderVo.getOverTheLine() != null && orderVo.getOverTheLine()) {
				map.put("overTheLine", 1);
			}else {
				map.put("overTheLine", 0);
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
	public Result addCarsProductsEstimateDate(CustomerOrderSearch search, Result result, SystemUsers users)
			throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(!GeneralConstant.CustomerOrderState.deliveryOfTheTail.equals(customerOrder.getCustomerOrderState())) {
			if(customerOrder.getCustomerOrderState() > GeneralConstant.CustomerOrderState.deliveryOfTheTail) {
				result.setError("订单已历过上牌完成的历程，已不需要进行此操作");
				return result;
			}else {
				result.setError("订单尚未至上牌历程，请耐心等待");
				return result;
			}
		}
		customerOrder.setEstimateDate(DateUtil.format(search.getEstimateDate()));
		if(customerOrderDao.updateById(customerOrder)) {
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("保存数据失败");
		}
		return result;
	}

	@Override
	public Result carsProductsInfo(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请先联系管理员");
			return result;
		}
		CustomerOrderVo customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		Map<String,Object> payParams = new HashMap<String, Object>();
		payParams.put("customerOrderId", customerOrder.getCustomerOrderId());
		payParams.put("customerOrderState", GeneralConstant.CustomerOrderState.deliveryOfTheTail);
		List<CustomerOrderInPay> inPays = customerOrderInPayDao.select(payParams);
		if(inPays != null && inPays.size() > 0) {
			customerOrder.setInPay(inPays.get(0));
		}

		//查询车辆
		Map<String,Object> carParams = new HashMap<String, Object>();
		carParams.put("isPutOut", true);
		carParams.put("stockCarId", customerOrder.getCarsId());
		List<StockCarVo> stockCarVos = stockCarDao.select(carParams);
		Map<String,Object> allMap = new HashMap<String, Object>();
		if(stockCarVos == null) {
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("customerOrderCode",customerOrder.getCustomerOrderCode());
			map.put("customerName", customerOrder.getCustomerName());//
//			map.put("createDate", DateUtil.format(orderVo.getCreateDate()));//
			map.put("customerOrderId", customerOrder.getCustomerOrderId());//
			map.put("interiorId", customerOrder.getInteriorId());//
			map.put("interiorName", customerOrder.getInteriorName());//
			map.put("carsName", customerOrder.getCarsName());
			map.put("colourId", customerOrder.getColourId());
			map.put("colourName", customerOrder.getColourName());
			map.put("carsNameBefor", takeCareCarsName(customerOrder.getCarsName(), true));
			map.put("carsNameAfter", takeCareCarsName(customerOrder.getCarsName(), false));
			map.put("carsId", customerOrder.getCarsId());
			map.put("followInformation", customerOrder.getFollowInformation());
			map.put("boutiqueInformation", customerOrder.getBoutiqueInformation());
			map.put("estimateDate", customerOrder.getEstimateDate() != null ? DateUtil.format(customerOrder.getEstimateDate(),"yyyy-MM-dd"):"");
			map.put("systemUserId", customerOrder.getSystemUserId());
			map.put("systemUserName", customerOrder.getSystemUserName());
			map.put("systemUserPhone", customerOrder.getSystemUserPhone());
			for(StockCarVo carVo : stockCarVos) {
				if(carVo.getStockCarId().equals(customerOrder.getStockCarId())) {
					map.put("frameNumber", carVo.getFrameNumber());
				}
			}
			if(customerOrder.getInPay() != null) {
				map.put("payDate", DateUtil.format(customerOrder.getInPay().getPayDate(),"yyyy-MM-dd"));
			}else {
				map.put("payDate", "");
			}
			if(customerOrder.getOverTheLine() != null && customerOrder.getOverTheLine()) {
				map.put("overTheLine", 1);
			}else {
				map.put("overTheLine", 0);
			}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",TakeCareMapDate.cutNullMap(map));
		return result;
	}

	@Override
	public Result carsProductsDone(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(!GeneralConstant.CustomerOrderState.deliveryOfTheTail.equals(customerOrder.getCustomerOrderState())) {
			if(customerOrder.getCustomerOrderState() > GeneralConstant.CustomerOrderState.deliveryOfTheTail) {
				result.setError("订单已历过加装精品的历程，已不需要进行此操作");
				return result;
			}else {
				result.setError("订单尚未至加装精品历程，请耐心等待");
				return result;
			}
		}
		customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.retrofitting);//加装完成，等待上牌
		if(customerOrderDao.updateById(customerOrder)) {
			//记录订单跟踪
			CustomerOrderTrack orderTrack = new CustomerOrderTrack();
			orderTrack.setCreateDate(new Date());
			/**
			 * 1:已提交定车单
				3:已支付定金,待银行审批贷款
				5:待车辆到店
				7:车辆到店，可到店交付尾款
				9:已交付尾款，待车辆加装上牌
				11:加装／上牌完成，可到店提车
			 */
			orderTrack.setTrackContent("加装精品完成，等待上牌");
			orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
			orderTrack.setCustomerOrderState(customerOrder.getCustomerOrderState());
			orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
			customerOrderTrackDao.insert(orderTrack);
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("保存数据失败");
		}
		return result;
	}

	@Override
	public Result editCustomerOrder(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = null;
		if(StringUtil.isEmpty(users.getOrgId())) {
			result.setError("你的公司信息不明确，不能执行此操作，请联系管理员");
			return result;
		}
		OrganizationVo organizationVo = organizationDao.selectById(users.getOrgId());
		if(organizationVo == null) {
			result.setError("你的公司信息不明确，不能执行此操作，请联系管理员");
			return result;
		}
		if(!organizationVo.getOrgLevel().equals(GeneralConstant.Org.Level_3)) {
			result.setError("你不是门店级别的用户，不能进行此操作");
			return result;
		}
		if(StringUtil.isEmpty(search.getCustomerOrderId())) {
			//查询该用户是否已存在订单，如果已存在则不让其再开一单（系统1.0不支持多单）
			customerOrder = customerOrderDao.selectByUsersId(search.getCustomerUsersId());
			if(customerOrder != null) {
				result.setError("该用户已经存在订单，请勿再下单，如有疑问请联系管理员");
				return result;
			}
			customerOrder = new CustomerOrder();
			customerOrder.setCreateDate(new Date());
			if(StringUtil.isEmpty(search.getCustomerUsersId())) {
				result.setError("你选择的用户信息错误，请刷新重试或者联系管理员");
				return result;
			}

			customerOrder.setCustomerOrderCode(customerOrderDao.getCode(customerOrder.getCustomerPhoneNumber()));
			customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.start);
			customerOrder.setOverTheLine(false);

			customerOrder.setOrgId(organizationVo.getOrgId());
			customerOrder.setOrgName(organizationVo.getShortName());
			
			CustomerUsers customerUsers = customerUsersDao.selectById(search.getCustomerUsersId());
			if(customerUsers == null) {
				result.setError("你选择的用户信息错误，请刷新重试或者联系管理员");
				return result;
			}
			customerOrder.setCustomerId(customerUsers.getCustomerUsersId());
			customerOrder.setCustomerName(customerUsers.getCustomerUsersName());
			customerOrder.setCustomerPhoneNumber(customerUsers.getPhoneNumber());
			
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("orgId", organizationVo.getOrgId());
			params.put("customerUsersId", customerUsers.getCustomerUsersId());
			List<CustomerCustomerOrg> customerOrgs = customerCustomerOrgDao.select(params);
			if(customerOrgs == null || customerOrgs.size() <= 0) {
				result.setError("门店&客户信息错误，请先新建立用户信息");
				return result;
			}
		}else {
			customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
			if(customerOrder.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.orderBeenFinish)) {
				result.setError("订单已交车，订单信息已不能再进行修改");
				return result;
			}
		}
		if(StringUtil.isEmpty(search.getCustomerUserCard())) {
			result.setError("请输入用户身份证号");
			return result;
		}
		IdcardValidator validator = new IdcardValidator();
		if(validator.isValidatedAllIdcard(search.getCustomerUserCard())) {
			customerOrder.setCustomerUserCard(search.getCustomerUserCard());
		}else{
			result.setError(ResultCode.CODE_STATE_4005, "身份证号码不正确,请核对");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getSystemUserId())) {
			SystemUsers systemUsers = systemUsersDao.selectByIdInUse(search.getSystemUserId());
			customerOrder.setSystemUserId(systemUsers.getUsersId());
			customerOrder.setSystemUserName(systemUsers.getRealName());
			customerOrder.setSystemUserPhone(systemUsers.getPhoneNumber());
			
			if(StringUtil.isNotEmpty(search.getCustomerOrderId())){//如果再次编辑订单时，涉及到修改销售
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("orgId", users.getOrgId());
				params.put("customerUsersId", customerOrder.getCustomerId());
				List<CustomerCustomerOrg> customerOrgs = customerCustomerOrgDao.select(params);
				//自动从跟踪用户列表剔除出去
				for(CustomerCustomerOrg customerOrg : customerOrgs) {
					customerOrg.setIsTrack(true);//标记为跟踪
					customerOrg.setSystemUserId(customerOrder.getSystemUserId());
					customerOrg.setSystemUserName(customerOrder.getSystemUserName());
					customerOrg.setSystemUserPhone(customerOrder.getSystemUserPhone());
					customerCustomerOrgDao.updateById(customerOrg);
				}
			}
		}else {
			result.setError("请选择销售人员");
			return result;
		}
		if(StringUtil.isEmpty(search.getBrandId())) {
			result.setError("请选择品牌");
			return result;
		}
		customerOrder.setBrandId(search.getBrandId());
		if(StringUtil.isEmpty(search.getFamilyId())) {
			result.setError("请选择车系");
			return result;
		}
		customerOrder.setFamilyId(search.getFamilyId());
		if(StringUtil.isEmpty(search.getCarsId())) {
			result.setError("请选择车型");
			return result;
		}
		if(customerOrder.getCustomerOrderState().intValue() >= GeneralConstant.CustomerOrderState.deliveryOfTheTail && !search.getCarsId().equals(customerOrder.getCarsId())) {
			result.setError("订单车辆已出库，已不能修改订单车辆");
			return result;
		}
		CarsVo cars = carsDao.selectById(search.getCarsId());
		if(cars == null) {
			result.setError(ResultCode.CODE_STATE_4005, "所选车型不存在，请重新选择");
			return result;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(cars.getBrandName()).append(" ");
		buffer.append(cars.getFamilyName()).append(" ");
		buffer.append(cars.getYearPattern()).append("款").append(" ");
		buffer.append(cars.getPl()).append(" ");
		buffer.append(cars.getGearboxName()).append(cars.getStyleName());
		customerOrder.setCarsId(search.getCarsId());
		customerOrder.setCarsName(buffer.toString());
		customerOrder.setCarsIndexImage(cars.getIndexImage());
		if(StringUtil.isEmpty(search.getColourId())) {
			result.setError("请选择车身颜色");
			return result;
		}
		CarColourVo colourVo = carColourDao.selectById(search.getColourId());
		if(colourVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你所选择的车身颜色不存在");
			return result;
		}
		customerOrder.setColourName(colourVo.getCarColourName());
		customerOrder.setColourId(search.getColourId());
		if(StringUtil.isEmpty(search.getInteriorId())) {
			result.setError("请选择内饰");
			return result;
		}
		CarInteriorVo interiorVo = carInteriorDao.selectById(search.getInteriorId());
		if(interiorVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你所选择的车辆内饰不存在");
			return result;
		}
		customerOrder.setInteriorName(interiorVo.getInteriorName());
		customerOrder.setInteriorId(search.getInteriorId());
		if(StringUtil.isEmpty(search.getCarUnitPrice())) {
			result.setError("请输入最终成交价");
			return result;
		}else {
			customerOrder.setCarUnitPrice(new BigDecimal(search.getCarUnitPrice()));
		}
//		if(search.getIsTakeLicensePlate() != null && search.getIsTakeLicensePlate()) {
//			customerOrder.setIsTakeLicensePlate(true);
		Double amount = search.getCarUnitPrice();
		if(StringUtil.isNotEmpty(search.getLicensePlatePriace())) {
			customerOrder.setIsTakeLicensePlate(false);
			customerOrder.setLicensePlatePriace(new BigDecimal(search.getLicensePlatePriace()));
			amount += search.getLicensePlatePriace();
		}
		if(StringUtil.isNotEmpty(search.getDepositPrice())) {//定金
			if(!customerOrder.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.start) && customerOrder.getDepositPrice().doubleValue() != search.getDepositPrice()) {
				result.setError("订单定金已支付，定金金额已不能进行修改");
				return result;
			}
			customerOrder.setDepositPrice(new BigDecimal(search.getDepositPrice()));
		}else {
			result.setError("请输入定金");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getInsurancePriace())) {
			customerOrder.setIsInsurance(false);
			customerOrder.setInsurancePriace(new BigDecimal(search.getInsurancePriace()));
			amount += search.getInsurancePriace();
		}
		if(StringUtil.isNotEmpty(search.getPurchaseTaxPriace())) {
			customerOrder.setIsPurchaseTax(true);
			customerOrder.setPurchaseTaxPriace(new BigDecimal(search.getPurchaseTaxPriace()));
			amount += search.getPurchaseTaxPriace();
		}
		if(StringUtil.isNotEmpty(search.getBoutiqueInformation())) {		
			if(StringUtil.isEmpty(search.getBoutiquePriace())) {
				result.setError("请选择添加加装的精品费用");
				return result;
			}
			customerOrder.setBoutiqueInformation(search.getBoutiqueInformation());
			customerOrder.setBoutiquePriace(new BigDecimal(search.getBoutiquePriace()));
			amount += search.getBoutiquePriace();
		}
//		else {
//			if(StringUtil.isNotEmpty(search.getBoutiqueInformation())) {
//				result.setError("请选择添加加装的精品费用");
//				return result;
//			}
//		}
		
		if(StringUtil.isNotEmpty(search.getMortgagePriace())) {
			customerOrder.setMortgagePriace(new BigDecimal(search.getMortgagePriace()));
			amount += search.getMortgagePriace();
		}
		if(StringUtil.isNotEmpty(search.getVehicleAndVesselTax())) {//车船税
			customerOrder.setVehicleAndVesselTax(new BigDecimal(search.getVehicleAndVesselTax()));
			amount += search.getVehicleAndVesselTax();
		}
		customerOrder.setAmount(new BigDecimal(amount));//把各项价格叠加，确定订单价格
		if(StringUtil.isEmpty(search.getPaymentWay())) {
			result.setError("请选择购车方案");
			return result;
		}
		if(search.getPaymentWay().equals(GeneralConstant.CustomerOrderState.fullPayment)) {//全款
			if(customerOrder.getCustomerOrderState().intValue() >= GeneralConstant.CustomerOrderState.paymentOfADeposit && !search.getPaymentWay().equals(customerOrder.getPaymentWay())) {
				result.setError("订单已确认，已不能由贷款改为全款");
				return result;
			}
			customerOrder.setPaymentWay(GeneralConstant.CustomerOrderState.fullPayment);
		}else if(search.getPaymentWay().equals(GeneralConstant.CustomerOrderState.byStages)) {
			if(customerOrder.getCustomerOrderState().intValue() >= GeneralConstant.CustomerOrderState.loanAudit && !search.getPaymentWay().equals(customerOrder.getPaymentWay())) {
				result.setError("订单已确认，已不能由贷款改为全款");
				return result;
			}
//			Set<Integer> set = new HashSet<Integer>();
//			set.add(GeneralConstant.LoanBank.CHERY);
			if(customerOrder.getCustomerOrderState().intValue() >= GeneralConstant.CustomerOrderState.loanAudit && !search.getLoanBank().equals(customerOrder.getLoanBank())) {
//				if(customerOrder.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.notPassThrough)) {
//					
//				}else {
//					result.setError("订单已确认，已不能修改贷款银行");
//					return result;
//				}
				result.setError("订单已确认，已不能修改贷款银行");
				return result;
			}
			/**
			 * 在农行审核不通过的情况下修改银行
			 */
			if(GeneralConstant.CustomerOrderState.notPassThrough.equals(customerOrder.getCustomerOrderState()) && !search.getLoanBank().equals(customerOrder.getLoanBank())) {
				customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.paymentOfADeposit);
				customerOrder.setAuditStatus(0);
//				customerOrder.setAuditTime();
			}
			if(StringUtil.isNotEmpty(search.getLoanBank())) {
				switch (search.getLoanBank()) {
				case GeneralConstant.LoanBank.CHERY:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.CHERY);
					break;
				case GeneralConstant.LoanBank.GONGSHANG:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.GONGSHANG);
					break;
				case GeneralConstant.LoanBank.HESHANZHUJIANG:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.HESHANZHUJIANG);
					break;
				case GeneralConstant.LoanBank.JIANSHE:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.JIANSHE);
					break;
				case GeneralConstant.LoanBank.NONGYE:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.NONGYE);
					break;
				case GeneralConstant.LoanBank.RUIFUDE:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.RUIFUDE);
					break;
				case GeneralConstant.LoanBank.GUNAGZHOU:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.GUNAGZHOU);
					break;
				case GeneralConstant.LoanBank.HESHANNONGCUN:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.HESHANNONGCUN);
					break;
				default:
					result.setError("你选择的贷款银行错误，请重新选择");
					return result;
				}
			}else {
				result.setError("请选择贷款银行");
				return result;
			}
			customerOrder.setPaymentWay(GeneralConstant.CustomerOrderState.byStages);
			if(StringUtil.isNotEmpty(search.getDownPayments())) {
				customerOrder.setDownPayments(new BigDecimal(search.getDownPayments()));
			}else {
				result.setError("请输入首付金额");
				return result;
			}
			if(StringUtil.isNotEmpty(search.getLoan())) {
				customerOrder.setLoan(new BigDecimal(search.getLoan()));
			}else {
				result.setError("请输入贷款金额");
				return result;
			}
			if(StringUtil.isNotEmpty(search.getLoanPayBackStages())) {
				customerOrder.setLoanPayBackStages(search.getLoanPayBackStages());
			}else {
				result.setError("请输入还款期数");
				return result;
			}
			if(StringUtil.isEmpty(search.getIsMortgage())) {
				result.setError(ResultCode.CODE_STATE_4005, "请选择是否抵押车辆");
				return result;
			}
			customerOrder.setIsMortgage(search.getIsMortgage());
			if(customerOrder.getDownPayments().doubleValue() + customerOrder.getLoan().doubleValue() < customerOrder.getAmount().doubleValue()) {
				result.setError("首付加贷款小于订单应付，请核对数据");
				return result;
			}
		}else {
			result.setError("购车方案选择错误，请重新选择");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getFollowInformation())) {
			customerOrder.setFollowInformation(search.getFollowInformation());
		}
		if(StringUtil.isNotEmpty(search.getRemark())) {
			customerOrder.setRemarks(search.getRemark());
		}
		if(StringUtil.isEmpty(search.getCustomerOrderId())) {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("orgId", users.getOrgId());
			params.put("customerUsersId", customerOrder.getCustomerId());
			List<CustomerCustomerOrg> customerOrgs = customerCustomerOrgDao.select(params);
			//自动从跟踪用户列表剔除出去
			for(CustomerCustomerOrg customerOrg : customerOrgs) {
				customerOrg.setIsTrack(true);//标记为跟踪
				customerOrg.setSystemUserId(customerOrder.getSystemUserId());
				customerOrg.setSystemUserName(customerOrder.getSystemUserName());
				customerOrg.setSystemUserPhone(customerOrder.getSystemUserPhone());
				customerCustomerOrgDao.updateById(customerOrg);
			}
			if(customerOrderDao.insert(customerOrder)) {
				//记录订单跟踪
				CustomerOrderTrack orderTrack = new CustomerOrderTrack();
				orderTrack.setCreateDate(new Date());
				/**
				 * 1:已提交定车单
					3:已支付定金,待银行审批贷款
					5:待车辆到店
					7:车辆到店，可到店交付尾款
					9:已交付尾款，待车辆加装上牌
					11:加装／上牌完成，可到店提车
				 */
				orderTrack.setTrackContent("单号："+customerOrder.getCustomerOrderCode());
				orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
				orderTrack.setCustomerOrderState(customerOrder.getCustomerOrderState());
				orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
				customerOrderTrackDao.insert(orderTrack);
				result.setOK(ResultCode.CODE_STATE_200, "订单创建成功",takeMapOfOrder(customerOrder));
			}else {
				result.setError("订单数据保存失败，请联系管理员");
			}
		}else {
			if(customerOrderDao.updateById(customerOrder)) {
				result.setOK(ResultCode.CODE_STATE_200, "订单编辑成功",takeMapOfOrder(customerOrder));
			}else {
				result.setError("订单数据更新失败，请联系管理员");
			}
		}
		return result;
	}

	@Override
	public Result carsPadPastingList(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请先联系管理员");
			return result;
		}
		search.setCustomerOrderState(CustomerOrderState.padPasting);//设置查询上牌中的订单
//		Set<Integer> orderSet = new HashSet<Integer>();
//		if(search.getStartDate() != null || search.getEndDate() != null) {
//			Map<String,Object> payParams = new HashMap<String,Object>();
//			payParams.put("startDate", search.getStartDate());
//			payParams.put("endDate", search.getEndDate());
//			payParams.put("customerOrderState", CustomerOrderState.deliveryOfTheTail);
//			List<CustomerOrderInPay> inPays = customerOrderInPayDao.select(payParams);
//			for(CustomerOrderInPay inPay : inPays) {
//				orderSet.add(inPay.getCustomerOrderId());
//			}
//		}
		Map<String,Object> params = getCustomerParams(search,users);
//		if(orderSet != null && orderSet.size() >0) {
//			params.put("ids", orderSet);
//		}
		List<CustomerOrderVo> customerOrderVos = customerOrderDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = customerOrderDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();		
		//查询车辆
		Set<Integer> set = new HashSet<Integer>();
		for(CustomerOrderVo orderVo : customerOrderVos) {
			set.add(orderVo.getStockCarId());
		}
		if(set.size() <= 0) {
			allMap.put("page", search.getPage());
			allMap.put("total", total);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		//查询车辆
		Map<String,Object> carParams = new HashMap<String, Object>();
		carParams.put("isPutOut", true);
		carParams.put("ids", set);
		List<StockCarVo> stockCarVos = stockCarDao.select(carParams);
		if(stockCarVos == null) {
			allMap.put("page", search.getPage());
			allMap.put("total", total);
			allMap.put("rows", rows);
			allMap.put("list", maps);
			result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
			return result;
		}
		for(CustomerOrderVo orderVo : customerOrderVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("customerOrderCode",orderVo.getCustomerOrderCode());
			map.put("customerName", orderVo.getCustomerName());//
//			map.put("createDate", DateUtil.format(orderVo.getCreateDate()));//
			map.put("customerOrderId", orderVo.getCustomerOrderId());//
			map.put("interiorId", orderVo.getInteriorId());//
			map.put("interiorName", orderVo.getInteriorName());//
			map.put("carsName", orderVo.getCarsName());
			map.put("colourId", orderVo.getColourId());
			map.put("colourName", orderVo.getColourName());
			map.put("carsNameBefor", takeCareCarsName(orderVo.getCarsName(), true));
			map.put("carsNameAfter", takeCareCarsName(orderVo.getCarsName(), false));
			map.put("carsId", orderVo.getCarsId());
			map.put("isMortgage", orderVo.getIsMortgage());
			map.put("systemUserId", orderVo.getSystemUserId());
			map.put("systemUserName", orderVo.getSystemUserName());
			map.put("systemUserPhone", orderVo.getSystemUserPhone());
			map.put("estimateDate", orderVo.getEstimateDate() != null ? DateUtil.format(orderVo.getEstimateDate(),"yyyy-MM-dd"):"");
			for(StockCarVo carVo : stockCarVos) {
				if(carVo.getStockCarId().equals(orderVo.getStockCarId())) {
					map.put("frameNumber", carVo.getFrameNumber());
				}
			}
			if(orderVo.getInPay() != null) {
				map.put("payDate", DateUtil.format(orderVo.getInPay().getPayDate(),"yyyy-MM-dd"));
			}else {
				map.put("payDate", "");
			}
			if(orderVo.getOverTheLine() != null && orderVo.getOverTheLine()) {
				map.put("overTheLine", 1);
			}else {
				map.put("overTheLine", 0);
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
	public Result carsPadPastingDone(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(!GeneralConstant.CustomerOrderState.padPasting.equals(customerOrder.getCustomerOrderState())) {
			if(customerOrder.getCustomerOrderState() > GeneralConstant.CustomerOrderState.padPasting) {
				result.setError("订单已历过贴膜的历程，已不需要进行此操作");
				return result;
			}else {
				result.setError("订单尚未至贴膜历程，请耐心等待");
				return result;
			}
		}
		customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.deliver);//贴膜完成，待交付
		if(customerOrderDao.updateById(customerOrder)) {
			//记录订单跟踪
			CustomerOrderTrack orderTrack = new CustomerOrderTrack();
			orderTrack.setCreateDate(new Date());
			/**
			 * 1:已提交定车单
				3:已支付定金,待银行审批贷款
				5:待车辆到店
				7:车辆到店，可到店交付尾款
				9:已交付尾款，待车辆加装上牌
				11:加装／上牌完成，可到店提车
			 */
			orderTrack.setTrackContent("贴膜完成，等待交付车辆");
			orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
			orderTrack.setCustomerOrderState(customerOrder.getCustomerOrderState());
			orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
			customerOrderTrackDao.insert(orderTrack);
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("保存数据失败");
		}
		return result;
	}

	@Override
	public Result turnOverVehicle(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(!GeneralConstant.CustomerOrderState.deliver.equals(customerOrder.getCustomerOrderState())) {
			if(customerOrder.getCustomerOrderState() > GeneralConstant.CustomerOrderState.deliver) {
				result.setError("订单已历过交付车辆的历程，已不需要进行此操作");
				return result;
			}else {
				result.setError("订单尚未至交付车辆历程，请耐心等待");
				return result;
			}
		}
		if(StringUtil.isEmpty(search.getExtractCarImage())) {
			result.setError("请上传人车合照");
			return result;
		}
		customerOrder.setExtractCarImage(search.getExtractCarImage());
		customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.deliverTheLibrary);//交互车辆
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgId", users.getOrgId());
		params.put("customerUsersId", customerOrder.getCustomerId());
		List<CustomerCustomerOrg> customerOrgs = customerCustomerOrgDao.select(params);
		//自动从跟踪用户列表剔除出去
		for(CustomerCustomerOrg customerOrg : customerOrgs) {
			customerOrg.setIsTrack(false);
			customerCustomerOrgDao.updateById(customerOrg);
		}
		
		if(customerOrderDao.updateById(customerOrder)) {
			//记录订单跟踪
			CustomerOrderTrack orderTrack = new CustomerOrderTrack();
			orderTrack.setCreateDate(new Date());
			/**
			 * 1:已提交定车单
				3:已支付定金,待银行审批贷款
				5:待车辆到店
				7:车辆到店，可到店交付尾款
				9:已交付尾款，待车辆加装上牌
				11:加装／上牌完成，可到店提车
			 */
			orderTrack.setTrackContent("客户提车");
			orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
			orderTrack.setCustomerOrderState(customerOrder.getCustomerOrderState());
			orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
			customerOrderTrackDao.insert(orderTrack);
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("数据保存失败");
		}
		return result;
	}

	@Override
	public Result customerOrderAllInfo(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		Map<String,Object> map = takeMapOfOrder(customerOrder);
		CarsVo cars = carsDao.selectById(customerOrder.getCarsId());
		if(cars!=null) {
			map.put("guidingPrice", cars.getPrice());
		}else {
			map.put("guidingPrice", "0");
		}
		result.setOK(ResultCode.CODE_STATE_200, "", TakeCareMapDate.cutNullMap(map));
		return result;
	}

	@Override
	public Result bankApprovalPass(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(!GeneralConstant.CustomerOrderState.paymentOfADeposit.equals(customerOrder.getCustomerOrderState())) {
			if(customerOrder.getCustomerOrderState() > GeneralConstant.CustomerOrderState.paymentOfADeposit) {
				result.setError("订单已历过银行审核的历程，已不需要进行此操作");
				return result;
			}else {
				result.setError("订单尚未至银行审核历程，请耐心等待");
				return result;
			}
		}
		if(GeneralConstant.CustomerOrderState.fullPayment.equals(customerOrder.getPaymentWay())) {
			result.setError("该订单为全款支付订单，不需要通过银行审核操作");
			return result;
		}
		customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.loanAudit);//审核通过，等待出库
		if(customerOrderDao.updateById(customerOrder)) {
			//记录订单跟踪
			CustomerOrderTrack orderTrack = new CustomerOrderTrack();
			orderTrack.setCreateDate(new Date());
			/**
			 * 1:已提交定车单
				3:已支付定金,待银行审批贷款
				5:待车辆到店
				7:车辆到店，可到店交付尾款
				9:已交付尾款，待车辆加装上牌
				11:加装／上牌完成，可到店提车
			 */
			orderTrack.setTrackContent("银行审批已通过");
			orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
			orderTrack.setCustomerOrderState(customerOrder.getCustomerOrderState());
			orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
			customerOrderTrackDao.insert(orderTrack);
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("保存数据失败");
		}
		return result;
	}

	@Override
	public Result changeFullPayment(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(!GeneralConstant.CustomerOrderState.notPassThrough.equals(customerOrder.getCustomerOrderState()) 
				&& !GeneralConstant.CustomerOrderState.paymentOfADeposit.equals(customerOrder.getCustomerOrderState())) {
			if(customerOrder.getCustomerOrderState() > GeneralConstant.CustomerOrderState.notPassThrough) {
				result.setError("订单已历过修改分期为全款的历程，已不需要进行此操作");
				return result;
			}else {
				result.setError("订单尚未至银行审核不通过历程，请耐心等待");
				return result;
			}
		}
		if(GeneralConstant.CustomerOrderState.fullPayment.equals(customerOrder.getPaymentWay())) {
			result.setError("该订单为全款支付订单，不需要再更改");
			return result;
		}
		customerOrder.setPaymentWay(GeneralConstant.CustomerOrderState.fullPayment);//审核通过，等待出库
		customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.loanAudit);//审核通过，等待出库
//			return customerOrderDao.updateByIdAndResultIT(customerOrder, result);
		if(customerOrderDao.updateById(customerOrder)) {
			//记录订单跟踪
			CustomerOrderTrack orderTrack = new CustomerOrderTrack();
			orderTrack.setCreateDate(new Date());
			/**
			 * 1:已提交定车单
				3:已支付定金,待银行审批贷款
				5:待车辆到店
				7:车辆到店，可到店交付尾款
				9:已交付尾款，待车辆加装上牌
				11:加装／上牌完成，可到店提车
			 */
			orderTrack.setTrackContent("已把分期改成全款");
			orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
			orderTrack.setCustomerOrderState(customerOrder.getCustomerOrderState());
			orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
			customerOrderTrackDao.insert(orderTrack);
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("数据保存失败");
		}
//			result.setOK(ResultCode.CODE_STATE_200, "");
		return result;
	}

	@Override
	public Result overTheLine(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
//		if(!GeneralConstant.CustomerOrderState.retrofitting.equals(customerOrder.getCustomerOrderState())) {
//			if(customerOrder.getCustomerOrderState() > GeneralConstant.CustomerOrderState.retrofitting) {
//				result.setError("订单已历过上牌完成的历程，已不需要进行此操作");
//				return result;
//			}else {
//				result.setError("订单尚未至上牌历程，请耐心等待");
//				return result;
//			}
//		}
		customerOrder.setOverTheLine(true);
		if(customerOrderDao.updateById(customerOrder)) {
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("保存数据失败");
		}
		return result;
	}

//	@Override
//	public Result customerOrderPrint(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
//		CustomerOrderVo customerOrder = customerOrderDao.selectByIdJoin(search.getCustomerOrderId());
//		if(customerOrder == null) {
//			result.setError("你选择的订单不存在或者已删除");
//			return result;
//		}
//		Map<String,Object> map = takeMapOfOrder(customerOrder);
//		map.put("userAddress", customerOrder.getCustomerUsersVo().getAddress());
//		map.put("orgAddress", customerOrder.getOrganization().getProvinceName()+customerOrder.getOrganization().getCityName() + customerOrder.getOrganization().getAreaName() + customerOrder.getOrganization().getAddress());
//		map.put("shortName", customerOrder.getOrganization().getShortName());
//		map.put("telePhone", customerOrder.getOrganization().getTelePhone());
//		result.setOK(ResultCode.CODE_STATE_200, "", TakeCareMapDate.cutNullMap(map));
//		return result;
//	}
	
	@Override
	public Result customerOrderPrint(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrderVo customerOrder = customerOrderDao.selectByIdJoin(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		Map<String,Object> map = takeMapOfOrder(customerOrder);
		if(customerOrder.getCustomerUsersVo() != null) {
			map.put("address", customerOrder.getCustomerUsersVo().getAddress());
		}else {
			map.put("address", "");
		}
		if(customerOrder.getCarsVo() != null) {
			map.put("guidingPrice", customerOrder.getCarsVo().getPrice());
		}else {
			map.put("guidingPrice", "");
		}
		if(customerOrder.getOrganization() != null) {
			map.put("orgAddress", customerOrder.getOrganization().getProvinceName()+customerOrder.getOrganization().getCityName() + customerOrder.getOrganization().getAreaName() + customerOrder.getOrganization().getAddress());
			map.put("shortName", customerOrder.getOrganization().getShortName());
			map.put("telePhone", customerOrder.getOrganization().getTelePhone());
		}
		result.setOK(ResultCode.CODE_STATE_200, "", TakeCareMapDate.cutNullMap(map));
		return result;
	}

	@Override
	public Result cancelOrder(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(customerOrder.getIsDelete() != null && customerOrder.getIsDelete()) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(!GeneralConstant.CustomerOrderState.start.equals(customerOrder.getCustomerOrderState()) || !GeneralConstant.CustomerOrderState.initial.equals(customerOrder.getCustomerOrderState())) {
			if(customerOrder.getCustomerOrderState() > GeneralConstant.CustomerOrderState.start) {
				result.setError("订单已有交付定金等后续操作，已不能进行取消操作....");
				return result;
			}
		}
		customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.initial);//审核通过，等待出库
		customerOrder.setIsDelete(true);//取消的同时执行删除操作
		if(customerOrderDao.updateById(customerOrder)) {
			//记录订单跟踪
			CustomerOrderTrack orderTrack = new CustomerOrderTrack();
			orderTrack.setCreateDate(new Date());
			/**
			 * 1:已提交定车单
				3:已支付定金,待银行审批贷款
				5:待车辆到店
				7:车辆到店，可到店交付尾款
				9:已交付尾款，待车辆加装上牌
				11:加装／上牌完成，可到店提车
			 */
			orderTrack.setTrackContent("订单已被取消");
			orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
			orderTrack.setCustomerOrderState(customerOrder.getCustomerOrderState());
			orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
			customerOrderTrackDao.insert(orderTrack);
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("保存数据失败");
		}
		return result;
	}

	@Override
	public Result orderVisit(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(customerOrder.getIsDelete() != null && customerOrder.getIsDelete()) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(!GeneralConstant.CustomerOrderState.orderBeenFinish.equals(customerOrder.getCustomerOrderState())) {
			result.setError("订单尚未交车，暂不能回访");
			return result;
		}
		if(StringUtil.isEmpty(search.getVisitContent())) {
			result.setError("回访记录不能为空");
			return result;
		}
		customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.orderVisit);//回访完成
		if(customerOrderDao.updateById(customerOrder)) {
			//记录订单跟踪
			CustomerOrderTrack orderTrack = new CustomerOrderTrack();
			orderTrack.setCreateDate(new Date());
			/**
			 * 1:已提交定车单
				3:已支付定金,待银行审批贷款
				5:待车辆到店
				7:车辆到店，可到店交付尾款
				9:已交付尾款，待车辆加装上牌
				11:加装／上牌完成，可到店提车
			 */
			orderTrack.setTrackContent(search.getVisitContent());
			orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
			orderTrack.setCustomerOrderState(customerOrder.getCustomerOrderState());
			orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
			customerOrderTrackDao.insert(orderTrack);
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("保存数据失败");
		}
		return result;
	}

	@Override
	@Transactional
	public Result editCustomerOrderShop(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = null;
		ShopAdvanceOrderVo advanceOrder = null;
		if (StringUtil.isEmpty(users.getOrgId())) {
			result.setError("你的公司信息不明确，不能执行此操作，请联系管理员");
			return result;
		}
		OrganizationVo organizationVo = organizationDao.selectById(users.getOrgId());
		if (organizationVo == null) {
			result.setError("你的公司信息不明确，不能执行此操作，请联系管理员");
			return result;
		}
		if (!organizationVo.getOrgLevel().equals(GeneralConstant.Org.Level_3)) {
			result.setError("你不是门店级别的用户，不能进行此操作");
			return result;
		}
		ShopAdvanceOrderPayment orderPayment = null;
		if (StringUtil.isNotEmpty(search.getAdvanceOrderId())) {
			advanceOrder = shopAdvanceOrderDao.selectByIdJoin(search.getAdvanceOrderId());
			if (advanceOrder.getOverDelete()) {
				result.setError("抱歉，该预约已取消");
				return result;
			}
			if (advanceOrder.getOverCatch()) {
				result.setError("该预购单已生成订单，请勿重复操作");
				return result;
			}
			advanceOrder.setOverCatch(true);
			if (advanceOrder.getOverPay()) {
				orderPayment = advanceOrder.getOrderPaymentVo();
			}
			// 把商城C端用户转化成系统客户
			CustomerUsersSearch customerUsersSearch = new CustomerUsersSearch();
			customerUsersSearch.setCustomerUsersName(advanceOrder.getRealName());
			customerUsersSearch.setPhoneNumber(advanceOrder.getPhoneNumber());
			customerUsersSearch.setAdvanceOrderId(search.getAdvanceOrderId());
			customerUsersSearch.setCarsId(advanceOrder.getOrderInfoVos().get(0).getCarsId());
			employeeCustomerService.addCustomerUsersr(customerUsersSearch, result, users);
			if (!result.isSuccess()) {
				return result;
			}
			search.setCustomerUsersId(Integer.parseInt(result.getData() + ""));
		}

		if (StringUtil.isEmpty(search.getCustomerOrderId())) {
			// 查询该用户是否已存在订单，如果已存在则不让其再开一单（系统1.0不支持多单）
//			customerOrder = customerOrderDao.selectByUsersId(search.getCustomerUsersId());
//			if (customerOrder != null) {
//				result.setError("该用户已经存在订单，请勿再下单，如有疑问请联系管理员");
//				return result;
//			}
			customerOrder = new CustomerOrder();
			customerOrder.setCreateDate(new Date());
			if (StringUtil.isEmpty(search.getCustomerUsersId())) {
				result.setError("你选择的用户信息错误，请刷新重试或者联系管理员");
				return result;
			}

			customerOrder.setCustomerOrderCode(customerOrderDao.getCode(customerOrder.getCustomerPhoneNumber()));
			customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.start);
			customerOrder.setOverTheLine(false);

			customerOrder.setOrgId(organizationVo.getOrgId());
			customerOrder.setOrgName(organizationVo.getShortName());

			CustomerUsers customerUsers = customerUsersDao.selectById(search.getCustomerUsersId());
			if (customerUsers == null) {
				result.setError("你选择的用户信息错误，请刷新重试或者联系管理员");
				return result;
			}
			customerOrder.setCustomerId(customerUsers.getCustomerUsersId());
			customerOrder.setCustomerName(customerUsers.getCustomerUsersName());
			customerOrder.setCustomerPhoneNumber(customerUsers.getPhoneNumber());

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("orgId", organizationVo.getOrgId());
			params.put("customerUsersId", customerUsers.getCustomerUsersId());
			List<CustomerCustomerOrg> customerOrgs = customerCustomerOrgDao.select(params);
			if (customerOrgs == null || customerOrgs.size() <= 0) {
				result.setError("门店&客户信息错误，请先新建立用户信息");
				return result;
			}
		} else {
			customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
			if (customerOrder.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.orderBeenFinish)) {
				result.setError("订单已交车，订单信息已不能再进行修改");
				return result;
			}
		}
		if (StringUtil.isEmpty(search.getCustomerUserCard())) {
			result.setError("请输入用户身份证号");
			return result;
		}
		IdcardValidator validator = new IdcardValidator();
		if (validator.isValidatedAllIdcard(search.getCustomerUserCard())) {
			customerOrder.setCustomerUserCard(search.getCustomerUserCard());
		} else {
			result.setError(ResultCode.CODE_STATE_4005, "身份证号码不正确,请核对");
			return result;
		}
		if (StringUtil.isNotEmpty(search.getSystemUserId())) {
			SystemUsers systemUsers = systemUsersDao.selectByIdInUse(search.getSystemUserId());
			if (systemUsers == null) {
				result.setError("你选择的销售不存在或者已删除");
				return result;
			}
			customerOrder.setSystemUserId(systemUsers.getUsersId());
			customerOrder.setSystemUserName(systemUsers.getRealName());
			customerOrder.setSystemUserPhone(systemUsers.getPhoneNumber());

			if (StringUtil.isNotEmpty(search.getCustomerOrderId())) {// 如果再次编辑订单时，涉及到修改销售
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("orgId", users.getOrgId());
				params.put("customerUsersId", customerOrder.getCustomerId());
				List<CustomerCustomerOrg> customerOrgs = customerCustomerOrgDao.select(params);
				// 自动从跟踪用户列表剔除出去
				for (CustomerCustomerOrg customerOrg : customerOrgs) {
					customerOrg.setIsTrack(true);// 标记为跟踪
					customerOrg.setSystemUserId(customerOrder.getSystemUserId());
					customerOrg.setSystemUserName(customerOrder.getSystemUserName());
					customerOrg.setSystemUserPhone(customerOrder.getSystemUserPhone());
					customerCustomerOrgDao.updateById(customerOrg);
				}
			}
		} else {
			result.setError("请选择销售人员");
			return result;
		}
		if (StringUtil.isEmpty(search.getBrandId())) {
			result.setError("请选择品牌");
			return result;
		}
		customerOrder.setBrandId(search.getBrandId());
		if (StringUtil.isEmpty(search.getFamilyId())) {
			result.setError("请选择车系");
			return result;
		}
		customerOrder.setFamilyId(search.getFamilyId());
		if (StringUtil.isEmpty(search.getCarsId())) {
			result.setError("请选择车型");
			return result;
		}
		if (customerOrder.getCustomerOrderState().intValue() >= GeneralConstant.CustomerOrderState.deliveryOfTheTail
				&& !search.getCarsId().equals(customerOrder.getCarsId())) {
			result.setError("订单车辆已出库，已不能修改订单车辆");
			return result;
		}
		CarsVo cars = carsDao.selectById(search.getCarsId());
		if (cars == null) {
			result.setError(ResultCode.CODE_STATE_4005, "所选车型不存在，请重新选择");
			return result;
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(cars.getBrandName()).append(" ");
		buffer.append(cars.getFamilyName()).append(" ");
		buffer.append(cars.getYearPattern()).append("款").append(" ");
		buffer.append(cars.getPl()).append(" ");
		buffer.append(cars.getGearboxName()).append(cars.getStyleName());
		customerOrder.setCarsId(search.getCarsId());
		customerOrder.setCarsName(buffer.toString());
		customerOrder.setCarsIndexImage(cars.getIndexImage());
		if (StringUtil.isEmpty(search.getColourId())) {
			result.setError("请选择车身颜色");
			return result;
		}
		CarColourVo colourVo = carColourDao.selectById(search.getColourId());
		if (colourVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你所选择的车身颜色不存在");
			return result;
		}
		customerOrder.setColourName(colourVo.getCarColourName());
		customerOrder.setColourId(search.getColourId());
		if (StringUtil.isEmpty(search.getInteriorId())) {
			result.setError("请选择内饰");
			return result;
		}
		CarInteriorVo interiorVo = carInteriorDao.selectById(search.getInteriorId());
		if (interiorVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你所选择的车辆内饰不存在");
			return result;
		}
		customerOrder.setInteriorName(interiorVo.getInteriorName());
		customerOrder.setInteriorId(search.getInteriorId());
		if (StringUtil.isEmpty(search.getCarUnitPrice())) {
			result.setError("请输入最终成交价");
			return result;
		} else {
			customerOrder.setCarUnitPrice(new BigDecimal(search.getCarUnitPrice()));
		}
		// if(search.getIsTakeLicensePlate() != null && search.getIsTakeLicensePlate())
		// {
		// customerOrder.setIsTakeLicensePlate(true);
		Double amount = search.getCarUnitPrice();
		if (StringUtil.isNotEmpty(search.getLicensePlatePriace())) {
			customerOrder.setIsTakeLicensePlate(false);
			customerOrder.setLicensePlatePriace(new BigDecimal(search.getLicensePlatePriace()));
			amount += search.getLicensePlatePriace();
		}
		// if(StringUtil.isNotEmpty(search.getDepositPrice())) {
		// result.setError("订单定金已支付，定金金额已不能进行修改");
		// return result;
		// }else
		if (StringUtil.isNotEmpty(search.getDepositPrice())) {// 定金
//			if (advanceOrder != null && advanceOrder.getOverPay()) {
//				result.setError("订单定金已支付，定金金额已不能进行修改");
//				return result;
//			} else if (!customerOrder.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.start)
//					&& customerOrder.getDepositPrice().doubleValue() != search.getDepositPrice()) {
//				result.setError("订单定金已支付，定金金额已不能进行修改");
//				return result;
//			}
			if(StringUtil.isNotEmpty(customerOrder.getCustomerOrderId()) && !customerOrder.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.start)
					&& customerOrder.getDepositPrice().doubleValue() != search.getDepositPrice()) {
				result.setError("订单定金已支付，定金金额已不能进行修改");
				return result;
			}
			customerOrder.setDepositPrice(new BigDecimal(search.getDepositPrice()));
		} else {
			result.setError("请输入定金");
			return result;
		}
		if (StringUtil.isNotEmpty(search.getInsurancePriace())) {
			customerOrder.setIsInsurance(false);
			customerOrder.setInsurancePriace(new BigDecimal(search.getInsurancePriace()));
			amount += search.getInsurancePriace();
		}
		if (StringUtil.isNotEmpty(search.getPurchaseTaxPriace())) {
			customerOrder.setIsPurchaseTax(true);
			customerOrder.setPurchaseTaxPriace(new BigDecimal(search.getPurchaseTaxPriace()));
			amount += search.getPurchaseTaxPriace();
		}
		if (StringUtil.isNotEmpty(search.getBoutiqueInformation())) {
			if (StringUtil.isEmpty(search.getBoutiquePriace())) {
				result.setError("请选择添加加装的精品费用");
				return result;
			}
			customerOrder.setBoutiqueInformation(search.getBoutiqueInformation());
			customerOrder.setBoutiquePriace(new BigDecimal(search.getBoutiquePriace()));
			amount += search.getBoutiquePriace();
		}
		// else {
		// if(StringUtil.isNotEmpty(search.getBoutiqueInformation())) {
		// result.setError("请选择添加加装的精品费用");
		// return result;
		// }
		// }

		if (StringUtil.isNotEmpty(search.getMortgagePriace())) {
			customerOrder.setMortgagePriace(new BigDecimal(search.getMortgagePriace()));
			amount += search.getMortgagePriace();
		}
		if (StringUtil.isNotEmpty(search.getVehicleAndVesselTax())) {// 车船税
			customerOrder.setVehicleAndVesselTax(new BigDecimal(search.getVehicleAndVesselTax()));
			amount += search.getVehicleAndVesselTax();
		}
		customerOrder.setAmount(new BigDecimal(amount));// 把各项价格叠加，确定订单价格
		if (StringUtil.isEmpty(search.getPaymentWay())) {
			result.setError("请选择购车方案");
			return result;
		}
		if (search.getPaymentWay().equals(GeneralConstant.CustomerOrderState.fullPayment)) {// 全款
			if (customerOrder.getCustomerOrderState().intValue() >= GeneralConstant.CustomerOrderState.paymentOfADeposit
					&& !search.getPaymentWay().equals(customerOrder.getPaymentWay())) {
				result.setError("订单已确认，已不能由贷款改为全款");
				return result;
			}
			customerOrder.setPaymentWay(GeneralConstant.CustomerOrderState.fullPayment);
		} else if (search.getPaymentWay().equals(GeneralConstant.CustomerOrderState.byStages)) {
			if (customerOrder.getCustomerOrderState().intValue() >= GeneralConstant.CustomerOrderState.loanAudit
					&& !search.getPaymentWay().equals(customerOrder.getPaymentWay())) {
				result.setError("订单已确认，已不能由贷款改为全款");
				return result;
			}
			// Set<Integer> set = new HashSet<Integer>();
			// set.add(GeneralConstant.LoanBank.CHERY);
			if (customerOrder.getCustomerOrderState().intValue() >= GeneralConstant.CustomerOrderState.loanAudit
					&& !search.getLoanBank().equals(customerOrder.getLoanBank())) {
				// if(customerOrder.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.notPassThrough))
				// {
				//
				// }else {
				// result.setError("订单已确认，已不能修改贷款银行");
				// return result;
				// }
				result.setError("订单已确认，已不能修改贷款银行");
				return result;
			}
			/**
			 * 在农行审核不通过的情况下修改银行
			 */
			if (GeneralConstant.CustomerOrderState.notPassThrough.equals(customerOrder.getCustomerOrderState())
					&& !search.getLoanBank().equals(customerOrder.getLoanBank())) {
				customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.paymentOfADeposit);
				customerOrder.setAuditStatus(0);
				// customerOrder.setAuditTime();
			}
			if (StringUtil.isNotEmpty(search.getLoanBank())) {
				switch (search.getLoanBank()) {
				case GeneralConstant.LoanBank.CHERY:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.CHERY);
					break;
				case GeneralConstant.LoanBank.GONGSHANG:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.GONGSHANG);
					break;
				case GeneralConstant.LoanBank.HESHANZHUJIANG:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.HESHANZHUJIANG);
					break;
				case GeneralConstant.LoanBank.JIANSHE:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.JIANSHE);
					break;
				case GeneralConstant.LoanBank.NONGYE:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.NONGYE);
					break;
				case GeneralConstant.LoanBank.RUIFUDE:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.RUIFUDE);
					break;
				case GeneralConstant.LoanBank.GUNAGZHOU:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.GUNAGZHOU);
					break;
				case GeneralConstant.LoanBank.HESHANNONGCUN:
					customerOrder.setLoanBank(GeneralConstant.LoanBank.HESHANNONGCUN);
					break;
				default:
					result.setError("你选择的贷款银行错误，请重新选择");
					return result;
				}
			} else {
				result.setError("请选择贷款银行");
				return result;
			}
			customerOrder.setPaymentWay(GeneralConstant.CustomerOrderState.byStages);
			if (StringUtil.isNotEmpty(search.getDownPayments())) {
				customerOrder.setDownPayments(new BigDecimal(search.getDownPayments()));
			} else {
				result.setError("请输入首付金额");
				return result;
			}
			if (StringUtil.isNotEmpty(search.getLoan())) {
				customerOrder.setLoan(new BigDecimal(search.getLoan()));
			} else {
				result.setError("请输入贷款金额");
				return result;
			}
			if (StringUtil.isNotEmpty(search.getLoanPayBackStages())) {
				customerOrder.setLoanPayBackStages(search.getLoanPayBackStages());
			} else {
				result.setError("请输入还款期数");
				return result;
			}
			if (StringUtil.isEmpty(search.getIsMortgage())) {
				result.setError(ResultCode.CODE_STATE_4005, "请选择是否抵押车辆");
				return result;
			}
			customerOrder.setIsMortgage(search.getIsMortgage());
			if (customerOrder.getDownPayments().doubleValue() + customerOrder.getLoan().doubleValue() < customerOrder
					.getAmount().doubleValue()) {
				result.setError("首付加贷款小于订单应付，请核对数据");
				return result;
			}
		} else {
			result.setError("购车方案选择错误，请重新选择");
			return result;
		}
		if (StringUtil.isNotEmpty(search.getFollowInformation())) {
			customerOrder.setFollowInformation(search.getFollowInformation());
		}
		if (StringUtil.isNotEmpty(search.getRemark())) {
			customerOrder.setRemarks(search.getRemark());
		}
		try {
			if (StringUtil.isEmpty(search.getCustomerOrderId())) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("orgId", users.getOrgId());
				params.put("customerUsersId", customerOrder.getCustomerId());
				List<CustomerCustomerOrg> customerOrgs = customerCustomerOrgDao.select(params);
				// 自动从跟踪用户列表剔除出去
				for (CustomerCustomerOrg customerOrg : customerOrgs) {
					customerOrg.setIsTrack(true);// 标记为跟踪
					customerOrg.setSystemUserId(customerOrder.getSystemUserId());
					customerOrg.setSystemUserName(customerOrder.getSystemUserName());
					customerOrg.setSystemUserPhone(customerOrder.getSystemUserPhone());
					customerCustomerOrgDao.updateById(customerOrg);
				}

				if (advanceOrder != null && advanceOrder.getOverPay()) {
					customerOrder.setDepositPrice(advanceOrder.getDepositPrice());
					if (customerOrder.getPaymentWay().equals(GeneralConstant.CustomerOrderState.fullPayment)) {// 如果是全款
						customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.loanAudit);
					} else {
						customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.paymentOfADeposit);
					}
				}

				if (customerOrderDao.insert(customerOrder)) {
					// 记录订单跟踪
					CustomerOrderTrack orderTrack = new CustomerOrderTrack();
					orderTrack.setCreateDate(new Date());
					/**
					 * 1:已提交定车单 3:已支付定金,待银行审批贷款 5:待车辆到店 7:车辆到店，可到店交付尾款 9:已交付尾款，待车辆加装上牌
					 * 11:加装／上牌完成，可到店提车
					 */
					orderTrack.setTrackContent("单号：" + customerOrder.getCustomerOrderCode());
					orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
					orderTrack.setCustomerOrderState(customerOrder.getCustomerOrderState());// 强制搞一个初始化状态，不然就跟下面的支付搞混了（如果已支付的话）
																							// 失效
					orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
					if (!customerOrderTrackDao.insert(orderTrack)) {
						result.setError("由商城预订单生成订单数据，创建订单新增跟踪失败，请联系管理员");
						throw new Exception("由商城预订单生成订单数据，创建订单新增跟踪失败，手动抛错回滚");
					} else {
						result.setOK(ResultCode.CODE_STATE_200, "订单创建成功", takeMapOfOrder(customerOrder));
					}

					if (orderPayment != null) {// 预付单已经支付
						CustomerOrderInPay customerOrderInPay = new CustomerOrderInPay();
						customerOrderInPay.setAmount(orderPayment.getAmount());
						customerOrderInPay.setCreateDate(orderPayment.getCreateDate());
						customerOrderInPay.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
						customerOrderInPay.setCustomerOrderId(customerOrder.getCustomerOrderId());
						customerOrderInPay.setCustomerOrderState(customerOrder.getCustomerOrderState());
						customerOrderInPay.setOrderInPayCode(customerOrderInPayDao.getPayCode());
						customerOrderInPay.setRemarks(orderPayment.getRemarks());
						customerOrderInPay.setPayMethod(orderPayment.getPayMethod());
						customerOrderInPay.setPaymentRecordBillId(orderPayment.getPaymentRecordBillId());
						customerOrderInPay.setPayDate(orderPayment.getPayDate());
						customerOrderInPay.setOrderInPayState(orderPayment.getOrderInPayState());
						customerOrderInPay.setRemarks("商城预订单生成订单");
						if (!customerOrderInPayDao.insert(customerOrderInPay)) {
							result.setError("由商城预订单生成订单数据，创建订单支付单失败，请联系管理员");
							throw new Exception("由商城预订单生成订单数据，创建订单支付单失败，手动抛错回滚");
						}
						// 记录订单跟踪
						CustomerOrderTrack orderTrack_pay = new CustomerOrderTrack();
						orderTrack.setCreateDate(new Date());
						/**
						 * 1:已提交定车单 3:已支付定金,待银行审批贷款 5:待车辆到店 7:车辆到店，可到店交付尾款 9:已交付尾款，待车辆加装上牌
						 * 11:加装／上牌完成，可到店提车
						 */
						orderTrack_pay.setTrackContent("支付定金：" + customerOrderInPay.getAmount().doubleValue());
						orderTrack_pay.setCustomerOrderId(customerOrder.getCustomerOrderId());
						orderTrack_pay.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
						if (customerOrder.getPaymentWay().equals(GeneralConstant.CustomerOrderState.fullPayment)) {// 如果是全款
							// 限制支付不能超过最终
							// 查询尾款，贷款用首付，全款用最终成交价
							Double amount_ = customerOrder.getAmount().doubleValue();
							if (customerOrderInPay.getAmount().doubleValue() >= amount_) {
								result.setError("定金不能大于等于最终成交价，最终成交价：" + amount_ + "元");
								return result;
							}
							orderTrack_pay.setCreateDate(new Date());
							/**
							 * 1:已提交定车单 3:已支付定金,待银行审批贷款 5:待车辆到店 7:车辆到店，可到店交付尾款 9:已交付尾款，待车辆加装上牌
							 * 11:加装／上牌完成，可到店提车
							 */
							orderTrack_pay.setTrackContent("全款购车，不需要审核");
							orderTrack_pay.setCustomerOrderId(customerOrder.getCustomerOrderId());
							orderTrack_pay.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
							orderTrack_pay.setCustomerOrderState(GeneralConstant.CustomerOrderState.loanAudit);
							orderTrack_pay.setCustomerOrderState(GeneralConstant.CustomerOrderState.paymentOfADeposit);
							customerOrderTrackDao.insert(orderTrack_pay);
						} else {
							Double amount_ = customerOrder.getDownPayments().doubleValue();
							if (customerOrderInPay.getAmount().doubleValue() >= amount_) {
								result.setError("定金不能大于等于首付，首付：" + amount_ + "元");
								return result;
							}
							orderTrack_pay.setCustomerOrderState(customerOrder.getCustomerOrderState());
							orderTrack_pay.setCreateDate(new Date());
							/**
							 * 1:已提交定车单 3:已支付定金,待银行审批贷款 5:待车辆到店 7:车辆到店，可到店交付尾款 9:已交付尾款，待车辆加装上牌
							 * 11:加装／上牌完成，可到店提车
							 */
							orderTrack_pay.setTrackContent("已支付定金，等待银行审核");
							orderTrack_pay.setCustomerOrderId(customerOrder.getCustomerOrderId());
							orderTrack_pay.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
							orderTrack_pay.setCustomerOrderState(GeneralConstant.CustomerOrderState.loanAudit);
							orderTrack_pay.setCustomerOrderState(GeneralConstant.CustomerOrderState.paymentOfADeposit);
							customerOrderTrackDao.insert(orderTrack_pay);
						}
					}

					if (advanceOrder != null) {
						advanceOrder.setRealOrderId(customerOrder.getCustomerOrderId());
						advanceOrder.setUserType(1);// 普通用户
						advanceOrder.setOverCatch(true);
						advanceOrder.setSystemUserId(users.getUsersId());//记录处理人
						advanceOrder.setSystemUserName(users.getUserName());
						if (!shopAdvanceOrderDao.updateById(advanceOrder)) {
							result.setError("由商城预订单生成订单数据，更新预订单时失败，请联系管理员");
							throw new Exception("由商城预订单生成订单数据，更新预订单时失败，手动抛错回滚");
						}
						result.setOK(ResultCode.CODE_STATE_200, "订单创建成功", takeMapOfOrder(customerOrder));
					}
				}
			} else {
				if (customerOrderDao.updateById(customerOrder)) {
					result.setOK(ResultCode.CODE_STATE_200, "订单编辑成功", takeMapOfOrder(customerOrder));
				} else {
					result.setError("订单数据更新失败，请联系管理员");
					throw new Exception("订单编辑失败，手动抛错回滚");
				}
			}
			// else {
			// result.setError("由商城预订单生成订单数据保存失败，请联系管理员");
			// throw new Exception("由商城预订单生成订单数据保存失败，手动抛错回滚");
			// }
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("由商城预订单生成订单或者订单编辑数据保存失败，手动抛错回滚");
		}
		return result;
	}
	
}
