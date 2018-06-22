package main.com.weixinHtml.dao.po;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import main.com.utils.Base64Util;

import java.io.Serializable;

//@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
public class ShopUsers implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer shopUserId;

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
     * 邮箱
     */
    private String email;

    /**
     * 
     */
    private String password;

    /**
     * 是否启用
     */
    private Boolean overEnable;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 登录凭证
     */
    private String sessionId;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 身份证号
     */
    private String cardNo;

    /**
     * 性别 1男 2女
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
     * unionId
     */
    private String unionId;
    /**
     * unionId
     */
    private Boolean overPush;
    /**
     * unionId
     */
    private Boolean overFollow;
    /**
     * unionId
     */
    private String userCode;
    
    /**
     * 用户类型 1普通 2商家
     */
    private Integer userType;
    /**
     * B端用户所属组织ID
     */
    private Integer orgId;
    /**
     * B端用户所属组织名称
     */
    private String orgName;


    /**
     * 主键
     * @return shop_user_id 主键
     */
    public Integer getShopUserId() {
        return shopUserId;
    }

    /**
     * 主键
     * @param shopUserId 主键
     */
    public void setShopUserId(Integer shopUserId) {
        this.shopUserId = shopUserId;
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
//        this.realName = Base64Util.encodeData(realName);
    	this.realName=realName;
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
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
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
    public String getRemarks() {
        return remarks;
    }

    /**
     * 
     * @param remark 
     */
    public void setRemark(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 
     * @return status 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
     * 生日
     * @return birthday 生日
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
     * 性别 1男 2女
     * @return agent_gender 性别 1男 2女
     */
    public Integer getAgentGender() {
        return agentGender;
    }

    /**
     * 性别 1男 2女
     * @param agentGender 性别 1男 2女
     */
    public void setAgentGender(Integer agentGender) {
        this.agentGender = agentGender;
    }

    /**
     * 昵称
     * @return nike_name 昵称
     */
    public String getNikeName() {
        return nikeName;//Base64Util.decodeData(nikeName);
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
     * 
     * @return union_id 
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * 
     * @param unionId 
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

	public Boolean getOverPush() {
		return overPush;
	}

	public void setOverPush(Boolean overPush) {
		this.overPush = overPush;
	}

	public Boolean getOverFollow() {
		return overFollow;
	}

	public void setOverFollow(Boolean overFollow) {
		this.overFollow = overFollow;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}