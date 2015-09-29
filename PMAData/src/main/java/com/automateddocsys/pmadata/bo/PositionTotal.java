package com.automateddocsys.pmadata.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the PositionTotals database table.
 * 
 */
@Entity
@Table(name="PositionTotals")
@NamedQuery(name="PositionTotal.findAll", query="SELECT p FROM PositionTotal p")
public class PositionTotal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3693288139618767147L;


	@Column(name="BookValue")
	private BigDecimal bookValue;

	@Column(name="ClientNo", precision=53)
	private Integer clientNo;

	@Column(name="FundName")
	private String fundName;

	@Column(name="FundNo")
	private int fundNo;

	@Column(name="Name1")
	private String name1;

	@Column(name="ObjectName")
	private String objectName;

	@Id
	private Long rownum;

	@Column(name="SharePrice")
	private BigDecimal sharePrice;

	@Column(name="Shares", precision=53)
	private double shares;

	@Column(name="Total", precision=53)
	private BigDecimal total;

	public PositionTotal() {
	}

	public BigDecimal getBookValue() {
		return this.bookValue;
	}

	public void setBookValue(BigDecimal bookValue) {
		this.bookValue = bookValue;
	}

	public Integer getClientNo() {
		return this.clientNo;
	}

	public void setClientNo(Integer clientNo) {
		this.clientNo = clientNo;
	}

	public String getFundName() {
		return this.fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public int getFundNo() {
		return this.fundNo;
	}

	public void setFundNo(int fundNo) {
		this.fundNo = fundNo;
	}

	public String getName1() {
		return this.name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String ObjectName) {
		this.objectName = ObjectName;
	}

	public Long getRownum() {
		return this.rownum;
	}

	public void setRownum(Long rownum) {
		this.rownum = rownum;
	}

	public BigDecimal getSharePrice() {
		return this.sharePrice;
	}

	public void setSharePrice(BigDecimal sharePrice) {
		this.sharePrice = sharePrice;
	}

	public double getShares() {
		return this.shares;
	}

	public void setShares(double shares) {
		this.shares = shares;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PositionTotal [clientNo=");
		builder.append(clientNo);
		builder.append(", name1=");
		builder.append(name1);
		builder.append(", ObjectName=");
		builder.append(objectName);
		builder.append(", fundName=");
		builder.append(fundName);
		builder.append(", total=");
		builder.append(total);
		builder.append(", bookValue=");
		builder.append(bookValue);
		builder.append(", fundNo=");
		builder.append(fundNo);
		builder.append(", rownum=");
		builder.append(rownum);
		builder.append(", sharePrice=");
		builder.append(sharePrice);
		builder.append(", shares=");
		builder.append(shares);
		builder.append("]");
		return builder.toString();
	}

}