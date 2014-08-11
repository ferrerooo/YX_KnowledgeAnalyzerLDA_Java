package com.kalda.dto;

public class TopicInDocument {

	private Integer topicNumber;
	private String corpusDocName;
	// 这个id不是数据库里的key，而是在folder中，document排序的id
	private Integer corpusDocId;
	private Double topicInDocumentProbability;
	
	public Integer getCorpusDocId() {
		return corpusDocId;
	}
	public void setCorpusDocId(Integer corpusDocId) {
		this.corpusDocId = corpusDocId;
	}
	public Integer getTopicNumber() {
		return topicNumber;
	}
	public void setTopicNumber(Integer topicNumber) {
		this.topicNumber = topicNumber;
	}
	public String getCorpusDocName() {
		return corpusDocName;
	}
	public void setCorpusDocName(String corpusDocName) {
		this.corpusDocName = corpusDocName;
	}
	public Double getTopicInDocumentProbability() {
		return topicInDocumentProbability;
	}
	public void setTopicInDocumentProbability(Double topicInDocumentProbability) {
		this.topicInDocumentProbability = topicInDocumentProbability;
	}

}
