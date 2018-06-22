package main.com.allInPay.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.allInPay.dao.dao.OrderAipBillDao;
import main.com.allInPay.dao.po.OrderAipBill;
import main.com.allInPay.dao.po.QueryRsp;
import main.com.allInPay.dao.vo.AllInPayWebVo;
import main.com.allInPay.service.AllInPayPostService;
import main.com.allInPay.utils.AppConstants;
import main.com.allInPay.utils.FuncUtil;
import main.com.constant.LogisticsDistributionState;
import main.com.constant.LogisticsGoodsCarState;
import main.com.constant.LogisticsconsignmentState;
import main.com.customer.dao.dao.CustomerCustomerOrgDao;
import main.com.customer.dao.dao.CustomerOrderDao;
import main.com.customer.dao.dao.CustomerOrderInPayDao;
import main.com.customer.dao.dao.CustomerOrderTrackDao;
import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.po.CustomerOrderInPay;
import main.com.customer.dao.po.CustomerOrderTrack;
import main.com.customer.dao.vo.CustomerCustomerOrgVo;
import main.com.customer.dao.vo.CustomerOrderInPayVo;
import main.com.frame.exception.ServiceException;
import main.com.logistics.constant.DrivateState;
import main.com.logistics.dao.dao.LogisticsConsignmentDao;
import main.com.logistics.dao.dao.LogisticsConsignmentInPayDao;
import main.com.logistics.dao.dao.LogisticsDistributionDao;
import main.com.logistics.dao.dao.LogisticsDriverDao;
import main.com.logistics.dao.dao.LogisticsGoodsCarDao;
import main.com.logistics.dao.po.LogisticsConsignmentInPay;
import main.com.logistics.dao.po.LogisticsGoodsCar;
import main.com.logistics.dao.vo.LogisticsConsignmentVo;
import main.com.logistics.dao.vo.LogisticsDistributionVo;
import main.com.logistics.dao.vo.LogisticsDriverVo;
import main.com.logistics.dao.vo.LogisticsGoodsCarVo;
import main.com.stock.dao.dao.StockOrderDao;
import main.com.stock.dao.po.StockOrder;
import main.com.utils.GeneralConstant.PayModeType;
import main.com.weixinHtml.dao.dao.ShopAdvanceOrderDao;
import main.com.weixinHtml.dao.dao.ShopAdvanceOrderPaymentDao;
import main.com.weixinHtml.dao.po.ShopAdvanceOrderPayment;
import main.com.weixinHtml.dao.vo.ShopAdvanceOrderPaymentVo;
import main.com.weixinHtml.dao.vo.ShopAdvanceOrderVo;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;

@Service
public class AllInPayPostServiceImpl implements AllInPayPostService {

	@Autowired
	StockOrderDao stockOrderDao;
	
	@Autowired
	OrderAipBillDao orderAipBillDao;
	
	@Autowired
	CustomerOrderInPayDao customerOrderInPayDao;
	
	@Autowired
	CustomerOrderDao customerOrderDao;
	
	@Autowired
	CustomerOrderTrackDao customerOrderTrackDao;
	
	@Autowired
	CustomerCustomerOrgDao customerCustomerOrgDao;
	
	@Autowired
	LogisticsConsignmentInPayDao inPayDao;
	
	@Autowired
	LogisticsGoodsCarDao logisticsGoodsCarDao;
	
	@Autowired
	LogisticsConsignmentDao logisticsConsignmentDao;
	
	@Autowired
	LogisticsDistributionDao logisticsDistributionDao;
	
	@Autowired
	LogisticsDriverDao logisticsDriverDao;
	
	@Autowired
	ShopAdvanceOrderDao shopAdvanceOrderDao;
	
	@Autowired
	ShopAdvanceOrderPaymentDao advanceOrderPaymentDao;
	
	/**
	 * 订单查询
	 * appid      通联分配的appid
	 * cusid      商户号
	 * trxcode    接口编号
	 * timestamp  返回时间戳
	 * randomstr  随机字符串
	 * sign       sign校验码
	 * bizseq     业务流水号
	 * retcode    响应码
	 * retmsg     响应消息
	 * amount     金额
	 * trxreserve 业务关联内容
	 * 通联目前处理系统的相关业务：1：会员充值 2：订单支付 3：优惠券套餐支付
	 */
	@Override
	public QueryRsp queryOrder(Map params) {
		QueryRsp rsp = new QueryRsp();                                    //返回数据
		String bizseq = FuncUtil.getMapValue(params, "bizseq");           //业务流水号
		String trxreserve = FuncUtil.getMapValue(params, "trxreserve");   //业务关联内容
		String sign = FuncUtil.getMapValue(params, "sign");               //校验码
		DecimalFormat    df   = new DecimalFormat("######0"); 
		rsp.setSign(sign);
		rsp.initHead();
		rsp.setTrxcode(AppConstants.TRXCODE_QUERYORDER);                  //接口编号
		rsp.setBizseq(bizseq);
		Map<String, Object> map = new HashMap<String,Object>();
		System.out.println("通联请求查询的清单号："+bizseq);
		if(!StringUtil.isEmpty(bizseq)){
			if(bizseq.contains(GeneralConstant.OrderCodePRE.ORDER_DC)) {//订车单
				//检测是否是重复回调
				map.put("isPage", false);
				map.put("orderCode",bizseq);
				//根据单号查询订单
				StockOrder order = stockOrderDao.selectByCode(bizseq);
				if(order != null) {
					if(order.getStatementCodeBefor().trim().equals(bizseq.trim())) {//付定金
						if(order.getPayDateBefor()!=null && order.getPayMethodBefor() != null) {
							rsp.setRetcode("9999");
							rsp.setRetmsg("该订单的定金已支付");
						}else {
							System.out.println("订单金额："+order.getDepositPrice().floatValue());
							rsp.setAmount(Long.parseLong(df.format(order.getDepositPrice().floatValue()*100f)));
							//业务类型(05)#收费类型#订购人姓名#订购人地址#联系电话#跟踪订单号#证件类型#证件号#备注#保留字段#保留字段#保留字段#保留字段
							rsp.setTrxreserve("05##"+order.getStockOrderBuyOrgName()+"###"+order.getStatementCodeBefor()+":"+order.getDepositPrice().floatValue()+"#######");//商户个性化的信息
							rsp.setRetcode("0000");
							rsp.setRetmsg("处理成功");		
						}			
					}else if(order.getStatementCodeAfter().trim().equals(bizseq.trim())) {//付尾款
						if(order.getPayDateAfter()!=null && order.getPayMethodAfter() != null) {
							rsp.setRetcode("9999");
							rsp.setRetmsg("该订单的尾款已支付");
						}else {
							System.out.println("订单尾款："+order.getBalancePrice().floatValue());
							rsp.setAmount(Long.parseLong(df.format(order.getBalancePrice().floatValue()*100f)));
							//业务类型(05)#收费类型#订购人姓名#订购人地址#联系电话#跟踪订单号#证件类型#证件号#备注#保留字段#保留字段#保留字段#保留字段
							rsp.setTrxreserve("05##"+order.getStockOrderBuyOrgName()+"###"+order.getStatementCodeAfter()+":"+order.getBalancePrice().floatValue()+"#######");//商户个性化的信息
							rsp.setRetcode("0000");
							rsp.setRetmsg("处理成功");
						}
					}else {
//						if(order.getStockOrderState() <= GeneralConstant.StockOrdersState.STATE_CANCEL) {
//							rsp.setRetcode("9999");
//							rsp.setRetmsg("该订单已取消");
//						}else if(order.getStockOrderState() > GeneralConstant.StockOrdersState.STATE_STATER && order.getStockOrderState() < GeneralConstant.StockOrdersState.STATE_NOTICE) {
//							rsp.setRetcode("9999");
//							rsp.setRetmsg("该订单的定金已支付");
//						}else if(GeneralConstant.StockOrdersState.STATE_NOTICE < order.getStockOrderState()) {
//							rsp.setRetcode("9999");
//							rsp.setRetmsg("该订单的尾款已支付");
//						}
//						System.out.println("订单状态错误:"+order.getStockOrderState());
						rsp.setRetcode("9999");
						rsp.setRetmsg("非法订单号");
					}
//					rsp.setRetcode("0000");
//					rsp.setRetmsg("处理成功");
				}else{
//					System.out.println("订单查询不到");
					rsp.setRetcode("9999");
					rsp.setRetmsg("非法订单号");
				}
			}else if(bizseq.contains(GeneralConstant.OrderCodePRE.ORDER_PDD)) { //订单支付单
				System.out.println("支付单号确认");
				//检测是否是重复回调
				map.put("isPage", false);
				map.put("orderCode",bizseq);
				//根据单号查询订单
				CustomerOrderInPay customerOrderInPay = customerOrderInPayDao.selectByCode(bizseq);
				if(customerOrderInPay != null) {
					if(GeneralConstant.OrderInPayState.un_paid.equals(customerOrderInPay.getOrderInPayState())) {//付定金
						CustomerOrder order = customerOrderDao.selectById(customerOrderInPay.getCustomerOrderId());
						if(order == null) {
							System.out.println("支付单队友订单查询不到");
							rsp.setRetcode("9999");
							rsp.setRetmsg("非法订单号");
							return rsp;
						}
						rsp.setAmount(Long.parseLong(df.format(customerOrderInPay.getAmount().floatValue()*100f)));
						//业务类型(05)#收费类型#订购人姓名#订购人地址#联系电话#跟踪订单号#证件类型#证件号#备注#保留字段#保留字段#保留字段#保留字段
						rsp.setTrxreserve("05##"+order.getCustomerName()+" "+order.getCustomerPhoneNumber()+"###"+order.getCustomerOrderCode()+":"+customerOrderInPay.getAmount().floatValue()+"#######");//商户个性化的信息
						rsp.setRetcode("0000");
						rsp.setRetmsg("处理成功");						
					}else if(GeneralConstant.OrderInPayState.in_pay.equals(customerOrderInPay.getOrderInPayState())){
						rsp.setRetcode("9999");
						rsp.setRetmsg("该订单项已支付");
					}
				}else{
					System.out.println("支付单查询不到");
					rsp.setRetcode("9999");
					rsp.setRetmsg("非法订单号");
				}
			}else if(bizseq.contains(GeneralConstant.OrderCodePRE.ORDER_PT)) { //订单支付单
				System.out.println("支付单号确认");
				//检测是否是重复回调
//				map.put("isPage", false);
//				map.put("orderCode",bizseq);
				//根据单号查询订单
				LogisticsConsignmentInPay inPay = inPayDao.selectByCode(bizseq);
				if(inPay != null) {
					if(GeneralConstant.OrderInPayState.un_paid.equals(inPay.getConsignmentInPayState())) {
						//业务类型(05)#收费类型#订购人姓名#订购人地址#联系电话#跟踪订单号#证件类型#证件号#备注#保留字段#保留字段#保留字段#保留字段
						rsp.setAmount(Long.parseLong(df.format(inPay.getAmount().floatValue()*100f)));
						rsp.setTrxreserve("05##"+inPay.getPayName()+" "+""+"###"+inPay.getConsignmentInPayCode()+":"+inPay.getAmount().floatValue()+"#######");//商户个性化的信息
						rsp.setRetcode("0000");
						rsp.setRetmsg("处理成功");						
					}else if(GeneralConstant.OrderInPayState.in_pay.equals(inPay.getConsignmentInPayState())){
						rsp.setRetcode("9999");
						rsp.setRetmsg("该订单项已支付");
					}
				}else{
					System.out.println("支付单查询不到");
					rsp.setRetcode("9999");
					rsp.setRetmsg("非法订单号");
				}
			}
			else{
				System.out.println("订单号前缀错误");
				rsp.setRetcode("9999");
				rsp.setRetmsg("非法订单号");
			}
		}
		return rsp;
	}

	@Override
	@Transactional
	public String resultPay(Map params) {
		//返回结果标识
		Boolean flag = false;
		String result = "error";
		Boolean trxstatusFlag = true; //交易状态结果:默认成功
		//获取通联交易结果参数
		String bizseq = FuncUtil.getMapValue(params, "bizseq");           //业务流水号
		System.out.println("bizseq:"+bizseq);
		String trxcode = FuncUtil.getMapValue(params, "trxcode");         //交易类型
//		System.out.println("trxcode:"+trxcode);
		String trxstatus = FuncUtil.getMapValue(params, "trxstatus");     //交易状态
//		System.out.println("trxstatus:"+trxstatus);
		String aipAmount = FuncUtil.getMapValue(params, "amount");        //通联金额
//		System.out.println("amount:"+aipAmount);
		String trxId = FuncUtil.getMapValue(params, "trxid");             //交易流水号
//		System.out.println("trxid:"+trxId);
		String srctrxid = FuncUtil.getMapValue(params, "srctrxid");       //通联原交易流水,冲正撤销交易本字段不为空
		if(srctrxid == null) {
			srctrxid = "";
		}
//		System.out.println("srctrxid:"+srctrxid);
		String trxday = FuncUtil.getMapValue(params, "trxday");           //交易请求日期
//		System.out.println("trxday:"+trxday);
		String paytime = FuncUtil.getMapValue(params, "paytime");         //交易完成时间
//		System.out.println("paytime:"+paytime);
		String termid = FuncUtil.getMapValue(params, "termid");           //终端编码
//		System.out.println("termid:"+termid);
		String termbatchid = FuncUtil.getMapValue(params, "termbatchid"); //终端批次号
//		System.out.println("termbatchid:"+termbatchid);
		String traceno = FuncUtil.getMapValue(params, "traceno");         //终端流水
//		System.out.println("traceno:"+traceno);
		String trxreserve = FuncUtil.getMapValue(params, "trxreserve");   //业务关联内容
//		System.out.println("trxreserve:"+trxreserve);
		//流水信息，便于对账
//		System.out.println("记录流水");
		OrderAipBill orderAipBill = new OrderAipBill(bizseq, null, null, null, null, new Date(), trxcode, trxstatus, aipAmount, trxId, srctrxid, trxday, paytime, termid, termbatchid, traceno, trxreserve, null);
		//处理系统业务
		if(!AppConstants.RETCODE_0000.equals(trxstatus)){//交易失败
			System.out.println("交易失败");
			trxstatusFlag = false;
		}
//		System.out.println("处理订单");
		StockOrder stockOrders = null;
		if(StringUtil.isNotEmpty(bizseq) && StringUtil.isNotEmpty(trxcode)){
			Integer payMode = this.getPayMode(trxcode);//支付方式
//			System.out.println("支付方式："+payMode);
			if(StringUtil.isNotEmpty(bizseq)){
				if(bizseq.contains(GeneralConstant.OrderCodePRE.ORDER_DC)){//订单支付成功
					System.out.println("订单支付处理开始：bizseq："+bizseq);
					stockOrders = stockOrderDao.selectByCode(bizseq);
					if(stockOrders != null){
						//流水信息
						orderAipBill.setOrgId(stockOrders.getStockOrderSellOrgId());
						orderAipBill.setOrgName(stockOrders.getStockOrderSellOrgName());
						orderAipBill.setOrderId(stockOrders.getStockOrderId());
						System.out.println("stockOrders.getStatementCodeBefor()："+stockOrders.getStatementCodeBefor());
						System.out.println("stockOrders.getStatementCodeAfter()："+stockOrders.getStatementCodeAfter());
						if(stockOrders.getStatementCodeBefor().trim().equals(bizseq.trim())) {//付定金
							System.out.println("定金");
							orderAipBill.setAmount(stockOrders.getDepositPrice());
//							stockOrders.setStockOrderState(GeneralConstant.StockOrdersState.STATE_INPAY);//更改订单状态
							stockOrders.setPayDateBefor(new Date());
							stockOrders.setPayMethodBefor(payMode);
						}else if(stockOrders.getStatementCodeAfter().trim().equals(bizseq.trim())) {//付尾款
							System.out.println("尾款");
							orderAipBill.setAmount(stockOrders.getBalancePrice());
//							stockOrders.setStockOrderState(GeneralConstant.StockOrdersState.STATE_STORAGEOUT);//更改订单状态
							stockOrders.setPayDateAfter(new Date());
							stockOrders.setPayMethodAfter(payMode);
						}
//						System.out.println("未知");
//						if(GeneralConstant.StockOrdersState.STATE_STATER.equals(stockOrders.getStockOrderState())) {//付定金
//							orderAipBill.setAmount(stockOrders.getDepositPrice());
//							stockOrders.setStockOrderState(GeneralConstant.StockOrdersState.STATE_INPAY);//更改订单状态
//							stockOrders.setPayDateBefor(new Date());
//							stockOrders.setPayMethodBefor(payMode);
//						}else if(GeneralConstant.StockOrdersState.STATE_NOTICE.equals(stockOrders.getStockOrderState())) {//付尾款
//							orderAipBill.setAmount(stockOrders.getBalancePrice());
//							stockOrders.setStockOrderState(GeneralConstant.StockOrdersState.STATE_STORAGEOUT);//更改订单状态
//							stockOrders.setPayDateAfter(new Date());
//							stockOrders.setPayMethodAfter(payMode);
//						}
					}else {
						System.out.println("订车单POST支付订单回掉查询不到数据:"+bizseq);
					}
//					System.out.println("更新订单");
					flag = stockOrderDao.updateById(stockOrders);							
					if (!flag) {
						throw new ServiceException("入库单支付成功，但更新数据失败");
					}
//					System.out.println("写入流水");
					flag = this.orderAipBillDao.insert(orderAipBill);
				}else if(bizseq.contains(GeneralConstant.OrderCodePRE.ORDER_PDD)) {//支付订单的定金
					//根据单号查询订单
					CustomerOrderInPay customerOrderInPay = customerOrderInPayDao.selectByCode(bizseq);
					if(customerOrderInPay != null) {
						Double amount = customerOrderInPay.getAmount().doubleValue();
						if(GeneralConstant.OrderInPayState.un_paid.equals(customerOrderInPay.getOrderInPayState())) {//未支付
							CustomerOrder order = customerOrderDao.selectById(customerOrderInPay.getCustomerOrderId());							
							if(order.getCustomerOrderState().equals(GeneralConstant.CustomerOrderState.start)) {//支付定金
								order.setDepositPrice(customerOrderInPay.getAmount());
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
								orderTrack.setTrackContent("支付定金："+amount);
								orderTrack.setCustomerOrderId(order.getCustomerOrderId());
								orderTrack.setCustomerOrderCode(order.getCustomerOrderCode());
								if(order.getPaymentWay().equals(GeneralConstant.CustomerOrderState.fullPayment)) {//如果是全款									
									order.setCustomerOrderState(GeneralConstant.CustomerOrderState.loanAudit);
									orderTrack.setCustomerOrderState(GeneralConstant.CustomerOrderState.paymentOfADeposit);
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
									orderTrack_1.setCustomerOrderId(order.getCustomerOrderId());
									orderTrack_1.setCustomerOrderCode(order.getCustomerOrderCode());				
									orderTrack_1.setCustomerOrderState(GeneralConstant.CustomerOrderState.loanAudit);
									customerOrderTrackDao.insert(orderTrack_1);
								}else {
									order.setCustomerOrderState(GeneralConstant.CustomerOrderState.paymentOfADeposit);
									orderTrack.setCustomerOrderState(order.getCustomerOrderState());
//									orderTrack.setTrackContent(orderTrack.getTrackContent() + "贷款购车（首付："+order.getDownPayments().doubleValue()+"，贷款金额："+order.getLoan().doubleValue()
//											+ "还款期数："+order.getLoanPayBackStages()+"）");						
								}
								Map<String,Object> params_c = new HashMap<String,Object>();
//								params_c.put("orgId", order.getOrgId());//组织搜索
								params_c.put("customerUsersId", order.getCustomerId());//组织搜索
								List<CustomerCustomerOrgVo> customerOrgVos = customerCustomerOrgDao.select(params_c);
								for(CustomerCustomerOrgVo customerOrgVo : customerOrgVos) {
									if(order.getOrgId().equals(customerOrgVo.getOrgId())) {
										customerOrgVo.setIsTrack(true);
									}else {
										customerOrgVo.setIsEdit(false);
									}
									customerOrgVo.setIsShareEdit(false);
									customerCustomerOrgDao.updateById(customerOrgVo);
								}
//								CustomerCustomerOrgVo customerCustomerOrgVo = customerOrgVos.get(0);
								customerOrderTrackDao.insert(orderTrack);
							}else {
								//查询该订单的所有支付单
//								Map<String, Object> map = new HashMap<String, Object>();
//								map.put("customerOrderId", order.getCustomerOrderId());
//								List<CustomerOrderInPayVo> customerOrderInPays = customerOrderInPayDao.select(map);
//								for(CustomerOrderInPayVo inPayVo : customerOrderInPays) {
//									amount += inPayVo.getAmount().doubleValue();
//								}
								//查询
								/**
								 * 把尾款支付完成检测关闭
								 */
//								Double amount_ = 0.0d;
//								if(order.getPaymentWay().equals((GeneralConstant.CustomerOrderState.fullPayment))) {
//									amount_ = order.getAmount().doubleValue();
//								}else {
//									amount_ = order.getDownPayments().doubleValue();
//								}
//								
//								if(amount >= amount_) {//支付大于等于全款
//									//记录订单跟踪
//									CustomerOrderTrack orderTrack = new CustomerOrderTrack();
//									orderTrack.setCreateDate(new Date());
//									/**
//									 * 1:已提交定车单
//										3:已支付定金,待银行审批贷款
//										5:待车辆到店
//										7:车辆到店，可到店交付尾款
//										9:已交付尾款，待车辆加装上牌
//										11:加装／上牌完成，可到店提车
//									 */
//									orderTrack.setCustomerOrderId(order.getCustomerOrderId());
//									orderTrack.setCustomerOrderCode(order.getCustomerOrderCode());
//									orderTrack.setCustomerOrderState(GeneralConstant.CustomerOrderState.deliveryOfTheTail);
//									orderTrack.setTrackContent("尾款已支付："+(amount_ - order.getDepositPrice().doubleValue()));
//									customerOrderTrackDao.insert(orderTrack);
//									order.setCustomerOrderState(GeneralConstant.CustomerOrderState.deliveryOfTheTail);//直接调至加装牌照
//									customerOrderInPay.setCustomerOrderState(order.getCustomerOrderState());
//								}
								/**
								 * 把尾款支付完成检测关闭(尾款由人工审核)
								 */
							}
							//更新订单
							flag = customerOrderDao.updateById(order);
							if (!flag) {
								throw new ServiceException("订单"+order.getCustomerOrderCode()+"的支付单"+customerOrderInPay.getCustomerOrderCode()+"支付成功，但订单本身更新数据失败");
							}
							customerOrderInPay.setOrderInPayState(GeneralConstant.OrderInPayState.in_pay);
							customerOrderInPay.setPayMethod(payMode);
							flag = this.orderAipBillDao.insert(orderAipBill);
							if (!flag) {
								throw new ServiceException("订单支付单"+customerOrderInPay.getOrderInPayCode()+"支付成功，但写入支付流水记录失败");
							}
							customerOrderInPay.setPaymentRecordBillId(orderAipBill.getId());
							customerOrderInPay.setPayDate(new Date());
							flag = customerOrderInPayDao.updateById(customerOrderInPay);
							if (!flag) {
								throw new ServiceException("订单支付单"+customerOrderInPay.getOrderInPayCode()+"支付成功，但支付单自身更新数据失败");
							}
//							System.out.println("写入流水");
//							flag = this.orderAipBillDao.insert(orderAipBill);
						}
					}else {
						System.out.println("订单查询不到数据："+bizseq);
					}
				}else if(bizseq.contains(GeneralConstant.OrderCodePRE.ORDER_PT)) {
					LogisticsConsignmentInPay inPay = inPayDao.selectByCode(bizseq);
					if(inPay != null) {
						if(GeneralConstant.OrderInPayState.un_paid.equals(inPay.getConsignmentInPayState())) {
							inPay.setPayDate(new Date());
							inPay.setPayMethod(payMode);
							inPay.setConsignmentInPayState(GeneralConstant.OrderInPayState.in_pay);
							//查询被支付的车辆
							Set<String> set = new HashSet<>(Arrays.asList((inPay.getGoodsCarIds().split(GeneralConstant.SystemBoolean.SPLIT))));
							Map<String, Object> map = new HashMap<>();
							map.put("ids", set);
							List<LogisticsGoodsCarVo> goodsCars = logisticsGoodsCarDao.selectJoin(map);
							/**
							 * 检测托运单状态
							 * 如果该托运单下所有的车都已支付，托运单才会变成已支付状态，物流单亦然
							 */
							LogisticsConsignmentVo consignmentVo = goodsCars.get(0).getConsignmentVo();
							//根据物流单查询所有的车辆
							map.clear();
							map.put("consignmentId", consignmentVo.getConsignmentId());
							List<LogisticsGoodsCarVo> goodsCars_c = logisticsGoodsCarDao.select(map);
							Boolean is = true;
							for(LogisticsGoodsCarVo carVo : goodsCars_c) {
								if(!carVo.getGoodsCarState().equals(LogisticsGoodsCarState.Paid.getCode()) && !goodsCars.contains(carVo)) {
									is = false;
								}
							}
							if(is) {
								consignmentVo.setConsignmentInPayState(LogisticsconsignmentState.Paid.getCode());
							}
							/**
							 * 检测物流单状态
							 */
//							LogisticsDistributionVo distributionVo = goodsCars.get(0).getDistributionVo();
//							LogisticsDriverVo driverVo = null;
//							if(distributionVo.getDestinationType().equals(GeneralConstant.ConsignmentState.TYPEPOINT)) {
//								driverVo = logisticsDriverDao.selectById(distributionVo.getDriverId());
//							}
							//根据物流单查询所有的车辆
//							map.clear();
//							map.put("distributionId", distributionVo.getDistributionId());
//							List<LogisticsGoodsCarVo> goodsCars_d = logisticsGoodsCarDao.select(map);
//							is = true;
//							for(LogisticsGoodsCarVo carVo : goodsCars_d) {
//								if(!carVo.getGoodsCarState().equals(GeneralConstant.GoodsCarsState.OVERPAY) && !goodsCars.contains(carVo)) {
//									is = false;
//								}
//							}
//							if(is) {
//								distributionVo.setDistributionState(LogisticsDistributionState.Done.getCode());
//								logisticsDistributionDao.updateById(distributionVo);
//								if(distributionVo.getDestinationType().equals(GeneralConstant.ConsignmentState.TYPEPOINT)) {									
//									if(driverVo != null) {//点对点付款完毕就置司机为空闲，专线在更早前就执行了此操作
//										driverVo.setStatus(DrivateState.FREE.getCode());
//										logisticsDriverDao.updateById(driverVo);
//									}								
//								}
//							}
							/**
							 * 更改车辆支付状态
							 */
							for(LogisticsGoodsCarVo carVo : goodsCars) {
								carVo.setGoodsCarState(LogisticsGoodsCarState.Paid.getCode());
								logisticsGoodsCarDao.updateById(carVo);
							}
							/**
							 * 更新两单状态
							 */
							logisticsConsignmentDao.updateById(consignmentVo);
							
							flag = this.orderAipBillDao.insert(orderAipBill);
							inPay.setWeixinPayAccountId(orderAipBill.getId());
							flag = inPayDao.updateById(inPay);
							
							if (!flag) {
//								throw new ServiceException("物流单支付成功，但更新数据失败："+bizseq);
								throw new RuntimeException("物流单支付成功，但更新数据失败："+bizseq);
							}
//							System.out.println("写入流水");
						}
				}else {
					System.out.println("订单查询不到数据："+bizseq);
					}		
				}else if(bizseq.contains(GeneralConstant.OrderCodePRE.ORDER_AY)) {
					ShopAdvanceOrderPaymentVo orderPaymentVo = advanceOrderPaymentDao.selectByCode(bizseq);
//					ShopAdvanceOrderVo advanceOrder = shopAdvanceOrderDao.selectByCode(bizseq);
					if(orderPaymentVo != null && GeneralConstant.OrderInPayState.un_paid.equals(orderPaymentVo.getOrderInPayState())) {
						ShopAdvanceOrderVo advanceOrder = shopAdvanceOrderDao.selectById(orderPaymentVo.getAdvanceOrderId());
							advanceOrder.setOverPay(true);
							orderPaymentVo.setOrderInPayState(GeneralConstant.OrderInPayState.in_pay);
							orderPaymentVo.setPayDate(new Date());
							
							flag = shopAdvanceOrderDao.updateById(advanceOrder);
							if (!flag) {
								throw new ServiceException("商城预订单"+advanceOrder.getOrderCode()+"的支付单"+orderPaymentVo.getOrderInPayCode()+"支付成功，但订单本身更新数据失败");
							}
							orderPaymentVo.setOrderInPayState(GeneralConstant.OrderInPayState.in_pay);
							orderPaymentVo.setPayMethod(payMode);
							flag = this.orderAipBillDao.insert(orderAipBill);
							if (!flag) {
								throw new ServiceException("订单支付单"+orderPaymentVo.getOrderInPayCode()+"支付成功，但写入支付流水记录失败");
							}
							orderPaymentVo.setPaymentRecordBillId(orderAipBill.getId());
							orderPaymentVo.setPayDate(new Date());
							flag = advanceOrderPaymentDao.updateById(orderPaymentVo);
							if (!flag) {
								throw new ServiceException("订单支付单"+orderPaymentVo.getOrderInPayCode()+"支付成功，但支付单自身更新数据失败");
							}
						
					}else {
						System.out.println("商城预定单查询不到数据："+bizseq);
					}
				}
			}
		}
		//保存流水，并返回结果
		if(flag || !trxstatusFlag){
//			flag = this.orderAipBillDao.insert(orderAipBill);//单独更新流水
			if(flag){
				if(trxstatusFlag){
					System.out.println("支付成功返回");
					result = "success";
				}
			}else{
				throw new ServiceException("保存通联流水失败");
			}
		}
		return result;
	}
	
	@Override
	@Transactional
	public String resultPay_noSing(Map params) {
		//返回结果标识
		/**
		 * {acct=oZnC40ZBIXEN0ZvT9wLPMbMlnva8, appid=00021606, chnltrxid=4200000124201805085531221797, cusid=55058105511QQT4, 
		 * cusorderid=AY20180508182031lVn7, outtrxid=AY20180508182031lVn7, paytime=20180508182038, sign=2992635DF1C846DAF1533FD4ACB42768, 
		 * termauthno=CFT, termrefnum=4200000124201805085531221797, termtraceno=0, trxamt=1, trxcode=VSP501, trxdate=20180508, 
		 * trxid=111816140000211845, trxreserved=定金, trxstatus=0000}
		 */
		Boolean flag = false;
		String result = "error";
		Boolean trxstatusFlag = true; //交易状态结果:默认成功
		//获取通联交易结果参数
		String bizseq = FuncUtil.getMapValue(params, "outtrxid");           //业务流水号
		System.out.println("bizseq:"+bizseq);
		String trxcode = FuncUtil.getMapValue(params, "trxcode");         //交易类型
//		System.out.println("trxcode:"+trxcode);
		String trxstatus = FuncUtil.getMapValue(params, "trxstatus");     //交易状态
//		System.out.println("trxstatus:"+trxstatus);
		String aipAmount = FuncUtil.getMapValue(params, "amount");        //通联金额
//		System.out.println("amount:"+aipAmount);
		String trxId = FuncUtil.getMapValue(params, "trxid");             //交易流水号
//		System.out.println("trxid:"+trxId);
		String srctrxid = FuncUtil.getMapValue(params, "srctrxid");       //通联原交易流水,冲正撤销交易本字段不为空
		if(srctrxid == null) {
			srctrxid = "";
		}
//		System.out.println("srctrxid:"+srctrxid);
		String trxday = FuncUtil.getMapValue(params, "trxday");           //交易请求日期
//		System.out.println("trxday:"+trxday);
		String paytime = FuncUtil.getMapValue(params, "paytime");         //交易完成时间
//		System.out.println("paytime:"+paytime);
		String termid = FuncUtil.getMapValue(params, "termid");           //终端编码
//		System.out.println("termid:"+termid);
		String termbatchid = FuncUtil.getMapValue(params, "termbatchid"); //终端批次号
//		System.out.println("termbatchid:"+termbatchid);
		String traceno = FuncUtil.getMapValue(params, "traceno");         //终端流水
//		System.out.println("traceno:"+traceno);
		String trxreserve = FuncUtil.getMapValue(params, "trxreserve");   //业务关联内容
//		System.out.println("trxreserve:"+trxreserve);
		//流水信息，便于对账
//		System.out.println("记录流水");
		OrderAipBill orderAipBill = new OrderAipBill(bizseq, null, null, null, null, new Date(), trxcode, trxstatus, aipAmount, trxId, srctrxid, trxday, paytime, termid, termbatchid, traceno, trxreserve, null);
		//处理系统业务
		if(!AppConstants.RETCODE_0000.equals(trxstatus)){//交易失败
			System.out.println("交易失败");
			trxstatusFlag = false;
		}
//		System.out.println("处理订单");
		if(StringUtil.isNotEmpty(bizseq) && StringUtil.isNotEmpty(trxcode)){
			Integer payMode = this.getPayMode(trxcode);//支付方式
//			System.out.println("支付方式："+payMode);
			if(StringUtil.isNotEmpty(bizseq)){
				if(bizseq.contains(GeneralConstant.OrderCodePRE.ORDER_AY)) {
					ShopAdvanceOrderPaymentVo orderPaymentVo = advanceOrderPaymentDao.selectByCode(bizseq);
//					ShopAdvanceOrderVo advanceOrder = shopAdvanceOrderDao.selectByCode(bizseq);
					if(orderPaymentVo != null && GeneralConstant.OrderInPayState.un_paid.equals(orderPaymentVo.getOrderInPayState())) {
						ShopAdvanceOrderVo advanceOrder = shopAdvanceOrderDao.selectById(orderPaymentVo.getAdvanceOrderId());
						advanceOrder.setOverPay(true);
						orderPaymentVo.setOrderInPayState(GeneralConstant.OrderInPayState.in_pay);
						orderPaymentVo.setPayDate(new Date());
						
						flag = shopAdvanceOrderDao.updateById(advanceOrder);
						if (!flag) {
							throw new ServiceException("商城预订单"+advanceOrder.getOrderCode()+"的支付单"+orderPaymentVo.getOrderInPayCode()+"支付成功，但订单本身更新数据失败");
						}
						orderPaymentVo.setOrderInPayState(GeneralConstant.OrderInPayState.in_pay);
						orderPaymentVo.setPayMethod(payMode);
						flag = this.orderAipBillDao.insert(orderAipBill);
						if (!flag) {
							throw new ServiceException("订单支付单"+orderPaymentVo.getOrderInPayCode()+"支付成功，但写入支付流水记录失败");
						}
						orderPaymentVo.setPaymentRecordBillId(orderAipBill.getId());
						orderPaymentVo.setPayDate(new Date());
						flag = advanceOrderPaymentDao.updateById(orderPaymentVo);
						if (!flag) {
							throw new ServiceException("订单支付单"+orderPaymentVo.getOrderInPayCode()+"支付成功，但支付单自身更新数据失败");
						}
						
					}else {
						System.out.println("商城预定单查询不到数据："+bizseq);
					}
				}
			}
		}
		//保存流水，并返回结果
		if(flag || !trxstatusFlag){
//			flag = this.orderAipBillDao.insert(orderAipBill);//单独更新流水
			if(flag){
				if(trxstatusFlag){
					System.out.println("支付成功返回");
					result = "success";
				}
			}else{
				throw new ServiceException("保存通联流水失败");
			}
		}
		return result;
	}
	
	private Integer getPayMode(String trxcode){
		Integer payMode = null;
		if(AppConstants.VSP001.equals(trxcode)){       //POS 刷卡方式
			payMode = PayModeType.PAY_POS;
		}else if(AppConstants.VSP010.equals(trxcode)){ //闪付支付方式
			payMode = PayModeType.PAY_POS;
		}else if(AppConstants.VSP501.equals(trxcode)){ //微信支付方式
			payMode = PayModeType.PAY_BY_WX;
		}else if(AppConstants.VSP511.equals(trxcode)){ //支付宝支付方式
			payMode = PayModeType.PAY_BY_ZFB;
		}else {
			payMode = PayModeType.PAY_POS; //默认POS
		}
		return payMode;
	}
	
	/**
	 * 提交订单
	 * @throws IOException
	 */
	public String submit(String extNo){
//		Map<String, Object> map = new HashMap<String, Object>();
//		// 参数
//		map.put("extNo", extNo);//接入方订单号
//
//		String param = PeccancyHandleUtils.hashMapToJson(map);
//		System.out.println(param);
//
//		Map<String, String> headers = new HashMap<String, String>();
//		String date = String.valueOf(new Date().getTime());
//		final StringBuilder builder = new StringBuilder(PeccancyHandleUtils.USER_NAME);
//		builder.append(":");
//		builder.append(HttpRequestSiningHelper.createRequestSignature("POST", date, PeccancyHandleUtils.serverUri+"/violation/submit", param, PeccancyHandleUtils.SECRET_KEY));// 生成签名头信息
//		headers.put(HmacAttributes.X_HMAC_AUTH_SIGNATURE, builder.toString());
//		headers.put(HmacAttributes.X_HMAC_AUTH_METHOD, "HmacMD5");
//		headers.put(HmacAttributes.X_HMAC_AUTH_DATE, date);
//		String back = "";
//		try {
//			back = HttpPostUtils.http(PeccancyHandleUtils.serverUri+"/violation/submit", param.getBytes(), headers);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(back);
//		return back;
		return "";
	}
	
	public static void main(String[] args) {
		String bizseq = "DC2018010916220942XF_2";
		if("DC2018010916220942XF_2".equals(bizseq)) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
	}

}
