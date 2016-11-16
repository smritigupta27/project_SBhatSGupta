package bhat.gupta.hummingbee;

import java.awt.*;

import javax.swing.*;

public class OutputPanel extends JPanel{
	JLabel lblOutput;
	public OutputPanel() {
    	setOutputPanelLayout();
    	lblOutput = new JLabel("This is the ouput panel");
    	lblOutput.setFont(new Font("Serif", Font.PLAIN, 20));
    	lblOutput.setForeground(Color.WHITE);
    	setBackground(Color.black);
 		add(lblOutput);
 		setComponentOrientation(
		ComponentOrientation.RIGHT_TO_LEFT);	 	
	}

	private void setOutputPanelLayout()
 	{
 		this.setLayout(new FlowLayout());
 			 		
 	}
}
