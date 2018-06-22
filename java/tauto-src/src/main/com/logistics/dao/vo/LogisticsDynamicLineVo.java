package main.com.logistics.dao.vo;

import java.util.List;

import main.com.logistics.dao.po.LogisticsDynamicLine;

public class LogisticsDynamicLineVo extends LogisticsDynamicLine{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<LogisticsDynamicLineInfoVo> lineInfoVos;

	public List<LogisticsDynamicLineInfoVo> getLineInfoVos() {
		return lineInfoVos;
	}

	public void setLineInfoVos(List<LogisticsDynamicLineInfoVo> lineInfoVos) {
		this.lineInfoVos = lineInfoVos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
