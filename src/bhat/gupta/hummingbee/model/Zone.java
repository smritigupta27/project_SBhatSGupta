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
	List<Sprinkler> zoneSprinklerList;
	
	public Zone(String groupId){
		this.groupId = groupId;
	}
	
	public Zone(String groupId, Sprinkler... sprinklers){
		this.groupId = groupId;
		for(Sprinkler s : sprinklers){
			this.zoneSprinklerList.add(s);
		}
	}
	
	public void addSprinkler(ArrayList<Sprinkler> sprinklerId){
		this.zoneSprinklerList.addAll(sprinklerId);
	}
	
}
