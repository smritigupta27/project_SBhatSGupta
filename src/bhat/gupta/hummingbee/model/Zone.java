package bhat.gupta.hummingbee.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import bhat.gupta.hummingbee.controller.GardenController.ZoneId;
import bhat.gupta.hummingbee.controller.WaterConsumptionCalculator;

public class Zone implements  ActionListener, Comparator<Zone>{

	private ZoneId groupId;
	private int minTemperature;
	private int maxTemperature;
	private int waterFlow;
	private boolean isProgrammed;
	private Timer temperatureTimer, scheduleTimer;
	private boolean isOn;
	private Garden garden;
	private Date startDate = null, endDate = null, sysDate = null;
	private Map dayVolumeMap;
	private static final int TEMP_SPRINKLER_TIME = 60000; //in miliiseconds
	private static final int TEMP_RATE_OF_FLOW = 50;

	private List<Sprinkler> zoneSprinklerList;

	public Zone(Garden garden) {
		this.isProgrammed = false;
		this.garden = garden;
		zoneSprinklerList = new ArrayList<Sprinkler>();
		this.temperatureTimer = new Timer(TEMP_SPRINKLER_TIME, this);
	}

	public Zone(Garden garden, ZoneId groupId) {
		this.groupId = groupId;
		this.garden = garden;
		this.isProgrammed = false;
		this.temperatureTimer = new Timer(TEMP_SPRINKLER_TIME, this);
		this.zoneSprinklerList = new ArrayList<Sprinkler>();
	}

	public Zone(Garden garden, ZoneId groupId, Sprinkler... sprinklers) {
		this.groupId = groupId;
		this.isProgrammed = false;
		this.garden = garden;
		this.zoneSprinklerList = new ArrayList<Sprinkler>();
		this.temperatureTimer = new Timer(TEMP_SPRINKLER_TIME, this);
		for (Sprinkler s : sprinklers) {
			s.setZone(this);
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
		if(this.minTemperature > 0){
			activate();
		}else{
			deactivate();
		}
	}

	public int getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(int maxTemperature) {
		this.maxTemperature = maxTemperature;
		if(this.maxTemperature > 0){
			activate();
		}else{
			deactivate();
		}
	}

	
	  public Date getStartDate() { return startDate; }
	  
	  public void setStartDate(String startTime) 
	  
	  { 
		  String startHr = startTime.substring(0, 2);
		String startMin = startTime.substring(3,5);
		Calendar startCal = Calendar.getInstance();
		int hr = Integer.parseInt(startHr);
		startCal.set(Calendar.HOUR_OF_DAY, hr);
		startCal.set(Calendar.MINUTE, Integer.parseInt(startMin));
		startCal.set(Calendar.SECOND, 00);
		startDate = startCal.getTime(); 
		}
	  
	 public Date getEndDate() { return endDate; }
	  
	  public void setEndDate(String endTime) { 
		  String endHr = endTime.substring(0, 2);
			String endMin = endTime.substring(3,5);
			Calendar endCal = Calendar.getInstance();
			endCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHr));
			endCal.set(Calendar.MINUTE, Integer.parseInt(endMin));
			endCal.set(Calendar.SECOND, 00);
			endDate = endCal.getTime();
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

	public void removeSprinkler(Sprinkler sprinkler) {
		this.zoneSprinklerList.remove(sprinkler);
	}

	@Override
	public String toString() {
		return "GroupID = " + this.groupId + "\tStart time = " + this.getStartDate().toString() + "\tStop time = "
				+ this.getEndDate().toString() + "\tMin Temp = " + this.minTemperature + "\t Max Temp = " + this.maxTemperature
				+ "\twateRate = " + this.waterFlow;
	}

/*	public String getStartTime() {
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
	}*/
	
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
			if (!temperatureTimer.isRunning()) {
				temperatureTimer.start();
			}
			if (!isOn && temperatureTimer.isRunning()) {
				temperatureTimer.stop();
			}
				this.setOn(true);
		}
		
		if (temp <= this.minTemperature) {
				this.setOn(false); 
				//swabhat if the sprinklers are running according to schedule & temperature decreases then stop the scheduleTimmer
				//and reset it
				if(scheduleTimer != null){
					if(scheduleTimer.isRunning()){
						scheduleTimer.stop();
					}
				}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		temperatureTimer.stop();
		recordWaterDataForTemperature();
		//TODO: Before setting to false check if this is within schedule and turn off only if it is outside the schedule.
		if(!isScheduled()){
		this.isOn = false;
		garden.notifyView();
		
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Zone zone1, Zone zone2) {
		// TODO Auto-generated method stub
		return zone1.getGroupId().compareTo(zone2.getGroupId());
	}
	
	public boolean isScheduled(){
		
		
		// if sysdate between startDate and endDate then return true.
		if(startDate != null && endDate != null){ 
			if(sysDate.before(endDate) && sysDate.after(startDate) ){
				return true;
			}
		}
		return false;
		
	}
	public void setSchedule(){
		
		if(startDate != null && endDate != null){
			
			sysDate = getSysDate();
			
			
			double initialDelay = startDate.getTime() - sysDate.getTime();
			double stopTimeDelay = endDate.getTime() - startDate.getTime();
			
			scheduleTimer = new Timer((int) initialDelay, new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if(isOn){
						isOn = false;
						scheduleTimer.stop();//or make it repeat timer.repeat()
						recordWaterData();
						garden.notifyView();
						return;
					}
					if(!isOn){
					isOn = true;
					garden.notifyView();
					scheduleTimer.setDelay((int) stopTimeDelay);
					}
					
				}
			});
			scheduleTimer.start();
		}
	}
	
	public void recordWaterData(){
		WaterConsumptionCalculator pushWaterConsumptionData = new WaterConsumptionCalculator();
		int rateOfFlow = this.getWaterFlow();
	    String zoneId = groupId.toString();
	    pushWaterConsumptionData.insertWaterConsumptionData(zoneId, startDate , endDate, rateOfFlow);
				
	}
	
	public void recordWaterDataForTemperature(){
		
		WaterConsumptionCalculator pushWaterConsumptionData = new WaterConsumptionCalculator();
		Calendar sysCal = Calendar.getInstance();
		Date endDate = sysCal.getTime();
		sysCal.set(Calendar.MILLISECOND, -TEMP_SPRINKLER_TIME);
		Date startDate = sysCal.getTime();
	    String zoneId = groupId.toString();
	    pushWaterConsumptionData.insertWaterConsumptionData(zoneId, startDate , endDate, TEMP_RATE_OF_FLOW);
	}
	
	private Date getSysDate(){
		Calendar sysCal = Calendar.getInstance();
		Date sysDate = sysCal.getTime(); 
		return sysDate;
	}
	
	//swabhat
	public void addWaterData(Map<String, Double> dayVolumeMap){
		this.dayVolumeMap = dayVolumeMap;
	}
	//swabhat
}
