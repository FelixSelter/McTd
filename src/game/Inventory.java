package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import chickencode.ImageLoader;
import menue.Run;
import tower.ArrowTower;
import tower.EggTower;
import traps.CactusTrap;
import traps.WaterTrap;

public class Inventory {

	private BufferedImage sprite;
	private Field selected;
	private BufferedImage image;
	private boolean opened;
	private Graphics g;

	public Inventory() {
		close();
	}

	public boolean isOpened() {
		return opened;
	}

	public void open(Field field) {
		selected = field;
		sprite = selected.getSprite();

		if (sprite.equals(ImageLoader.getImage("HoveredGrass"))) {
			g.drawImage(ImageLoader.getImage("Inventory"), 25, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 175, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 325, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 475, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 625, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 775, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 925, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 1075, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 1225, 850, 150, 150, null);

			if (Run.instance.frame.game.varmanager.getValue("Money") >= Run.instance.frame.game.varmanager
					.getValue("EggTowerPrice")) {
				g.drawImage(ImageLoader.getImage("EggTower"), 44, 869, 112, 112, null);
			} else {
				g.drawImage(ImageLoader.getImage("EggTowerRed"), 44, 869, 112, 112, null);
			}
			
			if (Run.instance.frame.game.varmanager.getValue("Money") >= Run.instance.frame.game.varmanager
					.getValue("ArrowTowerPrice")) {
				g.drawImage(ImageLoader.getImage("ArrowTower"), 194, 869, 112, 112, null);
			} else {
				g.drawImage(ImageLoader.getImage("ArrowTowerRed"), 194, 869, 112, 112, null);
			}

		} else if (sprite.equals(ImageLoader.getImage("HoveredPath"))) {
			g.drawImage(ImageLoader.getImage("Inventory"), 175, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 325, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 475, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 625, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 775, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 925, 850, 150, 150, null);
			g.drawImage(ImageLoader.getImage("Inventory"), 1075, 850, 150, 150, null);

			if (Run.instance.frame.game.varmanager.getValue("Money") >= Run.instance.frame.game.varmanager
					.getValue("CactusTrapPrice")) {
				g.drawImage(ImageLoader.getImage("Cactus").getSubimage(0, 0, 100, 100), 194, 869, 112, 112, null);
			} else {
				g.drawImage(ImageLoader.getImage("Cactus_Red").getSubimage(0, 0, 100, 100), 194, 869, 112, 112, null);
			}

			if (Run.instance.frame.game.varmanager.getValue("Money") >= Run.instance.frame.game.varmanager
					.getValue("WaterTrapPrice")) {
				g.drawImage(ImageLoader.getImage("WaterTrench").getSubimage(0, 0, 100, 100), 344, 869, 112, 112, null);
			} else {
				g.drawImage(ImageLoader.getImage("WaterTrench_Red").getSubimage(0, 0, 100, 100), 344, 869, 112, 112,
						null);
			}
		}

		opened = true;
	}

	public void close() {
		opened = false;
		image = new BufferedImage(1400, 1080, BufferedImage.TYPE_INT_ARGB);
		g = image.getGraphics();
		g.drawImage(ImageLoader.getImage("InventoryInfo"), 1100, 100, 300, 700, null);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void onClick(int clickedX, int clickedY) {
		

		if (sprite.equals(ImageLoader.getImage("HoveredGrass"))) {
			if (clickedX >= 25 && clickedX <= 175 && clickedY >= 850 && clickedY <= 1000
					&& Run.instance.frame.game.varmanager.getValue("Money") >= Run.instance.frame.game.varmanager
							.getValue("EggTowerPrice")) {
				selected.setSprite(ImageLoader.getImage("EggTower"));
				Run.instance.frame.game.towers.add(new EggTower(selected));
				Run.instance.frame.game.varmanager.addValue("Money",
						-Run.instance.frame.game.varmanager.getValue("EggTowerPrice"));
				Run.instance.frame.game.varmanager.addValue("EggTowerPrice", 100);
			}
			if (clickedX >= 175 && clickedX <= 325 && clickedY >= 850 && clickedY <= 1000
					&& Run.instance.frame.game.varmanager.getValue("Money") >= Run.instance.frame.game.varmanager
							.getValue("ArrowTowerPrice")) {
				selected.setSprite(ImageLoader.getImage("ArrowTower"));
				Run.instance.frame.game.towers.add(new ArrowTower(selected));
				Run.instance.frame.game.varmanager.addValue("Money",
						-Run.instance.frame.game.varmanager.getValue("ArrowTowerPrice"));
				Run.instance.frame.game.varmanager.addValue("ArrowTowerPrice", 100);
			}
		} else if (sprite.equals(ImageLoader.getImage("HoveredPath"))) {
			if (clickedX >= 175 && clickedX <= 325 && clickedY >= 850 && clickedY <= 1000
					&& Run.instance.frame.game.varmanager.getValue("Money") >= Run.instance.frame.game.varmanager
							.getValue("CactusTrapPrice")) {

				Run.instance.frame.game.traps.add(new CactusTrap(selected, ImageLoader.getImage("Cactus")));
				Run.instance.frame.game.varmanager.addValue("Money",
						-Run.instance.frame.game.varmanager.getValue("CactusTrapPrice"));
				Run.instance.frame.game.varmanager.addValue("CactusTrapPrice", 100);

			} else if (clickedX >= 325 && clickedX <= 475 && clickedY >= 850 && clickedY <= 1000
					&& Run.instance.frame.game.varmanager.getValue("Money") >= Run.instance.frame.game.varmanager
							.getValue("WaterTrapPrice")) {

				selected.setSprite(ImageLoader.getImage("WaterTrench").getSubimage(0, 0, 100, 100));
				Run.instance.frame.game.traps.add(new WaterTrap(selected, ImageLoader.getImage("WaterTrench")));
				Run.instance.frame.game.varmanager.addValue("Money",
						-Run.instance.frame.game.varmanager.getValue("WaterTrapPrice"));
				Run.instance.frame.game.varmanager.addValue("WaterTrapPrice", 100);
			}
		}
		close();
	}

}
