package main.com.weixinApp.dao.search;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ConsumerOrderInfoChangePriceSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = 7808081378345276407L;

	@NotNull(message="子单ID不能为空")
	private Integer infoId;
	
	/**
     * 
     */
	@NotNull(message="优惠价不能为空")
    private BigDecimal changePrice;

    /**
     * 交强险
     */
    private BigDecimal trafficCompulsoryInsurancePrice;

    /**
     * 商业险
     */
    private BigDecimal commercialInsurancePrice;
	
	private String remark;
	
}
