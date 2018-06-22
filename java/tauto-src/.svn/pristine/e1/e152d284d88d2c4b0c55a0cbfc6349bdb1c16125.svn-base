package main.com.weixinHtml.dao.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import main.com.constant.LogisticsDistributionState;
import main.com.system.dao.vo.OrganizationVo;
import main.com.weixinHtml.constant.ShopApplyLoanState;
import main.com.weixinHtml.dao.po.ShopApplyLoan;

@JsonIgnoreProperties(value = {"handler"}) //各种关联查询时，bean添加此标签可无视延迟加载无法封装返回的错误
public class ShopApplyLoanVo extends ShopApplyLoan{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrganizationVo organizationVo;
	
	private String createTimeForMat;
	private String loneStateName;

	public OrganizationVo getOrganizationVo() {
		return organizationVo;
	}

	public void setOrganizationVo(OrganizationVo organizationVo) {
		this.organizationVo = organizationVo;
	}

	public String getCreateTimeForMat() {
		return createTimeForMat;
	}

	public void setCreateTimeForMat(String createTimeForMat) {
		this.createTimeForMat = createTimeForMat;
	}

	public String getLoneStateName() {
		return loneStateName;
	}

	public void setLoneStateName(String loneStateName) {
		this.loneStateName = ShopApplyLoanState.getMsgByCode(super.getLoneState());
	}
}
