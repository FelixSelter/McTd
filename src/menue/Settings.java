package menue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import chickencode.ImageLoader;
import chickencode.ResolutionPanel;
import listener.ListenerSettings;

public class Settings extends ResolutionPanel {

	public BufferedImage button_language;
	public BufferedImage button_sound;
	public BufferedImage button_fullscreen;
	public BufferedImage button_exit;
	public ListenerSettings listener=new ListenerSettings();

	public Settings(Frame frame) {

		super("SETTINGS", 1400, 1000);

		button_language = ImageLoader.getImage("Button");
		button_sound = ImageLoader.getImage("Button");
		button_fullscreen = ImageLoader.getImage("Button");
		button_exit = ImageLoader.getImage("Button");

		frame.panel.registerMouseListener("SETTINGS", listener);
		frame.panel.registerMouseMotionListener("SETTINGS", listener);

		setBackground(Color.BLACK);

	}

	@Override
	protected void paintComponent(Graphics g) {

		g.drawImage(ImageLoader.getImage("Dirt"), 0, 0, 1400, 1000, null);

		g.setFont(new Font(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()[69], 0, 50));
		g.setColor(Color.WHITE);

		g.drawImage(button_language, 450, 200, 500, 100, null);
		g.drawImage(button_sound, 450, 320, 500, 100, null);
		g.drawImage(button_fullscreen, 450, 440, 500, 100, null);
		g.drawImage(button_exit, 450, 600, 500, 100, null);

		g.drawString(Run.instance.propertie_language.getProperty("Language:"),
				1400 / 2 - g.getFontMetrics().stringWidth(Run.instance.propertie_language.getProperty("Language:")) / 2,
				260);
		g.drawString(Run.instance.propertie_language.getProperty("Volume:"),
				1400 / 2 - g.getFontMetrics().stringWidth(Run.instance.propertie_language.getProperty("Volume:")) / 2,
				380);
		g.drawString(Run.instance.propertie_language.getProperty("FullScreen:"), 1400 / 2
				- g.getFontMetrics().stringWidth(Run.instance.propertie_language.getProperty("FullScreen:")) / 2, 510);
		g.drawString(Run.instance.propertie_language.getProperty("Back:"),
				1400 / 2 - g.getFontMetrics().stringWidth(Run.instance.propertie_language.getProperty("Back:")) / 2,
				660);
	}

}
