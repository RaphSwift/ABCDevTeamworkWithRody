import java.util.Scanner;

public class ListerDiviseurDuNombre {
	// JEU D'ESSAI : 0,1,2,5,42
	public static void main(String[] args) {
		int nombreRecherche;
		int nombreTeste;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Bonjour, ce programme va lister tout les diviserur d'un nombre"
				+ "\nsauf 1 et lui même"
				+ "\nVeuillez entrez le nombre a tester");
		nombreRecherche = sc.nextInt();
		
		if (nombreRecherche <= 2) {
			System.out.println("Le nombre doit etre plus grand que 2");
		}else {
			System.out.println("La liste des diviseurs:");
			listerDiviseur(nombreRecherche);
		}
		sc.close();
	}
	
	public static void listerDiviseur(int nb) {
		int compteurDiviseur=0;
		for (int nombreTeste=2; nombreTeste<nb;nombreTeste++) {
			if (nb % nombreTeste == 0) {
				System.out.println(nombreTeste + " est diviseur de " + nb);
				compteurDiviseur++;
			}
		}
		if (compteurDiviseur == 0) {
			System.out.println("Pas de diviseurs trouvés le nombre est premier");
		}
	}
}
