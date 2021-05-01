package com.vrp.app.wrapper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import com.vrp.app.components.Node;

public class Utils {
	
	private static Random random = new Random();
	
	public static String generateId() {
		return String.valueOf(Math.abs(random.nextInt())).substring(0, 6);
	}
	
	public static float distanceBetween(Location start, Location end) {
		float dX = end.getPosX() - start.getPosX();
		float dY = end.getPosY() - start.getPosY();
		return (float) Math.sqrt(dX * dX + dY * dY);
	}
	
	public static float distanceBetween(Node start, Node end) {
		float dX = end.getX() - start.getX();
		float dY = end.getY() - start.getY();
		return (float) Math.sqrt(dX * dX + dY * dY);
	}

	public static boolean checkArrayBounds(int i, int upperBound) {
		if(i < 0 || i >= upperBound) {
			throw new ArrayIndexOutOfBoundsException(
					String.format("Index %d out of bounds for [0,%d]", i, upperBound)
			);
		}
		return true;
	}
	
	public static void writeToFile(String filename, String content) {
		try {
			System.out.println("Writing to file: " + filename);
		    FileWriter fileWriter = new FileWriter(filename);
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    printWriter.print(content);
		    printWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
