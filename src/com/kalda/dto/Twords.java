package com.kalda.dto;

import java.util.ArrayList;
import java.util.List;

// only one column -> word
public class Twords {

	private String ldaDocName;
	private Double alpah;
	private Double beta;
	private Integer ntopics;
	private Integer niters;
	private Integer twords;
	
	private List<TopWordInTopic> list = new ArrayList<TopWordInTopic>();

	
	
	public String getLdaDocName() {
		return ldaDocName;
	}

	public void setLdaDocName(String ldaDocName) {
		this.ldaDocName = ldaDocName;
	}

	public List<TopWordInTopic> getList() {
		return list;
	}

	public void setList(List<TopWordInTopic> list) {
		this.list = list;
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
