package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import chickencode.ImageLoader;
import game.Field;
import menue.Run;

public class ListenerFrame implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch (keycode) {

		case KeyEvent.VK_BACK_SPACE:
			if (Run.instance.frame.game.fields[9][13].getSprite() != ImageLoader.getImage("Path")&&Run.instance.frame.game.fields[9][13].getSprite() != ImageLoader.getImage("HoveredPath")) {
				Run.instance.frame.game.listenergame.lastclick.setSprite(ImageLoader.getImage("Grass"));
				for (int line = 0; line < Run.instance.frame.game.fields.length; line++) {
					for (int column = 0; column < Run.instance.frame.game.fields[0].length; column++) {

						Run.instance.frame.game.fields[line][column] = new Field(line, column);

					}
				}
				Run.instance.frame.game.fields[0][0].setSprite(ImageLoader.getImage("Path"));
				Run.instance.frame.game.fields[9][13].setSprite(ImageLoader.getImage("Goal"));
				Run.instance.frame.game.listenergame.lastclick = Run.instance.frame.game.fields[0][0];
				Run.instance.frame.game.varmanager.setVar("Money", 0);

			}
			break;

		case KeyEvent.VK_F11:
			boolean change = Run.instance.frame.isUndecorated();
			Run.instance.frame.dispose();
			Run.instance.frame.setUndecorated(!change);
			Run.instance.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			Run.instance.frame.setAlwaysOnTop(!change);
			Run.instance.frame.setVisible(true);
			Run.instance.frame.panel.recalculateSize();
			break;

		case KeyEvent.VK_F12:
			Run.instance.frame.panel.setScale_up(!Run.instance.frame.panel.isScale_up());
			Run.instance.frame.panel.recalculateSize();
			break;

		case KeyEvent.VK_ENTER:
				Run.instance.frame.game.wavemanager.startNewWave();
				
			break;

		case KeyEvent.VK_ESCAPE:
			Run.instance.frame.playing = !Run.instance.frame.playing;
			if (Run.instance.frame.playing) {
				Run.instance.frame.panel.setActive("GAME");
			} else {
				Run.instance.frame.panel.setActive("MENUE");
			}
			break;

		case KeyEvent.VK_F3:
			Run.instance.frame.game.hitboxes = !Run.instance.frame.game.hitboxes;
			break;

		case KeyEvent.VK_F1:
			Run.instance.frame.game.mapmanager.saveMap(Run.instance.frame.game.fields);
			break;
		case KeyEvent.VK_F2:
			Run.instance.frame.game.fields = Run.instance.frame.game.mapmanager.loadMap();
			break;

		default:
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
