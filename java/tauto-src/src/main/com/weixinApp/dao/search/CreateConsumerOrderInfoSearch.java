package main.com.weixinApp.dao.search;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class CreateConsumerOrderInfoSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = 4054224430947307006L;

	/**
	 * 订购单ID
	 */
	@NotNull(message="订单ID不能为空")
	private Integer orderId;
	
	/**
	 * 客户信息
	 */
	@Valid
	private ConsumerOrderUserSearch customer;
	
	/**
	 * 车辆信息
	 */
	@Valid
	private List<ConsumerOrderInfoSearch> infos;

}
