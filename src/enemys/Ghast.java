package enemys;

import java.awt.Rectangle;

import chickencode.ImageLoader;
import game.Enemy;
import menue.Run;

public class Ghast extends Enemy {

	public Ghast() {
		super( 1,1, 500,ImageLoader.getImage("Ghast"), Run.instance.frame.game.fields[0][0],new Rectangle(0,0,0,12),new Rectangle(0,15,0,18),new Rectangle(0,0,12,0),new Rectangle(12,0,18,0));
	}

}
