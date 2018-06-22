package main.com.system.dao.po;

import java.util.Date;

/**
 * 统一日志管理操作日志实体表（表名：sys_log_operate）
 * 日志的目的：记录什么时间什么人操作了什么
 */

public class LogOperate implements java.io.Serializable {
	
	private static final long serialVersionUID = -6855444643281469899L;

	private String logId;         //主键
	private Date logDate;         //记录日志时间
	private String clientUrl;     //客户端url
	private String operUserId;    //操作人
	private String operUserName;  //操作人姓名
	private String accessUrl;     //请求服务器url
	private String accessParams;  //请求参数集合
	private String operMethod;    //执行方法
	private String operDetail;    //操作明细
	private String ipAddress;     //IP地址
	private String logDes;        //日记说明
	private String operSessionId;     //登陆标识号
	
	public LogOperate() {}

	public LogOperate(String logId, Date logDate, String clientUrl,
			String operUserId, String operUserName, String accessUrl,
			String accessParams, String operMethod, String operDetail,String ipAddress) {
		this.logId = logId;
		this.logDate = logDate;
		this.clientUrl = clientUrl;
		this.operUserId = operUserId;
		this.operUserName = operUserName;
		this.accessUrl = accessUrl;
		this.accessParams = accessParams;
		this.operMethod = operMethod;
		this.operDetail = operDetail;
		this.ipAddress = ipAddress;
	}

	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}

	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getClientUrl() {
		return clientUrl;
	}
	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}

	public String getOperUserId() {
		return operUserId;
	}
	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
	}
	
	public String getOperUserName() {
		return operUserName;
	}
	public void setOperUserName(String operUserName) {
		this.operUserName = operUserName;
	}
	
	public String getAccessUrl() {
		return accessUrl;
	}
	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public String getAccessParams() {
		return accessParams;
	}
	public void setAccessParams(String accessParams) {
		this.accessParams = accessParams;
	}
	
	public String getOperMethod() {
		return operMethod;
	}
	public void setOperMethod(String operMethod) {
		this.operMethod = operMethod;
	}

	public String getOperDetail() {
		return operDetail;
	}
	public void setOperDetail(String operDetail) {
		this.operDetail = operDetail;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLogDes() {
		return logDes;
	}

	public void setLogDes(String logDes) {
		this.logDes = logDes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOperSessionId() {
		return operSessionId;
	}

	public void setOperSessionId(String operSessionId) {
		this.operSessionId = operSessionId;
	}
}