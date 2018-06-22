package main.com.logistics.dao.search;

import java.io.Serializable;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class MakeCarArrivedSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = -1064593298892861485L;

	private Integer consignmentId;
	
	private Integer distributionId;

}
