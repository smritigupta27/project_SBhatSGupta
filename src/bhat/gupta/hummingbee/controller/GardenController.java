package bhat.gupta.hummingbee.controller;

import bhat.gupta.hummingbee.model.Sprinkler;
import bhat.gupta.hummingbee.model.Zone;

public class GardenController {
	Sprinkler sp1, sp2, sp3, sp4, sp5, sp6, sp7, sp8, sp9, 
				sp10, sp11, sp12, sp13, sp14, sp15, sp16;
	Zone zoneEast, zoneWest, zoneNorth, zoneSouth;
	static final int NUMBER_OF_SPRINKLERS = 16;
	

		public GardenController(){
		//Initialize sprinklers and zones
		
		sp1 = new Sprinkler("SP1");
		sp2 = new Sprinkler("SP2");
		sp3 = new Sprinkler("SP3");
		sp4 = new Sprinkler("SP4");
		
		sp5 = new Sprinkler("SP5");
		sp6 = new Sprinkler("SP6");
		sp7 = new Sprinkler("SP7");
		sp8 = new Sprinkler("SP8");
		
		sp9 = new Sprinkler("SP9");
		sp10 = new Sprinkler("SP10");
		sp11 = new Sprinkler("SP11");
		sp12 = new Sprinkler("SP12");
		
		sp13 = new Sprinkler("SP13");
		sp14 = new Sprinkler("SP14");
		sp15 = new Sprinkler("SP15");
		sp16 = new Sprinkler("SP16");
		
		zoneEast = new Zone("ZE", sp1,sp2, sp3, sp4);
		zoneWest = new Zone("ZW", sp5,sp6, sp7, sp8);
		zoneNorth = new Zone("ZN", sp9,sp10, sp11, sp12);
		zoneSouth = new Zone("ZS", sp13, sp14, sp15, sp16);
				
	}

	public static void main(String args[]){
		
	}

}
