package main.com.weixin.mune;

public class TempButton extends Button{

	private String button;    //	是	一级菜单数组，个数应为1~3个
//	private String sub_button;    //	否	二级菜单数组，个数应为1~5个
	private String type;    //	是	菜单的响应动作类型
//	private String name;    //	是	菜单标题，不超过16个字节，子菜单不超过40个字节
//	private String key;    //	click等点击类型必须	菜单KEY值，用于消息接口推送，不超过128字节
	private String url;    //	view类型必须	网页链接，用户点击菜单可打开链接，不超过256字节
//	private String media_id;    //	media_id类型和view_limited类型必须	调用新增永久素材接口返回的合法media_id
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
