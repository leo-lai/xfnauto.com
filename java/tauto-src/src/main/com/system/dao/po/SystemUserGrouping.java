package main.com.system.dao.po;

import java.io.Serializable;

public class SystemUserGrouping implements Serializable{
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

    /**
     * 主键id
     * @return user_grouping_id 主键id
     */
    public Integer getUserGroupingId() {
        return userGroupingId;
    }

    /**
     * 主键id
     * @param userGroupingId 主键id
     */
    public void setUserGroupingId(Integer userGroupingId) {
        this.userGroupingId = userGroupingId;
    }

    /**
     * 用户id
     * @return user_id 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户id
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 组别
     * @return grouping_id 组别
     */
    public Integer getGroupingId() {
        return groupingId;
    }

    /**
     * 组别
     * @param groupingId 组别
     */
    public void setGroupingId(Integer groupingId) {
        this.groupingId = groupingId;
    }

    /**
     * 是否是管理员
     * @return over_manage 是否是管理员
     */
    public  Boolean getOverManage() {
        return overManage;
    }

    /**
     * 是否是管理员
     * @param overManage 是否是管理员
     */
    public void setOverManage(Boolean overManage) {
        this.overManage = overManage;
    }
}