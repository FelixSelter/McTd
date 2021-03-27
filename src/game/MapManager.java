package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import chickencode.EncriptedProperties;
import chickencode.ImageLoader;
import menue.Run;

public class MapManager {

	private EncriptedProperties savedmap = new EncriptedProperties();

	public MapManager() {


		try {
			savedmap.loadDecripted(new FileInputStream(new File("rsc/saves/map.map")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Field[][] loadMap() {

		HashMap<Field, String[]> nextpaths = new HashMap<Field, String[]>();
		Field[][] map = new Field[10][14];

		for (int line = 0; line < map.length; line++) {
			for (int column = 0; column < map[0].length; column++) {
				map[line][column] = new Field(line, column);
				if (!savedmap.getProperty(map[line][column].getLine() + "/" + map[line][column].getColumn())
						.contains(";")) {
					map[line][column].setSprite(ImageLoader.getImage(
							savedmap.getProperty(map[line][column].getLine() + "/" + map[line][column].getColumn())));
				} else {
					String[] content = savedmap
							.getProperty(map[line][column].getLine() + "/" + map[line][column].getColumn()).split(";");
					map[line][column].setSprite(ImageLoader.getImage(content[0]));
					nextpaths.put(map[line][column], content[1].split("/"));
				}
			}
		}

		for (Entry<Field, String[]> nextpath : nextpaths.entrySet()) {
			nextpath.getKey().setNextfield(
					map[Integer.valueOf(nextpath.getValue()[0])][Integer.valueOf(nextpath.getValue()[1])]);
		}

		for (Entry<Object, Object> toload : savedmap.entrySet()) {
					if(String.valueOf(toload.getKey()).startsWith("$var") ){
						
						String name = String.valueOf(toload.getKey()).replace("$var", "");
						int value = Integer.valueOf(String.valueOf(toload.getValue()));
						Run.instance.frame.game.varmanager.setVar(name, value);
						
					}
		}
		
		
		
		return map;
	}

	public void saveMap(Field[][] map) {

		for (Entry<String, Integer> tosave : Run.instance.frame.game.varmanager.getData().entrySet()) {
			
			
			String name = "$var" + tosave.getKey();
			int value = tosave.getValue();

			savedmap.setProperty(name, Integer.toString(value));
		}

		for (int line = 0; line < map.length; line++) {
			for (int column = 0; column < map[0].length; column++) {
				if (map[line][column].getNextfield() != null) {
					savedmap.setProperty(map[line][column].getLine() + "/" + map[line][column].getColumn(),
							ImageLoader.getNameFromImage(map[line][column].getSprite()) + ";"
									+ map[line][column].getNextfield().getLine() + "/"
									+ map[line][column].getNextfield().getColumn());
				} else {
					savedmap.setProperty(map[line][column].getLine() + "/" + map[line][column].getColumn(),
							ImageLoader.getNameFromImage(map[line][column].getSprite()));
				}
			}
		}

		try {
			savedmap.storeEncripted(new FileOutputStream(new File("rsc/saves/map.map")), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
