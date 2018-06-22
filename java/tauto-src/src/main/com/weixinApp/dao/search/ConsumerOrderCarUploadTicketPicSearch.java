package main.com.weixinApp.dao.search;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ConsumerOrderCarUploadTicketPicSearch extends BaseSearch implements Serializable{
	

	private static final long serialVersionUID = 1165051762053756822L;

	@NotNull(message="车辆ID不能为空")
	private Integer carId;
	
	@NotBlank(message="票证不能为空")
	private String ticketPic;
	
	@NotBlank(message="合格证不能为空")
	private String certificationPic;
	
	@NotBlank(message="交强险不能为空")
	private String tciPic;
	
	@NotBlank(message="商业险不能为空")
	private String ciPic;
	
	@NotBlank(message="快递单不能为空")
	private String expressPic;
	
	private String otherPic;
	
	private String ticketRemark;

}
