package bhat.gupta.hummingbee;
import java.awt.*;

import javax.swing.*;
public class FilterPanel extends JPanel{
	JLabel lblFilter;
	public FilterPanel() {
    	setFilterPanelLayout();
		setBackground(Color.LIGHT_GRAY);
		lblFilter=new JLabel("This is the filter panel");
		lblFilter.setFont(new Font("Serif", Font.PLAIN, 20));
		lblFilter.setForeground(Color.BLACK);
		add(lblFilter);
	}

	private void setFilterPanelLayout()
 	{
 		this.setLayout(new GridLayout(4,0));	 		
 	}

}
