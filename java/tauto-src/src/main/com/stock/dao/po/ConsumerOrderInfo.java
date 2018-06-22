package main.com.stock.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
@Data
public class ConsumerOrderInfo implements Serializable{
	
	private static final long serialVersionUID = 5682932492625805960L;

	/**
     * ID
     */
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
     * 客户ID
     */
    private Integer customerId;

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
     * 裸车价
     */
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
     * 官方指导价
     */
    private BigDecimal guidePrice;

    /**
     * 定金
     */
    private BigDecimal depositPrice;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态 1：待配车 2：待验车 3：待换车 4：待协商 5：验车完成
     */
    private Integer state;

    /**
     * 
     */
    private Integer isDel;

    
}