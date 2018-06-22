package main.com.logistics.dao.search;


import java.util.ArrayList;
import java.util.List;

import main.com.frame.search.BaseSearch;
import main.com.logistics.dao.vo.LogisticsDynamicLineInfoVo;

/**
 * @author Zwen
 *
 */
public class LogisticsDynamicLineSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer dynamicLineId;

    /**
     * 起步价 元/KM
     */
    private Double startPrice;

    /**
     * 起步里程 KM
     */
    private Double startingMileage;

    /**
     * 
     */
    private Double gradeFirst;

    /**
     * 
     */
    private Double gradeSecond;

    /**
     * 
     */
    private Double gradeThird;

    /**
     * 
     */
    private Double gradeFour;

    /**
     * 
     */
    private Double gradeFive;

    /**
     * 
     */
    private Double gradeSix;

    /**
     * 组织名称
     */
    private Integer orgId;

    /**
     * 组织名称
     */
    private String orgName;
    
    private String dynamicLineInfo;
    
    private Double additionalAmount;
    
    List<LogisticsDynamicLineInfoVo> list;
    
    

//	public LogisticsDynamicLineSearch(
//			List<LogisticsDynamicLineInfoVo> lineInfoVos) {
//		super();
//		this.lineInfoVos = new ArrayList<>();
//	}

	public Integer getDynamicLineId() {
		return dynamicLineId;
	}

	public List<LogisticsDynamicLineInfoVo> getList() {
		return list;
	}

	public void setList(List<LogisticsDynamicLineInfoVo> list) {
		this.list = list;
	}

	public void setDynamicLineId(Integer dynamicLineId) {
		this.dynamicLineId = dynamicLineId;
	}

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Double getStartingMileage() {
		return startingMileage;
	}

	public void setStartingMileage(Double startingMileage) {
		this.startingMileage = startingMileage;
	}

	public Double getGradeFirst() {
		return gradeFirst;
	}

	public void setGradeFirst(Double gradeFirst) {
		this.gradeFirst = gradeFirst;
	}

	public Double getGradeSecond() {
		return gradeSecond;
	}

	public void setGradeSecond(Double gradeSecond) {
		this.gradeSecond = gradeSecond;
	}

	public Double getGradeThird() {
		return gradeThird;
	}

	public void setGradeThird(Double gradeThird) {
		this.gradeThird = gradeThird;
	}

	public Double getGradeFour() {
		return gradeFour;
	}

	public void setGradeFour(Double gradeFour) {
		this.gradeFour = gradeFour;
	}

	public Double getGradeFive() {
		return gradeFive;
	}

	public void setGradeFive(Double gradeFive) {
		this.gradeFive = gradeFive;
	}

	public Double getGradeSix() {
		return gradeSix;
	}

	public void setGradeSix(Double gradeSix) {
		this.gradeSix = gradeSix;
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

	public String getDynamicLineInfo() {
		return dynamicLineInfo;
	}

	public void setDynamicLineInfo(String dynamicLineInfo) {
		this.dynamicLineInfo = dynamicLineInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getAdditionalAmount() {
		return additionalAmount;
	}

	public void setAdditionalAmount(Double additionalAmount) {
		this.additionalAmount = additionalAmount;
	}
}
