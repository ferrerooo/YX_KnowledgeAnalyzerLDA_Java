package com.kalda.dto;

public class TopWordInTopic {

	private Integer topicNumber;
	private String word;
	private Double wordInTopicProbability;


	public Double getWordInTopicProbability() {
		return wordInTopicProbability;
	}
	public void setWordInTopicProbability(Double wordInTopicProbability) {
		this.wordInTopicProbability = wordInTopicProbability;
	}
	public Integer getTopicNumber() {
		return topicNumber;
	}
	public void setTopicNumber(Integer topicNumber) {
		this.topicNumber = topicNumber;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
}
