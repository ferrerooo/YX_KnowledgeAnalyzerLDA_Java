package com.kalda.dto.ldaanalysis;

public class TopicDocListWithThetaThreshold {

	private Integer docNum;
	private String docName;
	private Double topicProbabilityForThisDoc;
	

	public Integer getDocNum() {
		return docNum;
	}
	public void setDocNum(Integer docNum) {
		this.docNum = docNum;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public Double getTopicProbabilityForThisDoc() {
		return topicProbabilityForThisDoc;
	}
	public void setTopicProbabilityForThisDoc(Double topicProbabilityForThisDoc) {
		this.topicProbabilityForThisDoc = topicProbabilityForThisDoc;
	}
	
	

}
