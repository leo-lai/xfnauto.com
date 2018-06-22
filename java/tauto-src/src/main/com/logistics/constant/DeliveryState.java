package main.com.logistics.constant;

/** 
* @author liaozijie 
* @version 创建时间：2018年1月17日 上午11:34:32 
* 类描述： 配送状态枚举类
*/
public enum DeliveryState {

	INIT(0,"初始"),
	READY(1,"待配送"),
	DELIVERYING(2,"配送中"),
	END(3,"已送达");
	
	private Integer code;
	private String desc;
	
	DeliveryState(Integer code,String desc){
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	
	
}
