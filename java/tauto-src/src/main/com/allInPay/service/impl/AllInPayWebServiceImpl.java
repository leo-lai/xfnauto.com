package main.com.allInPay.service.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.com.allInPay.dao.dao.AllInPayWebDao;
import main.com.allInPay.dao.po.AllInPayWeb;
import main.com.allInPay.dao.search.AllInPaySearch;
import main.com.allInPay.dao.vo.AllInPayWebVo;
import main.com.allInPay.service.AllInPayWebService;
import main.com.frame.dao.BaseDao;
import main.com.frame.exception.ServiceException;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.stock.dao.dao.StockOrderDao;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.po.StockOrder;
import main.com.stock.dao.vo.StockOrderVo;
import main.com.utils.GeneralConstant;
import main.com.utils.GeneralConstant.OrderCodePRE;

@Service
public class AllInPayWebServiceImpl extends BaseServiceImpl<AllInPayWeb> implements AllInPayWebService{

	@Autowired
	private AllInPayWebDao allInPayDao;
	
	@Autowired
	private StockOrderDao stockOrderDao;
	
	@Override
	protected BaseDao<AllInPayWeb> getBaseDao() {
		return allInPayDao;
	}

	@Override
	@Transactional
	public String resultPay(TreeMap<String, String> params) throws Exception {
		//把返回值转为对象
		String result = "error";
		Boolean issuccess = false;
		AllInPayWeb allInPay = new AllInPayWeb();
		setValue(params,allInPay);
		//查询支付的订单，拼接订单信息
		if(allInPay.getOrderNo().indexOf(GeneralConstant.OrderCodePRE.ORDER_DC)>=0){//确认是订车单订单支付
			result = checkPay(issuccess,allInPay,result);
			if("success".equals(result)){
				return result;//重复回调，不做其他处理
			}
		}
		issuccess = allInPayDao.insert(allInPay);
		if(issuccess){
			result = "success";
		}else{
			System.out.println("回调信息："+params);
			throw new RuntimeException("保存通联回调信息出错");
		}
		return result;
	}
	
	@Override
	@Transactional
	public String resultPay(AllInPayWeb allInPay) throws Exception {
		//把返回值转为对象
		String result = "error";
		Boolean issuccess = false;
		//查询支付的订单，拼接订单信息
		if(allInPay.getOrderNo().indexOf(OrderCodePRE.ORDER_DC)>=0 || allInPay.getOrderNo().indexOf(OrderCodePRE.ORDER_DC)>=0){//确认是订单支付
			result = checkPay(issuccess,allInPay,result);
			if("success".equals(result)){
				return result;//重复回调，不做其他处理
			}
			//支付成功
		}
		issuccess = allInPayDao.insert(allInPay);
		if(issuccess){
			result = "success";
		}else{
			throw new RuntimeException("保存通联回调信息出错");
		}
		return result;
	}
	
	@Override
	@Transactional
	public String checkPay(Boolean issuccess,AllInPayWeb allInPay,String result)throws Exception{
		//检测是否是重复回调
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("isPage", false);
		params.put("orderCode",allInPay.getOrderNo());
		List<AllInPayWebVo> list = allInPayDao.select(params);
		if(list != null && list.size() > 0){//重复回调不作处理
			result = "success";
			return result;
		}
		//根据单号查询订单
		List<StockOrder> stockOrders = stockOrderDao.select(params);
		StockOrder order = null;
		if(stockOrders != null && stockOrders.size() > 0) {
			order = stockOrders.get(0);
			if(order.getStatementCodeBefor().equals(allInPay.getOrderNo())) {//付定金
				allInPay.setAmount(order.getDepositPrice());
//				order.setStockOrderState(GeneralConstant.StockOrdersState.STATE_INPAY);//更改订单状态
				order.setPayDateBefor(new Date());
				order.setPayMethodBefor(GeneralConstant.PayWay.PAY_BY_WEB);
				allInPay.setCreateDate(new Date());
				allInPay.setOrderCode(order.getStatementCodeBefor());
			}else if(order.getStatementCodeAfter().equals(allInPay.getOrderNo())) {//付尾款
				allInPay.setAmount(order.getBalancePrice());
//				order.setStockOrderState(GeneralConstant.StockOrdersState.STATE_STORAGEOUT);//更改订单状态
				order.setPayDateAfter(new Date());
				order.setPayMethodAfter(GeneralConstant.PayWay.PAY_BY_WEB);
				allInPay.setCreateDate(new Date());
				allInPay.setOrderCode(order.getStatementCodeAfter());
			}else {
				System.out.println("通联网关回调通知无查询到匹配的订单号："+allInPay.getOrderNo());
			}
//			if(GeneralConstant.StockOrdersState.STATE_STATER.equals(order.getStockOrderState())) {//付定金
//				allInPay.setAmount(order.getDepositPrice());
//				order.setStockOrderState(GeneralConstant.StockOrdersState.STATE_INPAY);//更改订单状态
//				order.setPayDateBefor(new Date());
//				order.setPayMethodBefor(GeneralConstant.PayWay.PAY_BY_WEB);
//				allInPay.setCreateDate(new Date());
//				allInPay.setOrderCode(order.getStatementCodeBefor());
//			}else if(GeneralConstant.StockOrdersState.STATE_NOTICE.equals(order.getStockOrderState())) {//付尾款
//				allInPay.setAmount(order.getBalancePrice());
//				order.setStockOrderState(GeneralConstant.StockOrdersState.STATE_STORAGEOUT);//更改订单状态
//				order.setPayDateAfter(new Date());
//				order.setPayMethodAfter(GeneralConstant.PayWay.PAY_BY_WEB);
//				allInPay.setCreateDate(new Date());
//				allInPay.setOrderCode(order.getStatementCodeAfter());
//			}
			allInPay.setPayUserId(order.getStockOrderBuyOrgId());
			allInPay.setOrderId(order.getStockOrderId());
		}else{
			throw new ServiceException("通联回调出错");
		}
		if(stockOrderDao.updateById(order)){
			return result;
		}else{
			throw new RuntimeException("保存通联回调信息出错");
		}
//		StockOrderVo orderVo
//		BusiOrderSearch busiOrderSearch = new BusiOrderSearch();
//		busiOrderSearch.setIsPage(false);
//		busiOrderSearch.setOrderCode(allInPay.getOrderNo());
//		List<BusiOrderVo> busiOrderVos = busiOrderDao.selectJoinLess(BeanUtils.toMap(busiOrderSearch));
//		BusiOrderVo busiOrderVo = null;
//		if(busiOrderVos != null && busiOrderVos.size() > 0){
//			busiOrderVo = busiOrderVos.get(0);
//			allInPay.setAmount(busiOrderVo.getRealAmount());
//			allInPay.setCreateDate(new Date());
//			allInPay.setPayUserId(busiOrderVo.getBuyerId());
//			allInPay.setOrderCode(busiOrderVo.getOrderCode());
//			allInPay.setOrderId(busiOrderVo.getId());
//		}else{
//			throw new ServiceException("通联回调出错");
//		}
//		for(BusiOrderDetailVo detailVo : busiOrderVo.getBusiOrderDetailList()){
//			detailVo.setState(OrderStatus.DELIIVERGOODSNO);//支付完成把所有配件标记为未发货
//			busiOrderDetailService.edit(detailVo);
//		}
//		busiOrderVo.setPayMode(PayMode.GATEWAY);
//		busiOrderVo.setState(OrderStatus.PAYYES);//把订单标记为已支付
//		
//		BusiOrderRecord orderRecord = new BusiOrderRecord();
//		orderRecord = OrderRecordDescription.ordereToRcordPay(busiOrderVo);
//		//把所有返回值写入数据库
//		issuccess = busiOrderDao.updateById(busiOrderVo);
//		if(issuccess){
//			busiOrderRecordDao.insert(orderRecord);
//			return result;
//		}else{
//			throw new RuntimeException("保存通联回调信息出错");
//		}
	}
	
	@Override
	public void setValue(TreeMap<String, String> map,AllInPayWeb allInPay)  
	{
	  Set set = map.keySet();  
	  Iterator iterator = set.iterator();  
	  while (iterator.hasNext())  
	  {  
	    Object obj = iterator.next();  
	    Object val = map.get(obj);  
	    setMethod(obj, val, allInPay);  
	  }  
	}  
	
	@Override
	public void setValue(Map<String, String> map,AllInPayWeb allInPay)  
	{
	  Set set = map.keySet();  
	  Iterator iterator = set.iterator();  
	  while (iterator.hasNext())  
	  {  
	    Object obj = iterator.next();  
	    Object val = map.get(obj);  
	    setMethod(obj, val, allInPay);  
	  }  
	} 

	public void setMethod(Object method, Object value ,AllInPayWeb allInPay)  
	  {  
	    Class c = null;  
	    try  
	    {  
	      c = Class.forName(allInPay.getClass().getName());  
	      String met = (String) method;  
	      met = met.trim();  
	      if (!met.substring(0, 1).equals(met.substring(0, 1).toUpperCase()))  
	      {  
	        met = met.substring(0, 1).toUpperCase() + met.substring(1);  
	      }  
	      if (!String.valueOf(method).startsWith("set"))  
	      {  
	        met = "set" + met;  
	      }  
	      Class types[] = new Class[1];  
	      types[0] = Class.forName("java.lang.String");  
	      Method m = c.getMethod(met, types);  
	      m.invoke(allInPay, value);  
	    }  
	    catch (Exception e)  
	    {  
	      // TODO: handle exception  
	      e.printStackTrace();  
	    }  
	  } 
	
	
//	@Override
//	public Result setPayInfo(String orderCode,String pickupUrl,UserVo payUser,String productName,String quantity
//			,Float amount,Float unitAmount,Date payDate) throws Exception {
//		Result result = new Result();
//		try {
//			if(orderCode == null || "".equals(orderCode)){
//				result.setError(ResultCode.CODE_STATE_4006, "参数错误，orderCode不能为空！");
//				return result;
//			}
//			if(pickupUrl == null || "".equals(pickupUrl)){
//				result.setError(ResultCode.CODE_STATE_4006, "参数错误，pickupUrl不能为空！");
//				return result;
//			}
//			if(payUser == null || "".equals(payUser.getRealName())){
//				result.setError(ResultCode.CODE_STATE_4006, "参数错误，支付用户不能为空或支付用户真实姓名不能为空！");
//				return result;
//			}
//			AllInPaySubmit allInPaySubmit = new AllInPaySubmit();
//			/*
//			 private String inputCharset;//	字符集	2	不可空	默认填1；1代表UTF-8、2代表GBK、3代表GB2312；
//	private String pickupUrl;//	付款客户的取货url地址	100	不为空	客户的取货地址
//	private String receiveUrl;//	服务器接受支付结果的后台地址	100	不为空	通知商户网站支付结果的url地址
//	private String version;//	网关接收支付请求接口版本	10	不可空	固定填v1.0 
//	private String language;//	网关页面显示语言种类	2	不为空	默认填1，固定选择值：1；1代表简体中文、2代表繁体中文、3代表英文
//	private String signType;//	签名类型	2	不可空	默认填1，固定选择值：0、1；0表示订单上送和交易结果通知都使用MD5进行签名1表示商户用使用MD5算法验签上送订单，通联交易结果通知使用证书签名Asp商户不使用通联dll文件签名验签的商户填0
//	private String merchantId;//	商户号	30	不可空	数字串，商户在通联申请开户的商户号
//	private String payerName;//	付款人姓名	32	可为空	跨境支付商户若采用直连模式，必须填写该值
//	private String payerEmail;//	付款人邮件联系方式	50	可为空	字符串
//	private String payerTelephone;//	付款人电话联系方式	16	可为空	数字串
//	private String orderNo;//	商户订单号	50	不可空	字符串，只允许使用字母、数字、- 、_,并以字母或数字开头；每商户提交的订单号，必须在当天的该商户所有交易中唯一
//	private String orderAmount;//	商户订单金额	10	不可空	整型数字，金额与币种有关如果是人民币，则单位是分，即10元提交时金额应为1000如果是美元，单位是美分，即10美元提交时金额为1000
//	private String orderCurrency;//	订单金额币种类型	3	不可空	默认填00和156代表人民币、840代表美元、344代表港币，跨境支付商户不建议使用0
//	private String orderDatetime;//	商户订单提交时间	14	不可空	日期格式：yyyyMMDDhhmmss，例如：20121116020101
//	private String orderExpireDatetime;// 	订单过期时间	14	可为空	整形数字，单位为分钟。最大值为9999分钟。如填写则以商户上送时间为准，如不填写或填0或填非法值，则服务端默认该订单9999分钟后过期，超期后不允许商户发起同一商户订单支付
//	private String productName;//	商品名称	256	可为空	英文或中文字符串，请勿首尾有空格字符
//	private String productPrice;//	商品价格	20	可为空	整型数字
//	private String productNum;//	商品数量	8	可为空	整型数字，默认传1
//	private String productId;//	商品代码	20	可为空	字母、数字或- 、_ 的组合；用于使用产品数据中心的产品数据，或用于市场活动的优惠
//	private String productDesc;//	商品描述	400	可为空	英文或中文字符串
//	private String ext1;//	扩展字段1	128	可为空	英文或中文字符串，支付完成后，按照原样返回给商户
//	private String ext2;//	扩展字段2	128	可为空	英文或中文字符串，支付完成后，按照原样返回给商户
//	private String extTL;//	业务扩展字段	1024	可为空	参见《接口技术规范文档3.9节介绍》
//	private String payType;//	支付方式	2	不可空	固定选择值：0、1、4、11、23、28接入互联网关时，默认为间连模式，填0若需接入外卡支付，只支持直连模式，即固定上送payType=23，issuerId=visa或mastercard
//	//0代表未指定支付方式，即显示该商户开通的所有支付方式1个人储蓄卡网银支付4企业网银支付11个人信用卡网银支付23外卡支付28认证支付非直连模式，设置为0；直连模式，值为非0的固定选择值
//	private String issuerId;//	发卡方代码	8	可为空	银行或预付卡发卡机构代码，用于指定支付使用的付款方机构。接入手机网关时，该值留空
////	payType为0时，issuerId必须为空——显示该商户支持的所有支付类型和各支付类型下支持的全部发卡机构
////	payType为非0时，若issuerId为空——显示该商户所填payType支付类型下的全部发卡机构
////	payType为非0时，若issuerId不为空——直接跳转到该商户所填payType下指定的发卡机构网银页面，支持发卡机构详见附录《机构代码》
//	private String pan;//	付款人支付卡号	19	可为空	数字串
////	如果是民生银行B2B直连模式，企业客户号，必填；
////	如果是投融资行业希望支付卡号，则必填
////	上送的卡号必须使用公钥加密(PKCS1)后进行Base64编码。
//	private String tradeNature;//	贸易类型	2	可为空	固定选择值：GOODS或SERVICES
////	当币种为人民币时选填
////	当币种为非人民币时必填，GOODS表示实物类型，SERVICES表示服务类型
//	private String signMsg;//	签名字符串	1024	不可空	为防止非法篡改要求商户对请求内容进行签名，供服务端进行校验；签名串生成机制：按上述顺序所有非空参数与密钥key组合，经加密后生成signMsg；
//	private String customsExt;//	海关扩展字段	1024	可为空	详见“3.13.3海关扩展字段要求”章节
//	private String payerIDCard;//	付款人类型及证件号	22	可为空	填写规则：证件类型+证件号码再使用通联公钥加密。（加密请参考示例代码）证件类型仅支持01-身份证跨境支付商户若采用直连模式，必须填写该值
//	private String pid;//	合作伙伴的商户号	30	可为空	用于商户与第三方合作伙伴拓展支付业务，Partner merchantId
//			 */
//			StringBuffer buffer = new StringBuffer();
////			buffer.append("&");
//			//拼装支付参数
//			SimpleDateFormat formatFrom = new SimpleDateFormat("yyyyMMddhhmmss");
//			allInPaySubmit.setInputCharset(SystemConfig.getInputCharset());
//			forsign(buffer,"inputCharset",SystemConfig.getInputCharset());
//			allInPaySubmit.setPickupUrl(pickupUrl);
//			forsign(buffer,"pickupUrl",pickupUrl);
//			allInPaySubmit.setReceiveUrl(SystemConfig.getReceiveUrl());
//			forsign(buffer,"receiveUrl",SystemConfig.getReceiveUrl());
//			allInPaySubmit.setVersion(SystemConfig.getVersion());
//			forsign(buffer,"version",SystemConfig.getVersion());
//			allInPaySubmit.setLanguage(SystemConfig.getLanguage());
//			forsign(buffer,"language",SystemConfig.getLanguage());
//			allInPaySubmit.setSignType(SystemConfig.getSignType());
//			forsign(buffer,"signType",SystemConfig.getSignType());
//			allInPaySubmit.setMerchantId(SystemConfig.getMerchantId());
//			forsign(buffer,"merchantId",SystemConfig.getMerchantId());
//			
//			allInPaySubmit.setPayerName(payUser.getRealName());
//			forsign(buffer,"payerName",payUser.getRealName());
//			
//			allInPaySubmit.setOrderNo(orderCode);
//			forsign(buffer,"orderNo",orderCode);
////			BigDecimal payAmount = new BigDecimal();
//			BigDecimal payAmount = new BigDecimal(amount*10*10);
//			payAmount = payAmount.multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_UP);
//			allInPaySubmit.setOrderAmount(payAmount.intValue()+"");
//			forsign(buffer,"orderAmount",(payAmount.intValue())+"");
//			allInPaySubmit.setOrderCurrency(SystemConfig.getOrderCurrency());
//			forsign(buffer,"orderCurrency",SystemConfig.getOrderCurrency());
//			//使创建时间保持一致
//			allInPaySubmit.setOrderDatetime(formatFrom.format(payDate));
//			forsign(buffer,"orderDatetime",allInPaySubmit.getOrderDatetime());
//			allInPaySubmit.setOrderExpireDatetime(SystemConfig.getOrderExpireDatetime());
//			forsign(buffer,"orderExpireDatetime",allInPaySubmit.getOrderExpireDatetime());
//			
//			/**
//			 * 测试为get请求，暂不带中文
//			 */
//			allInPaySubmit.setProductName(productName);
//			forsign(buffer,"productName",allInPaySubmit.getProductName());
//			
//			BigDecimal unitPrice = new BigDecimal(unitAmount*10*10);
//			unitPrice = unitPrice.multiply(new BigDecimal(1)).setScale(2, BigDecimal.ROUND_HALF_UP);
//			allInPaySubmit.setProductPrice(unitPrice.intValue()+"");
//			forsign(buffer,"productPrice",allInPaySubmit.getProductPrice());
//			
//			allInPaySubmit.setProductNum(quantity);
//			forsign(buffer,"productNum",allInPaySubmit.getProductNum());
//			
//			allInPaySubmit.setPayType(SystemConfig.getPayType());
//			forsign(buffer,"payType",SystemConfig.getPayType());
//			//签名
//			TreeMap<String, String> params = BeanUtils.toTreeMap(allInPaySubmit);
//			
//			allInPaySubmit.setSignMsg(AllInPayUtil.sign(buffer, SystemConfig.getMD5Key()));
//			params.put("signMsg", allInPaySubmit.getSignMsg());
//			//记录支付操作过程
//			result.setOK(ResultCode.CODE_STATE_200, "", allInPaySubmit);
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setError(ResultCode.CODE_STATE_4006, "系统繁忙，请刷新重试！");
//		}
//		return result;
//	}
	
	void forsign(StringBuffer buffer,String name,String value){
		if(value == null || "".equals(value)){
			return;
		}
		buffer.append(name).append("=").append(value).append("&");
	}

	@Override
	public String selectMD5Key(AllInPayWeb allInPay) throws Exception {
//		AllInPayConfigureVo payConfigureVo = allInPayConfigureService.getByOrderCode(allInPay.getOrderNo());
//		return payConfigureVo.getMd5Key();
		return null;
	}
}
