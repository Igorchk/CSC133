package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;

public class Flag extends Fixed{

	private GameWorld gw;
	private int sequenceNumber;
	
	public Flag(Point location, int sequenceNumber) {
		
		super(25, location, ColorUtil.rgb(0, 0, 255));
		this.sequenceNumber = sequenceNumber;
		
	}
	
	/*
	 * Setter for sequenceNumber
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
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
	 * Returns current stats of Flag
	 */
	@Override
	public String toString() {
		return "Flag: loc=" + Math.round(getLocation().getX() * 100)/100.0f + ", " + Math.round(getLocation().getY()* 100)/100.0f + 
				" color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + 
				"] size=" + getSize() + " seqNum=" + getSequenceNumber();
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
	    float x = getLocation().getX() + pCmpRelPrnt.getX();
	    float y = getLocation().getY() + pCmpRelPrnt.getY();
	    int side = getSize() * 3;
	    int half = side / 2;

	    int[] xPoints = {(int) x, (int)(x - half), (int)(x + half)};
	    int[] yPoints = {(int)(y + half), (int)(y - half), (int)(y - half)};

	    if (isSelected()) {
	        g.setColor(ColorUtil.WHITE);
	        g.fillPolygon(xPoints, yPoints, 3);
	        g.setColor(ColorUtil.BLUE);
	        g.drawPolygon(xPoints, yPoints, 3);
	    } else {
	        g.setColor(ColorUtil.BLUE);
	        g.fillPolygon(xPoints, yPoints, 3);
	    }

	    g.setColor(ColorUtil.BLACK);
	    String seq = String.valueOf(getSequenceNumber());
	    g.drawString(seq, (int)(x + side/4), (int)(y + side/4));
	    }
	
	@Override
	public void handleCollision(GameObject otherObject) {
		if(otherObject instanceof Ant) {
			Ant ant = (Ant) otherObject;
			ant.flagCollision(sequenceNumber);
		}
	}
}
