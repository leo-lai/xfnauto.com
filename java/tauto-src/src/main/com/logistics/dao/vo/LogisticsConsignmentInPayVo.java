package main.com.logistics.dao.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import main.com.logistics.dao.po.LogisticsConsignmentInPay;

@JsonIgnoreProperties(value = {"handler"})
public class LogisticsConsignmentInPayVo extends LogisticsConsignmentInPay{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
