package com.automateddocsys.pmadata.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Permissions database table.
 * 
 */
@Entity
@Table(name="AccountPermissions")
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long rownum;
	
	@Column(name="ValidAccounts")
	private Integer validAccount;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne
	@JoinColumn(name="PortalAccounts", referencedColumnName="ClientNo")
	private UserAccount userAccount;

	public Permission() {
	}

	public Integer getValidAccount() {
		return this.validAccount;
	}

	public void setValidAccount(Integer validAccount) {
		this.validAccount = validAccount;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Long getRownum() {
		return rownum;
	}

	public void setRownum(Long rownum) {
		this.rownum = rownum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Permission [validAccount=");
		builder.append(validAccount);
		builder.append("]");
		return builder.toString();
	}

}