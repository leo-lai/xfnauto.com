package main.com.system.dao.vo;

import main.com.system.dao.po.SystemUserGrouping;

public class SystemUserGroupingVo extends SystemUserGrouping{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SystemUsersVo usersVo;

	public SystemUsersVo getUsersVo() {
		return usersVo;
	}

	public void setUsersVo(SystemUsersVo usersVo) {
		this.usersVo = usersVo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
