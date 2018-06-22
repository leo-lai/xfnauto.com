package main.com.car.dao.po;

import java.io.Serializable;

public class CarModeYear implements Serializable {
	private static final long serialVersionUID = 7678248867478794335L;
	private Integer carModeYearId;                //车系id
	private String carModeYearName;              //车系名称
	private Integer brandId;				//品牌id
	
	public String getCarModeYearName() {
		return carModeYearName;
	}
	public void setCarModeYearName(String carModeYearName) {
		this.carModeYearName = carModeYearName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getCarModeYearId() {
		return carModeYearId;
	}
	public void setCarModeYearId(Integer carModeYearId) {
		this.carModeYearId = carModeYearId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
}
