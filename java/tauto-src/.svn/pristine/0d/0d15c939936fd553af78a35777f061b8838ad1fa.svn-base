package main.com.system.dao.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 三级组织架构
 * @author Zwen
 *
 */
@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
public class Organization implements Serializable {

	private static final long serialVersionUID = -6011989667742661206L;
	
	private Integer orgId;   //主键
	private Integer parentId;         //上级组织
	private Integer seq;              //排序序列
	private String orgCode;             //编码
	private String orgCodeLevel;             //编码（所有上级加下级）
	private String introduce;       //组织简介
	private String shortName;       //组织简称
	private String fullName;       //组织全称
	private String provinceId;       //省份
	private String cityId;           //城市
	private String areaId;           //地区
	private String provinceName;       //省份
	private String cityName;           //城市
	private String areaName;           //地区
	private String address;         //地址
	private String zipCode;         //邮编
	private String linkMan;         //联系人
	private String telePhone;        //联系电话
	private Integer orgType;         //组织类型 1加盟 2联盟 3其他
	private Integer orgLevel;         //等级
	private String orgLink;           //等级链路
	private Double longitude;        //经度
	private Double latitude;          //维度
	private String remark;            //备注
	private Integer status;           //状态
	private String imageUrl;          //图片
	private String imgIntroduce;      //图片介绍
	
	private String bankAccount;        //银行账号
	private String bankName;        //银行名称
	private String openingBranch;      //开户支行
	private String nameOfAccount;        //账户名称
	private String theSource;        //来源
	private String idCardPicOn;        //身份证正面
	private String idCardPicOff;        //身份证反面
	private Integer natureType;        //性质 14S店，2资源公司，3汽贸公司
	private String businessLicense;          //图片
	private String servicePhone;          //客服电话
	private String serviceName;          //客服名称
	private Date createDate;
	
	/**
	 * 通联支付配置
	 */
	private String allInPayAppId;
	private String allInPaySecretKey;
	private String allInPayMerchant;
	private Boolean overAllInPay;
	
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getTelePhone() {
		return telePhone;
	}
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	public Integer getOrgType() {
		return orgType;
	}
	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImgIntroduce() {
		return imgIntroduce;
	}
	public void setImgIntroduce(String imgIntroduce) {
		this.imgIntroduce = imgIntroduce;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}
	public String getOrgLink() {
		return orgLink;
	}
	public void setOrgLink(String orgLink) {
		this.orgLink = orgLink;
	}
	public String getOrgCodeLevel() {
		return orgCodeLevel;
	}
	public void setOrgCodeLevel(String orgCodeLevel) {
		this.orgCodeLevel = orgCodeLevel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getOpeningBranch() {
		return openingBranch;
	}
	public void setOpeningBranch(String openingBranch) {
		this.openingBranch = openingBranch;
	}
	public String getNameOfAccount() {
		return nameOfAccount;
	}
	public void setNameOfAccount(String nameOfAccount) {
		this.nameOfAccount = nameOfAccount;
	}
	public String getTheSource() {
		return theSource;
	}
	public void setTheSource(String theSource) {
		this.theSource = theSource;
	}
	public String getIdCardPicOn() {
		return idCardPicOn;
	}
	public void setIdCardPicOn(String idCardPicOn) {
		this.idCardPicOn = idCardPicOn;
	}
	public String getIdCardPicOff() {
		return idCardPicOff;
	}
	public void setIdCardPicOff(String idCardPicOff) {
		this.idCardPicOff = idCardPicOff;
	}
	public Integer getNatureType() {
		return natureType;
	}
	public void setNatureType(Integer natureType) {
		this.natureType = natureType;
	}
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getServicePhone() {
		return servicePhone;
	}
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
     * 创建时间
     * @return create_time 创建时间
     */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAllInPayAppId() {
		return allInPayAppId;
	}
	public void setAllInPayAppId(String allInPayAppId) {
		this.allInPayAppId = allInPayAppId;
	}
	public String getAllInPaySecretKey() {
		return allInPaySecretKey;
	}
	public void setAllInPaySecretKey(String allInPaySecretKey) {
		this.allInPaySecretKey = allInPaySecretKey;
	}
	public String getAllInPayMerchant() {
		return allInPayMerchant;
	}
	public void setAllInPayMerchant(String allInPayMerchant) {
		this.allInPayMerchant = allInPayMerchant;
	}
	public Boolean getOverAllInPay() {
		return overAllInPay;
	}
	public void setOverAllInPay(Boolean overAllInPay) {
		this.overAllInPay = overAllInPay;
	}
}
