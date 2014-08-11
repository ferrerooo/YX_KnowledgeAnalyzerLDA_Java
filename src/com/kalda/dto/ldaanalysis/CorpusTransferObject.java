package com.kalda.dto.ldaanalysis;

public class CorpusTransferObject {

	private Integer id;
	private String corpusDocName;  // input parameter
	private String corpusDocContent;
	private String corpusDocComment;
	
	private Integer replyTableKey;
	private Integer threadTableKey;
	private String  threadurl;
	private Integer replySequence;
	
	
	
	public Integer getReplySequence() {
		return replySequence;
	}
	public void setReplySequence(Integer replySequence) {
		this.replySequence = replySequence;
	}
	public String getThreadurl() {
		return threadurl;
	}
	public void setThreadurl(String threadurl) {
		this.threadurl = threadurl;
	}
	public Integer getReplyTableKey() {
		return replyTableKey;
	}
	public void setReplyTableKey(Integer replyTableKey) {
		this.replyTableKey = replyTableKey;
	}
	public Integer getThreadTableKey() {
		return threadTableKey;
	}
	public void setThreadTableKey(Integer threadTableKey) {
		this.threadTableKey = threadTableKey;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCorpusDocName() {
		return corpusDocName;
	}
	public void setCorpusDocName(String corpusDocName) {
		this.corpusDocName = corpusDocName;
	}
	public String getCorpusDocContent() {
		return corpusDocContent;
	}
	public void setCorpusDocContent(String corpusDocContent) {
		this.corpusDocContent = corpusDocContent;
	}
	public String getCorpusDocComment() {
		return corpusDocComment;
	}
	public void setCorpusDocComment(String corpusDocComment) {
		this.corpusDocComment = corpusDocComment;
	}
	
	
	
	

}
