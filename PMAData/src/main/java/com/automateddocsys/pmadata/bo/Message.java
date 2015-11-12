package com.automateddocsys.pmadata.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Message")
public class Message {
	
	@Id
	@Column(name="WebPageDisplayMessage")
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

}
