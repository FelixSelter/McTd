package game;

import java.util.ArrayList;

import chickencode.ImageLoader;
import helper.Updateable;
import menue.Run;
import waves.BasicEnemyWave;
import waves.CreeperWave;
import waves.EndermannWave;
import waves.GhastWave;
import waves.SchleimWave;
import waves.SilberfischWave;
import waves.SpinneWave;

public class WaveManager implements Updateable {

	private ArrayList<Wave> waves = new ArrayList<Wave>();
	private ArrayList<Wave> runningwaves = new ArrayList<Wave>();
	private int maxrunningwaves = 5;
	private int waveindex = 0;

	public WaveManager() {

		waves.add(new BasicEnemyWave());
		waves.add(new CreeperWave());
		waves.add(new SpinneWave());
		waves.add(new EndermannWave());
		waves.add(new SchleimWave());
		waves.add(new SilberfischWave());
		waves.add(new GhastWave());

		if (maxrunningwaves > waves.size())
			maxrunningwaves = waves.size();

	}

	@Override
	public void updateGameLoop() {
		for (Wave wave : runningwaves) {
			wave.updateGameLoop();
		}

	}

	public void startNewWave() {

		if (Run.instance.frame.game.fields[9][13].getSprite() == ImageLoader.getImage("Path")
				|| Run.instance.frame.game.fields[9][13].getSprite() == ImageLoader.getImage("HoveredPath")) {

			if (runningwaves.size() < maxrunningwaves) {

				runningwaves.add(waves.get(waveindex));
				runningwaves.get(runningwaves.size()-1).startWave();
				Run.instance.frame.game.varmanager.addValue("Wave", 1);

				waveindex++;
				if (waveindex > maxrunningwaves)
					waveindex = 0;
			}
		}
	}

}
