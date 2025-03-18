
package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.charts.util.ColorUtil;


public class FlagCommand extends Command implements ActionListener{
	
	private GameWorld gw;
	
	public FlagCommand(GameWorld gw) {
		super("Collide with Flag");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
	    int flagNum;
	    TextField userInput = new TextField(); 
	    Command cOk = new Command("Ok");
	    Command cCancel = new Command("Cancel");

	    Command c = Dialog.show("Which Flag Did Ant Collide With?", userInput, cOk, cCancel);

	    if (c == cOk) {
	        String inputText = userInput.getText(); 
	        if (!inputText.isEmpty() && isNumeric(inputText)) {
	            flagNum = Integer.parseInt(inputText);
	            if (flagNum >= 1 && flagNum <= 4) {
	                gw.flagCollision(flagNum);
	            } else {
	                Dialog.show("Error", "Enter a number between 1 and 4.", "OK", null);
	            }
	        } else {
	            Dialog.show("Error", "Invalid input, enter a number.", "OK", null);
	        }
	    }
	}

	public boolean isNumeric(String input) {
	    try {
	        Integer.parseInt(input);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
}