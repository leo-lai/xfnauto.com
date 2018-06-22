package main.com.frame.constants;

public enum ConsumerOrderCarAuditState {
	
	Auditing(1,"待审核"),
	CarDistributed(2,"已配车"),
	PriceChanging(3,"审核不通过（待修改价格）"),
	PriceChanged(4,"审核不通过（已修改价格）"),
	CarChangeApply(5,"申请换车"),
	CarChanging(6,"审核不通过(待换车)"),
	CarChanged(7,"审核不通过(已换车)"),
	Audited(8,"已审核"),
	countermand(9,"已退订");
	
	private Integer code;
	private String msg;
	
	ConsumerOrderCarAuditState(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	
}
