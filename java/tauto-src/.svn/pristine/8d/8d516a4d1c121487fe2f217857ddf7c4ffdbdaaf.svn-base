package main.com.logistics.dao.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import main.com.constant.LogisticsGoodsCarState;
import main.com.logistics.dao.po.LogisticsGoodsCar;

@JsonIgnoreProperties(value = {"handler"})
public class LogisticsGoodsCarVo extends LogisticsGoodsCar implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String  goodsCarStateName;
	
	private LogisticsConsignmentVo consignmentVo;
	
	private LogisticsDistributionVo distributionVo;
	
	private Integer carsId;
	
	private LogisticsGoodsCarCostsVo carCostsVo;
	
	@Override
	public void setGoodsCarState(Integer goodsCarState) {
		// TODO Auto-generated method stub
		super.setGoodsCarState(goodsCarState);
		this.goodsCarStateName = LogisticsGoodsCarState.getMsgByCode(goodsCarState);
	}

	public String getGoodsCarStateName() {
		return goodsCarStateName;
	}

	public void setGoodsCarStateName(String goodsCarStateName) {
		this.goodsCarStateName = goodsCarStateName;
	}

	public LogisticsConsignmentVo getConsignmentVo() {
		return consignmentVo;
	}

	public void setConsignmentVo(LogisticsConsignmentVo consignmentVo) {
		this.consignmentVo = consignmentVo;
	}

	public LogisticsDistributionVo getDistributionVo() {
		return distributionVo;
	}

	public void setDistributionVo(LogisticsDistributionVo distributionVo) {
		this.distributionVo = distributionVo;
	}

	public Integer getCarsId() {
		return carsId;
	}

	public void setCarsId(Integer carsId) {
		this.carsId = carsId;
	}

	public LogisticsGoodsCarCostsVo getCarCostsVo() {
		return carCostsVo;
	}

	public void setCarCostsVo(LogisticsGoodsCarCostsVo carCostsVo) {
		this.carCostsVo = carCostsVo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
