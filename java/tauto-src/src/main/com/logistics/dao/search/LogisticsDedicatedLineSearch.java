package main.com.logistics.dao.search;

import java.util.Date;

import main.com.frame.search.BaseSearch;

public class LogisticsDedicatedLineSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 主键
     */
    private Integer dedicatedLineId;

    /**
     * 专线名称
     */
    private String dedicatedLineName;

    /**
     * 起点
     */
    private String startingPointAddress;

    /**
     * 终点
     */
    private String destinationAddress;

    /**
     * 出发时间
     */
    private String departureTime;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 是否已删除
     */
    private String overDelete;

    /**
     * 组织名称
     */
    private Integer orgId;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 起点经度
     */
    private String startingPointLongitude;

    /**
     * 起点纬度
     */
    private String startingPointLatitude;

    /**
     * 起点经度
     */
    private String destinationLongitude;

    /**
     * 起点纬度
     */
    private String destinationLatitude;
    
    private Double additionalAmount;

	public Integer getDedicatedLineId() {
		return dedicatedLineId;
	}

	public void setDedicatedLineId(Integer dedicatedLineId) {
		this.dedicatedLineId = dedicatedLineId;
	}

	public String getDedicatedLineName() {
		return dedicatedLineName;
	}

	public void setDedicatedLineName(String dedicatedLineName) {
		this.dedicatedLineName = dedicatedLineName;
	}

	public String getStartingPointAddress() {
		return startingPointAddress;
	}

	public void setStartingPointAddress(String startingPointAddress) {
		this.startingPointAddress = startingPointAddress;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOverDelete() {
		return overDelete;
	}

	public void setOverDelete(String overDelete) {
		this.overDelete = overDelete;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStartingPointLongitude() {
		return startingPointLongitude;
	}

	public void setStartingPointLongitude(String startingPointLongitude) {
		this.startingPointLongitude = startingPointLongitude;
	}

	public String getStartingPointLatitude() {
		return startingPointLatitude;
	}

	public void setStartingPointLatitude(String startingPointLatitude) {
		this.startingPointLatitude = startingPointLatitude;
	}

	public String getDestinationLongitude() {
		return destinationLongitude;
	}

	public void setDestinationLongitude(String destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}

	public String getDestinationLatitude() {
		return destinationLatitude;
	}

	public void setDestinationLatitude(String destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getAdditionalAmount() {
		return additionalAmount;
	}

	public void setAdditionalAmount(Double additionalAmount) {
		this.additionalAmount = additionalAmount;
	}
}
