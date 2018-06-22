package main.com.car.dao.search;

import java.io.Serializable;
import java.math.BigDecimal;

import main.com.customer.dao.po.UsersTmpl;
import main.com.frame.search.BaseSearch;

/**
 * 车型大类信息表
 * @author Zwen
 *
 */
public class CarsSearch extends BaseSearch{
	private static final long serialVersionUID = -1857474422983921832L;
	private Integer carId;
	private Integer carsId;
	private Integer brandId;   				//品牌id
	private String brandCode;				//品牌编码
	private String brandName;				//品牌名称
	private String brandInitial;            //品牌首字母
	private Integer familyId;                //车系id
	private String familyName;              //车系名称
	private String vehicleName;             //车型名称(SUV/CRV/轿车)
	private String pl;						//排量
	private String engineDesc;              //发动机描述
	private String engineModel;             //发动机类型
	private String inairform;               //进气方式
	private String driveStyle;              //驱动形式
	private String gearboxName;             //变速箱类型
	private String supplyOil;				//供油方式
	private String gearNum;                 //变速器档数
	private String wheelbase;               //轴距
	private String marketDate;              //上市年月
	private String yearPattern;             //年款
	private Integer styleId;                //款式ID
	private String styleName;                //款式名称（配置级别）
	private Float price;            //官方指导价
	private Integer seat;                   //座位
	private String powerType;               //动力类型
	private String emissionStandard;        //排放标准
	private String oilConsumption;        //百公里油耗
	private String introduce;        //简介
	private String searchText;        //搜索框内容参数
	private Float bareCarPrice;            //裸车价
	private Float comprehensivePrice;            //预计落地价
	private Boolean isDelete;
	private String carsName;
	
	private Float minPrice;            //官方指导价
	private Float maxPrice;            //官方指导价
	private String carName;           //车辆搜索名称
	
	private Double paymentRatio;         //首付比例
	private Integer TimeOfPayment;        //付款年限
	
	private UsersTmpl usersTmpl;
	
	private BigDecimal thirdPartyLiabilityInsuranceTopBack;//第三方责任险赔付金额
	
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandInitial() {
		return brandInitial;
	}
	public void setBrandInitial(String brandInitial) {
		this.brandInitial = brandInitial;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getPl() {
		return pl;
	}
	public void setPl(String pl) {
		this.pl = pl;
	}
	public String getEngineDesc() {
		return engineDesc;
	}
	public void setEngineDesc(String engineDesc) {
		this.engineDesc = engineDesc;
	}
	public String getEngineModel() {
		return engineModel;
	}
	public void setEngineModel(String engineModel) {
		this.engineModel = engineModel;
	}
	public String getInairform() {
		return inairform;
	}
	public void setInairform(String inairform) {
		this.inairform = inairform;
	}
	public String getDriveStyle() {
		return driveStyle;
	}
	public void setDriveStyle(String driveStyle) {
		this.driveStyle = driveStyle;
	}
	public String getGearboxName() {
		return gearboxName;
	}
	public void setGearboxName(String gearboxName) {
		this.gearboxName = gearboxName;
	}
	public String getSupplyOil() {
		return supplyOil;
	}
	public void setSupplyOil(String supplyOil) {
		this.supplyOil = supplyOil;
	}
	public String getGearNum() {
		return gearNum;
	}
	public void setGearNum(String gearNum) {
		this.gearNum = gearNum;
	}
	public String getWheelbase() {
		return wheelbase;
	}
	public void setWheelbase(String wheelbase) {
		this.wheelbase = wheelbase;
	}
	public String getMarketDate() {
		return marketDate;
	}
	public void setMarketDate(String marketDate) {
		this.marketDate = marketDate;
	}
	public String getYearPattern() {
		return yearPattern;
	}
	public void setYearPattern(String yearPattern) {
		this.yearPattern = yearPattern;
	}
	public Integer getStyleId() {
		return styleId;
	}
	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getSeat() {
		return seat;
	}
	public void setSeat(Integer seat) {
		this.seat = seat;
	}
	public String getPowerType() {
		return powerType;
	}
	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}
	public String getEmissionStandard() {
		return emissionStandard;
	}
	public void setEmissionStandard(String emissionStandard) {
		this.emissionStandard = emissionStandard;
	}
	public String getOilConsumption() {
		return oilConsumption;
	}
	public void setOilConsumption(String oilConsumption) {
		this.oilConsumption = oilConsumption;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Integer getFamilyId() {
		return familyId;
	}
	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public Float getBareCarPrice() {
		return bareCarPrice;
	}
	public void setBareCarPrice(Float bareCarPrice) {
		this.bareCarPrice = bareCarPrice;
	}
	public Float getComprehensivePrice() {
		return comprehensivePrice;
	}
	public void setComprehensivePrice(Float comprehensivePrice) {
		this.comprehensivePrice = comprehensivePrice;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public String getCarsName() {
		return carsName;
	}
	public void setCarsName(String carsName) {
		this.carsName = carsName;
	}
	public Float getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}
	public Float getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public Double getPaymentRatio() {
		return paymentRatio;
	}
	public void setPaymentRatio(Double paymentRatio) {
		this.paymentRatio = paymentRatio;
	}
	public Integer getTimeOfPayment() {
		return TimeOfPayment;
	}
	public void setTimeOfPayment(Integer timeOfPayment) {
		TimeOfPayment = timeOfPayment;
	}
	public UsersTmpl getUsersTmpl() {
		return usersTmpl;
	}
	public void setUsersTmpl(UsersTmpl usersTmpl) {
		this.usersTmpl = usersTmpl;
	}
	public Integer getCarsId() {
		return carsId;
	}
	public void setCarsId(Integer carsId) {
		this.carsId = carsId;
	}
	public BigDecimal getThirdPartyLiabilityInsuranceTopBack() {
		return thirdPartyLiabilityInsuranceTopBack;
	}
	public void setThirdPartyLiabilityInsuranceTopBack(BigDecimal thirdPartyLiabilityInsuranceTopBack) {
		this.thirdPartyLiabilityInsuranceTopBack = thirdPartyLiabilityInsuranceTopBack;
	}
}
