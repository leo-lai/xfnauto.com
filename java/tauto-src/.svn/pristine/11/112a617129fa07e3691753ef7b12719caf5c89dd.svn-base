package main.com.weixinHtml.constant;

import java.util.Objects;

public enum ShopApplyLoanState {

	loan(1,"贷款"),
	advance(2,"垫资"),
	
	pass(1,"已通过"),
	refuse(2,"已拒绝"),
	initial(0,"等待审核");
	
	
	
	private Integer code;
	private String msg;
	
	ShopApplyLoanState(Integer code,String msg){
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
		for(ShopApplyLoanState e : ShopApplyLoanState.values()) {
			if(Objects.equals(code, e.getCode())) {
				msg = e.getMsg();
				break;
			}
		}
		return msg;
	}
}
