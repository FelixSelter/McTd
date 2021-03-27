package enemys;

import java.awt.Rectangle;

import chickencode.ImageLoader;
import game.Enemy;
import menue.Run;

public class Zombie extends Enemy {

	public Zombie() {
		super(2, 1, 100, ImageLoader.getImage("Zombie"), Run.instance.frame.game.fields[0][0], new Rectangle(0, 5, 0, 41),
				new Rectangle(0, 30, 0, 40), new Rectangle(5, 0, 41, 0), new Rectangle(30, 0, 40, 0));
	}

}
