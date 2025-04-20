package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import java.util.Random;
import java.util.ArrayList;
import java.util.Observable;
import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;


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
	private int mvWidth = 0;
	private int mvHeight= 0;
	private boolean isPaused;
	private boolean buttonClicked = false;
	private Sound spiderSound, foodSound, flagSound;
	private BGSound bgSound;
	
	/*
	 * Default constructor for GameWorld.
	 */
	public GameWorld() {
	}
	
	/*
	 * Initializes all game objects and adds them to the list.
	 */
	public void init() {        
	    gameObjects = new GameObjectCollection();
	    
	    for(int seq=1; seq<=4; seq++){
	        int S    = new Flag(new Point(0,0), seq).getSize();  
	        int half = S/2;
	        int x    = half + rand.nextInt(mvWidth  - S);
	        int y    = half + rand.nextInt(mvHeight - S);
	        gameObjects.add(new Flag(new Point(x, y), seq));
	    }
	    
		gameObjects.add(Ant.getAnt());
		Ant.getAnt().setGameWorld(this);
	    
	    int spiderSize = 25;
	    int halfSpider = spiderSize / 2;
	    for (int i = 0; i < 2; i++) {
	        int sx = halfSpider + rand.nextInt(mvWidth - spiderSize);
	        int sy = halfSpider + rand.nextInt(mvHeight - spiderSize);
	        gameObjects.add(new Spider(new Point(sx, sy), getAbsolX(), getAbsolY()));
	    }
	    
	    for (int i = 0; i < 2; i++) {
	        FoodStation foodStation = new FoodStation(new Point(0,0));
	        int fsSize = foodStation.getSize();
	        int halfFs = fsSize / 2;
	        int fx2 = halfFs + rand.nextInt(mvWidth - fsSize);
	        int fy2 = halfFs + rand.nextInt(mvHeight - fsSize);
	        foodStation.setLocation(fx2, fy2);
	        gameObjects.add(foodStation);
	    }
	    
	    createSounds();
	}
	public IIterator getIterator() {
		return gameObjects.getIterator();
	}
	
	public GameObjectCollection getGameObjects() {
		return gameObjects;
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
	
	public void setMapDimen(int width, int height) {
		this.mvWidth = width;
		this.mvHeight = height;
	}
	
	public void setPaused(boolean pause) {
		isPaused = pause;
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	
	public void getSpiderSound() {
	    spiderSound.play();
	}

	public Sound getFoodSound() {
	    return foodSound;
	}
	
	public Sound getFlagSound() {
	    return flagSound;
	}

	public BGSound getBGSound() {
	    return bgSound;
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
				System.out.println("Ant has slowed down\n");

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
		Point antCords;
		gameTick += 1;
		
		IIterator iterator = getIterator();
		while(iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        
	   
	        if(obj instanceof Spider) {
	        	((Spider) obj).move(mvAbsolX, mvAbsolY, mvWidth, mvHeight);
	        }
		}
		
		IIterator iterator2 = getIterator();
		while(iterator2.hasNext()) {
	        GameObject obj = (GameObject) iterator2.getNext();
	        
	        if(obj instanceof Ant) {
	        	Ant ant = (Ant) obj;
	        	antCords = ant.getLocation();
	        	ant.move(mvAbsolX, mvAbsolY, mvWidth, mvHeight);
	        	ant.setFoodConsumption(1);
				if(ant.getFoodLevel() == 0) {
					System.out.println("Food Level is 0, you lost a life. Map Reset.\n");
					loseLife();
				}
	        	if(ant.getLastFlagReached() == 4) {
	        		String gameFinished = "Game over, you win! Total time: " + getGameTick();
	        		Dialog.show("Game Finished", gameFinished, "Ok", null );
	        		System.out.println("Game over, you win! Total time: " + getGameTick());
	        		Display.getInstance().exitApplication();
				}
	        }
		}
				
		IIterator iterator3 = getIterator();
		while(iterator3.hasNext()) {
			GameObject curObj = (GameObject)iterator3.getNext();
			IIterator iterator4 = getIterator();
			while(iterator4.hasNext()) {
				GameObject nextObj = (GameObject)iterator4.getNext();
				if(curObj != null && nextObj != null && curObj.collidesWith(nextObj)) {
					if(!curObj.isColliding(nextObj)) {
						curObj.handleCollision(nextObj);
						curObj.addCollision(nextObj);
						nextObj.addCollision(curObj);
					}
					}else {
						curObj.removeCollision(nextObj);
						nextObj.removeCollision(curObj);
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

	public void setClick(boolean click) {
		buttonClicked = click;
	}
	
	public boolean getClick() {
		return buttonClicked;
	}
	
	public void createSounds() {
	    spiderSound = new Sound("alarm.wav");
	    foodSound = new Sound("crunch.wav");
	    flagSound = new Sound("cheer.wav");
	    bgSound = new BGSound("bgmusic.wav");
	}
}