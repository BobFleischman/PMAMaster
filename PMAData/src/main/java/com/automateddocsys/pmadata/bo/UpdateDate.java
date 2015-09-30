package com.automateddocsys.pmadata.bo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the UpdateDate database table.
 * 
 */
@Entity
@Table(name="UpdateDate")
@NamedQuery(name="UpdateDate.findAll", query="SELECT u FROM UpdateDate u")
public class UpdateDate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", nullable=false)
	private Integer id;

	@Column(name="UpdateDate", length=10)
	private String updateDate;

	public UpdateDate() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Date getLastDateUpdated() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		try {
			return formatter.parse(getUpdateDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
}