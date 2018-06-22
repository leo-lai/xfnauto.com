package main.com.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.com.customer.dao.dao.CustomerOrderDao;
import main.com.customer.dao.dao.CustomerOrderTrackDao;
import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.po.CustomerOrderTrack;
import main.com.customer.dao.search.CustomerOrderSearch;
import main.com.customer.dao.vo.CustomerOrderVo;
import main.com.frame.dao.BaseDao;
import main.com.frame.domain.Result;
import main.com.frame.domain.ResultCode;
import main.com.frame.service.impl.BaseServiceImpl;
import main.com.system.dao.po.SystemUsers;
import main.com.system.service.BankToExamineService;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.utils.TakeCareMapDate;

@Service
public class BankToExamineServiceImpl extends BaseServiceImpl<CustomerOrder>implements BankToExamineService{

	@Autowired
	CustomerOrderDao customerOrderDao;
	
	@Autowired
	CustomerOrderTrackDao customerOrderTrackDao;
	
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
		List<CustomerOrderVo> customerOrderVos = customerOrderDao.selectJoin(params);
		Map<String,Object> allMap = new HashMap<String, Object>();
		Long total = customerOrderDao.getRowCountJoin(params);
		int rows = search.getRows();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		if(customerOrderVos == null || customerOrderVos.size() <= 0) {
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
			map.put("auditStatus", orderVo.getAuditStatus());//
			map.put("auditTime", orderVo.getAuditTime() != null?DateUtil.format(orderVo.getAuditTime()):"");//
			map.put("orgName", orderVo.getOrgName());//
			map.put("loan", orderVo.getLoan()!=null?orderVo.getLoan().doubleValue():0);//
			map.put("loanPayBackStages", orderVo.getLoanPayBackStages());//
			map.put("customerPhoneNumber", orderVo.getCustomerPhoneNumber());//
			if(orderVo.getIsMortgage() != null && orderVo.getIsMortgage()) {
				map.put("isMortgage", 1);//
			}else {
				map.put("isMortgage", 0);//
			}
			if(orderVo.getCustomerUsersVo() != null) {
				map.put("bankAuditsImage", orderVo.getCustomerUsersVo().getBankAuditsImage());//
				map.put("bankAuditsvideo", orderVo.getCustomerUsersVo().getBankAuditsvideo());//
			}else {
//				map.put("bankAuditsImage", "");//
//				map.put("bankAuditsvideo", "");//
				continue;
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

	public Map<String,Object> getCustomerParams(CustomerOrderSearch search, SystemUsers users){
		Map<String,Object> params = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(search.getCustomerOrderCode())) {
			params.put("customerOrderCode", search.getCustomerOrderCode());
		}
		if(StringUtil.isNotEmpty(search.getCustomerPhoneNumber())) {
			params.put("phoneNumber", search.getCustomerPhoneNumber());
		}
//		if(StringUtil.isNotEmpty(search.getCustomerOrderState())) {
//			params.put("customerOrderState", search.getCustomerOrderState());
//		}
		if(search.getIsPassThrough() != null) {
			if(search.getIsPassThrough()) {
				params.put("customerOrderState", GeneralConstant.CustomerOrderState.notPassThrough);
			}else {
				params.put("customerOrderState", GeneralConstant.CustomerOrderState.loanAudit);
			}
		}
		params.put("isBank", true);
		params.put("loanBank", GeneralConstant.LoanBank.NONGYE);
		//从第几条开始
		params.put("sortField", true);
		params.put("isPage", true);
		params.put("offset", (search.getPage()-1)*search.getRows());
		//返回几条
		params.put("limit", search.getRows()); 
		return params;
	}

	@Override
	public Result bankToExamineOrder(CustomerOrderSearch search, Result result, SystemUsers users) throws Exception {
		if(StringUtil.isEmpty(search.getCustomerOrderId())) {
			result.setError("请选择订单进行操作");
			return result;
		}
		CustomerOrder customerOrder = customerOrderDao.selectById(search.getCustomerOrderId());
		if(customerOrder == null) {
			result.setError("你选择的订单不存在或者已删除");
			return result;
		}
		if(search.getIsPassThrough() == null) {
			result.setError(ResultCode.CODE_STATE_4005, "请提交审核结果");
			return result;
		}
		if(!search.getIsPassThrough() && StringUtil.isEmpty(search.getRefusalReason())) {
			result.setError(ResultCode.CODE_STATE_4005, "请填写拒绝理由");
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
		if(search.getIsPassThrough()) {
			customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.loanAudit);//审核通过，等待出库
			customerOrder.setAuditStatus(GeneralConstant.CustomerOrderState.passThrough);
			customerOrder.setAuditTime(new Date());
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
		}else {
			customerOrder.setCustomerOrderState(GeneralConstant.CustomerOrderState.notPassThrough);//审核不通过
			customerOrder.setAuditStatus(GeneralConstant.CustomerOrderState.refuse);
			customerOrder.setAuditTime(new Date());
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
				orderTrack.setTrackContent("银行已拒绝。拒绝原因："+search.getRefusalReason());
				orderTrack.setCustomerOrderId(customerOrder.getCustomerOrderId());
				orderTrack.setCustomerOrderState(customerOrder.getCustomerOrderState());
				orderTrack.setCustomerOrderCode(customerOrder.getCustomerOrderCode());
				customerOrderTrackDao.insert(orderTrack);
				result.setOK(ResultCode.CODE_STATE_200, "操作成功");
			}else {
				result.setError("保存数据失败");
			}
		}
		return result;
	}
}
