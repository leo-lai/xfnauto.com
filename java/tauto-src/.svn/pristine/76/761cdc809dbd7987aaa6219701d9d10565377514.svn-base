package main.com.weixinApp.dao.search;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import main.com.frame.search.BaseSearch;
/**
 * 销售业绩入参
 * @author liaozijie
 *
 */
public class SalesPerformanceSearch extends BaseSearch implements Serializable{
	
	private static final long serialVersionUID = -9034277873902508169L;

	/**
	 * 员工ID
	 */
	private Integer systemUserId;
	
	/**
	 * 员工名称
	 */
	private String systemUserName;
	
	/**
	 * 查询月份
	 */
	@DateTimeFormat(pattern = "yyyy-MM")  
	private Date queryDate;
	

	public Integer getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Integer systemUserId) {
		this.systemUserId = systemUserId;
	}

	public String getSystemUserName() {
		return systemUserName;
	}

	public void setSystemUserName(String systemUserName) {
		this.systemUserName = systemUserName;
	}

	public Date getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}

	
}
