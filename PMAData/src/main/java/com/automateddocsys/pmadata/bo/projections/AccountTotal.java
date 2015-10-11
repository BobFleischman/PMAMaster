package com.automateddocsys.pmadata.bo.projections;

import java.math.BigDecimal;

public class AccountTotal {
	
	private String accountName;
	private BigDecimal totalValue = new BigDecimal("0");
	private Integer clientNumber;
	private BigDecimal percentOfTotal;
	
	public BigDecimal getPercentOfTotal() {
		return percentOfTotal;
	}
	public void setPercentOfTotal(BigDecimal percentOfTotal) {
		this.percentOfTotal = percentOfTotal;
	}
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
	
	public String getTicker() {
		if (getAccountName() == null) {
			return "NO TICKER";
		}
		int spot = getAccountName().lastIndexOf(" ");
		String ticker = getAccountName().substring(spot).trim();
		return ticker;
	}
	
	public String getNameOnly() {
		int spot = getAccountName().lastIndexOf(" ");
		String nameOnly = getAccountName().substring(0, spot).trim();
		return nameOnly;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccountTotal [clientNumber=");
		builder.append(clientNumber);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", ticker=");
		builder.append(getTicker());
		builder.append(", totalValue=");
		builder.append(totalValue);
		builder.append(", percentOfTotal=");
		builder.append(getPercentOfTotal());
		builder.append("]");
		return builder.toString();
	}

	public BigDecimal getTwoDigitTotal() {
		return totalValue.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
}
