package main.com.logistics.dao.search;

import java.io.Serializable;
import main.com.frame.search.BaseSearch;

/** 
* @author liaozijie 
* @version 创建时间：2018年1月15日 下午5:18:25 
* 类描述： 
*/
public class DriverDistributionSearch extends BaseSearch implements Serializable{

	private static final long serialVersionUID = 38245952843202357L;

    /**
     * 司机ID
     */
    private Integer driverId;
    
    /**
     * 配送订单ID
     */
    private Integer distributionId;

    /**
     * 状态 -1取消 1等待配送 2配送中 3完成
     */
    private Integer distributionState;
    
    @Override
    public String getSessionId() {
    	// TODO Auto-generated method stub
    	return super.getSessionId();
    }

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getDistributionState() {
		return distributionState;
	}

	public void setDistributionState(Integer distributionState) {
		this.distributionState = distributionState;
	}
    
    

	
}
