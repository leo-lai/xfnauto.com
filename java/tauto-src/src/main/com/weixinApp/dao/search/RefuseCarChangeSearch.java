package main.com.weixinApp.dao.search;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class RefuseCarChangeSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = 4653314333286472901L;
	
	@NotNull(message="车辆ID不能为空")
	private Integer carId;

}
