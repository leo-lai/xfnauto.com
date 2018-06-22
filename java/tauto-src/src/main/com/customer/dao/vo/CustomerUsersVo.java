package main.com.customer.dao.vo;

import java.util.List;

import main.com.customer.dao.po.CustomerOrder;
import main.com.customer.dao.po.CustomerUsers;

public class CustomerUsersVo extends CustomerUsers{

//	private CustomerUsersVo customerUsersVo;
//
//	public CustomerUsersVo getCustomerUsersVo() {
//		return customerUsersVo;
//	}
//
//	public void setCustomerUsersVo(CustomerUsersVo customerUsersVo) {
//		this.customerUsersVo = customerUsersVo;
//	}
	/**
	 * 已取消连表查询 2015 01 08
	 * 保留旧代码
	 * @return
	 */
	List<CustomerOrderVo> customerOrderVos;
	List<CustomerCustomerOrgVo> customerOrgVos;
	
	/** 
	 * 拆分查询  2015 01 08
	 */
	CustomerOrderVo customerOrderVo;
	CustomerCustomerOrgVo customerOrgVo;
	
	public CustomerOrderVo getCustomerOrderVo() {
		return customerOrderVo;
	}
	public void setCustomerOrderVo(CustomerOrderVo customerOrderVo) {
		this.customerOrderVo = customerOrderVo;
	}
	public CustomerCustomerOrgVo getCustomerOrgVo() {
		return customerOrgVo;
	}
	public void setCustomerOrgVo(CustomerCustomerOrgVo customerOrgVo) {
		this.customerOrgVo = customerOrgVo;
	}
	public List<CustomerOrderVo> getCustomerOrderVos() {
		return customerOrderVos;
	}
	public void setCustomerOrderVos(List<CustomerOrderVo> customerOrderVos) {
		this.customerOrderVos = customerOrderVos;
	}
	public List<CustomerCustomerOrgVo> getCustomerOrgVos() {
		return customerOrgVos;
	}
	public void setCustomerOrgVos(List<CustomerCustomerOrgVo> customerOrgVos) {
		this.customerOrgVos = customerOrgVos;
	}
}
