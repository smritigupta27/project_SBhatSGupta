package bhat.gupta.hummingbee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class InputPanel extends JPanel implements ActionListener{

	JButton btnProgramSprinkler,btnControlTemp,btnCheckSprinklerStatus,btnCheckWaterUsage;
	public InputPanel() {
		setBackground(Color.WHITE);
		btnCheckSprinklerStatus = new JButton("View Sprinkler Status");
		btnCheckSprinklerStatus.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action Command = " + e.getActionCommand());
		//if(){
			
			
	//	}
		
	}
	
}
