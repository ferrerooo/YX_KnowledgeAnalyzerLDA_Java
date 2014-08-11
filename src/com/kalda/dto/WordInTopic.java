package com.kalda.dto;

public class WordInTopic {

	private Integer wordNum;  // this is the number in wordmap.txt
	private String word;
	private Double wordInTopicProbability;
	
	
	public Integer getWordNum() {
		return wordNum;
	}
	public void setWordNum(Integer wordNum) {
		this.wordNum = wordNum;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Double getWordInTopicProbability() {
		return wordInTopicProbability;
	}
	public void setWordInTopicProbability(Double wordInTopicProbability) {
		this.wordInTopicProbability = wordInTopicProbability;
	}
	
}
