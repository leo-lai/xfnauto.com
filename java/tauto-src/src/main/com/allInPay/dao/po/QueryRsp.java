package main.com.allInPay.dao.po;


public class QueryRsp extends BaseRsp {

	public String trxreserve;   //业务关联内容
	public String bizseq;       //业务流水号
	public long amount;         //金额
	public String getTrxreserve() {
		return trxreserve;
	}
	public void setTrxreserve(String trxreserve) {
		this.trxreserve = trxreserve;
	}
	public String getBizseq() {
		return bizseq;
	}
	public void setBizseq(String bizseq) {
		this.bizseq = bizseq;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
}
