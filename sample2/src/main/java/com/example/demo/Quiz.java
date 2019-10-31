package com.example.demo;

import javax.validation.constraints.NotEmpty;

public class Quiz {

	private String ploblemNumber;

	private String orderNumber;

	@NotEmpty(message = "問題の空白は不可")
	private String question;

	@NotEmpty(message = "答えの空白は不可")
	private String answer;

	private String result;

	public void setPloblemNumber(String ploblemNumber) {
		this.ploblemNumber = ploblemNumber;
	}

	public String getPloblemNumber() {
		return ploblemNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}
}
