package main.com.car.dao.search;

import main.com.frame.search.BaseSearch;

public class CarsStyleSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer carStyleId;                //款式ID
	private String carStyleName;                //款式名称（配置级别）
	private Integer familyId;   				//车系id
	private Integer carFamilyId;               //兼容
	
	public Integer getCarStyleId() {
		return carStyleId;
	}
	public void setCarStyleId(Integer carStyleId) {
		this.carStyleId = carStyleId;
	}
	public String getCarStyleName() {
		return carStyleName;
	}
	public void setCarStyleName(String carStyleName) {
		this.carStyleName = carStyleName;
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
	public Integer getCarFamilyId() {
		return carFamilyId;
	}
	public void setCarFamilyId(Integer carFamilyId) {
		this.carFamilyId = carFamilyId;
	}
}
