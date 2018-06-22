package main.com.weixinHtml.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
//@JsonInclude(Include.ALWAYS)
public class ShopFindCar implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Integer findTheCarId;

    /**
     * 车大类
     */
    private Integer carsId;

    /**
     * 车大类名称
     */
    private String carsName;

    /**
     * 指导价
     */
    private BigDecimal guidancePrice;

    /**
     * 
     */
    private Integer colourId;

    /**
     * 
     */
    private String colourName;

    /**
     * 
     */
    private Integer interiorId;

    /**
     * 内饰
     */
    private String interiorName;

    /**
     * 期待价格
     */
    private BigDecimal expectationAmount;

    /**
     * 上牌城市
     */
    private String signCity;

    /**
     * 期待提车时间
     */
    private Date expectationHaveingCarTime;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 申请用户ID
     */
    private Integer shopUserId;

    /**
     * 联系人姓名
     */
    private String linkmanName;

    /**
     * 联系电话
     */
    private String linkmanPhone;

    /**
     * 0新建 1已处理
     */
    private Integer state;

    /**
     * 是否已删除
     */
    private Boolean overDel;

    /**
     * 创建时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-ddHH:mm:ss")
    private Date createDate;

    /**
     * 处理人ID
     */
    private Integer systemUserId;

    /**
     * 处理人
     */
    private String systemUserName;
    
    /**
     * 封面图
     */
    private String image;

    /**
     * 
     * @return find_the_car_id 
     */
    public Integer getFindTheCarId() {
        return findTheCarId;
    }

    /**
     * 
     * @param findTheCarId 
     */
    public void setFindTheCarId(Integer findTheCarId) {
        this.findTheCarId = findTheCarId;
    }

    /**
     * 车大类
     * @return cars_id 车大类
     */
    public Integer getCarsId() {
        return carsId;
    }

    /**
     * 车大类
     * @param carsId 车大类
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
     * 指导价
     * @return guidance_price 指导价
     */
    public BigDecimal getGuidancePrice() {
        return guidancePrice;
    }

    /**
     * 指导价
     * @param guidancePrice 指导价
     */
    public void setGuidancePrice(BigDecimal guidancePrice) {
        this.guidancePrice = guidancePrice;
    }

    /**
     * 
     * @return colour_id 
     */
    public Integer getColourId() {
        return colourId;
    }

    /**
     * 
     * @param colourId 
     */
    public void setColourId(Integer colourId) {
        this.colourId = colourId;
    }

    /**
     * 
     * @return colour_name 
     */
    public String getColourName() {
        return colourName;
    }

    /**
     * 
     * @param colourName 
     */
    public void setColourName(String colourName) {
        this.colourName = colourName;
    }

    /**
     * 
     * @return interior_id 
     */
    public Integer getInteriorId() {
        return interiorId;
    }

    /**
     * 
     * @param interiorId 
     */
    public void setInteriorId(Integer interiorId) {
        this.interiorId = interiorId;
    }

    /**
     * 内饰
     * @return interior_name 内饰
     */
    public String getInteriorName() {
        return interiorName;
    }

    /**
     * 内饰
     * @param interiorName 内饰
     */
    public void setInteriorName(String interiorName) {
        this.interiorName = interiorName;
    }

    /**
     * 期待价格
     * @return expectation_amount 期待价格
     */
    public BigDecimal getExpectationAmount() {
        return expectationAmount;
    }

    /**
     * 期待价格
     * @param expectationAmount 期待价格
     */
    public void setExpectationAmount(BigDecimal expectationAmount) {
        this.expectationAmount = expectationAmount;
    }

    /**
     * 上牌城市
     * @return sign_city 上牌城市
     */
    public String getSignCity() {
        return signCity;
    }

    /**
     * 上牌城市
     * @param signCity 上牌城市
     */
    public void setSignCity(String signCity) {
        this.signCity = signCity;
    }

    /**
     * 期待提车时间
     * @return expectation_haveing_car_time 期待提车时间
     */
//    @DateTimeFormat(pattern="yyyy-MM-ddHH:mm:ss") 
//    @JsonFormat(pattern="YYYY-MM-DD HH:mm:SS")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getExpectationHaveingCarTime() {
        return expectationHaveingCarTime;
    }

    /**
     * 期待提车时间
     * @param expectationHaveingCarTime 期待提车时间
     */
    public void setExpectationHaveingCarTime(Date expectationHaveingCarTime) {
        this.expectationHaveingCarTime = expectationHaveingCarTime;
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
     * 申请用户ID
     * @return shop_user_id 申请用户ID
     */
    public Integer getShopUserId() {
        return shopUserId;
    }

    /**
     * 申请用户ID
     * @param shopUserId 申请用户ID
     */
    public void setShopUserId(Integer shopUserId) {
        this.shopUserId = shopUserId;
    }

    /**
     * 联系人姓名
     * @return linkman_name 联系人姓名
     */
    public String getLinkmanName() {
        return linkmanName;
    }

    /**
     * 联系人姓名
     * @param linkmanName 联系人姓名
     */
    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    /**
     * 联系电话
     * @return linkman_phone 联系电话
     */
    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    /**
     * 联系电话
     * @param linkmanPhone 联系电话
     */
    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    /**
     * 0新建 1已处理
     * @return state 0新建 1已处理
     */
    public Integer getState() {
        return state;
    }

    /**
     * 0新建 1已处理
     * @param state 0新建 1已处理
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 是否已删除
     * @return over_del 是否已删除
     */
    public Boolean getOverDel() {
        return overDel;
    }

    /**
     * 是否已删除
     * @param overDel 是否已删除
     */
    public void setOverDel(Boolean overDel) {
        this.overDel = overDel;
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    
//    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
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
     * 处理人ID
     * @return system_user_id 处理人ID
     */
    public Integer getSystemUserId() {
        return systemUserId;
    }

    /**
     * 处理人ID
     * @param systemUserId 处理人ID
     */
    public void setSystemUserId(Integer systemUserId) {
        this.systemUserId = systemUserId;
    }

    /**
     * 处理人
     * @return system_user_name 处理人
     */
    public String getSystemUserName() {
        return systemUserName;
    }

    /**
     * 处理人
     * @param systemUserName 处理人
     */
    public void setSystemUserName(String systemUserName) {
        this.systemUserName = systemUserName;
    }
    

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}