/**
 * 
 */
package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.Beans;
import model.Containers;
import model.Farmer;
import model.Fox;
import model.Goose;
import model.Status;

/**
 * @author mashichao
 *
 */
public class MyFrame extends JFrame implements Observer{
	//initialise the panels
	private GrassPanel rightGrass;
	private GrassPanel leftGrass;
	private WaterPanel aWaterPanel;
	private BoatPanel aBoatPanel;
	private JPanel occupier;
	//initialise the pics of items
	private JLabel JLOfTheFox;
	private JLabel JLOfTheGoose;
	private JLabel JLOfTheBean;
	private JLabel JLOfTheFarmer;
	//initialise the container(used in virtulizing panels) and the status
	private Containers aContainer;
	private Status aStatus;
	//initialise the buttons
	private JButton boatLeft;
	private JButton boatRight;
	private JButton foxLeft;
	private JButton foxRight;
	private JButton gooseLeft;
	private JButton gooseRight;
	private JButton beansLeft;
	private JButton beansRight;
	private JButton farmerLeft;
	private JButton farmerRight;
	//constructor
	public MyFrame(Fox aFox,
			Goose aGoose,
			Beans aBean,
			Farmer aFarmer,
			Containers aContainer,
			Controller aController,
			Status aStatus) {
		
		this.aContainer = aContainer;
		this.aStatus = aStatus;
		/**
		 * instantiate some basic widgets
		 */
		JLabel boatLabel = new JLabel("Boat: ");
		JLabel foxLabel = new JLabel("Fox: ");
		JLabel gooseLabel = new JLabel("Goose: ");
		JLabel beansLabel = new JLabel("Beans: ");
		JLabel farmerLabel = new JLabel("Farmer: ");	
		/**
		 * instantiate JButtons
		 */
		boatLeft = new JButton("<");
		boatLeft.setActionCommand("JBboatLeft");
		boatRight = new JButton(">");
		boatRight.setActionCommand("JBboatRight");
		foxLeft = new JButton("<");
		foxLeft.setActionCommand("JBfoxLeft");
		foxRight = new JButton(">");
		foxRight.setActionCommand("JBfoxRight");
		gooseLeft = new JButton("<");
		gooseLeft.setActionCommand("JBgooseLeft");	
		gooseRight = new JButton(">");
		gooseRight.setActionCommand("JBgooseRight");	
		beansLeft = new JButton("<");
		beansLeft.setActionCommand("JBbeansLeft");
		beansRight = new JButton(">");
		beansRight.setActionCommand("JBbeansRight");
		farmerLeft = new JButton("<");
		farmerLeft.setActionCommand("JBfarmerLeft");
		farmerRight = new JButton(">");
		farmerRight.setActionCommand("JBfarmerRight");
		/**
		 * Add ActionListener to above JButtons
		 */
		boatLeft.addActionListener(aController);
		boatRight.addActionListener(aController);
		foxLeft.addActionListener(aController);
		foxRight.addActionListener(aController);
		gooseLeft.addActionListener(aController);
		gooseRight.addActionListener(aController);
		beansLeft.addActionListener(aController);
		beansRight.addActionListener(aController);
		farmerLeft.addActionListener(aController);
		farmerRight.addActionListener(aController);
		
		/**
		 * The FlowLayout(for putting the basic labels and buttons) inside the southern part of BorderLayout.
		 */
		JPanel flowPanel = new JPanel(new FlowLayout());
		flowPanel.add(boatLabel);
		flowPanel.add(boatLeft);
		flowPanel.add(boatRight);
		flowPanel.add(foxLabel);
		flowPanel.add(foxLeft);
		flowPanel.add(foxRight);
		flowPanel.add(gooseLabel);
		flowPanel.add(gooseLeft);
		flowPanel.add(gooseRight);
		flowPanel.add(beansLabel);
		flowPanel.add(beansLeft);
		flowPanel.add(beansRight);
		flowPanel.add(farmerLabel);
		flowPanel.add(farmerLeft);
		flowPanel.add(farmerRight);
		/**
		 * instantiate two grassPanel
		 */
		rightGrass = new GrassPanel();
		rightGrass.setLayout(new GridLayout(4, 1));
		leftGrass = new GrassPanel();
		leftGrass.setLayout(new GridLayout(4, 1));
		/**
		 * instantiate the pictures(JLabel) of items
		 */
		ImageIcon imageIconOfTheFox = new ImageIcon(aFox.getThePicPath());
		JLOfTheFox = new JLabel(imageIconOfTheFox);
		ImageIcon imageIconOfTheGoose = new ImageIcon(aGoose.getThePicPath());
		JLOfTheGoose = new JLabel(imageIconOfTheGoose);
		ImageIcon imageIconOfTheBean = new ImageIcon(aBean.getThePicPath());
		JLOfTheBean = new JLabel(imageIconOfTheBean);
		ImageIcon imageIconOfTheFarmer = new ImageIcon(aFarmer.getThePicPath());
		JLOfTheFarmer = new JLabel(imageIconOfTheFarmer);
		
		/**
		 * instantiate the waterPanel and set the layout
		 */
		aWaterPanel = new WaterPanel();
		aWaterPanel.setLayout(new GridLayout(1, 2));
		/**
		 * instantiate the boatPanel and set the layout
		 */
		aBoatPanel = new BoatPanel();
		aBoatPanel.setLayout(new GridLayout(1, 2));
		aBoatPanel.setOpaque(false);
		occupier = new JPanel();
		occupier.setOpaque(false);
		
		/**
		 * Initialise the default position of items
		 */
		this.initializeTheRightGrass();
		this.initializeTheBoat();
		this.initializeTheLeftGrass();
		this.initializeTheRiver();
		this.initializeTheTitle();
		
		/**
		 * Put the waterPanel and the grassPanel on the JWindow(JFrame, BorderLayout)
		 */
		setLayout(new BorderLayout());
		add(leftGrass, BorderLayout.WEST);
		add(aWaterPanel, BorderLayout.CENTER);
		add(rightGrass, BorderLayout.EAST);
		add(flowPanel, BorderLayout.SOUTH);
		/**
		 * pack and setVisible
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.initializeTheRightGrass();
		this.initializeTheBoat();
		this.initializeTheLeftGrass();
		this.initializeTheRiver();
		this.initializeTheTitle();
		this.disableTheButtons();
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * some methods used to virtualize the models  
	 */
	public void initializeTheRightGrass(){
		if(aContainer.foxInRGC())rightGrass.add(JLOfTheFox);
		if(aContainer.gooseInRGC())rightGrass.add(JLOfTheGoose);
		if(aContainer.beanInRGC())rightGrass.add(JLOfTheBean);
		if(aContainer.farmerInRGC())rightGrass.add(JLOfTheFarmer);
	}
	public void initializeTheBoat(){
		if(aContainer.foxInBC())aBoatPanel.add(JLOfTheFox);
		if(aContainer.gooseInBC())aBoatPanel.add(JLOfTheGoose);
		if(aContainer.beanInBC())aBoatPanel.add(JLOfTheBean);
		if(aContainer.farmerInBC())aBoatPanel.add(JLOfTheFarmer);
	}
	public void initializeTheLeftGrass(){
		if(aContainer.foxInLGC())leftGrass.add(JLOfTheFox);
		if(aContainer.gooseInLGC())leftGrass.add(JLOfTheGoose);
		if(aContainer.beanInLGC())leftGrass.add(JLOfTheBean);
		if(aContainer.farmerInLGC())leftGrass.add(JLOfTheFarmer);
	}
	public void initializeTheRiver(){
		if(aContainer.boatInRightRiver()){
			aWaterPanel.add(occupier);
			aWaterPanel.add(aBoatPanel);
		}else{
			aWaterPanel.add(aBoatPanel);
			aWaterPanel.add(occupier);
		}
	}
	public void initializeTheTitle(){
		this.setTitle(aStatus.getStatus());
	}	
	public void disableTheButtons(){
		if(aStatus.getEnd()){
			boatLeft.setEnabled(false);
			boatRight.setEnabled(false);
			foxLeft.setEnabled(false);
			foxRight.setEnabled(false);
			gooseLeft.setEnabled(false);
			gooseRight.setEnabled(false);
			beansLeft.setEnabled(false);
			beansRight.setEnabled(false);
			farmerLeft.setEnabled(false);
			farmerRight.setEnabled(false);
		}
	}
}
