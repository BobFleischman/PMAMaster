/**
 * 
 */
package com.automateddocsys.pma.webdata.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author Robert
 *
 */
@Entity
public class PotentialQuestion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1496456134854606672L;

	@Id
	private Long questionId;
	
	@NotNull
	@Column(unique=true)
	private String question;

	public PotentialQuestion(int ct, String pQuestion) {
		this.questionId = (long) ct;
		this.question = pQuestion;
	}

	public PotentialQuestion() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PotentialQuestion [questionId=");
		builder.append(questionId);
		builder.append(", question=");
		builder.append(question);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
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
		PotentialQuestion other = (PotentialQuestion) obj;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		return true;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
