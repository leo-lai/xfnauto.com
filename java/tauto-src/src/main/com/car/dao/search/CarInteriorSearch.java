package main.com.car.dao.search;

import main.com.frame.search.BaseSearch;

public class CarInteriorSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  /**
     * 内饰名称
     */
    private Integer interiorId;

    /**
     * 
     */
    private String interiorName;

    /**
     * 车系ID
     */
    private Integer familyId;
    
    private Boolean isDelete;

    /**
     * 内饰名称
     * @return interior_id 内饰名称
     */
    public Integer getInteriorId() {
        return interiorId;
    }

    /**
     * 内饰名称
     * @param interiorId 内饰名称
     */
    public void setInteriorId(Integer interiorId) {
        this.interiorId = interiorId;
    }

    /**
     * 
     * @return interior_name 
     */
    public String getInteriorName() {
        return interiorName;
    }

    /**
     * 
     * @param interiorName 
     */
    public void setInteriorName(String interiorName) {
        this.interiorName = interiorName;
    }

//    /**
//     * 品牌ID
//     * @return brand_id 品牌ID
//     */
//    public Integer getBrandId() {
//        return brandId;
//    }
//
//    /**
//     * 品牌ID
//     * @param brandId 品牌ID
//     */
//    public void setBrandId(Integer brandId) {
//        this.brandId = brandId;
//    }

	public Boolean getIsDelete() {
		return isDelete;
	}

	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
}
