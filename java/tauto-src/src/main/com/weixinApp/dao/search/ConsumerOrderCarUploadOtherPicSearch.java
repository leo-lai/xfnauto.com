package main.com.weixinApp.dao.search;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ConsumerOrderCarUploadOtherPicSearch extends BaseSearch implements Serializable{

	private static final long serialVersionUID = 4864713466395594011L;

	@NotNull(message="车辆ID不能为空")
	private Integer carId;
	
	@NotBlank(message="图片不能为空")
	private String otherPic;
}
