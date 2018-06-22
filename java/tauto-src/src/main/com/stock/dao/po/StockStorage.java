package main.com.stock.dao.po;

import java.io.Serializable;
import java.util.Date;

public class StockStorage implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 入库ID
     */
    private Integer storageId;

    /**
     * 入库单号
     */
    private String storageCode;

    /**
     * 入库时间
     */
    private Date createDate;

    /**
     * 供应商ID
     */
    private Integer supplierId;

    /**
     * 供应商Name
     */
    private String supplierName;

    /**
     * 
     */
    private Integer systemUsersId;

    /**
     * 采购员
     */
    private String systemUserName;

    /**
     * 总价
     */
    private Double totalPurchasePrice;

    /**
     * 采购总量
     */
    private Integer totalPurchase;
    
    private Integer source;

    /**
     * 物流总费用
     */
    private Double logisticsCost;//物流费用
    
    private Boolean isDelete;
    
    private Integer storageSource; //车辆来源 1资源采购 2 4S店出货
    
    private Integer orgId;
    
    private String orgName;
    
    private String remarks;
    private String contractNumber;//合同编号
    private String contractImage;//合同扫描件

    /**
     * 合格证到达时间
     */
    private Integer certificateDate;
    /**
     * 确定全部入库
     */
    private Integer overSure;
    /**
     * 入库ID
     * @return storage_id 入库ID
     */
    public Integer getStorageId() {
        return storageId;
    }

    /**
     * 入库ID
     * @param storageId 入库ID
     */
    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    /**
     * 入库单号
     * @return storage_code 入库单号
     */
    public String getStorageCode() {
        return storageCode;
    }

    /**
     * 入库单号
     * @param storageCode 入库单号
     */
    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    /**
     * 入库时间
     * @return create_date 入库时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 入库时间
     * @param createDate 入库时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 供应商ID
     * @return supplier_id 供应商ID
     */
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * 供应商ID
     * @param supplierId 供应商ID
     */
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * 供应商Name
     * @return supplier_name 供应商Name
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * 供应商Name
     * @param supplierName 供应商Name
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * 
     * @return system_users_id 
     */
    public Integer getSystemUsersId() {
        return systemUsersId;
    }

    /**
     * 
     * @param systemUsersId 
     */
    public void setSystemUsersId(Integer systemUsersId) {
        this.systemUsersId = systemUsersId;
    }

    /**
     * 采购员
     * @return system_user_name 采购员
     */
    public String getSystemUserName() {
        return systemUserName;
    }

    /**
     * 采购员
     * @param systemUserName 采购员
     */
    public void setSystemUserName(String systemUserName) {
        this.systemUserName = systemUserName;
    }

    /**
     * 总价
     * @return total_purchase_price 总价
     */
    public Double getTotalPurchasePrice() {
        return totalPurchasePrice;
    }

    /**
     * 总价
     * @param totalPurchasePrice 总价
     */
    public void setTotalPurchasePrice(Double totalPurchasePrice) {
        this.totalPurchasePrice = totalPurchasePrice;
    }

    /**
     * 采购总量
     * @return total_purchase 采购总量
     */
    public Integer getTotalPurchase() {
        return totalPurchase;
    }

    /**
     * 采购总量
     * @param totalPurchase 采购总量
     */
    public void setTotalPurchase(Integer totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

    /**
     * 物流总费用
     * @return logistics_cost 物流总费用
     */
    public Double getLogisticsCost() {
        return logisticsCost;
    }

    /**
     * 物流总费用
     * @param logisticsCost 物流总费用
     */
    public void setLogisticsCost(Double logisticsCost) {
        this.logisticsCost = logisticsCost;
    }

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getStorageSource() {
		return storageSource;
	}

	public void setStorageSource(Integer storageSource) {
		this.storageSource = storageSource;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getContractImage() {
		return contractImage;
	}

	public void setContractImage(String contractImage) {
		this.contractImage = contractImage;
	}

	public Integer getCertificateDate() {
		return certificateDate;
	}

	public void setCertificateDate(Integer certificateDate) {
		this.certificateDate = certificateDate;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getOverSure() {
		return overSure;
	}

	public void setOverSure(Integer overSure) {
		this.overSure = overSure;
	}
}