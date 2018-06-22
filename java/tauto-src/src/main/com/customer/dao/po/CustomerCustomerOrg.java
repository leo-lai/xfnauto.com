package main.com.customer.dao.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
public class CustomerCustomerOrg implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Integer customerUsersOrgId;

    /**
     * 用户ID
     */
    private Integer customerUsersId;
    /**
     * 用户姓名
     */
    private String customerUsersName;

    /**
     * 用户电话
     */
    private String phoneNumber;

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
     * 购车意向（一天内 三天内）
     */
    private String carPurchaseIntention;

    /**
     * 是否可编辑
     */
    private Boolean isEdit;
    /**
     * 是否可共享编辑（未交订金之前，有关的门店共享用户）
     */
    private Boolean isShareEdit;

    /**
     * 期望购车方式
     */
    private Integer expectWayId;
    /**
     * 期望购车方式
     */
    private String expectWayName;

    /**
     * 意向车型
     */
    private Integer intentionCarId;

    /**
     * 预约时间段
     */
    private String timeOfAppointment;
    
    /**
     * 预约时间
     */
    private Date appointmentDate;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 销售顾问ID
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
     * 意向车辆信息
     */
    private String intentionCarInfo;
    
    /**
     * 是否标记为跟踪
     */
    private Boolean isTrack;
    
    /**
     * 是否标记为不买车
     */
    private Boolean isNotBuy;
    /**
     * 客户提交预约时间的时间
     */
    private Date timeOfAppointmentDate;
    
    private String theSource; //来源，供代码使用来源 HT（后台新建）TG（推广）SW（商务端新建）YY（用户端预约）WZ（其他）
    /**
     * 客户提交预约时间的时间
     */
    private Boolean isAppointment;
    
    /**
     * 客户强度 低中高
     */
    private String intensity;
    /**
     * 后台新建来源 1.4S店 2微信 3朋友介绍 4公众号 5直接到店
     */
    private String makeSource;

    /**
     * 
     * @return customer_users_org_id 
     */
    public Integer getCustomerUsersOrgId() {
        return customerUsersOrgId;
    }

    /**
     * 
     * @param customerUsersOrgId 
     */
    public void setCustomerUsersOrgId(Integer customerUsersOrgId) {
        this.customerUsersOrgId = customerUsersOrgId;
    }

    /**
     * 用户姓名
     * @return customer_users_name 用户姓名
     */
    public String getCustomerUsersName() {
        return customerUsersName;
    }

    /**
     * 用户姓名
     * @param customerUsersName 用户姓名
     */
    public void setCustomerUsersName(String customerUsersName) {
        this.customerUsersName = customerUsersName;
    }

    /**
     * 用户电话
     * @return phone_number 用户电话
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 用户电话
     * @param phoneNumber 用户电话
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
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
     * 组织名称（三级组织名称）
     * @return org_id 组织名称（三级组织名称）
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 组织名称（三级组织名称）
     * @param orgId 组织名称（三级组织名称）
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 组织简称
     * @return org_short_name 组织简称
     */
    public String getOrgShortName() {
        return orgShortName;
    }

    /**
     * 组织简称
     * @param orgShortName 组织简称
     */
    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }

    /**
     * 购车意向
     * @return car_purchase_intention 购车意向
     */
    public String getCarPurchaseIntention() {
        return carPurchaseIntention;
    }

    /**
     * 购车意向
     * @param carPurchaseIntention 购车意向
     */
    public void setCarPurchaseIntention(String carPurchaseIntention) {
        this.carPurchaseIntention = carPurchaseIntention;
    }

    /**
     * 是否可编辑
     * @return is_edit 是否可编辑
     */
    public Boolean getIsEdit() {
        return isEdit;
    }

    /**
     * 是否可编辑
     * @param isEdit 是否可编辑
     */
    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    /**
     * 期望购车方式
     * @return expect_way_id 期望购车方式
     */
    public Integer getExpectWayId() {
        return expectWayId;
    }

    /**
     * 期望购车方式
     * @param expectWayId 期望购车方式
     */
    public void setExpectWayId(Integer expectWayId) {
        this.expectWayId = expectWayId;
    }

    /**
     * 意向车型
     * @return intention_car_id 意向车型
     */
    public Integer getIntentionCarId() {
        return intentionCarId;
    }

    /**
     * 意向车型
     * @param intentionCarId 意向车型
     */
    public void setIntentionCarId(Integer intentionCarId) {
        this.intentionCarId = intentionCarId;
    }

    /**
     * 预约时间段
     * @return time_of_appointment 预约时间段
     */
    public String getTimeOfAppointment() {
        return timeOfAppointment;
    }

    /**
     * 预约时间段
     * @param timeOfAppointment 预约时间段
     */
    public void setTimeOfAppointment(String timeOfAppointment) {
        this.timeOfAppointment = timeOfAppointment;
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
     * 
     * @return system_user_id 
     */
    public Integer getSystemUserId() {
        return systemUserId;
    }

    /**
     * 
     * @param systemUserId 
     */
    public void setSystemUserId(Integer systemUserId) {
        this.systemUserId = systemUserId;
    }

    /**
     * 销售顾问姓名
     * @return system_user_name 销售顾问姓名
     */
    public String getSystemUserName() {
        return systemUserName;
    }

    /**
     * 销售顾问姓名
     * @param systemUserName 销售顾问姓名
     */
    public void setSystemUserName(String systemUserName) {
        this.systemUserName = systemUserName;
    }

    /**
     * 销售电话
     * @return system_user_phone 销售电话
     */
    public String getSystemUserPhone() {
        return systemUserPhone;
    }

    /**
     * 销售电话
     * @param systemUserPhone 销售电话
     */
    public void setSystemUserPhone(String systemUserPhone) {
        this.systemUserPhone = systemUserPhone;
    }

	public String getIntentionCarInfo() {
		return intentionCarInfo;
	}

	public void setIntentionCarInfo(String intentionCarInfo) {
		this.intentionCarInfo = intentionCarInfo;
	}

	public Integer getCustomerUsersId() {
		return customerUsersId;
	}

	public void setCustomerUsersId(Integer customerUsersId) {
		this.customerUsersId = customerUsersId;
	}

	public Boolean getIsShareEdit() {
		return isShareEdit;
	}

	public void setIsShareEdit(Boolean isShareEdit) {
		this.isShareEdit = isShareEdit;
	}

	public String getExpectWayName() {
		return expectWayName;
	}

	public void setExpectWayName(String expectWayName) {
		this.expectWayName = expectWayName;
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

	public Date getTimeOfAppointmentDate() {
		return timeOfAppointmentDate;
	}

	public void setTimeOfAppointmentDate(Date timeOfAppointmentDate) {
		this.timeOfAppointmentDate = timeOfAppointmentDate;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Boolean getIsAppointment() {
		return isAppointment;
	}

	public void setIsAppointment(Boolean isAppointment) {
		this.isAppointment = isAppointment;
	}

	public String getTheSource() {
		return theSource;
	}

	public void setTheSource(String theSource) {
		this.theSource = theSource;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

	public String getMakeSource() {
		return makeSource;
	}

	public void setMakeSource(String makeSource) {
		this.makeSource = makeSource;
	}
}