package com.mycompany.a1;


import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;


public class Game extends Form{
	private GameWorld gw;
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
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
							gw.exit();
							break;
						
						case 'y':
							gw.yes();
							break;
						
						case 'n':
							gw.no();
							break;
						
						default:
							System.out.println("Please enter a valid input");
						//add code to handle rest of the commands
					} //switch
		} //actionPerformed
		} //new ActionListener()
		); //addActionListener
		} //play
}