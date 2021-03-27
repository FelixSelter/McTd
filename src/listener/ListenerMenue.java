package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import chickencode.ImageLoader;
import menue.Run;

public class ListenerMenue implements MouseListener, MouseMotionListener {

	public ListenerMenue() {
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();

		if (x >= 450 && x <= 950 && y >= 500 && y <= 600) {
			Run.instance.frame.menue.button_start = ImageLoader.getImage("HoveredButton");
		} else if (Run.instance.frame.menue.button_start == ImageLoader.getImage("HoveredButton")) {
			Run.instance.frame.menue.button_start = ImageLoader.getImage("Button");
		}

		if (x >= 450 && x <= 950 && y >= 620 && y <= 720) {
			Run.instance.frame.menue.button_settings = ImageLoader.getImage("HoveredButton");
		} else if (Run.instance.frame.menue.button_settings == ImageLoader.getImage("HoveredButton")) {
			Run.instance.frame.menue.button_settings = ImageLoader.getImage("Button");
		}

		if (x >= 450 && x <= 950 && y >= 740 && y <= 840) {
			Run.instance.frame.menue.button_exit = ImageLoader.getImage("HoveredButton");
		} else if (Run.instance.frame.menue.button_exit == ImageLoader.getImage("HoveredButton")) {
			Run.instance.frame.menue.button_exit = ImageLoader.getImage("Button");
		}

		System.out.println();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (x >= 450 && x <= 950 && y >= 500 && y <= 600) {
			Run.instance.sound_button.play();
			Run.instance.frame.panel.setActive("GAME");
			Run.instance.frame.playing = true;
		} else

		if (x >= 450 && x <= 950 && y >= 620 && y <= 720) {
			Run.instance.sound_button.play();
			Run.instance.frame.panel.setActive("SETTINGS");
		} else

		if (x >= 450 && x <= 950 && y >= 740 && y <= 840) {
			Run.instance.sound_button.play();
			Run.instance.exit();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
		mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
