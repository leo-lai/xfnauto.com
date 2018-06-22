package main.com.weixinHtml.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
public class ShopGoodsCarsActivity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer goodsCarsActivityId;

    /**
     * 
     */
    private Integer orgId;

    /**
     * 车打雷ID
     */
    private Integer carsId;

    /**
     * 是否已删除
     */
    private Boolean overDelete;

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
     * 备用二
     */
    private Integer brandId;

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
     * 是否下架 0上架 1新建+下架
     */
    private Boolean overOffShelf;

    /**
     * 
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
    private Date dateOfManufacture;

    /**
     * 车辆图片
     */
    private String carsImage;

    /**
     * 内饰
     */
    private Integer interiorId;

    /**
     * 内饰名称
     */
    private String interiorName;

    /**
     * 省份
     */
    private String provinceId;

    /**
     * 城市
     */
    private String cityId;

    /**
     * 地区
     */
    private String areaId;

    /**
     * 上架时间
     */
    private Date onShelfDate;

    /**
     * 活动价
     */
    private BigDecimal activityPrice;
    
    /**
     * 是否已过期
     */
    private Integer state;
    /**
     * 轮播图
     */
    private String carsImages;
    private String image;
    private String provinceName;
    private String cityName;
    private String areaName;
    private Integer systemUsersId;
    
    /**
     * 所属组织代码
     */
    private String orgCode;
    
    private Double longitude;
    private Double latitude;
    
    /**
     * 主键
     * @return goods_cars_activity_id 主键
     */
    public Integer getGoodsCarsActivityId() {
        return goodsCarsActivityId;
    }

    /**
     * 主键
     * @param goodsCarsActivityId 主键
     */
    public void setGoodsCarsActivityId(Integer goodsCarsActivityId) {
        this.goodsCarsActivityId = goodsCarsActivityId;
    }

    /**
     * 
     * @return org_id 
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 
     * @param orgId 
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 车打雷ID
     * @return cars_id 车打雷ID
     */
    public Integer getCarsId() {
        return carsId;
    }

    /**
     * 车打雷ID
     * @param carsId 车打雷ID
     */
    public void setCarsId(Integer carsId) {
        this.carsId = carsId;
    }

    /**
     * 是否已删除
     * @return over_delete 是否已删除
     */
    public Boolean getOverDelete() {
        return overDelete;
    }

    /**
     * 是否已删除
     * @param overDelete 是否已删除
     */
    public void setOverDelete(Boolean overDelete) {
        this.overDelete = overDelete;
    }

    /**
     * 单价
     * @return unit_price 单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 单价
     * @param unitPrice 单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 创建日期
     * @return create_date 创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建日期
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 发票金额
     * @return invoice_price 发票金额
     */
    public BigDecimal getInvoicePrice() {
        return invoicePrice;
    }

    /**
     * 发票金额
     * @param invoicePrice 发票金额
     */
    public void setInvoicePrice(BigDecimal invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    /**
     * 定金
     * @return deposit_price 定金
     */
    public BigDecimal getDepositPrice() {
        return depositPrice;
    }

    /**
     * 定金
     * @param depositPrice 定金
     */
    public void setDepositPrice(BigDecimal depositPrice) {
        this.depositPrice = depositPrice;
    }

    /**
     * 优惠金额
     * @return discount_price_on_line 优惠金额
     */
    public BigDecimal getDiscountPriceOnLine() {
        return discountPriceOnLine;
    }

    /**
     * 优惠金额
     * @param discountPriceOnLine 优惠金额
     */
    public void setDiscountPriceOnLine(BigDecimal discountPriceOnLine) {
        this.discountPriceOnLine = discountPriceOnLine;
    }

    /**
     * 官方指导价
     * @return guiding_price 官方指导价
     */
    public BigDecimal getGuidingPrice() {
        return guidingPrice;
    }

    /**
     * 官方指导价
     * @param guidingPrice 官方指导价
     */
    public void setGuidingPrice(BigDecimal guidingPrice) {
        this.guidingPrice = guidingPrice;
    }

    /**
     * 车系id
     * @return family_id 车系id
     */
    public Integer getFamilyId() {
        return familyId;
    }

    /**
     * 车系id
     * @param familyId 车系id
     */
    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    /**
     * 备用二
     * @return brand_id 备用二
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * 备用二
     * @param brandId 备用二
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * 备注
     * @return remarks 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 颜色ID
     * @return colour_id 颜色ID
     */
    public Integer getColourId() {
        return colourId;
    }

    /**
     * 颜色ID
     * @param colourId 颜色ID
     */
    public void setColourId(Integer colourId) {
        this.colourId = colourId;
    }

    /**
     * 车大类中文介绍
     * @return cars_name 车大类中文介绍
     */
    public String getCarsName() {
        return carsName;
    }

    /**
     * 车大类中文介绍
     * @param carsName 车大类中文介绍
     */
    public void setCarsName(String carsName) {
        this.carsName = carsName;
    }

    /**
     * 颜色
     * @return colour_name 颜色
     */
    public String getColourName() {
        return colourName;
    }

    /**
     * 颜色
     * @param colourName 颜色
     */
    public void setColourName(String colourName) {
        this.colourName = colourName;
    }

    /**
     * 是否下架 0上架 1新建+下架
     * @return over_off_shelf 是否下架 0上架 1新建+下架
     */
    public Boolean getOverOffShelf() {
        return overOffShelf;
    }

    /**
     * 是否下架 0上架 1新建+下架
     * @param overOffShelf 是否下架 0上架 1新建+下架
     */
    public void setOverOffShelf(Boolean overOffShelf) {
        this.overOffShelf = overOffShelf;
    }

    /**
     * 
     * @return org_name 
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 
     * @param orgName 
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 可售数量
     * @return saleing_number 可售数量
     */
    public Integer getSaleingNumber() {
        return saleingNumber;
    }

    /**
     * 可售数量
     * @param saleingNumber 可售数量
     */
    public void setSaleingNumber(Integer saleingNumber) {
        this.saleingNumber = saleingNumber;
    }

    /**
     * 线下优惠 负数为优惠，正数为加价
     * @return discount_price_under_line 线下优惠 负数为优惠，正数为加价
     */
    public BigDecimal getDiscountPriceUnderLine() {
        return discountPriceUnderLine;
    }

    /**
     * 线下优惠 负数为优惠，正数为加价
     * @param discountPriceUnderLine 线下优惠 负数为优惠，正数为加价
     */
    public void setDiscountPriceUnderLine(BigDecimal discountPriceUnderLine) {
        this.discountPriceUnderLine = discountPriceUnderLine;
    }

    /**
     * 是否携带交强险 0不带 1带
     * @return over_insurance 是否携带交强险 0不带 1带
     */
    public Boolean getOverInsurance() {
        return overInsurance;
    }

    /**
     * 是否携带交强险 0不带 1带
     * @param overInsurance 是否携带交强险 0不带 1带
     */
    public void setOverInsurance(Boolean overInsurance) {
        this.overInsurance = overInsurance;
    }

    /**
     * 线上裸车价
     * @return bare_car_price_on_line 线上裸车价
     */
    public BigDecimal getBareCarPriceOnLine() {
        return bareCarPriceOnLine;
    }

    /**
     * 线上裸车价
     * @param bareCarPriceOnLine 线上裸车价
     */
    public void setBareCarPriceOnLine(BigDecimal bareCarPriceOnLine) {
        this.bareCarPriceOnLine = bareCarPriceOnLine;
    }

    /**
     * 线下裸车价
     * @return bare_car_price_under_line 线下裸车价
     */
    public BigDecimal getBareCarPriceUnderLine() {
        return bareCarPriceUnderLine;
    }

    /**
     * 线下裸车价
     * @param bareCarPriceUnderLine 线下裸车价
     */
    public void setBareCarPriceUnderLine(BigDecimal bareCarPriceUnderLine) {
        this.bareCarPriceUnderLine = bareCarPriceUnderLine;
    }

    /**
     * 仓位ID
     * @return warehouse_id 仓位ID
     */
    public Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * 仓位ID
     * @param warehouseId 仓位ID
     */
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * 仓位名称
     * @return warehouse_name 仓位名称
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * 仓位名称
     * @param warehouseName 仓位名称
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    /**
     * 物流周期
     * @return logistics_cycle 物流周期
     */
    public Integer getLogisticsCycle() {
        return logisticsCycle;
    }

    /**
     * 物流周期
     * @param logisticsCycle 物流周期
     */
    public void setLogisticsCycle(Integer logisticsCycle) {
        this.logisticsCycle = logisticsCycle;
    }

    /**
     * 物流费用
     * @return logistics_price 物流费用
     */
    public BigDecimal getLogisticsPrice() {
        return logisticsPrice;
    }

    /**
     * 物流费用
     * @param logisticsPrice 物流费用
     */
    public void setLogisticsPrice(BigDecimal logisticsPrice) {
        this.logisticsPrice = logisticsPrice;
    }

    /**
     * 发票周期
     * @return invoice_cycle 发票周期
     */
    public Integer getInvoiceCycle() {
        return invoiceCycle;
    }

    /**
     * 发票周期
     * @param invoiceCycle 发票周期
     */
    public void setInvoiceCycle(Integer invoiceCycle) {
        this.invoiceCycle = invoiceCycle;
    }

    /**
     * 生产日期
     * @return date_of_manufacture 生产日期
     */
    @JsonFormat(pattern="yyyy-MM")
    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    /**
     * 生产日期
     * @param dateOfManufacture 生产日期
     */
    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    /**
     * 车辆图片
     * @return cars_image 车辆图片
     */
    public String getCarsImage() {
        return carsImage;
    }

    /**
     * 车辆图片
     * @param carsImage 车辆图片
     */
    public void setCarsImage(String carsImage) {
        this.carsImage = carsImage;
    }

    /**
     * 内饰
     * @return interior_id 内饰
     */
    public Integer getInteriorId() {
        return interiorId;
    }

    /**
     * 内饰
     * @param interiorId 内饰
     */
    public void setInteriorId(Integer interiorId) {
        this.interiorId = interiorId;
    }

    /**
     * 内饰名称
     * @return interior_name 内饰名称
     */
    public String getInteriorName() {
        return interiorName;
    }

    /**
     * 内饰名称
     * @param interiorName 内饰名称
     */
    public void setInteriorName(String interiorName) {
        this.interiorName = interiorName;
    }

    /**
     * 省份
     * @return province_id 省份
     */
    public String getProvinceId() {
        return provinceId;
    }

    /**
     * 省份
     * @param provinceId 省份
     */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 城市
     * @return city_id 城市
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 城市
     * @param cityId 城市
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * 地区
     * @return area_id 地区
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * 地区
     * @param areaId 地区
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
     * 活动价
     * @return activity_price 活动价
     */
    public BigDecimal getActivityPrice() {
        return activityPrice;
    }

    /**
     * 活动价
     * @param activityPrice 活动价
     */
    public void setActivityPrice(BigDecimal activityPrice) {
        this.activityPrice = activityPrice;
    }
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getOnShelfDate() {
		return onShelfDate;
	}

	public void setOnShelfDate(Date onShelfDate) {
		this.onShelfDate = onShelfDate;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCarsImages() {
		return carsImages;
	}

	public void setCarsImages(String carsImages) {
		this.carsImages = carsImages;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getSystemUsersId() {
		return systemUsersId;
	}

	public void setSystemUsersId(Integer systemUsersId) {
		this.systemUsersId = systemUsersId;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
}