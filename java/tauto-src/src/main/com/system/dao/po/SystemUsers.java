package main.com.system.dao.po;

import java.io.Serializable;
import java.util.Date;

import main.com.utils.JsonDateTimeSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class SystemUsers implements Serializable {

	private static final long serialVersionUID = -490896280361896195L;
	
	private Integer usersId;                //主键
	private String headPortrait;       //头像
	private String userName;           //用户名
	private String realName;           //姓名
	private String phoneNumber;           //手机号码
	private String contactPhone;       //联系电话
	private String email;              //个人邮箱
	private String password;           //密码
	private Boolean isFirstLogin;      //是否首次登陆
	private Boolean isOnline;          //是否在线
	private Boolean isEnable;          //是否启用
	private Boolean isReset;           //是否重置
	private Float accountAmount;       //账号金额
	private Integer creditValue;       //信用值
	private String remark;             //备注
	private Integer status;            //用户状态 : 1启用 2禁用
	private Date createTime;           //创建时间
	private Date updateTime;           //更新时间
	private String sessionId;          //登录凭证
	private Date loginTime;            //登陆时间
	private Integer orgId;             //组织ID
	private String orgName;             //组织名称
	private String orgCode;          //组织编码
	private Date birthday;          //生日
	private String cardNo;          //身份证号
	private Date entryTime;           //入职时间
	private Double basePay;            //基本工资
	private Integer agentGender;           //性别 1男 2女
	private String nikeName;           //昵称
	private String openId;           //openId
	
	private String idCardPicOn;    //身份证正面
	private String idCardPicOff;   //身份证反面
	
	private String ticket;   //二维码凭证
	private String ticketImage;   //二维码
	
	private String userCode;
	private String weixinQrImage;//个人名片二维码

	public Integer getUsersId() {
		return usersId;
	}
	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}
	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	
	public Boolean getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}
	
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	public Boolean getIsReset() {
		return isReset;
	}
	public void setIsReset(Boolean isReset) {
		this.isReset = isReset;
	}
	
	public Float getAccountAmount() {
		return accountAmount;
	}
	public void setAccountAmount(Float accountAmount) {
		this.accountAmount = accountAmount;
	}
	
	public Integer getCreditValue() {
		return creditValue;
	}
	public void setCreditValue(Integer creditValue) {
		this.creditValue = creditValue;
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
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public Double getBasePay() {
		return basePay;
	}
	public void setBasePay(Double basePay) {
		this.basePay = basePay;
	}
	public Integer getAgentGender() {
		return agentGender;
	}
	public void setAgentGender(Integer agentGender) {
		this.agentGender = agentGender;
	}
	public String getNikeName() {
		return nikeName;
	}
	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
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
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getTicketImage() {
		return ticketImage;
	}
	public void setTicketImage(String ticketImage) {
		this.ticketImage = ticketImage;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getWeixinQrImage() {
		return weixinQrImage;
	}
	public void setWeixinQrImage(String weixinQrImage) {
		this.weixinQrImage = weixinQrImage;
	}
}
