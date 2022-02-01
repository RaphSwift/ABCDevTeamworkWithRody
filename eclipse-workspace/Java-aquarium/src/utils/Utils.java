package utils;

import java.util.Random;

public class Utils {
	public static short random(int i, int j) {
		Random rnd = new Random();
		return (short)(Math.random()*(j-i)+i);
	}
}
