package tower;

import java.awt.Rectangle;

import chickencode.ImageLoader;
import game.Field;
import game.Tower;
import menue.Run;

public class ArrowTower extends Tower {

	public ArrowTower(Field position) {
		super(position, 100, ImageLoader.getImage("ArrowProjectile"), 3, 100, new Rectangle(0, 10, 0, 10),
				new Rectangle(0, 0, 0, 10), new Rectangle(10, 0, 10, 0), new Rectangle(0, 0, 10, 0));
	}

}
