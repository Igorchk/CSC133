package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class FoodStation extends Fixed implements IDrawable{

	private GameWorld gw;
	private int capacity;
	private static Random rand = new Random();
	
	public FoodStation(Point location) {
		super(rand.nextInt(41) + 10 ,location, ColorUtil.rgb(0, 255, 0));
		this.capacity = getSize();
	}

	/*
	 * Getter for capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/*
	 * Setter for capacity
	 */
	public void setCapacity( ) {
		this.capacity = 0;
	}
	
	/*
	 * Overrides parents method to change color
	 */
	@Override
	public void setColor(int color) {	
	}

	public void setGameWorld(GameWorld gw) {
		this.gw = gw;
	}
	
	/*
	 * Returns current stats for FoodStation
	 */
	@Override
	public String toString() {
		return "FoodStation: loc=" + Math.round(getLocation().getX() * 100)/100.0f + ", " + Math.round(getLocation().getY()* 100)/100.0f + 
				" color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + 
				"] size=" + getSize() + " capacity=" + getCapacity();
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {		
		int size = getSize() * 3;
		float x = getLocation().getX() + pCmpRelPrnt.getX() - size / 2;
		float y = getLocation().getY() + pCmpRelPrnt.getY() - size / 2;
	    String capacity = String.valueOf(getCapacity());

	    if (isSelected()) {
	        g.setColor(ColorUtil.WHITE);
	        g.fillRect((int)x, (int)y, size, size);

	        g.setColor(ColorUtil.GREEN);
	        g.drawRect((int)x, (int)y, size, size);
	        
	    } else {
	        g.setColor(ColorUtil.GREEN);
	        g.fillRect((int)x, (int)y, size, size);
	    }

	    g.setColor(ColorUtil.BLACK);
	    g.drawString(capacity, (int)x + size, (int)y + size);
	    	    
	}
	
	@Override
	public void handleCollision(GameObject otherObject) {
		if(otherObject instanceof Ant) {
			Ant ant = (Ant) otherObject;
			ant.foodStationCollision(capacity);
			
		}
	}
}