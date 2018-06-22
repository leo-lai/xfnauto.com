package main.com.logistics.dao.search;

import java.io.Serializable;

/** 
* @author liaozijie 
* @version 创建时间：2018年1月17日 上午10:38:45 
* 类描述： 
*/
public class CarInDistributionSearch implements Serializable{
	
	
	private static final long serialVersionUID = 3630835365097462105L;
	
	/**
	 * 配送单ID
	 */
	private Integer distributionId;

	/**
	 * 配送单号
	 */
	private String distributionCode;
	
	/**
	 * 配送单状态 1取消 1等待配送 2配送中 3完成
	 */
	private Integer distributionState;
	
	

	public Integer getDistributionId() {
		return distributionId;
	}

	public void setDistributionId(Integer distributionId) {
		this.distributionId = distributionId;
	}

	public String getDistributionCode() {
		return distributionCode;
	}

	public void setDistributionCode(String distributionCode) {
		this.distributionCode = distributionCode;
	}

	public Integer getDistributionState() {
		return distributionState;
	}

	public void setDistributionState(Integer distributionState) {
		this.distributionState = distributionState;
	}
	
	

}
