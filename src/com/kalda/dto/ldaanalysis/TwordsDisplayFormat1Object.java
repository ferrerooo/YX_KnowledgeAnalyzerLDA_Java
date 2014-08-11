package com.kalda.dto.ldaanalysis;

public class TwordsDisplayFormat1Object {

	private Integer topicNumber;
	private String topicnumAndWords;
	private Double topicTotalProbability;
	private String topicnumAndWordsAndProbability;
	
	
	public Integer getTopicNumber() {
		return topicNumber;
	}
	public void setTopicNumber(Integer topicNumber) {
		this.topicNumber = topicNumber;
	}
	public String getTopicnumAndWords() {
		return topicnumAndWords;
	}
	public void setTopicnumAndWords(String topicnumAndWords) {
		this.topicnumAndWords = topicnumAndWords;
	}
	public Double getTopicTotalProbability() {
		return topicTotalProbability;
	}
	public void setTopicTotalProbability(Double topicTotalProbability) {
		this.topicTotalProbability = topicTotalProbability;
	}
	public String getTopicnumAndWordsAndProbability() {
		return topicnumAndWordsAndProbability;
	}
	public void setTopicnumAndWordsAndProbability(
			String topicnumAndWordsAndProbability) {
		this.topicnumAndWordsAndProbability = topicnumAndWordsAndProbability;
	}
}
