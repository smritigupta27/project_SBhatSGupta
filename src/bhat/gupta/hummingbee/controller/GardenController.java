package bhat.gupta.hummingbee.controller;

import bhat.gupta.hummingbee.model.Garden;
import bhat.gupta.hummingbee.model.Sprinkler;
import bhat.gupta.hummingbee.model.Zone;

public class GardenController {
	
	Garden garden;
//	Sprinkler east_sp1, east_sp2, east_sp3, east_sp4, west_sp1, west_sp2, west_sp3, west_sp4, north_sp1, 
//	north_sp2, north_sp3, north_sp4, south_sp1, south_sp2, south_sp3, south_sp4;
//	Zone zoneEast, zoneWest, zoneNorth, zoneSouth;
	//static final int NUMBER_OF_SPRINKLERS = 16;
	

		public GardenController(Garden garden){
		
		//Initialize sprinklers and zones
		this.garden =garden;
		
		Sprinkler east_sp1 = new Sprinkler("E1",true);
		Sprinkler east_sp2 = new Sprinkler("E2",true);
		Sprinkler east_sp3 = new Sprinkler("E3",true);
		Sprinkler east_sp4 = new Sprinkler("E4",false);
		
		Sprinkler west_sp1 = new Sprinkler("W1",true);
		Sprinkler west_sp2 = new Sprinkler("W2",false);
		Sprinkler west_sp3 = new Sprinkler("W3",true);
		Sprinkler west_sp4 = new Sprinkler("W4",true);
		
		Sprinkler north_sp1 = new Sprinkler("N1",true);
		Sprinkler north_sp2 = new Sprinkler("N2",true);
		Sprinkler north_sp3 = new Sprinkler("N3",true);
		Sprinkler north_sp4 = new Sprinkler("N4",true);
		
		Sprinkler south_sp1 = new Sprinkler("S1",true);
		Sprinkler south_sp2 = new Sprinkler("S2",true);
		Sprinkler south_sp3 = new Sprinkler("S3",false);
		Sprinkler south_sp4 = new Sprinkler("S4",true);
		
		
		Zone zoneEast = new Zone("East", east_sp1,east_sp2, east_sp3, east_sp4);
		Zone zoneWest = new Zone("West", west_sp1,west_sp2, west_sp3, west_sp4);
		Zone zoneNorth = new Zone("North", north_sp1,north_sp2, north_sp3, north_sp4);
		Zone zoneSouth = new Zone("South", south_sp1, south_sp2, south_sp3, south_sp4);
		
		garden.addZone(zoneEast);
		garden.addZone(zoneWest);
		garden.addZone(zoneNorth);
		garden.addZone(zoneSouth);
				
	}


		public Garden getGarden() {
			return garden;
		}
		
		public Zone getZoneFromZoneId(String zoneId)
		{
			for(Zone z: garden.getZones())
			{
				if(z.getGroupId().equalsIgnoreCase(zoneId))
					return z;
			}
			return null;
		}
		
		public String getZoneStatus(String zoneId)
		{
			Zone z=getZoneFromZoneId(zoneId);
			String status="";
			for(Sprinkler s: z.getZoneSprinklerList())
			{
				status+="\n"+s.getStatus();
			}
			return status;
		}

	

}
