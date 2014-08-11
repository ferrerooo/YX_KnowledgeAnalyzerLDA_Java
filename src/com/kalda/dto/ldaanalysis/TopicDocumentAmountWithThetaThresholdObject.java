package com.kalda.dto.ldaanalysis;

public class TopicDocumentAmountWithThetaThresholdObject {

	private int topicNum;
	private String topicString;
	private int corpusDocAmount; // how many corpus doc amount, a topic has
	
	
	public int getTopicNum() {
		return topicNum;
	}
	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}
	public String getTopicString() {
		return topicString;
	}
	public void setTopicString(String topicString) {
		this.topicString = topicString;
	}
	public int getCorpusDocAmount() {
		return corpusDocAmount;
	}
	public void setCorpusDocAmount(int corpusDocAmount) {
		this.corpusDocAmount = corpusDocAmount;
	}
	
	

}
