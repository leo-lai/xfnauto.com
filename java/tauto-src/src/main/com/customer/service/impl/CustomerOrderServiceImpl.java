package main.com.customer.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarInteriorDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.po.Cars;
import main.com.car.dao.vo.CarColourVo;
import main.com.car.dao.vo.CarInteriorVo;
import main.com.car.dao.vo.CarsVo;
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
import main.com.customer.dao.vo.CustomerCustomerOrgVo;
import main.com.customer.dao.vo.CustomerOrderInPayVo;
import main.com.customer.dao.vo.CustomerOrderVo;
import main.com.customer.service.CustomerOrderService;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.search.StockOrderSearch;
import main.com.stock.dao.vo.StockCarVo;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.vo.OrganizationVo;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.NumberFormat;
import main.com.utils.GeneralConstant.CustomerOrderState;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class CustomerOrderServiceImpl extends BaseServiceImpl<CustomerOrder> implements CustomerOrderService{

	@Autowired
	CustomerOrderDao customerOrderDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	CustomerUsersDao customerUsersDao;
	
	@Autowired
	CustomerCustomerOrgDao customerCustomerOrgDao;
	
	@Autowired
	CarsDao carsDao;
		
	@Autowired
	CustomerOrderTrackDao customerOrderTrackDao;
	
	@Autowired
	CustomerOrderInPayDao customerOrderInPayDao;
	
	@Autowired
	CarColourDao carColourDao;
	
	@Autowired
	CarInteriorDao carInteriorDao;
	
	@Autowired
	SystemUsersDao systemUsersDao;
	
	@Autowired
	StockCarDao stockCarDao;
	
	@Override
	protected BaseDao<CustomerOrder> getBaseDao() {
		return customerOrderDao;
	}

	@Override
	@Transactional
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
			customerOrder = new CustomerOrder();
			customerOrder.setCreateDate(new Date());
			customerOrder.setCustomerOrderCode(customerOrderDao.getCode(customerOrder.getCustomerPhoneNumber()));
			customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.start);

			customerOrder.setOrgId(organizationVo.getOrgId());
			customerOrder.setOrgName(organizationVo.getShortName());
			if(StringUtil.isEmpty(search.getCustomerUsersId())) {
				result.setError("你选择的用户信息错误，请刷新重试或者联系管理员");
				return result;
			}
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
			if(customerOrder.getCustomerOrderState() > GeneralConstant.CustomerOrderState.start) {
				result.setError("抱歉，订单已支付定金，已不能进行修改");
				return result;
			}
		}
		if(StringUtil.isNotEmpty(search.getSystemUserId())) {
			SystemUsers systemUsers = systemUsersDao.selectByIdInUse(search.getSystemUserId());
			customerOrder.setSystemUserId(systemUsers.getUsersId());
			customerOrder.setSystemUserName(systemUsers.getRealName());
			customerOrder.setSystemUserPhone(systemUsers.getPhoneNumber());
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
		if(StringUtil.isEmpty(search.getAmount())) {
			result.setError("请输入最终成交价");
			return result;
		}
		customerOrder.setAmount(new BigDecimal(search.getAmount()));
		if(StringUtil.isEmpty(search.getPaymentWay())) {
			result.setError("请选择购车方案");
			return result;
		}
		if(search.getPaymentWay().equals(GeneralConstant.CustomerOrderState.fullPayment)) {//全款
			customerOrder.setPaymentWay(GeneralConstant.CustomerOrderState.fullPayment);
		}else if(search.getPaymentWay().equals(GeneralConstant.CustomerOrderState.byStages)) {
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
				result.setError("首付加贷款小于订单最终成加价，请核对数据");
				return result;
			}
		}else {
			result.setError("购车方案选择错误，请重新选择");
			return result;
		}
		if(search.getIsPurchaseTax() != null && search.getIsPurchaseTax()) {
			customerOrder.setIsPurchaseTax(true);
		}else {
			customerOrder.setIsPurchaseTax(false);
		}
		if(search.getIsTakeLicensePlate() != null && search.getIsTakeLicensePlate()) {
			customerOrder.setIsTakeLicensePlate(true);
			if(StringUtil.isEmpty(search.getLicensePlatePriace())) {
				result.setError("请输入上牌费用");
				return result;
			}
			customerOrder.setLicensePlatePriace(new BigDecimal(search.getLicensePlatePriace()));
		}else {
			customerOrder.setIsTakeLicensePlate(false);
		}
		if(search.getIsInsurance() != null && search.getIsInsurance()) {
			customerOrder.setIsInsurance(true);
			if(StringUtil.isEmpty(search.getInsurancePriace())) {
				result.setError("请输入商业保险金额");
				return result;
			}
			customerOrder.setInsurancePriace(new BigDecimal(search.getInsurancePriace()));
		}else {
			customerOrder.setIsInsurance(false);
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
				customerOrg.setIsTrack(true);
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
	public Result payInOrder(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(search.getCustomerOrderId())) {
			result.setError("订单选择错误，请正确选择订单");
			return result;
		}
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		//校验支付
		Double amount = 0.0d;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("customerOrderId", customerOrder.getCustomerOrderId());
		List<CustomerOrderInPayVo> orderInPayVos = customerOrderInPayDao.select(params);
		if(orderInPayVos != null && orderInPayVos.size() > 0) {
			for(CustomerOrderInPayVo orderInPayVo : orderInPayVos) {
				if(orderInPayVo.getAmount() == null) {
					continue;
				}
				amount += orderInPayVo.getAmount().doubleValue();
			}
		}
		
		if((amount+search.getAmount()) > customerOrder.getAmount().doubleValue()) {
			result.setError("此订单共已支付"+ amount +"元，再支付"+search.getAmount()+"元已大于等于订单总金额："+customerOrder.getAmount().doubleValue());
			return result;
		}
		amount += search.getAmount();//之前支付总额再加上此次支付金额（校验现金支付是否支付到尾款）
		CustomerOrderInPay customerOrderInPay = new CustomerOrderInPay();
		customerOrderInPay.setAmount(new BigDecimal(search.getAmount()));
		customerOrderInPay.setCreateDate(new Date());
		customerOrderInPay.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
		customerOrderInPay.setCustomerOrderId(customerOrder.getCustomerOrderId());
		customerOrderInPay.setCustomerOrderState(customerOrder.getCustomerOrderState());
		customerOrderInPay.setOrderInPayCode(customerOrderInPayDao.getPayCode());
		customerOrderInPay.setRemarks(search.getRemarks());
		customerOrderInPay.setSystemUserId(users.getUsersId());
		customerOrderInPay.setSystemUserName(users.getRealName());
		if(search.getIsDepositPrice() == null) {//默认为支付定金
			search.setIsDepositPrice(false);
		}
		if(search.getIsDepositPrice()) {
			if(customerOrder.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.start)) {//如果是支付定金
				if(customerOrderInPay.getAmount().doubleValue() != customerOrder.getDepositPrice().doubleValue()) {
						result.setError("定金金额错误，应付定金："+customerOrder.getDepositPrice().doubleValue());
						return result;					
				}
				if(customerOrder.getPaymentWay().equals(GeneralConstant.CustomerOrderState.fullPayment)) {//后期急着上线，冗余代码
					Double amount_ = customerOrder.getAmount().doubleValue();
					if(customerOrderInPay.getAmount().doubleValue() >= amount_) {
						result.setError("定金不能大于等于订单总额，订单总额："+amount_+"元");
						return result;
					}
				}else {
					Double amount_ = customerOrder.getDownPayments().doubleValue();
					//限制支付不能超过首付
					if(customerOrderInPay.getAmount().doubleValue() >= amount_) {
						result.setError("定金不能大于等于首付，首付："+amount_+"元");
						return result;
					}
				}
			}
		}
		
		if(search.getPayMethod().equals(GeneralConstant.PayModeType.PAY_CASH) || search.getPayMethod().equals(GeneralConstant.PayModeType.PAY_BY_POS_ONESELF)) {
			if(search.getPayMethod().equals(GeneralConstant.PayModeType.PAY_CASH)) {
				customerOrderInPay.setPayMethod(GeneralConstant.PayModeType.PAY_CASH);
			}else {
				customerOrderInPay.setPayMethod(GeneralConstant.PayModeType.PAY_BY_POS_ONESELF);
			}
			customerOrderInPay.setOrderInPayState(GeneralConstant.OrderInPayState.in_pay);
			customerOrderInPay.setPayDate(new Date());
			if(customerOrder.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.start)) {//支付定金
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
			orderTrack.setTrackContent("支付定金："+customerOrderInPay.getAmount().doubleValue());
			customerOrder.setDepositPrice(customerOrderInPay.getAmount());
			orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
			orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());			
			if(customerOrder.getPaymentWay().equals(GeneralConstant.CustomerOrderState.fullPayment)) {//如果是全款		
				//限制支付不能超过最终
				//查询尾款，贷款用首付，全款用最终成交价
				Double amount_ = customerOrder.getAmount().doubleValue();
//				if(customerOrder.getPaymentWay().equals((GeneralConstant.CustomerOrderState.fullPayment))) {
//					amount_ = customerOrder.getAmount().doubleValue();
//				}else {
//					amount_ = customerOrder.getDownPayments().doubleValue();
//				}
				if(customerOrderInPay.getAmount().doubleValue() >= amount_) {
					result.setError("定金不能大于等于最终成交价，最终成交价："+amount_+"元");
					return result;
				}
				customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.loanAudit);
				CustomerOrderTrack orderTrack_1 = new CustomerOrderTrack();
				orderTrack_1.setCreateDate(new Date());
				/**
				 * 1:已提交定车单
					3:已支付定金,待银行审批贷款
					5:待车辆到店
					7:车辆到店，可到店交付尾款
					9:已交付尾款，待车辆加装上牌
					11:加装／上牌完成，可到店提车
				 */
				orderTrack_1.setTrackContent("全款购车，不需要审核");
				orderTrack_1.setCustomerOrderId(customerOrder.getCustomerOrderId());
				orderTrack_1.setCustomerOrderCode(customerOrder.getCustomerOrderCode());				
				orderTrack_1.setCustomerOrderState(GeneralConstant.CustomerOrderState.loanAudit);
				orderTrack.setCustomerOrderState(GeneralConstant.CustomerOrderState.paymentOfADeposit);
				customerOrderTrackDao.insert(orderTrack_1);
			}else {
				Double amount_ = customerOrder.getDownPayments().doubleValue();
//				if(customerOrder.getPaymentWay().equals((GeneralConstant.CustomerOrderState.fullPayment))) {
//					amount_ = customerOrder.getAmount().doubleValue();
//				}else {
//					amount_ = customerOrder.getDownPayments().doubleValue();
//				}
				//限制支付不能超过首付
				if(customerOrderInPay.getAmount().doubleValue() >= amount_) {
					result.setError("定金不能大于等于首付，首付："+amount_+"元");
					return result;
				}
				customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.paymentOfADeposit);
				orderTrack.setCustomerOrderState(customerOrder.getCustomerOrderState());
				customerOrderInPay.setCustomerOrderState(customerOrder.getCustomerOrderState());
//				orderTrack.setTrackContent(orderTrack.getTrackContent() + "贷款购车（首付："+customerOrder.getDownPayments().doubleValue()+"，贷款金额："+customerOrder.getLoan().doubleValue()
//						+ "还款期数："+customerOrder.getLoanPayBackStages()+"）");						
			}
			Map<String,Object> params_c = new HashMap<String,Object>();
//			params_c.put("orgId", order.getOrgId());//组织搜索
			params_c.put("customerUsersId", customerOrder.getCustomerId());//组织搜索
			List<CustomerCustomerOrgVo> customerOrgVos = customerCustomerOrgDao.select(params_c);
			for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
				if(customerOrder.getOrgId().equals(customerOrgVo.getOrgId())) {
					customerOrgVo.setIsTrack(true);
				}else {
					customerOrgVo.setIsEdit(false);
				}
				customerOrgVo.setIsShareEdit(false);
				customerCustomerOrgDao.updateById(customerOrgVo);
			}
//			CustomerCustomerOrgVo customerCustomerOrgVo = customerOrgVos.get(0);
			customerOrderTrackDao.insert(orderTrack);
			}else {
				//查询该订单的所有支付单
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("customerOrderId", customerOrder.getCustomerOrderId());
//				List<CustomerOrderInPayVo> customerOrderInPays = customerOrderInPayDao.select(map);
//				for(CustomerOrderInPayVo inPayVo : customerOrderInPays) {
//					amount += inPayVo.getAmount().doubleValue();
//				}
				//查询尾款，贷款用首付，全款用最终成交价
				
				/**
				 * 暂时把尾款检测冻结
				 */
//				Double amount_ = 0.0d;
//				if(customerOrder.getPaymentWay().equals((GeneralConstant.CustomerOrderState.fullPayment))) {
//					amount_ = customerOrder.getAmount().doubleValue();
//				}else {
//					amount_ = customerOrder.getDownPayments().doubleValue();
//				}
//				
//				if(amount >= amount_) {//支付大于等于全款
//					//记录订单跟踪
//					CustomerOrderTrack orderTrack = new CustomerOrderTrack();
//					orderTrack.setCreateDate(new Date());
//					/**
//					 * 1:已提交定车单
//						3:已支付定金,待银行审批贷款
//						5:待车辆到店
//						7:车辆到店，可到店交付尾款
//						9:已交付尾款，待车辆加装上牌
//						11:加装／上牌完成，可到店提车
//					 */
//					orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
//					orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
//					orderTrack.setCustomerOrderState(GeneralConstant.CustomerOrderState.deliveryOfTheTail);
//					orderTrack.setTrackContent("尾款已支付："+(amount_ - customerOrder.getDepositPrice().doubleValue()));
//					customerOrderTrackDao.insert(orderTrack);
//					customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.deliveryOfTheTail);//直接调至加装牌照
//					customerOrderInPay.setCustomerOrderState(customerOrder.getCustomerOrderState());
//				}	
				/**
				 * 暂时把尾款检测冻结
				 */
			}
			customerOrderDao.updateById(customerOrder);
		}else {
			customerOrderInPay.setOrderInPayState(GeneralConstant.OrderInPayState.un_paid);
		}
		if(customerOrderInPayDao.insert(customerOrderInPay)) {
			result.setOK(ResultCode.CODE_STATE_200, "", customerOrderInPay.getOrderInPayCode());
		}else {
			result.setError("操作失败");
		}
		return result;
	}

	@Override
	public Result customerOrderInfo(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		Map<String,Object> map = takeMapOfOrder(customerOrder);
		result.setOK(ResultCode.CODE_STATE_200, "", map);
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
	    map.put("boutiqueInformation", customerOrder.getBoutiqueInformation());//随车资料，多个用逗号隔开
	    map.put("balancePrice", customerOrder.getBalancePrice() != null ?customerOrder.getBalancePrice().doubleValue():0.0d);
	    map.put("orgId", customerOrder.getOrgId());
	    map.put("orgName", customerOrder.getOrgName());
	    map.put("stockCarId", customerOrder.getStockCarId());//具体库存车辆
	    if(StringUtil.isNotEmpty(customerOrder.getStockCarId())) {
		    StockCarVo carVo = stockCarDao.selectById(customerOrder.getStockCarId());
		    map.put("frameNumber", carVo.getFrameNumber());//具体库存车辆
	    }else {
	    	map.put("frameNumber", "");//具体库存车辆
	    }
	    map.put("remark", customerOrder.getRemarks());//备注
	    map.put("paymentWay", customerOrder.getPaymentWay());//付款方式 1.全款 2分期
	    Double amount = 0.0d;
		Map<String,Object> paramsInpay = new HashMap<String,Object>();
		paramsInpay.put("customerOrderId", customerOrder.getCustomerOrderId());
		List<CustomerOrderInPayVo> orderInPayVos = customerOrderInPayDao.select(paramsInpay);
		if(orderInPayVos != null && orderInPayVos.size() > 0) {
			for(CustomerOrderInPayVo orderInPayVo : orderInPayVos) {
				if(orderInPayVo.getAmount() == null) {
					continue;
				}
				amount += orderInPayVo.getAmount().doubleValue();
			}
		}
	    if(GeneralConstant.CustomerOrderState.byStages.equals(customerOrder.getPaymentWay())) {
			map.put("payAmount", customerOrder.getDownPayments().doubleValue() - amount);//剩余应付金额
			map.put("totalAmount", customerOrder.getDownPayments().doubleValue());//合计应收费用
		}else {
			map.put("payAmount", customerOrder.getAmount().doubleValue() - amount);//剩余应付金额
			map.put("totalAmount", customerOrder.getAmount().doubleValue());//合计应收费用
		}
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
//		return customerOrderDao.updateByIdAndResultIT(customerOrder, result);
//		result.setOK(ResultCode.CODE_STATE_200, "");
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
//		return customerOrderDao.updateByIdAndResultIT(customerOrder, result);
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
//		result.setOK(ResultCode.CODE_STATE_200, "");
		return result;
	}

	@Override
	@Transactional
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
	public Result bankExamineOders(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
//		if(StringUtil.isEmpty(users.getOrgCode())) {
//			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请先联系管理员");
//			return result;
//		}
		Map<String,Object> params = getCustomerParams(search,users);
		params.put("isBank", true);
//		params.put("loanBank", GeneralConstant.LoanBank.NONGYE);
//		params.put("customerOrderState", GeneralConstant.CustomerOrderState.initialization);
		List<CustomerOrderVo> customerOrderVos = customerOrderDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = customerOrderDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(CustomerOrderVo orderVo : customerOrderVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("customerOrderCode",orderVo.getCustomerOrderCode());
			map.put("customerName", orderVo.getCustomerName());//
			map.put("createDate", orderVo.getCreateDate());//
			map.put("customerOrderId", orderVo.getCustomerOrderId());//
			map.put("interiorId", orderVo.getInteriorId());//
			map.put("interiorName", orderVo.getInteriorName());//
			map.put("carsName", orderVo.getCarsName());
			map.put("customerOrderState", orderVo.getCustomerOrderState());
			map.put("colourId", orderVo.getColourId());
			map.put("colourName", orderVo.getColourName());
			map.put("customerPhoneNumber", orderVo.getCustomerPhoneNumber());			
			map.put("systemUserName", orderVo.getSystemUserName());			
			map.put("amount", orderVo.getAmount());//指导价			
			map.put("remark", orderVo.getRemarks());//指导价			
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("page", search.getPage());
		allMap.put("total", total);
		allMap.put("rows", rows);
		allMap.put("list", maps);
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",allMap);
		return result;
	} 
	
	public Map<String,Object> getCustomerParams(CustomerOrderSearch search, SystemUsers users){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getCustomerOrderCode())) {
			params.put("customerOrderCode", search.getCustomerOrderCode());
		}
		params.put("customerOrderState", GeneralConstant.CustomerOrderState.paymentOfADeposit);
		params.put("loanBank", GeneralConstant.LoanBank.NONGYE);
//		params.put("orgCode", users.getOrgCode());
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows()); 
		return params;
	}

	@Override
	public Map<String, Object> getMapCustomerOrder(SystemUsers users) throws Exception {
		//查询出门店的所有订单
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isEmpty(users.getOrgCode())) {
			return params;
		}
		params.put("orgCode", users.getOrgCode());
		List<CustomerOrderVo> customerOrderVos = customerOrderDao.select(params);
		Integer one = 0; //落定
		Integer two = 0;//车辆出库未交尾款
		Integer three = 0;//已完款
		Integer four = 0; //加装/上牌
		Integer five = 0;//可以交付
		Map<String,Object> map = new HashMap<String, Object>();	
		if(customerOrderVos == null || customerOrderVos.size() <= 0) {
			map.put("one", 0);
			map.put("two", 0);
			map.put("three", 0);
			map.put("four", 0);
			map.put("five", 0);
		}
		for(CustomerOrderVo customerOrderVo : customerOrderVos) {
			if(CustomerOrderState.paymentOfADeposit.equals(customerOrderVo.getCustomerOrderState()) || CustomerOrderState.notPassThrough.equals(customerOrderVo.getCustomerOrderState())
					|| CustomerOrderState.loanAudit.equals(customerOrderVo.getCustomerOrderState())) {
				one += 1;
			}else if(CustomerOrderState.deliverTheLibrary.equals(customerOrderVo.getCustomerOrderState())) {
				two += 1;
			}else if(CustomerOrderState.deliveryOfTheTail.equals(customerOrderVo.getCustomerOrderState())) {
				three += 1;
			}else if(CustomerOrderState.retrofitting.equals(customerOrderVo.getCustomerOrderState()) || CustomerOrderState.deliveryOfTheTail.equals(customerOrderVo.getCustomerOrderState())) {
				four += 1;				
			}else if(CustomerOrderState.deliver.equals(customerOrderVo.getCustomerOrderState())) {
			four += 1;				
		}

		}
		map.put("one", one);
		map.put("two", two);
		map.put("three", three);
		map.put("four", four);
		map.put("five", five);
		return map;
	}

	@Override
	public Result orderPayList(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		//查询支付记录
		Map<String,Object> payParams = new HashMap<String,Object>();
		payParams.put("customerOrderId", customerOrder.getCustomerOrderId());
		List<CustomerOrderInPay> inPays = customerOrderInPayDao.select(payParams);
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		for(CustomerOrderInPay customerOrderInPay : inPays) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("createDate", DateUtil.format(customerOrderInPay.getCreateDate()));
			map.put("amount", customerOrderInPay.getAmount()!=null?customerOrderInPay.getAmount().doubleValue():0);
			map.put("remarks", customerOrderInPay.getRemarks());
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		result.setOK(ResultCode.CODE_STATE_200, "", maps);
		return result;
	}

	@Override
	public Result orderPriceList(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		Cars cars = carsDao.selectById(customerOrder.getCarsId());		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("carUnitPrice", customerOrder.getCarUnitPrice()!=null?customerOrder.getCarUnitPrice().doubleValue():0);
		map.put("licensePlatePriace", customerOrder.getLicensePlatePriace()!=null?customerOrder.getLicensePlatePriace().doubleValue():0);
		map.put("insurancePriace", customerOrder.getInsurancePriace()!=null?customerOrder.getInsurancePriace().doubleValue():0);
		map.put("purchaseTaxPriace", customerOrder.getPurchaseTaxPriace()!=null?customerOrder.getPurchaseTaxPriace().doubleValue():0);
		map.put("boutiquePriace", customerOrder.getBoutiquePriace()!=null?customerOrder.getBoutiquePriace().doubleValue():0);
		map.put("mortgagePriace", customerOrder.getMortgagePriace()!=null?customerOrder.getMortgagePriace().doubleValue():0);
		map.put("vehicleAndVesselTax", customerOrder.getVehicleAndVesselTax()!=null?customerOrder.getVehicleAndVesselTax().doubleValue():0);
		map.put("carsName", customerOrder.getCarsName());
		if(cars != null) {
			map.put("guidingPrice", cars.getPrice());
		}
		result.setOK(ResultCode.CODE_STATE_200, "", TakeCareMapDate.cutNullMap(map));
		return result;
	}

	@Override
	public Result endOrder(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(!GeneralConstant.CustomerOrderState.deliverTheLibrary.equals(customerOrder.getCustomerOrderState())) {
			if(customerOrder.getCustomerOrderState() > GeneralConstant.CustomerOrderState.deliverTheLibrary) {
				result.setError("订单已历过客户提车车的历程，已不需要进行此操作");
				return result;
			}else {
				result.setError("订单尚未至客户提车历程，请耐心等待");
				return result;
			}
		}
		customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.orderBeenFinish);//交付车辆
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
			orderTrack.setTrackContent("已确认交车");
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
	public Result customerOrderList(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String, Object> params = new HashMap<>();
		params.put("keywords", search.getKeywords());
		params.put("customerOrderState", search.getCustomerOrderState());
		params.put("orgId", users.getOrgId());
		List<CustomerOrderVo> orderVos = customerOrderDao.select(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", orderVos);
		return new Result(returnMap);
	}
}
