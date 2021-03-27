package waves;

import java.util.Timer;
import java.util.TimerTask;

import enemys.Creeper;
import enemys.Skelett;
import game.Wave;
import menue.Run;

public class SkelettWave extends Wave {

	private int counter;

	@Override
	public void startWave() {

		counter = Run.instance.frame.game.varmanager.getValue("Wave") * 5;

	}

	@Override
	public void updateGameLoop() {
		if (counter > 0) {
			if (delayCounter < 1) {
				delayCounter = spawnDelay;
				Run.instance.frame.game.enemys.add(new Skelett());
				counter--;
			} else
				delayCounter--;
		} else {
			Run.instance.frame.game.varmanager.addValue("RunningWaves", -1);
		}
	}

}
