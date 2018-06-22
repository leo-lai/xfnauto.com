package main.com.logistics.dao.search;

import java.io.Serializable;

import lombok.Data;
@Data
public class UnloadCarSubSearch implements Serializable{

	private static final long serialVersionUID = -1978956173380862940L;

	private Integer goodsCarId;
	
	private String deliverToImage;
}
