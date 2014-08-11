package com.kalda.dto;

import java.util.ArrayList;
import java.util.List;

public class PhiLine {

	private Integer topicNumber;
	private List<WordInTopic> list = new ArrayList<WordInTopic>();
	
	private Double phiLineMeanValue;
	private Double standDeviationValue;
	
	public Double getPhiLineMeanValue() {
		return phiLineMeanValue;
	}
	public void setPhiLineMeanValue(Double phiLineMeanValue) {
		this.phiLineMeanValue = phiLineMeanValue;
	}
	public Double getStandDeviationValue() {
		return standDeviationValue;
	}
	public void setStandDeviationValue(Double standDeviationValue) {
		this.standDeviationValue = standDeviationValue;
	}
	public Integer getTopicNumber() {
		return topicNumber;
	}
	public void setTopicNumber(Integer topicNumber) {
		this.topicNumber = topicNumber;
	}
	public List<WordInTopic> getList() {
		return list;
	}
	public void setList(List<WordInTopic> list) {
		this.list = list;
	}
}
