
package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.charts.util.ColorUtil;


public class HelpCommand extends Command implements ActionListener{
		
	public HelpCommand() {
		super("Help");
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		String text = "Key Bindings:\n"
                + "a - Accelerate \n"
                + "b - Brake \n"
                + "l - Turn Left \n"
                + "r - Turn Right \n"
                + "f - Collide with Flag \n"
                + "g - Collide with Spider \n"
                + "t - Game Tick";
		
		Dialog.show("Help", text,"Ok", null);
		
	}
}