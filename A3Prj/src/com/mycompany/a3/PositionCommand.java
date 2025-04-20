
package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class PositionCommand extends Command implements ActionListener{

	private GameWorld gw;
	private MapView mv;
	private boolean isPaused = false;
	
	public PositionCommand(GameWorld gw, MapView mv) {
		super("Position");
		this.gw = gw;
		this.mv = mv;
	}
	
	public void toggleTime() {
		isPaused = !isPaused;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.setClick(true);
	}
}