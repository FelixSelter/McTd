package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import chickencode.ImageLoader;
import chickencode.ResolutionPanel;
import helper.Updateable;
import listener.ListenerGame;
import menue.Frame;
import waves.BasicEnemyWave;
import waves.CreeperWave;
import waves.EndermannWave;
import waves.GhastWave;
import waves.SchleimWave;
import waves.SilberfischWave;
import waves.SpinneWave;

public class Game extends ResolutionPanel implements Updateable {

	public MapManager mapmanager = new MapManager();
	public Inventory inv = new Inventory();
	public Field[][] fields = new Field[10][14];
	public ListenerGame listenergame;
	public ArrayList<Enemy> enemys = new ArrayList<Enemy>();
	public ArrayList<Tower> towers = new ArrayList<Tower>();
	public ArrayList<Trap> traps = new ArrayList<Trap>();
	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public boolean hitboxes = false;
	public VarManager varmanager = new VarManager();
	public WaveManager wavemanager = new WaveManager();

	public Game(Frame frame) {
		super("GAME", 1400, 1000);

		listenergame = new ListenerGame();
		frame.panel.registerMouseListener("GAME", listenergame);
		frame.panel.registerMouseMotionListener("GAME", listenergame);

		for (int line = 0; line < fields.length; line++) {
			for (int column = 0; column < fields[0].length; column++) {

				fields[line][column] = new Field(line, column);

			}
		}
		fields[0][0].setSprite(ImageLoader.getImage("Path"));
		fields[9][13].setSprite(ImageLoader.getImage("Goal"));
		listenergame.lastclick = fields[0][0];

	}

	@Override
	protected void paintComponent(Graphics g) {

		ArrayList<Field> draw_later = new ArrayList<Field>();

		for (int line = 0; line < fields.length; line++) {
			for (int column = 0; column < fields[0].length; column++) {
				BufferedImage sprite = fields[line][column].getSprite();
				if (sprite.equals(ImageLoader.getImage("EggTower"))) {
					draw_later.add(fields[line][column]);

				} else if (sprite.equals(ImageLoader.getImage("ArrowTower"))) {
					draw_later.add(fields[line][column]);

				} else {
					g.drawImage(sprite, column * 100, line * 100, 100 + 1, 100 + 1, null);
				}
			}
		}

		for (int i = 0; i < projectiles.size(); i++) {
			Projectile projectile = projectiles.get(i);
			BufferedImage img = projectile.getImage();
			g.drawImage(img, 50 + projectile.getX() - (img.getWidth() / 2),
					50 + projectile.getY() - (img.getHeight() / 2), img.getWidth(), img.getHeight(), null);

			if (hitboxes) {
				Rectangle hitbox = projectile.getHitbox();
				g.setColor(Color.green);
				g.drawRect((50 + projectile.getX() - (img.getWidth() / 2) + (int) (hitbox.getX())),
						50 + projectile.getY() - (img.getHeight() / 2) + (int) (hitbox.getY()),
						img.getWidth() - (int) (hitbox.getWidth()), img.getHeight() - (int) (hitbox.getHeight()));
			}
		}

		for (Field field : draw_later) {
			g.drawImage(field.getSprite(), field.getColumn() * 100, field.getLine() * 100, 100 + 1, 100 + 1, null);
		}

		for (int i = enemys.size() - 1; i >= 0; i--) {
			BufferedImage img = enemys.get(i).getImage();
			g.drawImage(img, 50 + enemys.get(i).getX() - (img.getWidth() * 5 / 2),
					50 + enemys.get(i).getY() - (img.getHeight() * 5 / 2), img.getWidth() * 5, img.getHeight() * 5,
					null);

			if (hitboxes) {
				Rectangle hitbox = enemys.get(i).getHitbox();
				g.setColor(Color.red);
				g.drawRect(50 + enemys.get(i).getX() - (img.getWidth() * 5 / 2) + (int) (hitbox.getX()),
						50 + enemys.get(i).getY() - (img.getHeight() * 5 / 2) + (int) (hitbox.getY()),
						img.getWidth() * 5 - (int) (hitbox.getWidth()),
						img.getHeight() * 5 - (int) (hitbox.getHeight()));
			}
		}

		if (inv.isOpened()) {
			g.drawImage(inv.getImage(), 0, 0, 1400, 1080, null);
		}

		g.setColor(Color.BLACK);
		g.setFont(new Font("", 0, 25));

		int offset = getWidth() - g.getFontMetrics().stringWidth(String.valueOf(varmanager.getValue("Money"))) - 125
				- g.getFontMetrics().stringWidth(String.valueOf(enemys.size()))
				- g.getFontMetrics().stringWidth(String.valueOf(varmanager.getValue("Wave") - 1))
				- (varmanager.getValue("Live") * 40) - 10;
		g.drawImage(ImageLoader.getImage("IconBackground"), offset, 0, 1400 - offset, 40, null);

		g.drawString(String.valueOf(varmanager.getValue("Money")),
				getWidth() - g.getFontMetrics().stringWidth(String.valueOf(varmanager.getValue("Money"))) - 10, 30);
		offset = getWidth() - g.getFontMetrics().stringWidth(String.valueOf(varmanager.getValue("Money"))) - 10;
		g.drawImage(ImageLoader.getImage("Money"), offset - 25, 7, 25, 25, null);
		offset -= 40;
		g.drawString(String.valueOf(enemys.size()),
				offset - g.getFontMetrics().stringWidth(String.valueOf(enemys.size())), 30);
		offset -= g.getFontMetrics().stringWidth(String.valueOf(enemys.size()));
		g.drawImage(ImageLoader.getImage("LivingEnemys"), offset - 25, 7, 25, 25, null);
		offset -= 40;
		g.drawString(String.valueOf(varmanager.getValue("Wave") - 1),
				offset - g.getFontMetrics().stringWidth(String.valueOf(varmanager.getValue("Wave"))), 30);
		offset -= g.getFontMetrics().stringWidth(String.valueOf(varmanager.getValue("Wave") - 1));
		if ((varmanager.getValue("Wave") - 1) != 0 && (varmanager.getValue("Wave") - 1) % 50 == 0) {
			g.drawImage(ImageLoader.getImage("Waveboss"), offset - 25, 7, 25, 25, null);
		} else if (enemys.size() > 0) {
			g.drawImage(ImageLoader.getImage("UnfinishedWave"), offset - 25, 7, 25, 25, null);
		} else {
			g.drawImage(ImageLoader.getImage("Wave"), offset - 25, 7, 25, 25, null);
		}
		for (int i = 0; i < varmanager.getValue("Live"); i++) {
			offset -= 40;
			g.drawImage(ImageLoader.getImage("Heart"), offset - 25, 7, 25, 25, null);
		}
	}

	public void lose() {

	}

	@Override
	public void updateGameLoop() {

		wavemanager.updateGameLoop();

		for (int i = 0; i < enemys.size(); i++) {
			enemys.get(i).updateGameLoop();
		}

		if (enemys.size() > 0) {
			for (Tower tower : towers) {
				tower.updateGameLoop();
			}
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).updateGameLoop();
		}

		for (int i = 0; i < traps.size(); i++) {
			traps.get(i).updateGameLoop();
		}

		if (enemys.size() == 0) {
			for (Tower tower : towers) {
				tower.reload = 0;
			}
		}

	}

}
