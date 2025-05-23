
package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.charts.util.ColorUtil;


public class TickCommand extends Command implements ActionListener{
	
	private GameWorld gw;
	
	public TickCommand(GameWorld gw) {
		super("Tick");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.tickedClock();
	}
}