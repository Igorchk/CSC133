package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed{

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
	
	/*
	 * Returns current stats of Flag
	 */
	@Override
	public String toString() {
		return "Flag: loc=" + Math.round(getLocation().getX() * 100)/100.0f + ", " + Math.round(getLocation().getY()* 100)/100.0f + 
				" color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + 
				"] size=" + getSize() + " seqNum=" + getSequenceNumber();
	}
}
