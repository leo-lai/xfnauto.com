package main.com.logistics.constant;

import java.util.Objects;

import main.com.constant.LogisticsGoodsCarState;

/**
 * 板车状态
 * @author Zwen
 *
 */
public enum LogisticsCarState {

	FREE(0,"空闲"),
	BUSY(1,"运输中");
	
	private Integer code;
	private String msg;
	
	LogisticsCarState(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static String getMsgByCode(Integer code) {
		String msg = "";
		for(LogisticsCarState e : LogisticsCarState.values()) {
			if(Objects.equals(code, e.getCode())) {
				msg = e.getMsg();
				break;
			}
		}
		return msg;
	}
}
