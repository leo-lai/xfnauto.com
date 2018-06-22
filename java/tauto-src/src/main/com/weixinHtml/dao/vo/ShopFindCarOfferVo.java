package main.com.weixinHtml.dao.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import main.com.weixinHtml.dao.po.ShopFindCar;
import main.com.weixinHtml.dao.po.ShopFindCarOffer;

@Data
@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
public class ShopFindCarOfferVo extends ShopFindCarOffer{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ShopFindCar shopFindCar;
}
