package bhat.gupta.hummingbee.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.Timer;

import bhat.gupta.hummingbee.controller.GardenController.ZoneId;

public class Zone implements  ActionListener, Comparator<Zone>{

	private ZoneId groupId;
	private int minTemperature;
	private int maxTemperature;
	private String startTime;
	private String endTime;
	private int waterFlow;
	private boolean isProgrammed;
	private Timer timer;
	private boolean isOn;
	private Garden garden;

	private List<Sprinkler> zoneSprinklerList;

	public Zone(Garden garden) {
		this.isProgrammed = false;
		this.garden = garden;
		zoneSprinklerList = new ArrayList<Sprinkler>();
		this.timer = new Timer(5000, this);
	}

	public Zone(Garden garden, ZoneId groupId) {
		this.groupId = groupId;
		this.garden = garden;
		this.isProgrammed = false;
		this.timer = new Timer(5000, this);
		this.zoneSprinklerList = new ArrayList<Sprinkler>();
	}

	public Zone(Garden garden, ZoneId groupId, Sprinkler... sprinklers) {
		this.groupId = groupId;
		this.isProgrammed = false;
		this.garden = garden;
		this.zoneSprinklerList = new ArrayList<Sprinkler>();
		this.timer = new Timer(5000, this);
		for (Sprinkler s : sprinklers) {
			this.zoneSprinklerList.add(s);
		}
	}

	public void addSprinklers(ArrayList<Sprinkler> sprinklers) {
		this.zoneSprinklerList.addAll(sprinklers);
	}

	public void addSprinkler(Sprinkler sprinkler) {
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
		activate();
	}

	public int getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(int maxTemperature) {
		this.maxTemperature = maxTemperature;
		activate();
	}

	/*
	 * public Date getStartTime() { return startTime; }
	 * 
	 * public void setStartTime(Date startTime) { this.startTime = startTime; }
	 * 
	 * public Date getEndTime() { return endTime; }
	 * 
	 * public void setEndTime(Date endTime) { this.endTime = endTime; }
	 */

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

	public void removeSprinkler(Sprinkler sprinkler) {
		this.zoneSprinklerList.remove(sprinkler);
	}

	@Override
	public String toString() {
		return "GroupID = " + this.groupId + "\tStart time = " + this.getStartTime() + "\tStop time = "
				+ this.getEndTime() + "\tMin Temp = " + this.minTemperature + "\t Max Temp = " + this.maxTemperature
				+ "\twateRate = " + this.waterFlow;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		 
		this.startTime = startTime;
		/*setChanged();
	      // notify Observers that model has changed
		 
	      notifyObservers();
	      System.out.println("notified");*/

	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public boolean isOn() {
		return isOn;
	}
	
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	public void activate() {
		this.isProgrammed = true;
	}
	
	public void deactivate() {
		if (!isProgrammed) {
			return;
		}
		this.isProgrammed = false;
		this.setOn(true);
	}
	
	public void checkTemperature(int temp) {
		if (!isProgrammed) {
			return;
		}
		
		if (temp >= this.maxTemperature) {
			if (!timer.isRunning()) {
				timer.start();
			}
			if (!isOn && timer.isRunning()) {
				timer.stop();
			}
				this.setOn(true);
		}
		
		if (temp <= this.minTemperature) {
				this.setOn(false); 
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.stop();
		//TODO: Before setting to false check if this is within schedule and turn off only if it is outside the schedule.
		this.isOn = false;
		garden.notifyView();
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Zone zone1, Zone zone2) {
		// TODO Auto-generated method stub
		return zone1.getGroupId().compareTo(zone2.getGroupId());
	}
}
