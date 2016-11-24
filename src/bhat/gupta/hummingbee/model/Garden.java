package bhat.gupta.hummingbee.model;

import java.util.*;

public class Garden {

	private String name;
	private List<Zone> zones;

	public Garden(String name) {
		this.name = name;
		this.zones = new ArrayList<Zone>();
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
			zoneNames[i]=z.getGroupId();
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
	
	

}
