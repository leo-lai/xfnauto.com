package main.com.weixinApp.dao.search;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ConsumerOrderInfoCreateSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = -2369895022406375175L;

    /**
     * 车大类ID
     */
	@NotNull(message="车型不能为空")
    private Integer carsId;

    /**
     * 车大类名称
     */
    private String carsName;

    /**
     * C端订单ID
     */
    @NotNull(message="订单ID不能为空")
    private Integer orderId;

    /**
     * 客户ID
     */
    private Integer customerId;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 车身颜色ID
     */
    @NotNull(message="车身颜色不能为空")
    private Integer colorId;


    /**
     * 内饰ID
     */
    private Integer interiorId;

    /**
     * 车系ID
     */
    private Integer familyId;

    /**
     * 车系名称
     */
    private String familyName;

    /**
     * 汽车数量
     */
    @NotNull(message="数量不能为空")
    @Max(value=20,message="同一车型订购数量最多为20辆")
    private Integer carNum;

    /**
     * 定金
     */
    @NotNull(message="定金不能为空")
    private BigDecimal depositPrice;

    /**
     * 成交价
     */
    private BigDecimal finalPrice;
    
    /**
	 * 裸车价
	 */
    @NotNull(message="裸车价不能为空")
	private BigDecimal nakedPrice;
	
	/**
	 * 交强险
	 */
	private BigDecimal trafficCompulsoryInsurancePrice;
	
	/**
	 * 商业险
	 */

	private BigDecimal commercialInsurancePrice;

    /**
     * 价格变动（+为加价，-为减价）
     */
    private BigDecimal changePrice;

    /**
     * 票据信息
     */
    private String ticketPic;

    /**
     * 备注
     */
    private String remark;

}
