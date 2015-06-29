package com.automateddocsys.pma.webdata.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity()
public class ClientAnswer implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6578077782623748370L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long answerId;
	
	@ManyToOne
	private PotentialQuestion question;
	
	@NotEmpty
	private String answer;
	
	@Column(insertable=false,nullable=false,updatable=false)
	private Long userId;

	public ClientAnswer() {
		super();
	}

	public ClientAnswer(PotentialQuestion pQuestion, String pAnswer) {
		super();
		this.question = pQuestion;
		this.answer = pAnswer;
	}
	
	public PotentialQuestion getQuestion() {
		return question;
	}
	public void setQuestion(PotentialQuestion question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientAnswer [question=");
		builder.append(question);
		builder.append(", answer=");
		builder.append(answer);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		ClientAnswer other = (ClientAnswer) obj;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
