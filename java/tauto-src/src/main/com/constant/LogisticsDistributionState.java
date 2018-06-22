package main.com.constant;

import java.util.Objects;

public enum LogisticsDistributionState {
	
	Fail(-1,"无效"),
	Init(0,"初始"),
	Distributed(1,"已派单"),
	OrderReceived(2,"已接单"),
	Loaded(3,"已装车"),
	Transporting(4,"运输中"),
	Arrived(5,"到达目的地"),
	Unloaded(6,"已卸车"),
	Signed(7,"已签收"),
	Done(8,"完成");

	private Integer code;
	private String msg;
	
	LogisticsDistributionState(Integer code,String msg){
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
		for(LogisticsDistributionState e : LogisticsDistributionState.values()) {
			if(Objects.equals(code, e.getCode())) {
				msg = e.getMsg();
				break;
			}
		}
		return msg;
	}
}
