package traps;

import java.awt.image.BufferedImage;

import game.Enemy;
import game.Field;
import game.Trap;

public class WaterTrap extends Trap {

	public WaterTrap(Field position,BufferedImage sprite) {
		super(position,sprite);
	}

	@Override
	public void trigger(Enemy enemy) {

		enemy.setFreezed(true);
	}
}
