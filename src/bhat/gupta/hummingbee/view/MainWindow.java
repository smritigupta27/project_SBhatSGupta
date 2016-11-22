package bhat.gupta.hummingbee.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class MainWindow {

	private JFrame frame;
	private JTextField startTimeTextField;
	private JTextField stopTimeTextField;
	private JTextField minTempTextField;
	private JTextField maxTempTextField;
	private JTextField waterFlowTextField;

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
		
		JPanel titlePanel = new JPanel();
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JLabel lblHummingBee = new JLabel("Humming Bee");
		lblHummingBee.setFont(new Font("Baghdad", Font.BOLD, 14));
		titlePanel.add(lblHummingBee);
		
		JPanel onOffButtonPanel = new JPanel();
		frame.getContentPane().add(onOffButtonPanel, BorderLayout.SOUTH);
		
		JButton onOffButton = new JButton("ON");
		onOffButton.setFont(new Font("Baghdad", Font.PLAIN, 13));
		onOffButtonPanel.add(onOffButton);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		JPanel blankPanel = new JPanel();
		blankPanel.setBackground(Color.BLACK);
		mainPanel.add(blankPanel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainPanel.add(tabbedPane);
		
		JPanel viewGardenPanel = new JPanel();
		tabbedPane.addTab("View Garden", null, viewGardenPanel, null);
		tabbedPane.setEnabledAt(0, true);
		
		JPanel programSprinklerPanel = new JPanel();
		tabbedPane.addTab("Program", null, programSprinklerPanel, null);
		programSprinklerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel zoneSelectionPanel = new JPanel();
		programSprinklerPanel.add(zoneSelectionPanel, BorderLayout.NORTH);
		
		JComboBox zoneSelectionComboBox = new JComboBox();
		zoneSelectionComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "North Zone", "South Zone", "East Zone", "West Zone"}));
		zoneSelectionPanel.add(zoneSelectionComboBox);
		
		JComboBox sprinklerSelectionComboBox = new JComboBox();
		sprinklerSelectionComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "sprinklerZN1", "sprinklerZN2", "sprinklerZN3", "sprinklerZN4", "sprinklerZN5"}));
		zoneSelectionPanel.add(sprinklerSelectionComboBox);
		
		JPanel programInputPanel = new JPanel();
		programSprinklerPanel.add(programInputPanel, BorderLayout.CENTER);
		programInputPanel.setLayout(new GridLayout(3, 4, 0, 0));
		
		JLabel startTimeLabel = new JLabel("Start Time");
		programInputPanel.add(startTimeLabel);
		
		startTimeTextField = new JTextField();
		startTimeTextField.setToolTipText("Enter the Start Time for the Sprinkler(s)");
		startTimeTextField.setText("hrs/min");
		programInputPanel.add(startTimeTextField);
		startTimeTextField.setColumns(10);
		
		JLabel stopTimeLabel = new JLabel("Stop Time");
		programInputPanel.add(stopTimeLabel);
		
		stopTimeTextField = new JTextField();
		stopTimeTextField.setToolTipText("Enter the Stop Time for the Sprinkler(s)");
		stopTimeTextField.setText("hrs/min");
		programInputPanel.add(stopTimeTextField);
		stopTimeTextField.setColumns(10);
		
		JLabel minTempLabel = new JLabel("Min Temperature");
		programInputPanel.add(minTempLabel);
		
		minTempTextField = new JTextField();
		minTempTextField.setToolTipText("Enter the Minimum temperature in degree fahrenheit");
		minTempTextField.setText("number");
		programInputPanel.add(minTempTextField);
		minTempTextField.setColumns(10);
		
		JLabel maxTempLabel = new JLabel("Max Temperature");
		programInputPanel.add(maxTempLabel);
		
		maxTempTextField = new JTextField();
		maxTempTextField.setToolTipText("Enter the maximum temperature in degree fahrenheit");
		maxTempTextField.setText("degrees");
		programInputPanel.add(maxTempTextField);
		maxTempTextField.setColumns(10);
		
		JLabel waterFlowLabel = new JLabel("Water Flow");
		programInputPanel.add(waterFlowLabel);
		
		waterFlowTextField = new JTextField();
		waterFlowTextField.setToolTipText("Enter the water flow in terms of liters per hour");
		waterFlowTextField.setText("Liters/hr");
		programInputPanel.add(waterFlowTextField);
		waterFlowTextField.setColumns(10);
		
		JLabel durationLabel = new JLabel("Duration");
		programInputPanel.add(durationLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Week", "Month", "Year"}));
		programInputPanel.add(comboBox);
		
		JPanel viewStatusPanel = new JPanel();
		viewStatusPanel.setToolTipText("View the Status of the Sprinklers");
		tabbedPane.addTab("Status", null, viewStatusPanel, null);
		
		JPanel checkWaterConsumptionPanel = new JPanel();
		checkWaterConsumptionPanel.setToolTipText("View Water Consumption data");
		tabbedPane.addTab("Water Report", null, checkWaterConsumptionPanel, null);
		
	}

}
