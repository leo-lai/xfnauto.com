package main.com.weixinApp.dao.search;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ConsumerOrderCarUploadTciPicSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = 7327832505297053145L;

	@NotNull(message="车辆ID不能为空")
	private Integer carId;
	
	@NotBlank(message="交强险不能为空")
	private String tciPic;
}
