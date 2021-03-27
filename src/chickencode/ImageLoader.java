package chickencode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

public class ImageLoader {

	private static Logger logger;
	private static HashMap<String, BufferedImage> loadedimages = new HashMap<String, BufferedImage>();

	public static BufferedImage getImage(String name) {
		if (loadedimages.containsKey(name)) {
			return loadedimages.get(name);
		} else {
			logger.log("The programm requested an image which wasnt loaded by the ImageLoader Requested: " + name,
					LogType.Error);
			return null;
		}
	}

	public ImageLoader(LogManager manager) {
		logger = new Logger("ImageLoader", manager);
	}

	public static void addImage(String name, File file) {
		if (logger != null) {
			logger.log("Trying to load image " + file, LogType.Try);
			if (file.exists()) {
				if (name != null) {
					try {
						loadedimages.put(name, ImageIO.read(file));
						logger.log("Successfully loaded image " + name, LogType.Info);
					} catch (Exception e) {
						logger.log("An Error occured while trying to load image " + file + " Error: "
								+ StackTraceTools.toString(e), LogType.Error);
					}
				} else {
					logger.log("The name to save the file " + file + " is null", LogType.Error);
				}
			} else {
				logger.log("The file " + file + " does not exist", LogType.Error);
			}
		} else {
			System.err.println("Please call new ImageLoader(); first if you want to use a static method");
		}
	}

	public static String getNameFromImage(BufferedImage image) {
		if (image != null) {
			if (loadedimages.values().contains(image)) {
				for (Entry<String, BufferedImage> loadedimage : loadedimages.entrySet()) {
					if (loadedimage.getValue().equals(image)) {
						return loadedimage.getKey();
					}
				}
			} else {
				logger.log("The programm requested an name which wasnt loaded by the ImageLoader", LogType.Error);
			}
		} else {
			logger.log("The given image is null", LogType.Error);
		}
		return null;
	}

}
