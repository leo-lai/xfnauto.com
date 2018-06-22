package main.com.weixinHtml.dao.search;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import main.com.frame.search.BaseSearch;

@Data
public class ShopAdvanceOrderSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    private Integer advanceOrderId;
    /**
     * 
     */
    private Integer orderId;

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
    
    private Integer goodsCarsId;
    
    private Integer goodsCarsActivityId;
    
    private Integer number;

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
    private String appointmentDate;

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
}
