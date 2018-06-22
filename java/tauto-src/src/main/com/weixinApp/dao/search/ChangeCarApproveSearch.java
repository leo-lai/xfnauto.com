package main.com.weixinApp.dao.search;

import java.io.Serializable;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ChangeCarApproveSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = 7080445861856836680L;
	
	private Integer carId;

}
