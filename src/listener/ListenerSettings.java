package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import chickencode.ImageLoader;
import menue.Run;

public class ListenerSettings implements MouseListener, MouseMotionListener {

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (x >= 450 && x <= 950 && y >= 200 && y <= 300) {
			Run.instance.frame.settings.button_language = ImageLoader.getImage("HoveredButton");
		} else if (Run.instance.frame.settings.button_language == ImageLoader.getImage("HoveredButton")) {
			Run.instance.frame.settings.button_language = ImageLoader.getImage("Button");
		}

		if (x >= 450 && x <= 950 && y >= 320 && y <= 420) {
			Run.instance.frame.settings.button_sound = ImageLoader.getImage("HoveredButton");
		} else if (Run.instance.frame.settings.button_sound == ImageLoader.getImage("HoveredButton")) {
			Run.instance.frame.settings.button_sound = ImageLoader.getImage("Button");
		}
		if (x >= 450 && x <= 950 && y >= 440 && y <= 540) {
			Run.instance.frame.settings.button_fullscreen = ImageLoader.getImage("HoveredButton");
		} else if (Run.instance.frame.settings.button_fullscreen == ImageLoader.getImage("HoveredButton")) {
			Run.instance.frame.settings.button_fullscreen = ImageLoader.getImage("Button");
		}
		if (x >= 450 && x <= 950 && y >= 600 && y <= 700) {
			Run.instance.frame.settings.button_exit = ImageLoader.getImage("HoveredButton");
		} else if (Run.instance.frame.settings.button_exit == ImageLoader.getImage("HoveredButton")) {
			Run.instance.frame.settings.button_exit = ImageLoader.getImage("Button");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (x >= 450 && x <= 950 && y >= 200 && y <= 300) {
			Run.instance.sound_button.play();
		} else if (x >= 450 && x <= 950 && y >= 320 && y <= 420) {
			Run.instance.sound_button.play();
		} else if (x >= 450 && x <= 950 && y >= 440 && y <= 540) {
			Run.instance.sound_button.play();
		} else if (x >= 450 && x <= 950 && y >= 600 && y <= 700) {
			Run.instance.sound_button.play();
			Run.instance.frame.panel.setActive("MENUE");
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
		mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
