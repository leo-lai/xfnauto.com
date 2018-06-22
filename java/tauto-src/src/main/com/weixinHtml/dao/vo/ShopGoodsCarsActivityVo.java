package main.com.weixinHtml.dao.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import main.com.system.dao.po.Organization;
import main.com.system.dao.vo.SystemUsersVo;
import main.com.weixinHtml.dao.po.ShopGoodsCarsActivity;

@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
@Data
public class ShopGoodsCarsActivityVo extends ShopGoodsCarsActivity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Organization organization;
	
	private SystemUsersVo usersVo;
}
