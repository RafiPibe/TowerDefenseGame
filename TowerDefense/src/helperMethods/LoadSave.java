package helperMethods;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import handlers.UtilitiesHandler;


public class LoadSave {
	public static BufferedImage getSprite() {
		
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("sprite.png");
		
		// try to import the image
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			//if it goes wrong then it will print an error message
			e.printStackTrace();
		}
		return img;
	}
	
	//create world
	public static void CreateFile() {
		File txtFile = new File("resources/testTextFile.txt");
		
		try {
			txtFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void CreateWorld(String name, int[] ArrId) {
		File newWorld = new File("resources/" + name + ".txt");
		
		if(newWorld.exists()) {
			System.out.println("File " + name + " already exist");
			return;
		} else {
			try {
				newWorld.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			WriteToFile(newWorld, ArrId);
			
		}
	}
	
	public static void WriteToFile(File f, int[] ArrId) {
		try {
			PrintWriter pw = new PrintWriter(f);
			for (Integer i : ArrId) pw.println(i);
			
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void SaveWorld(String name, int[][] ArrId) {
		File worldFile = new File("resources/" + name + ".txt");
		
		if(worldFile.exists()) {
			WriteToFile(worldFile, UtilitiesHandler.twoDto1DintArray(ArrId));
		} else {
			System.out.println("File " + name + "does not exist");
			return;
		}
	}
	
	
	private static ArrayList<Integer> ReadFromFile(File file) {
		ArrayList<Integer> list = new ArrayList<>();
		try {
			Scanner sc = new Scanner(file);
			
			while(sc.hasNextLine()) {
				list.add(Integer.parseInt(sc.nextLine()));
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static int[][] GetWorldData(String name) {
		File worldFile = new File("resources/" + name + ".txt");
		
		if(worldFile.exists()) {
			ArrayList<Integer> list = ReadFromFile(worldFile);
			return UtilitiesHandler.ArrayListTo2Dint(list, 20, 20);
		} else {
			System.out.println("File " + name + "does not exists.");
			return null;
		}
	}
}
