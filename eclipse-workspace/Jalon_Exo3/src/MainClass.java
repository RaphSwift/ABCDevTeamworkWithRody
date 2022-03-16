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
		(nbEssais > 1 ?  "(" + nbEssais + "�me essai)" : ""));
			saisie = sc.nextLine();
			nbEssais++;
		} while (nbEssais <= 3 && !saisie.equals(PASSWORD));
		
		if (!saisie.equals(PASSWORD)) {
			System.out.println("Echec de l'authentification");
			System.out.println("R�pondez � la question secrete: \""  + SECRET_SENTENCE+ "\"");
			saisie = sc.nextLine();
			System.out.println((saisie.equals(SECRET_ANSWER) ? "Vous etes connect�" : "Compte bloqu�"));
		} else {
			System.out.println("Vous �tes connect�");
		}
		sc.close();
	}
}
