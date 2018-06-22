package main.com.stock.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StockOrder implements Serializable{
    /**
     * 主键
     */
    private Integer stockOrderId;

    /**
     * 下订单的门店
     */
    private Integer stockOrderBuyOrgId;

    /**
     * 下订单的门店
     */
    private String stockOrderBuyOrgName;

    /**
     * 卖方orgID
     */
    private Integer stockOrderSellOrgId;

    /**
     * 卖方名称
     */
    private String stockOrderSellOrgName;

    /**
     * 库存车辆
     */
    private String stockCarIds;

    /**
     * 订单状态
     */
    private Integer stockOrderState;

    /**
     * 订单实际支付金额
     */
    private Long stockOrderAmount;
    /**
     * 订单实际总额
     */
    private Long stockOrderTotalAmount;

    /**
     * 采购数量
     */
    private Integer stockOrderNumber;

    /**
     * 车大类ID
     */
    private Integer carsId;

    /**
     * 车大类基本信息
     */
    private String carsInfo;

    /**
     * 发票金额
     */
    private BigDecimal invoicePrice;
    /**
     * 定金
     */
    private BigDecimal depositPrice;
    /**
     * 余额（尾款）  stockOrderAmount = depositPrice + balancePrice
     * 			 stockOrderAmount = guidingPrice - discountPrice
     */
    private BigDecimal balancePrice;
    /**
     * 优惠金额
     */
    private BigDecimal discountPrice;

    /**
     * 指导价
     */
    private BigDecimal guidingPrice;

    /**
     * 是否已删除
     */
    private Boolean isDelete;

    /**
     * 多张以英文逗号隔开
     */
    private String stockCarImages;

    /**
     * 备注
     */
    private String stockOrderRemarks;

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
     * 物流单号
     */
    private String logisticsOddNumber;

    /**
     * 随车清单
     */
    private Integer followListId;

    /**
     * 定金支付方式
     */
    private Integer payMethodBefor;

    /**
     * 定金结算流水ID
     */
    private Integer statementIdBefor;

    /**
     * 结算流水号
     */
    private String statementCodeBefor;

    /**
     * 尾款流水ID
     */
    private Integer statementIdAfter;

    /**
     * 尾款单号code
     */
    private String statementCodeAfter;

    /**
     * 支付定金日期
     */
    private Date payDateBefor;

    /**
     * 支付尾款日期
     */
    private Date payDateAfter;

    /**
     * 尾款支付方法
     */
    private Integer payMethodAfter;

    /**
     * 是否已取消
     */
    private Boolean isCancel;

    /**
     * 物流方式
     */
    private Integer logisticsMode;
    /**
     *  随车资料，以都英文逗号隔开
     */
    private String followInformation;
    /**
     * 订单号
     */
    private String stockOrderCode;
    
    /**
     * 订车样板图
     */
    private String templateImage;

    
    private Date createDate;
    private String stockOrderBuyOrgAddress;//买方公司地址
    private Integer certificateDate;
    /**
     * 
     * @return stock_order_id 
     */
    public Integer getStockOrderId() {
        return stockOrderId;
    }

    /**
     * 
     * @param stockOrderId 
     */
    public void setStockOrderId(Integer stockOrderId) {
        this.stockOrderId = stockOrderId;
    }

    /**
     * 下订单的门店
     * @return stock_order_buy_org_id 下订单的门店
     */
    public Integer getStockOrderBuyOrgId() {
        return stockOrderBuyOrgId;
    }

    /**
     * 下订单的门店
     * @param stockOrderBuyOrgId 下订单的门店
     */
    public void setStockOrderBuyOrgId(Integer stockOrderBuyOrgId) {
        this.stockOrderBuyOrgId = stockOrderBuyOrgId;
    }

    /**
     * 下订单的门店
     * @return stock_order_buy_org_name 下订单的门店
     */
    public String getStockOrderBuyOrgName() {
        return stockOrderBuyOrgName;
    }

    /**
     * 下订单的门店
     * @param stockOrderBuyOrgName 下订单的门店
     */
    public void setStockOrderBuyOrgName(String stockOrderBuyOrgName) {
        this.stockOrderBuyOrgName = stockOrderBuyOrgName;
    }

    /**
     * 卖方门店
     * @return stock_order_sell_org_id 卖方门店
     */
    public Integer getStockOrderSellOrgId() {
        return stockOrderSellOrgId;
    }

    /**
     * 卖方门店
     * @param stockOrderSellOrgId 卖方门店
     */
    public void setStockOrderSellOrgId(Integer stockOrderSellOrgId) {
        this.stockOrderSellOrgId = stockOrderSellOrgId;
    }

    /**
     * 
     * @return stock_order_sell_org_name 
     */
    public String getStockOrderSellOrgName() {
        return stockOrderSellOrgName;
    }

    /**
     * 
     * @param stockOrderSellOrgName 
     */
    public void setStockOrderSellOrgName(String stockOrderSellOrgName) {
        this.stockOrderSellOrgName = stockOrderSellOrgName;
    }

    /**
     * 库存车辆
     * @return stock_car_id 库存车辆
     */
    public String getStockCarIds() {
        return stockCarIds;
    }

    /**
     * 库存车辆
     * @param stockCarId 库存车辆
     */
    public void setStockCarIds(String stockCarIds) {
        this.stockCarIds = stockCarIds;
    }

    /**
     * 订单状态
     * @return stock_order_state 订单状态
     */
    public Integer getStockOrderState() {
        return stockOrderState;
    }

    /**
     * 订单状态
     * @param stockOrderState 订单状态
     */
    public void setStockOrderState(Integer stockOrderState) {
        this.stockOrderState = stockOrderState;
    }

    /**
     * 订单金额
     * @return stock_order_amount 订单金额
     */
    public Long getStockOrderAmount() {
        return stockOrderAmount;
    }

    /**
     * 订单金额
     * @param stockOrderAmount 订单金额
     */
    public void setStockOrderAmount(Long stockOrderAmount) {
        this.stockOrderAmount = stockOrderAmount;
    }

    /**
     * 
     * @return stock_order_total_amount 
     */
    public Long getStockOrderTotalAmount() {
        return stockOrderTotalAmount;
    }

    /**
     * 
     * @param stockOrderTotalAmount 
     */
    public void setStockOrderTotalAmount(Long stockOrderTotalAmount) {
        this.stockOrderTotalAmount = stockOrderTotalAmount;
    }

    /**
     * 采购数量
     * @return stock_order_number 采购数量
     */
    public Integer getStockOrderNumber() {
        return stockOrderNumber;
    }

    /**
     * 采购数量
     * @param stockOrderNumber 采购数量
     */
    public void setStockOrderNumber(Integer stockOrderNumber) {
        this.stockOrderNumber = stockOrderNumber;
    }

    /**
     * 
     * @return cars_id 
     */
    public Integer getCarsId() {
        return carsId;
    }

    /**
     * 
     * @param carsId 
     */
    public void setCarsId(Integer carsId) {
        this.carsId = carsId;
    }

    /**
     * 
     * @return cars_info 
     */
    public String getCarsInfo() {
        return carsInfo;
    }

    /**
     * 
     * @param carsInfo 
     */
    public void setCarsInfo(String carsInfo) {
        this.carsInfo = carsInfo;
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
     * 优惠金额
     * @return discount_price 优惠金额
     */
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    /**
     * 优惠金额
     * @param discountPrice 优惠金额
     */
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    /**
     * 指导价
     * @return guiding_price 指导价
     */
    public BigDecimal getGuidingPrice() {
        return guidingPrice;
    }

    /**
     * 指导价
     * @param guidingPrice 指导价
     */
    public void setGuidingPrice(BigDecimal guidingPrice) {
        this.guidingPrice = guidingPrice;
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
     * 多张以英文逗号隔开
     * @return stock_car_images 多张以英文逗号隔开
     */
    public String getStockCarImages() {
        return stockCarImages;
    }

    /**
     * 多张以英文逗号隔开
     * @param stockCarImages 多张以英文逗号隔开
     */
    public void setStockCarImages(String stockCarImages) {
        this.stockCarImages = stockCarImages;
    }

    /**
     * 备注
     * @return stock_order_remarks 备注
     */
    public String getStockOrderRemarks() {
        return stockOrderRemarks;
    }

    /**
     * 备注
     * @param stockOrderRemarks 备注
     */
    public void setStockOrderRemarks(String stockOrderRemarks) {
        this.stockOrderRemarks = stockOrderRemarks;
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
     * 物流单号
     * @return logistics_odd_number 物流单号
     */
    public String getLogisticsOddNumber() {
        return logisticsOddNumber;
    }

    /**
     * 物流单号
     * @param logisticsOddNumber 物流单号
     */
    public void setLogisticsOddNumber(String logisticsOddNumber) {
        this.logisticsOddNumber = logisticsOddNumber;
    }

    /**
     * 随车清单
     * @return follow_list_id 随车清单
     */
    public Integer getFollowListId() {
        return followListId;
    }

    /**
     * 随车清单
     * @param followListId 随车清单
     */
    public void setFollowListId(Integer followListId) {
        this.followListId = followListId;
    }

    /**
     * 支付方式
     * @return pay_method_befor 支付方式
     */
    public Integer getPayMethodBefor() {
        return payMethodBefor;
    }

    /**
     * 支付方式
     * @param payMethodBefor 支付方式
     */
    public void setPayMethodBefor(Integer payMethodBefor) {
        this.payMethodBefor = payMethodBefor;
    }

    /**
     * 
     * @return statement_id_befor 
     */
    public Integer getStatementIdBefor() {
        return statementIdBefor;
    }

    /**
     * 
     * @param statementIdBefor 
     */
    public void setStatementIdBefor(Integer statementIdBefor) {
        this.statementIdBefor = statementIdBefor;
    }

    /**
     * 结算流水号
     * @return statement_code_befor 结算流水号
     */
    public String getStatementCodeBefor() {
        return statementCodeBefor;
    }

    /**
     * 结算流水号
     * @param statementCodeBefor 结算流水号
     */
    public void setStatementCodeBefor(String statementCodeBefor) {
        this.statementCodeBefor = statementCodeBefor;
    }

    /**
     * 尾款流水
     * @return statement_id_after 尾款流水
     */
    public Integer getStatementIdAfter() {
        return statementIdAfter;
    }

    /**
     * 尾款流水
     * @param statementIdAfter 尾款流水
     */
    public void setStatementIdAfter(Integer statementIdAfter) {
        this.statementIdAfter = statementIdAfter;
    }

    /**
     * 
     * @return statement_code_after 
     */
    public String getStatementCodeAfter() {
        return statementCodeAfter;
    }

    /**
     * 
     * @param statementCodeAfter 
     */
    public void setStatementCodeAfter(String statementCodeAfter) {
        this.statementCodeAfter = statementCodeAfter;
    }

    /**
     * 
     * @return pay_date_befor 
     */
    public Date getPayDateBefor() {
        return payDateBefor;
    }

    /**
     * 
     * @param payDateBefor 
     */
    public void setPayDateBefor(Date payDateBefor) {
        this.payDateBefor = payDateBefor;
    }

    /**
     * 
     * @return pay_date_after 
     */
    public Date getPayDateAfter() {
        return payDateAfter;
    }

    /**
     * 
     * @param payDateAfter 
     */
    public void setPayDateAfter(Date payDateAfter) {
        this.payDateAfter = payDateAfter;
    }

    /**
     * 
     * @return pay_method_after 
     */
    public Integer getPayMethodAfter() {
        return payMethodAfter;
    }

    /**
     * 
     * @param payMethodAfter 
     */
    public void setPayMethodAfter(Integer payMethodAfter) {
        this.payMethodAfter = payMethodAfter;
    }

    /**
     * 是否已取消
     * @return is_cancel 是否已取消
     */
    public Boolean getIsCancel() {
        return isCancel;
    }

    /**
     * 是否已取消
     * @param isCancel 是否已取消
     */
    public void setIsCancel(Boolean isCancel) {
        this.isCancel = isCancel;
    }

    /**
     * 订单号
     * @return stock_order_code 订单号
     */
    public String getStockOrderCode() {
        return stockOrderCode;
    }

    /**
     * 订单号
     * @param stockOrderCode 订单号
     */
    public void setStockOrderCode(String stockOrderCode) {
        this.stockOrderCode = stockOrderCode;
    }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStockOrderBuyOrgAddress() {
		return stockOrderBuyOrgAddress;
	}

	public void setStockOrderBuyOrgAddress(String stockOrderBuyOrgAddress) {
		this.stockOrderBuyOrgAddress = stockOrderBuyOrgAddress;
	}

	public Integer getCertificateDate() {
		return certificateDate;
	}

	public void setCertificateDate(Integer certificateDate) {
		this.certificateDate = certificateDate;
	}

	public BigDecimal getBalancePrice() {
		return balancePrice;
	}

	public void setBalancePrice(BigDecimal balancePrice) {
		this.balancePrice = balancePrice;
	}

	public Integer getLogisticsMode() {
		return logisticsMode;
	}

	public void setLogisticsMode(Integer logisticsMode) {
		this.logisticsMode = logisticsMode;
	}

	public String getFollowInformation() {
		return followInformation;
	}

	public void setFollowInformation(String followInformation) {
		this.followInformation = followInformation;
	}

	public String getTemplateImage() {
		return templateImage;
	}

	public void setTemplateImage(String templateImage) {
		this.templateImage = templateImage;
	}
}