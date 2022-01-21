package utils;
import java.util.ArrayList;

import pieces.Piece;
import pieces.Piece_Roi;

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
		
		Piece blanc2 = plateauActuel.getPieceFromCoord(new Coordonees((byte)3,(byte)7));
		System.out.println(blanc2);
		Piece roiNoir = null;
		ArrayList<Mouvement> echec = null;
		
		if (plateauActuel.deplacerPiece(new Coordonees(blanc2.getPosition()), new Coordonees((byte)4, (byte)7), blanc2.estNoir())) {
			System.out.println("Mouvement effectue");
			System.out.println(plateauActuel);
			roiNoir = plateauActuel.getRoi(true);
			
			System.out.println(roiNoir);
			echec= roiNoir.estEnEchec(plateauActuel);
			
			if (echec.size() > 0) {
				System.out.println("est en echec par: ");
				Piece tmp = null;
				for (int i = 0; i < echec.size();i++) {
					
					tmp = plateauActuel.getPieceFromCoord(echec.get(i).getFrom());
					if (tmp != null) {
						System.out.println(tmp);
					}
					
				}
			}
		} else {
			System.out.println("Mouvement impossible");
			
		}
		roiNoir = plateauActuel.getRoi(true);
		if(((Piece_Roi)roiNoir).estEchecEtMat(plateauActuel)) {
			System.out.println(roiNoir + " est en echec et mat");
		} else {
			System.out.println(roiNoir + " n'est pas echec et mat");
			
		}

	}
	
	
	
}
