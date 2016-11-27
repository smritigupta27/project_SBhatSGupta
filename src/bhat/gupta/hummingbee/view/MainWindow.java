package bhat.gupta.hummingbee.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bhat.gupta.hummingbee.controller.GardenController;
import bhat.gupta.hummingbee.controller.GardenController.ZoneId;
import bhat.gupta.hummingbee.model.Garden;

public class MainWindow {

	private JFrame frame;
	private JPanel titlePanel, onOffButtonPanel, mainPanel, blankPanel, viewGardenPanel, programInputPanel,
			viewStatusPanel, zoneSelectionPanel, programSprinklerPanel, checkWaterConsumptionPanel;
	private JTabbedPane tabbedPane;
	JPanel submitButtonPanel, timeConfigPanel, temperatureConfigPanel, waterConfigPanel, statusZonePanel,
			statusDisplayPanel;
	private JTextField startTimeTextField, stopTimeTextField, minTempTextField, maxTempTextField, waterFlowTextField;
	private JTextArea statusTextArea;
	private JButton onOffButton, submitButton;
	JLabel durationLabel, waterFlowLabel, maxTempLabel, minTempLabel, stopTimeLabel, startTimeLabel, zoneLabel,
			dayLabel;
	GardenController gardenController;
	JComboBox timeComboBox, zoneComboBox, sprinklerComboBox, dayComboBox, statusZoneComboBox, waterRateComboBox;

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Garden garden = new Garden("Humming Bee Garden");
		gardenController = new GardenController(garden);

		createTitlePanel();
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		createOnOffButtonPanel();

		frame.getContentPane().add(onOffButtonPanel, BorderLayout.SOUTH);
		createMainPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("Tab: " + tabbedPane.getSelectedIndex() + "has been selected");
			}
		});

		Toolkit toolkit = frame.getToolkit();
		Dimension size = toolkit.getScreenSize();
		frame.setLocation(size.width / 3 - frame.getWidth() / 3, size.height / 3 - frame.getHeight() / 3);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		System.out.println(width);
		frame.setSize(width / 2, height / 2);
	}

	public void createTitlePanel() {
		titlePanel = new JPanel();
		JLabel lblHummingBee = new JLabel("Humming Bee");
		lblHummingBee.setFont(new Font("Baghdad", Font.BOLD, 14));
		titlePanel.add(lblHummingBee);
	}

	public void createOnOffButtonPanel() {
		onOffButtonPanel = new JPanel();
		onOffButton = new JButton("ON");
		onOffButton.setFont(new Font("Baghdad", Font.PLAIN, 13));
		onOffButtonPanel.add(onOffButton);
		onOffButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cd = (CardLayout) mainPanel.getLayout();
				if (e.getActionCommand().equalsIgnoreCase("On")) {
					cd.show(mainPanel, "tabbedPane");
					onOffButton.setText("OFF");
				} else {
					cd.show(mainPanel, "blankPanel");
					onOffButton.setText("On");
				}
			}
		});
	}

	public void createWaterConsumptionPanel() {
		checkWaterConsumptionPanel = new JPanel();
		checkWaterConsumptionPanel.setToolTipText("View Water Consumption data");
	}

	public void createViewStatusPanel() {
		viewStatusPanel = new JPanel();
		viewStatusPanel.setLayout(new BorderLayout());
		createZoneSelectionPanelForStatus();
		viewStatusPanel.add(statusZonePanel, BorderLayout.NORTH);
		viewStatusPanel.setToolTipText("View the Status of the Sprinklers");
		createStatusDisplayPanel();
		viewStatusPanel.add(statusDisplayPanel, BorderLayout.CENTER);
	}

	public void createMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setLayout(new CardLayout(0, 0));
		blankPanel = new JPanel();
		blankPanel.setBackground(Color.BLACK);
	    mainPanel.add(blankPanel, "blankPanel");

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainPanel.add(tabbedPane, "tabbedPane");
		Map<ZoneId,ArrayList<Boolean>> sprinklerConditionMap = (HashMap<ZoneId, ArrayList<Boolean>>) gardenController.getWorkingSprinklerListForEachZone();
		viewGardenPanel = new ViewGardenPanel((HashMap<ZoneId, ArrayList<Boolean>>) sprinklerConditionMap);

		createProgramSprinklerPanel();

		createViewStatusPanel();
		createWaterConsumptionPanel();
		tabbedPane.addTab("View Garden", null, viewGardenPanel, null);
		tabbedPane.addTab("Status", null, viewStatusPanel, null);
		tabbedPane.addTab("Program", null, programSprinklerPanel, null);
		tabbedPane.addTab("Water Report", null, checkWaterConsumptionPanel, null);
		tabbedPane.setEnabledAt(0, true);
		/*tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				 JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
			     int selectedIndex = tabbedPane.getSelectedIndex();
			     if(selectedIndex == 0){
			    	 System.out.println("its changed");
			    	 viewGardenPanel = new ViewGardenPanel();
			     }
				
			}
		});*/
	}

	public void createProgramSprinklerPanel() {
		programSprinklerPanel = new JPanel();
		// programSprinklerPanel.setLayout(new BorderLayout(0, 0));
		programSprinklerPanel.setLayout(new BorderLayout());
		createZoneSelectionPanel();
		programSprinklerPanel.add(zoneSelectionPanel, BorderLayout.NORTH);
		createProgramInputPanel();
		programSprinklerPanel.add(programInputPanel, BorderLayout.CENTER);
		createSubmitButtonPanel();
		programSprinklerPanel.add(submitButtonPanel, BorderLayout.SOUTH);
	}

	public void createZoneSelectionPanel() {
		zoneSelectionPanel = new JPanel();
		// zoneSelectionPanel.setLayout(new FlowLayout());
		zoneLabel = new JLabel("Zone: ");
		String[] zones = gardenController.getGarden().getZoneNames();
		zoneComboBox = new JComboBox<String>(zones);
		// zoneComboBox.setModel(new
		// DefaultComboBoxModel(gardenController.getGarden().getZoneNames()));
		zoneSelectionPanel.add(zoneLabel);
		zoneSelectionPanel.add(zoneComboBox);
		// sprinklerSelectionComboBox = new JComboBox();
		// sprinklerSelectionComboBox.setModel(new DefaultComboBoxModel(new
		// String[] {"", "sprinklerZN1", "sprinklerZN2", "sprinklerZN3",
		// "sprinklerZN4", "sprinklerZN5"}));
		// zoneSelectionPanel.add(sprinklerSelectionComboBox);
	}

	public void createProgramInputPanel() {
		programInputPanel = new JPanel();
		// programInputPanel.setLayout(new GridLayout(3, 4, 0, 0));
		programInputPanel.setLayout(new GridLayout(3, 1));

		createTimeConfigPanel();
		programInputPanel.add(timeConfigPanel);
		createTemperatureConfigPanel();
		programInputPanel.add(temperatureConfigPanel);

		createWaterConfigPanel();
		programInputPanel.add(waterConfigPanel);
	}

	public void createSubmitButtonPanel() {
		submitButtonPanel = new JPanel();
		submitButton = new JButton("Submit");
		submitButtonPanel.add(submitButton);
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setProgramDataForZone();
				programSprinklerPanel.remove(programInputPanel);
				createProgramInputPanel();
				programSprinklerPanel.add(programInputPanel, BorderLayout.CENTER);
			}
		});
	}

	public void createTimeConfigPanel() {
		timeConfigPanel = new JPanel();
		timeConfigPanel.setLayout(new GridLayout(2, 4));
		timeConfigPanel.setBorder(new TitledBorder("Time"));
		durationLabel = new JLabel("Duration: ");
		timeConfigPanel.add(durationLabel);

		timeComboBox = new JComboBox(new String[] { "Week", "Day" });
		// timeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Week",
		// "Day"}));
		timeConfigPanel.add(timeComboBox);

		dayLabel = new JLabel("Day: ");
		// dayComboBox = new JComboBox();

		// Calendar calendar = Calendar.getInstance();
		// for (int i = 0; i < 7; ++i) {
		// Date date=calendar.getTime();
		// dayComboBox.addItem(new MyDate(date));
		// calendar.add(Calendar.DATE, 1);
		// }

		// Calendar calendar = Calendar.getInstance();
		// calendar.set(Calendar.HOUR_OF_DAY, 0);
		// calendar.set(Calendar.MINUTE, 0);
		//
		// Calendar end = Calendar.getInstance();
		// end.set(Calendar.HOUR_OF_DAY, 23);
		// end.set(Calendar.MINUTE, 59);
		// calendar.add(Calendar.MINUTE, 15);
		// //dayComboBox.addItem(new MyDate(end.getTime()));
		//// DefaultComboBoxModel<Date> model = new DefaultComboBoxModel<>();
		//// do {
		//// model.addElement(calendar.getTime());
		//// calendar.add(Calendar.MINUTE, 15);
		//// } while (calendar.getTime().before(end.getTime()));
		////
		//// JComboBox<Date> cb = new JComboBox<>(model);
		//// cb.setRenderer(new DateFormattedListCellRenderer(new
		// SimpleDateFormat("HH:mm")));
		////
		//// add(cb);
		String[] days = new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		dayComboBox = new JComboBox(days);
		timeConfigPanel.add(dayLabel);
		timeConfigPanel.add(dayComboBox);
		dayLabel.setVisible(false);
		dayComboBox.setVisible(false);

		startTimeLabel = new JLabel("Start Time:");
		timeConfigPanel.add(startTimeLabel);
		startTimeTextField = new JTextField();
		startTimeTextField.setToolTipText("Enter the Start Time for the Sprinkler(s)");
		startTimeTextField.setText("hrs:min");
		startTimeTextField.setColumns(10);
		timeConfigPanel.add(startTimeTextField);

		stopTimeLabel = new JLabel("Stop Time:");
		timeConfigPanel.add(stopTimeLabel);

		stopTimeTextField = new JTextField();
		stopTimeTextField.setToolTipText("Enter the Stop Time for the Sprinkler(s)");
		stopTimeTextField.setText("hrs:min");
		stopTimeTextField.setColumns(10);
		timeConfigPanel.add(stopTimeTextField);

		timeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String time = (String) cb.getSelectedItem();
				if (time == "Day") {
					dayLabel.setVisible(true);
					dayComboBox.setVisible(true);
				} else {
					dayLabel.setVisible(false);
					dayComboBox.setVisible(false);

				}
			}
		});

	}

	public void createTemperatureConfigPanel() {
		temperatureConfigPanel = new JPanel();
		temperatureConfigPanel.setBorder(new TitledBorder("Temperature"));
		minTempLabel = new JLabel("Min Temperature (°F): ");
		minTempLabel.setHorizontalAlignment(SwingConstants.CENTER);
		temperatureConfigPanel.add(minTempLabel);

		minTempTextField = new JTextField("");
		minTempTextField.setHorizontalAlignment(SwingConstants.CENTER);
		minTempTextField.setToolTipText("Enter the Minimum temperature in degree fahrenheit");
		temperatureConfigPanel.add(minTempTextField);
		minTempTextField.setColumns(10);

		maxTempLabel = new JLabel("    Max Temperature (°F): ");
		temperatureConfigPanel.add(maxTempLabel);

		maxTempTextField = new JTextField("");
		maxTempTextField.setToolTipText("Enter the maximum temperature in degree fahrenheit");
		temperatureConfigPanel.add(maxTempTextField);
		maxTempTextField.setColumns(10);
	}

	public void createWaterConfigPanel() {
		waterConfigPanel = new JPanel();
		waterConfigPanel.setBorder(new TitledBorder("Water"));
		waterFlowLabel = new JLabel("Water Flow (litres/hour): ");
		waterConfigPanel.add(waterFlowLabel);
		waterFlowTextField = new JTextField();

		String[] waterRateArray = new String[] { "10", "20", "30", "40", "50" };
		waterRateComboBox = new JComboBox(waterRateArray);
		waterConfigPanel.add(waterRateComboBox);

		waterRateComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String waterRate = (String) cb.getSelectedItem();
				gardenController.setWaterRate(waterRate);

			}
		});
		/*
		 * waterFlowTextField.setToolTipText(
		 * "Enter the water flow in terms of liters per hour");
		 * //waterFlowUnitLabel=new JLabel("liters/hr");
		 * //waterFlowTextField.setText("Liters/hr");
		 * waterConfigPanel.add(waterFlowTextField);
		 * //waterConfigPanel.add(waterFlowUnitLabel);
		 * waterFlowTextField.setColumns(10);
		 */
	}

	public void createZoneSelectionPanelForStatus() {
		statusZonePanel = new JPanel();
		zoneLabel = new JLabel("Zone: ");
		String[] zones = gardenController.getGarden().getZoneNames();
		// statusZoneComboBox=new JComboBox();

		statusZoneComboBox = new JComboBox(zones);
		statusZoneComboBox.setSelectedIndex(-1);
		statusZonePanel.add(zoneLabel);
		statusZonePanel.add(statusZoneComboBox);
		statusZoneComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String zoneId = (String) cb.getSelectedItem();
				statusTextArea.setText(gardenController.getZoneStatus(zoneId));

			}
		});

	}

	public void createStatusDisplayPanel() {
		statusDisplayPanel = new JPanel();
		statusDisplayPanel.setBorder(new TitledBorder("Status"));
		statusDisplayPanel.setLayout(new GridLayout());
		statusTextArea = new JTextArea();
		statusTextArea.setEditable(false);
		statusDisplayPanel.add(statusTextArea);
	}

	public void setProgramDataForZone() {

		String zoneIdString = (String) zoneComboBox.getSelectedItem();
		gardenController.setZoneForProgramming(zoneIdString);

		DateFormat sdf = new SimpleDateFormat("hh:mm");
		String startTime = startTimeTextField.getText();// "15:30:18";
		String stopTime = stopTimeTextField.getText();
		/*Date beginTime = new Date();
		Date endTime = new Date();
		try {
			beginTime = startTime != null ?sdf.parse(startTime) : sdf.parse("10:00");
			endTime = endTime != null ? sdf.parse(stopTime) : sdf.parse("10:00");
		} catch (ParseException e1) {
			System.out.println("You have enters Incorrect Time");
			e1.printStackTrace();
		}*/
		
		gardenController.setStartTime(startTime);
		gardenController.setStopTime(stopTime);
		
		String minTemp = minTempTextField.getText();
		String maxTemp = maxTempTextField.getText();
		if (minTemp.length() < 3 && minTemp.matches("[0-9]+")) {
			gardenController.setMinTemp(minTemp);
		}
		if (maxTemp.length() < 3 && maxTemp.matches("[0-9]+")) {
			gardenController.setMaxTemp(maxTemp);
		}
		
		String wterRate = (String) waterRateComboBox.getSelectedItem();
		gardenController.setWaterRate(wterRate);

		gardenController.saveProgramDataForZone();
	}
}

// class MyDate
// {
// private Date date;
//
// MyDate(Date date) {
// this.date = date;
// }ø
//
// public String toString() {
// SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
// String dateString = dateFormat.format(this.date);
// return dateString;
// }
//
//
// }
