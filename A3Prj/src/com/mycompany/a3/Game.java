package com.mycompany.a3;


import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import java.util.Observer;
import java.util.Observable;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;


public class Game extends Form implements Runnable{
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	private UITimer gameTimer;
	
	private boolean isPaused = false;
	
	private AccelerateCommand accelCmd;
	private BrakeCommand brakeCmd;
	private LeftCommand leftCmd;
	private RightCommand rightCmd;
	private ConsumptionCommand consumpCmd;
	private StationCommand stationCmd;
	private SpiderCommand spiderCmd;
	private TickCommand tickCmd;
	private FlagCommand flagCmd;
	private ExitCommand exitCmd;
	private HelpCommand helpCmd;
	private AboutCommand aboutCmd;
	private SoundCommand soundCmd;
	private PauseCommand pauseCmd;
	private PositionCommand positionCmd;
	
	
	//Runs method init() which starts new instance of GameWorld()
	public Game() {
		
		initializeGameComponents();
		initializeCommands();
		setupListeners();
		
		this.setLayout(new BorderLayout());
		
		setupTitle();
		setupWestContainer();
		setupEastContainer();
		setupSouthContainer();
		this.add(BorderLayout.CENTER, mv);
		this.add(BorderLayout.NORTH, sv);
		
		this.show();
		
		gw.setMapDimen(mv.getWidth(), mv.getHeight());
		gw.setAbsolCords(mv.getX(), mv.getY());
		gw.init();
		gw.createSounds();
		revalidate();
	
	}

	public void initializeGameComponents() {
		gw = new GameWorld();
		mv = new MapView(gw);
		sv = new ScoreView(gw);
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		gameTimer = new UITimer(this);
		gameTimer.schedule(50, true, this);
	}
	
	public void initializeCommands(){
		accelCmd = new AccelerateCommand(gw);
		brakeCmd = new BrakeCommand(gw);
		leftCmd = new LeftCommand(gw);
		rightCmd = new RightCommand(gw);
		consumpCmd = new ConsumptionCommand(gw);
		stationCmd = new StationCommand(gw);
		spiderCmd = new SpiderCommand(gw);
		tickCmd = new TickCommand(gw);
		flagCmd = new FlagCommand(gw);
		soundCmd = new SoundCommand(gw);
		exitCmd = new ExitCommand();
		aboutCmd = new AboutCommand();
		helpCmd = new HelpCommand();
		pauseCmd = new PauseCommand(gw, this);
		positionCmd = new PositionCommand(gw, mv);
	}
	
	public void setupListeners() {
		addKeyListener('a', accelCmd);
		addKeyListener('b', brakeCmd);
		addKeyListener('l', leftCmd);
		addKeyListener('r', rightCmd);
		addKeyListener('c', consumpCmd);
		addKeyListener('f', stationCmd);
		addKeyListener('g', spiderCmd);
		addKeyListener('t', tickCmd);
	}
	
	public void setupTitle() {
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		
		CheckBox soundToggle = new CheckBox("Sound Toggle");
		soundToggle.setCommand(soundCmd);
		soundToggle.getUnselectedStyle().setBgTransparency(255);

		myToolbar.setTitle("Avoid-It Game");
		
		myToolbar.addCommandToSideMenu(accelCmd);
		myToolbar.addCommandToSideMenu(exitCmd);
		myToolbar.addComponentToSideMenu(soundToggle);
		myToolbar.addCommandToSideMenu(aboutCmd);
		myToolbar.addCommandToRightBar(helpCmd);
		
	}
	
	public void setupWestContainer() {
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Button accelButton = createButton("Accelerate", accelCmd);
		Button leftButton = createButton("Left", leftCmd);
		
		westContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		
		westContainer.add(accelButton);
		westContainer.add(leftButton);
		westContainer.getAllStyles().setPadding(Component.TOP, 100);
		
		this.add(BorderLayout.WEST, westContainer);
	}
	
	public void setupEastContainer() {
		Container eastContainer = new Container();
		Button brakeButton = createButton("Brake", brakeCmd);
		Button rightButton = createButton("Right", rightCmd);
		
		eastContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		
		eastContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		eastContainer.add(brakeButton);
		eastContainer.add(rightButton);
		eastContainer.getAllStyles().setPadding(Component.TOP, 100);
		
		this.add(BorderLayout.EAST, eastContainer);
	}
	
	public void setupSouthContainer() {
		Container southContainer = new Container(new FlowLayout(Component.CENTER));
		Button pauseButton = createButton("Pause", pauseCmd);
		Button tickButton = createButton("Tick", tickCmd);
		Button positionButton = createButton("Position", positionCmd);

		southContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));

		southContainer.add(pauseButton);
		southContainer.add(tickButton);
		southContainer.add(positionButton);

		
		this.add(BorderLayout.SOUTH, southContainer);

	}

	public Button createButton(String name, Command cmd) {
		Button button = new Button(name);
		button.setCommand(cmd);
		
		button.getUnselectedStyle().setBgTransparency(255);
		button.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		button.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		button.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		button.getAllStyles().setPadding(5, 5, 2, 2);
	
		return button;
	}
	
	public void toggleTime() {
		isPaused = !isPaused;
		mv.togglePause();
		gw.setPaused(isPaused);
		positionCmd.toggleTime();
		
		if(isPaused) {
			gameTimer.cancel();
		}else {
			gameTimer.schedule(50, true, this);
		}
	}
	
	@Override
	public void run() {
		gw.tickedClock();
	}
}