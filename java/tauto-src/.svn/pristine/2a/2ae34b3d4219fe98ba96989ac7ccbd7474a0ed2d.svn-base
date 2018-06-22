package main.com.weixinHtml.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
public class ShopFindCarOffer implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Integer findCarOfferId;

    /**
     * 报价时间
     */
    private Date createDate;

    /**
     * 过期时间
     */
    private Date overdueDate;

    /**
     * 
     */
    private BigDecimal offerAmount;

    /**
     * 报价用户（商务端）
     */
    private Integer systemUserId;

    /**
     * 名称
     */
    private String systemUserName;

    /**
     * 
     */
    private String systemUserPhone;

    /**
     * 门店电话
     */
    private Integer orgId;

    /**
     * 门店名称
     */
    private String orgName;

    /**
     * 0初始 1已过期
     */
    private Integer offerState;
    
    /**
     * 寻车申请ID
     */
    private Integer findCarId;
    
    /**
     * 车辆所在地
     */
    private String location;

    /**
     * 
     * @return find_car_offer_id 
     */
    public Integer getFindCarOfferId() {
        return findCarOfferId;
    }

    /**
     * 
     * @param findCarOfferId 
     */
    public void setFindCarOfferId(Integer findCarOfferId) {
        this.findCarOfferId = findCarOfferId;
    }

    /**
     * 报价时间
     * @return create_date 报价时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 报价时间
     * @param createDate 报价时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 过期时间
     * @return overdue_date 过期时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getOverdueDate() {
        return overdueDate;
    }

    /**
     * 过期时间
     * @param overdueDate 过期时间
     */
    public void setOverdueDate(Date overdueDate) {
        this.overdueDate = overdueDate;
    }

    /**
     * 
     * @return offer_amount 
     */
    public BigDecimal getOfferAmount() {
        return offerAmount;
    }

    /**
     * 
     * @param offerAmount 
     */
    public void setOfferAmount(BigDecimal offerAmount) {
        this.offerAmount = offerAmount;
    }

    /**
     * 报价用户（商务端）
     * @return system_user_id 报价用户（商务端）
     */
    public Integer getSystemUserId() {
        return systemUserId;
    }

    /**
     * 报价用户（商务端）
     * @param systemUserId 报价用户（商务端）
     */
    public void setSystemUserId(Integer systemUserId) {
        this.systemUserId = systemUserId;
    }

    /**
     * 名称
     * @return system_user_name 名称
     */
    public String getSystemUserName() {
        return systemUserName;
    }

    /**
     * 名称
     * @param systemUserName 名称
     */
    public void setSystemUserName(String systemUserName) {
        this.systemUserName = systemUserName;
    }

    /**
     * 
     * @return system_user_phone 
     */
    public String getSystemUserPhone() {
        return systemUserPhone;
    }

    /**
     * 
     * @param systemUserPhone 
     */
    public void setSystemUserPhone(String systemUserPhone) {
        this.systemUserPhone = systemUserPhone;
    }

    /**
     * 门店电话
     * @return org_id 门店电话
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 门店电话
     * @param orgId 门店电话
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 门店名称
     * @return org_name 门店名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 门店名称
     * @param orgName 门店名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 0初始 1已过期
     * @return offer_state 0初始 1已过期
     */
    public Integer getOfferState() {
        return offerState;
    }

    /**
     * 0初始 1已过期
     * @param offerState 0初始 1已过期
     */
    public void setOfferState(Integer offerState) {
        this.offerState = offerState;
    }

	public Integer getFindCarId() {
		return findCarId;
	}

	public void setFindCarId(Integer findCarId) {
		this.findCarId = findCarId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}