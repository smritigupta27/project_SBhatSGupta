package bhat.gupta.hummingbee.model;

import java.util.*;

public class Garden extends Observable{

	private String name;
	private List<Zone> zones;
	private int temp;

	public Garden(String name) {
		
		this.name = name;
		this.zones = new ArrayList<Zone>();
		this.temp = 0;
	}
	public Garden() {
		this.zones = new ArrayList<Zone>();
		this.temp = 0;
	}
	
	public void setTemperature(int temp) {
		this.temp = temp;

		for(Zone z:zones)
		{
			z.checkTemperature(this.temp);
		}
		notifyView();
	}

	public void addZone(Zone zone) {
		this.zones.add(zone);
	}

	public void removeZone(Zone zone) {
		this.zones.remove(zone);
	}
	public String[] getZoneNames()
	{
		String[] zoneNames=new String[zones.size()];
		int i=0;
		for(Zone z:zones)
		{
			zoneNames[i]=z.getGroupId().toString();
			i++;
		}
		return zoneNames;
	}

	public String getName() {
		return name;
	}

	public List<Zone> getZones() {
		return zones;
	}
	
	public void notifyView() {
		setChanged();
		notifyObservers();
	}
}
