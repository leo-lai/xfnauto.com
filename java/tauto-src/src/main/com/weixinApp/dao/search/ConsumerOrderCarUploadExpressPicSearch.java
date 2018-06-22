package main.com.weixinApp.dao.search;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ConsumerOrderCarUploadExpressPicSearch extends BaseSearch implements Serializable{

	private static final long serialVersionUID = 5324696153110207850L;

	@NotNull(message="车辆ID不能为空")
	private Integer carId;
	
	@NotBlank(message="快递单不能为空")
	private String expressPic;
}
