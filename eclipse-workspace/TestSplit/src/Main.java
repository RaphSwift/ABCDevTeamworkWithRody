import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String[][] saisies = new String[100][2];
		String saisieUtilisateur;
		Scanner sc = new Scanner(System.in);
		int i = 0;
		do {
			saisieUtilisateur = sc.nextLine().trim();
			String[] split = saisieUtilisateur.split(" ");
			if (split.length == 2) {
				saisies[i] = split;
				i++;
			}
		} while (!saisieUtilisateur.equals("go"));
		sc.close();
		
	}
}
