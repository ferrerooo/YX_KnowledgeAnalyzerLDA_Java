package com.kalda.dto;

import java.util.ArrayList;
import java.util.List;

public class ThetaColumn {

	
	private Integer topicNumber;
	
	private List<TopicInDocument> list = new ArrayList<TopicInDocument>();
	
	public List<TopicInDocument> getList() {
		return list;
	}
	public void setList(List<TopicInDocument> list) {
		this.list = list;
	}
	public Integer getTopicNumber() {
		return topicNumber;
	}
	public void setTopicNumber(Integer topicNumber) {
		this.topicNumber = topicNumber;
	}	
	
}
