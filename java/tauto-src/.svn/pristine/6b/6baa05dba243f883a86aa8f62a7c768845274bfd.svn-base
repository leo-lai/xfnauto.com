package main.com.allInPay.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 支付流水(网关支付)
 * @author Zwen
 *
 */
public class AllInPayWeb implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 系统支付信息
	 */
	private Integer id;                 //主键
	private String orderCode;           //订单号
	private Integer orderId;            //订单Id
	private BigDecimal amount;               //金额
	private Date createDate;            //创建时间
	private Integer payUserId;          //支付用户
	
	/*通联交易信息*/
	private String merchantId;//	商户号不可空	数字串，与提交订单时的商户号保持一致
	private String version;//	网关返回支付结果接口版本不可空	固定选择值：v1.0；注意为小写字母
	private String language;//	网页显示语言种类可空	固定值：1；1代表中文显示
	private String signType;//	签名类型不可空	固定选择值：0、1，与提交订单时的签名类型保持一致
	private String payType;//	支付方式可空	字符串，与提交订单时的支付方式一致
	private String orderNo;//	通联订单号不可空	字符串，通联订单号
	private String orderDatetime;//	商户订单提交时间不可空	数字串，与提交订单时的商户订单提交时间保持一致
	private String orderAmount;//	商户订单金额不可空	整型数字，金额与币种有关如果是人民币，则单位是分，即10元提交时金额应为1000 如果是美元，单位是美分，即10美元提交时金额为1000
	private String payDatetime;//	支付完成时间不可空	日期格式：yyyyMMDDhhmmss，例如：20121116020101
	private String payAmount;//	订单实际支付金额不可空	整型数字，实际支付金额，用户实际支付币种为人民币；以分为单位，例如10元返回时应为1000分
	private String payResult;//	处理结果不可空	1：支付成功仅在支付成功时通知商户。商户可以通过查询接口查询订单状态。
	private String errorCode;//	错误代码可空	固定为空
	private String returnDatetime;//	结果返回时间不可空	系统返回支付结果的时间，日期格式：yyyyMMDDhhmmss
	private String issuerId;//	发卡方机构代码可空	固定为空
	private String ext1;//	扩展字段1可空	字符串，与提交订单时的扩展字段1保持一致
	private String ext2;//	扩展字段2可空	字符串，与提交订单时的扩展字段2保持一致
	private String paymentOrderId;//支付订单号
	/*
	merchantId=100020091218001&version=v1.0&language=1&signType=0&payType=0&
	paymentOrderId=201607041451089534&orderNo=DD250720160704025011&
	orderDatetime=20160704025109&orderAmount=100557&payDatetime=20160704145120&
	payAmount=100557&payResult=1&returnDatetime=20160704144831&
	*/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getPayUserId() {
		return payUserId;
	}
	public void setPayUserId(Integer payUserId) {
		this.payUserId = payUserId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getIssuerId() {
		return issuerId;
	}
	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}

	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderDatetime() {
		return orderDatetime;
	}
	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getPayDatetime() {
		return payDatetime;
	}
	public void setPayDatetime(String payDatetime) {
		this.payDatetime = payDatetime;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getPayResult() {
		return payResult;
	}
	public void setPayResult(String payResult) {
		this.payResult = payResult;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getReturnDatetime() {
		return returnDatetime;
	}
	public void setReturnDatetime(String returnDatetime) {
		this.returnDatetime = returnDatetime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPaymentOrderId() {
		return paymentOrderId;
	}
	public void setPaymentOrderId(String paymentOrderId) {
		this.paymentOrderId = paymentOrderId;
	}
}
