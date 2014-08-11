package com.kalda.dto;

import java.util.ArrayList;
import java.util.List;


// line -> document, column -> topic
public class Theta {

	private String ldaDocName;
	private Double alpah;
	private Double beta;
	private Integer ntopics;
	private Integer niters;
	private Integer twords;
	
	private List<ThetaLine> lineList = new ArrayList<ThetaLine>();
	private List<ThetaColumn> columnList = new ArrayList<ThetaColumn>();
	
	
	
	
	public String getLdaDocName() {
		return ldaDocName;
	}
	public void setLdaDocName(String ldaDocName) {
		this.ldaDocName = ldaDocName;
	}
	public List<ThetaColumn> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<ThetaColumn> columnList) {
		this.columnList = columnList;
	}
	public List<ThetaLine> getLineList() {
		return lineList;
	}
	public void setLineList(List<ThetaLine> lineList) {
		this.lineList = lineList;
	}
	public Double getAlpah() {
		return alpah;
	}
	public void setAlpah(Double alpah) {
		this.alpah = alpah;
	}
	public Double getBeta() {
		return beta;
	}
	public void setBeta(Double beta) {
		this.beta = beta;
	}
	public Integer getNtopics() {
		return ntopics;
	}
	public void setNtopics(Integer ntopics) {
		this.ntopics = ntopics;
	}
	public Integer getNiters() {
		return niters;
	}
	public void setNiters(Integer niters) {
		this.niters = niters;
	}
	public Integer getTwords() {
		return twords;
	}
	public void setTwords(Integer twords) {
		this.twords = twords;
	}
	
	

}
