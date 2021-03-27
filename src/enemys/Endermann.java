package enemys;

import java.awt.Rectangle;

import chickencode.ImageLoader;
import game.Enemy;
import menue.Run;

public class Endermann extends Enemy {

	public Endermann() {
		super( 5,1, 80, ImageLoader.getImage("Endermann"), Run.instance.frame.game.fields[0][0],new Rectangle(5,20,12,40),new Rectangle(5,20,12,35),new Rectangle(20,5,40,12),new Rectangle(20,5,35,12));
	}

}
