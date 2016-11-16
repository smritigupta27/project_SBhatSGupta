package bhat.gupta.hummingbee;

import java.awt.*;

import javax.swing.*;
public class GardenSprinklerFrame extends JFrame{
	private JPanel mainPanel,leftPanel, rightPanel;
	private JPanel inputPanel,outputPanel,gardenPanel,filterPanel;
	private Container contentPane;
	public GardenSprinklerFrame(){
		setTitle("Garden Sprinkler System");
		contentPane=getContentPane();
		mainPanel=new JPanel();
		
		mainPanel.setLayout(new GridLayout(1,0));
		leftPanel=new JPanel();
		
//		JTabbedPane tabbedPane = new JTabbedPane();
//    	tabbedPane.addTab("Check Sprinkler status", new GardenPanel());
//    	tabbedPane.addTab("Check water usage", new OutputPanel());
//    	tabbedPane.addTab("Program sprinklers", new GardenPanel());
//    	tabbedPane.addTab("Control Temperature", new OutputPanel());
    	
		//leftPanel.setLayout(new GridLayout(2,0));
		leftPanel.setLayout(new BorderLayout());
		inputPanel=new InputPanel();
		filterPanel=new FilterPanel();
		Dimension df = leftPanel.getSize();
		inputPanel.setSize(new Dimension((int)df.getWidth(),(int)(df.getHeight()*0.2)));
		filterPanel.setSize(new Dimension((int)df.getWidth(),(int)(df.getHeight()*0.8)));
		leftPanel.add(inputPanel,BorderLayout.NORTH);
		leftPanel.add(filterPanel,BorderLayout.CENTER);
		//leftPanel.add(tabbedPane);
		
		rightPanel=new JPanel();
		rightPanel.setLayout(new GridLayout(0,1));
		outputPanel=new OutputPanel();
		gardenPanel=new GardenPanel();
		rightPanel.add(gardenPanel);
		rightPanel.add(outputPanel);
		
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		contentPane.add(mainPanel,BorderLayout.CENTER);
		
		Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/3 - getWidth()/3,size.height/3 - getHeight()/3);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	int height = screenSize.height;
    	int width = screenSize.width;
    	System.out.println(width);
    	setSize(width/2, height/2);
    	
    	//pack();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
