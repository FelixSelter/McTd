
package waves;

import enemys.Skelett;
import enemys.Zombie;
import game.Wave;
import menue.Run;

public class BasicEnemyWave extends Wave {

	private boolean zombielast;
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

				if (zombielast) {
					Run.instance.frame.game.enemys.add(new Zombie());
					zombielast = true;
				} else {
					Run.instance.frame.game.enemys.add(new Skelett());
				}
				counter--;
			} else
				delayCounter--;
			

		} else {
			Run.instance.frame.game.varmanager.addValue("RunningWaves", -1);
		}
	}

}
