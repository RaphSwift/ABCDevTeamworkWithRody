import java.util.Random;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		final int MIN_CRAYON = 10;
		final int MAX_CRAYON = 35;
		
		Scanner sc = new Scanner(System.in);
		String[] joueurs = new String[2];
		
		boolean playAgainstIA = false;
		
		joueurs[0] = getName(sc);
		joueurs[1] = getName(sc);
		
		String saisie;
		do {
			System.out.println(joueurs[1] + " est il une IA? (oui/non)");
			saisie = sc.nextLine().trim().toLowerCase();
			if (saisie.equals("oui"))
				playAgainstIA= true;
		} while (!saisie.equals("oui") && !saisie.equals("non"));
		int nbCrayons = genererCrayon(MIN_CRAYON, MAX_CRAYON);
		gererJeu(joueurs,nbCrayons, sc, playAgainstIA);
		sc.close();
	}
	
	public static String getName(Scanner sc) {
		System.out.println("Veuillez entrez le nom du joueur");
		String tmp =  sc.nextLine();
		System.out.println(tmp + " à été ajouté avec succès! ");
		return tmp;
	}
	
	public static int genererCrayon(int min, int max) {
		Random rnd = new Random();
		return rnd.nextInt((max+1)-min)+min;
	}
	
	public static void gererJeu(String[] joueurs, int nbCrayons, Scanner sc, boolean isIA) {
		int joueurActuel = 0;
		int crayonsRetires = 0;
		do {
			if (joueurActuel == 0 || (joueurActuel == 1 && !isIA)) {
				System.out.println(joueurs[joueurActuel] + ", veuillez saisir un nombre de crayons a retires entre 1 et 3 compris (il en reste " + nbCrayons + ")");
				do {
					try {
						crayonsRetires = Integer.valueOf(sc.nextLine());
						if (crayonsRetires < 1 || crayonsRetires > 3)
							System.out.println("Saisie incorrecte");
					} catch (NumberFormatException e) {
						
					}
				} while (crayonsRetires < 1 || crayonsRetires >3);
				nbCrayons-=crayonsRetires;
			} else {
				 
				crayonsRetires=IAPlay(nbCrayons);
				nbCrayons-=crayonsRetires;
				System.out.println(joueurs[joueurActuel]+ " retire " + crayonsRetires + " crayons");
			}
			
			joueurActuel = (joueurActuel+1)%2;
		} while (nbCrayons > 0);
		System.out.println(joueurs[joueurActuel] + " a gagné!");
	}
	
	public static int IAPlay(int n) {
		int nbc = 1;
		if ((n-2)%4 == 1) {
			nbc = 2;
		} else if ((n-3)%4 == 1) {
			nbc=3;
		}
		return nbc;
	}
}
