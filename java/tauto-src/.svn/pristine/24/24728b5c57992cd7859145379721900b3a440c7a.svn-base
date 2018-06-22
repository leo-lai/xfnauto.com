package main.com.weixinHtml.constant;

import java.util.Objects;

public enum OrderPayMethodName {

	PayByShop(1,"到店支付"),
	PAY_BY_WX(2,"微信支付"),
	PAY_BY_ZFB(3,"支付宝"),
	PAY_WALLET(4,"钱包"),
	PAY_POS(5,"POS机"),
	PAY_CASH(6,"现金支付"),
	PAY_BILL(7,"挂账"),
	PAY_BY_WX_PINGXX(8,"微信支付ping++方式"),
	PAY_ALLINPAY(12,"通联支付"),
	PAY_ALLINPAY_WEIXIN_JS(13,"通联微信支付"),
	PAY_BY_POS_ONESELF(14,"店内POS机支付");

	private Integer code;
	private String msg;
	
	OrderPayMethodName(Integer code,String msg){
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
		for(OrderPayMethodName e : OrderPayMethodName.values()) {
			if(Objects.equals(code, e.getCode())) {
				msg = e.getMsg();
				break;
			}
		}
		return msg;
	}
}
