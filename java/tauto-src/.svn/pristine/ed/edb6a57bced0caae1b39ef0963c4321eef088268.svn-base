package main.com.weixinApp.dao.search;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import main.com.frame.search.BaseSearch;

@Data
public class DistributeCarSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = -4180342882860909898L;
	
	@NotNull(message="infoId不能为空")
	private Integer infoId;
	
	@NotBlank(message="配车ID不能为空")
	private String stockCarIds;
}
