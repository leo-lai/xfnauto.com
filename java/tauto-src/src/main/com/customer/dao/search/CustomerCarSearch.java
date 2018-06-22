package main.com.customer.dao.search;

import java.math.BigDecimal;
import java.util.Date;

import main.com.frame.search.BaseSearch;

public class CustomerCarSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		/**
	     * 人车主键
	     */
	    private Integer customerCarId;

	    /**
	     * 创建时间
	     */
	    private Date createDate;

	    /**
	     * 用户ID
	     */
	    private Integer customerUserId;

	    /**
	     * 车辆ID
	     */
	    private Integer stockCarId;

	    /**
	     * 车辆信息
	     */
	    private String stockCarName;

	    /**
	     * 车辆类型ID
	     */
	    private Integer carsId;

	    /**
	     * 发动机号
	     */
	    private String engineNumber;
	    
	    /**
	     * 发动机号
	     */
	    private String frameNumber;

	    /**
	     * 合格证号
	     */
	    private String certificateNumber;

	    /**
	     * 内饰
	     */
	    private Integer interiorId;

	    /**
	     * 内饰名称
	     */
	    private String interiorName;

	    /**
	     * 颜色
	     */
	    private Integer colourId;

	    /**
	     * 颜色名称
	     */
	    private String colourName;

	    /**
	     * 是否已删除
	     */
	    private Byte isDelete;

	    /**
	     * 车牌号码
	     */
	    private String licensePlateNumber;

	    /**
	     * 成交价
	     */
	    private BigDecimal transactionPrice;

	    /**
	     * 购车方式
	     */
	    private String carPurchasePlan;

	    /**
	     * 贷款方案
	     */
	    private String loanScheme;

	    /**
	     * 赠送精品
	     */
	    private String give;

	    /**
	     * 售后服务
	     */
	    private String afterSaleSupport;

	    /**
	     * 购置税
	     */
	    private Long purchaseTax;

	    /**
	     * 消费税
	     */
	    private Long exciseTax;

	    /**
	     * 车船税
	     */
	    private Long vehicleAndVesselTax;

	    /**
	     * 上牌费用
	     */
	    private Long premium;

	    /**
	     * 强制保险
	     */
	    private Long compulsoryInsurance;

	    /**
	     * 第三者责任险
	     */
	    private Long thirdPartyLiabilityInsurance;

	    /**
	     * 车辆损失险
	     */
	    private Long vehicleLossInsurance;

	    /**
	     * 玻璃单独破碎险
	     */
	    private Long riskOfGlassBreakage;

	    /**
	     * 自燃损失险
	     */
	    private Long selfIgnitionLossInsurance;

	    /**
	     * 不计免赔特约险
	     */
	    private Long exemptionFromSpecialContract;

	    /**
	     * 无过责任险
	     */
	    private Long noLiabilityInsurance;

	    /**
	     * 车上人员责任险
	     */
	    private Long personnelLiabilityInsurance;

	    /**
	     * 车上划痕险
	     */
	    private Long scratchRisk;
	    /**
	     * 订单ID
	     */
	    private Integer customerOrderId;
	    /**
	     * 图片
	     */
	    private String images;

	    /**
	     * 人车主键
	     * @return customer_car_id 人车主键
	     */
	    public Integer getCustomerCarId() {
	        return customerCarId;
	    }

	    /**
	     * 人车主键
	     * @param customerCarId 人车主键
	     */
	    public void setCustomerCarId(Integer customerCarId) {
	        this.customerCarId = customerCarId;
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
	     * 用户ID
	     * @return customer_user_id 用户ID
	     */
	    public Integer getCustomerUserId() {
	        return customerUserId;
	    }

	    /**
	     * 用户ID
	     * @param customerUserId 用户ID
	     */
	    public void setCustomerUserId(Integer customerUserId) {
	        this.customerUserId = customerUserId;
	    }

	    /**
	     * 车辆ID
	     * @return stock_car_id 车辆ID
	     */
	    public Integer getStockCarId() {
	        return stockCarId;
	    }

	    /**
	     * 车辆ID
	     * @param stockCarId 车辆ID
	     */
	    public void setStockCarId(Integer stockCarId) {
	        this.stockCarId = stockCarId;
	    }

	    /**
	     * 车辆信息
	     * @return stock_car_name 车辆信息
	     */
	    public String getStockCarName() {
	        return stockCarName;
	    }

	    /**
	     * 车辆信息
	     * @param stockCarName 车辆信息
	     */
	    public void setStockCarName(String stockCarName) {
	        this.stockCarName = stockCarName;
	    }

	    /**
	     * 车辆类型ID
	     * @return cars_id 车辆类型ID
	     */
	    public Integer getCarsId() {
	        return carsId;
	    }

	    /**
	     * 车辆类型ID
	     * @param carsId 车辆类型ID
	     */
	    public void setCarsId(Integer carsId) {
	        this.carsId = carsId;
	    }

	    /**
	     * 发动机号
	     * @return engine_number 发动机号
	     */
	    public String getEngineNumber() {
	        return engineNumber;
	    }

	    /**
	     * 发动机号
	     * @param engineNumber 发动机号
	     */
	    public void setEngineNumber(String engineNumber) {
	        this.engineNumber = engineNumber;
	    }

	    /**
	     * 合格证号
	     * @return certificate_number 合格证号
	     */
	    public String getCertificateNumber() {
	        return certificateNumber;
	    }

	    /**
	     * 合格证号
	     * @param certificateNumber 合格证号
	     */
	    public void setCertificateNumber(String certificateNumber) {
	        this.certificateNumber = certificateNumber;
	    }

	    /**
	     * 内饰
	     * @return interior_id 内饰
	     */
	    public Integer getInteriorId() {
	        return interiorId;
	    }

	    /**
	     * 内饰
	     * @param interiorId 内饰
	     */
	    public void setInteriorId(Integer interiorId) {
	        this.interiorId = interiorId;
	    }

	    /**
	     * 内饰名称
	     * @return interior_name 内饰名称
	     */
	    public String getInteriorName() {
	        return interiorName;
	    }

	    /**
	     * 内饰名称
	     * @param interiorName 内饰名称
	     */
	    public void setInteriorName(String interiorName) {
	        this.interiorName = interiorName;
	    }

	    /**
	     * 颜色
	     * @return colour_id 颜色
	     */
	    public Integer getColourId() {
	        return colourId;
	    }

	    /**
	     * 颜色
	     * @param colourId 颜色
	     */
	    public void setColourId(Integer colourId) {
	        this.colourId = colourId;
	    }

	    /**
	     * 颜色名称
	     * @return colour_name 颜色名称
	     */
	    public String getColourName() {
	        return colourName;
	    }

	    /**
	     * 颜色名称
	     * @param colourName 颜色名称
	     */
	    public void setColourName(String colourName) {
	        this.colourName = colourName;
	    }

	    /**
	     * 是否已删除
	     * @return is_delete 是否已删除
	     */
	    public Byte getIsDelete() {
	        return isDelete;
	    }

	    /**
	     * 是否已删除
	     * @param isDelete 是否已删除
	     */
	    public void setIsDelete(Byte isDelete) {
	        this.isDelete = isDelete;
	    }

	    /**
	     * 车牌号码
	     * @return license_plate_number 车牌号码
	     */
	    public String getLicensePlateNumber() {
	        return licensePlateNumber;
	    }

	    /**
	     * 车牌号码
	     * @param licensePlateNumber 车牌号码
	     */
	    public void setLicensePlateNumber(String licensePlateNumber) {
	        this.licensePlateNumber = licensePlateNumber;
	    }

	    /**
	     * 成交价
	     * @return transaction_price 成交价
	     */
	    public BigDecimal getTransactionPrice() {
	        return transactionPrice;
	    }

	    /**
	     * 成交价
	     * @param transactionPrice 成交价
	     */
	    public void setTransactionPrice(BigDecimal transactionPrice) {
	        this.transactionPrice = transactionPrice;
	    }

	    /**
	     * 
	     * @return car_purchase_plan 
	     */
	    public String getCarPurchasePlan() {
	        return carPurchasePlan;
	    }

	    /**
	     * 
	     * @param carPurchasePlan 
	     */
	    public void setCarPurchasePlan(String carPurchasePlan) {
	        this.carPurchasePlan = carPurchasePlan;
	    }

	    /**
	     * 
	     * @return loan_scheme 
	     */
	    public String getLoanScheme() {
	        return loanScheme;
	    }

	    /**
	     * 
	     * @param loanScheme 
	     */
	    public void setLoanScheme(String loanScheme) {
	        this.loanScheme = loanScheme;
	    }

	    /**
	     * 赠送精品
	     * @return give 赠送精品
	     */
	    public String getGive() {
	        return give;
	    }

	    /**
	     * 赠送精品
	     * @param give 赠送精品
	     */
	    public void setGive(String give) {
	        this.give = give;
	    }

	    /**
	     * 
	     * @return after_sale_support 
	     */
	    public String getAfterSaleSupport() {
	        return afterSaleSupport;
	    }

	    /**
	     * 
	     * @param afterSaleSupport 
	     */
	    public void setAfterSaleSupport(String afterSaleSupport) {
	        this.afterSaleSupport = afterSaleSupport;
	    }

	    /**
	     * 购置税
	     * @return purchase_tax 购置税
	     */
	    public Long getPurchaseTax() {
	        return purchaseTax;
	    }

	    /**
	     * 购置税
	     * @param purchaseTax 购置税
	     */
	    public void setPurchaseTax(Long purchaseTax) {
	        this.purchaseTax = purchaseTax;
	    }

	    /**
	     * 消费税
	     * @return excise_tax 消费税
	     */
	    public Long getExciseTax() {
	        return exciseTax;
	    }

	    /**
	     * 消费税
	     * @param exciseTax 消费税
	     */
	    public void setExciseTax(Long exciseTax) {
	        this.exciseTax = exciseTax;
	    }

	    /**
	     * 车船税
	     * @return vehicle_and_vessel_tax 车船税
	     */
	    public Long getVehicleAndVesselTax() {
	        return vehicleAndVesselTax;
	    }

	    /**
	     * 车船税
	     * @param vehicleAndVesselTax 车船税
	     */
	    public void setVehicleAndVesselTax(Long vehicleAndVesselTax) {
	        this.vehicleAndVesselTax = vehicleAndVesselTax;
	    }

	    /**
	     * 上牌费用
	     * @return premium 上牌费用
	     */
	    public Long getPremium() {
	        return premium;
	    }

	    /**
	     * 上牌费用
	     * @param premium 上牌费用
	     */
	    public void setPremium(Long premium) {
	        this.premium = premium;
	    }

	    /**
	     * 强制保险
	     * @return compulsory_insurance 强制保险
	     */
	    public Long getCompulsoryInsurance() {
	        return compulsoryInsurance;
	    }

	    /**
	     * 强制保险
	     * @param compulsoryInsurance 强制保险
	     */
	    public void setCompulsoryInsurance(Long compulsoryInsurance) {
	        this.compulsoryInsurance = compulsoryInsurance;
	    }

	    /**
	     * 第三者责任险
	     * @return third_party_liability_insurance 第三者责任险
	     */
	    public Long getThirdPartyLiabilityInsurance() {
	        return thirdPartyLiabilityInsurance;
	    }

	    /**
	     * 第三者责任险
	     * @param thirdPartyLiabilityInsurance 第三者责任险
	     */
	    public void setThirdPartyLiabilityInsurance(Long thirdPartyLiabilityInsurance) {
	        this.thirdPartyLiabilityInsurance = thirdPartyLiabilityInsurance;
	    }

	    /**
	     * 车辆损失险
	     * @return vehicle_loss_insurance 车辆损失险
	     */
	    public Long getVehicleLossInsurance() {
	        return vehicleLossInsurance;
	    }

	    /**
	     * 车辆损失险
	     * @param vehicleLossInsurance 车辆损失险
	     */
	    public void setVehicleLossInsurance(Long vehicleLossInsurance) {
	        this.vehicleLossInsurance = vehicleLossInsurance;
	    }

	    /**
	     * 玻璃单独破碎险
	     * @return risk_of_glass_breakage 玻璃单独破碎险
	     */
	    public Long getRiskOfGlassBreakage() {
	        return riskOfGlassBreakage;
	    }

	    /**
	     * 玻璃单独破碎险
	     * @param riskOfGlassBreakage 玻璃单独破碎险
	     */
	    public void setRiskOfGlassBreakage(Long riskOfGlassBreakage) {
	        this.riskOfGlassBreakage = riskOfGlassBreakage;
	    }

	    /**
	     * 自燃损失险
	     * @return self_ignition_loss_insurance 自燃损失险
	     */
	    public Long getSelfIgnitionLossInsurance() {
	        return selfIgnitionLossInsurance;
	    }

	    /**
	     * 自燃损失险
	     * @param selfIgnitionLossInsurance 自燃损失险
	     */
	    public void setSelfIgnitionLossInsurance(Long selfIgnitionLossInsurance) {
	        this.selfIgnitionLossInsurance = selfIgnitionLossInsurance;
	    }

	    /**
	     * 不计免赔特约险
	     * @return exemption_from_special_contract 不计免赔特约险
	     */
	    public Long getExemptionFromSpecialContract() {
	        return exemptionFromSpecialContract;
	    }

	    /**
	     * 不计免赔特约险
	     * @param exemptionFromSpecialContract 不计免赔特约险
	     */
	    public void setExemptionFromSpecialContract(Long exemptionFromSpecialContract) {
	        this.exemptionFromSpecialContract = exemptionFromSpecialContract;
	    }

	    /**
	     * 无过责任险
	     * @return no_liability_insurance 无过责任险
	     */
	    public Long getNoLiabilityInsurance() {
	        return noLiabilityInsurance;
	    }

	    /**
	     * 无过责任险
	     * @param noLiabilityInsurance 无过责任险
	     */
	    public void setNoLiabilityInsurance(Long noLiabilityInsurance) {
	        this.noLiabilityInsurance = noLiabilityInsurance;
	    }

	    /**
	     * 车上人员责任险
	     * @return personnel_liability_insurance 车上人员责任险
	     */
	    public Long getPersonnelLiabilityInsurance() {
	        return personnelLiabilityInsurance;
	    }

	    /**
	     * 车上人员责任险
	     * @param personnelLiabilityInsurance 车上人员责任险
	     */
	    public void setPersonnelLiabilityInsurance(Long personnelLiabilityInsurance) {
	        this.personnelLiabilityInsurance = personnelLiabilityInsurance;
	    }

	    /**
	     * 车上划痕险
	     * @return scratch_risk 车上划痕险
	     */
	    public Long getScratchRisk() {
	        return scratchRisk;
	    }

	    /**
	     * 车上划痕险
	     * @param scratchRisk 车上划痕险
	     */
	    public void setScratchRisk(Long scratchRisk) {
	        this.scratchRisk = scratchRisk;
	    }

		public Integer getCustomerOrderId() {
			return customerOrderId;
		}

		public void setCustomerOrderId(Integer customerOrderId) {
			this.customerOrderId = customerOrderId;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public String getFrameNumber() {
			return frameNumber;
		}

		public void setFrameNumber(String frameNumber) {
			this.frameNumber = frameNumber;
		}

		public String getImages() {
			return images;
		}

		public void setImages(String images) {
			this.images = images;
		}
}
