package main.com.weixinApp.dao.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ConsumerOrderInfoVO implements Serializable{
	 
	private static final long serialVersionUID = 8327701131389023900L;

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
     * 官方指导价
     */
    private BigDecimal guidePrice;

    /**
     * 定金
     */
    private BigDecimal depositPrice;

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
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    
    private Integer state;
    
    private List<ConsumerOrderCarVO> cars;
}
