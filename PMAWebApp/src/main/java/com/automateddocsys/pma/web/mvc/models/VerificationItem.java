package com.automateddocsys.pma.web.mvc.models;

public class VerificationItem {
		private Long questionNumber;
		private String question;
		private String answer;
		public Long getQuestionNumber() {
			return questionNumber;
		}
		public void setQuestionNumber(Long questionNumber) {
			this.questionNumber = questionNumber;
		}
		public String getQuestion() {
			return question;
		}
		public void setQuestion(String question) {
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
			builder.append("VerificationItem [questionNumber=");
			builder.append(questionNumber);
			builder.append(", question=");
			builder.append(question);
			builder.append(", answer=");
			builder.append(answer);
			builder.append("]");
			return builder.toString();
		}

		
}
