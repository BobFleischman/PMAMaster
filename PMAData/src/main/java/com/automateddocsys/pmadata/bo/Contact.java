package com.automateddocsys.pmadata.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Contact database table.
 * 
 */
@Entity
@Table(name="Contact")
@NamedQuery(name="Contact.findAll", query="SELECT c FROM Contact c")
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Address")
	private String address;

	@Column(name="City")
	private String city;

	@Column(name="Comments1")
	private String comments1;

	@Column(name="Email")
	private String email;

	@Column(name="EntryDate")
	private Timestamp entryDate;

	@Column(name="FirstName")
	private String firstName;

	@Column(name="HomePhone")
	private String homePhone;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false)
	private int id;

	@Column(name="LastName")
	private String lastName;

	@Column(name="State")
	private String state;

	@Column(name="WorkPhone")
	private String workPhone;

	@Column(name="Zip")
	private String zip;

	public Contact() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getComments1() {
		return this.comments1;
	}

	public void setComments1(String comments1) {
		this.comments1 = comments1;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWorkPhone() {
		return this.workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contact [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", address=");
		builder.append(address);
		builder.append(", city=");
		builder.append(city);
//		builder.append(", comments1=");
//		builder.append(comments1);
		builder.append(", email=");
		builder.append(email);
		builder.append(", entryDate=");
		builder.append(entryDate);
		builder.append(", homePhone=");
		builder.append(homePhone);
		builder.append(", state=");
		builder.append(state);
		builder.append(", workPhone=");
		builder.append(workPhone);
		builder.append(", zip=");
		builder.append(zip);
		builder.append("]");
		return builder.toString();
	}

}