package com.kalda.domain;

public class TblKeyWordsForTopic {

	private TblKeyWordsForTopicPK kwtpk;
	private String topicLable;
	private String keyWords;
	
	
	public String getTopicLable() {
		return topicLable;
	}
	public void setTopicLable(String topicLable) {
		this.topicLable = topicLable;
	}
	public TblKeyWordsForTopicPK getKwtpk() {
		return kwtpk;
	}
	public void setKwtpk(TblKeyWordsForTopicPK kwtpk) {
		this.kwtpk = kwtpk;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	
	

}
