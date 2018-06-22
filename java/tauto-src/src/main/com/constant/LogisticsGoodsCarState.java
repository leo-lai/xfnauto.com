package main.com.constant;

import java.util.Objects;

public enum LogisticsGoodsCarState {

	Init(0,"新建"),
	Distributed(1,"已分配"),
	Dispatched(2,"已派单"),
	OrderReceived(3,"已接单"),
	Loaded(4,"已装车"),
	Transporting(5,"运输中"),
	Arrived(6,"到达目的地"),
	Unloaded(7,"已卸车"),
	Signed(8,"已签收"),
	Paid(9,"已支付");

	private Integer code;
	private String msg;
	
	LogisticsGoodsCarState(Integer code,String msg){
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
		for(LogisticsGoodsCarState e : LogisticsGoodsCarState.values()) {
			if(Objects.equals(code, e.getCode())) {
				msg = e.getMsg();
				break;
			}
		}
		return msg;
	}
}
