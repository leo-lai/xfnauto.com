package main.com.logistics.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class LogisticsDynamicLineInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer dynamicLineInfoId;

    /**
     * 上级表ID
     */
    private Integer dynamicLineId;

    /**
     * 
     */
    private BigDecimal amount;

    /**
     * 开始里程
     */
    private Double minMileage;

    /**
     * 结束里程
     */
    private Double maxMileage;

    /**
     * 主键
     * @return dynamic_line_info_id 主键
     */
    public Integer getDynamicLineInfoId() {
        return dynamicLineInfoId;
    }

    /**
     * 主键
     * @param dynamicLineInfoId 主键
     */
    public void setDynamicLineInfoId(Integer dynamicLineInfoId) {
        this.dynamicLineInfoId = dynamicLineInfoId;
    }

    /**
     * 上级表ID
     * @return dynamic_line_id 上级表ID
     */
    public Integer getDynamicLineId() {
        return dynamicLineId;
    }

    /**
     * 上级表ID
     * @param dynamicLineId 上级表ID
     */
    public void setDynamicLineId(Integer dynamicLineId) {
        this.dynamicLineId = dynamicLineId;
    }

    /**
     * 
     * @return amount 
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount 
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 开始里程
     * @return min_mileage 开始里程
     */
    public Double getMinMileage() {
        return minMileage;
    }

    /**
     * 开始里程
     * @param minMileage 开始里程
     */
    public void setMinMileage(Double minMileage) {
        this.minMileage = minMileage;
    }

    /**
     * 结束里程
     * @return max_mileage 结束里程
     */
    public Double getMaxMileage() {
        return maxMileage;
    }

    /**
     * 结束里程
     * @param maxMileage 结束里程
     */
    public void setMaxMileage(Double maxMileage) {
        this.maxMileage = maxMileage;
    }
}