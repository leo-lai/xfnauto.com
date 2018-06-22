package main.com.customer.dao.search;

import java.util.Date;

import main.com.frame.search.BaseSearch;

public class CustomerCustomerOrgSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  /**
     * 
     */
    private Integer customerUsersOrgId;

    /**
     * 用户姓名
     */
    private String customerUsersName;

    /**
     * 用户电话
     */
    private String phoneNumber;
    
    /**
     * 用户ID
     */
    private Integer customerUsersId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 组织名称（三级组织名称）
     */
    private Integer orgId;

    /**
     * 组织简称
     */
    private String orgShortName;

    /**
     * 购车意向
     */
    private String carPurchaseIntention;

    /**
     * 是否可编辑
     */
    private Byte isEdit;

    /**
     * 期望购车方式
     */
    private Integer expectWayId;

    /**
     * 意向车型
     */
    private Integer intentionCarId;

    /**
     * 预约时间段
     */
    private String timeOfAppointment;

    /**
     * 备注
     */
    private String remarks;
    /**
     * 备注
     */
    private String remarksContent;

    /**
     * 
     */
    private Integer systemUserId;

    /**
     * 销售顾问姓名
     */
    private String systemUserName;

    /**
     * 销售电话
     */
    private String systemUserPhone;
    /**
     * 是否标记为跟踪
     */
    private Boolean isTrack;
    
    /**
     * 是否标记为不买车
     */
    private Boolean isNotBuy;
    /**
     * 是否标记预约
     */
    private Boolean isBespeak;
    private Boolean buyCarAlready;
    private String orderStates;
    private Integer paymentWay;
    private String customerUsersSearch;
    /**
     * 预约日期
     */
    private Date appointmentDate;
    
	private Integer agentGender;           //性别 1男 2女
	private String headPortrait;       //头像	varchar
	private String sessionId;   //登录唯一标识	varchar
	private Integer state;      //状态	int
	private String publicSignOpenId;     //公众号openid	varchar
	private String publicSignOpendId;        //公众号opendID
	private String customerUserCode;     //用户全局
	private Boolean publicSignIsPush;   //是否接受推送(公众号)
	private Boolean publicSignIsFollow;   //是否已关注(公众号)
	private String unionId;   //是否unionid	
	private String appProgramOpenId;     //小程序openId
	private String annualIncome;      //年收入
	private String cardNo;             //身份证号
	private String incomeSource;        //收入来源
	private Integer maritalStatus;       //婚姻状况
	private String housingSource;         //住房来源
	private Boolean isHasDriversLicense;    //是否有驾驶证
	private String email;        //邮箱
	private String education;           //学历
	private String address;            //家庭住址
	private String emergencyAContact;       //紧急联系人A
	private String emergencyBContact;          //紧急联系人B
	private String emergencyARelationship;        //与用户关系A
	private String emergencyBRelationship;         //与用户关系B
	private String emergencyAPhone;               //联系人A电话号
	private String emergencyBPhone;               //联系人B电话号
	private String workUnit;                    //工作单位
	private String annualIncomeAfterTax;         //税后年收入
	private String workingPlace;                //工作地点
	private String entryTime;                 //工作入职时间
	private String position;                 //职位
	private String companyTelephone;         //公司电话
	private String intentionCarInfo;     //车型信息
	private String remark;
	
	private Integer customerCarId;
	
	private Integer customerType;   //客户类型

	public Integer getCustomerUsersOrgId() {
		return customerUsersOrgId;
	}

	public void setCustomerUsersOrgId(Integer customerUsersOrgId) {
		this.customerUsersOrgId = customerUsersOrgId;
	}

	public String getCustomerUsersName() {
		return customerUsersName;
	}

	public void setCustomerUsersName(String customerUsersName) {
		this.customerUsersName = customerUsersName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgShortName() {
		return orgShortName;
	}

	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}

	public String getCarPurchaseIntention() {
		return carPurchaseIntention;
	}

	public void setCarPurchaseIntention(String carPurchaseIntention) {
		this.carPurchaseIntention = carPurchaseIntention;
	}

	public Byte getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Byte isEdit) {
		this.isEdit = isEdit;
	}

	public Integer getExpectWayId() {
		return expectWayId;
	}

	public void setExpectWayId(Integer expectWayId) {
		this.expectWayId = expectWayId;
	}

	public Integer getIntentionCarId() {
		return intentionCarId;
	}

	public void setIntentionCarId(Integer intentionCarId) {
		this.intentionCarId = intentionCarId;
	}

	public String getTimeOfAppointment() {
		return timeOfAppointment;
	}

	public void setTimeOfAppointment(String timeOfAppointment) {
		this.timeOfAppointment = timeOfAppointment;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getIsTrack() {
		return isTrack;
	}

	public void setIsTrack(Boolean isTrack) {
		this.isTrack = isTrack;
	}

	public Boolean getIsNotBuy() {
		return isNotBuy;
	}

	public void setIsNotBuy(Boolean isNotBuy) {
		this.isNotBuy = isNotBuy;
	}

	public Boolean getIsBespeak() {
		return isBespeak;
	}

	public void setIsBespeak(Boolean isBespeak) {
		this.isBespeak = isBespeak;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Integer getCustomerUsersId() {
		return customerUsersId;
	}

	public void setCustomerUsersId(Integer customerUsersId) {
		this.customerUsersId = customerUsersId;
	}

	public String getRemarksContent() {
		return remarksContent;
	}

	public void setRemarksContent(String remarksContent) {
		this.remarksContent = remarksContent;
	}

	public Integer getAgentGender() {
		return agentGender;
	}

	public void setAgentGender(Integer agentGender) {
		this.agentGender = agentGender;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getPublicSignOpenId() {
		return publicSignOpenId;
	}

	public void setPublicSignOpenId(String publicSignOpenId) {
		this.publicSignOpenId = publicSignOpenId;
	}

	public String getPublicSignOpendId() {
		return publicSignOpendId;
	}

	public void setPublicSignOpendId(String publicSignOpendId) {
		this.publicSignOpendId = publicSignOpendId;
	}

	public String getCustomerUserCode() {
		return customerUserCode;
	}

	public void setCustomerUserCode(String customerUserCode) {
		this.customerUserCode = customerUserCode;
	}

	public Boolean getPublicSignIsPush() {
		return publicSignIsPush;
	}

	public void setPublicSignIsPush(Boolean publicSignIsPush) {
		this.publicSignIsPush = publicSignIsPush;
	}

	public Boolean getPublicSignIsFollow() {
		return publicSignIsFollow;
	}

	public void setPublicSignIsFollow(Boolean publicSignIsFollow) {
		this.publicSignIsFollow = publicSignIsFollow;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getAppProgramOpenId() {
		return appProgramOpenId;
	}

	public void setAppProgramOpenId(String appProgramOpenId) {
		this.appProgramOpenId = appProgramOpenId;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getIncomeSource() {
		return incomeSource;
	}

	public void setIncomeSource(String incomeSource) {
		this.incomeSource = incomeSource;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getHousingSource() {
		return housingSource;
	}

	public void setHousingSource(String housingSource) {
		this.housingSource = housingSource;
	}

	public Boolean getIsHasDriversLicense() {
		return isHasDriversLicense;
	}

	public void setIsHasDriversLicense(Boolean isHasDriversLicense) {
		this.isHasDriversLicense = isHasDriversLicense;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmergencyAContact() {
		return emergencyAContact;
	}

	public void setEmergencyAContact(String emergencyAContact) {
		this.emergencyAContact = emergencyAContact;
	}

	public String getEmergencyBContact() {
		return emergencyBContact;
	}

	public void setEmergencyBContact(String emergencyBContact) {
		this.emergencyBContact = emergencyBContact;
	}

	public String getEmergencyARelationship() {
		return emergencyARelationship;
	}

	public void setEmergencyARelationship(String emergencyARelationship) {
		this.emergencyARelationship = emergencyARelationship;
	}

	public String getEmergencyBRelationship() {
		return emergencyBRelationship;
	}

	public void setEmergencyBRelationship(String emergencyBRelationship) {
		this.emergencyBRelationship = emergencyBRelationship;
	}

	public String getEmergencyAPhone() {
		return emergencyAPhone;
	}

	public void setEmergencyAPhone(String emergencyAPhone) {
		this.emergencyAPhone = emergencyAPhone;
	}

	public String getEmergencyBPhone() {
		return emergencyBPhone;
	}

	public void setEmergencyBPhone(String emergencyBPhone) {
		this.emergencyBPhone = emergencyBPhone;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getAnnualIncomeAfterTax() {
		return annualIncomeAfterTax;
	}

	public void setAnnualIncomeAfterTax(String annualIncomeAfterTax) {
		this.annualIncomeAfterTax = annualIncomeAfterTax;
	}

	public String getWorkingPlace() {
		return workingPlace;
	}

	public void setWorkingPlace(String workingPlace) {
		this.workingPlace = workingPlace;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompanyTelephone() {
		return companyTelephone;
	}

	public void setCompanyTelephone(String companyTelephone) {
		this.companyTelephone = companyTelephone;
	}

	public String getIntentionCarInfo() {
		return intentionCarInfo;
	}

	public void setIntentionCarInfo(String intentionCarInfo) {
		this.intentionCarInfo = intentionCarInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCustomerCarId() {
		return customerCarId;
	}

	public void setCustomerCarId(Integer customerCarId) {
		this.customerCarId = customerCarId;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public Boolean getBuyCarAlready() {
		return buyCarAlready;
	}

	public void setBuyCarAlready(Boolean buyCarAlready) {
		this.buyCarAlready = buyCarAlready;
	}

	public String getOrderStates() {
		return orderStates;
	}

	public void setOrderStates(String orderStates) {
		this.orderStates = orderStates;
	}

	public Integer getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(Integer paymentWay) {
		this.paymentWay = paymentWay;
	}

	public String getCustomerUsersSearch() {
		return customerUsersSearch;
	}

	public void setCustomerUsersSearch(String customerUsersSearch) {
		this.customerUsersSearch = customerUsersSearch;
	}
}
