package main.com.customer.dao.po;

import main.com.frame.domain.Result;

public class CustomerUsersDTO {

	private CustomerUsers customerUsers;
	private Integer advanceOrderId;
	private Result result;
	public CustomerUsers getCustomerUsers() {
		return customerUsers;
	}
	public void setCustomerUsers(CustomerUsers customerUsers) {
		this.customerUsers = customerUsers;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public Integer getAdvanceOrderId() {
		return advanceOrderId;
	}
	public void setAdvanceOrderId(Integer advanceOrderId) {
		this.advanceOrderId = advanceOrderId;
	}
}
