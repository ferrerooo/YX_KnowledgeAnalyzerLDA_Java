package com.kalda.dto.corpus;

public class NewCorpusTrainResult {

	private int topicNum;
	private Double probability;
	private String topWords;  // key words in .twords file
	
	private String selectedWords;  // key words manually selected. get from DB
	private String topicSummary;   // get from DB
	
	
	public int getTopicNum() {
		return topicNum;
	}
	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}
	public Double getProbability() {
		return probability;
	}
	public void setProbability(Double probability) {
		this.probability = probability;
	}
	public String getTopWords() {
		return topWords;
	}
	public void setTopWords(String topWords) {
		this.topWords = topWords;
	}
	public String getSelectedWords() {
		return selectedWords;
	}
	public void setSelectedWords(String selectedWords) {
		this.selectedWords = selectedWords;
	}
	public String getTopicSummary() {
		return topicSummary;
	}
	public void setTopicSummary(String topicSummary) {
		this.topicSummary = topicSummary;
	}
	
	

}
