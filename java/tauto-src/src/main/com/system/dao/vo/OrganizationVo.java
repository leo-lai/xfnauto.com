package main.com.system.dao.vo;

import java.util.List;
import java.util.Set;

import main.com.system.dao.po.Organization;

public class OrganizationVo extends Organization{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Organization orgParent;
	
	private List<OrganizationVo> childrens;
	
	private Double distance; //距离
	
	private Double discount; //优惠
	
	private Double price;

	public Organization getOrgParent() {
		return orgParent;
	}

	public void setOrgParent(Organization orgParent) {
		this.orgParent = orgParent;
	}

	public List<OrganizationVo> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<OrganizationVo> childrens) {
		this.childrens = childrens;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
