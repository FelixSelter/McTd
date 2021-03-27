package enemys;

import java.awt.Rectangle;

import chickencode.ImageLoader;
import game.Enemy;
import menue.Run;

public class Creeper extends Enemy {

	public Creeper() {
		super( 2,1, 100, ImageLoader.getImage("Creeper"), Run.instance.frame.game.fields[0][0],new Rectangle(20,10,40,20),new Rectangle(20,10,40,20),new Rectangle(10,20,20,40),new Rectangle(10,20,20,40));
	}

}
