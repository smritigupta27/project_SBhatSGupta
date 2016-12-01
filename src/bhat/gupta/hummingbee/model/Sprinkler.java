package bhat.gupta.hummingbee.model;

public class Sprinkler {
	private String sprinklerId;
	private boolean isFunctional;
	private int waterFlow;
	private Zone zone;
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	
	
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
		String strFunctional="";
		String strActive="";
		if(this.isFunctional)
			strFunctional="FUNCTIONAL";
		else
			strFunctional=" NOT FUNCTIONAL";
		
		if(this.getZone().isOn() && this.isFunctional)
		{
			strActive="ON";
		}
		else
		{
			strActive="OFF";
		}
//		return "Sprinkler ID: "+this.getSprinklerId()+",\tFunctional: "+this.isFunctional;
		return this.getSprinklerId()+","+strFunctional+","+strActive;
	}
	@Override
	public String toString() {
		return this.sprinklerId;
	}
	

}
