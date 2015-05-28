package com.automateddocsys.pma.web.mvc.models;

public class AnswerSet {

	private Long question1 = (long) 0;
	private String answer1 = "";
	private Long question2 = (long) 0;
	private String answer2 = "";
	private Long question3 = (long) 0;
	private String answer3 = "";
	public Long getQuestion1() {
		return question1;
	}
	public void setQuestion1(Long question1) {
		this.question1 = question1;
	}
	public Long getQuestion2() {
		return question2;
	}
	public void setQuestion2(Long question2) {
		this.question2 = question2;
	}
	public Long getQuestion3() {
		return question3;
	}
	public void setQuestion3(Long question3) {
		this.question3 = question3;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AnswerSet [question1=");
		builder.append(question1);
		builder.append(", answer1=");
		builder.append(answer1);
		builder.append(", question2=");
		builder.append(question2);
		builder.append(", answer2=");
		builder.append(answer2);
		builder.append(", question3=");
		builder.append(question3);
		builder.append(", answer3=");
		builder.append(answer3);
		builder.append("]");
		return builder.toString();
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
}
