package game;

import java.awt.image.BufferedImage;

import helper.Trigerable;
import helper.Updateable;
import menue.Run;

public class Trap implements Updateable, Trigerable {

	private Field position;
	private int spritecounter = 0;
	private int tilecount = 4;
	private int tilewidth = 102;
	private int tileheight = 102;
	private int offsetY = 101;
	private int animation_speed_subtractor = 15;
	private int animation_update_counter = 0;
	private BufferedImage sprite;

	public Trap(Field position, BufferedImage sprite) {
		this.position = position;
		this.sprite=sprite;
	}

	@Override
	public void updateGameLoop() {

		animation_update_counter++;

		if (animation_speed_subtractor - animation_update_counter < 1) {
			animation_update_counter = 0;
			spritecounter++;

			if (spritecounter == tilecount)
				spritecounter = 0;
		}
		getPosition().setSprite(
				sprite.getSubimage(spritecounter + (spritecounter * tilewidth), offsetY, tilewidth, tileheight));

		for (int i = 0; i < Run.instance.frame.game.enemys.size(); i++) {
			Enemy enemy = Run.instance.frame.game.enemys.get(i);

			int x = enemy.getX();
			if (!calculateX(x)) {
				x += enemy.getImage().getWidth() * 5;
				if (!calculateX(x)) {
					return;
				}
			}
			int y = enemy.getY();
			if (!calculateY(y)) {
				y += enemy.getImage().getHeight() * 5;
				if (!calculateY(y)) {
					return;
				}
			}
			trigger(enemy);
		}
	}

	private boolean calculateX(int x) {
		return x >= position.getColumn() * 100 && x <= (position.getColumn() + 1) * 100;
	}

	private boolean calculateY(int y) {
		return y >= position.getLine() * 100 && y <= (position.getLine() + 1) * 100;
	}

	@Override
	public void trigger(Enemy enemy) {
	}

	public Field getPosition() {
		return position;
	}

}
