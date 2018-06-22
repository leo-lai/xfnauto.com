package main.com.weixinApp.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fit.cssbox.demo.ImageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jhlabs.composite.SubtractComposite;

import main.com.customer.dao.search.CustomerUsersSearch;
import main.com.exception.BusinessException;
import main.com.frame.constants.ConsumerOrderCarAuditState;
import main.com.frame.constants.ConsumerOrderInfoState;
import main.com.frame.constants.ConsumerOrderState;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.search.BaseSearch;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.pay.allInPay.SystemConfig;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.dao.StockStorageDao;
import main.com.stock.dao.po.ConsumerOrder;
import main.com.stock.dao.po.ConsumerOrderCar;
import main.com.stock.dao.po.ConsumerOrderInfo;
import main.com.stock.dao.po.ConsumerOrderPayment;
import main.com.stock.dao.po.ConsumerOrderUser;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.po.StockStorage;
import main.com.stock.dao.vo.StockCarVo;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemUsersDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.vo.OrganizationVo;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.utils.ConvertUtil;
import main.com.utils.FileInterchangeBytes;
import main.com.utils.QiniuUtils;
import main.com.utils.StringUtil;
import main.com.utils.SystemPath;
import main.com.weixinApp.dao.dao.ConsumerOrderCarDao;
import main.com.weixinApp.dao.dao.ConsumerOrderDao;
import main.com.weixinApp.dao.dao.ConsumerOrderInfoDao;
import main.com.weixinApp.dao.dao.ConsumerOrderPaymentDao;
import main.com.weixinApp.dao.dao.ConsumerOrderUserDao;
import main.com.weixinApp.dao.search.ConsumerOrderSearch;
import main.com.weixinApp.dao.search.ConsumerOrderUpdateLogisticsInfoSearch;
import main.com.weixinApp.dao.search.ConsumerOrderUserSearch;
import main.com.weixinApp.dao.search.CreateConsumerOrderSearch;
import main.com.weixinApp.dao.search.ListOrderSearch;
import main.com.weixinApp.dao.search.OrderUpdateSearch;
import main.com.weixinApp.dao.search.OrderUpdateStateSearch;
import main.com.weixinApp.dao.vo.ConsumerOrderCarVO;
import main.com.weixinApp.dao.vo.ConsumerOrderInfoVO;
import main.com.weixinApp.dao.vo.ConsumerOrderPaymentVO;
import main.com.weixinApp.dao.vo.ConsumerOrderUserVO;
import main.com.weixinApp.dao.vo.ConsumerOrderVO;
import main.com.weixinApp.service.ConsumerOrderService;
import main.com.weixinHtml.dao.dao.ShopAdvanceOrderDao;
import main.com.weixinHtml.dao.dao.ShopUsersDao;
import main.com.weixinHtml.dao.po.ShopAdvanceOrder;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.vo.ShopAdvanceOrderPaymentVo;
import main.com.weixinHtml.dao.vo.ShopAdvanceOrderVo;
@Service
public class ConsumerOrderServiceImpl extends BaseServiceImpl<ConsumerOrder> implements ConsumerOrderService{
	
	@Autowired
	private ConsumerOrderDao consumerOrderDao;
	
	@Autowired
	private ConsumerOrderUserDao consumerOrderUesrDao;
	
	@Autowired
	private ConsumerOrderInfoDao consumerOrderInfoDao;
	
	@Autowired
	private ConsumerOrderCarDao consumerOrderCarDao;
	
	@Autowired
	private ConsumerOrderPaymentDao consumerOrderPaymentDao;
	
	@Autowired
	private StockCarDao stockCarDao;
	
	@Autowired
	private StockStorageDao stockStorageDao;
	
	@Autowired
	private SystemUsersDao systemUsersDao;
	
	@Autowired
	private OrganizationDao organizationDao;
	
	@Autowired
	private ConsumerOrderUserDao consumerOrderUserDao;
	
	@Autowired
	private ShopAdvanceOrderDao shopAdvanceOrderDao;
	
	@Autowired
	private ShopUsersDao shopUsersDao;
	 
	
	@Override
	protected BaseDao<ConsumerOrder> getBaseDao() {
		// TODO Auto-generated method stub
		return consumerOrderDao;
	}
	
	@Override
	public String createOrderCode(String phone) {
		// TODO Auto-generated method stub
	
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String orderCode = "DG" + sdf.format(now) 
			+ phone.substring(phone.length()-4, phone.length())
			+ (int)((Math.random()*9+1)*10);
		Map<String,Object> map = new HashMap<>();
		map.put("orderCode", orderCode);
		List<ConsumerOrder> orders = consumerOrderDao.select(map);
		if(orders != null && !orders.isEmpty()) {
			return createOrderCode(phone);
		}else {
			return orderCode;
		}
	}
	
	@Override
	@Transactional
	public Result createOrder(CreateConsumerOrderSearch search) {
		// TODO Auto-generated method stub
		
		//保存订单
		ConsumerOrder order = new ConsumerOrder();
		ConvertUtil.objectToObject(search, order,false);
		order.setState(ConsumerOrderState.Init.getCode());//设置订单状态为新建
		order.setCreateTime(new Date());
		order.setOrderCode(createOrderCode(search.getOrgPhone()));
		if(order.getOrderType() == null) {
			order.setOrderType(ConsumerOrderState.Routine.getCode());//标记为炒车
		}
		if(!consumerOrderDao.insert(order)) {
			throw new BusinessException(ResultCode.CODE_STATE_4014, "创建订购单失败");
		}
		
		//保存提车人
		List<ConsumerOrderUserSearch> pickerSearchs = search.getPickers();
		if(pickerSearchs != null && !pickerSearchs.isEmpty()) {
			List<ConsumerOrderUser> pickers = new ArrayList<>(pickerSearchs.size());
			ConvertUtil.listObjectToListObject(pickerSearchs, pickers, ConsumerOrderUser.class);
			
			//校验提车人手机号唯一
			List<String> phones = pickers.stream()
					.map((n) -> n.getUserPhone()).distinct().collect(Collectors.toList());
			if(!Objects.equals(phones.size(), pickers.size())) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "提车人手机号不能重复");
			}
			for(ConsumerOrderUser user : pickers) {
				user.setOrderId(order.getId());
				user.setType(2);//提车人
				user.setCreateTime(new Date());
				if(!consumerOrderUesrDao.insert(user))
					throw new BusinessException(ResultCode.CODE_STATE_4014, "保存提车人失败");
			}
		}	
		
		ConsumerOrderVO orderVO = new ConsumerOrderVO();
		ConvertUtil.objectToObject(order, orderVO);
		return new Result(orderVO);		
	}
	
	@Override
	public Result listOrders(ListOrderSearch search,SystemUsers user) {
		// TODO Auto-generated method stub
		if(search.getKeywords() != null && !search.getKeywords().isEmpty()) {
			search.setKeywords(search.getKeywords().replaceAll(" ", ""));
		}
		
		Organization org = organizationDao.selectById(user.getOrgId());
		
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
		
		//如果不属于 仓管，仓管主管，IT部 角色的，就只能看自己的订单
		//查看用户角色
		Map<String,Object> params = new HashMap<>();
		params.put("usersId", user.getUsersId());
		List<SystemUsersVo> systemUsersVos = systemUsersDao.selectJoin(params);
		Boolean isOnlyUserSelf = true;
		if(systemUsersVos != null && systemUsersVos.size() > 0) {
			for(SystemUsersVo systemUsersVo : systemUsersVos) {
				if(StringUtil.isEmpty(systemUsersVo.getRoleName())) {
					continue;
				}
				if(systemUsersVo.getRoleName().equals("仓管") || systemUsersVo.getRoleName().equals("仓管主管") || systemUsersVo.getRoleName().equals("IT部")) {
					isOnlyUserSelf = false;
					break;
				}
			}
		}
		if(isOnlyUserSelf) {
			m1.put("usersId", user.getUsersId());
		}
//		m1.put("orgId", search.getOrgId());
		//从第几条开始
		m1.put("isDel", 0);
//		if(StringUtil.isNotEmpty(search.getFrameNumber())) {//存在车架号搜索
//			1
//		}
		Page<Object> page = PageHelper.startPage(search.getPage(),
	                search.getRows());
		List<ConsumerOrder> orders = consumerOrderDao.mylistOrders(m1);
		List<ConsumerOrderVO> orderVOs = new ArrayList<>(orders.size());
		ConvertUtil.listObjectToListObject(orders, orderVOs, ConsumerOrderVO.class);
		for(ConsumerOrderVO o : orderVOs) {
			Map<String,Object> m2 = new HashMap<>();
			m2.put("orderId", o.getId());
			m2.put("isDel", 0);
			List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m2);
			List<ConsumerOrderInfoVO> infoVOs = new ArrayList<>(infos.size());
			ConvertUtil.listObjectToListObject(infos, infoVOs, ConsumerOrderInfoVO.class);
//			List<ConsumerOrderUser> customers = consumerOrderUesrDao.select(m2);
//			List<ConsumerOrderUserVO> customerVOs = new ArrayList<>(customers.size());
//			ConvertUtil.listObjectToListObject(customers, customerVOs, ConsumerOrderUserVO.class);			
//			for(ConsumerOrderUserVO orderUser : customerVOs) {
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
//					o.setInfos(infoVOs);
				}
//				o.setCustomers(customerVOs);
//			}
			o.setInfos(infoVOs);
//			Map<String,Object> m3 = new HashMap<>();
//			m3.put("orderId", o.getId());
//			m3.put("isDel", 0);
//			List<ConsumerOrderPayment> payments = consumerOrderPaymentDao.select(m3);
//			List<ConsumerOrderPaymentVO> orderPaymentVOs = new ArrayList<>(payments.size());
//			ConvertUtil.listObjectToListObject(payments, orderPaymentVOs, ConsumerOrderPaymentVO.class);
//			o.setOrderPaymentVOs(orderPaymentVOs);
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
	
	@Override
	public Result listOrders(ListOrderSearch search,SystemUsers user,String isPC) {
		// TODO Auto-generated method stub
		if(search.getKeywords() != null && !search.getKeywords().isEmpty()) {
			search.setKeywords(search.getKeywords().replaceAll(" ", ""));
		}
		
		Organization org = organizationDao.selectById(user.getOrgId());
		
		//三级用户返回空列表
//		if(Objects.equals(org.getOrgLevel(), 3)) {
//			Map<String,Object> returnMap = new HashMap<>();
//			returnMap.put("page", search.getPage());
//			returnMap.put("total", 0);
//			returnMap.put("rows", search.getRows());
//			returnMap.put("list", new ArrayList<>());
//		}
//		
		Map<String,Object> m1 = new HashMap<>();
		m1.put("keywords", search.getKeywords());
		m1.put("state", search.getState());
		m1.put("startDate", search.getStartDate());
		m1.put("endDate", search.getEndDate());
//		m1.put("orgCodeLevel", search.getOrgCode());
		m1.put("orgId", org.getOrgId());
		//从第几条开始
		m1.put("isDel", 0);
//		if(StringUtil.isNotEmpty(search.getFrameNumber())) {//存在车架号搜索
//			1
//		}
		Page<Object> page = PageHelper.startPage(search.getPage(),
				search.getRows());
		List<ConsumerOrder> orders = consumerOrderDao.mylistOrders(m1);
		List<ConsumerOrderVO> orderVOs = new ArrayList<>(orders.size());
		ConvertUtil.listObjectToListObject(orders, orderVOs, ConsumerOrderVO.class);
		for(ConsumerOrderVO o : orderVOs) {
			Map<String,Object> m2 = new HashMap<>();
			m2.put("orderId", o.getId());
			m2.put("isDel", 0);
			List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m2);
			List<ConsumerOrderInfoVO> infoVOs = new ArrayList<>(infos.size());
			ConvertUtil.listObjectToListObject(infos, infoVOs, ConsumerOrderInfoVO.class);
			List<ConsumerOrderUser> customers = consumerOrderUesrDao.select(m2);
			List<ConsumerOrderUserVO> customerVOs = new ArrayList<>(customers.size());
			ConvertUtil.listObjectToListObject(customers, customerVOs, ConsumerOrderUserVO.class);			
			for(ConsumerOrderUserVO orderUser : customerVOs) {
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
					orderUser.setInfos(infoVOs);
				}
				o.setCustomers(customerVOs);
			}
//			o.setInfos(infoVOs);
			Map<String,Object> m3 = new HashMap<>();
			m3.put("orderId", o.getId());
			m3.put("isDel", 0);
			List<ConsumerOrderPayment> payments = consumerOrderPaymentDao.select(m3);
			List<ConsumerOrderPaymentVO> orderPaymentVOs = new ArrayList<>(payments.size());
			ConvertUtil.listObjectToListObject(payments, orderPaymentVOs, ConsumerOrderPaymentVO.class);
			o.setOrderPaymentVOs(orderPaymentVOs);
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
	
	@Override
	public Result auditList(BaseSearch search) {
		// TODO Auto-generated method stub
		Map<String,Object> m1 = new HashMap<>();
		m1.put("state", ConsumerOrderState.CarChecking.getCode());
		m1.put("isDel", 0);
		List<ConsumerOrder> orders = consumerOrderDao.select(m1);
		List<ConsumerOrderVO> orderVOs = new ArrayList<>(orders.size());
		List<ConsumerOrderVO> returnOrderVOs = new ArrayList<>();
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
				m4.put("auditState", ConsumerOrderCarAuditState.CarChangeApply.getCode());
				List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m4);
				List<ConsumerOrderCarVO> carVos = new ArrayList<>(cars.size());
				ConvertUtil.listObjectToListObject(cars, carVos, ConsumerOrderCarVO.class);
				i.setCars(carVos);
			}
			o.setInfos(infoVOs);
		}
		
		//过滤
		for(ConsumerOrderVO or : orderVOs) {
			or.setOrderStateName("待审核");
			List<ConsumerOrderInfoVO> is = or.getInfos().stream().filter((n) -> n.getCars().size() > 0).collect(Collectors.toList());
			or.setInfos(is);
		}
		List<ConsumerOrderVO> os = orderVOs.stream().filter((o) -> o.getInfos().size() > 0).collect(Collectors.toList());
		
		return new Result(os);
	}
	
	@Override
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
	
	@Override
	public Result getContractInfo(Integer id) {
		// TODO Auto-generated method stub
		ConsumerOrder order = consumerOrderDao.selectById(id);
		if(order == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		
		ConsumerOrderVO orderVO = new ConsumerOrderVO();
		ConvertUtil.objectToObject(order, orderVO);
		
		//获取金额
		Map<String,BigDecimal> m = getOrderAmount(order);
		orderVO.setTotalDepositPrice(m.get("totalDepositPrice").toString());
		orderVO.setTotalRestPrice(m.get("totalRestPrice").toString());
		orderVO.setTotalFinalPrice(m.get("totalFinalPrice").toString());
		orderVO.setFreight(order.getFreight().toString());
		
		//获取公司甲方、户名、银行卡信息
		orderVO.setPartyA(SystemConfig.get("partyA"));
		orderVO.setBankAccountName(SystemConfig.get("bankAccountName"));
		orderVO.setBankBranch(SystemConfig.get("bankBranch"));
		orderVO.setBankCardNum(SystemConfig.get("bankCardNum"));
		
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
	
	@Override
	public Result update(OrderUpdateSearch search) {
		// TODO Auto-generated method stub
		
		ConsumerOrder order = new ConsumerOrder();
		ConvertUtil.objectToObject(search, order);
		if(!StringUtil.isEmpty(search.getFreight())) {
			order.setFreight(new BigDecimal(search.getFreight()));
		}
		if(!consumerOrderDao.updateById(order)) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "更新订单失败");
		}
		return new Result(ResultCode.CODE_STATE_200,true,"更新订单成功");
	}
	
	@Override
	@Transactional
	public Result updateState(OrderUpdateStateSearch search) throws Exception {
		// TODO Auto-generated method stub
		
		Integer state = search.getState();
		Integer orderId = search.getOrderId();
		
		//校验订单状态
		String msg = ConsumerOrderState.getMsgByCode(state);
		if(msg.isEmpty()) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "无效的订单状态");
		}
		
		ConsumerOrder order = consumerOrderDao.selectById(orderId);
		if(order == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "无此订单");
		}
		
		if(order.getCountermandApply() != null && order.getCountermandApply()) {//新增退款需求  已申请退款的单，不能继续操作往下的步骤了 
			throw new BusinessException(ResultCode.CODE_STATE_4006, "订单正在申请退款，暂不能进行此操作");
		}
		
		//待交定金
		if(Objects.equals(state, ConsumerOrderState.DepositPaying.getCode())) {
			//校验上一次状态
			if(!Objects.equals(order.getState(), ConsumerOrderState.Init.getCode())) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
			}
		}
		//待配车
		if(Objects.equals(state, ConsumerOrderState.CarDistributing.getCode())) {
			if(!Objects.equals(order.getState(), ConsumerOrderState.DepositPaying.getCode())) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
			}		
			//检查定金凭证
			Map<String,Object> m1 = new HashMap<>();
			m1.put("orderId", order.getId());
			m1.put("type", 1);//定金
			m1.put("isDel", 0);
			List<ConsumerOrderPayment> payments = consumerOrderPaymentDao.select(m1);
			if(payments == null || payments.isEmpty()) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "无定金凭证");
			}
		}
		//待验车
		if(Objects.equals(state, ConsumerOrderState.CarChecking.getCode())) {
			if(!Objects.equals(order.getState(), ConsumerOrderState.CarDistributing.getCode())) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
			}
			//校验订单是否已配车
			Map<String,Object> m1 = new HashMap<>();
			m1.put("orderId", order.getId());
			m1.put("isDel", 0);
			List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m1);
			if(infos == null || infos.isEmpty()) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
			}
			for(ConsumerOrderInfo i : infos) {
				Map<String,Object> m2 = new HashMap<>();
				m2.put("infoId", i.getId());
				m2.put("isDel", 0);
				List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m2);
				if(cars == null || cars.isEmpty()) {
					throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
				}
				for(ConsumerOrderCar c : cars) {
					if(!Objects.equals(c.getAuditState(), ConsumerOrderCarAuditState.CarDistributed.getCode())
							|| StringUtil.isEmpty(c.getVin())) {
						throw new BusinessException(ResultCode.CODE_STATE_4006, "请先配完所有车辆");
					}
				}
			}
		}
		//待协商
		if(Objects.equals(state, ConsumerOrderState.Consulting.getCode())) {
			if(!Objects.equals(order.getState(), ConsumerOrderState.CarChecking.getCode())) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
			}
		}
		//待收尾款
		if(Objects.equals(state, ConsumerOrderState.FinalPricePaying.getCode())) {
			if(!(Objects.equals(order.getState(), ConsumerOrderState.Consulting.getCode())
					|| Objects.equals(order.getState(), ConsumerOrderState.CarChecking.getCode()))) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
			}
			//校验订单是否已验车
			Map<String,Object> m1 = new HashMap<>();
			m1.put("orderId", order.getId());
			m1.put("isDel", 0);
			List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m1);
			if(infos == null || infos.isEmpty()) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
			}
			for(ConsumerOrderInfo i : infos) {
				Map<String,Object> m2 = new HashMap<>();
				m2.put("infoId", i.getId());
				m2.put("isDel", 0);
				List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m2);
				if(cars == null || cars.isEmpty()) {
					throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
				}
				for(ConsumerOrderCar c : cars) {
					if(!Objects.equals(c.getAuditState(), ConsumerOrderCarAuditState.Audited.getCode())
							) {
						throw new BusinessException(ResultCode.CODE_STATE_4006, "该订单还有车辆未验车");
					}
					if(StringUtil.isEmpty(c.getVin())) {
						throw new BusinessException(ResultCode.CODE_STATE_4006, c.getCarsName() + "车架号为空");
					}
				}
			}
		}
		//待出库
		if(Objects.equals(state, ConsumerOrderState.StockOuting.getCode())) {
			if(!Objects.equals(order.getState(), ConsumerOrderState.FinalPricePaying.getCode())) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
			}		
			//检查尾款凭证
			Map<String,Object> m1 = new HashMap<>();
			m1.put("orderId", order.getId());
			m1.put("type", 2);//尾款
			m1.put("isDel", 0);
			List<ConsumerOrderPayment> payments = consumerOrderPaymentDao.select(m1);
			if(payments == null || payments.isEmpty()) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "无尾款凭证");
			}
		}
		//待上传票证
		if(Objects.equals(state, ConsumerOrderState.TickerUploading.getCode())) {
			if(Objects.equals(order.getState(), ConsumerOrderState.Routine.getCode())) {
				if(!Objects.equals(order.getState(), ConsumerOrderState.StockOuting.getCode())) {
					throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
				}
				
				//检查客户信息
				Map<String,Object> m2 = new HashMap<>();
				m2.put("orderId", order.getId());
				m2.put("type", 1);//客户
				m2.put("isDel", 0);
				List<ConsumerOrderUser> customers = consumerOrderUserDao.select(m2);
				if(customers == null || customers.isEmpty()) {
					throw new BusinessException(ResultCode.CODE_STATE_4005, "请客户经理完善客户信息");
				}
				
				//检查物流信息
				if(order.getLogisticsType() == 2) {
					if(order.getLogisticsCompany().isEmpty() 
							|| order.getLogisticsDriver().isEmpty()
							|| order.getLogisticsDriverPhone().isEmpty()
							|| order.getLogisticsPlateNumber().isEmpty()) {
						throw new BusinessException(ResultCode.CODE_STATE_4005, "物流信息不完整");
					}
				}
				
				//更新车辆库存为已出库
				Map<String,Object> m3 = new HashMap<>();
				m3.put("orderId", order.getId());
				m3.put("isDel", 0);
				List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m3);
				if(infos == null || infos.isEmpty()) {
					throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
				}
				for(ConsumerOrderInfo i : infos) {
					Map<String,Object> m4 = new HashMap<>();
					m4.put("infoId", i.getId());
					m4.put("isDel", 0);
					List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m4);
					if(cars == null || cars.isEmpty()) {
						throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
					}
					for(ConsumerOrderCar c : cars) {
						StockCar car = stockCarDao.selectById(c.getStockCarId());
						car.setIsPutOut(true);
						stockCarDao.updateById(car);
					}
				}			
				//三级入库
				Result result = inStock(order.getId(),search.getCurrentUser());
				if(!result.isSuccess()) {
					throw new BusinessException(ResultCode.CODE_STATE_4005, "三级入库失败");
				}
				//插入入库人、入库时间
				order.setOutStocker(search.getCurrentUser().getRealName());
				order.setOutStockTime(new Date());
			}
		}
		//完成
		if(Objects.equals(state, ConsumerOrderState.Doned.getCode())) {
			if(Objects.equals(order.getState(), ConsumerOrderState.Routine.getCode())) {//如果是常规单，才需要校验
				if(!Objects.equals(order.getState(), ConsumerOrderState.TickerUploading.getCode())) {
					throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
				}
				//检查图片上传情况
				Map<String,Object> m1 = new HashMap<>();
				m1.put("orderId", order.getId());
				m1.put("isDel", 0);
				List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m1);
				if(infos == null || infos.isEmpty()) {
					throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
				}
				for(ConsumerOrderInfo i : infos) {
					Map<String,Object> m2 = new HashMap<>();
					m2.put("infoId", i.getId());
					m2.put("isDel", 0);
					List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m2);
					if(cars == null || cars.isEmpty()) {
						throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
					}
//					for(ConsumerOrderCar c : cars) {
//						if(c.getTicketPic() == null || c.getTicketPic().isEmpty()) {
//							throw new BusinessException(ResultCode.CODE_STATE_4006, "车辆无票证");
//						}
//						if(c.getCertificationPic() == null || c.getCertificationPic().isEmpty()) {
//							throw new BusinessException(ResultCode.CODE_STATE_4006, "车辆无合格证");
//						}
//						if(c.getTciPic() == null || c.getTciPic().isEmpty()) {
//							throw new BusinessException(ResultCode.CODE_STATE_4006, "车辆无交强险");
//						}
//						if(c.getCiPic() == null || c.getCiPic().isEmpty()) {
//							throw new BusinessException(ResultCode.CODE_STATE_4006, "车辆无商业险");
//						}
						
//					}
				}
			}				
		}
		
		order.setState(state);
		consumerOrderDao.updateById(order);
		return new Result(ResultCode.CODE_STATE_200,true,"更新成功");
	}
	
	/**
	 * 三级入库
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	private Result inStock(Integer orderId,SystemUsers user) throws Exception {
		ConsumerOrder order = consumerOrderDao.selectById(orderId);
		Map<String,Object> m1 = new HashMap<>();
		m1.put("orderId", order.getId());
		m1.put("isDel", 0);
		List<StockCar> stockCars = new ArrayList<>();
		List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m1);
		for(ConsumerOrderInfo i : infos) {
			Map<String,Object> m2 = new HashMap<>();
			m2.put("infoId", i.getId());
			m2.put("isDel", 0);
			List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m2);
			for(ConsumerOrderCar c : cars) {
				if(c.getStockCarId() != null) {
					StockCar stockCar = stockCarDao.getById(c.getStockCarId());
					stockCars.add(stockCar);
				}
			}
		}
		
		//新建入库单
		StockStorage stockStorage = new StockStorage();
		stockStorage.setStorageCode(stockStorageDao.getStorageCode());
		stockStorage.setOrgId(order.getOrgId());
		stockStorage.setOrgName(order.getOrgName());
		stockStorage.setIsDelete(false);
		stockStorage.setCreateDate(new Date());
		stockStorage.setSource(1);//设置为手动入库
		stockStorageDao.insert(stockStorage);
		
		for(StockCar c : stockCars) {
			StockCar car = new StockCar();
			car.setStorageId(stockStorage.getStorageId());
			car.setFrameNumber(c.getFrameNumber());
			car.setCarsId(c.getCarsId());
			car.setEngineNumber(c.getEngineNumber());
			car.setCertificateNumber(c.getCertificateNumber());
			car.setStockCarImages(c.getStockCarImages());
			car.setInvoicePrice(c.getInvoicePrice());
			car.setDepositPrice(c.getDepositPrice());
			car.setDiscountPrice(c.getDiscountPrice());
			car.setIsOnLine(true);
			car.setUnitPrice(c.getUnitPrice());
			car.setCarsInfo(c.getCarsInfo());
			car.setGuidingPrice(c.getGuidingPrice());
			car.setColourId(c.getColourId());
			car.setColourName(c.getColourName());
			car.setInteriorId(c.getInteriorId());
			car.setInteriorName(c.getInteriorName());
			car.setCreateDate(new Date());
			car.setIsDelete(false);
			car.setOrgId(order.getOrgId());
			car.setIsPutOut(false);
			stockCarDao.insert(car);
		}
		
		return new Result(ResultCode.CODE_STATE_200,true,"入库成功");
	}
	
	@Override
	public Result delete(Integer id) {
		// TODO Auto-generated method stub
		ConsumerOrder order = new ConsumerOrder();
		order.setId(id);
		order.setIsDel(1);
		if(consumerOrderDao.updateById(order)) {
			return new Result(ResultCode.CODE_STATE_200,true,"删除成功");
		}
		return new Result(ResultCode.CODE_STATE_200,false,"删除失败");
	}
	
	@Override
	public Result getPaymentInfo(Integer orderId) {
		// TODO Auto-generated method stub
		ConsumerOrder order = consumerOrderDao.selectById(orderId);
		if(order == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数出错");
		}
		ConsumerOrderPaymentVO payment = new ConsumerOrderPaymentVO();
		Map<String,BigDecimal> map = getOrderAmount(order);
		//定金
		if(Objects.equals(order.getState(), ConsumerOrderState.DepositPaying.getCode())) {
			payment.setOrderId(order.getId());
			payment.setAmount(map.get("totalDepositPrice"));
			payment.setType(1);//定金
			return new Result(payment);
		}else if(Objects.equals(order.getState(), ConsumerOrderState.FinalPricePaying.getCode())) {
			payment.setOrderId(order.getId());
			payment.setAmount(map.get("totalRestPrice"));
			payment.setType(2);//尾款
			return new Result(payment);
		}else {
			throw new BusinessException(ResultCode.CODE_STATE_4006,"订单不在支付阶段");
		}
	}
	
	@Override
	public Result updateLogisticsInfo(ConsumerOrderUpdateLogisticsInfoSearch search) {
		// TODO Auto-generated method stub
		
		ConsumerOrder order = consumerOrderDao.selectById(search.getOrderId());
		if(order == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		
		order.setLogisticsOrderCode(search.getLogisticsOrderCode());
		order.setLogisticsDriver(search.getLogisticsDriver());
		order.setLogisticsDriverPhone(search.getLogisticsDriverPhone());
		order.setLogisticsPlateNumber(search.getLogisticsPlateNumber());
		order.setLogisticsCompany(search.getLogisticsCompany());
		consumerOrderDao.updateById(order);
		
		return new Result(ResultCode.CODE_STATE_200,true,"更新成功");
	}

	@Override
	@Transactional
	public Result cancel(Integer id)throws Exception {
		Result result = new Result();
		ConsumerOrder order = consumerOrderDao.selectById(id);
		if(order == null) {
			result.setError("你选择的订购单不存在或者已删除");
			return result;
		}
		if(order.getState() > ConsumerOrderState.DepositPaying.getCode()) {
			result.setError("你选择的订购单已支付定金，不能进行此操作");
			return result;
		}
		try {
//			if(order.getState().equals(ConsumerOrderState.Init.getCode())) {
//				order.setIsDel(1);
//				if(!consumerOrderDao.updateById(order)) {
//					throw new Exception("取消购车单，更新consumerOrder出错，自动回滚");
//				}
//			}
			Map<String,Object> m1 = new HashMap<>();
			m1.put("orderId", order.getId());
			m1.put("isDel", 0);
			List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m1);
			for(ConsumerOrderInfo i : infos) {
				i.setIsDel(1);
				if(!consumerOrderInfoDao.updateById(i)) {
					throw new Exception("取消购车单，更新ConsumerOrderInfo出错，自动回滚");
				}	
				Map<String,Object> m2 = new HashMap<>();
				m2.put("infoId", i.getId());
				m2.put("isDel", 0);
				List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m2);
				for(ConsumerOrderCar c : cars) {
					c.setIsDel(1);
					if(!consumerOrderCarDao.updateById(c)) {
						throw new Exception("取消购车单，更新ConsumerOrderCar出错，自动回滚");
					}
				}
			}
			order.setIsDel(1);
			if(!consumerOrderDao.updateById(order)) {
				throw new Exception("取消购车单，更新consumerOrder出错，自动回滚");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("取消购车单出错，自动回滚");
		}
		result.setOK(ResultCode.CODE_STATE_200, "删除成功");
		return result;
	}
	
	@Override
	@Transactional
	public Result countermand(ConsumerOrderSearch search,Result result) throws Exception {
		ConsumerOrder order = consumerOrderDao.selectById(search.getId());
		if(order == null) {
			result.setError("你选择的订购单不存在或者已删除");
			return result;
		}
		/**
		 * 出库之前都可以退款
		 */
		if(order.getState() >= ConsumerOrderState.StockOuting.getCode()) {
			result.setError("你选择的订购车辆已出库，不能进行此操作");
			return result;
		}
		if(StringUtil.isEmpty(search.getCountermandReason())) {
			result.setError("请输入退款原因");
			return result;
		}
		order.setCountermandApply(true);
		if(StringUtil.isNotEmpty(search.getCountermandPic())) {
			order.setCountermandPic(search.getCountermandPic());
		}
		order.setCountermandReason(search.getCountermandReason());
		if(!consumerOrderDao.updateById(order)) {
			result.setError("系统正在升级...");
		}else {
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}
		return result;
	}
	

	@Override
	@Transactional
	public Result countermandExamine(ConsumerOrderSearch search,Result result) throws Exception {
		ConsumerOrder order = consumerOrderDao.selectById(search.getId());
		if(order == null) {
			result.setError("你选择的订购单不存在或者已删除");
			return result;
		}
		/**
		 * 出库之前都可以退款
		 */
		if(order.getState() >= ConsumerOrderState.StockOuting.getCode()) {
			result.setError("你选择的订购车辆已出库，不能进行此操作");
			return result;
		}
		if(search.getOverPass() != null && !search.getOverPass()) {//拒绝退款
			order.setCountermandApply(false);
			if(!consumerOrderDao.updateById(order)) {
				throw new Exception("退订更新购车单错误，手动抛错回滚");
			}else {
				result.setOK(ResultCode.CODE_STATE_200, "拒绝成功");
			}
			return result;
		}
//		if(StringUtil.isEmpty(search.getCountermandReason())) {
//			result.setError("上传退款");
//			return result;
//		}
		//获取客户下订单
		Map<String,Object> m3 = new HashMap<>();
		m3.put("orderId", order.getId());
		m3.put("isDel", 0);
		List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m3);
		Set<Integer> set = new HashSet<>();
		for(ConsumerOrderInfo info : infos) {
			set.add(info.getId());
		}
		if(set.size() > 0) {
			Map<String,Object> m4 = new HashMap<>();
			m4.put("infoIds", set);
			m4.put("isDel", 0);
			List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m4);
			set.clear();
			for(ConsumerOrderCar orderCar : cars) {
				set.add(orderCar.getStockCarId());
			}
			if(set.size() > 0) {
				Map<String,Object> m5 = new HashMap<>();
				m5.put("ids", set);
				m5.put("isDel", 0);
				List<StockCar> stockCars = stockCarDao.select(m5);
				for(StockCar stockCar : stockCars) {
					if(stockCar.getIsPutOut()) {
						result.setError(stockCar.getCarsInfo()+"已出库，操作失败，请核对数据");
						return result;
					}
				}
				try {
					for(StockCar stockCar : stockCars) {
						stockCar.setLockState(2);//解除绑定
						if(!stockCarDao.updateById(stockCar)) {
							throw new Exception("退订解锁库存错误，手动抛错回滚");
						}
					}
					for(ConsumerOrderCar orderCar : cars) {
						orderCar.setStockCarId(null);
						orderCar.setVin(null);
						orderCar.setCheckCarPic(null);
						orderCar.setAuditState(ConsumerOrderCarAuditState.countermand.getCode());//待换车
						if(!consumerOrderCarDao.update(orderCar)) {
							throw new Exception("退订更新ConsumerOrderCar错误，手动抛错回滚");
						}
					}
					for(ConsumerOrderInfo orderInfo : infos) {
						orderInfo.setState(ConsumerOrderInfoState.countermand.getCode());
						if(!consumerOrderInfoDao.updateById(orderInfo)) {
							throw new Exception("退订更新ConsumerOrderInfo错误，手动抛错回滚");
						}
					}					
				} catch (Exception e) {
					e.printStackTrace();
					result.setError("系统正在升级...");
					throw new RuntimeException();
				}
			}			
		}
		try {
			order.setState(ConsumerOrderState.countermand.getCode());
			order.setCountermandApply(false);
			order.setCountermandApply(false);
			if(!consumerOrderDao.updateById(order)) {
				throw new Exception("退订更新购车单错误，手动抛错回滚");
			}else {
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
			}
			
			if(StringUtil.isNotEmpty(search.getVoucherPic()) || StringUtil.isNotEmpty(search.getRemarks())) {
				ConsumerOrderPayment payment = new ConsumerOrderPayment();
				payment.setCreateTime(new Date());
				payment.setType(3);//退款
				payment.setIsDel(0);
				payment.setOrderId(order.getId());
				payment.setVoucher(search.getVoucherPic());
				payment.setRemark(search.getRemarks());
				if(consumerOrderPaymentDao.insert(payment)) {
				}else {
					throw new Exception("退订更新购车单错误，手动抛错回滚");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("系统正在升级...");
			throw new RuntimeException();
		}
		return result;
	}

	@Override
	@Transactional
	public Result createOrderShop(CreateConsumerOrderSearch search,Result result) throws Exception {
		// TODO Auto-generated method stub
		
		//保存订单
		ConsumerOrder order = new ConsumerOrder();
		ConvertUtil.objectToObject(search, order,false);
		order.setState(ConsumerOrderState.Init.getCode());//设置订单状态为新建
		order.setCreateTime(new Date());
		order.setOrderCode(createOrderCode(search.getOrgPhone()));
		if(order.getOrderType() == null) {
			order.setOrderType(ConsumerOrderState.Routine.getCode());//标记为炒车
		}else if(order.getOrderType().equals(ConsumerOrderState.Traffic.getCode()) && StringUtil.isNotEmpty(search.getAdvanceOrderId())) {
			result.setError("抱歉，商城预购单尚不支持炒车单");
			return result;
		}
		ShopAdvanceOrderVo advanceOrder = null;
		ShopAdvanceOrderPaymentVo orderPayment = null;
		ConsumerOrderPayment payment = null;
		if(StringUtil.isNotEmpty(search.getAdvanceOrderId())) {
			advanceOrder = shopAdvanceOrderDao.selectByIdJoin(search.getAdvanceOrderId());
			if(advanceOrder.getOverDelete()) {
				result.setError("抱歉，该预约已取消");
				return result;
			}
			if(advanceOrder.getOverCatch()) {
				result.setError("该预购单已生成订车单，请勿重复操作");
				return result;
			}
			ShopUsers users = shopUsersDao.selectById(advanceOrder.getShopUserId());
//			Map<String, Object> orgParams = new HashMap<>();
//			orgParams.put("phone", advanceOrder.getPhoneNumber());
//			orgParams.put("allstatus", true);
//			orgParams.put("status", true);
//			List<OrganizationVo> organizations = organizationDao.select(orgParams);
			OrganizationVo organization = organizationDao.selectById(users.getOrgId());
			if(organization == null) {
				organization = organizationDao.selectById(123);//因为商城的C端用户不存在门店划分，所以C端门店向系统下了购车单就默认配置为淘车网的三级门店
				if(organization == null) {
					result.setError("商城用户所属门店未知，请核对数据");
					return result;
				}
			}
			advanceOrder.setOverCatch(true);
			if(advanceOrder.getOverPay()) {
//				orderPayment = advanceOrder.getOrderPaymentVo();
//				payment = new ConsumerOrderPayment();
//				payment.setAmount(orderPayment.getAmount());
//				payment.setCreateTime(new Date());
//				payment.setIsDel(0);
//				payment.setPayType(1);//标记为定价
//				payment.setType(1);//标记为定价
//				payment.setRemark("商城已支付定金的订购单转化成为采购单");
//				order.setState(ConsumerOrderState.CarDistributing.getCode());//设置订单状态为待配车
			}
			order.setOrgId(organization.getOrgId());
			order.setOrgName(organization.getShortName());
			order.setAdvanceOrderId(advanceOrder.getAdvanceOrderId());
			advanceOrder.setCatchDate(new Date());
			advanceOrder.setOverCatch(true);
		}
		
		

		try {
			if(!consumerOrderDao.insert(order)) {
				throw new Exception("创建订购单失败，手动回滚");
			}
			//保存提车人
			List<ConsumerOrderUserSearch> pickerSearchs = search.getPickers();
			if(pickerSearchs != null && !pickerSearchs.isEmpty()) {
				List<ConsumerOrderUser> pickers = new ArrayList<>(pickerSearchs.size());
				ConvertUtil.listObjectToListObject(pickerSearchs, pickers, ConsumerOrderUser.class);
				
				//校验提车人手机号唯一
				List<String> phones = pickers.stream()
						.map((n) -> n.getUserPhone()).distinct().collect(Collectors.toList());
				if(!Objects.equals(phones.size(), pickers.size())) {
					throw new BusinessException(ResultCode.CODE_STATE_4006, "提车人手机号不能重复");
				}
				for(ConsumerOrderUser user : pickers) {
					user.setOrderId(order.getId());
					user.setType(2);//提车人
					user.setCreateTime(new Date());
					if(!consumerOrderUesrDao.insert(user))
						throw new BusinessException(ResultCode.CODE_STATE_4014, "保存提车人失败");
				}
			}
			if(payment != null) {
				payment.setOrderId(order.getId());
				if(!consumerOrderPaymentDao.insert(payment)) {
					throw new Exception("创建订购单支付信息失败，手动回滚");
				}
			}
			if(advanceOrder != null) {
				advanceOrder.setRealOrderId(order.getId());
				advanceOrder.setSystemUserId(search.getCreatorId());//记录处理人
				advanceOrder.setSystemUserName(search.getCreatorName());
				if(!shopAdvanceOrderDao.updateById(advanceOrder)) {
					throw new Exception("更新订购单处理信息失败，手动回滚");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("创建订购单失败，手动回滚");
		}
		ConsumerOrderVO orderVO = new ConsumerOrderVO();
		ConvertUtil.objectToObject(order, orderVO);
		return new Result(orderVO);
	}

	@Override
	public Result getContractInfoImage(Integer orderId, Result result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ImageRenderer renderer = new ImageRenderer();
		String url = "http://localhost:8080/tauto//emInterface/employee/consumerOrder/getContractInfoImageHtml?orderId="+orderId;
		String path = SystemPath.ROOT_PATH+File.separator+"res"+File.separator+"image"+orderId+System.currentTimeMillis()+".png";
		File file = new File(path);
		FileOutputStream out = new FileOutputStream(file);
		//生成网页截图
		if(!renderer.renderURL(url, out, ImageRenderer.Type.PNG)) {
			result.setError("生成分享截图失败");
			return result;
		}
		String pathBack = QiniuUtils.uploadFileBackPath(FileInterchangeBytes.getBytes(path), System.currentTimeMillis()+"", null);//默认未images空间
		 if(StringUtil.isEmpty(pathBack)){
			 System.out.println("上传文件出错");
			 result.setError(ResultCode.CODE_STATE_4009, "生成分享截图失败");
		 }else{
			 //删除旧文件
			 file.delete();
			 result.setOK(ResultCode.CODE_STATE_200,"",pathBack);
		 }
		 return result;
	}

	@Override
	public Result getContractInfoImageHtml(Integer orderId, Result result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
		orderVO.setBankAccountName(SystemConfig.get("bankAccountName"));
		orderVO.setBankBranch(SystemConfig.get("bankBranch"));
		orderVO.setBankCardNum(SystemConfig.get("bankCardNum"));
		//合同盖章图：https://res.xfnauto.com/zang.png
		return null;
	}
}
