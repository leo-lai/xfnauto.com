package main.com.frame.constants;

public enum ConsumerOrderState {
		
	Fail(-1,"无效单"),
	Init(1,"新建"),
	DepositPaying(5,"待收定金"),
	CarDistributing(10,"待配车"),
	CarChecking(15,"待验车"),
	CarChangeApply(20,"换车申请"),
	CarChanging(25,"待换车"),
	Consulting(30,"待协商"),
	FinalPricePaying(35,"待收尾款"),
	countermand(37,"已退款"),
	StockOuting(40,"待出库"),
	TickerUploading(45,"待上传票证"),
	Doned(50,"完成"),
	
	/**
	 * 常规单
	 */
	Routine(1,"常规单"),
	
	/**
	 * 炒车单
	 */
	Traffic(2,"炒车单");
	
	private Integer code;
	private String msg;
	ConsumerOrderState(Integer code,String msg) {
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
        for (ConsumerOrderState e : ConsumerOrderState.values()) {
            if (e.getCode() == code) {
            	msg = e.getMsg();
                break;
            }
        }
        return msg;
    }
	
}
