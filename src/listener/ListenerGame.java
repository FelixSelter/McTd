package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import chickencode.ImageLoader;
import game.Field;
import game.Tower;
import game.Wave;
import menue.Run;

public class ListenerGame implements MouseListener, MouseMotionListener {

	public Field lastclick;
	private boolean closed;

	@Override
	public void mouseMoved(MouseEvent e) {

		try {
			Field hovered = Run.instance.frame.game.fields[e.getY() / 100][e.getX() / 100];

			for (int line = 0; line < Run.instance.frame.game.fields.length; line++) {
				for (int column = 0; column < Run.instance.frame.game.fields[0].length; column++) {
					if (Run.instance.frame.game.fields[line][column].getSprite() == ImageLoader
							.getImage("HoveredGrass")) {
						Run.instance.frame.game.fields[line][column].setSprite(ImageLoader.getImage("Grass"));
					} else if (Run.instance.frame.game.fields[line][column].getSprite() == ImageLoader
							.getImage("HoveredPath")) {
						Run.instance.frame.game.fields[line][column].setSprite(ImageLoader.getImage("Path"));
					}
				}
			}
			if (hovered.getSprite() == ImageLoader.getImage("Grass")) {
				hovered.setSprite(ImageLoader.getImage("HoveredGrass"));
			}
			if (hovered.getSprite() == ImageLoader.getImage("Path")) {
				hovered.setSprite(ImageLoader.getImage("HoveredPath"));
			}

//			if (Run.instance.frame.game.fields[9][13].getSprite() != ImageLoader.getImage("Path")) {
//				Run.instance.frame.game.setCursor(Toolkit.getDefaultToolkit()
//						.createCustomCursor(ImageLoader.getImage("Shovel"), new Point(10, 10), "shovel"));
//			} else {
//				Run.instance.frame.game.setCursor(Cursor.getDefaultCursor());
//			}

		} catch (Exception e2) {
		}

	}

	public void mouseAction(MouseEvent e, boolean dragged) {
		if (!(e.getX() > Run.instance.frame.getWidth() || e.getX() < 0 || e.getY() > Run.instance.frame.getHeight()
				|| e.getY() < 0)) {

			Field clicked = Run.instance.frame.game.fields[e.getY() / 100][e.getX() / 100];

			closed = false;
			if (Run.instance.frame.game.inv.isOpened()) {
				Run.instance.frame.game.inv.onClick(e.getX(), e.getY());
				closed = true;
			}

			if (Run.instance.frame.game.fields[9][13].getSprite() != ImageLoader.getImage("Path")) {

				Field north = null;
				Field south = null;
				Field east = null;
				Field west = null;

				if (lastclick.getLine() > 0) {
					north = Run.instance.frame.game.fields[lastclick.getLine() - 1][lastclick.getColumn()];
				}

				if (lastclick.getLine() < 9) {
					south = Run.instance.frame.game.fields[lastclick.getLine() + 1][lastclick.getColumn()];
				}

				if (lastclick.getColumn() < 13) {
					east = Run.instance.frame.game.fields[lastclick.getLine()][lastclick.getColumn() + 1];
				}

				if (lastclick.getColumn() > 0) {
					west = Run.instance.frame.game.fields[lastclick.getLine()][lastclick.getColumn() - 1];
				}

				if ((clicked == north || clicked == south || clicked == east || clicked == west)
						&& (clicked.getSprite().equals(ImageLoader.getImage("HoveredGrass"))
								|| clicked.getSprite().equals(ImageLoader.getImage("Grass"))
								|| clicked.getSprite().equals(ImageLoader.getImage("Goal")))) {
					clicked.setSprite(ImageLoader.getImage("Path"));
					lastclick.setNextfield(clicked);
					lastclick = clicked;
					Run.instance.frame.game.varmanager.addValue("Money", 100);
				}
			} else {
				if (Run.instance.frame.game.inv.isOpened() == false && closed == false && dragged == false) {
					Run.instance.frame.game.inv.open(clicked);
				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseAction(e, true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseAction(e, false);
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
