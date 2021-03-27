package chickencode;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ResolutionPanel extends JPanel {

	private String name;
	private int width, height;

	public ResolutionPanel(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImage() {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		paintComponent(img.getGraphics());
		return img;
	}

}
