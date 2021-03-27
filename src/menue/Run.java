package menue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import chickencode.ImageLoader;
import chickencode.LogManager;

public class Run {

	public static Run instance;
	public LogManager logmanager=new LogManager();

	public File file_eggprojectile;
	public File file_hoveredgrass;
	public File file_path;
	public File file_menue;
	public File file_button;
	public File file_hoveredbutton;
	public File file_settings;
	public File file_language;
	public File file_buttonsound;
	public File file_banner;
	public File file_grass;
	public File file_shovel;
	public File file_zombie;
	public File file_skelett;
	public File file_inventory;
	public File file_creeper;
	public File file_endermann;
	public File file_ghast;
	public File file_schleim;
	public File file_silberfisch;
	public File file_spinne;
	public File file_eggtower;
	public File file_dirt;
	public File file_goal;
	public File file_money;
	public File file_living_enemys;
	public File file_unfinished_wave;
	public File file_wave;
	public File file_waveboss;
	public File file_iconbackground;
	public File file_inventory_info;
	public File file_watertrench;
	public File file_cactus;
	public File file_heart;
	public File file_eggtower_red;
	public File file_cactus_red;
	public File file_watertrench_red;



	public Properties propertie_settings;
	public Properties propertie_language;

	public WavSound sound_button;
	public WavSound music_background;

	public Frame frame;

	public static void main(String[] args) {
		try {

			new Run();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Run() throws IOException {

		instance = this;
		new ImageLoader(logmanager);

		propertie_settings = new Properties();
		propertie_language = new Properties();

		ImageLoader.addImage("EggProjectile", new File("rsc/Images/Projectile/Egg.png"));
		ImageLoader.addImage("Dirt", new File("rsc/Images/Dirt.png"));
		ImageLoader.addImage("EggTower", new File("rsc/Images/Tower/EggTower.png"));
		ImageLoader.addImage("Inventory", new File("rsc/Images/Inventory.png"));
		ImageLoader.addImage("Spinne", new File("rsc/Images/Enemys/Spinne.png"));
		ImageLoader.addImage("Schleim", new File("rsc/Images/Enemys/Schleim.png"));
		ImageLoader.addImage("Endermann", new File("rsc/Images/Enemys/Endermann.png"));
		ImageLoader.addImage("Ghast", new File("rsc/Images/Enemys/Ghast.png"));
		ImageLoader.addImage("Silberfisch", new File("rsc/Images/Enemys/Silberfisch.png"));
		ImageLoader.addImage("Creeper", new File("rsc/Images/Enemys/Creeper.png"));
		ImageLoader.addImage("Skelett", new File("rsc/Images/Enemys/Skelett.png"));
		ImageLoader.addImage("Zombie", new File("rsc/Images/Enemys/Zombie.png"));
		ImageLoader.addImage("Shovel", new File("rsc/Images/Shovel.png"));
		ImageLoader.addImage("HoveredGrass", new File("rsc/Images/HoveredGrass.png"));
		ImageLoader.addImage("Path", new File("rsc/Images/Path.png"));
		ImageLoader.addImage("Grass", new File("rsc/Images/Grass.png"));
		ImageLoader.addImage("Banner", new File("rsc/Images/Banner.png"));
		ImageLoader.addImage("Menue", new File("rsc/Images/Menue.png"));
		ImageLoader.addImage("Button", new File("rsc/Images/Button.png"));
		ImageLoader.addImage("HoveredButton", new File("rsc/Images/HoveredButton.png"));
		file_settings = new File("rsc/Configs/Settings.xml");
		file_buttonsound = new File("rsc/Sounds/Button.wav");
		ImageLoader.addImage("Goal", new File("rsc/Images/Goal.png"));
		ImageLoader.addImage("Money", new File("rsc/Images/Icons/Money.png"));
		ImageLoader.addImage("LivingEnemys", new File("rsc/Images/Icons/LivingEnemys.png"));
		ImageLoader.addImage("UnfinishedWave", new File("rsc/Images/Icons/UnfinishedWave.png"));
		ImageLoader.addImage("Wave", new File("rsc/Images/Icons/Wave.png"));
		ImageLoader.addImage("WaveBoss", new File("rsc/Images/Icons/WaveBoss.png"));
		ImageLoader.addImage("IconBackground", new File("rsc/Images/IconBackground.png"));
		ImageLoader.addImage("InventoryInfo", new File("rsc/Images/InventoryInfo.png"));
		ImageLoader.addImage("WaterTrench", new File("rsc/Images/Traps/WaterTrench.png"));
		ImageLoader.addImage("Cactus", new File("rsc/Images/Traps/Cactus.png"));
		ImageLoader.addImage("Heart", new File("rsc/Images/Icons/Heart.png"));
		ImageLoader.addImage("EggTowerRed", new File("rsc/Images/Tower/Eggtower_red.png"));
		ImageLoader.addImage("CactusRed", new File("rsc/Images/Traps/Cactus_red.png"));
		ImageLoader.addImage("WaterTrenchRed", new File("rsc/Images/Traps/WaterTrench_red.png"));
		ImageLoader.addImage("HoveredPath", new File("rsc/Images/HoveredPath.png"));
		ImageLoader.addImage("ArrowProjectile", new File("rsc/Images/Projectile/Arrow.png"));
		ImageLoader.addImage("ArrowTower", new File("rsc/Images/Tower/ArrowThrower.png"));
		ImageLoader.addImage("ArrowTowerRed", new File("rsc/Images/Tower/ArrowThrower_red.png"));
		
		propertie_settings.loadFromXML(new FileInputStream(file_settings));
		file_language = new File("rsc/Languages/" + propertie_settings.getProperty("Language:"));
		propertie_language.loadFromXML(new FileInputStream(file_language));

		sound_button = new WavSound(file_buttonsound);
		music_background = new WavSound(new File(propertie_settings.getProperty("Backgroundmusic:")));
		music_background.loop();

		frame = new Frame();

	}

	public void exit() {

		try {

			propertie_settings.storeToXML(new FileOutputStream(file_settings), "");
//			mapmanager.saveMap(frame.cardpanel.game.fields);
			Thread.sleep(500);
			System.exit(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
