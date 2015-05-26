package com.automateddocsys.pma.webdata.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6638976885536236271L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	@Column(name="username",insertable=false,updatable=false)
	protected String username;
	
	@Column(name="accountNumber")
	protected String accountNumber;
	
	@Column(name="accountName")
	protected String accountName;

//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	@JoinColumn(name="accountNumber",referencedColumnName="accountNumber",updatable=false,nullable=false,insertable=false)
//	Set<PMAClientPositions> positions = new HashSet<PMAClientPositions>(0);
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientAccount other = (ClientAccount) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientAccount [accountId=");
		builder.append(accountId);
		builder.append(", username=");
		builder.append(username);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append("]");
		return builder.toString();
	}
	
}
