package main.com.weixinHtml.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
public class ShopAdvanceOrderPayment implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer orderInPayId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 订单ID
     */
    private Integer advanceOrderId;

    /**
     * 订单CODE
     */
    private String advanceOrderCode;

    /**
     * 订单状态
     */
    private Integer advanceOrderState;

    /**
     * 支付方式
     */
    private Integer payMethod;

    /**
     * 支付流水
     */
    private Integer paymentRecordBillId;

    /**
     * 支付状态 0未支付 1已支付 2已退款
     */
    private Integer orderInPayState;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 支付时间
     */
    private Date payDate;

    /**
     * 支付单号
     */
    private String orderInPayCode;

    /**
     * 主键
     * @return order_in_pay_id 主键
     */
    public Integer getOrderInPayId() {
        return orderInPayId;
    }

    /**
     * 主键
     * @param orderInPayId 主键
     */
    public void setOrderInPayId(Integer orderInPayId) {
        this.orderInPayId = orderInPayId;
    }

    /**
     * 金额
     * @return amount 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 金额
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 订单ID
     * @return advance_order_id 订单ID
     */
    public Integer getAdvanceOrderId() {
        return advanceOrderId;
    }

    /**
     * 订单ID
     * @param advanceOrderId 订单ID
     */
    public void setAdvanceOrderId(Integer advanceOrderId) {
        this.advanceOrderId = advanceOrderId;
    }

    /**
     * 订单CODE
     * @return advance_order_code 订单CODE
     */
    public String getAdvanceOrderCode() {
        return advanceOrderCode;
    }

    /**
     * 订单CODE
     * @param advanceOrderCode 订单CODE
     */
    public void setAdvanceOrderCode(String advanceOrderCode) {
        this.advanceOrderCode = advanceOrderCode;
    }

    /**
     * 订单状态
     * @return advance_order_state 订单状态
     */
    public Integer getAdvanceOrderState() {
        return advanceOrderState;
    }

    /**
     * 订单状态
     * @param advanceOrderState 订单状态
     */
    public void setAdvanceOrderState(Integer advanceOrderState) {
        this.advanceOrderState = advanceOrderState;
    }

    /**
     * 支付方式
     * @return pay_method 支付方式
     */
    public Integer getPayMethod() {
        return payMethod;
    }

    /**
     * 支付方式
     * @param payMethod 支付方式
     */
    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * 支付流水
     * @return payment_record_bill_id 支付流水
     */
    public Integer getPaymentRecordBillId() {
        return paymentRecordBillId;
    }

    /**
     * 支付流水
     * @param paymentRecordBillId 支付流水
     */
    public void setPaymentRecordBillId(Integer paymentRecordBillId) {
        this.paymentRecordBillId = paymentRecordBillId;
    }

    /**
     * 支付状态 0未支付 1已支付 2已退款
     * @return order_in_pay_state 支付状态 0未支付 1已支付 2已退款
     */
    public Integer getOrderInPayState() {
        return orderInPayState;
    }

    /**
     * 支付状态 0未支付 1已支付 2已退款
     * @param orderInPayState 支付状态 0未支付 1已支付 2已退款
     */
    public void setOrderInPayState(Integer orderInPayState) {
        this.orderInPayState = orderInPayState;
    }

    /**
     * 备注
     * @return remarks 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 支付时间
     * @return pay_date 支付时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 支付时间
     * @param payDate 支付时间
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 支付单号
     * @return order_in_pay_code 支付单号
     */
    public String getOrderInPayCode() {
        return orderInPayCode;
    }

    /**
     * 支付单号
     * @param orderInPayCode 支付单号
     */
    public void setOrderInPayCode(String orderInPayCode) {
        this.orderInPayCode = orderInPayCode;
    }
}