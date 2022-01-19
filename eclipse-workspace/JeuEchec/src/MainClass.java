import java.util.Scanner;

import utils.Jeu;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String j1, j2;
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez le nom du j1:");
		j1 = sc.next();
		System.out.println("Entrez le nombre du j2");
		j2 =sc.next();
		Jeu jeu = new Jeu(j1,j2);
		jeu.gererJeu();
		sc.close();
	}

}
