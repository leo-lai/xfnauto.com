package main.com.system.dao.search;

import main.com.frame.search.BaseSearch;

public class RoleSearch extends BaseSearch {

	private static final long serialVersionUID = 7055314584366242417L;

	private Integer roleId;             //主键
	private Integer parentId;       //父ID
	private String nodePathLR;      //节点路径
	private Integer nodeId;         //节点id
	private String roleName;        //角色名称:匹配
	private String roleNameLike;    //角色名称:模糊
	private Integer userId;         //用户id
	private String remark;
	private String menuIds;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public String getNodePathLR() {
		return nodePathLR;
	}
	public void setNodePathLR(String nodePathLR) {
		this.nodePathLR = nodePathLR;
	}
	
	public Integer getNodeId() {
		return nodeId;
	}
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleNameLike() {
		return roleNameLike;
	}
	public void setRoleNameLike(String roleNameLike) {
		this.roleNameLike = roleNameLike;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
}
