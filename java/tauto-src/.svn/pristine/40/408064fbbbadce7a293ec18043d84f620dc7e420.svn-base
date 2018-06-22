package main.com.customer.constant;

import java.util.Objects;

import main.com.constant.LogisticsDistributionState;

/** 
* @author liaozijie 
* @version 创建时间：2018年1月17日 上午11:34:32 
* 类描述： 配送状态枚举类
*/
public enum CustomerOrderState {

	initial(0,"初始"),
	start(1,"待收定金"),
	paymentOfADeposit(3,"等待银行审核"),
	notPassThrough(4,"银行审核不通过"),
	loanAudit(5,"等待车辆出库"),
	deliveryOfTheTail(7,"等待加装精品"),
	retrofitting(9,"等待上牌"),
	padPasting(11,"等待贴膜"),
	deliver(13,"等待交车"),
	deliverTheLibrary(15,"已人车合照"),
	orderBeenFinish(17,"已完款，交车放行"),
	orderVisit(19,"已回访");

	private Integer code;
	private String desc;
	
	CustomerOrderState(Integer code,String desc){
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static String getMsgByCode(Integer code) {
		String msg = "";
		for(CustomerOrderState e : CustomerOrderState.values()) {
			if(Objects.equals(code, e.getCode())) {
				msg = e.getDesc();
				break;
			}
		}
		return msg;
	}
	
}
