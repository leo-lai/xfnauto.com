package main.com.car.dao.po;

import java.io.Serializable;
import java.util.Date;

public class CarBrowseRecord implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 车辆浏览主键
     */
    private Integer carBrowseRecordId;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 临时用户ID
     */
    private Integer userTempId;

    /**
     * 车大类ID
     */
    private Integer carsId;

    /**
     * 用户ID
     */
    private Integer customerId;

    /**
     * 车大类信息
     */
    private String carsName;

    /**
     * 车辆浏览主键
     * @return car_browse_record_id 车辆浏览主键
     */
    public Integer getCarBrowseRecordId() {
        return carBrowseRecordId;
    }

    /**
     * 车辆浏览主键
     * @param carBrowseRecordId 车辆浏览主键
     */
    public void setCarBrowseRecordId(Integer carBrowseRecordId) {
        this.carBrowseRecordId = carBrowseRecordId;
    }

    /**
     * 创建日期
     * @return create_date 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建日期
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 临时用户ID
     * @return user_temp_id 临时用户ID
     */
    public Integer getUserTempId() {
        return userTempId;
    }

    /**
     * 临时用户ID
     * @param userTempId 临时用户ID
     */
    public void setUserTempId(Integer userTempId) {
        this.userTempId = userTempId;
    }

    /**
     * 车大类ID
     * @return cars_id 车大类ID
     */
    public Integer getCarsId() {
        return carsId;
    }

    /**
     * 车大类ID
     * @param carsId 车大类ID
     */
    public void setCarsId(Integer carsId) {
        this.carsId = carsId;
    }

    /**
     * 用户ID
     * @return customer_id 用户ID
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 用户ID
     * @param customerId 用户ID
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 车大类信息
     * @return cars_name 车大类信息
     */
    public String getCarsName() {
        return carsName;
    }

    /**
     * 车大类信息
     * @param carsName 车大类信息
     */
    public void setCarsName(String carsName) {
        this.carsName = carsName;
    }
}