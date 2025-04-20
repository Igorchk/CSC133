
package com.mycompany.a2;


import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class SpiderCommand extends Command implements ActionListener{
	
	private GameWorld gw;
	
	public SpiderCommand(GameWorld gw) {
		super("Collide with Spider");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.spiderCollision();
	}
}