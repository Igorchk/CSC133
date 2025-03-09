package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;


public class MapView extends Container implements Observer{

	public MapView(Observable myModel) { 
		super();
		myModel.addObserver(this);
		
		this.getAllStyles().setBorder(Border.createLineBorder(5, 0xFF0000));	
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("MapView");
	}
	
}