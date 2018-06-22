package main.com.weixinApp.dao.search;

import java.io.Serializable;

import lombok.Data;
import main.com.frame.search.BaseSearch;
import main.com.system.dao.po.SystemUsers;
@Data
public class OrderUpdateStateSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = -1627553521127907116L;

	private Integer orderId;
	
	private Integer state;
	
	private SystemUsers currentUser;
	
}
