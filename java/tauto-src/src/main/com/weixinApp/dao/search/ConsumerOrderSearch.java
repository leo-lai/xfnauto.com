package main.com.weixinApp.dao.search;

import lombok.Data;
import main.com.frame.search.BaseSearch;

@Data
public class ConsumerOrderSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * ID
     */
    private Integer id;
	 /**
     * 退订原因
     */
    private String countermandReason;
    /**
     * 是否已申请退订（审核通过就置为退订状态，把申请置为否的状态。退订状态为0，包含在订单状态里）
     */
    private Boolean countermandApply;
    /**
     * 退订截图
     */
    private String countermandPic;
    
    /**
     * 是否同意 拒绝0 同意1
     */
    private Boolean overPass;
    
    /**
     * 同意备注
     */
    private String remarks;
    
    /**
     * 退款凭证
     */
    private String voucherPic;
}
