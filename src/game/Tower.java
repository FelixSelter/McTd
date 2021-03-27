package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import chickencode.ImageTools;
import helper.Updateable;
import menue.Run;

public class Tower implements Updateable {

	private Field position;
	public int shootfrequenze;
	public int reload;
	private BufferedImage projectile;
	private int projectilespeed;
	private int livetime;
	private Rectangle projectile_hitbox_north;
	private Rectangle projectile_hitbox_south;
	private Rectangle projectile_hitbox_west;
	private Rectangle projectile_hitbox_east;

	public Tower(Field position, int shootfrequence, BufferedImage projectile, int projectilespeed, int livetime,
			Rectangle projectile_hitbox_north, Rectangle projectile_hitbox_south, Rectangle projectile_hitbox_west,
			Rectangle projectile_hitbox) {

		this.position = position;
		this.shootfrequenze = shootfrequence;
		this.projectile = projectile;
		this.projectilespeed = projectilespeed;
		this.livetime = livetime;
		this.projectile_hitbox_north = projectile_hitbox_north;
		this.projectile_hitbox_south = projectile_hitbox_south;
		this.projectile_hitbox_west = projectile_hitbox_west;
		this.projectile_hitbox_east = projectile_hitbox;
	}

	@Override
	public void updateGameLoop() {

		if (reload < shootfrequenze) {
			reload++;
		} else {
			shoot();
			reload = 0;
		}

	}

	private void shoot() {

		Run.instance.frame.game.projectiles.add(new Projectile((100 * position.getColumn()), 100 * (position.getLine()),
				projectile, projectilespeed, "north", livetime, projectile_hitbox_north));

		Run.instance.frame.game.projectiles.add(new Projectile((100 * position.getColumn()), 100 * (position.getLine()),
				projectile, projectilespeed, "south", livetime, projectile_hitbox_south));

		Run.instance.frame.game.projectiles.add(new Projectile((100 * (position.getColumn())), 100 * position.getLine(),
				projectile, projectilespeed, "west", livetime, projectile_hitbox_west));

		Run.instance.frame.game.projectiles.add(new Projectile((100 * (position.getColumn())), 100 * position.getLine(),
				projectile, projectilespeed, "east", livetime, projectile_hitbox_east));
	}

}
