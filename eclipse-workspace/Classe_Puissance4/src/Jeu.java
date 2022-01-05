import java.util.Scanner;

public class Jeu {
	private Joueur participants[];
	private Joueur currentPlayer;
	private final String nom;
	private Plateau plateau;
	
	public Jeu(String _nom, String _nomJoueurRouge, String _nomJoueurJaune) {
		nom = _nom;
		participants = new Joueur[2];
		participants[0] = new Joueur(_nomJoueurRouge, 'R');
		participants[1] = new Joueur(_nomJoueurJaune, 'J');
		currentPlayer = participants[0];
		plateau = new Plateau((byte)7,(byte)6);
	}
	
	public void changerJoueur() {
		if  (currentPlayer == participants[0])
			currentPlayer = participants[1];
		else
			currentPlayer = participants[0];
	}
	
	public Joueur gererPlateau() {
		Joueur winner = null;
		char win = ' ';
		Scanner sc = new Scanner(System.in);
		int column;
		boolean havePlayed = false;
		System.out.println("Vous jouez au " + nom );
		while (winner == null) {
			plateau.afficherGrille();
			do {
				do {
					System.out.println("C'est à " + currentPlayer.getNom() + " de jouer. Quel colonne(0-6)");
					column = sc.nextInt();
				} while (column <  0 || column >= 7);
				if (plateau.ajouterCoup((byte)column,currentPlayer)) {
					havePlayed = true;
				}
			} while (!havePlayed);
			win = plateau.getWin();
			if (win != ' ') {
				if (win == participants[0].getCouleur()) {
					winner = participants[0];
				} else {
					winner = participants[1];
				}
				plateau.afficherGrille();
				System.out.println(winner.getNom() + " a gagné!");
				
			} else {
				changerJoueur();
				havePlayed = false;
			}			
		}
		sc.close();
		return winner;
	}
}
