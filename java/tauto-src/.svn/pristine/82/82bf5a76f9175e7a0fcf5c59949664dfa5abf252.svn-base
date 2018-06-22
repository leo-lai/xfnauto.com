package main.com.allInPay.dao.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import main.com.utils.StringUtil;

/**
 * 通联POST流水流水账总汇
 * @author Zwen
 *
 */
public class OrderAipBill implements Serializable{

	private static final long serialVersionUID = 8695283369147113059L;

	/*订单信息*/
	private Integer id;                 //主键
	private String orderCode;           //订单号
	private Integer orderId;            //订单Id
	private Integer orgId;              //门店id
	private String orgName;             //服务门店
	private BigDecimal amount;               //金额
	private Date createDate;            //创建时间
	/*通联交易信息*/
	private String trxcode;             //交易类型
	private String trxstatus;           //交易状态
	private String aipAmount;           //通联金额
	private String trxId;               //交易流水号
	private String srctrxid;            //通联原交易流水,冲正撤销交易本字段不为空
	private String trxday;              //交易请求日期
	private String paytime;             //交易完成时间
	private String termid;              //终端编码
	private String termbatchid;         //终端批次号
	private String traceno;             //终端流水
	private String trxreserve;          //业务关联内容
	/*备用字段*/
	private String remark1;              
	
	public OrderAipBill() {
		super();
	}
	
	public OrderAipBill(String orderCode, Integer orderId,
			Integer orgId, String orgName, Float amount, Date createDate,
			String trxcode, String trxstatus, String aipAmount, String trxId,
			String srctrxid, String trxday, String paytime, String termid,
			String termbatchid, String traceno, String trxreserve,
			String remark1) {
		super();
		if(StringUtil.isNotEmpty(orderCode)) {
			this.orderCode = orderCode;
		}
		if(StringUtil.isNotEmpty(orderId)) {
			this.orderId = orderId;
		}
		if(StringUtil.isNotEmpty(orgId)) {
			this.orgId = orgId;
		}
		if(StringUtil.isNotEmpty(orgName)) {
			this.orgName = orgName;
		}
		if(amount != null) {
			this.amount = new BigDecimal(amount);
		}
		if(createDate != null) {
			this.createDate = createDate;
		}
		if(StringUtil.isNotEmpty(trxcode)) {
			this.trxcode = trxcode;
		}
		if(StringUtil.isNotEmpty(trxstatus)) {
			this.trxstatus = trxstatus;
		}
		if(StringUtil.isNotEmpty(aipAmount)) {
			this.aipAmount = aipAmount;
		}
		if(StringUtil.isNotEmpty(trxId)) {
			this.trxId = trxId;
		}
		if(StringUtil.isNotEmpty(srctrxid)) {
			this.srctrxid = srctrxid;
		}
		if(StringUtil.isNotEmpty(trxday)) {
			this.trxday = trxday;
		}
		if(StringUtil.isNotEmpty(paytime)) {
			this.paytime = paytime;
		}
		if(StringUtil.isNotEmpty(termid)) {
			this.termid = termid;
		}
		if(StringUtil.isNotEmpty(termbatchid)) {
			this.termbatchid = termbatchid;
		}
		if(StringUtil.isNotEmpty(traceno)) {
			this.traceno = traceno;
		}
		if(StringUtil.isNotEmpty(trxreserve)) {
			this.trxreserve = trxreserve;
		}
		if(StringUtil.isNotEmpty(remark1)) {
			this.remark1 = remark1;
		}
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id=id; 
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTrxcode() {
		return trxcode;
	}
	public void setTrxcode(String trxcode) {
		this.trxcode = trxcode;
	}
	
	public String getTrxstatus() {
		return trxstatus;
	}
	public void setTrxstatus(String trxstatus) {
		this.trxstatus = trxstatus;
	}
	
	public String getAipAmount() {
		return aipAmount;
	}
	public void setAipAmount(String aipAmount) {
		this.aipAmount = aipAmount;
	}
	
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	
	public String getSrctrxid() {
		return srctrxid;
	}
	public void setSrctrxid(String srctrxid) {
		this.srctrxid = srctrxid;
	}
	
	public String getTrxday() {
		return trxday;
	}
	public void setTrxday(String trxday) {
		this.trxday = trxday;
	}
	
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	
	public String getTermid() {
		return termid;
	}
	public void setTermid(String termid) {
		this.termid = termid;
	}
	
	public String getTermbatchid() {
		return termbatchid;
	}
	public void setTermbatchid(String termbatchid) {
		this.termbatchid = termbatchid;
	}
	
	public String getTraceno() {
		return traceno;
	}
	public void setTraceno(String traceno) {
		this.traceno = traceno;
	}
	
	public String getTrxreserve() {
		return trxreserve;
	}
	public void setTrxreserve(String trxreserve) {
		this.trxreserve = trxreserve;
	}
	
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
