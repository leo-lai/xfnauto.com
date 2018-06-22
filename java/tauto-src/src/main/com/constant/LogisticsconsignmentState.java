package main.com.constant;

import java.util.Objects;

public enum LogisticsconsignmentState {
	
	
//	Init(0,"等待分配"),
//	Distributed(1,"等待装车"),
//	OrderReceived(2,"配送中"),
//	Loaded(3,"已卸车"),
//	Transporting(4,"支付部分"),
//	Arrived(5,"支付完毕");
	
	Fail(-1,"已取消"),
	Init(0,"新建"),
	Distributed(1,"已分配"),
	OrderReceived(2,"已接单"),
	Loaded(3,"已装车"),
	Transporting(4,"运输中"),
	Arrived(5,"到达目的地"),
	Unloaded(6,"已卸车"),
	Signed(7,"已签收"),
	Paid(8,"已支付");
	
	private Integer code;
	private String msg;
	
	LogisticsconsignmentState(Integer code,String msg){
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
		for(LogisticsconsignmentState e : LogisticsconsignmentState.values()) {
			if(Objects.equals(code, e.getCode())) {
				msg = e.getMsg();
				break;
			}
		}
		return msg;
	}
}
