package bhat.gupta.hummingbee.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WaterConsumptionCalculator {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/db_garden_sprinkler_sbsg";
	Map<String, Map<String, Double>> dayVolumeMapByZones;
	public WaterConsumptionCalculator() {
		this.dayVolumeMapByZones=new HashMap<String, Map<String, Double>>();
	
	}

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	public Map<String,Map<String, Double>> getWaterConsumptionData()
	{
		//Map<String, Map<String, Double>> dayVolumeMapByZones = new HashMap<String, Map<String, Double>>();
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			// Map<String,Double>
			// dayVolumeMap=objDB.getDayVolumeCalculationForZone("EAST",conn,stmt);
			List<String> zoneNames = getZoneNames(conn, stmt);
			
			for (String s : zoneNames) {
				Map<String, Double> dayVolumeMap = getDayVolumeCalculationForZone(s, conn, stmt);
				dayVolumeMapByZones.put(s, dayVolumeMap);
				//objDB.printMap(dayVolumeMap);
			}
			
			for (Entry<String,  Map<String, Double>> entry : dayVolumeMapByZones.entrySet()) {
				System.out.println("Zone: " + entry.getKey());
				printMap(entry.getValue());
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		return this.dayVolumeMapByZones;
		
	}
	
	public static void main(String[] args) {
//		WaterConsumptionCalculator objDB = new WaterConsumptionCalculator();
//		Map<String, Map<String, Double>> dayVolumeMapByZones = new HashMap<String, Map<String, Double>>();
//		Connection conn = null;
//		Statement stmt = null;
//		try {
//			// STEP 2: Register JDBC driver
//			Class.forName(JDBC_DRIVER);
//
//			// STEP 3: Open a connection
//			System.out.println("Connecting to database...");
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//			// STEP 4: Execute a query
//			System.out.println("Creating statement...");
//			stmt = conn.createStatement();
//			// Map<String,Double>
//			// dayVolumeMap=objDB.getDayVolumeCalculationForZone("EAST",conn,stmt);
//			List<String> zoneNames = objDB.getZoneNames(conn, stmt);
//			
//			for (String s : zoneNames) {
//				Map<String, Double> dayVolumeMap = objDB
//						.getDayVolumeCalculationForZone(s, conn, stmt);
//				dayVolumeMapByZones.put(s, dayVolumeMap);
//				//objDB.printMap(dayVolumeMap);
//			}
//			
//			for (Entry<String,  Map<String, Double>> entry : dayVolumeMapByZones.entrySet()) {
//				System.out.println("Zone: " + entry.getKey());
//				objDB.printMap(entry.getValue());
//			}
//
//		} catch (SQLException se) {
//			// Handle errors for JDBC
//			se.printStackTrace();
//		} catch (Exception e) {
//			// Handle errors for Class.forName
//			e.printStackTrace();
//		} finally {
//			// finally block used to close resources
//			try {
//				if (stmt != null)
//					stmt.close();
//			} catch (SQLException se2) {
//			}// nothing we can do
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}// end finally try
//		}// end try

	}

	public void printMap(Map<String, Double> dayVolumeMap) {
		for (Entry<String, Double> entry : dayVolumeMap.entrySet()) {
			System.out.println("Date: " + entry.getKey() + ", Volume: "
					+ entry.getValue());
		}
		// System.out.println(Collections.max(dayVolumeMap.values()));
	}

	public List<String> getZoneNames(Connection conn, Statement stmt) {

		List<String> zoneNames = new ArrayList<String>();
		String sql;
		sql = "SELECT DISTINCT zone_id FROM  tbl_water_consumption_zonewise";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				zoneNames.add(rs.getString("zone_id"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return zoneNames;
	}

	public Map<String, Double> getDayVolumeCalculationForZone(String strZoneId,
			Connection conn, Statement stmt) {
		Map<String, Double> dayVolumeMap = new LinkedHashMap<String, Double>();
		// Connection conn=null;
		// Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			// Class.forName(JDBC_DRIVER);
			//
			// //STEP 3: Open a connection
			// System.out.println("Connecting to database...");
			// conn = DriverManager.getConnection(DB_URL,USER,PASS);
			//
			// //STEP 4: Execute a query
			// System.out.println("Creating statement...");
			// stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM  tbl_water_consumption_zonewise where zone_id='"
					+ strZoneId + "' order by start_time asc";
			ResultSet rs = stmt.executeQuery(sql);
			// double volume=0.0;
			// String currentDate="";
			while (rs.next()) {
				String date = rs.getDate("start_time").toString();
				String zone_id = rs.getString("zone_id");
				int rate_of_water_flow = rs.getInt("rate_of_water_flow");
				String startTime = rs.getTime("start_time").toString();
				String endTime = rs.getTime("stop_time").toString();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				Date d1 = sdf.parse(startTime);
				Date d2 = sdf.parse(endTime);
				double elapsedTimeInHours = (d2.getTime() - d1.getTime())
						/ (double) 3600000;
				double volume = (double)elapsedTimeInHours * rate_of_water_flow;
				if (dayVolumeMap.containsKey(date)) {
					dayVolumeMap.put(date, dayVolumeMap.get(date) + volume);
				} else {
					dayVolumeMap.put(date, volume);
				}
				// System.out.println("Zone_id:"+zone_id +
				// "   , Rate of water flow:" +
				// rate_of_water_flow+", Date: "+date+
				// " Elapsed="+elapsedTimeInHours+", Volume: "+volume);

			}
			rs.close();

			// System.out.println(rs);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			// }catch(Exception e){
			// //Handle errors for Class.forName
			// e.printStackTrace();
			// }finally{
			// //finally block used to close resources
			// try{
			// if(stmt!=null)
			// stmt.close();
			// }catch(SQLException se2){
			// }// nothing we can do
			// try{
			// if(conn!=null)
			// conn.close();
			// }catch(SQLException se){
			// se.printStackTrace();
			// }//end finally try
		}// end try
 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dayVolumeMap;

	}

}
