package com.kalda.dto.ldaanalysis;

public class CorpusnameAndTopicamountcontained {

	// 这个id不是数据库里的key，而是在folder中，document排序的id
	private int docSeqId;
	private String name;
	private int topicAmount;
	
	// ei.... 'CorpusnameAndTopicamountcontained' is returned as a list. every element of this list has this same value.
    public double deviationOfTopicAmount=0; 
    
    public double meanOfTopicAmount=0;

	public int getDocSeqId() {
		return docSeqId;
	}
	public void setDocSeqId(int docSeqId) {
		this.docSeqId = docSeqId;
	}
	public double getMeanOfTopicAmount() {
		return meanOfTopicAmount;
	}
	public void setMeanOfTopicAmount(double meanOfTopicAmount) {
		this.meanOfTopicAmount = meanOfTopicAmount;
	}
	public double getDeviationOfTopicAmount() {
		return deviationOfTopicAmount;
	}
	public void setDeviationOfTopicAmount(double deviationOfTopicAmount) {
		this.deviationOfTopicAmount = deviationOfTopicAmount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTopicAmount() {
		return topicAmount;
	}
	public void setTopicAmount(int topicAmount) {
		this.topicAmount = topicAmount;
	}
	
	
	
}
