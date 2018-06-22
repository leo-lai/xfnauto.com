package main.com.customer.dao.vo;

import lombok.Data;
import main.com.customer.dao.po.CustomerOrderInPay;

@Data
public class CustomerOrderInPayVo extends CustomerOrderInPay{

	private String payMethodName;
	
	private String orgName;
	
	private Integer orgId;
}
