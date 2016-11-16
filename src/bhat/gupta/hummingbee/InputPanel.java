package bhat.gupta.hummingbee;

import java.awt.*;

import javax.swing.*;
public class InputPanel extends JPanel{

	JButton btnProgramSprinkler,btnControlTemp,btnCheckSprinklerStatus,btnCheckWaterUsage;
	public InputPanel() {
		setBackground(Color.WHITE);
		btnCheckSprinklerStatus = new JButton("View Sprinkler Status");
		btnCheckWaterUsage = new JButton("Check Water Usage");
    	btnProgramSprinkler = new JButton("Program Sprinklers");
    	btnControlTemp = new JButton("Control Temperature");
    	setInputPanelLayout();
	}

	private void setInputPanelLayout()
 	{
 		this.setLayout(new GridLayout(2,2));
 		add(btnCheckSprinklerStatus);
 		add(btnCheckWaterUsage);
 		add(btnProgramSprinkler);
 		add(btnControlTemp);	 		
 	}
	
}
