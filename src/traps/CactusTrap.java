package traps;

import java.awt.image.BufferedImage;

import chickencode.ImageLoader;
import game.Enemy;
import game.Field;
import game.Trap;
import menue.Run;

public class CactusTrap extends Trap {

	int live = 5;

	public CactusTrap(Field position,BufferedImage sprite) {
		super(position,sprite);
	}

	@Override
	public void trigger(Enemy enemy) {

		live--;
		enemy.attack(50);
		loop: for (int line = 0; line < Run.instance.frame.game.fields.length; line++) {
			for (int column = 0; column < Run.instance.frame.game.fields[0].length; column++) {
				Field field = Run.instance.frame.game.fields[line][column];
				if (field.getNextfield() != null && field.getNextfield().getNextfield().equals(getPosition())) {
					enemy.setX(field.getColumn() * 100);
					enemy.setY(field.getLine() * 100);
					enemy.setField(field);
					break loop;
				}
			}
		}

		if (live < 1) {
			Run.instance.frame.game.traps.remove(this);
			getPosition().setSprite(ImageLoader.getImage("Path"));

		}
	}

}
