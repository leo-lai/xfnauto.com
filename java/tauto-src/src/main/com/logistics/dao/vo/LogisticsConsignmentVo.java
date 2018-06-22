package main.com.logistics.dao.vo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import main.com.logistics.dao.po.LogisticsConsignment;
import main.com.stock.dao.po.ConsumerOrderUser;
import main.com.utils.DateUtil;
import main.com.utils.GeneralConstant;
import main.com.utils.StringUtil;
import main.com.weixinApp.dao.vo.ConsumerOrderUserVO;

@JsonIgnoreProperties(value = {"handler"})
public class LogisticsConsignmentVo extends LogisticsConsignment{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<LogisticsConsignmentInPayVo> consignmentInPayVos;
	
	List<LogisticsGoodsCarVo> goodsCarVos;
	
	/**
	 * 交车人
	 */
	List<ConsumerOrderUser> leaveTheCarPerson;
	
	/**
	 * 提车人
	 * @return
	 */
	List<ConsumerOrderUser> extractTheCarPerson;
	
	private String partyA;
	
	private String appointmentTimeDate;
	
	private String createTimeStr;

	public List<LogisticsConsignmentInPayVo> getConsignmentInPayVos() {
		return consignmentInPayVos;
	}

	public void setConsignmentInPayVos(List<LogisticsConsignmentInPayVo> consignmentInPayVos) {
		this.consignmentInPayVos = consignmentInPayVos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<LogisticsGoodsCarVo> getGoodsCarVos() {
		return goodsCarVos;
	}

	public void setGoodsCarVos(List<LogisticsGoodsCarVo> goodsCarVos) {
		this.goodsCarVos = goodsCarVos;
	}

	public List<ConsumerOrderUser> getLeaveTheCarPerson() {
		return leaveTheCarPerson;
	}

	public void setLeaveTheCarPerson(List<ConsumerOrderUser> leaveTheCarPerson) {
		this.leaveTheCarPerson = leaveTheCarPerson;
	}

	public List<ConsumerOrderUser> getExtractTheCarPerson() {
		return extractTheCarPerson;
	}

	public void setExtractTheCarPerson(List<ConsumerOrderUser> extractTheCarPerson) {
		this.extractTheCarPerson = extractTheCarPerson;
	}
	
	public Set<String> getThePersionId(String ids){
		return new HashSet<>(Arrays.asList((ids.split(GeneralConstant.SystemBoolean.SPLIT))));
	}
	
	public List<ConsumerOrderUser> getThePerson(List<ConsumerOrderUser> persionList, LogisticsConsignmentVo consignmentVo,Boolean whatPerson){
		if(whatPerson == null) {
			whatPerson = true;
		}
		Set<String> leave = null;
		Set<String> extract = null;
		if(StringUtil.isNotEmpty(consignmentVo.getLeaveTheCarPersonIds())) {
			leave = new HashSet<>(Arrays.asList((consignmentVo.getLeaveTheCarPersonIds().split(GeneralConstant.SystemBoolean.SPLIT))));
		}
		if(StringUtil.isNotEmpty(consignmentVo.getExtractTheCarPersonIds())) {
			extract = new HashSet<>(Arrays.asList((consignmentVo.getExtractTheCarPersonIds().split(GeneralConstant.SystemBoolean.SPLIT))));
		}
		for(ConsumerOrderUser userVO : persionList) {
			if(leave.add(userVO.getId()+"")) {
				this.leaveTheCarPerson.add(userVO);
			}else if(extract.add(userVO.getId()+"")) {
				this.extractTheCarPerson.add(userVO);
			}
		}
		if(whatPerson) {
			return this.leaveTheCarPerson;
		}else {
			return this.extractTheCarPerson;
		}
	}
	
	public static void main(String[] args) {
		String ids = "1,2,3";
		Set<String> leave = new HashSet<>(Arrays.asList((ids.split(GeneralConstant.SystemBoolean.SPLIT))));
		System.out.println(leave.toString());
	}

	public String getPartyA() {
		return partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public String getAppointmentTimeDate() {
		this.appointmentTimeDate = this.getAppointmentTime()!=null?DateUtil.format(this.getAppointmentTime(),"yyyy年MM月dd日 HH时mm分"):"";
		return appointmentTimeDate;
	}

	public void setAppointmentTimeDate(String appointmentTimeDate) {
		this.appointmentTimeDate = appointmentTimeDate;
	}

	public String getCreateTimeStr() {
		this.createTimeStr = this.getCreateDate()!=null?DateUtil.format(this.getCreateDate(),"yyyy年MM月dd日"):"";
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
}
