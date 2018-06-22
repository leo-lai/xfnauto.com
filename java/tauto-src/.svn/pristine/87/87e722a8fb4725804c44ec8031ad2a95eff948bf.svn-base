package main.com.logistics.constant;

import java.util.Objects;

import main.com.constant.LogisticsGoodsCarState;

/**
 * 司机状态枚举
 * @author Zwen
 *
 */
public enum DrivateState {

	QUIT(-1,"离职"),
	FREE(0,"空闲"),
	BUSY(2,"运输中");
	
	private Integer code;
	private String msg;
	
	DrivateState(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}

	public static String getMsgByCode(Integer code) {
		String msg = "";
		for(DrivateState e : DrivateState.values()) {
			if(Objects.equals(code, e.getCode())) {
				msg = e.getMsg();
				break;
			}
		}
		return msg;
	}
	
}
