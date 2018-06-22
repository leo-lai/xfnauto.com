package main.com.weixinApp.dao.search;

import java.io.Serializable;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ListOrderSearch  extends BaseSearch implements Serializable{

	private static final long serialVersionUID = 7510435733568170342L;
	
	private String keywords;
	
	private Integer orgId;
	
	private Integer state;
	
	private Integer currentOrgId;
	
	private String orgCode;
	
	private String phoneNumber;
	
//	private String frameNumber;
}
