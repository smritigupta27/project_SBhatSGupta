package bhat.gupta.hummingbee.model;

import java.security.Timestamp;
import java.util.Date;

public class Sprinkler {
	private String sprinklerId;
	private boolean isFunctional;
	private boolean isOn;
	private int minTemperature;
	private int maxTemperature;
	private Timestamp startTime;
	private Timestamp endTime;
	private int waterFlow;
	
	public Sprinkler(String sprinklerId){
		this.sprinklerId = sprinklerId;
		this.isOn=false;
		this.isFunctional=true;
	}
	public Sprinkler(String sprinklerId, boolean isFunctional){
		this.sprinklerId = sprinklerId;
		this.isOn=false;
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
	public boolean isOn() {
		return isOn;
	}
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	public int getMinTemperature() {
		return minTemperature;
	}
	public void setMinTemperature(int minTemperature) {
		this.minTemperature = minTemperature;
	}
	public int getMaxTemperature() {
		return maxTemperature;
	}
	public void setMaxTemperature(int maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public int getWaterFlow() {
		return waterFlow;
	}
	public void setWaterFlow(int waterFlow) {
		this.waterFlow = waterFlow;
	}
	
	public String getStatus() {
		return "Sprinkler ID: "+this.getSprinklerId()+",\tFunctional: "+this.isFunctional+ ",\tON: "+this.isOn;
	}
	@Override
	public String toString() {
		return this.sprinklerId;
	}

}
