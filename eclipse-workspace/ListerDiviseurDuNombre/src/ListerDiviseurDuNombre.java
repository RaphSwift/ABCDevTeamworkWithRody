import java.util.Scanner;

public class ListerDiviseurDuNombre {
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
			for (nombreTeste=2; nombreTeste<nombreRecherche;nombreTeste++) {
				if (nombreRecherche % nombreTeste == 0)
					System.out.println(nombreTeste + " est diviseur de " + nombreRecherche);
			}
		}
		sc.close();
	}
}
