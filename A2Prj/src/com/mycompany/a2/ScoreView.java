package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;


public class ScoreView extends Container implements Observer{

	private Label timeLabel;
	private Label livesLabel;
	private Label lastFlagLabel;
	private Label foodLabel;
	private Label healthLabel;
	private Label soundLabel;
    private Label titleLabel;
	
	public ScoreView(Observable myModel) { 
		super();
		myModel.addObserver(this);

		setupContainer();
	}
	
	public void setupContainer() {
		this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		Container northContainer = new Container(new FlowLayout(Component.CENTER));
		
		timeLabel = new Label("Time: 0" );
		livesLabel = new Label("Lives left: 3");
		lastFlagLabel = new Label("Last Flag Reached: 1");
		foodLabel = new Label("Food Level: 25");
		healthLabel = new Label("Health Level: 10");
		soundLabel = new Label("Sound: Off");
		
		northContainer.add(timeLabel);
		northContainer.add(livesLabel);
		northContainer.add(lastFlagLabel);
		northContainer.add(foodLabel);
		northContainer.add(healthLabel);
		northContainer.add(soundLabel);
		
		this.add(northContainer);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GameWorld) {
			GameWorld gw = (GameWorld) o;
			
			timeLabel.setText("Time: " + gw.getGameTick());
			livesLabel.setText("Lives Left: " + gw.getPlayerLives());
			lastFlagLabel.setText("Last Flag Reacehd: " + gw.getLastFlag());
			foodLabel.setText("Food Level: " + gw.getFoodLevel());
			healthLabel.setText("Health Level: " + gw.getHealthLevel());
			soundLabel.setText("Sound: " + gw.getSound());
			
		}
	}
	
}