package main.com.logistics.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"})
public class LogisticsConsignmentInPay implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer consignmentInPayId;

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
    private Integer consignmentId;

    /**
     * 订单CODE
     */
    private String consignmentCode;

    /**
     * 订单状态
     */
    private Integer consignmentState;

    /**
     * 支付方式
     */
    private Integer payMethod;

    /**
     * 支付流水
     */
    private Integer weixinPayAccountId;

    /**
     * 支付状态 0未支付 1已支付 2已退款
     */
    private Integer consignmentInPayState;

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
    private String consignmentInPayCode;
    
    /**
     * 所属物流单
     */
    private Integer distributionId;
    
    /**
     * 所属物流单CODE
     */
    private String distributionCode;
    
    /**
     * 所属物流单CODE
     */
    private String payName;
    
    /**
     * 托运车辆，多个使用逗号隔开
     */
    private String goodsCarIds;
    
    /**
     * 托运车辆，多个使用逗号隔开
     */
//    private String consumerOrderUserName;
    
    /**
     * 托运车辆，多个使用逗号隔开
     */
//    private String phoneNumber;
    
    
    /**
     * 托运车辆，多个使用逗号隔开
     */
//    private Integer consumerOrderUserId;


    /**
     * 主键
     * @return consignment_in_pay_id 主键
     */
    public Integer getConsignmentInPayId() {
        return consignmentInPayId;
    }

    /**
     * 主键
     * @param consignmentInPayId 主键
     */
    public void setConsignmentInPayId(Integer consignmentInPayId) {
        this.consignmentInPayId = consignmentInPayId;
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
     * @return consignment_id 订单ID
     */
    public Integer getConsignmentId() {
        return consignmentId;
    }

    /**
     * 订单ID
     * @param consignmentId 订单ID
     */
    public void setConsignmentId(Integer consignmentId) {
        this.consignmentId = consignmentId;
    }

    /**
     * 订单CODE
     * @return consignment_code 订单CODE
     */
    public String getConsignmentCode() {
        return consignmentCode;
    }

    /**
     * 订单CODE
     * @param consignmentCode 订单CODE
     */
    public void setConsignmentCode(String consignmentCode) {
        this.consignmentCode = consignmentCode;
    }

    /**
     * 订单状态
     * @return consignment_state 订单状态
     */
    public Integer getConsignmentState() {
        return consignmentState;
    }

    /**
     * 订单状态
     * @param consignmentState 订单状态
     */
    public void setConsignmentState(Integer consignmentState) {
        this.consignmentState = consignmentState;
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
     * @return weixin_pay_account_id 支付流水
     */
    public Integer getWeixinPayAccountId() {
        return weixinPayAccountId;
    }

    /**
     * 支付流水
     * @param weixinPayAccountId 支付流水
     */
    public void setWeixinPayAccountId(Integer weixinPayAccountId) {
        this.weixinPayAccountId = weixinPayAccountId;
    }

    /**
     * 支付状态 0未支付 1已支付 2已退款
     * @return consignment_in_pay_state 支付状态 0未支付 1已支付 2已退款
     */
    public Integer getConsignmentInPayState() {
        return consignmentInPayState;
    }

    /**
     * 支付状态 0未支付 1已支付 2已退款
     * @param consignmentInPayState 支付状态 0未支付 1已支付 2已退款
     */
    public void setConsignmentInPayState(Integer consignmentInPayState) {
        this.consignmentInPayState = consignmentInPayState;
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
     * @return consignment_in_pay_code 支付单号
     */
    public String getConsignmentInPayCode() {
        return consignmentInPayCode;
    }

    /**
     * 支付单号
     * @param consignmentInPayCode 支付单号
     */
    public void setConsignmentInPayCode(String consignmentInPayCode) {
        this.consignmentInPayCode = consignmentInPayCode;
    }

	public Integer getDistributionId() {
		return distributionId;
	}

	public void setDistributionId(Integer integer) {
		this.distributionId = integer;
	}

	public String getDistributionCode() {
		return distributionCode;
	}

	public void setDistributionCode(String distributionCode) {
		this.distributionCode = distributionCode;
	}

	public String getGoodsCarIds() {
		return goodsCarIds;
	}

	public void setGoodsCarIds(String goodsCarIds) {
		this.goodsCarIds = goodsCarIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}
}