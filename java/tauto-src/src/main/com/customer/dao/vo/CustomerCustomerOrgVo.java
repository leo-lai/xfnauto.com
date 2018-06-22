package main.com.customer.dao.vo;

import main.com.customer.dao.po.CustomerCustomerOrg;

public class CustomerCustomerOrgVo extends CustomerCustomerOrg{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CustomerUsersVo customerUsersVo;

	public CustomerUsersVo getCustomerUsersVo() {
		return customerUsersVo;
	}

	public void setCustomerUsersVo(CustomerUsersVo customerUsersVo) {
		this.customerUsersVo = customerUsersVo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
