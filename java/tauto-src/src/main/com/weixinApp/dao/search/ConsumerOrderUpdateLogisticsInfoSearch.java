package main.com.weixinApp.dao.search;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class ConsumerOrderUpdateLogisticsInfoSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = -6397326313310266370L;
	
	@NotNull(message="订单ID不能为空")
	private Integer orderId;

	@NotBlank(message="物流单号")
	private String logisticsOrderCode;
	
	@NotBlank(message="车牌号不能为空")
	private String logisticsPlateNumber;
	
	@NotBlank(message="司机姓名不能为空")
	private String logisticsDriver;
	
	@NotBlank(message="联系方式不能为空")
	private String logisticsDriverPhone;
	
	@NotBlank(message="物流公司不能为空")
	private String logisticsCompany;

}
