package com.automateddocsys.pmadata.bo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ClientName database table.
 * 
 */
@Entity
@Table(name="ClientName")
@NamedQuery(name="ClientName.findAll", query="SELECT c FROM ClientName c")
public class ClientName implements Serializable {
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientName [clientNo=");
		builder.append(clientNo);
		builder.append(", name1=");
		builder.append(name1);
		builder.append(", name2=");
		builder.append(name2);
		builder.append(", name3=");
		builder.append(name3);
		builder.append("]");
		return builder.toString();
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ClientNo", insertable=false, updatable=false, precision=53)
	private int clientNo;

	@Column(name="Name1", insertable=false, updatable=false)
	private String name1;

	@Column(name="Name2", insertable=false, updatable=false)
	private String name2;

	@Column(name="Name3", insertable=false, updatable=false)
	private String name3;

	public ClientName() {
	}

	public int getClientNo() {
		return this.clientNo;
	}

	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}

	public String getName1() {
		return this.name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		if (name2 == null) {
			return "";
		} else {
			return this.name2;
		}
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		if (name3 == null) {
			return "";
		} else {
			return this.name3;
		}
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}
	
	public String getFullName() {
		String name = (name1 + " " + getName2() + " " + getName3()).trim();
		name = name.replace("  ", " ");
		return name;
	}

}