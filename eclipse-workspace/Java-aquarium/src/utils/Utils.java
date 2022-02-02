package utils;

import java.util.Random;

public class Utils {
	public static short random(int i, int j) {
		Random rnd = new Random();
		return (short)(Math.random()*(j-i)+i);
	}
	
	public static String generateName(byte sylabe) {
		String str = "";
		String consonnesPossibles[] = {"B","C","D","F","J","L","M","N","P","R","T","V"};
		String complementsPossibles[] = {"a","e","é","i","o","u","ou"};
		int rnd1, rnd2;
		for (int i = 0; i < sylabe; i++) {
			rnd1 = random(0,consonnesPossibles.length);
			rnd2 = random(0,complementsPossibles.length);
			str+= consonnesPossibles[rnd1] + complementsPossibles[rnd2];
		}
		return str.charAt(0) + str.toLowerCase().substring(1,str.length());
	}
}
