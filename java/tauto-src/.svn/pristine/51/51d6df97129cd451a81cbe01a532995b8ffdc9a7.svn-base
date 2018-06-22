package main.com.logistics.dao.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import main.com.constant.LogisticsDistributionState;
import main.com.logistics.dao.po.LogisticsDistribution;

@JsonIgnoreProperties(value = {"handler"})
public class LogisticsDistributionVo extends LogisticsDistribution{

	private static final long serialVersionUID = 1L;
	
	private String distributionStateName;
	
	private List<LogisticsConsignmentVo> logisticsConsignmentVos;

	private LogisticsCarVo logisticsCarVo;
	
	private List<LogisticsGoodsCarVo> goodsCarVos; 
	
	private LogisticsDriverVo logisticsDriverVo;
	
	@Override
	public void setDistributionState(Integer distributionState) {
		// TODO Auto-generated method stub
		super.setDistributionState(distributionState);
		this.distributionStateName = LogisticsDistributionState.getMsgByCode(distributionState);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDistributionStateName() {
		return distributionStateName;
	}

	public void setDistributionStateName(String distributionStateName) {
		this.distributionStateName = distributionStateName;
	}

	public List<LogisticsConsignmentVo> getLogisticsConsignmentVos() {
		return logisticsConsignmentVos;
	}

	public void setLogisticsConsignmentVos(List<LogisticsConsignmentVo> logisticsConsignmentVos) {
		this.logisticsConsignmentVos = logisticsConsignmentVos;
	}

	public LogisticsCarVo getLogisticsCarVo() {
		return logisticsCarVo;
	}

	public void setLogisticsCarVo(LogisticsCarVo logisticsCarVo) {
		this.logisticsCarVo = logisticsCarVo;
	}

	public List<LogisticsGoodsCarVo> getGoodsCarVos() {
		return goodsCarVos;
	}

	public void setGoodsCarVos(List<LogisticsGoodsCarVo> goodsCarVos) {
		this.goodsCarVos = goodsCarVos;
	}

	public LogisticsDriverVo getLogisticsDriverVo() {
		return logisticsDriverVo;
	}

	public void setLogisticsDriverVo(LogisticsDriverVo logisticsDriverVo) {
		this.logisticsDriverVo = logisticsDriverVo;
	}
}
