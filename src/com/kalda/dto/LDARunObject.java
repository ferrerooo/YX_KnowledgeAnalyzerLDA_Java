package com.kalda.dto;

public class LDARunObject {

	private Twords twords;	
	private Phi phi;	
	private Theta theta;
	private Tassign tassign;
	
	
	public Twords getTwords() {
		return twords;
	}
	public void setTwords(Twords twords) {
		this.twords = twords;
	}
	public Tassign getTassign() {
		return tassign;
	}
	public void setTassign(Tassign tassign) {
		this.tassign = tassign;
	}
	public Phi getPhi() {
		return phi;
	}
	public void setPhi(Phi phi) {
		this.phi = phi;
	}
	public Theta getTheta() {
		return theta;
	}
	public void setTheta(Theta theta) {
		this.theta = theta;
	}
	
	

}
