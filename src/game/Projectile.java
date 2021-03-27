package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import chickencode.ImageTools;
import helper.Updateable;
import menue.Run;

public class Projectile implements Updateable {

	private int x;
	private int y;
	private BufferedImage sprite;
	private int speed;
	private String direction;
	private int livetime;
	private int spritecounter = 0;
	private int tilecount = 4;
	private int tilewidth = 37;
	private int tileheight = 24;
	private int offsetY = 40;
	private int animation_speed_subtractor = 5;
	private int animation_update_counter = 0;
	private Rectangle hitbox;

	public Projectile(int x, int y, BufferedImage instance, int speed, String direction, int livetime,
			Rectangle hitbox) {
		this.x = x;
		this.y = y;
		this.sprite = instance;
		this.speed = speed;
		this.direction = direction;
		this.livetime = livetime;
		this.hitbox = hitbox;
	}

	@Override
	public void updateGameLoop() {

		animation_update_counter++;

		if (animation_speed_subtractor - animation_update_counter < 1) {
			animation_update_counter = 0;
			spritecounter++;

			if (spritecounter == tilecount)
				spritecounter = 0;
		}

		livetime--;
		if (livetime < 1) {
			Run.instance.frame.game.projectiles.remove(this);
		}

		switch (direction) {
		case "north":
			y -= speed;
			break;
		case "south":
			y += speed;
			break;
		case "west":
			x -= speed;
			break;
		case "east":
			x += speed;
			break;

		default:
			System.out.println("Projectile direction is wrong");
			System.exit(0);
		}

		for (int i = Run.instance.frame.game.enemys.size() - 1; i >= 0; i--) {

			int projectileX = 50 + getX() - (sprite.getWidth() / 2);
			int projectileY = 50 + getY() - (sprite.getHeight() / 2);
			int projectileWidth = sprite.getWidth();
			int projectileHeight = sprite.getHeight();

			Enemy enemy = Run.instance.frame.game.enemys.get(i);
			int enemyX = 50 + enemy.getX() - (enemy.getImage().getWidth() * 5 / 2);
			int enemyY = 50 + enemy.getY() - (enemy.getImage().getHeight() * 5 / 2);
			int enemyWidth = enemy.getImage().getWidth() * 5;
			int enemyHeight = enemy.getImage().getHeight() * 5;

		}

	}

	public BufferedImage getImage() {

		BufferedImage sprite_tile = sprite.getSubimage(spritecounter + (spritecounter * tilewidth), offsetY, tilewidth,
				tileheight);

		String movementdirection = direction;

		if (movementdirection != null) {
			switch (direction) {

			case "north":
				sprite_tile = ImageTools.rotateBufferedImage(sprite_tile, -90);
				break;

			case "south":
				sprite_tile = ImageTools.rotateBufferedImage(sprite_tile, 90);
				break;

			case "east":
				break;

			case "west":
				sprite_tile = ImageTools.rotateBufferedImage(sprite_tile, 180);
				break;

			default:
				break;
			}
		}
		return sprite_tile;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
