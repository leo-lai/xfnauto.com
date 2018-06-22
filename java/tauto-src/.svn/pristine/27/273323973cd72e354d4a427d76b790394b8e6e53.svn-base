package main.com.logistics.dao.search;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
import main.com.frame.search.BaseSearch;

/** 
* @author liaozijie 
* @version 创建时间：2018年1月16日 下午3:30:40 
* 类描述： 上传车辆图片入参
*/
@Data
public class UploadPicSearch extends BaseSearch implements Serializable{

	private static final long serialVersionUID = -4153548282112731901L;
	
	/**
	 * 货物车ID
	 */
	private Integer goodsCarId;
	
	/**
	 * 上传图片类型 1：装车图片 2：卸车图片
	 */
	private Integer type;
	
	/**
	 * 图片上传地址（若多张则用','隔开）
	 */
	private String picUrl;

}
