import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String joueur1, joueur2;
		System.out.println("Entrez le nom du joueur 1");
		joueur1 = sc.next();
		System.out.println("Entrez le nom du joueur 2");
		joueur2 = sc.next();
		Jeu monJeu = new Jeu("puissance 4",joueur1,joueur2);
		monJeu.gererPlateau();
		sc.close();
	}
}
