package main.com.weixinApp.dao.search;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class ConsumerOrderUserSearch implements Serializable{
	
	
	private static final long serialVersionUID = 8549464000104199672L;
	
	private String userName;
	
	//@Pattern(regexp = "^\\d{11}$",message = "手机号码格式错误")
	private String userPhone;
	

	private String idCard;
	

	private String idCardPicOn;
	
	
	private String idCardPicOff;
	
	/**
	 * 1：客户 2：提车人
	 */
	private Integer type;

}
