package main.com.system.dao.search;

import main.com.frame.search.BaseSearch;

public class SystemUserGroupingSearch extends BaseSearch{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 主键id
     */
    private Integer userGroupingId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 组别
     */
    private Integer groupingId;

    /**
     * 是否是管理员
     */
    private Boolean overManage;

	public Integer getUserGroupingId() {
		return userGroupingId;
	}

	public void setUserGroupingId(Integer userGroupingId) {
		this.userGroupingId = userGroupingId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGroupingId() {
		return groupingId;
	}

	public void setGroupingId(Integer groupingId) {
		this.groupingId = groupingId;
	}

	public Boolean getOverManage() {
		return overManage;
	}

	public void setOverManage(Boolean overManage) {
		this.overManage = overManage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
