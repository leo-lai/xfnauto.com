package main.com.weixinApp.dao.search;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ConsumerOrderPaymentCreateSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = -8661765738161140714L;

	/**
     * 
     */
	@NotNull(message="订单ID不能为空")
    private Integer orderId;

    /**
     * 
     */
	@NotNull(message="金额不能为空")
    private BigDecimal amount;
	
    /**
     * 支付方式
     */
	@NotNull(message="支付方式不能为空")
    private Integer payType;

    /**
     * 凭证
     */
//	@NotBlank(message="凭证不能为空")
    private String voucher;

    /**
     * 备注
     */
    private String remark;

	
}
