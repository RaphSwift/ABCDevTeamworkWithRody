import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		final String PASSWORD = "Bonjour";
		final String SECRET_ANSWER = "formation";
		final String SECRET_SENTENCE = "Que faites vous?";
		
		Scanner sc = new Scanner(System.in);
		String saisie;
		int nbEssais = 1;
		do {
			System.out.println("Veuillez entrez votre mot de passe" + 
		(nbEssais > 1 ?  "(" + nbEssais + "ème essai)" : ""));
			saisie = sc.nextLine();
			nbEssais++;
		} while (nbEssais <= 3 && !saisie.equals(PASSWORD));
		
		if (!saisie.equals(PASSWORD)) {
			System.out.println("Echec de l'authentification");
			System.out.println("Répondez à la question secrete: \""  + SECRET_SENTENCE+ "\"");
			saisie = sc.nextLine();
			System.out.println((saisie.equals(SECRET_ANSWER) ? "Vous etes connecté" : "Compte bloqué"));
		} else {
			System.out.println("Vous êtes connecté");
		}
		sc.close();
	}
}
