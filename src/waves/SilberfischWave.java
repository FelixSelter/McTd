package waves;

import java.util.Timer;
import java.util.TimerTask;

import enemys.Creeper;
import enemys.Silberfisch;
import game.Wave;
import menue.Run;

public class SilberfischWave extends Wave {

	private int counter;

	@Override
	public void startWave() {
		counter = Run.instance.frame.game.varmanager.getValue("Wave") * 10;

	}

	@Override
	public void updateGameLoop() {
		if (counter > 0) {
			if (delayCounter < 1) {
				delayCounter = spawnDelay;
				Run.instance.frame.game.enemys.add(new Silberfisch());
				counter--;
			} else
				delayCounter--;
		} else {
			Run.instance.frame.game.varmanager.addValue("RunningWaves", -1);
		}

	}

}
