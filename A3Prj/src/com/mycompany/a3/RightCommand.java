
package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class RightCommand extends Command implements ActionListener{
	
	private GameWorld gw;
	
	public RightCommand(GameWorld gw) {
		super("Right");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(!gw.isPaused()) {
			gw.turnRight();
		}
	}
}