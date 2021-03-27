package enemys;

import java.awt.Rectangle;

import chickencode.ImageLoader;
import game.Enemy;
import menue.Run;

public class Skelett extends Enemy {

	public Skelett() {
		super( 2,1, 100, ImageLoader.getImage("Skelett"), Run.instance.frame.game.fields[0][0],new Rectangle(0,30,0,60),new Rectangle(0,30,0,60),new Rectangle(30,0,60,0),new Rectangle(30,0,60,0));
	}

}
