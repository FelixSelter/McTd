package menue;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import chickencode.ContentPanel;
import chickencode.EngineFrame;
import game.Game;
import listener.ListenerFrame;

public class Frame extends EngineFrame {

	public ContentPanel panel;
	public Menue menue;
	public Settings settings;
	public Game game;
	public boolean playing = false;

	public Frame() {

		// setup
		panel = getContentPanel();
		menue = new Menue(this);
		settings = new Settings(this);
		game = new Game(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 1000);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setAlwaysOnTop(true);
		setVisible(true);

		// register
		panel.setScale_up(false);
		panel.registerResolutionPanel(menue);
		panel.registerResolutionPanel(settings);
		panel.registerResolutionPanel(game);
		panel.setActive("MENUE");

		// listener
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Run.instance.exit();
			}
		});

		addKeyListener(new ListenerFrame());

		// enable
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {

				if (playing) {
					game.updateGameLoop();
				} else {
					menue.updateGameLoop();
				}

			}
		}, 0, 30);

		Thread repainter = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					repaint();
				}
			}
		});
		repainter.start();

	}
}
