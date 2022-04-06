package data;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticData {
	private static String rootDir;
	private static String imagesDir;

	public static void setup() {
		rootDir = getRootDirFromTheMachine();
		imagesDir = rootDir + "images/";		
	}
	
	private static String getRootDirFromTheMachine() {
		Path resourceDirectory = Paths.get("src");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		absolutePath = absolutePath.replace("\\", "/");
		
		if (!absolutePath.endsWith("/")) {
			absolutePath = absolutePath + "/";
		}
		
		return absolutePath;
	}

	public static String getRootDir() {
		return rootDir;
	}
	
	public static String getImagesDir() {
		return imagesDir;
	}
	
	public static String getImageFullname(String imageName){
		return "file:///" + StaticData.getImagesDir() + imageName;
	}
}
