package com.kalda.domain;

public class TblUser {

	private Integer id;
	private String whichCorpus;
	private String userName;
	private Integer postAmount = 0;
	private Integer replyAmount = 0;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWhichCorpus() {
		return whichCorpus;
	}
	public void setWhichCorpus(String whichCorpus) {
		this.whichCorpus = whichCorpus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPostAmount() {
		return postAmount;
	}
	public void setPostAmount(int postAmount) {
		this.postAmount = postAmount;
	}
	public int getReplyAmount() {
		return replyAmount;
	}
	public void setReplyAmount(int replyAmount) {
		this.replyAmount = replyAmount;
	}

}
