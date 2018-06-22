package main.com.car.dao.search;

import java.util.Date;

import main.com.frame.search.BaseSearch;

public class CarSupplierSearch extends BaseSearch{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 供应商ID
     */
    private Integer supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 电话号
     */
    private String phoneNumber;

    /**
     * 
     */
    private String remark;

    /**
     * 组织ID
     */
    private Integer orgId;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 是否已删除
     */
    private Byte isDelete;

    /**
     * 创建日期
     */
    private Date createrDate;

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
     * 供应商名称
     * @return supplier_name 供应商名称
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * 供应商名称
     * @param supplierName 供应商名称
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * 电话号
     * @return phone_number 电话号
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 电话号
     * @param phoneNumber 电话号
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
     * 组织ID
     * @return org_id 组织ID
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 组织ID
     * @param orgId 组织ID
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 组织名称
     * @return org_name 组织名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 组织名称
     * @param orgName 组织名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 是否已删除
     * @return is_delete 是否已删除
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * 是否已删除
     * @param isDelete 是否已删除
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 创建日期
     * @return creater_date 创建日期
     */
    public Date getCreaterDate() {
        return createrDate;
    }

    /**
     * 创建日期
     * @param createrDate 创建日期
     */
    public void setCreaterDate(Date createrDate) {
        this.createrDate = createrDate;
    }
}
