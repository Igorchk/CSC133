
package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class PauseCommand extends Command implements ActionListener{
	
	private GameWorld gw;
	private Game game;
	
	public PauseCommand(GameWorld gw, Game game) {
		super("Pause");
		this.gw = gw;
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		game.toggleTime();
	}
}