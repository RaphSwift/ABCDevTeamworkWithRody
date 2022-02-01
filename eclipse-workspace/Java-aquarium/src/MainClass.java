import java.util.Scanner;

import utils.Aquarium;

public class MainClass {
	public static void main(String[] args) {
		Aquarium aqua = new Aquarium();
		aqua.AfficherContenu();
		Scanner sc = new Scanner(System.in);
		int tour = sc.nextInt();
		aqua.jouer(tour);
		sc.close();
	}
}
