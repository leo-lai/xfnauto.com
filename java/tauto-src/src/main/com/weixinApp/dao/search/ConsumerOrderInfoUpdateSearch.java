package main.com.weixinApp.dao.search;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;
import main.com.frame.search.BaseSearch;

@Data
public class ConsumerOrderInfoUpdateSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = -4473806796488790127L;

	/**
     * ID
     */
	@NotNull(message="子单ID不能为空")
    private Integer id;

    /**
     * 车大类ID
     */
    private Integer carsId;

    /**
     * 车大类名称
     */
    private String carsName;

    /**
     * C端订单ID
     */
    private Integer orderId;
    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 车身颜色ID
     */
    private Integer colorId;

    /**
     * 车身颜色名称
     */
    private String colorName;

    /**
     * 内饰ID
     */
    private Integer interiorId;

    /**
     * 内饰名称
     */
    private String interiorName;

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
    private Integer carNum;


    /**
     * 官方指导价
     */
    private BigDecimal guidePrice;

    /**
     * 定金
     */
    private BigDecimal depositPrice;

    /**
     * 成交价
     */
    private BigDecimal finalPrice;
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
    
    /**
     * 
     */
    private Integer isDel;
}
