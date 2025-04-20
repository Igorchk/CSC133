package com.mycompany.a3;


import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;


abstract class GameObject implements IDrawable, ICollider{

	private int size;
	private Point location;
	private int color;
	private Vector <GameObject> colliding;
	
	public GameObject(int size, Point location, int color) {
		this.size = size;
		this.location = location;
		this.color = color;
		colliding = new Vector<>();
	}
	
	/*
	 * Getter for size
	 */
	public int getSize() {
		return size;
	}
	
	/*
	 * Getter for location
	 */
	public Point getLocation() {
		return location;
	}
	
	/*
	 * Setter for location
	 */
	public void setLocation(float x, float y) {
		this.location = new Point(x, y);
	}
	
	/*
	 * Getter for color
	 */
	public int getColor() {
		return color;
	}
	
	/*
	 * Setter for color
	 */
	public void setColor(int color) {
		this.color = color;
	}
	
	protected void addCollision(GameObject obj) {
	    if (!colliding.contains(obj)) {
	        colliding.add(obj);
	    }
	}

	protected void removeCollision(GameObject obj) {
	    colliding.remove(obj);
	}

	protected boolean isColliding(GameObject obj) {
	    return colliding.contains(obj);
	}
	
	//Leave it up to the kids to define
	public void draw(Graphics g, Point pCmpRelPrnt) {}
	public boolean collidesWith(GameObject otherObject) {
		
		int curLeft = (int)getLocation().getX() - getSize()/2;
		int curRight = (int)getLocation().getX() + getSize()/2;
		int curTop = (int)getLocation().getY() - getSize()/2;
		int curBottom = (int)getLocation().getY() + getSize()/2;
		
		int otherLeft = (int)otherObject.getLocation().getX() - otherObject.getSize()/2;
		int otherRight = (int)otherObject.getLocation().getX() + otherObject.getSize()/2;
		int otherTop = (int)otherObject.getLocation().getY() - otherObject.getSize()/2;
		int otherBottom = (int)otherObject.getLocation().getY() + otherObject.getSize()/2;
		
	    return !(curRight < otherLeft || curLeft > otherRight ||
	             curBottom < otherTop || curTop > otherBottom);
	}
	public void handleCollision(GameObject otherObject) {}
}
