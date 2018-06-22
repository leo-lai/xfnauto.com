package main.com.weixinApp.dao.search;

import java.io.Serializable;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class RedistributeCarSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = 5769318147521425753L;
	
	private Integer carId;
	
	private Integer stockCarId;
}
