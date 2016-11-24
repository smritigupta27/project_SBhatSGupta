package bhat.gupta.hummingbee.model;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Zone {
	
	private String groupId;
	private int minTemperature;
	private int maxTemperature;
	private Timestamp startTime;
	private Timestamp endTime;
	private int waterFlow;
	
	private List<Sprinkler> zoneSprinklerList;
	
	public Zone(String groupId){
		this.groupId = groupId;
		this.zoneSprinklerList=new ArrayList<Sprinkler>();
	}
	
	public Zone(String groupId, Sprinkler sprinkler){
		this.groupId = groupId;
		this.zoneSprinklerList=new ArrayList<Sprinkler>();
		this.zoneSprinklerList.add(sprinkler);
		}
	
	public Zone(String groupId, Sprinkler... sprinklers){
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
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
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
		return this.groupId;
	}
}
