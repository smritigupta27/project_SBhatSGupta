package bhat.gupta.hummingbee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bhat.gupta.hummingbee.model.Garden;
import bhat.gupta.hummingbee.model.Sprinkler;
import bhat.gupta.hummingbee.model.Zone;

public class GardenController{

	Garden garden;
	Sprinkler east_sp1, east_sp2, east_sp3, east_sp4,west_sp1,west_sp2,west_sp3,west_sp4,north_sp1,north_sp2,north_sp3,north_sp4,south_sp1,south_sp2,south_sp3,south_sp4;
	Zone zoneEast, zoneWest, zoneSouth, zoneNorth, currentZone;
	private static final int DEFAULT_WATER_RATE = 30;
	//public enum SprinklerCondition {WORKING, NOT_WORKING};
	public enum ZoneId {NORTH, EAST, WEST, SOUTH}

	public GardenController(Garden garden) {

		// Initialize sprinklers and zones
		this.garden = garden;

		east_sp1 = new Sprinkler("E1", true);
		east_sp2 = new Sprinkler("E2", true);
		east_sp3 = new Sprinkler("E3", true);
		east_sp4 = new Sprinkler("E4", false);

		west_sp1 = new Sprinkler("W1", true);
		west_sp2 = new Sprinkler("W2", false);
		west_sp3 = new Sprinkler("W3", true);
		west_sp4 = new Sprinkler("W4", true);

		north_sp1 = new Sprinkler("N1", true);
		north_sp2 = new Sprinkler("N2", true);
		north_sp3 = new Sprinkler("N3", true);
		north_sp4 = new Sprinkler("N4", true);

		south_sp1 = new Sprinkler("S1", true);
		south_sp2 = new Sprinkler("S2", true);
		south_sp3 = new Sprinkler("S3", false);
		south_sp4 = new Sprinkler("S4", true);

		currentZone = null;
		zoneEast = new Zone(garden, ZoneId.EAST, east_sp1, east_sp2, east_sp3, east_sp4);
		zoneWest = new Zone(garden, ZoneId.WEST, west_sp1, west_sp2, west_sp3, west_sp4);
		zoneNorth = new Zone(garden, ZoneId.NORTH, north_sp1, north_sp2, north_sp3, north_sp4);
		zoneSouth = new Zone(garden, ZoneId.SOUTH, south_sp1, south_sp2, south_sp3, south_sp4);

		garden.addZone(zoneEast);
		garden.addZone(zoneWest);
		garden.addZone(zoneNorth);
		garden.addZone(zoneSouth);

	}

	public Garden getGarden() {
		return garden;
	}

	public Zone getZoneFromZoneIdString(String zoneId) {
		for (Zone z : garden.getZones()) {
			if (z.getGroupId().toString().equalsIgnoreCase(zoneId))
				return z;
		}
		return null;
	}

	public String getZoneStatus(String zoneId) {
		Zone z = getZoneFromZoneIdString(zoneId);
		String status = "";
		for (Sprinkler s : z.getZoneSprinklerList()) {
			status += "\n" + s.getStatus();
		}
		return status;
	}

	public void setZoneForProgramming(String zoneIdString) {
		currentZone = getZoneFromZoneIdString(zoneIdString);
	}

	public void setMaxTemp(String maxTempTextField) {

		int maxTemp = maxTempTextField != null && !maxTempTextField.equals("") ? Integer.parseInt(maxTempTextField)
				: -1;
		currentZone.setMaxTemperature(maxTemp);
	}

	public void setMinTemp(String minTempTextField) {
		int minTemp = minTempTextField != null && !minTempTextField.equals("") ? Integer.parseInt(minTempTextField)
				: -1;
		currentZone.setMinTemperature(minTemp);
	}

	public void setWaterRate(String waterRateString) {
		int waterRate = waterRateString != null && !waterRateString.equals("") ? Integer.parseInt(waterRateString)
				: DEFAULT_WATER_RATE;
		currentZone.setWaterFlow(waterRate);
	}

	/*public void setStartTime(Date beginTime) {
		currentZone.setStartTime(beginTime);

	}

	public void setStopTime(Date endTime) {
		currentZone.setEndTime(endTime);
	}*/
	
	public void setStartTime(String beginTime) {
		currentZone.setStartDate(beginTime);

	}

	public void setStopTime(String endTime) {
		currentZone.setEndDate(endTime);
	}

	public void saveProgramDataForZone() {
		ZoneId zoneId = currentZone.getGroupId();
		switch(zoneId){
		case EAST:
			zoneEast = currentZone;
			break;
			
		case WEST:
			zoneWest = currentZone;
			break;
		
		case NORTH:
			zoneNorth = currentZone;
			
		case SOUTH:
			zoneSouth = currentZone;
			
		}
		System.out.println("Zone Details = " + currentZone.toString());
	}
	

	/*
	 * When the environment temperature changes this method is called.
	 * It checks if the sprinklers need to be turned on by comparing the 
	 * current temp with the min and max temp setting for each zone.
	 * @ param int : The current environment temperature.
	 */
/*	public ArrayList<ZoneId> checkTempConditions(int environmentTemp){
		ArrayList<ZoneId> zonesToBeStarted = new ArrayList<ZoneId>();
		for (Zone z : garden.getZones()){
			 int maxTemp = z.getMaxTemperature();
			 if(maxTemp > 0 && environmentTemp >= maxTemp){ //to make sure max temp was set
				 zonesToBeStarted.add(z.getGroupId());
			 }
		}
		return zonesToBeStarted;
	}
*/
	public void setGarden(Garden garden) {
		this.garden = garden;
	}
	
	public void setCurTemperature(int temp) {
		this.garden.setTemperature(temp);
	}
	
	//swabhat
	public void saveTimeTemperature(String beginTime, String endTime, String minTemp, String maxTemp){
		currentZone.setStartDate(beginTime);
		currentZone.setEndDate(endTime);
		setMinTemp(minTemp);
		setMaxTemp(maxTemp);
		currentZone.setSchedule();
		
	}
	
	//swabhat
	public void addWaterData(Map<String,Map<String,Double>> dayVolumeMapByZones){
			
			
			for (Map.Entry<String, Map<String, Double>> entry : dayVolumeMapByZones.entrySet()) {
				
				Map<String, Double> dayVolumeMap = entry.getValue();
				switch(entry.getKey()) {
				
				case "EAST":
					zoneEast.addWaterData(dayVolumeMap);
					break;
					
				case "WEST":
					zoneWest.addWaterData(dayVolumeMap);
					break;
					
				case "NORTH":
					zoneNorth.addWaterData(dayVolumeMap);
					break;
					
				case "SOUTH":
					zoneSouth.addWaterData(dayVolumeMap);
				}
			}
				
	}
	//swabhat
}
