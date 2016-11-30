package bhat.gupta.hummingbee.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import bhat.gupta.hummingbee.controller.GardenController.ZoneId;
import bhat.gupta.hummingbee.model.Garden;
import bhat.gupta.hummingbee.model.Sprinkler;
import bhat.gupta.hummingbee.model.Zone;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class ViewGardenPanel extends JPanel implements Observer {

	private Graphics2D g2d;
	private final String ACTIVE_SPRINKLER_IMG = "/bhat/gupta/hummingbee/resources/ActiveSprinkler.jpg";
	private final String INACTIVE_SPRINKLER_IMG = "/bhat/gupta/hummingbee/resources/InactiveSprinkler.jpg";
	private final String WATER_SPRINKLER_IMG = "/bhat/gupta/hummingbee/resources/WaterSprinkler1.jpg";
	private int panelWidth, panelHeight, zoneHeight, zoneWidth, columnWidth, imageHeight;
	private BufferedImage activeImage, inactiveImage, waterSprinkler;
	private Timer timer;
	private boolean initialize;
	private int startX, startY, endX1, endX2, endY1, endY2;
	private int numOfSprinklers; // Swabhat - might be needed for animation
	private Map<ZoneId, ArrayList<Boolean>> sprinklerConditionMap;
	private ZoneId zone;
	private Garden garden;
	
	/*public ViewGardenPanel(HashMap<ZoneId, ArrayList<Boolean>> sprinklerConditionMap) {
		//zone = observable
		this.sprinklerConditionMap = new HashMap<ZoneId, ArrayList<Boolean>>();
		this.sprinklerConditionMap = sprinklerConditionMap;
		timer = new Timer(1000, this);
		initialize = true;
		startSprinkler = false;
		System.out.println("Initialize set to true");
		//timer.start();
	}*/
	
	public ViewGardenPanel(Garden observableGarden){
		//garden = new Garden();
	
		garden = observableGarden;
		garden.addObserver(this);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;

//		if (initialize) { // Swabhat - might be needed for animation
			// initialize few required variables
			initializeVariables();
//		}
			
		// give background garden color
		setBackgroundColorForGarden();

		// draw zone boundary lines
		drawBoundaryLines();

		// Write the zone names
		writeZoneNames();

		// draw sprinklers in each zone
		drawSprinklers();

		
	//	if (initialize) { // Swabhat - might be needed for animation
		// initialize the points for drawing waterLines
			//intitializeWaterLinePoints(); //-- calling it from update
			//initialize = false;
	//	}

//		Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 7, 3 }, 0);
//		g2d.setStroke(dashed);
//		g2d.setColor(Color.BLUE);

	}

	/*
	 * initialize few member variables
	 */
	private void initializeVariables() {
		Dimension df = this.getSize();
		panelWidth = (int) df.getWidth();
		panelHeight = (int) df.getHeight();
		zoneHeight = panelHeight / 2;
		zoneWidth = panelWidth / 2;
		columnWidth = panelWidth / 10;
	}

	/*
	 * draw a rectangle of screen size and fill it with green gradient
	 */
	private void setBackgroundColorForGarden() {

		int startPointX = 0;
		int startPointY = 0;

		int endPointX = panelWidth;
		int endPointY = panelHeight;
		Color startColor = new Color(50, 120, 44);
		Color endColor = new Color(140, 210, 133);

		boolean isCyclic = true;
		g2d.setPaint(new GradientPaint(startPointX, startPointY, startColor, endPointX, endPointY, endColor, isCyclic));
		g2d.fill(getVisibleRect());
	}

	/*
	 * helper method that will draw the separating lines for the four zones
	 *
	 */
	private void drawBoundaryLines() {
		int startX, startY, endX, endY;
		g2d.setPaint(Color.black);
		// draw vertical line
		startX = panelWidth / 2;
		startY = 0;
		endX = panelWidth / 2;
		endY = panelHeight;
		Line2D lineV = new Line2D.Double(startX, startY, endX, endY);
		g2d.draw(lineV);

		// draw horizontal line
		startX = 0;
		startY = panelHeight / 2;
		endX = panelWidth;
		endY = panelHeight / 2;

		Line2D lineH = new Line2D.Double(startX, startY, endX, endY);
		g2d.draw(lineH);
		g2d.fill(lineH);
	}

	/*
	 * Helper method that will write the names of each zone.
	 */
	private void writeZoneNames() {
		int startX, startY;
		int fontAscent = g2d.getFontMetrics().getMaxAscent();
		Font font = g2d.getFont();
		FontMetrics metrics = g2d.getFontMetrics(font);
		String nZoneText = "North Zone";
		int textWidthHf = metrics.stringWidth(nZoneText) / 2;
		// Write North Zone
		startX = (panelWidth / 4 - textWidthHf);
		startY = panelHeight / 2 - fontAscent;
		g2d.drawString(nZoneText, (int) startX, (int) startY);

		// Write East Zone
		startX = ((3 * panelWidth) / 4 - textWidthHf);
		startY = panelHeight / 2 - fontAscent;
		g2d.drawString("East Zone", (int) startX, (int) startY);

		// Write West Zone
		startX = ((panelWidth) / 4 - textWidthHf);
		startY = panelHeight - fontAscent;
		g2d.drawString("West Zone", (int) startX, (int) startY);

		// Write South Zone
		startX = ((3 * panelWidth) / 4 - textWidthHf);
		startY = panelHeight - fontAscent;
		g2d.drawString("South Zone", (int) startX, (int) startY);

	}

	/*
	 * Helper method that will draw sprinklers in each Zone
	 */
	private void drawSprinklers() {

		activeImage = createImageFromFile(ACTIVE_SPRINKLER_IMG);
		inactiveImage = createImageFromFile(INACTIVE_SPRINKLER_IMG);
		waterSprinkler = createImageFromFile(WATER_SPRINKLER_IMG);
		imageHeight = activeImage.getHeight();

		// sprinklerCondition
		// draw sprinklers for one zone at a time

		for (Zone zone : garden.getZones()) {
			//ArrayList<Boolean> sprinklerConditionList = (ArrayList<Boolean>) sprinklerConditionMap.get(zoneName);
			switch (zone.getGroupId()) {

			case EAST:
				drawEastZoneSprinklers(zone);
				break;

			case WEST:
				drawWestZoneSprinklers(zone);
				break;

			case NORTH:
				drawNorthZoneSprinklers(zone);

			case SOUTH:
				drawSouthZoneSprinklers(zone);

			}
		}
	}

	/*
	 * Helper method to retrieve active and inactive sprinkler image from files
	 */
	private BufferedImage createImageFromFile(String path) {
		BufferedImage image;
		try {
			// read from file in working directory
			File file = new File(path);
			if (file.isFile()) {
				image = ImageIO.read(file);
			}
			// or read from file in same directory as this .class file
			else {
				URL url = getClass().getResource(path);
				if (url == null) {
					url = new URL(path);
				}
				image = ImageIO.read(url);
			}

		} catch (IOException e) {
			throw new RuntimeException("Could not open file: " + path);
		}
		return image;
	}

	/*
	 * helper method that displays the appropriate sprinkler image for
	 * functional and non functional sprinklers in the North Zone
	 */
	private void drawNorthZoneSprinklers(Zone zone) {

		// draw the image 4 times in each zone
		int startX, startY;
		startX = columnWidth;
		startY = panelHeight / 2 - imageHeight - 30;
		BufferedImage image;
		for (Sprinkler s : zone.getZoneSprinklerList()) {
			if (s.isFunctional()) {
				image = activeImage;
			} else {
				image = inactiveImage;
			}
			g2d.drawImage(image, startX, startY, null);
			startX += columnWidth;
		}
	}

	/*
	 * helper method that displays the appropriate sprinkler image for
	 * functional and non functional sprinklers in the East Zone
	 */
//	private void drawEastZoneSprinklers(ArrayList<Boolean> sprinklerConditionList) {
	private void drawEastZoneSprinklers(Zone zone) {
		
		// draw the image 4 times in each zone
		int startX, startY;
		startX = zoneWidth + columnWidth;
		startY = zoneHeight - imageHeight - 30;
		for (Sprinkler s : zone.getZoneSprinklerList()) {
			BufferedImage image = zone.isOn() ? waterSprinkler : activeImage;
			if (!s.isFunctional()) {
				image = inactiveImage;
			}
			g2d.drawImage(image, startX, startY, null);
			startX += columnWidth;
		}
	}

	/*
	 * helper method that displays the appropriate sprinkler image for
	 * functional and non functional sprinklers in the East Zone
	 */
	private void drawWestZoneSprinklers(Zone zone) {

		// draw the image 4 times in each zone
		int startX, startY;
		startX = columnWidth;
		startY = panelHeight - imageHeight - 30;
		BufferedImage image;
		for (Sprinkler s : zone.getZoneSprinklerList()) {
			if (s.isFunctional()) {
				image = activeImage;
			} else {
				image = inactiveImage;
			}
			g2d.drawImage(image, startX, startY, null);
			startX += columnWidth;
		}
	}

	/*
	 * helper method that displays the appropriate sprinkler image for
	 * functional and non functional sprinklers in the East Zone
	 */
	private void drawSouthZoneSprinklers(Zone zone) {

		// draw the image 4 times in each zone
		int startX, startY;
		startX = zoneWidth + columnWidth;
		startY = panelHeight - imageHeight - 30;
		BufferedImage image;
		for (Sprinkler s : zone.getZoneSprinklerList()) {
			if (s.isFunctional()) {
				image = activeImage;
			} else {
				image = inactiveImage;
			}
			g2d.drawImage(image, startX, startY, null);
			startX += columnWidth;
		}
	}

	/*
	 * draw for each zone separately. draw dashed line starting from the
	 * sprinkler to the upper boundary of the zone. define all points again
	 * every time for each zone because later each zone related water lines will
	 * be called separately/independently. There is no startX1, startX2 ..Y1,
	 * ..Y2 coz starting point is same for both the lines
	 */
	private void intitializeWaterLinePoints() {

		// imageHeight = image.getHeight();
		int hGap = 10;// 10 - Keep gap between lower and upper zones

		// draw for north Zone
		startX = columnWidth;
		startY = zoneHeight - imageHeight - 30;
		endX1 = 0;
		endY1 = hGap;
		endX2 = endX1 + 2 * columnWidth;
		numOfSprinklers = 0;

	}

	// Swabhat - might be needed for animation
	public void drawNorthZoneLines() {

		System.out.println("InsideNorthZoneLines");
		g2d.drawLine(startX, startY, endX1, endY1);
		g2d.drawLine(startX, startY, endX2, endY1);
		startX += columnWidth;
		endX1 += columnWidth;
		endX2 += columnWidth;
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (numOfSprinklers < 3) {
//			repaint();
//		} else {
//			timer.stop();
//		}
//		numOfSprinklers++;
//	}

	/*
	 * 
	 */
//	public void startSprinklersCozOfTemperature(ArrayList<ZoneId> zonesToBeStarted){
//		repaint();
//	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Starting Sprinkler");
		repaint();
	}
}
