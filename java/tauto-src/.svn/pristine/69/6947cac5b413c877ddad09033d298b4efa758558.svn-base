package main.com.weixinApp.dao.search;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ConsumerOrderInfoSearch implements Serializable{
	
	private static final long serialVersionUID = -6948801925706145399L;

	/**
	 * 车大类id
	 */
	@NotNull(message="carsId不能为空")
	private Integer carsId;
	
	
	/**
	 * 车身颜色ID
	 */
	private Integer colorId;
	
	
	/**
	 * 内饰ID
	 */
	private Integer interiorId;
	
	/**
	 * 定金
	 */
	@NotNull(message="定金不能为空")
	private BigDecimal depositPrice;
	
	/**
	 * 裸车价
	 */
    //private BigDecimal nakedPrice;
	
	/**
	 * 交强险
	 */
    @Min(value=0,message="交强险不能低于0")
	private BigDecimal trafficCompulsoryInsurancePrice;
	
	/**
	 * 商业险
	 */
    @Min(value=0,message="商业险不能低于0")
	private BigDecimal commercialInsurancePrice;
	
	/**
	 * 加价/减价
	 */
	private BigDecimal changePrice;
	
	/**
	 * 备注
	 */
	private String remark;
	
	@NotNull(message="数量不能为空")
	@Max(value=20,message="同一车型订购数量最多为20辆")
	private Integer carNum;
}
