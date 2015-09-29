package com.automateddocsys.pmadata.bo.projections;

import java.math.BigDecimal;

public class AccountTotal {
	
	private String accountName;
	private BigDecimal totalValue = new BigDecimal("0");
	private Integer clientNumber;
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public BigDecimal getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}
	public void addValue(BigDecimal pValue) {
		this.totalValue = totalValue.add(pValue);		
	}
	public Integer getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(Integer clientNumber) {
		this.clientNumber = clientNumber;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountTotal [clientNumber=");
		builder.append(clientNumber);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", totalValue=");
		builder.append(totalValue);
		builder.append("]");
		return builder.toString();
	}

	public BigDecimal getTwoDigitTotal() {
		return totalValue.setScale(2, BigDecimal.ROUND_DOWN);
	}
}
