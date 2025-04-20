
package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class ConsumptionCommand extends Command implements ActionListener{
	
	private GameWorld gw;
	
	public ConsumptionCommand(GameWorld gw) {
		super("Consumption");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.consumptionRate();
	}
}