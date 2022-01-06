import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
		System.out.println(monJeu.toString());
		try {
			final FileOutputStream file = new FileOutputStream("fichier.ser");
			file.write(monJeu.toString().getBytes());
			file.flush();
			file.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		sc.close();
	}
}
