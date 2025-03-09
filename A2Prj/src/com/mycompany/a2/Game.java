package com.mycompany.a2;


import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.util.Observer;
import java.util.Observable;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;


public class Game extends Form{
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	private boolean exitCommand = false;
	
	
	//Runs method init() which starts new instance of GameWorld()
	public Game() {
		gw = new GameWorld();
		mv = new MapView(gw);
		sv = new ScoreView(gw);
		
		this.setLayout(new BorderLayout());
		
		setupTitle();
		setupWestContainer();
		setupEastContainer();
		setupSouthContainer();
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.NORTH, sv);
		
		gw.addObserver(mv);
		gw.addObserver(sv);
				
		gw.init();
		play();
}

	//Getter for exitCommand
	public boolean getExitCommand() {
		return exitCommand;
	}
	
	//Setter for exitCommand
	public void setExitCommand(boolean command) {
		this.exitCommand = command;
	}

	public void setupWestContainer() {
		Container westContainer = new Container();
		Button accelButton = new Button("Accelerate");
		Button leftButton = new Button("Left");
		
		westContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		
		accelButton.getUnselectedStyle().setBgTransparency(255);
		accelButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		accelButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		accelButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		accelButton.getAllStyles().setPadding(Component.TOP, 4);
		accelButton.getAllStyles().setPadding(Component.BOTTOM, 4);
		accelButton.getAllStyles().setPadding(Component.LEFT, 2);
		accelButton.getAllStyles().setPadding(Component.RIGHT, 2);
		
		leftButton.getUnselectedStyle().setBgTransparency(255);
		leftButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		leftButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		leftButton.getAllStyles().setPadding(Component.TOP, 4);
		leftButton.getAllStyles().setPadding(Component.BOTTOM, 4);
		leftButton.getAllStyles().setPadding(Component.LEFT, 2);
		leftButton.getAllStyles().setPadding(Component.RIGHT, 2);
		
		westContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		westContainer.add(accelButton);
		westContainer.add(leftButton);
		westContainer.getAllStyles().setPadding(Component.TOP, 150);
		
		this.add(BorderLayout.WEST, westContainer);
	}
	
	public void setupEastContainer() {
		Container eastContainer = new Container();
		Button brakeButton = new Button("Brake");
		Button rightButton = new Button("Right");
		
		eastContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		
		brakeButton.getUnselectedStyle().setBgTransparency(255);
		brakeButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		brakeButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		brakeButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		brakeButton.getAllStyles().setPadding(Component.TOP, 4);
		brakeButton.getAllStyles().setPadding(Component.BOTTOM, 4);
		brakeButton.getAllStyles().setPadding(Component.LEFT, 2);
		brakeButton.getAllStyles().setPadding(Component.RIGHT, 2);
		
		rightButton.getUnselectedStyle().setBgTransparency(255);
		rightButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		rightButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		rightButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		rightButton.getAllStyles().setPadding(Component.TOP, 4);
		rightButton.getAllStyles().setPadding(Component.BOTTOM, 4);
		rightButton.getAllStyles().setPadding(Component.LEFT, 2);
		rightButton.getAllStyles().setPadding(Component.RIGHT, 2);
		
		eastContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		eastContainer.add(brakeButton);
		eastContainer.add(rightButton);
		eastContainer.getAllStyles().setPadding(Component.TOP, 150);
		
		this.add(BorderLayout.EAST, eastContainer);
	}
	
	public void setupSouthContainer() {
		Container southContainer = new Container(new FlowLayout(Component.CENTER));
		Button flagButton = new Button("Collide with Flag");
		Button spiderButton = new Button("Collide with Spider");
		Button foodButton = new Button("Collide with Food Station");
		Button tickButton = new Button("Tick");
		
		southContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		
		flagButton.getUnselectedStyle().setBgTransparency(255);
		flagButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		flagButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		flagButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		flagButton.getAllStyles().setPadding(Component.TOP, 5);
		flagButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		flagButton.getAllStyles().setPadding(Component.LEFT, 2);
		flagButton.getAllStyles().setPadding(Component.RIGHT, 2);
		
		spiderButton.getUnselectedStyle().setBgTransparency(255);
		spiderButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		spiderButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		spiderButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		spiderButton.getAllStyles().setPadding(Component.TOP, 5);
		spiderButton.getAllStyles().setPadding(Component.BOTTOM, 5);		
		spiderButton.getAllStyles().setPadding(Component.LEFT, 2);
		spiderButton.getAllStyles().setPadding(Component.RIGHT, 2);
		
		foodButton.getUnselectedStyle().setBgTransparency(255);
		foodButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		foodButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		foodButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		foodButton.getAllStyles().setPadding(Component.TOP, 5);
		foodButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		foodButton.getAllStyles().setPadding(Component.LEFT, 2);
		foodButton.getAllStyles().setPadding(Component.RIGHT, 2);
	
		tickButton.getUnselectedStyle().setBgTransparency(255);
		tickButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		tickButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		tickButton.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		tickButton.getAllStyles().setPadding(Component.TOP, 5);
		tickButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		tickButton.getAllStyles().setPadding(Component.LEFT, 2);
		tickButton.getAllStyles().setPadding(Component.RIGHT, 2);
		
		southContainer.add(flagButton);
		southContainer.add(spiderButton);
		southContainer.add(foodButton);
		southContainer.add(tickButton);
		
		this.add(BorderLayout.SOUTH, southContainer);

	}
	
	public void setupTitle() {

	}
	
private void play() {
	

	this.show();
/*	
	myTextField.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent evt) {
			
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if(sCommand.length() != 0)
					
					
					switch (sCommand.charAt(0)) {
					
						case 'a':
							gw.accelerate();//Tells ant to accelerate
							break;
						
						case 'b':
							gw.brake();//Tells ant to brake
							break;
						
						case 'l':
							gw.turnLeft();//Tells ant to turn left
							break;
						
						case 'r':
							gw.turnRight(); //Tells ant to turn right
							break;
						
						case 'c':
							gw.consumptionRate();//Sets ants consumption rate to a random value between 1-3
							break;
						
						case '1':
							gw.flagCollision(1);//Tells game ant collided with flag (1)
							break;
							
						case '2':
							gw.flagCollision(2);//Tells game ant collided with flag (2)
							break;
							
						case '3':
							gw.flagCollision(3);//Tells game ant collided with flag (3)
							break;
							
						case '4':
							gw.flagCollision(4);//Tells game ant collided with flag (4)
							break;
							
						case 'f':
							gw.foodStationCollision();//Tells game ant collided with a random FoodStation
							break;
							
						case 'g':
							gw.spiderCollision();//Tells game ant collided with a random spider
							break;
					
						case 't':
							gw.tickedClock();//Tells game clock has passed tick
							break;
						
						case 'd':
							gw.showDisplay();//Displays ants stats
							break;
						
						case 'm':
							gw.showMap();//Displays all the objects information
							break;
						
						case 'x'://Exits game
							System.out.println("Please Enter Y or N to confirm.\n");//Asks user for confirmation
							setExitCommand(true);//Sets the exitcommand to true
							break;
						
						case 'y':
							if(getExitCommand()) {//Checks if exitCommand is true, if so then exits game
								System.out.println("Game Closing.\n");
								System.exit(0);
							}else {
								break;
							}
						
						case 'n':
							setExitCommand(false);//Sets exitCommand to false and resumes game
							System.out.println("Game Continuing.\n");
							break;
						
						default:
							System.out.println("Please enter a valid input.\n");//Displays if user input is incorrect
						//add code to handle rest of the commands
					} //switch
				} //actionPerformed
			} //new ActionListener()
			); //addActionListener
	} //play
	*/
}
}