package main.com.system.dao.search;

import java.util.Date;

import main.com.frame.search.BaseSearch;

public class SystemWarehouseSearch extends BaseSearch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		/**
	     * 组织仓库主键
	     */
	    private Integer warehouseId;

	    /**
	     * 组织仓库名称
	     */
	    private String warehouseName;

	    /**
	     * 组织ID
	     */
	    private Integer orgId;

	    /**
	     * 组织名称
	     */
	    private String orgName;

	    /**
	     * 组织code
	     */
	    private String orgCode;

	    /**
	     * 仓库类型
	     */
	    private Integer warehouseType;

	    /**
	     * 是否已删除
	     */
	    private Byte isDelete;

	    /**
	     * 创建日期
	     */
	    private Date create;

		public Integer getWarehouseId() {
			return warehouseId;
		}

		public void setWarehouseId(Integer warehouseId) {
			this.warehouseId = warehouseId;
		}

		public String getWarehouseName() {
			return warehouseName;
		}

		public void setWarehouseName(String warehouseName) {
			this.warehouseName = warehouseName;
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

		public String getOrgCode() {
			return orgCode;
		}

		public void setOrgCode(String orgCode) {
			this.orgCode = orgCode;
		}

		public Integer getWarehouseType() {
			return warehouseType;
		}

		public void setWarehouseType(Integer warehouseType) {
			this.warehouseType = warehouseType;
		}

		public Byte getIsDelete() {
			return isDelete;
		}

		public void setIsDelete(Byte isDelete) {
			this.isDelete = isDelete;
		}

		public Date getCreate() {
			return create;
		}

		public void setCreate(Date create) {
			this.create = create;
		}
}
