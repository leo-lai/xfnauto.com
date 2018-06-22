package main.com.stock.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import com.wilddog.client.core.s;

import main.com.allInPay.utils.AllInPayInfo;
import main.com.allInPay.utils.AllInPayUtil;
import main.com.allInPay.utils.HttpClientUtil;
import main.com.car.dao.dao.CarColourDao;
import main.com.car.dao.dao.CarInteriorDao;
import main.com.car.dao.dao.CarsDao;
import main.com.car.dao.dao.OrgCarsConfigureDao;
import main.com.car.dao.po.OrgCarsConfigure;
import main.com.car.dao.vo.CarColourVo;
import main.com.car.dao.vo.CarInteriorVo;
import main.com.car.dao.vo.CarsVo;
import main.com.customer.dao.dao.CustomerCarDao;
import main.com.customer.dao.dao.CustomerOrderDao;
import main.com.customer.dao.dao.CustomerOrderTrackDao;
import main.com.customer.dao.po.CustomerOrderTrack;
import main.com.customer.dao.vo.CustomerCarVo;
import main.com.customer.dao.vo.CustomerOrderVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.pay.allInPay.AllInPaySubmit;
import main.com.pay.allInPay.SystemConfig;
import main.com.stock.dao.dao.StockCarDao;
import main.com.stock.dao.dao.StockOrderDao;
import main.com.stock.dao.dao.StockStorageDao;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.po.StockOrder;
import main.com.stock.dao.po.StockStorage;
import main.com.stock.dao.search.StockOrderSearch;
import main.com.stock.dao.vo.StockCarVo;
import main.com.stock.dao.vo.StockOrderVo;
import main.com.stock.service.StockOrderService;
import main.com.system.dao.dao.OrganizationDao;
import main.com.system.dao.dao.SystemWarehouseDao;
import main.com.system.dao.po.Organization;
import main.com.system.dao.po.SystemUsers;
import main.com.system.dao.po.SystemWarehouse;
import main.com.system.dao.vo.SystemWarehouseVo;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.StringCode;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class StockOrderServiceImpl extends BaseServiceImpl<StockOrder> implements StockOrderService {

	@Autowired
	StockOrderDao stockOrderDao;
	
	@Autowired
	StockCarDao stockCarDao;
	
	@Autowired
	CarsDao carsDao;
	
	@Autowired
	CarColourDao carColourDao;
	
	@Autowired
	CarInteriorDao carInteriorDao;
	
	@Autowired
	OrganizationDao organizationDao;
	
	@Autowired
	SystemWarehouseDao systemWarehouseDao;
	
	@Autowired
	StockStorageDao stockStorageDao;
	
	@Autowired
	OrgCarsConfigureDao orgCarsConfigureDao;
	
	@Autowired
	CustomerOrderDao customerOrderDao;
	
	@Autowired
	CustomerCarDao customerCarDao;
	
	@Autowired
	CustomerOrderTrackDao customerOrderTrackDao;
	
	@Override
	protected BaseDao<StockOrder> getBaseDao() {
		return stockOrderDao;
	}
	
	@Override
	public Result stockOrderList(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请先联系管理员");
			return result;
		}
		Map<String,Object> params = getParams(search,users);
		List<StockOrderVo> stockOrderVos = stockOrderDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = stockOrderDao.getRowCount(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(StockOrderVo orderVo : stockOrderVos) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("carsName",orderVo.getCarsInfo());
			map.put("createDate",orderVo.getCreateDate() != null ? DateUtil.format(orderVo.getCreateDate()):"");
			map.put("buyOrgName",orderVo.getStockOrderBuyOrgName());
			map.put("carsId", orderVo.getCarsId());//
			map.put("colourName", orderVo.getColourName());//
			map.put("colourId", orderVo.getColourId());//
			map.put("interiorId", orderVo.getInteriorId());//
			map.put("interiorName", orderVo.getInteriorName());
			map.put("stockOrderState", orderVo.getStockOrderState());
			map.put("stockOrderId", orderVo.getStockOrderId());
			map.put("stockOrderCode", orderVo.getStockOrderCode());			
			map.put("stockOrderNumber", orderVo.getStockOrderNumber());			
			map.put("guidingPrice", orderVo.getGuidingPrice()!=null?orderVo.getGuidingPrice().doubleValue():0);//指导价			
			map.put("balancePrice", orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0);//尾款			
			map.put("depositPrice", orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0);//定金
			if(orderVo.getPayDateBefor()!=null && orderVo.getPayMethodBefor() != null && orderVo.getPayDateAfter() == null && orderVo.getPayMethodAfter() == null) {				
				map.put("payBrief", "已支付定金："+(orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0)+"。共支付总额："+(orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0));//定金				
				map.put("paystate", 2);//需要支付尾款
			}else
			if(orderVo.getPayDateBefor()!=null && orderVo.getPayMethodBefor() != null && orderVo.getPayDateAfter() != null && orderVo.getPayMethodAfter() != null) {
				map.put("payBrief", "已支付定金："+ (orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0) + "。已支付尾款："+
			(orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0)+"。共支付总额："+
						((orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0)+(orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0)));//
				map.put("paystate", 3);//已经全部支付
			}else if(orderVo.getPayDateBefor()==null && orderVo.getPayMethodBefor() == null && orderVo.getPayDateAfter() != null && orderVo.getPayMethodAfter() != null) {
				map.put("payBrief", "定金未支付。已支付尾款："+
						(orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0)+"。共支付总额："+
									((orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0)));//
				map.put("paystate", 1);//需要支付定金
			}else {
				map.put("payBrief", "未支付");
				map.put("paystate", 1);//需要支付定金
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
	
	public Map<String,Object> getParams(StockOrderSearch search, SystemUsers users){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getStockOrderCode())) {
			params.put("stockOrderCodeList", search.getStockOrderCode());
		}
		if(StringUtil.isNotEmpty(search.getStartDate())) {
			params.put("startDate", search.getStartDate());
		}
		if(StringUtil.isNotEmpty(search.getEndDate())) {
			params.put("endDate", search.getEndDate());
		}
		if(StringUtil.isNotEmpty(search.getStockOrderState())) {
			params.put("stockOrderState", search.getStockOrderState());
		}
		if(StringUtil.isNotEmpty(search.getCarsName())) {
			params.put("carsInfo", search.getCarsName());
		}
		if(StringUtil.isNotEmpty(search.getCarsId())) {
			params.put("carsId", search.getCarsId());
		}
		if(search.getIsSellList() != null && search.getIsSellList() ) {
			params.put("orgCodeSell", users.getOrgCode());
		}else {
			params.put("orgCodeBuy", users.getOrgCode());
		}
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows()); 
		return params;
	}

	@Override
	public Result stockOrderInfo(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(search.getStockOrderId())) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体的订单记录查看");
			return result;
		}
		StockOrderVo orderVo = stockOrderDao.selectById(search.getStockOrderId());
		if(orderVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的记录不存在或者已删除");
			return result;
		}		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("carsName",orderVo.getCarsInfo());
		map.put("carsId", orderVo.getCarsId());//
		map.put("colourName", orderVo.getColourName());//
		map.put("colourId", orderVo.getColourId());//
		map.put("interiorId", orderVo.getInteriorId());//
		map.put("interiorName", orderVo.getInteriorName());
		map.put("stockOrderState", orderVo.getStockOrderState());
		map.put("stockOrderId", orderVo.getStockOrderId());
		map.put("stockOrderCode", orderVo.getStockOrderCode());			
		map.put("stockOrderNumber", orderVo.getStockOrderNumber());			
		map.put("guidingPrice", orderVo.getGuidingPrice());//指导价
		map.put("remark", orderVo.getStockOrderRemarks());//备注
		map.put("logisticsOddNumber", orderVo.getLogisticsOddNumber());//物流单号	
		map.put("depositPrice", orderVo.getDepositPrice());//定金
		map.put("balancePrice", orderVo.getBalancePrice());//尾款
		map.put("address", orderVo.getStockOrderBuyOrgAddress());
		map.put("logisticsMode", orderVo.getLogisticsMode());
		map.put("followInformation", orderVo.getFollowInformation());
		map.put("templateImage", orderVo.getTemplateImage());
		
		if(orderVo.getPayDateBefor()!=null && orderVo.getPayMethodBefor() != null && orderVo.getPayDateAfter() == null && orderVo.getPayMethodAfter() == null) {				
			map.put("payBrief", "已支付定金："+(orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0)+"。共支付总额："+(orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0));//定金				
			map.put("paystate", 2);//需要支付尾款
		}else
		if(orderVo.getPayDateBefor()!=null && orderVo.getPayMethodBefor() != null && orderVo.getPayDateAfter() != null && orderVo.getPayMethodAfter() != null) {
			map.put("payBrief", "已支付定金："+ (orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0) + "。已支付尾款："+
		(orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0)+"。共支付总额："+
					((orderVo.getDepositPrice()!=null?orderVo.getDepositPrice().doubleValue():0)+(orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0)));//
			map.put("paystate", 3);//已经全部支付
		}else if(orderVo.getPayDateBefor()==null && orderVo.getPayMethodBefor() == null && orderVo.getPayDateAfter() != null && orderVo.getPayMethodAfter() != null) {
			map.put("payBrief", "定金未支付。已支付尾款："+
					(orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0)+"。共支付总额："+
								((orderVo.getBalancePrice()!=null?orderVo.getBalancePrice().doubleValue():0)));//
			map.put("paystate", 1);//需要支付定金
		}else {
			map.put("payBrief", "未支付");
			map.put("paystate", 1);//需要支付定金
		}
		
		List<Map<String,Object>> carMapList = new ArrayList<Map<String,Object>>();
		if(StringUtil.isNotEmpty(orderVo.getStockCarIds())) {//卖方已经出库具体车辆
			String[] carIds = orderVo.getStockCarIds().split(GeneralConstant.SystemBoolean.SPLIT);
			Set<Integer> set = new HashSet<Integer>();
			for(String carId : carIds) {
				set.add(Integer.parseInt(carId));
			}
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("ids", set);
			List<StockCarVo> carVos = stockCarDao.select(params);//new ArrayList<StockCarVo>();
			for(StockCarVo stockCarVo : carVos) {
				Map<String,Object> carmap = new HashMap<String, Object>();
				carmap.put("frameNumber", stockCarVo.getFrameNumber());
				carmap.put("carsId", stockCarVo.getCarsId());
				carmap.put("engineNumber", stockCarVo.getEngineNumber());
				carmap.put("certificateNumber", stockCarVo.getCertificateNumber());
				carmap.put("warehouseId", stockCarVo.getWarehouseId());
				carmap.put("warehouseName", stockCarVo.getWarehouseName());
				carmap.put("stockCarImages", stockCarVo.getStockCarImages());
				carmap.put("invoicePrice", stockCarVo.getInvoicePrice());
				carmap.put("depositPrice", stockCarVo.getDepositPrice());
				carmap.put("discountPrice", stockCarVo.getDiscountPrice());
				carmap.put("isOnLine", stockCarVo.getIsOnLine());
				carmap.put("stockCarId", stockCarVo.getStockCarId());
				carmap.put("storageId", stockCarVo.getStorageId());
				carmap.put("number", stockCarVo.getNumber());
				carmap.put("createDate", DateUtil.format(stockCarVo.getCreateDate()));
				carMapList.add(TakeCareMapDate.cutNullMap(carmap));
			}
			map.put("list", carMapList);
		}else {
			map.put("list", new ArrayList<Map<String,Object>>());
		}
		result.setOK(ResultCode.CODE_STATE_200, "请求成功",TakeCareMapDate.cutNullMap(map));
		return result;
	}

	@Override
	public Result stockOrderCreate(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(users.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不完整，请联系管理员补充完整");
			return result;
		}
		StockOrder order = new StockOrder();
		Organization organization = organizationDao.selectById(users.getOrgId());
		if(!GeneralConstant.Org.Level_3.equals(organization.getOrgLevel())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司不是门店级别，不能进行此操作");
			return result;
		}
		order.setStockOrderBuyOrgId(organization.getOrgId());
		order.setStockOrderBuyOrgName(organization.getShortName());
		order.setStockOrderBuyOrgAddress(organization.getProvinceName() + organization.getCityName() + organization.getAreaName() + organization.getAddress());		
		Organization organizationParent = organizationDao.selectById(organization.getParentId());
		if(organizationParent == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的上级公司不存在或者已删除，无法发起此项操作");
			return result;
		}
		order.setStockOrderSellOrgId(organizationParent.getOrgId());
		order.setStockOrderSellOrgName(organizationParent.getShortName());
		if(search.getCarsId() != null) {
			CarsVo carVo = carsDao.selectById(search.getCarsId());
			if(carVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车型不存在");
				return result;
			}
			order.setCarsId(carVo.getCarId());
			StringBuffer buffer = new StringBuffer();
				//品牌(本田) + 车系（雅阁） + 年款（2016/2017） + 排量(2.0) + 变速箱类型（手动自动） + 款式（高中低配）
				buffer.setLength(0);
				buffer.append(carVo.getBrandName()).append(" ");
				buffer.append(carVo.getFamilyName()).append(" ");
				buffer.append(carVo.getYearPattern()).append("款").append(" ");
				buffer.append(carVo.getPl()).append(" ");
				buffer.append(carVo.getGearboxName()).append(carVo.getStyleName());
				order.setCarsInfo(buffer.toString());
				order.setGuidingPrice(new BigDecimal(carVo.getPrice()));
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择的车型");
			return result;
		}
		if(search.getColourId() != null) {
			CarColourVo colourVo = carColourDao.selectById(search.getColourId());
			if(colourVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车身颜色不存在");
				return result;
			}
			order.setColourId(colourVo.getCarColourId());
			order.setColourName(colourVo.getCarColourName());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择的车身颜色");
			return result;
		}
		if(search.getInteriorId() != null) {
			CarInteriorVo interiorVo = carInteriorDao.selectById(search.getInteriorId());
			if(interiorVo == null) {
				result.setError(ResultCode.CODE_STATE_4005, "你所选择的车辆内饰不存在");
				return result;
			}
			order.setInteriorId(interiorVo.getInteriorId());
			order.setInteriorName(interiorVo.getInteriorName());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择的车辆内饰");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getStockOrderNumber())) {
			order.setStockOrderNumber(search.getStockOrderNumber());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请输入采购数量");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getStockOrderRemarks())) {
			order.setStockOrderRemarks(search.getStockOrderRemarks());
		}
		if(StringUtil.isNotEmpty(search.getTemplateImage())) {
			order.setTemplateImage(search.getTemplateImage());
		}
		if(search.getCertificateDate() != null) {
			switch (search.getCertificateDate()) {
			case GeneralConstant.StockStorageAbstract.CertificateDate_one:
				order.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_one);
				break;
			case GeneralConstant.StockStorageAbstract.CertificateDate_two:
				order.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_two);
				break;
			case GeneralConstant.StockStorageAbstract.CertificateDate_three:
				order.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_three);
				break;
			case GeneralConstant.StockStorageAbstract.CertificateDate_four:
				order.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_four);
				break;
			case GeneralConstant.StockStorageAbstract.CertificateDate_five:
				order.setCertificateDate(GeneralConstant.StockStorageAbstract.CertificateDate_five);
				break;
			default:
				result.setError("合格证时间选择错误，请重新选择");
				return result;
			}			
		}
		//查询定金和优惠
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("carsId", search.getCarsId());
		params.put("interiorId", search.getInteriorId());
		params.put("colourId", search.getColourId());
		params.put("orgId", organizationParent.getOrgId());
		params.put("orgLevel", organizationParent.getOrgLevel());
		List<OrgCarsConfigure> orgCarsConfigures = orgCarsConfigureDao.select(params);
		if(orgCarsConfigures == null || orgCarsConfigures.size() <= 0) {
			order.setDepositPrice(new BigDecimal(GeneralConstant.StockOrdersState.depositPrice * order.getStockOrderNumber()));//默认定金
			if(order.getDiscountPrice() == null) {
				order.setDiscountPrice(new BigDecimal(0));
			}
			if(order.getGuidingPrice().doubleValue() - order.getDiscountPrice().doubleValue() - GeneralConstant.StockOrdersState.depositPrice <= 0) {
				result.setError(ResultCode.CODE_STATE_4005, "价格错误，指导价减去优惠减去定金以后，尾款数额过低，不合理");
				return result;
			}
			order.setBalancePrice(new BigDecimal((order.getGuidingPrice().doubleValue() - order.getDiscountPrice().doubleValue() - GeneralConstant.StockOrdersState.depositPrice) * order.getStockOrderNumber()));//默认定金
		}else {
			OrgCarsConfigure carsConfigure = orgCarsConfigures.get(0);
			order.setDepositPrice(new BigDecimal(carsConfigure.getDepositPrice().doubleValue() * order.getStockOrderNumber()));//默认定金
			if(carsConfigure.getDepositPrice() == null) {
				carsConfigure.setDepositPrice(new BigDecimal(0));
			}
			if(carsConfigure.getGuidingPrice().doubleValue() - carsConfigure.getDepositPrice().doubleValue() - carsConfigure.getDepositPrice().doubleValue() <= 0) {
				result.setError(ResultCode.CODE_STATE_4005, "价格错误，指导价减去优惠减去定金以后，尾款数额过低，不合理");
				return result;
			}
			order.setBalancePrice(new BigDecimal((carsConfigure.getGuidingPrice().doubleValue() - carsConfigure.getDiscountPrice().doubleValue() - carsConfigure.getDepositPrice().doubleValue()) * order.getStockOrderNumber()));//默认定金
		}
		order.setStockOrderState(GeneralConstant.StockOrdersState.STATE_STATER);
		order.setCreateDate(new Date());
		order.setIsDelete(false);
		order.setStockOrderCode(stockOrderDao.getOrderCode());
		order.setStatementCodeAfter(order.getStockOrderCode()+"_2");
		order.setStatementCodeBefor(order.getStockOrderCode()+"_1");		
		return stockOrderDao.insertAndResultIT(order, result);
	}

	@Override
	public Result stockOrderPay(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getStockOrderId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体订单进行操作");
			return result;
		}
		StockOrder order = stockOrderDao.selectById(search.getStockOrderId());
		if(order == null){
			result.setError(ResultCode.CODE_STATE_4006, "订单信息错误支付失败。请返回订单列表重新支付！");
			return result;
		}
		if(search.getIsDeposit() == null) {
			search.setIsDeposit(false);
		}
		if(search.getPayWay().equals(GeneralConstant.PayWay.PAY_BY_WEB)) {//网关
			result = ordersPayInfo(order,search);
		}else if(search.getPayWay().equals(GeneralConstant.PayWay.PAY_BY_POS)){//POS机
			//4、保存处理的信息
			String payChoose = "";
			String chooseId = "";
			if(search.getIsDeposit()) {//付定金
				if(order.getPayDateBefor() != null && order.getPayMethodBefor() != null) {
					result.setError(ResultCode.CODE_STATE_4006, "订单已支付定金，请勿重复支付");
					return result;
				}
				payChoose = "Y,"+ order.getDepositPrice().doubleValue()+","+order.getStatementCodeBefor();
				chooseId = order.getStatementCodeBefor();
			}else{//付尾款
				if(order.getBalancePrice() == null || order.getBalancePrice().doubleValue() <= 0d) {
					result.setError(ResultCode.CODE_STATE_4006, "订单尾款数额为0，不能发起支付");
					return result;
				}
				if(order.getPayDateAfter() != null && order.getPayMethodAfter() != null) {
					result.setError(ResultCode.CODE_STATE_4006, "订单已支付尾款，请勿重复支付");
					return result;
				}
				payChoose = "Y,"+ order.getBalancePrice().doubleValue()+","+order.getStatementCodeAfter();
				chooseId = order.getStatementCodeAfter();
			}
//			else {
//				result.setError(ResultCode.CODE_STATE_4005, "该订单不是未付定金或未付尾款状态，不能发起支付");
//				return result;
//			}
//			if(GeneralConstant.StockOrdersState.STATE_STATER.equals(order.getStockOrderState())) {//付定金
//				payChoose = "Y,"+ order.getDepositPrice().doubleValue()+","+order.getStatementCodeBefor();
//				chooseId = order.getStatementCodeBefor();
//			}else if(GeneralConstant.StockOrdersState.STATE_NOTICE.equals(order.getStockOrderState())) {//付尾款
//				payChoose = "Y,"+ order.getDepositPrice().doubleValue()+","+order.getStatementCodeAfter();
//				chooseId = order.getStatementCodeAfter();
//			}else {
//				result.setError(ResultCode.CODE_STATE_4005, "该订单不是未付定金或未付尾款状态，不能发起支付");
//				return result;
//			}
			String message = "订单已提交，等待支付.....";	
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("payChoose", payChoose);
			map.put("chooseId", chooseId);
			result.setOK(ResultCode.CODE_STATE_200, message,map);
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体的支付方式");
		}
		return result;
	}
	
	/**
	 * 订单支付
	 */
	public Result ordersPayInfo(StockOrder stockOrder,StockOrderSearch search) throws Exception {
		Result result = new Result();
		try {
			
			AllInPaySubmit allInPaySubmit = new AllInPaySubmit();
			/*
			 private String inputCharset;//	字符集	2	不可空	默认填1；1代表UTF-8、2代表GBK、3代表GB2312；
	private String pickupUrl;//	付款客户的取货url地址	100	不为空	客户的取货地址
	private String receiveUrl;//	服务器接受支付结果的后台地址	100	不为空	通知商户网站支付结果的url地址
	private String version;//	网关接收支付请求接口版本	10	不可空	固定填v1.0 
	private String language;//	网关页面显示语言种类	2	不为空	默认填1，固定选择值：1；1代表简体中文、2代表繁体中文、3代表英文
	private String signType;//	签名类型	2	不可空	默认填1，固定选择值：0、1；0表示订单上送和交易结果通知都使用MD5进行签名1表示商户用使用MD5算法验签上送订单，通联交易结果通知使用证书签名Asp商户不使用通联dll文件签名验签的商户填0
	private String merchantId;//	商户号	30	不可空	数字串，商户在通联申请开户的商户号
	private String payerName;//	付款人姓名	32	可为空	跨境支付商户若采用直连模式，必须填写该值
	private String payerEmail;//	付款人邮件联系方式	50	可为空	字符串
	private String payerTelephone;//	付款人电话联系方式	16	可为空	数字串
	private String orderNo;//	商户订单号	50	不可空	字符串，只允许使用字母、数字、- 、_,并以字母或数字开头；每商户提交的订单号，必须在当天的该商户所有交易中唯一
	private String orderAmount;//	商户订单金额	10	不可空	整型数字，金额与币种有关如果是人民币，则单位是分，即10元提交时金额应为1000如果是美元，单位是美分，即10美元提交时金额为1000
	private String orderCurrency;//	订单金额币种类型	3	不可空	默认填00和156代表人民币、840代表美元、344代表港币，跨境支付商户不建议使用0
	private String orderDatetime;//	商户订单提交时间	14	不可空	日期格式：yyyyMMDDhhmmss，例如：20121116020101
	private String orderExpireDatetime;// 	订单过期时间	14	可为空	整形数字，单位为分钟。最大值为9999分钟。如填写则以商户上送时间为准，如不填写或填0或填非法值，则服务端默认该订单9999分钟后过期，超期后不允许商户发起同一商户订单支付
	private String productName;//	商品名称	256	可为空	英文或中文字符串，请勿首尾有空格字符
	private String productPrice;//	商品价格	20	可为空	整型数字
	private String productNum;//	商品数量	8	可为空	整型数字，默认传1
	private String productId;//	商品代码	20	可为空	字母、数字或- 、_ 的组合；用于使用产品数据中心的产品数据，或用于市场活动的优惠
	private String productDesc;//	商品描述	400	可为空	英文或中文字符串
	private String ext1;//	扩展字段1	128	可为空	英文或中文字符串，支付完成后，按照原样返回给商户
	private String ext2;//	扩展字段2	128	可为空	英文或中文字符串，支付完成后，按照原样返回给商户
	private String extTL;//	业务扩展字段	1024	可为空	参见《接口技术规范文档3.9节介绍》
	private String payType;//	支付方式	2	不可空	固定选择值：0、1、4、11、23、28接入互联网关时，默认为间连模式，填0若需接入外卡支付，只支持直连模式，即固定上送payType=23，issuerId=visa或mastercard
	//0代表未指定支付方式，即显示该商户开通的所有支付方式1个人储蓄卡网银支付4企业网银支付11个人信用卡网银支付23外卡支付28认证支付非直连模式，设置为0；直连模式，值为非0的固定选择值
	private String issuerId;//	发卡方代码	8	可为空	银行或预付卡发卡机构代码，用于指定支付使用的付款方机构。接入手机网关时，该值留空
//	payType为0时，issuerId必须为空——显示该商户支持的所有支付类型和各支付类型下支持的全部发卡机构
//	payType为非0时，若issuerId为空——显示该商户所填payType支付类型下的全部发卡机构
//	payType为非0时，若issuerId不为空——直接跳转到该商户所填payType下指定的发卡机构网银页面，支持发卡机构详见附录《机构代码》
	private String pan;//	付款人支付卡号	19	可为空	数字串
//	如果是民生银行B2B直连模式，企业客户号，必填；
//	如果是投融资行业希望支付卡号，则必填
//	上送的卡号必须使用公钥加密(PKCS1)后进行Base64编码。
	private String tradeNature;//	贸易类型	2	可为空	固定选择值：GOODS或SERVICES
//	当币种为人民币时选填
//	当币种为非人民币时必填，GOODS表示实物类型，SERVICES表示服务类型
	private String signMsg;//	签名字符串	1024	不可空	为防止非法篡改要求商户对请求内容进行签名，供服务端进行校验；签名串生成机制：按上述顺序所有非空参数与密钥key组合，经加密后生成signMsg；
	private String customsExt;//	海关扩展字段	1024	可为空	详见“3.13.3海关扩展字段要求”章节
	private String payerIDCard;//	付款人类型及证件号	22	可为空	填写规则：证件类型+证件号码再使用通联公钥加密。（加密请参考示例代码）证件类型仅支持01-身份证跨境支付商户若采用直连模式，必须填写该值
	private String pid;//	合作伙伴的商户号	30	可为空	用于商户与第三方合作伙伴拓展支付业务，Partner merchantId
			 */
			//准备支付记录
			//orderRecord 在第N次改版之后已经失去了效用，此处使用只用于记录支付创建时间，用于后台查询而已
			StringBuffer buffer = new StringBuffer();
//			buffer.append("&");
			//拼装支付参数
			SimpleDateFormat formatFrom = new SimpleDateFormat("yyyyMMddhhmmss");
			allInPaySubmit.setInputCharset(SystemConfig.getInputCharset());
			forsign(buffer,"inputCharset",SystemConfig.getInputCharset());
			allInPaySubmit.setPickupUrl(search.getPickupUrl());
			forsign(buffer,"pickupUrl",search.getPickupUrl());
//			allInPaySubmit.setReceiveUrl(SystemConfig.getReceiveUrl());
//			forsign(buffer,"receiveUrl",SystemConfig.getReceiveUrl());
			
			allInPaySubmit.setReceiveUrl(SystemConfig.getReceiveUrl());
			forsign(buffer,"receiveUrl",SystemConfig.getReceiveUrl());
			
			allInPaySubmit.setVersion(SystemConfig.getVersion());
			forsign(buffer,"version",SystemConfig.getVersion());
			allInPaySubmit.setLanguage(SystemConfig.getLanguage());
			forsign(buffer,"language",SystemConfig.getLanguage());
			allInPaySubmit.setSignType(SystemConfig.getSignType());
			forsign(buffer,"signType",SystemConfig.getSignType());
			
			allInPaySubmit.setMerchantId(SystemConfig.getMerchantId());//AllInPayInfo.AllInPayInfo_Test.merchantId_WEB
			forsign(buffer,"merchantId",SystemConfig.getMerchantId());
			
			allInPaySubmit.setPayerName(stockOrder.getStockOrderBuyOrgName());
			forsign(buffer,"payerName",stockOrder.getStockOrderBuyOrgName());
			

//			BigDecimal payAmount = new BigDecimal();
			if(search.getIsDeposit()) {//付定金
				allInPaySubmit.setOrderNo(stockOrder.getStatementCodeBefor());
				forsign(buffer,"orderNo",stockOrder.getStatementCodeBefor());
				BigDecimal payAmount = new BigDecimal(stockOrder.getDepositPrice().doubleValue()*10*10);
				payAmount = payAmount.multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_UP);
				allInPaySubmit.setOrderAmount(payAmount.intValue()+"");
				forsign(buffer,"orderAmount",(payAmount.intValue())+"");
			}else{//付尾款
				if(stockOrder.getBalancePrice() == null || stockOrder.getBalancePrice().doubleValue() <= 0d) {
					result.setError(ResultCode.CODE_STATE_4006, "订单尾款数额为0，不能发起支付");
					return result;
				}
				allInPaySubmit.setOrderNo(stockOrder.getStatementCodeAfter());
				forsign(buffer,"orderNo",stockOrder.getStatementCodeAfter());
				BigDecimal payAmount = new BigDecimal(stockOrder.getBalancePrice().doubleValue()*10*10);
				payAmount = payAmount.multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_UP);
				allInPaySubmit.setOrderAmount(payAmount.intValue()+"");
				forsign(buffer,"orderAmount",(payAmount.intValue())+"");
			}
//			if(GeneralConstant.StockOrdersState.STATE_STATER.equals(stockOrder.getStockOrderState())) {//付定金
//				allInPaySubmit.setOrderNo(stockOrder.getStatementCodeBefor());
//				forsign(buffer,"orderNo",stockOrder.getStatementCodeBefor());
//				BigDecimal payAmount = new BigDecimal(stockOrder.getDepositPrice().doubleValue()*10*10);
//				payAmount = payAmount.multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_UP);
//				allInPaySubmit.setOrderAmount(payAmount.intValue()+"");
//				forsign(buffer,"orderAmount",(payAmount.intValue())+"");
//			}else if(GeneralConstant.StockOrdersState.STATE_NOTICE.equals(stockOrder.getStockOrderState())) {//付尾款
//				allInPaySubmit.setOrderNo(stockOrder.getStatementCodeAfter());
//				forsign(buffer,"orderNo",stockOrder.getStatementCodeAfter());
//				BigDecimal payAmount = new BigDecimal(stockOrder.getBalancePrice().doubleValue()*10*10);
//				payAmount = payAmount.multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_UP);
//				allInPaySubmit.setOrderAmount(payAmount.intValue()+"");
//				forsign(buffer,"orderAmount",(payAmount.intValue())+"");
//			}
//			else {
//				result.setError(ResultCode.CODE_STATE_4006, "该订单不是未付定金或未付尾款状态，不能发起支付");
//				return result;
//			}
			allInPaySubmit.setOrderCurrency(SystemConfig.getOrderCurrency());
			forsign(buffer,"orderCurrency",SystemConfig.getOrderCurrency());
			//使创建时间保持一致
			allInPaySubmit.setOrderDatetime(formatFrom.format(stockOrder.getCreateDate()));
			forsign(buffer,"orderDatetime",allInPaySubmit.getOrderDatetime());
			allInPaySubmit.setOrderExpireDatetime(SystemConfig.getOrderExpireDatetime());
			forsign(buffer,"orderExpireDatetime",allInPaySubmit.getOrderExpireDatetime());
			
			/**
			 * 测试为get请求，暂不带中文
			 */
			allInPaySubmit.setProductName(stockOrder.getCarsInfo());
			forsign(buffer,"productName",allInPaySubmit.getProductName());
			if(search.getIsDeposit()) {//付定金
				BigDecimal unitPrice = new BigDecimal((stockOrder.getDepositPrice().doubleValue()/stockOrder.getStockOrderNumber())*10*10);
				unitPrice = unitPrice.multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_UP);
				allInPaySubmit.setProductPrice(unitPrice.intValue()+"");
				forsign(buffer,"productPrice",allInPaySubmit.getProductPrice());
			}else {//付尾款
				BigDecimal unitPrice = new BigDecimal((stockOrder.getBalancePrice().doubleValue()/stockOrder.getStockOrderNumber())*10*10);
				unitPrice = unitPrice.multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_UP);
				allInPaySubmit.setProductPrice(unitPrice.intValue()+"");
				forsign(buffer,"productPrice",allInPaySubmit.getProductPrice());
			}
//			if(GeneralConstant.StockOrdersState.STATE_STATER.equals(stockOrder.getStockOrderState())) {//付定金
//				BigDecimal unitPrice = new BigDecimal((stockOrder.getDepositPrice().doubleValue()/stockOrder.getStockOrderNumber())*10*10);
//				unitPrice = unitPrice.multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_UP);
//				allInPaySubmit.setProductPrice(unitPrice.intValue()+"");
//				forsign(buffer,"productPrice",allInPaySubmit.getProductPrice());
//			}else if(GeneralConstant.StockOrdersState.STATE_NOTICE.equals(stockOrder.getStockOrderState())) {//付尾款
//				BigDecimal unitPrice = new BigDecimal((stockOrder.getBalancePrice().doubleValue()/stockOrder.getStockOrderNumber())*10*10);
//				unitPrice = unitPrice.multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_UP);
//				allInPaySubmit.setProductPrice(unitPrice.intValue()+"");
//				forsign(buffer,"productPrice",allInPaySubmit.getProductPrice());
//			}else {
//				result.setError(ResultCode.CODE_STATE_4006, "该订单不是未付定金或未付尾款状态，不能发起支付");
//				return result;
//			}
			allInPaySubmit.setProductNum(stockOrder.getStockOrderNumber()+"");
			forsign(buffer,"productNum",allInPaySubmit.getProductNum());
			
			allInPaySubmit.setPayType(SystemConfig.getPayType());
			forsign(buffer,"payType",SystemConfig.getPayType());
			//签名
//			TreeMap<String, String> params = BeanUtils.toTreeMap(allInPaySubmit);
			
			allInPaySubmit.setSignMsg(AllInPayUtil.sign(buffer, SystemConfig.getMD5Key()));
//			params.put("signMsg", allInPaySubmit.getSignMsg());
			//跨域请求，去掉头和尾的 & 符号(在这里不需要发起请求，由前端进行把参数变成from表单发起POST请求即可)
//			String urlparams = "";
//			if(!buffer.toString().equals("")){
//				urlparams = buffer.substring(1, buffer.length()-1);
//				HttpClientUtil.transboundaryRequest(SystemConfig.getPayUrl(),urlparams);//发起跨域请求
//			}
			//记录支付操作过程
			result.setOK(ResultCode.CODE_STATE_200, "", allInPaySubmit);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(ResultCode.CODE_STATE_4006, "系统繁忙，请刷新重试！");
		}
		return result;
	}
	
	void forsign(StringBuffer buffer,String name,String value){
		if(value == null || "".equals(value)){
			return;
		}
		buffer.append(name).append("=").append(value).append("&");
	}

	@Override
	public Result stockOrderCancel(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getStockOrderId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体订单进行操作");
			return result;
		}
		StockOrder order = stockOrderDao.selectById(search.getStockOrderId());
		if(order == null){
			result.setError(ResultCode.CODE_STATE_4006, "订单信息错误，你选择的订单已删除或不存在。请刷新重试或者重新选择");
			return result;
		}
		if(users.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不完整，请联系管理员补充完整");
			return result;
		}
		if(!order.getStockOrderBuyOrgId().equals(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单的买方与你公司信息不匹配，你不能进行修改");
			return result;
		}
		if(order.getPayDateBefor() != null && order.getPayMethodBefor() != null) {
			result.setError(ResultCode.CODE_STATE_4005, "订单已收取定金，订单正在持续，已不能进行取消");
			return result;
		}
//		if(!GeneralConstant.StockOrdersState.STATE_STATER.equals(order.getStockOrderState())) {
//			result.setError(ResultCode.CODE_STATE_4005, "订单正在持续，已不能进行取消");
//			return result;
//		}
		order.setStockOrderState(GeneralConstant.StockOrdersState.STATE_CANCEL);
		order.setIsCancel(true);
		return stockOrderDao.updateByIdAndResultIT(order, result);
	}

	@Override
	@Transactional
	public Result stockOrderSign(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getStockOrderId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体订单进行操作");
			return result;
		}
		StockOrder order = stockOrderDao.selectById(search.getStockOrderId());
		if(order == null){
			result.setError(ResultCode.CODE_STATE_4006, "订单信息错误，你选择的订单已删除或不存在。请刷新重试或者重新选择");
			return result;
		}
		if(users.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不完整，请联系管理员补充完整");
			return result;
		}
		if(!order.getStockOrderBuyOrgId().equals(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单的买方与你公司信息不匹配，你不能进行修改");
			return result;
		}
		if(!GeneralConstant.StockOrdersState.STATE_LEAVEON.equals(order.getStockOrderState())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单商品尚未出库，尚不能进行签收入库");
			return result;
		}
		if(order.getPayDateBefor() == null || order.getPayMethodBefor()==null || order.getPayDateAfter() == null || order.getPayMethodAfter()== null) {
			result.setError(ResultCode.CODE_STATE_4005, "订单尚未支付全款（定金和尾款），尚不能进行签收入库");
			return result;
		}
		order.setStockOrderState(GeneralConstant.StockOrdersState.STATE_STORAGEPUTIN);//更改订单状态
		//入库
		if(StringUtil.isEmpty(order.getStockCarIds())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单车辆信息缺失，不能进行此操作，请联系管理员");
			return result;
		}
		//卖方已经出库具体车辆
		String[] carIds = order.getStockCarIds().split(GeneralConstant.SystemBoolean.SPLIT);
		Set<Integer> set = new HashSet<Integer>();
		for(String carId : carIds) {
			set.add(Integer.parseInt(carId));
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ids", set);
		params.put("isPutOut", true);
		List<StockCarVo> carVos = stockCarDao.select(params);//new ArrayList<StockCarVo>();
		List<StockCar> caStockCars = new ArrayList<StockCar>();
		
		//查询仓库
		Map<String, Object> wparams =  new HashMap<String,Object>();
		wparams.put("OrgCode", users.getOrgCode());
		wparams.put("isDelete", false);
		wparams.put("warehouseType", GeneralConstant.SystemWarehouseType.vehicle);
		List<SystemWarehouseVo> warehouseVos = systemWarehouseDao.select(wparams);
		if(warehouseVos == null || warehouseVos.size() <= 0) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司缺少整车仓，自动入库失败，请先补充仓库信息");
			return result;
		}
		SystemWarehouse systemWarehouse = warehouseVos.get(0);		
		for(StockCarVo stockCarVo : carVos) {
			StockCar car = new StockCar();
			car.setFrameNumber(stockCarVo.getFrameNumber());
			car.setCarsId(stockCarVo.getCarsId());
			car.setEngineNumber(stockCarVo.getEngineNumber());
			car.setCertificateNumber(stockCarVo.getCertificateNumber());
			car.setStockCarImages(stockCarVo.getStockCarImages());
			car.setInvoicePrice(stockCarVo.getInvoicePrice());
			car.setDepositPrice(stockCarVo.getDepositPrice());
			car.setDiscountPrice(stockCarVo.getDiscountPrice());
			car.setIsOnLine(true);
			car.setOrderId(order.getStockOrderId());
			car.setUnitPrice(stockCarVo.getUnitPrice());
			car.setCarsInfo(stockCarVo.getCarsInfo());
			car.setGuidingPrice(stockCarVo.getGuidingPrice());
			car.setColourId(stockCarVo.getColourId());
			car.setColourName(stockCarVo.getColourName());
			car.setInteriorId(stockCarVo.getInteriorId());
			car.setInteriorName(stockCarVo.getInteriorName());
			car.setCreateDate(new Date());
			car.setIsDelete(false);
			car.setWarehouseId(systemWarehouse.getWarehouseId());
			car.setWarehouseName(systemWarehouse.getWarehouseName());
			car.setOrgId(users.getOrgId());
			car.setIsPutOut(false);
			caStockCars.add(car);
		}
		//新建入库单
		StockStorage stockStorage = new StockStorage();
		stockStorage.setStorageCode(stockStorageDao.getStorageCode());
		stockStorage.setOrgId(users.getOrgId());
		stockStorage.setOrgName(users.getOrgName());
		stockStorage.setIsDelete(false);
		stockStorage.setCreateDate(new Date());
		stockStorage.setStorageSource(3);
		stockStorage.setTotalPurchase(order.getStockOrderNumber());
		stockStorage.setTotalPurchasePrice(order.getBalancePrice().doubleValue() + order.getDepositPrice().doubleValue());
		if(stockOrderDao.updateById(order)) {//更新订单状态
			//保存入库单
			stockStorageDao.insert(stockStorage);
			//入库
			for(StockCar car : caStockCars) {
				car.setStorageId(stockStorage.getStorageId());
				stockCarDao.insert(car);
			}
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "数据保存失败，请刷新重试或联系管理员");			
		}
		return result;
	}

	@Override
	public Result stockOrderchangebalancePrice(StockOrderSearch search, Result result, SystemUsers users)
			throws Exception {
		if(search.getStockOrderId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体订单进行操作");
			return result;
		}
		StockOrder order = stockOrderDao.selectById(search.getStockOrderId());
		if(order == null){
			result.setError(ResultCode.CODE_STATE_4006, "订单信息错误，你选择的订单已删除或不存在。请刷新重试或者重新选择");
			return result;
		}
		if(users.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不完整，请联系管理员补充完整");
			return result;
		}
		if(!order.getStockOrderSellOrgId().equals(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单的卖方与你公司信息不匹配，你不能进行修改");
			return result;
		}
		if(search.getBalancePrice() == null || search.getBalancePrice() <= 0) {
			result.setError(ResultCode.CODE_STATE_4005, "请输入尾款金额");
			return result;
		}
		order.setBalancePrice(new BigDecimal(search.getBalancePrice()));
		return stockOrderDao.updateByIdAndResultIT(order, result);
	}

	/**
	 * 
	 */
	@Override
	public Result stockOrderNotice(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getStockOrderId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体订单进行操作");
			return result;
		}
		StockOrder order = stockOrderDao.selectById(search.getStockOrderId());
		if(order == null){
			result.setError(ResultCode.CODE_STATE_4006, "订单信息错误，你选择的订单已删除或不存在。请刷新重试或者重新选择");
			return result;
		}
		if(users.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不完整，请联系管理员补充完整");
			return result;
		}
		if(!order.getStockOrderSellOrgId().equals(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单的卖方与你公司信息不匹配，你不能进行修改");
			return result;
		}
//		if(!GeneralConstant.StockOrdersState.STATE_INPAY.equals(order.getStockOrderState())) {//改变支付与状态的挂钩，全开放，不再使用支付来限制 20180105
//			result.setError(ResultCode.CODE_STATE_4005, "订单不是已支付未通知状态，不能进行此操作");
//			return result;
//		}
		if(!GeneralConstant.StockOrdersState.STATE_STATER.equals(order.getStockOrderState())) {//改变支付与状态的挂钩，全开放，不再使用支付来限制 20180105
			result.setError(ResultCode.CODE_STATE_4005, "该订单不是初始下单状态，不能进行此操作");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(order.getCarsId())) {
			params.put("carsId", order.getCarsId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+order.getStockOrderCode()+"的车型数据错误，无法进行此操作");
			return result;
		}
		if(StringUtil.isNotEmpty(order.getColourId())) {
			params.put("colourId", order.getColourId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+order.getStockOrderCode()+"的车身颜色数据错误，无法进行此操作");
			return result;
		}
		if(StringUtil.isNotEmpty(order.getInteriorId())) {
			params.put("interiorId", order.getInteriorId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+order.getStockOrderCode()+"的车辆内饰数据错误，无法进行此操作");
			return result;
		}
		params.put("stockOrgId", users.getOrgId());
		List<StockCarVo> cars = stockCarDao.select(params);		
		if(cars == null || cars.size() <= 0) {
			result.setError(ResultCode.CODE_STATE_4005, "库存车辆不足");
			return result;
		}else if(cars.size() < order.getStockOrderNumber()) {
			result.setError(ResultCode.CODE_STATE_4005, "库存车辆不足");
			return result;
		}
		if(search.getBalancePrice() != null && search.getBalancePrice() > 0f) {
			order.setBalancePrice(new BigDecimal(search.getBalancePrice()));
		}
		order.setStockOrderState(GeneralConstant.StockOrdersState.STATE_NOTICE);
		return stockOrderDao.updateByIdAndResultIT(order, result);
	}

	@Override
	public Result stockOrderStorageOut(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(search.getStockOrderId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体订单进行操作");
			return result;
		}
		StockOrder order = stockOrderDao.selectById(search.getStockOrderId());
		if(order == null){
			result.setError(ResultCode.CODE_STATE_4006, "订单信息错误，你选择的订单已删除或不存在。请刷新重试或者重新选择");
			return result;
		}
		if(users.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不完整，请联系管理员补充完整");
			return result;
		}
		if(!order.getStockOrderSellOrgId().equals(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "订单的卖方与你公司信息不匹配，你不能进行修改");
			return result;
		}
//		if(!GeneralConstant.StockOrdersState.STATE_STORAGEOUT.equals(order.getStockOrderState())) {//改变订单的支付与状态的挂钩方式，全开放，不再使用支付来限制 20180105
//			result.setError(ResultCode.CODE_STATE_4005, "订单尾款尚未支付，尚未能出库");
//			return result;
//		}
		if(!GeneralConstant.StockOrdersState.STATE_NOTICE.equals(order.getStockOrderState())) {//改变订单的支付与状态的挂钩方式，全开放，不再使用支付来限制 20180105
			result.setError(ResultCode.CODE_STATE_4005, "您尚未通知买方有车，尚未能出库，请先进行通知有车操作");
			return result;
		}
		Set<Integer> set = new HashSet<Integer>();
		if(StringUtil.isEmpty(search.getStockCarIds())) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择出库车辆");
			return result;
		}else {
			String[] carsIds = search.getStockCarIds().split(GeneralConstant.SystemBoolean.SPLIT);
			if(carsIds == null || carsIds.length <= 0) {
				result.setError(ResultCode.CODE_STATE_4005, "请选择出库车辆");
				return result;
			}
			if(!order.getStockOrderNumber().equals(carsIds.length)) {
				result.setError(ResultCode.CODE_STATE_4005, "需要出库的车辆数量为："+order.getStockOrderNumber()+"辆，请重新选择");
				return result;
			}
			for(String string : carsIds) {
				set.add(Integer.parseInt(string));
			}
			order.setStockCarIds(search.getStockCarIds());
		}
		
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(order.getCarsId())) {
			params.put("carsId", order.getCarsId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+order.getStockOrderCode()+"的车型数据错误，无法进行此操作");
			return result;
		}
		if(StringUtil.isNotEmpty(order.getColourId())) {
			params.put("colourId", order.getColourId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+order.getStockOrderCode()+"的车身颜色数据错误，无法进行此操作");
			return result;
		}
		if(StringUtil.isNotEmpty(order.getInteriorId())) {
			params.put("interiorId", order.getInteriorId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+order.getStockOrderCode()+"的车辆内饰数据错误，无法进行此操作");
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
				changeCar.add(carVo);
			}
		}
		//记录随车资料和物流方式
		if(StringUtil.isNotEmpty(search.getLogisticsMode())) {
			if(GeneralConstant.StockOrdersState.logistics_1.equals(search.getLogisticsMode())) {
				order.setLogisticsMode(GeneralConstant.StockOrdersState.logistics_1);
			}else if(GeneralConstant.StockOrdersState.logistics_2.equals(search.getLogisticsMode())) {
				order.setLogisticsMode(GeneralConstant.StockOrdersState.logistics_2);
				if(StringUtil.isNotEmpty(search.getLogisticsOddNumber())) {
					order.setLogisticsOddNumber(search.getLogisticsOddNumber());
				}else {
					result.setError(ResultCode.CODE_STATE_4005, "请填写物流单号");
					return result;
				}
			}else {
				result.setError(ResultCode.CODE_STATE_4005, "选择的票据物流方式错误");
				return result;
			}
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "请选择票据物流方式");
			return result;
		}
		if(StringUtil.isNotEmpty(search.getFollowInformation())) {
			order.setFollowInformation(search.getFollowInformation());
		}
		order.setStockOrderState(GeneralConstant.StockOrdersState.STATE_LEAVEON);
		if(stockOrderDao.updateById(order)) {
			for(StockCarVo stockCarVo : changeCar) {
				stockCarDao.updateById(stockCarVo);
			}
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("数据保存失败，请联系管理员");
		}
		return result;
//		return stockOrderDao.updateByIdAndResultIT(order, result);
	}

	@Override
	public Map<String, Object> getMapNumber(SystemUsers users) throws Exception {
		//待处理（已付定金，未通知有车）
		Map<String,Object> params = new HashMap<String, Object>();
		Map<String,Object> map = new HashMap<String, Object>();
		params.put("orgId", users.getOrgId());
//		params.put("stockOrderState", GeneralConstant.StockOrdersState.STATE_INPAY);//2018 01 05 首页等待交付定金数据已被胡阳绿放弃
//		List<StockOrder> orders = stockOrderDao.select(params);
//		if(orders == null || orders.size() <= 0) {
//			map.put("pending", 0);
//		}else {
//			map.put("pending", orders.size());
//		}
		map.put("pending", 0);
		params.put("stockOrderState", GeneralConstant.StockOrdersState.STATE_NOTICE);
		List<StockOrder> orders_1 = stockOrderDao.select(params);
		if(orders_1 == null || orders_1.size() <= 0) {
			map.put("waitFor", 0);
		}else {
			map.put("waitFor", orders_1.size());
		}
		params.put("stockOrderState", GeneralConstant.StockOrdersState.STATE_LEAVEON);
		List<StockOrder> orders_2 = stockOrderDao.select(params);
		if(orders_2 == null || orders_2.size() <= 0) {
			map.put("waitingForStorage", 0);
		}else {
			map.put("waitingForStorage", orders_2.size());
		}
		return map;
	}

	@Override
	public Result stockOrderNoticeBefor(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(search.getStockOrderId())) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体的订单记录查看");
			return result;
		}
		if(StringUtil.isEmpty(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，无法执行此操作，请联系管理员");
			return result;
		}
		StockOrderVo orderVo = stockOrderDao.selectById(search.getStockOrderId());
		if(orderVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的记录不存在或者已删除");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(orderVo.getCarsId())) {
			params.put("carsId", orderVo.getCarsId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+orderVo.getStockOrderCode()+"的车型数据错误，无法进行此操作");
			return result;
		}
		if(StringUtil.isNotEmpty(orderVo.getColourId())) {
			params.put("colourId", orderVo.getColourId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+orderVo.getStockOrderCode()+"的车身颜色数据错误，无法进行此操作");
			return result;
		}
		if(StringUtil.isNotEmpty(orderVo.getInteriorId())) {
			params.put("interiorId", orderVo.getInteriorId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+orderVo.getStockOrderCode()+"的车辆内饰数据错误，无法进行此操作");
			return result;
		}
		params.put("stockOrgId", users.getOrgId());
		List<StockCarVo> cars = stockCarDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		allMap.put("carsId", orderVo.getCarsId());
		allMap.put("carsName", orderVo.getCarsInfo());
		allMap.put("interiorName", orderVo.getInteriorName());
		allMap.put("interiorId", orderVo.getInteriorId());
		allMap.put("colourName", orderVo.getColourName());
		allMap.put("colourId", orderVo.getColourId());
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(StockCarVo stockCarVo : cars) {
			if(StringUtil.isEmpty(stockCarVo.getStockCarId())) {
				continue;
			}
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("frameNumber", stockCarVo.getFrameNumber());
			map.put("carsId", stockCarVo.getCarsId());
			map.put("carsName", stockCarVo.getCarsInfo());
			map.put("engineNumber", stockCarVo.getEngineNumber());
			map.put("certificateNumber", stockCarVo.getCertificateNumber());
			map.put("warehouseId", stockCarVo.getWarehouseId());
			map.put("warehouseName", stockCarVo.getWarehouseName());
			map.put("stockCarImages", stockCarVo.getStockCarImages());
			map.put("stockCarId", stockCarVo.getStockCarId());
			map.put("interiorName", stockCarVo.getInteriorName());
			map.put("interiorId", stockCarVo.getInteriorId());
			map.put("colourName", stockCarVo.getColourName());
			map.put("colourId", stockCarVo.getColourId());
			map.put("createDate", DateUtil.format(stockCarVo.getCreateDate()));
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("list", maps);
		allMap.put("stockOrderNumber", orderVo.getStockOrderNumber());
		allMap.put("balancePrice", orderVo.getBalancePrice());
		allMap.put("remark", orderVo.getStockOrderRemarks());
		result.setOK(ResultCode.CODE_STATE_200, "", allMap);
		return result;
	}

	@Override
	public Result stockOrderStorageOutBefor(StockOrderSearch search, Result result, SystemUsers users)
			throws Exception {
		if(StringUtil.isEmpty(users.getOrgId())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，无法执行此操作，请联系管理员");
			return result;
		}
		if(StringUtil.isEmpty(search.getStockOrderId())) {
			result.setError(ResultCode.CODE_STATE_4005, "请选择具体的订单记录查看");
			return result;
		}
		StockOrderVo orderVo = stockOrderDao.selectById(search.getStockOrderId());
		if(orderVo == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你选择的记录不存在或者已删除");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(orderVo.getCarsId())) {
			params.put("carsId", orderVo.getCarsId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+orderVo.getStockOrderCode()+"的车型数据错误，无法进行此操作");
			return result;
		}
		if(StringUtil.isNotEmpty(orderVo.getColourId())) {
			params.put("colourId", orderVo.getColourId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+orderVo.getStockOrderCode()+"的车身颜色数据错误，无法进行此操作");
			return result;
		}
		if(StringUtil.isNotEmpty(orderVo.getInteriorId())) {
			params.put("interiorId", orderVo.getInteriorId());
		}else {
			result.setError(ResultCode.CODE_STATE_4005, "订单："+orderVo.getStockOrderCode()+"的车辆内饰数据错误，无法进行此操作");
			return result;
		}
		params.put("stockOrgId", users.getOrgId());
		List<StockCarVo> cars = stockCarDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		allMap.put("carsId", orderVo.getCarsId());
		allMap.put("carsName", orderVo.getCarsInfo());
		allMap.put("interiorName", orderVo.getInteriorName());
		allMap.put("interiorId", orderVo.getInteriorId());
		allMap.put("colourName", orderVo.getColourName());
		allMap.put("colourId", orderVo.getColourId());
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(StockCarVo stockCarVo : cars) {
			if(StringUtil.isEmpty(stockCarVo.getStockCarId())) {
				continue;
			}
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("frameNumber", stockCarVo.getFrameNumber());
			map.put("carsId", stockCarVo.getCarsId());
			map.put("carsName", stockCarVo.getCarsInfo());
			map.put("engineNumber", stockCarVo.getEngineNumber());
			map.put("certificateNumber", stockCarVo.getCertificateNumber());
			map.put("warehouseId", stockCarVo.getWarehouseId());
			map.put("warehouseName", stockCarVo.getWarehouseName());
			map.put("stockCarImages", stockCarVo.getStockCarImages());
			map.put("stockCarId", stockCarVo.getStockCarId());
			map.put("interiorName", stockCarVo.getInteriorName());
			map.put("interiorId", stockCarVo.getInteriorId());
			map.put("colourName", stockCarVo.getColourName());
			map.put("colourId", stockCarVo.getColourId());
			map.put("createDate", DateUtil.format(stockCarVo.getCreateDate()));
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("list", maps);
		allMap.put("stockOrderNumber", orderVo.getStockOrderNumber());
		allMap.put("balancePrice", orderVo.getBalancePrice());
		allMap.put("remark", orderVo.getStockOrderRemarks());
		result.setOK(ResultCode.CODE_STATE_200, "", allMap);
		return result;
	}

	@Override
	public Result carDepositPrice(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(users.getOrgId() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不完整，请联系管理员补充完整");
			return result;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("depositPrice", 3000.0f);
		Organization organization = organizationDao.selectById(users.getOrgId());
		if(organization == null) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不完整，请联系管理员补充完整");
			return result;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getCarsId())) {
			params.put("carsId", search.getCarsId());
		}else {
			result.setOK(ResultCode.CODE_STATE_200, "",map);
			return result;
		}
		if(StringUtil.isNotEmpty(search.getColourId())) {
			params.put("colourId", search.getColourId());
		}else {
			result.setOK(ResultCode.CODE_STATE_200, "",map);
			return result;
		}
		if(StringUtil.isNotEmpty(search.getInteriorId())) {
			params.put("interiorId", search.getInteriorId());
		}else {
			result.setOK(ResultCode.CODE_STATE_200, "",map);
			return result;
		}
		if(organization.getParentId() == null || organization.getParentId().equals(0)) {
			result.setOK(ResultCode.CODE_STATE_200, "",map);
			return result;
		}else {
			params.put("orgId", organization.getParentId());
		}
		List<OrgCarsConfigure> orgCarsConfigures = orgCarsConfigureDao.select(params);
		if(orgCarsConfigures == null || orgCarsConfigures.size() <= 0) {
			result.setOK(ResultCode.CODE_STATE_200, "",map);
			return result;
		}else {
			map.put("depositPrice", orgCarsConfigures.get(0).getDepositPrice());
			result.setOK(ResultCode.CODE_STATE_200, "",map);
			return result;
		}
	}

	@Override
	public Result customerOrderList(StockOrderSearch search, Result result, SystemUsers users) throws Exception {		
		if(StringUtil.isEmpty(users.getOrgCode())) {
			result.setError(ResultCode.CODE_STATE_4005, "你的公司信息不明确，不能进行此操作，请先联系管理员");
			return result;
		}
		Map<String,Object> params = getCustomerParams(search,users);
		List<CustomerOrderVo> customerOrderVos = customerOrderDao.select(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = customerOrderDao.getRowCount(params);
		int rows = search.getRows();
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
	
	public Map<String,Object> getCustomerParams(StockOrderSearch search, SystemUsers users){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getCustomerOrderCode())) {
			params.put("customerOrderCode", search.getCustomerOrderCode());
		}
		if(StringUtil.isNotEmpty(search.getCustomerOrderState())) {
			params.put("customerOrderState", search.getCustomerOrderState());
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
	@Transactional
	public Result customerOrderStorageOut(StockOrderSearch search, Result result, SystemUsers users) throws Exception {
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
			orderTrack.setCustomerOrderState(GeneralConstant.CustomerOrderState.retrofitting);
			orderTrack.setTrackContent("车辆已出库");
			customerOrderTrackDao.insert(orderTrack);
			result.setOK(ResultCode.CODE_STATE_200, "操作成功");
		}else {
			result.setError("数据保存失败，请联系管理员");
			return result;
		}
		//入库到人车表
		CustomerCarVo customerCarVo = new CustomerCarVo();
		customerCarVo.setCustomerUserId(customerOrderVo.getCustomerId());
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
	
	@Override
	public Result customerOrderStorageOutBefor(StockOrderSearch search, Result result, SystemUsers users)
			throws Exception {
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
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(StockCarVo stockCarVo : cars) {
			if(StringUtil.isEmpty(stockCarVo.getStockCarId())) {
				continue;
			}
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("frameNumber", stockCarVo.getFrameNumber());
			map.put("carsId", stockCarVo.getCarsId());
			map.put("carsName", stockCarVo.getCarsInfo());
			map.put("engineNumber", stockCarVo.getEngineNumber());
			map.put("certificateNumber", stockCarVo.getCertificateNumber());
			map.put("warehouseId", stockCarVo.getWarehouseId());
			map.put("warehouseName", stockCarVo.getWarehouseName());
			map.put("stockCarImages", stockCarVo.getStockCarImages());
			map.put("stockCarId", stockCarVo.getStockCarId());
			map.put("interiorName", stockCarVo.getInteriorName());
			map.put("interiorId", stockCarVo.getInteriorId());
			map.put("colourName", stockCarVo.getColourName());
			map.put("colourId", stockCarVo.getColourId());
			map.put("createDate", DateUtil.format(stockCarVo.getCreateDate()));
			maps.add(TakeCareMapDate.cutNullMap(map));
		}
		allMap.put("list", maps);
		allMap.put("orderNumber", 1);
		allMap.put("amount", orderVo.getAmount());
		allMap.put("remark", orderVo.getRemarks());
		result.setOK(ResultCode.CODE_STATE_200, "", allMap);
		return result;
	}
}
