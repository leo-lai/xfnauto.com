package main.com.logistics.dao.search;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class SignCarSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = -2045875921683363280L;

	private List<SignCarSubSearch> cars;
}
