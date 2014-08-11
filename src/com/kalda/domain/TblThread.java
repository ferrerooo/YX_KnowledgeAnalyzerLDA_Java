package com.kalda.domain;

import java.util.Date;

public class TblThread {
	
	private Integer id; // key
	
	private String title;
	private String issueDescription;
	private Integer threadId;
	private String threadURL;
	
	private Integer voteAmount;
	private Integer answerAmount;
	
	private String component; // for stackoverflow, this is the tags!!
	private String subComponent;   // for stackoverflow, this is not used
	
	private String author;
	private Integer authorForumScore;
	private Date creationTime;
	
	//---------------------------------------------------------------------
	
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
	public String getIssueDescription() {
		return issueDescription;
	}
	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
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
	public String getSubComponent() {
		return subComponent;
	}
	public void setSubComponent(String subComponent) {
		this.subComponent = subComponent;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getAuthorForumScore() {
		return authorForumScore;
	}
	public void setAuthorForumScore(Integer authorForumScore) {
		this.authorForumScore = authorForumScore;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
	

}
