package main.com.logistics.dao.search;

import java.math.BigDecimal;

import main.com.frame.search.BaseSearch;

public class LogisticsGoodsCarCostsSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 /**
     * 主键
     */
    private Integer logisticsGoodsCarCostsId;

    /**
     * 溢出价格
     */
    private Double overflow;

    /**
     * 车价附加费
     */
    private Double thePriceAdditional;

    /**
     * 优惠
     */
    private Double discount;

    /**
     * 
     */
    private Double priceMarkup;

    /**
     * 
     */
    private Integer goodsCarId;

    /**
     * 总价
     */
    private Double costsAmount;

    /**
     * 物流类型 1点对点 2专线
     */
    private Integer lineType;

    /**
     * 专线费用
     */
    private Double lineAmount;

    /**
     * 起步价
     */
    private Double initiateRate;

	public Integer getLogisticsGoodsCarCostsId() {
		return logisticsGoodsCarCostsId;
	}

	public void setLogisticsGoodsCarCostsId(Integer logisticsGoodsCarCostsId) {
		this.logisticsGoodsCarCostsId = logisticsGoodsCarCostsId;
	}

	public Double getOverflow() {
		return overflow;
	}

	public void setOverflow(Double overflow) {
		this.overflow = overflow;
	}

	public Double getThePriceAdditional() {
		return thePriceAdditional;
	}

	public void setThePriceAdditional(Double thePriceAdditional) {
		this.thePriceAdditional = thePriceAdditional;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getPriceMarkup() {
		return priceMarkup;
	}

	public void setPriceMarkup(Double priceMarkup) {
		this.priceMarkup = priceMarkup;
	}

	public Integer getGoodsCarId() {
		return goodsCarId;
	}

	public void setGoodsCarId(Integer goodsCarId) {
		this.goodsCarId = goodsCarId;
	}

	public Double getCostsAmount() {
		return costsAmount;
	}

	public void setCostsAmount(Double costsAmount) {
		this.costsAmount = costsAmount;
	}

	public Integer getLineType() {
		return lineType;
	}

	public void setLineType(Integer lineType) {
		this.lineType = lineType;
	}

	public Double getLineAmount() {
		return lineAmount;
	}

	public void setLineAmount(Double lineAmount) {
		this.lineAmount = lineAmount;
	}

	public Double getInitiateRate() {
		return initiateRate;
	}

	public void setInitiateRate(Double initiateRate) {
		this.initiateRate = initiateRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
