package main.com.system.dao.vo;

import java.util.List;

import main.com.system.dao.po.Menu;

public class MenuVo extends Menu {
	private static final long serialVersionUID = 5399707311609161588L;
	
	private String state="colsed";
	private Boolean ck;
	private List<MenuVo> children;

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Boolean getCk() {
		return ck;
	}
	public void setCk(Boolean ck) {
		this.ck = ck;
	}
	
	public List<MenuVo> getChildren() {
		return children;
	}
	public void setChildren(List<MenuVo> children) {
		this.children = children;
	}
	
	@Override
	public String toString() {
		return "id:"+super.getMenuId()+"-prentId:"+super.getParentId()+"-menuName:"+super.getMenuName()+"-levelNum:"+super.getLevelNum();
	}
	
	
	
}
