package com.automateddocsys.pma.web.mvc.models;

public class PasswordChange {

	private String password = "";
	private String oldpassword = "";
	private String confirm_password = "";
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PasswordChange [password=");
		builder.append(password);
		builder.append(", oldpassword=");
		builder.append(oldpassword);
		builder.append(", confirm_password=");
		builder.append(confirm_password);
		builder.append("]");
		return builder.toString();
	}
	public boolean doesMatch() {
		return getPassword().equals(getConfirm_password());
	}
}
