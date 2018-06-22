package main.com.stock.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class ConsumerOrderPayment implements Serializable{
    
	
	private static final long serialVersionUID = 1020505776798022988L;

	/**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer orderId;

    /**
     * 
     */
    private BigDecimal amount;

    /**
     * 
     */
    private Integer type;
    
    /**
     * 支付方式
     */
    private Integer payType;

    /**
     * 凭证
     */
    private String voucher;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer isDel;

   
}