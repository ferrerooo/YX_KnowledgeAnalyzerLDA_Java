package com.kalda.domain;

import java.io.Serializable;

public class TblKeyWordsForTopicPK implements Serializable {
	
	private static final long serialVersionUID = 1762369691490551212L;

	private String whichCorpus;
	private String whichLDARun;
	private Integer topicNum;
	
	public String getWhichCorpus() {
		return whichCorpus;
	}
	public void setWhichCorpus(String whichCorpus) {
		this.whichCorpus = whichCorpus;
	}
	public String getWhichLDARun() {
		return whichLDARun;
	}
	public void setWhichLDARun(String whichLDARun) {
		this.whichLDARun = whichLDARun;
	}
	public Integer getTopicNum() {
		return topicNum;
	}
	public void setTopicNum(Integer topicNum) {
		this.topicNum = topicNum;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof TblKeyWordsForTopicPK) {
			TblKeyWordsForTopicPK pk = (TblKeyWordsForTopicPK)o;
			if (this.whichCorpus.equals(pk.getWhichCorpus()) &&
					this.whichLDARun.equals(pk.getWhichLDARun()) &&
					this.topicNum.toString().equals(pk.getTopicNum().toString())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.whichCorpus.hashCode();
	}
}
