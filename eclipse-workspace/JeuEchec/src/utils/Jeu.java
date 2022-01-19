package utils;
import java.util.ArrayList;

import pieces.Piece;

public class Jeu {
	private ArrayList<Plateau> parties;
	private Plateau plateauActuel;
	private Joueur participants[];
	private Joueur joueurActuel;
	
	public Jeu(String nomJ1, String nomJ2) {
		parties = new ArrayList<Plateau>();
		plateauActuel = new Plateau((byte)8,(byte)9);
		participants = new Joueur[2];
		participants[0]=new Joueur(nomJ1,true);
		participants[1]=new Joueur(nomJ2,false);
		joueurActuel = participants[0];
	}
	
	public void changerJoueur() {
		if (joueurActuel == participants[0]) {
			joueurActuel = participants[1];
		} else {
			joueurActuel = participants[0];
		}
	}
	
	public void gererJeu() {
		System.out.println(plateauActuel);
		Piece blanc = plateauActuel.getPieceFromColor(true).get(0);
		System.out.println(blanc);
		ArrayList<Mouvement> echec = blanc.estEnEchec(plateauActuel);
		if (echec.size() > 0) {
			System.out.print("est en echec");
			Piece tmp = null;
			for (int i = 0; i < echec.size();i++) {
				tmp = plateauActuel.getPieceFromCoord(echec.get(i).getFrom());
				if (tmp != null)
					System.out.println(tmp);
			}
		}
	}
	
	
	
}
