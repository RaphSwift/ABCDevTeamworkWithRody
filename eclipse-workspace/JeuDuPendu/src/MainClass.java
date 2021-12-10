
import java.io.IOException;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] chaineAffiche;
		String motATrouve;
		int iterateurRecherche;
		int nbEssais=0;
		char caractereRecherche;
		boolean caractereTrouve;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("Entrez le mot a trouvé (5 lettre min)");
			motATrouve = sc.next().toUpperCase();
		} while (motATrouve.length() < 5);
		// EFFACER CONSOLE
		//cls();
		for (iterateurRecherche = 0; iterateurRecherche < 20; iterateurRecherche++) {
			System.out.println();
		}

		//---
		chaineAffiche = motATrouve.toCharArray();
		
		for (iterateurRecherche = 1; iterateurRecherche < motATrouve.length()-1; iterateurRecherche++) {
			chaineAffiche[iterateurRecherche] = '-';
		}
		do {
			caractereTrouve = false;
			System.out.println("Entrez le caractere a chercher dans : " + String.valueOf(chaineAffiche));
			caractereRecherche = sc.next().toUpperCase().charAt(0);
			for (iterateurRecherche = 1; iterateurRecherche < motATrouve.length()-1;iterateurRecherche++) {
				if (motATrouve.charAt(iterateurRecherche)==caractereRecherche) {
					chaineAffiche[iterateurRecherche]=caractereRecherche;
					caractereTrouve=true;
				}
			}
			if (!caractereTrouve) {
				nbEssais++;
				System.out.println("'"+caractereRecherche+"' n'est pas dans le mot il vous reste " + (6-nbEssais) + " tentatives");
			}
		} while (!String.valueOf(chaineAffiche).equals(motATrouve) && nbEssais != 6);
		if (String.valueOf(chaineAffiche).equals(motATrouve.toUpperCase())) {
			System.out.println("BRAVO TU AS TROUVE LE MOT " + motATrouve);
		} else {
			System.out.println("DOMMAGE LE MOT ETAIT " + motATrouve);
		}
		System.out.println(String.valueOf(chaineAffiche));
		sc.close();
	}
	
	/*public static void cls() {
		try {
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		} catch (Exception e) {
			System.out.println(e);
		}
	}*/

}
