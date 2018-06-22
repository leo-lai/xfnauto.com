package main.com.stock.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ConsumerOrder implements Serializable{
    /**
     * ID
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 订单状态 0退订 1:初始  5:待收定金 10:待验车 15:待协商 20:待收尾款 25:待出库 30:待上传票证 35:完成
     */
    private Integer state;

    /**
     * 门店ID
     */
    private Integer orgId;

    /**
     * 门店名称
     */
    private String orgName;

    /**
     * 联系人名称
     */
    private String orgLinker;

    /**
     * 联系人手机号
     */
    private String orgPhone;

    /**
     * 物流渠道 1：自提 2：其他
     */
    private Integer logisticsType;

    /**
     * 物流车牌号
     */
    private String logisticsPlateNumber;

    /**
     * 物流司机姓名
     */
    private String logisticsDriver;

    /**
     * 物流司机手机
     */
    private String logisticsDriverPhone;

    /**
     * 物流单号
     */
    private String logisticsOrderCode;
    
    /**
     * 物流公司
     */
    private String logisticsCompany;

    /**
     * 预计交车日期
     */
    private Date pickCarDate;

    /**
     * 交车地址
     */
    private String pickCarAddr;
    
    /**
     * 出库人
     */
    private String outStocker;
    
    /**
     * 出库时间
     */
    private Date outStockTime;
    
    /**
     * 运费
     */
    private BigDecimal freight;
    
   
    /**
     * 创建时间
     */
    private Date createTime;
    
    private Integer creatorId;
    
    private String creator;

    /**
     * 
     */
    private Integer isDel;
    
    /**
     * 退订原因
     */
    private String countermandReason;
    /**
     * 是否已申请退订（审核通过就置为退订状态，把申请置为否的状态。退订状态为0，包含在订单状态里）
     */
    private Boolean countermandApply;
    /**
     * 退订截图
     */
    private String countermandPic;
    
    /**
     * 订单类型 1购车单 2炒车单
     */
    private Integer orderType;
    
    /**
     * 商城预订单ID
     */
    private Integer advanceOrderId;

}