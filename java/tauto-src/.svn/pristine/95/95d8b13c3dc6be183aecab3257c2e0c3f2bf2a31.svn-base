package main.com.weixinHtml.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
public class ShopAdvanceOrder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Integer advanceOrderId;

    /**
     * 预约门店
     */
    private Integer orgId;

    /**
     * 预约门店
     */
    private String orgName;

    /**
     * 客户
     */
    private Integer shopUserId;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 电话号
     */
    private String phoneNumber;

    /**
     * 用户类型 1普通 2商家
     */
    private Integer userType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     */
    private String remarks;

    /**
     * 1启用 2禁用
     */
    private Integer orderStatus;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 是否已删除
     */
    private Boolean overDelete;

    /**
     * 定金
     */
    private BigDecimal depositPrice;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 是否已开单
     */
    private Boolean overCatch;

    /**
     * 
     */
    private Integer systemUserId;

    /**
     * 处理人
     */
    private String systemUserName;

    /**
     * 真实下单ID，根据user_type区分用户
     */
    private Integer realOrderId;

    /**
     * 处理时间
     */
    private Date catchDate;

    /**
     * 来源 HT（后台新建）TG（推广）SW（商务端新建）YY（用户端预约）WZ（其他）SC（商城注册）
     */
    private String theSource;

    /**
     * 预约时间段
     */
    private String timeOfAppointment;

    /**
     * 客户提交预约时间的时间
     */
    private Date timeOfAppointmentDate;

    /**
     * 预约日期
     */
    private Date appointmentDate;

    /**
     * 是否已支付定金 0未支付 1已支付
     */
    private Boolean overPay;

    /**
     * 期望购车方式 1全款 2分期
     */
    private Integer expectBuyWay;

    /**
     * 期待支付方式 1微信 2线下到店
     */
    private Integer expectPayWay;
    
    /**
     * 预约来源 1普通上架商品 2活动
     */
    private Integer orderSource;
    
    /**
     * 来源的ID shop_goods_cars的ID或者shop_goods_cars_activity的iD
     */
    private Integer orderSourceId;

    private BigDecimal discountPriceOnLine;
    private BigDecimal bareCarPriceOnLine;
    private BigDecimal logisticsPrice;
    
    /**
     * 所属组织代码
     */
    private String orgCode;
    /**
     * 所属组织代码
     */
    private Boolean readyPay;
    /**
     * 
     * @return advance_order_id 
     */
    public Integer getAdvanceOrderId() {
        return advanceOrderId;
    }

    /**
     * 
     * @param advanceOrderId 
     */
    public void setAdvanceOrderId(Integer advanceOrderId) {
        this.advanceOrderId = advanceOrderId;
    }

    /**
     * 预约门店
     * @return org_id 预约门店
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 预约门店
     * @param orgId 预约门店
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 预约门店
     * @return org_name 预约门店
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 预约门店
     * @param orgName 预约门店
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 客户
     * @return shop_user_id 客户
     */
    public Integer getShopUserId() {
        return shopUserId;
    }

    /**
     * 客户
     * @param shopUserId 客户
     */
    public void setShopUserId(Integer shopUserId) {
        this.shopUserId = shopUserId;
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
     * 用户类型 1普通 2商家
     * @return user_type 用户类型 1普通 2商家
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 用户类型 1普通 2商家
     * @param userType 用户类型 1普通 2商家
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
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
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 1启用 2禁用
     * @return order_status 1启用 2禁用
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 1启用 2禁用
     * @param orderStatus 1启用 2禁用
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 订单号
     * @return order_code 订单号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 订单号
     * @param orderCode 订单号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 是否已删除
     * @return over_delete 是否已删除
     */
    public Boolean getOverDelete() {
        return overDelete;
    }

    /**
     * 是否已删除
     * @param overDelete 是否已删除
     */
    public void setOverDelete(Boolean overDelete) {
        this.overDelete = overDelete;
    }

    /**
     * 定金
     * @return deposit_price 定金
     */
    public BigDecimal getDepositPrice() {
        return depositPrice;
    }

    /**
     * 定金
     * @param depositPrice 定金
     */
    public void setDepositPrice(BigDecimal depositPrice) {
        this.depositPrice = depositPrice;
    }

    /**
     * 金额
     * @return amount 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 金额
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 是否已开单
     * @return over_catch 是否已开单
     */
    public Boolean getOverCatch() {
        return overCatch;
    }

    /**
     * 是否已开单
     * @param overCatch 是否已开单
     */
    public void setOverCatch(Boolean overCatch) {
        this.overCatch = overCatch;
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
     * 处理人
     * @return system_user_name 处理人
     */
    public String getSystemUserName() {
        return systemUserName;
    }

    /**
     * 处理人
     * @param systemUserName 处理人
     */
    public void setSystemUserName(String systemUserName) {
        this.systemUserName = systemUserName;
    }

    /**
     * 真实下单ID，根据user_type区分用户
     * @return real_order_id 真实下单ID，根据user_type区分用户
     */
    public Integer getRealOrderId() {
        return realOrderId;
    }

    /**
     * 真实下单ID，根据user_type区分用户
     * @param realOrderId 真实下单ID，根据user_type区分用户
     */
    public void setRealOrderId(Integer realOrderId) {
        this.realOrderId = realOrderId;
    }

    /**
     * 处理时间
     * @return catch_date 处理时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCatchDate() {
        return catchDate;
    }

    /**
     * 处理时间
     * @param catchDate 处理时间
     */
    public void setCatchDate(Date catchDate) {
        this.catchDate = catchDate;
    }

    /**
     * 来源 HT（后台新建）TG（推广）SW（商务端新建）YY（用户端预约）WZ（其他）SC（商城注册）
     * @return the_source 来源 HT（后台新建）TG（推广）SW（商务端新建）YY（用户端预约）WZ（其他）SC（商城注册）
     */
    public String getTheSource() {
        return theSource;
    }

    /**
     * 来源 HT（后台新建）TG（推广）SW（商务端新建）YY（用户端预约）WZ（其他）SC（商城注册）
     * @param theSource 来源 HT（后台新建）TG（推广）SW（商务端新建）YY（用户端预约）WZ（其他）SC（商城注册）
     */
    public void setTheSource(String theSource) {
        this.theSource = theSource;
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
     * 客户提交预约时间的时间
     * @return time_of_appointment_date 客户提交预约时间的时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getTimeOfAppointmentDate() {
        return timeOfAppointmentDate;
    }

    /**
     * 客户提交预约时间的时间
     * @param timeOfAppointmentDate 客户提交预约时间的时间
     */
    public void setTimeOfAppointmentDate(Date timeOfAppointmentDate) {
        this.timeOfAppointmentDate = timeOfAppointmentDate;
    }

    /**
     * 预约日期
     * @return appointment_date 预约日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * 预约日期
     * @param appointmentDate 预约日期
     */
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * 是否已支付定金 0未支付 1已支付
     * @return over_pay 是否已支付定金 0未支付 1已支付
     */
    public Boolean getOverPay() {
        return overPay;
    }

    /**
     * 是否已支付定金 0未支付 1已支付
     * @param overPay 是否已支付定金 0未支付 1已支付
     */
    public void setOverPay(Boolean overPay) {
        this.overPay = overPay;
    }

    /**
     * 期望购车方式 1全款 2分期
     * @return expect_buy_way 期望购车方式 1全款 2分期
     */
    public Integer getExpectBuyWay() {
        return expectBuyWay;
    }

    /**
     * 期望购车方式 1全款 2分期
     * @param expectBuyWay 期望购车方式 1全款 2分期
     */
    public void setExpectBuyWay(Integer expectBuyWay) {
        this.expectBuyWay = expectBuyWay;
    }

    /**
     * 期待支付方式 1微信 2线下到店
     * @return expect_pay_way 期待支付方式 1微信 2线下到店
     */
    public Integer getExpectPayWay() {
        return expectPayWay;
    }

    /**
     * 期待支付方式 1微信 2线下到店
     * @param expectPayWay 期待支付方式 1微信 2线下到店
     */
    public void setExpectPayWay(Integer expectPayWay) {
        this.expectPayWay = expectPayWay;
    }

	public Integer getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(Integer orderSource) {
		this.orderSource = orderSource;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getOrderSourceId() {
		return orderSourceId;
	}

	public void setOrderSourceId(Integer orderSourceId) {
		this.orderSourceId = orderSourceId;
	}

	public BigDecimal getDiscountPriceOnLine() {
		return discountPriceOnLine;
	}

	public void setDiscountPriceOnLine(BigDecimal discountPriceOnLine) {
		this.discountPriceOnLine = discountPriceOnLine;
	}

	public BigDecimal getBareCarPriceOnLine() {
		return bareCarPriceOnLine;
	}

	public void setBareCarPriceOnLine(BigDecimal bareCarPriceOnLine) {
		this.bareCarPriceOnLine = bareCarPriceOnLine;
	}

	public BigDecimal getLogisticsPrice() {
		return logisticsPrice;
	}

	public void setLogisticsPrice(BigDecimal logisticsPrice) {
		this.logisticsPrice = logisticsPrice;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Boolean getReadyPay() {
		return readyPay;
	}

	public void setReadyPay(Boolean readyPay) {
		this.readyPay = readyPay;
	}
}