package bhat.gupta.hummingbee;

import java.awt.*;

import javax.swing.*;

public class GardenPanel extends JPanel{

	JLabel lblGarden;
	public GardenPanel() {
    	setGardenPanelLayout();
    	lblGarden = new JLabel("This is the garden panel");
    	lblGarden.setFont(new Font("Serif", Font.PLAIN, 20));
    	lblGarden.setForeground(Color.BLUE);
		setBackground(Color.green);
 		add(lblGarden);
	}

	private void setGardenPanelLayout()
 	{
 		this.setLayout(new GridLayout(2,2));	 		
 	}
}
