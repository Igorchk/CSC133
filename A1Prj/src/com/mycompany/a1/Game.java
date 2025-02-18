package com.mycompany.a1;


import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;


public class Game extends Form{
	
	private GameWorld gw;
	
	private boolean exitCommand = false;
	
	
	//Runs method init() which starts new instance of GameWorld()
	public Game() {
		gw = new GameWorld();
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

	
private void play() {
	
	Label myLabel = new Label("Enter a Command:");//Display input prompt
	this.addComponent(myLabel);
	final TextField myTextField = new TextField();//Creates a textField object to enter commands
	this.addComponent(myTextField);
	this.show();
	
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
}