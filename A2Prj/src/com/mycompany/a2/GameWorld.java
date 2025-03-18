package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import java.util.Random;
import java.util.ArrayList;
import java.util.Observable;


/*
 * The GameWorld class manages all game objects, state, and game logic.
 */
public class GameWorld extends Observable{
	
	private GameObjectCollection gameObjects;
	private Random rand = new Random();
	private int gameTick = 0;
	private int playerLives = 3;
	private boolean sound = false;
	private int mvAbsolX = 0; 
	private int mvAbsolY = 0;
	
	/*
	 * Default constructor for GameWorld.
	 */
	public GameWorld() {
	}
	
	/*
	 * Initializes all game objects and adds them to the list.
	 */
	public void init() {		
		
		//Creates ArrayList<> for all game objects to be stored
		gameObjects = new GameObjectCollection();
		
		//Creates 4 flags and assigns size of 25, predefined locations, color blue, and sequence numbers accordingly
		gameObjects.add(new Flag(new Point(100,100), 1));
		gameObjects.add(new Flag(new Point(900,100), 2));
		gameObjects.add(new Flag(new Point(900,100), 3));
		gameObjects.add(new Flag(new Point(900,100), 4));

		//Grabs instance of Ant, if it doesn't exist -> creates one in class.
		gameObjects.add(Ant.getAnt());
		
		//Creates 2 Spiders with size 25, random location, color black, random heading, speed of 5, and foodLevel 100 (won't be using)
		gameObjects.add(new Spider(new Point(rand.nextInt(1000), rand.nextInt(1000)), getAbsolX(), getAbsolY()));
		gameObjects.add(new Spider(new Point(rand.nextInt(1000), rand.nextInt(1000)), getAbsolX(), getAbsolY()));
		
		//Creates 2 FoodStations with random size, random location, color green, capacity is proportional to size so it will be set in the class
		gameObjects.add(new FoodStation(new Point(rand.nextInt(1000), rand.nextInt(1000))));
		gameObjects.add(new FoodStation(new Point(rand.nextInt(1000), rand.nextInt(1000))));
		
		}
	
	public IIterator getIterator() {
		return gameObjects.getIterator();
	}
	
	/*
	 * Returns for current game time, gameTick
	 */
	public int getGameTick() {
		return gameTick;
	}
	
	/*
	 * Setter for gameTick
	 */
	public void setGameTick(int amount) {
		gameTick = amount;
	}
	
	/*
	 * Returns for number of player lives left, playerLives
	 */
	public int getPlayerLives() {
		return playerLives;
	}
	
	/*
	 * Setter for playerLives 
	 */
	public void setPlayerLives(int amount) {
		playerLives = amount;
	}
	
	
	public int getLastFlag() {
		IIterator iterator = getIterator();
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	        if(obj instanceof Ant) {
	        	return ((Ant) obj).getLastFlagReached();	
	        }
		}
		return -1;
	}
	
	public int getFoodLevel() {
		IIterator iterator = getIterator();
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	        if(obj instanceof Ant) {
	        	return ((Ant) obj).getFoodLevel();	
	        }
		}
		return -1;
	}
	
	public int getHealthLevel() {
		IIterator iterator = getIterator();
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	        if(obj instanceof Ant) {
	        	return ((Ant) obj).getHealthLevel();	
	        }
		}
		return -1;
	}
	
	public String getSound() {
		if(sound == true) {
			return "ON"; 
		}else {
			return "OFF";
		}
	
	}
		
	public void setAbsolCords(int x, int y) {
		this.mvAbsolX = x;
		this.mvAbsolY = y;
	}
	
	public int getAbsolX() {
	    return mvAbsolX;
	}

	public int getAbsolY() {
	    return mvAbsolY;
	}
	
	/*
	 * Handles acceleration of Ant.
	 * Looks for Ant object and calls on method
	 */
	public void accelerate() {
		//Cycles through all game objects
		IIterator iterator = getIterator();
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	        if(obj instanceof Ant) {
	        	((Ant) obj).accelerate();
				System.out.println("Ant has sped up\n");

	        }
		}
		
		setChanged();
		notifyObservers();
	}
	
	/*
	 * Handles braking of the Ant.
	 * Looks for Ant object and calls on method
	 */
	public void brake() {
		IIterator iterator = getIterator();
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	        if(obj instanceof Ant) {
	        	((Ant) obj).brake();
				System.out.println("Ant has slowed down\\n");

	        }
		}
		
		setChanged();
		notifyObservers();
	}
	
	/*
	 * Handles turning left.
	 * Looks for Ant object and calls on method
	 */
	public void turnLeft() {
		IIterator iterator = getIterator();
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	        if(obj instanceof Ant) {
	        	((Ant) obj).turnLeft();
				System.out.println("Ant has turned left. Current heading: " + ((Ant) obj).getHeading() + "\n");
	        }
		}
		
		setChanged();
		notifyObservers();
	}
	
	/*
	 * Handles turning right.
	 * Looks for Ant object and calls on method
	 */
	public void turnRight() {
		IIterator iterator = getIterator();
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	        if(obj instanceof Ant) {
	        	((Ant) obj).turnRight();
				System.out.println("Ant has turned right. Current heading: " + ((Ant) obj).getHeading() + "\n");
	        }
		}
		
		setChanged();
		notifyObservers();
	}
	/*
	 * Sets the consumption rate of Ant to a random value between 1-3
	 */
	public void consumptionRate() {
		IIterator iterator = getIterator();
		int randFood = rand.nextInt(3) + 1;
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	        if(obj instanceof Ant) {
	        	((Ant) obj).setFoodConsumption(randFood);
				System.out.println("Ant's food set to: " + randFood + "\n");
	        }
		}
		
		setChanged();
		notifyObservers();
	}
	
	/*
	 * Tells Ant current flagReached, if flag not next in sequence
	 * return next flag needed to be reached
	 */
	public void flagCollision(int sequenceNumber) {
		IIterator iterator = getIterator();
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	        if(obj instanceof Ant) {
	        	Ant ant = (Ant) obj;
	        	ant.flagCollision(sequenceNumber);
			
	        	if(ant.getLastFlagReached() == 4) {
	        		String gameFinished = "Game over, you win! Total time: " + getGameTick();
	        		Dialog.show("Game Finished", gameFinished, "Ok", null );
	        		System.out.println("Game over, you win! Total time: " + getGameTick());
	        		Display.getInstance().exitApplication();
				}
	        }
		}
		
		
		setChanged();
		notifyObservers();
	}
	
	/*
	 * Handles food station collision.
	 * Looks for non-empty foodStation object
	 * Once found, calls on Ant to update foodLevel
	 * If not returns no stations found
	 */
	public void foodStationCollision() {
	    int foodStationFood = 0;
	    Ant ant = null;
	    boolean foundNonEmpty = false;

		IIterator iterator = getIterator();
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	        if(obj instanceof Ant) {
	        	ant = (Ant)obj;
	        }
		}
		
		IIterator iterator2 = getIterator();
		
		while(iterator2.hasNext()) {
	        GameObject obj = (GameObject) iterator2.getNext();
	        
	        if(obj instanceof FoodStation) {
	        	FoodStation station = (FoodStation)obj;
	        
            if (station.getCapacity() > 0) {
                foodStationFood = station.getCapacity();
                station.setCapacity();
                foundNonEmpty = true;
                break;
            	}
	        }
		}

	    if (foundNonEmpty) {
	        ant.foodStationCollision(foodStationFood);
	        System.out.println("Ant has increased food level.\n");
	    } else {
	        System.out.println("All FoodStations have been collected.\n");
	    }
	    
		setChanged();
		notifyObservers();
	}

	/*
	 * Tells Ant it collided with a Spider.
	 */
	public void spiderCollision() {
		Ant ant = null;
		IIterator iterator = getIterator();
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	        if(obj instanceof Ant) {
	        	ant = (Ant) obj;
	        	ant.spiderCollision();
				System.out.println("Ant has collided with Spider and lost health!\n");
				break;
	        }
		}

		if(ant.getHealthLevel() <= 0) {
			loseLife();
		}
		
		setChanged();
		notifyObservers();
	}
	
	/*
	 * Tells game clock has ticked. 
	 * Movable objects make their next move. 
	 * Ant has stats adjusted. 
	 */
	public void tickedClock() {
		
		gameTick += 1;
		System.out.println("Game Clock has Ticked.\n");
		
		IIterator iterator = getIterator();
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	   
	        if(obj instanceof Spider) {
	        	((Spider) obj).move();
	        }
		}
		
		IIterator iterator2 = getIterator();
		
		while(iterator2.hasNext()) {
	        GameObject obj = (GameObject) iterator2.getNext();
	        
	        if(obj instanceof Ant) {
	        	Ant ant = (Ant) obj;
	        	ant.move();
	        	ant.setFoodConsumption(1);
				if(ant.getFoodLevel() == 0) {
					System.out.println("Food Level is 0, you lost a life. Map Reset.\n");
					loseLife();
				}
	        }
		}
				
		setChanged();
		notifyObservers();
	}

	/*
	 * Reduces Ant's life by one. 
	 * Ends game if no lives left
	 */
	public void loseLife() {		
		
		setPlayerLives(getPlayerLives() - 1);
		System.out.println("Health Reduced, map reset!\n");
		
		if(getPlayerLives() == 0) {
			Dialog.show("GAME OVER!", "Game over, you failed!", "Ok", null);
			Display.getInstance().exitApplication();
		}
		
		IIterator iterator = getIterator();
		
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	        if(obj instanceof Ant) {
	        	((Ant) obj).resetAnt();
	        }
		}
		init();
		
		setChanged();
		notifyObservers();
	}
	
	public void toggleSound() {
		sound =! sound;
		
		setChanged();
		notifyObservers();
	}
}