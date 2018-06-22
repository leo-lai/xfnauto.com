package main.com.weixinApp.dao.search;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ConsumerOrderCarUploadCiPicSearch extends BaseSearch implements Serializable{

	private static final long serialVersionUID = 7812221565090396146L;

	@NotNull(message="车辆ID不能为空")
	private Integer carId;
	
	@NotBlank(message="商业险不能为空")
	private String ciPic;
}
