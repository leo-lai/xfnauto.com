package main.com.system.dao.vo;

import main.com.system.dao.po.Dictionary;

public class DictionaryVo extends Dictionary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer dictionaryId;//	int 主键
	private String dictionaryName;//	varchar 名称
	private String dictionaryValue;//	varchar 值
	private String dictionaryGrouping;//	varchar 分组
	private String dictionaryTile;//	varchar 标题
	private String dictionaryContent;//	text 描述
	private Boolean dictionaryState;//	tinyint 状态
	private Integer dictionaryShopTypesId;//商品类型ID
	
	public Integer getDictionaryId() {
		return dictionaryId;
	}
	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	public String getDictionaryName() {
		return dictionaryName;
	}
	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}
	public String getDictionaryValue() {
		return dictionaryValue;
	}
	public void setDictionaryValue(String dictionaryValue) {
		this.dictionaryValue = dictionaryValue;
	}
	public String getDictionaryGrouping() {
		return dictionaryGrouping;
	}
	public void setDictionaryGrouping(String dictionaryGrouping) {
		this.dictionaryGrouping = dictionaryGrouping;
	}
	public String getDictionaryTile() {
		return dictionaryTile;
	}
	public void setDictionaryTile(String dictionaryTile) {
		this.dictionaryTile = dictionaryTile;
	}
	public String getDictionaryContent() {
		return dictionaryContent;
	}
	public void setDictionaryContent(String dictionaryContent) {
		this.dictionaryContent = dictionaryContent;
	}
	public Boolean getDictionaryState() {
		return dictionaryState;
	}
	public void setDictionaryState(Boolean dictionaryState) {
		this.dictionaryState = dictionaryState;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getDictionaryShopTypesId() {
		return dictionaryShopTypesId;
	}
	public void setDictionaryShopTypesId(Integer dictionaryShopTypesId) {
		this.dictionaryShopTypesId = dictionaryShopTypesId;
	}
}
