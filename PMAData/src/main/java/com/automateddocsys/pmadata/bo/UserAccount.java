package com.automateddocsys.pmadata.bo;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the Passwords database table.
 * 
 */
@Entity
@Table(name="Passwords")
@NamedQuery(name="UserAccount.findAll", query="SELECT p FROM UserAccount p")
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ClientNo")
	private Integer clientNo;

	@Id
	@Column(nullable=false)
	private Integer client_ID;

	private Timestamp dateAdded;

	@Column(name="Name")
	private String name;

	@Column(name="Password")
	private String password;

	@Column(name="Username",insertable=false, updatable= false)
	private String username;

	//bi-directional many-to-one association to Permission
	@OneToMany(mappedBy="userAccount")
	private List<Permission> permissions;

	public UserAccount() {
	}

	public Integer getClient_ID() {
		return this.client_ID;
	}

	public void setClient_ID(Integer client_ID) {
		this.client_ID = client_ID;
	}

	public Timestamp getDateAdded() {
		return this.dateAdded;
	}

	public void setDateAdded(Timestamp dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Permission addPermission(Permission permission) {
		getPermissions().add(permission);
		permission.setUserAccount(this);
		return permission;
	}

	public Permission removePermission(Permission permission) {
		getPermissions().remove(permission);
		permission.setUserAccount(null);
		return permission;
	}

	public Integer getClientNo() {
		return clientNo;
	}

	public void setClientNo(Integer clientNo) {
		this.clientNo = clientNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserAccount [clientNo=");
		builder.append(clientNo);
		builder.append(", client_ID=");
		builder.append(client_ID);
		builder.append(", dateAdded=");
		builder.append(dateAdded);
		builder.append(", name=");
		builder.append(name);
		builder.append(", password=");
		builder.append(password);
		builder.append(", username=");
		builder.append(username);
		builder.append(", permissions=");
		builder.append(permissions);
		builder.append("]");
		return builder.toString();
	}

}