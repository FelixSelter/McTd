package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

import chickencode.ImageTools;
import helper.Updateable;
import menue.Run;

public class Enemy implements Updateable {

	private int speed;
	private int live;
	private BufferedImage sprite;
	private int x = 0;
	private int y = 0;
	public Field field;
	private int spritecounter = 0;
	private int tilecount = 11;
	private int tilewidth = 21;
	private int tileheight = 16;
	private int offsetY = 58;
	private int animation_speed_subtractor = 5;
	private int animation_update_counter = 0;
	private boolean freezed = false;
	int freezedspeed;
	private Rectangle hitbox_north;
	private Rectangle hitbox_south;
	private Rectangle hitbox_west;
	private Rectangle hitbox_east;
	private Rectangle returnable_hitbox;

	public Enemy(int speed, int freezedspeed, int live, BufferedImage sprite, Field field, Rectangle hitbox_north,
			Rectangle hitbox_south, Rectangle hitbox_west, Rectangle hitbox_east) {

		if (100 % speed != 0)
			throw new RuntimeException("100 has to be divided by speed without becoming a decimal number");

		if (100 % freezedspeed != 0)
			throw new RuntimeException("100 has to be divided by freezedspeed without becoming a decimal number");

		this.speed = speed;
		this.freezedspeed = freezedspeed;
		this.live = live;
		this.sprite = sprite;
		this.field = field;
		this.hitbox_north = hitbox_north;
		this.hitbox_south = hitbox_south;
		this.hitbox_west = hitbox_west;
		this.hitbox_east = hitbox_east;

	}

	public Rectangle getHitbox() {
		return returnable_hitbox;
	}

	public void setFreezed(boolean freezed) {
		this.freezed = freezed;
	}

	public boolean isFreezed() {
		return freezed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int live) {
		this.live = live;
	}

	public void attack(int damage) {
		live -= damage;
	}

	@Override
	public void updateGameLoop() {

		if (live < 1) {
			Run.instance.frame.game.enemys.remove(this);
		}

		animation_update_counter++;

		if (animation_speed_subtractor - animation_update_counter < 1) {
			animation_update_counter = 0;
			spritecounter++;

			if (spritecounter == tilecount)
				spritecounter = 0;
		}

		int currentspeed = speed;
		if (freezed) {
			currentspeed = freezedspeed;
		}

		String movementdirection = getMovementDirection();

		if (movementdirection != null) {
			int toline = field.getNextfield().getLine();
			int tocolumn = field.getNextfield().getColumn();
			switch (getMovementDirection()) {

			case "north":
				if (y - currentspeed < toline * 100) {
					y = toline * 100;
				} else {
					y -= currentspeed;
				}
				break;

			case "south":
				if (y + currentspeed > toline * 100) {
					y = toline * 100;
				} else {
					y += currentspeed;
				}
				break;

			case "east":
				if (x + currentspeed > tocolumn * 100) {
					x = tocolumn * 100;
				} else {
					x += currentspeed;
				}
				break;

			case "west":
				if (x - currentspeed < tocolumn * 100) {
					x = tocolumn * 100;
				} else {
					x -= currentspeed;
				}
				break;

			default:
				break;
			}
		} else {
			Run.instance.frame.game.enemys.remove(this);
			Run.instance.frame.game.varmanager.addValue("Live", -1);
			;
			if (Run.instance.frame.game.varmanager.getValue("Live") < 1) {
				Run.instance.frame.game.lose();
			}
		}
		freezed = false;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setField(Field field) {
		this.field = field;
	}

	private String getMovementDirection() {

		if (field.getNextfield() != null) {

			int toline = field.getNextfield().getLine();
			int tocolumn = field.getNextfield().getColumn();

			if (x < tocolumn * 100) {

				returnable_hitbox = hitbox_east;
				return "east";

			} else if (x > tocolumn * 100) {

				returnable_hitbox = hitbox_west;
				return "west";

			} else if (y < toline * 100) {

				returnable_hitbox = hitbox_south;
				return "south";

			} else if (y > toline * 100) {

				returnable_hitbox = hitbox_north;
				return "north";

			} else {

				field = Run.instance.frame.game.fields[toline][tocolumn];
				return getMovementDirection();

			}
		} else
			return null;
	}

	public BufferedImage getImage() {

		BufferedImage sprite_tile = sprite.getSubimage(spritecounter + (spritecounter * tilewidth), offsetY, tilewidth,
				tileheight);

		String movementdirection = getMovementDirection();

		if (movementdirection != null) {
			switch (getMovementDirection()) {

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

	public void setSprite(BufferedImage instance) {
		this.sprite = instance;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
