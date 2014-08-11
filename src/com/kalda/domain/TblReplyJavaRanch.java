package com.kalda.domain;

public class TblReplyJavaRanch {

	private Integer id;
	private String replyContent;
	private String author;
	private Integer replySequenceNum;  // thread description is 0, first reply is 1 etc...
	private Integer threadId;  // foreign key for table 'TblThreadJavaRanch'
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getReplySequenceNum() {
		return replySequenceNum;
	}
	public void setReplySequenceNum(Integer replySequenceNum) {
		this.replySequenceNum = replySequenceNum;
	}
	public Integer getThreadId() {
		return threadId;
	}
	public void setThreadId(Integer threadId) {
		this.threadId = threadId;
	}
	
	
}
