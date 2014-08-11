package com.kalda.dto;

import java.util.ArrayList;
import java.util.List;

public class ThetaLine {

	private String corpusDocName;
	
	private List<TopicInDocument> list = new ArrayList<TopicInDocument>();
	
	
	public String getCorpusDocName() {
		return corpusDocName;
	}
	public void setCorpusDocName(String corpusDocName) {
		this.corpusDocName = corpusDocName;
	}
	public List<TopicInDocument> getList() {
		return list;
	}
	public void setList(List<TopicInDocument> list) {
		this.list = list;
	}	
	
}
