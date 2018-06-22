package main.com.weixinHtml.constant;

import java.util.Objects;

public enum ShopGoodsCarsState {

	onShelf(0,"上架"),
	initial(1,"新建/下架");
	
	
	
	private Integer code;
	private String msg;
	
	ShopGoodsCarsState(Integer code,String msg){
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
		for(ShopGoodsCarsState e : ShopGoodsCarsState.values()) {
			if(Objects.equals(code, e.getCode())) {
				msg = e.getMsg();
				break;
			}
		}
		return msg;
	}
}
