import java.util.Scanner;

public class CalculerNombreParfaitV2 {
	public static void main(String[] args) {
		// DECLARATION
		char veutContinuer;
		int nbDeNbParfaits;
		int sommeDiviseur;		
		int nbATester;
		int nbTesterCourant;
		int nbDeNbParfaitCourant;
		
		Scanner sc = new Scanner(System.in);
		//BOUCLE PRINCIPALE
		do {
			System.out.println("Combien de nombre parfait voulez vous?");
			
			nbDeNbParfaits = sc.nextInt();	
			nbATester = 0;
			nbDeNbParfaitCourant = 0;
			while(nbDeNbParfaits != nbDeNbParfaitCourant) {
				sommeDiviseur = 0;
				nbTesterCourant = 0;
				nbATester++;
				for (nbTesterCourant = 1; nbTesterCourant < nbATester; nbTesterCourant++) {
					if (nbATester % nbTesterCourant == 0)
						sommeDiviseur+= nbTesterCourant;
				}
				if (sommeDiviseur == nbATester) {
					System.out.println(nbATester + " est un nombre Parfait");
					nbDeNbParfaitCourant++;
				}
				//System.out.println("test pour " + nbATester + " n'est pas parfait");
			}
			// L'UTILISATEUR VEUT IL QUITTER
			System.out.println("voulez vous continuer? (o/n)");
			veutContinuer = sc.next().charAt(0);
		} while (veutContinuer == 'o');
		
		sc.close();
	}
}
