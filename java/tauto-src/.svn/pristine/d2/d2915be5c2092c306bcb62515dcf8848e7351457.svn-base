package main.com.weixinApp.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cloopen.rest.sdk.utils.DateUtil;

import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.po.Cars;
import main.com.car.dao.search.CarsSearch;
import main.com.customer.dao.dao.CustomerCustomerOrgDao;
import main.com.customer.dao.dao.CustomerOrderDao;
import main.com.customer.dao.dao.CustomerOrderTrackDao;
import main.com.customer.dao.dao.CustomerUsersDao;
import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.po.CustomerUsers;
import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.customer.dao.vo.CustomerCustomerOrgVo;
import main.com.customer.dao.vo.CustomerOrderTrackVo;
import main.com.customer.dao.vo.CustomerOrderVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.vo.StockCarVo;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.NumberFormat;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.weixinApp.service.AppOderService;

@Service
public class AppOderServiceImpl extends BaseServiceImpl<CustomerOrder> implements AppOderService{

	@Autowired
	CustomerOrderDao customerOrderDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	CustomerCustomerOrgDao customerCustomerOrgDao;
	
	@Autowired
	CustomerOrderTrackDao customerOrderTrackDao;
	
	@Autowired
	StockCarDao stockCarDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	CustomerUsersDao customerUsersDao;
	
	@Override
	protected BaseDao<CustomerOrder> getBaseDao() {
		return customerOrderDao;
	}

	@Override
	public Result orderInfo(CustomerOrderSearch search, Result result) throws Exception {
		if(StringUtil.isEmpty(search.getUsersTmpl().getCustomerUsersId())) {
			result.setOK(ResultCode.CODE_STATE_200, "");
			return result;
		}
		//根据用户查询订单
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("customerId", search.getUsersTmpl().getCustomerUsersId());
		params.put("sortField", true);
		List<CustomerOrderVo> orderVos = customerOrderDao.select(params);
		Organization organization = null;
		Map<String,Object> map = new HashMap<String, Object>();
		if(orderVos == null || orderVos.size() <= 0) {
			params.put("isAppointment", true);
			List<CustomerCustomerOrgVo> customerOrgVos = customerCustomerOrgDao.select(params);
			if(customerOrgVos == null || customerOrgVos.size() <= 0) {
				result.setOK(ResultCode.CODE_STATE_200, "");
				return result;
			}
			CustomerCustomerOrgVo customerOrgVo = customerOrgVos.get(0);
			organization = organizationDao.selectById(customerOrgVo.getOrgId());
			Cars cars = carsDao.selectById(customerOrgVo.getIntentionCarId());
			map.put("carsId", customerOrgVo.getIntentionCarId());
			map.put("carsName", customerOrgVo.getIntentionCarInfo());
			map.put("address", organization.getProvinceName() + organization.getCityName() + organization.getAreaName() + organization.getAddress());
			map.put("shortName", organization.getShortName());
			map.put("latitude", organization.getLatitude());
			map.put("longitude", organization.getLongitude());
			map.put("telPhone", organization.getTelePhone());
			map.put("orgImage", organization.getImageUrl());
			map.put("indexImage", cars.getIndexImage());
			map.put("customerOrderState", "");
			map.put("systemUserId", customerOrgVo.getSystemUserId());
			map.put("systemUserName", customerOrgVo.getSystemUserName());
			map.put("systemUserPhone", customerOrgVo.getSystemUserPhone());
			map.put("customerOrderId", "");
			map.put("isAppointment", 1);
			result.setOK(ResultCode.CODE_STATE_200, "",map);
			return result;
		}
		CustomerOrderVo orderVo = getTheOrder(orderVos);
		organization = organizationDao.selectById(orderVo.getOrgId());		
		map.put("carsId", orderVo.getCarsId());
		map.put("carsName", orderVo.getCarsName());
		map.put("address", organization.getProvinceName() + organization.getCityName() + organization.getAreaName() + organization.getAddress());
		map.put("shortName", organization.getShortName());
		map.put("latitude", organization.getLatitude());
		map.put("longitude", organization.getLongitude());
		map.put("telPhone", organization.getTelePhone());
		map.put("orgImage", organization.getImageUrl());
		map.put("customerOrderState", orderVo.getCustomerOrderState());
		map.put("systemUserId", orderVo.getSystemUserId());
		SystemUsers systemUsers = systemUsersDao.selectById(orderVo.getSystemUserId());
		map.put("systemUserName", orderVo.getSystemUserName());
		map.put("systemUserPhone", orderVo.getSystemUserPhone());
		map.put("systemImage", systemUsers.getHeadPortrait());
		map.put("customerOrderId", orderVo.getCustomerOrderId());
		Cars cars = carsDao.selectById(orderVo.getCarsId());
		map.put("indexImage", cars.getIndexImage());
		map.put("isAppointment", 0);
		result.setOK(ResultCode.CODE_STATE_200, "",map);
		return result;
	}

	public CustomerOrderVo getTheOrder(List<CustomerOrderVo> orderVos) {
		/**
		 * 客户预约成功调整预约成功页面
		客户落定成功后进入小程序显示订单跟踪页面
			- 订单状态
				开单
				落定
				银行贷款审批(全款跳过)
				车辆出库
				完款
				加装／上牌
			- 显示时间最后的购车订单对应状态
			- 同时在不同地方买车的土豪不考虑
			- 订单跟踪状态下方显示该订单对应销售人员
		客户交付车辆成功后进入小程序显示订单完成界面
		 */
		if(orderVos.size() == 1) {
			return orderVos.get(0);
		}
		for(CustomerOrderVo orderVo : orderVos) {
			if(orderVo.getCustomerOrderState() < GeneralConstant.CustomerOrderState.deliver) {
				return orderVo;
			}
		}
		//如果木有正在进行的订单，则返回最后一个订单
		return orderVos.get(0);
	}

	@Override
	public Result orderTrack(CustomerOrderSearch search, Result result) throws Exception {
		//查询订单
		CustomerOrderVo orderVo = customerOrderDao.selectById(search.getCustomerOrderId());
		if(orderVo == null ) {
			result.setErrorKefu();
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("customerOrderId", orderVo.getCustomerOrderId());
		params.put("sortField", true);
		List<CustomerOrderTrackVo> customerOrderTrackVos = customerOrderTrackDao.select(params);
		String image = "";
		if(StringUtil.isNotEmpty(orderVo.getStockCarId())) {
			StockCarVo stockCar = stockCarDao.selectByIdAtAll(orderVo.getStockCarId());
			image = stockCar.getStockCarImages();
		}
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CustomerOrderTrackVo orderTrackVo : customerOrderTrackVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("createDate", main.com.utils.DateUtil.format(orderTrackVo.getCreateDate()));
			map.put("trackContent", orderTrackVo.getTrackContent());
			if(GeneralConstant.CustomerOrderState.deliveryOfTheTail.equals(orderTrackVo.getCustomerOrderState())) {
				map.put("image", image);
			}else {
				map.put("image", "");
			}
			maps.add(map);
		}
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}

	@Override
	public Result customerOrderPrint(CustomerOrderSearch search, Result result, UsersTmpl users) {
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
	
	private Map<String, Object> takeMapOfOrder(CustomerOrder customerOrder) {
		Map<String,Object> map = new HashMap<String,Object>();
	    map.put("customerOrderId", customerOrder.getCustomerOrderId()); //主键ID
	    map.put("customerOrderCode", customerOrder.getCustomerOrderCode()); //订单编号
	    map.put("customerOrderState", customerOrder.getCustomerOrderState()); //订单状态
	    map.put("createDate", DateUtil.format(customerOrder.getCreateDate())); //创建日期
	    map.put("customerUserId", customerOrder.getCustomerId()); //购买用户ID
	    map.put("customerName", customerOrder.getCustomerName()); //购买用户ID
	    map.put("customerPhoneNumber", customerOrder.getCustomerPhoneNumber()); //购买用户ID
	    map.put("customerUserCard", customerOrder.getCustomerUserCard()); //购买用户ID
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
	    map.put("balancePrice", customerOrder.getBalancePrice() != null ?customerOrder.getBalancePrice().doubleValue():0.0d);
	    map.put("orgId", customerOrder.getOrgId());
	    map.put("orgName", customerOrder.getOrgName());
	    map.put("stockCarId", customerOrder.getStockCarId());//具体库存车辆
	    map.put("remark", customerOrder.getRemarks());//备注
	    map.put("paymentWay", customerOrder.getPaymentWay());//付款方式 1.全款 2分期
	    map.put("downPayments", customerOrder.getDownPayments()!= null ?customerOrder.getDownPayments().doubleValue():0.0d);//首付
	    map.put("loan", customerOrder.getLoan()!= null ?customerOrder.getLoan().doubleValue():0.0d);//贷款
	    map.put("loanPayBackStages", customerOrder.getLoanPayBackStages());//贷款分期数
	    map.put("amount", customerOrder.getAmount()!= null ?customerOrder.getAmount().doubleValue():0.0d);//最终成交价
	    map.put("loanBank", customerOrder.getLoanBank());//贷款银行
	    if(customerOrder.getIsMortgage() != null && customerOrder.getIsMortgage()) {
	    	map.put("isMortgage", 1);//是否抵押
	    }else {
	    	map.put("isMortgage", 0);//是否抵押
	    }
//	    map.put("amount", customerOrder.getAmount()!= null ?customerOrder.getAmount().doubleValue():0.0d);//最终成交价
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
//	    map.put("licensePlatePriace", customerOrder.getLicensePlatePriace()!= null ?customerOrder.getLicensePlatePriace().doubleValue():0.0d);//上牌费用
//	    map.put("insurancePriace", customerOrder.getInsurancePriace()!= null ?customerOrder.getInsurancePriace().doubleValue():0.0d);//保险金额
	    map.put("systemUserId", customerOrder.getSystemUserId());//销售顾问
	    map.put("systemUserName", customerOrder.getSystemUserName());//销售顾问
	    map.put("systemUserPhone", customerOrder.getSystemUserPhone());	//销售电话
	    map.put("extractCarImage", customerOrder.getExtractCarImage());	//提车图片
	    
	    map.put("amountMoney",NumberFormat.number2CNMontrayUnit(customerOrder.getAmount()));//最终成交价
	    map.put("depositPriceMoney", NumberFormat.number2CNMontrayUnit(customerOrder.getDepositPrice())); //定金
	    
	    map.put("carUnitPrice", customerOrder.getCarUnitPrice()!=null?customerOrder.getCarUnitPrice().doubleValue():0);
		map.put("licensePlatePriace", customerOrder.getLicensePlatePriace()!=null?customerOrder.getLicensePlatePriace().doubleValue():0);
		map.put("insurancePriace", customerOrder.getInsurancePriace()!=null?customerOrder.getInsurancePriace().doubleValue():0);
		map.put("purchaseTaxPriace", customerOrder.getPurchaseTaxPriace()!=null?customerOrder.getPurchaseTaxPriace().doubleValue():0);
		map.put("boutiquePriace", customerOrder.getBoutiquePriace()!=null?customerOrder.getBoutiquePriace().doubleValue():0);
		map.put("mortgagePriace", customerOrder.getMortgagePriace()!=null?customerOrder.getMortgagePriace().doubleValue():0);
		map.put("vehicleAndVesselTax", customerOrder.getVehicleAndVesselTax()!=null?customerOrder.getVehicleAndVesselTax().doubleValue():0);		
		return TakeCareMapDate.cutNullMap(map);
	}
}
