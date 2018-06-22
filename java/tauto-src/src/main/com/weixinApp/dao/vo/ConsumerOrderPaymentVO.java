package main.com.weixinApp.dao.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
@Data
public class ConsumerOrderPaymentVO implements Serializable{
	
	private static final long serialVersionUID = 2421043809245752199L;

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
     * 凭证
     */
    private String voucher;

    /**
     * 备注
     */
    private String remark;

  
}
