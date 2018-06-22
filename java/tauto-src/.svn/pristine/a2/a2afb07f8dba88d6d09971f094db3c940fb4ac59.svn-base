package main.com.weixinApp.dao.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import main.com.frame.constants.ConsumerOrderState;
import main.com.stock.dao.po.ConsumerOrder;
import main.com.stock.dao.po.ConsumerOrderUser;

@Data
public class ConsumerOrderVO implements Serializable{
	
	 /**
     * ID
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 订单状态 1:初始  5:待收定金 10:待验车 15:待协商 20:待收尾款 25:待出库 30:待上传票证 35:完成
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
    
    private String logisticsCompany;

    /**
     * 物流单号
     */
    private String logisticsOrderCode;

    /**
     * 预计交车日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date pickCarDate;

    /**
     * 交车地址
     */
    private String pickCarAddr;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    
    private Integer creatorId;
    
    private String creator;
	
	private List<ConsumerOrderInfoVO> infos;
	
	private String orderStateName;
	
	private String totalDepositPrice;
	
	private String totalFinalPrice;
	
	private String totalRestPrice;
	
	private List<ConsumerOrderUserVO> customers;
	
	private List<ConsumerOrderUserVO> pickers;
	
	private List<ConsumerOrderPaymentVO> orderPaymentVOs;
	
	/**
	 * 甲方
	 */
	private String partyA;
	
	/**
	 * 开户支行
	 */
	private String bankBranch;
	
	/**
	 * 户名
	 */
	private String bankAccountName;
	
	/**
	 * 银行卡号
	 */
	private String bankCardNum;
	
	/**
	 * 出库人
	 */
	private String outStocker;
	
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
     * 订单类型
     */
    private Integer orderType;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date outStockTime;
	
	/**
	 * 运费
	 */
	private String freight;
	
	public void setOrderStateName() {
		this.orderStateName = ConsumerOrderState.getMsgByCode(this.getState());
	}
	
}
