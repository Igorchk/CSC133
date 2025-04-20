
package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class BrakeCommand extends Command implements ActionListener{
	
	private GameWorld gw;
	
	public BrakeCommand(GameWorld gw) {
		super("Brake");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(!gw.isPaused()) {
			gw.brake();
		}
	}
}