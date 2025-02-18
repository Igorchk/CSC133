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
	
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
}
	public boolean getExitCommand() {
		return exitCommand;
	}
	
	public void setExitCommand(boolean command) {
		this.exitCommand = command;
	}

	
private void play() {
	
	Label myLabel=new Label("Enter a Command:");
	this.addComponent(myLabel);
	final TextField myTextField = new TextField();
	this.addComponent(myTextField);
	this.show();
	
	myTextField.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent evt) {
			
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if(sCommand.length() != 0)
					
					
					
					switch (sCommand.charAt(0)) {
						
						case 'a':
							gw.accelerate();
							break;
						
						case 'b':
							gw.brake();
							break;
						
						case 'l':
							gw.turnLeft();
							break;
						
						case 'r':
							gw.turnRight();
							break;
						
						case 'c':
							gw.consumptionRate();
							break;
						
						case '1':
							gw.flagCollision(1);
							break;
							
						case '2':
							gw.flagCollision(2);
							break;
							
						case '3':
							gw.flagCollision(3);
							break;
							
						case '4':
							gw.flagCollision(4);
							break;
							
						case 'f':
							gw.foodStationCollision();
							break;
							
						case 'g':
							gw.spiderCollision();
							break;
					
						case 't':
							gw.tickedClock();
							break;
						
						case 'd':
							gw.showDisplay();
							break;
						
						case 'm':
							gw.showMap();
							break;
						
						case 'x':
							System.out.println("Please Enter Y or N to confirm.\n");
							setExitCommand(true);
							break;
						
						case 'y':
							if(getExitCommand()) {
								System.out.println("Game Closing.\n");
								System.exit(0);
							}else {
								break;
							}
						
						case 'n':
							setExitCommand(false);
							System.out.println("Game Continuing.\n");
							break;
						
						default:
							System.out.println("Please enter a valid input.\n");
						//add code to handle rest of the commands
					} //switch
				} //actionPerformed
			} //new ActionListener()
			); //addActionListener
	} //play
}