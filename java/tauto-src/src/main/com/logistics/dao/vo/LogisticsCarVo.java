package main.com.logistics.dao.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import main.com.logistics.dao.po.LogisticsCar;

@JsonIgnoreProperties(value = {"handler"})
public class LogisticsCarVo extends LogisticsCar{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
