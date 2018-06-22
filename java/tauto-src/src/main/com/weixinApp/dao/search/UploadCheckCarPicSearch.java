package main.com.weixinApp.dao.search;

import java.io.Serializable;

import lombok.Data;
import main.com.frame.search.BaseSearch;
@Data
public class UploadCheckCarPicSearch extends BaseSearch implements Serializable{
	
	private Integer carId;
	
	private String checkCarPic;
}
