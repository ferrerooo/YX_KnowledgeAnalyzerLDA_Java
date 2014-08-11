package com.kalda.domain;

import java.util.Date;

public class TblThreadJavaRanch {

	private Integer id; // key
	
	private String title;
	private Integer threadId;
	private String threadURL;
	
	private Integer answerAmount;
	
	private String component; 
	
	private String author;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getThreadId() {
		return threadId;
	}

	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
	}

	public String getThreadURL() {
		return threadURL;
	}

	public void setThreadURL(String threadURL) {
		this.threadURL = threadURL;
	}

	public Integer getAnswerAmount() {
		return answerAmount;
	}

	public void setAnswerAmount(Integer answerAmount) {
		this.answerAmount = answerAmount;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	

}
