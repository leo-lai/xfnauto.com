package main.com.weixinApp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarInteriorDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.po.CarColour;
import main.com.car.dao.po.CarInterior;
import main.com.car.dao.po.Cars;
import main.com.exception.BusinessException;
import main.com.frame.constants.ConsumerOrderCarAuditState;
import main.com.frame.constants.ConsumerOrderInfoState;
import main.com.frame.constants.ConsumerOrderState;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.po.ConsumerOrder;
import main.com.stock.dao.po.ConsumerOrderCar;
import main.com.stock.dao.po.ConsumerOrderInfo;
import main.com.stock.dao.po.ConsumerOrderPayment;
import main.com.stock.dao.po.ConsumerOrderUser;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.vo.StockCarVo;
import main.com.system.dao.vo.OrganizationVo;
import main.com.utils.ConvertUtil;
import main.com.utils.StringUtil;
import main.com.weixinApp.dao.dao.ConsumerOrderCarDao;
import main.com.weixinApp.dao.dao.ConsumerOrderDao;
import main.com.weixinApp.dao.dao.ConsumerOrderInfoDao;
import main.com.weixinApp.dao.dao.ConsumerOrderPaymentDao;
import main.com.weixinApp.dao.dao.ConsumerOrderUserDao;
import main.com.weixinApp.dao.search.ChangeCarApplySearch;
import main.com.weixinApp.dao.search.ChangeCarApproveSearch;
import main.com.weixinApp.dao.search.ChangeCarSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoChangePriceSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoCreateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoUpdateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderInfoUpdateStateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderUserCreateSearch;
import main.com.weixinApp.dao.search.ConsumerOrderUserSearch;
import main.com.weixinApp.dao.search.CreateConsumerOrderInfoSearch;
import main.com.weixinApp.dao.search.DistributeCarSearch;
import main.com.weixinApp.dao.search.QueryVinSearch;
import main.com.weixinApp.dao.search.RedistributeCarSearch;
import main.com.weixinApp.dao.search.RefuseCarChangeSearch;
import main.com.weixinApp.service.ConsumerOrderInfoService;
import main.com.weixinApp.service.ConsumerOrderUserService;
import main.com.weixinHtml.dao.dao.ShopAdvanceOrderDao;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.dao.vo.ShopAdvanceOrderPaymentVo;
import main.com.weixinHtml.dao.vo.ShopAdvanceOrderVo;
@Service
public class ConsumerOrderInfoServiceImpl extends BaseServiceImpl<ConsumerOrderInfo> implements ConsumerOrderInfoService{

	@Autowired
	private ConsumerOrderInfoDao consumerOrderInfoDao;
	
	@Autowired
	private ConsumerOrderUserDao consumerOrderUserDao;
	
	@Autowired
	private ConsumerOrderCarDao consumerOrderCarDao;
	
	@Autowired
	private ConsumerOrderDao consumerOrderDao;
	
	@Autowired
	private CarsDao carsDao;
	
	@Autowired
	private StockCarDao stockCarDao;
	
	@Autowired
	private CarInteriorDao carInteriorDao;
	
	@Autowired
	private CarColourDao carColourDao;
	
	@Autowired
	private ConsumerOrderUserService consumerOrderUserService;
	
	@Autowired
	private ShopAdvanceOrderDao shopAdvanceOrderDao;
	
	@Autowired
	private ConsumerOrderPaymentDao consumerOrderPaymentDao;
	
	@Override
	protected BaseDao<ConsumerOrderInfo> getBaseDao() {
		// TODO Auto-generated method stub
		return consumerOrderInfoDao;
	}
	
	@Override
	@Transactional
	public Result createOrderInfo(CreateConsumerOrderInfoSearch search) {
		// TODO Auto-generated method stub
		
		//保存客户信息
		ConsumerOrderUser customer = new ConsumerOrderUser();
		ConvertUtil.objectToObject(search.getCustomer(),customer);
		customer.setOrderId(search.getOrderId());
		customer.setCreateTime(new Date());
		customer.setType(1);//客户
		
		//校验唯一
		Result validateResult = consumerOrderUserService.validate(customer);
		if(!validateResult.isSuccess()) {
			return validateResult;
		}
		consumerOrderUserDao.insert(customer);
		
		List<ConsumerOrderInfo> infos = new ArrayList<>(search.getInfos().size());
		ConvertUtil.listObjectToListObject(search.getInfos(), infos, ConsumerOrderInfo.class);
		
		if(customer.getId() == null){
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		//校验车型/颜色唯一
		List<Map<String,Object>> maps = new ArrayList<>();
		for(ConsumerOrderInfo i : infos) {
			Map<String,Object> m = new HashMap<>();
			m.put("customerId",i.getCustomerId());
			m.put("carsId", i.getCarsId());
			m.put("colorId", i.getColorId());
			m.put("isDel", 0);
			maps.add(m);
		}	
		List<Map<String,Object>> ms = maps.stream().distinct().collect(Collectors.toList());
		if(!Objects.equals(maps.size(), ms.size())) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "存在重复车型");
		}
		
		for(ConsumerOrderInfo info : infos) {
			
			Cars c = carsDao.selectById(info.getCarsId());
			if(c == null) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "保存车辆失败，该车型不存在");
			}
			
			//裸车价=指导价+加价（减价）
			info.setNakedPrice(new BigDecimal(c.getPrice()).add(info.getChangePrice()));
			
			info.setCustomerId(customer.getId());
			info.setOrderId(search.getOrderId());
			info.setBrandId(c.getBrandId());
			info.setBrandName(c.getBrandName());
			info.setFamilyId(c.getFamilyId());
			info.setFamilyName(c.getFamilyName());
			info.setCarsName(c.getCarName());
			info.setGuidePrice(new BigDecimal(c.getPrice()));
			info.setCreateTime(new Date());
			info.setState(ConsumerOrderInfoState.CarDistributing.getCode());
			
			//车身颜色
			CarColour color = carColourDao.selectById(info.getColorId());
			if(color == null) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
			}
			info.setColorId(color.getCarColourId());
			info.setColorName(color.getCarColourName());
			
			//内饰颜色
			if(info.getInteriorId() != null) {
				CarInterior interior = carInteriorDao.selectById(info.getInteriorId());
				if(interior == null) {
					throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
				}
				info.setInteriorId(interior.getInteriorId());
				info.setInteriorName(interior.getInteriorName());
			}
			
			if(!consumerOrderInfoDao.insert(info)) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "保存车辆失败");
			}
			
			//往汽车表加加数据
			for(int i = 0; i < info.getCarNum(); i++) {
				ConsumerOrderCar car = new ConsumerOrderCar();
				car.setInfoId(info.getId());
				car.setCarsId(info.getCarsId());
				car.setCarsName(info.getCarsName());
				car.setBrandId(info.getBrandId());
				car.setBrandName(info.getBrandName());
				car.setFamilyId(info.getFamilyId());
				car.setFamilyName(info.getFamilyName());
				car.setColorId(info.getColorId());
				car.setColorName(info.getColorName());
				car.setInteriorId(info.getInteriorId());
				car.setInteriorName(info.getInteriorName());
				car.setCreateTime(new Date());
				car.setAuditState(ConsumerOrderCarAuditState.Auditing.getCode());
				if(!consumerOrderCarDao.insert(car)) {
					throw new BusinessException(ResultCode.CODE_STATE_4006, "保存车辆失败");
				}
			}
		}
		
		//更改订单状态为待支付定金
//		ConsumerOrder order = new ConsumerOrder();
		ConsumerOrder order = consumerOrderDao.selectById(search.getOrderId());
		if(order == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "订单不存在");
		}
		ShopAdvanceOrderVo advanceOrder = null;
		ShopAdvanceOrderPaymentVo orderPayment = null;
		ConsumerOrderPayment payment = null;
		if(StringUtil.isNotEmpty(order.getAdvanceOrderId())) {
			advanceOrder = shopAdvanceOrderDao.selectByIdJoin(order.getAdvanceOrderId());
			if(advanceOrder.getOverPay()) {
				orderPayment = advanceOrder.getOrderPaymentVo();
				payment = new ConsumerOrderPayment();
				payment.setAmount(orderPayment.getAmount());
				payment.setCreateTime(new Date());
				payment.setIsDel(0);
				payment.setPayType(1);//标记为定价
				payment.setType(1);//标记为定价
				payment.setRemark("商城已支付定金的订购单转化成为采购单");
				order.setState(ConsumerOrderState.CarDistributing.getCode());//设置订单状态为待配车
			}else {
				order.setState(ConsumerOrderState.DepositPaying.getCode());
			}
		}else {
			order.setState(ConsumerOrderState.DepositPaying.getCode());
		}
		
		if(!consumerOrderDao.updateById(order)) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "更新订单状态失败");
		}
		if(payment != null) {
			payment.setOrderId(order.getId());
			if(!consumerOrderPaymentDao.insert(payment)) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "更新订单支付状态失败");
			}
		}
		return new Result(ResultCode.CODE_STATE_200,true,"新增订购车辆成功");
	}
	
	@Override
	public Result create(ConsumerOrderInfoCreateSearch search) {
		// TODO Auto-generated method stub
		
		ConsumerOrder order = consumerOrderDao.selectById(search.getOrderId());
		if(order == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		
		ConsumerOrderUser user = consumerOrderUserDao.selectById(search.getCustomerId());
		if(user == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		
		
		
		ConsumerOrderInfo info = new ConsumerOrderInfo();
		ConvertUtil.objectToObject(search, info);
		//先校验订单状态,只有新建/待支付定金状态才可以新增
		if(!(Objects.equals(order.getState(), ConsumerOrderState.Init.getCode()) ||
				Objects.equals(order.getState(), ConsumerOrderState.DepositPaying.getCode()))) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "新增失败");
		}
		

		Cars c = carsDao.selectById(search.getCarsId());
		if(c == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		info.setCarsName(c.getCarName());
		info.setGuidePrice(new BigDecimal(c.getPrice()));
		
		CarColour color = carColourDao.selectById(search.getColorId());
		if(color == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		info.setColorId(color.getCarColourId());
		info.setColorName(color.getCarColourName());
		
		//内饰颜色
		if(search.getInteriorId() != null) {
			CarInterior interior = carInteriorDao.selectById(search.getInteriorId());
			if(interior == null) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
			}
			info.setInteriorId(interior.getInteriorId());
			info.setInteriorName(interior.getInteriorName());
		}
		
		Map<String,Object> m1 = new HashMap<>();
		m1.put("orderId", info.getOrderId());
		m1.put("carsId", info.getCarsId());
		m1.put("colorId", info.getColorId());
		m1.put("customerId", info.getCustomerId());
		m1.put("isDel", 0);
		List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m1);
		if(infos != null && !infos.isEmpty()) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "该车型已存在，新增失败");
		}
		
		if(!consumerOrderInfoDao.insert(info)) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "新增失败");
		}
		//往汽车表加加数据
		for(int i = 0; i < info.getCarNum(); i++) {
			ConsumerOrderCar car = new ConsumerOrderCar();
			car.setInfoId(info.getId());
			car.setCarsId(info.getCarsId());
			car.setCarsName(info.getCarsName());
			car.setBrandId(info.getBrandId());
			car.setBrandName(info.getBrandName());
			car.setColorId(info.getColorId());
			car.setColorName(info.getColorName());
			car.setInteriorId(info.getInteriorId());
			car.setInteriorName(info.getInteriorName());
			car.setCreateTime(new Date());
			car.setAuditState(ConsumerOrderCarAuditState.Auditing.getCode());
			if(!consumerOrderCarDao.insert(car)) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "保存车辆失败");
			}
		}
		return new Result(info);
	}
	
	@Override
	@Transactional
	public Result update(ConsumerOrderInfoUpdateSearch search) {
		// TODO Auto-generated method stub
		
		
		ConsumerOrderInfo info = consumerOrderInfoDao.selectById(search.getId());
		if(info == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		
		ConsumerOrder order = consumerOrderDao.selectById(info.getOrderId());
		if(order == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		
		//待支付定金状态
		if(Objects.equals(order.getState(), ConsumerOrderState.DepositPaying.getCode())) {
			
		}
		
		//支付完定金后不能更改信息
		if(order.getState() > ConsumerOrderState.CarDistributing.getCode()) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "定金已支付，无法修改信息");
		}
		
		//待验车状态
		if(Objects.equals(order.getState(), ConsumerOrderState.CarChecking.getCode())) {
			
		}
		///待交尾款状态
		if(Objects.equals(order.getState(), ConsumerOrderState.CarChecking.getCode())) {
			
		}
		//待出库状态
		if(Objects.equals(order.getState(), ConsumerOrderState.CarChecking.getCode())) {
			
		}
		
		//检查该订单有无相同车型
		Map<String,Object> m3 = new HashMap<>();
		m3.put("carsId", info.getCarsId());
		m3.put("colorId", info.getColorId());
		m3.put("customerId", info.getCustomerId());
		m3.put("isDel", 0);
		List<ConsumerOrderInfo> infos = consumerOrderInfoDao.select(m3);
		if(infos != null && !infos.isEmpty()) {
			for(ConsumerOrderInfo i : infos) {
				if(!Objects.equals(i.getId(), info.getId())) {
					throw new BusinessException(ResultCode.CODE_STATE_4006, "该车型已存在，更新失败");
				}
			}	
		}
		
		//现将与该info关联的汽车记录删除
		Map<String,Object> m2 = new HashMap<>();
		m2.put("infoId", info.getId());
		m2.put("customerId", info.getCustomerId());
		m2.put("isDel", 0);
		List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m2);
		for(ConsumerOrderCar car : cars) {
			car.setIsDel(1);
			consumerOrderCarDao.updateById(car);
		}
		
		ConvertUtil.objectToObject(search, info);
		info.setNakedPrice(new BigDecimal(info.getGuidePrice().doubleValue() + info.getChangePrice().doubleValue()));//更新裸车价
		if(!consumerOrderInfoDao.updateById(info)) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "更新失败");
		}
		
		for(int i = 0; i < info.getCarNum(); i++) {	
			ConsumerOrderCar car = new ConsumerOrderCar();
			car.setInfoId(info.getId());
			car.setCarsId(info.getCarsId());
			car.setCarsName(info.getCarsName());
			car.setBrandId(info.getBrandId());
			car.setBrandName(info.getBrandName());
			car.setColorId(info.getColorId());
			car.setColorName(info.getColorName());
			car.setInteriorId(info.getInteriorId());
			car.setInteriorName(info.getInteriorName());
			car.setCreateTime(new Date());
			car.setAuditState(ConsumerOrderCarAuditState.Auditing.getCode());
			if(!consumerOrderCarDao.insert(car)) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "保存车辆失败");
			}
		}
		
		return new Result(info);
	}
	
	@Override
	public Result queryVin(QueryVinSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderInfo info = consumerOrderInfoDao.selectById(search.getInfoId());
		if(info == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "查询订购信息出错");
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("carsId", info.getCarsId());
		map.put("colourId", info.getColorId());
		map.put("isDelete", 0);
		map.put("isPutOut", null);//未出库
		map.put("lockState", 2);//未锁定
		map.put("overSure", 1);//未锁定
		map.put("orgId", search.getCurrentUser().getOrgId());
		List<StockCar> stockCars = stockCarDao.select(map);
		
		Map<String,Object> m2 = new HashMap<>();
		m2.put("infoId", info.getId());
		m2.put("isDel", 0);
		List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m2);
		Long carNum  = cars.stream().filter((c) -> c.getStockCarId() != null).count();
		
		if(stockCars != null) {
			if(stockCars.isEmpty() || stockCars.size() < carNum) {
				return new Result(ResultCode.CODE_STATE_200,false,"库存不足");
			}
			List<StockCarVo> carVOs = new ArrayList<>(stockCars.size());
			ConvertUtil.listObjectToListObject(stockCars, carVOs, StockCarVo.class);
			return new Result(carVOs);
		}
		
		return new Result(ResultCode.CODE_STATE_200,false,"查询出错");
	}
	
	@Override
	public Result distributeCar(DistributeCarSearch search) {
		// TODO Auto-generated method stub
		
		
		List<String> ids = Arrays.asList(search.getStockCarIds().split(","));
		List<Integer> carIds = ids.stream().map((s) -> Integer.valueOf(s)).collect(Collectors.toList());
		List<StockCar> stockCars = new ArrayList<>();
		for(Integer carId : carIds){
			StockCar car = stockCarDao.selectById(carId);
			if(car == null 
					|| Objects.equals(car.getIsPutOut(), 1)
					|| Objects.equals(car.getLockState(), 1)
					|| car.getIsDelete()) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "所选车辆不在库存中");
			}
			stockCars.add(car);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("infoId", search.getInfoId());
		map.put("isDel",0);
		List<ConsumerOrderCar> cars = consumerOrderCarDao.select(map);
		if(!Objects.equals(stockCars.size(), cars.size())) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "分配车辆数量与订单车辆数量不对应，分配失败");
		}
		//绑定车架号and锁定库存车辆
		for(int i = 0; i < cars.size(); i++) {
			StockCar stockcar = stockCars.get(i);
			ConsumerOrderCar c = new ConsumerOrderCar();
			c.setId(cars.get(i).getId());
			c.setStockCarId(stockcar.getStockCarId());
			c.setVin(stockcar.getFrameNumber());
			c.setInteriorId(stockcar.getInteriorId());
			c.setInteriorName(stockcar.getInteriorName());
			c.setAuditState(ConsumerOrderCarAuditState.CarDistributed.getCode());
			//绑定车架号和内饰
			if(!consumerOrderCarDao.updateById(c)) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "操作失败");
			}
			//锁定库存车辆
			stockcar.setLockState(1);
			if(!stockCarDao.updateById(stockcar)) {
				throw new BusinessException(ResultCode.CODE_STATE_4006, "操作失败");
			}
		}
		
		//更新info表状态为待验车
		ConsumerOrderInfo info = new ConsumerOrderInfo();
		info.setId(search.getInfoId());
		info.setState(ConsumerOrderInfoState.CarChecking.getCode());
		consumerOrderInfoDao.updateById(info);
		
		return new Result(ResultCode.CODE_STATE_200,true,"分配成功");
	}
	
	@Override
	public Result redistributeCar(RedistributeCarSearch search) {
		// TODO Auto-generated method stub

		ConsumerOrderCar car = consumerOrderCarDao.selectById(search.getCarId());
		if(car == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		
		StockCar stockCar = stockCarDao.selectById(search.getStockCarId());
		if(stockCar == null 
				|| Objects.equals(stockCar.getIsPutOut(), 1)
				|| Objects.equals(stockCar.getLockState(), 1)
				|| stockCar.getIsDelete()) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "所选车辆不在库存中");
		}
		
		car.setStockCarId(stockCar.getStockCarId());
		car.setVin(stockCar.getFrameNumber());
		car.setAuditState(ConsumerOrderCarAuditState.CarDistributed.getCode());//已配车
		
		consumerOrderCarDao.updateById(car);
		
		//锁定库存车辆
		stockCar.setLockState(1);
		stockCarDao.updateById(stockCar);
		
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
		
	}
	
	@Override
	@Transactional
	public Result changeCar(ChangeCarSearch search) {
		// TODO Auto-generated method stub
		Integer carId = search.getCarId();
		ConsumerOrderCar car = consumerOrderCarDao.selectById(carId);
		if(car == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006,"该车辆不存在");
		}
		
		//校验车辆审核状态是否为待换车
		if(!Objects.equals(car.getAuditState(), ConsumerOrderCarAuditState.CarChanging.getCode())) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "操作失败");
		}
		
		ConsumerOrderCar c = new ConsumerOrderCar();
		ConvertUtil.objectToObject(car, c);
		
		StockCar stockCar = stockCarDao.selectById(car.getStockCarId());
		if(stockCar == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		
		//解锁被换库存车辆
		stockCar.setLockState(2);
		stockCarDao.updateById(stockCar);
		
		//将该车辆记录置废
		car.setIsDel(1);
		consumerOrderCarDao.updateById(car);
		
		//取出要换的车辆
		StockCar stockCar2 = stockCarDao.selectById(search.getStockCarId());
		if(stockCar2 == null 
				|| Objects.equals(stockCar2.getLockState(),1)
				|| Objects.equals(stockCar2.getIsPutOut(),1 )
				|| stockCar2.getIsDelete()) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		//锁定要换的车辆
		stockCar2.setLockState(1);
		stockCarDao.updateById(stockCar2);
		
		//添加记录
		c.setId(null);
		c.setIsDel(0);
		c.setAuditState(ConsumerOrderCarAuditState.CarChanged.getCode());//已换车状态
		c.setStockCarId(stockCar2.getStockCarId());
		c.setVin(stockCar2.getFrameNumber());
		if(!consumerOrderCarDao.insert(c)) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "换车失败");
		}
		
		//更新子单表状态
		ConsumerOrderInfo info = consumerOrderInfoDao.selectById(car.getInfoId());
		if(info == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		info.setState(ConsumerOrderInfoState.CarChanged.getCode());
		consumerOrderInfoDao.updateById(info);
		
		//todo	更改总单状态
		
		return new Result(ResultCode.CODE_STATE_200,true,"换车成功");
	}
	
	@Override
	@Transactional
	public Result refuseChangeCar(RefuseCarChangeSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderCar car = consumerOrderCarDao.selectById(search.getCarId());
		if(car == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		
		//更新车辆状态
		car.setAuditState(ConsumerOrderCarAuditState.PriceChanging.getCode());//待协商价格
		consumerOrderCarDao.updateById(car);
		
		//更新子单状态
		ConsumerOrderInfo info = consumerOrderInfoDao.selectById(car.getInfoId());
		if(info == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4005, "参数错误");
		}
		info.setState(ConsumerOrderInfoState.Consulting.getCode());//待协商
		consumerOrderInfoDao.updateById(info);
		
		/*//更新总单状态
		ConsumerOrder order = consumerOrderDao.selectById(info.getOrderId());
		if(order == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		order.setState(ConsumerOrderState.Consulting.getCode());//待换车
		consumerOrderDao.updateById(order);*/
		
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
	}
	
	@Override
	@Transactional
	public Result changeCarApply(ChangeCarApplySearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderCar car = consumerOrderCarDao.selectById(search.getCarId());
		if(car == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "操作失败");
		}
		
		car.setAuditState(ConsumerOrderCarAuditState.CarChangeApply.getCode());//申请换车
		car.setAuditRemark(search.getAuditRemark());
		car.setCheckCarPic(search.getCheckCarPic());
		consumerOrderCarDao.updateById(car);
		
		/*//更新子单状态
		ConsumerOrderInfo info = consumerOrderInfoDao.selectById(car.getInfoId());
		if(info == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		info.setState(ConsumerOrderInfoState.CarChangeApply.getCode());//换车申请状态
		consumerOrderInfoDao.updateById(info);*/
		
		return new Result(ResultCode.CODE_STATE_200,true,"更新成功");
	}
	
	@Override
	@Transactional
	public Result changeCarApprove(ChangeCarApproveSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderCar car = consumerOrderCarDao.selectById(search.getCarId());
		if(car == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "操作失败");
		}
		
		//解绑配车车辆
		StockCar stockCar = stockCarDao.selectById(car.getStockCarId());
		if(stockCar == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "操作失败");
		}
		stockCar.setLockState(2);//未绑定
		stockCarDao.updateById(stockCar);
		
		car.setStockCarId(null);
		car.setVin(null);
		car.setCheckCarPic(null);
		car.setAuditState(ConsumerOrderCarAuditState.CarChanging.getCode());//待换车
		consumerOrderCarDao.update(car);
		
		//更新子单状态
		ConsumerOrderInfo info = consumerOrderInfoDao.selectById(car.getInfoId());
		if(info == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		info.setState(ConsumerOrderInfoState.CarChanging.getCode());//待换车
		consumerOrderInfoDao.updateById(info);
		
		/*//更新总单状态
		ConsumerOrder order = consumerOrderDao.selectById(info.getOrderId());
		if(order == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		order.setState(ConsumerOrderState.CarDistributing.getCode());//待换车
		consumerOrderDao.updateById(order);
		*/
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
		
	}
	
	@Override
	public Result updateState(ConsumerOrderInfoUpdateStateSearch search) {
		// TODO Auto-generated method stub
		
		ConsumerOrderInfo info = consumerOrderInfoDao.selectById(search.getId());
		if(info == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "操作失败");
		}
		if(Objects.equals(search.getState(), ConsumerOrderInfoState.CarChecking.getCode())) {
			//校验info单下所有车辆已配车
			Map<String,Object> m1 = new HashMap<>();
			m1.put("infoId", info.getId());
			m1.put("isDel", 0);
			List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m1);
			for(ConsumerOrderCar c :cars) {
				if(!Objects.equals(c.getAuditState(), ConsumerOrderCarAuditState.CarDistributed.getCode())) {
					throw new BusinessException(ResultCode.CODE_STATE_4006, "有车辆未校验");
				}
			}
		}
		//已验车
		if(Objects.equals(search.getState(), ConsumerOrderInfoState.CarChecked.getCode())) {
			//校验info单下所有车辆已验车
			Map<String,Object> m1 = new HashMap<>();
			m1.put("infoId", info.getId());
			m1.put("isDel", 0);
			List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m1);
			for(ConsumerOrderCar c :cars) {
				if(!Objects.equals(c.getAuditState(), ConsumerOrderCarAuditState.Audited.getCode())) {
					throw new BusinessException(ResultCode.CODE_STATE_4006, "有车辆未校验");
				}
			}
		}
		
		info.setState(search.getState());
		consumerOrderInfoDao.updateById(info);
		return new Result(ResultCode.CODE_STATE_200,true,"操作成功");
	}
	
	@Override
	public Result delete(Integer id) {
		// TODO Auto-generated method stub
		//校验订单状态
		 ConsumerOrderInfo info = consumerOrderInfoDao.selectById(id);
		 if(info == null) {
			 throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		 } 
		 ConsumerOrder order = consumerOrderDao.selectById(info.getOrderId());
		 if(order == null) {
			 throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		 }
		 if(order.getState() > ConsumerOrderState.CarDistributing.getCode()) {
			 throw new BusinessException(ResultCode.CODE_STATE_4006, "该单已支付定金，不能删除");
		 }
		 
		 info.setIsDel(1);
		 if(consumerOrderInfoDao.updateById(info)) {
			 return new Result(ResultCode.CODE_STATE_200,true,"删除成功");
		 }
		 return new Result(ResultCode.CODE_STATE_200,false,"删除失败");
	}
	
	@Override
	@Transactional
	public Result changePrice(ConsumerOrderInfoChangePriceSearch search) {
		// TODO Auto-generated method stub
		ConsumerOrderInfo info = consumerOrderInfoDao.selectById(search.getInfoId());
		if(info == null) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "参数错误");
		}
		
		Map<String, Object> m = new HashMap<>();
		m.put("infoId", info.getId());
		m.put("isDel", 0);
		m.put("auditState", ConsumerOrderCarAuditState.PriceChanging.getCode());
		List<ConsumerOrderCar> cars = consumerOrderCarDao.select(m);
		if(cars == null || cars.isEmpty()) {
			throw new BusinessException(ResultCode.CODE_STATE_4006, "无法修改价格");
		}
		
		//更新车辆状态
		for(ConsumerOrderCar car : cars) {
			car.setAuditState(ConsumerOrderCarAuditState.PriceChanged.getCode());
			consumerOrderCarDao.updateById(car);
		}
		
		//更新价格
		info.setRemark(search.getRemark());
		info.setChangePrice(search.getChangePrice());
		info.setNakedPrice(info.getGuidePrice().add(info.getChangePrice()));
		info.setTrafficCompulsoryInsurancePrice(search.getTrafficCompulsoryInsurancePrice());
		info.setCommercialInsurancePrice(search.getCommercialInsurancePrice());
		consumerOrderInfoDao.updateById(info);
		
		return new Result(info);
	}
}
