package com.kalda.dto.ldaanalysis;

public class ThetaThreshold {
	
	private String ldaRunDocName;	// input parameter
	
	private Integer totalGridAmount;
	private Integer totalCorpusDocAmount;
	
	private String note;
	private Integer selectedGridAmount;
	private Integer selectedCorpusDocAmount;

	private boolean total;			// input parameter
	
	
	private boolean percentile;		// input parameter
	private Double percentNum;		// input parameter
	// smallest value of probability of the selected grids as the threshold
	private Double percentThreshold;
	
	
	private boolean deviation;		// input parameter
	private Integer kValue;			// input parameter
	private Double meanValue;
	private Double standDeviationValue;
	private Double deviationValue;  // this value is what we want: meanValue + key X standDeviationValue. 	

	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getSelectedGridAmount() {
		return selectedGridAmount;
	}
	public void setSelectedGridAmount(Integer selectedGridAmount) {
		this.selectedGridAmount = selectedGridAmount;
	}
	public Integer getSelectedCorpusDocAmount() {
		return selectedCorpusDocAmount;
	}
	public void setSelectedCorpusDocAmount(Integer selectedCorpusDocAmount) {
		this.selectedCorpusDocAmount = selectedCorpusDocAmount;
	}
	public Integer getTotalCorpusDocAmount() {
		return totalCorpusDocAmount;
	}
	public void setTotalCorpusDocAmount(Integer totalCorpusDocAmount) {
		this.totalCorpusDocAmount = totalCorpusDocAmount;
	}
	public Double getPercentThreshold() {
		return percentThreshold;
	}
	public void setPercentThreshold(Double percentThreshold) {
		this.percentThreshold = percentThreshold;
	}
	public Integer getTotalGridAmount() {
		return totalGridAmount;
	}
	public void setTotalGridAmount(Integer totalGridAmount) {
		this.totalGridAmount = totalGridAmount;
	}
	public Double getStandDeviationValue() {
		return standDeviationValue;
	}
	public void setStandDeviationValue(Double standDeviationValue) {
		this.standDeviationValue = standDeviationValue;
	}
	public Double getMeanValue() {
		return meanValue;
	}
	public void setMeanValue(Double meanValue) {
		this.meanValue = meanValue;
	}
	public Double getDeviationValue() {
		return deviationValue;
	}
	public void setDeviationValue(Double deviationValue) {
		this.deviationValue = deviationValue;
	}
	public String getLdaRunDocName() {
		return ldaRunDocName;
	}
	public void setLdaRunDocName(String ldaRunDocName) {
		this.ldaRunDocName = ldaRunDocName;
	}
	public boolean isTotal() {
		return total;
	}
	public void setTotal(boolean total) {
		this.total = total;
	}
	public boolean isPercentile() {
		return percentile;
	}
	public void setPercentile(boolean percentile) {
		this.percentile = percentile;
	}
	
	public Double getPercentNum() {
		return percentNum;
	}
	public void setPercentNum(Double percentNum) {
		this.percentNum = percentNum;
	}
	public boolean isDeviation() {
		return deviation;
	}
	public void setDeviation(boolean deviation) {
		this.deviation = deviation;
	}
	public Integer getkValue() {
		return kValue;
	}
	public void setkValue(Integer kValue) {
		this.kValue = kValue;
	}
	
	
	

}
