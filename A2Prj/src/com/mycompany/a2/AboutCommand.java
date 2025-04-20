
package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.charts.util.ColorUtil;


public class AboutCommand extends Command implements ActionListener{
		
	public AboutCommand() {
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		
		String text = "About:\n"
                + "Name: Igor Romantsov\n"
                + "Class: Object-Oriented Computer Graphics Programming\n";
		
		Dialog.show("Help", text,"Ok", null);
		
	}
}