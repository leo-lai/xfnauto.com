package main.com.customer.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
public class CustomerOrder implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键ID
     */
    private Integer customerOrderId;

    /**
     * 订单编号
     */
    private String customerOrderCode;

    /**
     * 订单状态
     */
    private Integer customerOrderState;

    /**
     * 
     */
    private Date createDate;

    /**
     * 是否已删除
     */
    private Boolean isDelete;

    /**
     * 购买用户ID
     */
    private Integer customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 
     */
    private String customerPhoneNumber;

    /**
     * 车大类名称
     */
    private Integer carsId;

    /**
     * 车大类名称
     */
    private String carsName;

    /**
     * 车辆展示图
     */
    private String carsIndexImage;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 车系ID
     */
    private Integer familyId;

    /**
     * 发票金额
     */
    private BigDecimal invoicePrice;

    /**
     * 定金
     */
    private BigDecimal depositPrice;

    /**
     * 
     */
    private BigDecimal discountPrice;

    /**
     * 内饰ID
     */
    private Integer interiorId;

    /**
     * 颜色ID
     */
    private Integer colourId;

    /**
     * 赠送精品
     */
    private String followInformation;
    /**
     * 加装精品
     */
    private String boutiqueInformation;

    /**
     * 
     */
    private BigDecimal balancePrice;

    /**
     * 
     */
    private Integer orgId;

    /**
     * 
     */
    private String orgName;

    /**
     * 具体库存车辆
     */
    private Integer stockCarId;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 付款方式 1.全款 2分期
     */
    private Integer paymentWay;

    /**
     * 首付
     */
    private BigDecimal downPayments;

    /**
     * 贷款
     */
    private BigDecimal loan;

    /**
     * 贷款分期数
     */
    private Integer loanPayBackStages;

    /**
     * 订单费用
     */
    private BigDecimal amount;

    /**
     * 是否附带购置税
     */
    private Boolean isPurchaseTax;

    /**
     * 是否附带上牌
     */
    private Boolean isTakeLicensePlate;

    /**
     * 上牌费用
     */
    private BigDecimal licensePlatePriace;

    /**
     * 是否附加商业险
     */
    private Boolean isInsurance;

    /**
     * 保险金额
     */
    private BigDecimal insurancePriace;
    
    /**
     * 购置税
     */
    private BigDecimal purchaseTaxPriace;
    
    /**
     * 车船税
     */
    private BigDecimal vehicleAndVesselTax;
    
    /**
     * 精品加装费
     */
    private BigDecimal boutiquePriace;
    
    /**
     * 按揭费用
     */
    private BigDecimal mortgagePriace;
    /**
     * 按揭银行
     */
    private Integer loanBank;
    /**
     * 是否已过线
     */
    private Boolean overTheLine;
    /**
     * 汽车单价（汽车最终成交价）
     */
    private BigDecimal carUnitPrice;
    
    
    public BigDecimal getMortgagePriace() {
		return mortgagePriace;
	}

	public void setMortgagePriace(BigDecimal mortgagePriace) {
		this.mortgagePriace = mortgagePriace;
	}

	private Integer systemUserId;//销售顾问
    private String systemUserName;//销售顾问
    private String systemUserPhone;	//销售电话
    private String extractCarImage;	//提车图片

    private String colourName;
    private String interiorName;
    
    private Boolean isMortgage;     //是否抵押
    private Date estimateDate;     //加装精品预计时间
    
    private Integer auditStatus;     //银行审核是否
    private Date auditTime;     //银行审核时间
    
    private String customerUserCard;     //用户身份证号
    
    /**
     * 主键ID
     * @return customer_order_id 主键ID
     */
    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    /**
     * 主键ID
     * @param customerOrderId 主键ID
     */
    public void setCustomerOrderId(Integer customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    /**
     * 订单编号
     * @return customer_order_code 订单编号
     */
    public String getCustomerOrderCode() {
        return customerOrderCode;
    }

    /**
     * 订单编号
     * @param customerOrderCode 订单编号
     */
    public void setCustomerOrderCode(String customerOrderCode) {
        this.customerOrderCode = customerOrderCode;
    }

    /**
     * 订单状态
     * @return customer_order_state 订单状态
     */
    public Integer getCustomerOrderState() {
        return customerOrderState;
    }

    /**
     * 订单状态
     * @param customerOrderState 订单状态
     */
    public void setCustomerOrderState(Integer customerOrderState) {
        this.customerOrderState = customerOrderState;
    }

    /**
     * 
     * @return create_date 
     */
    /**
     * 创建时间
     * @return create_time 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 
     * @param createDate 
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 是否已删除
     * @return is_delete 是否已删除
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 是否已删除
     * @param isDelete 是否已删除
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 购买用户ID
     * @return customer_id 购买用户ID
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 购买用户ID
     * @param customerId 购买用户ID
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 客户名称
     * @return customer_name 客户名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 客户名称
     * @param customerName 客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 
     * @return customer_phone_number 
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * 
     * @param customerPhoneNumber 
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    /**
     * 车大类名称
     * @return cars_id 车大类名称
     */
    public Integer getCarsId() {
        return carsId;
    }

    /**
     * 车大类名称
     * @param carsId 车大类名称
     */
    public void setCarsId(Integer carsId) {
        this.carsId = carsId;
    }

    /**
     * 车大类名称
     * @return cars_name 车大类名称
     */
    public String getCarsName() {
        return carsName;
    }

    /**
     * 车大类名称
     * @param carsName 车大类名称
     */
    public void setCarsName(String carsName) {
        this.carsName = carsName;
    }

    /**
     * 车辆展示图
     * @return cars_index_image 车辆展示图
     */
    public String getCarsIndexImage() {
        return carsIndexImage;
    }

    /**
     * 车辆展示图
     * @param carsIndexImage 车辆展示图
     */
    public void setCarsIndexImage(String carsIndexImage) {
        this.carsIndexImage = carsIndexImage;
    }

    /**
     * 品牌ID
     * @return brand_id 品牌ID
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * 品牌ID
     * @param brandId 品牌ID
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * 车系ID
     * @return family_id 车系ID
     */
    public Integer getFamilyId() {
        return familyId;
    }

    /**
     * 车系ID
     * @param familyId 车系ID
     */
    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    /**
     * 发票金额
     * @return invoice_price 发票金额
     */
    public BigDecimal getInvoicePrice() {
        return invoicePrice;
    }

    /**
     * 发票金额
     * @param invoicePrice 发票金额
     */
    public void setInvoicePrice(BigDecimal invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    /**
     * 定金
     * @return deposit_price 定金
     */
    public BigDecimal getDepositPrice() {
        return depositPrice;
    }

    /**
     * 定金
     * @param depositPrice 定金
     */
    public void setDepositPrice(BigDecimal depositPrice) {
        this.depositPrice = depositPrice;
    }

    /**
     * 
     * @return discount_price 
     */
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    /**
     * 
     * @param discountPrice 
     */
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * 内饰ID
     * @return interior_id 内饰ID
     */
    public Integer getInteriorId() {
        return interiorId;
    }

    /**
     * 内饰ID
     * @param interiorId 内饰ID
     */
    public void setInteriorId(Integer interiorId) {
        this.interiorId = interiorId;
    }

    /**
     * 颜色ID
     * @return colour_id 颜色ID
     */
    public Integer getColourId() {
        return colourId;
    }

    /**
     * 颜色ID
     * @param colourId 颜色ID
     */
    public void setColourId(Integer colourId) {
        this.colourId = colourId;
    }

    /**
     * 随车资料，多个用逗号隔开
     * @return follow_information 随车资料，多个用逗号隔开
     */
    public String getFollowInformation() {
        return followInformation;
    }

    /**
     * 随车资料，多个用逗号隔开
     * @param followInformation 随车资料，多个用逗号隔开
     */
    public void setFollowInformation(String followInformation) {
        this.followInformation = followInformation;
    }

    /**
     * 
     * @return balance_price 
     */
    public BigDecimal getBalancePrice() {
        return balancePrice;
    }

    /**
     * 
     * @param balancePrice 
     */
    public void setBalancePrice(BigDecimal balancePrice) {
        this.balancePrice = balancePrice;
    }

    /**
     * 
     * @return org_id 
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 
     * @param orgId 
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 
     * @return org_name 
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 
     * @param orgName 
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 具体库存车辆
     * @return stock_car_id 具体库存车辆
     */
    public Integer getStockCarId() {
        return stockCarId;
    }

    /**
     * 具体库存车辆
     * @param stockCarId 具体库存车辆
     */
    public void setStockCarId(Integer stockCarId) {
        this.stockCarId = stockCarId;
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
     * 付款方式 1.全款 2分期
     * @return payment_way 付款方式 1.全款 2分期
     */
    public Integer getPaymentWay() {
        return paymentWay;
    }

    /**
     * 付款方式 1.全款 2分期
     * @param paymentWay 付款方式 1.全款 2分期
     */
    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    /**
     * 首付
     * @return down_payments 首付
     */
    public BigDecimal getDownPayments() {
        return downPayments;
    }

    /**
     * 首付
     * @param downPayments 首付
     */
    public void setDownPayments(BigDecimal downPayments) {
        this.downPayments = downPayments;
    }

    /**
     * 贷款
     * @return loan 贷款
     */
    public BigDecimal getLoan() {
        return loan;
    }

    /**
     * 贷款
     * @param loan 贷款
     */
    public void setLoan(BigDecimal loan) {
        this.loan = loan;
    }

    /**
     * 贷款分期数
     * @return loan_pay_back_stages 贷款分期数
     */
    public Integer getLoanPayBackStages() {
        return loanPayBackStages;
    }

    /**
     * 贷款分期数
     * @param loanPayBackStages 贷款分期数
     */
    public void setLoanPayBackStages(Integer loanPayBackStages) {
        this.loanPayBackStages = loanPayBackStages;
    }

    /**
     * 最终成交价
     * @return amount 最终成交价
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 最终成交价
     * @param amount 最终成交价
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 是否附带购置税
     * @return is_purchase_tax 是否附带购置税
     */
    public Boolean getIsPurchaseTax() {
        return isPurchaseTax;
    }

    /**
     * 是否附带购置税
     * @param isPurchaseTax 是否附带购置税
     */
    public void setIsPurchaseTax(Boolean isPurchaseTax) {
        this.isPurchaseTax = isPurchaseTax;
    }

    /**
     * 是否附带上牌
     * @return is_take_license_plate 是否附带上牌
     */
    public Boolean getIsTakeLicensePlate() {
        return isTakeLicensePlate;
    }

    /**
     * 是否附带上牌
     * @param isTakeLicensePlate 是否附带上牌
     */
    public void setIsTakeLicensePlate(Boolean isTakeLicensePlate) {
        this.isTakeLicensePlate = isTakeLicensePlate;
    }

    /**
     * 上牌费用
     * @return license_plate_priace 上牌费用
     */
    public BigDecimal getLicensePlatePriace() {
        return licensePlatePriace;
    }

    /**
     * 上牌费用
     * @param licensePlatePriace 上牌费用
     */
    public void setLicensePlatePriace(BigDecimal licensePlatePriace) {
        this.licensePlatePriace = licensePlatePriace;
    }

    /**
     * 是否附加商业险
     * @return is_insurance 是否附加商业险
     */
    public Boolean getIsInsurance() {
        return isInsurance;
    }

    /**
     * 是否附加商业险
     * @param isInsurance 是否附加商业险
     */
    public void setIsInsurance(Boolean isInsurance) {
        this.isInsurance = isInsurance;
    }

    /**
     * 保险金额
     * @return insurance_priace 保险金额
     */
    public BigDecimal getInsurancePriace() {
        return insurancePriace;
    }

    /**
     * 保险金额
     * @param insurancePriace 保险金额
     */
    public void setInsurancePriace(BigDecimal insurancePriace) {
        this.insurancePriace = insurancePriace;
    }

	public Integer getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Integer systemUserId) {
		this.systemUserId = systemUserId;
	}

	public String getSystemUserName() {
		return systemUserName;
	}

	public void setSystemUserName(String systemUserName) {
		this.systemUserName = systemUserName;
	}

	public String getSystemUserPhone() {
		return systemUserPhone;
	}

	public void setSystemUserPhone(String systemUserPhone) {
		this.systemUserPhone = systemUserPhone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getExtractCarImage() {
		return extractCarImage;
	}

	public void setExtractCarImage(String extractCarImage) {
		this.extractCarImage = extractCarImage;
	}

	public String getColourName() {
		return colourName;
	}

	public void setColourName(String colourName) {
		this.colourName = colourName;
	}

	public String getInteriorName() {
		return interiorName;
	}

	public void setInteriorName(String interiorName) {
		this.interiorName = interiorName;
	}

	public Boolean getIsMortgage() {
		return isMortgage;
	}

	public void setIsMortgage(Boolean isMortgage) {
		this.isMortgage = isMortgage;
	}

	public Date getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getCustomerUserCard() {
		return customerUserCard;
	}

	public void setCustomerUserCard(String customerUserCard) {
		this.customerUserCard = customerUserCard;
	}

	public BigDecimal getPurchaseTaxPriace() {
		return purchaseTaxPriace;
	}

	public void setPurchaseTaxPriace(BigDecimal purchaseTaxPriace) {
		this.purchaseTaxPriace = purchaseTaxPriace;
	}

	public BigDecimal getBoutiquePriace() {
		return boutiquePriace;
	}

	public void setBoutiquePriace(BigDecimal boutiquePriace) {
		this.boutiquePriace = boutiquePriace;
	}

	public Integer getLoanBank() {
		return loanBank;
	}

	public void setLoanBank(Integer loanBank) {
		this.loanBank = loanBank;
	}

	public BigDecimal getCarUnitPrice() {
		return carUnitPrice;
	}

	public void setCarUnitPrice(BigDecimal carUnitPrice) {
		this.carUnitPrice = carUnitPrice;
	}

	public BigDecimal getVehicleAndVesselTax() {
		return vehicleAndVesselTax;
	}

	public void setVehicleAndVesselTax(BigDecimal vehicleAndVesselTax) {
		this.vehicleAndVesselTax = vehicleAndVesselTax;
	}

	public Boolean getOverTheLine() {
		return overTheLine;
	}

	public void setOverTheLine(Boolean overTheLine) {
		this.overTheLine = overTheLine;
	}

	public String getBoutiqueInformation() {
		return boutiqueInformation;
	}

	public void setBoutiqueInformation(String boutiqueInformation) {
		this.boutiqueInformation = boutiqueInformation;
	}
}