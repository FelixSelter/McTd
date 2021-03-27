package menue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import chickencode.ImageLoader;
import chickencode.ResolutionPanel;
import helper.Updateable;
import listener.ListenerMenue;

public class Menue extends ResolutionPanel implements Updateable {

	public BufferedImage button_start;
	public BufferedImage button_settings;
	public BufferedImage button_exit;
	private int bannerX = 0;
	private ListenerMenue listener = new ListenerMenue();

	public Menue(Frame frame) {

		super("MENUE", 1400, 1000);
		frame.panel.registerMouseListener("MENUE", listener);
		frame.panel.registerMouseMotionListener("MENUE", listener);
		button_start = ImageLoader.getImage("Button");
		button_settings = ImageLoader.getImage("Button");
		button_exit = ImageLoader.getImage("Button");

	}

	@Override
	protected void paintComponent(Graphics g) {

		g.setFont(new Font(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()[69], 0, 50));
		g.setColor(Color.WHITE);

		g.drawImage(ImageLoader.getImage("Menue"), 0, 0, 1400, 1000, null);
		g.drawImage(button_start, 450, 500, 500, 100, null);
		g.drawImage(button_settings, 450, 620, 500, 100, null);
		g.drawImage(button_exit, 450, 740, 500, 100, null);

		g.drawString(Run.instance.propertie_language.getProperty("Start:"),
				700 - (g.getFontMetrics().stringWidth(Run.instance.propertie_language.getProperty("Start:")) / 2), 565);

		g.drawString(Run.instance.propertie_language.getProperty("Settings:"),
				700 - (g.getFontMetrics().stringWidth(Run.instance.propertie_language.getProperty("Settings:")) / 2),
				685);
		g.drawString(Run.instance.propertie_language.getProperty("Exit:"),
				700 - (g.getFontMetrics().stringWidth(Run.instance.propertie_language.getProperty("Exit:")) / 2), 805);

		g.drawImage(ImageLoader.getImage("Banner"), bannerX - ImageLoader.getImage("Banner").getWidth(), 900,
				ImageLoader.getImage("Banner").getWidth()+10, 100, null);
		g.drawImage(ImageLoader.getImage("Banner"), bannerX, 900, ImageLoader.getImage("Banner").getWidth()+10, 100, null);

	}

	@Override
	public void updateGameLoop() {
		if (bannerX < 1400) {
			bannerX += 5;
		} else {
			bannerX = 0;
		}
	}

}
