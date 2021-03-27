
package game;

import java.util.Timer;

import helper.Updateable;


public abstract class Wave implements Updateable {


	protected int spawnDelay=50;
	protected int delayCounter=0;
	public abstract void startWave();

}
