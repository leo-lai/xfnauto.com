package main.com.weixinHtml.dao.search;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import main.com.frame.search.BaseSearch;

@Data
public class ShopGoodsCarsSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * 省
     */
    private String provinceId;
    /**
     * 市
     */
    private String cityId;
    /**
     * 区
     */
    private String areaId;    
    /**
     * 品牌
     */
    private Integer brandId;

    /**
     * 主键
     */
    private Integer goodsCarsId;

    /**
     * 组织ID
     */
    private Integer orgId;

    /**
     * 车打雷ID
     */
    private Integer carsId;

    /**
     * 是否已删除
     */
    private Byte overDelete;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 发票金额
     */
    private BigDecimal invoicePrice;

    /**
     * 定金
     */
    private BigDecimal depositPrice;

    /**
     * 优惠金额
     */
    private BigDecimal discountPriceOnLine;

    /**
     * 官方指导价
     */
    private BigDecimal guidingPrice;

    /**
     * 车系id
     */
    private Integer familyId;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 颜色ID
     */
    private Integer colourId;

    /**
     * 车大类中文介绍
     */
    private String carsName;

    /**
     * 颜色
     */
    private String colourName;

    /**
     * 是否下架
     */
    private Boolean overOffShelf;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 可售数量
     */
    private Integer saleingNumber;

    /**
     * 线下优惠 负数为优惠，正数为加价
     */
    private BigDecimal discountPriceUnderLine;

    /**
     * 是否携带交强险 0不带 1带
     */
    private Boolean overInsurance;

    /**
     * 线上裸车价
     */
    private BigDecimal bareCarPriceOnLine;

    /**
     * 线下裸车价
     */
    private BigDecimal bareCarPriceUnderLine;

    /**
     * 仓位ID
     */
    private Integer warehouseId;

    /**
     * 仓位名称
     */
    private String warehouseName;

    /**
     * 物流周期
     */
    private Integer logisticsCycle;

    /**
     * 物流费用
     */
    private BigDecimal logisticsPrice;

    /**
     * 发票周期
     */
    private Integer invoiceCycle;

    /**
     * 生产日期
     */
    private String dateOfManufacture;

    /**
     * 车辆图片
     */
    private String carsImage;
    /**
     * 车辆内饰
     */
    private Integer interiorId;
    /**
     * 车辆内饰
     */
    private String interiorName;
    /**
     * 车辆内饰
     */
    private Boolean isNew;
    
    private String carsImages;
    
    private Double longitude;
    private Double latitude;
}
