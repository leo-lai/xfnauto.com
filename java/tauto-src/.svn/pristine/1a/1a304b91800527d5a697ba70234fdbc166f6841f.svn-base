package main.com.customer.dao.po;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
public class CustomerOrderTrack implements Serializable {
    /**
     * 
     */
    private Integer orderTrackId;

    /**
     * 订单跟踪内容
     */
    private String trackContent;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 订单ID
     */
    private Integer customerOrderId;

    /**
     * 订单CODE
     */
    private String customerOrderCode;

    /**
     * 
     */
    private Integer customerOrderState;

    /**
     * 
     * @return order_track_id 
     */
    public Integer getOrderTrackId() {
        return orderTrackId;
    }

    /**
     * 
     * @param orderTrackId 
     */
    public void setOrderTrackId(Integer orderTrackId) {
        this.orderTrackId = orderTrackId;
    }

    /**
     * 订单跟踪内容
     * @return track_content 订单跟踪内容
     */
    public String getTrackContent() {
        return trackContent;
    }

    /**
     * 订单跟踪内容
     * @param trackContent 订单跟踪内容
     */
    public void setTrackContent(String trackContent) {
        this.trackContent = trackContent;
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
     * 订单ID
     * @return customer_order_id 订单ID
     */
    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    /**
     * 订单ID
     * @param customerOrderId 订单ID
     */
    public void setCustomerOrderId(Integer customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    /**
     * 订单CODE
     * @return customer_order_code 订单CODE
     */
    public String getCustomerOrderCode() {
        return customerOrderCode;
    }

    /**
     * 订单CODE
     * @param customerOrderCode 订单CODE
     */
    public void setCustomerOrderCode(String customerOrderCode) {
        this.customerOrderCode = customerOrderCode;
    }

    /**
     * 
     * @return customer_order_state 
     */
    public Integer getCustomerOrderState() {
        return customerOrderState;
    }

    /**
     * 
     * @param customerOrderState 
     */
    public void setCustomerOrderState(Integer customerOrderState) {
        this.customerOrderState = customerOrderState;
    }
}