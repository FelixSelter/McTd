package enemys;

import java.awt.Rectangle;

import chickencode.ImageLoader;
import game.Enemy;
import menue.Run;

public class Silberfisch extends Enemy {

	public Silberfisch() {
		super( 5,1, 40, ImageLoader.getImage("Silberfisch"), Run.instance.frame.game.fields[0][0],new Rectangle(20,10,40,20),new Rectangle(20,10,40,20),new Rectangle(10,20,20,40),new Rectangle(10,20,20,40));
	}

}
