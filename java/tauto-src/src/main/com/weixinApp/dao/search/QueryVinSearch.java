package main.com.weixinApp.dao.search;

import java.io.Serializable;

import lombok.Data;
import main.com.frame.search.BaseSearch;
import main.com.system.dao.po.SystemUsers;

@Data
public class QueryVinSearch extends BaseSearch implements Serializable{
	
	
	private static final long serialVersionUID = -7801937675478914820L;
	
	private Integer infoId;
	
	private SystemUsers currentUser;

}
