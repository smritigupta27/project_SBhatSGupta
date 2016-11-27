package bhat.gupta.hummingbee.model;

import java.util.ArrayList;
import java.util.List;

import bhat.gupta.hummingbee.controller.GardenController.ZoneId;



public class Zone {
	
	private ZoneId groupId;
	private int minTemperature;
	private int maxTemperature;
	private String startTime;
	private String endTime;
	private int waterFlow;
	
	private List<Sprinkler> zoneSprinklerList;
	
	public Zone(){
		zoneSprinklerList = new ArrayList<Sprinkler>();
	}
	
	public Zone(ZoneId groupId){
		this.groupId = groupId;
		this.zoneSprinklerList=new ArrayList<Sprinkler>();
	}
	
	public Zone(ZoneId groupId, Sprinkler sprinkler){
		this.groupId = groupId;
		this.zoneSprinklerList=new ArrayList<Sprinkler>();
		this.zoneSprinklerList.add(sprinkler);
		}
	
	public Zone(ZoneId groupId, Sprinkler... sprinklers){
		this.groupId = groupId;
		this.zoneSprinklerList=new ArrayList<Sprinkler>();
		for(Sprinkler s : sprinklers){
			this.zoneSprinklerList.add(s);
		}
	}
	
	public void addSprinklers(ArrayList<Sprinkler> sprinklers){
		this.zoneSprinklerList.addAll(sprinklers);
	}
	
	public void addSprinkler(Sprinkler sprinkler){
		this.zoneSprinklerList.add(sprinkler);
	}
	
	public ZoneId getGroupId() {
		return groupId;
	}

	public void setGroupId(ZoneId groupId) {
		this.groupId = groupId;
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

	/*public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}*/

	public int getWaterFlow() {
		return waterFlow;
	}

	public void setWaterFlow(int waterFlow) {
		this.waterFlow = waterFlow;
	}

	public List<Sprinkler> getZoneSprinklerList() {
		return zoneSprinklerList;
	}

	public void setZoneSprinklerList(List<Sprinkler> zoneSprinklerList) {
		this.zoneSprinklerList = zoneSprinklerList;
	}

	public void removeSprinkler(Sprinkler sprinkler){
		this.zoneSprinklerList.remove(sprinkler);
	}
	
	@Override
	public String toString() {
		return "GroupID = " + this.groupId +
				"\tStart time = " + this.getStartTime() +
				"\tStop time = " + this.getEndTime() +
				"\tMin Temp = " + this.minTemperature + 
				"\t Max Temp = " + this.maxTemperature + 
				"\twateRate = " + this.waterFlow;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
