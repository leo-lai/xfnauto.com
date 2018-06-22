package main.com.customer.dao.search;

import java.math.BigDecimal;
import java.util.Date;

import main.com.customer.dao.po.UsersTmpl;
import main.com.frame.search.BaseSearch;

public class CustomerOrderSearch extends BaseSearch{ 

	/**
	 * 
	 */
	private UsersTmpl usersTmpl;
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
	     * 购买用户ID
	     */
	    private Integer customerUsersId;

	    /**
	     * 客户名称
	     */
	    private String customerName;

	    /**
	     * 
	     */
	    private String customerPhoneNumber;
	    
	    /**
	     * 
	     */
	    private String phoneNumber;

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
	    private Double invoicePrice;

	    /**
	     * 定金
	     */
	    private Double depositPrice;
	    /**
	     * 定金
	     */
	    private Boolean isDepositPrice;

	    /**
	     * 
	     */
	    private Double discountPrice;

	    /**
	     * 内饰ID
	     */
	    private Integer interiorId;

	    /**
	     * 颜色ID
	     */
	    private Integer colourId;

	    /**
	     * 随车资料，多个用逗号隔开
	     */
	    private String followInformation;

	    /**
	     * 
	     */
	    private Double balancePrice;

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
	     * 备注
	     */
	    private String remark;

	    /**
	     * 付款方式 1.全款 2分期
	     */
	    private Integer paymentWay;

	    /**
	     * 首付
	     */
	    private Double downPayments;

	    /**
	     * 贷款
	     */
	    private Double loan;

	    /**
	     * 贷款分期数
	     */
	    private Integer loanPayBackStages;

	    /**
	     * 最终成交价
	     */
	    private Double amount;

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
	    private Double licensePlatePriace;

	    /**
	     * 是否附加商业险
	     */
	    private Boolean isInsurance;

	    /**
	     * 保险金额
	     */
	    private Double insurancePriace;
	    /**
	     * 保险金额
	     */
	    private Double vehicleAndVesselTax;
	    /**
	     * 搜索
	     */
	    private String carsSearch;
	    
	    /**
	     * 支付方式
	     */
	    private Integer payMethod;	    
	    
	    private Integer systemUserId;//销售顾问
	    private String systemUserName;//销售顾问
	    private String systemUserPhone;	//销售电话
	    private String extractCarImage;	//提车图片
	    
	    private Boolean isMortgage; //是否抵押
	    private String estimateDate; //是否抵押
	    
	    private Boolean isPassThrough; //是否审核通过
	    private String refusalReason; //拒绝理由
	    private String customerUserCard; //身份证号
	    private String visitContent;//回访内容
	    
	    /**
	     * 购置税
	     */
	    private Double purchaseTaxPriace;
	    
	    /**
	     * 精品加装费
	     */
	    private Double boutiquePriace;
	    
	    /**
	     * 加装精品
	     */
	    private String boutiqueInformation;
	    
	    /**
	     * 按揭费用
	     */
	    private Double mortgagePriace;
	    /**
	     * 最终成交价
	     */
	    private Double carUnitPrice;
	    /**
	     * 贷款银行
	     */
	    private Integer loanBank;
	    
	    private Integer advanceOrderId;
	    
		public UsersTmpl getUsersTmpl() {
			return usersTmpl;
		}
		public void setUsersTmpl(UsersTmpl usersTmpl) {
			this.usersTmpl = usersTmpl;
		}
		public Integer getCustomerOrderId() {
			return customerOrderId;
		}
		public void setCustomerOrderId(Integer customerOrderId) {
			this.customerOrderId = customerOrderId;
		}
		public String getCustomerOrderCode() {
			return customerOrderCode;
		}
		public void setCustomerOrderCode(String customerOrderCode) {
			this.customerOrderCode = customerOrderCode;
		}
		public Integer getCustomerOrderState() {
			return customerOrderState;
		}
		public void setCustomerOrderState(Integer customerOrderState) {
			this.customerOrderState = customerOrderState;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public Boolean getIsDelete() {
			return isDelete;
		}
		public void setIsDelete(Boolean isDelete) {
			this.isDelete = isDelete;
		}
		public Integer getCustomerId() {
			return customerId;
		}
		public void setCustomerId(Integer customerId) {
			this.customerId = customerId;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getCustomerPhoneNumber() {
			return customerPhoneNumber;
		}
		public void setCustomerPhoneNumber(String customerPhoneNumber) {
			this.customerPhoneNumber = customerPhoneNumber;
		}
		public Integer getCarsId() {
			return carsId;
		}
		public void setCarsId(Integer carsId) {
			this.carsId = carsId;
		}
		public String getCarsName() {
			return carsName;
		}
		public void setCarsName(String carsName) {
			this.carsName = carsName;
		}
		public String getCarsIndexImage() {
			return carsIndexImage;
		}
		public void setCarsIndexImage(String carsIndexImage) {
			this.carsIndexImage = carsIndexImage;
		}
		public Integer getBrandId() {
			return brandId;
		}
		public void setBrandId(Integer brandId) {
			this.brandId = brandId;
		}
		public Integer getFamilyId() {
			return familyId;
		}
		public void setFamilyId(Integer familyId) {
			this.familyId = familyId;
		}
		public Double getInvoicePrice() {
			return invoicePrice;
		}
		public void setInvoicePrice(Double invoicePrice) {
			this.invoicePrice = invoicePrice;
		}
		public Double getDepositPrice() {
			return depositPrice;
		}
		public void setDepositPrice(Double depositPrice) {
			this.depositPrice = depositPrice;
		}
		public Double getDiscountPrice() {
			return discountPrice;
		}
		public void setDiscountPrice(Double discountPrice) {
			this.discountPrice = discountPrice;
		}
		public Integer getInteriorId() {
			return interiorId;
		}
		public void setInteriorId(Integer interiorId) {
			this.interiorId = interiorId;
		}
		public Integer getColourId() {
			return colourId;
		}
		public void setColourId(Integer colourId) {
			this.colourId = colourId;
		}
		public String getFollowInformation() {
			return followInformation;
		}
		public void setFollowInformation(String followInformation) {
			this.followInformation = followInformation;
		}
		public Double getBalancePrice() {
			return balancePrice;
		}
		public void setBalancePrice(Double balancePrice) {
			this.balancePrice = balancePrice;
		}
		public Integer getOrgId() {
			return orgId;
		}
		public void setOrgId(Integer orgId) {
			this.orgId = orgId;
		}
		public String getOrgName() {
			return orgName;
		}
		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}
		public Integer getStockCarId() {
			return stockCarId;
		}
		public void setStockCarId(Integer stockCarId) {
			this.stockCarId = stockCarId;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public Integer getPaymentWay() {
			return paymentWay;
		}
		public void setPaymentWay(Integer paymentWay) {
			this.paymentWay = paymentWay;
		}
		public Double getDownPayments() {
			return downPayments;
		}
		public void setDownPayments(Double downPayments) {
			this.downPayments = downPayments;
		}
		public Double getLoan() {
			return loan;
		}
		public void setLoan(Double loan) {
			this.loan = loan;
		}
		public Integer getLoanPayBackStages() {
			return loanPayBackStages;
		}
		public void setLoanPayBackStages(Integer loanPayBackStages) {
			this.loanPayBackStages = loanPayBackStages;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public Boolean getIsPurchaseTax() {
			return isPurchaseTax;
		}
		public void setIsPurchaseTax(Boolean isPurchaseTax) {
			this.isPurchaseTax = isPurchaseTax;
		}
		public Boolean getIsTakeLicensePlate() {
			return isTakeLicensePlate;
		}
		public void setIsTakeLicensePlate(Boolean isTakeLicensePlate) {
			this.isTakeLicensePlate = isTakeLicensePlate;
		}
		public Double getLicensePlatePriace() {
			return licensePlatePriace;
		}
		public void setLicensePlatePriace(Double licensePlatePriace) {
			this.licensePlatePriace = licensePlatePriace;
		}
		public Boolean getIsInsurance() {
			return isInsurance;
		}
		public void setIsInsurance(Boolean isInsurance) {
			this.isInsurance = isInsurance;
		}
		public Double getInsurancePriace() {
			return insurancePriace;
		}
		public void setInsurancePriace(Double insurancePriace) {
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
		public String getExtractCarImage() {
			return extractCarImage;
		}
		public void setExtractCarImage(String extractCarImage) {
			this.extractCarImage = extractCarImage;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
//		public Integer getCustomerUserId() {
//			return customerUserId;
//		}
//		public void setCustomerUserId(Integer customerUserId) {
//			this.customerUserId = customerUserId;
//		}
//		public Boolean getIsCash() {
//			return isCash;
//		}
		public Integer getCustomerUsersId() {
			return customerUsersId;
		}
		public void setCustomerUsersId(Integer customerUsersId) {
			this.customerUsersId = customerUsersId;
		}
//		public void setIsCash(Boolean isCash) {
//			this.isCash = isCash;
//		}
		public Boolean getIsMortgage() {
			return isMortgage;
		}
		public void setIsMortgage(Boolean isMortgage) {
			this.isMortgage = isMortgage;
		}
		public String getCarsSearch() {
			return carsSearch;
		}
		public void setCarsSearch(String carsSearch) {
			this.carsSearch = carsSearch;
		}
		public String getEstimateDate() {
			return estimateDate;
		}
		public void setEstimateDate(String estimateDate) {
			this.estimateDate = estimateDate;
		}
		public Boolean getIsPassThrough() {
			return isPassThrough;
		}
		public void setIsPassThrough(Boolean isPassThrough) {
			this.isPassThrough = isPassThrough;
		}
		public String getRefusalReason() {
			return refusalReason;
		}
		public void setRefusalReason(String refusalReason) {
			this.refusalReason = refusalReason;
		}
		public String getCustomerUserCard() {
			return customerUserCard;
		}
		public void setCustomerUserCard(String customerUserCard) {
			this.customerUserCard = customerUserCard;
		}
		public Double getPurchaseTaxPriace() {
			return purchaseTaxPriace;
		}
		public void setPurchaseTaxPriace(Double purchaseTaxPriace) {
			this.purchaseTaxPriace = purchaseTaxPriace;
		}
		public Double getBoutiquePriace() {
			return boutiquePriace;
		}
		public void setBoutiquePriace(Double boutiquePriace) {
			this.boutiquePriace = boutiquePriace;
		}
		public Double getMortgagePriace() {
			return mortgagePriace;
		}
		public void setMortgagePriace(Double mortgagePriace) {
			this.mortgagePriace = mortgagePriace;
		}
		public Integer getLoanBank() {
			return loanBank;
		}
		public void setLoanBank(Integer loanBank) {
			this.loanBank = loanBank;
		}
		public Double getCarUnitPrice() {
			return carUnitPrice;
		}
		public void setCarUnitPrice(Double carUnitPrice) {
			this.carUnitPrice = carUnitPrice;
		}
		public Double getVehicleAndVesselTax() {
			return vehicleAndVesselTax;
		}
		public void setVehicleAndVesselTax(Double vehicleAndVesselTax) {
			this.vehicleAndVesselTax = vehicleAndVesselTax;
		}
		public Boolean getIsDepositPrice() {
			return isDepositPrice;
		}
		public void setIsDepositPrice(Boolean isDepositPrice) {
			this.isDepositPrice = isDepositPrice;
		}
		public Integer getPayMethod() {
			return payMethod;
		}
		public void setPayMethod(Integer payMethod) {
			this.payMethod = payMethod;
		}
		public String getVisitContent() {
			return visitContent;
		}
		public void setVisitContent(String visitContent) {
			this.visitContent = visitContent;
		}
		public String getBoutiqueInformation() {
			return boutiqueInformation;
		}
		public void setBoutiqueInformation(String boutiqueInformation) {
			this.boutiqueInformation = boutiqueInformation;
		}
		public Integer getAdvanceOrderId() {
			return advanceOrderId;
		}
		public void setAdvanceOrderId(Integer advanceOrderId) {
			this.advanceOrderId = advanceOrderId;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
}
