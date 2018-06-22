package main.com.weixinHtml.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import main.com.customer.dao.dao.CustomerOrderDao;
import main.com.customer.dao.po.CustomerOrderInPay;
import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.customer.dao.vo.CustomerOrderInPayVo;
import main.com.customer.dao.vo.CustomerOrderVo;
import main.com.exception.BusinessException;
import main.com.frame.constants.ConsumerOrderState;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.logistics.dao.search.LogisticsConsignmentSearch;
import main.com.logistics.dao.vo.LogisticsConsignmentVo;
import main.com.pay.allInPay.SystemConfig;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.po.ConsumerOrder;
import main.com.stock.dao.po.ConsumerOrderCar;
import main.com.stock.dao.po.ConsumerOrderInfo;
import main.com.stock.dao.po.ConsumerOrderPayment;
import main.com.stock.dao.po.ConsumerOrderUser;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.po.StockStorage;
import main.com.stock.dao.vo.StockCarVo;
import main.com.stock.dao.vo.StockStorageVo;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.vo.OrganizationVo;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.utils.ConvertUtil;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;
import main.com.utils.weixin.PayUtils;
import main.com.weixin.dao.vo.WeixinAppTokenVo;
import main.com.weixinApp.dao.dao.ConsumerOrderCarDao;
import main.com.weixinApp.dao.dao.ConsumerOrderDao;
import main.com.weixinApp.dao.dao.ConsumerOrderInfoDao;
import main.com.weixinApp.dao.dao.ConsumerOrderPaymentDao;
import main.com.weixinApp.dao.dao.ConsumerOrderUserDao;
import main.com.weixinApp.dao.search.ListOrderSearch;
import main.com.weixinApp.dao.vo.ConsumerOrderCarVO;
import main.com.weixinApp.dao.vo.ConsumerOrderInfoVO;
import main.com.weixinApp.dao.vo.ConsumerOrderPaymentVO;
import main.com.weixinApp.dao.vo.ConsumerOrderUserVO;
import main.com.weixinApp.dao.vo.ConsumerOrderVO;
import main.com.weixinHtml.constant.OrderPayMethodName;
import main.com.weixinHtml.dao.dao.ShopAdvanceOrderDao;
import main.com.weixinHtml.dao.dao.ShopAdvanceOrderInfoDao;
import main.com.weixinHtml.dao.dao.ShopAdvanceOrderPaymentDao;
import main.com.weixinHtml.dao.dao.ShopGoodsCarsActivityDao;
import main.com.weixinHtml.dao.dao.ShopGoodsCarsDao;
import main.com.weixinHtml.dao.dao.ShopUsersDao;
import main.com.weixinHtml.dao.po.ShopAdvanceOrder;
import main.com.weixinHtml.dao.po.ShopAdvanceOrderPayment;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.search.ShopAdvanceOrderInfoSearch;
import main.com.weixinHtml.dao.search.ShopAdvanceOrderSearch;
import main.com.weixinHtml.dao.search.ShopGoodsCarsActivitySearch;
import main.com.weixinHtml.dao.vo.ShopAdvanceOrderInfoVo;
import main.com.weixinHtml.dao.vo.ShopAdvanceOrderPaymentVo;
import main.com.weixinHtml.dao.vo.ShopAdvanceOrderVo;
import main.com.weixinHtml.dao.vo.ShopGoodsCarsActivityVo;
import main.com.weixinHtml.dao.vo.ShopGoodsCarsVo;
import main.com.weixinHtml.service.ShopAdvanceOrderService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

@Service
public class ShopAdvanceOrderServiceImpl extends BaseServiceImpl<ShopAdvanceOrder> implements ShopAdvanceOrderService{

	@Autowired
	ShopAdvanceOrderDao shopAdvanceOrderDao;
	
	@Autowired
	ShopGoodsCarsActivityDao activityDao;
	
	@Autowired
	ShopGoodsCarsDao goodsCarsDao;
	
	@Autowired
	ShopAdvanceOrderInfoDao orderInfoDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	ShopAdvanceOrderPaymentDao orderPaymentDao;
	
	@Autowired
	ConsumerOrderDao consumerOrderDao;
	
	@Autowired
	ConsumerOrderInfoDao consumerOrderInfoDao;
	
	@Autowired
	ConsumerOrderCarDao consumerOrderCarDao;
	
	@Autowired
	StockCarDao stockCarDao;
	
	@Autowired
	CustomerOrderDao customerOrderDao;
	
	@Autowired
	ConsumerOrderUserDao consumerOrderUesrDao;
	
	@Autowired
	ConsumerOrderPaymentDao consumerOrderPaymentDao;
	
	@Autowired
	ShopUsersDao shopUsersDao;
	
	@Override
	protected BaseDao<ShopAdvanceOrder> getBaseDao() {
		return shopAdvanceOrderDao;
	}

	@Override
	@Transactional
	public Result advanceOrderEdit(ShopAdvanceOrderSearch search, Result result, ShopUsers users) throws Exception {
		if(users.getUserType().equals(2) && StringUtil.isEmpty(users.getOrgId())) {
			result.setError("请在个人中心中先完善店铺信息");
			return result;
		}
		ShopAdvanceOrderVo advanceOrder = null;
		List<ShopAdvanceOrderInfoVo> infoVos = null; 
		if(StringUtil.isEmpty(search.getAdvanceOrderId()) && StringUtil.isNotEmpty(search.getOrderId())) {
			search.setAdvanceOrderId(search.getOrderId());
		}
		if(StringUtil.isEmpty(search.getAdvanceOrderId())) {
			advanceOrder = new ShopAdvanceOrderVo();
			advanceOrder.setCatchDate(new Date());
			advanceOrder.setCreateTime(new Date());
			advanceOrder.setTheSource("SC");
			advanceOrder.setTimeOfAppointmentDate(new Date());
			advanceOrder.setOrderCode(shopAdvanceOrderDao.getCode(users.getPhoneNumber()));
			advanceOrder.setOverDelete(false);
			advanceOrder.setUserType(users.getUserType());
			advanceOrder.setShopUserId(users.getShopUserId());
		}else {
			advanceOrder = shopAdvanceOrderDao.selectByIdJoin(search.getAdvanceOrderId());
			if(advanceOrder.getOverPay() || advanceOrder.getOverDelete() || advanceOrder.getOverCatch()) {
				result.setError("抱歉，该预约已锁定，已不能进行修改");
				return result;
			}
			if(advanceOrder.getOrderInfoVos() != null && advanceOrder.getOrderInfoVos().size() > 0) {
				infoVos = advanceOrder.getOrderInfoVos();
			}
		}
		if(!StringUtil.isNotEmptyMoreThenZero(search.getNumber())) { //循环参数 number
			result.setError("请输入预定车辆数量");
			return result;
		}
		BigDecimal amount = new BigDecimal(0);
		BigDecimal depositPrice = new BigDecimal(0);
		BigDecimal discountPriceOnLine = new BigDecimal(0);
		BigDecimal bareCarPriceOnLine = new BigDecimal(0);
		BigDecimal logisticsPrice = new BigDecimal(0);
		ShopAdvanceOrderInfoSearch infoSearch = new ShopAdvanceOrderInfoSearch();
		if(StringUtil.isNotEmpty(search.getGoodsCarsActivityId())) {
			ShopGoodsCarsActivityVo activityVo = activityDao.selectByIdJoin(search.getGoodsCarsActivityId());
			if(activityVo == null) {
				result.setError("抱歉，该活动不存在或者已下架");
				return result;
			}
			infoSearch.setCarsId(activityVo.getCarsId());
			infoSearch.setBrandId(activityVo.getBrandId());
			infoSearch.setFamilyId(activityVo.getFamilyId());
			infoSearch.setCarsName(activityVo.getCarsName());
			infoSearch.setDepositPrice(activityVo.getDepositPrice());
			infoSearch.setInteriorId(activityVo.getInteriorId());
			infoSearch.setColourId(activityVo.getColourId());
			infoSearch.setInteriorName(activityVo.getInteriorName());
			infoSearch.setColourName(activityVo.getColourName());
			infoSearch.setGuidingPrice(activityVo.getGuidingPrice());
			infoSearch.setInvoicePrice(activityVo.getInvoicePrice());
			infoSearch.setOverInsurance(activityVo.getOverInsurance());
			infoSearch.setImage(activityVo.getImage());
			infoSearch.setDiscountPriceOnLine(activityVo.getDiscountPriceOnLine());
			infoSearch.setBareCarPriceOnLine(activityVo.getBareCarPriceOnLine());
			infoSearch.setLogisticsPrice(activityVo.getLogisticsPrice());
			amount = amount.add(activityVo.getActivityPrice());
			depositPrice = depositPrice.add(activityVo.getDepositPrice());
			discountPriceOnLine = discountPriceOnLine.add(activityVo.getDiscountPriceOnLine());
			bareCarPriceOnLine = bareCarPriceOnLine.add(activityVo.getBareCarPriceOnLine());
			if(activityVo.getLogisticsPrice() != null) {
				logisticsPrice = logisticsPrice.add(activityVo.getLogisticsPrice());
			}
			advanceOrder.setOrderSource(2);//来自活动
			advanceOrder.setOrgCode(activityVo.getOrgCode());
			advanceOrder.setOrderSourceId(activityVo.getGoodsCarsActivityId());
			advanceOrder.setOrgId(activityVo.getOrgId());
			advanceOrder.setOrgName(activityVo.getOrgName());
			advanceOrder.setSystemUserId(activityVo.getSystemUsersId());
			advanceOrder.setSystemUserName(activityVo.getUsersVo().getRealName());
//			if(users.getUserType().equals(1)) {//普通用户
//				advanceOrder.setOrgId(activityVo.getOrgId());
//				advanceOrder.setOrgName(activityVo.getOrgName());
//			}else {//B端用户
//				advanceOrder.setOrgId(activityVo.getOrgId());
//				advanceOrder.setOrgName(activityVo.getOrgName());
//			}
		}else if(StringUtil.isNotEmpty(search.getGoodsCarsId())) {
			ShopGoodsCarsVo goodsCarsVo = goodsCarsDao.selectByIdJoin(search.getGoodsCarsId());
			if(goodsCarsVo == null) {
				result.setError("抱歉，该车辆不存在或者已下架");
				return result;
			}
			infoSearch.setCarsId(goodsCarsVo.getCarsId());
			infoSearch.setBrandId(goodsCarsVo.getBrandId());
			infoSearch.setFamilyId(goodsCarsVo.getFamilyId());
			infoSearch.setCarsName(goodsCarsVo.getCarsName());
			infoSearch.setDepositPrice(goodsCarsVo.getDepositPrice());
			infoSearch.setInteriorId(goodsCarsVo.getInteriorId());
			infoSearch.setColourId(goodsCarsVo.getColourId());
			infoSearch.setInteriorName(goodsCarsVo.getInteriorName());
			infoSearch.setColourName(goodsCarsVo.getColourName());
			infoSearch.setGuidingPrice(goodsCarsVo.getGuidingPrice());
			infoSearch.setInvoicePrice(goodsCarsVo.getInvoicePrice());
			infoSearch.setOverInsurance(goodsCarsVo.getOverInsurance());
			infoSearch.setImage(goodsCarsVo.getImage());
			infoSearch.setDiscountPriceOnLine(goodsCarsVo.getDiscountPriceOnLine());
			infoSearch.setBareCarPriceOnLine(goodsCarsVo.getBareCarPriceOnLine());
			infoSearch.setLogisticsPrice(goodsCarsVo.getLogisticsPrice());
			amount = amount.add(goodsCarsVo.getGuidingPrice().add(goodsCarsVo.getDiscountPriceOnLine()));
			depositPrice = depositPrice.add(goodsCarsVo.getDepositPrice());
			discountPriceOnLine = discountPriceOnLine.add(goodsCarsVo.getDiscountPriceOnLine());
			bareCarPriceOnLine = bareCarPriceOnLine.add(goodsCarsVo.getBareCarPriceOnLine());
			if(goodsCarsVo.getLogisticsPrice() != null) {
				logisticsPrice = logisticsPrice.add(goodsCarsVo.getLogisticsPrice());
			}
			advanceOrder.setOrderSource(1);//来自上架商品
			advanceOrder.setOrgCode(goodsCarsVo.getOrgCode());
			advanceOrder.setOrderSourceId(goodsCarsVo.getGoodsCarsId());
			advanceOrder.setSystemUserId(goodsCarsVo.getSystemUsersId());
			advanceOrder.setSystemUserName(goodsCarsVo.getUsersVo().getRealName());
//			if(users.getUserType().equals(1)) {//普通用户
//				advanceOrder.setOrgId(goodsCarsVo.getOrgId());
//				advanceOrder.setOrgName(goodsCarsVo.getOrgName());
//			}else {//B端用户
//				advanceOrder.setOrgId(goodsCarsVo.getOrgId());
//				advanceOrder.setOrgName(goodsCarsVo.getOrgName());
//			}
			advanceOrder.setOrgId(goodsCarsVo.getOrgId());
			advanceOrder.setOrgName(goodsCarsVo.getOrgName());
		}else {
			result.setError("抱歉，预约来源错误，请刷新重试");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getRealName())) {
			advanceOrder.setRealName(search.getRealName());
		}else {
			result.setError("请填写客户姓名");
			return result;
		}
		advanceOrder.setPhoneNumber(users.getPhoneNumber());//为保证数据完整性，电话号码贯穿始终，不能进行修改
//		if(StringUtil.isNotEmpty(search.getPhoneNumber())) {
//			advanceOrder.setPhoneNumber(search.getPhoneNumber());
//		}else {
//			result.setError("请填写客户电话");
//			return result;
//		}
		if(StringUtil.isNotEmpty(search.getExpectBuyWay())) {
			advanceOrder.setExpectBuyWay(search.getExpectBuyWay());
		}else {
			result.setError("请选择购车方式");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getExpectPayWay())) {
			advanceOrder.setExpectPayWay(search.getExpectPayWay());
		}else {
			result.setError("请选择付款方式");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getRemarks())) {
			advanceOrder.setRemarks(search.getRemarks());
		}
		if(StringUtil.isNotEmpty(search.getAppointmentDate())) {			
			advanceOrder.setAppointmentDate(DateUtil.format(search.getAppointmentDate(),"yyyy-MM-dd"));
		}

		advanceOrder.setAmount(amount.multiply(new BigDecimal(search.getNumber())));//金额*车辆数
		advanceOrder.setDepositPrice(depositPrice.multiply(new BigDecimal(search.getNumber())));//金额*车辆数
		
		List<ShopAdvanceOrderInfoVo> orderInfoVos = new ArrayList<>();
		for(int i=0;i<search.getNumber();i++) {
			ShopAdvanceOrderInfoVo infoVo = new ShopAdvanceOrderInfoVo();
			infoVo.setCarsId(infoSearch.getCarsId());
			infoVo.setBrandId(infoSearch.getBrandId());
			infoVo.setFamilyId(infoSearch.getFamilyId());
			infoVo.setCarsName(infoSearch.getCarsName());
			infoVo.setDepositPrice(infoSearch.getDepositPrice());
			infoVo.setInteriorId(infoSearch.getInteriorId());
			infoVo.setColourName(infoSearch.getColourName());
			infoVo.setGuidingPrice(infoSearch.getGuidingPrice());
			infoVo.setInvoicePrice(infoSearch.getInvoicePrice());
			infoVo.setOverInsurance(infoSearch.getOverInsurance());	
			infoVo.setColourId(infoSearch.getColourId());
			infoVo.setInteriorName(infoSearch.getInteriorName());
			infoVo.setImage(infoSearch.getImage());
			infoVo.setDiscountPriceOnLine(infoSearch.getDiscountPriceOnLine());
			infoVo.setBareCarPriceOnLine(infoSearch.getBareCarPriceOnLine());
			infoVo.setLogisticsPrice(infoSearch.getLogisticsPrice());
			orderInfoVos.add(infoVo);
		}
		try {
			if(StringUtil.isNotEmpty(advanceOrder.getAdvanceOrderId())) {//如果是编辑
				for(ShopAdvanceOrderInfoVo infoVo : infoVos) {//删除旧数据
					infoVo.setOverDelete(true);
					if(!orderInfoDao.updateById(infoVo)) {
						throw new Exception("删除子表，手动回滚");
					}
				}
				if(!shopAdvanceOrderDao.updateById(advanceOrder)) {
					throw new Exception("更新失败，手动回滚");
				}
			}else {
				if(!shopAdvanceOrderDao.insert(advanceOrder)) {
					throw new Exception("插入失败，手动回滚");
				}
			}
			for(ShopAdvanceOrderInfoVo infoVo : orderInfoVos) {//删除旧数据
				infoVo.setOverDelete(false);
				infoVo.setCreateDate(new Date());
				infoVo.setAdvanceOrderId(advanceOrder.getAdvanceOrderId());
				if(!orderInfoDao.insert(infoVo)) {
					throw new Exception("插入子表失败，手动回滚");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		result.setOK(ResultCode.CODE_STATE_200, "", advanceOrder);//advanceOrder.getOrderCode()
		return result;
	}

	@Override
	public Result orgAdvanceOrderList(ShopAdvanceOrderSearch search, Result result, SystemUsers users)
			throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("keywords", search.getKeywords());
		params.put("startDate", search.getStartDate());
		params.put("endDate", search.getEndDate());
		params.put("overPay", search.getOverPay());
		params.put("orgId", users.getOrgId());
		params.put("createOder", true);
		List<ShopAdvanceOrderVo> advanceOrderVos = shopAdvanceOrderDao.selectJoin(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", advanceOrderVos);
		return new Result(returnMap);
	}
	
	/**
	 * 2018 05 04 蛋疼的需求，冗余处理
	 */
	@Override
	public Result orgAdvanceOrderList(ShopAdvanceOrderSearch search, Result result, SystemUsersVo users,Boolean isbreak)
			throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
				search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("keywords", search.getKeywords());
		params.put("startDate", search.getStartDate());
		params.put("endDate", search.getEndDate());
		params.put("overPay", search.getOverPay());
		params.put("orgId", users.getOrgId());
		params.put("createOder", true);
		List<String> strings = new ArrayList<>();
		strings.add("总经理");
		strings.add("IT部");
		strings.add("仓管");
		strings.add("仓管主管");
		strings.add("B端客户总监");
		strings.add("销售经理");
		if(!strings.contains(users.getRoleName())) {
			params.put("systemUserId", users.getUsersId());			
		}
		List<ShopAdvanceOrderVo> advanceOrderVos = shopAdvanceOrderDao.selectJoin(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", advanceOrderVos);
		return new Result(returnMap);
	}

	@Override
	public Result orgAdvanceOrderDel(ShopAdvanceOrderSearch search, Result result, ShopUsers users) throws Exception {
		ShopAdvanceOrderVo advanceOrder = shopAdvanceOrderDao.selectById(search.getAdvanceOrderId());
		if(advanceOrder == null) {
			result.setError("抱歉，你选择得预约单不存在或者已删除");
			return result;
		}
		if(advanceOrder.getOverPay() || advanceOrder.getOverDelete() || advanceOrder.getOverCatch()) {
			result.setError("抱歉，该预约已锁定，已不能进行修改");
			return result;
		}
		if(!advanceOrder.getShopUserId().equals(users.getShopUserId())) {
			result.setError("抱歉，你没有更改此数据得权限");
			return result;
		}
		advanceOrder.setOverDelete(true);
		return save(advanceOrder, result, search.getAdvanceOrderId());
	}

	@Override
	public Result orgAdvanceOrderInfo(ShopAdvanceOrderSearch search, Result result) throws Exception {
		ShopAdvanceOrderVo advanceOrder = shopAdvanceOrderDao.selectByIdJoin(search.getAdvanceOrderId());
		if(advanceOrder == null) {
			result.setError("抱歉，你选择得预约单不存在或者已删除");
			return result;
		}
		OrganizationVo organizationVo = organizationDao.selectById(advanceOrder.getOrgId());
		if(organizationVo != null) {
			advanceOrder.setOrgLinker(organizationVo.getLinkMan());
			advanceOrder.setOrgPhone(organizationVo.getTelePhone());
			advanceOrder.setOrgName(organizationVo.getShortName());
			advanceOrder.setOrgId(organizationVo.getOrgId());
		}
		if(advanceOrder.getUserType().equals(2)) {
			Map<String, Object> map = new HashMap<>();
			ShopUsers shopUsers = shopUsersDao.selectById(advanceOrder.getShopUserId());
//			map.put("telePhone", advanceOrder.getPhoneNumber());
//			List<OrganizationVo> organizationVos = organizationDao.select(map);
			if(StringUtil.isEmpty(shopUsers.getOrgId())) {
				result.setError("请在个人中心中先完善店铺信息");
				return result;
			}
			OrganizationVo organization = organizationDao.selectById(shopUsers.getOrgId());
			advanceOrder.setOrganizationVo(organization);
		}else if(advanceOrder.getUserType().equals(1) && advanceOrder.getOrgId().equals(61)) {//为了兼容二级开C端订单，这里虚拟一个门店出来当做二级的客户
			OrganizationVo organizationVos = organizationDao.selectById(123);
			advanceOrder.setOrganizationVo(organizationVos);
		}
//		ShopUsers shopUsers = shopUsersDao.selectById(advanceOrder.getShopUserId());
//		if(shopUsers != null) {
//			advanceOrder.setIdCardPicOn(shopUsers.getid);
//		}
		result.setOK(ResultCode.CODE_STATE_200, "", advanceOrder);
		return result;
	}

	@Override
	public Result orgAdvanceOrderInpay(ShopAdvanceOrderSearch search, Result result, ShopUsers users) throws Exception {
		if(StringUtil.isEmpty(search.getAdvanceOrderId()) && StringUtil.isNotEmpty(search.getOrderId())) {
			search.setAdvanceOrderId(search.getOrderId());
		}
		ShopAdvanceOrderVo advanceOrder = shopAdvanceOrderDao.selectByIdJoin(search.getAdvanceOrderId());
		if(advanceOrder == null) {
			result.setError("抱歉，你选择得预约单不存在或者已删除");
			return result;
		}
		if(!advanceOrder.getShopUserId().equals(users.getShopUserId())) {
			result.setError("抱歉，你没有此项权限");
			return result;
		}
		if(advanceOrder.getOverPay()) {
			result.setError("该单已支付过定金，请勿重复支付");
			return result;
		}
		OrganizationVo organizationVo = organizationDao.selectById(advanceOrder.getOrgId());
		if(organizationVo == null) {
			result.setError("抱歉，该门店暂停服务，发起支付失败");
			return result;
		}
		if(organizationVo.getStatus().equals(GeneralConstant.Org.audited)) {
			result.setError("抱歉，该门店尚未开放，发起支付失败");
			return result;
		}
		if(StringUtil.isEmpty(organizationVo.getAllInPayMerchant()) || StringUtil.isEmpty(organizationVo.getAllInPayAppId()) || StringUtil.isEmpty(organizationVo.getAllInPaySecretKey())) {
			result.setError(ResultCode.CODE_STATE_4008, "抱歉，该门店尚未开放线上支付");
			return result;
		}
		if(advanceOrder.getDepositPrice() == null || advanceOrder.getDepositPrice().doubleValue() <= 0) {
			result.setError(ResultCode.CODE_STATE_4008, "订单金额错误，请核对订单");
			return result;
		}
		ShopAdvanceOrderPayment orderPayment = new ShopAdvanceOrderPayment();
		orderPayment.setAdvanceOrderCode(advanceOrder.getOrderCode());
		orderPayment.setAdvanceOrderId(advanceOrder.getAdvanceOrderId());
		orderPayment.setAdvanceOrderState(advanceOrder.getOrderStatus());
		orderPayment.setAmount(advanceOrder.getDepositPrice());
		orderPayment.setCreateDate(new Date());
		orderPayment.setOrderInPayCode(orderPaymentDao.getCode());
		orderPayment.setOrderInPayState(GeneralConstant.OrderInPayState.un_paid);
		if(!orderPaymentDao.insert(orderPayment)) {
			result.setError(ResultCode.CODE_STATE_4008, "创建支付单失败");
			return result;
		}
		String url = "https://vsp.allinpay.com/apiweb/unitorder/pay";
		String notifyUrl = "https://tomcat.xfnauto.com/tauto/allInPayPos/resultPay_noSing";
		main.com.allInPay.utils.HttpConnectionUtil http = new main.com.allInPay.utils.HttpConnectionUtil(url);
		http.init();
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("cusid", organizationVo.getAllInPayMerchant());
		params.put("appid", organizationVo.getAllInPayAppId());
		params.put("version", "11");
		params.put("trxamt", advanceOrder.getDepositPrice().multiply(new BigDecimal(100)).intValue()+"");
		params.put("reqsn", orderPayment.getOrderInPayCode());
		params.put("paytype", "W02");
		params.put("randomstr", PayUtils.createNoncestr());
		params.put("body", "定金");
		params.put("remark", "定金");
		params.put("acct", users.getOpenId());
		params.put("authcode", "authcode");
		params.put("notify_url",notifyUrl);
		params.put("limit_pay", "");
		params.put("sign", main.com.allInPay.utils.SybUtil.sign(params,organizationVo.getAllInPaySecretKey()));
		byte[] bys = http.postParams(params, true);
		String result_it = new String(bys,"UTF-8");
		Map<String,Object> map = handleResult(result_it,organizationVo.getAllInPaySecretKey());		 
//		 JSONObject jsonObject = JSONObject.fromObject(map);//把Map转成JSON
		 
		System.out.println("发起通联微信支付");
		System.out.println("请求的地址：" + url);
		System.out.println("请求的参数：" + params.toString());
		System.out.println("通联返回的map:"+map);	
		if(map.containsKey("retcode") && "FAIL".equals(map.get("retcode"))) {
			result.setError(map.get("retmsg"));
			return result;
		}else {
			JSONObject payinfo = JSONObject.fromObject(main.com.allInPay.utils.SybUtil.json2Obj(map.get("payinfo").toString(), Map.class));
			map.remove("payinfo");
			map.put("payinfo", payinfo);
			result.setOK(ResultCode.CODE_STATE_200, "",map);
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String,Object> handleResult(String result,String key) throws Exception{
		Map map = main.com.allInPay.utils.SybUtil.json2Obj(result, Map.class);
		if(map == null){
			throw new Exception("返回数据错误");
		}
		if("SUCCESS".equals(map.get("retcode"))){			
			TreeMap tmap = new TreeMap();
			tmap.putAll(map);
			String sign = tmap.remove("sign").toString();
			String sign1 = main.com.allInPay.utils.SybUtil.sign(tmap,key);
			if(sign1.toLowerCase().equals(sign.toLowerCase())){
				return map;
			}else{
				throw new Exception("验证签名失败");
			}
		}else{
			throw new Exception(map.get("retmsg").toString());
		}
	}

	@Override
	public Result myOrgAdvanceOrderList(ShopAdvanceOrderSearch search, Result result, ShopUsers users) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		params.put("keywords", search.getKeywords());
		params.put("shopUserId", users.getShopUserId());
		params.put("startDate", search.getStartDate());
		params.put("endDate", search.getEndDate());
		params.put("overPay", search.getOverPay());
		params.put("createOder", true);
		List<ShopAdvanceOrderVo> advanceOrderVos = shopAdvanceOrderDao.selectJoin(params);
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", advanceOrderVos);
		return new Result(returnMap);
	}

	@Override
	public Result myOrderList(ShopAdvanceOrderSearch search, Result result, ShopUsers users) throws Exception {
		if(users.getUserType().equals(1)) {//如果是普通商城用户 C端
			CustomerOrderSearch orderSearch = new CustomerOrderSearch();
			orderSearch.setKeywords(search.getKeywords());
			orderSearch.setPhoneNumber(users.getPhoneNumber());
			result = customerOrderList(orderSearch, result);			
//			if() {
//				
//			}
		}else if(users.getUserType().equals(2)) {
			ListOrderSearch orderSearch = new ListOrderSearch();
			orderSearch.setKeywords(search.getKeywords());
//			orderSearch.setPhoneNumber(users.getPhoneNumber());
			orderSearch.setOrgId(users.getOrgId());
			result = listOrdersB(orderSearch, result);
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "你的用户数据不完整，请联系客户："+GeneralConstant.KEFU);
		}
		return result;
	}
	
	public Result listOrdersB(ListOrderSearch search, Result result) {
		if(search.getKeywords() != null && !search.getKeywords().isEmpty()) {
			search.setKeywords(search.getKeywords().replaceAll(" ", ""));
		}
		Map<String, Object> orgParams = new HashMap<>();
		orgParams.put("orgId", search.getOrgId());
		orgParams.put("allstatus", true);
		orgParams.put("status", true);
		List<OrganizationVo> organizations = organizationDao.select(orgParams);
		if(organizations == null || organizations.size() <= 0) {
			result.setError(ResultCode.CODE_STATE_4005, "你的门店尚未审核通过或不存在，暂不支持该操作");
			return result;
		}
		Organization org = organizations.get(0);		
		//三级用户返回空列表
//		if(Objects.equals(org.getOrgLevel(), 3)) {
//			Map<String,Object> returnMap = new HashMap<>();
//			returnMap.put("page", search.getPage());
//			returnMap.put("total", 0);
//			returnMap.put("rows", search.getRows());
//			returnMap.put("list", new ArrayList<>());
//		}
		
		Map<String,Object> m1 = new HashMap<>();
		m1.put("keywords", search.getKeywords());
		m1.put("state", search.getState());
		m1.put("startDate", search.getStartDate());
		m1.put("endDate", search.getEndDate());
		m1.put("orgCodeLevel", search.getOrgCode());
		m1.put("orgId", org.getOrgId());
		//从第几条开始
		m1.put("isDel", 0);
		Page<Object> page = PageHelper.startPage(search.getPage(),
	                search.getRows());
		List<ConsumerOrder> orders = consumerOrderDao.listOrders(m1);
		List<ConsumerOrderVO> orderVOs = new ArrayList<>(orders.size());
		ConvertUtil.listObjectToListObject(orders, orderVOs, ConsumerOrderVO.class);
		for(ConsumerOrderVO o : orderVOs) {
			Map<String,Object> m2 = new HashMap<>();
			m2.put("orderId", o.getId());
			m2.put("isDel", 0);
			List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m2);
			List<ConsumerOrderInfoVO> infoVOs = new ArrayList<>(infos.size());
			ConvertUtil.listObjectToListObject(infos, infoVOs, ConsumerOrderInfoVO.class);
				for(ConsumerOrderInfoVO i : infoVOs) {
					Map<String,Object> m4 = new HashMap<>();
					m4.put("infoId", i.getId());
					m4.put("isDel", 0);
					List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m4);				
					//有车架号才返回具体车辆
					if(cars.stream().map((n) -> n.getStockCarId()).filter((n) -> n != null).count() > 0) {
						List<ConsumerOrderCarVO> carVOs = new ArrayList<>(cars.size());
						ConvertUtil.listObjectToListObject(cars, carVOs, ConsumerOrderCarVO.class);
						for(ConsumerOrderCarVO carVO : carVOs) {
							if(carVO.getStockCarId() != null) {
								StockCar stockCar = stockCarDao.selectById(carVO.getStockCarId());
								StockCarVo stockCarVO = new StockCarVo();
								ConvertUtil.objectToObject(stockCar, stockCarVO);
								carVO.setStockCar(stockCarVO);
							}						
						}
						i.setCars(carVOs);
					}
				}
			o.setInfos(infoVOs);
			o.setOrderStateName(ConsumerOrderState.getMsgByCode(o.getState()));
			if(o.getCountermandApply() != null && o.getCountermandApply()) {
				o.setOrderStateName("退款中");
			}
		}		
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", orderVOs);
		return new Result(returnMap);		
	}
	
	public Result listOrdersB(ListOrderSearch search, Result result,Set<Integer> ids) {
		if(search.getKeywords() != null && !search.getKeywords().isEmpty()) {
			search.setKeywords(search.getKeywords().replaceAll(" ", ""));
		}
//		Map<String, Object> orgParams = new HashMap<>();
//		orgParams.put("phone", search.getPhoneNumber());
//		orgParams.put("allstatus", true);
//		orgParams.put("status", true);
//		List<OrganizationVo> organizations = organizationDao.select(orgParams);
		OrganizationVo org = organizationDao.selectById(123);
//		if(organizations == null || organizations.size() <= 0) {
//			result.setError(ResultCode.CODE_STATE_4005, "你的门店尚未审核通过或不存在，暂不支持该操作");
//			return result;
//		}
//		Organization org = organizations.get(0);		
		//三级用户返回空列表
//		if(Objects.equals(org.getOrgLevel(), 3)) {
//			Map<String,Object> returnMap = new HashMap<>();
//			returnMap.put("page", search.getPage());
//			returnMap.put("total", 0);
//			returnMap.put("rows", search.getRows());
//			returnMap.put("list", new ArrayList<>());
//		}
		
		Map<String,Object> m1 = new HashMap<>();
//		m1.put("keywords", search.getKeywords());
//		m1.put("state", search.getState());
//		m1.put("startDate", search.getStartDate());
//		m1.put("endDate", search.getEndDate());
//		m1.put("orgCodeLevel", search.getOrgCode());
		m1.put("orgId", org.getOrgId());
		m1.put("ids",ids);
		//从第几条开始
		m1.put("isDel", 0);
		Page<Object> page = PageHelper.startPage(search.getPage(),
				search.getRows());
		List<ConsumerOrder> orders = consumerOrderDao.listOrders(m1);
		List<ConsumerOrderVO> orderVOs = new ArrayList<>(orders.size());
		ConvertUtil.listObjectToListObject(orders, orderVOs, ConsumerOrderVO.class);
		for(ConsumerOrderVO o : orderVOs) {
			Map<String,Object> m2 = new HashMap<>();
			m2.put("orderId", o.getId());
			m2.put("isDel", 0);
			List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m2);
			List<ConsumerOrderInfoVO> infoVOs = new ArrayList<>(infos.size());
			ConvertUtil.listObjectToListObject(infos, infoVOs, ConsumerOrderInfoVO.class);
			for(ConsumerOrderInfoVO i : infoVOs) {
				Map<String,Object> m4 = new HashMap<>();
				m4.put("infoId", i.getId());
				m4.put("isDel", 0);
				List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m4);				
				//有车架号才返回具体车辆
				if(cars.stream().map((n) -> n.getStockCarId()).filter((n) -> n != null).count() > 0) {
					List<ConsumerOrderCarVO> carVOs = new ArrayList<>(cars.size());
					ConvertUtil.listObjectToListObject(cars, carVOs, ConsumerOrderCarVO.class);
					for(ConsumerOrderCarVO carVO : carVOs) {
						if(carVO.getStockCarId() != null) {
							StockCar stockCar = stockCarDao.selectById(carVO.getStockCarId());
							StockCarVo stockCarVO = new StockCarVo();
							ConvertUtil.objectToObject(stockCar, stockCarVO);
							carVO.setStockCar(stockCarVO);
						}						
					}
					i.setCars(carVOs);
				}
			}
			o.setInfos(infoVOs);
			o.setOrderStateName(ConsumerOrderState.getMsgByCode(o.getState()));
			if(o.getCountermandApply() != null && o.getCountermandApply()) {
				o.setOrderStateName("退款中");
			}
		}		
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", orderVOs);
		return new Result(returnMap);		
	}
	
	public Result customerOrderList(CustomerOrderSearch search, Result result) throws Exception {
		Page<Object> page = PageHelper.startPage(search.getPage(),
                search.getRows());
		Map<String,Object> params = new HashMap<>();
		if(StringUtil.isNotEmpty(search.getCustomerOrderCode())) {
			params.put("keywords", search.getKeywords());
		}
		params.put("phoneNumber", search.getPhoneNumber());
		
		List<CustomerOrderVo> customerOrderVos = customerOrderDao.select(params);
		
//		Map<String,Object> carParams = new HashMap<String, Object>();
//		Set<Integer> set = new HashSet<>();
//		for(CustomerOrderVo orderVo : customerOrderVos) {
//			set.add(orderVo.getStockCarId());
//		}
//		if(set != null && set.size() > 0) {
//			carParams.put("ids", set);
//		}
//		List<StockCarVo> cars = stockCarDao.selectByOrg(carParams);
//		for(CustomerOrderVo orderVo : customerOrderVos) {
//			for(StockCarVo carVo : cars) {
//				if(orderVo.getCarsId().equals(carVo.getCarsId()) && orderVo.getColourId().equals(carVo.getColourId()) && orderVo.getInteriorId().equals(carVo.getInteriorId())) {
//					orderVo.setStockCarNumber(orderVo.getStockCarNumber() + carVo.getNumber());
//				}
//			}
//		}
		Map<String,Object> returnMap = new HashMap<>();
		returnMap.put("page", search.getPage());
		returnMap.put("total", page.getTotal());
		returnMap.put("rows", search.getRows());
		returnMap.put("list", customerOrderVos);
		return new Result(returnMap);
	}

	@Override
	public Result myOrderInfo(ShopAdvanceOrderSearch search, Result result, ShopUsers users) throws Exception {
		if(users.getUserType().equals(1)) {//如果是普通商城用户 C端
			CustomerOrderSearch orderSearch = new CustomerOrderSearch();
			orderSearch.setCustomerOrderId(search.getOrderId());
			result = customerOrderInfo(orderSearch, result);
		}else if(users.getUserType().equals(2)) {
			result = getOrderDetail(search.getOrderId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "你的用户数据不完整，请联系客户："+GeneralConstant.KEFU);
		}
		return result;
	}	
	
	public Result customerOrderInfo(CustomerOrderSearch search, Result result) throws Exception {
		CustomerOrderVo customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("customerOrderCode",customerOrder.getCustomerOrderCode());
//		map.put("createDate", DateUtil.format(customerOrder.getCreateDate(),"yyyy-MM-dd"));//
//		map.put("customerOrderId", customerOrder.getCustomerOrderId());//
//		map.put("interiorId", customerOrder.getInteriorId());//
//		map.put("interiorName", customerOrder.getInteriorName());//
//		map.put("carsName", customerOrder.getCarsName());
//		map.put("carsId", customerOrder.getCarsId());
//		map.put("colourId", customerOrder.getColourId());
//		map.put("colourName", customerOrder.getColourName());
//		map.put("remark", customerOrder.getRemarks());
//		map.put("customerUserCard", customerOrder.getCustomerUserCard());//购买者身份证号
//		map.put("number", 1);
//		map.put("customerOrderType", 1);
//		if(customerOrder.getCarsVo() != null) {
//			map.put("familyId", customerOrder.getCarsVo().getFamilyId());
//			map.put("guidingPrice", customerOrder.getCarsVo().getPrice());
//		}else {
//			map.put("familyId", "");
//			map.put("guidingPrice", "");
//		}
//		Organization organization = organizationDao.selectById(customerOrder.getOrgId());
//		if(organization != null) {
//			map.put("crgId", customerOrder.getOrgId());
//			map.put("orgId", customerOrder.getOrgId());
//			map.put("orgName", customerOrder.getOrgName());
//			map.put("address", organization.getProvinceName() + organization.getCityName() + organization.getAreaName() + organization.getAddress());
//		}else {
//			map.put("crgId", "");
//			map.put("orgId", "");
//			map.put("orgName", "");
//			map.put("address", "");
//		}
		if(StringUtil.isNotEmptyMoreThenZero(customerOrder.getStockCarId())) {
			StockCar stockCarVo = stockCarDao.getById(customerOrder.getStockCarId());
			customerOrder.setStockCarVo(stockCarVo);
//			map.put("frameNumber", stockCarVo.getFrameNumber());
		}else {
//			map.put("frameNumber", "");
		}
		result.setOK(ResultCode.CODE_STATE_200, "", customerOrder);
//		result.setOK(ResultCode.CODE_STATE_200, "", TakeCareMapDate.cutNullMap(map));
		return result;
	}
	
	public Result getOrderDetail(Integer orderId) {
		ConsumerOrder order = consumerOrderDao.selectById(orderId);
		if(order == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		
		ConsumerOrderVO orderVO = new ConsumerOrderVO();
		ConvertUtil.objectToObject(order, orderVO);
		
		//获取支付信息
		Map<String,Object> m1 = new HashMap<>();
		m1.put("orderId", order.getId());
		m1.put("isDel", 0);
		List<ConsumerOrderPayment> consumerOrderPayments = consumerOrderPaymentDao.select(m1);
		List<ConsumerOrderPaymentVO> consumerOrderPaymentVOs = new ArrayList<>(consumerOrderPayments.size());
		ConvertUtil.listObjectToListObject(consumerOrderPayments, consumerOrderPaymentVOs, ConsumerOrderPaymentVO.class);
		orderVO.setOrderPaymentVOs(consumerOrderPaymentVOs);
		
		//获取客户
		Map<String,Object> m2 = new HashMap<>();
		m2.put("orderId", order.getId());
		m2.put("type", 1);//客户
		m2.put("isDel", 0);
		List<ConsumerOrderUser> customers = consumerOrderUesrDao.select(m2);
		List<ConsumerOrderUserVO> customerVOs = new ArrayList<>(customers.size());
		ConvertUtil.listObjectToListObject(customers, customerVOs, ConsumerOrderUserVO.class);
		orderVO.setCustomers(customerVOs);
		orderVO.setOrderStateName();
		for(ConsumerOrderUserVO u : customerVOs) {
			Map<String,Object> m3 = new HashMap<>();
			m3.put("orderId", orderVO.getId());
			m3.put("customerId", u.getId());
			m3.put("isDel", 0);
			List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m3);
			List<ConsumerOrderInfoVO> infoVOs = new ArrayList<>(infos.size());
			ConvertUtil.listObjectToListObject(infos, infoVOs, ConsumerOrderInfoVO.class);
			for(ConsumerOrderInfoVO i : infoVOs) {
				Map<String,Object> m4 = new HashMap<>();
				m4.put("infoId", i.getId());
				m4.put("isDel", 0);
				List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m4);
				
				//有车架号才返回具体车辆
				if(cars.stream().map((n) -> n.getStockCarId()).filter((n) -> n != null).count() > 0) {
					List<ConsumerOrderCarVO> carVOs = new ArrayList<>(cars.size());
					ConvertUtil.listObjectToListObject(cars, carVOs, ConsumerOrderCarVO.class);
					for(ConsumerOrderCarVO carVO : carVOs) {
						if(carVO.getStockCarId() != null) {
							StockCar stockCar = stockCarDao.selectById(carVO.getStockCarId());
							StockCarVo stockCarVO = new StockCarVo();
							ConvertUtil.objectToObject(stockCar, stockCarVO);
							carVO.setStockCar(stockCarVO);
						}						
					}
					i.setCars(carVOs);
				}
				
			}
			u.setInfos(infoVOs);
		}
			
		//获取金额
		Map<String,BigDecimal> m = getOrderAmount(order);
		orderVO.setTotalDepositPrice(m.get("totalDepositPrice").toString());
		orderVO.setTotalRestPrice(m.get("totalRestPrice").toString());
		orderVO.setTotalFinalPrice(m.get("totalFinalPrice").toString());
		if(order.getFreight() != null) {
			orderVO.setFreight(order.getFreight().toString());
		}
		
		//获取提车人
		Map<String,Object> m3 = new HashMap<>();
		m3.put("orderId", order.getId());
		m3.put("type", 2);//提车人
		m3.put("isDel", 0);
		List<ConsumerOrderUser> pickers = consumerOrderUesrDao.select(m3);
		List<ConsumerOrderUserVO> pickerVOs = new ArrayList<>(customers.size());
		ConvertUtil.listObjectToListObject(pickers, pickerVOs, ConsumerOrderUserVO.class);
		orderVO.setPickers(pickerVOs);
		
		//获取公司甲方、户名、银行卡信息
		orderVO.setPartyA(SystemConfig.get("partyA"));
//		System.out.println("SystemConfig.get(\"partyA\"):"+SystemConfig.get("partyA"));
		orderVO.setBankAccountName(SystemConfig.get("bankAccountName"));
//		System.out.println("bankAccountName:"+SystemConfig.get("bankAccountName"));
		orderVO.setBankBranch(SystemConfig.get("bankBranch"));
//		System.out.println("bankBranch:"+SystemConfig.get("bankBranch"));
		orderVO.setBankCardNum(SystemConfig.get("bankCardNum"));
//		System.out.println("bankCardNum"+SystemConfig.get("bankCardNum"));
//		System.out.println("电子合同值返回完毕");
		return new Result(orderVO);	
	}
	
	/**
	 * 获取订单金额信息
	 * @param order
	 * @return
	 */
	private Map<String,BigDecimal> getOrderAmount(ConsumerOrder order) {
		
		Map<String,Object> m2 = new HashMap<>();
		m2.put("orderId", order.getId());
		m2.put("type", 1);//客户
		m2.put("isDel", 0);
		List<ConsumerOrderUser> customers = consumerOrderUesrDao.select(m2);
		List<ConsumerOrderUserVO> customerVOs = new ArrayList<>(customers.size());
		ConvertUtil.listObjectToListObject(customers, customerVOs, ConsumerOrderUserVO.class);
		List<BigDecimal> infoDepositPrice = new ArrayList<>();
		List<BigDecimal> infoFinalPrice = new ArrayList<>();
		List<BigDecimal> infoNakedPrice = new ArrayList<>();
		List<BigDecimal> infoTrafficCompulsoryInsurancePrice = new ArrayList<>();
		List<BigDecimal> infoCommercialInsurancePrice = new ArrayList<>();
		List<BigDecimal> infoChangePrice = new ArrayList<>();
		
		for(ConsumerOrderUserVO c: customerVOs) {
			//获取客户下订单
			Map<String,Object> m3 = new HashMap<>();
			m3.put("orderId", order.getId());
			m3.put("customerId", c.getId());
			m3.put("isDel", 0);
			List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m3);
			List<ConsumerOrderInfoVO> infoVOs = new ArrayList<>(infos.size());
			ConvertUtil.listObjectToListObject(infos, infoVOs, ConsumerOrderInfoVO.class);
			//客户单总裸车价
			BigDecimal infoTotalNakedPrice = 
					infos.stream().filter(i -> i.getNakedPrice() != null).map((i) -> i.getNakedPrice()).reduce(BigDecimal.ZERO,BigDecimal::add);
			//客户单总交强险
			BigDecimal infoTotalTrafficCompulsoryInsurancePrice = 
					infos.stream().filter(i -> i.getTrafficCompulsoryInsurancePrice() != null).map((i) -> i.getTrafficCompulsoryInsurancePrice()).reduce(BigDecimal.ZERO,BigDecimal::add);			
			//客户单总商业险
			BigDecimal infoTotalCommercialInsurancePrice = 
					infos.stream().filter(i -> i.getCommercialInsurancePrice() != null).map((i) -> i.getCommercialInsurancePrice()).reduce(BigDecimal.ZERO,BigDecimal::add);
			//客户单总定金
			BigDecimal infoTotalDepositPrice = 
					infos.stream().filter(i -> i.getDepositPrice() != null).map((i) -> i.getDepositPrice()).reduce(BigDecimal.ZERO,BigDecimal::add);
			//客户单总优惠金额
			BigDecimal infoTotalChangePrice = 
					infos.stream().filter(i -> i.getChangePrice() != null).map((i) -> i.getChangePrice()).reduce(BigDecimal.ZERO,BigDecimal::add);
			
			infoDepositPrice.add(infoTotalDepositPrice);
			infoNakedPrice.add(infoTotalNakedPrice);
			infoTrafficCompulsoryInsurancePrice.add(infoTotalTrafficCompulsoryInsurancePrice);
			infoCommercialInsurancePrice.add(infoTotalCommercialInsurancePrice);
			infoChangePrice.add(infoTotalChangePrice);
			c.setInfos(infoVOs);
		}
		
		//总定金
		BigDecimal totalDepositPrice = infoDepositPrice.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
		totalDepositPrice.setScale(2,BigDecimal.ROUND_UP);
		//总裸车价
		BigDecimal totalNakedPrice = infoNakedPrice.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
		totalNakedPrice.setScale(2,BigDecimal.ROUND_UP);
		//总交强险
		BigDecimal totalTrafficCompulsoryInsurancePrice = infoTrafficCompulsoryInsurancePrice.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
		totalTrafficCompulsoryInsurancePrice.setScale(2,BigDecimal.ROUND_UP);
		//总商业险
		BigDecimal totalCommercialPrice = infoCommercialInsurancePrice.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
		totalCommercialPrice.setScale(2,BigDecimal.ROUND_UP);
		//总价格变动
		BigDecimal totalChangePrice = infoChangePrice.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
		totalChangePrice.setScale(2,BigDecimal.ROUND_UP);
		
		//最终成交价=裸车价+交强险+商业险
		//订单总额=最终成交价之和+物流费用
		//最终价格
		BigDecimal totalFinalPrice = //totalDepositPrice
				 new BigDecimal(0)
				.add(totalNakedPrice != null ? totalNakedPrice : new BigDecimal(0))
				.add(totalTrafficCompulsoryInsurancePrice != null ? totalTrafficCompulsoryInsurancePrice : new BigDecimal(0))
				.add(totalCommercialPrice != null ? totalCommercialPrice : new BigDecimal(0))
				//.add(totalChangePrice != null ? totalChangePrice : new BigDecimal(0))
				.add(order.getFreight() != null ? order.getFreight() : new BigDecimal(0));
		//尾款
		BigDecimal totalRestPrice = totalFinalPrice
				.subtract(totalDepositPrice != null ? totalDepositPrice : new BigDecimal(0));
				
			
		totalRestPrice.setScale(2,BigDecimal.ROUND_UP);
		
		Map<String, BigDecimal> map = new HashMap<>();
		//最终总定金
		map.put("totalDepositPrice", totalDepositPrice);
		//最终总裸车价
		map.put("totalNakedPrice", totalNakedPrice);
		//最终总交强险
		map.put("totalTrafficCompulsoryInsurancePrice", totalTrafficCompulsoryInsurancePrice);
		//最终总商业险
		map.put("totalCommercialPrice", totalCommercialPrice);
		//最终总尾款
		map.put("totalRestPrice", totalRestPrice);
		//最终费用（总裸车价+总交强险+总商业险+定金-运费-优惠）
		map.put("totalFinalPrice", totalFinalPrice);
		return map;
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.operDayDate(new Date(), 1));
	}

	@Override
	public Result myOrderPaymentList(CustomerOrderSearch search, Result result, ShopUsers users) throws Exception {
		CustomerOrderVo customerOrderVo = customerOrderDao.selectByIdJoinPayment(search.getCustomerOrderId());
		if(customerOrderVo == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		for(CustomerOrderInPayVo inPayVo : customerOrderVo.getOrderInPayVos()) {
			inPayVo.setPayMethodName(OrderPayMethodName.getMsgByCode(inPayVo.getPayMethod()));
			inPayVo.setOrgId(customerOrderVo.getOrgId());
			inPayVo.setOrgName(customerOrderVo.getOrgName());
		}
		result.setOK(ResultCode.CODE_STATE_200, "", customerOrderVo.getOrderInPayVos());
		return result;
	}
}
