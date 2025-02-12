package com.mycompany.a1;


import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


public class GameObject {

	private int size;
	private Point location;
	private int color;
	
	public GameObject(int size, Point location, int color) {
		this.size = size;
		this.location = location;
		this.color = color;
	}
	
	
	public int getSize() {
		return size;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setLocation(int x, int y) {
		location = new Point(x, y);
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
}
