package com.mycompany.a3;

import java.util.Vector;

public class GameObjectCollection implements ICollection{
	private Vector<GameObject> gameObjects;
	
	public GameObjectCollection() {
		gameObjects = new Vector<>();
	}
	
	public void add(GameObject newObject) {
        gameObjects.addElement(newObject);
	}
	
	public IIterator getIterator() {
		return new GameObjectIterator();
	}

	private class GameObjectIterator implements IIterator{
		
		private int curIndex = 0;
		
		public boolean hasNext() {
			return curIndex < gameObjects.size();
		}
		
		public Object getNext() {
			if(hasNext()) {
				return gameObjects.elementAt(curIndex++);
			}
			return null;
		}

	}
}