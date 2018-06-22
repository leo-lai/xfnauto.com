package main.com.system.dao.search;

import main.com.frame.search.BaseSearch;

public class SystemOrderSearch extends BaseSearch{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sessionId;
	//参数：是否需要发票，下单日期开始，下单日期结束，订单号，卖家手机号
	private Boolean isPaperCheck;
	private String phoneNumber;
	private String startDate;
	private String finishDate;
	private String orderCode;
	private Integer orderId;
	private String orderIds;
	private Boolean isPass;
	private Integer ordersState;
	private Integer expressId;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Boolean getIsPaperCheck() {
		return isPaperCheck;
	}
	public void setIsPaperCheck(Boolean isPaperCheck) {
		this.isPaperCheck = isPaperCheck;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderIds() {
		return orderIds;
	}
	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}
	public Boolean getIsPass() {
		return isPass;
	}
	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}
	public Integer getOrdersState() {
		return ordersState;
	}
	public void setOrdersState(Integer ordersState) {
		this.ordersState = ordersState;
	}
	public Integer getExpressId() {
		return expressId;
	}
	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}
}
