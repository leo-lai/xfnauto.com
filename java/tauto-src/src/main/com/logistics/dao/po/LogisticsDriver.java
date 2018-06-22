package main.com.logistics.dao.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"})
public class LogisticsDriver implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer driverId;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 电话号
     */
    private String phoneNumber;

    /**
     * 
     */
    private String password;

    /**
     * 是否启用
     */
    private Boolean overEnable;

    /**
     * 
     */
    private String remark;

    /**
     * 状态 -1离职 0闲置 1运输中
     */
    private Integer status;

    /**
     * 登录凭证
     */
    private String sessionId;

    /**
     * 组织ID
     */
    private Integer orgId;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 组织CODE
     */
    private String orgCode;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 身份证号
     */
    private String cardNo;

    /**
     * 入职时间
     */
    private Date entryTime;

    /**
     * 基本工资
     */
    private Double basePay;

    /**
     * 性别
     */
    private Integer agentGender;

    /**
     * 昵称
     */
    private String nikeName;

    /**
     * openId
     */
    private String openId;

    /**
     * 身份证正面
     */
    private String idcardPicOn;

    /**
     * 身份证反面
     */
    private String idcardPicOff;

    /**
     * 类型 1公司员工 2加盟
     */
    private Integer type;

    /**
     * 主键
     * @return driver_id 主键
     */
    public Integer getDriverId() {
        return driverId;
    }

    /**
     * 主键
     * @param driverId 主键
     */
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    /**
     * 头像
     * @return head_portrait 头像
     */
    public String getHeadPortrait() {
        return headPortrait;
    }

    /**
     * 头像
     * @param headPortrait 头像
     */
    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    /**
     * 姓名
     * @return real_name 姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 姓名
     * @param realName 姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 电话号
     * @return phone_number 电话号
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 电话号
     * @param phoneNumber 电话号
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 
     * @return password 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 是否启用
     * @return over_enable 是否启用
     */
    public Boolean getOverEnable() {
        return overEnable;
    }

    /**
     * 是否启用
     * @param overEnable 是否启用
     */
    public void setOverEnable(Boolean overEnable) {
        this.overEnable = overEnable;
    }

    /**
     * 
     * @return remark 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark 
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 状态 -1离职 0闲置 1运输中
     * @return status 状态 -1离职 0闲置 1运输中
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态 -1离职 0闲置 1运输中
     * @param status 状态 -1离职 0闲置 1运输中
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 登录凭证
     * @return session_id 登录凭证
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * 登录凭证
     * @param sessionId 登录凭证
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 组织ID
     * @return org_id 组织ID
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 组织ID
     * @param orgId 组织ID
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 组织名称
     * @return org_name 组织名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 组织名称
     * @param orgName 组织名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 
     * @return org_code 
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 
     * @param orgCode 
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 生日
     * @return birthday 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 生日
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 身份证号
     * @return card_no 身份证号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 身份证号
     * @param cardNo 身份证号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 入职时间
     * @return entry_time 入职时间
     */
    public Date getEntryTime() {
        return entryTime;
    }

    /**
     * 入职时间
     * @param entryTime 入职时间
     */
    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    /**
     * 基本工资
     * @return base_pay 基本工资
     */
    public Double getBasePay() {
        return basePay;
    }

    /**
     * 基本工资
     * @param basePay 基本工资
     */
    public void setBasePay(Double basePay) {
        this.basePay = basePay;
    }

    /**
     * 性别
     * @return agent_gender 性别
     */
    public Integer getAgentGender() {
        return agentGender;
    }

    /**
     * 性别
     * @param agentGender 性别
     */
    public void setAgentGender(Integer agentGender) {
        this.agentGender = agentGender;
    }

    /**
     * 昵称
     * @return nike_name 昵称
     */
    public String getNikeName() {
        return nikeName;
    }

    /**
     * 昵称
     * @param nikeName 昵称
     */
    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    /**
     * openId
     * @return open_id openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * openId
     * @param openId openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 身份证正面
     * @return idcard_pic_on 身份证正面
     */
    public String getIdcardPicOn() {
        return idcardPicOn;
    }

    /**
     * 身份证正面
     * @param idcardPicOn 身份证正面
     */
    public void setIdcardPicOn(String idcardPicOn) {
        this.idcardPicOn = idcardPicOn;
    }

    /**
     * 身份证反面
     * @return idcard_pic_off 身份证反面
     */
    public String getIdcardPicOff() {
        return idcardPicOff;
    }

    /**
     * 身份证反面
     * @param idcardPicOff 身份证反面
     */
    public void setIdcardPicOff(String idcardPicOff) {
        this.idcardPicOff = idcardPicOff;
    }

    /**
     * 类型 1公司员工 2加盟
     * @return type 类型 1公司员工 2加盟
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型 1公司员工 2加盟
     * @param type 类型 1公司员工 2加盟
     */
    public void setType(Integer type) {
        this.type = type;
    }
}