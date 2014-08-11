package com.kalda.dto.ldaanalysis;

/*only used in server side*/
/*discussed with professor on 2013/12/20. 
 * for the algorithm
 * that theta table, every line(document), choose top1 or top2 probability
 * then read theta table by column, get the chosen grids out!*/
public class ThetaGrid {

	private Integer docNum;
	private String docName;
	private Double topicProbabilityForThisDoc;
	
	private int topicNum;
	private int rank; // 1 or 2
	
	
	public Integer getDocNum() {
		return docNum;
	}
	public void setDocNum(Integer docNum) {
		this.docNum = docNum;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public Double getTopicProbabilityForThisDoc() {
		return topicProbabilityForThisDoc;
	}
	public void setTopicProbabilityForThisDoc(Double topicProbabilityForThisDoc) {
		this.topicProbabilityForThisDoc = topicProbabilityForThisDoc;
	}
	public int getTopicNum() {
		return topicNum;
	}
	public void setTopicNum(int topicNum) {
		this.topicNum = topicNum;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	

}
