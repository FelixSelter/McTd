package waves;

import java.util.Timer;
import java.util.TimerTask;

import enemys.Spinne;
import game.Wave;
import menue.Run;

public class SpinneWave extends Wave {

	private int counter;

	@Override
	public void startWave() {
		counter = Run.instance.frame.game.varmanager.getValue("Wave") * 4;

		

	}

	@Override
	public void updateGameLoop() {
		if (counter > 0) {
			if (delayCounter < 1) {
				delayCounter = spawnDelay;
			Run.instance.frame.game.enemys.add(new Spinne());
			counter--;
			} else 
				delayCounter--;
		} else {
			Run.instance.frame.game.varmanager.addValue("RunningWaves", -1);
		}		
	}

}
