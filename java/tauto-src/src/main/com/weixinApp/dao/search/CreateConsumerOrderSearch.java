package main.com.weixinApp.dao.search;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import main.com.frame.search.BaseSearch;

@Data
public class CreateConsumerOrderSearch extends BaseSearch implements Serializable{

	private static final long serialVersionUID = -5402210879539768351L;
	
	/**
	 * 门店ID
	 */
//	@NotNull(message="门店不能为空")
	private Integer orgId;
	
	/**
	 * 门店名称
	 */
	private String orgName;
	
	/**
	 * 联系人名称
	 */
	@NotBlank(message="联系人不能为空")
	private String orgLinker;
	
	/**
	 * 门店联系人手机号
	 */
	@NotBlank(message="联系电话不能为空")
	@Pattern(regexp = "^\\d{11}$",message = "联系电话格式错误")
	private String orgPhone;
	
	/**
	 * 物流类型 1：自提 2：其他
	 */
	
	private Integer logisticsType;
	
	/**
	 * 物流车牌号
	 */
	private String logisticsPlateNumber;
	
	/**
	 * 物流司机名称
	 */
	private String logisticsDriver;
	
	/**
	 * 物流司机电话
	 */
	private String logisticsDriverPhone;
	
	/**
	 * 物流订单号
	 */
	private String logisticsOrderCode;
	
	/**
	 * 物流公司
	 */
	private String logisticsCompany;
	
	/**
	 * 提车日期
	 */
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date pickCarDate;
	
	/**
	 * 运费
	 */
	private BigDecimal freight;
	
	/**
	 * 提车地址
	 */
	private String pickCarAddr;
	
	private Integer creatorId;
	private String creatorName;
	
	private String creator;
	
	/**
	 * 订单类型
	 */
	private Integer orderType;
	
	/**
	 * 提车人
	 */
	@NotNull(message="提车人不能为空")
	private List<ConsumerOrderUserSearch> pickers;
	
	private Integer advanceOrderId;
}


