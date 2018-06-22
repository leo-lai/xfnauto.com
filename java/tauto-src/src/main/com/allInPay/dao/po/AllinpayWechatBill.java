package main.com.allInPay.dao.po;

import java.io.Serializable;

public class AllinpayWechatBill implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private Integer allinpayWechatBillId;

    /**
     * 返回信息说明
     */
    private String retmsg;

    /**
     * SUCCESS/FAIL 此字段是通信标识，非交易结果，交易是否成功需要查看trxstatus来判断
     */
    private String retcode;

    /**
     * 平台分配的商户号
     */
    private String cusid;

    /**
     * 平台分配的APPID
     */
    private String appid;

    /**
     * 收银宝平台的交易流水号
     */
    private String trxid;

    /**
     * 例如微信,支付宝平台的交易单号
     */
    private String chnltrxid;

    /**
     * 商户的交易订单号
     */
    private String reqsn;

    /**
     * 随机生成的字符串
     */
    private String randomstr;

    /**
     * 交易的状态,
     */
    private String trxstatus;

    /**
     * 交易完成时间yyyyMMddHHmmss
     */
    private String fintime;

    /**
     * 失败的原因说明
     */
    private String errmsg;

    /**
     * 小程序js支付返回json字符串
     */
    private String payinfo;

    /**
     * 
     */
    private Integer orgId;

    /**
     * 
     */
    private String orgName;

    /**
     * 
     */
    private Long amount;

    /**
     * 
     */
    private Integer consignmentId;

    /**
     * 主键
     * @return allinpay_wechat_bill_id 主键
     */
    public Integer getAllinpayWechatBillId() {
        return allinpayWechatBillId;
    }

    /**
     * 主键
     * @param allinpayWechatBillId 主键
     */
    public void setAllinpayWechatBillId(Integer allinpayWechatBillId) {
        this.allinpayWechatBillId = allinpayWechatBillId;
    }

    /**
     * 返回信息说明
     * @return retmsg 返回信息说明
     */
    public String getRetmsg() {
        return retmsg;
    }

    /**
     * 返回信息说明
     * @param retmsg 返回信息说明
     */
    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    /**
     * SUCCESS/FAIL 此字段是通信标识，非交易结果，交易是否成功需要查看trxstatus来判断
     * @return retcode SUCCESS/FAIL 此字段是通信标识，非交易结果，交易是否成功需要查看trxstatus来判断
     */
    public String getRetcode() {
        return retcode;
    }

    /**
     * SUCCESS/FAIL 此字段是通信标识，非交易结果，交易是否成功需要查看trxstatus来判断
     * @param retcode SUCCESS/FAIL 此字段是通信标识，非交易结果，交易是否成功需要查看trxstatus来判断
     */
    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    /**
     * 平台分配的商户号
     * @return cusid 平台分配的商户号
     */
    public String getCusid() {
        return cusid;
    }

    /**
     * 平台分配的商户号
     * @param cusid 平台分配的商户号
     */
    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    /**
     * 平台分配的APPID
     * @return appid 平台分配的APPID
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 平台分配的APPID
     * @param appid 平台分配的APPID
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * 收银宝平台的交易流水号
     * @return trxid 收银宝平台的交易流水号
     */
    public String getTrxid() {
        return trxid;
    }

    /**
     * 收银宝平台的交易流水号
     * @param trxid 收银宝平台的交易流水号
     */
    public void setTrxid(String trxid) {
        this.trxid = trxid;
    }

    /**
     * 例如微信,支付宝平台的交易单号
     * @return chnltrxid 例如微信,支付宝平台的交易单号
     */
    public String getChnltrxid() {
        return chnltrxid;
    }

    /**
     * 例如微信,支付宝平台的交易单号
     * @param chnltrxid 例如微信,支付宝平台的交易单号
     */
    public void setChnltrxid(String chnltrxid) {
        this.chnltrxid = chnltrxid;
    }

    /**
     * 商户的交易订单号
     * @return reqsn 商户的交易订单号
     */
    public String getReqsn() {
        return reqsn;
    }

    /**
     * 商户的交易订单号
     * @param reqsn 商户的交易订单号
     */
    public void setReqsn(String reqsn) {
        this.reqsn = reqsn;
    }

    /**
     * 随机生成的字符串
     * @return randomstr 随机生成的字符串
     */
    public String getRandomstr() {
        return randomstr;
    }

    /**
     * 随机生成的字符串
     * @param randomstr 随机生成的字符串
     */
    public void setRandomstr(String randomstr) {
        this.randomstr = randomstr;
    }

    /**
     * 交易的状态,
     * @return trxstatus 交易的状态,
     */
    public String getTrxstatus() {
        return trxstatus;
    }

    /**
     * 交易的状态,
     * @param trxstatus 交易的状态,
     */
    public void setTrxstatus(String trxstatus) {
        this.trxstatus = trxstatus;
    }

    /**
     * 交易完成时间yyyyMMddHHmmss
     * @return fintime 交易完成时间yyyyMMddHHmmss
     */
    public String getFintime() {
        return fintime;
    }

    /**
     * 交易完成时间yyyyMMddHHmmss
     * @param fintime 交易完成时间yyyyMMddHHmmss
     */
    public void setFintime(String fintime) {
        this.fintime = fintime;
    }

    /**
     * 失败的原因说明
     * @return errmsg 失败的原因说明
     */
    public String getErrmsg() {
        return errmsg;
    }

    /**
     * 失败的原因说明
     * @param errmsg 失败的原因说明
     */
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    /**
     * 小程序js支付返回json字符串
     * @return payinfo 小程序js支付返回json字符串
     */
    public String getPayinfo() {
        return payinfo;
    }

    /**
     * 小程序js支付返回json字符串
     * @param payinfo 小程序js支付返回json字符串
     */
    public void setPayinfo(String payinfo) {
        this.payinfo = payinfo;
    }

    /**
     * 
     * @return org_id 
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 
     * @param orgId 
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * 
     * @return org_name 
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 
     * @param orgName 
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 
     * @return amount 
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount 
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return consignment_id 
     */
    public Integer getConsignmentId() {
        return consignmentId;
    }

    /**
     * 
     * @param consignmentId 
     */
    public void setConsignmentId(Integer consignmentId) {
        this.consignmentId = consignmentId;
    }
}