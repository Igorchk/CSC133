package com.mycompany.a2;


import com.codename1.ui.Form;
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
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;


public class Game extends Form{
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
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
		
		gw.init();
		this.show();
}

	public void initializeGameComponents() {
		gw = new GameWorld();
		mv = new MapView(gw);
		sv = new ScoreView(gw);
		
		gw.addObserver(mv);
		gw.addObserver(sv);
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
		Button flagButton = createButton("Collide with Flag", flagCmd);
		Button spiderButton = createButton("Collide with Spider", spiderCmd);
		Button stationButton = createButton("Collide with Food Stations", stationCmd);
		Button tickButton = createButton("Tick", tickCmd);
		
		southContainer.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));

		southContainer.add(flagButton);
		southContainer.add(spiderButton);
		southContainer.add(stationButton);
		southContainer.add(tickButton);
		
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

	public void getAbsoluteCords() {
		int absolX = mv.getAbsoluteX();
		int absolY = mv.getAbsoluteY();
		
		gw.setAbsolCords(absolX, absolY);
	}
}