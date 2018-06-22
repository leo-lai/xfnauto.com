package main.com.weixinHtml.dao.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import main.com.weixinHtml.dao.po.ShopFindCar;
import main.com.weixinHtml.dao.po.ShopFindCarOffer;

@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
@Data
public class ShopFindCarVo extends ShopFindCar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<ShopFindCarOffer> findCarOffers;
	
	ShopUsersVo shopUsersVo;
}
