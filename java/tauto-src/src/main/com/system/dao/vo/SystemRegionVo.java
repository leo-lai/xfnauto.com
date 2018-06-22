package main.com.system.dao.vo;

import java.util.List;

import main.com.system.dao.po.SystemRegion;

public class SystemRegionVo extends SystemRegion{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String regionId;
	private Integer parentId;	//int
	private String regionName;	//varchar
	private String shortName;	//varchar
	private Float longitude;	//float
	private Float latitude;	//float
	private Integer level;	//int
	private Integer status;	//int
	
    private List<SystemRegion> childrenModules;  
    
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<SystemRegion> getChildrenModules() {
		return childrenModules;
	}
	public void setChildrenModules(List<SystemRegion> childrenModules) {
		this.childrenModules = childrenModules;
	}
}
