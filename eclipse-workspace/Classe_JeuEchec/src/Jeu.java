import java.util.ArrayList;

public class Jeu {
	private Plateau monPlateau;
	private ArrayList<Plateau> parties;
	private Joueur[] participants;
	public Joueur joueurActuel;
	
	
	public Jeu(String _nomJ1, String _nomJ2) {
		parties = new ArrayList<Plateau>();
		monPlateau = null;
		participants = new Joueur[2];
		participants[0] = new Joueur(_nomJ1,true);
		participants[1] = new Joueur(_nomJ1,false);
		joueurActuel = participants[0];
		monPlateau = new Plateau((byte)8,(byte)9);
		monPlateau.reset();
	}
	

	
	public void changerJoueur() {
		if (joueurActuel == participants[0]) {
			joueurActuel = participants[1];
		} else {
			joueurActuel = participants[0];
		}
		
	}
	
	public void gererJeu() {
		while(monPlateau.verifierPlateau() != GAMESTATUS.BLACK_CHECKMATE && monPlateau.verifierPlateau() != GAMESTATUS.WHITE_CHECKMATE) {
			
		}
	}
}
