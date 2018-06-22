package main.com.weixinHtml.constant;

import java.util.Objects;

public enum Institution {

	pass_1(1,"已通过"),
	refuse_2(2,"已拒绝"),
	initial_3(0,"等待审核");
	
	private Integer code;
	private String msg;
	
	Institution(Integer code,String msg){
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
