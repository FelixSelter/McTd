package game;

import java.awt.image.BufferedImage;
import java.io.File;

import chickencode.ImageLoader;
import menue.Run;

public class Field {

	private int line;
	private int column;
	private BufferedImage sprite;
	private Field nextfield;

	public Field(int line, int column) {

		this.line = line;
		this.column = column;
		sprite = ImageLoader.getImage("Grass");

	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage img) {
		this.sprite = img;
	}

	public void setNextfield(Field nextfield) {
		this.nextfield = nextfield;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public Field getNextfield() {
		return nextfield;
	}

}
