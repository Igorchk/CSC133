
package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class StationCommand extends Command implements ActionListener{
	
	private GameWorld gw;
	
	public StationCommand(GameWorld gw) {
		super("Collide with Food Stations");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.foodStationCollision();
	}
}