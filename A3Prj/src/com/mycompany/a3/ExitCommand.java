
package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


public class ExitCommand extends Command implements ActionListener{
		
	public ExitCommand() {
		super("Exit");
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		boolean confirm = Dialog.show("Confirm Exit", "Are you sure you want to exit?", "Yes", "No");
		
		if(confirm) {
			Display.getInstance().exitApplication();
		}
	}
}