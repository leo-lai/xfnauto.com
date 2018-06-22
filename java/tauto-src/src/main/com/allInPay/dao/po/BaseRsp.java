package main.com.allInPay.dao.po;

import java.util.Date;

import main.com.allInPay.utils.AppConstants;
import main.com.allInPay.utils.FuncUtil;

/**
 * 通联POST对接父类基本配置
 * @author Zwen
 *
 */
public class BaseRsp {

	public String retcode;     //响应码  
	public String retmsg;      //响应消息
	public String cusid;       //商户号
	public String appid;       //通联分配的appid
	public String randomstr;   //随机字符串,主要用于加签
	public String timestamp;   //返回时间戳:yyyymmddhhmmss
	public String sign;        //sign校验码
	public String trxcode;     //接口编号
	
	
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	
	public String getRetmsg() {
		return retmsg;
	}
	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}
	
	public String getCusid() {
		return cusid;
	}
	public void setCusid(String cusid) {
		this.cusid = cusid;
	}
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getRandomstr() {
		return randomstr;
	}
	public void setRandomstr(String randomstr) {
		this.randomstr = randomstr;
	}
	
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String getTrxcode() {
		return trxcode;
	}
	public void setTrxcode(String trxcode) {
		this.trxcode = trxcode;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public void initHead(){
		cusid = AppConstants.CUSID;
		appid = AppConstants.APPID;
		timestamp = FuncUtil.formatTime(new Date(), "yyyyMMddHHmmss");
		randomstr = FuncUtil.getRandcode(8);
		
	}
	
	public static BaseRsp getFaildResult(String msg){
		BaseRsp rsp = new BaseRsp();
		rsp.initHead();
		rsp.setRetcode("9999");
		rsp.setRetmsg(msg);
		return rsp;
	}
	
	
}
