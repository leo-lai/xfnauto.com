package main.com.system.dao.search;

import main.com.frame.search.BaseSearch;

public class MenuSearch extends BaseSearch {

	private static final long serialVersionUID = 7055314584366242417L;

	private Integer menuId;             //主键
	private Integer parentId;       //父ID
	private String nodePathLR;      //节点路径
	private Integer roleId;         //角色id
	private Integer nodeId;         //节点id
	private Integer seq;          //序号
	private String iconUrl;       //图标
//	private String menuCode;      //菜单编码
	private String menuName;      //菜单名称
	private String src;           //菜单地址
	private Integer levelNum;     //级别（辅助字段）
	private Boolean isLeaf;       //是否叶子（辅助字段）
	private String nodePath;      //节点路径
	private Boolean isdelete;      //是否已删除
	
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
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
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	public Integer getNodeId() {
		return nodeId;
	}
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public Integer getLevelNum() {
		return levelNum;
	}
	public void setLevelNum(Integer levelNum) {
		this.levelNum = levelNum;
	}
	public Boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getNodePath() {
		return nodePath;
	}
	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}
	public Boolean getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Boolean isdelete) {
		this.isdelete = isdelete;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
