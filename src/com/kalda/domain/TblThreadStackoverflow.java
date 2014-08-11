package com.kalda.domain;


public class TblThreadStackoverflow {

	private Integer id; // key
	
	private String title;
	private Integer threadId;
	private String threadURL;
	
	private Integer voteAmount;
	private Integer answerAmount;
	
	private String answerStatus;
	
	private String component; 
	
	private String author;
	private Integer reputationScore;
	private Integer badgecount;
	
	
	public String getAnswerStatus() {
		return answerStatus;
	}
	public void setAnswerStatus(String answerStatus) {
		this.answerStatus = answerStatus;
	}
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
	public Integer getVoteAmount() {
		return voteAmount;
	}
	public void setVoteAmount(Integer voteAmount) {
		this.voteAmount = voteAmount;
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
	public Integer getReputationScore() {
		return reputationScore;
	}
	public void setReputationScore(Integer reputationScore) {
		this.reputationScore = reputationScore;
	}
	public Integer getBadgecount() {
		return badgecount;
	}
	public void setBadgecount(Integer badgecount) {
		this.badgecount = badgecount;
	}
	
	

}
