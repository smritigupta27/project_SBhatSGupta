package bhat.gupta.hummingbee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel titleLabelHummingBee = new JLabel("Humming Bee");
		titleLabelHummingBee.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(titleLabelHummingBee, BorderLayout.NORTH);
		
		JButton onOffButton = new JButton("ON");
		frame.getContentPane().add(onOffButton, BorderLayout.SOUTH);
		
		JPanel mainFrameCentralPanel = new JPanel();
		frame.getContentPane().add(mainFrameCentralPanel, BorderLayout.CENTER);
		mainFrameCentralPanel.setLayout(new CardLayout(0, 0));
		
		/*mainFrameCentralPanel has a cardlayout and contains other panels like the tabbed panel card 
		 * for tasks , a welcome card (can remove later if not needed), and blank card
		 * to simulate the turning off of the system.
		 */
		
		JPanel welcomeCardPanel = new JPanel();
		welcomeCardPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		mainFrameCentralPanel.add(welcomeCardPanel, "name_65743012474573");
		
		String welcomeText = "<html><center><h2>Welcome to </h2> <br>"
				+ "<h1> Humming Bee's </h1><br><h2>"
				+ " Garden Sprinkler System</h2></center><html>";
		JLabel welcomeLabel = new JLabel(welcomeText);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		welcomeCardPanel.add(welcomeLabel);
		
		/* tabbed task panel contains various tasks panel like the
		 * view garden panel, check water consumption data, 
		 * view status of sprinklers and program sprinklers panel
		 */
		
		JTabbedPane taskCardPanel = new JTabbedPane(JTabbedPane.TOP);
		mainFrameCentralPanel.add(taskCardPanel, "name_65167717149205");
		
		JPanel viewGardenTabPanel = new JPanel();
		taskCardPanel.addTab("View Garden", null, viewGardenTabPanel, null);
		
		JPanel programSprinklerTabPanel = new JPanel();
		taskCardPanel.addTab("Program", null, programSprinklerTabPanel, null);
		
		JPanel viewWaterConsumptionTabPanel = new JPanel();
		taskCardPanel.addTab("View Report", null, viewWaterConsumptionTabPanel, null);
		
		JPanel viewSprinklerStatusTabPanel = new JPanel();
		taskCardPanel.addTab("View Status", null, viewSprinklerStatusTabPanel, null);
		
		JPanel BlankCardPanel = new JPanel();
		BlankCardPanel.setBackground(Color.BLACK);
		mainFrameCentralPanel.add(BlankCardPanel, "name_67390271097499");
	}

}
