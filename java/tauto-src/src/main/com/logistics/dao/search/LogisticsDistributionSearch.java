package main.com.logistics.dao.search;

import java.util.Date;

import main.com.frame.search.BaseSearch;

public class LogisticsDistributionSearch extends BaseSearch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer distributionId;

	/**
	 * 单号
	 */
	private String distributionCode;

	/**
	 * 状态
	 */
	private Integer distributionState;
	
	/**
	 * 状态
	 */
	private Integer distributionStateMAX;

	/**
	 * 司机名称
	 */
	private String driverName;

	/**
	 * 司机ID
	 */
	private Integer driverId;

	/**
	 * 司机联系电话
	 */
	private String driverPhone;

	/**
	 * 配送日期
	 */
	private String createDate;

	/**
	 * 配送车辆拍照
	 */
	private String distributionLicensePlate;

	/**
	 * 班车类型 1小 2 中 3大
	 */
	private String distributionCarType;

	/**
	 * 空位
	 */
	private Integer vacancy;

	/**
	 * 板车ID
	 */
	private Integer logisticsCarId;

	/**
	 * 剩余空位
	 */
	private Integer leaveVacancy;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 起点
	 */
	private String startingPointAddress;

	/**
	 * 起点经度
	 */
	private String startingPointLongitude;

	/**
	 * 起点纬度
	 */
	private String startingPointLatitude;

	/**
	 * 起点
	 */
	private String destinationAddress;

	/**
	 * 起点经度
	 */
	private String destinationLongitude;

	/**
	 * 起点纬度
	 */
	private String destinationLatitude;
	
	private String consignmentTypeLineId;
	
	private Integer goodsCarId;
	private String goodsCarIds;
	private String acceptImage;
	private String deliverToImage;
	
	private Integer destinationType;
	private Integer consignmentType;
	
	private String idCardPicOn;    //身份证正面
	private String idCardPicOff;   //身份证反面

	/**
	 * 主键
	 * 
	 * @return distribution_id 主键
	 */
	public Integer getDistributionId() {
		return distributionId;
	}

	/**
	 * 主键
	 * 
	 * @param distributionId
	 *            主键
	 */
	public void setDistributionId(Integer distributionId) {
		this.distributionId = distributionId;
	}

	/**
	 * 单号
	 * 
	 * @return distribution_code 单号
	 */
	public String getDistributionCode() {
		return distributionCode;
	}

	/**
	 * 单号
	 * 
	 * @param distributionCode
	 *            单号
	 */
	public void setDistributionCode(String distributionCode) {
		this.distributionCode = distributionCode;
	}

	/**
	 * 状态
	 * 
	 * @return distribution_state 状态
	 */
	public Integer getDistributionState() {
		return distributionState;
	}

	/**
	 * 状态
	 * 
	 * @param distributionState
	 *            状态
	 */
	public void setDistributionState(Integer distributionState) {
		this.distributionState = distributionState;
	}

	/**
	 * 司机名称
	 * 
	 * @return driver_name 司机名称
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * 司机名称
	 * 
	 * @param driverName
	 *            司机名称
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	/**
	 * 司机ID
	 * 
	 * @return driver_id 司机ID
	 */
	public Integer getDriverId() {
		return driverId;
	}

	/**
	 * 司机ID
	 * 
	 * @param driverId
	 *            司机ID
	 */
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	/**
	 * 司机联系电话
	 * 
	 * @return driver_phone 司机联系电话
	 */
	public String getDriverPhone() {
		return driverPhone;
	}

	/**
	 * 司机联系电话
	 * 
	 * @param driverPhone
	 *            司机联系电话
	 */
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

	/**
	 * 配送日期
	 * 
	 * @return create_date 配送日期
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * 配送日期
	 * 
	 * @param createDate
	 *            配送日期
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * 配送车辆拍照
	 * 
	 * @return distribution_license_plate 配送车辆拍照
	 */
	public String getDistributionLicensePlate() {
		return distributionLicensePlate;
	}

	/**
	 * 配送车辆拍照
	 * 
	 * @param distributionLicensePlate
	 *            配送车辆拍照
	 */
	public void setDistributionLicensePlate(String distributionLicensePlate) {
		this.distributionLicensePlate = distributionLicensePlate;
	}

	/**
	 * 班车类型 1小 2 中 3大
	 * 
	 * @return distribution_car_type 班车类型 1小 2 中 3大
	 */
	public String getDistributionCarType() {
		return distributionCarType;
	}

	/**
	 * 班车类型 1小 2 中 3大
	 * 
	 * @param distributionCarType
	 *            班车类型 1小 2 中 3大
	 */
	public void setDistributionCarType(String distributionCarType) {
		this.distributionCarType = distributionCarType;
	}

	/**
	 * 空位
	 * 
	 * @return vacancy 空位
	 */
	public Integer getVacancy() {
		return vacancy;
	}

	/**
	 * 空位
	 * 
	 * @param vacancy
	 *            空位
	 */
	public void setVacancy(Integer vacancy) {
		this.vacancy = vacancy;
	}

	/**
	 * 板车ID
	 * 
	 * @return logistics_car_id 板车ID
	 */
	public Integer getLogisticsCarId() {
		return logisticsCarId;
	}

	/**
	 * 板车ID
	 * 
	 * @param logisticsCarId
	 *            板车ID
	 */
	public void setLogisticsCarId(Integer logisticsCarId) {
		this.logisticsCarId = logisticsCarId;
	}

	/**
	 * 剩余空位
	 * 
	 * @return leave_vacancy 剩余空位
	 */
	public Integer getLeaveVacancy() {
		return leaveVacancy;
	}

	/**
	 * 剩余空位
	 * 
	 * @param leaveVacancy
	 *            剩余空位
	 */
	public void setLeaveVacancy(Integer leaveVacancy) {
		this.leaveVacancy = leaveVacancy;
	}

	/**
	 * 备注
	 * 
	 * @return remarks 备注
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 备注
	 * 
	 * @param remarks
	 *            备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 起点
	 * 
	 * @return starting_point_address 起点
	 */
	public String getStartingPointAddress() {
		return startingPointAddress;
	}

	/**
	 * 起点
	 * 
	 * @param startingPointAddress
	 *            起点
	 */
	public void setStartingPointAddress(String startingPointAddress) {
		this.startingPointAddress = startingPointAddress;
	}

	/**
	 * 起点经度
	 * 
	 * @return starting_point_longitude 起点经度
	 */
	public String getStartingPointLongitude() {
		return startingPointLongitude;
	}

	/**
	 * 起点经度
	 * 
	 * @param startingPointLongitude
	 *            起点经度
	 */
	public void setStartingPointLongitude(String startingPointLongitude) {
		this.startingPointLongitude = startingPointLongitude;
	}

	/**
	 * 起点纬度
	 * 
	 * @return starting_point_latitude 起点纬度
	 */
	public String getStartingPointLatitude() {
		return startingPointLatitude;
	}

	/**
	 * 起点纬度
	 * 
	 * @param startingPointLatitude
	 *            起点纬度
	 */
	public void setStartingPointLatitude(String startingPointLatitude) {
		this.startingPointLatitude = startingPointLatitude;
	}

	/**
	 * 起点
	 * 
	 * @return destination_address 起点
	 */
	public String getDestinationAddress() {
		return destinationAddress;
	}

	/**
	 * 起点
	 * 
	 * @param destinationAddress
	 *            起点
	 */
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	/**
	 * 起点经度
	 * 
	 * @return destination_longitude 起点经度
	 */
	public String getDestinationLongitude() {
		return destinationLongitude;
	}

	/**
	 * 起点经度
	 * 
	 * @param destinationLongitude
	 *            起点经度
	 */
	public void setDestinationLongitude(String destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}

	/**
	 * 起点纬度
	 * 
	 * @return destination_latitude 起点纬度
	 */
	public String getDestinationLatitude() {
		return destinationLatitude;
	}

	/**
	 * 起点纬度
	 * 
	 * @param destinationLatitude
	 *            起点纬度
	 */
	public void setDestinationLatitude(String destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	public Integer getGoodsCarId() {
		return goodsCarId;
	}

	public void setGoodsCarId(Integer goodsCarId) {
		this.goodsCarId = goodsCarId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getGoodsCarIds() {
		return goodsCarIds;
	}

	public void setGoodsCarIds(String goodsCarIds) {
		this.goodsCarIds = goodsCarIds;
	}

	public String getAcceptImage() {
		return acceptImage;
	}

	public void setAcceptImage(String acceptImage) {
		this.acceptImage = acceptImage;
	}

	public String getDeliverToImage() {
		return deliverToImage;
	}

	public void setDeliverToImage(String deliverToImage) {
		this.deliverToImage = deliverToImage;
	}

	public Integer getDestinationType() {
		return destinationType;
	}

	public void setDestinationType(Integer destinationType) {
		this.destinationType = destinationType;
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

	public String getConsignmentTypeLineId() {
		return consignmentTypeLineId;
	}

	public void setConsignmentTypeLineId(String consignmentTypeLineId) {
		this.consignmentTypeLineId = consignmentTypeLineId;
	}

	public Integer getConsignmentType() {
		return consignmentType;
	}

	public void setConsignmentType(Integer consignmentType) {
		this.consignmentType = consignmentType;
	}

	public Integer getDistributionStateMAX() {
		return distributionStateMAX;
	}

	public void setDistributionStateMAX(Integer distributionStateMAX) {
		this.distributionStateMAX = distributionStateMAX;
	}
}
