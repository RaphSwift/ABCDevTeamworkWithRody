import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Jeu implements java.io.Serializable{
	private Joueur participants[];
	private Joueur currentPlayer;
	private final String nom;
	private Plateau plateauActuel;
	private ArrayList<Plateau> parties;
	private ArrayList<Joueur> winner;

	
	public Jeu(String _nom, String _nomJoueurRouge, String _nomJoueurJaune) {
		nom = _nom;
		parties = new ArrayList<Plateau>();
		participants = new Joueur[2];
		participants[0] = new Joueur(_nomJoueurRouge, Winner.RED_WINNER.getLetter());
		participants[1] = new Joueur(_nomJoueurJaune, Winner.YELLOW_WINNER.getLetter());
		currentPlayer = participants[0];
		plateauActuel = new Plateau((byte)7,(byte)6);
	}
	
	
	
	
	@Override
	public String toString() {
		String str = "Jeu [participants=" + Arrays.toString(participants) + ", currentPlayer=" + currentPlayer + ", nom="
				+ nom;
		if (parties.size() > 0) {
			str += ", ";
			for (int i = 0 ; i < parties.size();i++) {
				str += "parties["+i+"]=[";
				str += parties.get(i).toString();
				str += "]";
				if (i+1 < parties.size())
					str+=", ";
			}
		}
		str += "]";
		return str;
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
			plateauActuel.afficherGrille();
			do {
				do {
					System.out.println("C'est à " + currentPlayer.getNom() + " de jouer. Quel colonne(0-6)");
					column = sc.nextInt();
				} while (column <  0 || column >= 7);
				if (plateauActuel.ajouterCoup((byte)column,currentPlayer)) {
					havePlayed = true;
				}
			} while (!havePlayed);
			win = plateauActuel.getWin();
			if (win != ' ') {
				if (win == participants[0].getCouleur()) {
					winner = participants[0];
				} else {
					winner = participants[1];
				}
				plateauActuel.afficherGrille();
				System.out.println(winner.getNom() + " a gagné!");
				parties.add(plateauActuel);
				
			} else {
				changerJoueur();
				havePlayed = false;
			}			
		}
		sc.close();
		return winner;
	}
}
