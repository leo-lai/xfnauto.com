package main.com.allInPay.dao.po;

import java.io.Serializable;

/**
 * 退款流水
 * @author Zwen
 *
 */
public class ReturnPay implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//系统部分
	private Integer id; //主键ＩＤ
	private Integer orderId; //订单ID
	private Integer returnId; //退货单ID
	private String orderCode; //订单编号
	private String returnCode; //退货单编号
	private Float returnAmount; //退货退款金额
	private Boolean returnStatus; //是否已成功退款
	
	//通联部分
	private String merchantId;//	商户号	30	不可空	数字串，与提交订单时的商户号保持一致
	private String version;//	网关联机退款接口版本	10	不可空	固定值：v2.3
	private String signType;//	签名类型	2	不可空	固定选择值：0、1；与客户提交请求填写的值保持一致
	private String orderNo;//	商户订单号	50	不可空	字母、数字、-、_ 及其组合，与提交订单时的商户订单号保持一致
	private String orderAmount;//	商户订单金额	10	不可空	整型数字，金额与币种有关如果是人民币，则单位是分，即10元提交时金额应为1000如果是美元，单位是美分，即10美元提交时金额为1000
	private String orderDatetime;//	商户订单提交时间	14	不可空	数字串，与提交订单时的商户订单提交时间保持一致
	private String refundAmount;//	退款金额	10	不可空	整型数字，金额与币种有关如果是人民币，则单位是分，即10元提交时金额应为1000如果是美元，单位是美分，即10美元提交时金额为1000
	private String refundDatetime;//	退款受理时间	14	不可空	数字串、退款申请受理的时间日期格式：yyyyMMDDhhmmss 如20121116143030
	private String refundResult;//	退款结果	10	不可空	申请成功：20  其他为失败
	private String mchtRefundOrderNo;//	商户退款订单号	50	可空	字母、数字、-、_ 及其组合，如请求有填写，退款结果原样返回
	private String returnDatetime;//	结果返回时间	14	不可空	数字串、退款申请完成的时间日期格式：yyyyMMDDhhmmss 如20121116143030
	private String signMsg;//	签名字符串	1024	不可空	以上所有非空参数按上述顺序与密钥组合，经加密后生成该值。
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getReturnId() {
		return returnId;
	}
	public void setReturnId(Integer returnId) {
		this.returnId = returnId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public Float getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(Float returnAmount) {
		this.returnAmount = returnAmount;
	}
	public Boolean getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(Boolean returnStatus) {
		this.returnStatus = returnStatus;
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
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderDatetime() {
		return orderDatetime;
	}
	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getRefundDatetime() {
		return refundDatetime;
	}
	public void setRefundDatetime(String refundDatetime) {
		this.refundDatetime = refundDatetime;
	}
	public String getRefundResult() {
		return refundResult;
	}
	public void setRefundResult(String refundResult) {
		this.refundResult = refundResult;
	}
	public String getMchtRefundOrderNo() {
		return mchtRefundOrderNo;
	}
	public void setMchtRefundOrderNo(String mchtRefundOrderNo) {
		this.mchtRefundOrderNo = mchtRefundOrderNo;
	}
	public String getReturnDatetime() {
		return returnDatetime;
	}
	public void setReturnDatetime(String returnDatetime) {
		this.returnDatetime = returnDatetime;
	}
	public String getSignMsg() {
		return signMsg;
	}
	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
