package main.com.weixinApp.dao.search;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ConsumerOrderCarUploadCertificationPicSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = -1738082462642162823L;

	@NotNull(message="车辆ID不能为空")
	private Integer carId;
	
	@NotBlank(message="合格证不能为空")
	private String certificationPic;
}
