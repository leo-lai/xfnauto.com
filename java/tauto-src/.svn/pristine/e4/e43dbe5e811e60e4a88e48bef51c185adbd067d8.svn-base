package main.com.customer.dao.vo;

import java.util.List;

import main.com.car.dao.vo.CarsVo;
import main.com.constant.LogisticsDistributionState;
import main.com.customer.constant.CustomerOrderState;
import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.po.CustomerOrderInPay;
import main.com.stock.dao.po.StockCar;
import main.com.stock.dao.vo.StockCarVo;
import main.com.system.dao.vo.OrganizationVo;

public class CustomerOrderVo extends CustomerOrder{

	private CustomerUsersVo customerUsersVo;
	
	private CarsVo carsVo;
	
	private CustomerOrderInPay inPay;
	
	private OrganizationVo organization;
	
	private String customerOrderStateName;
	
	private StockCar stockCar;
	
	private List<CustomerOrderInPayVo> orderInPayVos;
	
	private int stockCarNumber;

	public CustomerUsersVo getCustomerUsersVo() {
		return customerUsersVo;
	}

	public void setCustomerUsersVo(CustomerUsersVo customerUsersVo) {
		this.customerUsersVo = customerUsersVo;
	}

	public CustomerOrderInPay getInPay() {
		return inPay;
	}

	public void setInPay(CustomerOrderInPay inPay) {
		this.inPay = inPay;
	}

	public CarsVo getCarsVo() {
		return carsVo;
	}

	public void setCarsVo(CarsVo carsVo) {
		this.carsVo = carsVo;
	}

	public OrganizationVo getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationVo organization) {
		this.organization = organization;
	}

	public int getStockCarNumber() {
		return stockCarNumber;
	}

	public void setStockCarNumber(int stockCarNumber) {
		this.stockCarNumber = stockCarNumber;
	}
	
	@Override
	public void setCustomerOrderState(Integer customerOrderState) {
		// TODO Auto-generated method stub
		super.setCustomerOrderState(customerOrderState);
		this.customerOrderStateName = CustomerOrderState.getMsgByCode(customerOrderState);
	}

	public String getCustomerOrderStateName() {
		return customerOrderStateName;
	}

	public void setCustomerOrderStateName(String customerOrderStateName) {
		this.customerOrderStateName = customerOrderStateName;
	}

	public StockCar getStockCar() {
		return stockCar;
	}

	public void setStockCarVo(StockCar stockCarVo) {
		this.stockCar = stockCarVo;
	}

	public List<CustomerOrderInPayVo> getOrderInPayVos() {
		return orderInPayVos;
	}

	public void setOrderInPayVos(List<CustomerOrderInPayVo> orderInPayVos) {
		this.orderInPayVos = orderInPayVos;
	}

	public void setStockCar(StockCar stockCar) {
		this.stockCar = stockCar;
	}

//	public Integer getImage() {
//		return image;
//	}
//
//	public void setImage(Integer image) {
//		this.image = image;
//	}
}
