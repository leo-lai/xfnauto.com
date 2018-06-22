package main.com.frame.search;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseSearch implements Serializable {

	private static final long serialVersionUID = 9057824370152750360L;

	/**
	 * 当前页
	 */
	private int page = 1;
	/**
	 * 每页显示记录数
	 */
	private int rows = 10;
	/**
	 * 排序字段名
	 */
	private String sort = null; 
	/**
	 * 按什么排序(asc,desc)
	 */
	private String order = "asc";  
	/**
	 * 是否分页
	 */
	private Boolean isPage = false;
	
	/**
	 * mysql分页辅助
	 */
	private int offset;
	/**
	 * mysql分页辅助
	 */
	private int limit;
	/**
	 * 是否使用关键字模糊查询
	 * @return
	 */
	private String keySearch;
	/**
	 * 是否使用关键字模糊查询
	 * @return
	 */
	private String keywords;
	/**
	 * 关键字模糊查询的列集合
	 * @return
	 */
	private String [] keySearchFileds;
	
	private String sessionId; //登录凭证
	
	private String startDate; //时间搜索
	private String endDate;
	private String orgCode;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public int getPage() {
		if(page == 0){
			return 1;
		}
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public Boolean getIsPage() {
		return isPage;
	}
	public void setIsPage(Boolean isPage) {
		this.isPage = isPage;
	}
	
	public int getOffset() {
		int offset = (this.page-1)*this.rows;
		if(offset < 0)offset = 0;
		return offset;
	}
	
	public int getLimit() {
		return this.rows;
	}
	public String getKeySearch() {
		return keySearch;
	}
	public void setKeySearch(String keySearch) {
		this.keySearch = keySearch;
	}
	public String[] getKeySearchFileds() {
		return keySearchFileds;
	}
	public void setKeySearchFileds(String[] keySearchFileds) {
		this.keySearchFileds = keySearchFileds;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
}
