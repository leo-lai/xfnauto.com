package main.com.car.dao.search;

import java.math.BigDecimal;
import java.util.Date;

import main.com.frame.search.BaseSearch;

public class OrgCarsConfigureSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 主键
     */
    private Integer orgCarsConfigureId;

    /**
     * 组织ID
     */
    private Integer orgId;

    /**
     * 车大类ID
     */
    private Integer carsId;
    /**
     * 组织类别
     */
    private Integer orgLevel;

    /**
     * 是否已删除
     */
    private Boolean isDelete;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 是否线上展示
     */
    private Boolean isOnLine;

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
    private BigDecimal discountPrice;

    /**
     * 官方指导价
     */
    private BigDecimal guidingPrice;

    /**
     * 备用一
     */
    private String brandId;

    /**
     * 备用二
     */
    private Integer familyId;

    /**
     * 备用三
     */
    private String vehicleName;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 内饰ID
     */
    private Integer interiorId;

    /**
     * 颜色ID
     */
    private Integer colourId;

    /**
     * 车大类中文介绍
     */
    private String carsInfo;

    /**
     * 颜色
     */
    private String colourName;

    /**
     * 内饰
     */
    private String interiorName;
    
    /**
     * 最大优惠值
     */
    private BigDecimal maxDiscount;
    
    private String carsName;

    /**
     * 主键
     * @return org_cars_configure_id 主键
     */
    public Integer getOrgCarsConfigureId() {
        return orgCarsConfigureId;
    }

    /**
     * 主键
     * @param orgCarsConfigureId 主键
     */
    public void setOrgCarsConfigureId(Integer orgCarsConfigureId) {
        this.orgCarsConfigureId = orgCarsConfigureId;
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
     * @return is_delete 是否已删除
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 是否已删除
     * @param isDelete 是否已删除
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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
     * 是否线上展示
     * @return is_on_line 是否线上展示
     */
    public Boolean getIsOnLine() {
        return isOnLine;
    }

    /**
     * 是否线上展示
     * @param isOnLine 是否线上展示
     */
    public void setIsOnLine(Boolean isOnLine) {
        this.isOnLine = isOnLine;
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
     * @return discount_price 优惠金额
     */
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    /**
     * 优惠金额
     * @param discountPrice 优惠金额
     */
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
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

    public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}


    public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
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
     * 内饰ID
     * @return interior_id 内饰ID
     */
    public Integer getInteriorId() {
        return interiorId;
    }

    /**
     * 内饰ID
     * @param interiorId 内饰ID
     */
    public void setInteriorId(Integer interiorId) {
        this.interiorId = interiorId;
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
     * @return cars_info 车大类中文介绍
     */
    public String getCarsInfo() {
        return carsInfo;
    }

    /**
     * 车大类中文介绍
     * @param carsInfo 车大类中文介绍
     */
    public void setCarsInfo(String carsInfo) {
        this.carsInfo = carsInfo;
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
     * 内饰
     * @return interior_name 内饰
     */
    public String getInteriorName() {
        return interiorName;
    }

    /**
     * 内饰
     * @param interiorName 内饰
     */
    public void setInteriorName(String interiorName) {
        this.interiorName = interiorName;
    }

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

	public BigDecimal getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(BigDecimal maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public String getCarsName() {
		return carsName;
	}

	public void setCarsName(String carsName) {
		this.carsName = carsName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
