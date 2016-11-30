package bhat.gupta.hummingbee.model;

public class Sprinkler {
	private String sprinklerId;
	private boolean isFunctional;
	private int waterFlow;
	
	public Sprinkler(String sprinklerId){
		this.sprinklerId = sprinklerId;
		this.isFunctional=true;
	}
	public Sprinkler(String sprinklerId, boolean isFunctional){
		this.sprinklerId = sprinklerId;
		this.isFunctional=isFunctional;
	}
	public String getSprinklerId() {
		return sprinklerId;
	}
	public void setSprinklerId(String sprinklerId) {
		this.sprinklerId = sprinklerId;
	}
	public boolean isFunctional() {
		return isFunctional;
	}
	public void setFunctional(boolean isFunctional) {
		this.isFunctional = isFunctional;
	}
	
	public int getWaterFlow() {
		return waterFlow;
	}
	public void setWaterFlow(int waterFlow) {
		this.waterFlow = waterFlow;
	}
	
	public String getStatus() {
		return "Sprinkler ID: "+this.getSprinklerId()+",\tFunctional: "+this.isFunctional;
	}
	@Override
	public String toString() {
		return this.sprinklerId;
	}
	

}
