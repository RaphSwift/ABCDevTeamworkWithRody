package testConversion;

import java.util.Scanner;

public class testConversion {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String fc;
		String fc2;
		String x;

		fc = "17.4.5% est d 15.5%";
		String split;
		split= "%";
		String words[] = fc.split(split);
		int i;
		for (i = 0; i < words.length; i++) {
			System.out.println(i+": "+words[i]);
		}
	}
}
