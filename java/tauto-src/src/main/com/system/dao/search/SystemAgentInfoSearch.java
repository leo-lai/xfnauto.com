package main.com.system.dao.search;

import main.com.frame.search.BaseSearch;

public class SystemAgentInfoSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sessionId;
	private Boolean isPartner;//是否是合伙人列表
	private String phoneNumber;
	private String startDate;
	private String finishDate;
	private String agentInfoName;
	private String area;
	private Integer agentInfoId;
	
	private Integer agentInfoAreasId;
	private String provinceId;
	private String cityId;
	private String areaId;
	
	private Integer bankId;//	varchar 银行账户		
	private String bankAccountNumber;//	varchar 银行账户		
	private String accountHolder;//	varchar 开户人			
	private String bankAccount;	//	varchar 开户银行		
	private String bankBranch;	 //	varchar 银行支行	
	
	private String agentTypeCode;
	
	
	private Integer agentGoodsId;
	private Integer colorTypeId;
	private Integer expressId;
	private String userName;
	private String address; //收货地址
	private String remark; //收货地址
	private Integer goodsNumber;//提货数量
	private String deliveryGoodsInfo;//提货信息
	private String deliveryCode;//提货数量
	private Boolean isRebates;//是否扣除货款
	
	private Boolean isPass;
	private Integer agentState;
	private String areaIds;
	private Integer agentId;
//	province
//	city
//	area
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getIsPartner() {
		return isPartner;
	}

	public void setIsPartner(Boolean isPartner) {
		if(isPartner == null){
			isPartner = false;
		}
		this.isPartner = isPartner;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getAgentInfoName() {
		return agentInfoName;
	}

	public void setAgentInfoName(String agentInfoName) {
		this.agentInfoName = agentInfoName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getAgentInfoId() {
		return agentInfoId;
	}

	public void setAgentInfoId(Integer agentInfoId) {
		this.agentInfoId = agentInfoId;
	}

	public Integer getAgentInfoAreasId() {
		return agentInfoAreasId;
	}

	public void setAgentInfoAreasId(Integer agentInfoAreasId) {
		this.agentInfoAreasId = agentInfoAreasId;
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

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getAgentTypeCode() {
		return agentTypeCode;
	}

	public void setAgentTypeCode(String agentTypeCode) {
		this.agentTypeCode = agentTypeCode;
	}

	public Integer getAgentGoodsId() {
		return agentGoodsId;
	}

	public void setAgentGoodsId(Integer agentGoodsId) {
		this.agentGoodsId = agentGoodsId;
	}

	public Integer getColorTypeId() {
		return colorTypeId;
	}

	public void setColorTypeId(Integer colorTypeId) {
		this.colorTypeId = colorTypeId;
	}

	public Integer getExpressId() {
		return expressId;
	}

	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public String getDeliveryCode() {
		return deliveryCode;
	}

	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}

	public Boolean getIsPass() {
		return isPass;
	}

	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}

	public Integer getAgentState() {
		return agentState;
	}

	public void setAgentState(Integer agentState) {
		this.agentState = agentState;
	}

	public String getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}

	public String getDeliveryGoodsInfo() {
		return deliveryGoodsInfo;
	}

	public void setDeliveryGoodsInfo(String deliveryGoodsInfo) {
		this.deliveryGoodsInfo = deliveryGoodsInfo;
	}

	public Boolean getIsRebates() {
		return isRebates;
	}

	public void setIsRebates(Boolean isRebates) {
		this.isRebates = isRebates;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
}
