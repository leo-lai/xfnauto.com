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
import main.com.allInPay.dao.dao.AllinpayWechatBillDao;
import main.com.allInPay.dao.po.AllInPayWeb;
import main.com.allInPay.dao.po.AllinpayWechatBill;
import main.com.allInPay.dao.search.AllInPaySearch;
import main.com.allInPay.dao.vo.AllInPayWebVo;
import main.com.allInPay.service.AllInPayWeChatAppService;
import main.com.allInPay.service.AllInPayWebService;
import main.com.frame.dao.BaseDao;
import main.com.frame.exception.ServiceException;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.logistics.dao.dao.LogisticsConsignmentDao;
import main.com.logistics.dao.dao.LogisticsConsignmentInPayDao;
import main.com.logistics.dao.vo.LogisticsConsignmentInPayVo;
import main.com.logistics.dao.vo.LogisticsConsignmentVo;
import main.com.stock.dao.dao.StockOrderDao;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.po.StockOrder;
import main.com.stock.dao.vo.StockOrderVo;
import main.com.utils.GeneralConstant;
import main.com.utils.GeneralConstant.OrderCodePRE;

@Service
public class AllInPayWeChatAppServiceImpl extends BaseServiceImpl<AllinpayWechatBill> implements AllInPayWeChatAppService{

	@Autowired
	private AllinpayWechatBillDao allinpayWechatBillDao;
	
	@Autowired
	private LogisticsConsignmentDao consignmentDao;
	
	@Autowired
	private LogisticsConsignmentInPayDao consignmentInPayDao;
	
	@Override
	protected BaseDao<AllinpayWechatBill> getBaseDao() {
		return allinpayWechatBillDao;
	}
	
	@Override
	@Transactional
	public String resultPay(TreeMap<String, String> params) throws Exception {
		//把返回值转为对象
		String result = "error";
		Boolean issuccess = false;
		AllinpayWechatBill allInPay = new AllinpayWechatBill();
		setValue(params,allInPay);
		//查询支付的订单，拼接订单信息
		if(allInPay.getReqsn().indexOf(GeneralConstant.OrderCodePRE.ORDER_TY)>=0){//确认是托运单支付
			result = checkPay(issuccess,allInPay,result);
			if("success".equals(result)){
				return result;//重复回调，不做其他处理
			}
		}
		return result;
	}
	
	@Transactional
	public String checkPay(Boolean issuccess,AllinpayWechatBill allInPay,String result)throws Exception{
		//检测是否是重复回调
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("isPage", false);
		params.put("orderCode",allInPay.getReqsn());
		List<AllinpayWechatBill> list = allinpayWechatBillDao.select(params);
		if(list != null && list.size() > 0){//重复回调不作处理
			result = "success";
			return result;
		}
		//根据单号查询订单
		List<LogisticsConsignmentVo> stockOrders = consignmentDao.select(params);
		LogisticsConsignmentVo consignmentVo = null;
		LogisticsConsignmentInPayVo inPayVo = null;
		if(stockOrders != null && stockOrders.size() > 0) {
			consignmentVo = stockOrders.get(0);
			if(consignmentVo.getConsignmentInPayState() == null || GeneralConstant.OrderInPayState.un_paid.equals(consignmentVo.getConsignmentInPayState())) {//付定金
				consignmentVo.setConsignmentInPayState(GeneralConstant.OrderInPayState.in_pay);				
				inPayVo = new LogisticsConsignmentInPayVo();
				inPayVo.setAmount(new BigDecimal(allInPay.getAmount()));
				inPayVo.setConsignmentCode(consignmentVo.getConsignmentCode());
				inPayVo.setConsignmentId(consignmentVo.getConsignmentId());
				inPayVo.setConsignmentInPayCode(consignmentInPayDao.getCode());
				inPayVo.setConsignmentInPayState(GeneralConstant.OrderInPayState.in_pay);
				inPayVo.setConsignmentState(consignmentVo.getConsignmentState());
				inPayVo.setCreateDate(new Date());
				inPayVo.setPayDate(new Date());
				inPayVo.setPayMethod(GeneralConstant.PayModeType.PAY_ALLINPAY_WEIXIN_JS);				
			}else if(GeneralConstant.OrderInPayState.in_pay.equals(consignmentVo.getConsignmentInPayState())){
				result = "success";
				return result;
			}else if(GeneralConstant.OrderInPayState.refund.equals(consignmentVo.getConsignmentInPayState())) {
				result = "success";
				return result;
			}else {
				System.out.println("通联网关回调通知无查询到匹配的订单号："+allInPay.getReqsn());
			}
			allInPay.setConsignmentId(consignmentVo.getConsignmentId());
		}else{
			throw new ServiceException("通联回调出错");
		}
		if(consignmentDao.updateById(consignmentVo)){
			issuccess = allinpayWechatBillDao.insert(allInPay);
			inPayVo.setWeixinPayAccountId(allInPay.getAllinpayWechatBillId());
			consignmentInPayDao.insert(inPayVo);
			if(issuccess){
				result = "success";
			}else{
				System.out.println("回调信息："+params);
				throw new RuntimeException("保存通联回调信息出错");
			}
			return result;
		}else{
			throw new RuntimeException("保存通联回调信息出错");
		}
	}
	
	@Override
	public void setValue(TreeMap<String, String> map,AllinpayWechatBill allInPay)  
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
	

	public void setMethod(Object method, Object value ,AllinpayWechatBill allInPay)  
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
	
	
	void forsign(StringBuffer buffer,String name,String value){
		if(value == null || "".equals(value)){
			return;
		}
		buffer.append(name).append("=").append(value).append("&");
	}
}
