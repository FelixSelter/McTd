package enemys;

import java.awt.Rectangle;

import chickencode.ImageLoader;
import game.Enemy;
import menue.Run;

public class Spinne extends Enemy {

	public Spinne() {
		super(4, 1,100, ImageLoader.getImage("Spinne"), Run.instance.frame.game.fields[0][0],new Rectangle(20,10,40,20),new Rectangle(20,10,40,20),new Rectangle(10,20,20,40),new Rectangle(10,20,20,40));
	}

}
