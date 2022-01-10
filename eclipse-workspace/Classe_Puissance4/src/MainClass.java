import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String joueur1, joueur2;
		String saisieTmp=null;
		Jeu monJeu = null;
		boolean load = false;
		File f1 = new File("fichier.ser");
		if (f1.exists() && !f1.isDirectory()) {
			// UN FICHIER EXISTE
			do {
				System.out.println("Reprendre la partie?");
				saisieTmp = sc.next().toUpperCase();
				if (!saisieTmp.equals("OUI") && !saisieTmp.equals("NON"))
					System.out.println("Veuillez rentrer oui ou non");
			} while (!saisieTmp.equals("OUI") && !saisieTmp.equals("NON"));
			if (saisieTmp.equals("OUI")) {
				try {
					FileInputStream fIn = new FileInputStream("fichier.ser");
					ObjectInputStream in = new ObjectInputStream(fIn);
					monJeu = (Jeu) in.readObject();
					load = true;
					in.close();
					fIn.close();
					
				} catch (IOException e) {
					System.out.println("failed to load");
					System.out.println(e.getMessage());
					// IGNORE
				} catch (ClassNotFoundException c) {
					System.out.println("failed to load");
					System.out.println(c.getMessage());
					// IGNORE
				}
			}
		}
		if (!load) {
			
			System.out.println("Entrez le nom du joueur 1");
			joueur1 = sc.next();
			System.out.println("Entrez le nom du joueur 2");
			joueur2 = sc.next();
			String saisie;
			do {
				System.out.println("Le joueur " + joueur2 + " est il un ordinateur?(oui ou non)");
				saisie = sc.next().toUpperCase();
				if (!saisie.equals("OUI") && !saisie.equals("NON"))
					System.out.println("Veuillez rentrer oui ou non");
			} while (!saisie.equals("OUI") && !saisie.equals("NON"));
			if (saisie.equals("OUI")) {
				monJeu = new Jeu("puissance 4",new Joueur(joueur1, Winner.RED_WINNER.getLetter()),new Joueur_Ordi(joueur2,Winner.YELLOW_WINNER.getLetter()));
			} else {
				monJeu = new Jeu("puissance 4",new Joueur(joueur1, Winner.RED_WINNER.getLetter()),new Joueur(joueur2,Winner.YELLOW_WINNER.getLetter()));
			}
				
		}
		monJeu.gererPlateau();
		monJeu.serialize();
		
		
		sc.close();
	}
}
