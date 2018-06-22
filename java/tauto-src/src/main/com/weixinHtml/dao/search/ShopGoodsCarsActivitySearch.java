package main.com.weixinHtml.dao.search;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import main.com.frame.search.BaseSearch;

@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
@Data
public class ShopGoodsCarsActivitySearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer orgId; 
	
	private Integer brandId;
	
	private Boolean overOffShelf;
	
	private Integer goodsCarsActivityId;
	
	private Integer goodsCarsId;
	
	/**
	 * 可销售数量
	 */
	private Integer saleingNumber;
	
	/**
	 * 活动描述
	 */
	private String remarks;
	
	/**
	 * 活动图片
	 */
	private String carsImage;
	
	/**
	 * 活动价格
	 */
	private BigDecimal activityPrice;
	
	/**
	 * 定金
	 */
	private BigDecimal depositPrice;
}
