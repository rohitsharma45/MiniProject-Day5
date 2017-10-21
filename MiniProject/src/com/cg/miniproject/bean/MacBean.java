package com.cg.miniproject.bean;

public class MacBean {

	
	private String loginId;
	private String password;
	public MacBean(String loginId, String password) {
		super();
		this.loginId = loginId;
		this.password = password;
	}


	private String role;

	public MacBean() {
		
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	@Override
	public String toString() {
		return "MacBean [loginId=" + loginId + ", password=" + password
				+ ", role=" + role + "]";
	}
	
	
}
