package main.com.logistics.dao.search;

import java.util.Date;
import java.util.List;

import main.com.frame.search.BaseSearch;
import main.com.logistics.dao.vo.LogisticsGoodsCarVo;
import main.com.stock.dao.po.ConsumerOrderUser;

public class LogisticsConsignmentSearch extends BaseSearch{

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

	    /**
	     * 交车联系人
	     */
	    private String leaveTheCarPersonName;

	    /**
	     * 交车联系人电话
	     */
	    private String leaveTheCarPersonPhone;

	    /**
	     * 提车联系人
	     */
	    private String extractTheCarPersonName;

	    /**
	     * 提车联系人电话
	     */
	    private String extractTheCarPersonPhone;

	    /**
	     * 提车联系人身份证号
	     */
	    private String extractTheCarPersonIdcard;

	    /**
	     * 起点
	     */
	    private String startingPointAddress;

	    /**
	     * 起点
	     */
	    private String destinationAddress;

	    /**
	     * 备注
	     */
	    private String remarks;

	    /**
	     * 状态
	     */
	    private String consignmentState;

	    /**
	     * 费用
	     */
	    private Double amount;
	    
	    /**
	     * 预估价
	     */
	    private Double estimateAmount;
	    
	    /**
	     * 总的起步价
	     */
	    private Double initiateRate;
	    
	    /**
	     * 总的溢出价
	     */
	    private Double overflow;

	    /**
	     * 是否已取消
	     */
	    private Byte isCancel;

	    /**
	     * 是否已删除
	     */
	    private Byte isDelete;

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
	    private String appointmentTime;

	    /**
	     * 起点经度
	     */
	    private String startingPointLongitude;

	    /**
	     * 起点纬度
	     */
	    private String startingPointLatitude;

	    /**
	     * 终点经度
	     */
	    private String destinationLongitude;

	    /**
	     * 终点纬度
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
	     * 顾客电话
	     */
	    private Integer payMethod;
	    
	    private Integer consignmentInPayState;
	    
	    private String goodsCarInfo;
	    
	    private Integer consignmentTypeLineId;
	    
	    private Integer carsId;
	    private String carsIds;
	    private Integer number;
	    
	    private String distributionCode;
	    private String goodsCarIds;
	    private Integer distributionId;
	    
	    private Double mileage;
	    
		/**
		 * 交车人
		 */
		List<ConsumerOrderUser> leaveTheCarPerson;
		
		/**
		 * 提车人
		 * @return
		 */
		List<ConsumerOrderUser> extractTheCarPerson;

	    private List<LogisticsGoodsCarVo> goodsCarVos; 
	    	    
	    /**
	     * 组织名称
	     */
	    private String orgName;
	    
	    /**
	     * 组织ID
	     */
	    private Integer orgId;
//	    
//	    /**
//	     * 顾客姓名
//	     */
//	    private String purchasersName;
//	    
//	    /**
//	     * 顾客电话
//	     */
//	    private String purchasersPhone;
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

	    /**
	     * 交车联系人
	     * @return leave_the_car_person_name 交车联系人
	     */
	    public String getLeaveTheCarPersonName() {
	        return leaveTheCarPersonName;
	    }

	    /**
	     * 交车联系人
	     * @param leaveTheCarPersonName 交车联系人
	     */
	    public void setLeaveTheCarPersonName(String leaveTheCarPersonName) {
	        this.leaveTheCarPersonName = leaveTheCarPersonName;
	    }

	    /**
	     * 交车联系人电话
	     * @return leave_the_car_person_phone 交车联系人电话
	     */
	    public String getLeaveTheCarPersonPhone() {
	        return leaveTheCarPersonPhone;
	    }

	    /**
	     * 交车联系人电话
	     * @param leaveTheCarPersonPhone 交车联系人电话
	     */
	    public void setLeaveTheCarPersonPhone(String leaveTheCarPersonPhone) {
	        this.leaveTheCarPersonPhone = leaveTheCarPersonPhone;
	    }

	    /**
	     * 提车联系人
	     * @return extract_the_car_person_name 提车联系人
	     */
	    public String getExtractTheCarPersonName() {
	        return extractTheCarPersonName;
	    }

	    /**
	     * 提车联系人
	     * @param extractTheCarPersonName 提车联系人
	     */
	    public void setExtractTheCarPersonName(String extractTheCarPersonName) {
	        this.extractTheCarPersonName = extractTheCarPersonName;
	    }

	    /**
	     * 提车联系人电话
	     * @return extract_the_car_person_phone 提车联系人电话
	     */
	    public String getExtractTheCarPersonPhone() {
	        return extractTheCarPersonPhone;
	    }

	    /**
	     * 提车联系人电话
	     * @param extractTheCarPersonPhone 提车联系人电话
	     */
	    public void setExtractTheCarPersonPhone(String extractTheCarPersonPhone) {
	        this.extractTheCarPersonPhone = extractTheCarPersonPhone;
	    }

	    /**
	     * 提车联系人身份证号
	     * @return extract_the_car_person_idcard 提车联系人身份证号
	     */
	    public String getExtractTheCarPersonIdcard() {
	        return extractTheCarPersonIdcard;
	    }

	    /**
	     * 提车联系人身份证号
	     * @param extractTheCarPersonIdcard 提车联系人身份证号
	     */
	    public void setExtractTheCarPersonIdcard(String extractTheCarPersonIdcard) {
	        this.extractTheCarPersonIdcard = extractTheCarPersonIdcard;
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
	     * 起点
	     * @return destination_address 起点
	     */
	    public String getDestinationAddress() {
	        return destinationAddress;
	    }

	    /**
	     * 起点
	     * @param destinationAddress 起点
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
	    public String getConsignmentState() {
	        return consignmentState;
	    }

	    /**
	     * 状态
	     * @param consignmentState 状态
	     */
	    public void setConsignmentState(String consignmentState) {
	        this.consignmentState = consignmentState;
	    }

	    /**
	     * 费用
	     * @return amount 费用
	     */
	    public Double getAmount() {
	        return amount;
	    }

	    /**
	     * 费用
	     * @param amount 费用
	     */
	    public void setAmount(Double amount) {
	        this.amount = amount;
	    }

	    /**
	     * 是否已取消
	     * @return is_cancel 是否已取消
	     */
	    public Byte getIsCancel() {
	        return isCancel;
	    }

	    /**
	     * 是否已取消
	     * @param isCancel 是否已取消
	     */
	    public void setIsCancel(Byte isCancel) {
	        this.isCancel = isCancel;
	    }

	    /**
	     * 是否已删除
	     * @return is_delete 是否已删除
	     */
	    public Byte getIsDelete() {
	        return isDelete;
	    }

	    /**
	     * 是否已删除
	     * @param isDelete 是否已删除
	     */
	    public void setIsDelete(Byte isDelete) {
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
	    public String getAppointmentTime() {
	        return appointmentTime;
	    }

	    /**
	     * 预约时间
	     * @param appointmentTime 预约时间
	     */
	    public void setAppointmentTime(String appointmentTime) {
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

		public Integer getPayMethod() {
			return payMethod;
		}

		public void setPayMethod(Integer payMethod) {
			this.payMethod = payMethod;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public Integer getConsignmentInPayState() {
			return consignmentInPayState;
		}

		public void setConsignmentInPayState(Integer consignmentInPayState) {
			this.consignmentInPayState = consignmentInPayState;
		}

		public String getGoodsCarInfo() {
			return goodsCarInfo;
		}

		public void setGoodsCarInfo(String goodsCarInfo) {
			this.goodsCarInfo = goodsCarInfo;
		}

		public Integer getConsignmentTypeLineId() {
			return consignmentTypeLineId;
		}

		public void setConsignmentTypeLineId(Integer consignmentTypeLineId) {
			this.consignmentTypeLineId = consignmentTypeLineId;
		}

		public Integer getCarsId() {
			return carsId;
		}

		public void setCarsId(Integer carsId) {
			this.carsId = carsId;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}

		public String getCarsIds() {
			return carsIds;
		}

		public void setCarsIds(String carsIds) {
			this.carsIds = carsIds;
		}

		public List<LogisticsGoodsCarVo> getGoodsCarVos() {
			return goodsCarVos;
		}

		public void setGoodsCarVos(List<LogisticsGoodsCarVo> goodsCarVos) {
			this.goodsCarVos = goodsCarVos;
		}

		public Double getInitiateRate() {
			return initiateRate;
		}

		public void setInitiateRate(Double initiateRate) {
			this.initiateRate = initiateRate;
		}

		public Double getOverflow() {
			return overflow;
		}

		public void setOverflow(Double overflow) {
			this.overflow = overflow;
		}

		public List<ConsumerOrderUser> getLeaveTheCarPerson() {
			return leaveTheCarPerson;
		}

		public void setLeaveTheCarPerson(List<ConsumerOrderUser> leaveTheCarPerson) {
			this.leaveTheCarPerson = leaveTheCarPerson;
		}

		public List<ConsumerOrderUser> getExtractTheCarPerson() {
			return extractTheCarPerson;
		}

		public void setExtractTheCarPerson(List<ConsumerOrderUser> extractTheCarPerson) {
			this.extractTheCarPerson = extractTheCarPerson;
		}

		public Double getEstimateAmount() {
			return estimateAmount;
		}

		public void setEstimateAmount(Double estimateAmount) {
			this.estimateAmount = estimateAmount;
		}

		public String getDistributionCode() {
			return distributionCode;
		}

		public void setDistributionCode(String distributionCode) {
			this.distributionCode = distributionCode;
		}

		public String getGoodsCarIds() {
			return goodsCarIds;
		}

		public void setGoodsCarIds(String goodsCarIds) {
			this.goodsCarIds = goodsCarIds;
		}

		public Integer getDistributionId() {
			return distributionId;
		}

		public void setDistributionId(Integer distributionId) {
			this.distributionId = distributionId;
		}

		public Double getMileage() {
			return mileage;
		}

		public void setMileage(Double mileage) {
			this.mileage = mileage;
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
}
