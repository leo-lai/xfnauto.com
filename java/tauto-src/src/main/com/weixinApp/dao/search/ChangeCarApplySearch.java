package main.com.weixinApp.dao.search;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class ChangeCarApplySearch implements Serializable{
	
	private static final long serialVersionUID = -6130067693427080918L;

	@NotNull
	private Integer carId;
	
	private String auditRemark;
	
	private String checkCarPic;
}
