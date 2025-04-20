package com.mycompany.t1;


import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.charts.util.ColorUtil;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;

public class Game extends Form{

public Game() {
	this.show();
}

public void paint(Graphics g) {
	super.paint(g);
	
	g.setColor(ColorUtil.BLACK);
    g.fillRect(250, 250, 100, 50);
	}

}