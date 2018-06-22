package main.com.logistics.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 物流模块 ------ 托运单
 * @author Zwen
 *
 */
@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
public class LogisticsConsignment implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer consignmentId;

    /**
     * 物流单号
     */
    private String consignmentCode;

    /**
     * 配送方式 1配送上门 2专线配送
     */
    private Integer consignmentType;

//    /**
//     * 交车联系人
//     */
//    private String leaveTheCarPersonName;
//
//    /**
//     * 交车联系人电话
//     */
//    private String leaveTheCarPersonPhone;
//
//    /**
//     * 提车联系人
//     */
//    private String extractTheCarPersonName;
//
//    /**
//     * 提车联系人电话
//     */
//    private String extractTheCarPersonPhone;
//
//    /**
//     * 提车联系人身份证号
//     */
//    private String extractTheCarPersonIdcard;
    
    private String leaveTheCarPersonIds;
    private String extractTheCarPersonIds;

    /**
     * 起点
     */
    private String startingPointAddress;

    /**
     * 终点
     */
    private String destinationAddress;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private Integer consignmentState;

    /**
     * 费用
     */
    private BigDecimal amount;

    /**
     * 是否已取消
     */
    private Boolean isCancel;

    /**
     * 是否已删除
     */
    private Boolean isDelete;

    /**
     * 下单时间
     */
    private Date createDate;

    /**
     * 下单来源（保留）
     */
    private Integer createType;

    /**
     * 预约时间
     */
    private Date appointmentTime;

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

    /**
     * 顾客ID
     */
    private Integer purchasersId;

    /**
     * 顾客名称
     */
    private String purchasersName;

    /**
     * 顾客电话
     */
    private String purchasersPhone;
    
    /**
     * 组织名称
     */
    private String orgName;
    
    /**
     * 组织ID
     */
    private Integer orgId;
    
    /**
     * 总的支付状态
     */
    private Integer consignmentInPayState;
    
    /**
     * 托运单资费配置ID
     */
    private Integer consignmentTypeLineId;
    
    /**
     * 公里数
     */
    private Double mileage;
    
    private Integer systemUserId;
    private String systemUserName;
    private String systemUserPhone;


    /**
     * 主键
     * @return consignment_id 主键
     */
    public Integer getConsignmentId() {
        return consignmentId;
    }

    /**
     * 主键
     * @param consignmentId 主键
     */
    public void setConsignmentId(Integer consignmentId) {
        this.consignmentId = consignmentId;
    }

    /**
     * 物流单号
     * @return consignment_code 物流单号
     */
    public String getConsignmentCode() {
        return consignmentCode;
    }

    /**
     * 物流单号
     * @param consignmentCode 物流单号
     */
    public void setConsignmentCode(String consignmentCode) {
        this.consignmentCode = consignmentCode;
    }

    /**
     * 配送方式 1配送上门 2专线配送
     * @return consignment_type 配送方式 1配送上门 2专线配送
     */
    public Integer getConsignmentType() {
        return consignmentType;
    }

    /**
     * 配送方式 1配送上门 2专线配送
     * @param consignmentType 配送方式 1配送上门 2专线配送
     */
    public void setConsignmentType(Integer consignmentType) {
        this.consignmentType = consignmentType;
    }
   
    public String getLeaveTheCarPersonIds() {
		return leaveTheCarPersonIds;
	}

	public void setLeaveTheCarPersonIds(String leaveTheCarPersonIds) {
		this.leaveTheCarPersonIds = leaveTheCarPersonIds;
	}

	public String getExtractTheCarPersonIds() {
		return extractTheCarPersonIds;
	}

	public void setExtractTheCarPersonIds(String extractTheCarPersonIds) {
		this.extractTheCarPersonIds = extractTheCarPersonIds;
	}

	/**
     * 起点
     * @return starting_point_address 起点
     */
    public String getStartingPointAddress() {
        return startingPointAddress;
    }

    /**
     * 起点
     * @param startingPointAddress 起点
     */
    public void setStartingPointAddress(String startingPointAddress) {
        this.startingPointAddress = startingPointAddress;
    }

    /**
     * 终点
     * @return destination_address 终点
     */
    public String getDestinationAddress() {
        return destinationAddress;
    }

    /**
     * 终点
     * @param destinationAddress 终点
     */
    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    /**
     * 
     * @return remarks 
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 
     * @param remarks 
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 状态
     * @return consignment_state 状态
     */
    public Integer getConsignmentState() {
        return consignmentState;
    }

    /**
     * 状态
     * @param consignmentState 状态
     */
    public void setConsignmentState(Integer consignmentState) {
        this.consignmentState = consignmentState;
    }

    /**
     * 费用
     * @return amount 费用
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 费用
     * @param amount 费用
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 是否已取消
     * @return is_cancel 是否已取消
     */
    public Boolean getIsCancel() {
        return isCancel;
    }

    /**
     * 是否已取消
     * @param isCancel 是否已取消
     */
    public void setIsCancel(Boolean isCancel) {
        this.isCancel = isCancel;
    }

    /**
     * 是否已删除
     * @return is_delete 是否已删除
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 是否已删除
     * @param isDelete 是否已删除
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 下单时间
     * @return create_date 下单时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 下单时间
     * @param createDate 下单时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 下单来源（保留）
     * @return create_type 下单来源（保留）
     */
    public Integer getCreateType() {
        return createType;
    }

    /**
     * 下单来源（保留）
     * @param createType 下单来源（保留）
     */
    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    /**
     * 预约时间
     * @return appointment_time 预约时间
     */
    public Date getAppointmentTime() {
        return appointmentTime;
    }

    /**
     * 预约时间
     * @param appointmentTime 预约时间
     */
    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    /**
     * 起点经度
     * @return starting_point_longitude 起点经度
     */
    public String getStartingPointLongitude() {
        return startingPointLongitude;
    }

    /**
     * 起点经度
     * @param startingPointLongitude 起点经度
     */
    public void setStartingPointLongitude(String startingPointLongitude) {
        this.startingPointLongitude = startingPointLongitude;
    }

    /**
     * 起点纬度
     * @return starting_point_latitude 起点纬度
     */
    public String getStartingPointLatitude() {
        return startingPointLatitude;
    }

    /**
     * 起点纬度
     * @param startingPointLatitude 起点纬度
     */
    public void setStartingPointLatitude(String startingPointLatitude) {
        this.startingPointLatitude = startingPointLatitude;
    }

    /**
     * 起点经度
     * @return destination_longitude 起点经度
     */
    public String getDestinationLongitude() {
        return destinationLongitude;
    }

    /**
     * 起点经度
     * @param destinationLongitude 起点经度
     */
    public void setDestinationLongitude(String destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    /**
     * 起点纬度
     * @return destination_latitude 起点纬度
     */
    public String getDestinationLatitude() {
        return destinationLatitude;
    }

    /**
     * 起点纬度
     * @param destinationLatitude 起点纬度
     */
    public void setDestinationLatitude(String destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    /**
     * 顾客ID
     * @return purchasers_id 顾客ID
     */
    public Integer getPurchasersId() {
        return purchasersId;
    }

    /**
     * 顾客ID
     * @param purchasersId 顾客ID
     */
    public void setPurchasersId(Integer purchasersId) {
        this.purchasersId = purchasersId;
    }

    /**
     * 顾客名称
     * @return purchasers_name 顾客名称
     */
    public String getPurchasersName() {
        return purchasersName;
    }

    /**
     * 顾客名称
     * @param purchasersName 顾客名称
     */
    public void setPurchasersName(String purchasersName) {
        this.purchasersName = purchasersName;
    }

    /**
     * 顾客电话
     * @return purchasers_phone 顾客电话
     */
    public String getPurchasersPhone() {
        return purchasersPhone;
    }

    /**
     * 顾客电话
     * @param purchasersPhone 顾客电话
     */
    public void setPurchasersPhone(String purchasersPhone) {
        this.purchasersPhone = purchasersPhone;
    }

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getConsignmentInPayState() {
		return consignmentInPayState;
	}

	public void setConsignmentInPayState(Integer consignmentInPayState) {
		this.consignmentInPayState = consignmentInPayState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getConsignmentTypeLineId() {
		return consignmentTypeLineId;
	}

	public void setConsignmentTypeLineId(Integer consignmentTypeLineId) {
		this.consignmentTypeLineId = consignmentTypeLineId;
	}

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
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
}